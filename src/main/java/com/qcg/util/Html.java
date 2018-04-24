package com.qcg.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 10:53
 */
public class Html {

    public static String getHtml(String url) {
        //全部歌单的url
        //String url = "https://music.163.com/#/discover/playlist";
        //timeout(3000)设置超时时间为3s
        //Map<String,String> map = new Hashtable<>();
        //map.put("userAgent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0");

        //Jsoup.connect(url).headers(map);
        Connection.Response response = null;
        try {
             response = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0").timeout(3000).execute();
        }catch (IOException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }
        //File file = new File("D:\\music.html");
        /*File file = new File("D:\\music1.html");
        FileUtil.addContent(file,response.body());*/
        if (response == null){
            return "";
        }
        return response.body();
    }
}
