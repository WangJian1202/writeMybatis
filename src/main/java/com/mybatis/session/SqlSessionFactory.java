package com.mybatis.session;

import com.mybatis.config.Configuration;
import com.mybatis.statement.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 0:27
 */
//1.读取配置文件填充configuration
// 2.
public class SqlSessionFactory {

    private static final Configuration conf = new Configuration();

    private static final String MAPPER_CONFIG_LOCATION = "com.mybatis.mapper";
    private static final String DB_CONFIG_FILE = "db.properties";

    public SqlSessionFactory() {
        loadDbInfo();
        loadMaperInfo();
    }




    //加载数据库信息
    private static void loadDbInfo() {
        InputStream resourceAsStream = SqlSessionFactory.class.getClassLoader().getResourceAsStream(DB_CONFIG_FILE);
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        conf.setJdbcDriver(properties.get("driver").toString());
        conf.setJdbcUrl(properties.get("url").toString());
        conf.setJdbcUsername(properties.get("username").toString());
        conf.setJdbcPassword(properties.get("password").toString());

    }
    private static void loadMaperInfo(File file){
        SAXReader saxReader =new SAXReader();
        Document document=null;
        try {
            document= saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attribute("namespace").getData().toString();
        List <Element>selects = rootElement.elements("select");
        for (int i = 0; i <selects.size() ; i++) {
            Element element = selects.get(i);
            MappedStatement mappedStatement = new MappedStatement();
            String id = element.attribute("id").getData().toString();
            String resultType = element.attribute("resultMap").getData().toString();
            String sql = element.getData().toString();
            String sourceId=namespace+"."+id;
            mappedStatement.setNamespace(namespace);
            mappedStatement.setResultType(resultType);
            mappedStatement.setSql(sql);
            mappedStatement.setSourceId(sourceId);
            conf.getMappedStatements().put(sourceId,mappedStatement);
        }


    }

    private static void loadMaperInfo() {
        URL resources = null;
        resources = SqlSessionFactory.class.getClassLoader().getResource(MAPPER_CONFIG_LOCATION);
        File mappers = new File(resources.getFile());
        if (mappers.isDirectory()) {
            File[] listfiles = mappers.listFiles();
            for (File file : listfiles) {
                loadMaperInfo(file);
            }
        }
    }

    public Sqlsession openSession(){
        return new DefaultSqlsession(conf);
    }



}
