package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.book.hotel.dao.idao.IHotelDAO;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Hotelproduct;

public class HotelOrderPriceHandler implements InvocationHandler {
	private IOrderBusiness target;
	private IHotelDAO hotelDao;

	public HotelOrderPriceHandler(IOrderBusiness target) {
		super();
		this.target = target;
		hotelDao = (IHotelDAO) SpringUtil.getBean("hotelDao");
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = method.invoke(target, args);
		OrderCombin combin = (OrderCombin) args[0];
		Custom custom = (Custom) hotelDao.get(Custom.class, combin.getMorder().getUsid());
		MOrder morder = combin.getMorder();
		morder.setMont(0.0);
		morder.setZfmont(0.0);
		morder.setNoted("01");
		for (TOrder t : morder.getTorders()) {
			t.setMont(0.0);
			t.setZfmont(0.0);
			t.setNoted("01");
			Long productid = 0L;
			Long num = 0L;
			for (TOrderlist tl : t.getTorderlists()) {
				productid = tl.getItickettypeid();
				num=tl.getNumb();
				Edmcrowdkindpricetab price = hotelDao.getTicketPrice(tl.getItickettypeid().toString(), tl.getDtstartdate(), tl.getIcrowdkindid().toString(), custom.getIbusinessid().toString(),
						StringUtils.isEmpty(custom.getNote2()) ? "0000" : custom.getNote2());
				tl.setPric(price.getMactualsaleprice());
				tl.setYhamnt(tl.getYhnumb().intValue() * price.getMactualsaleprice());
				tl.setAmnt(tl.getNumb() * price.getMactualsaleprice());
				// yorder
				tl.getYorderlist().setPric(price.getMactualsaleprice());
				tl.getYorderlist().setYhamnt(tl.getYhnumb().intValue() * price.getMactualsaleprice());
				tl.getYorderlist().setAmnt(tl.getNumb() * price.getMactualsaleprice());
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
				t.setMont(tl.getAmnt()+t.getMont());
				t.setZfmont(tl.getAmnt()+t.getZfmont());
			}
			if(productid!=0){
				Hotelproduct hp = (Hotelproduct) hotelDao.get(Hotelproduct.class,productid);
				if(hp!=null&&hp.getDeposittype()==0){
					t.setZfmont(num*hp.getDeposit());
					t.setNoted("02");
					t.getYorder().setZfmont(t.getZfmont());
					t.getYorder().setNoted("02");
					morder.setNoted("02");
				}
			}
			morder.setMont(t.getMont()+morder.getMont());
			morder.setZfmont(t.getZfmont()+morder.getZfmont());
			morder.setYhamnt(morder.getYhamnt() + t.getYhamnt());
		}
		return result;
	}

}
