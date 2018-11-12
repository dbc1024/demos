/*package com.ectrip.ec.custag.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.custag.dao.idao.IHotThemeDAO;
import com.ectrip.ec.custag.model.Hotsale;

public class HotSaleTag extends SimpleTagSupport {

	private String item;
	private String index;
	private String type;
	private String size;
	private String top;

	public List getHotsale() {
		IHotThemeDAO themedao = (IHotThemeDAO) SpringUtil.getBean("hotthemeDao");
		List list = themedao.getHotSale(type, top);
		List salelist = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Hotsale sale = new Hotsale();
				sale.setProductid(Integer.parseInt(map.get("productid".toUpperCase()).toString()));
				sale.setProductname(map.get("productname".toUpperCase()).toString());
				sale.setSalenum(Integer.parseInt(map.get("salenum".toUpperCase()).toString()));
				sale.setIcrowdkindid(Integer.parseInt(map.get("icrowdkindid".toUpperCase()).toString()));
				sale.setIscenicid(Integer.parseInt(map.get("iscenicid".toUpperCase()).toString()));
				Object upadder=map.get("upadder".toUpperCase());
				Object upname=map.get("upfilename".toUpperCase());
				if(upadder!=null){
					sale.setUpadder(upadder.toString());
				}else{
					sale.setUpadder("");
				}
				if(upname!=null){
					sale.setUpfilename(upname.toString());
				}else{
					sale.setUpfilename("");
				}
				sale.setListingprice(Double.parseDouble(map.get("listingprice".toUpperCase()).toString()));
				sale.setMactualsaleprice(Double.parseDouble(map.get("mactualsaleprice".toUpperCase()).toString()));
				sale.setDiscount(String.valueOf(Math.round(sale.getListingprice()/sale.getMactualsaleprice()*100)/10));
				salelist.add(sale);
			}
		}
		return salelist;
	}

	@Override
	public void doTag() throws JspException, IOException {
		List list = getHotsale();
		getJspContext().setAttribute(size, list.size());
		for (int i = 0; i < list.size(); i++) {
			Object s = list.get(i);
			getJspContext().setAttribute(item, s);
			getJspContext().setAttribute(index, i);
			getJspBody().invoke(null);
		}
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}
}
*/