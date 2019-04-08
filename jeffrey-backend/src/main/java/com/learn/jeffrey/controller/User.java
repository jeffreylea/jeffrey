/**
 * 
 */
package com.learn.jeffrey.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>description</p>
 * @author lijianfei
 * @date 2019-04-08
 */
@Setter
@Getter
public class User {
	private String ToUserName;
	private String FromUserName;
	private String MsgType;
	private String Content;
	private String MsgId;

}
