/*
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2005 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: SimpleUploaderServlet.java
 * 	Java File Uploader class.
 * 
 * Version:  2.3
 * Modified: 2005-08-11 16:29:00
 * 
 * File Authors:
 * 		Simone Chiaretta (simo@users.sourceforge.net)
 

package com.fredck.FCKeditor.uploader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.model.upload.Attach;
import com.ectrip.model.upload.Upfilev5;

*//**
 * Servlet to upload files.<br>
 * This servlet accepts just file uploads, eventually with a parameter specifying file type
 * 
 * @author Simone Chiaretta (simo@users.sourceforge.net)
 *//*

public class SimpleUploaderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String baseDir;
	private static String maxSize;
	private static boolean debug = true;
	private static boolean enabled = false;
	private static Hashtable allowedExtensions;
	private static Hashtable deniedExtensions;
	private String pathFileName;
	private long fileSzie;
	private String fileName;
	private String upid = "0";
	private String newFileName;
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getUpid() {
		return upid;
	}

	public void setUpid(String upid) {
		this.upid = upid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		if (fileName != null && !"".equals(fileName)) {
			fileName = fileName.toLowerCase();
		}
		this.fileName = fileName;
	}

	public String getPathFileName() {
		return pathFileName;
	}

	*//**
	 * Initialize the servlet.<br>
	 * Retrieve from the servlet configuration the "baseDir" which is the root of the file repository:<br>
	 * If not specified the value of "/UserFiles/" will be used.<br>
	 * Also it retrieve all allowed and denied extensions to be handled.
	 *//*
	public void init() throws ServletException {

		debug = (new Boolean(getInitParameter("debug"))).booleanValue();
		if (debug)
			System.out.println("\r\n---- SimpleUploaderServlet initialization started ----");
		baseDir = getInitParameter("baseDir");
		enabled = (new Boolean(getInitParameter("enabled"))).booleanValue();
		if (baseDir == null)
			baseDir = "/upfiles/";
		String realBaseDir = getServletContext().getRealPath(baseDir);
		File baseFile = new File(realBaseDir);
		if (!baseFile.exists()) {
			baseFile.mkdir();
		}
		maxSize = getInitParameter("maxSize");
		if (maxSize == null) {
			maxSize = "512000";
		}
		allowedExtensions = new Hashtable(3);
		deniedExtensions = new Hashtable(3);

		allowedExtensions.put("File", stringToArrayList(getInitParameter("AllowedExtensionsFile")));
		deniedExtensions.put("File", stringToArrayList(getInitParameter("DeniedExtensionsFile")));

		allowedExtensions.put("Image", stringToArrayList(getInitParameter("AllowedExtensionsImage")));
		deniedExtensions.put("Image", stringToArrayList(getInitParameter("DeniedExtensionsImage")));

		allowedExtensions.put("Flash", stringToArrayList(getInitParameter("AllowedExtensionsFlash")));
		deniedExtensions.put("Flash", stringToArrayList(getInitParameter("DeniedExtensionsFlash")));
		if (debug)
			System.out.println("---- SimpleUploaderServlet initialization completed ----\r\n");
		this.fileName = "";
		this.pathFileName = "";
	}

	*//**
	 * Manage the Upload requests.<br>
	 * 
	 * The servlet accepts commands sent in the following format:<br>
	 * simpleUploader?Type=ResourceType<br>
	 * <br>
	 * It store the file (renaming it in case a file with the same name exists) and then return an HTML file with a
	 * javascript command in it.
	 * 
	 *//*
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (debug) {
			System.out.println("--- BEGIN DOPOST ---");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String typeStr = request.getParameter("Type");
		String upfrom = request.getParameter("upfrom");// ��Դ
		String note = request.getParameter("note");// ˵��
		String author = request.getParameter("author");// ����
		if (baseDir == null || "".equals(baseDir)) {
			baseDir = "/upfiles/";
		}
		this.path = baseDir;
		String currentPath = baseDir + Tools.getDays() + "/";
		// mkdirs begin
		try {
			File f = new File(getServletContext().getRealPath(currentPath));
			if (!f.exists()) {
				f.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// mkdirs begin
		String currentDirPath = getServletContext().getRealPath(currentPath);
		currentPath = request.getContextPath() + currentPath;
		String retVal = "0"; // ���ز���
		String newName = ""; // ����ͼƬ����
		String fileUrl = ""; // ����ͼƬ��ַ
		String abelong = "";// ��������
		String errorMessage = "";
		if (enabled) {
			DiskFileUpload upload = new DiskFileUpload();
			try {
				List items = upload.parseRequest(request);
				Map fields = new HashMap();
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField())
						fields.put(item.getFieldName(), item.getString());
					else
						fields.put(item.getFieldName(), item);
				}
				abelong = (String) fields.get("abelong");
				FileItem uplFile = (FileItem) fields.get("NewFile");
				if (uplFile.getSize() > new Long(maxSize).longValue()) {
					retVal = "101";
					errorMessage = "�ϴ��ļ����������ֻ���ϴ�" + maxSize + "b���ļ�";
				} else {
					String fileNameLong = uplFile.getName();
					fileNameLong = fileNameLong.replace('\\', '/');
					String[] pathParts = fileNameLong.split("/");
					String fileName = pathParts[pathParts.length - 1];
					if (fileName != null && !"".equals(fileName)) {
						fileName = fileName.toLowerCase();
					}
					String ext = getExtension(fileName);
					if (this.fileName == null || "".equals(this.fileName)) {
						this.fileName = fileName;
					} else {
						fileName = this.fileName;
					}
					newName = Tools.getDayTime() + "0." + ext;
					this.newFileName = newName;
					File pathToSave = new File(currentDirPath, newName);
					fileUrl = currentPath + newName;
					if (extIsAllowed(typeStr, ext)) {
						while (pathToSave.exists()) {
							newName = Tools.getDayTime() + "1." + ext; // �޸��ϴ��ļ���
							fileUrl = currentPath + newName;
							pathToSave = new File(currentDirPath, newName);
						}
						uplFile.write(pathToSave);
						if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
							this.pathFileName = currentDirPath + newName;
							//request .setAttribute("pathFileName", pathFileName);
						} else {
							this.pathFileName = currentDirPath + "\\" + newName;
							//request.setAttribute("pathFileName", pathFileName);
						}
						this.fileSzie = pathToSave.length();
						if (request.getParameter("Type").toLowerCase().equals("image")) {
							fileUrl = fileUrl.substring(0, fileUrl.lastIndexOf("/")) + "/m"
									+ fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
						}
						// datebase begin
						Upfilev5 upfile = new Upfilev5();
						upfile.setUpname(this.getFileName().substring(0, this.getFileName().lastIndexOf(".")));
						upfile.setFiletype(request.getParameter("Type").toLowerCase());
						upfile.setUpadder(baseDir + Tools.getDays() + "/");
						upfile.setUpdatetime(Tools.getDayTimes());
						upfile.setUpfilename(this.newFileName);
						upfile.setFilesize(String.valueOf(this.fileSzie));
						upfile.setUpfrom(upfrom);
						upfile.setNote(note);
						upfile.setAuthor(author);
						
						 * WebApplicationContext ctx =
						 * WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
						 * HibernateTemplate hibernateTemplate = (HibernateTemplate) ctx.getBean("hibernateTemplate");
						 * hibernateTemplate.save(upfile);
						 
						IGenericService genericService = (IGenericService) SpringUtil.getBean("genericService");
						if (debug) {
							System.out.println("upfile" + upfile.getAuthor());
						}
						genericService.save(upfile);
						
						if (abelong != null && !abelong.equals("") && abelong.equals("0000")) {
							Attach attach = new Attach();
							attach.setUpid(upfile.getUpid());
							attach.setAbelong(abelong);
							attach.setOrdernumb(new Long(0));
							genericService.save(attach);
							
						}
						// datebase end
						upid = String.valueOf(upfile.getUpid());
					} else {
						retVal = "202";
						errorMessage = "";
						pathFileName = "";
						if (debug) {
							System.out.println("Invalid file type: " + ext);
						}
					}
				}
			} catch (Exception ex) {
				if (debug) {
					System.out.println("fck->" + ex.getMessage());
					ex.printStackTrace();
				}

				retVal = "203";
			}
		} else {
			retVal = "1";
			errorMessage = "This file uploader is disabled. Please check the WEB-INF/web.xml file";
		}
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.OnUploadCompleted(" + retVal + ",'" + fileUrl + "','" + newName + "','"
				+ errorMessage + "','" + upid + "');");
		out.println("</script>");
		out.flush();
		out.close();
		if (debug) {
			System.out.println("--- END DOPOST ---");
		}
	}

	
	 * This method was fixed after Kris Barnhoorn (kurioskronic) submitted SF bug #991489
	 

	private static String getNameWithoutExtension(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	
	 * This method was fixed after Kris Barnhoorn (kurioskronic) submitted SF bug #991489
	 
	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	*//**
	 * Helper function to convert the configuration string to an ArrayList.
	 *//*

	private ArrayList stringToArrayList(String str) {
		if (debug) {
			System.out.println(str);
		}
		String[] strArr = str.split("\\|");
		ArrayList tmp = new ArrayList();
		if (str.length() > 0) {
			for (int i = 0; i < strArr.length; ++i) {
				if (debug)
					System.out.println(i + " - " + strArr[i]);
				tmp.add(strArr[i].toLowerCase());
			}
		}
		return tmp;
	}

	*//**
	 * Helper function to verify if a file extension is allowed or not allowed.
	 *//*

	private boolean extIsAllowed(String fileType, String ext) {

		ext = ext.toLowerCase();

		ArrayList allowList = (ArrayList) allowedExtensions.get(fileType);
		ArrayList denyList = (ArrayList) deniedExtensions.get(fileType);

		if (allowList.size() == 0)
			if (denyList.contains(ext))
				return false;
			else
				return true;

		if (denyList.size() == 0)
			if (allowList.contains(ext))
				return true;
			else
				return false;

		return false;
	}

	public long getFileSzie() {
		return fileSzie;
	}

}
*/