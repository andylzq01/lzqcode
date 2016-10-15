package com.taotao.manage.controller.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.item.ItemCatService;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public List<ItemCat> queryItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId)
	{
		return itemService.queryItemCatList(parentId);
	}

}
