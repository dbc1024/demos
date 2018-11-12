package com.ectrip.ticket.sale.service.card.dao.idao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.balance.Useryfktype;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.card.Bindingprice;
import com.ectrip.ticket.model.card.Icconsumerecord;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.sale.service.card.model.GetCardsRequest;
import com.ectrip.ticket.sale.service.card.model.GetICItemsRequest;

/**
 * 
 * @author liujianwen
 */
public interface IOneCardDao extends IGenericDao{
	/**
	 * 
	 * Describe:���ݵ�����������ȡ������¼
	 * @author liujianwen
	 * @param thirdpartyOrid
	 * @return
	 * return:Icconsumerecord
	 * Date:2016-10-19
	 */
	public Icconsumerecord getTicketConsumeByThirdpartyOrid(String thirdpartyOrid);
	/**
	 * 
	 * Describe:��ȡ��Ʊ���ѵ�Ĭ��������Ŀ,�ü۸��������֡���Ʊ������ĿΪPXF
	 * @author liujianwen
	 * @return
	 * return:Edmcrowdkindpricetab
	 * Date:2016-10-18
	 */
	public Edmcrowdkindpricetab getTicketConsumePrice(String icrowdkindpricecode);
	/**
	 * Describe:��ѯ��Ƭ����
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:GetCardsResponse
	 * Date:2016-4-30
	 */
	public List<?> getCards(GetCardsRequest request);
	/**
	 * Describe:����բ��id��ȡ������Ŀ�б�
	 * @see com.ectrip.sale.service.card.dao.idao.IOneCardDao#getConsumePrices(com.ectrip.sale.service.card.model.GetCardsRequest)
	 * @param request
	 * @return
	 * @author liujianwen
	 * Date:2016-5-13
	 */
	public List<?> getConsumePrices(Long iaccessequipid, String date) ;
	/**
	 * 
	 * Describe:������Ʊ��id�Ҷ�Ӧ������բ��id
	 * @author liujianwen	
	 * @param iemployeeid
	 * @return
	 * return:Esbaccessequiptab
	 * Date:2016-5-13
	 */
	public Esbaccessequiptab getEsbaccessequiptabByIticketwinid(Long iticketwinid);
	/**
	 * Describe:��ѯ��ֵ��Ŀ
	 * @author liujianwen
	 * @param date
	 * @param icrowdkindpriceid
	 * @return
	 * return:List<?>
	 * Date:2016-5-10
	 */
	public List<?> getICItems(String date, Long icrowdkindpriceid);
	
	/**
	 * 
	 * Describe:ģ����ѯ
	 * @author liujianwen
	 * @param request
	 * @param count ����
	 * @return
	 * return:List<?>
	 * Date:2016-5-7
	 */
	public List<?> getCustoms(GetICItemsRequest request, int count);
	
	
	/**	
	 * Describe:����Ԥ������ϸ,˳������Vipbalance
	 * @author liujianwen
	 * @param yfk
	 * @return
	 * return:int
	 * Date:2016-5-5
	 */
	public void saveUseryfk(Useryfk yfk);
	/**
	 * Describe:�������ͽ��Ԥ������ϸ,˳������Vipbalancetype
	 * @author liujianwen
	 * @param yfk
	 * @return
	 * return:int
	 * Date:2016-5-5
	 */
	public void saveUseryfktype(Useryfktype yfk);
	/**
	 * Describe:��ȡԤ�������
	 * @author liujianwen
	 * @return
	 * return:double
	 * Date:2016-5-5
	 */
	public double getUseryfkBalance(String usid);
	/**
	 * Describe:��ȡ���ͽ�����
	 * @author liujianwen
	 * @return
	 * return:double
	 * Date:2016-5-5
	 */
	public double getUseryfktypeBalance(String usid);
	
	/**
	 * Describe:��ѯ���Ѽ�¼
	 * @author liujianwen
	 * @param usid �ڲ�����
	 * @param startDate ��ʼʱ�� 
	 * @param endDate ����ʱ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-10
	 */
	public List<?> getConsumeHistoryByUsid(String usid, String startDate, String endDate);
	
	/**
	 * Describe:��ѯ������Ѽ�¼
	 * @author liujianwen
	 * @param usid �ڲ�����
	 * @param startDate ��ʼʱ��  yyyy-MM-dd HH:mm:ss
	 * @param endDate ����ʱ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-10
	 */
	public List<?> getTopConsumeByUsid(String usid, int count);
	/**
	 * Describe:��ѯ��ֵ��¼
	 * @author liujianwen
	 * @param usid �ڲ�����
	 * @param startDate ��ʼʱ�� yyyy-MM-dd HH:mm:ss
	 * @param endDate ����ʱ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-10
	 */
	public List<?> getRechargeHistoryByUsid(String usid, String startDate, String endDate);
	
	/**
	 * Describe:��ѯ��ֵ��¼
	 * @author liujianwen
	 * @param usid �ڲ�����
	 * @param startDate ��ʼʱ��  yyyy-MM-dd HH:mm:ss
	 * @param endDate ����ʱ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-10
	 */
	public List<?> getTopRechargeByUsid(String usid, int count);
	
