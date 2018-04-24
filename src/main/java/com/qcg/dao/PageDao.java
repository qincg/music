package com.qcg.dao;

import com.qcg.interfaces.DaoInterface;
import com.qcg.model.Page;
import com.qcg.util.JdbcUtil;
import com.qcg.util.LogUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageDao implements DaoInterface<Page> {
    @Override
    public boolean add(Page page) {
        String sql = "insert into page(url,status,title,type) values(?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try {
            int result = queryRunner.update(connection, sql, page.getUrl(), page.getStatus(), page.getTitle(), page.getType());
            if (result == 1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    /**
     * 修改爬取状态
     * @param url
     * @param status
     * @return
     */
    @Override
    public boolean update(String url,int status) {
        String sql = "update page set status = ? where url = ?";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try {
            int result = queryRunner.update(connection, sql, status,url);
            if (result == 1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean query(String url) {
        String sql = "select * from page where url = ?";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try {
            List list = queryRunner.query(connection, sql, new ArrayListHandler(), url);
            if (list.size() == 1){
                return true;
            }else if (list.size() > 1){
                LogUtil.getLogger().error("page有重复url");
            }
        }catch (SQLException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    /**
     * 查询未爬取页面的信息
     * @return
     */
    public List<Page> query(int type) {
        String sql = "select * from page where status = 0 and type = ?";
        List<Page> pageList = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try {
            pageList = queryRunner.query(connection, sql, new BeanListHandler<Page>(Page.class),type);
        }catch (SQLException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return pageList;
    }

    /**
     * 查询第一条未爬取的记录
     * @param type
     */
    public Page queryFirst(int type){
        String sql = "select * from page where status = 0 and type = ?";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        Page page = new Page();
        try {
            page = queryRunner.query(connection, sql, new BeanHandler<Page>(Page.class), type);
        }catch (SQLException e){
            e.printStackTrace();
            LogUtil.getLogger().error(e.getMessage());
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return page;
    }
}
