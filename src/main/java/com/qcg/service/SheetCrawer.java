package com.qcg.service;

import com.qcg.model.Page;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qcg
 * @Description: 爬取页面，首先爬取歌单，把歌单url加入到爬取列表
 * @Date: 2018/4/20 15:23
 */
public class SheetCrawer {

    public SheetCrawer() {
        init();
    }

    public List<String> addUrl(String url){
        List<String> list = new ArrayList<>();
        list.add(url);
        return list;
    }
    private void init(){
        String baseUrl = "http://music.163.com/discover/playlist";
        SongSheet ss = new SongSheet();
        List<Page> pageList = ss.getSongSheets(baseUrl);
        for (Page page:pageList){
            String sheetUrl = page.getUrl();
            addUrl(sheetUrl);
        }
    }

}
