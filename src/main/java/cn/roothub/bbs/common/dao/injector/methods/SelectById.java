package cn.roothub.bbs.common.dao.injector.methods;

import cn.roothub.bbs.common.dao.enums.SqlMethod;
import cn.roothub.bbs.common.dao.metadata.TableInfo;

import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据 ID 查询一条数据
 *
 * @Author: miansen.wang
 * @Date: 2019/10/16 22:06
 */
public class SelectById extends AbstractMethod{

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod selectById = SqlMethod.SELECT_BY_ID;
        String sqlScript = String.format(selectById.getSql(), initSqlSelectColumns(tableInfo),
                tableInfo.getTableName(), tableInfo.getPrimaryKeyColumn(), "${id}");
        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sqlScript, modelClass);
        return this.addMappedStatement(mapperClass, selectById.getMethod(), sqlSource, SqlCommandType.SELECT, String.class, null, modelClass, new NoKeyGenerator(), null, null);
    }
}