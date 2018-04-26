package com.qcg.util;

import com.qcg.dao.IpDao;
import com.qcg.model.Ip;

import java.util.List;
import java.util.Random;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/26 14:42
 */
public class MathUtil {
    public static Ip getRandomIp(){
        IpDao ipDao = new IpDao();
        List<Ip> ipList = ipDao.query();
        Random random = new Random();
        int temp = random.nextInt(ipList.size());
        Ip ip = ipList.get(temp);
        //通过网易云验证ip的可用性
        boolean canUse = ProxyUtil.checkIp(ip.getIp(),ip.getPort(),"http://music.163.com/playlist?id=2182683172");
        if (!canUse){
            ipDao.delete(ip);
            getRandomIp();
            //删除库中没用的ip
        }
        System.out.println("ip 123= " + ip);
        return ip;
    }

    public static Object getRandom(int size,List list){
        Random random = new Random();
        int temp = random.nextInt(size);
        return list.get(temp);
    }
}
