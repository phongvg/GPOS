/*
 * Copyright 2018. All rights reserved.
 * This software is the confidential and proprietary information
 * of GG ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with GG.
 */
package com.gg.gpos.common.constant;

/**
 * The Class DaysOfWeekEnum.
 *
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
public enum HoursInDayEnum {

	ONE				("1", "1 AM"),
	TWO				("2", "2 AM"),
	THREE			("3", "3 AM"),
	FOUR			("4", "4 AM"),
	FIVE			("5", "5 AM"),
	SIX				("6", "6 AM"),
	SEVEN			("7", "7 AM"),
	EIGHT			("8", "8 AM"),
	NINE			("9", "9 AM"),
	TEN				("10", "10 AM"),
	ELEVEN			("11", "11 AM"),
	TWEVLE			("12", "12 AM"),
	THIRTEEN		("13", "1 PM"),
	FOURTEEN		("14", "2 PM"),
	FIFTEEN			("15", "3 PM"),
	SIXTEEN			("16", "4 PM"),
	SEVENTEEN		("17", "5 PM"),
	EIGHTEEN		("18", "6 PM"),
	NINETEEN		("19", "7 PM"),
	TWENTY			("20", "8 PM"),
	TWENTY_ONE		("21", "9 PM"),
	TWENTY_TWO		("22", "10 PM"),
	TWENTY_THREE	("23", "11 PM"),
	TWENTY_FOUR		("0", "12 PM"),
	;

	private String key;

	private String value;

	private HoursInDayEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public static HoursInDayEnum fromKey(String key) {
		for (HoursInDayEnum hoursInDay : HoursInDayEnum.values()) {
			if (hoursInDay.key.equalsIgnoreCase(key)) {
				return hoursInDay;
			}
		}
		throw new IllegalArgumentException("No constant with key " + key + " found");
	}
	
	public boolean isIn(String[] keyList) {
		if (keyList != null) {
			for (String key : keyList) {
				if (this.key.equalsIgnoreCase(key)) {
					return true;
				}
			}
		}
		return false;
	}
}
