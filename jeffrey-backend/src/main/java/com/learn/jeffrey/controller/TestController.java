package com.learn.jeffrey.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jeffrey.utils.DataFormatUtil;

/**
 * @author Jeffrey
 *
 * 2019年4月3日
 */
@Controller
@RestController
public class TestController{
    
    @PostMapping("/h")
    public Map<String , String> hello(HttpServletRequest request) throws IOException, DocumentException{
        return DataFormatUtil.xmlToMap(request);
    }
    
}
