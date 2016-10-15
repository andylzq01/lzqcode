package com.taotao.manage.mapper;

import java.util.List;

import com.taotao.manage.base.mapper.TaotaoMapper;
import com.taotao.manage.pojo.Item;

public interface ItemMapper extends TaotaoMapper<Item> {

	public abstract List<Item> queryItemListOrderByUpdated();

}
