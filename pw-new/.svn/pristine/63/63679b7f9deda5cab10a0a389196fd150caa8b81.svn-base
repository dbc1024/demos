package com.ectrip.ec.book.shopcart.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.Shopcart;
import com.ectrip.ec.model.order.TRealname;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;

public interface IShopCartDAO extends IGenericDao {
	/**
	 * ���ӹ��ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopcart
	 * @return return:String Date:2012-2-1
	 */
	public String addShopCart(Shopcart shopcart);

	/**
	 * �޸Ĺ��ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopcart
	 *            return:void Date:2012-2-1
	 */
	public void updateShopCart(Shopcart shopcart);

	/**
	 * ���ݱ��ɾ�����ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopIdҪɾ���Ĳ�Ʒ�������
	 *            return:void Date:2012-2-1
	 */
	public void deleteShopCart(Long[] shopId);

	/**
	 * �����û�ɾ�����ﳵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid�û���
	 * @param shopseq���ﳵ���
	 *            (����ɾ������ʱ������0) return:void Date:2012-2-2
	 */
	public void deleteShopCartByUser(String usid, Long shopseq);

	/**
	 * ɾ�����ڹ��ﳵ Describe:���û���¼ʱ��usid��Ϊ�գ�cookieidΪ�գ���δ��¼ʱ��usidΪ�գ�cookieid����Ϊ��
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 *            �û���
	 * @param cookieid
	 *            return:void Date:2012-2-2
	 */
	public void deleteShopCartByTime(String usid, String cookieid);

	/**
	 * �õ����ﳵ�б��û���¼ʱ�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid�û���
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url·��
	 * @return return:PaginationSupport Date:2012-2-1
	 */
	public PaginationSupport queryShopCartListByUser(String usid, int page, int pageSize, String url);

	/**
	 * �õ����ﳵ�б��û���¼ʱ�� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param shopSeq
	 *            ���ﳵ�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url·��
	 * @return return:PaginationSupport Date:2012-2-1
	 */
	public PaginationSupport queryShopCartList(Long[] shopSeq, int page, int pageSize, String url);

	/**
	 * ��ȡ�����б� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param Seq���ﳵ�������
	 *            ��δ��¼��
	 * @param usid�û���
	 *            ����¼ʱ�ã�
	 * @return return:List Date:2012-2-4
	 */
	public List queryShopList(Long[] seq, String usid);
	/**
	 * ��ȡ�����б� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param Seq���ﳵ�������
	 *            ��δ��¼��
	 * @param usid�û���
	 *            ����¼ʱ�ã�
	 * @param id��Ʒid
	 *            
	 * @return return:List Date:2012-2-4
	 *//*
	public List queryShopList(Long[] Seq, String usid,Long id) ;*/

	/**
	 * ��ѯ�Ƶ�ÿ��۸� (non-Javadoc) Describe:
	 * 
	 * @auth:huangyuqi
	 * @param itickettypeId��Ʒ���
	 * @param providertype����������
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param lgtp
	 *            �û���¼���ͣ���δ��¼�������
	 * @return return:List Date:2012-2-3
	 */
	public List getPriceHotel(Long itickettypeId, String providertype, String rzti, String ldti, String lgtp, Long icrowdkindpriceid);

	/**
	 * ��ѯ�Ӳ�Ʒ�۸� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param priceId
	 * @param sontickettypeId
	 * @return return:Edmticketcomposepricetab Date:2012-2-20
	 */
	public Edmticketcomposepricetab querySonPrice(Long priceId, Long sontickettypeId);

	/**
	 * 
	 * Describe:����ʵ����
	 * 
	 * @author:chenxinhao
	 * @param list
	 *            return:void Date:2012-10-16
	 */
	public void saveRealname(List<TRealname> list);

	/**
	 * ��ѯ�Ƶ�۸�
	 * 
	 * @param itickettypeId
	 * @param providertype
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return
	 */
	public List getPriceHotel(Long itickettypeId, String providertype, String rzti, String ldti, String lgtp);

	/**
	 * ��ѯ������ checkPv(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param iscenicid
	 * @param usid
	 * @return int
	 * @exception
	 * @date:2013-10-16 ����05:25:12
	 * @since 1.0.0
	 */
	public int checkPv(Long iscenicid, String usid);

	/**
	 * ͨ��id��ѯ��Ʒ��Ϣ checkOrder(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param itickettypeid
	 *            ��Ʒid
	 * @return PaginationSupport
	 * @exception
	 * @date:2013-10-21 ����04:46:12
	 * @since 1.0.0
	 */
	public PaginationSupport checkOrder(Long itickettypeid);

	/**
	 * ��ѯ��ͬ�����ļ۸� checkPrice(������һ�仰�����������������) (����������������������� �C ��ѡ)
	 * 
	 * @auth hejiahua
	 * @param itickettypeid
	 *            ��Ʒid
	 * @param ibusinessid
	 *            ҵ������
	 * @param icrowdkindid
	 *            ��Ⱥ����
	 * @param rzti
	 *            ��ʼ����
	 * @param ldti
	 *            ��������
	 * @param note1
	 *            �û�����
	 * @return price ʵ���ۼ� String
	 * @exception
	 * @date:2013-10-22 ����06:23:15
	 * @since 1.0.0
	 */
	public Double checkPrice(Long itickettypeid, Long ibusinessid, Long icrowdkindid, String rzti, String ldti, String note1);

	public Double getPrice(Long itickettypeid, Long ibusinessid, Long icrowdkindid, String rzti, String ldti, String note1);
	 /**
     * 
     * Describe:��ȡ��Ʒ�Ż���Ϣ
     * @author:lijingrui
     * @param iscenicid
     * @param ibusinessid
     * @param icrowkind
     * @param itickettypeid
     * @param startdate
     * @return
     * return:Edpofferschemetab
     * Date:2014-4-1
     */
    public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid, String startdate);
    
    /**
     * 
     * Describe:
     * @author:chenxinhao
     * @param itickettypeid	��ƷID
     * @param ibusinessid	ҵ��ID
     * @param icrowdkindid	��ȺID
     * @param rzti	��ʼ����
     * @param ldti	��ֹ����
     * @param note1	�۸����
     * @return
     * return:Edmcrowdkindpricetab
     * Date:2015-11-11
     */
    public Edmcrowdkindpricetab getPriceModel(Long itickettypeid,
                                              Long ibusinessid, Long icrowdkindid, String rzti, String ldti,
                                              String note1);
}
