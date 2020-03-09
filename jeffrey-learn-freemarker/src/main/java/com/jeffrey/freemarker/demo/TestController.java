package com.jeffrey.freemarker.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lijianfei
 *<p></p>
 * 2020年3月9日
 */
@Controller
public class TestController {
	 
	@RequestMapping("/index")
	public String index(Model model){
        model.addAttribute("welcome","hello world");
        return "index";
    }
}
