package com.qcg;

import com.qcg.model.Page;
import com.qcg.model.Song;
import com.qcg.service.SongService;
import com.qcg.service.SongSheet;
import com.qcg.util.FileUtil;
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
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        logger.debug("debug");
        logger.trace("trace");
        logger.error("error");
        logger.fatal("fatal");
        logger.warn("warn");
        logger.info("info");
        Logger logger1 = LogManager.getLogger(Test.class);
        logger1.debug("debug");
        logger1.trace("trace");
        logger1.error("error");
        logger1.fatal("fatal");
        logger1.warn("warn");
        logger1.info("info");
    }

    public static void main(String[] args) {
//        xmlTest();
        logTest();
    }
}
