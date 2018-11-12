/*package com.ectrip.ticket.service.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.service.dao.idao.IMbMsGDAO;
import com.ectrip.ticket.service.message.WebSendMsg;

*//**
 * 短信发送内,对于已经发送的短信,都将保存在MBSESSAGE表中. 2010-11-22日修改
 * 
 * @author LiJin
 * 
 *//*
public class MbMsGDAO extends GenericDao implements IMbMsGDAO {

	*//**
	 * 检查字符串长度
	 * 
	 * @param strParameter
	 * @param limitLength
	 * @return
	 *//*

	public boolean validateStrByLength(String strParameter, int limitLength) {
		int temp_int = 0;
		byte[] b = strParameter.getBytes();

		for (int i = 0; i < b.length; i++) {
			if (b[i] >= 0) {
				temp_int = temp_int + 1;
			} else {
				temp_int = temp_int + 2;
				i++;
			}
		}

		if (temp_int > limitLength) {
			return false;
		} else {
			return true;
		}
	}

	*//**
	 * 用于旧方法,一个参数,新方法有两个参数
	 * 
	 * @param orid
	 *//*
	public void OrderSend(String orid) {
		OrderSend(orid, "");
	}

	*//**
	 * 短信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendSk(String orid) {

		Mbmessage mbmsg = new Mbmessage();

		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";

		List list = this.find(sql);

		String ls_str = "";

		File file = new File(WebContant.REALPATH + "/WEB-INF/mbsk.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}
				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}

		// 目前只发一条信息
		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);
			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {
				if (ib_truefile) {
					content = content.replaceAll("<companyname/>", WebContant.CORPNAME);
					content = content.replaceAll("<orid/>", orid);
					content = content.replaceAll("<amnt/>", torder.getZfmont().toString());
					content = content.replaceAll("<ornm/>", torder.getOrnm());
					content = content.replaceAll("<orhm/>", torder.getOrhm());
					ls_str = content;

				} else {
					ls_str = WebContant.CORPNAME + "预订信息:订单号:" + orid + ",领票人:" + torder.getOrnm() + ",证件号:"
							+ torder.getOrhm();
				}
				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0001'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					String ls_pmcd = sysparv5.getPmva();
					if (ls_pmcd.equals("1")) {
						this.save(mbmsg);
					}
				}

			}

		}

	}

	*//**
	 * 短信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendReturn(String orid) {

		Mbmessage mbmsg = new Mbmessage();

		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";

		List list = this.find(sql);

		String ls_str = "";

		File file = new File(WebContant.REALPATH + "/WEB-INF/mbreturn.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}
		// 目前只发一条信息
		if (list != null && list.size() >= 1) {

			TOrder torder = (TOrder) list.get(0);
			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {

				if (ib_truefile) {
					content = content.replaceAll("<companyname/>", WebContant.CORPNAME);
					content = content.replaceAll("<orid/>", orid);
					content = content.replaceAll("<amnt/>", torder.getZfmont().toString());
					content = content.replaceAll("<ornm/>", torder.getOrnm());
					content = content.replaceAll("<orhm/>", torder.getOrhm());
					ls_str = content;

				} else {
					ls_str = WebContant.CORPNAME + "确订:订单号:" + orid + ",退订成功，详情上网查询,服务电话:"
							+ WebContant.GetKeyValue("SERVICETELEPHONE");
				}
				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0001'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					String ls_pmcd = sysparv5.getPmva();
					if (ls_pmcd.equals("1")) {
						this.save(mbmsg);
					}
				}
			}
		}
	}

	*//**
	 * 知信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendInfo(String orid, String Strinfo) {

		Mbmessage mbmsg = new Mbmessage();

		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";

		List list = this.find(sql);

		String ls_str = "";
		TOrder torder = new TOrder();
		if (list != null && list.size() >= 1) {
			torder = (TOrder) list.get(0);
			ls_str = Strinfo;
		}

		mbmsg.setRevmbno(torder.getOrph());// 电话
		mbmsg.setNote(ls_str);
		mbmsg.setIsok(0);
		mbmsg.setDtime(Tools.getDayTimes());
		mbmsg.setQuantity(1);

		String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0001'";// 短信是否发送控制
		List list2 = this.find(syssql);
		if (list2 != null && list2.size() >= 1) {
			this.save(mbmsg);
		}

	}

	*//**
	 * 短信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendOrhm(String orid, String Strinfo) {
		Mbmessage mbmsg = new Mbmessage();
		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";

		List list = this.find(sql);
		String ls_str = "";

		TOrder torder = new TOrder();
		if (list != null && list.size() >= 1) {
			torder = (TOrder) list.get(0);
			ls_str = Strinfo;
		}

		// System.out.println("短信发送内容：" + ls_str);
		mbmsg.setRevmbno(torder.getOrph());// 电话
		mbmsg.setNote(ls_str);
		mbmsg.setIsok(0);
		mbmsg.setDtime(Tools.getDayTimes());
		mbmsg.setQuantity(1);

		String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0001'";// 短信是否发送控制
		List list2 = this.find(syssql);
		if (list2 != null && list2.size() >= 1) {
			Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
			String ls_pmcd = sysparv5.getPmva();
			if (ls_pmcd.equals("1")) {
				boolean ib_true = WebSendMsg.jcsendinfo(mbmsg.getRevmbno(), mbmsg.getNote(), mbmsg.getSeq() + "");
				mbmsg.setIsok(ib_true ? 1 : 0);
			}
			this.save(mbmsg);
		}

	}

	*//**
	 * 短信发送，不用到系统中查询是订单，手机号由调用系统进行提供。
	 * 
	 * @param MobileNo
	 *            手机号
	 * @param Strinfo
	 *            发送内容
	 *//*
	public void SendInfo(String MobileNo, String Strinfo) {

		if (MobileNo == null || MobileNo.equals("")) {

			System.out.println("手机号为空");
			return;
		}
		if (Strinfo == null || Strinfo.equals("")) {

			System.out.println("发送内容为空");
			return;
		}

		SendInfo(MobileNo, Strinfo);

		Mbmessage mbmsg = new Mbmessage();
		// 发送短信的总的空制，
		String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0001'";

		String ls_str = Strinfo;

		mbmsg.setRevmbno(MobileNo);
		mbmsg.setNote(ls_str);
		mbmsg.setIsok(0);
		mbmsg.setDtime(Tools.getDayTimes());
		mbmsg.setQuantity(1);

		List list = this.find(syssql);
		if (list != null && list.size() >= 1) {
			Sysparv5 sysparv5 = (Sysparv5) list.get(0);
			String ls_pmcd = sysparv5.getPmva();
			if (ls_pmcd.equals("1")) {
				this.save(mbmsg);
			}
		}

	}

	*//**
	 * 注册发送短信
	 * 
	 * @param orid
	 *//*
	public void OrderSendReg(String mobileno, String Strinfo) {

		SendInfo(mobileno, Strinfo);
	}

	*//**
	 * 短信发送,酒店订单短信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendHotel(Orderlog orderLog) {

		if (orderLog == null)
			return;

		String orid = orderLog.getOrid();
		if (orid == null || orid.equals("")) {
			return;
		}

		String ls_strorderlog = "";

		Mbmessage mbmsg = new Mbmessage();

		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";

		List list = this.find(sql);
		String ls_str = "";

		File file = new File(WebContant.REALPATH + "/WEB-INF/mbhotel.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}

		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);

			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {

				if (ib_truefile) {
					content = content.replaceAll("<companyname/>", WebContant.CORPNAME);
					content = content.replaceAll("<orid/>", orid);
					content = content.replaceAll("<amnt/>", torder.getZfmont().toString());
					content = content.replaceAll("<ornm/>", torder.getOrnm());
					content = content.replaceAll("<orhm/>", torder.getOrhm());
					ls_str = content;

				} else {
					ls_str = WebContant.CORPNAME + "预订信息:订单号:" + orid + ",领票人:" + torder.getOrnm() + ",证件号:"
							+ torder.getOrhm();
				}
				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0003'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					this.save(mbmsg);
				}

			}

		}

	}

	*//**
	 * 短信发送,酒店订单短信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendMobile(Orderlog orderLog) {

		if (orderLog == null)
			return;

		String orid = orderLog.getOrid();
		if (orid == null || orid.equals("")) {
			return;
		}
		Mbmessage mbmsg = new Mbmessage();
		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";
		List list = this.find(sql);

		String ls_str = "";

		File file = new File(WebContant.REALPATH + "/WEB-INF/mbsk.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}

		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);

			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {
				if (ib_truefile) {
					content = content.replaceAll("<companyname/>", WebContant.CORPNAME);
					content = content.replaceAll("<orid/>", orid);
					content = content.replaceAll("<amnt/>", torder.getZfmont().toString());
					content = content.replaceAll("<ornm/>", torder.getOrnm());
					content = content.replaceAll("<orhm/>", torder.getOrhm());
					ls_str = content;

				} else {
					ls_str = WebContant.CORPNAME + "预订信息:订单号:" + orid + ",领票人:" + torder.getOrnm() + ",证件号:"
							+ torder.getOrhm();
				}
				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0003'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					this.save(mbmsg);
				}

			}

		}
	}

	*//**
	 * 知信发送,酒店订单短信发送
	 * 
	 * @param orid
	 *//*
	public void OrderSendLine(Orderlog orderLog) {

		if (orderLog == null)
			return;

		String orid = orderLog.getOrid();
		if (orid == null || orid.equals("")) {
			return;
		}

		String ls_strorderlog = "";

		Mbmessage mbmsg = new Mbmessage();
		String sql = "select t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";
		List list = this.find(sql);

		String ls_str = "";

		File file = new File(WebContant.REALPATH + "/WEB-INF/mbline.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}

		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);

			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {
				if (ib_truefile) {
					content = content.replaceAll("<companyname/>", WebContant.CORPNAME);
					content = content.replaceAll("<orid/>", orid);
					content = content.replaceAll("<amnt/>", torder.getZfmont().toString());
					content = content.replaceAll("<ornm/>", torder.getOrnm());
					content = content.replaceAll("<orhm/>", torder.getOrhm());
					ls_str = content;

				} else {
					ls_str = WebContant.CORPNAME + "预订信息:订单号:" + orid + ",领票人:" + torder.getOrnm() + ",证件号:"
							+ torder.getOrhm();
				}
				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0004'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					this.save(mbmsg);
				}

			}

		}

	}

	*//**
	 * 知信发送,mbstr 是内具体内容
	 * 
	 * @param orid
	 *//*
	public void OrderSend(String orid, String taobarcode) {

		Mbmessage mbmsg = new Mbmessage();
		String sql = "select t.orfl, t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";
		List list = this.find(sql);

		String ls_str = "";
		String orfl = "";
		File file = new File(WebContant.REALPATH + "/WEB-INF/mb.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}
		// 目前只发一条信息
		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);
			orfl = torder.getOrfl();

			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {
				String temstr = "金额";
				String temstr1 = "";
				if (orfl.equals("01")) {
					temstr = "房款";
					temstr1 = "";
				}
				if (orfl.equals("02")) {
					temstr = "门票款";
					temstr1 = "领票人证件号为" + torder.getOrhm() + "。";
				}
				if (orfl.equals("03")) {
					temstr = "线路款";
					temstr1 = "";
				}
				ls_str = "您的订单编号为" + orid + "," + temstr + torder.getZfmont() + "元已支付成功 " + temstr1;

				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				// pmcd='0002' 等于0002 表示支付后发送.
				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0002'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					String ls_pmcd = sysparv5.getPmva();
					if (ls_pmcd.equals("1")) {
						boolean ib_true = WebSendMsg.jcsendinfo(mbmsg.getRevmbno(), mbmsg.getNote(), mbmsg.getSeq()
								+ "");
						mbmsg.setIsok(ib_true ? 1 : 0);
					}
					this.save(mbmsg);
				}

			}

		}
	}

	*//**
	 * 短信发送,mbstr 是内具体内容
	 * 
	 * @param orid
	 *//*
	public void OrderSendC2C(String MobileNo, String orfl, String orid, String lr) {

		Mbmessage mbmsg = new Mbmessage();

		String Revmbno = MobileNo;
		String ls_str = lr;
		mbmsg.setRevmbno(Revmbno);
		mbmsg.setNote(ls_str);
		mbmsg.setIsok(0);
		mbmsg.setDtime(Tools.getDayTimes());
		mbmsg.setQuantity(1);

		String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0001'";// 短信是否发送控制
		List list2 = this.find(syssql);
		if (list2 != null && list2.size() >= 1) {
			Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
			String ls_pmcd = sysparv5.getPmva();
			if (ls_pmcd.equals("1")) {
				boolean ib_true = WebSendMsg.jcsendinfo(mbmsg.getRevmbno(), mbmsg.getNote(), mbmsg.getSeq() + "");
				mbmsg.setIsok(ib_true ? 1 : 0);
			}
			this.save(mbmsg);
		}
	}

	*//**
	 * 发送成功
	 * 
	 * @param seq
	 * @throws SQLException
	 * @throws javax.naming.NamingException
	 *//*

	public int UpdateSendOK(Long seq) {
		try {
			Mbmessage message = (Mbmessage) this.get(Mbmessage.class, seq);
			message.setIsok(1);
			message.setStime(Tools.getDayTimes());
			this.update(message);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 1;

	}

	*//**
	 * 批量更新已经发送状态
	 * 
	 * @param seq
	 * @throws SQLException
	 * @throws javax.naming.NamingException
	 *//*
	public void UpdateSendBathOK(String seq) {
		String hsql = " from Mbmessage where seq in ( " + seq + " ) ";
		List list = this.find(hsql);
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Mbmessage message = (Mbmessage) list.get(i);
				message.setIsok(1);
				message.setStime(Tools.getDayTimes());
				this.update(message);
			}
		}

	}

	*//**
	 * 发送失败
	 * 
	 * @param seq
	 * @throws SQLException
	 * @throws javax.naming.NamingException
	 *//*
	public int UpdateSendError(Long seq) {
		try {
			Mbmessage message = (Mbmessage) this.get(Mbmessage.class, seq);
			message.setIsok(0);
			message.setStime(Tools.getDayTimes());
			message.setQuantity(message.getQuantity() + 1);
			this.update(message);
		} catch (Exception e) {
			return -1;
		}
		return 1;

	}

	*//**
	 * 读取待发送的信息,保存在LIST中.
	 * 
	 * @param fromccda开始时间
	 *            (2011-11-11 10:13:13)
	 * @param toccda结事时间
	 * @param md5str
	 * @return
	 * @throws Exception
	 *//*
	public List<Mbmessage> getAllWaitSendInfo(String fromccda, String toccda, String md5str) {

		String hsql = " from Mbmessage where dtime >= '" + fromccda + "' and dtime <= '" + toccda
				+ "' and isok  = 0 and quantity < 4";
		List<Mbmessage> list = this.find(hsql);
		//删除过期短信
		//李进于 2014.9.2新加，读取一次表示发送一次；
		for ( int i = 0; i < list.size() ; i++ )
		{
			Mbmessage  msg  =  list.get(i);
			msg .setQuantity(  (msg .getQuantity()==null || msg .getQuantity()==0 ? 1 : msg .getQuantity())  + 1) ;
		    
			saveOrUpdate(msg);
		}
		//李进于 2014.9.2新加，读取一次表示发送一次,结束；
	    deleteGuoqiMessage(fromccda, toccda, md5str);
		
	    
	    return list;

	}

	*//**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 *//*
	public static long getMillis(java.util.Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	*//**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 *//*
	public static java.util.Date addDate(java.util.Date date, int day) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}
	
	*//**
	 * 删除过期短信，
	 * @param fromccda  开始时间 提前30天，
	 * @param toccda    暂时没有用。
	 * @param md5str
	 *//*

	public void deleteGuoqiMessage(String fromccda, String toccda, String md5str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date day1 = sdf.parse(fromccda);
			Date day2 = addDate(day1, -30);
			java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String result = df.format(day2);
			System.out.println(result);
			String hsql = " from Mbmessage where dtime <= '" + fromccda + "'";

			List<Mbmessage> list = this.find(hsql);
			for (int i = 0; i < list.size(); i++) {
				this.delete((Mbmessage) list.get(i));
			}
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	*//**
	 * 短信发送,mbstr 是内具体内容
	 * 
	 * @param orid
	 *//*
	public void OrderZfOkSendTwoCode(String orid, String taobarcode) {

		Mbmessage mbmsg = new Mbmessage();

		String sql = "select t.orfl, t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";
		List list = this.find(sql);

		String ls_str = "";
		String orfl = "";
		File file = new File(WebContant.REALPATH + "/WEB-INF/mb.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}
		// 目前只发一条信息

		String listsql = " from TOrderlist where id.orid='" + orid + "'";
		List orlist = this.find(listsql);
		String liststr = "";
		if (orlist != null && orlist.size() >= 1) {
			for (int i = 0; i < orlist.size(); i++) {
				TOrderlist torderlist = (TOrderlist) orlist.get(0);
				liststr = orid + torderlist.getItickettypeid() + ";";
			}
		}

		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);
			orfl = torder.getOrfl();

			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {
				String temstr = "金额";
				String temstr1 = "";
				if (orfl.equals("01")) {
					temstr = "房款";
					temstr1 = "";
				}
				if (orfl.equals("02")) {
					temstr = "门票款";
					temstr1 = "证件号:" + torder.getOrhm() + ",";
				}
				if (orfl.equals("03")) {
					temstr = "线路款";
					temstr1 = "";
				}

				ls_str = orid + "," + temstr + torder.getZfmont() + "元已支付成功 " + temstr1 + "。" + liststr;

				mbmsg.setRevmbno(torder.getOrph());
				mbmsg.setNote(ls_str);
				mbmsg.setIsok(0);
				mbmsg.setDtime(Tools.getDayTimes());
				mbmsg.setQuantity(1);

				// pmcd='0002' 等于0002 表示支付后发送.
				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0002'";// 短信是否发送控制
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					Sysparv5 sysparv5 = (Sysparv5) list2.get(0);
					String ls_pmcd = sysparv5.getPmva();
					if (ls_pmcd.equals("1")) {
						boolean ib_true = WebSendMsg.sendinfomms(mbmsg.getRevmbno(), temstr, orid);
						mbmsg.setIsok(ib_true ? 1 : 0);
					}
					this.save(mbmsg);
				}

			}

		}
	}

	*//**
	 * 知信发送,mbstr 是内具体内容
	 * 
	 * @param orid
	 *//*
	public void OrderZfOkSendTwoCode(String orid) {

		Mbmessage mbmsg = new Mbmessage();

		String qrsrcstr = "";
		String usid = "";

		String sql = "select t.usid,t.orfl, t.ornm,t.orhm,t.orph,t.faxno,t.zfmont from TOrder t,MOrder m where t.id.orid = m.orid and t.id.orid='"
				+ orid + "' ";
		List list = this.find(sql);

		String pzid = "";
		String ls_str = "";
		String orfl = "";
		File file = new File(WebContant.REALPATH + "/WEB-INF/mb.txt");
		String content = "";
		boolean ib_truefile = false;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				String str;
				while ((str = in.readLine()) != null) {
					content = content + str;
				}

				ib_truefile = true;

				in.close();
			} catch (Exception dd) {

				ib_truefile = false;
			}
		} else {
			ib_truefile = false;
		}
		// 目前只发一条信息
		// 票号组织在一起

		if (list != null && list.size() >= 1) {
			TOrder torder = (TOrder) list.get(0);

			usid = torder.getUsid();
			String ls_orhm = torder.getOrhm();
			String mbno = torder.getOrph();// 电话

			if ((torder.getOrhm() != null) && (torder.getOrnm() != null) && (torder.getOrph() != null)) {
				String temstr = "金额";
				String temstr1 = "";
				if (orfl.equals("01")) {
					temstr = "房款";
					temstr1 = "";
				}
				if (orfl.equals("02")) {
					temstr = "门票款";
					temstr1 = "证件号:" + torder.getOrhm() + ",";
				}
				if (orfl.equals("03")) {
					temstr = "线路款";
					temstr1 = "";
				}

				ls_str = "尊敬的游客，欢迎光临" + WebContant.DOMAINAME + "。您的订单编号" + orid + "," + temstr + torder.getZfmont()
						+ temstr1 + "。";

				// 发送二维码的内容　orid + "," +
				String srcstr = pzid;

				// pmcd='0002' 等于0002 表示支付后发送.
				String syssql = " from Sysparv5 where id.pmky='SEND' and id.pmcd='0002'";// 短信是否发送控制
				Custom custom = (Custom) this.get(Custom.class, usid);
				String lgtp = "";
				if (custom != null) {
					lgtp = custom.getLgtp();
				} else {
					lgtp = "02";
				}
				List list2 = this.find(syssql);
				if (list2 != null && list2.size() >= 1) {
					if (lgtp.equals("01")) {
						// OrderPzSendTwoCode(mbno, orid, ls_str);
					} else {
						mbmsg.setRevmbno(mbno);
						mbmsg.setNote(ls_str);
						mbmsg.setIsok(0);
						mbmsg.setDtime(Tools.getDayTimes());
						mbmsg.setQuantity(1);
						this.save(mbmsg);
					}
				}

			}

		}
	}

	public void OrderPzSendTwoCode(String revmbno, String orid, String ls_note) {

		// Mbmessage mbmsg = new Mbmessage();
		// Statement stmt = conn.createStatement();
		// String ticketno = "select orid, pzid from x_orderticket where  orid='" + orid + "'";
		// ResultSet pzidrs = stmt.executeQuery(ticketno);
		// String pzid = "";
		// boolean ib_true = true ;
		// while (pzidrs.next()) {
		// mbmsg.setRevmbno(revmbno);
		// mbmsg.setIsok(0);
		// pzid = pzidrs.getString("pzid");
		// mbmsg.setNote(ls_note+" 本票票号："+pzid);
		// insert(cmbmsg);
		// ib_true = false;
		//			
		// }
		// if (ib_true)
		// { //当没有检票数据时，发一条短信即可。
		// mbmsg.setRevmbno(revmbno);
		// mbmsg.setIsok(0);
		// mbmsg.setNote(ls_note);
		// mbmsg.setTaobarcode("");
		// insert(conn, mbmsg);
		// }
	}

	public int UpdateMbMeaage(Long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
*/