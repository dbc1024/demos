package com.ectrip.ec.report.system.datereport.service.iservice;

import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.ec.model.report.sales.Rsalecounttab;

public interface IChartService extends IGenericService {
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

