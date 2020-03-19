package com.jeffrey.weibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeffrey.weibo.service.IWeiboService;

import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

/**
 * @author lijianfei
 *<p></p>
 * 2020年3月19日
 */
@RestController
public class WeiboController {

	@Autowired
	private IWeiboService weiboService;
	
	/**
	 * 根据授权码获取AccessToken
	 * 
	 * @param code
	 * @return
	 * @throws WeiboException
	 */
	@GetMapping("/oauth2/access_token")
	public AccessToken getAccessToken(@RequestParam("code") String code) throws WeiboException {
		return weiboService.getAccessTokenByCode(code);
	}
	
	/**
	 * 根据accessToken获取登录用户uid
	 * 
	 * @param access_token
	 * @return uid
	 */
	@GetMapping("/oauth2/account/uid")
	public String getUid(@RequestParam("accessToken") String accessToken) {
		return weiboService.getUid(accessToken);
	}
	
	/**
	 * 根据accessToken获取登录用户信息
	 * 
	 * @param accessToken
	 * @return
	 */
	@GetMapping("/oauth2/account")
	public ResponseEntity<User>  getAccountInfo(@RequestParam("accessToken") String accessToken) {
		return new ResponseEntity<User>(weiboService.getAccountInfo(accessToken), HttpStatus.OK);
	}
	
	
}
