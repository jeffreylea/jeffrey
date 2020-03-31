package com.jeffrey.weibo.service;

import weibo4j.http.AccessToken;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

/**
 * @author Administrator
 * @Description 
 * <p>  </p>
 * @Date 2020-03-19 07:22:02
 */
public interface IWeiboService {
	
	/**
	 * 根据授权码获取AccessToken
	 * 
	 * @param code
	 * @return
	 * @throws WeiboException
	 */
	AccessToken getAccessTokenByCode(String code) throws WeiboException;

	/**
	 * 根据accessToken获取登录用户uid
	 * 
	 * @param access_token
	 * @return uid
	 */
	String getUid(String accessToken);
	
	/**
	 * 根据uid获取用户信息，如果UID为空，则查询accessToken登录用户信息
	 * 
	 * @param access_token
	 * @return
	 */
	User getAccountInfo(String accessToken, String uid);
	
	/**
	 * 获取某个用户最新发表的微博列表
	 * @return
	 */
	StatusWapper getUserTimeline(String accessToken);
	
}
