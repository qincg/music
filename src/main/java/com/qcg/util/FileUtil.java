package com.qcg.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/19 13:40
 */
public class FileUtil {
    /**
     * @param file
     * @param content
     * @param append 是否追加内容
     */
    public static void addContent(File file,String content,boolean append){
        //判断文件是否存在
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file,append);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bw != null){
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
