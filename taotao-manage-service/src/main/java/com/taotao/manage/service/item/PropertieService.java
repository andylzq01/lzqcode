// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PropertieService.java

package com.taotao.manage.service.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertieService
{
	@Value(value = "${image_base_url}")
	public String IMAGE_BASE_URL;
	@Value(value = "${repository_path}")
	public String REPOSITORY_PATH;
}
