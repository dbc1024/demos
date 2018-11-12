package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.line.dao.idao.ILineDao;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Lineproduct;

/**
 * ������չ�߼�
 * 
 * @author yangguang
 * 
 */
public class LineOrderPriceHandler implements InvocationHandler {

	private IOrderBusiness target;
	private ITicketDAO ticketDao;
	private ILineDao lineDao;

	public LineOrderPriceHandler(IOrderBusiness target) {
		super();
		this.target = target;
		ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
		lineDao = (ILineDao) SpringUtil.getBean("lineDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(target, args);
		OrderCombin combin = (OrderCombin) args[0];
		Custom custom = (Custom) ticketDao.get(Custom.class, combin.getMorder().getUsid());
		MOrder morder = combin.getMorder();
		morder.setZfmont(0.0);
		double deposit = 0.0;
		Long num = 0L;
		for (TOrder t : morder.getTorders()) {
			for (TOrderlist tl : t.getTorderlists()) {
				num=num+tl.getNumb();
				double signprice = 0.0;
				
				String ibusinessid="1";
				if(custom.getIbusinessid()!=1){
					ibusinessid="1";
				}else{
					ibusinessid=custom.getIbusinessid().toString();
				}
				
				Edmcrowdkindpricetab line_pric = ticketDao.getProductPrice(tl.getItickettypeid().toString(), ibusinessid , tl.getDtstartdate(),tl.getIcrowdkindid());
				signprice = line_pric.getMactualsaleprice();
				Lineproduct lineproduct = (Lineproduct) ticketDao.get(Lineproduct.class, tl.getItickettypeid());
				if (lineproduct.getDeposittype()!=null) {
					if (lineproduct.getDeposittype()==1L) {
						if (lineproduct.getDeposit()!=null) {
							if (lineproduct.getDeposit()>0.0) {
								deposit = lineproduct.getDeposit();
							}
						}
					}
					
				}
				
				if (lineproduct.getStatus()!=null&&lineproduct.getStatus().equals("1")) {
					//ִ�ж�ʱ��int num �� ��ȥ����, Long icrowdkindpriceid �� �۸�id,final String orid ������id
					lineDao.updateLineNumb(tl.getNumb().intValue(), line_pric.getIcrowdkindpriceid(),morder.getOrid());
				}
				
//				Edmcrowdkindpricetab corwdkindprice = ticketDao.getProductPrice(tl.getItickettypeid().toString(), custom.getIbusinessid().toString(), tl.getDtstartdate(), tl.getIcrowdkindid().toString(),
//						custom.getNote2());
				tl.setPric(signprice);
				tl.setYhamnt(tl.getYhnumb().intValue() * signprice);
				tl.setAmnt(tl.getNumb() * signprice);
				// yorder
				tl.getYorderlist().setPric(signprice);
				tl.getYorderlist().setYhamnt(tl.getYhnumb().intValue() * signprice);
				tl.getYorderlist().setAmnt(tl.getNumb() * signprice);
				for (TZorderlist tz : tl.getZorderlist()) {
					tz.setZyhnumb(tz.getZnumb() / tl.getNumb() * tl.getYhnumb());
					tz.setZyhamnt(tz.getZyhnumb() * tz.getZpric());
					// yorder
					tz.getYzorderlist().setZyhnumb(tz.getZnumb() / tl.getNumb() * tl.getYhnumb());
					tz.getYzorderlist().setZyhamnt(tz.getZyhnumb() * tz.getZpric());
				}
				t.setYhamnt(t.getYhamnt() + tl.getYhamnt());
				// yorder
				t.getYorder().setYhamnt(t.getYorder().getYhamnt() + tl.getYhamnt());
			}
			if(deposit!=0.0){
				t.setNoted("02");//��ʶ����֧��
				t.setZfmont(num*deposit-t.getYhamnt());;
				morder.setNoted("02");//����֧��
			}
			
			morder.setZfmont(t.getZfmont()+morder.getZfmont());
			morder.setYhamnt(morder.getYhamnt() + t.getYhamnt());
		}
		return result;
	}

}