	/**
	 * Describe:������Ʊ����ԭ�û�������漰����������,�µ�Ʊ�Ž��洢��Icrechargecord��
	 * @author liujianwen
	 * @param usid ���ڲ�����
	 * @return ���Ϊnull����δ�����������򷵻���ʵ��usid
	 * return:String 
	 * Date:2016-5-11
	 */
	public Custom getBukaUsid(String usid);
	
	/**
	 * Describe:�ж��Ƿ񲹹���
	 * @author liujianwen
	 * @param usid
	 * @return
	 * return:boolean
	 * Date:2016-5-12
	 */
	public boolean isReplaceCard(String usid);
	
	/**
	 * Describe:����������Ŀ
	 * @author liujianwen
	 * @param icrowdkindpriceid
	 * @param iaccessequipid
	 * @return
	 * return:Esbaccessequiptab
	 * Date:2016-5-13
	 */
	public Edmcrowdkindpricetab getConsumeEdmcrowdkindpricetab(Long icrowdkindpriceid, Long iaccessequipid);
	
	/**
	 * Describe:����բ����ѡ������Ŀ
	 * @author liujianwen
	 * @param icrowdkindpriceid
	 * @param iaccessequipid
	 * @return
	 * return:Esbaccessequiptab
	 * Date:2016-5-13
	 */
	public Edmcrowdkindpricetab getFirstConsumeEdmcrowdkindpricetab(Long iaccessequipid);
	
	/**
	 * Describe:����������Ŀ��
	 * @author liujianwen
	 * @param icrowdkindpriceid
	 * @param igardengateid
	 * @return
	 * return:Bindingprice
	 * Date:2016-5-14
	 */
	public Bindingprice getBindingprice(Long icrowdkindpriceid, Long iaccessequipid);
	
	/**
	 * Describe:���Ҵ�ֵ���û�
	 * @author liujianwen
	 * @param usid
	 * @return
	 * return:Custom
	 * Date:2016-5-18
	 */
	public Custom getOnecardCustom(String usid);
	/**
	 * Describe:���ݶ����Ż�ȡ������Ϣ,СƱ��ӡ��
	 * @author liujianwen
	 * @param orid
	 * @param isSumRefund �Ƿ��ѯ�Ѿ������Ľ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-19
	 */
	public List<?> getConsumeByOrid(String orid, boolean isSumRefund);
	/**
	 * Describe:
	 * @author liujianwen
	 * @param orid
	 * @param isSumRefund �Ƿ��ѯ�Ѿ������Ľ��
	 * @return
	 * return:List<?>
	 * Date:2016-5-30
	 */
	public List<?> getRechargeByOrid(String orid, boolean isSumRefund) ;
	
	/**
	 * Describe:��ѯ�ѳ������
	 * @author liujianwen
	 * @param icconsumerecordid
	 * @return
	 * return:double
	 * Date:2016-5-30
	 */
	public double getRefundConsumeMoney(Long icconsumerecordid);
	
	/**
	 * Describe:��ѯ�ѳ������
	 * @author liujianwen
	 * @param icconsumerecordid
	 * @return
	 * return:double
	 * Date:2016-5-30
	 */
	public Map<String,Double> getRefundRechargeMoney(Long icrechargerecordid);
	
	/**
	 * Describe:����������Ŀ���û�,��ȡ�����һ�����Ѽ�¼
	 * @author liujianwen
	 * @return
	 * return:Icconsumerecord
	 * Date:2016-5-31
	 */
	public Icconsumerecord getRecentlyIcconsumerecord(Long icrowdkindpriceid, String usid);
	
	/**
	 * Describe:����һ��ͨ����
	 * @author liujianwen
	 * @param phone �绰
	 * @param seq ����
	 * @param mesg ϵͳ����
	 * @param values {1} ��Ӧ��ֵ,������˳������
	 * return:void
	 * Date:2016-6-17
	 */
	public void saveMbmessage(long seq, String phone, String mesg, String... values);
	
	/**
	 * *
	 * Describe: ����δ���˽��(��ʱֻ�������)
	 * @see com.ectrip.sale.service.card.dao.idao.IOneCardDao#getUseryfkBalance(String)
	 * @param usid
	 * @return
	 * @author liujianwen
	 * Date:2016-7-2
	 */
	@SuppressWarnings("rawtypes")
	public double getUncheckoutMoney(String usid) ;
	
	/**
	 * Describe:��ȡδ�����б�
	 * @author liujianwen
	 * @param usid
	 * @param onlyUncheckout �Ƿ�ֻ��δ���˵�
	 * @return
	 * return:List<?>
	 * Date:2016-7-4
	 */
	public List<?> getUncheckoutList(String usid, boolean onlyUncheckout) ;
	
	/**
	 * 
	 * Describe:��ȡ�û�Ѻ��
	 * @author liujianwen
	 * @return
	 * return:double
	 * Date:2016-9-22
	 */
	public double findDeposit(String usid);
	
	/**
	 * 
	 * Describe:����Ѻ��
	 * @author liujianwen
	 * @param usid
	 * @return
	 * return:double
	 * Date:2016-9-22
	 */
	public void finishDeposit(String usid);
	
}
