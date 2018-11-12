package com.ectrip.ec.book.shopcart.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.OrderInfo;
import com.ectrip.ec.model.order.Shopcart;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;

/**
 * ���ﳵIservice
 * @author huangyuqi
 */
public interface IShopCartService {

	/**
	 * ���ӹ��ﳵ
	 * Describe:
	 * @auth:huangyuqi
	 * @param shopcart
	 * @return
	 * return:String
	 * Date:2012-2-1
	 */
	public String addShopCart(Shopcart shopcart);
	/**
	 * �޸Ĺ��ﳵ
	 * Describe:
	 * @auth:huangyuqi
	 * @param shopcart
	 * return:void
	 * Date:2012-2-1
	 */
	public void updateShopCart(Shopcart shopcart);
	/**
	 * ���ݱ��ɾ�����ﳵ
	 * Describe:
	 * @auth:huangyuqi
	 * @param shopId Ҫɾ���Ĳ�Ʒ�������
	 * return:void
	 * Date:2012-2-1
	 */
	public void deleteShopCart(Long[] shopId);
	/**
	 * �����û�ɾ�����ﳵ
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid �û���
	 * @param shopseq ���ﳵ���(����ɾ������ʱ������0)
	 * return:void
	 * Date:2012-2-2
	 */
	public void deleteShopCartByUser(String usid, Long shopseq);
	/**
	 * ɾ�����ڹ��ﳵ
	 * Describe:���û���¼ʱ��usid��Ϊ�գ�cookieidΪ�գ���δ��¼ʱ��usidΪ�գ�cookieid����Ϊ��
	 * @auth:huangyuqi
	 * @param usid �û���
	 * @param cookieid 
	 * return:void
	 * Date:2012-2-2
	 */
	public void deleteShopCartByTime(String usid, String cookieid);
	/**
	 * �õ����ﳵ�б��û���¼ʱ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid �û���
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ��
	 * @param url ·��
	 * @return
	 * return:PaginationSupport
	 * Date:2012-2-1
	 */
	public PaginationSupport queryShopCartListByUser(String usid, int page, int pageSize, String url);
	
	/**
	 * �õ����ﳵ�б��û���¼ʱ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param shopSeq ���ﳵ�������
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ��
	 * @param url ·��
	 * @return
	 * return:PaginationSupport
	 * Date:2012-2-1
	 */
	public PaginationSupport queryShopCartList(Long[] shopSeq, int page, int pageSize, String url);
	/**
	 * ��ȡ�����б�
	 * Describe:
	 * @auth:huangyuqi
	  * @param Seq ���ﳵ������飨δ��¼��
	 * @param usid �û�������¼ʱ�ã�
	 * @return
	 * return:List
	 * Date:2012-2-4
	 */
	public List queryShopList(Long[] seq, String usid);
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
	public List queryShopList(Long[] seq, String usid,Long id);*/
	/**
	 *  ��ѯ�Ƶ�ÿ��۸� (non-Javadoc)
	 * Describe:
	 * @auth:huangyuqi
	 * @param itickettypeId ��Ʒ���
	 * @param providertype ����������
	 * @param rzti ��ʼ����
	 * @param ldti ��������
	 * @param lgtp �û����ͣ���δ��¼�������
	 * @return
	 * return:List
	 * Date:2012-2-3
	 */
	public List getPriceHotel(Long itickettypeId, String providertype, String rzti, String ldti, String lgtp, Long icrowdkindpriceid);
	
	/**
	 * ��ѯ�Ӳ�Ʒ�۸�
	 * Describe:
	 * @auth:huangyuqi
	 * @param priceId
	 * @param sontickettypeId
	 * @return
	 * return:Edmticketcomposepricetab
	 * Date:2012-2-20
	 */
	public Edmticketcomposepricetab querySonPrice(Long priceId, Long sontickettypeId);
	/**
	 * 
	 * Describe:����ʵ����
	 * @author:chenxinhao
	 * @param list
	 * return:void
	 * Date:2012-10-16
	 */
	public void saveRealname(List<TRealname> list);
	
	public List getPriceHotel(Long itickettypeId, String providertype, String rzti, String ldti,
                              String lgtp);
	
	/**
	 * ��ѯ������
	 * checkPv(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param usid
	 * @return 
	 * int
	 * @exception 
	 * @date:2013-10-16 ����05:25:12
	 * @since  1.0.0
	 */
	public int checkPv(Long iscenicid, String usid);
	
	/**
	 * ��ѯ��ͬ�����ļ۸�
	 * checkPrice(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid ��Ʒid
	 * @param ibusinessid ҵ������
	 * @param icrowdkindid ��Ⱥ����
	 * @param note1 �û�����
	 * @param rzti ��ʼ����
	 * @param ldti ��������
	 * @return price ʵ���ۼ�
	 * String
	 * @exception 
	 * @date:2013-10-22 ����06:23:15
	 * @since  1.0.0
	 */
	public Double checkPrice(Long itickettypeid, Long ibusinessid, Long icrowdkindid, String rzti, String ldti, String note1);
	
	public double fillOrderInfo(List<OrderInfo> orderinfos, int ibusinessid);
	
	public boolean syncPrice(Long itickettypeid, Long icrowdkindid, Long ibusinessid, String playtime);
	
	
	public List<OrderInfo> fillOrderInfo(List<OrderInfo> orderinfos, int ibusinessid, String fz);
	
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
	public int check(String type, String firstdate, String lastdate, LprPojo lpr);
	
	/**
	 * *
	 * Describe:��ѯɢ��Ԥ���ķ�����
	 * @see com.ectrip.book.shopcart.service.iservice.IShopCartService#findList(Long)
	 * @param iscenicid
	 * @return
	 * @author huying
	 * Date:2015-4-8
	 */
	public List findList(Long iscenicid, Long ibusinessid);
	
	public List getTorderlist(String usid, Long itickettypeid, Long icrowdkindpriceid, Long icrowdkindid);

    public void updateOrderDdzt(String orid, String ddzt);
}

