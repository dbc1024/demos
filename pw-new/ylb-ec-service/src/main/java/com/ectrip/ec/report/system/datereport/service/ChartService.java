package com.ectrip.ec.report.system.datereport.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.service.GenericService;
import com.ectrip.ec.model.report.sales.Rsalecounttab;
import com.ectrip.ec.report.system.datereport.dao.idao.IChartDao;
import com.ectrip.ec.report.system.datereport.service.iservice.IChartService;

public class ChartService extends GenericService implements IChartService {
	IChartDao chartDao;

	public IChartDao getChartDao() {
		return chartDao;
	}

	public void setChartDao(IChartDao chartDao) {
		this.chartDao = chartDao;
	}
	/**
	 * *
	 * Describe:������ͳ��ͼ����
	 * @see com.ectrip.report.system.datereport.service.iservice.IChartService#showlxschart(java.lang.String)
	 * @param type
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2013-1-22
	 */
	public List<Map> showlxschart(String type,int radiobutton1,String rzyear,String ldyear,String rzmonth,String ldmonth,String rzti,String ldti) throws Exception{
		return this.chartDao.showlxschart(type, radiobutton1, rzyear, ldyear, rzmonth, ldmonth, rzti, ldti);
	}
	/**
	 * *
	 * Describe:���ۻ���ͬ�ڶԱ�ͳ��ͼ����
	 * @see com.ectrip.report.system.datereport.service.iservice.IChartService#showempchart(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, com.ectrip.model.report.sales.Rsalecounttab)
	 * @param type
	 * @param radiobutton1
	 * @param rzyear
	 * @param rzmonth
	 * @param rzti
	 * @param salecount
	 * @return
	 * @throws Exception
	 * @author chenxinhao
	 * Date:2013-1-22
	 */
	public List<Map> showempchart(String type,int radiobutton1,String rzyear,String rzmonth,String rzti,Rsalecounttab salecount) throws Exception{
		return this.chartDao.showempchart(type, radiobutton1, rzyear, rzmonth, rzti, salecount);
	}
}

