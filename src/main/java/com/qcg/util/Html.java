package com.qcg.util;

import com.qcg.dao.IpDao;
import com.qcg.model.Ip;
import org.apache.logging.log4j.LogManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 10:53
 */
public class Html {
    /**
     * 使用代理ip随机访问:获取ip总长度，然后取随机数
     * @param url
     * @return
     */
    public static String getHtml(String url) {
        Ip ip = MathUtil.getRandomIp(url);
        Connection.Response response = null;
        try {
             response = Jsoup.connect(url).proxy(ip.getIp(),ip.getPort()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:59.0) Gecko/20100101 Firefox/59.0").timeout(3000).execute();
        }catch (IOException e){
            new IpDao().delete(ip);
            LogManager.getLogger().error(e.getMessage());
        }
        //File file = new File("D:\\music.html");
        /*File file = new File("D:\\music1.html");
        FileUtil.addContent(file,response.body());*/
        return response.body();
    }
}
