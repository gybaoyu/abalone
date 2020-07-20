package com.abalone.service;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: gavy
 * CreateTime: 2020-07-11-16-31
 * <p>
 * 没错这是个独一无二的类,用于处理b站的json
 */
@Service
public class BiliServiceImpl implements BiliService {
    public String readJSON (String url)throws Exception {
        StringBuilder json = new StringBuilder();
            URL urlObject = new URL(url);
            //获取url
            URLConnection uc = urlObject.openConnection();
            //获取链接
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(),"utf-8"));
            //读html,注意用utf-8
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
                //一个一个加到json里ddne
            }
            in.close();//记得观赏
        return json.toString();//toString返回(StringBuilder转String)
    }
}
