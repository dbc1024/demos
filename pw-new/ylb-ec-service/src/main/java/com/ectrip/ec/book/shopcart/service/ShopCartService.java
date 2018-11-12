package com.ectrip.ec.book.shopcart.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.shopcart.dao.idao.IShopCartDAO;
import com.ectrip.ec.book.shopcart.service.iservice.IShopCartService;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.OrderInfo;
import com.ectrip.ec.model.order.Shopcart;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

@Service
public class ShopCartService implements IShopCartService {
	private IShopCartDAO shopcartDao;
	private ITicketDAO ticketDao;
	
	public ITicketDAO getTicketDao() {
		return ticketDao;
	}

	public void setTicketDao(ITicketDAO ticketDao) {
		this.ticketDao = ticketDao;
	}

	public IShopCartDAO getShopcartDao() {
		return shopcartDao;
	}

	public void setShopcartDao(IShopCartDAO shopcartDao) {
		this.shopcartDao = shopcartDao;
	}

	/**
	 * ���ӹ��ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopcart
	 * @return return:String Date:2012-2-1
	 */
	public String addShopCart(Shopcart shopcart) {
		return shopcartDao.addShopCart(shopcart);
	}

	/**
	 * �޸Ĺ��ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopcart
	 *            return:void Date:2012-2-1
	 */
	public void updateShopCart(Shopcart shopcart) {
		shopcartDao.updateShopCart(shopcart);
	}

	/**
	 * ���ݱ��ɾ�����ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopId Ҫɾ���Ĳ�Ʒ�������
	 *            return:void Date:2012-2-1
	 */
	public void deleteShopCart(Long[] shopId) {
		shopcartDao.deleteShopCart(shopId);
	}

	/**
	 * �����û�ɾ�����ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid �û���
	 * @param shopseq ���ﳵ���
	 *            (����ɾ������ʱ������0) return:void Date:2012-2-2
	 */
	public void deleteShopCartByUser(String usid, Long shopseq) {
		shopcartDao.deleteShopCartByUser(usid, shopseq);
	}

	/**
	 * ɾ�����ڹ��ﳵ Describe:���û���¼ʱ��usid��Ϊ�գ�cookieidΪ�գ���δ��¼ʱ��usidΪ�գ�cookieid����Ϊ��
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 *            �û���
	 * @param cookieid
	 *            return:void Date:2012-2-2
	 */
	public void deleteShopCartByTime(String usid, String cookieid) {
		shopcartDao.deleteShopCartByTime(usid, cookieid);
	}

	/**
	 * �õ����ﳵ�б��û���¼ʱ�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid �û���
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ��
	 * @param url ·��
	 * @return return:PaginationSupport Date:2012-2-1
	 */
	public PaginationSupport queryShopCartListByUser(String usid, int page,
			int pageSize, String url) {
		return shopcartDao.queryShopCartListByUser(usid, page, pageSize, url);
	}

	/**
	 * �õ����ﳵ�б��û�δ��¼ʱ�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopSeq
	 *            ���ﳵ�������
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ��
	 * @param url ·��
	 * @return return:PaginationSupport Date:2012-2-1
	 */
	public PaginationSupport queryShopCartList(Long[] shopSeq, int page,
			int pageSize, String url) {
		return shopcartDao.queryShopCartList(shopSeq, page, pageSize, url);
	}

	/**
	 * ��ȡ�����б� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param seq ���ﳵ�������
	 *            ��δ��¼��
	 * @param usid �û���
	 *            ����¼ʱ�ã�
	 * @return return:List Date:2012-2-4
	 */
	public List queryShopList(Long[] seq, String usid) {
		return shopcartDao.queryShopList(seq, usid);
	}

	/**
	 * ��ȡ�����б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param Seq ���ﳵ������飨δ��¼��
	 * @param usid �û�������¼ʱ�ã�
	 * @param id ��ǰ��Ʒ��id
	 * @return
	 * return:List
	 * Date:2012-2-4
	 *//*
	public List queryShopList(Long[] seq, String usid,Long id){
		return shopcartDao.queryShopList(seq, usid,id);
	}*/
	
