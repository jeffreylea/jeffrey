package com.jeffrey.helloworld.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @Description 
 * <p>  </p>
 * @Date 2020-03-18 10:40:40
 */
@Controller
public class PageController {

	@RequestMapping("/index.html")
	public String index(Model model) {
		model.addAttribute("name", "jeffrey");
		return "index";
	}
}
