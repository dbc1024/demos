package com.ectrip.ec.order.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.job.ijob.TaskLogService;
import com.ectrip.ec.order.dao.idao.IMOrderDAO;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.ConsumeOrderRequest;
import com.ectrip.hqyt.model.response.JSONInvoice;

public class OrderConsumeService extends TaskLogService {
	
	public IMOrderDAO morderdao;
	 
	public IMOrderDAO getMorderdao() {
		return morderdao;
	}

	public void setMorderdao(IMOrderDAO morderdao) {
		this.morderdao = morderdao;
	}


	/**
	 * ӆ�κ��N* Describe:
	 * 
	 * @return
	 * @author koka Date:2017-06-27
	 */
	@SuppressWarnings("rawtypes")
	synchronized public int jobrun() {
		System.out.println("ӆ�κ��N��ʼִ��" + Tools.getDayTimes());
		String today = Tools.getDate(Tools.getDays(), -1);
		try {
			List list=morderdao.getNeedConsumeMOrderList(today);
			if(list!=null&&list.size()>0){
				for(int i=0;i <list.size();i++){
					Map map=(Map)list.get(i);
					String invoiceId=map.get("dtstartdate").toString();
					HqytClient client = new HqytClient();
					ConsumeOrderRequest request = new ConsumeOrderRequest();
			        request.setId(Long.parseLong(invoiceId));
			        JSONInvoice refundInvoice = client.consumeOrder(request);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		System.out.println("ӆ�κ��N����ִ��" + Tools.getDayTimes());
		return 1;
	}
	public static void main(String[] args) {
		
		try {
			//List list=morderdao.getNeedConsumeMOrderList(today);
			 HqytClient client = new HqytClient();
				ConsumeOrderRequest request = new ConsumeOrderRequest();
		        request.setId(646L);
		        JSONInvoice refundInvoice = client.consumeOrder(request);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

}
