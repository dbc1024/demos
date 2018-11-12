package com.ectrip.ec.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.order.QueryOrder;
import com.ectrip.ec.order.dao.idao.ITourcardTicketOrderDAO;
import com.ectrip.ec.order.service.iservice.ITourcardTicketOrderService;
import com.ectrip.sys.model.employee.Esfemployeetab;

@Service
public class TourcardTicketOrderService extends GenericService implements ITourcardTicketOrderService{
	
		private ITourcardTicketOrderDAO tourcardTicketOrderDAO;

		@Autowired
		public void setTourcardTicketOrderDAO(ITourcardTicketOrderDAO tourcardTicketOrderDAO) {
			this.tourcardTicketOrderDAO = tourcardTicketOrderDAO;
		}
		
		
		

		public PaginationSupport queryOrderInfoByPage(Esfemployeetab esfemployeetab, QueryOrder order, int page,
				int pageSize, String url,String cardNumber,String cardName,String profitNum) throws RuntimeException, Exception {
			
			return tourcardTicketOrderDAO.queryOrderInfoByPage(esfemployeetab, order, page, pageSize, url,cardNumber,cardName,profitNum);
		}

		public PaginationSupport queryAllOrder(Esfemployeetab esfemployeetab, QueryOrder order, int page, int pageSize,
				String url) throws Exception {
			
			return tourcardTicketOrderDAO.queryAllOrder(esfemployeetab, order, page, pageSize, url);
		}
}
