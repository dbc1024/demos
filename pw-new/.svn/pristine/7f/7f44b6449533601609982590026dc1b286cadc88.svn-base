package com.ectrip.ticket.sale.service.card.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ticket.sale.service.card.core.Response;
import com.ectrip.ticket.sale.service.card.model.ConsumeRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardStatusRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardsRequest;
import com.ectrip.ticket.sale.service.card.model.GetCardsResponse;
import com.ectrip.ticket.sale.service.card.model.GetICItemsRequest;
import com.ectrip.ticket.sale.service.card.model.GetICItemsResponse;
import com.ectrip.ticket.sale.service.card.model.GetOrderListRequest;
import com.ectrip.ticket.sale.service.card.model.GetOrderListResponse;
import com.ectrip.ticket.sale.service.card.model.GetSzticketprintnosRequest;
import com.ectrip.ticket.sale.service.card.model.GetSzticketprintnosResponse;
import com.ectrip.ticket.sale.service.card.model.PrintReceiptRequest;
import com.ectrip.ticket.sale.service.card.model.RefundRequest;
import com.ectrip.ticket.sale.service.card.model.SaleRequest;

public interface IOneCardService extends IGenericService {
	
	/////////////////////////////////////////////////////////////////
	public static final String CONSUME_ONECARD = "000";//һ��ͨ����
	public static final String CONSUME_TICKET = "PXF";//������������Ʊ����
	//////////////////////////////////////////////////////////////////
	/**
	 * Describe:��ѯ��Ƭ����
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:GetCardsResponse
	 * Date:2016-4-30
	 */
	public GetCardsResponse getCards(GetCardsRequest request);
	
	/**
	 * Describe:����������ȡΨһƱ��
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:GetSzticketprintnosResponse
	 * Date:2016-5-3
	 */
	public GetSzticketprintnosResponse getSzticketprintnos(GetSzticketprintnosRequest request);
	/**
	 * Describe:��ѯ��ֵ��Ŀ�б�
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:GetICItemsCardsResponse
	 * Date:2016-5-3
	 */
	public GetICItemsResponse getICItems(GetICItemsRequest request);
	
	/**
	 * Describe:�ۿ�
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-4
	 */
	public Response sale(SaleRequest request);
	
	/**
	 * Describe:��ֵ
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-4
	 */
	public Response  recharge(SaleRequest request);
	
	
	/**
	 * Describe:������ֵ,�˿�������
	 * @author liujianwen
	 * @return
	 * return:String
	 * Date:2016-5-5
	 */
	public String createRechargeOrid();
	/**
	 * Describe:�������Ѷ�����
	 * @author liujianwen
	 * @return
	 * return:String
	 * Date:2016-5-5
	 */
	public String createConsumeOrid();
	
	/**
	 * Describe:��ȡ����next
	 * @author liujianwen
	 * @return
	 * return:long
	 * Date:2016-5-5
	 */
	public long getSeq(String seqName) ;
	
	
	/**
	 * Describe:�ж��û��Ƿ�����Լ�������Ψһ��Ʊ��
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-7
	 */
	public Response cardIsExist(GetCardStatusRequest request);
	
	/**
	 * 
	 * Describe:���� ���š�������֤���š��ֻ��š��ڲ����ŵ�ģ����ѯǰ20����¼
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-7
	 */
	public Response getCustoms(GetICItemsRequest request);
	
	/**
	 * Describe:��ʧ���
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-11
	 */
	public Response reportTheLoss(GetICItemsRequest request);
	/**
	 * Describe:����
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-12
	 */
	public Response replaceCard(SaleRequest request);
	
	/**
	 * Describe:�˿�
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-12
	 */
	public Response refundedCard(SaleRequest request);
	
	/**
	 * Describe:��ѯ԰�Ű󶨵�������Ŀ
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:GetCardsResponse
	 * Date:2016-4-30
	 */
	public GetCardsResponse getConsumePrices(GetCardsRequest request);
	
	/**
	 * Describe:����
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-13
	 */
	public Response consume(ConsumeRequest request);
	
	/**
	 * ��ѯ������¼
	 * @param request
	 * @return PosLoginResponse res
	 */
	public GetOrderListResponse getOrderList(GetOrderListRequest request);

	/**
	 * Describe:��ӡСƱ
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-24
	 */
	public Response printReceipt(PrintReceiptRequest request);
	/**
	 * Describe:���ݶ����Ų�ѯ��ϸ
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-25
	 */
	public Response getConsumeByOrid(PrintReceiptRequest request);
	/**
	 * Describe:���ݶ����Ų�ѯ��ϸ
	 * @author liujianwen
	 * @param request
	 * @return
	 * return:Response
	 * Date:2016-5-25
	 */
	public Response getRechargeByOrid(PrintReceiptRequest request);
	
	/**
	 * Describe:��������
	 * @author liujianwen
	 * @return
	 * return:Response
	 * Date:2016-5-26
	 */
	public Response refund(RefundRequest request);
	
	/**
	 * Describe:����բ��id����������Ŀ
	 * @author liujianwen
	 * @param Iaccessequipid
	 * @return
	 * return:List<?>
	 * Date:2016-6-20
	 */
	public List<?> getConsumePricesByIaccessequipid(Long iaccessequipid);

	/**
	 * 
	 * Describe:��ӡ��ʷ���Ѽ�¼
	 * @author liujianwen
	 * @param parseObject
	 * @return
	 * return:Response
	 * Date:2016-9-23
	 */
	public Response printHistoryConsumes(GetOrderListRequest parseObject);
}
