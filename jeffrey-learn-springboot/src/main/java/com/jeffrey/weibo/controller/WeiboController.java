package com.jeffrey.weibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeffrey.base.model.dto.BaseController;
import com.jeffrey.base.model.dto.BaseEntityDTO;
import com.jeffrey.weibo.service.IWeiboService;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

/**
 * @author lijianfei
 *         <p>
 *         </p>
 *         2020年3月19日
 */
@RestController
public class WeiboController extends BaseController {
	Oauth oauth = new Oauth();

	@Autowired
	private IWeiboService weiboService;

	/**
	 * 根据授权码获取AccessToken
	 * 
	 * @param code
	 * @return
	 * @throws WeiboException
	 */
	@GetMapping("/oauth2/accessToken")
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
	 * 根据uid获取登录用户信息,当uid为空时，查询accessToken登录用户信息
	 * 
	 * @param accessToken
	 * @return
	 */
	@GetMapping("/oauth2/account")
	public BaseEntityDTO<User> getAccountInfo(@RequestParam("accessToken") String accessToken,
			@RequestParam(value = "uid", required = false) String uid) {
		return successReturn(weiboService.getAccountInfo(accessToken, uid));
	}
	
	/**
	 * 获取某个用户最新发表的微博列表
	 * @param accessToken
	 * @return
	 */
	@GetMapping("/statuses/user/timeline")
	public StatusWapper getUserTimeline(@RequestParam("accessToken") String accessToken) {
		return weiboService.getUserTimeline(accessToken);
	}

}
