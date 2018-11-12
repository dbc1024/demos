package com.ectrip.ec.book.ticket.dao.idao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.QueryProviderBook;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.upload.model.Upfile;

public interface ITicketBookDAO extends IGenericDao {
	/**
	 * ������������ѯ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti���ѿ�ʼʱ��
	 * @param ldti���ѽ���ʱ��
	 * @return
	 * return:List
	 * Date:2012-1-4
	 */
	public List queryTicket(String rzti,String ldti,String lgtp);
	/**
	 * ����������ѯ���������̣��з�ҳ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param providerbook �����̲�ѯ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url���ʵ�ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-1-5
	 */
	public PaginationSupport queryTicketProviderSearch(String rzti,String ldti,QueryProviderBook providerbook,int page,int pageSize,String url);

	/**
	 * �õ���������������
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������0��,1�ǣ�
	 * @return
	 * return:List
	 * Date:2012-1-6
	 */
	public List queryProviderTopics(String pdtp,int isson);
	/**
	 * ���ݷ��������õ����а�
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������0��,1�ǣ�
	 * @param pageSize��ʾ����
	 * @return
	 * return:List
	 * Date:2012-1-6
	 */
	public List queryProviderbyRemarkNum(String pdtp,String lgtp,int isson,int pageSize);
	/**
	 * ���ݷ����̱�ŵõ�����������
	 * Describe:
	 * @auth:huangyuqi
	 * @param providerId
	 * @param lgtp ��¼���
	 * @return
	 * return:Esbscenicareatab
	 * Date:2012-1-31
	 */
	public Esbscenicareatab queryProviderDetail(Long providerId,String lgtp);
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
	public List queryProviderZhouBianList(QueryProviderBook providerbook,int isson);
	
	/**
	 * ��ѯ������Ʊ�б���ҳʹ�ã�
	 * Describe:
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return
	 * return:List
	 * Date:2012-3-12
	 */
	public List queryTicketList(String rzti,String ldti,String lgtp);
	
	
	
	public List getProviderByArea(String type, int top, int areaid);
	
	public List getProductByProvider(int iscenicid, int top);
	
	
	public Map getMinPrice(int iscenicid);
	
	public Upfile getProviderPic(int iscenicid);
	
	
	public PaginationSupport searchTicketByKey(SearchKey keys, int pageSize, int startIndex, String url);
	
	/**
	 * date ��������ɴ���
	 */
	public List getTicketCrowd(Long iscenicid,String date,int ibusinessid);
	/**
	 * date ��������ɴ���
	 */
	public List getTicketByCrowd(Long kindid, Long iscenicid,String date,int ibusinessid);
	public List queryZhouBianList(QueryProviderBook providerbook, int isson);
	public List getProviderByRegional(String type, Long iregionalid,int tip) ;
	public List queryProduct(String rzti, String ldti, String lgtp,int top,int tip);
	public List searchTicketByProid(Long iscenicid, String rzti, String lgtp) throws Exception;
	public PaginationSupport searchProduct(String scenicIds,SearchKey keys,String rzti,String lgtp,int page,
			int pageSize, String url) throws Exception;
	/**
	 * 
	 * Describe:��Ʊ����ҳ��ؾƵ���Ϣ
	 * @author:chenxinhao
	 * @param iscenicids
	 * @return
	 * return:List
	 * Date:2013-12-26
	 */
	public List getHotel(Long[] iscenicids);
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
	public List getWanhui(Long[] iscenicids,String type);
	
	
	public List getscenicSourceList(String pdtp);
	public List queryscenicSource(String pdtp);
	/**
	 * ����ҵ���ȡ��Ʒ
	 * @param rzti
	 * @param ldti
	 * @param ibusinessid
	 * @param top
	 * @param tip
	 * @return
	 */
	public List queryProduct(String rzti, String ldti, Long ibusinessid,int top,int tip);
	/**
	 * �����������˾�������
	 * @param iscenicIds
	 * @param rzti
	 * @param ldti
	 * @param ibusinessid
	 * @param top
	 * @param tip
	 * @return
	 */
	public List queryProductIndex(String iscenicIds,String rzti, String ldti, Long ibusinessid,int top,int tip,boolean isAll);
	public PaginationSupport queryProductIndexs(String iscenicIds,String rzti, String ldti, Long ibusinessid,int top,int tip,boolean isAll, int pageSize, int startIndex, String url);
	public List searchTicketByProid(Long iscenicid, String rzti,Long ibusinessid) throws Exception;
	public List searchTicketByProid(Long iscenicid, Long isscancode, String rzti, Long ibusinessid) throws Exception;
	public List searchTicketByProid(Long iscenicid, String rzti,Long ibusinessid,String groupno) throws Exception;
	public List searchTicketByProid(Long iscenicid, Long icrowdkindpiceid, String rzti,Long ibusinessid,String groupno) throws Exception ;
	public Esbprovicerq findEsbprovicerq(Long iscenicid, Long ibusinessid);
	public List findTimeticket(long iscenicid, long i);
	public List searchTimeticketsizeByIscenicid(long iscenicid);

	public Ticketxgz searchTicketRefused(Long ticketid) throws Exception;

	public MOrder queryOrder(Long orderId,String ddzt);
	public List<TOrder> queryTOrder(String orderId) throws Exception;

}

