package com.ectrip.ec.book.hotel.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.hotel.dao.idao.IHotelDAO;
import com.ectrip.ec.book.hotel.model.HotelDTO;
import com.ectrip.ec.book.hotel.service.iservice.IHotelService;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;

public class HotelService implements IHotelService {
	private IHotelDAO hotelDao;

	public void setHotelDao(IHotelDAO hotelDao) {
		this.hotelDao = hotelDao;
	}

	/**
	 * ���ݷ��������Ͳ�ѯ�������̿�Դ���б�* Describe:
	 * 
	 * @see com.ectrip.book.hotel.service.iservice.IHotelService#getHotelSourceList(java.lang.String)
	 * @param pdtp
	 * @return
	 * @author huangyuqi Date:2011-11-28
	 */
	public List getHotelSourceList(String pdtp) {
		return hotelDao.getHotelSourceList(pdtp);
	}

	/**
	 * * Describe:��ѯ�������̲�Ʒ(�Ƶ�)
	 * 
	 * @see com.ectrip.book.hotel.service.iservice.IHotelService#getHotelProductList(java.lang.String,
	 *      java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author chenxinhao Date:2012-11-7
	 */
	public PaginationSupport getHotelProductList(String rzti, String ldti, String lgtp, int page, int pageSize, String url) {
		return this.hotelDao.getHotelProductList(rzti, ldti, lgtp, page, pageSize, url);
	}

	/**
	 * ��ѯ��ĳ�����µķ����̲�Ʒ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param regionId��Դ��
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2011-11-28
	 */
	public PaginationSupport getHotelProductList(Long regionId, String rzti, String ldti, String lgtp, int page, int pageSize, String url) {
		return hotelDao.getHotelProductList(regionId, rzti, ldti, lgtp, page, pageSize, url);
	}

	/**
	 * ��ѯ�Ƶ� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param provider
	 * @param price
	 * @param product
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2011-11-30
	 */
	public PaginationSupport getHotelProductSearchList(Esbscenicareatab provider, Hotelprovider hotelpro, String rzti, String ldti, String lgtp, int page, int pageSize, String url) {
		return hotelDao.getHotelProductSearchList(provider, hotelpro, rzti, ldti, lgtp, page, pageSize, url);
	}

	/**
	 * * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * 
	 * @see com.ectrip.book.hotel.service.iservice.IHotelService#getHotelTicketduct(java.lang.Long,
	 *      java.lang.String, java.lang.String)
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return
	 * @author lijingrui Date:Feb 15, 2012
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti, String ldti, String lgtp) throws Exception {
		return hotelDao.getHotelTicketduct(iscenicid, rzti, ldti, lgtp);
	}

	/**
	 * * Describe:�Ƶ������еľ����Ƽ�
	 * 
	 * @see com.ectrip.book.hotel.service.iservice.IHotelService#shopAllticketcenter(java.lang.Long,
	 *      java.lang.String, int, int, java.lang.String)
	 * @param szregionalid
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui Date:Mar 2, 2012
	 */
	public PaginationSupport shopAllticketcenter(Long szregionalid, String lgtp, int page, int pageSize, String url) {
		return hotelDao.shopAllticketcenter(szregionalid, lgtp, page, pageSize, url);
	}

	/**
	 * * Describe:��ʾ���еľƵ��Ʒ��Ϣ����Ѷ����ҳʹ�ã�
	 * 
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#queryHotelList(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Long)
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param rowid
	 * @return
	 * @author lijingrui Date:Mar 12, 2012
	 */
	public List queryHotelList(String rzti, String ldti, String lgtp, Long rowid) {
		return hotelDao.queryHotelList(rzti, ldti, lgtp, rowid);
	}

