package com.gg.gpos.menu.io.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gpos.common.constant.ErrorImportEnum;
import com.gg.gpos.common.constant.ExcelNameEnum;
import com.gg.gpos.common.constant.FunctionTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.SheetNameEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.helper.FileNameHelper;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.io.dto.IOPrintGroupDto;
import com.gg.gpos.menu.dto.PrintGroupDto;
import com.gg.gpos.menu.manager.PrintGroupManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IOPrintGroupController extends BaseController{
	@Autowired
	private PrintGroupManager printGroupManager;
	@Autowired
	private FileNameHelper fileNameHelper;
	
	@GetMapping("/export/printGroup")
	public void exportPrintGroup(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name = "resCode", required = true) Integer restaurantCode) throws IOException {
		log.info("ENTERING 'EXPORT PRINT_GROUP' METHOD...");
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Print_Group");
			writeHeader(sheet, headerText(request));
			processExportPrintGroup(sheet, restaurantCode);
			String fileLocation = fileNameHelper.createNameFile("Print_Group");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileLocation + "\"");
			response.flushBuffer();			
			workbook.write(response.getOutputStream());
			response.getOutputStream().flush();
		} finally {
			workbook.close();
			response.getOutputStream().close();
		}
	}
	
	@PostMapping("/import/printGroup")
    public String importData (@Valid PrintGroupDto printGroupDto, RedirectAttributes redirectAttrs, HttpServletRequest request)throws IOException, ParseException  {
    	log.info("ENTERING 'IMPORT PRINT_GROUP' METHOD...");
    	String url = "";
    	if (printGroupDto.getMultipartFile() != null) {
    		try {
    			url = processReadFileExcelAndImportPrintGroup(printGroupDto, headerText(request));
    			if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				log.error("ERROR IMPORT PRINT_GROUP, EXCEPTION: {}", e);
				addError(request, getText("import.status.error", request.getLocale()));
			}
    	}
    	if(url != null && !url.isEmpty()) {
        	redirectAttrs.addFlashAttribute("url", url);
    	}
        return "redirect:/printGroup/list?rCode="+printGroupDto.getRestaurantCode();
    }
	
	//============= Function IMPORT and EXPORT PRINT_GROUP ================//
	/*
	 * function export PrintGroup
	 */
	private void processExportPrintGroup(XSSFSheet sheet, Integer restaurantCode) {
		log.debug("PROCESS: EXPORT PRINT_GROUP BY RESTAURANT_CODE, RESTAURANT_CODE: {}", restaurantCode);
		List<IOPrintGroupDto> ioPrintGroupDtos = printGroupManager.getIOPrintGroupsByRestaurantCode(restaurantCode);
		writeDataLines(sheet, ioPrintGroupDtos);
	}
	
	/*
	 * function import PrintGroup
	 */
	public String processReadFileExcelAndImportPrintGroup(PrintGroupDto printGroupDto, String[] headerText) throws Exception,ParseException {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT");
		
		List<IOPrintGroupDto> ioPrintGroupDtos = new ArrayList<>();
		Integer resCode = printGroupDto.getRestaurantCode(); 
		int i = 1;
		
		XSSFWorkbook workbook = new XSSFWorkbook(printGroupDto.getMultipartFile().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOPrintGroupDto ioPrintGroupDto = new IOPrintGroupDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioPrintGroupDto.setPrintGroupCode(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioPrintGroupDto.setPrintGroupCode(value);
					}
				} catch (Exception e) {
					ioPrintGroupDto.setPrintGroupCode(null);
					ioPrintGroupDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field PrintGroupCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioPrintGroupDto.setKitchenType(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioPrintGroupDto.setKitchenType(value);
					}
				} catch (Exception e) {
					ioPrintGroupDto.setKitchenType(null);
					ioPrintGroupDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field KitchenType Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioPrintGroupDto.setFoodItemCode(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioPrintGroupDto.setFoodItemCode(value);
					}
				} catch (Exception e) {
					ioPrintGroupDto.setFoodItemCode(null);
					ioPrintGroupDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING)) {
						ioPrintGroupDto.setFoodItemName(dataRow.getCell(3).getStringCellValue());
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(3).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioPrintGroupDto.setFoodItemName(value);
					}
				} catch (Exception e) {
					ioPrintGroupDto.setFoodItemName(null);
					ioPrintGroupDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioPrintGroupDtos.add(ioPrintGroupDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOPrintGroupDto> ioPrintGroupErrs =  printGroupManager.importPrintGroup(ioPrintGroupDtos, resCode);
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioPrintGroupErrs)) {
			url.append(exportPrintGroupLineErrsAfterImport(ioPrintGroupErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
    
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportPrintGroupLineErrsAfterImport(List<IOPrintGroupDto> ioPrintGroupErrs, String[] headerText) throws IOException {
		String attachmentPath = appProperties.getAttachmentPath();
		String attachmentContextPath = appProperties.getAttachmentContextPath();
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_PRINT_GROUP.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = attachmentPath + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.PRINT_GROUP.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = attachmentContextPath + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.PRINT_GROUP.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.PRINT_GROUP.val);
			writeHeader(sheet, headerText);
			writeDataLines(sheet, ioPrintGroupErrs);
			FileOutputStream newExcel = new FileOutputStream(filePath);
			workbook.write(newExcel);
			newExcel.close();
			workbook.close();
		} finally {
			workbook.close();
		}
		return builderUrl.toString();
	}
	
	/*
	 * Ghi các bản ghi ra file excel
	 */
	private void writeDataLines(XSSFSheet sheet, List<IOPrintGroupDto> ioPrintGroupDtos) {
		Integer rowNum = 1;
		for(IOPrintGroupDto item : ioPrintGroupDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getPrintGroupCode() != null ? item.getPrintGroupCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getKitchenType()!= null ? item.getKitchenType() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemCode() != null ? item.getFoodItemCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemName() != null ? item.getFoodItemName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	
	private String[] headerText(HttpServletRequest request) {
    	Locale locale = request.getLocale();
    	String[] headerText = {
				getText("printGroup.code", locale),getText("kitchen.type", locale), 
				getText("foodItem.code", locale),getText("foodItem.name.vn", locale),
				getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	
	private void writeHeader(XSSFSheet sheet, String[] headerText) {
		Integer sumCell = 6;
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < sumCell; i++) {
			headerRow.createCell(i).setCellValue(headerText[i]);
		}
	}
}
