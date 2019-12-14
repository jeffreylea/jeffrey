package com.learn.jeffrey.designpattern.strategy;

/**
 * <p>description</p>
 * @author lijianfei
 * @date 2019-12-02
 */
public class OperationMultiply implements Strategy {

	@Override
	public int doOperation(int num1, int num2) {
		return num1*num2;
	}
}
