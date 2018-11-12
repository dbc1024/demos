package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;

/**
 * ������չ�߼�
 * 
 * @author yangguang 2014-11-23
 * 
 */
public class OrderPriceHandler implements InvocationHandler {

	private IOrderBusiness target;
	private ITicketDAO ticketDao;
	
	

	public OrderPriceHandler(IOrderBusiness target) {
		super();
		this.target = target;
		ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		Object result = method.invoke(target, args);
		//System.out.println("OrderPriceHandler invoke������");
		OrderCombin combin = (OrderCombin) args[0];
		Custom custom = (Custom) ticketDao.get(Custom.class, combin.getMorder()
				.getUsid());
		MOrder morder = combin.getMorder();
		morder.setMont(0.0);
		morder.setYhamnt(0.0);
		morder.setZfmont(0.0);
		for (TOrder t : morder.getTorders()) {
			for (TOrderlist tl : t.getTorderlists()) {
				// ��ȡ�۸����
				String note2 = ticketDao.searchJgfz(custom.getUsid(),
						t.getId().getIscenicid());
				Edmcrowdkindpricetab corwdkindprice = ticketDao
						.getProductPrice(tl.getItickettypeid().toString(),
								custom.getIbusinessid().toString(), tl
										.getDtstartdate(), tl.getIcrowdkindid()
										.toString(), note2);

				// Edmcrowdkindpricetab corwdkindprice =
				// ticketDao.getProductPrice(tl.getItickettypeid().toString(),
				// custom.getIbusinessid().toString(), tl.getDtstartdate(),
				// tl.getIcrowdkindid().toString(),
				// custom.getNote2());
				if(morder.getOrdersource()!=null&&morder.getOrdersource().equals("lykgp")){
					tl.setPric(0.0);
				}else {
					tl.setPric(corwdkindprice.getMactualsaleprice());
				}


				tl.setYhamnt(tl.getYhnumb().intValue()
						* corwdkindprice.getMactualsaleprice());
				tl.setAmnt(tl.getNumb() * corwdkindprice.getMactualsaleprice());
				// yorder
				tl.getYorderlist()
						.setPric(corwdkindprice.getMactualsaleprice());
				tl.getYorderlist().setYhamnt(
						tl.getYhnumb().intValue()
								* corwdkindprice.getMactualsaleprice());
				tl.getYorderlist().setAmnt(
						tl.getNumb() * corwdkindprice.getMactualsaleprice());
				List sontickets = ticketDao.getSonticketlist(corwdkindprice
						.getIcrowdkindpriceid());
				for (TZorderlist tz : tl.getZorderlist()) {
					for (int i = 0; i < sontickets.size(); i++) {
						Edmticketcomposepricetab sonticket = (Edmticketcomposepricetab) sontickets
								.get(i);
						if (tz.getIztickettypeid().equals(
								sonticket.getItickettypeid())) {
							tz.setZpric(sonticket.getMactualsaleprice());
						}
					}
					tz.setZyhnumb(tz.getZnumb() / tl.getNumb() * tl.getYhnumb());
					tz.setZyhamnt(tz.getZyhnumb() * tz.getZpric());
					// yorder
					tz.getYzorderlist().setZyhnumb(
							tz.getZnumb() / tl.getNumb() * tl.getYhnumb());
					tz.getYzorderlist().setZyhamnt(
							tz.getZyhnumb() * tz.getZpric());
				}
				t.setMont(t.getMont() + tl.getAmnt());
				t.setYhamnt(t.getYhamnt() + tl.getYhamnt());
				if(morder.getOrdersource()!=null&&morder.getOrdersource().equals("lykgp")){
					t.setZfmont(0.0);
				}else {
					t.setZfmont(t.getMont() - t.getYhamnt());
				}

				// yorder
				t.getYorder().setMont(t.getMont());
				t.getYorder().setYhamnt(t.getYhamnt());
				if(morder.getOrdersource()!=null&&morder.getOrdersource().equals("lykgp")){
					t.getYorder().setZfmont(0.0);
				}else {
					t.getYorder().setZfmont(t.getZfmont());
				}

			}
			morder.setMont(morder.getMont() + t.getMont());
			morder.setYhamnt(morder.getYhamnt() + t.getYhamnt());
			//����û������ο���Ʊ ֱ���޸�֧�����Ϊ0 update by zyj
			if(morder.getOrdersource()!=null&&morder.getOrdersource().equals("lykgp")){
				morder.setZfmont(0.0);
			}else{
				morder.setZfmont(morder.getZfmont() + t.getZfmont());
			}


		}
		return result;
	}

}
