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
import com.gg.gpos.io.dto.IOKdsAccountDto;
import com.gg.gpos.io.dto.IOKdsConfigCookingDto;
import com.gg.gpos.io.dto.IOKdsPlaceDto;
import com.gg.gpos.menu.dto.KdsAccountDto;
import com.gg.gpos.menu.dto.KdsConfigCookingDto;
import com.gg.gpos.menu.dto.KdsPlaceDto;
import com.gg.gpos.menu.manager.KdsAccountManager;
import com.gg.gpos.menu.manager.KdsConfigCookingManager;
import com.gg.gpos.menu.manager.KdsPlaceManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IOKdsController extends BaseController {
	@Autowired
	private KdsPlaceManager kdsPlaceManager;
	@Autowired
	private KdsAccountManager kdsAccountManager;
	@Autowired
	private KdsConfigCookingManager kdsConfigCookingManager;
	@Autowired
	private FileNameHelper fileNameHelper;
	
	//============= Controller IMPORT and EXPORT KDS_PLACE  ================//
	@GetMapping("/export/kdsPlace")
	public void exportKdsPlace(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name = "rCode", required = true) Integer resCode) throws IOException {
		log.info("ENTERING 'EXPORT KDS_PLACE' METHOD...");
		Integer sumCell = 7;
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Kds_Place");
			writeHeader(sheet, sumCell, headerKdsPlaceText(request));
			processExportPrintGroup(sheet, resCode);
			String fileLocation = fileNameHelper.createNameFile("Kds_Place");
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
	
	@PostMapping("/import/kdsPlace")
    public String importDataKdsPlace(@Valid KdsPlaceDto kdsPlaceDto, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		log.info("ENTERING 'IMPORT KDS_PLACE' METHOD...");
    	String url = "";
    	if (kdsPlaceDto.getMultipartFile() != null) {
			try {
				url = processReadFileExcelAndImportKdsPlace(kdsPlaceDto, headerKdsPlaceText(request));
				if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				log.error("ERROR IMPORT KDS_PLACE, EXCEPTION: {}", e);
				addError(request, getText("import.status.error", request.getLocale()));
			}
		}
        return "redirect:/res/kdsPlace/list?rCode=" + kdsPlaceDto.getRestaurantCode();
    }
	//============= Controller IMPORT and EXPORT KDS_PLACE  ================//
	
	//============= Function IMPORT and EXPORT KDS_PLACE ================//
	/*
	 * function export KDS_PLACE
	 */
	private void processExportPrintGroup(XSSFSheet sheet, Integer restaurantCode) {
		log.debug("PROCESS: EXPORT KDS_PLACE BY RESTAURANT_CODE, RESTAURANT_CODE: {}", restaurantCode);
		List<IOKdsPlaceDto> ioKdsPlaceDtos = kdsPlaceManager.getIOKdsPlacesByRestaurantCode(restaurantCode);
		writeKdsPlaceDataLines(sheet, ioKdsPlaceDtos);
	}
	
	/*
	 * function import KDS_PLACE
	 */
	public String processReadFileExcelAndImportKdsPlace(KdsPlaceDto kdsPlaceDto, String[] headerText) throws Exception,ParseException {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT DATA KDS_PLACE");
		
		List<IOKdsPlaceDto> ioKdsPlaceDtos = new ArrayList<>();
		Integer resCode = kdsPlaceDto.getRestaurantCode(); 
		int i = 1;
		
		XSSFWorkbook workbook = new XSSFWorkbook(kdsPlaceDto.getMultipartFile().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOKdsPlaceDto ioKdsPlaceDto = new IOKdsPlaceDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsPlaceDto.setCode(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsPlaceDto.setCode(value);
					}
				} catch (Exception e) {
					ioKdsPlaceDto.setCode(null);
					ioKdsPlaceDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Code Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsPlaceDto.setName(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsPlaceDto.setName(value);
					}
				} catch (Exception e) {
					ioKdsPlaceDto.setName(null);
					ioKdsPlaceDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Name Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsPlaceDto.setType(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsPlaceDto.setType(value);
					}
				} catch (Exception e) {
					ioKdsPlaceDto.setType(null);
					ioKdsPlaceDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Type Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsPlaceDto.setHallplanIds(dataRow.getCell(3).getStringCellValue());
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(3).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsPlaceDto.setHallplanIds(value);
					}
				} catch (Exception e) {
					ioKdsPlaceDto.setHallplanIds(null);
					ioKdsPlaceDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field HallplanIds Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(4) != null) {
				try {
					if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsPlaceDto.setPrinter(dataRow.getCell(4).getStringCellValue());
					} else if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(4).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(4).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsPlaceDto.setPrinter(value);
					}
				} catch (Exception e) {
					ioKdsPlaceDto.setPrinter(null);
					ioKdsPlaceDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Printer Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioKdsPlaceDto.setRestaurantCode(resCode);
			ioKdsPlaceDtos.add(ioKdsPlaceDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOKdsPlaceDto> ioKdsPlaceErrs =  kdsPlaceManager.importKdsPlace(ioKdsPlaceDtos, resCode);
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioKdsPlaceErrs)) {
			url.append(exportKdsPlaceLineErrsAfterImport(ioKdsPlaceErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportKdsPlaceLineErrsAfterImport(List<IOKdsPlaceDto> ioKdsPlaceErrs, String[] headerText) throws IOException {
		Integer sumCell = 7;
		String attachmentPath = appProperties.getAttachmentPath();
		String attachmentContextPath = appProperties.getAttachmentContextPath();
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_KDS_PLACE.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = attachmentPath + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.KDS_PLACE.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = attachmentContextPath + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.KDS_PLACE.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.PRINT_GROUP.val);
			writeHeader(sheet, sumCell, headerText);
			writeKdsPlaceDataLines(sheet, ioKdsPlaceErrs);
			FileOutputStream newExcel = new FileOutputStream(filePath);
			workbook.write(newExcel);
			newExcel.close();
			workbook.close();
		} finally {
			workbook.close();
		}
		return builderUrl.toString();
	}
	
	private String[] headerKdsPlaceText(HttpServletRequest request) {
		Locale locale = request.getLocale();
    	String[] headerText = {
    			getText("kds.place.code", locale), getText("kds.place.name", locale),
				getText("kds.place.type", locale), getText("hallplan", locale),
				getText("printer", locale),getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	
	/*
	 * Ghi các bản ghi KDS_PLACE ra file excel
	 */
	private void writeKdsPlaceDataLines(XSSFSheet sheet, List<IOKdsPlaceDto> ioKdsPlaceDtos) {
		Integer rowNum = 1;
		for(IOKdsPlaceDto item : ioKdsPlaceDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getCode() != null ? item.getCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getName() != null ? item.getName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getType() != null ? item.getType() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getHallplanIds() != null ? item.getHallplanIds() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getPrinter() != null ? item.getPrinter() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	//============= Function IMPORT and EXPORT KDS_PLACE ================//
	
	//============= Controller IMPORT and EXPORT KDS_CONFIG_COOKING  ================//
	@GetMapping("/export/kdsConfigCooking")
	public void exportKdsConfigCooking(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name = "rCode", required = true) Integer resCode) throws IOException {
		log.info("ENTERING 'EXPORT KDS_CONFIG_COOKING' METHOD...");
		Integer sumCell = 10;
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Kds_Config_Cooking");
			writeHeader(sheet, sumCell, headerKdsConfigCookingText(request));
			processExportKdsConfigCooking(sheet, resCode);
			String fileLocation = fileNameHelper.createNameFile("Kds_Config_Cooking");
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
	
	@PostMapping("/import/kdsConfigCooking")
    public String importDataKdsConfigCooking(@Valid KdsConfigCookingDto kdsConfigCookingDto, RedirectAttributes redirectAttrs, HttpServletRequest request) {
    	log.info("ENTERING 'IMPORT KDS_CONFIG_COOKING' METHOD...");
    	String url = "";
    	if (kdsConfigCookingDto.getMultipartFile() != null) {
			try {
				url = processReadFileExcelAndImportKdsConfigCooking(kdsConfigCookingDto, headerKdsConfigCookingText(request));
				if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				log.error("ERROR IMPORT KDS_CONFIG_COOKING, EXCEPTION: {}", e);
				addError(request, getText("import.status.error", request.getLocale()));
			}
		}
        return "redirect:/res/kdsConfigCooking/list?rCode=" + kdsConfigCookingDto.getRestaurantCode();
    }
	
	//============= Function IMPORT and EXPORT KDS_CONFIG_COOKING ================//
	/*
	 * function export KDS_CONFIG_COOKING
	 */
	private void processExportKdsConfigCooking(XSSFSheet sheet, Integer restaurantCode) {
		log.debug("PROCESS: EXPORT KDS_CONFIG_COOKING BY RESTAURANT_CODE, RESTAURANT_CODE: {}", restaurantCode);
		List<IOKdsConfigCookingDto> ioKdsConfigCookingDtos = kdsConfigCookingManager.getIOKdsConfigCookingsByRestaurantCode(restaurantCode);
		writeKdsConfigCookingDataLines(sheet, ioKdsConfigCookingDtos);
	}
	
	/*
	 * function import KDS_CONFIG_COOKING
	 */
	public String processReadFileExcelAndImportKdsConfigCooking (KdsConfigCookingDto kdsConfigCookingDto, String[] headerText) throws Exception,ParseException {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT DATA KDS_CONFIG_COOKING");
		
		List<IOKdsConfigCookingDto> ioKdsConfigCookingDtos = new ArrayList<>();
		Integer resCode = kdsConfigCookingDto.getRestaurantCode(); 
		int i = 1;
		
		XSSFWorkbook workbook = new XSSFWorkbook(kdsConfigCookingDto.getMultipartFile().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOKdsConfigCookingDto ioKdsConfigCookingDto = new IOKdsConfigCookingDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsConfigCookingDto.setFoodItemCode(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsConfigCookingDto.setFoodItemCode(value);
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setFoodItemCode(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsConfigCookingDto.setFoodItemName(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsConfigCookingDto.setFoodItemName(value);
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setFoodItemName(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(2).getStringCellValue().isEmpty()) {
						ioKdsConfigCookingDto.setSingleCookingTime(Double.valueOf(dataRow.getCell(2).getStringCellValue()));
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioKdsConfigCookingDto.setSingleCookingTime(dataRow.getCell(2).getNumericCellValue());
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setSingleCookingTime(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field SingleCookingTime Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(3).getStringCellValue().isEmpty()) {
						ioKdsConfigCookingDto.setMultiCookingTime(Double.valueOf(dataRow.getCell(3).getStringCellValue()));
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioKdsConfigCookingDto.setMultiCookingTime(dataRow.getCell(3).getNumericCellValue());
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setMultiCookingTime(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field MultiCookingTime Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(4) != null) {
				try {
					if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsConfigCookingDto.setFasting(dataRow.getCell(4).getStringCellValue());
					} else if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(4).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(4).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsConfigCookingDto.setFasting(value);
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setFasting(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Fasting Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(5) != null) {
				try {
					if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsConfigCookingDto.setKdsPlaceCode(dataRow.getCell(5).getStringCellValue());
					} else if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(5).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(5).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsConfigCookingDto.setKdsPlaceCode(value);
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setKdsPlaceCode(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field KdsPlaceCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(6) != null) {
				try {
					if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsConfigCookingDto.setGroupTypeKdsCode(dataRow.getCell(6).getStringCellValue());
					} else if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(6).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(6).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsConfigCookingDto.setGroupTypeKdsCode(value);
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setGroupTypeKdsCode(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field GroupTypeKdsCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(7) != null) {
				try {
					if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsConfigCookingDto.setGroupTypeKdsName(dataRow.getCell(7).getStringCellValue());
					} else if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(7).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(7).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsConfigCookingDto.setGroupTypeKdsName(value);
					}
				} catch (Exception e) {
					ioKdsConfigCookingDto.setGroupTypeKdsName(null);
					ioKdsConfigCookingDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field GroupTypeKdsName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioKdsConfigCookingDto.setRestaurantCode(resCode);
			ioKdsConfigCookingDtos.add(ioKdsConfigCookingDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOKdsConfigCookingDto> ioKdsConfigCookingErrs =  kdsConfigCookingManager.importKdsConfigCooking(ioKdsConfigCookingDtos, resCode);
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioKdsConfigCookingErrs)) {
			url.append(exportKdsConfigCookingLineErrsAfterImport(ioKdsConfigCookingErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportKdsConfigCookingLineErrsAfterImport(List<IOKdsConfigCookingDto> ioKdsConfigCookingErrs, String[] headerText) throws IOException {
		Integer sumCell = 10;
		String attachmentPath = appProperties.getAttachmentPath();
		String attachmentContextPath = appProperties.getAttachmentContextPath();
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_KDS_CONFIG_COOKING.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = attachmentPath + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.KDS_CONFIG_COOKING.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = attachmentContextPath + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.KDS_CONFIG_COOKING.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.KDS_CONFIG_COOKING.val);
			writeHeader(sheet, sumCell, headerText);
			writeKdsConfigCookingDataLines(sheet, ioKdsConfigCookingErrs);
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
	 * Ghi các bản ghi KDS_CONFIG_COOKING ra file excel
	 */
	private void writeKdsConfigCookingDataLines(XSSFSheet sheet, List<IOKdsConfigCookingDto> ioKdsConfigCookingDtos) {
		Integer rowNum = 1;
		for(IOKdsConfigCookingDto item : ioKdsConfigCookingDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemCode() != null ? item.getFoodItemCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemName() != null ? item.getFoodItemName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getSingleCookingTime() != null ? item.getSingleCookingTime() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getMultiCookingTime() != null ? item.getMultiCookingTime() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getFasting() != null ? item.getFasting() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getKdsPlaceCode() != null ? item.getKdsPlaceCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getGroupTypeKdsCode() != null ? item.getGroupTypeKdsCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getGroupTypeKdsName() != null ? item.getGroupTypeKdsName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	
	private String[] headerKdsConfigCookingText(HttpServletRequest request) {
		Locale locale = request.getLocale();
		String[] headerText = {
    			getText("foodItem.code", locale), getText("foodItem.name.vn", locale),
				getText("kds.config.cooking.single.cooking.time", locale), getText("kds.config.cooking.multi.cooking.time", locale),
				getText("kds.config.cooking.fasting", locale), getText("ex.kds.place.code", locale), 
				getText("kds.config.cooking.group.type.kds.code", locale),	getText("kds.config.cooking.group.type.kds.name",locale),
				getText("export.so.status", request.getLocale()),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	//============= Function IMPORT and EXPORT KDS_CONFIG_COOKING ================//
	
	//============= Controller IMPORT and EXPORT KDS_ACCOUNT  ================//
	@GetMapping("/export/kdsAccount")
	public void exportKdsAccount(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name = "rCode", required = true) Integer resCode) throws IOException {
		log.info("ENTERING 'EXPORT KDS_ACCOUNT' METHOD...");
		Integer sumCell = 7;
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Kds_Account");
			writeHeader(sheet, sumCell, headerKdsAccountText(request));
			processExportKdsAccount(sheet, resCode);
			String fileLocation = fileNameHelper.createNameFile("Kds_Account");
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
	
	@PostMapping("/import/kdsAccount")
    public String importDataKds(@Valid KdsAccountDto kdsAccountDto, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		log.info("ENTERING 'IMPORT KDS_ACCOUNT' METHOD...");
    	String url = "";
		if (kdsAccountDto.getMultipartFile() != null) {
			try {
				url = processReadFileExcelAndImportKdsAccount(kdsAccountDto,headerKdsAccountText(request));
				if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				log.error("ERROR IMPORT KDS_ACCOUNT, EXCEPTION: {}", e);
				addError(request, getText("import.status.error", request.getLocale()));
			}
		}
        return "redirect:/res/kdsAccount/list?rCode=" + kdsAccountDto.getRestaurantCode();
    }
	//============= Controller IMPORT and EXPORT KDS_ACCOUNT  ================//
	
	//============= Function IMPORT and EXPORT KDS_ACCOUNT ================//
	/*
	 * function export KDS_ACCOUNT
	 */
	private void processExportKdsAccount(XSSFSheet sheet, Integer restaurantCode) {
		log.debug("PROCESS: EXPORT KDS_ACCOUNT BY RESTAURANT_CODE, RESTAURANT_CODE: {}", restaurantCode);
		List<IOKdsAccountDto> ioKdsAccountDtos = kdsAccountManager.getIOKdsAccountsByRestaurantCode(restaurantCode);
		writeKdsAccountDataLines(sheet, ioKdsAccountDtos);
	}
	
	/*
	 * function import KDS_ACCOUNT
	 */
	public String processReadFileExcelAndImportKdsAccount (KdsAccountDto kdsAccountDto, String[] headerText) throws Exception,ParseException {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT DATA KDS_ACCOUNT");
		
		List<IOKdsAccountDto> ioKdsAccountDtos = new ArrayList<>();
		Integer resCode = kdsAccountDto.getRestaurantCode(); 
		int i = 1;
		
		XSSFWorkbook workbook = new XSSFWorkbook(kdsAccountDto.getMultipartFile().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOKdsAccountDto ioKdsAccountDto = new IOKdsAccountDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsAccountDto.setAccountName(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsAccountDto.setAccountName(value);
					}
				} catch (Exception e) {
					ioKdsAccountDto.setAccountName(null);
					ioKdsAccountDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AccountName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsAccountDto.setPassword(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsAccountDto.setPassword(value);
					}
				} catch (Exception e) {
					ioKdsAccountDto.setPassword(null);
					ioKdsAccountDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Password Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsAccountDto.setParentAccountName(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsAccountDto.setParentAccountName(value);
					}
				} catch (Exception e) {
					ioKdsAccountDto.setParentAccountName(null);
					ioKdsAccountDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ParentAccountName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(3).getStringCellValue().isEmpty()) {
						ioKdsAccountDto.setRole(Double.valueOf(dataRow.getCell(3).getStringCellValue()));
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioKdsAccountDto.setRole(dataRow.getCell(3).getNumericCellValue());
					}
				} catch (Exception e) {
					ioKdsAccountDto.setRole(null);
					ioKdsAccountDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Role Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(4) != null) {
				try {
					if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.STRING)) {
						ioKdsAccountDto.setKdsPlaceCodes(dataRow.getCell(4).getStringCellValue());
					} else if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(4).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(4).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioKdsAccountDto.setKdsPlaceCodes(value);
					}
				} catch (Exception e) {
					ioKdsAccountDto.setKdsPlaceCodes(null);
					ioKdsAccountDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field KdsPlaceCodes Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioKdsAccountDto.setRestaurantCode(resCode);
			ioKdsAccountDtos.add(ioKdsAccountDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOKdsAccountDto> ioKdsAccountErrs =  kdsAccountManager.importKdsAccount(ioKdsAccountDtos, resCode);
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioKdsAccountErrs)) {
			url.append(exportKdsAccountLineErrsAfterImport(ioKdsAccountErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportKdsAccountLineErrsAfterImport(List<IOKdsAccountDto> ioKdsAccountDtos, String[] headerText) throws IOException {
		Integer sumCell = 7;
		String attachmentPath = appProperties.getAttachmentPath();
		String attachmentContextPath = appProperties.getAttachmentContextPath();
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_KDS_ACCOUNT.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = attachmentPath + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.KDS_ACCCOUNT.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = attachmentContextPath + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.KDS_ACCCOUNT.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.KDS_ACCCOUNT.val);
			writeHeader(sheet, sumCell, headerText);
			writeKdsAccountDataLines(sheet,ioKdsAccountDtos);
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
	 * Ghi các bản ghi KDS_ACCOUNT ra file excel
	 */
	private void writeKdsAccountDataLines(XSSFSheet sheet, List<IOKdsAccountDto> ioKdsAccountDtos) {
		Integer rowNum = 1;
		for(IOKdsAccountDto item : ioKdsAccountDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getAccountName() != null ? item.getAccountName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getPassword() != null ? item.getPassword() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getParentAccountName() != null ? item.getParentAccountName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getRole() != null ? item.getRole() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getKdsPlaceCodes() != null ? item.getKdsPlaceCodes() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	
	private String[] headerKdsAccountText(HttpServletRequest request) {
		Locale locale = request.getLocale();
		String[] headerText = {
    			getText("kds.account.name", locale), getText("kds.account.password", locale),
				getText("foodGroup.parent", locale), getText("group.roles", locale),
				getText("hallPlan.codes", locale),
				getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	//============= Function IMPORT and EXPORT KDS_ACCOUNT ================//
	private void writeHeader(XSSFSheet sheet, Integer sumCell, String[] headerText) {
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < sumCell; i++) {
			headerRow.createCell(i).setCellValue(headerText[i]);
		}
	}	
}
