package com.mybatis.executor;

import com.mybatis.statement.MappedStatement;

import java.util.List;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 10:48
 */
public interface Executor {
    public <E>List<E> query(MappedStatement ms,Object parameter);
}
