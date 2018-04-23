package com.qcg.service;

import com.qcg.dao.PageDao;
import com.qcg.model.Page;

import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/23 14:49
 */
public class PageService {
    private PageDao pageDao = new PageDao();

    public PageService(){

    }

    /**
     * 把歌单信息增加到库中
     */
    public void addSheets(){
        SongSheet ss = new SongSheet();
        String baseUrl = "http://music.163.com/discover/playlist";
        List<Page> pageList = ss.getSongSheets(baseUrl);
        for (Page page:pageList) {
            //先判断库中是否有此url
            if (!pageDao.query(page.getUrl())){
                pageDao.add(page);
            }
        }
    }

    public void add(){

    }
}
