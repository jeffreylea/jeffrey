package com.learn.jeffrey.designpattern.strategy;

import cn.hutool.crypto.SecureUtil;

/**
 * <p>description</p>
 * @author lijianfei
 * @date 2019-12-02
 */
public class Context {
	private Strategy strategy;
	public Context(Strategy strategy) {
		this.strategy=strategy;
	}
	
	public int executeStrategy(int num1,int num2) {
		return strategy.doOperation(num1, num2);
	}

}
