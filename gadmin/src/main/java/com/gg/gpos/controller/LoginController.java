package com.gg.gpos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * The Class LoginController.
 * 
 * @author <a href="mailto:ngtrungkien@gmail.com">Kien Nguyen</a>
 */
@Controller
public class LoginController extends BaseController {
	@GetMapping("/login*")
    public String handleRequest(HttpServletRequest request) throws Exception {
        return "login";
    }
}
