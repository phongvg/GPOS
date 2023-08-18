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
import com.gg.gpos.common.constant.ModuleTypeEnum;
import com.gg.gpos.common.constant.SheetNameEnum;
import com.gg.gpos.common.constant.SymbolEnum;
import com.gg.gpos.common.helper.FileNameHelper;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.io.dto.IOCoCategoryDto;
import com.gg.gpos.io.dto.IOCoFoodItemDto;
import com.gg.gpos.menu.dto.CoDto;
import com.gg.gpos.menu.manager.CoCategoryManager;
import com.gg.gpos.menu.manager.CoFoodItemManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IOCoController extends BaseController {
	
	@Autowired
	private CoFoodItemManager coFoodItemManager;
	@Autowired
	private CoCategoryManager coCategoryManager;
	@Autowired
	private FileNameHelper fileNameHelper;
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setAutoGrowCollectionLimit(5000);
	}
	
	
	//============= Controller IMPORT and EXPORT CO_FOODITEM  ================//
	@GetMapping("/export/coFoodItem")
	public void exportCoFoodItem(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(name = "coId", required = false) Long coId,@RequestParam(name = "resCode", required = false) Integer resCode) throws IOException {
		log.info("ENTERING 'EXPORT CO_FOODITEM' METHOD...");
		Integer sumCell = 38;
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Customer_Menu");
			writeHeader(sheet, sumCell, headerCoFoodItemText(request));
			processExportCoFoodItem(sheet, coId, resCode);
			String fileLocation = fileNameHelper.createNameFile("customer_menu");
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
	
	@PostMapping("/import/coFoodItem")
    public String importCoFoodItem(@Valid CoDto coDto, RedirectAttributes redirectAttrs, HttpServletRequest request) {
		log.info("ENTERING 'IMPORT CO_FOODITEM' METHOD...");
    	String url = "";
		if (coDto.getFileImport() != null && coDto.getId() != null) {
			try {
				url = processReadFileExcelAndImportCoFoodItem(coDto ,headerCoFoodItemText(request));
				if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				log.error("ERROR IMPORT CO_FOODITEM, EXCEPTION: {}", e);
				addError(request, getText("import.status.error", request.getLocale()));
			}
		} else {
			addError(request, getText("import.status.error", request.getLocale()));
		}
        return "redirect:/co/catalog-list";
    }
	//============= Controller IMPORT and EXPORT CO_FOODITEM ================//
	
	//============= Function IMPORT and EXPORT CO_FOODITEM ================//
	/*
	 * function export CO_FOODITEM
	 */
	private void processExportCoFoodItem(XSSFSheet sheet, Long coId, Integer resCode) {
		log.debug("PROCESS: EXPORT CO_FOODITEM BY CO_ID OR RESTAURANT_CODE, CO_ID: {}, RESTAURANT_CODE: {}",coId, resCode);
		List<IOCoFoodItemDto> ioCoFoodItemDtos = coFoodItemManager.getIOCoFoodItemDtosByCoIdOrResCode(coId, resCode);
		writeCoFoodItemDataLines(sheet, ioCoFoodItemDtos);
	}
	
	/*
	 * function import CO_FOODITEM
	 */
	private String processReadFileExcelAndImportCoFoodItem(CoDto coDto, String[] headerText) throws Exception {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT DATA CO_FOODITEM");
		List<IOCoFoodItemDto> ioCoFoodItemDtos = new ArrayList<>();
		int i = 1;
		XSSFWorkbook workbook = new XSSFWorkbook(coDto.getFileImport().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOCoFoodItemDto ioCoFoodItemDto = new IOCoFoodItemDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setFoodItemCode(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setFoodItemCode(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setFoodItemCode(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setNameVn(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setNameVn(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setNameVn(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field NameVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setNameEn(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setNameEn(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setNameEn(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field NameEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setExtraFoodItem(dataRow.getCell(3).getStringCellValue());
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(3).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setExtraFoodItem(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setExtraFoodItem(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ExtraFoodItem Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(4) != null) {
				try {
					if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setDescVn(dataRow.getCell(4).getStringCellValue());
					} else if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(4).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(4).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setDescVn(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setDescVn(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(5) != null) {
				try {
					if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setDescEn(dataRow.getCell(5).getStringCellValue());
					} else if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(5).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(5).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setDescEn(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setDescEn(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(6) != null) {
				try {
					if(dataRow.getCell(6).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(6).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setMaxPerTransaction(Double.valueOf(dataRow.getCell(6).getStringCellValue()));
					} else if(dataRow.getCell(6).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setMaxPerTransaction(dataRow.getCell(6).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setMaxPerTransaction(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field MaxPerTransaction Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(7) != null) {
				try {
					if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(7).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setMaxForKitchen(Double.valueOf(dataRow.getCell(7).getStringCellValue()));
					} else if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setMaxForKitchen(dataRow.getCell(7).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setMaxForKitchen(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field MaxPerTransaction Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(8) != null) {
				try {
					if(dataRow.getCell(8).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(8).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setKalo(Double.valueOf(dataRow.getCell(8).getStringCellValue()));
					} else if(dataRow.getCell(8).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setKalo(dataRow.getCell(8).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setKalo(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Kalo Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(9) != null) {
				try {
					if(dataRow.getCell(9).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setKaloGroup(dataRow.getCell(9).getStringCellValue());
					} else if(dataRow.getCell(9).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(9).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(9).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setKaloGroup(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setKaloGroup(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field KaloGroup Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(10) != null) {
				try {
					if(dataRow.getCell(10).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setBufferLabel(dataRow.getCell(10).getStringCellValue());
					} else if(dataRow.getCell(10).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(10).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(10).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setBufferLabel(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setBufferLabel(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field BufferLabel Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(11) != null) {
				try {
					if(dataRow.getCell(11).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(11).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setPositionNumber(Double.valueOf(dataRow.getCell(11).getStringCellValue()));
					} else if(dataRow.getCell(11).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setPositionNumber(dataRow.getCell(11).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setPositionNumber(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field PositionNumber Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(12) != null) {
				try {
					if(dataRow.getCell(12).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(12).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setCoImageSize(Double.valueOf(dataRow.getCell(12).getStringCellValue()));
					} else if(dataRow.getCell(12).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setCoImageSize(dataRow.getCell(12).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setCoImageSize(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field CoImageSize Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(13) != null) {
				try {
					if(dataRow.getCell(13).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(13).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setViewType(Double.valueOf(dataRow.getCell(13).getStringCellValue()));
					} else if(dataRow.getCell(13).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setViewType(dataRow.getCell(13).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setViewType(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ViewType Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(14) != null) {
				try {
					if(dataRow.getCell(14).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(14).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setNumberOfPeopleEat(Double.valueOf(dataRow.getCell(14).getStringCellValue()));
					} else if(dataRow.getCell(14).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setNumberOfPeopleEat(dataRow.getCell(14).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setNumberOfPeopleEat(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field NumberOfPeopleEat Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(15) != null) {
				try {
					if(dataRow.getCell(15).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(15).getStringCellValue().isEmpty()) {
						ioCoFoodItemDto.setHideIcon(Double.valueOf(dataRow.getCell(15).getStringCellValue()));
					} else if(dataRow.getCell(15).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoFoodItemDto.setHideIcon(dataRow.getCell(15).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setHideIcon(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field HideIcon Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(16) != null) {
				try {
					if(dataRow.getCell(16).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setNote(dataRow.getCell(16).getStringCellValue());
					} else if(dataRow.getCell(16).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(16).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(16).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setNote(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setNote(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Note Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(17) != null) {
				try {
					if(dataRow.getCell(17).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setNoteQuantitative(dataRow.getCell(17).getStringCellValue());
					} else if(dataRow.getCell(17).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(17).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(17).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setNoteQuantitative(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setNoteQuantitative(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field NoteQuantitative Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(18) != null) {
				try {
					if(dataRow.getCell(18).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setInfoFoodItem(dataRow.getCell(18).getStringCellValue());
					} else if(dataRow.getCell(18).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(18).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(18).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setInfoFoodItem(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setInfoFoodItem(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field InfoFoodItem Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(19) != null) {
				try {
					if(dataRow.getCell(19).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setRelatedFoodItem(dataRow.getCell(19).getStringCellValue());
					} else if(dataRow.getCell(19).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(19).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(19).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setRelatedFoodItem(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setRelatedFoodItem(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field RelatedFoodItem Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(20) != null) {
				try {
					if(dataRow.getCell(20).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setSizeFoodItem(dataRow.getCell(20).getStringCellValue());
					} else if(dataRow.getCell(20).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(20).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(20).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setSizeFoodItem(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setSizeFoodItem(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field SizeFoodItem Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(21) != null) {
				try {
					if(dataRow.getCell(21).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setToppingFoodItem(dataRow.getCell(21).getStringCellValue());
					} else if(dataRow.getCell(21).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(21).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(21).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setToppingFoodItem(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setToppingFoodItem(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ToppingFoodItem Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(22) != null) {
				try {
					if(dataRow.getCell(22).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setModifier(dataRow.getCell(22).getStringCellValue());
					} else if(dataRow.getCell(22).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(22).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(22).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setModifier(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setModifier(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Modifier Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(23) != null) {
				try {
					if(dataRow.getCell(23).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setFeature(dataRow.getCell(23).getStringCellValue());
					} else if(dataRow.getCell(23).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(23).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(23).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setFeature(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setFeature(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Feature Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(24) != null) {
				try {
					if(dataRow.getCell(24).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setAvatarUrl(dataRow.getCell(24).getStringCellValue());
					} else if(dataRow.getCell(24).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(24).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(24).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setAvatarUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setAvatarUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AvatarUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(25) != null) {
				try {
					if(dataRow.getCell(25).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setHalfPhotoUrl(dataRow.getCell(25).getStringCellValue());
					} else if(dataRow.getCell(25).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(25).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(25).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setHalfPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setHalfPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field HalfPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(26) != null) {
				try {
					if(dataRow.getCell(26).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setToppingPhotoUrl(dataRow.getCell(26).getStringCellValue());
					} else if(dataRow.getCell(26).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(26).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(26).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setToppingPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setToppingPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ToppingPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(27) != null) {
				try {
					
					if(dataRow.getCell(27).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setQuarterPhotoUrl(dataRow.getCell(27).getStringCellValue());
					} else if(dataRow.getCell(27).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(27).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(27).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setQuarterPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setQuarterPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field QuarterPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(28) != null) {
				try {
					if(dataRow.getCell(28).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setGroupPhotoUrl(dataRow.getCell(28).getStringCellValue());
					} else if(dataRow.getCell(28).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(28).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(28).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setGroupPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setGroupPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field GroupPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(29) != null) {
				try {
					if(dataRow.getCell(29).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setGroupHiddenPhotoUrl(dataRow.getCell(29).getStringCellValue());
					} else if(dataRow.getCell(29).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(29).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(29).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setGroupHiddenPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setGroupHiddenPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field GroupHiddenPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(30) != null) {
				try {
					if(dataRow.getCell(30).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setHorizontalPhotoUrl(dataRow.getCell(30).getStringCellValue());
					} else if(dataRow.getCell(30).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(30).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(30).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setHorizontalPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setHorizontalPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field HorizontalPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(31) != null) {
				try {
					if(dataRow.getCell(31).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setVerticalPhotoUrl(dataRow.getCell(31).getStringCellValue());
					} else if(dataRow.getCell(31).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(31).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(31).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setVerticalPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setVerticalPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field VerticalPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(32) != null) {
				try {
					if(dataRow.getCell(32).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setDrinkPhotoUrl(dataRow.getCell(32).getStringCellValue());
					} else if(dataRow.getCell(32).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(32).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(32).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setDrinkPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setDrinkPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DrinkPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			

			if(dataRow.getCell(33) != null) {
				try {
					if(dataRow.getCell(33).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setQrOrderPhotoUrl(dataRow.getCell(33).getStringCellValue());
					} else if(dataRow.getCell(33).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(33).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(33).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setQrOrderPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setQrOrderPhotoUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DrinkPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			
			if(dataRow.getCell(34) != null) {
				try {
					if(dataRow.getCell(34).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setThumbnailUrl(dataRow.getCell(34).getStringCellValue());
					} else if(dataRow.getCell(34).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(34).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(34).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setThumbnailUrl(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setThumbnailUrl(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field ThumbnailUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(35) != null) {
				try {
					if(dataRow.getCell(35).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoFoodItemDto.setFoodItemSapCode(dataRow.getCell(35).getStringCellValue());
					} else if(dataRow.getCell(35).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(35).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(35).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoFoodItemDto.setFoodItemSapCode(value);
					}
				} catch (Exception e) {
					ioCoFoodItemDto.setFoodItemSapCode(null);
					ioCoFoodItemDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field FoodItemSapCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			ioCoFoodItemDtos.add(ioCoFoodItemDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOCoFoodItemDto> ioCoFoodItemErrs =  coFoodItemManager.importCoFoodItem(ioCoFoodItemDtos, coDto.isOverride(), coDto.getId(), appProperties.getAttachmentPath());
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioCoFoodItemErrs)) {
			url.append(exportCoFoodItemLineErrsAfterImport(ioCoFoodItemErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportCoFoodItemLineErrsAfterImport(List<IOCoFoodItemDto> ioCoFoodItemDtos, String[] headerText) throws IOException {
		Integer sumCell = 37;
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_CO.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = appProperties.getAttachmentPath() + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.CUSTOMER_MENU.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = appProperties.getAttachmentContextPath() + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.CUSTOMER_MENU.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.CUSTOMER_MENU.val);
			writeHeader(sheet, sumCell, headerText);
			writeCoFoodItemDataLines(sheet, ioCoFoodItemDtos);
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
	 * Ghi các bản ghi CO_FOODITEM ra file excel
	 */
	private void writeCoFoodItemDataLines(XSSFSheet sheet, List<IOCoFoodItemDto> ioCoFoodItemDtos) {
		Integer rowNum = 1;
		for(IOCoFoodItemDto item : ioCoFoodItemDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemCode() != null ? item.getFoodItemCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getNameVn() != null ? item.getNameVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getNameEn() != null ? item.getNameEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getExtraFoodItem() != null ? item.getExtraFoodItem() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescVn() != null ? item.getDescVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescEn() != null ? item.getDescEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getMaxPerTransaction() != null ? item.getMaxPerTransaction() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getMaxForKitchen() != null ? item.getMaxForKitchen() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getKalo() != null ? item.getKalo() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getKaloGroup() != null ? item.getKaloGroup() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getBufferLabel() != null ? item.getBufferLabel() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getPositionNumber() != null ? item.getPositionNumber() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getCoImageSize() != null ? item.getCoImageSize() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getViewType() != null ? item.getViewType() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getNumberOfPeopleEat() != null ? item.getNumberOfPeopleEat() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getHideIcon() != null ? item.getHideIcon() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getNote() != null ? item.getNote() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getNoteQuantitative() != null ? item.getNoteQuantitative() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getInfoFoodItem() != null ? item.getInfoFoodItem() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getRelatedFoodItem() != null ? item.getRelatedFoodItem() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getSizeFoodItem() != null ? item.getSizeFoodItem() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getToppingFoodItem() != null ? item.getToppingFoodItem() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getModifier() != null ? item.getModifier() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFeature() != null ? item.getFeature() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getAvatarUrl() != null ? item.getAvatarUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getHalfPhotoUrl() != null ? item.getHalfPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getToppingPhotoUrl() != null ? item.getToppingPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getQuarterPhotoUrl() != null ? item.getQuarterPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getGroupPhotoUrl() != null ? item.getGroupPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getGroupHiddenPhotoUrl() != null ? item.getGroupHiddenPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getHorizontalPhotoUrl() != null ? item.getHorizontalPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getVerticalPhotoUrl() != null ? item.getVerticalPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDrinkPhotoUrl() != null ? item.getDrinkPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getQrOrderPhotoUrl() != null ? item.getQrOrderPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getThumbnailUrl() != null ? item.getThumbnailUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getFoodItemSapCode() != null ? item.getFoodItemSapCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	
	private String[] headerCoFoodItemText(HttpServletRequest request) {
		Locale locale = request.getLocale();
    	String[] headerText = {
				getText("foodItem.code", locale), getText("foodItem.name.vn", locale), 
				getText("foodItem.name.en", locale), getText("extra.fooditem", locale), 
				getText("foodItem.desc.vn", locale), getText("foodItem.desc.en", locale),
				getText("export.co.maxPerTransaction", locale), getText("export.co.maxForKitchen", locale), 
				getText("foodItem.kalo", locale), getText("cofoodItem.kaloGroup", locale),
				getText("export.co.nameBF", locale), getText("export.co.comboIndex", locale), 
				getText("export.co.image.size", locale), getText("export.co.foodItem.view.type", locale),
				getText("coFoodItem.numberOfPeopleEat", locale), getText("export.coFoodItem.hide.icon", locale),
				getText("coFoodItem.note", locale), getText("coFoodItem.note.quantitative", locale),
				getText("export.co.info.foodItem", locale), getText("foodItem.relate", locale),
				getText("co.foodItem.size", locale), getText("foodItem.topping", locale),
				getText("export.co.modifier", locale), getText("export.co.label", locale), 
				getText("foodItem.avatarUrl", locale), getText("export.co.halfPhoto", locale),
				getText("export.co.toppingPhoto", locale), getText("export.co.quarter", locale),
				getText("export.co.groupPhoto", locale), getText("export.co.groupHidden", locale),
				getText("export.co.horizontal", locale), getText("export.co.vertical", locale),
				getText("co.image.drinkPhoto", locale), getText("co.image.qrOrderPhoto", locale),
				getText("foodItem.thumbnail", locale), getText("foodItem.sapcode", locale),
				getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	//============= Function IMPORT and EXPORT CO_FOODITEM ================//
	
	//============= Controller IMPORT and EXPORT CO_CATEGORY ================//
	@GetMapping("/export/coCategory")
	public void exportCoCategory(HttpServletRequest request, HttpServletResponse response,@RequestParam(name = "coId", required = false) Long coId,@RequestParam(name = "resCode", required = false) Integer resCode) throws IOException {
		log.info("ENTERING 'EXPORT CO_CATEGORY' METHOD...");
		Integer sumCell = 28;
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Menu");
			writeHeader(sheet, sumCell, headerCoCategoryText(request));
			processExportCoCategory(sheet, coId,resCode);
			String fileLocation = fileNameHelper.createNameFile("Order_Category_Config_Nha_Hang");
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
	
	@PostMapping("/import/coCategory")
    public String importCoCategory (@Valid CoDto coDto, RedirectAttributes redirectAttrs, HttpServletRequest request) {
    	log.info("ENTERING 'IMPORT CO_CATEGORY' METHOD...");
    	String url = "";
    	if (coDto.getFileImport() != null && coDto.getId() != null) {
			try {
				url = processReadFileExcelAndImportCoCategory(coDto, headerCoCategoryText(request));
				if(url != null && !url.isEmpty()) {
    				addError(request, getText("import.error", request.getLocale()));
    				redirectAttrs.addFlashAttribute("url", url);
				} else {
					addMessage(request, getText("import.status.success", request.getLocale()));
				}
			} catch (Exception e) {
				log.error("ERROR IMPORT CO_CATEGORY, EXCEPTION: {}", e);
				addError(request, getText("import.status.error", request.getLocale()));
			}
		}
        return "redirect:/co/soCategory/list?cId=" + coDto.getId();
    }
	//============= Controller IMPORT and EXPORT CO_CATEGORY ================//
	
	//============= Function IMPORT and EXPORT CO_CATEGORY ================//
	/*
	 * function export CO_CATEGORY
	 */
	private void processExportCoCategory(XSSFSheet sheet, Long coId, Integer resCode) {
		log.debug("PROCESS: EXPORT CO_CATEGORY BY CO_ID OR RESTAURANT_CODE, CO_ID: {}, RESTAURANT_CODE: {}",coId, resCode);
		List<IOCoCategoryDto> ioCoCategoryDtos = coCategoryManager.getIOCoCategoryDtosByCoIdOrResCode(coId, resCode);
		writeCoCategoryData(sheet, ioCoCategoryDtos);
	}
	
	/*
	 * function import CO_CATEGORY
	 */
	private String processReadFileExcelAndImportCoCategory(CoDto coDto, String[] headerText) throws IOException {
		log.debug("PROCESS: READ FILE EXCEL AND IMPORT DATA CO_CATEGORY");
		List<IOCoCategoryDto> ioCoCategoryDtos = new ArrayList<>();
		int i = 1;
		XSSFWorkbook workbook = new XSSFWorkbook(coDto.getFileImport().getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		while (i <= worksheet.getLastRowNum()) {
			IOCoCategoryDto ioCoCategoryDto = new IOCoCategoryDto();
			XSSFRow dataRow = worksheet.getRow(i++);
			
			if(dataRow.getCell(0) != null) {
				try {
					if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setOrderCategoryCode(dataRow.getCell(0).getStringCellValue());
					} else if(dataRow.getCell(0).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(0).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(0).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setOrderCategoryCode(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setOrderCategoryCode(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field OrderCategoryCode Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(1) != null) {
				try {
					if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setNameVn(dataRow.getCell(1).getStringCellValue());
					} else if(dataRow.getCell(1).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(1).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(1).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setNameVn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setNameVn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field NameVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(2) != null) {
				try {
					if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setNameEn(dataRow.getCell(2).getStringCellValue());
					} else if(dataRow.getCell(2).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(2).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(2).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setNameEn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setNameEn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field NameEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(3) != null) {
				try {
					if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDescVn(dataRow.getCell(3).getStringCellValue());
					} else if(dataRow.getCell(3).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(3).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(3).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDescVn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDescVn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescVn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(4) != null) {
				try {
					if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDescEn(dataRow.getCell(4).getStringCellValue());
					} else if(dataRow.getCell(4).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(4).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(4).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDescEn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDescEn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(5) != null) {
				try {
					if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDescJp(dataRow.getCell(5).getStringCellValue());
					} else if(dataRow.getCell(5).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(5).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(5).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDescJp(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDescJp(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescJp Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(6) != null) {
				try {
					if(dataRow.getCell(6).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDescKr(dataRow.getCell(6).getStringCellValue());
					} else if(dataRow.getCell(6).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(6).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(6).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDescKr(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDescKr(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescKr Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(7) != null) {
				try {
					if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDescCn(dataRow.getCell(7).getStringCellValue());
					} else if(dataRow.getCell(7).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(7).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(7).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDescCn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDescCn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DescCn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(8) != null) {
				try {
					if(dataRow.getCell(8).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setNote(dataRow.getCell(8).getStringCellValue());
					} else if(dataRow.getCell(8).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(8).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(8).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setNote(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setNote(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Note Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(9) != null) {
				try {
					if(dataRow.getCell(9).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(9).getStringCellValue().isEmpty()) {
						ioCoCategoryDto.setAverageAmount(Double.valueOf(dataRow.getCell(9).getStringCellValue()));
					} else if(dataRow.getCell(9).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoCategoryDto.setAverageAmount(dataRow.getCell(9).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoCategoryDto.setAverageAmount(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AverageAmount Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(10) != null) {
				try {
					if(dataRow.getCell(10).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(10).getStringCellValue().isEmpty()) {
						ioCoCategoryDto.setType(Double.valueOf(dataRow.getCell(10).getStringCellValue()));
					} else if(dataRow.getCell(10).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoCategoryDto.setType(dataRow.getCell(10).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoCategoryDto.setType(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field Type Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(11) != null) {
				try {
					if(dataRow.getCell(11).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setAlacarteLabel(dataRow.getCell(11).getStringCellValue());
					} else if(dataRow.getCell(11).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(11).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(11).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setAlacarteLabel(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setAlacarteLabel(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AlacarteLabel Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(12) != null) {
				try {
					if(dataRow.getCell(12).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setBuffetLabel(dataRow.getCell(12).getStringCellValue());
					} else if(dataRow.getCell(12).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(12).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(12).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setBuffetLabel(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setBuffetLabel(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field BuffetLabel Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(13) != null) {
				try {
					if(dataRow.getCell(13).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDrinksLabel(dataRow.getCell(13).getStringCellValue());
					} else if(dataRow.getCell(13).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(13).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(13).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDrinksLabel(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDrinksLabel(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DrinksLabel Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(14) != null) {
				try {
					if(dataRow.getCell(14).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setAlacarteLabelEn(dataRow.getCell(14).getStringCellValue());
					} else if(dataRow.getCell(14).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(14).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(14).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setAlacarteLabelEn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setAlacarteLabelEn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AlacarteLabelEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(15) != null) {
				try {
					if(dataRow.getCell(15).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setBuffetLabelEn(dataRow.getCell(15).getStringCellValue());
					} else if(dataRow.getCell(15).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(15).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(15).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setBuffetLabelEn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setBuffetLabelEn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field BuffetLabelEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(16) != null) {
				try {
					if(dataRow.getCell(16).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setDrinksLabelEn(dataRow.getCell(16).getStringCellValue());
					} else if(dataRow.getCell(16).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(16).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(16).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setDrinksLabelEn(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setDrinksLabelEn(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field DrinksLabelEn Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(17) != null) {
				try {
					if(dataRow.getCell(17).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setSubGroupCodes(dataRow.getCell(17).getStringCellValue());
					} else if(dataRow.getCell(17).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(17).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(17).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setSubGroupCodes(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setSubGroupCodes(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field SubGroupCodes Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(18) != null) {
				try {
					if(dataRow.getCell(18).getCellTypeEnum().equals(CellType.STRING) && !dataRow.getCell(18).getStringCellValue().isEmpty()) {
						ioCoCategoryDto.setPhotoDisplayPosition(Double.valueOf(dataRow.getCell(18).getStringCellValue()));
					} else if(dataRow.getCell(18).getCellTypeEnum().equals(CellType.NUMERIC)) {
						ioCoCategoryDto.setPhotoDisplayPosition(dataRow.getCell(18).getNumericCellValue());
					}
				} catch (Exception e) {
					ioCoCategoryDto.setPhotoDisplayPosition(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field PhotoDisplayPosition Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(19) != null) {
				try {
					if(dataRow.getCell(19).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setAvatarUrl(dataRow.getCell(19).getStringCellValue());
					} else if(dataRow.getCell(19).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(19).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(19).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setAvatarUrl(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setAvatarUrl(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field AvatarUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(20) != null) {
				try {
					if(dataRow.getCell(20).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setIntroImgMenuUrls(dataRow.getCell(20).getStringCellValue());
					} else if(dataRow.getCell(20).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(20).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(20).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setIntroImgMenuUrls(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setIntroImgMenuUrls(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field IntroImgMenuUrls Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(21) != null) {
				try {
					if(dataRow.getCell(21).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setIntroImgResUrl(dataRow.getCell(21).getStringCellValue());
					} else if(dataRow.getCell(21).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(21).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(21).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setIntroImgResUrl(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setIntroImgResUrl(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field IntroImgResUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(22) != null) {
				try {
					if(dataRow.getCell(22).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setVideoIntroResUrl(dataRow.getCell(22).getStringCellValue());
					} else if(dataRow.getCell(22).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(22).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(22).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setVideoIntroResUrl(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setVideoIntroResUrl(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field VideoIntroResUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(23) != null) {
				try {
					if(dataRow.getCell(23).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setVideoIntroMenuUrl(dataRow.getCell(23).getStringCellValue());
					} else if(dataRow.getCell(23).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(23).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(23).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setVideoIntroMenuUrl(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setVideoIntroMenuUrl(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field VideoIntroMenuUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(24) != null) {
				try {
					if(dataRow.getCell(24).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setSingleCategoryPhotoUrl(dataRow.getCell(24).getStringCellValue());
					} else if(dataRow.getCell(24).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(24).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(24).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setSingleCategoryPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setSingleCategoryPhotoUrl(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field SingleCategoryPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			if(dataRow.getCell(25) != null) {
				try {
					if(dataRow.getCell(25).getCellTypeEnum().equals(CellType.STRING)) {
						ioCoCategoryDto.setMultiCategoryPhotoUrl(dataRow.getCell(25).getStringCellValue());
					} else if(dataRow.getCell(25).getCellTypeEnum().equals(CellType.NUMERIC)) {
						String value = String.valueOf(dataRow.getCell(25).getNumericCellValue()).substring(0,String.valueOf(dataRow.getCell(25).getNumericCellValue()).lastIndexOf(SymbolEnum.DOT.val));
						ioCoCategoryDto.setMultiCategoryPhotoUrl(value);
					}
				} catch (Exception e) {
					ioCoCategoryDto.setMultiCategoryPhotoUrl(null);
					ioCoCategoryDto.setError(ErrorImportEnum.ERROR_FORMAT.val);
					log.error("Read Field MultiCategoryPhotoUrl Error With Line: {}, Exception: {}", i, e);
					continue;
				}
			}
			log.error("ioCoCategoryDtos  Exception: {}", ioCoCategoryDtos);
			ioCoCategoryDtos.add(ioCoCategoryDto);
		}
		
		/*
		 * Thực hiện import data và trả về nhưng bản ghi import bị lỗi
		 */
		List<IOCoCategoryDto> ioCoCategoryErrs =  coCategoryManager.importCoCategory(ioCoCategoryDtos, coDto.getId(), appProperties.getAttachmentPath());
		StringBuilder url = new StringBuilder();
		if(!CollectionUtils.isEmpty(ioCoCategoryErrs)) {
			url.append(exportCoCategoryLineErrsAfterImport(ioCoCategoryErrs, headerText));
		}
		workbook.close();
		return url.toString(); 
	}
	
	/*
	 * Ghi các bản ghi CO_CATEGORY ra file excel
	 */
	private void writeCoCategoryData(XSSFSheet sheet,List<IOCoCategoryDto> ioCoCategoryDtos) {
		Integer rowNum = 1;
		for(IOCoCategoryDto item: ioCoCategoryDtos) {
			Row row = sheet.createRow(rowNum++);
			Integer column = 0;
			Cell cell = row.createCell(column++);
			cell.setCellValue(item.getOrderCategoryCode() != null ? item.getOrderCategoryCode() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getNameVn() != null ? item.getNameVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getNameEn() != null ? item.getNameEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescVn() != null ? item.getDescVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescEn() != null ? item.getDescVn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescJp() != null ? item.getDescJp() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescKr() != null ? item.getDescKr() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDescCn() != null ? item.getDescCn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getNote() != null ? item.getNote() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getAverageAmount() != null ? item.getAverageAmount() : 0);
			cell = row.createCell(column++);
			cell.setCellValue(item.getType() != null ? item.getType() : 1);
			cell = row.createCell(column++);
			cell.setCellValue(item.getAlacarteLabel() != null ? item.getAlacarteLabel() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getBuffetLabel() != null ? item.getBuffetLabel() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDrinksLabel() != null ? item.getDrinksLabel() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getAlacarteLabelEn() != null ? item.getAlacarteLabelEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getBuffetLabelEn() != null ? item.getBuffetLabelEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getDrinksLabelEn() != null ? item.getDrinksLabelEn() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getSubGroupCodes() != null ? item.getSubGroupCodes() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getPhotoDisplayPosition() != null ? item.getPhotoDisplayPosition() : 1);
			cell = row.createCell(column++);
			cell.setCellValue(item.getAvatarUrl() != null ? item.getAvatarUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getIntroImgMenuUrls() != null ? item.getIntroImgMenuUrls() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getIntroImgResUrl() != null ? item.getIntroImgResUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getVideoIntroResUrl() != null ? item.getVideoIntroResUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getVideoIntroMenuUrl() != null ? item.getVideoIntroMenuUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getSingleCategoryPhotoUrl() != null ? item.getSingleCategoryPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.getMultiCategoryPhotoUrl() != null ? item.getMultiCategoryPhotoUrl() : "");
			cell = row.createCell(column++);
			cell.setCellValue(item.isStatus() ? "Thêm thành công" : "Lỗi");
			cell = row.createCell(column++);
			cell.setCellValue(item.getError() != null ? item.getError() : "");
		}
	}
	
	private String[] headerCoCategoryText(HttpServletRequest request) {
		Locale locale = request.getLocale();
		String[] headerText = {
				getText("export.coCategory.code", locale), getText("export.coCategory.nameVn", locale),
				getText("export.coCategory.nameEn", locale), getText("export.coCategory.descVn", locale),
				getText("export.coCategory.descEn", locale), getText("export.coCategory.descJp", locale),
				getText("export.coCategory.descKr", locale), getText("export.coCategory.descCn", locale),
				getText("export.coCategory.note", locale), getText("export.coCategory.averageAmount", locale),
				getText("export.coCategory.type", locale), getText("export.coCategory.alc", locale),
				getText("export.coCategory.bf", locale), getText("export.coCategory.drink", locale),
				getText("export.coCategory.alc.en", locale),getText("export.coCategory.bf.en", locale),
				getText("export.coCategory.drink.en", locale), getText("export.coCategory.subGroupCode.display", locale),
				getText("export.coCategory.position.number", locale), getText("export.coCategory.srcImg", locale),
				getText("export.coCategory.srcImgIntros", locale), getText("export.coCategory.image.intro.res", locale),
				getText("export.coCategory.video.intro.res", locale), getText("export.coCategory.video.intro.menu", locale),
				getText("export.coCategory.single.category.photo", locale), getText("export.coCategory.multi.category.photo", locale),
				getText("export.so.status", locale),getText("export.so.errors", locale),
		};
    	return headerText;
    }
	
	/*
	 * Tạo file excel trả về các bản ghi bị lỗi khi import
	 */
	private String exportCoCategoryLineErrsAfterImport(List<IOCoCategoryDto> ioCoCategoryDtos,String[] headerText) throws IOException {
		Integer sumCell = 28;
		StringBuilder builder = new StringBuilder();
		builder.append(ModuleTypeEnum.EXPORT_CO_CATEGORY.val).append(SymbolEnum.SLASH.val).append(FunctionTypeEnum.EXCEL.val);
		String idFolder = builder.toString();
		String uploadFolder = appProperties.getAttachmentPath() + idFolder;
		Path uploadedFolder = Paths.get(uploadFolder);
		if (!uploadedFolder.toFile().exists()) {
			Files.createDirectories(uploadedFolder);
		}
		
		StringBuilder buf = new StringBuilder();
		buf.append(uploadFolder).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.ORDER_CATEGORY.val);
		String filePath = buf.toString();
		Path file = Paths.get(filePath);
		if (file.toFile().exists()) {
			Files.delete(file);
		}
		
		StringBuilder builderUrl = new StringBuilder();
		String url = appProperties.getAttachmentContextPath() + idFolder;
		builderUrl.append(url).append(SymbolEnum.SLASH.val).append(ExcelNameEnum.ORDER_CATEGORY.val);
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet(SheetNameEnum.ORDER_CATEGORY.val);
			writeHeader(sheet, sumCell, headerText);
			writeCoCategoryData(sheet, ioCoCategoryDtos);
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
	//============= Function IMPORT and EXPORT CO_CATEGORY ================//
	
}
