// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PicUploadController.java

package com.taotao.manage.controller.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.PicUploadResult;
import com.taotao.manage.service.item.PropertieService;

@Controller
@RequestMapping("pic")
public class PicUploadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PicUploadController.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	// 允许上传的文件格式
	private static final String IMAGE_TYPE[] = { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };
	@Autowired
	private PropertieService propertieService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(value = "uploadFile") MultipartFile uploadFile, HttpServletResponse response)
			throws Exception {
		// 检查图片格式
		boolean isLegal = false;
		for (String type : IMAGE_TYPE) {
 			if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
				isLegal = true;
				break;
			}
		}
		// 封装PicUploadResult对象，并将文件的byte数组防止到result对象中
 		PicUploadResult fileUploadResult = new PicUploadResult();
		fileUploadResult.setError(Integer.valueOf(isLegal ? 0 : 1));
		// 文件保存路径
		String filePath = getFilePath(uploadFile.getOriginalFilename());
  		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Pic file upload .[{}] to [{}] .", uploadFile.getOriginalFilename(), filePath);
		// 访问路径
		String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, propertieService.REPOSITORY_PATH),
				"\\", "/");
		fileUploadResult
				.setUrl((new StringBuilder()).append(propertieService.IMAGE_BASE_URL).append(picUrl).toString());
		// 写文件到磁盘
		File newFile = new File(filePath);
		uploadFile.transferTo(newFile);
		// 检查图片是否合法
		isLegal = false;
		try {
			// 通过文件的宽和高验证图片的合法性
			BufferedImage image = ImageIO.read(newFile);
			if (image != null) {
				fileUploadResult.setWidth((new StringBuilder()).append(image.getWidth()).append("").toString());
				fileUploadResult.setHeight((new StringBuilder()).append(image.getHeight()).append("").toString());
				isLegal = true;
			}
		} catch (IOException e) {
			LOGGER.error("上传的图片内容不合法，原因：", e);
		}
		fileUploadResult.setError(Integer.valueOf(isLegal ? 0 : 1));
		// 不合法将图片删除
		if (!isLegal)
			newFile.delete();
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		// response.setContentType("text/html");
		// 将fileUploadResult对象转化为json数据
		return mapper.writeValueAsString(fileUploadResult);
	}

	private String getFilePath(String sourceFileName) {
		String baseFolder = (new StringBuilder()).append(propertieService.REPOSITORY_PATH).append(File.separator)
				.append("images").toString();
//		String baseFolder = "d:\\upload\\images";
		Date nowDate = new Date();
		String fileFolder = (new StringBuilder()).append(baseFolder).append(File.separator)
				.append((new DateTime(nowDate)).toString("yyyy")).append(File.separator)
				.append((new DateTime(nowDate)).toString("MM")).append(File.separator)
				.append((new DateTime(nowDate)).toString("dd")).toString();
		// 创建目录
		File file = new File(fileFolder);
		if (!file.isDirectory())
			file.mkdirs();

		// 生成新的文件名称
		String fileName = (new StringBuilder()).append((new DateTime(nowDate)).toString("yyyyMMddhhmmssSSSS"))
				.append(RandomUtils.nextInt(100, 9999)).append(".")
				.append(StringUtils.substringAfterLast(sourceFileName, ".")).toString();
		return (new StringBuilder()).append(fileFolder).append(File.separator).append(fileName).toString();
	}

}
