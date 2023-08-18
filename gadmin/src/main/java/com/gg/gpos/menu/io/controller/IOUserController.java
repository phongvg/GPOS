package com.gg.gpos.menu.io.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gpos.common.constant.ErrorImportEnum;
import com.gg.gpos.common.constant.ExcelNameEnum;
import com.gg.gpos.common.constant.FunctionTypeEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.SheetNameEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.io.dto.IOAppUserDto;
import com.gg.gpos.user.manager.AppUserManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IOUserController extends BaseController{

	@Autowired
	private AppUserManager appUserManager;
	
	@PostMapping("/system/user/import")
    public String importUser (RedirectAttributes redirectAttrs,	@RequestParam(name = "inputImport", required = false) MultipartFile excelFile, HttpServletRequest request) {
    	log.info("ENTERING 'IMPORT USER' METHOD...");
    	String[] headerText = headerTextAppUser(request);
    	String url = "";
		try {
			url = processReadFileExcelImportUser(excelFile,headerText);
			addMessage(request, getText("import.status.success", request.getLocale()));
		} catch (Exception e) {
			log.error("ERROR IMPORT USER, EXCEPTION: {}", e);
			addError(request, getText("import.status.error", request.getLocale()));
		}
    	
    	if(url != null && !url.isEmpty()) {
        	redirectAttrs.addFlashAttribute("url", url);
    	}
        return "redirect:/system/user/list";
    }
	
    
	//============= Function IMPORT and EXPORT USER ================//
	
    private String[] headerTextAppUser(HttpServletRequest request) {
    	Locale locale = request.getLocale();
    	String[] headerText = {
    			getText("userForm.username", request.getLocale()),getText("user.password", request.getLocale()), getText("userForm.fullname", request.getLocale()),
				getText("userForm.email", request.getLocale()), getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
    
    private void writeHeader(XSSFSheet sheet, Integer sumCell, String[] headerText) {
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < sumCell; i++) {
			headerRow.createCell(i).setCellValue(headerText[i]);
		}
	}
    
    /*
     * Đọc và import file excel
     */
    private String processReadFileExcelImportUser(MultipartFile excelFile,String[] headerText) throws Exception {
    	log.debug("PROCESS: READ FILE EXCEL AND IMPORT");
    	List<IOAppUserDto> ioAppUserDtos = new ArrayList<>();
		int i = 1;
		XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOAppUserDto ioAppUserDto = new IOAppUserDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioAppUserDto.setUsername(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioAppUserDto.setUsername(value);
					}
				} catch (Exception e) {
					ioAppUserDto.setUsername(null);
					ioAppUserDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field UserName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioAppUserDto.setPassword(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioAppUserDto.setPassword(value);
					}
				} catch (Exception e) {
					ioAppUserDto.setPassword(null);
					ioAppUserDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Password Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioAppUserDto.setFullName(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioAppUserDto.setFullName(value);
					}
				} catch (Exception e) {
					ioAppUserDto.setFullName(null);
					ioAppUserDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FullName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING)) {
						ioAppUserDto.setEmail(dataRow.getCell(3).getStringCellValue());
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(3).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioAppUserDto.setEmail(value);
					}
				} catch (Exception e) {
					ioAppUserDto.setEmail(null);
					ioAppUserDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Email Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioAppUserDto.setStatus(true);
			ioAppUserDtos.add(ioAppUserDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOAppUserDto> ioAppUserErrs = appUserManager.importAppUser(ioAppUserDtos);
		
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioAppUserErrs)) {
			url.append(processExportUser(ioAppUserErrs,headerText));
		}
		workbook.close();
		return url.toString(); 
	};
    
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String processExportUser(List<IOAppUserDto> ioAppUserDtos, String[] headerText) throws IOException {
		String attachmentPath = appProperties.getAttachmentPath();
		String attachmentContextPath = appProperties.getAttachmentContextPath();
		Integer sumCell = 6;
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_APPUSER.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = attachmentPath + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.APPUSER.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = attachmentContextPath + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.APPUSER.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.APPUSER.val);
			writeHeader(sheet, sumCell, headerText);
			writeAppUser(sheet,ioAppUserDtos);
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
	 * Ghi các bản ghi bị lỗi ra file excel
	 */
	private void writeAppUser(XSSFSheet sheet,List<IOAppUserDto> iAppUsers) {
		Integer rowNum = 1;
		if(iAppUsers != null && !iAppUsers.isEmpty()) {
			for(IOAppUserDto iApp: iAppUsers) {
				if(!iApp.isStatus()) {
					Row row = sheet.createRow(rowNum++);
					Integer column = 0;
					//generate 
					Cell cell = row.createCell(column++);
					cell.setCellValue(iApp.getUsername() != null ? iApp.getUsername() : "");
					cell = row.createCell(column++);
					cell.setCellValue(iApp.getPassword() != null ? iApp.getPassword() : "");
					cell = row.createCell(column++);
					cell.setCellValue(iApp.getFullName() != null ? iApp.getFullName() : "");
					cell = row.createCell(column++);
					cell.setCellValue(iApp.getEmail() != null ? iApp.getEmail() : "");
					cell = row.createCell(column++);
					cell.setCellValue(iApp.isStatus() ? "Thêm thành công" : "Lỗi");
					cell = row.createCell(column++);
					cell.setCellValue(iApp.getError() != null ? iApp.getError() : "");
				}
			}
		}
	}
	
}
