package com.jeffrey.weibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;

/**
 * @author lijianfei
 *<p></p>
 * 2020年3月19日
 */
public class WeiboController {
	Oauth oauth = new Oauth();

	@RequestMapping("getAccessTokenByCode")
	public AccessToken getAccessTokenByCode(String code) throws WeiboException {
		return oauth.getAccessTokenByCode(code);
	}
}
