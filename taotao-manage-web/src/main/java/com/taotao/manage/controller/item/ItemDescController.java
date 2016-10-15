package com.taotao.manage.controller.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.item.ItemDescService;
import com.taotao.manage.service.item.ItemService;

@Controller
@RequestMapping("item/desc")
public class ItemDescController {

	private static final Logger log = LoggerFactory.getLogger(ItemDescController.class);
	@Autowired
	private ItemDescService itemDescService;

	@RequestMapping(value = "{itemId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ItemDesc> queryItemById(@PathVariable("itemId")Long itemId) {
		try {
			ItemDesc itemDesc = itemDescService.queryById(itemId);
			return ResponseEntity.ok(itemDesc);
		} catch (Exception e) {
			log.error("查询商品列表出错，原因:", e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
