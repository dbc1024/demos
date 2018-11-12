package com.ectrip.ec.book.shopcart.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.venuemarketing.Venue;
import com.ectrip.ticket.model.venuemarketing.Venuearea;

/**
 * ���ﳵ��������
 * @author huangyuqi
 */
public interface IShopCartOtherService {

	/**
	 * ���ݲ�Ʒ��������ڲ�ѯ�۸�
	 * Describe:
	 * @auth:huangyuqi
	 * @param tikcettypeId
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2012-2-2
	 */
	 public Edmcrowdkindpricetab queryPriceByTicketId(Long tikcettypeId,String rzti,String ldti,String lgtp);
	 public Edmcrowdkindpricetab queryPriceByTicketId(Long tikcettypeId,String rzti,String ldti,Long  ibusinessid);
	 
	 
	 /**
	  * ��ȡ��Ʒ��Ϣ,���ݲ�Ʒ���  ��ʼ����ֹ���� �û����� ��Ⱥ����
	  * @param tickettypeId
	  * @param rzti
	  * @param ldti
	  * @param lgtp
	  * @param icrowkindid
	  * @return
	  */
	 public Edmcrowdkindpricetab queryPriceByTicketId(Long tickettypeId, String rzti, String ldti,
				String lgtp,String icrowkindid);

	 /**
	  * ���ݼ۸����ж��Ƿ�����Ʊ
	  * Describe:
	  * @auth:huangyuqi
	  * @param priceId
	  * @param rzti
	  * @param ldti
	  * @return
	  * return:boolean
	  * Date:2012-2-2
	  */
	 public boolean queryProductOfIsTaoPao(Long priceId);
	 /**
	  * �жϲ�Ʒ�Ƿ��Ѿ��ڹ��ﳵ�д���
	  * Describe:��δ��¼ʱcookieId����Ϊ�գ�userIdΪ�գ�����¼ʱuserId����Ϊ�գ�cookieIdΪ��
	  * @auth:huangyuqi
	  * @param iticketypeId
	  * @param userId
	  * @param cookieId
	  * @return
	  * return:List
	  * Date:2012-2-3
	  */
	 public List queryProductIsShopCart(Long iticketypeId,String userId,String cookieId,Long icrowdkindpriceid);
	 /**
	  * �жϲ�Ʒ��ĳʱ�����Ƿ��Ѿ��ڹ��ﳵ�д���
	  * Describe:��δ��¼ʱcookieId����Ϊ�գ�userIdΪ�գ�����¼ʱuserId����Ϊ�գ�cookieIdΪ��
	  * @auth:huangyuqi
	  * @param iticketypeId
	  * @param userId
	  * @param cookieId
	  * @param rzti���ѿ�ʼʱ�䣨����Ϊ�գ�
	  * @param ldti���ѽ���ʱ�䣨����Ϊ�գ�
	  * @return
	  * return:List
	  * Date:2012-2-3
	  */
	 public List queryProductIsShopCartByTime(Long iticketypeId,String userId,String cookieId,String rzti,String ldti,Long icrowdkindpriceid);
	 /**
	  * ��ѯ����δ֧���Ķ���
	  * Describe:
	  * @auth:huangyuqi
	  * @param usid
	  * @return
	  * return:PaginationSupport
	  * Date:2012-2-7
	  */
	 public List queryOrderList(String usid);
	 /**
	  * �õ�δ֧�������Ĳ�Ʒ��Ϣ
	  * Describe:
	  * @auth:huangyuqi
	  * @param orid
	  * @return
	  * return:List
	  * Date:2012-2-7
	  */
	 public List queryProductList(String orid);
	 /**
	  * ��ȡ��Ʊ���б�
	  * Describe:
	  * @auth:huangyuqi
	  * @param usid
	  * @return
	  * return:List
	  * Date:2012-2-8
	  */
	 public List queryLingPiaoUser(String usid);
	 /**
	  * ��ȡ��ƱƱ���˴���Ϣ
	  * Describe:
	  * @auth:huangyuqi
	  * @param itickettype��Ʒ���
	  * @param rzti�������
	  * @param zfdate������
	  * @param lgtp �û����
	  * @return
	  * return:List
	  * Date:2012-2-9
	  */
	 public List queryRafttripInfoList(Long itickettype,String rzti,String zfdate,String lgtp)
		throws IllegalAccessException, InvocationTargetException;
	 
	 /**
	  * ����ʱ�䡢��Ʒ������ģʽ��ȡ��Ʒ������������
	  * Describe:
	  * @auth:huangyuqi
	  * @param iticketid
	  * @param date
	  * @param controlltype 00���������� 01:�տ��� 02:�˴ο��� 03:�˴�������� 04����λ����
	  * @return
	  * return:List
	  * Date:2012-2-9
	  */
		public List getNumberControllData(Long iticketid, String date, String controlltype) ;
		/**
		 * ��ѯ�Ӳ�Ʒ��Ϣ
 		 * Describe:
		 * @auth:huangyuqi
		 * @param itickettypeId��Ʒ���
		 * @param rzti����
		 * @param ldti����
		 * @param lgtp �û����
		 * @return return:List Date:2012-2-3
		 */
		public List getSonProductList(Long itickettypeId,String rzti,String ldti,String lgtp,Long icrowdkindpriceid);
		/**
		 * ���ݲ�ƷID������Id,ʱ������ѯ��������
		 * Describe:
		 * @auth:huangyuqi
		 * @param tripid����id���粻�贫Щ����������0��
		 * @param iticketyId��ƱID
		 * @param stdt����
		 * @return
		 * return:boolean
		 * Date:2012-2-10
		 */
		public List queryRaftIsShop(Long tripid,Long iticketypeId,String stdt);
		/**
		 * ��ѯ����
		 * Describe:
		 * @auth:huangyuqi
		 * @param providerId�����̱�� 
		 * @param venuetype�������
		 * @return
		 * return:Venue
		 * Date:2012-2-14
		 */
		public Venue queryVenue(Long providerId,String venuetype);
		/**
		 * ��ѯ��������
		 * Describe:
		 * @auth:huangyuqi
		 * @param venueId���ر��
		 * @return
		 * return:Venuearea
		 * Date:2012-2-14
		 */
		public Venuearea queryVenueArea(Long venueId);
		/**
		 * �����˴Ρ���Ʒ�������ڲ�ѯ��Ʒ�˴������ϵ
		 * Describe:
		 * @auth:huangyuqi
		 * @param tripid�˴�
		 * @param itickettypeid ��ƷID
		 * @param zfdate��������
		 * @return
		 * return:Prdtripvenuemanage
		 * Date:2012-2-15
		 */
		public List quereyPrdtripvenuemanage(Long tripid,Long itickettypeid ,String zfdate);
		
		
		/**
		 * ��ȡ��Ʊ����Ϣ
		 * ����������
		 */
		public List queryLingPiao(String usid);
		
		
}

