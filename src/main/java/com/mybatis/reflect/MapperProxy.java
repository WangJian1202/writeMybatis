package com.mybatis.reflect;

import com.mybatis.session.Sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 21:39
 */
public class MapperProxy implements InvocationHandler {
    private Sqlsession sqlsession;

    public MapperProxy(Sqlsession sqlsession) {
        this.sqlsession = sqlsession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> returnType = method.getReturnType();
        if(Collection.class.isAssignableFrom(returnType)){
            return sqlsession.selectList(method.getDeclaringClass().getName()+"."+method.getName(),args==null?null:args[0]);
        }else{
            return sqlsession.selectOne(method.getDeclaringClass().getName()+"."+method.getName(),args==null?null:args[0]);
        }

    }
}
