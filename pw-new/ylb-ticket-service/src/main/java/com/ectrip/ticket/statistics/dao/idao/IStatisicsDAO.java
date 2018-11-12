package com.ectrip.ticket.statistics.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.statistics.StatisticsModel;

public interface IStatisicsDAO extends IGenericDao{

    public void updateStatistics(Usernumjf jifen);
    
    
    /**
     * 
     * Describe:获取所有旅行社分社
     * @auth:yangguang
     * @return
     * return:List
     * Date:2012-4-12
     */
    public List queryStatisticsUser(String type);
    
    
    
    /**
     * 
     * Describe:根据上级用户查询下级用户
     * @auth:yangguang
     * @param usid
     * @return
     * return:List
     * Date:2012-4-13
     */
    public List queryStatisticsUserBySusid(String usid);
    
    /**
     * 
     * Describe:获取数量积分
     * @auth:yangguang
     * @return
     * return:List
     * Date:2012-4-12
     */
    public List getQueryStatistics();
    
    /**
     * 
     * Describe:根据服务商获取积分规则
     * @auth:yangguang
     * @param iscenicid
     * @return
     * return:Numjifenset
     * Date:2012-4-12
     */
    public Numjifenset getStatisicsRule(String iscenicid);

    /**
     * 
     * Describe:获取积分明细
     * @auth:yangguang
     * @param ruleid
     * @param starttime
     * @param endtime
     * @return
     * return:List
     * Date:2012-4-12
     */
    public List getStatisicsRuleDetail(String ruleid, String starttime,String endtime);
    
    /**
     * 
     * Describe:根据用户服务商统计出用户可以划为数量积分的票的总数
     * @auth:yangguang
     * @param usid
     * @param startDate
     * @param endDate
     * @return
     * return:int
     * Date:2012-4-12
     */
    public int getTicketNumber(String usid,StatisticsModel model,String itickettypeid);
    
    
    /**
     * 
     * Describe:根据月份查询用户数量积分
     * @auth:yangguang
     * @param starttime
     * @param endtime
     * @param usid
     * @param nid
     * @param jflb
     * @param isecnicid
     * @return
     * return:Usernumjf
     * Date:2012-5-21
     */
    public Usernumjf getUpMonthJifen(String starttime,String endtime,String usid,Long nid,Long jflb,Long isecnicid);
    
    public PaginationSupport getUserjifenList(String starttime, String endtime, int pageSize, int startIndex, String url, String usid,String iscenicid,int jflb);
    public Usernumjf getJifenByUser(String starttime,String endtime,String usid,Long jflb,Long isecnicid);
    public Numjifenset getNumjifenset(String iscenicid);
    public Numjifensetlist getSalesRule(String itickettypeid,String nid);
    
    public Usernumjf queryJifenByUsid(String starttime,String endtime,String usid);
    
    
    /**
     * 获取积分规则,以产品为单位.
     * @param itickettypeid
     * @param nid
     * @param icrowdid
     * @param usid
     * @return
     */
    public Numjifensetlist getSalesRule(Long itickettypeid,Long nid, Long icrowdid,String usid) ;
    
    public int getTicketNumber(String usid, StatisticsModel model, Numjifensetlist detail);
    
    
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
    
    public List queryJifenListByUsid(String starttime, String endtime, String usid);
    public Usernumjf queryJifenByUsid(String starttime, String endtime, String usid, int nid);
    public PaginationSupport getStatislist(int pageSize, int startIndex,String url,String usid);
    public List getOrderConsume(String orid);
    
    /**
     * 
     * Describe:判断唯一性
     * @author:lijingrui
     * @param model
     * @param startDate
     * @param endDate
     * @return
     * return:boolean
     * Date:2014-12-18
     */
    public boolean volidateUserjf(StatisticsModel model,String startDate,String endDate);
    public PaginationSupport getStatisUpdatelist(int pageSize, int startIndex,String url,String iscenicid,String usid);
}

