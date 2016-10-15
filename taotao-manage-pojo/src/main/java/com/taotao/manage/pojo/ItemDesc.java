// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ItemDesc.java

package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.taotao.manage.pojo.base.BasePojo;
@Table(name="tb_item_desc")
public class ItemDesc extends BasePojo
{
	@Id
	@Column(name="item_id")
	private Long itemId;
	@Column(name="item_desc")
	private String itemDesc;

	public ItemDesc()
	{
	}

	public Long getItemId()
	{
		return itemId;
	}

	public void setItemId(Long itemId)
	{
		this.itemId = itemId;
	}

	public String getItemDesc()
	{
		return itemDesc;
	}

	public void setItemDesc(String itemDesc)
	{
		this.itemDesc = itemDesc;
	}
}
