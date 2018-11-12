package com.ectrip.ec.book.ticket.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.book.ticket.dao.idao.ITicketBookDAO;
import com.ectrip.ec.book.ticket.model.HotProvider;
import com.ectrip.ec.book.ticket.model.ProviderArea;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.book.ticket.service.iservice.ITicketBookService;
import com.ectrip.ec.custag.dao.idao.IHotThemeDAO;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.QueryProviderBook;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.upload.model.Upfile;
@SuppressWarnings({"unchecked","rawtypes"})
public class TicketBookService implements ITicketBookService {

	private ITicketBookDAO ticketbookDao;

	private IHotThemeDAO hotthemeDao;

	public IHotThemeDAO getHotthemeDao() {
		return hotthemeDao;
	}

	public void setHotthemeDao(IHotThemeDAO hotthemeDao) {
		this.hotthemeDao = hotthemeDao;
	}

	public ITicketBookDAO getTicketbookDao() {
		return ticketbookDao;
	}

	public void setTicketbookDao(ITicketBookDAO ticketbookDao) {
		this.ticketbookDao = ticketbookDao;
	}

	/**
	 * ������������ѯ���� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param rzti���ѿ�ʼʱ��
	 * @param ldti���ѽ���ʱ��
	 * @return return:List Date:2012-1-4
	 */
	public List queryTicket(String rzti, String ldti, String lgtp) {
		return ticketbookDao.queryTicket(rzti, ldti, lgtp);
	}

	/**
	 * ����������ѯ���������̣��з�ҳ�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param providerbook
	 *            �����̲�ѯ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url���ʵ�ַ
	 * @return return:PaginationSupport Date:2012-1-5
	 */
	public PaginationSupport queryTicketProviderSearch(String rzti, String ldti, QueryProviderBook providerbook, int page, int pageSize, String url) {
		return ticketbookDao.queryTicketProviderSearch(rzti, ldti, providerbook, page, pageSize, url);
	}

	/**
	 * �õ��������������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������
	 *            ��0��,1�ǣ�
	 * @return return:List Date:2012-1-6
	 */
	public List queryProviderTopics(String pdtp, int isson) {
		return ticketbookDao.queryProviderTopics(pdtp, isson);
	}

	/**
	 * ���ݷ��������õ����а� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������
	 *            ��0��,1�ǣ�
	 * @param pageSize��ʾ����
	 * @return return:List Date:2012-1-6
	 */
	public List queryProviderbyRemarkNum(String pdtp, String lgtp, int isson, int pageSize) {
		return ticketbookDao.queryProviderbyRemarkNum(pdtp, lgtp, isson, pageSize);
	}

	/**
	 * ���ݷ����̱�ŵõ����������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param providerId
	 *            �����̱��
	 * @param lgtp
	 *            �û���¼���
	 * @return return:Esbscenicareatab Date:2012-1-31
	 */
	public Esbscenicareatab queryProviderDetail(Long providerId, String lgtp) {
		return ticketbookDao.queryProviderDetail(providerId, lgtp);
	}

	/**
	 * �õ��ܱ߷����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param providerbook
	 *            Ҫ�õ��ķ��������
	 * @param isson
	 *            �Ƿ������ǰ�������µ��ӷ��������
	 * @return return:List Date:2012-1-31
	 */
	public List queryProviderZhouBianList(QueryProviderBook providerbook, int isson) {
		return ticketbookDao.queryProviderZhouBianList(providerbook, isson);

	}

	/**
	 * ��ѯ������Ʊ�б���ҳʹ�ã� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return return:List Date:2012-3-12
	 */
	public List queryTicketList(String rzti, String ldti, String lgtp) {
		return ticketbookDao.queryTicketList(rzti, ldti, lgtp);
	}

