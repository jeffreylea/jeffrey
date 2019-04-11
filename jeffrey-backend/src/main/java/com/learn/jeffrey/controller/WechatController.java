/**
 * 
 */
package com.learn.jeffrey.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jeffrey.utils.DataFormatUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * description
 * </p>
 * 
 * @author lijianfei
 * @date 2019-04-10
 */
@Slf4j
@Controller
@RestController
public class WechatController {

	@GetMapping(value = "/wx/portal")
	public String get(HttpServletRequest request, HttpServletResponse response, String signature, String timestamp,
			String nonce, String echostr) throws Exception {
		log.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,timestamp, nonce, echostr);
		return echostr;
	}
	
	@PostMapping(value = "/wx/portal")
	public String post(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException{
		Map<String, String> map=DataFormatUtil.xmlToMap(request);
		log.info("接收到微信消息："+map);
		if(map.get("Event").equals("subscribe")) {
			
			Map<String,Object> reMap=new HashMap<>();
			reMap.put("ToUserName", map.get("FromUserName"));
			reMap.put("FromUserName", map.get("ToUserName"));
			reMap.put("CreateTime", "111");
			reMap.put("MsgType", "text");
			reMap.put("Content","你好");
			String reXML=DataFormatUtil.mapToXml("xml",reMap);
			return reXML;
		}
		return "success";
	}

}
