package com.taotao.manage.controller.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.item.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {

	private static final Logger log = LoggerFactory.getLogger(ItemController.class);
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<EasyUIResult> queryItemList(@RequestParam("page") Integer page,
			@RequestParam(value = "rows") Integer pageSize) {
		try {
			PageInfo<Item> pageInfo = itemService.queryListByPage(page, pageSize);
			EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			log.error("查询商品列表出错，原因:", e);
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	/**
	 * 新增商品
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public ResponseEntity<Void> saveItem(Item item,String desc) {
		try {
			Integer count = itemService.saveItem(item,desc);
			if (count != 1) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			log.error("新增商品出错，原因:", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		// return ResponseEntity.status(HttpStatus.OK).body(null);
		return ResponseEntity.ok(null);

	}
	
	/**
	 * 新增商品
	 * 
	 * @param item
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public ResponseEntity<Void> edit(Item item,String desc) {
		try {
			Integer count = itemService.editItem(item,desc);
			if (count != 1) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			}
		} catch (Exception e) {
			log.error("编辑商品出错，原因:", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		// return ResponseEntity.status(HttpStatus.OK).body(null);
		return ResponseEntity.ok(null);

	}


}
