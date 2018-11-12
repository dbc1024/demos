/*package com.ectrip.ec.custag.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.custag.dao.idao.IHotThemeDAO;

public class HotTheme extends SimpleTagSupport {

	private String item;
	private String index;
	private String type;
	private String size;

	public List getThemeList() {
		IHotThemeDAO themedao = (IHotThemeDAO) SpringUtil.getBean("hotthemeDao");
		List list = themedao.getHotTheme(type);
		List themelist = new ArrayList();
		if (list != null && list.size() > 0) {
			HashSet set = new HashSet();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String themes = (String) map.get("topics");
				if (themes != null && !themes.equals("")) {
					String[] array = themes.split(",");
					for (int j = 0; j < array.length; j++) {
						set.add(array[j]);
					}
				}
			}
			themelist.addAll(Arrays.asList(set.toArray()));
		}
		return themelist;
	}

	@Override
	public void doTag() throws JspException, IOException {
		List list = getThemeList();
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

}
*/