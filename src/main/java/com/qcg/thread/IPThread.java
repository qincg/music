package com.qcg.thread;

import com.qcg.util.ProxyUtil;

import java.util.Map;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/25 14:46
 */
public class IPThread implements Runnable {

    Map<String,Integer> ipMap;

    public IPThread(Map<String, Integer> ipMap) {
        this.ipMap = ipMap;
    }

    @Override
    public void run() {
        Map<String,Integer> map = ProxyUtil.checkIps(ipMap);
        for(String key:map.keySet()){
            System.out.println("key = " + key + "  value =" + map.get(key));
        }
    }

}
