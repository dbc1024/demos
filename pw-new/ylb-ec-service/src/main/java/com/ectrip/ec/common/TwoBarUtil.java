package com.ectrip.ec.common;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.DecimalUtil;
import com.ectrip.base.util.DesUtil;
import com.ectrip.base.util.QrCodesUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.feign.service.TicketService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.order.service.iservice.IOrderService;

public class TwoBarUtil {
	
	@Autowired
	private IGenericService genericService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private SysparService sysparService;
	
	private String TowCodePath ;

	
	public  String makeTwoBarcode (String orid,MOrder morder,String note,String newUrl) throws Exception {
		//判断是否输入域名
		if(newUrl == null || newUrl.length()<1){
			newUrl = WebContant.GetKeyValue("DOMAIN");
		}
		
		morder = (MOrder) this.genericService.get(MOrder.class, orid);
		// 从ECTRIP 中读入名码
		String key = WebContant.GetKeyValue("qrcodemiyue").equals("") ? "OTOECTRIP"
				: WebContant.GetKeyValue("qrcodemiyue");
		Map map = createTwoBarCode(orid, key,newUrl);// 生成二维码并返回地址
		
		String qrcodeimgpath = (String) map.get("url");
		
		morder.setNoteg(qrcodeimgpath);
		
		 //把URL保存到 M_ORDER中去。
		genericService.update(morder);
		
		List torders = orderService.getTOrderList(orid);
		if (torders != null && torders.size() > 0) {
			TOrder torder = new TOrder();
			BeanUtils.populate(torder, (Map) torders.get(0));
			//李进，2014-09-29 日加
			if (note == null || note.equals("") || note.equals("null") || note.equals("0")) {
				sysparService.sendMessageNew(torder.getOrph(), "0002", orid);
			} else {
				sysparService.sendMessageNew(torder.getOrph(), "0007", orid);
			}
		}
		return "1";

	}
	
	/**
	  * 生成二维码 Describe:
	  * 
	  * @author:hezhihong
	  * @param orid
	  *            订单ID CYT_201404280000001_1
	  * @param twobarcodekey
	  *            密钥
	  * @return flag:true 生成二维码成功 false 生成失败 url:发个顾客的路径 fliename:图片名称 return:Map
	  *         Date:2014-4-28
	  */
	  Map createTwoBarCode(String orid, String twobarcodekey,String newUrl) {
		Map map = new HashMap();
		 	
		 	//判断是否有传url
		if(newUrl == null || newUrl.length()<1){
			newUrl = WebContant.GetKeyValue("DOMAIN");
		}
		
		
		String domain = newUrl;
		String oriddata = orid.substring(0, 8);
		String url = null;// 发个顾客的路径
		String filename = null;// 图片存在地址
		boolean flag = false;// 生成图片是否
		
		
		TowCodePath = getTowCodePath();
		
		if (TowCodePath != null || !"".equals(TowCodePath) && domain != null || !"".equals(domain)) {
			try {
				// 生成文件夹
				File fl = new File(TowCodePath + "/" + oriddata);
				if (!fl.isFile()) {
					fl.mkdir();
					if (!fl.isFile()) {
						fl.mkdirs();
					}
				}
				// 生成随机数字（进制）
				int max = 35;
				int min = 25;
				Random random = new Random();
				int bases = random.nextInt(max) % (max - min + 1) + min;
				// 生成36进制
				BigInteger orids  = new BigInteger(orid);
				BigInteger base   = new BigInteger(bases + "");
				String dm         = DecimalUtil.toAnyConversion(orids, base);
				String dmlegth    = DecimalUtil.getzm(dm.length());
				// 异或算法
				String yhstr      = DecimalUtil.getmdecimal(dm);
				// 转换36进制
				BigInteger yhstr1 = new BigInteger(yhstr);
				BigInteger ba     = new BigInteger("36");
				String yhstr2     = DecimalUtil.toAnyConversion(yhstr1, ba);
		
				// 得到图片名称
				BigInteger nameorid = new BigInteger(orid);
				BigInteger basename = new BigInteger("36");
				String dmname       = DecimalUtil.toAnyConversion(nameorid, basename);
		
				// 生成二维码图片
				//ECT_32756aef5b0dea9b9046c04e4f3b8dea513ba5fe64e515ed,0,acb1f342ee7789a7d950e4a86c56b47a
		
				flag     = QrCodesUtil.createCode("ECT_" + DesUtil.encrypt(orid, twobarcodekey), TowCodePath, dmname, oriddata);
				url      = domain + "/code.jsp?code=" + DecimalUtil.getzm(bases) + dmlegth + dm + yhstr2;
				filename = TowCodePath + "/" + oriddata + "/" + dmname + ".png";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("flag", flag);
		map.put("url", url);
		map.put("fliename", filename);
		
		return map;
	 }
	  
	  
	  /**
	   * 二维码保存存路径，
	   * 李进修改，为了保持和解析一至
	   * 所有单独整了一个方法；
	   * @return
	   */
	 public String getTowCodePath() {
	 		String TowCodePathTemp = com.ectrip.base.util.WebContant.GetKeyValue("TwoCodePath");
	 		//二维码地址修改为存放图片服务器
	 		/*if (TowCodePathTemp == null || TowCodePathTemp.equals("")) {
	 			TowCodePathTemp = WebContant.REALPATH + "/barcode";
	 		}
	 		System.out.println("二维码图片保存缺省："+WebContant.REALPATH+"可以在 ectrip.xml 中指定： <TwoCodePath>XXX</TwoCodePath>");
	 		System.out.println("二维码图片保存路径："+TowCodePathTemp);*/
	 		return TowCodePathTemp;
	 }
}
