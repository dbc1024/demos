package com.ectrip.ec.order.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.book.rentrl.news.dao.idao.ICarlineDao;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.order.common.business.IOrderBusiness;
import com.ectrip.ec.order.common.business.impl.OrderCombin;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class CarTypeOrderPriceHandler  implements InvocationHandler {
	private IOrderBusiness target;
	private ICarlineDao carlineDao;
	
	public CarTypeOrderPriceHandler(IOrderBusiness target) {
		super();
		this.target = target;
		carlineDao = (ICarlineDao) SpringUtil.getBean("carlineDao");
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = method.invoke(target, args);
		OrderCombin combin = (OrderCombin) args[0];
		Custom custom = (Custom) carlineDao.get(Custom.class, combin.getMorder().getUsid());
		MOrder morder = combin.getMorder();
		morder.setMont(0.0);
		morder.setZfmont(0.0);
		for (TOrder t : morder.getTorders()) {
			t.setMont(0.0);
			t.setZfmont(0.0);
			Long productid = 0L;
			Long num = 0L;
			for (TOrderlist tl : t.getTorderlists()) {
				productid = tl.getItickettypeid();
				num=tl.getNumb();
				Edmcrowdkindpricetab price = carlineDao.getCartypePrice(tl.getItickettypeid().toString(), tl.getDtstartdate(), tl.getIcrowdkindid().toString(), custom.getIbusinessid().toString(),
						custom.getNote2());
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
				Edmtickettypetab product = (Edmtickettypetab) carlineDao.get(Edmtickettypetab.class, productid);
				if(product!=null&&product.getMnominalfee()!=0){
					t.setZfmont(num*product.getMnominalfee());
					t.setNoted("02");//��ʶ����֧����
					morder.setNoted("02");//����֧��
				}
			}
			morder.setMont(t.getMont()+morder.getMont());
			
			morder.setZfmont(t.getZfmont()+morder.getZfmont());
			morder.setYhamnt(morder.getYhamnt() + t.getYhamnt());
		}
		return result;
	}

}
