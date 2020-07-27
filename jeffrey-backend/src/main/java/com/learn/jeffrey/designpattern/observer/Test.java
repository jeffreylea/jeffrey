package com.learn.jeffrey.designpattern.observer;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 8:52
 **/
public class Test {
    public static void main(String[] args) {
        XiaoMei xiaoMei = new XiaoMei();
        LaoWang laoWang = new LaoWang();
        LaoLi laoLi = new LaoLi();

        xiaoMei.addPerson(laoLi);
        xiaoMei.addPerson(laoWang);
        xiaoMei.notifyAllPerson("你们过来吧");
    }
}
