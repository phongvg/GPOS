package com.gg.gpos.menu.io.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gg.gpos.common.helper.FileNameHelper;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.res.dto.RestaurantDto;
import com.gg.gpos.res.manager.RestaurantManager;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IORestaurantController extends BaseController{
	@Autowired
	private RestaurantManager restaurantManager;
	@Autowired
	private FileNameHelper fileNameHelper;
	
	@GetMapping("/export/restaurant")
	public void exportRestaurant(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("ENTERING 'EXPORT RESTAURANT' METHOD...");
		Locale locale = request.getLocale();
		Integer sumCell = 12;
		String[] headerText = {
				getText("restaurant.code", locale),getText("restaurant.name", locale), 
				getText("restaurant.province", locale),getText("restaurant.brand", locale),
				getText("restaurant.address", locale),getText("restaurant.phone", locale),
				getText("restaurant.email", locale), getText("restaurant.ip", locale),
				getText("restaurant.port", locale),getText("restaurant.status.sync.master.data", locale), 
				getText("restaurant.status.sync.menu", locale),getText("restaurant.version", locale),
		};
		XSSFWorkbook workbook = new XSSFWorkbook();
		try {
			XSSFSheet sheet = workbook.createSheet("Restaurant");
			writeHeader(sheet, sumCell, headerText);
			writeRestaurantDataLines(sheet);
			String fileLocation = fileNameHelper.createNameFile("Restaurant");
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
	
	//============= Function IMPORT and EXPORT RESTAURANT ================//
	public void writeRestaurantDataLines(XSSFSheet sheet) {
		Integer rowNum = 1;
		List<RestaurantDto> restaurants = restaurantManager.gets();
		if(restaurants != null && !restaurants.isEmpty()) {
			for(RestaurantDto restaurant : restaurants) {
				String sttSyncMasterData = restaurant.getSyncStatus().get(0).getMasterDataSyncStatus();
				String sttSyncBusinessData = restaurant.getSyncStatus().get(0).getBusinessSyncStatus();
				Row row = sheet.createRow(rowNum++);
				Integer column = 0;
				Cell cell = row.createCell(column++);
				cell.setCellValue(restaurant.getCode().toString() != null ? restaurant.getCode().toString() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getName() != null ? restaurant.getName() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getProvinceBrand().getProvince().getName() != null ? restaurant.getProvinceBrand().getProvince().getName() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getProvinceBrand().getBrand().getName() != null ? restaurant.getProvinceBrand().getBrand().getName() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getAddress() != null ? restaurant.getAddress() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getPhone() != null ? restaurant.getPhone() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getEmail() != null ? restaurant.getEmail() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getIp() != null ? restaurant.getIp() : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getPort() != null ? restaurant.getPort() : "");
				cell = row.createCell(column++);
				cell.setCellValue(sttSyncMasterData != null ? sttSyncMasterData : "");
				cell = row.createCell(column++);
				cell.setCellValue(sttSyncBusinessData != null ? sttSyncBusinessData : "");
				cell = row.createCell(column++);
				cell.setCellValue(restaurant.getVersion() != null ? restaurant.getVersion() : "");
				
			}
		}
	}
	
	private void writeHeader(XSSFSheet sheet, Integer sumCell, String[] headerText) {
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < sumCell; i++) {
			headerRow.createCell(i).setCellValue(headerText[i]);
		}
	}	
	//============= Function IMPORT and EXPORT RESTAURANT ================//
}
