package com.qcg.thread;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/25 16:59
 */
public class TestThread implements Runnable {
    public static int count;
    String[] strings;
    public TestThread(String[] strings) {
        this.strings = strings;
    }

    public TestThread() {
    }

    public int getCount(){
        return count;
    }

    @Override
    public void run() {
        for (String string:strings){
            System.out.println("string = " + string);
        }
        count ++;
    }
}