	/**
	 * ��ѯ��Ʒÿ��۸� (non-Javadoc) Describe:�Ƶ�Ҫ������δ��
	 * 
	 * @auth:huangyuqi
	 * @param itickettypeId ��Ʒ���
	 * @param providertype ����������
	 * @param rzti ��ʼ����
	 * @param ldti ��������
	 * @param lgtp
	 *            �û����ͣ���δ��¼�������
	 * @return return:List Date:2012-2-3
	 */
	public List getPriceHotel(Long itickettypeId, String providertype,
			String rzti, String ldti, String lgtp, Long icrowdkindpriceid) {
		return shopcartDao.getPriceHotel(itickettypeId, providertype, rzti,
				ldti, lgtp, icrowdkindpriceid);
	}

	/**
	 * ��ѯ�Ӳ�Ʒ�۸� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param priceId
	 * @param sontickettypeId
	 * @return return:Edmticketcomposepricetab Date:2012-2-20
	 */
	public Edmticketcomposepricetab querySonPrice(Long priceId,
			Long sontickettypeId) {
		return shopcartDao.querySonPrice(priceId, sontickettypeId);
	}

	/**
	 * * Describe:����ʵ����
	 * 
	 * @see com.ectrip.book.shopcart.service.iservice.IShopCartService#saveRealname(java.util.List)
	 * @param list
	 * @author chenxinhao Date:2012-10-16
	 */
	public void saveRealname(List<TRealname> list) {
		shopcartDao.saveRealname(list);
	}

	public List getPriceHotel(Long itickettypeId, String providertype,
			String rzti, String ldti, String lgtp) {
		return shopcartDao.getPriceHotel(itickettypeId, providertype, rzti,
				ldti, lgtp);
	}

	/*
	 * ��ѯ������(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.book.shopcart.service.iservice.IShopCartService#checkPv(java
	 * .lang.Long, java.lang.String)
	 */
	public int checkPv(Long iscenicid, String usid) {
		return shopcartDao.checkPv(iscenicid, usid);
	}

	/*
	 * ��ѯ�۸�(non-Javadoc)
	 * 
	 * @see
	 * com.ectrip.book.shopcart.service.iservice.IShopCartService#checkPrice
	 * (java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String,
	 * java.lang.String)
	 */
	public Double checkPrice(Long itickettypeid, Long ibusinessid,
			Long icrowdkindid, String rzti, String ldti, String note1) {
		return shopcartDao.checkPrice(itickettypeid, ibusinessid, icrowdkindid,
				rzti, ldti, note1);
	}

	public double fillOrderInfo(List<OrderInfo> orderinfos, int ibusinessid) {
		double xjcount = 0;
		if (orderinfos != null && orderinfos.size() > 0) {
			for (int i = 0; i < orderinfos.size(); i++) {
				OrderInfo info = orderinfos.get(i);
				String playtime = info.getPlaytime();
				if (info.getNum() == null || info.getNum() < 1) {
					orderinfos.remove(info);
					i--;
					continue;
				} else {
					Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.shopcartDao
							.get(Edpcrowdkindtab.class, info.getIcrowdkindid());
					info.setStrcrowd(crowdkind.getSzcrowdkindname());
					Edmtickettypetab ticket = (Edmtickettypetab) this.shopcartDao
							.get(Edmtickettypetab.class,
									info.getItickettypeid());
					info.setSztickettypename(ticket.getSztickettypename());
					Esbscenicareatab provider = (Esbscenicareatab) this.shopcartDao
							.get(Esbscenicareatab.class, ticket.getIscenicid());
					info.setSzscenicname(provider.getSzscenicname());
					info.setSzcrowdkindname(crowdkind.getSzcrowdkindname());
					info.setIscenicid(ticket.getIscenicid());
					if (playtime == null || playtime.equals("")) {
						SimpleDateFormat sf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						try {
							if (sf.parse(Tools.getDayTimes())
									.before(sf.parse(Tools.getDays() + " "
											+ provider.getSzlasttime() + ":00"))) {
								info.setPlaytime(Tools.getDays());
							} else {
								info.setPlaytime(Tools.getDate(Tools.getDays(),
                                        1));
							}
						} catch (ParseException e) {
							info.setPlaytime(Tools.getDate(Tools.getDays(), 1));
						}
					} else {
						info.setPlaytime(playtime);
					}
					Double price = shopcartDao.getPrice(
							info.getItickettypeid(), new Long(ibusinessid),
							info.getIcrowdkindid(), info.getPlaytime(),
							info.getPlaytime(), "0000");
					if (price != null) {
						info.setPrice(price);
						info.setStatus(true);
					} else {
						info.setPrice(price);
						info.setStatus(false);
					}
					// �����Ż�
					Edpofferschemetab scheme = shopcartDao.getScheme(
							info.getIscenicid(), new Long(ibusinessid),
							info.getIcrowdkindid(), info.getItickettypeid(),
							info.getPlaytime());

					if (scheme != null) {
						System.out.println("scheme="
								+ scheme.getSzoffersschemename());
						info.setIoffersschemeid(scheme.getIoffersschemeid());
						info.setYhnumb(info.getNum() / scheme.getImultiples()
								* scheme.getIoffernum());
						info.setXjmoney(info.getPrice()
								* (info.getNum() - info.getYhnumb()));
						System.out.println("yhnumb=" + info.getYhnumb());

					} else {
						info.setIoffersschemeid(0L);
						info.setYhnumb(0L);
						info.setXjmoney(info.getPrice() * info.getNum());
						System.out.println("yhnumb=" + info.getYhnumb());
					}
				}
				xjcount = xjcount + info.getXjmoney();
			}
		}
		return xjcount;
	}

