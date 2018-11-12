package com.ectrip.ticket.sale.service.card.webservice;




import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.ticket.sale.service.card.model.GetEsbscenicareatabResponse;
import com.ectrip.ticket.sale.service.card.model.PosLoginRequest;
import com.ectrip.ticket.sale.service.card.model.PosLoginResponse;
import com.ectrip.ticket.sale.service.card.model.TestRequest;
import com.ectrip.ticket.sale.service.card.model.TestResponse;
import com.ectrip.ticket.sale.service.card.service.iservice.IPosWebService;

public class PosWebservice {
//	@Autowired
//	private IPosWebService posWebService;
//	
//	public IPosWebService getPosWebService() {
//		return posWebService;
//	}
//	
//	@Autowired
//	public void setPosWebService(IPosWebService posWebService) {
//		this.posWebService = posWebService;
//	}

	/**
	 * ���Է���
	 * @param data
	 * @return
	 */
	public Object test(String data){
		TestRequest spr = JSON.parseObject(data, TestRequest.class);
		System.out.println(spr.getTargetClass());
		System.out.println(spr.getMethod());
		TestResponse skr = new TestResponse();
		skr.setCode("0000");
		skr.setDescribe("���յ�ֵ��:");
		return skr;
	}
	
	/**
	 * ��ȡ�������б�
	 * @param request
	 * @return GetEsbscenicareatabResponse res
	 */
	public Object getScenicareas(String request){
		GetEsbscenicareatabResponse res = null;
		try {
			System.out.println("getBean");
			IPosWebService service = (IPosWebService)SpringUtil.getBean("posWebService");
			res = service.getScenicList();
			System.out.println("ready return res");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new GetEsbscenicareatabResponse();
			res.setCode("2001");
			res.setDescribe("��ѯ����");
			return res;
		}
	}
	
	/**
	 * POS����½
	 * @param request
	 * @return PosLoginResponse res
	 */
	public Object login(String request){
		PosLoginResponse res = null;
		try {
			IPosWebService service = (IPosWebService) SpringUtil.getBean("posWebService");
			res = service.posLogin(JSON.parseObject(request, PosLoginRequest.class));
			System.out.println("login() ready return");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res = new PosLoginResponse();
			res.setCode("2001");
			res.setDescribe("��ѯ����");
			return res;
		}
	}
}

