// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ItemParamItem.java

package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.taotao.manage.pojo.base.BasePojo;


// Referenced classes of package com.taotao.manage.pojo:
//			BasePojo
@Table(name="tb_item_param_item")
public class ItemParamItem extends BasePojo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "item_id")
	private Long itemId;
	@Column(name = "param_data")
	private String paramData;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getItemId()
	{
		return itemId;
	}

	public void setItemId(Long itemId)
	{
		this.itemId = itemId;
	}

	public String getParamData()
	{
		return paramData;
	}

	public void setParamData(String paramData)
	{
		this.paramData = paramData;
	}
}
