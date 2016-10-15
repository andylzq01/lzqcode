// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ItemParam.java

package com.taotao.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.taotao.manage.pojo.base.BasePojo;


// Referenced classes of package com.taotao.manage.pojo:
//			BasePojo
@Table(name="tb_item_param")
public class ItemParam extends BasePojo
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "item_cat_id")
	private Long itemCatId;
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

	public Long getItemCatId()
	{
		return itemCatId;
	}

	public void setItemCatId(Long itemCatId)
	{
		this.itemCatId = itemCatId;
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
