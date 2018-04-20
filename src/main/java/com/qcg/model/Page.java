package com.qcg.model;

import java.io.Serializable;

/**
 * @Author: qcg
 * @Description: 每个歌单的信息
 * @Date: 2018/4/19 10:45
 */
public class Page implements Serializable {
    /**
     * url
     */
    private String url;

    /**
     * 状态：0：未爬取；1：已爬取
     */
    private int status;
    /**
     * 页面的html信息
     */
    private String html;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 歌单标题
     */
    private String title;

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    /**
     * 播放数
     */

    private String playCount;

    public Page() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", playCount='" + playCount + '\'' +
                '}';
    }
}