	public List simplehotArea(String type,String top){
		List list = hotthemeDao.getHotArea(type, top);
		if(list != null && list.size() > 0){
			for(int i=0;i<list.size();i++){
				ProviderArea area = new ProviderArea();
				Map map = (Map) list.get(i);
				int szregionalid = Integer.parseInt(map.get("szregionalid".toUpperCase()).toString());
				area.setSzregionalid(szregionalid);
				area.setSzregionalname(map.get("szregionalname".toUpperCase()).toString());
				list.set(i, area);
			}
		}
		return list;
	}
	
	public ProviderArea searchProByArea(String type,int top,int szregionalid){
		ProviderArea area = new ProviderArea();
		List providerlist = ticketbookDao.getProviderByArea(type, top, szregionalid);
		if(providerlist!=null){
			Map<String,List<HotProvider>> hotelmap = new HashMap<String, List<HotProvider>>();
			for(int i=0;i<providerlist.size();i++){
				Map provider = (Map) providerlist.get(i);
				HotProvider h = new HotProvider();
				try {
					BeanUtils.populate(h, provider);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("�������ȡ�����̳���:com.ectrip.book.ticket.service.TicketBookService==>hotAreaProducts" + e.getMessage());
				}
				//  ��ѯ��ͼ�
				Map lowprice = ticketbookDao.getMinPrice(h.getIscenicid());
				if (lowprice != null) {
					double listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
					double sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
					h.setLowprice(sprice);
					h.setDiscount(String.valueOf(Math.round(sprice / listingprice * 100) / 10));
				}
				if(type.equals("06")){
					List<HotProvider> hotellist =  (List<HotProvider>) hotelmap.get(h.getHoteltypename());
					if(hotellist==null){
						hotellist = new ArrayList<HotProvider>();
						hotellist.add(h);
						hotelmap.put(h.getHoteltypename(), hotellist);
					}else{
						hotellist.add(h);
						hotelmap.put(h.getHoteltypename(), hotellist);
					}
				}else{
					providerlist.set(i, h);
				}
			}
			if(type.equals("06")){
				area.setHotelmap(hotelmap);
			}else{
				area.setProviderlist(providerlist);
			}
		}
		return area;
	}
	
