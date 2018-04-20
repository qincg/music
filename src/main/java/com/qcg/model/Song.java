package com.qcg.model;

import java.io.Serializable;

/**
 * @Author: qcg
 * @Description: 歌曲信息
 * @Date: 2018/4/19 10:48
 */
public class Song implements Serializable {
    /**
     * 歌曲url
     */
    private String songUrl;
    /**
     * 歌名
     */
    private String songName;
    /**
     * 歌手
     */
    private String songer;
    /**
     * 评论数量
     */
    private long commentCount;

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSonger() {
        return songer;
    }

    public void setSonger(String songer) {
        this.songer = songer;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public Song() {

    }

    @Override
    public String toString() {
        return "SongService{" +
                "songUrl='" + songUrl + '\'' +
                ", songName='" + songName + '\'' +
                ", songer='" + songer + '\'' +
                ", commentCount=" + commentCount +
                '}';
    }
}
