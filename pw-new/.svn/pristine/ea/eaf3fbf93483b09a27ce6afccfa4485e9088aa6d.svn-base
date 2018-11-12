package com.ectrip.base.util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * 生成动态的码证码
 * 生成后在 SESSION 中进行存放 KEY 为 randomstr
 * 
 * @author lijin
 *
 */
public class CreateImage extends HttpServlet {

	static final private String CONTENT_TYPE = "text/html; charset=utf-8";

	final String input_back_color_error = "input rgb backcolor is error";

	final String input_fore_color_error = "input rgb forecolor is error";

	private Picture pic = new Picture();
	private String realpath = "";

	//Initialize global variables

	@Override
	public void init() throws ServletException {
		realpath = this.getServletContext().getRealPath("/images/user/codebakgroup.jpg"); //取JPG的绝对路径

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Picture pic = new Picture();

		String SessionId = request.getParameter("sessionId");
		//System.out.println("SessionId="+SessionId);
		HttpSession Sess = null;
		if (SessionId == null ) {
			HttpSessionContext SessCon = request.getSession(true).getSessionContext();
			Sess = SessCon.getSession(SessionId);
			
			if (Sess == null  )
			{
				Sess = request.getSession(true);	
			}
			//System.out.println("Main SessionId="+SessionId);
		} else {
			//Sess = request.getSession(true);
		}
		//System.out.println("New SessionId="+Sess.getId());
		BufferedImage image = pic.getBufferedImage();
		Graphics g = image.getGraphics();
		pic.getbackground(g, 0, 0);
		StringBuffer keyBuf = new StringBuffer();
		int offset = 0; // 偏移量
		int num = pic.getcharacterNumber();
		
		for (int i = 0; i < num; i++) {
			int ri = (int) (Math.random() * 10);
			keyBuf.append(ri);
			pic.getcharacter(g, offset, ri);
			offset += 60 / num;
		}
		pic.getInterrupt(g);
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		out.close();
		// 保存附加码
		// Debug.println(keyBuf.toString());
		Sess.setAttribute("randomstr", keyBuf.toString());
	}
	
	public void picmy() throws ImageFormatException, IOException
	{
		Picture pic = new Picture();
		
		BufferedImage image = pic.getBufferedImage();
		Graphics g = image.getGraphics();
		pic.getbackground(g, 0, 0);
		StringBuffer keyBuf = new StringBuffer();
		int offset = 0; // 偏移量
		int num = pic.getcharacterNumber();
		
		for (int i = 0; i < num; i++) {
		    String  ri = UUID.randomUUID().toString().replace("-", "").substring(2, 3) ;
			keyBuf.append(ri);
			pic.getcharacter(g, offset,ri.toCharArray()[0]);
			offset += 60 / num;
		}
		pic.getInterrupt(g);
		File file = new File("a.jpg");
		
		//ServletOutputStream out = response.getOutputStream();
		 FileOutputStream out=new FileOutputStream(file);
		
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);
		
	}
	 public static void main(String[] args) {
		 try{
		 CreateImage c = new CreateImage();
		 c.picmy();
		 } catch(Exception e)
		 {
			 System.out.println(e);
		 }
		}

}
