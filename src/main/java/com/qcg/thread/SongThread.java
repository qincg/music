package com.qcg.thread;

import com.qcg.model.Song;
import com.qcg.service.SongService;

import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/27 16:31
 */
public class SongThread implements Runnable{
    static List<Song> songList;
    String songUrl;
    public SongThread(String songUrl) {
        this.songUrl = songUrl;
    }

    @Override
    public void run() {
        SongService ss = new SongService();
        Song song = ss.getSongAllInfo(songUrl);
        songList.add(song);
    }
}
