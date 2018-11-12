package com.ectrip.ticket.statistics.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.report.sales.Rcgroupsalemonthtab;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.statistics.StatisticsModel;
import com.ectrip.ticket.statistics.dao.idao.IStatisicsDAO;

@Repository
public class StatisicsDAO extends GenericDao implements IStatisicsDAO {

	public List getQueryStatistics() {
		String hql = "";
		return null;
	}

	public List queryStatisticsUser(String type) {
		String hql = "from Custom  c where c.lgtp='02'  and c.status='01' and c.ttlb='01' and (select ustp from Custom c1 where c1.usid=c.susid)='01'";
		if (type.equals("02")) {
			hql += " and c.ustp='02'";
		} else {
			hql += " and c.ustp='01'";
		}
		return find(hql);
	}

	public List queryStatisticsUserBySusid(String usid) {
		String hql = "from Custom  c where c.lgtp='02'  and c.status='01' and c.ttlb='01' and c.susid='" + usid + "'";
		return find(hql);
	}

	public void updateStatistics(Usernumjf jifen) {
		String hql = "from Usernumjf where usid='" + jifen.getUsid() + "' and nid=" + jifen.getNid() + " and stdt='" + jifen.getStdt() + "' and etdt='" + jifen.getEtdt() + "' and jflb="
				+ jifen.getJflb() + " and iscenicid=" + jifen.getIscenicid() + "";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			Usernumjf userjifen = (Usernumjf) list.get(0);
			userjifen.setPoint(jifen.getPoint());
			userjifen.setItickettypeid2(new BigDecimal(jifen.getItickettypeid2().intValue()));
			update(userjifen);
		} else {
			Long seq = getMaxPk("seq", "Usernumjf");
			jifen.setSeq(new Long(seq.intValue() + 1));
			jifen.setYpoint(0.0);
			jifen.setIsvalid(new BigDecimal(1));
			save(jifen);
		}
	}

	/**
	 * * Describe:根据服务商获取积分规则
	 *
	 * @see com.ectrip.system.statistics.dao.idao.IStatisicsDAO#getStatisicsRule(java.lang.String)
	 * @param iscenicid
	 * @return
	 * @author yangguang Date:2012-4-12
	 */
	public Numjifenset getStatisicsRule(String iscenicid) {
		String hql = "from Numjifenset where iscenicid=" + iscenicid + " and jflb=1";
		List list = find(hql);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			return (Numjifenset) list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * * Describe:获取积分明细
	 *
	 * @see com.ectrip.system.statistics.dao.idao.IStatisicsDAO#getStatisicsRuleDetail(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 * @param ruleid
	 * @param starttime
	 * @param endtime
	 * @return
	 * @author yangguang Date:2012-4-12
	 */
	public List getStatisicsRuleDetail(String ruleid, String starttime, String endtime) {
		String hql = "from Numjifensetlist where id.nid=" + ruleid + " and  stdt<='" + starttime + "' and  etdt>='" + endtime + "' and isvalid=1";
		return find(hql);
	}

	/**
	 * * Describe:根据用户服务商统计出用户可以划为数量积分的票的总数
	 *
	 * @see com.ectrip.system.statistics.dao.idao.IStatisicsDAO#getTicketNumber(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 * @param usid
	 * @param startDate
	 * @param endDate
	 * @param iscenicid
	 * @return
	 * @author yangguang Date:2012-4-12
	 */
	public int getTicketNumber(String usid, StatisticsModel model, String itickettypeid) {
		Long num1 = 0l;
		Long num2 = 0l;
		String sql1 = "from Rcgroupsalemonthtab where noteb='01' and isa=1 and isb=2 and ryear='" + model.getYear() + "' and rmonth='" + model.getMonth() + "' and usid='" + usid
				+ "' and itickettypeid='" + itickettypeid + "'";
		String sql2 = "from Rcgroupsalemonthtab where noteb='02' and isa=1 and isb=2 and isd in (0,2) and ryear='" + model.getYear() + "' and rmonth='" + model.getMonth() + "' and usid='" + usid
				+ "' and itickettypeid='" + itickettypeid + "'";
		List list1 = find(sql1);
		List list2 = find(sql2);
		Rcgroupsalemonthtab m1 = null;
		Rcgroupsalemonthtab m2 = null;
		if (list1 != null && list1.size() > 0) {
			for (int x = 0; x < list1.size(); x++) {
				m1 = (Rcgroupsalemonthtab) list1.get(x);
				num1 += m1.getNumb();
			}
		}
		if (list2 != null && list2.size() > 0) {
			for (int x = 0; x < list2.size(); x++) {
				m2 = (Rcgroupsalemonthtab) list2.get(x);
				num2 += m2.getNumb().intValue();
			}
		}
		return num1.intValue() - num2.intValue();
	}

	public int getTicketNumber(String usid, StatisticsModel model, Numjifensetlist detail) {
		Long num1 = 0l;
		Long num2 = 0l;
		StringBuffer sql1 = new StringBuffer("from Rcgroupsalemonthtab where noteb='01' and isa="+detail.getIint1()+" and isb="+detail.getIint2()+" and usid='" + usid
				+ "' and itickettypeid='" + detail.getItickettypeid().intValue() + "'");
		StringBuffer sql2 = new StringBuffer("from Rcgroupsalemonthtab where noteb='02' and isa="+detail.getIint1()+" and isb="+detail.getIint2()+" and isd in (0,2) and usid='" + usid
				+ "' and itickettypeid='" + detail.getItickettypeid().intValue() + "'");
		if(detail.getJsfs().equals("03")){
			sql1.append(" and ryear='" + model.getYear() + "' and rmonth='" + model.getMonth() + "' ");
		}else if(detail.getJsfs().equals("04")){
			sql1.append(" and ryear='" + model.getYear() + "'");
		}
		System.out.println("sql1:"+sql1.toString());
		System.out.println("sql2:"+sql2.toString());
		List list1 = find(sql1.toString());
		List list2 = find(sql2.toString());
		Rcgroupsalemonthtab m1 = null;
		Rcgroupsalemonthtab m2 = null;
		if (list1 != null && list1.size() > 0) {
			for (int x = 0; x < list1.size(); x++) {
				m1 = (Rcgroupsalemonthtab) list1.get(x);
				num1 += m1.getNumb();
			}
		}
		if (list2 != null && list2.size() > 0) {
			for (int x = 0; x < list2.size(); x++) {
				m2 = (Rcgroupsalemonthtab) list2.get(x);
				num2 += m2.getNumb().intValue();
			}
		}
		return num1.intValue() - num2.intValue();
	}

	/**
	 *
	 * Describe:根据月份获取用户积分
	 *
	 * @auth:yangguang
	 * @param starttime
	 * @param endtime
	 * @param usid
	 * @param nid
	 * @param jflb
	 * @param isecnicid
	 * @return return:Usernumjf Date:2012-5-21
	 */
	public Usernumjf getUpMonthJifen(String starttime, String endtime, String usid, Long nid, Long jflb, Long isecnicid) {
		String sql = "from Usernumjf where nid=" + nid + " and stdt='" + starttime + "' and etdt='" + endtime + "' and iscenicid=" + isecnicid + " and usid='" + usid + "' and jflb=" + jflb + "";
		List list = find(sql);
		if (list != null && list.size() > 0) {
			Usernumjf userjifen = (Usernumjf) list.get(0);
			return userjifen;
		} else {
			return null;
		}
	}

	public Usernumjf getJifenByUser(String starttime, String endtime, String usid, Long jflb, Long isecnicid) {
		String sql = "from Usernumjf where stdt='" + starttime + "' and etdt='" + endtime + "' and iscenicid=" + isecnicid + " and usid='" + usid + "' and jflb=" + jflb + "";
		System.out.println("===>>获取用户积分sql:" + sql);
		List list = find(sql);
		if (list != null && list.size() > 0) {
			Usernumjf userjifen = (Usernumjf) list.get(0);
			return userjifen;
		} else {
			return null;
		}
	}

	public PaginationSupport getUserjifenList(String starttime, String endtime, int pageSize, int startIndex, String url, String usid,String iscenicid,int jflb) {
		String hql = "select new map(um.usid as usid,um.susid as susid,um.nid as nid,um.stdt as stdt,um.etdt as etdt,um.jflb as jflb,um.iscenicid as iscenicid,um.itickettypeid2 as itickettypeid2,um.point as point,um.ypoint as ypoint,um.isvalid as isvalid,c.corpname as corpname,c.lname as lname) from Usernumjf um,Custom c where um.usid=c.usid and um.stdt='"
				+ starttime + "' and um.etdt='" + endtime + "' and um.jflb="+jflb+" and um.iscenicid="+iscenicid;
		if (usid != null && !usid.equals("")) {
			hql += " and c.usid='" + usid + "'";
		}
		hql += " order by um.point desc";
		System.out.println("========>>>jfsql:"+hql);
		return findPage(hql, pageSize, startIndex, url);
	}

	public Usernumjf queryJifenByUsid(String starttime, String endtime, String usid) {
		String hql = "from Usernumjf  where usid=usid and  stdt='" + starttime + "' and  etdt='" + endtime + "' and  usid='" + usid + "'";
		List list = this.find(hql);
		System.out.println("==>>>>hql:"+hql);
		if (list != null && list.size() > 0) {
			return (Usernumjf) list.get(0);
		} else {
			return null;
		}
	}

	public List queryJifenListByUsid(String starttime, String endtime, String usid) {
		String hql = "from Usernumjf  where usid=usid and  stdt='" + starttime + "' and  etdt='" + endtime + "' and  usid='" + usid + "'";
		return find(hql);
	}

	public Numjifenset getNumjifenset(String iscenicid) {
		String hql = "from Numjifenset where iscenicid=" + iscenicid + " and jflb=-1";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Numjifenset) list.get(0);
		} else {
			return null;
		}
	}

	public Numjifensetlist getSalesRule(String itickettypeid, String nid) {
		String hql = "from Numjifensetlist where nid=" + nid + " and itickettypeid=" + itickettypeid + "";
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Numjifensetlist) list.get(0);
		} else {
			return null;
		}
	}


	/**
	 * 查询产品消费规则
	 * @param itickettypeid
	 * @param nid
	 * @param icrowid
	 * @param ibusinessid
	 * @param date
	 * @return
	 */
	public Numjifensetlist getSalesRule(Long itickettypeid, Long nid,Long icrowid,Long ibusinessid,String date) {
		String hql = "from Numjifensetlist where nid=" + nid + " and itickettypeid=" + itickettypeid + " and iint1="+icrowid+" and iint2="+ibusinessid+" and stdt<='"+date+"' and etdt>='"+date+"'";
		System.out.println("====.....sql"+hql);
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Numjifensetlist) list.get(0);
		} else {
			return null;
		}
	}

	public Numjifensetlist getSalesRule(Long itickettypeid, Long nid, Long icrowdid, String usid) {
		Custom custom = (Custom) get(Custom.class, usid);
		String hql = "from Numjifensetlist where  itickettypeid=" + itickettypeid + " and id.nid=" + nid + " and iint2=" + custom.getIbusinessid() + " and iint1=" + icrowdid;
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Numjifensetlist) list.get(0);
		} else {
			return null;
		}
	}

	public Usernumjf queryJifenByUsid(String starttime, String endtime, String usid, int nid) {
		String hql = "from Usernumjf  where usid=usid and  stdt='" + starttime + "' and  etdt='" + endtime + "' and  usid='" + usid + "' and nid=" + nid + "";
		List list = this.find(hql);
		if (list != null && list.size() > 0) {
			return (Usernumjf) list.get(0);
		} else {
			return null;
		}
	}

	public PaginationSupport getStatislist(int pageSize, int startIndex, String url, String usid) {
		String hql = "select new map(stid as stid,usid as usid,starttime as starttime,endtime as endtime,empid as empid,status as status,auempid as auempid,iscenicid as iscenicid,opeartype as opeartype,numb as numb) from Statisticsupdate";
		if (usid != null && !usid.equals("")) {
			hql += " where usid='" + usid + "'";
		}
		return findPage(hql, pageSize, startIndex, url);
	}

	public List getOrderConsume(String orid) {
		String hql = "from Usernumjflist where orid='" + orid + "'";
		List list = find(hql);
		return list;
	}

	/**
	 * *
	 * Describe:判断唯一性
	 * @see com.ectrip.system.statistics.dao.idao.IStatisicsDAO#volidateUserjf(com.ectrip.system.statistics.model.StatisticsModel, java.lang.String, java.lang.String)
	 * @param model
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author lijingrui
	 * Date:2014-12-18
	 */
	public boolean volidateUserjf(StatisticsModel model,String startDate,String endDate){
		boolean b=false;
		String hql = " from Usernumjf  where usid='"+model.getUsid()+"' and  stdt='" + startDate + "' and  etdt='" + endDate + "' and jflb="+model.getJflb()+" and iscenicid="+model.getIscenicid();
		List list=this.find(hql);
		if(list!=null&&list.size()>0){
			b=true;
		}
		return b;
	}

	public PaginationSupport getStatisUpdatelist(int pageSize, int startIndex,String url,String iscenicid,String usid){
		String hqls = "select new map(stid as stid,usid as usid,starttime as starttime,endtime as endtime,empid as empid,status as status,auempid as auempid,iscenicid as iscenicid,opeartype as opeartype,numb as numb) from Statisticsupdate";
		if(iscenicid!=null&&!iscenicid.equals("")){
			hqls+=" where iscenicid='" + iscenicid + "'";
		}
		if (usid != null && !usid.equals("")) {
			hqls += " and usid='" + usid + "'";
		}

		return findPage(hqls, pageSize, startIndex, url);
	}
}
