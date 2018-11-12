/*package com.ectrip.upload.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.Tools;
import com.ectrip.model.upload.Upfile;
import com.ectrip.upload.service.iservice.IUpfileService;



*//**
 * Copy Right Information : Ectrip Package : com.ectrip.upload.action ClassName
 * :SingleFileUploadAction.java JDK version used : jdk1.5 User : likai Version :
 * Modification history :2009-6-10 下午04:27:23
 *//*
public class SingleFileUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	IUpfileService upfileService;

	public void setUpfileService(IUpfileService upfileService) {
		this.upfileService = upfileService;
	}

	private File myFile;
	private String contentType;
	private String fileName;
	private String maxSize;
	private String allowedTypes;
	private Upfile upfile;
	private String returnStr;
	private String waterMark;

	public void setMyFileContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setMyFileFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
	}

	public Upfile getUpfile() {
		return upfile;
	}

	public void setUpfile(Upfile upfile) {
		this.upfile = upfile;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}

	
	public String getWaterMark() {
		return waterMark;
	}

	public void setWaterMark(String waterMark) {
		this.waterMark = waterMark;
	}

	// **************************** action method ****************************
	public String upfileOneAdd() {
		return SUCCESS;
	}

	public String upfileOneSave() {
		if (myFile == null) {
			this.addActionError(getText("please.choose.myFile"));
		} else {
			upfile.setFilesize(String.valueOf(myFile.length()));
			upfile.setFiletype(contentType);
			if (upfile.getUpname() == null || upfile.getUpname().equals("")) {
				this.addActionError(getText("upfile.upname.required"));
			}
			if (Long.parseLong(upfile.getFilesize()) > Long.parseLong(maxSize)) {
				this.addActionError(getText("upfile.filesize.error", new String[] { maxSize }));
			}
			String[] allowed = allowedTypes.split(",");
			boolean flag = false;
			for (int i = 0; i < allowed.length; i++) {
				if (upfile.getFiletype().equals(allowed[i])) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				this.addActionError(getText("upfile.filetype.error", new String[] { allowedTypes }));
			}
		}
		if (hasActionErrors()) {
			List errorList = (List) getActionErrors();
			String error = "";
			for (int i = 0; i < errorList.size(); i++) {
				error += "\"" + errorList.get(i).toString() + "\",";
			}
			error = error.substring(0, error.length() - 1);
			// {"error":["第1个上传文件大小已超系统允许上传范围，系统默认允许上传512000b的文件"]}
			returnStr = "{\"error\":[" + error + "]}";
		} else {
			upfileService.saveSingleFile(myFile, fileName, upfile, 0,waterMark);
			// {"upfilename":["200906120954010.jpg"],"upadder":["\/upfiles\/2009-06-12\/"],"upid":["15"],"upname":["22222"],"error":null}
			returnStr = "{\"upfilename\":[\"" + upfile.getUpfilename() + "\"],\"upadder\":[\""
					+ Tools.replaceAll(upfile.getUpadder(), "/", "\\/") + "\"],\"upid\":[\"" + upfile.getUpid()
					+ "\"],\"upname\":[\"" + upfile.getUpname() + "\"],\"error\":null}";
		}
		try {
			getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = getResponse().getWriter();
			out.print(returnStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	// default
	public String execute() {
		return SUCCESS;
	}

}
*/