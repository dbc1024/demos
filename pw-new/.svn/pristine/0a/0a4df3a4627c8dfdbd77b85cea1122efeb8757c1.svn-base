/*package com.ectrip.ec.book.rentrl.news.custag.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.book.rentrl.news.custag.dao.idao.IHotCarTypeDao;
import com.ectrip.ec.book.rentrl.news.custag.model.HotCarTypeModel;

*//**
 * ���ų��ͱ�ǩ
 * 
 * HotCarType
 * 
 * hejiahua hejiahua 2013-12-16 ����03:16:47
 * 
 * @version 1.0.0
 * 
 *//*
public class HotCarType extends SimpleTagSupport {
	private String item;
	private String index;
	private String type;
	private String size;
	private String top;

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public List getAreaList() {
		IHotCarTypeDao hotCarTypeDao = (IHotCarTypeDao) SpringUtil
				.getBean("hotCarTypeDao");
		List list = hotCarTypeDao.findHotCarType(top);
		List areaList = new ArrayList();
		if (list != null) {
			try {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					HotCarTypeModel hotCarTypeModel = new HotCarTypeModel();
					hotCarTypeModel.setIscenicid(map.get("iscenicid".toUpperCase())
							.toString());
					hotCarTypeModel.setSzscenicname(map.get(
							"szscenicname".toUpperCase()).toString());
					hotCarTypeModel.setUpfileaddr("");
					hotCarTypeModel.setUpfilename("");
					if (map.get("upadder".toUpperCase()) != null) {
						hotCarTypeModel.setUpfileaddr(map.get(
								"upadder".toUpperCase()).toString());
					} else {
						hotCarTypeModel.setUpfileaddr("");
					}
					if (map.get("upfilename".toUpperCase()) != null) {
						hotCarTypeModel.setUpfilename(map.get(
								"upfilename".toUpperCase()).toString());
					} else {
						hotCarTypeModel.setUpfilename("");
					}
					areaList.add(hotCarTypeModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return areaList;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
*/