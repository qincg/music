package com.qcg.util;

import org.apache.logging.log4j.LogManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/25 11:12
 */
public class ProxyUtil {

    /**
     * 获取免费代理ip
     */
    public static Map<String, Integer> getIps() {
        String[] url1 = {"http://www.xicidaili.com/nn/","http://www.xicidaili.com/nt/","http://www.xicidaili.com/wt/","http://www.xicidaili.com/wn/"};
        String url3 = "http://www.66ip.cn/mo.php?sxb=&tqsl=300&port=&export=&ktip=&sxa=&submit=%CC%E1++%C8%A1&textarea=http%3A%2F%2Fwww.66ip.cn%2F%3Fsxb%3D%26tqsl%3D1000%26ports%255B%255D2%3D%26ktip%3D%26sxa%3D%26radio%3Dradio%26submit%3D%25CC%25E1%2B%2B%25C8%25A1";
        Map<String, Integer> map = new HashMap<>(500);
        try {
            for (String url:url1) {
                Document document = Jsoup.connect(url).get();
                Elements elements = document.select("#ip_list tr");
                for (int i = 1; i < elements.size(); i++) {
                    String ip = elements.get(i).select("td").get(1).text();
                    String port = elements.get(i).select("td").get(2).text();
                    map.put(ip, Integer.parseInt(port));
                }
            }

            Document document2 = Jsoup.connect(url3).get();
            Element element = document2.body();
            String[] strings = element.text().split(" ");
            for (String str : strings) {
                String[] str1 = str.split(":");
                map.put(str1[0],Integer.parseInt(str1[1]));
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e.getMessage());
        }
        System.out.println("map = " + map.size());
        return map;
    }

    /**
     * 验证ip的有效性
     *
     * @param ip
     * @param port
     * @return
     */
    public static boolean checkIp(String ip, int port,String url) {
        try {
            Jsoup.connect(url).timeout(1000).proxy(ip, port).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 批量验证ip是否有效
     *
     * @param ipMap
     * @return
     */
    public static Map<String, Integer> checkIps(Map<String, Integer> ipMap) {
        Map<String, Integer> checkedIps = new HashMap<>();
        for (String ip : ipMap.keySet()) {
            int port = ipMap.get(ip);
            try {
                Jsoup.connect("http://www.baidu.com").timeout(3000).proxy(ip, port).execute();
                checkedIps.put(ip, port);
            } catch (IOException e) {

            }
        }
        return checkedIps;
    }
}
