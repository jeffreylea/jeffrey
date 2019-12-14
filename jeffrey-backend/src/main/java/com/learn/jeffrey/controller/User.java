/**
 * 
 */
package com.learn.jeffrey.controller;

import java.util.List;

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
	private test1 test11;
	private boolean flag;

}
@Setter
@Getter
class test1{
	private String Content;
	private String MsgId;
}
