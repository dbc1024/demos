package com.ectrip.ec.order.common.business.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.hotel.dao.idao.IHotelDAO;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Ordercs;

public class HotelOrderCombin extends OrderCombin {

	private IHotelDAO hotelDao;
	private ITicketDAO ticketDao;

	public HotelOrderCombin(OrderCombinDTO combinDto) {
		super(combinDto);
	}

	@Override
	protected void combinToOrder(OrderCombinDTO combinDto) {
		hotelDao = (IHotelDAO) SpringUtil.getBean("hotelDao");
		ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
		Custom custom = (Custom) hotelDao.get(Custom.class,
				combinDto.getUsid());
		String ddzt = "00";
		if (combinDto.getAskOrder() != null
				&& combinDto.getAskOrder().equals("1")) {// ���뵥δ֧������״̬Ϊ24
			ddzt = "24";
		}
		this.getMorder().setOrid(combinDto.getOrid());
		this.getMorder().setOrtp("01");// Ԥ����
		this.getMorder().setUsid(custom.getUsid());
		this.getMorder().setOrda(Tools.getDays());
		this.getMorder().setOrti(Tools.getNowTime());
		this.getMorder().setDdzt(ddzt);
		this.getMorder().setMont(0.0);
		this.getMorder().setZfmont(0.0);
		this.getMorder().setTpmont(0.0);
		this.getMorder().setYhamnt(0.0);
		this.getMorder().setIsa(0l);
		this.getMorder().setIsb(0l);
		this.getMorder().setIsc(0l);
		this.getMorder().setIsd(0l);
		this.getMorder().setIse(0l);
		this.getMorder().setIsf(0l);
		this.getMorder().setIsg(0l);
		this.getMorder().setIsh(0l);
		this.getMorder().setIsi(0l);
		this.getMorder().setIsj(0l);
		this.getMorder().setIsjl(0l);
		this.getMorder().setYhamnt(0.0);
		this.getMorder().setTpfs("00");
		this.getMorder().setIsallcp(0l);
		this.getMorder().setNote(combinDto.getNote());
		HashSet groups = new HashSet();
		List<OrderProduct> products = combinDto.getProducts();
		for (int x = 0; x < products.size(); x++) {// ���ݷ����̷���
			OrderProduct tic = products.get(x);
			groups.add(tic.getIscenicid());
		}
		Iterator ite = groups.iterator();
		while (ite.hasNext()) {
			LprPojo lprpojo = null;
			Object obj = ite.next();
			Esbscenicareatab provider = (Esbscenicareatab) hotelDao.get(
					Esbscenicareatab.class, new Long(obj.toString()));
			for (LprPojo lpr : combinDto.getLprs()) {
				if (lpr.getIscenicid().equals(obj.toString())
						|| lpr.getIscenicid().equals(
								provider.getIparentid().toString())) {
					lprpojo = lpr;
				}
			}
			List<TOrderlist> tlists = new ArrayList<TOrderlist>();
			String sdate = products.get(0).getStartdate();
			String edate = products.get(0).getEnddate();
			TOrder torder = new TOrder();
			torder.setId(new TOrderId(this.getMorder().getOrid(), provider
					.getIscenicid()));
			if (lprpojo.getSzregionalid() == null
					|| lprpojo.getSzregionalid().equals("")) {
				torder.setIregionalid(1l);
			} else {
				torder.setIregionalid(new Long(lprpojo.getSzregionalid()));
			}
			torder.setDdzt(this.getMorder().getDdzt());
			torder.setUsid(combinDto.getUsid());
			torder.setOrfl("01");
			torder.setIbusinessid(custom.getIbusinessid());
			torder.setSzscenicname(provider.getSzscenicname());
			torder.setScenictype(provider.getScenictype());
			torder.setDtstartdate(sdate);
			torder.setDtenddate(edate);
			torder.setYhamnt(0.0);
			torder.setZfmont(0.0);
			torder.setMont(0.0);
			torder.setIsa(0l);
			torder.setIsb(0l);
			torder.setIsc(0l);
			torder.setIsd(0l);
			torder.setIse(0l);
			torder.setIsf(0l);
			torder.setIsg(0l);
			torder.setIsh(0l);
			torder.setIsi(0l);
			torder.setIsj(0l);
			torder.setIsjfjf(0l);
			
			if (lprpojo.getDaoyouid() != null
					&& !lprpojo.getDaoyouid().equals("")) {
				Custom daoyou = (Custom) hotelDao.get(Custom.class,
						lprpojo.getDaoyouid());
				torder.setDyusid(daoyou.getUsid());
				torder.setOrnm(daoyou.getLname());
				torder.setOrzj(daoyou.getZjlb());
				torder.setOrhm(daoyou.getZjhm());
				torder.setOrph(daoyou.getMobile());
			} else {
				torder.setOrnm(lprpojo.getDaoyou());
				torder.setNotea(lprpojo.getPassword());
				torder.setOrzj(lprpojo.getZjlb());
				torder.setOrhm(lprpojo.getZjhm());
				torder.setOrph(lprpojo.getMobile());
			}
			
			YOrder yorder = new YOrder();
			yorder.setId(new YOrderId(this.getMorder().getOrid(), torder
					.getId().getIscenicid()));
			yorder.setScenictype(provider.getScenictype());
			yorder.setMont(torder.getMont());
			yorder.setZfmont(torder.getZfmont());
			yorder.setYhamnt(torder.getYhamnt());
			yorder.setUsid(custom.getUsid());
			yorder.setDdzt(torder.getDdzt());
			yorder.setIbusinessid(custom.getIbusinessid());
			yorder.setTdlx(custom.getTtlb());
			yorder.setDtstartdate(torder.getDtstartdate());
			yorder.setDtenddate(torder.getDtenddate());
			yorder.setOrnm(torder.getOrnm());
			yorder.setOrzj(torder.getOrzj());
			yorder.setDyusid(torder.getDyusid());
			yorder.setOrhm(torder.getOrhm());
			yorder.setOrph(torder.getOrph());
			yorder.setFaxno(torder.getFaxno());
			yorder.setTpfs("00");
			yorder.setIsj(0l);
			
			String sql = " from Ordercs where byisuse=1 and ibusinessid="
					+ custom.getIbusinessid() + " order by isequence ";
			List lst = hotelDao.find(sql);
			if (lst != null && lst.size() > 0) {
				for (int k = 0; k < lst.size(); k++) {
					Ordercs ordercs = (Ordercs) lst.get(k);
					if (ordercs.getEcs().equals("ornote1")) {
						if (lprpojo.getOrnote1() != null
								&& !lprpojo.getOrnote1().equals("")) {
							torder.setOrnote1(lprpojo.getOrnote1());
						}
					} else if (ordercs.getEcs().equals("ornote2")) {
						if (lprpojo.getOrnote2() != null
								&& !lprpojo.getOrnote2().equals("")) {
							torder.setOrnote2(lprpojo.getOrnote2());
						}
					} else if (ordercs.getEcs().equals("ornote3")) {
						if (lprpojo.getOrnote3() != null
								&& !lprpojo.getOrnote3().equals("")) {
							torder.setOrnote3(lprpojo.getOrnote3());
						}
					} else if (ordercs.getEcs().equals("ornote4")) {
						if (lprpojo.getOrnote4() != null
								&& !lprpojo.getOrnote4().equals("")) {
							torder.setOrnote4(lprpojo.getOrnote4());
						}
					} else if (ordercs.getEcs().equals("ornote5")) {
						if (lprpojo.getOrnote5() != null
								&& !lprpojo.getOrnote5().equals("")) {
							torder.setOrnote5(lprpojo.getOrnote5());
						}
					} else if (ordercs.getEcs().equals("ornote6")) {
						if (lprpojo.getOrnote6() != null
								&& !lprpojo.getOrnote6().equals("")) {
							torder.setOrnote6(lprpojo.getOrnote6());
						}
					} else if (ordercs.getEcs().equals("ornote7")) {
						if (lprpojo.getOrnote7() != null
								&& !lprpojo.getOrnote7().equals("")) {
							torder.setOrnote7(lprpojo.getOrnote7());
						}
					} else if (ordercs.getEcs().equals("ornote8")) {
						if (lprpojo.getOrnote8() != null
								&& !lprpojo.getOrnote8().equals("")) {
							torder.setOrnote8(lprpojo.getOrnote8());
						}
					} else if (ordercs.getEcs().equals("ornote9")) {
						if (lprpojo.getOrnote9() != null
								&& !lprpojo.getOrnote9().equals("")) {
							torder.setOrnote9(lprpojo.getOrnote9());
						}
					} else if (ordercs.getEcs().equals("ornote10")) {
						if (lprpojo.getOrnote10() != null
								&& !lprpojo.getOrnote10().equals("")) {
							torder.setOrnote10(lprpojo.getOrnote10());
						}
					}
				}
			}
			int ii = 1;
			int zid = 1;
			String rzti = "";
			String ldti = "";
			for (OrderProduct hotel : products) {
				Edmtickettypetab tt = (Edmtickettypetab) hotelDao.get(
						Edmtickettypetab.class, hotel.getIticketid());
				Long crowkindid = hotel.getCrowdkindid()==null?1L:hotel.getCrowdkindid();
				Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) hotelDao.get(
						Edpcrowdkindtab.class, crowkindid);
				rzti = hotel.getStartdate();
				ldti = hotel.getEnddate();
				int daynum = Tools.getDayNumb(rzti, ldti) - 1;
				for (int i = 0; i < daynum; i++) {
					TOrderlist tlist = new TOrderlist();
					tlist.setId(new TOrderlistId(new Long(ii++), torder.getId().getOrid(), provider.getIscenicid()));
					tlist.setIscenicid(obj.toString());
					tlist.setItickettypeid(hotel.getIticketid());
					tlist.setDtstartdate(Tools.getDate(rzti, i));
					tlist.setDtenddate(Tools.getDate(rzti, i+1));
					tlist.setNumb(hotel.getNum());
					tlist.setIcrowdkindid(crowkindid);
					tlist.setSztickettypename(tt.getSztickettypename());
					tlist.setSzscenicname(provider.getSzscenicname());
					tlist.setSzcrowdkindname(crowdkind.getSzcrowdkindname());
					Edmcrowdkindpricetab corwdkindprice = null;
					try {
						//��ȡ�۸����
						String groupno="0000";
/*						if(custom.getIbusinessid()==1){
							groupno="0000";
						}else{
							groupno=ticketDao.searchJgfz(custom.getUsid(), provider.getIscenicid());
						}*/
						
						corwdkindprice = hotelDao.getTicketPrice(hotel.getIticketid().toString(), Tools.getDate(rzti, i), crowkindid.toString(), custom.getIbusinessid().toString(),
								groupno);
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("��ȡ��Ʒ�۸����");
					}
					tlist.setIcrowdkindpriceid(corwdkindprice.getIcrowdkindpriceid());
					tlist.setPric(corwdkindprice.getMactualsaleprice());
					tlist.setIoffersschemeid(0l);
					tlist.setYhnumb(0l);
					tlist.setYhamnt(0.0);
					tlist.setAmnt(tlist.getPric() * tlist.getNumb());
					List sontickets = ticketDao.getSonticketlist(corwdkindprice.getIcrowdkindpriceid());
					List<TZorderlist> tzlists=new ArrayList<TZorderlist>();
					for (int j = 0; j <sontickets.size(); j++) {
						//TODOľ���ж���ĩ�۰�
						Edmticketcomposepricetab sonticket = (Edmticketcomposepricetab) sontickets.get(j);
						TZorderlist zorderlist = new TZorderlist();
						zorderlist.setId(new TZorderlistId(new Long(zid++), tlist.getId().getOrderlistid(), tlist.getId().getOrid(), torder.getId().getIscenicid()));
						zorderlist.setItickettypeid(hotel.getIticketid());// ��ƱID
						zorderlist.setIztickettypeid(sonticket.getItickettypeid());// ��ƱID
						zorderlist.setIcrowdkindpriceid(sonticket.getId().getIcrowdkindpriceid());// Ʊ��ID
						zorderlist.setIcrowdkindid(crowkindid);// ��Ⱥ����
						zorderlist.setZyhamnt(0.0);
						zorderlist.setZyhnumb(new Long(0));
						zorderlist.setDtstartdate(Tools.getDate(rzti, j)+ " " + "12:00:00");
						zorderlist.setDtenddate(Tools.getDate(rzti, j+1) + " " + "12:00:00");
						zorderlist.setTripid(new Long(0));
						zorderlist.setIvenueareaid(new Long(0));
						zorderlist.setIvenueid(new Long(0));
						zorderlist.setIvenueseatsid(new Long(0));
						zorderlist.setZpric(sonticket.getMactualsaleprice());
						zorderlist.setZnumb(tlist.getNumb() * sonticket.getNumb());
						zorderlist.setZamnt(zorderlist.getZpric() * zorderlist.getZnumb());
						YZorderlist y_zordderlist = new YZorderlist();
						y_zordderlist.setId(new YZorderlistId(zorderlist.getId().getZorderlistid(), zorderlist.getId().getOrderlistid(), zorderlist.getId().getOrid(), zorderlist.getId()
								.getIscenicid()));
						y_zordderlist.setIcrowdkindpriceid(zorderlist.getIcrowdkindpriceid());
						y_zordderlist.setIcrowdkindid(zorderlist.getIcrowdkindid());
						y_zordderlist.setItickettypeid(zorderlist.getItickettypeid());
						y_zordderlist.setIztickettypeid(zorderlist.getIztickettypeid());
						y_zordderlist.setDtstartdate(zorderlist.getDtstartdate());
						y_zordderlist.setDtenddate(zorderlist.getDtenddate());
						y_zordderlist.setTripid(zorderlist.getTripid());
						y_zordderlist.setIvenueid(zorderlist.getIvenueid());
						y_zordderlist.setIvenueareaid(zorderlist.getIvenueareaid());
						y_zordderlist.setIvenueseatsid(zorderlist.getIvenueseatsid());
						y_zordderlist.setZpric(zorderlist.getZpric());
						y_zordderlist.setZnumb(zorderlist.getZnumb());
						y_zordderlist.setZyhnumb(zorderlist.getZyhnumb());
						y_zordderlist.setZyhamnt(zorderlist.getZyhamnt());
						y_zordderlist.setZamnt(zorderlist.getZamnt());
						y_zordderlist.setJsprice(zorderlist.getJsprice());
						y_zordderlist.setIsa(0l);
						y_zordderlist.setIsb(0l);
						y_zordderlist.setIsc(0l);
						y_zordderlist.setIsd(0l);
						y_zordderlist.setIse(0l);
						y_zordderlist.setIsf(0l);
						y_zordderlist.setIsg(0l);
						y_zordderlist.setIsh(0l);
						y_zordderlist.setIsi(0l);
						y_zordderlist.setIsj(0l);
						zorderlist.setYzorderlist(y_zordderlist);
						tzlists.add(zorderlist);
						this.getZorderlists().add(zorderlist);
					}
					tlist.setZorderlist(tzlists);
					tlist.setIsa(0l);
					tlist.setIsb(0l);
					tlist.setIsc(0l);
					tlist.setIsd(0l);
					tlist.setIse(0l);
					tlist.setIsf(0l);
					tlist.setIsg(0l);
					tlist.setIsh(0l);
					tlist.setIsi(0l);
					tlist.setIsj(0l);
					tlists.add(tlist);
					this.getTorderlists().add(tlist);
					YOrderlist ylist = new YOrderlist();
					ylist.setId(new YOrderlistId(tlist.getId().getOrderlistid(), getMorder().getOrid(), provider.getIscenicid()));
					ylist.setItickettypeid(tt.getItickettypeid());
					ylist.setIcrowdkindpriceid(tlist.getIcrowdkindpriceid());
					ylist.setIcrowdkindid(tlist.getIcrowdkindid());
					ylist.setDtstartdate(tlist.getDtstartdate());
					ylist.setDtenddate(tlist.getDtenddate());
					ylist.setPric(tlist.getPric());
					ylist.setNumb(tlist.getNumb());
					ylist.setAmnt(tlist.getAmnt());
					ylist.setYhamnt(tlist.getYhamnt());
					ylist.setYhnumb(tlist.getYhnumb());
					ylist.setIsa(0l);
					ylist.setIsb(0l);
					ylist.setIsc(0l);
					ylist.setIsd(0l);
					ylist.setIse(0l);
					ylist.setIsf(0l);
					ylist.setIsg(0l);
					ylist.setIsh(0l);
					ylist.setIsi(0l);
					ylist.setIsj(0l);
					tlist.setYorderlist(ylist);
					yorder.setMont(yorder.getMont() + tlist.getAmnt());
					yorder.setZfmont(yorder.getMont() - torder.getYhamnt());
					torder.setMont(torder.getMont() + tlist.getAmnt());
					torder.setZfmont(torder.getMont() - torder.getYhamnt());
				}
			}
			torder.setTorderlists(tlists);
			torder.setYorder(yorder);
			torder.setOrnote10(this.getMorder().getNote());
			this.getTorders().add(torder);
			this.getMorder().setMont(
					this.getMorder().getMont() + torder.getMont());
			this.getMorder().setZfmont(
					this.getMorder().getZfmont() + torder.getZfmont());
			this.getMorder().setStdt(sdate);
			this.getMorder().setTorders(this.getTorders());
		}
	}
}
