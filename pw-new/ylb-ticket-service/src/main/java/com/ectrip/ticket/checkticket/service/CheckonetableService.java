package com.ectrip.ticket.checkticket.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.checkticket.service.iservice.ICheckonetableService;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.Fingerprintrandom;
import com.ectrip.ticket.model.order.Stsschecktab;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.StssoldticketattesttabId;
import com.ectrip.ticket.model.order.Ticketchecklist;

@Service
public class CheckonetableService extends GenericService implements ICheckonetableService {
	private ICheckDao checkDao;

	public void setCheckDao(ICheckDao checkDao) {
		this.checkDao = checkDao;
		setGenericDao(checkDao);
	}

	public ResultBean querychecktypeoneTable(String accid, String printno)
			throws Exception {
		System.out.println("单表检票开始调用3");
		ResultBean rs = new ResultBean();
		rs.setColumnCount(1);
		rs.setColumnNames(new String[] { "values" });
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		// 读取闸机信息
		String tickettypename = "";// 票名称
		String mactualsaleprice = "";// 单价
		String icrowdkindid = "";// 人群id
		String szcrowdkindname = "";// 人群名称
		String dtbegindate = "";// 开始时间
		String dtenddate = "";// 截至时间
		String bsfilebinary = "";// 指纹信息
		String szimagepath = "";// 相片路径
		String szmemo = "";// 语音路径
		String seq = "";// 竹筏流水
		String tripname = "";// 趟次名称
		List acclist = checkDao
				.find(" from Esbaccessequiptab where id.iaccessequipid="
						+ accid);
		if (acclist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 无效票
			tickettypename = "无效闸机";
			rs = getcanshu11(rs, tickettypename, mactualsaleprice,
					icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
					bsfilebinary, szimagepath, szmemo, seq, tripname);
			return rs;
		}
		System.out.println("单表检票开始调用4");
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
		// System.out.println("changeCheckTicket1");
		// 查询售出门票表
		List sstlist = checkDao
				.find("from Stsschecktab where szticketprintno='" + printno
						+ "' order by dtmakedate desc");
		Stsschecktab stss = null;
		System.out.println("单表检票开始调用5");
		if (sstlist == null || sstlist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 无效票
			tickettypename = "无效票";
			rs = getcanshu11(rs, tickettypename, mactualsaleprice,
					icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
					bsfilebinary, szimagepath, szmemo, seq, tripname);

			return rs;
		} else {
			System.out.println("单表检票开始调用6");
			System.out.println("sstlist.size()=" + sstlist.size());
			int a = 0;
			for (int i = 0; i < sstlist.size(); i++) {
				Stsschecktab nstss = (Stsschecktab) sstlist.get(i);
				System.out.println("nstss.getIgardengateid()="
						+ nstss.getIgardengateid());
				System.out.println("nstss.getIgardengateid()="
						+ nstss.getIgardengateid());
				if (nstss.getIgardengateid().longValue() == acc.getId()
						.getIgardengateid().longValue()) {
					if (nstss.getIsvalid() == 1) {
						if (a == 0) {
							stss = nstss;
						}
						a++;
					}
				}
			}
			System.out.println("a=" + a);
			if (a == 0) {
				stss = (Stsschecktab) sstlist.get(0);
			}
			System.out.println("单表检票开始调用6。1");

			tickettypename = stss.getSztickettypename();
			mactualsaleprice = stss.getMactualsaleprice().toString();
			icrowdkindid = stss.getIcrowdkindid().toString();
			szcrowdkindname = stss.getSzcrowdkindname();
			szmemo = stss.getSzmemo();
			System.out.println("单表检票开始调用6。2");

			if (a == 0) {
				rs.addRow(new String[] { "-3" });// 无效票

				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);
				rs = getchecklist(rs, stss);
				return rs;
			}

		}
		System.out.println("单表检票开始调用7");
		// 验证首次 进门与买票的间隔时间
		System.out.println("验证首次 进门与买票的间隔时间");
		if (stss.getIsfristtimaeyz() > 0) {
			List szsstlist = checkDao
					.find("from Stsschecktab where  id.isalesvoucherid="
							+ stss.getId().getIsalesvoucherid()
							+ " and id.iticketstationid="
							+ stss.getId().getIticketstationid()
							+ "   and  id.isalesvoucherdetailsid="
							+ stss.getId().getIsalesvoucherdetailsid()
							+ " and id.szsoldticketid="
							+ stss.getId().getSzsoldticketid()
							+ " and ipassedtimes>0");
			if (szsstlist == null || szsstlist.size() == 0) {
				// 表示一次票没有检过
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				Date d1 = df.parse(stss.getDtmakedate());
				calendar.setTime(d1);
				calendar.add(Calendar.MINUTE, stss.getIsfristtimaeyz()
						.intValue());

				if (now.after(calendar)) {
					rs.addRow(new String[] { "-11" });// 对应园门不能刷该票
					rs = getcanshu11(rs, tickettypename, mactualsaleprice,
							icrowdkindid, szcrowdkindname, dtbegindate,
							dtenddate, bsfilebinary, szimagepath, szmemo, seq,
							tripname);

					rs = getchecklist(rs, stss);
					return rs;
				}

			}
		}
		System.out.println("单表检票开始调用8");
		// 首次激活更新有效期
		if (stss.getIsfristactive() == 1) {
			List szsstlist = checkDao
					.find("from Stsschecktab where  id.isalesvoucherid="
							+ stss.getId().getIsalesvoucherid()
							+ " and id.iticketstationid="
							+ stss.getId().getIticketstationid()
							+ "   and  id.isalesvoucherdetailsid="
							+ stss.getId().getIsalesvoucherdetailsid()
							+ " and id.szsoldticketid="
							+ stss.getId().getSzsoldticketid()
							+ " and ipassedtimes>0");
			if (szsstlist == null || szsstlist.size() == 0) {
				// 表示一次票没有检过

				String startdate = Tools.getTodayString();
				List sscbtlist = checkDao
						.find("from Stsschecktab where  id.isalesvoucherid="
								+ stss.getId().getIsalesvoucherid()
								+ " and id.iticketstationid="
								+ stss.getId().getIticketstationid()
								+ "   and  id.isalesvoucherdetailsid="
								+ stss.getId().getIsalesvoucherdetailsid()
								+ " and id.szsoldticketid="
								+ stss.getId().getSzsoldticketid()
								+ "  and isvalid not in (-1,0)");
				for (int a = 0; a < sscbtlist.size(); a++) {
					String enddate = Tools.getDate(startdate, stss
							.getValidityday().intValue() - 1);
					Stsschecktab stsub = (Stsschecktab) sscbtlist.get(a);
					stsub.setDtbegindate(startdate + " 00:00:00");
					stsub.setDtenddate(enddate + " 23:59:59");
					this.checkDao.update(stsub);
				}

			}
		}
		// 检票前置条件
		if (stss.getIse() == 1) {
			String today = Tools.getTodayString();
			int day = Integer.parseInt(today.substring(8, 10));
			if (day % 2 == 0) {
				rs.addRow(new String[] { "-9" });// 已检过

				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { "0" });
				return rs;
			}
		}
		if (stss.getIse() == 2) {
			String today = Tools.getTodayString();
			int day = Integer.parseInt(today.substring(8, 10));
			if (day % 2 == 1) {
				rs.addRow(new String[] { "-9" });// 已检过

				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { "0" });
				return rs;
			}
		}
		System.out.println("单表检票开始调用9");
		dtbegindate = stss.getDtbegindate().substring(2);
		dtenddate = stss.getDtenddate().substring(2);
		String jpnumb1 = "1";
		if (stss.getMpasstimes() != null) {
			jpnumb1 = stss.getMpasstimes().toString();
		}

		if (stss.getIpasstimes() > 0) {
			if (stss.getIpasstimes() - stss.getIpassedtimes() <= 0) {
				rs.addRow(new String[] { "-9" });// 已检过

				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { "0" });
				return rs;
			}
		} else {

			if (stss.getByusage() == 0) {
				Long itimeinterval = stss.getItimeinterval();// 检票间隔时间
				String lastcheckdate = stss.getDtlastcheckdate();// 最后检票时间
				if (lastcheckdate != null && !lastcheckdate.equals("")) {
					// 判断检票时间间隔
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Calendar calendar = Calendar.getInstance();
					Date d1 = df.parse(lastcheckdate);
					calendar.setTime(d1);
					calendar.add(Calendar.SECOND, itimeinterval.intValue());
					if (now.before(calendar)) {
						System.out.println("changeCheckTicket:-10");
						rs.addRow(new String[] { "-10" });//
						rs = getcanshu11(rs, tickettypename, mactualsaleprice,
								icrowdkindid, szcrowdkindname, dtbegindate,
								dtenddate, bsfilebinary, szimagepath, szmemo,
								seq, tripname);

						rs = getchecklist(rs, stss);
						rs.addRow(new String[] { jpnumb1 });
						return rs;
					}
				}

			} else if (stss.getByusage() == 1) {
				Long itimeinterval = stss.getItimeinterval();// 检票间隔时间
				String lastcheckdate = stss.getDtlastcheckdate();// 最后检票时间
				if (lastcheckdate != null && !lastcheckdate.equals("")) {
					// 判断检票时间间隔
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Calendar calendar = Calendar.getInstance();
					Date d1 = df.parse(lastcheckdate);
					calendar.setTime(d1);
					calendar.add(Calendar.SECOND, itimeinterval.intValue());

					if (now.before(calendar)) {
						// 当前时间在间隔时间内
						if (stss.getMsingletimes() == stss.getMsingledtimes()) {
							// 单次 票数一检完
							rs.addRow(new String[] { "-10" });//
							rs = getcanshu11(rs, tickettypename,
									mactualsaleprice, icrowdkindid,
									szcrowdkindname, dtbegindate, dtenddate,
									bsfilebinary, szimagepath, szmemo, seq,
									tripname);

							rs = getchecklist(rs, stss);
							rs.addRow(new String[] { jpnumb1 });
							return rs;
						}
					}

				}
			}
		}
		System.out.println("单表检票开始调用10");
		if (stss.getBywicketconsumetype().equals("03")) {
			// 返回101标识一单一检
			rs.addRow(new String[] { "101" });
			return rs;
		} else {
			// 不是一单一检
			rs.addRow(new String[] { "100" });
			return rs;
		}
	}

	public ResultBean getcanshu11(ResultBean rs, String tickettypename,
								  String mactualsaleprice, String icrowdkindid,
								  String szcrowdkindname, String dtbegindate, String dtenddate,
								  String bsfilebinary, String szimagepath, String szmemo, String seq,
								  String tripname) throws Exception {
		rs.addRow(new String[] { tickettypename });
		rs.addRow(new String[] { mactualsaleprice });
		rs.addRow(new String[] { icrowdkindid });
		rs.addRow(new String[] { szcrowdkindname });
		rs.addRow(new String[] { dtbegindate });
		rs.addRow(new String[] { dtenddate });
		rs.addRow(new String[] { bsfilebinary });
		rs.addRow(new String[] { szimagepath });
		rs.addRow(new String[] { szmemo });
		rs.addRow(new String[] { seq });
		rs.addRow(new String[] { tripname });
		return rs;

	}

	/**
	 * 总体对外检票方法
	 *
	 * @param ticketstr
	 *            检票用的票号
	 * @param md5str
	 *            准确性校验方法
	 * @return 2012-7-28 日 李进进行重大修改 ,String ...OtherString 增加了第三个参数
	 */

	public ResultBean changeCheckTicketonetable(String accid,
												String szticketprintno, String... OtherString) throws Exception {
		// System.out.println(" in changeCheckTicket");
		ResultBean rs = new ResultBean();
		rs.setColumnCount(1);
		rs.setColumnNames(new String[] { "values" });
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		// 读取闸机信息

		String tickettypename = "";// 票名称
		String mactualsaleprice = "";// 单价
		String icrowdkindid = "";// 人群id
		String szcrowdkindname = "";// 人群名称
		String dtbegindate = "";// 开始时间
		String dtenddate = "";// 截至时间
		String bsfilebinary = "";// 指纹信息
		String szimagepath = "";// 相片路径
		String szmemo = "";// 语音路径
		String seq = "";// 竹筏流水
		String tripname = "";// 趟次名称

		List acclist = checkDao
				.find(" from Esbaccessequiptab where id.iaccessequipid="
						+ accid);
		if (acclist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 无效票
			tickettypename = "无效闸机";
			rs = getcanshu11(rs, tickettypename, mactualsaleprice,
					icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
					bsfilebinary, szimagepath, szmemo, seq, tripname);
			return rs;
		}
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
		List sstlist = checkDao
				.find("from Stsschecktab where szticketprintno='"
						+ szticketprintno + "' order by dtmakedate desc");
		Stsschecktab stss = null;

		if (sstlist == null || sstlist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 无效票
			tickettypename = "无效票";
			rs = getcanshu11(rs, tickettypename, mactualsaleprice,
					icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
					bsfilebinary, szimagepath, szmemo, seq, tripname);

			return rs;
		} else {
			stss = (Stsschecktab) sstlist.get(0);
			tickettypename = stss.getSztickettypename();
			mactualsaleprice = stss.getMactualsaleprice().toString();
			icrowdkindid = stss.getIcrowdkindid().toString();
			szcrowdkindname = stss.getSzcrowdkindname();
			szmemo = stss.getSzmemo();
			int a = 0;
			for (int i = 0; i < sstlist.size(); i++) {
				Stsschecktab nstss = (Stsschecktab) sstlist.get(i);
				if (nstss.getIgardengateid().longValue() == acc.getId()
						.getIgardengateid().longValue()) {
					if (nstss.getIsvalid() == 1) {
						if (a == 0) {
							stss = nstss;
						}
						a++;
					}
				}
			}
			if (a == 0) {
				stss = (Stsschecktab) sstlist.get(0);
			}
			tickettypename = stss.getSztickettypename();
			mactualsaleprice = stss.getMactualsaleprice().toString();
			icrowdkindid = stss.getIcrowdkindid().toString();
			szcrowdkindname = stss.getSzcrowdkindname();
			szmemo = stss.getSzmemo();

			if (a == 0) {
				rs.addRow(new String[] { "-3" });// 无效票

				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);
				rs = getchecklist(rs, stss);
				return rs;
			}

		}

		// 验证首次 进门与买票的间隔时间
		System.out.println("验证首次 进门与买票的间隔时间");
		if (stss.getIsfristtimaeyz() > 0) {
			List szsstlist = checkDao
					.find("from Stsschecktab where  id.isalesvoucherid="
							+ stss.getId().getIsalesvoucherid()
							+ " and id.iticketstationid="
							+ stss.getId().getIticketstationid()
							+ "   and  id.isalesvoucherdetailsid="
							+ stss.getId().getIsalesvoucherdetailsid()
							+ " and id.szsoldticketid="
							+ stss.getId().getSzsoldticketid()
							+ " and ipassedtimes>0");
			if (szsstlist == null || szsstlist.size() == 0) {
				// 表示一次票没有检过
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				Date d1 = df.parse(stss.getDtmakedate());
				calendar.setTime(d1);
				calendar.add(Calendar.MINUTE, stss.getIsfristtimaeyz()
						.intValue());

				if (now.after(calendar)) {
					rs.addRow(new String[] { "-11" });// 对应园门不能刷该票
					rs = getcanshu11(rs, tickettypename, mactualsaleprice,
							icrowdkindid, szcrowdkindname, dtbegindate,
							dtenddate, bsfilebinary, szimagepath, szmemo, seq,
							tripname);

					rs = getchecklist(rs, stss);
					return rs;
				}

			}
		}
		// 首次激活更新有效期
		if (stss.getIsfristactive() == 1) {
			List szsstlist = checkDao
					.find("from Stsschecktab where  id.isalesvoucherid="
							+ stss.getId().getIsalesvoucherid()
							+ " and id.iticketstationid="
							+ stss.getId().getIticketstationid()
							+ "   and  id.isalesvoucherdetailsid="
							+ stss.getId().getIsalesvoucherdetailsid()
							+ " and id.szsoldticketid="
							+ stss.getId().getSzsoldticketid()
							+ " and ipassedtimes>0");
			if (szsstlist == null || szsstlist.size() == 0) {
				// 表示一次票没有检过

				String startdate = Tools.getTodayString();

				List sscbtlist = checkDao
						.find("from Stsschecktab where  id.isalesvoucherid="
								+ stss.getId().getIsalesvoucherid()
								+ " and id.iticketstationid="
								+ stss.getId().getIticketstationid()
								+ "   and  id.isalesvoucherdetailsid="
								+ stss.getId().getIsalesvoucherdetailsid()
								+ " and id.szsoldticketid="
								+ stss.getId().getSzsoldticketid()
								+ "  and isvalid not in (-1,0)");
				for (int a = 0; a < sscbtlist.size(); a++) {
					String enddate = Tools.getDate(startdate, stss
							.getValidityday().intValue() - 1);
					Stsschecktab stsub = (Stsschecktab) sscbtlist.get(a);
					stsub.setDtbegindate(startdate + " 00:00:00");
					stsub.setDtenddate(enddate + " 23:59:59");
					this.checkDao.update(stsub);
				}

			}
		}
		dtbegindate = stss.getDtbegindate().substring(2);
		dtenddate = stss.getDtenddate().substring(2);
		String jpnumb1 = "1";
		if (stss.getMpasstimes() != null) {
			jpnumb1 = stss.getMpasstimes().toString();
		}
		try {
			// 验证有效时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = df.parse(stss.getDtbegindate());
			Calendar begcalendar = Calendar.getInstance();
			begcalendar.setTime(d1);
			Date d2 = df.parse(stss.getDtenddate());
			Calendar endcalendar = Calendar.getInstance();
			endcalendar.setTime(d2);

			if (now.before(begcalendar)) {
				rs.addRow(new String[] { "-4" });// 未到检票开始时间
				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { jpnumb1 });
				return rs;
			}

			if (now.after(endcalendar)) {
				rs.addRow(new String[] { "-5" });// 已过有效期
				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { jpnumb1 });
				return rs;
			}
			// 读取竹筏票seq
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (stss.getIpasstimes() > 0) {
			if (stss.getIpasstimes() - stss.getIpassedtimes() <= 0) {
				rs.addRow(new String[] { "-9" });// 已检过

				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);
				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { "0" });
				return rs;
			}
		}
		List zsstlist = checkDao
				.find("from Stsschecktab where  id.isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and id.iticketstationid="
						+ stss.getId().getIticketstationid()
						+ "   and  id.isalesvoucherdetailsid="
						+ stss.getId().getIsalesvoucherdetailsid()
						+ " and id.szsoldticketid="
						+ stss.getId().getSzsoldticketid()
						+ " and igardengateid="
						+ acc.getId().getIgardengateid()
						+ " and isvalid not in (-1,0)");
		if (stss.getByusage() == 0) {

			Long itimeinterval = stss.getItimeinterval();// 检票间隔时间
			String lastcheckdate = stss.getDtlastcheckdate();// 最后检票时间
			if (stss.getIpasstimes() == 0) {
				if (lastcheckdate != null && !lastcheckdate.equals("")) {
					// 判断检票时间间隔
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					Calendar calendar = Calendar.getInstance();
					Date d1 = df.parse(lastcheckdate);
					calendar.setTime(d1);
					calendar.add(Calendar.SECOND, itimeinterval.intValue());
					if (now.before(calendar)) {
						System.out.println("changeCheckTicket:-10");
						rs.addRow(new String[] { "-10" });//
						rs = getcanshu11(rs, tickettypename, mactualsaleprice,
								icrowdkindid, szcrowdkindname, dtbegindate,
								dtenddate, bsfilebinary, szimagepath, szmemo,
								seq, tripname);

						rs = getchecklist(rs, stss);
						rs.addRow(new String[] { jpnumb1 });
						return rs;
					}
				}
			}

			String daytime = Tools.getDayTimes();
			// 验票方式
			// 检票流水

			if (stss.getBywicketconsumetype().equals("03")) {
				System.out.println("一单一检");

				for (int c = 0; c < zsstlist.size(); c++) {
					Stsschecktab stssz = (Stsschecktab) zsstlist.get(c);
					stssz.setIpassedtimes(stssz.getIpassedtimes()
							+ stssz.getMpasstimes());
					stssz.setDtlastcheckdate(daytime);
					stssz.setByisout(new Long(1));
					checkDao.update(stssz);
				}

				Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
						daytime, 1L, OtherString);
				checkDao.save(checkt);

				rs.addRow(new String[] { "3" });// 检票成功,放行
				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { jpnumb1 });
				return rs;
			} else {
				if (stss.getByregfingerprinttype().equals("00")) {

					// 只验票，更新售出门票
					// 一票一人

					for (int c = 0; c < zsstlist.size(); c++) {
						Stsschecktab stssz = (Stsschecktab) zsstlist.get(c);
						stssz.setIpassedtimes(stssz.getIpassedtimes()
								+ stssz.getMpasstimes());
						stssz.setDtlastcheckdate(daytime);

						stssz.setByisout(new Long(1));
						checkDao.update(stssz);
					}

					Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
							daytime, 1L, OtherString);

					checkDao.save(checkt);

					rs.addRow(new String[] { "3" });// 检票成功,放行
					rs = getcanshu11(rs, tickettypename, mactualsaleprice,
							icrowdkindid, szcrowdkindname, dtbegindate,
							dtenddate, bsfilebinary, szimagepath, szmemo, seq,
							tripname);

					rs = getchecklist(rs, stss);
					rs.addRow(new String[] { jpnumb1 });

					return rs;
				} else if (stss.getByregfingerprinttype().equals("01")) {
					StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
					sttsid.setIsalesvoucherdetailsid(stss.getId()
							.getIsalesvoucherdetailsid());
					sttsid.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
					sttsid.setIticketstationid(stss.getId()
							.getIticketstationid());
					sttsid.setSzsoldticketid(stss.getId().getSzsoldticketid());
					Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
							.get(Stssoldticketattesttab.class, sttsid);
					if (stset == null) {
						rs.addRow(new String[] { "1" });// 验票登记指纹
						rs = getcanshu11(rs, tickettypename, mactualsaleprice,
								icrowdkindid, szcrowdkindname, dtbegindate,
								dtenddate, bsfilebinary, szimagepath, szmemo,
								seq, tripname);

						rs = getchecklist(rs, stss);
						rs.addRow(new String[] { jpnumb1 });
						return rs;

					} else {
						bsfilebinary = stset.getBsfilebinary();
						szimagepath = stset.getSzimagepath();
						rs.addRow(new String[] { "2" });
						rs = getcanshu11(rs, tickettypename, mactualsaleprice,
								icrowdkindid, szcrowdkindname, dtbegindate,
								dtenddate, bsfilebinary, szimagepath, szmemo,
								seq, tripname);

						rs = getchecklist(rs, stss);
						rs.addRow(new String[] { jpnumb1 });
						return rs;
					}
					// 指纹
				} else if (stss.getByregfingerprinttype().equals("02")) {
					// 身份证认
				} else if (stss.getByregfingerprinttype().equals("03")) {
					// 人像抓拍,
				} else if (stss.getByregfingerprinttype().equals("04")) {
					// 指纹识别+身份证认证
				} else if (stss.getByregfingerprinttype().equals("05")) {
					// 指纹识别+人像抓拍
					// 判断指纹数据是否存在
					// Edmtickettypetab
					// eticket=(Edmtickettypetab)checkDao.get(Edmtickettypetab.class,
					// stss.getItickettypeid());
					if (stss.getByuselimit() == 1) {
						// 长期有效票
						StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
						sttsid.setIsalesvoucherdetailsid(stss.getId()
								.getIsalesvoucherdetailsid());
						sttsid.setIsalesvoucherid(stss.getId()
								.getIsalesvoucherid());
						sttsid.setIticketstationid(stss.getId()
								.getIticketstationid());
						sttsid.setSzsoldticketid(stss.getId()
								.getSzsoldticketid());
						Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
								.get(Stssoldticketattesttab.class, sttsid);

						if (stset == null) {
							rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
							rs = getcanshu11(rs, tickettypename,
									mactualsaleprice, icrowdkindid,
									szcrowdkindname, dtbegindate, dtenddate,
									bsfilebinary, szimagepath, szmemo, seq,
									tripname);

							rs = getchecklist(rs, stss);
							rs.addRow(new String[] { jpnumb1 });
							return rs;
						} else {
							bsfilebinary = stset.getBsfilebinary();
							szimagepath = stset.getSzimagepath();
							rs.addRow(new String[] { "2" });// 验票验证指纹
							rs = getcanshu11(rs, tickettypename,
									mactualsaleprice, icrowdkindid,
									szcrowdkindname, dtbegindate, dtenddate,
									bsfilebinary, szimagepath, szmemo, seq,
									tripname);

							rs = getchecklist(rs, stss);
							rs.addRow(new String[] { jpnumb1 });
							return rs;
						}
					} else {
						Sysparv5 v5 = (Sysparv5) checkDao.get(Sysparv5.class,
								new Sysparv5Id("YZCS", "00"));
						if (v5.getPmvb().equals("0")) {
							// 指纹验证全局变量 为0 所有票务不验证指纹 不用登记指纹也不用验证指纹 放行
							for (int c = 0; c < zsstlist.size(); c++) {
								Stsschecktab stssz = (Stsschecktab) zsstlist
										.get(c);
								stssz.setIpassedtimes(stssz.getIpassedtimes()
										+ stssz.getMpasstimes());
								stssz.setDtlastcheckdate(daytime);

								stssz.setByisout(new Long(1));
								checkDao.update(stssz);
							}
							Ticketchecklist checkt = this.getTicketchecklist(
									acc, stss, daytime, 1L, OtherString);

							// 李进修改结束
							checkDao.save(checkt);

							rs.addRow(new String[] { "3" });// 检票成功,放行
							rs = getcanshu11(rs, tickettypename,
									mactualsaleprice, icrowdkindid,
									szcrowdkindname, dtbegindate, dtenddate,
									bsfilebinary, szimagepath, szmemo, seq,
									tripname);

							rs = getchecklist(rs, stss);
							rs.addRow(new String[] { jpnumb1 });
							return rs;
						} else {
							// 判断票务的业务属性
							if (stss.getIbusinessid() == 1) {
								// 散客
								Sysparv5 v51 = (Sysparv5) checkDao.get(
										Sysparv5.class, new Sysparv5Id("YZCS",
												"01"));
								if (v51.getPmvb().equals("0")) {
									// 为0 散客不用验证指纹，直接检票通过
									for (int c = 0; c < zsstlist.size(); c++) {
										Stsschecktab stssz = (Stsschecktab) zsstlist
												.get(c);
										stssz.setIpassedtimes(stssz
												.getIpassedtimes()
												+ stssz.getMpasstimes());
										stssz.setDtlastcheckdate(daytime);

										stssz.setByisout(new Long(1));
										checkDao.update(stssz);
									}
									Ticketchecklist checkt = this
											.getTicketchecklist(acc, stss,
													daytime, 1L, OtherString);

									checkDao.save(checkt);

									rs.addRow(new String[] { "3" });// 检票成功,放行
									rs = getcanshu11(rs, tickettypename,
											mactualsaleprice, icrowdkindid,
											szcrowdkindname, dtbegindate,
											dtenddate, bsfilebinary,
											szimagepath, szmemo, seq, tripname);

									rs = getchecklist(rs, stss);
									rs.addRow(new String[] { jpnumb1 });
									return rs;
								} else {
									// 散客要验证指纹

									StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
									sttsid.setIsalesvoucherdetailsid(stss
											.getId()
											.getIsalesvoucherdetailsid());
									sttsid.setIsalesvoucherid(stss.getId()
											.getIsalesvoucherid());
									sttsid.setIticketstationid(stss.getId()
											.getIticketstationid());
									sttsid.setSzsoldticketid(stss.getId()
											.getSzsoldticketid());
									Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
											.get(Stssoldticketattesttab.class,
													sttsid);

									if (stset == null) {
										// 不存在指纹数据
										rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
										rs = getcanshu11(rs, tickettypename,
												mactualsaleprice, icrowdkindid,
												szcrowdkindname, dtbegindate,
												dtenddate, bsfilebinary,
												szimagepath, szmemo, seq,
												tripname);

										rs = getchecklist(rs, stss);
										rs.addRow(new String[] { jpnumb1 });
										return rs;

									} else {
										bsfilebinary = stset.getBsfilebinary();
										szimagepath = stset.getSzimagepath();
										// 存在指纹数据
										rs.addRow(new String[] { "2" });// 验票验证指纹
										rs = getcanshu11(rs, tickettypename,
												mactualsaleprice, icrowdkindid,
												szcrowdkindname, dtbegindate,
												dtenddate, bsfilebinary,
												szimagepath, szmemo, seq,
												tripname);

										rs = getchecklist(rs, stss);
										rs.addRow(new String[] { jpnumb1 });
										return rs;
									}
								}
							} else if (stss.getIbusinessid() == 2) {
								// 团队
								Sysparv5 v52 = (Sysparv5) checkDao.get(
										Sysparv5.class, new Sysparv5Id("YZCS",
												"02"));
								if (v52.getPmvb().equals("0")) {
									// 全部不验证指纹
									for (int c = 0; c < zsstlist.size(); c++) {
										Stsschecktab stssz = (Stsschecktab) zsstlist
												.get(c);
										stssz.setIpassedtimes(stssz
												.getIpassedtimes()
												+ stssz.getMpasstimes());
										stssz.setDtlastcheckdate(daytime);

										stssz.setByisout(new Long(1));
										checkDao.update(stssz);
									}
									Ticketchecklist checkt = this
											.getTicketchecklist(acc, stss,
													daytime, 1L, OtherString);

									// 李进修改结束

									checkDao.save(checkt);

									rs.addRow(new String[] { "3" });// 检票成功,放行
									rs = getcanshu11(rs, tickettypename,
											mactualsaleprice, icrowdkindid,
											szcrowdkindname, dtbegindate,
											dtenddate, bsfilebinary,
											szimagepath, szmemo, seq, tripname);

									rs = getchecklist(rs, stss);
									rs.addRow(new String[] { jpnumb1 });
									return rs;
								} else if (v52.getPmvb().equals("1")) {
									// 全部验证指纹

									StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
									sttsid.setIsalesvoucherdetailsid(stss
											.getId()
											.getIsalesvoucherdetailsid());
									sttsid.setIsalesvoucherid(stss.getId()
											.getIsalesvoucherid());
									sttsid.setIticketstationid(stss.getId()
											.getIticketstationid());
									sttsid.setSzsoldticketid(stss.getId()
											.getSzsoldticketid());
									Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
											.get(Stssoldticketattesttab.class,
													sttsid);
									if (stset == null) {
										if (stss.getIpartitionsign() == 1) {

											rs.addRow(new String[] { "99" });// 放行卡放行
											rs = getcanshu11(rs,
													tickettypename,
													mactualsaleprice,
													icrowdkindid,
													szcrowdkindname,
													dtbegindate, dtenddate,
													bsfilebinary, szimagepath,
													szmemo, seq, tripname);

											rs = getchecklist(rs, stss);
											rs.addRow(new String[] { jpnumb1 });
											return rs;
										} else {
											rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
											rs = getcanshu11(rs,
													tickettypename,
													mactualsaleprice,
													icrowdkindid,
													szcrowdkindname,
													dtbegindate, dtenddate,
													bsfilebinary, szimagepath,
													szmemo, seq, tripname);

											rs = getchecklist(rs, stss);
											rs.addRow(new String[] { jpnumb1 });
											return rs;
										}
									} else {
										bsfilebinary = stset.getBsfilebinary();
										szimagepath = stset.getSzimagepath();
										rs.addRow(new String[] { "2" });// 验票验证指纹
										rs = getcanshu11(rs, tickettypename,
												mactualsaleprice, icrowdkindid,
												szcrowdkindname, dtbegindate,
												dtenddate, bsfilebinary,
												szimagepath, szmemo, seq,
												tripname);

										rs = getchecklist(rs, stss);
										rs.addRow(new String[] { jpnumb1 });
										return rs;
									}
								} else if (v52.getPmvb().equals("2")) {
									// 抽样验证指纹

									StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
									sttsid.setIsalesvoucherdetailsid(stss
											.getId()
											.getIsalesvoucherdetailsid());
									sttsid.setIsalesvoucherid(stss.getId()
											.getIsalesvoucherid());
									sttsid.setIticketstationid(stss.getId()
											.getIticketstationid());
									sttsid.setSzsoldticketid(stss.getId()
											.getSzsoldticketid());
									Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao
											.get(Stssoldticketattesttab.class,
													sttsid);
									if (stset == null) {

										rs.addRow(new String[] { "1" });// 验票登记指纹并抓拍人像
										rs = getcanshu11(rs, tickettypename,
												mactualsaleprice, icrowdkindid,
												szcrowdkindname, dtbegindate,
												dtenddate, bsfilebinary,
												szimagepath, szmemo, seq,
												tripname);

										rs = getchecklist(rs, stss);
										rs.addRow(new String[] { jpnumb1 });
										return rs;

									} else {
										// 读取随机数

										List fglist = checkDao
												.find(" from Fingerprintrandom where isalesvoucherid="
														+ stss.getIsalesvoucherid()
														+ " and iticketstationid="
														+ stss.getIticketstationid()
														+ " and endtime>='"
														+ daytime + "'");
										if (fglist == null
												|| fglist.size() == 0) {
											// 删除前面数据

											List fgqlist = checkDao
													.find(" from Fingerprintrandom where isalesvoucherid="
															+ stss.getIsalesvoucherid()
															+ " and iticketstationid="
															+ stss.getIticketstationid()
															+ " and endtime<'"
															+ daytime + "'");
											if (fgqlist != null
													&& fgqlist.size() > 0) {
												for (int i = 0; i < fgqlist
														.size(); i++) {
													Fingerprintrandom finger = (Fingerprintrandom) fgqlist
															.get(i);
													checkDao.delete(finger);
												}
											}

											// 没有随机数
											// 读出该订单中的该园门检票总数量

											List szsstlist = checkDao
													.findBySqlToMap(" select distinct szsoldticketid from Stssoldticketsubtab where  iticketstationid="
															+ stss.getIticketstationid()
															+ "   and isalesvoucherid="
															+ stss.getIsalesvoucherid()
															+ "   and igardengateid="
															+ acc.getId()
															.getIgardengateid());

											// 编号组成string
											String randnumber = "";
											Long dtPercentage = Long
													.parseLong(v52.getPmvc());// 团队百分比
											// 计算出产生几个随机数
											Long numb = szsstlist.size()
													* dtPercentage / 100;
											if (numb == 0) {
												numb = new Long(1);
											}

											Random rd = new Random();

											for (int i = 1; i <= numb; i++) {
												int cnumb = rd
														.nextInt(szsstlist
																.size());

												if (cnumb == 0) {
													cnumb = 1;
												}
												if (cnumb < 0) {
													cnumb = -cnumb;
												}
												if (i == 0) {
													// 将随机数保存到randnumber
													randnumber = "," + cnumb
															+ ",";
												} else {
													if (randnumber.indexOf(","
															+ cnumb + ",") == -1) {
														// randnumber没有新生成的随机数
														randnumber = randnumber
																+ cnumb + ",";
													}
												}

											}

											Fingerprintrandom finger = new Fingerprintrandom();
											finger.setIbusinessid(new Long(2));
											finger.setIgardengateid(stss
													.getIgardengateid());
											finger.setIsalesvoucherid(stss
													.getIsalesvoucherid());
											finger.setIticketstationid(stss
													.getIticketstationid());
											finger.setStarttime(daytime);
											String jgtime = "30";
											if (v52.getPmvd() != null
													&& !v52.getPmvd()
													.equals("")) {
												jgtime = v52.getPmvd();
											}

											SimpleDateFormat df = new SimpleDateFormat(
													"yyyy-MM-dd HH:mm:ss");
											Calendar calendar = Calendar
													.getInstance();
											Date d1 = df.parse(daytime);
											calendar.setTime(d1);
											calendar.add(
													Calendar.SECOND,
													Integer.parseInt(jgtime) * 60);
											finger.setEndtime(Tools
													.getDayTimes(calendar));
											if (randnumber.length() > 3900) {
												randnumber.substring(0, 3900);
											}
											finger.setRandomnumber(randnumber);

											if (randnumber.indexOf(",1,") == -1) {
												// 已检一张票

												finger.setJnumber(new Long(1));
												checkDao.save(finger);
												for (int c = 0; c < zsstlist
														.size(); c++) {
													Stsschecktab stssz = (Stsschecktab) zsstlist
															.get(c);
													stssz.setIpassedtimes(stssz
															.getIpassedtimes()
															+ stssz.getMpasstimes());
													stssz.setDtlastcheckdate(daytime);

													stssz.setByisout(new Long(1));
													checkDao.update(stssz);
												}

												Ticketchecklist checkt = this
														.getTicketchecklist(
																acc, stss,
																daytime, 1L,
																OtherString);

												// 李进修改结束
												checkDao.save(checkt);

												rs.addRow(new String[] { "3" });// 检票成功,放行
												rs = getcanshu11(rs,
														tickettypename,
														mactualsaleprice,
														icrowdkindid,
														szcrowdkindname,
														dtbegindate, dtenddate,
														bsfilebinary,
														szimagepath, szmemo,
														seq, tripname);

												rs = getchecklist(rs, stss);
												rs.addRow(new String[] { jpnumb1 });
												return rs;
											} else {
												System.out.println("抽样10");
												// 需验证指纹，一张票没有检
												finger.setJnumber(new Long(0));
												checkDao.save(finger);
												rs.addRow(new String[] { "2" });
												bsfilebinary = stset
														.getBsfilebinary();
												szimagepath = stset
														.getSzimagepath();
												rs = getcanshu11(rs,
														tickettypename,
														mactualsaleprice,
														icrowdkindid,
														szcrowdkindname,
														dtbegindate, dtenddate,
														bsfilebinary,
														szimagepath, szmemo,
														seq, tripname);

												rs = getchecklist(rs, stss);
												rs.addRow(new String[] { jpnumb1 });
												return rs;
											}
										} else {
											// 有随机数
											Fingerprintrandom finger = (Fingerprintrandom) fglist
													.get(0);
											String randnumber = finger
													.getRandomnumber();
											Long jnumber = finger.getJnumber();
											jnumber = jnumber + 1;
											int flag = randnumber.indexOf(","
													+ jnumber + ",");
											if (flag == -1) {
												// 不检验
												finger.setJnumber(jnumber);
												checkDao.update(finger);
												for (int c = 0; c < zsstlist
														.size(); c++) {
													Stsschecktab stssz = (Stsschecktab) zsstlist
															.get(c);
													stssz.setIpassedtimes(stssz
															.getIpassedtimes()
															+ stssz.getMpasstimes());
													stssz.setDtlastcheckdate(daytime);

													stssz.setByisout(new Long(1));
													checkDao.update(stssz);
												}
												Ticketchecklist checkt = this
														.getTicketchecklist(
																acc, stss,
																daytime, 1L,
																OtherString);

												// 李进修改结束
												checkDao.save(checkt);

												rs.addRow(new String[] { "3" });// 检票成功,放行
												rs = getcanshu11(rs,
														tickettypename,
														mactualsaleprice,
														icrowdkindid,
														szcrowdkindname,
														dtbegindate, dtenddate,
														bsfilebinary,
														szimagepath, szmemo,
														seq, tripname);

												rs = getchecklist(rs, stss);
												rs.addRow(new String[] { jpnumb1 });
												return rs;
											} else {
												bsfilebinary = stset
														.getBsfilebinary();
												szimagepath = stset
														.getSzimagepath();
												rs.addRow(new String[] { "2" });// 验票验证指纹
												rs = getcanshu11(rs,
														tickettypename,
														mactualsaleprice,
														icrowdkindid,
														szcrowdkindname,
														dtbegindate, dtenddate,
														bsfilebinary,
														szimagepath, szmemo,
														seq, tripname);

												rs = getchecklist(rs, stss);
												rs.addRow(new String[] { jpnumb1 });
												return rs;
											}
										}
									}
								}

							} else {
								// 接待
								for (int c = 0; c < zsstlist.size(); c++) {
									Stsschecktab stssz = (Stsschecktab) zsstlist
											.get(c);
									stssz.setIpassedtimes(stssz
											.getIpassedtimes()
											+ stssz.getMpasstimes());
									stssz.setDtlastcheckdate(daytime);

									stssz.setByisout(new Long(1));
									checkDao.update(stssz);
								}
								Ticketchecklist checkt = this
										.getTicketchecklist(acc, stss, daytime,
												1L, OtherString);

								checkDao.save(checkt);

								rs.addRow(new String[] { "3" });// 检票成功,放行
								rs = getcanshu11(rs, tickettypename,
										mactualsaleprice, icrowdkindid,
										szcrowdkindname, dtbegindate,
										dtenddate, bsfilebinary, szimagepath,
										szmemo, seq, tripname);

								rs = getchecklist(rs, stss);
								rs.addRow(new String[] { jpnumb1 });
								return rs;
							}
						}
					}
				}

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { "1" });
				return rs;
			}
		} else if (stss.getByusage() == 1) {
			// 一票多人销售

			Long itimeinterval = stss.getItimeinterval();// 检票间隔时间
			String lastcheckdate = stss.getDtlastcheckdate();// 最后检票时间
			if (lastcheckdate != null && !lastcheckdate.equals("")) {
				// 判断检票时间间隔
				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				Date d1 = df.parse(lastcheckdate);
				calendar.setTime(d1);
				calendar.add(Calendar.SECOND, itimeinterval.intValue());

				if (now.before(calendar)) {
					// 当前时间在间隔时间内
					if (stss.getMsingletimes() == stss.getMsingledtimes()) {
						// 单次 票数一检完
						rs.addRow(new String[] { "-10" });//
						rs = getcanshu11(rs, tickettypename,
								mactualsaleprice, icrowdkindid,
								szcrowdkindname, dtbegindate, dtenddate,
								bsfilebinary, szimagepath, szmemo, seq,
								tripname);

						rs = getchecklist(rs, stss);
						return rs;
					}
				}
			}

			String daytime = Tools.getDayTimes();
			if (stss.getBywicketconsumetype().equals("01")) {
				// 一检一人
				// stssz.getIpasstimes() > 0表示有次数限制 stssz.getIpasstimes() = 0
				// 不限制次数

				// 验票方式
				// 检票流水
				// if (opww.getByregfingerprinttype().equals("00")) {
				// 只检票
				for (int c = 0; c < zsstlist.size(); c++) {
					Stsschecktab stssz = (Stsschecktab) zsstlist.get(c);
					if (stssz.getIpasstimes() > 0) {
						// 限定次数的检票 不需要判断时间 间隔
						stssz.setIpassedtimes(stssz.getIpassedtimes()
								+ stssz.getMpasstimes());
						stssz.setMsingledtimes(stssz.getMsingledtimes()
								+ +stssz.getMpasstimes());
						stssz.setDtlastcheckdate(daytime);
						stssz.setDtmakedate(daytime);
						stssz.setByisout(new Long(1));
						checkDao.update(stssz);

					} else if (stssz.getIpasstimes() == 0) {
						if (lastcheckdate != null && !lastcheckdate.equals("")) {
							// 判断检票时间间隔
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							Calendar calendar = Calendar.getInstance();
							Date d1 = df.parse(lastcheckdate);
							calendar.setTime(d1);
							calendar.add(Calendar.SECOND,
									itimeinterval.intValue());
							if (now.before(calendar)) {
								// 间隔时间内

								stssz.setIpassedtimes(stssz.getIpassedtimes()
										+ stssz.getMpasstimes());
								stssz.setMsingledtimes(stssz.getMsingledtimes()
										+ stssz.getMpasstimes());
								stssz.setDtmakedate(daytime);
								stssz.setByisout(new Long(1));
								checkDao.update(stssz);
							} else {
								// 间隔时间外

								stssz.setIpassedtimes(stssz.getIpassedtimes()
										+ stssz.getMpasstimes());
								stssz.setMsingledtimes(stssz.getMpasstimes());
								stssz.setDtlastcheckdate(daytime);
								stssz.setDtmakedate(daytime);
								stssz.setByisout(new Long(1));
								checkDao.update(stssz);
							}
						} else {
							// 第一次检票

							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ stssz.getMpasstimes());
							stssz.setMsingledtimes(stssz.getMsingledtimes()
									+ stssz.getMpasstimes());
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						}
					}

				}
				Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
						daytime, 1L, OtherString);
				checkDao.save(checkt);
				rs.addRow(new String[] { "3" });// 检票成功,放行
				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { "1" });
				return rs;
				// }else{
				// 其他检票方式
				// }
			} else if (stss.getBywicketconsumetype().equals("02")) {
				// 一检多人
				// if (opww.getByregfingerprinttype().equals("00")) {
				// 只检票
				Long jpnumb = new Long(0);
				for (int c = 0; c < zsstlist.size(); c++) {
					Stsschecktab stssz = (Stsschecktab) zsstlist.get(c);
					Long synumb = stssz.getMsingletimes()
							- stssz.getMsingledtimes();
					if (stssz.getIpasstimes() > 0) {
						// 限定次数的检票 不需要判断时间 间隔
						// 剩余可检数量
						synumb = stssz.getIpasstimes()
								- stssz.getIpassedtimes();
						if (synumb > stssz.getMpasstimes()) {
							// 剩余可检数>每次检票数
							jpnumb = stssz.getMpasstimes();
							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ stssz.getMpasstimes());
							stssz.setMsingledtimes(stssz.getMpasstimes());
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						} else {
							jpnumb = synumb;
							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ synumb);
							stssz.setMsingledtimes(stssz.getMsingledtimes()
									+ synumb);
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						}
					} else if (stssz.getIpasstimes() == 0) {
						if (lastcheckdate != null && !lastcheckdate.equals("")) {
							// 判断检票时间间隔
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							Calendar calendar = Calendar.getInstance();
							Date d1 = df.parse(lastcheckdate);
							calendar.setTime(d1);
							calendar.add(Calendar.SECOND,
									itimeinterval.intValue());
							if (now.before(calendar)) {
								// 间隔时间内
								if (synumb > stssz.getMpasstimes()) {
									jpnumb = stssz.getMpasstimes();
									stssz.setIpassedtimes(stssz
											.getIpassedtimes()
											+ stssz.getMpasstimes());
									stssz.setMsingledtimes(stssz
											.getMsingledtimes()
											+ stssz.getMpasstimes());
									stssz.setDtmakedate(daytime);
									stssz.setByisout(new Long(1));
									checkDao.update(stssz);
								} else {
									jpnumb = synumb;
									stssz.setIpassedtimes(stssz
											.getIpassedtimes() + synumb);
									stssz.setMsingledtimes(stssz
											.getMsingledtimes() + synumb);
									stssz.setDtmakedate(daytime);
									stssz.setByisout(new Long(1));
									checkDao.update(stssz);
								}
							} else {
								// 间隔时间外
								jpnumb = stssz.getMpasstimes();
								stssz.setIpassedtimes(stssz.getIpassedtimes()
										+ stssz.getMpasstimes());
								stssz.setMsingledtimes(stssz.getMpasstimes());
								stssz.setDtlastcheckdate(daytime);
								stssz.setDtmakedate(daytime);
								stssz.setByisout(new Long(1));
								checkDao.update(stssz);
							}
						} else {
							// 第一次检票
							jpnumb = stssz.getMpasstimes();
							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ stssz.getMpasstimes());
							stssz.setMsingledtimes(stssz.getMsingledtimes()
									+ stssz.getMpasstimes());
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						}
					}

				}
				Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
						daytime,jpnumb, OtherString);

				checkDao.save(checkt);

				rs.addRow(new String[] { "3" });// 检票成功,放行
				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { jpnumb.toString() });
				return rs;

				// }else{
				// 其他检票方式
				// }
			} else if (stss.getBywicketconsumetype().equals("03")) {
				System.out.println("一单一检");
				// 一检多人
				// if (opww.getByregfingerprinttype().equals("00")) {
				// 只检票
				Long jpnumb = new Long(0);
				for (int c = 0; c < zsstlist.size(); c++) {
					Stsschecktab stssz = (Stsschecktab) zsstlist.get(c);
					Long synumb = stssz.getMsingletimes()
							- stssz.getMsingledtimes();
					if (stssz.getIpasstimes() > 0) {
						// 限定次数的检票 不需要判断时间 间隔
						// 剩余可检数量
						if (synumb > stssz.getMpasstimes()) {
							// 剩余可检数>每次检票数
							jpnumb = stssz.getMpasstimes();
							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ stssz.getMpasstimes());
							stssz.setMsingledtimes(stssz.getMsingledtimes()
									+ stssz.getMpasstimes());
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						} else {
							jpnumb = synumb;
							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ synumb);
							stssz.setMsingledtimes(stssz.getMsingledtimes()
									+ synumb);
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						}
					} else if (stssz.getIpasstimes() == 0) {
						if (lastcheckdate != null && !lastcheckdate.equals("")) {
							// 判断检票时间间隔
							SimpleDateFormat df = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							Calendar calendar = Calendar.getInstance();
							Date d1 = df.parse(lastcheckdate);
							calendar.setTime(d1);
							calendar.add(Calendar.SECOND,
									itimeinterval.intValue());
							if (now.before(calendar)) {
								// 间隔时间内
								if (synumb > stssz.getMpasstimes()) {
									jpnumb = stssz.getMpasstimes();
									stssz.setIpassedtimes(stssz
											.getIpassedtimes()
											+ stssz.getMpasstimes());
									stssz.setMsingledtimes(stssz
											.getMsingledtimes()
											+ stssz.getMpasstimes());
									stssz.setDtmakedate(daytime);
									stssz.setByisout(new Long(1));
									checkDao.update(stssz);
								} else {
									jpnumb = synumb;
									stssz.setIpassedtimes(stssz
											.getIpassedtimes() + synumb);
									stssz.setMsingledtimes(stssz
											.getMsingledtimes() + synumb);
									stssz.setDtmakedate(daytime);
									stssz.setByisout(new Long(1));
									checkDao.update(stssz);
								}
							} else {
								// 间隔时间外
								jpnumb = stssz.getMpasstimes();
								stssz.setIpassedtimes(stssz.getIpassedtimes()
										+ stssz.getMpasstimes());
								stssz.setMsingledtimes(stssz.getMpasstimes());
								stssz.setDtlastcheckdate(daytime);
								stssz.setDtmakedate(daytime);
								stssz.setByisout(new Long(1));
								checkDao.update(stssz);
							}
						} else {
							// 第一次检票
							jpnumb = stssz.getMpasstimes();
							stssz.setIpassedtimes(stssz.getIpassedtimes()
									+ stssz.getMpasstimes());
							stssz.setMsingledtimes(stssz.getMsingledtimes()
									+ stssz.getMpasstimes());
							stssz.setDtlastcheckdate(daytime);
							stssz.setDtmakedate(daytime);
							stssz.setByisout(new Long(1));
							checkDao.update(stssz);
						}
					}
				}
				Ticketchecklist checkt = this.getTicketchecklist(acc, stss,
						daytime, jpnumb,OtherString);

				checkDao.save(checkt);

				rs.addRow(new String[] { "3" });// 检票成功,放行
				rs = getcanshu11(rs, tickettypename, mactualsaleprice,
						icrowdkindid, szcrowdkindname, dtbegindate, dtenddate,
						bsfilebinary, szimagepath, szmemo, seq, tripname);

				rs = getchecklist(rs, stss);
				rs.addRow(new String[] { jpnumb.toString() });
				return rs;

				// }else{
				// 其他检票方式
				// }

			}
		}

		return rs;
	}

	public ResultBean getchecklist(ResultBean rs, Stsschecktab stss)
			throws Exception {
		List cklist = checkDao
				.findBySqlToMap("select * from ( select g.szgardengatename,s.dtmakedate,s.zfseq  from Ticketchecklist s,Esbgardengatetab g where s.isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and  s.iticketstationid="
						+ stss.getId().getIticketstationid()
						+ " and s.szsoldticketid="
						+ stss.getId().getSzsoldticketid()
						+ " and s.isalesvoucherdetailsid="
						+ stss.getId().getIsalesvoucherdetailsid()
						+ " and s.igardengateid=g.igardengateid and s.iscenicid=g.iscenicid order by dtmakedate　desc ) where rownum<10");
		if (cklist.size() >= 9) {
			for (int i = 0; i < 9; i++) {
				Map map1 = (Map) cklist.get(i);
				rs.addRow(new String[] { map1.get("SZGARDENGATENAME") + "  "
						+ map1.get("DTMAKEDATE").toString().substring(2) });
			}
		} else {
			for (int i = 0; i < cklist.size(); i++) {
				Map map1 = (Map) cklist.get(i);
				rs.addRow(new String[] { map1.get("SZGARDENGATENAME") + "  "
						+ map1.get("DTMAKEDATE").toString().substring(2) });
			}
			for (int i = 0; i < 9 - cklist.size(); i++) {
				rs.addRow(new String[] { " " });
			}
		}
		return rs;
	}

	/**
	 * 检票内部方法
	 *
	 * @param acc
	 * @param stss
	 * @param daytime
	 * @param OtherString
	 * @return
	 * @throws Exception
	 */
	public Ticketchecklist getTicketchecklist(Esbaccessequiptab acc,
											  Stsschecktab stss, String daytime, Long mpasstimes,
											  String... OtherString) throws Exception {
		Ticketchecklist checkt = new Ticketchecklist();

		List<Map> iserialnumlist = new ArrayList();
		iserialnumlist = checkDao
				.findBySqlToMapnocolsesession("select check_id.nextval  from dual");
		Long checkid = new Long(
				(((Map) iserialnumlist.get(0)).get("NEXTVAL")).toString());

		checkt.setCheckid(checkid);
		checkt.setIgardengateid(acc.getId().getIgardengateid());
		checkt.setIaccessequipid(acc.getId().getIaccessequipid());
		checkt.setIscenicid(acc.getId().getIscenicid());
		checkt.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
		checkt.setIsalesvoucherdetailsid(stss.getId()
				.getIsalesvoucherdetailsid());
		checkt.setIticketstationid(stss.getId().getIticketstationid());
		checkt.setSzsoldticketid(stss.getId().getSzsoldticketid());
		checkt.setDtmakedate(daytime);
		checkt.setInt1(mpasstimes);
		// 李进修改开始

		if (OtherString.length > 1) {
			checkt.setChuanhao(OtherString[0]);
		}
		if (OtherString.length > 2) {
			checkt.setTongxingzhi(OtherString[1]);
		}

		if (OtherString.length > 3) {
			checkt.setNote1(OtherString[2]);
		}
		if (OtherString.length > 4) {
			checkt.setNote2(OtherString[3]);
		}
		return checkt;
	}

	/**
	 * 根据闸机id和票号查询同一园门下该订单所有能通过这个园门的所有票号
	 *
	 * @param accid
	 * @param printno
	 * @return
	 * @throws Exception
	 */
	public List queryprintnolistonetable(String accid, String printno)
			throws Exception {
		List printnolist = new ArrayList();
		List acclist = checkDao
				.find(" from Esbaccessequiptab where id.iaccessequipid="
						+ accid);
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
		List stsslsit = checkDao
				.find(" from Stsschecktab s where s.szticketprintno='"
						+ printno + "'  order by s.dtmakedate desc");
		Stsschecktab stss = (Stsschecktab) stsslsit.get(0);
		printnolist = checkDao
				.findBySqlToMap("select distinct s.szticketprintno  from Stsschecktab s where s.isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and s.iticketstationid="
						+ stss.getId().getIticketstationid()
						+ " and  s.igardengateid="
						+ acc.getId().getIgardengateid() + " and s.isvalid=1");

		return printnolist;
	}

	public boolean isyw(String printno) {

		boolean flag = true;
		List stslist = checkDao
				.find(" from Stsschecktab where szticketprintno='" + printno
						+ "' order by dtmakedate desc");
		Stsschecktab stss = (Stsschecktab) stslist.get(0);
		List zsstlist = checkDao
				.find("from Stsschecktab where  id.isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and id.iticketstationid="
						+ stss.getId().getIticketstationid()
						+ "    and id.isalesvoucherdetailsid="
						+ stss.getId().getIsalesvoucherdetailsid()
						+ " and id.szsoldticketid="
						+ stss.getId().getSzsoldticketid() + "  and isvalid=1");

		for (int i = 0; i < zsstlist.size(); i++) {
			Stsschecktab zstss = (Stsschecktab) zsstlist.get(i);

			if (zstss.getIpasstimes().longValue() == 0) {
				// 有无限次入园
				flag = false;
			} else {
				if (zstss.getIpasstimes().longValue() > zstss.getIpassedtimes()
						.longValue()) {
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * 向服务上传指纹数据
	 *
	 * @param ticketstr
	 *            检票用的票号
	 * @param md5str
	 *            准确性校验方法
	 * @return
	 */
	public int changeticketPassIdintput(String accid, String ticketno,
										String md5str) throws Exception {

		List sstlist = checkDao
				.find("from Stsschecktab where ( szticketprintno='" + ticketno
						+ "' or myzj='" + ticketno
						+ "') and byvalidity='00' order by dtmakedate desc");
		Stsschecktab stss = (Stsschecktab) sstlist.get(0);
		String daytime = Tools.getDayTimes();
		List fglist = checkDao
				.find(" from Fingerprintrandom where isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and iticketstationid="
						+ stss.getId().getIticketstationid() + " and endtime>'"
						+ daytime + "'");
		if (fglist.size() > 0) {
			Fingerprintrandom f = (Fingerprintrandom) fglist.get(0);
			f.setJnumber(f.getJnumber() + 1);
			checkDao.update(f);
		}

		List acclist = checkDao
				.find(" from Esbaccessequiptab where id.iaccessequipid="
						+ accid);
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);
		Long igardengateid = acc.getId().getIgardengateid();// 园门ID
		List zsstlist = checkDao
				.find("from Stsschecktab where Isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and iticketstationid="
						+ stss.getId().getIticketstationid()
						+ " and isalesvoucherdetailsid="
						+ stss.getId().getIsalesvoucherdetailsid()
						+ " and szsoldticketid="
						+ stss.getId().getSzsoldticketid()
						+ " and igardengateid=" + igardengateid);

		// 更新随机表中的数据

		for (int i = 0; i < zsstlist.size(); i++) {
			Stsschecktab st = (Stsschecktab) zsstlist.get(i);
			st.setIpassedtimes(st.getIpassedtimes() + 1);
			st.setDtlastcheckdate(daytime);
			st.setByisout(new Long(1));

			checkDao.update(st);
		}

		Ticketchecklist checkt;
		String[] ortherstring = new String[] {};
		try {
			stss.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
			stss.setIsalesvoucherdetailsid(stss.getId()
					.getIsalesvoucherdetailsid());
			stss.setIticketstationid(stss.getId().getIticketstationid());
			stss.setSzsoldticketid(stss.getId().getSzsoldticketid());
			checkt = this.getTicketchecklist(acc, stss, daytime,1L, ortherstring);
			checkDao.save(checkt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * *上传指纹,更新闸机数据 Describe:
	 *
	 * @see com.ectrip.ticket.webservice.check.impl.checkticket.service.iservice.ICheckService#ticketZwintput(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 * @param accid
	 *            闸机ID
	 * @param ticketno
	 *            票号
	 * @param ziwenno
	 *            指纹数据
	 * @param szidcard
	 *            身份证号码
	 * @param szimagepath图片路径
	 * @param md5str
	 * @return
	 * @throws Exception
	 * @author yuanchengjun Date:2011-11-8
	 */

	public int changeticketZwintput(String accid, String ticketno,
									String ziwenno, String szidcard, String szimagepath, String md5str) {
		// 根据票号找到对应的出票数据
		String daytime = Tools.getDayTimes();
		List sstlist = checkDao
				.find("from Stsschecktab where  ( szticketprintno='" + ticketno
						+ "' or myzj='" + ticketno
						+ "' ) and isvalid=1 order by dtmakedate desc");
		Stsschecktab stss = (Stsschecktab) sstlist.get(0);
		// 根据出票数据保存有对应的身份验证信息
		StssoldticketattesttabId id = new StssoldticketattesttabId();
		id.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
		id.setIsalesvoucherdetailsid(stss.getId().getIsalesvoucherdetailsid());
		id.setIticketstationid(stss.getId().getIticketstationid());
		id.setSzsoldticketid(stss.getId().getSzsoldticketid());
		List stsist = checkDao
				.find("from Stssoldticketattesttab where id.isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ "and id.isalesvoucherdetailsid="
						+ stss.getId().getIsalesvoucherdetailsid()
						+ " and id.iticketstationid="
						+ stss.getId().getIticketstationid()
						+ " and id.szsoldticketid="
						+ stss.getId().getSzsoldticketid());

		if (stsist == null || stsist.size() == 0) {

			Stssoldticketattesttab sts = new Stssoldticketattesttab();
			sts.setId(id);
			sts.setBsfilebinary(ziwenno);
			sts.setByfactregtype("05");
			sts.setSzidcard(szidcard);
			sts.setSzimagepath(szimagepath);
			sts.setIpartitionsign(new Long(0));
			sts.setDtmakedate(daytime);
			sts.setByisout(new Long(1));
			checkDao.save(sts);
		} else {
			Stssoldticketattesttab sts = (Stssoldticketattesttab) stsist.get(0);
			sts.setBsfilebinary(ziwenno);
			sts.setByfactregtype("05");
			sts.setSzidcard(szidcard);
			sts.setSzimagepath(szimagepath);
			sts.setIpartitionsign(new Long(0));
			sts.setDtmakedate(daytime);
			sts.setByisout(new Long(1));
			checkDao.update(sts);
		}
		List acclist = checkDao
				.find(" from Esbaccessequiptab where id.iaccessequipid="
						+ accid);
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);

		List zsstlist = checkDao
				.find("from Stsschecktab where Isalesvoucherid="
						+ stss.getId().getIsalesvoucherid()
						+ " and iticketstationid="
						+ stss.getId().getIticketstationid()
						+ " and isalesvoucherdetailsid="
						+ stss.getId().getIsalesvoucherdetailsid()
						+ " and szsoldticketid="
						+ stss.getId().getSzsoldticketid()
						+ " and igardengateid="
						+ acc.getId().getIgardengateid() + " and isvalid=1");

		for (int i = 0; i < zsstlist.size(); i++) {
			Stsschecktab st = (Stsschecktab) zsstlist.get(i);
			st.setIpassedtimes(st.getIpassedtimes() + 1);
			st.setDtlastcheckdate(daytime);
			st.setByisout(new Long(1));
			checkDao.update(st);
		}

		// 检票流水

		Ticketchecklist checkt;
		String[] ortherstring = new String[] {};
		stss.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
		stss.setIsalesvoucherdetailsid(stss.getId().getIsalesvoucherdetailsid());
		stss.setIticketstationid(stss.getId().getIticketstationid());
		stss.setSzsoldticketid(stss.getId().getSzsoldticketid());
		try {
			checkt = this.getTicketchecklist(acc, stss, daytime,1L, ortherstring);
			checkDao.save(checkt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 1;
	}
	public int stopCheckTicket(Long accid, String szprintno, Long synumb) {
		List acclist = checkDao
				.find(" from Esbaccessequiptab where id.iaccessequipid="
						+ accid);
		if (acclist.size() == 0) {
			return 0;
		}
		Esbaccessequiptab acc = (Esbaccessequiptab) acclist.get(0);

		List zsstlist = checkDao
				.find("from Stsschecktab where  ( szticketprintno='"
						+ szprintno + "' or myzj='" + szprintno
						+ "' )  and igardengateid="
						+ acc.getId().getIgardengateid()
						+ " and isvalid=1 order by dtmakedate desc");

		for (int i = 0; i < zsstlist.size(); i++) {
			Stsschecktab stssz = (Stsschecktab) zsstlist.get(i);
			stssz.setMsingledtimes(stssz.getMsingletimes() - synumb);
			stssz.setIpassedtimes(stssz.getIpassedtimes() - synumb);
			checkDao.update(stssz);
		}
		Stsschecktab stssz = (Stsschecktab) zsstlist.get(0);
		List checklist = checkDao
				.find("from Ticketchecklist where  isalesvoucherid="
						+ stssz.getId().getIsalesvoucherid()
						+ " and and iticketstationid="
						+ stssz.getId().getIticketstationid()
						+ "   and  isalesvoucherdetailsid="
						+ stssz.getId().getIsalesvoucherdetailsid()
						+ " and szsoldticketid="
						+ stssz.getId().getSzsoldticketid()
						+ " and igardengateid="
						+ acc.getId().getIgardengateid()
						+ " order by dtmakedate desc");
		if (checklist != null && checklist.size() > 0) {

			Ticketchecklist check = (Ticketchecklist) checklist.get(0);
			check.setInt1(check.getInt1() - synumb);
			checkDao.update(check);

		}
		return 1;
	}
}

