package com.qcg.interfaces;

import java.util.List;

/**
 * dao层的接口，即：增删改查
 */
public interface DaoInterface<T> {

    /**
     * 增
     */
    boolean add(T t);

    /**
     * 删
     */
    boolean delete();

    /**
     * 根据url修改状态
     */
    boolean update(String url,int status);

    /**
     * 查询是否有此url信息
     */
    boolean query(String url);
}
