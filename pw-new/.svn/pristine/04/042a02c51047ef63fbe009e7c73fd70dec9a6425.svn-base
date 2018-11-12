package com.ectrip.ticket.statistics.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.usernumjf.Statisticsupdate;
import com.ectrip.ec.model.usernumjf.Usernumjf;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.statistics.StatisticsModel;
import com.ectrip.ticket.statistics.dao.idao.IStatisicsDAO;
import com.ectrip.ticket.statistics.service.iservice.IStatisticsService;

@Service
public class StatisticsService implements IStatisticsService {
	public static StringBuffer username = new StringBuffer();
	@Autowired
	private IStatisicsDAO statisicsDao;

	

	public void statisticsThread(StatisticsModel model) {
		StatisticsCreate create = new StatisticsCreate(model);
		create.start();
	}

	/**
	 * ' * Describe:更新完数量积分后，查询出来
	 * 
	 * @see com.ectrip.system.statistics.service.iservice.IStatisticsService#queryStatistics(com.ectrip.system.statistics.model.StatisticsModel)
	 * @param model
	 * @return
	 * @author yangguang Date:2012-4-12
	 */
	public List queryStatistics(StatisticsModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * * Describe:生成所有旅行社的数量积分
	 * 
	 * @see com.ectrip.system.statistics.service.iservice.IStatisticsService#updateStatistics(com.ectrip.system.statistics.model.StatisticsModel)
	 * @param model
	 * @author yangguang Date:2012-4-12
	 */
	private void updateStatistics(StatisticsModel model) {
		try {
			Map map = getMonthDate(Integer.parseInt(model.getYear()),
					Integer.parseInt(model.getMonth()) + 1);// 根据所选月份
															// 计算出下月月的起始日期 结束日期
			Map map1 = getMonthDate(Integer.parseInt(model.getYear()),
					Integer.parseInt(model.getMonth()));// 获取所选月份的起始日期结束日期
			Numjifenset jifenset = statisicsDao.getStatisicsRule(model
					.getIscenicid());// 根据服务商获取积分规则
			if (jifenset != null) {
				// 根据要生成的月份查生成规则
				List jifensetdetail = statisicsDao.getStatisicsRuleDetail(
						jifenset.getNid().toString(), map1.get("startDate")
								.toString(), map1.get("endDate").toString());
				if (jifensetdetail != null && jifensetdetail.size() > 0) {
					List list = queryStatisticsUser();// 获取到所有旅行社
					Custom custom = null;
					for (int i = 0; i < list.size(); i++) {
						custom = (Custom) list.get(i);
						if (custom.getUsid().equals("lxszsf1")) {
							System.out.println("11");
						}
						// TODO 计算出积分后 根据服务商 用户名 规则编号消费开始时间 消费结束时间
						// 查询表里有没有数据,有则更新产生消费积分,没有则插入信息
						username.delete(0, username.length());
						double yearJf = 0;
						double monthjf = 0;
						for (int j = 0; j < jifensetdetail.size(); j++) {
							Numjifensetlist detail = (Numjifensetlist) jifensetdetail
									.get(j);
							if (detail.getJsfs().equals("03")) {// 按月计算
								monthjf += getLxsJfnum(custom.getUsid(), model,
										jifenset, detail);
							} else if (detail.getJsfs().equals("04")) {// 按年计算
								yearJf += getLxsJfnum(custom.getUsid(), model,
										jifenset, detail);
							}
						}
						try {
							Usernumjf month = new Usernumjf();
							month.setUsid(custom.getUsid());
							month.setSusid(custom.getSusid());
							month.setNid(new BigDecimal(jifenset.getNid()));
							month.setStdt(map.get("startDate").toString());
							month.setEtdt(map.get("endDate").toString());
							month.setJflb(new BigDecimal(1));// 月积分
							month.setIscenicid(new BigDecimal(model
									.getIscenicid()));
							month.setPoint(new Double(Math.floor(monthjf)));
							month.setItickettypeid2(new BigDecimal(0));
							statisicsDao.updateStatistics(month);
							Usernumjf year = new Usernumjf();
							year.setUsid(custom.getUsid());
							year.setSusid(custom.getSusid());
							year.setNid(new BigDecimal(jifenset.getNid()));
							year.setStdt(model.getYear() + "-01-01");
							year.setEtdt(model.getYear() + "-12-31");
							year.setJflb(new BigDecimal(2));// 年积分
							year.setIscenicid(new BigDecimal(model
									.getIscenicid()));
							year.setPoint(new Double(Math.floor(yearJf)));
							year.setItickettypeid2(new BigDecimal(0));
							statisicsDao.updateStatistics(year);
							System.out.println(year.getUsid() + "用户积分生成/更新成功");
							username.append(year.getUsid());
						} catch (Exception e) {
							username.append("error");
							System.out
									.println(custom.getUsid() + "用户积分生成/更新失败");
							e.printStackTrace();
						}
					}
				}
				username.delete(0, username.length());
				username.append("success");
			}
		} catch (Exception e) {
			username.delete(0, username.length());
			username.append("error");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(Math.floor(1.23));
		System.out.println(Math.floor(1.58));
	}

	private double getLxsJfnum(String usid, StatisticsModel model,
			Numjifenset jifenset, Numjifensetlist detail) {
		double jifen = 0.0;
		jifen = statisicsDao.getTicketNumber(usid, model, detail);// 根据用户 时间
																	// 服务商生成数量积分
		System.out.println("-------------->_>>>用户:" + usid + "--->>" + jifen);
		List list = statisicsDao.queryStatisticsUserBySusid(usid);
		if (list != null && list.size() > 0) {
			Custom cus = null;
			for (int i = 0; i < list.size(); i++) {
				cus = (Custom) list.get(i);
				jifen += getLxsJfnum(cus.getUsid(), model, jifenset, detail);
			}
		}
		DecimalFormat format = new DecimalFormat("0.00");
		System.out
				.println(Double.parseDouble(format.format(jifen
						/ detail.getIint3().intValue()
						* detail.getIint4().intValue())));
		return Double.parseDouble(format.format(jifen
				/ detail.getIint3().intValue() * detail.getIint4().intValue()));
	}

	/**
	 * 根据不同的类型来统计 废弃不用
	 * 
	 * @deprecated
	 */
	private int calculateJfByType(String usid, StatisticsModel model,
			Numjifenset jifenset, Numjifensetlist detail) {
		int count = statisicsDao.getTicketNumber(usid, model, detail
				.getItickettypeid().toString());
		// 这里数据库设计有问题 在积分规则明细中jsfs为02规则累加,既然是规则累加 应该设置到规则中,
		// 不应该设计到规则明细中,因为规则累加是按照明细里面的进行累加,即使按照规则累加也不应该设计到明细中来
		// 导致现在如果明细中含有多个规则,一个按月计算 一个按年计算 那么按照两种规则生成的积分改怎么使用?累加 还是分开?
		if (detail.getTgfs().equals("01")) {// 按照单个订单计算,不够计算直接舍去 暂不实现
			if (detail.getJsfs().equals("01")) {// 直接计算

			} else if (detail.getJsfs().equals("02")) {// 规则累加 暂不实现

			} else if (detail.getJsfs().equals("03")) {// 按月计算 暂不实现

			} else if (detail.getJsfs().equals("04")) {// 按年计算 暂不实现

			}
		} else if (detail.getTgfs().equals("02")) {// 订单累加
			if (detail.getJsfs().equals("01")) {// 直接计算

			} else if (detail.getJsfs().equals("02")) {// 规则累加 暂不实现

			} else if (detail.getJsfs().equals("03")) {// 按月计算 暂不实现
				return count;
			} else if (detail.getJsfs().equals("04")) {// 按年计算 暂不实现

			}
		}
		return count;
	}

	/**
	 * 
	 * Describe:查询所有旅行社分社
	 * 
	 * @auth:yangguang
	 * @return return:List Date:2012-4-12
	 */
	private List queryStatisticsUser() {
		Sysparv5 syspar = (Sysparv5) statisicsDao.get(Sysparv5.class,
				new Sysparv5Id("YHDX", "0001"));
		return statisicsDao.queryStatisticsUser(syspar.getPmva());
	}

	public static Map getMonthDate(int year, int month) {
		Map map = new HashMap();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		String thisMonthStart = format.format(cal.getTime());// 本月开始（本月1号）;
		System.out.println("开始->" + thisMonthStart);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String thisMonthEnd = format.format(cal.getTime());
		cal.clear();
		System.out.println("结束->" + thisMonthEnd);
		map.put("startDate", thisMonthStart);
		map.put("endDate", thisMonthEnd);
		return map;
	}

	public PaginationSupport getUserjifenList(StatisticsModel model,
			int pageSize, int startIndex, String url, String usid) {
		Map map = new HashMap();
		if (model.getJflb().equals("1")) {
			map = getMonthDate(Integer.parseInt(model.getYear()),
					Integer.parseInt(model.getMonth()) + 1);
		} else {
			map.put("startDate", model.getYear() + "-01-01");
			map.put("endDate", model.getYear() + "-12-31");
		}
		return statisicsDao.getUserjifenList(map.get("startDate").toString(),
				map.get("endDate").toString(), pageSize, startIndex, url, usid,
				model.getIscenicid(), Integer.parseInt(model.getJflb()));
	}

	class StatisticsCreate extends Thread {
		private StatisticsModel model;

		public StatisticsModel getModel() {
			return model;
		}

		public void setModel(StatisticsModel model) {
			this.model = model;
		}

		StatisticsCreate(StatisticsModel model) {
			this.model = model;
		}

		public void run() {
			try {
				updateStatistics(model);
			} catch (Exception e) {
				System.out.println("生成数量积分失败");
				e.printStackTrace();
				username.delete(0, username.length());
				username.append("error");
			}
		}
	}

	public Usernumjf queryJifenByUsid(String starttime, String endtime,
			String usid) {
		return statisicsDao.queryJifenByUsid(starttime, endtime, usid);
	}

	public List queryJifenListByUsid(String starttime, String endtime,
			String usid) {
		return statisicsDao.queryJifenListByUsid(starttime, endtime, usid);
	}

	public Usernumjf getUserJf(String iscenicid, String starttime,
			String endtime, String usid,Long jflb) {
		Usernumjf usernumjf = statisicsDao.getJifenByUser(starttime, endtime,
				usid, jflb, Long.parseLong(iscenicid));
		return usernumjf;
	}

	public void updateUserjfByEmployee(Statisticsupdate stupdate) {
		if (stupdate.getStatus().equals("1")) {
			Usernumjf userjf = getUserJf(stupdate.getIscenicid().toString(),
					stupdate.getStarttime(), stupdate.getEndtime(),
					stupdate.getUsid(),Long.parseLong(stupdate.getOpeartype()));
			userjf.setPoint(userjf.getPoint() + stupdate.getNumb().intValue());
			statisicsDao.update(userjf);
		}
		statisicsDao.update(stupdate);
	}

	public void saveStatisticsInfo(Statisticsupdate stupdate) {
		stupdate.setStid(statisicsDao.getMaxPk("stid", "Statisticsupdate") + 1);
		stupdate.setStatus("0");
		statisicsDao.save(stupdate);
	}

	public PaginationSupport getStatisUpdatelist(int pageSize, int startIndex,
			String url,String iscenicid,String usid) {
		return statisicsDao.getStatisUpdatelist(pageSize, startIndex, url, iscenicid, usid);
	}

	/**
	 * * Describe:判断唯一性
	 * 
	 * @see com.ectrip.system.statistics.dao.idao.IStatisicsDAO#volidateUserjf(com.ectrip.system.statistics.model.StatisticsModel)
	 * @param model
	 * @return
	 * @author lijingrui Date:2014-12-18
	 */
	public boolean volidateUserjf(StatisticsModel model) {
		Map map = new HashMap();
		if (model.getJflb().equals("1")) {
			map = getMonthDate(Integer.parseInt(model.getYear()),
					Integer.parseInt(model.getMonth()) + 1);
		} else {
			map.put("startDate", model.getYear() + "-01-01");
			map.put("endDate", model.getYear() + "-12-31");
		}
		
		return statisicsDao.volidateUserjf(model,map.get("startDate").toString(),map.get("endDate").toString());
	}

	/**
	 * * Describe:添加用户积分
	 * 
	 * @see com.ectrip.system.statistics.service.iservice.IStatisticsService#saveJfStatisticsupdate(com.ectrip.system.statistics.model.StatisticsModel,
	 *      java.lang.Long)
	 * @param model
	 * @param iemployeeid
	 * @author lijingrui Date:2014-12-18
	 */
	public void saveJfStatisticsupdate(StatisticsModel model, Long iemployeeid) {
		Map map = new HashMap();
		if (model.getJflb().equals("1")) {
			map = getMonthDate(Integer.parseInt(model.getYear()),
					Integer.parseInt(model.getMonth()) + 1);
		} else {
			map.put("startDate", model.getYear() + "-01-01");
			map.put("endDate", model.getYear() + "-12-31");
		}
		
		Custom ct=(Custom)this.statisicsDao.get(Custom.class, model.getUsid());
		Numjifenset jifenset = statisicsDao.getStatisicsRule(model
				.getIscenicid());// 根据服务商获取积分规则
		if(jifenset==null){
			jifenset=new Numjifenset();
			jifenset.setNid(0L);
		}
		
		Usernumjf userjf = new Usernumjf();
		Long seq = this.statisicsDao.getMaxPk("seq", "Usernumjf");
		userjf.setSeq(new Long(seq.intValue() + 1));
		userjf.setYpoint(0.0);
		userjf.setIsvalid(new BigDecimal(1));
		userjf.setUsid(ct.getUsid());
		userjf.setSusid(ct.getSusid());
		userjf.setNid(new BigDecimal(jifenset.getNid()));
		userjf.setStdt(map.get("startDate").toString());
		userjf.setEtdt(map.get("endDate").toString());
		userjf.setJflb(new BigDecimal(model.getJflb()));
		userjf.setIscenicid(new BigDecimal(model
				.getIscenicid()));
		userjf.setPoint(new Double(0));
		userjf.setItickettypeid2(new BigDecimal(0));
		statisicsDao.save(userjf);
		
		Statisticsupdate stupdate=new Statisticsupdate();
		stupdate.setStid(statisicsDao.getMaxPk("stid", "Statisticsupdate") + 1);
		stupdate.setStatus("0");
		stupdate.setUsid(ct.getUsid());
		stupdate.setStarttime(map.get("startDate").toString());
		stupdate.setEndtime(map.get("endDate").toString());
		stupdate.setEmpid(iemployeeid.toString());
		stupdate.setIscenicid(Long.parseLong(model.getIscenicid()));
		stupdate.setOpeartype(model.getJflb());
		stupdate.setNumb(model.getPoint().longValue());
		
		statisicsDao.save(stupdate);
	}

	@Override
	public Numjifenset getNumjifenset(String iscenicid) {
		return statisicsDao.getNumjifenset(iscenicid);
	}

	@Override
	public Numjifensetlist getSalesRule(Long itickettypeid, Long nid, Long icrowid, Long ibusinessid, String date) {
		return statisicsDao.getSalesRule(itickettypeid, nid, icrowid, ibusinessid, date);
	}
	

	@Override
	public Usernumjf getJifenByUser(String starttime, String endtime, String usid, Long jflb, Long isecnicid) {
		return statisicsDao.getJifenByUser(starttime, endtime, usid, jflb, isecnicid);
	}

	@Override
	public Numjifensetlist getSalesRule(String itickettypeid, String nid) {
		return statisicsDao.getSalesRule(itickettypeid, nid);
	}
}
