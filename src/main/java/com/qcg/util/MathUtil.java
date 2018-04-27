package com.qcg.util;

import com.qcg.dao.IpDao;
import com.qcg.model.Ip;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/26 14:42
 */
public class MathUtil {

    static List<Ip> ipList;
    static IpDao ipDao;
    static  {
        ipDao = new IpDao();
        ipList = ipDao.query();
    }

    /**
     * @return
     */
    public static Ip getRandomIp(String url){
        long start = System.currentTimeMillis();

        Random random = new Random();
        int temp = random.nextInt(ipList.size());
        Ip ip = ipList.get(temp);
        //通过网易云验证ip的可用性
        boolean canUse = false;
        try {
            canUse = ProxyUtil.checkIp(ip.getIp(), ip.getPort(), url);
        }catch (NoSuchElementException e){
            LogManager.getLogger().error(e.getMessage());
        }
        if (!canUse){
            //删除数据库中无用的ip，删除list中无用的ip
            ipDao.delete(ip);
            ipList.remove(temp);
            return getRandomIp(url);
            //删除库中没用的ip
        }
        System.out.println("ip 123= " + ip);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return ip;
    }

}
