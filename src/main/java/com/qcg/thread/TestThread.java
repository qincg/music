package com.qcg.thread;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/25 16:59
 */
public class TestThread implements Runnable {
    String[] strings;
    public TestThread(String[] strings) {
        this.strings = strings;
    }

    @Override
    public void run() {
        for (String string:strings){
            System.out.println("string = " + string);
        }
    }
}
