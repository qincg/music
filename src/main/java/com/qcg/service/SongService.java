package com.qcg.service;

import com.qcg.model.Song;
import com.qcg.util.Html;
import com.qcg.util.HttpUtil;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 15:34
 */
public class SongService {

    /**
     * 获取完整的歌曲信息，在歌单中爬取的歌曲信息只是包含url，歌名，不完全
     * @param songUrl
     * @return
     */
    public Song getSongAllInfo(String songUrl){
        //此url为获取歌曲评论数  未加密的评论数
        String baseUrl = "http://music.163.com/api/v1/resource/comments/R_SO_4_";
        Document document = Jsoup.parse(Html.getHtml(songUrl));
        Song song = new Song();
        String songName = document.select("div.cnt div.tit em.f-ff2").text();
        String songer = document.select("div.cnt a.s-fc7").get(0).text();
        String singId = songUrl.substring(songUrl.indexOf("="));
        String result = HttpUtil.get(baseUrl+singId);
        JSONObject jsonObject = new JSONObject(result);
        long commentCount = jsonObject.getInt("total");
        song.setSongName(songName);
        song.setSongUrl(songUrl);
        song.setSonger(songer);
        song.setCommentCount(commentCount);
        return song;
    }

    /**
     * 获取歌单的所有歌曲信息
     * @param sheetUrl
     * @return
     */
    public List<Song> getSongsOfSheet(String sheetUrl){
        ArrayList<Song> songArrayList = new ArrayList<>();
        SongSheet songSheet = new SongSheet();
        List<String> songUrls = songSheet.getSongsUrlOfSheet(sheetUrl);
        SongService songService = new SongService();
        for (String songUrl:songUrls){
            Song song = songService.getSongAllInfo(songUrl);
            songArrayList.add(song);
        }
        return songArrayList;
    }
}
