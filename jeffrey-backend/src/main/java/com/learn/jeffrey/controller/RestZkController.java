package com.learn.jeffrey.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/9/11 17:38
 **/
@RestController
public class RestZkController {

    @RequestMapping(value = "/zkGet/{node}",method = RequestMethod.GET)
    public String zkGet(@PathVariable("node")String node){
        Watcher watcher= new Watcher(){
            public void process(WatchedEvent event) {
                System.out.println("receive event："+event);
            }
        };

        String value = null;
        try {
            final ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181", 999999, watcher);
            final byte[] data = zookeeper.getData("/"+node, watcher, null);
            value = new String(data);
            zookeeper.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return "获取 node_1 节点值为 [" + value + "]";
    }
}
