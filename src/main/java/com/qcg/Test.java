package com.qcg;

import com.qcg.model.Page;
import com.qcg.model.Song;
import com.qcg.service.PageService;
import com.qcg.service.SongService;
import com.qcg.service.SongSheet;
import com.qcg.util.FileUtil;
import com.qcg.util.LogUtil;
import com.qcg.util.XmlUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

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
        for (Page page:pageList){
            String pageUrl = page.getUrl();
            FileUtil.addContent(file,page+"\n\r",true);
            System.out.println("page = " + page);
            List<Song> songList = songService.getSongsOfSheet(pageUrl);
            for (Song song:songList){
                System.out.println("song = " + song);
                FileUtil.addContent(file,song+"\n\r",true);
            }
        }
    }

    public static void xmlTest(){
        XmlUtil xmlUtil = new XmlUtil();
        String userName = xmlUtil.getValue("username");
        System.out.println("userName = " + userName);
    }

    public static void logTest(){
        //Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        LogUtil logger = LogUtil.getLogger();
        for (int i = 0 ; i< 1000;i++) {
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

    public static void music(){
        PageService ps = new PageService();
        ps.addSheets();
        List<Page> pageList = ps.getAllSheet();
        for (Page page:pageList){
            ps.addSongInfo(page);
        }

    }

    public static void main(String[] args) {
//        xmlTest();
        //logTest();
        music();
    }
}
