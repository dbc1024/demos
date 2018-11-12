package com.ectrip.upload.model;

/**
 * zimg图片上传结果info字段封装类
 *
 * @Author CaiShuangZong
 * @Date 2018-5-22
 */
public class Info {
	
	private String md5;	//zimg返回的图片路径
	
	private String size;
	
	private String url; //拼接了域名的全路径
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
