package com.gg.gpos.menu.manager;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg.gpos.common.constant.TypeRestaurantDataEditEnum;
import com.gg.gpos.menu.entity.RestaurantDataEdit;
import com.gg.gpos.menu.repository.RestaurantDataEditRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RestaurantDataEditManager {
	@Autowired
	private RestaurantDataEditRepository restaurantDataEditRepository;
	
	
	public void save(String value, String type,Integer resCode) {
		log.debug("Entering 'save' method...");
		restaurantDataEditRepository.deleteByValueAndRestaurantCodeAndType(value, resCode, type);
		RestaurantDataEdit restaurantDataEdit = restaurantDataEditRepository.findByValueAndRestaurantCodeAndType(value, resCode, type);
		if(restaurantDataEdit == null) {
			restaurantDataEdit = new RestaurantDataEdit();
		}
		restaurantDataEdit.setValue(value);
		restaurantDataEdit.setType(type);
		restaurantDataEdit.setRestaurantCode(resCode);
		restaurantDataEditRepository.save(restaurantDataEdit);
	}

	private void updateTypeAfterSync(String type,Integer resCode,String typeUpdate) {
		List<RestaurantDataEdit> restaurantDataEdits = restaurantDataEditRepository.findByRestaurantCodeAndType(resCode, type);
		if(restaurantDataEdits != null && !restaurantDataEdits.isEmpty()) {
			restaurantDataEdits.stream().forEach(item ->{
				item.setType(typeUpdate);
				restaurantDataEditRepository.save(item);
			});
		}
	}

	public void deleteDataGroupParamEdit(Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE DATA GROUP_PARAM EDIT IN RESTAURANT, RESTAURANT_CODE: {}", restaurantCode);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.PARAM.val);
	}
	
	public void deleteDataCoMenuEdit(Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE DATA CO_MENU EDIT IN RESTAURANT, RESTAURANT_CODE: {}", restaurantCode);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val, restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_OLD.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val, restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_OLD.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val, restaurantCode, TypeRestaurantDataEditEnum.DEL_SO_CATEGORY_OLD_RES.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val, restaurantCode, TypeRestaurantDataEditEnum.DEL_CO_FOODITEM_OLD_RES.val);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_CATEGORY.val);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_FOODITEM.val);
	}
	
	public void deleteDataSoMenuEdit(Integer restaurantCode) {
		log.debug("PROCESS FUNCTION: DELETE DATA CO_MENU EDIT IN RESTAURANT, RESTAURANT_CODE: {}", restaurantCode);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY.val, restaurantCode, TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY_OLD.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY.val, restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_OLD.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM.val, restaurantCode, TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_OLD.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_SO_CATEGORY_RES.val, restaurantCode, TypeRestaurantDataEditEnum.DEL_SO_CATEGORY_OLD_RES.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_CATEGORY_RES.val, restaurantCode, TypeRestaurantDataEditEnum.DEL_SO_CATEGORY_OLD_RES.val);
		updateTypeAfterSync(TypeRestaurantDataEditEnum.DELETE_CO_FOODITEM_RES.val, restaurantCode, TypeRestaurantDataEditEnum.DEL_CO_FOODITEM_OLD_RES.val);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_CATEGORY.val);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.CO_FOODITEM.val);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.SO_CATEGORY.val);
		restaurantDataEditRepository.deleteByRestaurantCodeAndType(restaurantCode, TypeRestaurantDataEditEnum.PARAM.val);
	}
}
