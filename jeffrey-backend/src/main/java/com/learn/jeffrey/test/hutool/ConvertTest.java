package com.learn.jeffrey.test.hutool;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.springframework.security.crypto.codec.Utf8;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/11/26 16:49
 **/
public class ConvertTest {
    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();
        map.put("ceshi","kkk");
        map.put("newsType","");
        map.put("p","2");
        //请求列表页
        String listContent = HttpUtil.get("https://www.baidu.com/s?=xiaoleilu",map);
        HttpRequest httpRequest =HttpRequest.get("https://www.baidu.com/s?=xiaoleilu").form(map);
        System.out.println(httpRequest);
        //使用正则获取所有标题
        List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
        for (String title : titles) {
            //打印标题
            Console.log(title);
        }
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        //图形验证码写出，可以写出到文件，也可以写出到流
        lineCaptcha.write("d:/line.png");
        //输出code
        Console.log(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");

        //重新生成验证码
        lineCaptcha.createCode();
        lineCaptcha.write("d:/line.png");
        //新的验证码
        Console.log(lineCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        lineCaptcha.verify("1234");

        BitMapBloomFilter bitMapBloomFilter = new BitMapBloomFilter(10);
        String s = "测试";
        System.out.println(Convert.toHex(s, CharsetUtil.CHARSET_UTF_8).toUpperCase());
    }
}
