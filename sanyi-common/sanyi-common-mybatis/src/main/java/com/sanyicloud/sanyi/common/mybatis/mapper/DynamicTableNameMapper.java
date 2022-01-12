package com.sanyicloud.sanyi.common.mybatis.mapper;

import org.apache.ibatis.annotations.*;

/**
 * by zhaowenyuan create 2021/12/21 16:24
 * todo 根据传递进来的范型获取对应的表名
 */
@Mapper
public interface DynamicTableNameMapper {

    @Select("select count(*) from information_schema.TABLES where table_name = #{tableName}")
    int tableIsExist(@Param("tableName") String tableName);

    String TABLE_IS_EXIST_SQL = "select count(*) from information_schema.TABLES where table_name = %s";

    @Update("CREATE TABLE ${tableName} like ${oldTableName}")
    void createTable(@Param("tableName") String tableName,
                     @Param("oldTableName") String oldTableName);

    String CREATE_TABLE_SQL = "CREATE TABLE %s like %s";

    @Delete("DROP TABLE ${tableName}")
    void dropTable(@Param("tableName") String tableName);

    String DROP_TABLE_SQL = "DROP TABLE %s";
}
