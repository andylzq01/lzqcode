package com.taotao.manage.service.item;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.base.BaseService;

@Service("itemDescService")
public class ItemDescService extends BaseService<ItemDesc> {

	@Autowired
	private ItemDescMapper itemDescMapper;
	public Integer saveItemDes(Long itemId,String desc)
	{
		//新增描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		
		return itemDescMapper.insert(itemDesc);
		
	}
	
	/**
	 * 编辑商品描述信息
	 * @param itemId
	 * @param desc
	 * @return
	 */
	public Integer editItemDes(Long itemId,String desc)
	{
		//新增描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(itemId);
//		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
//		return itemDescMapper.updateByPrimaryKey(itemDesc);
		return itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		
	}

}
