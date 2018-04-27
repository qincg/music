package com.qcg.util;

import com.qcg.dao.IpDao;
import com.qcg.model.Ip;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 16:57
 */
public class HttpUtil {
    /**
     * get请求
     * @param url
     * @return
     */
    public static String get(String url){
        Ip ip = MathUtil.getRandomIp(url);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setProxy(new HttpHost(ip.getIp(),ip.getPort()));
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        String str = "";
        CloseableHttpResponse closeableHttpResponse = null;
        IpDao ipDao = new IpDao();
        try {
            closeableHttpResponse = httpClient.execute(httpGet);
            int status = closeableHttpResponse.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = closeableHttpResponse.getEntity();
                InputStream is = entity.getContent();
                str = readInput(is);
                return str;
            }else {
                ipDao.delete(ip);
            }
        }catch (IOException e){
            ipDao.delete(ip);
            e.printStackTrace();
        }finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String post(String url,String param){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(param,"UTF-8"));
        CloseableHttpResponse response = null;
        try {
            response = closeableHttpClient.execute(httpPost);
            InputStream is = response.getEntity().getContent();
            return readInput(is);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (response != null){
                try {
                    response.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static String readInput(InputStream is){
        if (is != null){
            StringBuffer sb = new StringBuffer();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }

            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                    if (br != null) {
                        br.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }
        return "";
    }
}
