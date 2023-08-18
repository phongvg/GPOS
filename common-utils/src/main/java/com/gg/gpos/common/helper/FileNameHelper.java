package com.gg.gpos.common.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.gg.gpos.common.constant.DatePatternEnum;

@Component
public class FileNameHelper {
	public String createNameFile(String baseName) {
		DateFormat dateFormat = new SimpleDateFormat(DatePatternEnum.YYYY_MM_DD__HH_MM_SS_1.pattern);
		String dateTimeInfo = dateFormat.format(new Date());
		return baseName.concat(String.format("_%s.xlsx", dateTimeInfo));
	}
}
