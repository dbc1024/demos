package com.ectrip.ec.report.system.datereport.dao.idao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.report.sales.Rsalecounttab;

public interface IChartDao extends IGenericDao {
	/**
	 * 
	 * Describe:������ͳ��ͼ����
	 * @author:chenxinhao
	 * @param type
	 * @return
	 * @throws Exception
	 * return:List<Map>
	 * Date:2013-1-22
	 */
	public List<Map> showlxschart(String type,int radiobutton1,String rzyear,String ldyear,String rzmonth,String ldmonth,String rzti,String ldti) throws Exception;
	/**
	 * 
	 * Describe:���ۻ���ͬ�ڶԱ�ͳ��ͼ����
	 * @author:chenxinhao
	 * @param type
	 * @param radiobutton1
	 * @param rzyear
	 * @param rzmonth
	 * @param rzti
	 * @param salecount
	 * @return
	 * @throws Exception
	 * return:List<Map>
	 * Date:2013-1-22
	 */
	public List<Map> showempchart(String type,int radiobutton1,String rzyear,String rzmonth,String rzti,Rsalecounttab salecount) throws Exception;
}

