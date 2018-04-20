package com.qcg.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String str = "";
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = httpClient.execute(httpGet);
            int status = closeableHttpResponse.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                HttpEntity entity = closeableHttpResponse.getEntity();
                InputStream is = entity.getContent();
                str = readInput(is);
                return str;
            }
        }catch (IOException e){
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
