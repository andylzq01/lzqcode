// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ItemParamController.java

package com.taotao.manage.controller.item;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ItemParam;
import com.taotao.manage.service.item.ItemParamService;

@Controller
@RequestMapping("item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;

	@RequestMapping(value = "{itemCatId}", method = RequestMethod.GET)
	public ResponseEntity<ItemParam> queryItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId) {
		ItemParam queryParam = new ItemParam();
		queryParam.setItemCatId(itemCatId);
		List<ItemParam> itemParams = itemParamService.queryList(queryParam);
		if (itemParams == null || itemParams.isEmpty())
			return ResponseEntity.ok(null);
		else
			return ResponseEntity.ok(itemParams.get(0));
	}

	@RequestMapping(value = "{itemCatId}", method = RequestMethod.POST)
	public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId") Long itemCatId, @RequestParam("paramData") String paramData) {
		try {
			ItemParam itemParam = new ItemParam();
			itemParam.setCreated(new Date());
			itemParam.setUpdated(itemParam.getCreated());
			itemParam.setItemCatId(itemCatId);
			itemParam.setParamData(paramData);
			itemParamService.save(itemParam);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
