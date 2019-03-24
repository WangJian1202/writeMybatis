package com.mybatis.session;

import java.util.List;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 9:46
 */
public interface Sqlsession {

     <E>List<E> selectList(String statement, Object parament);

     <T>T selectOne(String statement, Object parament);

     <T>T getMapper(Class<T>type);




}
