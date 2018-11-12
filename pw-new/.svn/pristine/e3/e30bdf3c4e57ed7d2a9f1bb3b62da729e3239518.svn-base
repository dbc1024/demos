package com.ectrip.sys.other.controller;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.upload.model.UploadResult;
import com.ectrip.upload.utils.FileUploadUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件上传控制器
 *
 * @Author CaiShuangZong
 * @Date 2018-5-22
 */
@RestController
@RequestMapping(value = "fileUpload")
@Api(tags = "文件上传")
public class FileUploadController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(FileUploadController.class);
	
	
	@PostMapping(value = "v1/uploadPic", consumes="multipart/*", headers="content-type=multipart/form-data")
	@ApiOperation(value = "图片上传")
	public ResponseBean uploadPicture(@RequestParam MultipartFile file) {
		
		try {
			UploadResult result = FileUploadUtils.upload(file);
			if(result.isRet()) {
				return new ResponseBean(SUCCESS_CODE_200, "上传成功", result);
			}else {
				return new ResponseBean(ERROR_CODE_400, "上传失败", result);
			}
		} catch (IOException e) {
			e.printStackTrace();
            LOGGER.error("图片上传异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "图片上传异常", "异常信息："+ e.getMessage());
		}
	}
	

}
