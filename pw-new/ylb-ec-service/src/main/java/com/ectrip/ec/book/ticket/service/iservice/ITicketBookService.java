package com.ectrip.ec.book.ticket.service.iservice;

import java.util.List;
import java.util.Map;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.book.ticket.model.ProviderArea;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.QueryProviderBook;
import com.ectrip.ticket.model.provider.Ticketxgz;

public interface ITicketBookService {

	/**
	 * ������������ѯ���� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param rzti���ѿ�ʼʱ��
	 * @param ldti���ѽ���ʱ��
	 * @return return:List Date:2012-1-4
	 */
	public List queryTicket(String rzti, String ldti, String lgtp);

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
	public PaginationSupport queryTicketProviderSearch(String rzti, String ldti, QueryProviderBook providerbook, int page, int pageSize, String url);

	/**
	 * �õ��������������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������
	 *            ��0��,1�ǣ�
	 * @return return:List Date:2012-1-6
	 */
	public List queryProviderTopics(String pdtp, int isson);

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
	public List queryProviderbyRemarkNum(String pdtp, String lgtp, int isson, int pageSize);

	/**
	 * ���ݷ����̱�ŵõ����������� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param providerId�����̱��
	 * @param lgtp
	 *            �û���¼���
	 * @return return:Esbscenicareatab Date:2012-1-31
	 */
	public Esbscenicareatab queryProviderDetail(Long providerId, String lgtp);

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
	public List queryProviderZhouBianList(QueryProviderBook providerbook, int isson);

	/**
	 * ��ѯ������Ʊ�б���ҳʹ�ã� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return return:List Date:2012-3-12
	 */
	public List queryTicketList(String rzti, String ldti, String lgtp);

	public ProviderArea searchProByArea(String type,int top,int szregionalid);
	
	public List hotArea(String type, String top);

	public List simplehotArea(String type,String top);
	
	public PaginationSupport searchTicketByKey(SearchKey keys, int pageSize, int startIndex, String url);

	/**
	 * date ����������� ����
	 */
	public List searhTicketByCrowd(Long iscenicid, String date,int ibusinessid);

	public Map getMinPrice(int iscenicid);

	public List queryZhouBianList(QueryProviderBook providerbook, int isson);

	public List hotHotel(String top);
	
	public List getProviderByRegional(String type, Long iregionalid,int tip) ;
	public List queryProduct(String rzti, String ldti, String lgtp,int top,int tip);
	public List searchTicketByProid(Long iscenicid, String rzti, String lgtp) throws Exception;
	public PaginationSupport searchProduct(String scenicids,SearchKey keys,String rzti,String lgtp,int page,
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
	/**
	 * 
	 * @param pdtp
	 * @return   ����  ���� ��������� ��ȡ ��������������  Galsourceregiontab �� ���������� ������list�ŵ� Galsourceregiontab �µ� sceniclist ����
	 */
	
	public List getscenicSourceList(String pdtp);
	public List queryscenicSource(String pdtp);
	public List queryProduct(String rzti, String ldti, Long ibusinessid,int top,int tip);
	/**
	 * ��ҳ��ȡ��Ʊ��Ϣ����������������
	 * @param iscenIds
	 * @param rzti
	 * @param ldti
	 * @param ibusinessid
	 * @param top
	 * @param tip
	 * @return
	 */
	public List queryProductIndex(String iscenIds,String rzti, String ldti, Long ibusinessid,int top,int tip,boolean isAll);
	public PaginationSupport queryProductIndexs(String iscenIds,String rzti, String ldti, Long ibusinessid,int top,int tip,boolean isAll, int pageSize, int startIndex, String url);
	public List searchTicketByProid(Long iscenicid, String rzti, Long ibusinessid) throws Exception;
	public List searchTicketByProid(Long iscenicid,Long isscancode, String rzti, Long ibusinessid) throws Exception;
	public List searchTicketByProid(Long iscenicid, Long icrowdkindpiceid,String rzti, Long ibusinessid,String groupno) throws Exception;
	
	public List searchTicketByProid(Long iscenicid, String rzti, Long ibusinessid,String groupno) throws Exception;
	public Esbprovicerq findEsbprovicerq(Long iscenicid, Long ibusinessid);

	public List searchTimeticketByIscenicid(long parseLong, long l);

	public List searchTimeticketsizeByIscenicid(long parseLong);
	
	public Ticketxgz searchTicketRefused(Long ticketid) throws Exception;
	public MOrder queryOrder(Long orderId,String ddzt);
	public List<TOrder> queryTOrder(String orderId) throws Exception;
}
