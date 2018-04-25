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
        String url1 = "http://www.xicidaili.com/nn/";
        String url2 = "https://www.kuaidaili.com/free/";
        String url3 = "http://www.66ip.cn/mo.php?sxb=&tqsl=300&port=&export=&ktip=&sxa=&submit=%CC%E1++%C8%A1&textarea=http%3A%2F%2Fwww.66ip.cn%2F%3Fsxb%3D%26tqsl%3D1000%26ports%255B%255D2%3D%26ktip%3D%26sxa%3D%26radio%3Dradio%26submit%3D%25CC%25E1%2B%2B%25C8%25A1";
        long startTime = System.currentTimeMillis();
        Map<String,Integer> map = new HashMap<>(500);
        try {
            Document document = Jsoup.connect(url1).get();
            Document document1 = Jsoup.connect(url2).get();
            Elements elements1 = document1.select("tbody tr");
            for(Element element:elements1){
                String ip = element.select("td").get(0).text();
                String port = element.select("td").get(1).text();
                map.put(ip,Integer.parseInt(port));
            }
            Elements elements = document.select("#ip_list tr");
            for (int i = 1; i < elements.size(); i++) {
                String ip = elements.get(i).select("td").get(1).text();
                String port = elements.get(i).select("td").get(2).text();
                map.put(ip,Integer.parseInt(port));
                /*if (response.statusCode() == 200) {
                    System.out.println("ip == " + ip + " port = " + port);
                }*/
            }
            Document document2 = Jsoup.connect(url3).get();
            Element element = document2.body();
            String[] strings = element.text().split(" ");
            for (String str:strings){
                String[] str1 = str.split(":");
                map.put(str1[0],Integer.parseInt(str1[1]));
            }
        } catch (IOException e) {
            LogManager.getLogger().error(e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("(endTime-startTime)/1000 = " + (endTime - startTime) / 1000);
        return map;
    }

    /**
     * 验证ip的有效性
     *
     * @param ip
     * @param port
     * @return
     */
    public static boolean checkIp(String ip, String port) {
        try {
            Jsoup.connect("http://www.baidu.com").timeout(1000).proxy(ip, Integer.parseInt(port)).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 批量验证ip是否有效
     * @param ipMap
     * @return
     */
    public static Map<String, Integer> checkIps(Map<String, Integer> ipMap) {
        Map<String, Integer> checkedIps = new HashMap<>();
        for (String ip : ipMap.keySet()) {
            int port = ipMap.get(ip);
            try {
                Jsoup.connect("http://www.baidu.com").timeout(1000).proxy(ip, port).execute();
                checkedIps.put(ip, port);
            } catch (IOException e) {

            }
        }
        int size = checkedIps.size();
        return checkedIps;
    }
}
