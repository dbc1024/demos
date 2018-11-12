/*package com.ectrip.ec.book.rentrl.news.custag.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.ec.book.rentrl.news.custag.dao.idao.IHotCarTypeDao;
import com.ectrip.ec.book.rentrl.news.custag.model.TJLineModel;


 * �Ƽ��⳵·��
 
public class TJLineTag extends SimpleTagSupport {

	private String item;
	private String index;
	private String type;
	private String size;
	private String top;

	public List getAreaList() {
		IHotCarTypeDao hotCarTypeDao = (IHotCarTypeDao) SpringUtil
				.getBean("hotCarTypeDao");
		List list = hotCarTypeDao.findTJCarType(top);
		List areaList = new ArrayList();
		if (list != null) {
			try {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					TJLineModel tjLineModel = new TJLineModel();
					tjLineModel.setItickettypeid(map.get(
							"Itickettypeid".toUpperCase()).toString());
					tjLineModel.setIscenicid(map.get("Iscenicid".toUpperCase())
							.toString());
					tjLineModel.setBycategorytype(map.get(
							"Bycategorytype".toUpperCase()).toString());
					tjLineModel.setJsprice(map.get("Jsprice".toUpperCase())
							.toString());
					tjLineModel.setMileage(map.get("Mileage".toUpperCase())
							.toString());
					tjLineModel.setMnominalfee(map.get("Mnominalfee".toUpperCase())
							.toString());
					tjLineModel.setRegion(map.get("Region".toUpperCase())
							.toString());
					tjLineModel.setScenictype(map.get("Scenictype".toUpperCase())
							.toString());
					tjLineModel.setSzdata(map.get("Szdata".toUpperCase())
							.toString());
					if (tjLineModel.getSzdata().equals("0")) {
						tjLineModel.setSzdata("���˾��");
					} else {
						tjLineModel.setSzdata("�����˾��");
					}
					tjLineModel.setSzgrade(map.get("Szgrade".toUpperCase())
							.toString());
					tjLineModel.setSzscenicname(map.get(
							"Szscenicname".toUpperCase()).toString());
					tjLineModel.setSztickettypename(map.get(
							"Sztickettypename".toUpperCase()).toString());
					areaList.add(tjLineModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return areaList;
	}

	@Override
	public void doTag() throws JspException, IOException {
		List areaList = getAreaList();
		getJspContext().setAttribute("size", areaList.size());
		for (int i = 0; i < areaList.size(); i++) {
			Object o = areaList.get(i);
			getJspContext().setAttribute(item, o);
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

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}
}
*/