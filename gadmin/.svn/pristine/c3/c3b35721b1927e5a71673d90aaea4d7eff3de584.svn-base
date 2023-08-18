package com.gg.gpos.menu.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gg.gpos.controller.BaseController;
import com.gg.gpos.integration.manager.SyncManager;
import com.gg.gpos.menu.dto.CoCategoryDto;
import com.gg.gpos.menu.manager.CatalogDataEditManager;
import com.gg.gpos.menu.manager.CoCategoryManager;
import com.gg.gpos.menu.manager.CoManager;
import com.gg.gpos.menu.manager.RestaurantDataEditManager;
import com.gg.gpos.reference.manager.AttachmentManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CoCategoryController extends BaseController {
    @Autowired
    private AttachmentManager attachmentManager;

    @PostMapping("/coCategory/upload")
    @ResponseBody
    public String uploadPhotos (@Valid CoCategoryDto coCategoryDto) {
        log.info("ENTERING 'UPLOAD DIGITAL-MENU' METHOD...");
        String result = "";
        try {
            if(coCategoryDto.getDigitalMenuPhotos() != null) {
                if(coCategoryDto.getCoId() != null) {
                    result = attachmentManager.saveDigitalMenuFiles(coCategoryDto.getDigitalMenuPhotos(),appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), new StringBuilder().append("COMENUID").append(coCategoryDto.getCoId()).toString());
                } else {
                    result = attachmentManager.saveDigitalMenuFiles(coCategoryDto.getDigitalMenuPhotos(),appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), new StringBuilder().append("RESTAURANTCODE").append(coCategoryDto.getRestaurantCode()).toString());
                }
            }
        } catch (Exception e) {
            log.error("ERROR UPLOAD DIGITAL-MENU EXCEPTION: {}", e);
        }
        return result;
    }

    @PostMapping("/coCategory/update-photo")
    @ResponseBody
    public String updatePhotos (@Valid CoCategoryDto coCategoryDto) {
        log.info("ENTERING 'UPDATE DIGITAL-MENU' METHOD...");
        String result = "";
        try {
            if(coCategoryDto.getAvatar() != null) {
                if(coCategoryDto.getCoId() != null) {
                    result = attachmentManager.saveDigitalMenuFile(coCategoryDto.getAvatar(),appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), new StringBuilder().append("COMENUID").append(coCategoryDto.getCoId()).toString());
                } else {
                    result = attachmentManager.saveDigitalMenuFile(coCategoryDto.getAvatar(),appProperties.getAttachmentPath(), appProperties.getAttachmentContextPath(), new StringBuilder().append("RESTAURANTCODE").append(coCategoryDto.getRestaurantCode()).toString());
                }
            }
        } catch (Exception e) {
            log.error("ERROR UPDATE DIGITAL-MENU EXCEPTION: {}", e);
        }
        return result;
    }

}
