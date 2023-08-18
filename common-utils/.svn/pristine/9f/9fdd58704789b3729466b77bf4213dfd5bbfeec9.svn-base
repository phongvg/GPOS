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
public enum DaysOfWeekEnum {

	MONDAY		("2", "daysOfWeek.monday"),
	TUESDAY		("3", "daysOfWeek.tuesday"),
	WEDNESDAY	("4", "daysOfWeek.wednesday"),
	THURSDAY	("5", "daysOfWeek.thursday"),
	FRIDAY		("6", "daysOfWeek.friday"),
	SATURDAY	("7", "daysOfWeek.saturday"),
	SUNDAY		("1", "daysOfWeek.sunday")
	;

	private String key;

	private String value;

	private DaysOfWeekEnum(String key, String value) {
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

	public static DaysOfWeekEnum fromKey(String key) {
		for (DaysOfWeekEnum daysOfWeekEnum : DaysOfWeekEnum.values()) {
			if (daysOfWeekEnum.key.equalsIgnoreCase(key)) {
				return daysOfWeekEnum;
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
