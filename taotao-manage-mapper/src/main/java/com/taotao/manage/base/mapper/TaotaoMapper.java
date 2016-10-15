// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TaotaoMapper.java

package com.taotao.manage.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface TaotaoMapper<T> {
	/**
	 * 根据主键批量删除
	 * 
	 * @param aobj
	 * @return
	 */
	@DeleteProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int deleteByIDS(@Param("ids") Object key);

	@SelectProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	T selectOne(T record);

	@SelectProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	List<T> select(T record);

	@SelectProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int selectCount(T record);

	@SelectProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	T selectByPrimaryKey(Object key);

	@InsertProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int insert(T record);

	@InsertProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int insertSelective(T record);

	@DeleteProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int delete(T record);

	@DeleteProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int deleteByPrimaryKey(Object key);

	@UpdateProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int updateByPrimaryKey(T record);

	@UpdateProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int updateByPrimaryKeySelective(T record);

	@SelectProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int selectCountByExample(Object example);

	@DeleteProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int deleteByExample(Object example);

	@SelectProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	List<T> selectByExample(Object example);

	@UpdateProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

	@UpdateProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int updateByExample(@Param("record") T record, @Param("example") Object example);

	/**
	 * 批量插入，支持数据库自增字段，支持回写
	 *
	 * @param recordList
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@InsertProvider(type = TaotaoMapperProvider.class, method = "dynamicSQL")
	int insertList(List<T> recordList);
}