	public boolean syncPrice(Long itickettypeid, Long icrowdkindid,
			Long ibusinessid, String playtime) {
		String hsql = "select new map(b.b2cid as b2cid) from B2bsyncinfotab b,Edmcrowdkindpricetab p where b.b2cid=p.icrowdkindpriceid and b.sztype='03' and p.icrowdkindid="
				+ icrowdkindid
				+ " and p.ibusinessid="
				+ ibusinessid
				+ " and p.itickettypeid="
				+ itickettypeid
				+ " and p.startdata<='"
				+ playtime
				+ "' and p.enddata>='"
				+ playtime + "'";
		System.out.println(hsql);
		List list = this.shopcartDao.find(hsql);
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	public List<OrderInfo> fillOrderInfo(List<OrderInfo> orderinfos, int ibusinessid,
			String fz) {
		if (orderinfos != null && orderinfos.size() > 0) {
			for (int i = 0; i < orderinfos.size(); i++) {
				OrderInfo info = orderinfos.get(i);
				String playtime = info.getPlaytime();
				if (info.getNum() == null || info.getNum() < 1) {
					orderinfos.remove(info);
					i--;
					continue;
				} else {
					Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.shopcartDao
							.get(Edpcrowdkindtab.class, info.getIcrowdkindid());
					info.setStrcrowd(crowdkind.getSzcrowdkindname());
					Edmtickettypetab ticket = (Edmtickettypetab) this.shopcartDao
							.get(Edmtickettypetab.class,
									info.getItickettypeid());
					info.setSztickettypename(ticket.getSztickettypename());
					Esbscenicareatab provider = (Esbscenicareatab) this.shopcartDao
							.get(Esbscenicareatab.class, ticket.getIscenicid());
					info.setSzscenicname(provider.getSzscenicname());
					info.setSzcrowdkindname(crowdkind.getSzcrowdkindname());
					info.setIscenicid(ticket.getIscenicid());
					if (playtime == null || playtime.equals("")) {
						SimpleDateFormat sf = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						try {
							if (sf.parse(Tools.getDayTimes())
									.before(sf.parse(Tools.getDays() + " "
											+ provider.getSzlasttime() + ":00"))) {
								info.setPlaytime(Tools.getDays());
							} else {
								info.setPlaytime(Tools.getDate(Tools.getDays(),
                                        1));
							}
						} catch (ParseException e) {
							info.setPlaytime(Tools.getDate(Tools.getDays(), 1));
						}
					} else {
						info.setPlaytime(playtime);
					}
					Edmcrowdkindpricetab price = shopcartDao.getPriceModel(
							info.getItickettypeid(), new Long(ibusinessid),
							info.getIcrowdkindid(), info.getPlaytime(),
							info.getPlaytime(), fz);
					if (price != null) {
						info.setPriceId(price.getIcrowdkindpriceid());
						info.setPrice(price.getMactualsaleprice());
						info.setStatus(true);
						if(price.getIpeoplenumrange() != null && price.getIpeoplenumrange() == 1L){
							info.setRealName(true);
						}else{
							info.setRealName(false);
						}
					} else {
						info.setPriceId(null);
						info.setPrice(null);
						info.setStatus(false);
						info.setRealName(false);
					}
					
					info.setIoffersschemeid(0L);
					info.setImultiples(0L);
					info.setIoffernum(0L);
					info.setYhnumb(0L);
					if(info.isStatus()){
						info.setXjmoney(info.getPrice() * info.getNum());
					}else{
						info.setXjmoney(null);
					}
				}

			}
		}
		return orderinfos;
	}
	
	/**
	 * 
	 * Describe:�ж�ɢ�Ͷ�������
	 * @author:chenxinhao
	 * @param type
	 * @param firstdate
	 * @param lastdate
	 * @return
	 * return:boolean
	 * Date:2015-1-26
	 */
	public int check(String type,String firstdate,String lastdate,LprPojo lpr){
		String hsql = "select distinct m.orid from TOrder t,MOrder m,TOrderlist tl where t.id.orid = m.orid and m.orid = tl.id.orid and m.ddzt in ('01','02','11') and t.orhm = '"+lpr.getZjhm()+"' ";
		if(type!=null && !"".equals(type)){
			if(type.equals("00")){//��������
				hsql+=" and m.orda>='"+firstdate+"' and m.orda <='"+lastdate+"' ";
			}
			if(type.equals("01")){//�·ݿ���
				hsql+= " and substr(m.orda,0,7)>='"+firstdate+"' and substr(m.orda,0,7)<='"+lastdate+"'";
			}
			List list = this.shopcartDao.find(hsql);
			System.out.println(hsql);
			if(list!=null && !list.isEmpty()){
				String sql = "select distinct m.orid from TOrder t,MOrder m where t.id.orid = m.orid and m.ddzt = '00' and m.orda = '"+ Tools.getDays()+"' and t.orhm = '"+lpr.getZjhm()+"' ";
				List list2 = this.shopcartDao.find(sql);
				if(list2!=null && !list2.isEmpty()){
					return list.size()+list2.size();
				}
				return list.size();
			}else{
				String sql = "select distinct m.orid from TOrder t,MOrder m where t.id.orid = m.orid and m.ddzt = '00' and m.orda = '"+ Tools.getDays()+"' and t.orhm = '"+lpr.getZjhm()+"' ";
				List list2 = this.shopcartDao.find(sql);
				if(list2!=null && !list2.isEmpty()){
					return list2.size();
				}
			}
		}
		return 0;
	}
	
	/**
	 * *
	 * Describe:��ѯɢ��Ԥ���ķ�����
	 * @param iscenicid
	 * @return
	 * @author huying
	 * Date:2015-4-8
	 */
	public List findList(Long iscenicid,Long ibusinessid) {
		String hql=" from Esbprovicerq where ibusinessid="+ibusinessid+" and  iscenicid="+iscenicid ;
		List list=this.shopcartDao.find(hql);
		return list;
	}
	
	public List getTorderlist(String usid,Long itickettypeid,Long icrowdkindpriceid,Long icrowdkindid){
		String hsql = "select new map(t.id.orid as orid) from TOrderlist t,TOrder td where t.itickettypeid="+itickettypeid+" and t.icrowdkindpriceid="+icrowdkindpriceid+" and t.icrowdkindid="+icrowdkindid+" and t.id.orid = td.id.orid and td.usid = '"+usid+"' and td.ddzt!='98' ";
		List list = this.shopcartDao.find(hsql);
		return list;
	}

    public void updateOrderDdzt(String orid,String ddzt){
        String sql = "from MOrder m where m.orid = '"+orid+"'";
        List list = this.shopcartDao.find(sql);
        if(list != null && !list.isEmpty()){
            for(int i = 0;i < list.size();i++){
                MOrder morder = (MOrder) list.get(i);
                morder.setDdzt(ddzt);
                this.shopcartDao.update(morder);
            }
        }
        sql = "from TOrder t where t.id.orid = '"+orid+"' ";
        list = this.shopcartDao.find(sql);
        if(list != null && !list.isEmpty()){
            for(int i = 0;i < list.size();i++){
                TOrder torder = (TOrder) list.get(i);
                torder.setDdzt(ddzt);
                this.shopcartDao.update(torder);
            }
        }
        sql = "from YOrder y where y.id.orid = '"+orid+"' ";
        list = this.shopcartDao.find(sql);
        if(list != null && !list.isEmpty()){
            for(int i = 0;i < list.size();i++){
                YOrder yorder = (YOrder) list.get(i);
                yorder.setDdzt(ddzt);
                this.shopcartDao.update(yorder);
            }
        }
        if(ddzt.equals("98")){
            sql = "from TRealname t where t.orid = '"+orid+"' ";
            list = this.shopcartDao.find(sql);
            if(list != null && !list.isEmpty()){
                for(int i = 0;i < list.size();i++){
                    TRealname realname = (TRealname) list.get(i);
                    this.shopcartDao.delete(realname);
                }
            }
        }
    }
}
