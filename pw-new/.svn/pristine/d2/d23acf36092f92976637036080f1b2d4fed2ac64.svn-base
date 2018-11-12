package com.ectrip.ticket.statistics.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.usernumjf.Statisticsupdate;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.statistics.StatisticsModel;

public interface IStatisticsService{
    
		public List queryStatistics(StatisticsModel model); 

	    public void statisticsThread(StatisticsModel model);
	    
	    public PaginationSupport getUserjifenList(StatisticsModel model,int pageSize, int startIndex,String url,String usid);
	    public Usernumjf queryJifenByUsid(String starttime,String endtime,String usid);
	    public Usernumjf getUserJf(String iscenicid,String starttime,String endtime,String usid,Long jflb);
	    public void saveStatisticsInfo(Statisticsupdate stupdate);
	    public PaginationSupport getStatisUpdatelist(int pageSize, int startIndex,String url,String iscenicid,String usid);
	    public void updateUserjfByEmployee(Statisticsupdate stupdate);
	    
	    /**
	     * 
	     * Describe:判断唯一性
	     * @author:lijingrui
	     * @param model
	     * @return
	     * return:boolean
	     * Date:2014-12-18
	     */
	    public boolean volidateUserjf(StatisticsModel model);
	    
	    /**
	     * 
	     * Describe:添加用户积分
	     * @author:lijingrui
	     * @param model
	     * @param iemployeeid
	     * return:void
	     * Date:2014-12-18
	     */
	    public void saveJfStatisticsupdate(StatisticsModel model,Long iemployeeid);
	    
	    public Numjifenset getNumjifenset(String iscenicid);
	    
	    /**
	     * 获取积分消费规则
	     * @param itickettypeid
	     * @param nid
	     * @param icrowid
	     * @param ibusinessid
	     * @param date
	     * @return
	     */
	    public Numjifensetlist getSalesRule(Long itickettypeid, Long nid,Long icrowid,Long ibusinessid,String date);
	    
	    public Usernumjf getJifenByUser(String starttime,String endtime,String usid,Long jflb,Long isecnicid);
	    
	    public Numjifensetlist getSalesRule(String itickettypeid,String nid);
}

