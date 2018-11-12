/*package com.ectrip.checkticket.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ectrip.base.action.BaseAction;

*//**
 * ����բ����Ʊ��ʾ��
 * @author lijin
 *
 *//*
public class CheckPicShow extends BaseAction {
	*//**
	 * 
	 *//*
	private static final long serialVersionUID = 4442989919307325659L;

	public String viewImages() {
		HttpServletRequest request = this.getRequest();
		// picΪ��ȡ��ͼƬ�Ĵ洢·�������ݿ��д洢���ֶ�ֵ��
		String pic = request.getParameter("pic");
		HttpServletResponse response = this.getResponse();
		ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			// ALARM_FILE_PATHΪ����ͼƬ���·����imagePathΪͼƬ����ʵ·��
			String imagePath = pic;
			File file =new File(imagePath);
			if  ( !file .exists() )
			{
			   return null;	
			}
				
			ips = new FileInputStream(file );
			response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			// ��ȡ�ļ���
			int i = 0;
			byte[] buffer = new byte[4096];
			while ((i = ips.read(buffer)) != -1) {
				// д�ļ���
				out.write(buffer, 0, i);
			}
			out.flush();
			ips.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ips != null) {
				try {
					ips.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	//���淽��û��
	public String viewImages(ServletOutputStream out,String pic) {
		HttpServletRequest request = this.getRequest();
		// picΪ��ȡ��ͼƬ�Ĵ洢·�������ݿ��д洢���ֶ�ֵ��
		//String pic = request.getParameter("pic");
		HttpServletResponse response = this.getResponse();
		//ServletOutputStream out = null;
		FileInputStream ips = null;
		try {
			// ALARM_FILE_PATHΪ����ͼƬ���·����imagePathΪͼƬ����ʵ·��
			String imagePath = pic;
			File file =new File(imagePath);
			if  ( !file .exists() )
			{
			   return null;	
			}
				
			ips = new FileInputStream(file );
			//response.setContentType("multipart/form-data");
			out = response.getOutputStream();
			// ��ȡ�ļ���
			int i = 0;
			byte[] buffer = new byte[4096];
			while ((i = ips.read(buffer)) != -1) {
				// д�ļ���
				out.write(buffer, 0, i);
			}
			out.flush();
			ips.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (ips != null) {
				try {
					ips.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
*/