/*package com.ectrip.upload.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.Tools;
import com.ectrip.upload.service.iservice.IUpfileService;

*//**
 * Copy Right Information : Ectrip Package : com.ectrip.upload.action ClassName
 * :MultipleFileUploadAction.java JDK version used : jdk1.5 User : likai Version :
 * Modification history :2009-6-10 下午04:27:11
 *//*
public class MultipleFileUploadAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	IUpfileService upfileService;

	public void setUpfileService(IUpfileService upfileService) {
		this.upfileService = upfileService;
	}

	private File[] uploads;
	private String[] uploadFileNames;
	private String[] uploadContentTypes;
	private String maxSize;
	private String allowedTypes;
	private String[] upid;
	private String[] upname;
	private String[] upfrom;
	private String[] note;
	private String[] author;
	private String[] utype;//图片分类
	private String[] upfilename;
	private String[] upadder;
	private String abelong;
	private String returnStr;
	private String waterMark;
	public String[] getUpfrom() {
		return upfrom;
	}

	public void setUpfrom(String[] upfrom) {
		 
		this.upfrom = upfrom;
	}

	public String[] getNote() {
		return note;
	}

	public void setNote(String[] note) {
		this.note = note;
	}

	public String[] getAuthor() {
		return author;
	}

	public void setAuthor(String[] author) {
		 
		this.author = author;
	}
	
	public String[] getUtype() {
		return utype;
	}

	public void setUtype(String[] utype) {
		this.utype = utype;
	}

	public void setUpload(File[] upload) {
		this.uploads = upload;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileNames = uploadFileName;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentTypes = uploadContentType;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
	}

	public String[] getUpname() {
		return upname;
	}

	public void setUpname(String[] upname) {
		 
		this.upname = upname;
	}

	public String getAbelong() {
		return abelong;
	}

	public void setAbelong(String abelong) {
		this.abelong = abelong;
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
	public String upfileMoreAdd() {
		if (abelong == null || abelong.equals("")) {
			this.addActionError(getText("upload.abelong.error"));
			return INPUT;
		} else {
			return SUCCESS;
		}
	}

	public String upfileMoreSave() {
		if (uploads == null) {
			this.addActionError(getText("please.choose.myFile"));
		} else {
			for (int i = 0; i < uploads.length; i++) {
				if (upname[i] == null || uploads[i].equals("")) {
					this.addActionError(getText("upfiles.upname.required",
							new String[] { String.valueOf(i + 1) }));
				}
				if (uploads[i].length() > Long.parseLong(maxSize)) {
					this.addActionError(getText("upfiles.filesize.error",
							new String[] { String.valueOf(i + 1), maxSize }));
				}
				String[] allowed = allowedTypes.split(",");
				boolean flag = false;
				for (int j = 0; j < allowed.length; j++) {
					if (uploadContentTypes[i].equals(allowed[j])) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					this.addActionError(getText("upfiles.filetype.error",
									new String[] { String.valueOf(i + 1),
											allowedTypes }));
				}
				if (hasActionErrors()) {
					break;
				}
			}
		}
		if (hasActionErrors()) {
			List errorList = (List) getActionErrors();
			String error = "";
			for (int i = 0; i < errorList.size(); i++) {
				error += "\"" + errorList.get(i).toString() + "\",";
			}
			error = error.substring(0, error.length() - 1);
			// {"error":["第1个上传文件大小已超系统允许上传范围，系统默认允许上传512000b的文件","第2个上传文件格式错误"]}
			returnStr = "{\"error\":[" + error + "]}";
		} else {
			upid = new String[uploads.length];
			upfilename = new String[uploads.length];
			upadder = new String[uploads.length]; 
			 
			upfileService.saveMultipeFile(uploads, uploadFileNames,
					uploadContentTypes, upid, upname,upfrom,note,author, utype,upfilename, upadder,
					abelong,waterMark);
			 
			// {"upfilename":["200906120954010.jpg","200906120954031.jpg"],"upadder":["\/upfiles\/2009-06-12\/","\/upfiles\/2009-06-12\/"],"upid":["14","15"],"upname":["11111","22222",""],"error":null}
			String strupfilename = "";
			String strupadder = "";
			String strupid = "";
			String strupname = "";
			for (int i = 0; i < upfilename.length; i++) {
				strupfilename += "\"" + upfilename[i] + "\",";
				strupadder += "\"" + Tools.replaceAll(upadder[i], "/", "\\/")
						+ "\",";
				strupid += "\"" + upid[i] + "\",";
				strupname += "\"" + upname[i] + "\",";
			}
			strupfilename = strupfilename.substring(0,
					strupfilename.length() - 1);
			strupadder = strupadder.substring(0, strupadder.length() - 1);
			strupid = strupid.substring(0, strupid.length() - 1);
			strupname = strupname.substring(0, strupname.length() - 1);
			returnStr = "{\"upfilename\":[" + strupfilename + "],\"upadder\":["
					+ strupadder + "],\"upid\":[" + strupid + "],\"upname\":["
					+ strupname + "],\"error\":null}";
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
 
}
*/