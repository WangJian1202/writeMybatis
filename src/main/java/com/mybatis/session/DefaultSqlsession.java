package com.mybatis.session;

import com.mybatis.config.Configuration;
import com.mybatis.executor.DefaultExecutor;
import com.mybatis.executor.Executor;
import com.mybatis.reflect.MapperProxy;
import com.mybatis.statement.MappedStatement;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 9:47
 */
public class DefaultSqlsession implements Sqlsession {

    private Configuration conf;
    private Executor executor;

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public DefaultSqlsession(Configuration conf) {
        this.conf = conf;
        executor=new DefaultExecutor(conf);
    }

    public <E> List<E> selectList(String statement, Object parament) {
        MappedStatement mappedStatement = conf.getMappedStatements().get(statement);
        return executor.query(mappedStatement,parament);
    }

    public <E> E selectOne(String statement, Object parament) {
        List<E> results = this.selectList(statement, parament);
        if(results==null||results.size()==0){
            return null;
        }
        if(results.size()==1){
            return results.get(0);
        }else{
            throw new RuntimeException("too many result");
        }
    }

    public <T> T getMapper(Class<T> type) {
        MapperProxy mapperProxy=new MapperProxy(this);
        return (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},mapperProxy);
    }




}
