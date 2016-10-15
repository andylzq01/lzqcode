package com.taotao.manage.base.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.jdbc.SqlBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.scripting.xmltags.WhereSqlNode;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.EntityHelper.EntityColumn;
import com.github.abel533.mapperhelper.MapperHelper;

public class TaotaoMapperProvider extends MapperProvider {

	public TaotaoMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	public SqlNode deleteByIDS(MappedStatement ms) {
		Class<?> entityClass = getSelectReturnType(ms);
		Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
		EntityHelper.EntityColumn entityColumn = null;

		for (EntityHelper.EntityColumn entity : entityColumns) {
			entityColumn = entity;
			break;
		}
		EntityColumn column = entityColumn;
		List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
		// 开始拼接sql
		SqlBuilder.BEGIN();
		SqlBuilder.DELETE_FROM(tableName(entityClass));
		String sql = SqlBuilder.SQL();
		// 静态sql部分
		sqlNodes.add(new StaticTextSqlNode((new StringBuilder()).append(sql).append(" WHERE ")
				.append(column.getColumn()).append(" IN ").toString()));
		// 构造foreach sql
		SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode((new StringBuilder())
				.append("#{").append(column.getProperty()).append("}").toString()), "ids", "index",
				column.getProperty(), "(", ")", ",");
		sqlNodes.add(foreach);
		return new MixedSqlNode(sqlNodes);
	}

	/**
	 * 
	 * 查询
	 * 
	 *
	 * 
	 * @param ms
	 * 
	 * @return
	 */

	public SqlNode selectOne(MappedStatement ms) {

		Class<?> entityClass = getSelectReturnType(ms);

		// 修改返回值类型为实体类型

		setResultType(ms, entityClass);

		List<SqlNode> sqlNodes = new ArrayList<SqlNode>();

		// 静态的sql部分:select column ... from table

		sqlNodes.add(new StaticTextSqlNode("SELECT "

		+ EntityHelper.getSelectColumns(entityClass)

		+ " FROM "

		+ tableName(entityClass)));

		// 将if添加到<where>

		sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), getAllIfColumnNode(entityClass)));

		return new MixedSqlNode(sqlNodes);

	}

	public String insertList(MappedStatement ms) {
		final Class<?> entityClass = getSelectReturnType(ms);
		// 获取表的各项属性
		EntityHelper.EntityTable table = EntityHelper.getEntityTable(entityClass);
		// 开始拼sql
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		sql.append(table.getName());
		sql.append("(");
		boolean first = true;
		for (EntityHelper.EntityColumn column : table.getEntityClassColumns()) {
			if (!first) {
				sql.append(",");
			}
			sql.append(column.getColumn());
			first = false;
		}
		sql.append(") values ");
		sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
		sql.append("(");
		first = true;
		for (EntityHelper.EntityColumn column : table.getEntityClassColumns()) {
			if (!first) {

				sql.append(",");
			}
			sql.append("#{record.").append(column.getProperty()).append("}");
			first = false;
		}
		sql.append(")");
		sql.append("</foreach>");
		return sql.toString();
	}

}
