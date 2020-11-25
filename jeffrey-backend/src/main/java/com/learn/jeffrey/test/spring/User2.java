package com.learn.jeffrey.test.spring;

import lombok.Data;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/19 17:50
 **/
@Data
public class User2 {
    String name;

    public static void main(String[] args) {
        User user = new User();
        user.setName("o");
        user.setBeanName("k");
        System.out.println(user);

        User2 user2 = new User2();
        System.out.println(user2);
    }
}
