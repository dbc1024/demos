package com.ectrip.ticket.checkticket.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.Tools;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.checkticket.service.iservice.IVerifycheckService;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.permitenter.Opwwicketsettab;
import com.ectrip.ticket.model.provider.Edmticketproduct;

@Service
public class VerifycheckService implements IVerifycheckService {
	
	@Autowired
	private ICheckDao checkDao;

	public int Verifyticket(Stssoldticketsubtab stssz) {

		List opwwicketsettablist = checkDao
				.find("from Opwwicketsettab where itickettypeid="
						+ stssz.getItickettypeid() + " and izticktypeid="
						+ stssz.getIztickettypeid() + " and igardengateid="
						+ stssz.getIgardengateid() + " and byisuse=1");
		Opwwicketsettab opww = (Opwwicketsettab) opwwicketsettablist.get(0);

		/*if (opww.getMsingleconsume() == 0) {

			return 0;
		} else */if (opww.getMsingleconsume() == 1) {
			// 单日检票
			System.out.println("单日检票");
			String today = Tools.getTodayString();
			int day = Integer.parseInt(today.substring(8, 10));
			if (day % 2 == 1) {

				return 0;
			} else {

				return -3;
			}
		} else if (opww.getMsingleconsume() == 2) {
			// 双日检票
			String today = Tools.getTodayString();
			int day = Integer.parseInt(today.substring(8, 10));
			if (day % 2 == 0) {

				return 0;
			} else {

				return -3;
			}
		}
		/* else if (opww.getMsingleconsume() == 3) {
			// 按星期分天检票
			Sysparv5 sys = (Sysparv5) checkDao.get(Sysparv5.class,
					new Sysparv5Id("JPQZ", "3"));
			String today = Tools.getDayTimes();
			int weekid = Tools.getDayOfWeek(today);
			String weeks = sys.getPmvb();
			String[] week = weeks.split(",");
			boolean canyong = false;
			for (int i = 0; i < week.length; i++) {
				System.out.print(week[i]);
				if (weekid == Integer.parseInt(week[i])) {
					canyong = true;
				}
			}
			if (canyong) {
				return 0;
			} else {
				return -1;
			}
		} else if (opww.getMsingleconsume() == 21) {
			Sysparv5 sys = (Sysparv5) checkDao.get(Sysparv5.class,
					new Sysparv5Id("JPQZ", "21"));
			String starttimes = sys.getPmvb();
			String endtimes = sys.getPmvc();
			Calendar now = Calendar.getInstance(TimeZone
					.getTimeZone("GMT+08:00"));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				if (starttimes != null && !starttimes.equals("")) {
					Date d1 = df.parse(Tools.getTodayString() + " "
							+ starttimes + ":00");
					Calendar begcalendar = Calendar.getInstance();
					begcalendar.setTime(d1);
					if (now.before(begcalendar)) {
						return -4;
					}
				}
				if (endtimes != null && !endtimes.equals("")) {
					Date d1 = df.parse(Tools.getTodayString() + " " + endtimes
							+ ":00");
					Calendar begcalendar = Calendar.getInstance();
					begcalendar.setTime(d1);
					if (now.after(begcalendar)) {
						return -5;
					}
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

		Edmticketproduct tproduct = (Edmticketproduct) checkDao.get(
				Edmticketproduct.class, stssz.getItickettypeid());
		if (tproduct != null && tproduct.getNoted5().equals("1")) {
			Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

			// 查询园门和票务对应的售出门票子票表
			List zsstlist = checkDao
					.find(" from Stssoldticketsubtab where id.isalesvoucherid="
							+ stssz.getId().getIsalesvoucherid()
							+ " and id.iticketstationid="
							+ stssz.getId().getIticketstationid()
							+ "   and  id.isalesvoucherdetailsid="
							+ stssz.getId().getIsalesvoucherdetailsid()
							+ " and id.szsoldticketid="
							+ stssz.getId().getSzsoldticketid()
							+ " and igardengateid=" + stssz.getIgardengateid()
							+ " and isvalid not in (-1,0)");
			for (int i = 0; i < zsstlist.size(); i++) {
				Stssoldticketsubtab stss = (Stssoldticketsubtab) zsstlist.get(i);

				try {
					// 验证有效时间
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					DateFormat format=new SimpleDateFormat("HH:mm");
					//有效开始时间
					Date d1 = df.parse(stss.getDtbegindate());
					Calendar begcalendar = Calendar.getInstance();
					begcalendar.setTime(d1);
					//有效结束时间
					Date d2 = df.parse(stss.getDtenddate());
					Calendar endcalendar = Calendar.getInstance();
					endcalendar.setTime(d2);
					System.out.println("分时预约时间验证开始");

					//---------------------新增分时预约检票时段判断------------------------//
					//分时预约检票设置：0为默认值，表示不是分时预约票，1检票时间不可提前不可延后，2检票时间可提前但不能延后
					//			  3检票时间不可提前但可延后，4检票时间可提前可延后
					long checkSet = opww.getIstimeticket();

					Date date = new Date();
					long  newtime = format.parse(format.format(date)).getTime();//得到当前时分秒的毫秒数
					if (now.before(begcalendar)) {

						return -4;//未到检票开始时间
					}else if (now.after(endcalendar)) {
						return -5;//已过检票时间
					}else {
						if(checkSet!=0){
							//预约开始时间
							long stDate;
							//预约结束时间
							long enDate;
							if(!StringUtils.isEmpty(stss.getTimeStart())&&!StringUtils.isEmpty(stss.getTimeEnd()))
							{
								stDate= format.parse(stss.getTimeStart()).getTime();
								enDate= format.parse(stss.getTimeEnd()).getTime();
								if ((newtime<stDate)&&(checkSet==1||checkSet==3)) {
									return -4;//未到检票开始时间
								}else if ((newtime>enDate)&&(checkSet==1||checkSet==2)) {
									return -5;//已过检票时间
								}
							}

						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		return 0;
	}
}
