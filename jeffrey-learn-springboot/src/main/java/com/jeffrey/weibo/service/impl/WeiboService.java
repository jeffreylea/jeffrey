package com.jeffrey.weibo.service.impl;

import org.springframework.stereotype.Service;

import com.jeffrey.weibo.service.IWeiboService;

import lombok.extern.slf4j.Slf4j;
import weibo4j.Account;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

/**
 * @author Administrator
 * @Description
 *              <p>
 *              </p>
 * @Date 2020-03-19 07:24:30
 */
@Service
@Slf4j
public class WeiboService implements IWeiboService {

	Oauth oauth = new Oauth();

	@Override
	public AccessToken getAccessTokenByCode(String code) throws WeiboException {
		return oauth.getAccessTokenByCode(code);
	}

	@Override
	public String getUid(String access_token) {
		Account am = new Account(access_token);
		String uid = "";
		try {
			JSONObject uidObject = am.getUid();
			try {
				uid = String.valueOf(uidObject.get("uid"));
			} catch (JSONException e) {
				log.error(" the key is not found");
				e.printStackTrace();
			}
			log.info(uid.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return uid;
	}

	@Override
	public User getAccountInfo(String access_token) {
		Users um = new Users(access_token);
		User user = null;
		try {
			user = um.showUserById(getUid(access_token));
			log.info(user.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		return user;
	}

}
