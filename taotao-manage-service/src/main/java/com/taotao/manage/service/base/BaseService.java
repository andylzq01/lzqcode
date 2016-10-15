package com.taotao.manage.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.base.mapper.TaotaoMapper;
import com.taotao.manage.pojo.base.BasePojo;

/**
 * 基类
 * 
 * @author Administrator
 *
 * @param <T>
 */
public abstract class BaseService<T extends BasePojo> {

	// spring 4 新特性、泛型注解
	@Autowired
	private TaotaoMapper<T> mapper;

	/**
	 * 需要子类实现，返回具体的Mapper
	 * 
	 * @return
	 */
	public TaotaoMapper<T> getMapper() {
		return this.mapper;
	}

	// private Mapper<T> mapper;
	//
	// public Mapper<T> getMapper()
	// {
	// return this.mapper;
	// }

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	public T queryById(Long id) {
		return getMapper().selectByPrimaryKey(id);

	}

	/**
	 * 查询集合
	 * 
	 * @param t
	 * @return
	 */
	public List<T> queryList(T t) {
		return getMapper().select(t);
	}

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<T> queryListByPage(T t, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询
		List<T> list = this.queryList(t);
		return new PageInfo<T>(list);
	}

	/**
	 * 统计数量
	 * 
	 * @param t
	 * @return
	 */
	public Integer queryCount(T t) {
		return this.getMapper().selectCount(t);
	}

	/**
	 * 保存数据
	 * 
	 * @param t
	 * @return 受影响行数
	 */
	public Integer save(T t) {
		return this.getMapper().insert(t);
//		return this.getMapper().insertSelective(t);
	}

	/**
	 * 保存数据，不为null的数据
	 * 
	 * @param t
	 * @return 受影响行数
	 */
	public Integer saveSelective(T t) {
		return this.getMapper().insertSelective(t);
	}

	/**
	 * 更新数据
	 * 
	 * @param t
	 * @return 受影响行数
	 */
	public Integer update(T t) {
		return this.getMapper().updateByPrimaryKey(t);
	}

	/**
	 * 更新数据,不为null作为参数更新
	 * 
	 * @param t
	 * @return 受影响行数
	 */
	public Integer updateSelective(T t) {
		return this.getMapper().updateByPrimaryKeySelective(t);
	}

	/**
	 * 根据主键删除数据
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteById(Long id) {
		return this.getMapper().deleteByPrimaryKey(id);
	}

}
