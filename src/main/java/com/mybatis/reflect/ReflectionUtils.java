package com.mybatis.reflect;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 21:11
 */
public class ReflectionUtils {

    public static void setProTobeanFromResult(Object entity, ResultSet resultSet) throws SQLException {
     Field[]decfields=entity.getClass().getDeclaredFields();
        for (int i = 0; i <decfields.length ; i++) {
            if(decfields[i].getType().getSimpleName().equals("String")){
                setProToBean(entity,decfields[i].getName(),resultSet.getString(decfields[i].getName()));
            }else if(decfields[i].getType().getSimpleName().equals("Long")){
                setProToBean(entity,decfields[i].getName(),resultSet.getLong(decfields[i].getName()));
            }else if(decfields[i].getType().getSimpleName().equals("Integer")){
                setProToBean(entity,decfields[i].getName(),resultSet.getInt(decfields[i].getName()));
            }
            
        }
    }
//    将属性值赋给对象
    private static void setProToBean(Object bean ,String name ,Object value){
        Field filed;
        try {
             filed = bean.getClass().getDeclaredField(name);
             filed.setAccessible(true);
             filed.set(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
