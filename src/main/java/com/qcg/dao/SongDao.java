package com.qcg.dao;

import com.qcg.interfaces.DaoInterface;
import com.qcg.model.Song;
import com.qcg.util.JdbcUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author: qcg
 * @Description:
 * @Date: 2018/4/23 14:43
 */
public class SongDao implements DaoInterface<Song> {
    @Override
    public boolean add(Song song) {
        String sql = "insert into song(song_url,song_name,songer,comment_count) values(?,?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        Connection connection = JdbcUtil.getConnection();
        try {
            int result = queryRunner.update(connection, sql, song.getSongUrl(), song.getSongName(), song.getSonger(), song.getCommentCount());
            if (result ==1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean delete() {
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

    public List<Song> query() {
        return null;
    }
}