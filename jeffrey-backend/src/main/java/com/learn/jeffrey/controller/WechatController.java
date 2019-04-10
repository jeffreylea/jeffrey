/**
 * 
 */
package com.learn.jeffrey.controller;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.jeffrey.utils.DataFormatUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>description</p>
 * @author lijianfei
 * @date 2019-04-10
 */
@Slf4j
@Controller
@RestController
public class WechatController {

	private static String WECHAT_TOKEN = "wx_oms";
	 
	@RequestMapping(value = "/wx")
	public void get(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		log.error("WechatController   ----   WechatController");
 
		System.out.println("========WechatController========= ");
		log.info("请求进来了...");
		/*Map<String, String> map=DataFormatUtil.xmlToMap(request);
		System.out.println(map);*/
		Enumeration pNames = request.getParameterNames();
		while (pNames.hasMoreElements()) {
			String name = (String) pNames.nextElement();
			String value = request.getParameter(name);
			// out.print(name + "=" + value);
 
			String log = "name =" + name + "     value =" + value;
		}
 
		String signature = request.getParameter("signature");/// 微信加密签名
		String timestamp = request.getParameter("timestamp");/// 时间戳
		String nonce = request.getParameter("nonce"); /// 随机数
		String echostr = request.getParameter("echostr"); // 随机字符串
		PrintWriter out = response.getWriter();
 System.out.println(signature+timestamp+nonce+echostr);
 System.out.println(signature);
 System.out.println(timestamp);
 System.out.println(nonce);
 System.out.println(echostr);
		//if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
//		}s
		//out.close();
//		out = null;
	}

}
