package com.hqyt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="文件上传相关接口")
@RestController
@RequestMapping("fileUpload")
public class FileUploadController {

	@ApiOperation("文件上传")
	@PostMapping(value="v1/up", consumes="multipart/*", headers="content-type=multipart/form-data")
	String fileUpload(@RequestParam MultipartFile file) {
		
//		String name = file.getName();
		String originalFilename = file.getOriginalFilename();
		
		return originalFilename;
	}
	
	
	@ApiOperation("多文件上传")
	@PostMapping(value="v1/upMore", consumes="multipart/*", headers="content-type=multipart/form-data")
	String fileUploads(@RequestParam MultipartFile[] files) {
		
		String fileNames = "";
		
		for (MultipartFile multipartFile : files) {
			
			fileNames = fileNames + "|" + multipartFile.getOriginalFilename();
		}
		
		return fileNames;
		
	}
}