	public List hotArea(String type, String top) {
		List list = hotthemeDao.getHotArea(type, top);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String,List<HotProvider>> hotelmap = new HashMap<String, List<HotProvider>>();
				Map map = (Map) list.get(i);
				ProviderArea area = new ProviderArea();
				int szregionalid = Integer.parseInt(map.get("szregionalid".toUpperCase()).toString());
				area.setSzregionalid(szregionalid);
				area.setSzregionalname(map.get("szregionalname".toUpperCase()).toString());
				list.set(i, area);
				List providerlist = null;
				if(type.equals("06")){
					providerlist = ticketbookDao.getProviderByArea(type, 1000, szregionalid);
				}else{
					providerlist = ticketbookDao.getProviderByArea(type, 3, szregionalid);
				}
				if (providerlist != null) {
					for (int j = 0; j < providerlist.size(); j++) {
						Map provider = (Map) providerlist.get(j);
						HotProvider h = new HotProvider();
						try {
							BeanUtils.populate(h, provider);
						} catch (Exception e) {
							e.printStackTrace();
							System.err.println("�������ȡ�����̳���:com.ectrip.book.ticket.service.TicketBookService==>hotAreaProducts" + e.getMessage());
						}
						Upfile upfile = ticketbookDao.getProviderPic(h.getIscenicid());
						if (upfile != null) {
							h.setUpfilename(upfile.getUpfilename());
							h.setUpadder(upfile.getUpadder());
						}
						//  ��ѯ��ͼ�
						Map lowprice = ticketbookDao.getMinPrice(h.getIscenicid());
						if (lowprice != null) {
							double listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
							double sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
							h.setLowprice(sprice);
							h.setDiscount(String.valueOf(Math.round(sprice / listingprice * 100) / 10));
						}
						if(type.equals("06")){
							List<HotProvider> hotellist =  (List<HotProvider>) hotelmap.get(h.getHoteltypename());
							if(hotellist==null){
								hotellist = new ArrayList<HotProvider>();
								hotellist.add(h);
								hotelmap.put(h.getHoteltypename(), hotellist);
							}else{
								hotellist.add(h);
								hotelmap.put(h.getHoteltypename(), hotellist);
							}
						}else{
							providerlist.set(j, h);
						}
					}
				}
				if(type.equals("06")){
					area.setHotelmap(hotelmap);
				}else{
					area.setProviderlist(providerlist);
				}
			}
		}
		return list;
	}
	
	public PaginationSupport searchTicketByKey(SearchKey keys, int pageSize, int startIndex, String url) {
		return ticketbookDao.searchTicketByKey(keys, pageSize, startIndex, url);
	}

	/**
	 * date ��������ɴ���
	 */
	public List searhTicketByCrowd(Long iscenicid, String date,int ibusinessid) {
		List list = ticketbookDao.getTicketCrowd(iscenicid, date,ibusinessid);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				map.put("products", ticketbookDao.getTicketByCrowd(Long.parseLong(map.get("icrowdkindid").toString()), iscenicid, date,ibusinessid));
			}
		}
		return list;
	}

	public Map getMinPrice(int iscenicid) {
		return ticketbookDao.getMinPrice(iscenicid);
	}

	public List queryZhouBianList(QueryProviderBook providerbook, int isson) {
		return ticketbookDao.queryZhouBianList(providerbook, isson);
	}
	
	public List hotHotel(String top){
		List hotlist = new ArrayList();
		List list = hotthemeDao.getHotHotel(top);
		if(list!=null&&!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				HotProvider h = new HotProvider();
				Esbscenicareatab esb = new Esbscenicareatab();
				Map map =  (Map) list.get(i);
				try {
					BeanUtils.populate(esb,map);
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println("ת�������̳���");
				}
				h.setIscenicid(Integer.parseInt(esb.getIscenicid().toString()));
				h.setProvidername(esb.getSzscenicname());
				Upfile upfile = ticketbookDao.getProviderPic(h.getIscenicid());
				if (upfile != null) {
					h.setUpfilename(upfile.getUpfilename());
					h.setUpadder(upfile.getUpadder());
				}
				//  ��ѯ��ͼ�
				Map lowprice = ticketbookDao.getMinPrice(h.getIscenicid());
				if (lowprice != null) {
					double listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
					double sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
					h.setLowprice(sprice);
					h.setDiscount(String.valueOf(Math.round(sprice / listingprice * 100) / 10));
				}
				Hotelprovider hotelprovider = (Hotelprovider) ticketbookDao.get(Hotelprovider.class, esb.getIscenicid());
				if(hotelprovider!=null){
					Sysparv5 sysparv5 = (Sysparv5) ticketbookDao.get(Sysparv5.class, new Sysparv5Id("HOTP", hotelprovider.getZxjb()));
					h.setHoteltypeid(hotelprovider.getZxjb());
					h.setHoteltypename(sysparv5.getPmva());
				}
				hotlist.add(h);
			}
		}
		return hotlist;
	}
	
	public List getProviderByRegional(String type, Long iregionalid,int tip) {
		return this.ticketbookDao.getProviderByRegional(type, iregionalid,tip);
	}
	
	public List queryProduct(String rzti, String ldti, String lgtp,int top,int tip){
		return this.ticketbookDao.queryProduct(rzti, ldti, lgtp, top, tip);
	}
	
	public List searchTicketByProid(Long iscenicid, String rzti, String lgtp)
			throws Exception{
		return this.ticketbookDao.searchTicketByProid(iscenicid, rzti, lgtp);  
	}
	public PaginationSupport searchProduct(String scenicIds,SearchKey keys,String rzti,String lgtp,int page,
			int pageSize, String url) throws Exception{
		return this.ticketbookDao.searchProduct(scenicIds,keys, rzti, lgtp, page, pageSize, url);
	}
	/**
	 * 
	 * Describe:��Ʊ����ҳ��ؾƵ���Ϣ
	 * @author:chenxinhao
	 * @param iscenicids
	 * @return
	 * return:List
	 * Date:2013-12-26
	 */
	public List getHotel(Long[] iscenicids){
		return this.ticketbookDao.getHotel(iscenicids);
	}
	/**
	 * 
	 * Describe:��Ʊ����ҳ��������Ϣ
	 * @author:chenxinhao
	 * @param iscenicids
	 * @param type ��Ʊ���
	 * @return
	 * return:List
	 * Date:2013-12-26
	 */
	public List getWanhui(Long[] iscenicids,String type){
		return this.ticketbookDao.getWanhui(iscenicids, type);
	}
	public List getscenicSourceList(String pdtp){
		return this.ticketbookDao.getscenicSourceList(pdtp);
	}
	public List queryscenicSource(String pdtp){
		return this.ticketbookDao.queryscenicSource(pdtp);
		
	}
	
	public List queryProduct(String rzti, String ldti, Long ibusinessid,int top,int tip){
		return this.ticketbookDao.queryProduct(rzti, ldti, ibusinessid, top, tip);
	}
	
	public List queryProductIndex(String iscenids, String rzti, String ldti, Long ibusinessid,int top,int tip,boolean isAll){
		return this.ticketbookDao.queryProductIndex(iscenids,rzti, ldti, ibusinessid, top, tip,isAll);
	}
	
	public PaginationSupport queryProductIndexs(String iscenids, String rzti, String ldti, Long ibusinessid,int top,int tip,boolean isAll, int pageSize, int startIndex, String url){
		return this.ticketbookDao.queryProductIndexs(iscenids,rzti, ldti, ibusinessid, top, tip,isAll,pageSize,startIndex,url);
	}
	
	public List searchTicketByProid(Long iscenicid, String rzti,Long ibusinessid)
			throws Exception{
		return this.ticketbookDao.searchTicketByProid(iscenicid, rzti, ibusinessid);  
	}
	public List searchTicketByProid(Long iscenicid,Long isscancode, String rzti,Long ibusinessid)
			throws Exception{
		return this.ticketbookDao.searchTicketByProid(iscenicid,isscancode, rzti, ibusinessid);  
	}
	public List searchTicketByProid(Long iscenicid, String rzti,Long ibusinessid,String groupno)
			throws Exception{
		return this.ticketbookDao.searchTicketByProid(iscenicid, rzti, ibusinessid, groupno);  
	}
	
	
	public Esbprovicerq findEsbprovicerq(Long iscenicid, Long ibusinessid){
		return this.ticketbookDao.findEsbprovicerq(iscenicid, ibusinessid);
	}


	//��ȡ��ʱԤԼʱ��Ρ����
	public List searchTimeticketByIscenicid(long iscenicid,long i) {
		return this.ticketbookDao.findTimeticket(iscenicid,i);
	}

	public List searchTimeticketsizeByIscenicid(long iscenicid) {
		// TODO Auto-generated method stub
		return this.ticketbookDao.searchTimeticketsizeByIscenicid(iscenicid);
	}


	public List searchTicketByProid(Long iscenicid, Long icrowdkindpiceid, String rzti, Long ibusinessid,String groupno) throws Exception {
		// TODO Auto-generated method stub
		return this.ticketbookDao.searchTicketByProid(iscenicid, icrowdkindpiceid , rzti, ibusinessid, groupno); 
	}

	
	//�˶�����
	public Ticketxgz searchTicketRefused(Long ticketid) throws Exception{
		return this.ticketbookDao.searchTicketRefused(ticketid);
	}
	
	public MOrder queryOrder(Long orderId,String ddzt){
		return this.ticketbookDao.queryOrder(orderId,ddzt);
	}

	public List<TOrder> queryTOrder(String orderId) throws Exception{
		return this.ticketbookDao.queryTOrder(orderId);
	}

}
