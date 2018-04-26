package com.qcg;

import com.qcg.model.Ip;
import com.qcg.model.Page;
import com.qcg.model.Song;
import com.qcg.service.PageService;
import com.qcg.service.SongService;
import com.qcg.service.SongSheet;
import com.qcg.thread.IPThread;
import com.qcg.thread.TestThread;
import com.qcg.util.*;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 9:48
 */
public class Test {
    public void test1() {
        //获取所有歌单
        String baseUrl = "http://music.163.com/discover/playlist";
        SongSheet songSheet = new SongSheet();
        List<Page> pageList = songSheet.getSongSheets(baseUrl);
        SongService songService = new SongService();
        File file = new File(Test.class.getClassLoader().getResource("") + "test.txt");
        for (Page page : pageList) {
            String pageUrl = page.getUrl();
            FileUtil.addContent(file, page + "\n\r", true);
            System.out.println("page = " + page);
            List<Song> songList = songService.getSongsOfSheet(pageUrl);
            for (Song song : songList) {
                System.out.println("song = " + song);
                FileUtil.addContent(file, song + "\n\r", true);
            }
        }
    }

    public static void xmlTest() {
        XmlUtil xmlUtil = new XmlUtil();
        String userName = xmlUtil.getValue("username");
        System.out.println("userName = " + userName);
    }

    public static void logTest() {
        //Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        LogUtil logger = LogUtil.getLogger();
        for (int i = 0; i < 1000; i++) {
            logger.debug("debug");
            logger.trace("trace");
            logger.error("error");
            logger.fatal("fatal");
            logger.warn("warn");
            logger.info("info");
        }
        /*Logger logger1 = LogManager.getLogger("my");
        logger1.debug("debug1");
        logger1.trace("trace1");
        logger1.error("error1");
        logger1.fatal("fatal1");
        logger1.warn("warn1");
        logger1.info("info1");
        LogUtil.getLogger();*/
    }

    public static void music() {
        PageService ps = new PageService();
        ps.addSheets();
        List<Page> pageList = ps.getAllSheet();
        for (Page page : pageList) {
            ps.addSongInfo(page);
        }

    }

    public static void ipTest() {
        try {
            //Connection connection = Jsoup.connect("http://www.baidu.com").proxy("",90);
            Connection.Response response = Jsoup.connect("http://www.baidu.com").proxy("122.114.31.177", 808).timeout(2000).execute();
            System.out.println("response.toString() = " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }
    }

    static void getIp() {
        Map<String, Integer> map = ProxyUtil.getIps();
        List<String> listKey = new ArrayList<>(500);
        List<Integer> listValue = new ArrayList<>(500);
        for (String key : map.keySet()) {
            listKey.add(key);
            listValue.add(map.get(key));
        }
        int temp = 10;
        int length = map.size() % temp == 0 ? map.size() / temp : map.size() / temp + 1;
        long start = System.currentTimeMillis();
        //ProxyUtil.checkIps(map);

        for (int i = 0; i < length; i++) {
            //分割map (0,9) (10,19) (20,29)
            Map<String, Integer> map1 = new HashMap<>(temp);
            List<String> list;
            List<Integer> list1;
            if (i == length - 1) {
                list = listKey.subList(i * 10, map.size());
                list1 = listValue.subList(i * 10, map.size());
            } else {
                list = listKey.subList(i * 10, (i + 1) * 10);
                list1 = listValue.subList(i * 10, (i + 1) * 10);
            }
            for (int y = 0; y < list.size(); y++) {
                map1.put(list.get(y), list1.get(y));
            }
            IPThread ipThread = new IPThread(map1);
            Thread thread = new Thread(ipThread);
            thread.start();
        }
        long end = System.currentTimeMillis();
        System.out.println("map.size() = " + map.size());
    }

    public static void testThread() {
        new Thread(new TestThread(new String[]{"123", "134", "145", "167"})).start();
        new TestThread(new String[]{"adsf", "sdfsd", "sdfs", "wer"}).run();
        new TestThread(new String[]{"12adssa", "dasd123", "sdfs2dfs"}).run();
    }


    public static void getRodom() {
        Random random = new Random(60);
        for (int i = 0; i < 1000; i++) {
            double temp = Math.floor(Math.random() * 60);
            int temp2 = random.nextInt(60);
            System.out.println("temp*60 = " + temp + "  temp2 = " + temp2);

        }
    }

    public static void getRandomIp() {
        Ip ip = MathUtil.getRandomIp();
        System.out.println("ip = " + ip);
    }

    public static void listTest() {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.remove(2);
        System.out.println("list.size() = " + list.size());
        for (Object o : list) {
            System.out.println("o = " + o);
        }
    }

    public static void main(String[] args) {
//        xmlTest();
        //logTest();
        //music();
        //ipTest();
        //getIp();
        getRandomIp();
        // testThread();
        //getRodom();
        //listTest();
    }
}
