package com.qcg;

import com.qcg.model.Page;
import com.qcg.model.Song;
import com.qcg.service.SongService;
import com.qcg.service.SongSheet;
import com.qcg.util.FileUtil;
import com.qcg.util.Html;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 9:48
 */
public class Test {
    public static void main(String[] args) {
        //获取所有歌单
        String baseUrl = "http://music.163.com/discover/playlist";
        SongSheet songSheet = new SongSheet();
        List<Page> pageList = songSheet.getSongSheets(baseUrl);
        SongService songService = new SongService();
        File file = new File("D:\\test.txt");
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
}
