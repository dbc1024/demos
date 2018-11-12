/*package com.ectrip.ec.custag.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.custag.dao.idao.IHotThemeDAO;
import com.ectrip.ec.custag.model.HotModel;
public class HotArea extends SimpleTagSupport {

	private String item;
	private String index;
	private String type;
	private String size;
    private String top;
	public List getAreaList() {
		IHotThemeDAO themedao = (IHotThemeDAO) SpringUtil.getBean("hotthemeDao");
		List list = themedao.getHotArea(type,top);
		List arealist = new ArrayList();
		if (list != null && list.size() > 0) {
			for(int i=0;i<list.size();i++){
				Map map=(Map) list.get(i);
				HotModel h=new HotModel();
				h.setSzregionalid(Integer.parseInt(map.get("szregionalid".toUpperCase()).toString()));
				h.setSzregionalname(map.get("szregionalname".toUpperCase()).toString());
				arealist.add(h);
			}
		}
		return arealist;
	}

	@Override
	public void doTag() throws JspException, IOException {
		List list = getAreaList();
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