package com.mybatis.executor;

import com.mybatis.config.Configuration;
import com.mybatis.statement.MappedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 10:49
 * 执行sql的核心类
 */
public class DefaultExecutor implements Executor {
    private Configuration conf;

    public DefaultExecutor(Configuration conf) {
        this.conf = conf;
    }

    public <E> List<E> query(MappedStatement ms, Object parameter) {
//        System.out.println(ms.getSql());
//        System.out.println(ms.getSourceId());
        List<E>result=new ArrayList<E>();
        try {
            // 1.加载驱动
            Class.forName(conf.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //2.获取连接
            Connection connection=DriverManager.getConnection(conf.getJdbcUrl(),conf.getJdbcUsername(),conf.getJdbcPassword());
            PreparedStatement preparedStatement = connection.prepareStatement(ms.getSql());
            perameterized(preparedStatement,parameter);
            ResultSet resultSet = preparedStatement.executeQuery();



        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    private<E>void handlerResult(ResultSet resultSet,List<E> ret,String resultMap){
        Class<E> clazz=null;
        try {
            clazz = (Class<E>)Class.forName(resultMap);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Object entity = clazz.newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void perameterized(PreparedStatement preparedStatement,Object parameter) throws SQLException{
        if (parameter instanceof Integer){
            preparedStatement.setInt(1,(Integer)parameter);
        }else if(parameter instanceof Long){
            preparedStatement.setLong(1,(Long)parameter);
        }else if(parameter instanceof String){
            preparedStatement.setString(1,(String)parameter);
        }
    }
}
