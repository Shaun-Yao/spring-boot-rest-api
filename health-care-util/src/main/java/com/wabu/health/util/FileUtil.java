package com.wabu.health.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "upload")
public class FileUtil {

	private String physicalPath;//上传文件存储物理路径
	private String webPath;//上传文件存web访问路径
	
	public String saveImage(MultipartFile multipartFile) throws IOException {
		
		String fileName = multipartFile.getOriginalFilename();//原文件名
		int idx = fileName.lastIndexOf(".");          
		String suffix= fileName.substring(idx); //文件后缀     
		String time = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
		String newFileName = time.concat(suffix);
		String filePath = physicalPath.concat(newFileName);
		FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(filePath));
		
		String path = webPath.concat(newFileName);
		return path;
	}
}
