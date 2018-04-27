package com.qcg.thread;

import com.qcg.model.Page;
import com.qcg.service.PageService;

/**
 * @Author: qcg
 * @Description: 多线程爬取歌单列表
 * @Date: 2018/4/27 14:08
 */
public class PageThread implements Runnable {
    private Page page;

    public PageThread(){

    }

    public PageThread(Page p) {
        this.page = p;
    }

    @Override
    public void run() {
        PageService ps = new PageService();
        ps.addSongInfo(page);
    }
}
