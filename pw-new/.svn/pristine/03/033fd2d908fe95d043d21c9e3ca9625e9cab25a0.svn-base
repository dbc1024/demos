package com.ectrip.base.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;

public class QrCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Date());
		try {
			//companymain("二维码测试","13923834675","dd@ectrip.com");
			/*companymain("李进","13923834675","lj@ectrip.com");
			companymain("张海平","1390290027","zhp@ectrip.com");
			companymain("李云峰","15986696972","lyf@ectrip.com");
			companymain("李进标","15986696987","ljb@ectrip.com");
			companymain("甘小红","13570830508","gxh@ectrip.om");
			companymain("肖一兵","1370349819","xyb@ectrip.com");
			companymain("袁成军","13692102340","ycj@ectrip.com");
			companymain("杨光","13670072994","yg@ectrip.com");
			companymain("刘建宁","13602603063","ljl@ectrip.com");
			companymain("刘德和","13682505049","ldh@ectrip.com");
			companymain("孙立广","15986696971","slg@ectrip.com");
			companymain("童明宇","13320061001 13798424240","tmy@ectrip.com");
			companymain("陶国栋","18970617171","tgd@ectrip.com");*/
			
			
			//调用例子：
			//实例发类
			QrCode qr = new QrCode();
			//生成图片并编成 BASE64 字串，用于保存，数据库保成字串
			String ls_str  = qr.EncodeCodeB64String("a43433433434bcd");
			System.out.println(ls_str );
			
			//二维码图片字串，返原成图片用于发送
			
			BufferedImage image = qr.DecoderCodeB64Image(ls_str);
			//二维码图片字串，返原成字符串
			
			String ls_decstr  = qr.DecoderCodeB64String(ls_str);
			
			
			System.out.println(ls_decstr );
			String FilePath = "e:\\tmp\\名片\\81.png";
			File f = new File(FilePath);
			
			ImageIO.write(image, "png", f);
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end main

	
	public static void companymain(String ls_name,String tel,String email)
	{
		// TODO Auto-generated method stub
		System.out.println(new Date());
		try {
			QrCode qr = new QrCode();
			String ls_comstr  = "公司：深圳鼎游";
			String ls_comstr1 = "地址：南山区中山园路1001号TCL国际E城D1栋9层";
			String ls_comstr2 = "tel：075526419190";
			String ls_comstr3 = "web:www.ectrip.com";
			
			String ls_comstr4 = "姓名：" + ls_name;

			String ls_comstr5 = "手机：" + tel;
			String ls_comstr6 = "EMAIL："+email;
			String ls_comstr7 = "服务热线：4006797580";
			String ls_str = ls_comstr + ";" + ls_comstr2 + ";" + ls_comstr4 + ";" + ls_comstr5 + ";" + ls_comstr6;
			BufferedImage image  = qr.EncodeCodeImage(ls_str, ls_name);
			
			String FilePath = "e:\\tmp\\名片\\1.png";
			File f = new File(FilePath);

		    ImageIO.write(image, "png", f);
			String ls_str1 = qr.DecoderCode( "e:\\tmp\\名片\\1.png");
			   
			   System.out.println("md5=" + ls_str1);
			   EctripBase64 b64 = new EctripBase64();
			//   ls_str2 =  b64.decode(ls_str1)
			   System.out.println("md5=" + ls_str1); 
			   /*
			  EctripMd5 md5 = new EctripMd5("ectrip");
			  md5.calc() ; EctripMd5 md51 = new
			  EctripMd5("ectrip".getBytes()); 
			  md51.calc(); 
			  System.out.println("md5=" + md5.toString());
			  System.out.println("md51=" + md51.toString());
			  
			  System.out.println("md51=" + EctripMd5.compareToMd5("ectrip" , md5.toString()));
			  EctripBase64 b64 = new EctripBase64();
			  b64.decode(encoded)
			  
			 FileInputStream fin = new FileInputStream(f); 
		
			//InputStream in = new ByteArrayInputStream(fin);
			 image = javax.imageio.ImageIO.read(fin);
			

			 String FilePath1 = "e:\\tmp\\名片\\21.png";
			 File ff = new File(FilePath1);
			 ImageIO.write(image, "png", ff);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end main

	/**
	 * 采用图片解码
	 * 
	 * @param image
	 * @return
	 */
	public String DecoderCode(String ls_pic) {

		QRCodeDecoder decoder = new QRCodeDecoder();

		File imageFile = new File(ls_pic);
		String decodedData = "";

		BufferedImage image = null;

		try {

			decodedData = new String(decoder.decode(new J2SEImage(image)), "GBK");

			System.out.println(decodedData);
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return decodedData;
	}

	public BufferedImage DecoderCodeB64Image(String srcstr) throws Exception {

		QRCodeDecoder decoder = new QRCodeDecoder();
		String decodedData = "";
		EctripBase64 b64 = new EctripBase64();
		BufferedImage image = null;
		byte[] byteArray = null;
		try {
			byteArray = b64.decode(srcstr);
			InputStream in = new ByteArrayInputStream(byteArray);
			image = javax.imageio.ImageIO.read(in);

		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return image;
	}

	/**
	 * 字符串解码
	 * 
	 * @param srcstr
	 * @return
	 * @throws Exception
	 */
	public byte[] DecoderCodeB64ImageByte(String srcstr) throws Exception {

		QRCodeDecoder decoder = new QRCodeDecoder();
		String decodedData = "";
		EctripBase64 b64 = new EctripBase64();
		BufferedImage image = null;
		byte[] byteArray = null;
		try {
			byteArray = b64.decode(srcstr);
			InputStream in = new ByteArrayInputStream(byteArray);
			image = javax.imageio.ImageIO.read(in);

		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return byteArray;
	}

	/**
	 * 字符串解码
	 * 
	 * @param srcstr
	 * @return
	 * @throws Exception
	 */
	public String DecoderCodeB64String(String srcstr) throws Exception {

		QRCodeDecoder decoder = new QRCodeDecoder();
		String decodedData = "";
		EctripBase64 b64 = new EctripBase64();
		try {
			// Jmd5Run jmd5 = new Jmd5Run(srcstr);
			byte[] byteArray = b64.decode(srcstr);
			InputStream in = new ByteArrayInputStream(byteArray);
			BufferedImage image = javax.imageio.ImageIO.read(in);
	    	decodedData = new String(decoder.decode(new J2SEImage(image)), "GBK");
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return decodedData;
	}

	
	/**
	 * 编码成字符串
	 * 
	 * @param srcstr
	 * @return
	 * @throws Exception
	 */
	public static String EncodeCodeB64String(String srcstr)
		    throws Exception
		   {
		    if (srcstr.length() > 150)
		    {
		      srcstr = srcstr.substring(0, 149) + ",1";
		    }else{
		    	srcstr = srcstr + ",0";
		    }
		    EctripMd5 md5 = new EctripMd5(srcstr);
		    md5.calc();
		    srcstr = srcstr + "," + md5.toString();

		    Qrcode qrcode = new Qrcode();
		    // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
		    qrcode.setQrcodeErrorCorrect('M');  
		    qrcode.setQrcodeEncodeMode('B');  
		    // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
		    qrcode.setQrcodeVersion(10);  
		    
		    
		    // 获得内容的字节数组，设置编码格式  
		    byte[] contentBytes = srcstr.getBytes("GBK");  
		    int imgSize = 67 + 12 * (10 - 1);  
		    BufferedImage bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
		   
		    
		    Graphics2D gs = bufImg.createGraphics();  
		    // 设置背景颜色  
		    gs.setBackground(Color.WHITE);  
		    gs.clearRect(0, 0, imgSize, imgSize);  
		    // 设定图像颜色> BLACK  
		    gs.setColor(Color.BLACK);  

		    
		    // 设置偏移量，不设置可能导致解析出错  
		    int pixoff = 2;  
		    // 输出内容> 二维码
		    if (contentBytes.length > 0 && contentBytes.length < 216) {  
		        boolean[][] codeOut = qrcode.calQrcode(contentBytes);  
		        for (int i = 0; i < codeOut.length; i++) {  
		            for (int j = 0; j < codeOut.length; j++) {  
		                if (codeOut[j][i]) {  
		                    gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
		                }  
		            }  
		        }  
		    } 
		    gs.dispose();  
		    bufImg.flush();  

		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    ImageIO.write(bufImg, "png", baos);
		    byte[] bytesOut = baos.toByteArray();

		    String ls_values = EctripBase64.encode(bytesOut);
		    return ls_values;
		  }	
	/**
	 * 编码成字符串
	 * 
	 * @param srcstr
	 * @return
	 * @throws Exception
	 */
	public String EncodeCodeB64StringOld(String srcstr) throws Exception

	{

		// System.out.print("生成二维护码的源串"+srcstr);

		if (srcstr.length() > 100) {
			// throw new Exception("进行二维码编码的字符串不能超过100个字符");
			srcstr = srcstr.substring(0, 149) + ",1";
		} else {
			srcstr = srcstr + ",0";
		}

		EctripMd5 md5 = new EctripMd5(srcstr);
		md5.calc();
		srcstr = srcstr + "," + md5.toString();

		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		qrcode.setQrcodeVersion(7);

		String testString = srcstr;

		byte[] d = testString.getBytes("GBK");

		BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);

		// createGraphics
		Graphics2D g = bi.createGraphics();

		// set background
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 139, 139);

		g.setColor(Color.BLACK);

		if (d.length > 0 && d.length < 123) {
			boolean[][] b = qrcode.calQrcode(d);

			for (int i = 0; i < b.length; i++) {

				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}
				}

			}
		}

		g.dispose();
		bi.flush();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", baos);
		byte[] bytesOut = baos.toByteArray();

    	Base64 b64 = new Base64();
		String ls_values = b64.Base64Encode(bytesOut);

		return ls_values;
	}

	/**
	 * 编码成图片
	 * 
	 * @param srcstr
	 * @return
	 * @throws Exception
	 */
	public BufferedImage EncodeCodeImage(String srcstr, String name) throws Exception

	{

		Qrcode qrcode = new Qrcode();
		qrcode.setQrcodeErrorCorrect('M');
		qrcode.setQrcodeEncodeMode('B');
		
		qrcode.setQrcodeVersion(7);

		/*
		 * EctripMd5 md5 = new EctripMd5(c); md5.calc(); srcstr = srcstr + "," + md5.toString();
		 */
		String testString = srcstr;
		System.out.println("srcstr=" + srcstr);
		byte[] d = testString.getBytes("GBK");

		BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);

		// createGraphics
		Graphics2D g = bi.createGraphics();

		// set background
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 139, 139);

		g.setColor(Color.BLACK);

		if (d.length > 0 && d.length < 123) {
			boolean[][] b = qrcode.calQrcode(d);

			for (int i = 0; i < b.length; i++) {

				for (int j = 0; j < b.length; j++) {
					if (b[j][i]) {
						g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
					}
				}

			}
		}

		g.dispose();
		bi.flush();
        /*
		String FilePath = "e:\\tmp\\名片\\" + name + ".png";
		File f = new File(FilePath);

		ImageIO.write(bi, "png", f);
        */
		return bi;
	}
	class J2SEImage implements QRCodeImage {  
	    BufferedImage image;  
	      
	    public J2SEImage(BufferedImage image) {  
	        this.image = image;  
	    }  
	      
	    public int getWidth() {  
	        return image.getWidth();  
	    }  
	      
	    public int getHeight() {  
	        return image.getHeight();  
	    }  
	      
	    public int getPixel(int x, int y) {  
	        return image.getRGB(x, y);  
	    }  
	      
	}
}