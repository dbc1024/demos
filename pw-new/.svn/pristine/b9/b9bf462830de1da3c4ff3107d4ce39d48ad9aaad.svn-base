package com.ectrip.ticket.sale.service.card.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.balance.Orderpay;
import com.ectrip.sys.model.balance.Useryfktype;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.card.Bindingprice;
import com.ectrip.ticket.model.card.Icconsumerecord;
import com.ectrip.ticket.model.card.Icitem;
import com.ectrip.ticket.model.card.Icrechargerecord;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.sale.service.card.core.CoreUtil;
import com.ectrip.ticket.sale.service.card.core.DesUtil;
import com.ectrip.ticket.sale.service.card.core.Response;
import com.ectrip.ticket.sale.service.card.dao.idao.IOneCardDao;
import com.ectrip.ticket.sale.service.card.model.ConsumeRequest;
import com.ectrip.ticket.sale.service.card.model.ConsumeResponse;
import com.ectrip.ticket.sale.service.card.model.Discountprice;
import com.ectrip.ticket.sale.service.card.model.GetCardStatusRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardStatusResponse;
import com.ectrip.ticket.sale.service.card.model.GetCardsRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardsResponse;
import com.ectrip.ticket.sale.service.card.model.GetCustomsResponse;
import com.ectrip.ticket.sale.service.card.model.GetICItemsRequest;
import com.ectrip.ticket.sale.service.card.model.GetICItemsResponse;
import com.ectrip.ticket.sale.service.card.model.GetOrderListRequest;
import com.ectrip.ticket.sale.service.card.model.GetOrderListResponse;
import com.ectrip.ticket.sale.service.card.model.GetSzticketprintnosRequest;
import com.ectrip.ticket.sale.service.card.model.GetSzticketprintnosResponse;
import com.ectrip.ticket.sale.service.card.model.PrintReceiptRequest;
import com.ectrip.ticket.sale.service.card.model.RefundItem;
import com.ectrip.ticket.sale.service.card.model.RefundRequest;
import com.ectrip.ticket.sale.service.card.model.RefundResponse;
import com.ectrip.ticket.sale.service.card.model.SaleRequest;
import com.ectrip.ticket.sale.service.card.model.Timekeeping;
import com.ectrip.ticket.sale.service.card.service.iservice.IOneCardService;

public class OneCardService extends GenericService implements IOneCardService{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");
	private SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String receiptSale = "com/ectrip/sale/service/card/receipt/sale.txt";
	private String receiptRecharge="com/ectrip/sale/service/card/receipt/recharge.txt";
	private String receiptReplace = "com/ectrip/sale/service/card/receipt/replace.txt";
	private String receiptRefund = "com/ectrip/sale/service/card/receipt/refund.txt";
	private String receiptCheckout = "com/ectrip/sale/service/card/receipt/checkout.txt";
	private String receiptConsume = "com/ectrip/sale/service/card/receipt/consume.txt";
	private String receiptHistoryConsumes = "com/ectrip/sale/service/card/receipt/consumeHistory.txt";//��ӡ��ʷ���Ѽ�¼
	private String receiptRechargeRefund="com/ectrip/sale/service/card/receipt/rechargeRefund.txt";
	private String receiptConsumeRefund = "com/ectrip/sale/service/card/receipt/consumeRefund.txt";
	private IOneCardDao oneCardDao;

	public IOneCardDao getOneCardDao() {
		return oneCardDao;
	}

	public void setOneCardDao(IOneCardDao oneCardDao) {
		this.oneCardDao = oneCardDao;
	}