	public OrderCombinDTO combinationOrder(String orid, Custom custom, List<HotelDTO> hotels, LprPojo lpr) {
		OrderCombinDTO dto = new OrderCombinDTO();
		dto.setOrid(orid);
		dto.setUsid(custom.getUsid());
		dto.setIbusiness(custom.getIbusinessid());
		dto.setGroupno(custom.getNote2());
		dto.setAskOrder("");
		List<LprPojo> lprs = new ArrayList<LprPojo>();
		lpr.setSzregionalid("1");
		lprs.add(lpr);
		dto.setLprs(lprs);
		String startDate="";
		String endDate="";
		List<OrderProduct> productlist=new ArrayList<OrderProduct>();
		for (HotelDTO hotel : hotels) {
			if(startDate==null||startDate.equals("")||Tools.getDayNumb(startDate, hotel.getRzdate())<1){
				startDate=hotel.getRzdate();
			}
			if(endDate==null||endDate.equals("")||Tools.getDayNumb(endDate, hotel.getTddate())>1){
				endDate=hotel.getTddate();
			}
			OrderProduct product = new OrderProduct();
			product.setIticketid(new Long(hotel.getProductid()));
			Edmtickettypetab ticket = (Edmtickettypetab) hotelDao.get(Edmtickettypetab.class, product.getIticketid());
			product.setIscenicid(ticket.getIscenicid());
			lpr.setIscenicid(String.valueOf(ticket.getIscenicid()));
			product.setStartdate(hotel.getRzdate());
			product.setIscenicid(ticket.getIscenicid());
			product.setCrowdkindid(1l);
			product.setEnddate(hotel.getTddate());
			product.setNum(new Long(hotel.getNum()));
			productlist.add(product);
		}
		lpr.setRzti(startDate);
		dto.setProducts(productlist);
		if (hotels.get(0)!=null&&hotels.get(0).getNote()!=null) {
			dto.setNote(hotels.get(0).getNote());
		}
		return dto;
	}
	
	public PaginationSupport getHotelProductList(SearchKey keys,String rzti,String ldti,String lgtp,int page, int pageSize, String url) throws Exception{
		return this.hotelDao.getHotelProductList(keys, rzti, ldti, lgtp, page, pageSize, url);
	}
	
	public List searchProduct(Long iscenicid, String rzti) {
		return this.hotelDao.searchProduct(iscenicid, rzti);
	}
	
	public List searchAddress(Long regionalid){
		return this.hotelDao.searchAddress(regionalid);
	}
	
	public List getHotHotel(Long iregionalid,Long iscenicid,int top){
		return this.hotelDao.getHotHotel(iregionalid, iscenicid, top);
	}
	
	public List getSpecialHotel(int top){
		return this.hotelDao.getSpecialHotel(top);
	}
	
	public Edmtickettypetab getHotel(Long hotelid) throws Exception{
		return this.hotelDao.getHotel(hotelid);
	}
	/**
	 * *
	 * Describe:��ѯ�Ƽ������̣���Ѷ��ר��
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#queryProviders(java.lang.String, java.lang.String, int)
	 * @param keys �ؼ��֣��Զ��Ÿ���
	 * @param type ����������
	 * @param top 
	 * @return
	 * @author chenxinhao
	 * Date:2013-12-24
	 */
	public List queryProviders(String keys,String type,int top){
		return this.hotelDao.queryProviders(keys, type, top);
	}
	
	public List getHotJinqu(){
		return this.hotelDao.getHotJinqu();
	}
	
	public List getHotJingqu(String scenictype,int top){
		String hsql = "from Esbscenicareatab es where es.scenictype='"+scenictype+"' and es.byisuse =1 and es.irootid=0 order by es.iordernumber desc";
		List list = this.hotelDao.findTopNumb(hsql, top);
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Esbscenicareatab es = (Esbscenicareatab) list.get(i);
				String hsql2 = "select es.iscenicid as iscenicid from Esbscenicareatab es,Galsourceregiontab g where es.scenictype='06' and es.byisuse =1 and (g.iregionalid=es.szregionalid or g.iparentid=es.szregionalid) and es.szregionalid="+es.getSzregionalid();
				List list2 = this.hotelDao.find(hsql2);
				if(list2!=null&&!list2.isEmpty()){
					Integer num = list2.size();
					es.setNote(num.toString());
				}else{
					es.setNote("0");
				}
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ es.getIscenicid()
						+ " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.hotelDao.findTopNumb(sql, 1);
				es.setPiclist(piclist);
			}
		}
		return list;
	}
	
	
	/**
	 * * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * 
	 * @see com.ectrip.book.hotel.service.iservice.IHotelService#getHotelTicketduct(java.lang.Long,
	 *      java.lang.String, java.lang.String)
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return
	 * @author lijingrui Date:Feb 15, 2012
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti, String ldti, Long ibusinessid) throws Exception {
		return hotelDao.getHotelTicketduct(iscenicid, rzti, ldti, ibusinessid);
	}
	public PaginationSupport getSpecialHotel(Long ibusinessid,int top,List hotelIds,int pageSize, int startInt, String url,Boolean isHqyatu){
		return hotelDao.getSpecialHotel(ibusinessid, top,hotelIds,pageSize, startInt,url,isHqyatu);
	}
	public boolean hadOrder(String usid){
		return hotelDao.hadOrder(usid);
	}
}
