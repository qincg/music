package com.qcg;

import com.qcg.model.Page;
import com.qcg.model.Song;
import com.qcg.service.SongSheet;
import com.qcg.util.FileUtil;
import com.qcg.util.Html;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 9:48
 */
public class Test {
    public void test() throws IOException {

        String html = Html.getHtml("https://music.163.com/discover/playlist");
        SongSheet ss = new SongSheet();
        List<Page> pages = ss.getSongSheets(html);
        for (Page page:pages){
            System.out.println("page.toString() = " + page.toString());
            File file = new File("D:\\songsOfSheet.html");
            String songsUrl = page.getUrl();
            List<Song> songList = ss.getSongsOfSheet(songsUrl);
            File file1 = new File("D:\\song.html");
            for (Song song:songList){
                String songHtml = Jsoup.connect(song.getSongUrl()).execute().body();
                FileUtil.addContent(file1,songHtml);
                break;
            }
            /*String songHtml = Jsoup.connect(page.getUrl()).execute().body();
            FileUtil.addContent(file,songHtml);*/
            break;
        }
    }

    public static void main(String[] args) {

    }
}
