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
     * 向文件增加内容（全覆盖）
     * @param file
     * @param content
     */
    public static void addContent(File file,String content){
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
            fw = new FileWriter(file);
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
