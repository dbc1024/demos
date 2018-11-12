package com.ectrip.ticket.checkticket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.Tools;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.checkticket.service.iservice.ICheckdengjiService;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.StssoldticketattesttabId;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeefingerprinttab;

@Service
public class CheckdengjiService implements ICheckdengjiService {
	
	@Autowired
	private ICheckDao checkDao;

	public ResultBean changeCheckDaoyou(String carno) throws Exception {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(1);
		rs.setColumnNames(new String[] { "values" });

		// 查询是否导游
		List dlist = checkDao
				.find(" from Opcemployeecardtab where upper(icardno)='"
						+ carno.toUpperCase() + "' and  byisdaoyou=1 and byticketstate=0");

		if (dlist == null || dlist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 无效证件
			return rs;
		} else {
			Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
			List zwlist = checkDao
					.find(" from Opcemployeefingerprinttab where iemployeecardid="
							+ op.getIemployeecardid() + " and byisuse=1");
			if (zwlist == null || zwlist.size() == 0) {
				rs.addRow(new String[] { "1" });// 登记指纹
			} else {
				rs.addRow(new String[] { "2" });// 已验证指纹
			}
		}

		rs.addRow(new String[] { "21" });// 导游
		return rs;
	}

	public ResultBean changeCheckEmployee(String carno) {

		ResultBean rs = new ResultBean();
		rs.setColumnCount(1);
		rs.setColumnNames(new String[] { "values" });

		// 查询是员工卡
		List dlist = checkDao
				.find(" from Opcemployeecardtab where upper(icardno)='"
						+ carno.toUpperCase()
						+ "' and (byisdaoyou=0 or byisdaoyou is null) and byticketstate=0");

		if (dlist == null || dlist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 不是员工卡

		} else {
			Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);

			if (op.getByconsumetype().equals("1")) {
				List zwlist = checkDao
						.find(" from Opcemployeefingerprinttab where iemployeecardid="
								+ op.getIemployeecardid() + " and byisuse=1");
				if (zwlist == null || zwlist.size() == 0) {
					rs.addRow(new String[] { "1" });// 登记指纹
				} else {
					rs.addRow(new String[] { "2" });// 验证指纹
				}

			} else {
				rs.addRow(new String[] { "97" });// 不用登记指纹

			}
		}
		rs.addRow(new String[] { "22" });// 员工卡
		return rs;
	}

	public String CheckPassCrad(String szticketprintno) {
		String date = Tools.getDays();
		String sql = "select s.szticketprintno from Stssoldtickettab s where  s.myzj = '"
				+ szticketprintno
				+ "'  and s.dtenddate>='"
				+ date
				+ "'";
		List list = new ArrayList();
		list = checkDao.find(sql);
		if (list == null || list.size() == 0) {// 判断该卡号在售出门票表中是否是唯一
			list = checkDao.find(" from Opcemployeecardtab op where op.icardno = '"
					+ szticketprintno + "'");
			if (list == null || list.size() == 0) {// 判断该卡号是否是员工
				return "0"; // 都不是
			} else {
				Opcemployeecardtab op = (Opcemployeecardtab) list.get(0);
				if (op.getByisdaoyou() == null || op.getByisdaoyou() == 0) {
					return "2"; // 是员工
				} else {
					return "1"; // 是员工
				}
			}
		} else if (list.size() == 1) {// 在售出门票表中唯一，返回票号
			return (String) list.get(0);
		} else {// 在售出门票表中存在多条数据
			return "-1";
		}
	}

	public ResultBean changeCheckTicket(String szticketprintno) {
		ResultBean rs = new ResultBean();
		rs.setColumnCount(1);
		rs.setColumnNames(new String[] { "values" });
		List sstlist = checkDao
				.find("from Stssoldtickettab where szticketprintno='" + szticketprintno
						+ "' order by dtmakedate desc");
		Stssoldtickettab stss = null;
		if (sstlist == null || sstlist.size() == 0) {
			rs.addRow(new String[] { "-1" });// 无效票
			return rs;
		} else {
			stss = (Stssoldtickettab) sstlist.get(0);
			Edmtickettypetab eticket = (Edmtickettypetab) checkDao.get(
					Edmtickettypetab.class, stss.getItickettypeid());
			if (eticket.getByusage() == 1) {
				rs.addRow(new String[] { "-97" });// 不用登记指纹
				return rs;
			}
			Edmticketproduct etp = (Edmticketproduct) checkDao.get(
					Edmticketproduct.class, stss.getItickettypeid());
			if (etp != null) {
				if (etp.getIszhiwen() == 0) {
					rs.addRow(new String[] { "-97" });// 不用登记指纹
					return rs;
				}
			}
			if (stss.getByvalidity().equals("01")) {
				rs.addRow(new String[] { "-2" });// 票已退订
				return rs;
			}
			if (stss.getByvalidity().equals("02")) {
				rs.addRow(new String[] { "-99" });// 已挂失
				return rs;
			}
			if (Tools.getDayNumb(Tools.getTodayString(), stss.getDtenddate()) < 1) {
				rs.addRow(new String[] { "-5" });//过期
				return rs;
			}
			StssoldticketattesttabId sttsid = new StssoldticketattesttabId();
			sttsid.setIsalesvoucherdetailsid(stss.getId().getIsalesvoucherdetailsid());
			sttsid.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
			sttsid.setIticketstationid(stss.getId().getIticketstationid());
			sttsid.setSzsoldticketid(stss.getId().getSzsoldticketid());
			Stssoldticketattesttab stset = (Stssoldticketattesttab) checkDao.get(
					Stssoldticketattesttab.class, sttsid);
			if (stset == null) {
				rs.addRow(new String[] { "1" });
			} else {
				rs.addRow(new String[] { "2" });
			}
		}
		rs.addRow(new String[] { "20" });
		return rs;
	}
	public int saveZwdengji(String type, String ticketno, String ziwenno) {
		String daytime = Tools.getDayTimes();
		String today=Tools.getTodayString();

		if (type.equals("20")) {
			List sstlist = checkDao
					.find("from Stssoldtickettab where (szticketprintno='" + ticketno
							+ "' or myzj='" + ticketno + "')  and dtenddate>='"
							+ today
							+ "'");
			Stssoldtickettab stss = (Stssoldtickettab) sstlist.get(0);
			// 根据出票数据保存有对应的身份验证信息
			StssoldticketattesttabId id = new StssoldticketattesttabId();
			id.setIsalesvoucherid(stss.getId().getIsalesvoucherid());
			id.setIsalesvoucherdetailsid(stss.getId().getIsalesvoucherdetailsid());
			id.setIticketstationid(stss.getId().getIticketstationid());
			id.setSzsoldticketid(stss.getId().getSzsoldticketid());
			Stssoldticketattesttab sts = new Stssoldticketattesttab();
			sts.setId(id);
			sts.setBsfilebinary(ziwenno);
			sts.setByfactregtype("05");
			sts.setSzidcard("");
			sts.setSzimagepath("");
			sts.setIpartitionsign(new Long(0));
			sts.setDtmakedate(daytime);
			sts.setByisout(new Long(1));
			checkDao.saveOrUpdate(sts);
		} else {
			List dlist = checkDao
					.find(" from Opcemployeecardtab where upper(icardno)='"
							+ ticketno.toUpperCase() + "'");
			Opcemployeecardtab op = (Opcemployeecardtab) dlist.get(0);
			// 保存指纹信息
			Opcemployeefingerprinttab opc = new Opcemployeefingerprinttab();
			opc.setIemployeecardid(op.getIemployeecardid());
			if (type.equals("21")) {
				opc.setUsid(op.getUsid());
			} else {
				opc.setIemployeeid(op.getIemployeeid());
			}
			opc.setIfingersid(new Long(1));
			opc.setSzfeatures(ziwenno);
			opc.setByisuse(new Long(1));
			opc.setIversion(new Long(0));
			Long iemployeefingerprintid = checkDao.getMaxPk("iemployeefingerprintid",
					"Opcemployeefingerprinttab");
			opc.setIemployeefingerprintid(iemployeefingerprintid + 1);
			opc.setDtmakedate(daytime);
			checkDao.save(opc);
		}
		return 1;
	}
}
