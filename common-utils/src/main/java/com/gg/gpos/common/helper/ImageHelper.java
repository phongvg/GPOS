package com.gg.gpos.common.helper;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ImageHelper {
	/*
	 * Function check đường dẫn của ảnh
	 */
	public Boolean checkImageUrlErr(String fileName) {
		String regex = "^[a-zA-Z0-9._-]$";
		Pattern pattern = Pattern.compile(regex); 
		boolean check = false;
		if(fileName.contains(" ")) {
			check = true;
		} else {
			for(char text: fileName.toCharArray()) {
				if(!pattern.matcher(String.valueOf(text)).matches()) {
					check = true;
					break;
				} 
			}
		}
		return check;
	}
}