	public GetCardsResponse getCards(GetCardsRequest request) {
		GetCardsResponse res = new GetCardsResponse();
		res.setCode("0000");
		res.setDescribe("�ɹ�");
		res.setCards(oneCardDao.getCards(request));
		return res;
	}
	/**
	 * *
	 * Describe:�򵥻�ȡ����(��ӦCustom��usid)
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#getSzticketprintnos(com.ectrip.sale.service.card.model.GetSzticketprintnosRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-3
	 */
	public GetSzticketprintnosResponse getSzticketprintnos(
			GetSzticketprintnosRequest request) {
		int count = Integer.parseInt(request.getCount());
		List<String> nums = new ArrayList<String>();
		try {
			for(int i = 0; i < count; i ++){
				nums.add(createSzticketprintno());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		GetSzticketprintnosResponse res = new GetSzticketprintnosResponse();
		res.setCode("0000");
		res.setDescribe("�ɹ�");
		res.setSzticketprintnos(nums);
		return res;
	}
	/**
	 * Describe:����δ���ܽ�����ÿ����Ŀ�ĵ������map���� realamont,checkouttime,unitprice,timesection
	 * @author liujianwen
	 * @param uncheckoutList
	 * @return
	 * return:double
	 * Date:2016-7-5
	 */
	private double getUncheckoutMoney(List<?> uncheckoutList){
		double amount = 0;
		try {
			if(uncheckoutList != null && uncheckoutList.size() > 0){
				long endTime = Calendar.getInstance().getTimeInMillis();//����ʱ��
				String endDate = ymdhms.format(new Date(endTime));
				endTime = endTime/1000;
				for (Object object : uncheckoutList) {
					Map map = (Map) object;
					Icconsumerecord record = (Icconsumerecord) map.get("self");
					double realamont = record.getConsumeamount();
					if("06".equals(record.getCztp())){//��ʱ����
						Timekeeping tk = JSON.parseObject(record.getTimekeeping(), Timekeeping.class);//��ʱ����
						Sysparv5 v5 = (Sysparv5) oneCardDao.get(Sysparv5.class, new Sysparv5Id("JSDW", tk.getUnit()));
						String dw =(v5==null?"":v5.getPmva());
						map.put("unitprice",tk.getUnitprice()+"Ԫ/"+(tk.getUnitscale()==1?"":tk.getUnitscale())+dw);
						int unit = 0;//��λʱ��
						if("00".equals(tk.getUnit())){//����
							unit = 60;//��Ϊ��λ
						}else if("01".equals(tk.getUnit())){//Сʱ
							unit = 60*60;//��Ϊ��λ
						}else if("02".equals(tk.getUnit())){//��
							unit = 24*60*60;//��Ϊ��λ
						}
						long jssj = ymdhms.parse(record.getConsumedate()).getTime()/1000;//��ʼ��ʱ��ʱ��
						long sjsj = endTime-jssj -tk.getDelayed()*60 ;//ʵ��ʱ��,������Ϊ��λ
						long jssx = tk.getUpperlimit() * unit;//��ʱ����
						sjsj = (sjsj<jssx?sjsj:jssx);
						int realunit= unit*tk.getUnitscale();//��λ��ʱ
						if(sjsj <= 0){//
							realamont = tk.getMinexpense();//��С���ѽ��
						}else{
							if(sjsj <= realunit){//���ʵ��ʱ��С�ڵ�λʱ��
								realamont = (tk.getUnitprice() <tk.getMinexpense()?tk.getMinexpense():tk.getUnitprice());
							}else{
								double m = 0;
								long count = (sjsj%realunit>0?(sjsj/realunit+1):(sjsj/realunit));//������ٸ���λ����ʱ��
								List<Discountprice> disprice = tk.getDiscountprices();
								long yfcount = 0;//�Ż�����
								if(disprice != null && disprice.size() > 0){
									Collections.sort(disprice);//����
									for(int j = 0; j < disprice.size(); j ++){
										Discountprice dp = disprice.get(j);
										int begin = dp.getStartTime()/tk.getUnitscale();//�������Ϊ��λ���ڵı���
										int end = dp.getEndTime()/tk.getUnitscale();
										long zq = end-begin;//�Ż�����
										if(count >=end){//�ܼƷ�������������������
											m+=(zq*dp.getDiscountprice());
											yfcount+=zq;
										}else if(begin < count){//��ʼ�Ż�����С���ܼƷ�����
											zq = count-begin;
											m+=(zq*dp.getDiscountprice());
											yfcount+=zq;
										}
									}
								}
								m+=((count-yfcount)*tk.getUnitprice());
								realamont = (m <tk.getMinexpense()?tk.getMinexpense():m);
							}
						}
						map.put("timesection", 
								record.getConsumedate().replaceAll("-|:|[ ]", "").substring(4, 12)
								+"-"+endDate.replaceAll("-|:|[ ]", "").substring(4, 12));
					}else if("07".equals(record.getCztp())){//��������
						map.put("unitprice",realamont+"Ԫ");
						map.put("timesection", record.getConsumedate().substring(5, 16));
					}else{
						map.put("timesection", record.getConsumedate().substring(5, 16));
					}
					amount+=realamont;
					map.put("realamont", CoreUtil.sswr2(realamont));
					map.put("checkouttime", endDate);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return CoreUtil.sswr2(amount);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GetICItemsResponse getICItems(GetICItemsRequest request) {
		long icrowdkindpriceid;
		GetICItemsResponse res = new GetICItemsResponse();
		if("01".equals(request.getType())){//�쿨
			icrowdkindpriceid = Long.parseLong(request.getKey());
			res.setIcitems(oneCardDao.getICItems(request.getDate(), icrowdkindpriceid));
			res.setCode("0000");
			res.setDescribe("�ɹ�");
		}else{
			Custom c = findCustom(request.getKey(), request.getSwiping());
			////////////////////////////////////////////////////////////////
			if(c == null || c.getInote10() == null){
				res.setCode("2018");
				res.setDescribe("�û�������");
			}else{
				String path = "com/ectrip/sale/service/card/html/tradingRecord.html";
				int count = 5;
				double balance = oneCardDao.getUseryfkBalance(c.getUsid());
				double balanceType = oneCardDao.getUseryfktypeBalance(c.getUsid());
				try {
					Map map = BeanUtils.describe(c);
					map.put("balance", balance);
					map.put("balanceType", balanceType);
					res.setCustom(map);
				} catch (Exception e) {
					e.printStackTrace();
					res.setCustom(c);
				} 
				List<?> uncheckoutList = null;//δ���˶���
				icrowdkindpriceid = c.getInote10();
				if("02".equals(request.getType()) && "01".equals(c.getStatus())) 
					res.setIcitems(oneCardDao.getICItems(request.getDate(), icrowdkindpriceid));
				if("04".equals(request.getType())){//����
					Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) oneCardDao.get(Edmcrowdkindpricetab.class, Long.valueOf(c.getInote10())); 
					Edmtickettypetab ticke = (Edmtickettypetab) oneCardDao.get(Edmtickettypetab.class, price.getItickettypeid());
					((Map)(res.getCustom())).put("itickettypeid",ticke.getItickettypeid()+"");
					res.setMont(ticke.getMnominalfee()+"");
				}else if("05".equals(request.getType())){//�˿�
					path = "com/ectrip/sale/service/card/html/checkout.html";
					uncheckoutList = oneCardDao.getUncheckoutList(c.getUsid(),true);
					double uncheckout = getUncheckoutMoney(uncheckoutList);//δ���˽��
					double deposit = oneCardDao.findDeposit(c.getUsid());//Ѻ��
					double money = CoreUtil.sswr2(balance-uncheckout+deposit);
					String msg = "<html><div><font color='red'>���˿���</font></div><div>����ʣ���<font color='BLUE'>��"
							+balance+"</font>��Ѻ��<font color='BLUE'>��"+deposit+"</font><div>δ���˽��<font color='Green'>��"+uncheckout+"</font></div><div>";
					if(money < 0){
						msg+="Ӧ��ȡ���<font color='red'>��"+-money+"</font>";
					}else{
						msg+="Ӧ�˿���<font color='red'>��"+money+"</font>";
					}
					msg+="</div></html>";
					res.setDescribe(msg);
					res.setMont(money+"");//������ʾҪ�յĽ��,������ʾҪ�˽��
					res.setType(request.getType());
					count = 10;
				}else if("06".equals(request.getType())){//����
					path = "com/ectrip/sale/service/card/html/checkout.html";
					count = 10;
					uncheckoutList = oneCardDao.getUncheckoutList(c.getUsid(),true);
					double uncheckout = getUncheckoutMoney(uncheckoutList);//δ���˽��
					double money = CoreUtil.sswr2(balance-uncheckout);
					String msg = "<html><div><font color='red'>�����ˡ�</font></div><div>�������<font color='BLUE'>��"
							+balance+"</font><div>δ���˽��<font color='BLUE'>��"+uncheckout+"</font></div><div>";
					if(uncheckoutList.size() > 0){
						if(money < 0){
							msg+="Ӧ��ȡ���<font color='red'>��"+-money+"</font>";
						}else{
							money = 0;
							msg+="�������ۿ�";
						}
					}else{
						msg+="�������";
					}
					res.setDescribe(msg);
					res.setMont(money+"");//������ʾҪ�յĽ��,������ʾҪ�˽��
					res.setType(request.getType());
				}
				res.setHtml(findTradingRecordHtml(uncheckoutList,null,path,c,balance,balanceType,count));
				res.setCode("0000");
			}
		}
		return res;
	}
	/**
	 * Describe:���׼�¼��װ
	 * @author liujianwen
	 * @param uncheckoutList δ����list
	 * @param msg
	 * @param path
	 * @param c
	 * @param yfk
	 * @param zsyfk
	 * @param count
	 * @return
	 * return:String
	 * Date:2016-7-6
	 */
	@SuppressWarnings("rawtypes")
	private String findTradingRecordHtml(List<?> uncheckoutList,String msg,String path,Custom c,double yfk,double zsyfk, int count){
		BufferedReader bf = null;
		try {
			//"com/ectrip/sale/service/card/html/tradingRecord.html"
			File file = new File(getClass().getClassLoader().getResource(path).toURI());
			if(file.exists()){
				bf = new BufferedReader(new FileReader(file));
				String line = null;
				StringBuilder sb = new StringBuilder();
				Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) oneCardDao.get(Edmcrowdkindpricetab.class, Long.valueOf(c.getInote10())); 
				Edmtickettypetab ticke = (Edmtickettypetab) oneCardDao.get(Edmtickettypetab.class, price.getItickettypeid());
				List<String> w1 = new ArrayList<String>();
				List<String> w2 = new ArrayList<String>();
				List<String> w3 = new ArrayList<String>();
				while((line = bf.readLine()) != null){
					line = line.trim();
					if("<!--msg-->".equals(line)){
						sb.append(msg);
					}else if("<!--cardName-->".equals(line)){
						sb.append(ticke.getSztickettypename());
					}else if("<!--status-->".equals(line)){
						Sysparv5 s = (Sysparv5) oneCardDao.get(Sysparv5.class, new Sysparv5Id("KPZT", c.getStatus()));
						if(s != null) sb.append("(").append(s.getPmva()).append(")");
					}else if("<!--userName-->".equals(line)){
						sb.append(c.getLname()==null?"":c.getLname());
					}else if("<!--userIDCard-->".equals(line)){
						sb.append(c.getZjhm()==null?"":c.getZjhm());
					}else if("<!--userId-->".equals(line)){
						sb.append(c.getUsid());
					}else if("<!--cardNo-->".equals(line)){
						sb.append(c.getNote10()==null?"":c.getNote10());
					}else if("<!--tel-->".equals(line)){
						sb.append(c.getMobile()==null?"":c.getMobile());
					}else if("<!--openTime-->".equals(line)){
						sb.append(c.getLdate());
					}else if("<!--balance-->".equals(line)){
						sb.append("��").append(yfk);
					}else if("<!--zsBalance-->".equals(line)){
						sb.append("��").append(zsyfk);
					}else if("<!--while1-->".equals(line)){
						while((line = bf.readLine()) != null){
							line = line.trim();
							if("<!--whileEnd1-->".equals(line)) break;
							w1.add(line);
						}
						List<?> cz = oneCardDao.getTopRechargeByUsid(c.getUsid(), count);
						for (Object o : cz) {
							Map map = (Map) o;
							packageHtmlWhile(sb, w1, 
									map.get("orid"),
									CoreUtil.sswr2(Double.parseDouble(map.get("amount").toString())),
									map.get("accpointzs")==null?"":CoreUtil.sswr2(Double.parseDouble(map.get("accpointzs").toString())),
											map.get("cztpStr"),
											map.get("szemployeename"),
											map.get("rechargedate")
									);
						}
					}else if("<!--while2-->".equals(line)){
						while((line = bf.readLine()) != null){
							line = line.trim();
							if("<!--whileEnd2-->".equals(line)) break;
							w2.add(line);
						}
						List<?> cz = oneCardDao.getTopConsumeByUsid(c.getUsid(), count);
						for (Object o : cz) {
							Map map = (Map) o;
							packageHtmlWhile(sb, w2, 
									map.get("orid"),
									CoreUtil.sswr2(Double.parseDouble(map.get("consumeamount").toString())),
									"<font color=\""+("00".equals(map.get("xfzt"))?"black":"red")+"\">"+map.get("xfztStr")+"</font>",
									map.get("sztickettypename"),
									map.get("xffsStr"),
									map.get("consumedate").toString()+"<br/>"+map.get("checkoutdate").toString()
									);
						}
					}else if("<!--while3-->".equals(line)){
						while((line = bf.readLine()) != null){
							line = line.trim();
							if("<!--whileEnd3-->".equals(line)) break;
							w3.add(line);
						}
						if(uncheckoutList != null){
							for (Object o : uncheckoutList) {
								Map map = (Map) o;
								packageHtmlWhile(sb, w3, 
										map.get("orid"),
										map.get("realamont").toString(),
										map.get("xfztStr") ,
										map.get("sztickettypename"),
										map.get("unitprice"),//checkouttime,unitprice
										map.get("consumedate")+"<br/>"+map.get("checkouttime")
										);
							}
						}
					}else{
						sb.append(line);
					}
				}
				sb.toString();
				System.out.println(sb);
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bf != null)
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return "";
	}
	private List<String> packageReceipt(String path,String orid,List<?> unlist, String ... addStrs){
		return packageReceipt(null,path,orid,unlist,addStrs);
	}
	/**
	 * Describe:��ӡСƱ
	 * @author liujianwen
	 * @param map ��ӡ��ʷ���Ѽ�¼��������
	 * @param path
	 * @param orid
	 * @param unlist δ���˵��б�
	 * @param addStrs �ڿ���֮ǰ����
	 * @param isRechange
	 * @return
	 * return:List<String>
	 * Date:2016-5-19
	 */
	private List<String> packageReceipt(Object oneMap,String path,String orid,List<?> unlist, String ... addStrs){
		BufferedReader bf = null;
		List<String> list = new ArrayList<String>();
		try {
			//			1605231000000381
			List<?> history = null;
			if(oneMap == null){
				boolean isRechange = "1".equals(orid.substring(6, 7));
				history = isRechange?oneCardDao.getRechargeByOrid(orid,false):oneCardDao.getConsumeByOrid(orid,false);
				if(history.size() == 0) return list;
			}
			if(path == null){
				Map map = (Map) history.get(0);
				Object cztp = map.get("cztp");
				if("00".equals(cztp)){//��ֵ
					path = receiptRecharge;
				}else if("01".equals(cztp)){//����
					path = receiptReplace;
				}else if("02".equals(cztp)){//�˿�
					path = receiptRefund;
				}else if("08".equals(cztp)){//����
					path = receiptCheckout;
				}else if("03".equals(cztp) || "06".equals(cztp) || "07".equals(cztp)){//����
					path = receiptConsume;
				}else if("04".equals(cztp)){//���ѳ���
					path = receiptConsumeRefund;
				}else if("05".equals(cztp)){//��ֵ����
					path = receiptRechargeRefund;
				}
			}
			File file = new File(getClass().getClassLoader().getResource(path).toURI());
			int addStrCurIndex = 0;//�������ֵ�����
			if(file.exists()){
				bf = new BufferedReader(new FileReader(file));
				String line = null;
				List<String> w1 = new ArrayList<String>();
				Map one = (Map) (oneMap == null?history.get(0):oneMap);
				while((line = bf.readLine()) != null){
					String linet = line.trim();
					int size = list.size();
					String arg = size>0?list.get(size-1):"";//
					if("<!--while1-->".equals(linet)){
						while((line = bf.readLine()) != null){
							linet = line.trim();
							if("<!--whileEnd1-->".equals(linet)) break;
							w1.add(line);
						}
						for (Object o : unlist==null?history:unlist) {
							Map map = (Map) o;
							StringBuilder sb = new StringBuilder();
							int i = 0;
							for(String key : w1){
								Object obj = map.get(key);
								sb.append(obj==null?"":obj.toString());
								if(i < w1.size()-1) sb.append("|");
								i ++;
							}
							list.add(sb.toString());
						}
					}else if(linet.matches("<!--.+-->")){
						if(size > 0) list.remove(size-1);
						list.add(arg+one.get(linet.substring(4, linet.length()-3)));
					}else{
						if(line.trim().equals("")){//�ڿ���֮ǰ���
							if(addStrs != null && addStrCurIndex < addStrs.length){//
								list.add(addStrs[addStrCurIndex]);
								addStrCurIndex ++;
							}else{
								list.add(line);
							}
						}else{
							list.add(line);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bf != null)
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	private void packageHtmlWhile(StringBuilder sb,List<String> keys, Object ... values){
		if(keys == null || values == null || keys.size() == 0 || values.length == 0) return;
		int i = 0;
		for (String k : keys) {
			if("<!--value-->".equals(k)){
				if(values.length >= i+1)sb.append(values[i] == null?"":values[i]);
				i ++;
			}else{
				sb.append(k);
			}
		}
	}
	/**
	 * Describe:�ۿ�����ֵ
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#sale(com.ectrip.sale.service.card.model.SaleRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-5
	 */
	public Response sale(SaleRequest request) {
		Response res = new Response();
		Custom c = (Custom) oneCardDao.get(Custom.class, request.getSzticketprintno());
		if(c == null){
			Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) oneCardDao.get(Edmcrowdkindpricetab.class, 
					Long.parseLong(request.getIcrowdkindpriceid()));
			//			Edmtickettypetab ticket = (Edmtickettypetab) oneCardDao.get(Edmtickettypetab.class, price.getItickettypeid());
			boolean isanonymous = price.getIpeoplenumrange()!=1;//�Ƿ�������
			if(!isanonymous
					&&(request.getName() == null 
					|| request.getCardno() == null 
					|| "".equals(request.getSzticketprintno())
					|| request.getIdcard() == null )
					){//ʵ����
				res.setCode("2001");
				res.setDescribe("ʵ���Ʋ������벻�Ϸ�!");
			}else{
				c = new Custom();
				c.setNote10(request.getCardno());//����
				c.setIbusinessid(price.getIbusinessid());
				c.setUsid(request.getSzticketprintno());
				String pwd = request.getPwd();
				if(pwd == null || "".equals(pwd)) pwd = CoreUtil.makeRandomPwd();
				c.setPassword(Tools.md5Encode(pwd));
				//				c.setPassword(isanonymous?CoreUtil.makeRandomPwd():request.getPwd());
				c.setLname(isanonymous?"�����û�":request.getName());
				c.setAddr(request.getAddress());
				c.setMobile(request.getTel());
				c.setInote10(price.getIcrowdkindpriceid().intValue());//inote10�濨Ƭ����(�۸�)id
				c.setLgtp("01");//ɢ��
				c.setUstp("01");//01-���û�
				c.setStatus("01");//����״̬
				c.setUsqx("01110000000000000000");
				c.setAutofapiao(0);
				c.setUsdj(0);
				c.setTtlb("00");//�Ŷ����
				c.setZjhm(request.getIdcard());
				c.setLogtimes("0");
				c.setNote6(request.getName());
				c.setNote2("0000");
				c.setNote3("0");// ���� 0δ���� 1����
				c.setNote4("0");// �ֻ�0δ���� 1����
				c.setInote2(1);// Ĭ����֧��Աʹ���Լ���Ԥ����
				//					c.setNote5(note5);//ע������ Tools.md5Encode(custom.getPassword())
				c.setLdate(Tools.getTodayString());// ע������
				c.setLpdate(c.getLdate());
				oneCardDao.save(c);
				//////////////////////////////������ֵ��һ��Ѻ�𿪿���¼///////////////////////////////////////////////
				String saleorid = createRechargeOrid();
				Icrechargerecord recharge = new Icrechargerecord();//��ֵ��¼��
				recharge.setOrid(saleorid);
				recharge.setIcrechargerecordid(getSeq("ICRechargerecord_SEQ"));
				recharge.setIemployeeid(Long.parseLong(request.getIemployeeid()));
				recharge.setIticketwinid(Long.parseLong(request.getIticketwinid()));
				recharge.setCardno(request.getCardno());
				recharge.setSzticketprintno(c.getUsid());//Ʊ�ż���usid
				recharge.setAccpointzs(0d);//���ͽ��䶯
				recharge.setAmount(request.getDeposit());//ʵ�ʽ��䶯,��Ϊ����
				recharge.setCztp("09");//cztp 09,����
				recharge.setZffs(request.getZffs());//֧����ʽ
				recharge.setNote("����,��ȡѺ��");
				recharge.setInote1(1L);//Ѻ��ר�ñ�־
				recharge.setRechargedate(ymdhms.format(Calendar.getInstance().getTime()));
				oneCardDao.save(recharge);
				///////////////////////////////////////////////////////////////////////////////////////////////////////////
				Icitem icitem = null;
				if(request.getIcitemid() != null) icitem = (Icitem) oneCardDao.get(Icitem.class, Long.parseLong(request.getIcitemid()));
				String orid = recharge("һ��ͨ������ֵ",icitem, c, request);
				if(c.getPassword() != null && c.getMobile() != null && !"".equals(c.getPassword()) && !"".equals(c.getMobile())) {
					oneCardDao.saveMbmessage(getSeq("mbmessage_seq"), c.getMobile(), "0013", c.getUsid(),pwd,c.getNote10());
				}
				res.setCode("0000");
				res.setDescribe("�����ɹ�!");
				res.setHtml(
						findTradingRecordHtml(null,"�ۿ��ɹ�","com/ectrip/sale/service/card/html/sale.html"
								,c,oneCardDao.getUseryfkBalance(c.getUsid()),oneCardDao.getUseryfktypeBalance(c.getUsid()),5));
				if("01".equals(request.getPrintReceipt())) res.setReceipt(packageReceipt(receiptSale, orid,null,"��ƬѺ��:"+request.getDeposit()));
			}
		}else{
			res.setCode("2001");
			res.setDescribe("�ڲ������Ѵ���!");
		}
		return res;
	}
	@SuppressWarnings("rawtypes")
	public long getSeq(String seqName) {
		try {
			List<Map> ls = oneCardDao.findBySqlToMapnocolsesession("select " + seqName
					+ ".nextval  from dual");
			long n = Long.parseLong((((Map) ls.get(0)).get("NEXTVAL")).toString());
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public Response recharge(SaleRequest request) {
		Response res = new Response();
		Custom c = oneCardDao.getOnecardCustom(request.getSzticketprintno());;
		if(c != null){
			if(!"01".equals(c.getStatus())){//ֻҪ״̬������,���޷���ֵ
				//				c.setStatus("20");
				//				oneCardDao.update(c);
				res.setCode("2020");
				res.setDescribe("��Ƭ״̬���������޷���ֵ��");
				return res;
			}
			boolean iszdycz = request.getIcitemid()==null;
			Icitem icitem = null;
			if(!iszdycz){
				icitem =(Icitem) oneCardDao.get(Icitem.class, Long.parseLong(request.getIcitemid()));
				if(icitem != null && icitem.getStatus() == 1){
					Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) oneCardDao.get(Edmcrowdkindpricetab.class, icitem.getIcrowdkindpriceid());
					if(price.getIcrowdkindpriceid().intValue() != c.getInote10().intValue()){//�����Ƭ���Ͳ�һ��
						res.setCode("2001");
						res.setDescribe("�˿�Ƭ��֧�ִ����ͳ�ֵ��Ŀ!");
						return res;
					}
				}else{
					res.setCode("2001");
					res.setDescribe("��ֵ��Ŀ������");
					return res;
				}
			}
			String orid = recharge("һ��ͨ��ֵ",icitem, c, request);
			res.setCode("0000");
			res.setDescribe("��ֵ�ɹ���");
			res.setHtml(
					findTradingRecordHtml(null,"��ֵ�ɹ�","com/ectrip/sale/service/card/html/recharge.html"
							,c,oneCardDao.getUseryfkBalance(c.getUsid()),oneCardDao.getUseryfktypeBalance(c.getUsid()),5));
			if("01".equals(request.getPrintReceipt())) res.setReceipt(packageReceipt(receiptRecharge, orid,null));
		}else{
			res.setCode("2001");
			res.setDescribe("�û�������!");
		}
		return res;
	}
	/**
	 * Describe:��ֵ˽�з���
	 * @author liujianwen
	 * @param custom
	 * @param request
	 * @return˵
	 * return:Response
	 * Date:2016-5-5
	 */
	private String recharge(String note,Icitem icitem,Custom c ,SaleRequest request) {
		boolean iszdyjg = (icitem== null);//�Ƿ����Զ���۸�
		double realmont = iszdyjg?CoreUtil.sswr2(Double.parseDouble(request.getMont())):CoreUtil.sswr2(icitem.getAccpoint());
		String orid = createRechargeOrid();
		Useryfk yfk = new Useryfk();
		yfk.setUsid(c.getUsid());  //֧���û�
		yfk.setBdate(Tools.getTodayString());
		yfk.setNote(note);
		yfk.setOrderid(orid);
		yfk.setPoint(realmont);
		yfk.setYfklb(1);
		yfk.setYfksc("05"); // �̻�
		yfk.setCztp(0);
		yfk.setSeq((int) getSeq("yfksequence"));
		oneCardDao.saveUseryfk(yfk);

		Orderpay op = new Orderpay();//ԭ��ֵ��¼��
		op.setOrid(yfk.getOrderid());
		op.setOrda(yfk.getBdate());
		op.setOrti(sdf1.format(Calendar.getInstance().getTime()));
		op.setUsid(yfk.getUsid());
		op.setMont(yfk.getPoint());
		op.setNote(note);
		// ��ֵ���
		op.setSclb("1"); // ��ֵ
		// ����״̬
		op.setDdzt("02");
		oneCardDao.save(op);

		if(!iszdyjg){//�Զ���۸���
			Useryfktype yfkt = new Useryfktype();
			yfkt.setUsid(yfk.getUsid());  //֧���û�
			yfkt.setBdate(yfk.getBdate());
			yfkt.setNote(yfk.getNote());
			yfkt.setOrderid(yfk.getOrderid());
			yfkt.setPoint(CoreUtil.sswr2(icitem.getAccpointzs()));
			yfkt.setYfklb(yfk.getYfklb());
			yfkt.setYfksc(yfk.getYfksc()); // �̻�
			yfkt.setCztp(yfk.getCztp());
			yfkt.setSeq((int) getSeq("yfksequence"));
			oneCardDao.saveUseryfktype(yfkt);
		}

		Icrechargerecord recharge = new Icrechargerecord();//��ֵ��¼��
		recharge.setOrid(yfk.getOrderid());
		recharge.setIcrechargerecordid(getSeq("ICRechargerecord_SEQ"));
		recharge.setIemployeeid(Long.parseLong(request.getIemployeeid()));
		recharge.setIticketwinid(Long.parseLong(request.getIticketwinid()));
		recharge.setCardno(request.getCardno());
		recharge.setSzticketprintno(c.getUsid());//Ʊ�ż���usid
		recharge.setAccpointzs(iszdyjg?0:icitem.getAccpointzs());//���ͽ��䶯
		recharge.setAmount(iszdyjg?CoreUtil.sswr2(Double.parseDouble(request.getMont())):icitem.getAccpoint());//ʵ�ʽ��䶯,��Ϊ����
		recharge.setCztp("00");//cztp 00��ֵ,01����,02�˿�
		recharge.setZffs(request.getZffs());//֧����ʽ
		recharge.setNote(note);
		recharge.setRechargedate(ymdhms.format(Calendar.getInstance().getTime()));
		oneCardDao.save(recharge);
		return orid;
	}
	public String createRechargeOrid() {
		try {
			long n = getSeq("ONECARDORID_SEQ");
			String num = (n<1000000000?String.format("%0"+10+'d', n):n+"");//19λqz
			return sdf2.format(Calendar.getInstance().getTime())+"1"+num;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String createConsumeOrid() {
		try {
			long n = getSeq("ONECARDORID_SEQ");
			String num = (n<1000000000?String.format("%0"+10+'d', n):n+"");//17λ
			return sdf2.format(Calendar.getInstance().getTime())+"2"+num;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Describe:����Ʊ��: �����λ+seq+У��1λ
	 * @author liujianwen
	 * @return
	 * return:String
	 * Date:2016-9-26
	 */
	private String createSzticketprintno(){
		int year = Calendar.getInstance().get(Calendar.YEAR)-2000;
		long n = getSeq("ticketid_sequence");
		String num = year+(n<1000?String.format("%0"+4+'d', n):n+"");
		num =  num+Math.abs(DesUtil.calc_crc(num.getBytes())%9);
		return num;
	}
	public Response cardIsExist(GetCardStatusRequest request) {
		GetCardStatusResponse res = new GetCardStatusResponse();
		boolean b = false;
		Custom c = (Custom) oneCardDao.get(Custom.class, request.getSzticketprintno());
		///////////////////////////////////////////////
		if(c == null){
			b = c== oneCardDao.getBukaUsid(request.getSzticketprintno());//���ܲ���
		}
		if(b){
			res.setCode("0000");
			res.setDescribe("���Ų�����,����д��");
			res.setSzticketprintno(createSzticketprintno());
		}else{
			res.setCode("2020");
			res.setDescribe("�����Ѵ���");
		}
		return res;
	}
	/**
	 * Describe:ģ����ѯ�û�
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#getCustoms(com.ectrip.sale.service.card.model.GetICItemsRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-7
	 */
	public Response getCustoms(GetICItemsRequest request) {
		GetCustomsResponse res = new GetCustomsResponse();
		res.setCode("0000");
		res.setCustoms(oneCardDao.getCustoms(request, 20));
		return res;
	}
	/**
	 * Describe:��ʧ���
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#reportTheLoss(com.ectrip.sale.service.card.model.GetICItemsRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-11
	 */
	public Response reportTheLoss(GetICItemsRequest request) {
		Response res = new Response();
		Custom c = oneCardDao.getOnecardCustom( request.getKey());
		if(c == null){
			res.setCode("2015");
			res.setDescribe("�û�������");
		}else{
			if("01".equals(request.getType())){//��ʧ
				if("01".equals(c.getStatus())){//ֻ������״̬���ܹ�ʧ
					c.setStatus("20");
					oneCardDao.update(c);
					res.setCode("0000");
					res.setDescribe("��ʧ�ɹ���");
					res.setHtml(
							findTradingRecordHtml(null,"��ʧ�ɹ�","com/ectrip/sale/service/card/html/custom.html"
									,c,oneCardDao.getUseryfkBalance(c.getUsid()),oneCardDao.getUseryfktypeBalance(c.getUsid()),5));
				}else{
					res.setCode("2020");
					res.setDescribe("������״̬���޷���ʧ��");
				}
			}else if("02".equals(request.getType())){//���
				if("20".equals(c.getStatus())){//ֻ�й�ʧ״̬���ܽ��
					c.setStatus("01");
					oneCardDao.update(c);
					res.setCode("0000");
					res.setDescribe("��ҳɹ���");
					res.setHtml(
							findTradingRecordHtml(null,"��ҳɹ�","com/ectrip/sale/service/card/html/custom.html"
									,c,oneCardDao.getUseryfkBalance(c.getUsid()),oneCardDao.getUseryfktypeBalance(c.getUsid()),5));
				}else{
					res.setCode("2020");
					res.setDescribe("�ǹ�ʧ״̬���޷���ң�");
				}
			}else{//
				res.setCode("2020");
				res.setDescribe("��֧�ִ˲��� ��");
			}
		}
		return res;
	}
	/**
	 * Describe:��������
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#replaceCard(com.ectrip.sale.service.card.model.SaleRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-12
	 */
	public Response replaceCard(SaleRequest request) {
		Response res = new Response();
		Custom c = oneCardDao.getOnecardCustom(request.getOldSzticketprintno());
		if(c == null || c.getInote10() == null || c.getNote10() == null){
			res.setCode("2020");
			res.setDescribe("��Ч�û�������ʧ��");
			return res;
		}
		if(!"01".equals(c.getStatus())){//ֻҪ״̬������,���޷���ֵ
			//			c.setStatus("20");
			//			oneCardDao.update(c);
			res.setCode("2020");
			res.setDescribe("��Ƭ״̬���������޷�������");
			return res;
		}
		double mont = Double.parseDouble(request.getMont());
		if(mont < 0){
			res.setCode("2020");
			res.setDescribe("����Ϊ������");
			return res;
		}
		String orid = createRechargeOrid();
		Icrechargerecord recharge = new Icrechargerecord();//��ֵ��¼��
		recharge.setOrid(orid);
		recharge.setIcrechargerecordid(getSeq("ICRechargerecord_SEQ"));
		recharge.setIemployeeid(Long.parseLong(request.getIemployeeid()));
		recharge.setIticketwinid(Long.parseLong(request.getIticketwinid()));
		recharge.setCardno(request.getCardno());
		recharge.setSzticketprintno(c.getUsid());//Ʊ�ż���usid
		recharge.setOldcardno(c.getNote10());
		recharge.setOldszticketprintno(c.getUsid());
		recharge.setNewszticketprintno(request.getSzticketprintno());//��Ʊ��
		recharge.setAccpointzs(0d);//���ͽ��䶯
		recharge.setAmount(CoreUtil.sswr2(mont));//ʵ�ʽ��䶯,��Ϊ����
		recharge.setCztp("01");//cztp 00��ֵ,01����,02�˿�
		recharge.setZffs(request.getZffs());//֧����ʽ
		recharge.setNote("����");
		recharge.setRechargedate(Tools.getTodayString()+" "+ Tools.getNowTimeString());
		c.setNote10(request.getCardno());
		oneCardDao.update(c);
		oneCardDao.save(recharge);
		res.setCode("0000");
		res.setDescribe("�����ɹ�");
		res.setHtml(
				findTradingRecordHtml(null,"�����ɹ�","com/ectrip/sale/service/card/html/custom.html"
						,c,oneCardDao.getUseryfkBalance(c.getUsid()),oneCardDao.getUseryfktypeBalance(c.getUsid()),5));
		if("01".equals(request.getPrintReceipt())) res.setReceipt(packageReceipt(receiptReplace, orid,null));
		return res;
	}
	public Response refundedCard(SaleRequest request) {
		Response res = new Response();
		if(!"05".equals(request.getType()) && !"06".equals(request.getType())){
			res.setCode("2020");
			res.setDescribe("����Ĳ������");
			return res;
		}
		Custom c = oneCardDao.getOnecardCustom(request.getSzticketprintno());
		if(c == null){
			res.setCode("2020");
			res.setDescribe("��Ч�û����˿�ʧ��");
			return res;
		}
		if("20".equals(c.getStatus())){
			res.setCode("2020");
			res.setDescribe("��Ƭ��ʧ���У��޷�����������");
			return res;
		}
		if(!"01".equals(c.getStatus()) && "05".equals(request.getType())){//ֻҪ״̬������,���޷��˿�
			//			c.setStatus("20");
			//			oneCardDao.update(c);
			res.setCode("2020");
			res.setDescribe("��Ƭ״̬���������޷��˿���");
			return res;
		}
		double mont = Double.parseDouble(request.getMont());//�������Ľ��
		double ye =  (oneCardDao.getUseryfkBalance(c.getUsid()));//�û����
		List<?> unlist = oneCardDao.getUncheckoutList(c.getUsid(),true);
		double uncheckout = getUncheckoutMoney(unlist);//δ���˼��˽��
		double money = CoreUtil.sswr2(ye-uncheckout);

		double zsye = oneCardDao.getUseryfktypeBalance(c.getUsid());
		String orid = createRechargeOrid();
		Icrechargerecord recharge = new Icrechargerecord();//��ֵ��¼��
		Useryfk yfk = new Useryfk();
		yfk.setUsid(c.getUsid());  //֧���û�
		yfk.setBdate(Tools.getTodayString());
		yfk.setOrderid(orid);
		yfk.setPoint(CoreUtil.sswr2(mont));
		yfk.setYfklb(-1);
		yfk.setYfksc("05"); // �̻�
		yfk.setCztp(0);
		yfk.setSeq((int) getSeq("yfksequence"));
		String receipt = receiptCheckout;
		double deposit = 0;//ֻ�����˿�ʱ����Ѻ��
		if("05".equals(request.getType())){//������˿�,�˿������Ѻ��
			deposit = oneCardDao.findDeposit(c.getUsid());//Ѻ��
			money=CoreUtil.sswr2(money+deposit);
			if(money != mont){
				res.setCode("2020");
				res.setDescribe("����ѱ䶯�������²�ѯ��");
				return res;
			}
			if(money == 0){//���˿��������Ϊ0��ʵ�ʽ�Ϊ0�����������
				if(request.getActualAmount() != 0){
					res.setCode("2020");
					res.setDescribe("��Ч�ı䶯���!");
					return res;
				}
			}else if(money < 0){//ActualAmountֻΪ���������Զ��ս��
				if(request.getActualAmount() < -money){
					res.setCode("2020");
					res.setDescribe("����Ǯ��!");
					return res;
				}
				mont = -request.getActualAmount();
			}else{//ActualAmountֻΪ��������������Ǯ
				if(request.getActualAmount() > money){
					res.setCode("2020");
					res.setDescribe("����Ǯ��!");
					return res;
				}
				mont = request.getActualAmount();
			}

			receipt = receiptRefund;
			//������0
			yfk.setPoint(ye);
			yfk.setNote("һ��ͨ�˿�");
			//////////////////////////////////////////
			//�˿�ʱ���͵���0
			//�Զ���۸���
			Useryfktype yfkt = new Useryfktype();
			yfkt.setUsid(yfk.getUsid());  //֧���û�
			yfkt.setBdate(yfk.getBdate());
			yfkt.setNote(yfk.getNote());
			yfkt.setOrderid(orid);
			yfkt.setPoint(zsye);
			yfkt.setYfklb(yfk.getYfklb());
			yfkt.setYfksc(yfk.getYfksc()); // �̻�
			yfkt.setCztp(yfk.getCztp());
			yfkt.setSeq((int) getSeq("yfksequence"));
			oneCardDao.saveUseryfktype(yfkt);
			////////////////////////////////////////////////
			recharge.setAccpointzs(-zsye);//���ͽ��䶯
			recharge.setCztp("02");//cztp 00��ֵ,01����,02�˿�
			c.setStatus("04");//ɾ��
			oneCardDao.finishDeposit(c.getUsid());//���Ѻ��
		}else if("06".equals(request.getType())){//����
			if(unlist.size() == 0){
				res.setCode("2020");
				res.setDescribe("��δ����������Ŀ��������ˣ�");
				return res;
			}
			if(money >= 0){//Ԥ�����㹻
				if(mont != 0){
					res.setCode("2020");
					res.setDescribe("����ѱ䶯�������²�ѯ��");
					return res;
				}
				yfk.setPoint(uncheckout);
			}else{//Ԥ����㣬������ȡ����Ϊ����
				if((ye+(-mont)) != uncheckout){
					res.setCode("2020");
					res.setDescribe("����ѱ䶯�������²�ѯ��");
					return res;
				}
				yfk.setPoint(ye);
			}
			yfk.setNote("һ��ͨ����");
			//////////////////////////////////////////
			////////////////////////////////////////////////
			recharge.setAccpointzs(-0d);//���˾�Ϊʵ�ʽ������
			recharge.setCztp("08");//cztp 00��ֵ,01����,02�˿�,08����

		}
		///////////////////////////////////////////////////
		oneCardDao.saveUseryfk(yfk);
		recharge.setAmount(-mont);//ʵ�ʽ��䶯,��Ϊ����
		recharge.setOrid(orid);
		recharge.setIcrechargerecordid(getSeq("ICRechargerecord_SEQ"));
		recharge.setIemployeeid(Long.parseLong(request.getIemployeeid()));
		recharge.setIticketwinid(Long.parseLong(request.getIticketwinid()));
		recharge.setCardno(request.getCardno());
		recharge.setSzticketprintno(c.getUsid());//Ʊ�ż���usid
		recharge.setCardno(c.getNote10());
		//		recharge.setZffs(request.getZffs());//֧����ʽ
		recharge.setZffs(request.getZffs());//֧����ʽ
		recharge.setNote(request.getNote());
		recharge.setRechargedate(Tools.getTodayString()+" "+ Tools.getNowTimeString());
		oneCardDao.save(recharge);
		for (Object object : unlist) {
			Map map = (Map) object;
			Icconsumerecord record = (Icconsumerecord) map.get("self");
			record.setXfzt("00");//�Ѿ�����
			record.setCheckoutdate(map.get("checkouttime").toString());
			record.setConsumeamount((Double) map.get("realamont"));
		}
		String message = "05".equals(request.getType())?"�˿��ɹ�":"���˳ɹ�";
		res.setHtml(
				findTradingRecordHtml(null,message,"com/ectrip/sale/service/card/html/recharge.html"
						,c,oneCardDao.getUseryfkBalance(c.getUsid()),oneCardDao.getUseryfktypeBalance(c.getUsid()),5));
		res.setCode("0000");
		res.setDescribe(message);
		if("01".equals(request.getPrintReceipt()))  res.setReceipt(
				packageReceipt(receipt, orid,unlist,"���ڿ۳�:"+CoreUtil.sswr2(yfk.getPoint()+deposit)));
		return res;

	}
	public List<?> getConsumePricesByIaccessequipid(Long iaccessequipid){
		return oneCardDao.getConsumePrices(iaccessequipid,Tools.getTodayString());
	}
	/**
	 * Describe:����ѡ˳���ȡ������Ŀ�б�
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#getConsumePrices(com.ectrip.sale.service.card.model.GetCardsRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-14
	 */
	public GetCardsResponse getConsumePrices(GetCardsRequest request) {
		GetCardsResponse res = new GetCardsResponse();
		Esbaccessequiptab acc = oneCardDao.getEsbaccessequiptabByIticketwinid(Long.parseLong(request.getIticketwinid()));
		if(acc == null){
			res.setCode("2020");
			res.setDescribe("δע����Ч��׼�������豸");
		}else{
			res.setCode("0000");
			res.setDescribe("�ɹ�");
			//			Edmticketproduct ep = (Edmticketproduct) oneCardDao.get(Edmticketproduct.class, price.getItickettypeid());
			res.setPrices(oneCardDao.getConsumePrices(acc.getId().getIaccessequipid(),Tools.getTodayString()));
		}
		return res;

	}
	/**
	 * Describe:�Ƿ����ѹ���
	 * @author liujianwen
	 * @param usid
	 * @param icrowdkindpriceid
	 * @param time
	 * @return
	 * return:boolean
	 * Date:2016-5-31
	 */
	private boolean isConsumeMoreFast(String usid, Long icrowdkindpriceid,long allow, long time){
		Icconsumerecord con = oneCardDao.getRecentlyIcconsumerecord(icrowdkindpriceid, usid);
		if(con == null){
			return false;
		}else{
			try {
				long last = ymdhms.parse(con.getConsumedate()).getTime()/1000;
				return time-last<allow;
			} catch (ParseException e) {
				e.printStackTrace();
				return true;
			}
		}
	}
	/**
	 * Describe:����
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-13
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Response consume(ConsumeRequest request){
		ConsumeResponse res = new ConsumeResponse();
		Custom c = findCustom(request.getSzticketprintno(), request.getSwiping());
		if(c == null){
			res.setCode("2015");
			res.setDescribe("��Ч�û�");
			return res;
		}
		Map map = null;
		try {
			map = BeanUtils.describe(c);
			res.setCustom(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!"01".equals(c.getStatus())){//
			res.setCode("2020");
			res.setDescribe("��Ƭ״̬������,�޷�����");
			return res;
		}

		List<Edmcrowdkindpricetab> real00 = new ArrayList<Edmcrowdkindpricetab>();//������ʵ�ʽ��
		List<Edmcrowdkindpricetab> real01 = new ArrayList<Edmcrowdkindpricetab>();//���������ͽ��
		List<Edmcrowdkindpricetab> real02 = new ArrayList<Edmcrowdkindpricetab>();//ֻ��������ʵ�ʽ��

		List<Edmcrowdkindpricetab> realJz = new ArrayList<Edmcrowdkindpricetab>();//������Ŀ
		Map<Edmcrowdkindpricetab, Bindingprice> realJs = new LinkedHashMap<Edmcrowdkindpricetab, Bindingprice>();////��ʱ��Ŀ

		boolean iszj = request.getIaccessequipid() != null;//�Ƿ���բ��
		double mont00 = 0;
		double mont01 = 0;
		double mont02 = 0;
		double montJZ = 0;//���˽��
		Date realDate = Calendar.getInstance().getTime();
		long time = realDate.getTime()/1000;//��
		String date = ymdhms.format(realDate);
		if(request.getIaccessequipid() == null){//��������
			Esbaccessequiptab acc = oneCardDao.getEsbaccessequiptabByIticketwinid(Long.parseLong(request.getIticketwinid()));
			if(acc == null){
				res.setCode("2020");
				res.setDescribe("���Ѵ���δע��");
				return  res;
			}
			request.setIaccessequipid(acc.getId().getIaccessequipid()+"");
			if(CONSUME_TICKET.equals(request.getConsumeType())){//����ǵ�������Ʊ����
				Icconsumerecord consum = oneCardDao.getTicketConsumeByThirdpartyOrid(request.getThirdpartyOrid());
				if(consum != null){
					res.setCode("3000");
					res.setDescribe("�Ѿ����ڶ������Ѿ�֧��");
					res.setConsumeamount(consum.getConsumeamount()+"");
					res.setUsid(consum.getUsid());
					res.setIemployeeid(consum.getIemployeeid()+"");
					res.setConsumedate(consum.getConsumedate());
					res.setIaccessequipid(consum.getIaccessequipid()+"");
					res.setConsumeOrid(consum.getOrid());
					return  res;
				}
				Edmcrowdkindpricetab price = oneCardDao.getTicketConsumePrice(CONSUME_TICKET);
				if(price == null){
					res.setCode("2023");
					res.setDescribe("һ��ͨ��Ʊ�����ѽ�ֹ(�޺Ϸ�Ĭ��������Ŀ)");
					return  res;
				}
				Edmcrowdkindpricetab p = new Edmcrowdkindpricetab();
				try {
					BeanUtils.copyProperties(p, price);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				mont02 = CoreUtil.sswr2(Double.parseDouble(request.getMont()));
				p.setMactualsaleprice(mont02);
				real02.add(p);
				res.setConsumeamount(request.getMont());
				res.setUsid(c.getUsid());
				res.setIemployeeid(request.getIemployeeid());
				res.setConsumedate(date);
				res.setIaccessequipid(request.getIaccessequipid());
			}else{
				if(request.getPrices() == null || request.getPrices().size() == 0){
					res.setCode("2020");
					res.setDescribe("������Ŀ����Ϊ��");
					return res;
				}
				for(Edmcrowdkindpricetab p : request.getPrices()){
					double inputAmount = p.getMactualsaleprice();
					Edmcrowdkindpricetab price = oneCardDao.getConsumeEdmcrowdkindpricetab(p.getIcrowdkindpriceid(),
							acc.getId().getIaccessequipid());
					Bindingprice bp = oneCardDao.getBindingprice(price.getIcrowdkindpriceid(), Long.parseLong(request.getIaccessequipid()));
					if(price == null || bp == null){
						res.setCode("2020");
						res.setDescribe("������Ŀ���Ϸ�");
						return res;
					}
					if(isConsumeMoreFast(c.getUsid(), price.getIcrowdkindpriceid(), bp.getIntervaltime(), time)){
						res.setCode("2022");
						res.setDescribe("ˢ������,��"+bp.getIntervaltime()+"�����ˢ����");
						return res;
					}
					boolean zdyjg = false;
					if(request.getPrices().size() == 1){//��Ϊ1ʱ�����Զ���۸�
						Edmticketproduct ep = (Edmticketproduct) oneCardDao.get(Edmticketproduct.class, price.getItickettypeid());
						zdyjg = (ep != null && ep.getInoteger4().intValue() != 0);//�Ƿ������Զ������Ѽ۸�
					}
					if(!zdyjg && price.getMactualsaleprice().doubleValue() != p.getMactualsaleprice()){//�������Զ���۸�
						res.setCode("2020");
						res.setDescribe("�۸�����");
						return res;
					}
					try {
						BeanUtils.copyProperties(p, price);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					p.setMactualsaleprice(inputAmount);
					if("00".equals(bp.getJztp())){//��ʱ����
						if("00".equals(bp.getXftp())){
							real00.add(p);
							mont00 +=p.getMactualsaleprice().doubleValue();
						}else if("01".equals(bp.getXftp())){
							real01.add(p);
							mont01 +=p.getMactualsaleprice().doubleValue();
						}else if("02".equals(bp.getXftp())){
							real02.add(p);
							mont02 +=p.getMactualsaleprice().doubleValue();
						}else{
							res.setCode("2020");
							res.setDescribe("���ѷ�ʽ���Ϸ�");
							return res;
						}
					}else if("01".equals(bp.getJztp())){//����
						realJz.add(p);
						montJZ += p.getMactualsaleprice().doubleValue();
					}else if("02".equals(bp.getJztp())){//��ʱ
						realJs.put(p, bp);
					}
				}
			}
		}else{//բ������
			Edmcrowdkindpricetab price  = oneCardDao.getFirstConsumeEdmcrowdkindpricetab(Long.parseLong(request.getIaccessequipid()));
			if(price == null){
				res.setCode("2020");
				res.setDescribe("������Ŀ���Ϸ�");
				return res;
			}
			Bindingprice bp = oneCardDao.getBindingprice(price.getIcrowdkindpriceid(), Long.parseLong(request.getIaccessequipid()));
			if(isConsumeMoreFast(c.getUsid(), price.getIcrowdkindpriceid(), bp.getIntervaltime(), time)){
				res.setCode("2022");
				res.setDescribe("ˢ������,��"+bp.getIntervaltime()+"�����ˢ����");
				return res;
			}
			if("00".equals(bp.getJztp())){//��ʱ����
				if("00".equals(bp.getXftp())){
					real00.add(price);
					mont00 +=price.getMactualsaleprice().doubleValue();
				}else if("01".equals(bp.getXftp())){
					real01.add(price);
					mont01 +=price.getMactualsaleprice().doubleValue();
				}else if("02".equals(bp.getXftp())){
					real02.add(price);
					mont02 +=price.getMactualsaleprice().doubleValue();
				}else{
					res.setCode("2020");
					res.setDescribe("���ѷ�ʽ���Ϸ�");
					return res;
				}
			}else if("01".equals(bp.getJztp())){//����
				realJz.add(price);
				montJZ += price.getMactualsaleprice().doubleValue();
			}else if("02".equals(bp.getJztp())){//��ʱ
				realJs.put(price, bp);
			}
		}

		mont00 = CoreUtil.sswr2(mont00);
		mont01 = CoreUtil.sswr2(mont01);
		mont02 = CoreUtil.sswr2(mont02);
		double totalMont = CoreUtil.sswr2(mont00+mont01+mont02);
		double yfk = oneCardDao.getUseryfkBalance(c.getUsid());//Ԥ����
		double yfkType = oneCardDao.getUseryfktypeBalance(c.getUsid());//�������
		map.put("balance", yfk);
		map.put("balanceType", yfkType);
		map.put("consume", CoreUtil.sswr2(totalMont+montJZ));
		double yfkneed = 0;
		double yfkTypeNeed = 0;
		if(mont02 > yfk){
			res.setCode("2021");
			res.setDescribe("�ֽ�����");
			return res;
		}else if(totalMont> CoreUtil.sswr2(yfk+yfkType)){
			res.setCode("2021");
			res.setDescribe("����");
			return res;
		}else{
			double yfkye = CoreUtil.sswr2(yfk -mont02);//Ԥ�������
			if(yfkye<mont00){//�ֽ�����
				yfkneed = yfk;
				yfkTypeNeed = CoreUtil.sswr2(totalMont-yfk);
			}else{
				if(mont01 > yfkType){//�����������ͽ��
					yfkTypeNeed = yfkType;
					yfkneed=  CoreUtil.sswr2(totalMont-yfkType);
				}else{
					yfkTypeNeed = mont01;
					yfkneed =CoreUtil.sswr2(mont00+mont02);
				}
			}
		}
		////////////////////////////////////////////////////////////////////////////
		String orid = createConsumeOrid();//�������Ѷ���
		String bdate = Tools.getTodayString();
		for(Edmcrowdkindpricetab p : realJz){//����
			Icconsumerecord record = new Icconsumerecord();
			record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
			record.setOrid(orid);
			record.setConsumeamount(p.getMactualsaleprice());
			record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
			record.setUsid(c.getUsid());
			record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
			record.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
			record.setConsumedate(date);
			record.setCheckoutdate(date);
			record.setCztp("07");//03��ʱ����,04���ѳ���,06��ʱ����,07��������
			record.setXfzt("01");//00:�ѽ��� 01:������ 02:��ʱ��
			record.setNote(request.getNote());
			record.setNote1(request.getThirdpartyOrid());
			record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
			oneCardDao.save(record);
		}
		for(Edmcrowdkindpricetab p : realJs.keySet()){//��ʱ
			Bindingprice bp = realJs.get(p);
			Timekeeping tk = JSON.parseObject(bp.getTimekeeping(), Timekeeping.class);//��ʱ����
			Icconsumerecord record = new Icconsumerecord();
			record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
			record.setOrid(orid);
			record.setConsumeamount(tk.getMinexpense());
			record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
			record.setUsid(c.getUsid());
			record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
			record.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
			record.setConsumedate(date);
			record.setCheckoutdate(date);
			record.setTimekeeping(bp.getTimekeeping());
			record.setCztp("06");//03��ʱ����,04���ѳ���,06��ʱ����,07��������
			record.setXfzt("02");//00:�ѽ��� 01:������ 02:��ʱ��
			record.setNote(request.getNote());
			record.setNote1(request.getThirdpartyOrid());
			record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
			oneCardDao.save(record);
		}
		for(Edmcrowdkindpricetab p : real02){//�����ֽ�
			Icconsumerecord record = new Icconsumerecord();
			record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
			record.setOrid(orid);
			record.setConsumeamount(p.getMactualsaleprice());
			record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
			record.setUsid(c.getUsid());
			record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
			record.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
			record.setConsumedate(date);
			record.setCheckoutdate(date);
			record.setCztp("03");//03����,04���ѳ���
			record.setXfzt("00");//�Ѿ�����
			record.setNote(request.getNote());
			record.setNote1(request.getThirdpartyOrid());
			record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
			oneCardDao.save(record);
		}
		double syyfk = CoreUtil.sswr2(yfkneed-mont02);//ʣ���ֽ�
		double syyfktype = yfkTypeNeed;//ʣ��Ԥ����
		for(Edmcrowdkindpricetab p : real00){//�������ֽ�
			if(syyfk >0 && syyfk < p.getMactualsaleprice().doubleValue()){//���ʣ���ֽ���������
				Icconsumerecord record = new Icconsumerecord();
				record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				record.setOrid(orid);
				record.setConsumeamount(syyfk);
				record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
				record.setUsid(c.getUsid());
				record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
				record.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
				record.setConsumedate(date);
				record.setCheckoutdate(date);
				record.setCztp("03");//03����,04���ѳ���
				record.setXfzt("00");//�Ѿ�����
				record.setNote(request.getNote());
				record.setNote1(request.getThirdpartyOrid());
				record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
				oneCardDao.save(record);
				///////////////////////////////////////////////////////////////
				double need =  CoreUtil.sswr2((p.getMactualsaleprice()-syyfk));
				Icconsumerecord record1 = new Icconsumerecord();
				record1.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				record1.setOrid(orid);
				record1.setConsumeamount(need);
				record1.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
				record1.setUsid(c.getUsid());
				record1.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
				record1.setXffs("21");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
				record1.setConsumedate(date);
				record1.setCheckoutdate(date);
				record1.setCztp("03");//03����,04���ѳ���
				record1.setXfzt("00");//�Ѿ�����
				record1.setNote(request.getNote());
				record1.setNote1(request.getThirdpartyOrid());
				record1.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
				syyfk = 0;
				syyfktype = CoreUtil.sswr2(syyfktype-need);
				oneCardDao.save(record1);
			}else{
				Icconsumerecord record = new Icconsumerecord();
				record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				record.setOrid(orid);
				record.setConsumeamount(p.getMactualsaleprice());
				record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
				record.setUsid(c.getUsid());
				record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
				if(syyfk==0){
					record.setXffs("21");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
					syyfktype = CoreUtil.sswr2(syyfktype-p.getMactualsaleprice());
				}else{
					record.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
					syyfk = CoreUtil.sswr2(syyfk-p.getMactualsaleprice());
				}
				record.setConsumedate(date);
				record.setCheckoutdate(date);
				record.setCztp("03");//03����,04���ѳ���
				record.setXfzt("00");//�Ѿ�����
				record.setNote(request.getNote());
				record.setNote1(request.getThirdpartyOrid());
				record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
				oneCardDao.save(record);
			}
		}
		for(Edmcrowdkindpricetab p : real01){//���������ͽ��
			if(syyfktype >0 && syyfktype < p.getMactualsaleprice().doubleValue()){//����������
				Icconsumerecord record = new Icconsumerecord();
				record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				record.setOrid(orid);
				record.setConsumeamount(syyfktype);
				record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
				record.setUsid(c.getUsid());
				record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
				record.setXffs("21");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
				record.setConsumedate(date);
				record.setCheckoutdate(date);
				record.setCztp("03");//03����,04���ѳ���
				record.setXfzt("00");//�Ѿ�����
				record.setNote(request.getNote());
				record.setNote1(request.getThirdpartyOrid());
				record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
				oneCardDao.save(record);
				///////////////////////////////////////////////////////////////
				double need =  CoreUtil.sswr2((p.getMactualsaleprice()-syyfktype));
				Icconsumerecord record1 = new Icconsumerecord();
				record1.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				record1.setOrid(orid);
				record1.setConsumeamount(need);
				record1.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
				record1.setUsid(c.getUsid());
				record1.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
				record1.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
				record1.setConsumedate(date);
				record1.setCheckoutdate(date);
				record1.setCztp("03");//03����,04���ѳ���
				record1.setXfzt("00");//�Ѿ�����
				record1.setNote(request.getNote());
				record1.setNote1(request.getThirdpartyOrid());
				record1.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
				syyfktype = 0;
				syyfk = CoreUtil.sswr2(syyfk-need);
				oneCardDao.save(record1);
			}else{
				Icconsumerecord record = new Icconsumerecord();
				record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				record.setOrid(orid);
				record.setConsumeamount(p.getMactualsaleprice());
				record.setIemployeeid(request.getIemployeeid()==null?null:Long.parseLong(request.getIemployeeid()));
				record.setUsid(c.getUsid());
				record.setIaccessequipid(Long.parseLong(request.getIaccessequipid()));
				if(syyfktype==0){
					record.setXffs("20");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
					syyfk = CoreUtil.sswr2(syyfk-p.getMactualsaleprice());
				}else{
					record.setXffs("21");//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
					syyfktype = CoreUtil.sswr2(syyfktype-p.getMactualsaleprice());
				}
				record.setConsumedate(date);
				record.setCheckoutdate(date);
				record.setCztp("03");//03����,04���ѳ���
				record.setXfzt("00");//�Ѿ�����
				record.setIcrowdkindpriceid(p.getIcrowdkindpriceid());
				record.setNote(request.getNote());
				record.setNote1(request.getThirdpartyOrid());
				oneCardDao.save(record);
			}
		}
		if(yfkneed > 0){
			Useryfk useryfk = new Useryfk();
			useryfk.setUsid(c.getUsid());  //֧���û�
			useryfk.setBdate(bdate);
			useryfk.setNote("һ��ͨԤ��������");
			useryfk.setOrderid(orid);
			useryfk.setPoint(yfkneed);
			useryfk.setYfklb(-1);
			useryfk.setYfksc("05"); // �̻�
			useryfk.setCztp(0);//0�Զ� 1�˹�
			useryfk.setSeq((int) getSeq("yfksequence"));
			oneCardDao.saveUseryfk(useryfk);
		}
		if(yfkTypeNeed > 0){
			Useryfktype useryfk = new Useryfktype();
			useryfk.setUsid(c.getUsid());  //֧���û�
			useryfk.setBdate(bdate);
			useryfk.setNote("һ��ͨ���ͽ������");
			useryfk.setOrderid(orid);
			useryfk.setPoint(yfkTypeNeed);
			useryfk.setYfklb(-1);
			useryfk.setYfksc("05"); // �̻�
			useryfk.setCztp(0);//0�Զ� 1�˹�
			useryfk.setSeq((int) getSeq("yfksequence"));
			oneCardDao.saveUseryfktype(useryfk);
		}
		////////////////////////////////////////////////////////////////////////////
		res.setCode("0000");
		res.setDescribe("���ѳɹ�!");
		res.setConsumeOrid(orid);
		double balance = oneCardDao.getUseryfkBalance(c.getUsid());
		double balanceType = oneCardDao.getUseryfktypeBalance(c.getUsid());
		try {
			map.put("balance", balance);
			map.put("balanceType", balanceType);
			//			map.put("consume", totalMont);
			res.setCustom(map);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCustom(c);
		} 
		if(!iszj) res.setHtml(
				findTradingRecordHtml(null,"���ѳɹ�","com/ectrip/sale/service/card/html/consume.html"
						,c,balance,balanceType,5));
		if("01".equals(request.getPrintReceipt()))  res.setReceipt(packageReceipt(receiptConsume, orid,null));
		return res;
	}
	private Custom findCustom(String usid,String swiping){
		Custom c = oneCardDao.getOnecardCustom(usid);
		if("01".equals(swiping)){//�����ˢ��,�����ж����޲���
			if(c == null){
				c = oneCardDao.getBukaUsid(usid);//���ܲ���
			}else{
				if(oneCardDao.isReplaceCard(c.getUsid())){//����в�����¼
					c = null;
				}
			}
		}
		return c;
	}
	public GetOrderListResponse getOrderList(GetOrderListRequest request) {
		GetOrderListResponse res = new GetOrderListResponse();
		String date1 = request.getStartDate()+ " 00:00:00";
		String date2 = request.getEndDate()+ " 23:59:59";
		try {
			if(ymdhms.parse(date2).getTime()
					-ymdhms.parse(date1).getTime()>24*60*60*1000*31l){
				res.setCode("2020");
				res.setDescribe("��Ѱ���ܳ���һ��");
				return res;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			res.setCode("2020");
			res.setDescribe("���ݲ��Ϸ�");
			return res;
		}
		Custom c =findCustom(request.getSzticketprintno(),request.getSwiping());
		////////////////////////////////////////////////////////////////
		if(c == null || c.getInote10() == null){
			res.setCode("2018");
			res.setDescribe("�û�������");
			return res;
		}
		double balance = oneCardDao.getUseryfkBalance(c.getUsid());
		double balanceType = oneCardDao.getUseryfktypeBalance(c.getUsid());
		try {
			Map map = BeanUtils.describe(c);
			map.put("balance", balance);
			map.put("balanceType", balanceType);
			res.setCustom(map);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCustom(c);
		} 
		res.setHtml(findTradingRecordHtml(null,"","com/ectrip/sale/service/card/html/custommin.html",c,balance,balanceType,5));
		res.setRecharges(oneCardDao.getRechargeHistoryByUsid(c.getUsid(), date1,date2
				));
		res.setConsumes(oneCardDao.getConsumeHistoryByUsid(c.getUsid(), 
				request.getStartDate()+ " 00:00:00", 
				request.getEndDate()+ " 23:59:59"));
		res.setCode("0000");
		res.setDescribe("��ѯ�ɹ�");
		return res;
	}
	/**
	 * 
	 * Describe:��ӡ��ʷ���Ѽ�¼
	 * @author liujianwen
	 * @return
	 * return:Response
	 * Date:2016-9-23
	 */
	public Response printHistoryConsumes(GetOrderListRequest request){
		GetOrderListResponse res = new GetOrderListResponse();
		String date1 = request.getStartDate()+ " 00:00:00";
		String date2 = request.getEndDate()+ " 23:59:59";
		try {
			if(ymdhms.parse(date2).getTime()
					-ymdhms.parse(date1).getTime()>24*60*60*1000*31l){
				res.setCode("2020");
				res.setDescribe("��ӡ���ݲ��ܳ���һ���£�");
				return res;
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
			res.setCode("2020");
			res.setDescribe("���ݲ��Ϸ�");
			return res;
		}
		Custom c =findCustom(request.getSzticketprintno(),request.getSwiping());
		////////////////////////////////////////////////////////////////
		if(c == null || c.getInote10() == null){
			res.setCode("2018");
			res.setDescribe("�û�������");
			return res;
		}
		double balance = oneCardDao.getUseryfkBalance(c.getUsid());
		double balanceType = oneCardDao.getUseryfktypeBalance(c.getUsid());
		Map map = null;
		try {
			map = BeanUtils.describe(c);
			map.put("balance", balance);
			map.put("balanceType", balanceType);
			res.setCustom(map);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCustom(c);
		} 
		List alllist = oneCardDao.getUncheckoutList(c.getUsid(),false);
		List unlist = new ArrayList();//δ���˵Ľ��
		List checkedlist = new ArrayList();//�Ѿ����˵Ľ��
		for(Object obj : alllist){
			Map m = (Map)obj;
			if("00".equals(m.get("xfzt"))){
				m.put("realamont", m.get("consumeamount"));
				m.put("timesection", m.get("consumedate").toString().substring(5, 16));
				m.put("sztickettypename", m.get("sztickettypename")+"*");
				checkedlist.add(m);
				//				sztickettypename
				//				realamont
				//				timesection
			}else{
				unlist.add(m);
			}
		}
		double uncheckout = getUncheckoutMoney(unlist);//δ���˼��˽��
		unlist.addAll(checkedlist);
		map.put("uncheckout", uncheckout);
		map.put("deposit", oneCardDao.findDeposit(c.getUsid()));//Ѻ��
		res.setCode("0000");
		res.setDescribe("��ѯ�ɹ�");
		res.setReceipt(packageReceipt(map, receiptHistoryConsumes, null, unlist));
		return res;

	}

	public Response printReceipt(PrintReceiptRequest request) {
		Response res = new Response();
		res.setReceipt(packageReceipt(null, request.getOrid(),null));
		res.setCode("0000");
		res.setDescribe("��ѯ�ɹ�");
		return res;
	}
	public Response getConsumeByOrid(PrintReceiptRequest request) {
		GetOrderListResponse res = new GetOrderListResponse();
		List<?> list = oneCardDao.getConsumeByOrid(request.getOrid(),true);
		res.setConsumes(list);
		if(list.size() > 0) res.setHtml(pachageRecordHtml("com/ectrip/sale/service/card/html/consumedetail.html", (Map<?, ?>) list.get(0)));
		res.setCode("0000");
		res.setDescribe("��ѯ�ɹ�");
		return res;
	}
	public Response getRechargeByOrid(PrintReceiptRequest request) {
		GetOrderListResponse res = new GetOrderListResponse();
		List<?> list = oneCardDao.getRechargeByOrid(request.getOrid(),true);
		res.setRecharges(list);
		if(list.size() > 0) res.setHtml(pachageRecordHtml("com/ectrip/sale/service/card/html/rechargedetail.html", (Map<?, ?>) list.get(0)));
		res.setCode("0000");
		res.setDescribe("��ѯ�ɹ�");
		return res;
	}
	/**
	 * Describe:��װ��ϸ
	 * @author liujianwen
	 * @return html
	 * return:String
	 * Date:2016-5-10
	 */
	@SuppressWarnings("rawtypes")
	private String pachageRecordHtml(String path,Map map){
		BufferedReader bf = null;
		try {
			File file = new File(getClass().getClassLoader().getResource(path).toURI());
			if(file.exists()){
				bf = new BufferedReader(new FileReader(file));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while((line = bf.readLine()) != null){
					line = line.trim();
					if(line.matches("<!--.+-->")){
						Object obj = map.get(line.substring(4, line.length()-3));
						sb.append(obj==null?"":obj);
					}else{
						sb.append(line);
					}
				}
				sb.toString();
				System.out.println(sb);
				return sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bf != null)
				try {
					bf.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return "";
	}
	/**
	 * Describe:��������
	 * @see com.ectrip.sale.service.card.service.iservice.IOneCardService#refund()
	 * @return
	 * @author liujianwen
	 * Date:2016-5-26
	 */
	public Response refund(RefundRequest request) {
		RefundResponse res = new RefundResponse();
		//////////////////////��Ʊ��Ʊ///////////////////////////////////////////////////////
		if(request.getRefundTicketOrid() != null){//��Ʊϵͳ��Ʊ
			Icconsumerecord record = oneCardDao.getTicketConsumeByThirdpartyOrid(request.getOrid());
			if(record == null){
				res.setCode("2020");
				res.setDescribe("�����Ѽ�¼!");
				return res;
			}
			List<RefundItem> refunds = new ArrayList<RefundItem>();
			RefundItem ri = new RefundItem();
			ri.setId(record.getIcconsumerecordid()+"");
			ri.setAmount(request.getRefundTicketMoney());
			ri.setAccpointzs("0");
			refunds.add(ri);
			request.setOrid(record.getOrid());
			request.setRefunds(refunds);
			///////////////////////////////////////////////
		}
		boolean isRechange = "1".equals(request.getOrid().substring(6, 7));
		double yfkcx = 0;//Ԥ����
		double yfktypecx=0;//���ͽ��
		String date = ymdhms.format(Calendar.getInstance().getTime());
		List<Icrechargerecord> recharges = new ArrayList<Icrechargerecord>();
		List<Icconsumerecord> consumes = new ArrayList<Icconsumerecord>();
		for(RefundItem t : request.getRefunds()){
			double d = CoreUtil.sswr2(Double.parseDouble(t.getAmount()));
			if(isRechange){
				double zs = CoreUtil.sswr2(Double.parseDouble(t.getAccpointzs()));
				yfkcx+=d;
				yfktypecx+=zs;
				Icrechargerecord r = (Icrechargerecord) oneCardDao.get(Icrechargerecord.class, Long.parseLong(t.getId()));
				if(d > r.getAmount() || zs > r.getAccpointzs() ||(d == 0d && zs == 0d)){
					res.setCode("2020");
					res.setDescribe("������������0�Ҳ��ܴ���ԭ���!");
					return res;
				}
				if(!r.getOrid().equals(request.getOrid())){
					res.setCode("2020");
					res.setDescribe("�����Ų�һ��!");
					return res;
				}
				Map<String,Double> map = oneCardDao.getRefundRechargeMoney(r.getIcrechargerecordid());
				if((d > 0d && CoreUtil.sswr2( r.getAmount()-d+map.get("amount")) < 0) || (zs > 0d && CoreUtil.sswr2(r.getAccpointzs()-zs+map.get("accpointzs")) <0)){
					res.setCode("2020");
					res.setDescribe("��ǰ�ѳ������ֱ�Ϊ"+(-map.get("amount"))+"��"+(-map.get("accpointzs"))+"���������ɳ���������ʧ��");
					return res;
				}
				Icrechargerecord recharge = new Icrechargerecord();//��ֵ��¼��
				//				recharge.setOrid(yfk.getOrderid());
				//				recharge.setIcrechargerecordid(getSeq("ICRechargerecord_SEQ"));
				recharge.setIemployeeid(Long.parseLong(request.getIemployeeid()));
				recharge.setIticketwinid(Long.parseLong(request.getIticketwinid()));
				recharge.setNewszticketprintno(r.getNewszticketprintno());
				recharge.setOldcardno(r.getOldcardno());
				recharge.setOldszticketprintno(r.getOldszticketprintno());
				recharge.setCardno(r.getCardno());
				recharge.setSzticketprintno(r.getSzticketprintno());//Ʊ�ż���usid
				recharge.setAccpointzs(-zs);//���ͽ��䶯
				recharge.setAmount(-d);//ʵ�ʽ��䶯,��Ϊ����
				recharge.setCztp("05");//cztp 00��ֵ,01����,02�˿�.05��ֵ����
				recharge.setZffs(r.getZffs());//֧����ʽ
				recharge.setNote(request.getNote());
				recharge.setRechargedate(date);
				recharge.setPicrechargerecordid(r.getIcrechargerecordid());
				recharge.setPiemployeeid(Long.parseLong(request.getSiemployeeid()));
				recharges.add(recharge);
			}else{
				Icconsumerecord r = (Icconsumerecord) oneCardDao.get(Icconsumerecord.class, Long.parseLong(t.getId()));
				if(d > r.getConsumeamount() || d == 0d){
					res.setCode("2020");
					res.setDescribe("������������0�Ҳ��ܴ���ԭ���!");
					return res;
				}
				if(!r.getOrid().equals(r.getOrid())){
					res.setCode("2020");
					res.setDescribe("�����Ų�һ��!");
					return res;
				}
				double ycx = oneCardDao.getRefundConsumeMoney(r.getIcconsumerecordid());
				if(CoreUtil.sswr2(r.getConsumeamount()-d+ycx) < 0){
					res.setCode("2020");
					res.setDescribe("��ǰ�ѳ������"+(-ycx)+"���������ɳ���������ʧ��");
					return res;
				}
				if("20".equals(r.getXffs())){//Ԥ��������
					yfkcx+=d;
				}else if("21".equals(r.getXffs())){
					yfktypecx+=d;
				}
				//���ʣ���ֽ���������
				Icconsumerecord record = new Icconsumerecord();
				//				record.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));//id
				//				record.setOrid(orid);
				record.setConsumeamount(-d);
				record.setIemployeeid(Long.parseLong(request.getIemployeeid()));
				record.setUsid(r.getUsid());
				record.setIaccessequipid(r.getIaccessequipid());
				record.setXffs(r.getXffs());//���ѷ�ʽ20ʵ�ʽ������,21���ͽ������
				record.setConsumedate(date);
				record.setCheckoutdate(date);
				record.setCztp("04");//03����,04���ѳ���
				record.setXfzt(r.getXfzt());
				record.setNote(request.getNote());
				record.setIcrowdkindpriceid(r.getIcrowdkindpriceid());
				record.setPicconsumerecordid(r.getIcconsumerecordid());
				record.setPiemployeeid(Long.parseLong(request.getSiemployeeid()));
				record.setIemployeeid(Long.parseLong(request.getIemployeeid()));
				record.setNote1(request.getRefundTicketOrid());//��Ʊƾ֤��
				if(!"00".equals(r.getXfzt())){;//�Ƿ��Ѿ�����
				res.setCode("2020");
				res.setDescribe("����Ŀδ����,�޷�������");
				return res;

				}
				consumes.add(record);
			}
		}
		String usid = isRechange?recharges.get(0).getSzticketprintno():consumes.get(0).getUsid();
		String orid = isRechange?createRechargeOrid():createConsumeOrid();
		yfkcx = CoreUtil.sswr2(yfkcx);
		if(yfkcx > 0d){
			if(isRechange && oneCardDao.getUseryfkBalance(usid) < yfkcx){
				res.setCode("2020");
				res.setDescribe("��������,�޷�����!");
				return res;
			}
			Useryfk yfk = new Useryfk();
			yfk.setUsid(usid);  //֧���û�
			yfk.setBdate(Tools.getTodayString());
			yfk.setNote(isRechange?"һ��ͨ��ֵ����":"һ��ͨ���ѳ���");
			yfk.setOrderid(orid);
			yfk.setPoint(yfkcx);
			yfk.setYfklb(isRechange?-1:1);
			yfk.setYfksc("05"); // �̻�
			yfk.setCztp(0);
			yfk.setSeq((int) getSeq("yfksequence"));
			oneCardDao.saveUseryfk(yfk);
			Orderpay op = new Orderpay();//ԭ��ֵ��¼��
			op.setOrid(yfk.getOrderid());
			op.setOrda(yfk.getBdate());
			op.setOrti(sdf1.format(Calendar.getInstance().getTime()));
			op.setUsid(yfk.getUsid());
			op.setMont(yfk.getPoint());
			op.setNote(yfk.getNote());
			// ��ֵ���
			op.setSclb("1"); // ��ֵ
			// ����״̬
			op.setDdzt("02");
			oneCardDao.save(op);
		}
		yfktypecx = CoreUtil.sswr2(yfktypecx);
		if(yfktypecx > 0d){
			if(isRechange && oneCardDao.getUseryfktypeBalance(usid) < yfktypecx){
				res.setCode("2020");
				res.setDescribe("��������,�޷�����!");
				return res;
			}
			Useryfktype yfkt = new Useryfktype();
			yfkt.setUsid(usid);  //֧���û�
			yfkt.setBdate(Tools.getTodayString());
			yfkt.setNote(isRechange?"һ��ͨ��ֵ����":"һ��ͨ���ѳ���");
			yfkt.setOrderid(orid);
			yfkt.setPoint(yfktypecx);
			yfkt.setYfklb(isRechange?-1:1);
			yfkt.setYfksc("05"); // �̻�
			yfkt.setCztp(0);
			yfkt.setSeq((int) getSeq("yfksequence"));
			oneCardDao.saveUseryfktype(yfkt);
		}
		for(Icrechargerecord r : recharges){
			r.setOrid(orid);
			r.setIcrechargerecordid(getSeq("ICRechargerecord_SEQ"));
			oneCardDao.save(r);
		}
		for(Icconsumerecord r : consumes){
			r.setOrid(orid);
			r.setIcconsumerecordid(getSeq("ICConsumerecord_SEQ"));
			oneCardDao.save(r);
		}
		if("01".equals(request.getPrintReceipt())) res.setReceipt(packageReceipt(isRechange?receiptRechargeRefund:receiptConsumeRefund, orid,null));
		res.setCode("0000");
		res.setDescribe("�����ɹ�");
		res.setOrid(orid);
		///////////////////////////////////////////////////////////////
		return res;
	}


}
