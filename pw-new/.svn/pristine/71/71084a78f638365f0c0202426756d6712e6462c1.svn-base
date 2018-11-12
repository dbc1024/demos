/*package com.ectrip.base.common.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.ectrip.upload.service.UpfileService;
import com.fredck.FCKeditor.uploader.SimpleUploaderServlet;
*//**
 * FCK 编辑器相关的处理类,主要处理上传,
 * 图片处理
 * @author lijin
 *
 *//*
public class FckUploadServlet extends SimpleUploaderServlet {
	private static final long serialVersionUID = 1L;
	private static final double m = 640.0;
	private static final double s = 120.0;
	private static final String WATERMARKPATH = "watermark.png";

	public FckUploadServlet() {
		super();
	}

	public void init() throws ServletException {
		super.init();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
  *//**
   * 对于上传图片进行处理处理，上传一个图片时，同时生成两幅图片，
   * 一幅中图片，尺寸是原图的一半，
   * 再生成一幅小尺寸的图片，大是 120，120 向素，
   *//*
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		String   pathFileName  = null;//super.getPathFileName();
		//System.out.println("pathFileName="+pathFileName);
		//System.out.println("pathFileName"+super.getFileSzie());
		if (request.getParameter("Type").toLowerCase().equals("image")) {
			UpfileService upfileService = new UpfileService();
			if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
				String upadder    =  pathFileName.substring(0, pathFileName.lastIndexOf("/"));
				String upfilename =  pathFileName.substring(pathFileName.lastIndexOf("/") + 1);
				upfileService.image(m, upadder + "/", upfilename, "m");
				upfileService.image(s, upadder + "/", upfilename, "s");
				String mImgPath = upadder + "/m" + upfilename;
				String waterPath = upadder.substring(0, upadder.lastIndexOf("/")) + "/" + WATERMARKPATH;
				upfileService.imgMark(mImgPath, waterPath, mImgPath);
			} else {
		
				String upadder    = pathFileName.substring(0, pathFileName.lastIndexOf("\\"));
				System.out.println("upadder="+upadder);
				String upfilename = pathFileName.substring(   pathFileName.lastIndexOf("\\") + 1);
				System.out.println("upfilename="+upfilename);
				upfileService.image(m, upadder + "\\", upfilename, "m");
				upfileService.image(s, upadder + "\\", upfilename, "s");
				String mImgPath = upadder + "\\m" + upfilename;
				String waterPath = upadder.substring(0, upadder.lastIndexOf("\\")) + "\\" + WATERMARKPATH;
				upfileService.imgMark(mImgPath, waterPath, mImgPath);
			}
		}
	 System.out.println("fck upload ok");
	}
}
*/