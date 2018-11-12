package com.ectrip.upload.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.upload.model.UploadResult;

/**
 * 文件上传工具类
 *
 * @Author CaiShuangZong
 * @Date 2018-5-22
 */
public class FileUploadUtils {
	private static final Logger LOGGER = LogManager.getLogger(FileUploadUtils.class);
	
	public static UploadResult upload(MultipartFile file) throws IOException {
		
		UploadResult result = new UploadResult();
		String imgHost = WebContant.GetKeyValue("IMGHOST");//zimg图片服务器路径
		try {
			PostMethod method = new PostMethod(imgHost);
	        
	        String fileName = file.getOriginalFilename();
	        method.setRequestHeader("Content-Type", fileName.substring(fileName.lastIndexOf(".") + 1));
	        
	        InputStream inputStream = file.getInputStream();
	        method.setRequestEntity(new InputStreamRequestEntity(inputStream));
	        
	        HttpClient client = new HttpClient();
	        int httpCode = client.executeMethod(method);
	        
	        if (httpCode != HttpStatus.SC_OK) {
	        	result.setRet(false);
	        	return result;
	        }
	        
	        String jsonResult = method.getResponseBodyAsString();
	        inputStream.close();
	        
	        result = JSON.parseObject(jsonResult, UploadResult.class);
	        result.getInfo().setUrl(imgHost+ "/" +result.getInfo().getMd5());
	        
	        return result;
	        
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("zimg图片上传失败:"+ StringUtil.toString_02(e));
            result.setRet(false);
        	return result;
		}
        
    }
}
