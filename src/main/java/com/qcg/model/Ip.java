package com.qcg.model;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/26 13:44
 */
public class Ip {
    private String ip;
    private int port;

    public Ip() {
    }

    public Ip(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Ip{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
