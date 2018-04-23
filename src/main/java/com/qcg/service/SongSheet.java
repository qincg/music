package com.qcg.service;

import com.qcg.model.Page;
import com.qcg.util.Html;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 14:36
 */
public class SongSheet {
    static String basePath = "https://music.163.com";

    /**
     * 获取所有歌单信息
     * @param baseUrl 网易云主链接
     * @return
     */
    //获取所有歌单信息
    public List<Page> getSongSheets(String baseUrl){
        String html = Html.getHtml(baseUrl);
        //根据html获取文档对象
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("u-cover u-cover-1");
        List<Page> pageList = new ArrayList<>();
        //遍历
        for (Element element:elements){
            Page page = new Page();
            //歌单标题
            String title = element.getElementsByClass("msk").get(0).attr("title");
            //链接
            String sheetUrl = basePath + element.getElementsByClass("msk").get(0).attr("href");
            //播放数
            String playCount = element.getElementsByClass("nb").get(0).text();
            page.setHtml(html);
            page.setTitle(title);
            page.setUrl(sheetUrl);
            page.setPlayCount(playCount);
            page.setStatus(0);
            pageList.add(page);
        }
        return pageList;
    }

    /**
     * 获取歌单的所有歌曲url
     * @param sheetUrl 歌单的url
     * @return
     */
    public List<String> getSongsUrlOfSheet(String sheetUrl){
        Document document = Jsoup.parse(Html.getHtml(sheetUrl));
        List<String> songList = new ArrayList<>();
        //获取歌单中歌曲所在位置
        Elements elements = document.select("ul.f-hide li");
       /* Element elements = document.getElementsByClass("f-hide").get(0);
        Elements elementsli = elements.getElementsByTag("li");*/
        for (Element element:elements){
            Elements elementSong = element.getElementsByTag("a");
            String songUrl = basePath + elementSong.attr("href");
            songList.add(songUrl);
        }
        return songList;
    }
}
