package com.mybatis.statement;

/**
 * @Author: WangJian
 * @Date: 2019/3/24 0:22
 * 保留一条sql语句
 */
public class MappedStatement {

    private String namespace;
    private String sourceId ;
    private String resultMap;
    private String sql;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getResultType() {
        return resultMap;
    }

    public void setResultType(String resultMap) {
        this.resultMap = resultMap;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
