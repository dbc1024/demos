package com.ectrip.upload.service;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;

import com.ectrip.base.util.Tools;
import com.ectrip.upload.dao.idao.IUpfileDao;
import com.ectrip.upload.model.Upfile;
import com.ectrip.upload.service.iservice.IUpfileService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;


public class UpfileService implements IUpfileService {

	IUpfileDao upfileDao;

	public void setUpfileDao(IUpfileDao upfileDao) {
		this.upfileDao = upfileDao;
	}

	private static final int BUFFER_SIZE = 16 * 1024;
	private static final String WATERMARKPATH = "/upfiles/waterMark";
	private static final double m = 640.0;
	private static final double m480 = 480.0;
	private static final double m320 = 320.0;
	private static final double s = 120.0;

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/*public void saveSingleFile(File file, String fileName, Upfile upfile, int i,String waterMark) {
		// upadder begin
		String todoy = Tools.getDays();
		if (upfile.getUpadder() == null || upfile.getUpadder().equals("")) {
			upfile.setUpadder("/upfiles/" + todoy + "/");
		} else {
			upfile.setUpadder(upfile.getUpadder() + "/" + todoy + "/");
		}
		if(waterMark==null || "".equals(waterMark)){
			waterMark="0";
		}
		upfile.setUpadder(Tools.replaceAll(upfile.getUpadder(), "//", "/"));
		Tools.createDirs(upfile.getUpadder());
		// upadder end
		// upload begin
		upfile.setUpfilename(Tools.getDayTime() + i + getExtention(fileName));
		File f = new File(Tools.getRealPath() + upfile.getUpadder() + upfile.getUpfilename());
		copy(file, f);
		// upload end
		// image begin
		String fType = upfile.getFiletype();
		// image/bmp,image/png,image/gif,image/jpeg,image/jpg,image/pjpeg
		if (fType.equals("image/bmp") || fType.equals("image/png") || fType.equals("image/gif") || fType.equals("image/jpeg") || fType.equals("image/jpg")
				|| fType.equals("image/pjpeg")) {
			// ͼƬ����
			this.image(m, Tools.getRealPath() + upfile.getUpadder(), upfile.getUpfilename(), "m");
			this.image(m480, Tools.getRealPath() + upfile.getUpadder(), upfile.getUpfilename(), "m480");
			this.image(m320, Tools.getRealPath() + upfile.getUpadder(), upfile.getUpfilename(), "m320");
			this.image(s, Tools.getRealPath() + upfile.getUpadder(), upfile.getUpfilename(), "s");
			// ͼƬ���ˮӡ
			String mImgPath = Tools.getRealPath() + upfile.getUpadder() + upfile.getUpfilename();
			this.imgMark(mImgPath, Tools.getRealPath() + WATERMARKPATH+waterMark+".png", mImgPath);
			mImgPath = Tools.getRealPath() + upfile.getUpadder() + "m" + upfile.getUpfilename();
			this.imgMark(mImgPath, Tools.getRealPath() + WATERMARKPATH+waterMark+".png", mImgPath);
		}
		// image end
		// datebase begin
		upfile.setUpdatetime(Tools.getDayTimes());
		Long upid=upfileDao.getMaxPk("upid", "Upfile");
		upfile.setUpid(upid+1);
		upfileDao.save(upfile);
		// datebase end
	}*/

	public void saveMultipeFile(File[] uploads, String[] uploadFileNames, String[] uploadContentTypes, String[] upid, String[] upname,String[] upfrom, String[] note, String[] author, String[] utype, String[] upfilename,
			String[] upadder, String abelong,String waterMark) {
		Upfile upfile = null;
		File file = null;
	
		for (int i = 0; i < uploads.length; i++) {
			upfile = new Upfile();
			file = uploads[i];
			upfile.setUpfrom(upfrom[i]);
			upfile.setNote(note[i]);
			upfile.setAuthor(author[i]);
		
			upfile.setUpname(upname[i]);
			upfile.setFilesize(String.valueOf(file.length()));
			upfile.setFiletype(uploadContentTypes[i]);
			this.saveSingleFile(file, uploadFileNames[i], upfile, i,waterMark);
			upid[i] = String.valueOf(upfile.getUpid());
			upname[i] = upfile.getUpname();
			upfilename[i] = upfile.getUpfilename();
			upadder[i] = upfile.getUpadder();
			// attach
			
		}
	}

	public void image(double length, String upadder, String upfilename, String frontname) {
		double proportion = 0.0;
		try {
			File file = new File(upadder + upfilename); // �����ļ�
			if (file.exists() == false) {
				return;
			} else {
				Image src = javax.imageio.ImageIO.read(file); // ����Image����
				int wideth = src.getWidth(null); // �õ�Դͼ���
				int height = src.getHeight(null); // �õ�Դͼ�߶�
				if (wideth >= height) {
					if (wideth > length) {
						proportion = length / wideth;
						wideth = (int) length;
						height = (int) (height * proportion);
					}
				} else {
					if (height > length) {
						proportion = length / height;
						wideth = (int) (wideth * proportion);
						height = (int) length;
					}
				}
				BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(src, 0, 0, wideth, height, null); // ������С���ͼ
				FileOutputStream out = new FileOutputStream(upadder + frontname + upfilename); // ������ļ���
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);// ��JPEG����
				out.close();
				out = null;
			}
		} catch (Exception e) {
			System.out.println("image->" + e.getMessage());
			e.printStackTrace();
		}
	}

	public void imgMark(String strOriginalFileName, String strWaterMarkFileName, String strNewFileName) {
		try {
			// Դ�ļ�
			File fileOriginal = new File(strOriginalFileName);
			if (fileOriginal.exists() == false) {
				return;
			}
			Image imageOriginal = ImageIO.read(fileOriginal);
			int widthOriginal = imageOriginal.getWidth(null);
			int heightOriginal = imageOriginal.getHeight(null);
			BufferedImage bufImage = new BufferedImage(widthOriginal, heightOriginal, BufferedImage.TYPE_INT_RGB);
			Graphics g = bufImage.createGraphics();
			g.drawImage(imageOriginal, 0, 0, widthOriginal, heightOriginal, null);
			// ˮӡ�ļ�
			File fileWaterMark = new File(strWaterMarkFileName);
			if (fileWaterMark.exists() == false) {
				return;
			}
			Image imageWaterMark = ImageIO.read(fileWaterMark);
			int widthWaterMark = imageWaterMark.getWidth(null);
			int heightWaterMark = imageWaterMark.getHeight(null);
			// ˮӡ�ļ���Դ�ļ������½�
			g.drawImage(imageWaterMark, widthOriginal - widthWaterMark, heightOriginal - heightWaterMark, widthWaterMark, heightWaterMark, null);
			g.dispose();
			FileOutputStream fos = new FileOutputStream(strNewFileName);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
			encoder.encode(bufImage);
			fos.flush();
			fos.close();
			fos = null;
		} catch (Exception e) {
			System.out.println("imgMark->" + e.getMessage());
			e.printStackTrace();
		}
	}



	public List findUpfiles(String abelong, String avalue) {
		return upfileDao.findUpfiles(abelong, avalue);
	}

	@Override
	public void saveSingleFile(File file, String fileName, Upfile upfile, int i, String waterMark) {
		// TODO Auto-generated method stub
		
	}

}
