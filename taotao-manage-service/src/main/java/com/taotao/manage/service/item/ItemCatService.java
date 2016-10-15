package com.taotao.manage.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.mapper.Mapper;
import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.base.BaseService;

@Service("itemCatService")
public class ItemCatService extends BaseService<ItemCat> {

	@Autowired
	private ItemCatMapper itemCatMapper;

//	@Override
//	public Mapper<ItemCat> getMapper() {
//		return this.itemCatMapper;
//	}

	//
	/**
	 * 根据父类ID查询子类目
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ItemCat> queryItemCatList(Long parentId) {
		ItemCat item = new ItemCat();
		item.setParentId(parentId);
		return super.queryList(item);
	}

}
