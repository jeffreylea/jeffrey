package com.jeffrey.freemarker.demo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

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
	
	public static void main(String[] args) throws IOException {
		//创建ConfigConfiguration实例并调整设置
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File("/templates"));
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		//构建这个数据模型的Java代码片段
		// Create the root hash
		Map<String, Object> root = new HashMap<>();
		// Put string ``user'' into the root
		root.put("user", "Big Joe");
		// Create the hash for ``latestProduct''
		Map<String, Object> latest = new HashMap<>();
		// and put it into the root
		root.put("latestProduct", latest);
		// put ``url'' and ``name'' into latest
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse");
		// 获取模板
		Template temp = cfg.getTemplate("test.ftl");
	}
}
