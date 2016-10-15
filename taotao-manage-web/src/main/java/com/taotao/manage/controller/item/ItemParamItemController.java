// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ItemParamItemController.java

package com.taotao.manage.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.manage.pojo.ItemParamItem;
import com.taotao.manage.service.item.ItemParamItemService;
@Controller
@RequestMapping("item/param/item")
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;

	public ResponseEntity<ItemParamItem> queryItemParamItem(Long itemId) {
		ItemParamItem param = new ItemParamItem();
		param.setItemId(itemId);
		List<ItemParamItem> itemParamItems = itemParamItemService.queryList(param);
		if (!itemParamItems.isEmpty())
			return ResponseEntity.ok(itemParamItems.get(0));
		else
			return ResponseEntity.ok(null);
	}
}
