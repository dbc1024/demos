package com.ectrip.ec.book.shopcart.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.ec.book.shopcart.dao.idao.IShopCartOtherDAO;
import com.ectrip.ec.book.shopcart.service.iservice.IShopCartOtherService;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venuearea;

public class ShopCartOtherService implements IShopCartOtherService {
	private IShopCartOtherDAO shopcartotherDao;

	public IShopCartOtherDAO getShopcartotherDao() {
		return shopcartotherDao;
	}

	public void setShopcartotherDao(IShopCartOtherDAO shopcartotherDao) {
		this.shopcartotherDao = shopcartotherDao;
	}

	/**
	 * ���ݲ�Ʒ��������ڲ�ѯ�۸� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tikcettypeId
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return return:Edmcrowdkindpricetab Date:2012-2-2
	 */
	public Edmcrowdkindpricetab queryPriceByTicketId(Long tikcettypeId, String rzti, String ldti, String lgtp) {
		return shopcartotherDao.queryPriceByTicketId(tikcettypeId, rzti, ldti, lgtp);
	}
	 public Edmcrowdkindpricetab queryPriceByTicketId(Long tikcettypeId,String rzti,String ldti,Long  ibusinessid){
		 return shopcartotherDao.queryPriceByTicketId(tikcettypeId, rzti, ldti, ibusinessid);
	 }

	public Edmcrowdkindpricetab queryPriceByTicketId(Long tickettypeId, String rzti, String ldti, String lgtp, String icrowkindid) {
		return shopcartotherDao.queryPriceByTicketId(tickettypeId, rzti, ldti, lgtp, icrowkindid);
	}

	/**
	 * ���ݼ۸����ж��Ƿ�����Ʊ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param priceId
	 * @param rzti
	 * @param ldti
	 * @return return:boolean Date:2012-2-2
	 */
	public boolean queryProductOfIsTaoPao(Long priceId) {
		return shopcartotherDao.queryProductOfIsTaoPao(priceId);
	}

	/**
	 * �жϲ�Ʒ�Ƿ��Ѿ��ڹ��ﳵ�д���
	 * Describe:��δ��¼ʱcookieId����Ϊ�գ�userIdΪ�գ�����¼ʱuserId����Ϊ�գ�cookieIdΪ��
	 * 
	 * @auth:huangyuqi
	 * @param iticketypeId
	 * @param userId
	 * @param cookieId
	 * @return return:List Date:2012-2-3
	 */
	public List queryProductIsShopCart(Long iticketypeId, String userId, String cookieId, Long icrowdkindpriceid) {
		return shopcartotherDao.queryProductIsShopCart(iticketypeId, userId, cookieId, icrowdkindpriceid);
	}

	/**
	 * �жϲ�Ʒ��ĳʱ�����Ƿ��Ѿ��ڹ��ﳵ�д���
	 * Describe:��δ��¼ʱcookieId����Ϊ�գ�userIdΪ�գ�����¼ʱuserId����Ϊ�գ�cookieIdΪ��
	 * 
	 * @auth:huangyuqi
	 * @param iticketypeId
	 * @param userId
	 * @param cookieId
	 * @param rzti���ѿ�ʼʱ��
	 *            ������Ϊ�գ�
	 * @param ldti���ѽ���ʱ��
	 *            ������Ϊ�գ�
	 * @return return:List Date:2012-2-3
	 */
	public List queryProductIsShopCartByTime(Long iticketypeId, String userId, String cookieId, String rzti, String ldti, Long icrowdkindpriceid) {
		return shopcartotherDao.queryProductIsShopCartByTime(iticketypeId, userId, cookieId, rzti, ldti, icrowdkindpriceid);
	}

	/**
	 * ��ѯ����δ֧���Ķ��� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:PaginationSupport Date:2012-2-7
	 */
	public List queryOrderList(String usid) {
		return shopcartotherDao.queryOrderList(usid);
	}

	/**
	 * �õ�δ֧�������Ĳ�Ʒ��Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param orid
	 * @return return:List Date:2012-2-7
	 */
	public List queryProductList(String orid) {
		return shopcartotherDao.queryProductList(orid);
	}

	/**
	 * ��ȡ��Ʊ���б� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:List Date:2012-2-8
	 */
	public List queryLingPiaoUser(String usid) {
		return shopcartotherDao.queryLingPiaoUser(usid);
	}

	/**
	 * ��ȡ��ƱƱ���˴���Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param itickettype��Ʒ���
	 * @param rzti�������
	 * @param zfdate������
	 * @param lgtp
	 *            �û����
	 * @return return:List Date:2012-2-9
	 */
	public List queryRafttripInfoList(Long itickettype, String rzti, String zfdate, String lgtp) throws IllegalAccessException, InvocationTargetException {
		return shopcartotherDao.queryRafttripInfoList(itickettype, rzti, zfdate, lgtp);
	}

	/**
	 * ����ʱ�䡢��Ʒ������ģʽ��ȡ��Ʒ������������ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param iticketid
	 * @param date
	 * @param controlltype
	 *            00���������� 01:�տ��� 02:�˴ο��� 03:�˴�������� 04����λ����
	 * @return return:List Date:2012-2-9
	 */
	public List getNumberControllData(Long iticketid, String date, String controlltype) {
		return shopcartotherDao.getNumberControllData(iticketid, date, controlltype);
	}

	/**
	 * ��ѯ�Ӳ�Ʒ��Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param itickettypeId��Ʒ���
	 * @param rzti����
	 * @param ldti����
	 * @param lgtp
	 *            �û����
	 * @return return:List Date:2012-2-3
	 */
	public List getSonProductList(Long itickettypeId, String rzti, String ldti, String lgtp, Long icrowdkindpriceid) {
		return shopcartotherDao.getSonProductList(itickettypeId, rzti, ldti, lgtp, icrowdkindpriceid);
	}

	/**
	 * ���ݲ�ƷID������Id,ʱ������ѯ�������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tripid����id
	 * @param iticketyId
	 * @param stdt
	 * @return return:boolean Date:2012-2-10
	 */
	public List queryRaftIsShop(Long tripid, Long iticketypeId, String stdt) {
		return shopcartotherDao.queryRaftIsShop(tripid, iticketypeId, stdt);
	}

	/**
	 * ��ѯ���� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param providerId�����̱��
	 * @param venuetype�������
	 * @return return:Venue Date:2012-2-14
	 */
	public Venue queryVenue(Long providerId, String venuetype) {
		return shopcartotherDao.queryVenue(providerId, venuetype);
	}

	/**
	 * ��ѯ�������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param venueId���ر��
	 * @return return:Venuearea Date:2012-2-14
	 */
	public Venuearea queryVenueArea(Long venueId) {
		return shopcartotherDao.queryVenueArea(venueId);
	}

	/**
	 * �����˴Ρ���Ʒ�������ڲ�ѯ��Ʒ�˴������ϵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param tripid�˴�
	 * @param itickettypeid
	 *            ��ƷID
	 * @param zfdate��������
	 * @return return:Prdtripvenuemanage Date:2012-2-15
	 */
	public List quereyPrdtripvenuemanage(Long tripid, Long itickettypeid, String zfdate) {
		return shopcartotherDao.quereyPrdtripvenuemanage(tripid, itickettypeid, zfdate);
	}

	/**
	 * ��ȡ��Ʊ����Ϣ
	 * ����������
	 */
	public List queryLingPiao(String usid){
		return shopcartotherDao.queryLingPiao(usid);
	}


}
