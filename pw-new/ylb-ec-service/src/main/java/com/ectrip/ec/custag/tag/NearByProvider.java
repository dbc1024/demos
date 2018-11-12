/*package com.ectrip.ec.custag.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.hotel.dao.idao.IHotelDAO;
import com.ectrip.ec.custag.dao.idao.IHotThemeDAO;
import com.ectrip.ec.custag.model.Hotsale;

public class NearByProvider extends SimpleTagSupport {

	private String item;
	private String index;
	private String type;
	private String size;
	private String iscenicid;

	public List getNearByprovider() {
		IHotThemeDAO themedao = (IHotThemeDAO) SpringUtil.getBean("hotthemeDao");
		IHotelDAO hoteldao = (IHotelDAO)SpringUtil.getBean("hotelDao");
		List list = themedao.getNearByProvider(type, iscenicid);
		List salelist = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Hotsale sale = new Hotsale();
				sale.setIscenicid(Integer.parseInt(map.get("iscenicid".toUpperCase()).toString()));
				sale.setSzscenicname(map.get("szscenicname".toUpperCase()).toString());
				if(type.equals("06")){
					sale.setStrzxjb(map.get("strzxjb".toUpperCase()).toString());
					if(map.get("grade".toUpperCase()).toString().equals("δ��")){
						sale.setGrade(map.get("strzxjb".toUpperCase()).toString());
					}else{
						sale.setGrade(map.get("grade".toUpperCase()).toString());
					}
				}else{
					sale.setGrade(map.get("grade".toUpperCase()).toString()==null?"":map.get("grade".toUpperCase()).toString());
				}
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
				String hql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab     p  where price.itickettypeid = product.itickettypeid and price.ibusinessid = 1 and price.note1 = '0000'  and p.iscenicid = "
						+ sale.getIscenicid()
						+ " and p.iscenicid = product.iscenicid and '"
						+ Tools.getDays()
						+ "' between  price.startdata and  price.enddata order by price.mactualsaleprice";
				List list2 = hoteldao.find(hql);
				if(list2!=null&&!list2.isEmpty()){
					Map map2 = (Map) list2.get(0);
					sale.setListingprice(Double.parseDouble(map2.get("mactualsaleprice").toString()));
				}else{
					sale.setListingprice(0D);
					list.remove(map);
					i--;
					continue;
				}
				salelist.add(sale);
				if(salelist.size()==6){
					break;
				}
			}
		}
		return salelist;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		List list = getNearByprovider();
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

	public String getIscenicid() {
		return iscenicid;
	}

	public void setIscenicid(String iscenicid) {
		this.iscenicid = iscenicid;
	}
	
}
*/