package com.qcg.dao;

import com.qcg.interfaces.DaoInterface;
import com.qcg.model.Ip;
import com.qcg.util.JdbcUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/26 13:47
 */
public class IpDao implements DaoInterface<Ip> {
    @Override
    public boolean add(Ip ip) {
        String sql = "insert into ip(ip,port) values(?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try{
            int result = queryRunner.update(connection,sql,ip.getIp(),ip.getPort());
            if (result ==1){
                return true;
            }
        }catch (SQLException e){
            LogManager.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Ip ip) {
        String sql = "delete from ip where ip = ? and port = ?";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try{
            int result = queryRunner.update(connection,sql,ip.getIp(),ip.getPort());
            if (result == 1){
                return true;
            }
        }catch (SQLException e){
            LogManager.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean update(String url, int status) {
        return false;
    }

    @Override
    public boolean query(String url) {
        return false;
    }

    public List<Ip> query(){
        String sql = "select * from ip";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        List<Ip> ips = new ArrayList<>();
        try{
            ips = queryRunner.query(connection,sql,new BeanListHandler<Ip>(Ip.class));
        }catch (SQLException e){
            LogManager.getLogger().error(e.getMessage());
        }
        return ips;
    }
    public boolean query(Ip ip){
        String sql = "select * from ip where ip = ? and port = ?";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try{
            List ips = queryRunner.query(connection,sql,new ArrayListHandler(),ip.getIp(),ip.getPort());
            if (ips.size() > 0){
                return false;
            }
        }catch (SQLException e){
            LogManager.getLogger().error(e.getMessage());
        }
        return true;
    }
}
