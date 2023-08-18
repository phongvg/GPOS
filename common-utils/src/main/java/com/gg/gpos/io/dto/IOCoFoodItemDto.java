package com.gg.gpos.io.dto;

import lombok.Data;

@Data
public class IOCoFoodItemDto {
	private String foodItemCode; 
	private String nameVn;
	private String nameEn;
	private String extraFoodItem;
	private String descVn;
	private String descEn;
	private Double maxPerTransaction; 
	private Double maxForKitchen; 
	private Double kalo; 
	private String kaloGroup; 
	private String bufferLabel;
	private Double positionNumber;
	private Double coImageSize; 
	private Double viewType; 
	private Double numberOfPeopleEat; 
	private Double hideIcon; 
	private String note;
	private String noteQuantitative;
	private String infoFoodItem;
	private String relatedFoodItem;
	private String sizeFoodItem; 
	private String toppingFoodItem; 
	private String modifier; 
	private String feature; 
	private String foodItemSapCode; 
	private String avatarUrl; 
	private String thumbnailUrl; 
	private String halfPhotoUrl; 
	private String toppingPhotoUrl; 
	private String groupPhotoUrl; 
	private String groupHiddenPhotoUrl; 
	private String horizontalPhotoUrl; 
	private String verticalPhotoUrl; 
	private String quarterPhotoUrl; 
	private String drinkPhotoUrl;
	private String qrOrderPhotoUrl;
	private boolean status;
	private String error;
} 
