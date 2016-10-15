package com.taotao.manage.service.item;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.base.BaseService;

@Service("itemService")
public class ItemService extends BaseService<Item> {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescService itemDescService;

	// @Override
	// public Mapper<ItemCat> getMapper() {
	// return this.itemCatMapper;
	// }

	//
	/**
	 * 查询商品列表
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public EasyUIResult queryItemList(Integer page, Integer pageSize) {
		ItemCat item = new ItemCat();
		return null;
	}

	public PageInfo<Item> queryListByPage(Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		return new PageInfo<Item>(itemMapper.queryItemListOrderByUpdated());
	}

	/**
	 * 新增商品
	 * @param item
	 * @param desc
	 * @return
	 */
	public Integer saveItem(Item item, String desc) {
		item.setId(null);
		item.setCreated(new Date());
		item.setStatus(1);
		item.setUpdated(item.getCreated());
		super.save(item);

		// 新增描述
		return itemDescService.saveItemDes(item.getId(), desc);

	}

	/**
	 * 编辑商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	public Integer editItem(Item item, String desc) {
//		item.setCreated(new Date());
		item.setUpdated(new Date());
//		super.update(item);
		super.updateSelective(item);
		return itemDescService.editItemDes(item.getId(), desc);
	}

}
