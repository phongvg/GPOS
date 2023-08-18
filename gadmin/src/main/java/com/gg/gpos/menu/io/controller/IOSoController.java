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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gg.gpos.common.constant.ErrorImportEnum;
import com.gg.gpos.common.constant.ExcelNameEnum;
import com.gg.gpos.common.constant.FunctionTypeEnum;
import com.gg.gpos.common.constant.InitDoubleValueEnum;
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.SheetNameEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.helper.FileNameHelper;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.io.dto.IOSoDto;
import com.gg.gpos.menu.dto.SoDto;
import com.gg.gpos.menu.manager.SoCategoryManager;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IOSoController extends BaseController{
	@Autowired
	private SoCategoryManager soCategoryManager;
	@Autowired
	private FileNameHelper fileNameHelper;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setAutoGrowCollectionLimit(5000);
	}
	
	//============= Controller IMPORT and EXPORT SO ================//
	@GetMapping("/export/so-menu")
	public void exportSoCatalog(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name = "soId", required = false) Long soId,@RequestParam(name = "resCode", required = false) Integer resCode) throws IOException {
		log.info("ENTERING 'EXPORT SO' METHOD...");
		Integer sumCell = 20;
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Menu Restaurant");
			writeHeader(sheet, sumCell, headerSoText(request));
			processExportSo(sheet, soId ,resCode);
			String fileLocation = fileNameHelper.createNameFile("Menu_Restaurant");
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
	
	@PostMapping("/import/so-menu")
    public String importSoCatalog(@Valid SoDto soDto, HttpServletRequest request, RedirectAttributes redirectAttrs)throws IOException, ParseException  {
		log.info("ENTERING 'IMPORT SO_MENU' METHOD...");
    	String url = "";
    	if (soDto.getFileImport() != null && soDto.getId() != null) {
    		try {
    			url = processReadFileExcelAndImportSoMenu(soDto, headerSoText(request));
    			if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				addError(request, getText("import.status.error", request.getLocale()));
				log.error("ERROR IMPORT SO_MENU, EXCEPTION: {}", e);
			}
    	}
        return "redirect:/cag/so/list";
    }
	//============= Controller IMPORT and EXPORT SO ================//
	
	//============= Function IMPORT and EXPORT SO ================//
	/*
	 * function export SO
	 */
	private void processExportSo(XSSFSheet sheet, Long soId, Integer resCode) {
		log.debug("PROCESS: EXPORT SO BY SO_ID OR RESTAURANT_CODE, SO_ID: {}, RESTAURANT_CODE: {}",soId, resCode);
		List<IOSoDto> ioSoDtos = soCategoryManager.getIOSoDtosBysoIdOrResCode(soId, resCode);
		writeSoDataLines(sheet, ioSoDtos);
	}

	/*
	 * function import SO_MENU
	 */
	private String processReadFileExcelAndImportSoMenu(SoDto soDto, String[] headerText) throws Exception {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT DATA SO_MENU");
		List<IOSoDto> ioSoDtos = new ArrayList<>();
		int i = 1;
		XSSFWorkbook workbook = new XSSFWorkbook(soDto.getFileImport().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOSoDto ioSoDto = new IOSoDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setOrderCategoryName(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setOrderCategoryName(value);
					}
				} catch (Exception e) {
					ioSoDto.setOrderCategoryName(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field OrderCategoryName Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setOrderCategoryCode(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setOrderCategoryCode(value);
					}
				} catch (Exception e) {
					ioSoDto.setOrderCategoryCode(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field OrderCategoryCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupParentNameVn(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupParentNameVn(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupParentNameVn(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupParentNameVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupParentNameEn(dataRow.getCell(3).getStringCellValue());
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupParentNameVn(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupParentNameEn(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupParentNameEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(4) != null) {
				try {
					if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupParentCode(dataRow.getCell(4).getStringCellValue());
					} else if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(4).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(4).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupParentCode(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupParentCode(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupParentCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(5) != null) {
				try {
					if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupParentType(dataRow.getCell(5).getStringCellValue());
					} else if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(5).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(5).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupParentType(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupParentType(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupParentType Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(6) != null) {
				try {
					if(dataRow.getCell(6).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setGroupOrder(Double.valueOf(dataRow.getCell(6).getStringCellValue()));
					} else if(dataRow.getCell(6).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioSoDto.setGroupOrder(dataRow.getCell(6).getNumericCellValue());
					}
				} catch (Exception e) {
					ioSoDto.setGroupOrder(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field GroupOrder Error With Line: {}, Exception: {}", i, e);
					continue;
				}
				
			}
			if(dataRow.getCell(7) != null) {
				try {
					if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupNameVn(dataRow.getCell(7).getStringCellValue());
					} else if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(7).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(7).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupNameVn(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupNameVn(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupNameVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(8) != null) {
				try {
					if(dataRow.getCell(8).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupNameEn(dataRow.getCell(8).getStringCellValue());
					} else if(dataRow.getCell(8).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(8).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(8).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupNameEn(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupNameEn(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupNameEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(9) != null) {
				try {
					if(dataRow.getCell(9).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupCode(dataRow.getCell(9).getStringCellValue());
					} else if(dataRow.getCell(9).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(9).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(9).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupCode(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupCode(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(10) != null) {
				try {
					if(dataRow.getCell(10).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupType(dataRow.getCell(10).getStringCellValue());
					} else if(dataRow.getCell(10).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(10).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(10).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodGroupType(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupType(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupType Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(11) != null) {
				try {
					if(dataRow.getCell(11).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodItemCode(dataRow.getCell(11).getStringCellValue());
					} else if(dataRow.getCell(11).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(11).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(11).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodItemCode(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodItemCode(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(12) != null) {
				try {
					if(dataRow.getCell(12).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodItemNameVn(dataRow.getCell(12).getStringCellValue());
					} else if(dataRow.getCell(12).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(12).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(12).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setFoodItemNameVn(value);
					}
				} catch (Exception e) {
					ioSoDto.setFoodItemNameVn(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemNameVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(13) != null) {
				try {
					if(dataRow.getCell(13).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setMenuTypeCode(Double.valueOf(dataRow.getCell(13).getStringCellValue()));
					} else if(dataRow.getCell(13).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioSoDto.setMenuTypeCode(dataRow.getCell(13).getNumericCellValue());
					}
				} catch (Exception e) {
					ioSoDto.setMenuTypeCode(InitDoubleValueEnum.ZERO.val);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field MenuTypeCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			} else {
				ioSoDto.setMenuTypeCode(InitDoubleValueEnum.ZERO.val);
			}
			
			if(dataRow.getCell(14) != null) {
				try {
					if(dataRow.getCell(14).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setFoodGroupParentLevel(Double.valueOf(dataRow.getCell(14).getStringCellValue()));
					} else if(dataRow.getCell(14).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioSoDto.setFoodGroupParentLevel(dataRow.getCell(14).getNumericCellValue());
					}
				} catch (Exception e) {
					ioSoDto.setFoodGroupParentLevel(InitDoubleValueEnum.ONE.val);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodGroupParentLevel Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			} else {
				ioSoDto.setFoodGroupParentLevel(InitDoubleValueEnum.ONE.val);
			}

			if(dataRow.getCell(15) != null) {
				try {
					if(dataRow.getCell(15).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setItemOrder(Double.valueOf(dataRow.getCell(15).getStringCellValue()));
					} else if(dataRow.getCell(15).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioSoDto.setItemOrder(dataRow.getCell(15).getNumericCellValue());
					}
				} catch (Exception e) {
					ioSoDto.setItemOrder(InitDoubleValueEnum.ZERO.val);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ItemOrder Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			} else {
				ioSoDto.setItemOrder(InitDoubleValueEnum.ZERO.val);
			}
			if(dataRow.getCell(16) != null) {
				try {
					if(dataRow.getCell(16).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setSrcImg(dataRow.getCell(16).getStringCellValue());
					} else if(dataRow.getCell(16).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(16).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(16).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setSrcImg(value);
					}
				} catch (Exception e) {
					ioSoDto.setSrcImg(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AvatarUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(17) != null) {
				try {
					if(dataRow.getCell(17).getCellTypeEnum().equals(CellType.STRING)) {
						ioSoDto.setSapCode(dataRow.getCell(17).getStringCellValue());
					} else if(dataRow.getCell(17).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(17).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(17).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioSoDto.setSapCode(value);
					}
				} catch (Exception e) {
					ioSoDto.setSapCode(null);
					ioSoDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field SapCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioSoDto.setStatus(true);
			ioSoDtos.add(ioSoDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOSoDto> ioSoErrs =  soCategoryManager.importSoMenu(ioSoDtos, soDto.getId(), appProperties.getAttachmentPath());
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioSoErrs)) {
			url.append(exportSoMenuLineErrsAfterImport(ioSoErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
	
	private String[] headerSoText(HttpServletRequest request) {
		Locale locale = request.getLocale();
		String[] headerText = {
				getText("order.type", locale),getText("order.category.code", locale), 
				getText("export.so.foodGroup.name", locale),getText("export.so.foodGroup.name.en", locale),
				getText("export.so.foodGroup.code", locale), getText("export.so.foodGroup.type", locale), 
				getText("export.so.soCatgoryFoodGroup.groupOrder", locale), getText("export.so.group.child", locale),
				getText("export.so.group.child.en", locale),getText("export.so.group.child.code", locale), 
				getText("export.so.group.child.type", locale),getText("foodItem.code", locale), 
				getText("foodItem.name", locale), getText("export.so.menuType.code", locale),
				getText("export.so.group.level", locale), getText("export.so.itemOrder", locale),
				getText("foodItem.avatarUrl", locale),getText("foodItem.sapcode", request.getLocale()),
				getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	
	/*
	 * Ghi các bản ghi SO ra file excel
	 */
	private void writeSoDataLines(XSSFSheet sheet, List<IOSoDto> ioSoDtos) {
		Integer rowNum = 1;
		for(IOSoDto item : ioSoDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getOrderCategoryName() != null ? item.getOrderCategoryName() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getOrderCategoryCode() != null ? item.getOrderCategoryCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupParentNameVn() != null ? item.getFoodGroupParentNameVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupParentNameEn() != null ? item.getFoodGroupParentNameEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupParentCode() != null ? item.getFoodGroupParentCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupParentType() != null ? item.getFoodGroupParentType() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getGroupOrder() != null ? item.getGroupOrder().toString() : "0");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupNameVn() != null ? item.getFoodGroupNameVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupNameEn() != null ? item.getFoodGroupNameEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupCode() != null ? item.getFoodGroupCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodGroupType() != null ? item.getFoodGroupType() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemCode() != null ? item.getFoodItemCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemNameVn() != null ? item.getFoodItemNameVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getMenuTypeCode() != null ? item.getMenuTypeCode() : 0);
			cell = row.createCell(column++);
			cell.setCellValue((item.getFoodGroupParentLevel() != null) ? item.getFoodGroupParentLevel() : 0);
			cell = row.createCell(column++);
			cell.setCellValue((item.getItemOrder() != null) ? item.getItemOrder() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getSrcImg() != null ? item.getSrcImg() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getSapCode() != null ? item.getSapCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportSoMenuLineErrsAfterImport(List<IOSoDto> ioSoErrs, String[] headerText) throws IOException {
		Integer sumCell = 20;
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_SO.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = appProperties.getAttachmentPath() + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.MENU_RESTAURANT.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = appProperties.getAttachmentContextPath() + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.MENU_RESTAURANT.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.MENU_RESTAURANT.val);
			writeHeader(sheet, sumCell, headerText);
			writeSoDataLines(sheet, ioSoErrs);
			FileOutputStream newExcel = new FileOutputStream(filePath);
			workbook.write(newExcel);
			
			newExcel.close();
			workbook.close();
			
			
		} finally {
			workbook.close();
		}
		
		return builderUrl.toString();
	}
	
	private void writeHeader(XSSFSheet sheet, Integer sumCell, String[] headerText) {
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < sumCell; i++) {
			headerRow.createCell(i).setCellValue(headerText[i]);
		}
	}
	//============= Function IMPORT and EXPORT SO ================//
}
