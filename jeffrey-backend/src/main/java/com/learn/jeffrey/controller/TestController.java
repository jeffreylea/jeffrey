package com.learn.jeffrey.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jeffrey
 *
 * 2019年4月3日
 */
@Controller
@RestController
public class TestController{
    
    @PostMapping("/h")
    public String hello(){
        return "heelo";
    }
    
    /**
     * 将接收到的XML转为map
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public Map<String , String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        
        Map<String , String> map=new  HashMap<String, String>();
        SAXReader reader=new  SAXReader();
        
        InputStream is=request.getInputStream();
        Document doc=reader.read(is);
        Element root=doc.getRootElement();
        List<Element> list=root.elements();
        for(Element e:list) {
            map.put(e.getName(), e.getText());
        }
        is.close();
        return  map;      
   }
    
}
