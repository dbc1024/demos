package com.ectrip.base.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.swetake.util.Qrcode;

/**
 * 生成二维码图片
 * @author hezhihong
 */
public class QrCodesUtil {
	  /**
	   * 产生随机字符串
	   * */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
  private final static String DES = "DES";

	/**
	 * Describe:生成二维码图片
	 * 
	 * @author:hezhihong
	 * @param str
	 *            数据//例如123,124,456,21
	 * @param url
	 *            保存二维码图片的路径
	 * @param filename 文件名称
	 * @return boolean
	 * @throws Exception
	 *             return:boolean Date:2014-4-16
	 */
	public static boolean createCode(String str, String url,String filename,String oriddata) {
		QrCode qr = new QrCode();
		// 生成图片并编成 BASE64 字串，用于保存，数据库保成字串
		if (str.length() <= 150) {
			try {
				if (str != null || !"".equals(str)) {
					// 编码
					String ls_str = QrCode.EncodeCodeB64String(str);
					// 二维码图片字串，返原成图片用于发送
					BufferedImage image = qr.DecoderCodeB64Image(ls_str);
					// 二维码图片字串，返原成字符串
					// String ls_decstr = DecoderCodeB64String(ls_str);
					// System.out.println(ls_decstr);
					if (url != null || !"".equals(url)) {
						String FilePath = url+"/"+oriddata+"/"+filename+".png";
						System.out.println(FilePath);
						File f = new File(FilePath);
						ImageIO.write(image, "png", f);
						return true;
					}
				}
				return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @Title: createImage  
	 * @Description: 在线生成二维码
	 * @author zyj
	 * @param @param content
	 * @param @return
	 * @param @throws UnsupportedEncodingException    
	 * @return byte[]   
	 * @throws
	 */
	public static byte[] createImage(String content,String imgUrl, String classPath) throws UnsupportedEncodingException{
		
      Qrcode qrcode = new Qrcode();    
      qrcode.setQrcodeErrorCorrect('M');
      qrcode.setQrcodeEncodeMode('B');    
      qrcode.setQrcodeVersion(20);
      //content为需要生成的字符串，可通过请求传入参数  
      byte[] bstr = content.getBytes("UTF-8");  
      int imgSize = 67+12*(20-1);
      BufferedImage bi = new BufferedImage(imgSize, imgSize,BufferedImage.TYPE_INT_RGB);
      Graphics2D g = bi.createGraphics();    
      g.setBackground(Color.WHITE);   //背景颜色    
      g.clearRect(0, 0, imgSize, imgSize);
      g.setColor(Color.BLACK);    //条码颜色    
      System.out.println(bstr.length);
   // 设置偏移量 不设置可能导致解析出错 
      int pixoff = 2;
      if (bstr.length > 0 && bstr.length < 500) {
          boolean[][] b = qrcode.calQrcode(bstr);    
          for (int i = 0; i < b.length; i++) {    
              for (int j = 0; j < b.length; j++) {    
                  if (b[j][i]) {    
                      g.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                  }
              }    
          }    
      }

      ByteArrayOutputStream os = new ByteArrayOutputStream();  
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);  
      try {
			if(imgUrl!=null&&!imgUrl.equals("")){
				Image img = ImageIO.read(new File(classPath+imgUrl));
				//实例化一个Image对象。
				g.drawImage(img, 97, 97, 100, 100, null);
			}


			encoder.encode(bi);
          g.dispose();  
      } catch (Exception e) {  
          // TODO Auto-generated catch block  
          e.printStackTrace();  
      }  
      return os.toByteArray();  
  }  
}
