package com.learn.jeffrey.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/27 8:48
 **/
public class XiaoMei {
    List<Person> list = new ArrayList<>();

    public void addPerson(Person person) {
        list.add(person);
    }

    public void removePerson(Person person) {
        list.remove(person);
    }

    public void notifyAllPerson(String s) {
        for (Person person : list) {
            person.getMessage(s);
        }
    }
}
