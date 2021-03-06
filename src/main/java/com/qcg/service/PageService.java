package com.qcg.service;

import com.qcg.dao.PageDao;
import com.qcg.dao.SongDao;
import com.qcg.model.Page;
import com.qcg.model.Song;

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

    /**
     * 取出第一条未爬取的歌单记录
     */

    public Page queryFistSheet(){
        return pageDao.queryFirst(0);
    }

    /**
     * 从歌单获取歌曲信息，判断歌曲的评论数，然后，加入到数据库中
     * 调用此方法后直接修改page的状态为2（正在爬取），若出现异常，改回状态
     */
    public void addSongInfo(Page page){
        //修改页面状态
        boolean temp = updateStatus(page.getUrl(),2);
        boolean status = false;
        try {
            SongService ss = new SongService();
            List<Song> songList = ss.getSongsOfSheet(page.getUrl());
            SongDao sd = new SongDao();
            for (Song song : songList) {
                long commentCount = song.getCommentCount();
                //判断歌曲评论数是否过万，且数据库中没有此记录
                if (commentCount >= 10000 && sd.query(song.getSongUrl())) {
                    status = sd.add(song);
                    System.out.println("song = " + song);
                }
            }
        }catch (Exception e){
            updateStatus(page.getUrl(),0);
        }

        if (status) {
            //把page页面标记为已爬取
            updateStatus(page.getUrl(),1);
        }
    }

    /**
     * 获取所有未爬取的歌单
     */

    public List<Page> getAllSheet(){
        return pageDao.query(0);
    }

    /**
     * 更新page页面状态
     */
    public boolean updateStatus(String url,int status){
        return pageDao.update(url,status);
    }
}
