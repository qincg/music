package com.qcg.thread;

import com.qcg.dao.IpDao;
import com.qcg.model.Ip;
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
        IpDao ipDao = new IpDao();
        for(String key:map.keySet()){
            Ip ip = new Ip(key,map.get(key));
            System.out.println("ip = " + ip);
            if (ipDao.query(ip)) {
                ipDao.add(ip);
            }
        }
    }

}
