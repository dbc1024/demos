
package com.ectrip.ec.ticket.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.order.common.ProductTripAttr;
import com.ectrip.ec.model.ticket.FilmbookModel;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.ticket.dao.idao.IFilmDAO;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ec.ticket.service.iservice.IFilmService;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.venuemarketing.Tripprdcontroldetailtab;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.upload.model.Upfile;



public class FilmService implements IFilmService {
	private IFilmDAO filmDao;
	private ITicketDAO ticketDao;

	public List searchVenueprogram(){
		List list = this.filmDao.searchVenueprogram();
		return list;
	}
	public Map showVenueprogram(Long iprogramid) throws Exception{
		return this.filmDao.showVenueprogram(iprogramid);
	}
	public List searchfilmTrip(String date,Long ivenueid,Long iprogramid){
		return this.filmDao.searchfilmTrip(date, ivenueid, iprogramid);
	}
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate){
		return this.filmDao.searchVenueArea(iprogramid,ivenueid, itripprdcontrolid, itripid, ibusinessid, startdate);
	}
	public List searchVenueArea(Long iprogramid,Long ivenueid, Long itripprdcontrolid,
			Long itripid, Long ibusinessid, String startdate,String groupno){
		return this.filmDao.searchVenueArea(iprogramid,ivenueid, itripprdcontrolid, itripid, ibusinessid, startdate,groupno);
	}
	/**
	 * ��ѯ��Ŀ��������ϸ
	 * 
	 * @param date
	 *            ��ѯ����
	 * @param iscenicid
	 *            �����̱��
	 */
	public List searchFilm(String date, Long iscenicid) {
		List list = filmDao.searchFilm(date, iscenicid);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				List flist = filmDao.filmTripList(date,
						Long.parseLong(map.get("iprogramid").toString()));
				if (date.equals(Tools.getTodayString())) {
					for (int j = 0; j < flist.size(); j++) {
						Map map1 = (Map) flist.get(j);
						List tlist = (List) map1.get("tripdetail");
						List ttlist = new ArrayList();
						for (int k = 0; k < tlist.size(); k++) {
							Tripprdcontroldetailtab tr = (Tripprdcontroldetailtab) tlist
									.get(k);
							try {
								String starttime = Tools.getTodayString() + " "
										+ tr.getStarttime() + ":00";
								SimpleDateFormat df = new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss");
								Date d1 = df.parse(starttime);
								Calendar begcalendar = Calendar.getInstance();
								begcalendar.setTime(d1);
								Calendar now = Calendar.getInstance(TimeZone
										.getTimeZone("GMT+08:00"));
								if (now.before(begcalendar)) {
									ttlist.add(tr);
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						map1.put("tripdetail", ttlist);
					}
				}
				map.put("trip", flist);

			}
		}
		return list;
	}
	
	public boolean checkSeatStatus(List filmproduct) {
		if (filmproduct != null && !filmproduct.isEmpty()) {
			for (int i = 0; i < filmproduct.size(); i++) {
				FilmbookModel f = (FilmbookModel) filmproduct.get(i);
				String hsql = "from Seatstatustab t where t.id.ivenueid="
						+ f.getIvenueid() + " and t.id.ivenueareaid="
						+ f.getIvenueareaid() + " and t.id.itripid="
						+ f.getItripid() + " and t.id.iseatid=" + f.getSeatid()
						+ " and t.id.startdate='" + f.getTourdate() + "'";
				List list = this.filmDao.find(hsql);
				if(list!=null && !list.isEmpty()){
					return false;
				}else{
					return true;
				}
			}
		}
		return true;
	}
	public List getFilmSeatPrice(Long productid, Long ivenueareaid,
			Long itripprdcontrolid, String usid, String date) {
		Custom custom = (Custom) filmDao.get(Custom.class, usid);
		Edmtickettypetab ticket = (Edmtickettypetab) ticketDao.get(Edmtickettypetab.class, productid);
		Esbscenicareatab scen = (Esbscenicareatab) ticketDao.get(Esbscenicareatab.class, ticket.getIscenicid());
		String groupno = ticketDao.searchJgfz(usid, scen.getIscenicid());
		List list = ticketDao.getPriceList(productid.toString(), custom
				.getIbusinessid().toString(), date, groupno);
		return list;
	}

	/**
	 * ��ѯ�н�Ŀ�ķ�����
	 */
	public List filmProvider(String date) {
		System.out.println("filmProvider1");
		return filmDao.filmProviderList(date);
	}

	/**
	 * ��ѯ��Ŀ�����б�
	 */
	public List filmTripList(String date, Long iprogramid) {
		return filmDao.filmTripList(date, iprogramid);
	}
	/**
	 * �����������ݵĲ���,��λ״̬��3��,�˷������3�� action�п��ܻ����һ����ѡ��ת̨Ϊ2 1:������ 0:δ���� -1 δ����
	 */
	public List filmSeatSaleStatus(String date, Long ivenueid,Long ivenueareaid, Long tripid) {
		Long stime = System.currentTimeMillis();
		List statuslist = new ArrayList();
		try {
			Venue seat = (Venue) filmDao.get(Venue.class, ivenueid);
			List seatlist = filmDao.venueSeats(ivenueid,ivenueareaid);
			List saledSeat = filmDao.filmSeatSaleStatu(date, ivenueid, ivenueareaid,tripid);
			
			// ��ȡ�����е���λ
			for (int x = 0; x < seatlist.size(); x++) {
				if (saledSeat.size() == 0) {// ���û���۳�����λֱ������
					break;
				}
				Map s = (Map) seatlist.get(x);
				for (int y = 0; y < saledSeat.size(); y++) {// ���۳�����λƥ����λ�ţ�������λ״̬
					Map saled = (Map) saledSeat.get(y);
					if (s.get("ivenueseatsid").toString()
							.equals(saled.get("iseatid").toString())) {
						s.put("status", saled.get("status"));
						break;
					}
				}
				if (!s.containsKey("status")) {
					s.put("status", 0);
				}
			}
			statuslist = seatlist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuslist;
	}
	/**
	 * �����������ݵĲ���,��λ״̬��3��,�˷������3�� action�п��ܻ����һ����ѡ��ת̨Ϊ2 1:������ 0:δ���� -1 δ����
	 */
	public List filmSeatSaleStatus(String date, Long ivenueid, Long tripid) {
		Long stime = System.currentTimeMillis();
		List statuslist = new ArrayList();
		try {
			Venue seat = (Venue) filmDao.get(Venue.class, ivenueid);
			List seatlist = filmDao.venueSeats(ivenueid);
			List saledSeat = filmDao.filmSeatSaleStatu(date, ivenueid, tripid);
			// ��ȡ�����е���λ
			for (int x = 0; x < seatlist.size(); x++) {
				Map s = (Map) seatlist.get(x);
				if (saledSeat.size() == 0) {// ���û���۳�����λֱ������
					break;
				}
				for (int y = 0; y < saledSeat.size(); y++) {// ���۳�����λƥ����λ�ţ�������λ״̬
					Map saled = (Map) saledSeat.get(y);
					if (s.get("ivenueseatsid").toString()
							.equals(saled.get("iseatid").toString())) {
						s.put("status", saled.get("status").toString());
						saledSeat.remove(saled);
					}
				}
			}
			// ��װ�������ݲ���,���ݳ��ݵ���Ϊ����װ
			for (int row = 1; row <= seat.getIrowscount(); row++) {
				Map first = null;
				// ����λ����������Ѿ�ȫ���Ƴ���,��������ûѭ���꣬˵���������б�ɾ.
				if (seatlist == null || seatlist.size() == 0) {
					for (int col = 1; col <= seat.getIcolumnscount(); col++) {
						Map m = new HashMap();
						m.put("icolumnserialnum", col);
						m.put("irowserialnum", row);
						m.put("status", -1);
						statuslist.add(m);
					}
					break;
				} else {
					first = (Map) seatlist.get(0);
				}
				// ѭ����
				if (Integer.parseInt(first.get("irowserialnum").toString()) == row) {
					int frontnum = 1;// ÿ�е��ж��Ǵ�1��ʼ,֮���¼ǰһ����λ��.
					while (seatlist.size() > 0) {
						Map chamap = (Map) seatlist.get(0);
						// ��λ���������ź����.���Կ���˳��Ƚ������ģ�Ȼ��ȫ
						int seatrow = Integer.parseInt(chamap.get(
								"irowserialnum").toString());
						// �����ǰ��λ��������ѭ���������Բ��Ͼ�������
						if (seatrow != row) {
							break;
						}
						// ��ȡ��λ�е�һ����λ���,������Ǵ�1��ʼ��ȫ
						for (int c = 1; c <= (Integer.parseInt(chamap.get(
								"icolumnserialnum").toString()))
								- frontnum; c++) {
							Map map = new HashMap();
							map.put("icolumnserialnum", c);
							map.put("irowserialnum", row);
							map.put("status", -1);
							statuslist.add(map);
						}
						// ����Ϊ��ǰ��λ���
						frontnum = Integer.parseInt(chamap.get(
								"icolumnserialnum").toString()) + 1;
						if (chamap.get("status") == null) {
							chamap.put("status", 0);
						}
						// ��������λ�б�
						statuslist.add(chamap);
						// Ϊ�˱�֤��һ����λ��֮ǰû�������
						seatlist.remove(chamap);
					}
					frontnum -= 1;
					while (seat.getIcolumnscount() - frontnum > 0) {
						Map map = new HashMap();
						map.put("icolumnserialnum", ++frontnum);
						map.put("irowserialnum", row);
						map.put("status", -1);
						statuslist.add(map);
					}
				} else {
					for (int i = 1; i <= seat.getIcolumnscount(); i++) {
						Map map = new HashMap();
						map.put("icolumnserialnum", i);
						map.put("irowserialnum", row);
						map.put("status", -1);
						statuslist.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statuslist;
	}

	public OrderCombinDTO combinationOrder(String orid, List filmproduct,
			Custom custom, LprPojo lpr) {
		List<FilmbookModel> combinList = new ArrayList<FilmbookModel>();
		HashSet groups = new HashSet();
		for (int i = 0; i < filmproduct.size(); i++) {
			FilmbookModel f = (FilmbookModel) filmproduct.get(i);
			if (combinList.size() < 1) {
				FilmbookModel f1 = new FilmbookModel();
				try {
					BeanUtils.copyProperties(f1, f);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("����ת������" + e.getMessage());
				}
				f1.setSeatids(f1.getSeatids()
						+ String.valueOf(f1.getSeatid().intValue()));
				combinList.add(f1);

			} else {
				boolean added = false;
				for (FilmbookModel cf : combinList) {
					if (f.sameFilmtripCrow().equals(cf.sameFilmtripCrow())) {
						cf.setNumb(cf.getNumb() + f.getNumb());
						if (f.getSeatid() != null
								&& f.getSeatid().intValue() > 0) {
							cf.setSeatids(cf.getSeatids() + ","
									+ f.getSeatid().intValue());
						}
						added = true;
						break;
					}
				}
				if (!added) {
					FilmbookModel f1 = new FilmbookModel();
					try {
						BeanUtils.copyProperties(f1, f);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("����ת������" + e.getMessage());
					}
					f1.setSeatids(f1.getSeatids()
							+ String.valueOf(f1.getSeatid().intValue()));
					combinList.add(f1);
				}
			}
			groups.add(f.getIscenicid());
		}
		List<LprPojo> lprList = new ArrayList<LprPojo>();
		Iterator ite = groups.iterator();
		try {
			while (ite.hasNext()) {
				Object obj = ite.next();
				LprPojo newlpr = new LprPojo();
				BeanUtils.copyProperties(newlpr, lpr);
				newlpr.setIscenicid(obj.toString());
				lprList.add(newlpr);
			}
		} catch (Exception e) {
			throw new RuntimeException("����ת���쳣:" + e.getMessage());
		}
		OrderCombinDTO dto = new OrderCombinDTO();
		dto.setOrid(orid);
		dto.setUsid(custom.getUsid());
		dto.setIbusiness(custom.getIbusinessid());
		dto.setGroupno(custom.getNote2());
		dto.setAskOrder("0");
		dto.setLprs(lprList);
		List<OrderProduct> productlist = new ArrayList<OrderProduct>();
		dto.setProducts(productlist);
		List<ProductTripAttr> triplist = new ArrayList<ProductTripAttr>();
		dto.setTriplist(triplist);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (LprPojo nl : lprList) {
			String startdate = "";
			for (int i = 0; i < combinList.size(); i++) {
				FilmbookModel pojo = (FilmbookModel) combinList.get(i);
				if (Integer.parseInt(nl.getIscenicid()) == pojo.getIscenicid()
						.intValue()) {
					if (startdate.equals("")) {
						startdate = pojo.getTourdate();
					} else {
						try {
							Date d1 = sdf.parse(startdate);
							Date d2 = sdf.parse(pojo.getTourdate());
							if (d2.before(d1)) {
								startdate = pojo.getTourdate();
							}
						} catch (ParseException e) {
							throw new RuntimeException("����ת���쳣:"
									+ e.getMessage());
						}
					}
					OrderProduct product = new OrderProduct();
					product.setIticketid(pojo.getIticketid());
					Edmtickettypetab ticket = (Edmtickettypetab) ticketDao.get(
							Edmtickettypetab.class, product.getIticketid());
					Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) ticketDao
							.get(Edmcrowdkindpricetab.class,
									pojo.getIcrowdkindpriceid());
					product.setIscenicid(pojo.getIscenicid());
					product.setStartdate(pojo.getTourdate());
					product.setEnddate(Tools.getDate(product.getStartdate(),
							ticket.getValidityday().intValue()));
					product.setCrowdkindid(price.getIcrowdkindid());
					product.setNum(new Long(pojo.getNumb()));
					if (pojo.getIprogramid() != null
							&& pojo.getIprogramid().intValue() > 0) {
						ProductTripAttr attr = new ProductTripAttr();
						attr.setProduct(product);
						attr.setIvenueid(pojo.getIvenueid());
						attr.setIvenueareaid(pojo.getIvenueareaid());
						attr.setTripdate(pojo.getTourdate());
						attr.setTripid(pojo.getItripid());
						attr.setTripticketid(pojo.getIticketid());
						attr.setFilmid(pojo.getIprogramid());
						if (pojo.getSeatids() != null
								&& !pojo.getSeatids().equals("")) {
							attr.setSeatids(pojo.getSeatids());
						} else {
							// TODO ���������λ
						}
						triplist.add(attr);
					}
					productlist.add(product);
					combinList.remove(pojo);
					i--;
				}
			}
			nl.setRzti(startdate);
		}

		return dto;
	}
	/**
	 * �Զ�ѡ��Ԥ��
	 * @param date	Ԥ������
	 * @param ivenueid	����ID
	 * @param ivenueareaid	��������ID
	 * @param tripid	�ݳ�ID
	 * @param bookNum	Ԥ������
	 * @param priceId	��Ʒ�۸�ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<?> autoChooseRandomSeat(String date, Long ivenueid, Long ivenueareaid, Long tripid, Long bookNum) {
		//{ivenueseatsid=1, icolumnserialnum=1, seq=2855, szvenueseatsname=1��45��, ivenueareaid=27, byisuse=1, szvenueseatscode=45, irowserialnum=1}
		//{ivenueid=4, ivenueareaid=27, itripid=4, startdate=2014-12-03, status=1, iseatid=1}
		List choosed = new ArrayList();
		try {
			//������������λ
			List<?> seatlist = filmDao.venueSeats(ivenueid, ivenueareaid);
			//��������������λ
			List saledSeat = filmDao.filmSeatSaleStatu(date, ivenueid, ivenueareaid, tripid);
			//����λ��Ϣ���黯
			long idxrow = 0;
			List allrows = new ArrayList();//��λ�����б�
			List cols1row = null;
			List allrowstemp = new ArrayList();//��λ�ֲ�����
			List cols1rowtemp = null;
			for (int i=0;i<seatlist.size();i++) {
				Map map1 = (HashMap) seatlist.get(i);
				long iseatid =  (Long) map1.get("ivenueseatsid");
				for (int j=0;j<saledSeat.size();j++) {
					Map map2 = (HashMap) saledSeat.get(j);
					long iseatid1 =  (Long) map2.get("iseatid");
					if (iseatid1==iseatid) {
						map1.put("byisuse", 2L);//��������  Ĭ��1���� 0��λ�쳣
						break;
					}
				}
				long row1 = (Long) map1.get("irowserialnum");
				long byisuse1 =  (Long) map1.get("byisuse");
				if (row1 != idxrow) {
					if (cols1row!=null) {
						allrows.add(cols1row);
						allrowstemp.add(StringUtils.join(cols1rowtemp.toArray()));
					}
					cols1row = new ArrayList();
					cols1rowtemp = new ArrayList();
					idxrow = row1;
				}
				cols1row.add(map1);
				cols1rowtemp.add(byisuse1+"");
			}
			if (cols1row.size()>0) {
				allrows.add(cols1row);
			}
			if (cols1rowtemp.size()>0) {
				allrowstemp.add(StringUtils.join(cols1rowtemp.toArray()));
			}
			String strwithsplit = StringUtils.join(allrowstemp.toArray(), "#");//#�ָ����λ�����ַ���11110112022#222211111111#111111111
			String strwithoutsplit = StringUtils.join(allrowstemp.toArray());//���ָ����λ�����ַ���   11110112022222211111111111111111
			int count = strwithoutsplit.length()-strwithoutsplit.replaceAll("1", "").length();
			if (count<bookNum) {
				throw new Exception("��Ԥ����λ"+count+"��,��������");
			}
			List allseat = new ArrayList();//������λ����
			for (int i=0;i<allrows.size();i++) {
				allseat.addAll((List) allrows.get(i));
			}
			//������λ����������������ѡ��
			int booked = -1;
			int idx = 0;
			while (true) {
				if (booked==-1) {
					booked = bookNum.intValue();
				}
				if (booked==0) {
					break;
				}
				StringBuffer sb = new StringBuffer();
				for (int i=booked; i>0; i--) {
					sb.append("1");
				}
				String bookstr = sb.toString();
				if ((idx=strwithsplit.indexOf(bookstr))!=-1) {//������λ����1���ڿɷ���
					idx = strwithsplit.substring(0, idx).replaceAll("#", "").length();
					break;
				} else if ((idx=strwithoutsplit.indexOf(bookstr))!=-1) {//�ɿ�������,ֱ�Ӿ�ȷ������ʼλ��
					break;
				} else {//�޷���������
					booked--;
				}
			}
			for (int i=idx;i<idx+booked;i++) {
				choosed.add(allseat.get(i));
			}
			int left = bookNum.intValue()-booked;
			for (int i=0;i<left;i++) {
				for (int j=0;(j<idx||j>idx+booked)&&j<allseat.size();j++) {
					if (strwithoutsplit.charAt(j)=='1') {//���У����Լ���
						choosed.add(allseat.get(j));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return choosed;
	}
	public IFilmDAO getFilmDao() {
		return filmDao;
	}

	public void setFilmDao(IFilmDAO filmDao) {
		this.filmDao = filmDao;
	}

	public ITicketDAO getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ITicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

	public Long getFilmProduct(Long ivenueareaid, Long itripprdcontrolid) {
		return filmDao.getFilmProduct(ivenueareaid, itripprdcontrolid);
	}

	public List venuAreaList(Long ivenueid) {
		return filmDao.venuAreaList(ivenueid);
	}
	public Upfile getVenuePicture(Long ivenueid) {
		// TODO Auto-generated method stub
		return filmDao.getVenuePicture(ivenueid);
	}
}

