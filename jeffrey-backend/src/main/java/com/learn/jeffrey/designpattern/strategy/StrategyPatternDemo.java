package com.learn.jeffrey.designpattern.strategy;

/**
 * <p>description</p>
 * @author lijianfei
 * @date 2019-12-02
 */
public class StrategyPatternDemo {
	public static void main(String[] args) {
		Context context=new Context(new OperationAdd());
		System.out.println(context.executeStrategy(1,2));
	}
}
