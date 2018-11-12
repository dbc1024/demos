/*package com.ectrip.ticket.service.message;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Encrypt;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.ticket.service.dao.MbMsGDAO;

*//**
 * 手机信息发送 所有数据来源都是 MBMEAASGE 表中, 将发送的业务数据全部放在这个表中. 如果有新的接口,
 * 只要修改 com.ectrip.service.message.WebSendMsg 对应的方法,即可. 在
 * ectrip.xml 中要配置相关参数: MBACCOUNT 帐号 ; MBPASSWORD 密码 MBHOST 发送机址,
 * 这个地址有可能不同的网关不同的类容,千万小心.;
 *
 * @author LIJIN
 *
 *//*
public class SendDao extends GenericDao {
	*//**
	 * 短信发送处理程序
	 *
	 * @throws Exception
	 *//*
	public int DbSendInfoOld() throws Exception {

		String body = "";
		String mobile = "";
		System.out.println("短信发送,RUN");
		Encrypt en = new Encrypt();

		String fromccda = Tools.getTodayString() + " 00:00:00";
		String toccda = Tools.getTodayString() + " 23:59:59";

		// 最多重试三次
		String account = WebContant.GetKeyValue("MBACCOUNT");
		String password = WebContant.GetKeyValue("MBPASSWORD");
		// String body = "测试信息,收到请在QQ中回复";
		
		 * <MBACCOUNT>101100-SJB-HUAX-627184</MBACCOUNT> <MBPASSWORD>GVBYIYBC</MBPASSWORD>
		 * <MBHOST>www.stongnet.com</MBHOST> <MBPORT>8080</MBPORT>
		 
		// String mobile = "15074429994";
		String host = WebContant.GetKeyValue("MBHOST");
		String strport = WebContant.GetKeyValue("MBPORT");

		String sql = " from Mbmessage where dtime >= '" + fromccda + "' and dtime <= '" + toccda
				+ "' and isok  = 0 and quantity < 4";

		List waitsedninfo = new ArrayList();

		waitsedninfo = this.find(sql);
		if(waitsedninfo!=null && waitsedninfo.size()>=1){

			for (int i = 0; i < waitsedninfo.size(); i++) {
				Mbmessage message = (Mbmessage) waitsedninfo
						.get(i);
				if (message.getIsok() == 0) {
					body = message.getNote();//发送内容
					mobile = message.getRevmbno();//电话号码
					int rc_id = WebSendMsg.WebSend(mobile, body);
					MbMsGDAO mb = new MbMsGDAO();
					if (rc_id > 0) {
						mb.UpdateSendOK(message.getSeq());//发送成功
					} else {
						mb.UpdateSendError(message.getSeq());//发送失败
					}
				}
			}
		}
		System.out.println("短信发送正常：mobile=" + mobile + ",发送内容为：" + body);

		return 1;
	}

	*//**
	 * 短信发送处理程序,九网现在使用 发送成功,为 1, 否则小于 0 表示不成功.
	 *
	 * @throws Exception
	 *//*
	public int DbSendInfo() throws Exception {

		String body = "";
		String mobile = "";
		System.out.println("短信发送,RUN");
		boolean rc_id = false;
		String fromccda = Tools.getTodayString() + " 00:00:00";
		String toccda = Tools.getTodayString() + " 23:59:59";
		// 最多重试3次
		String sql = "  from Mbmessage where dtime >= '" + fromccda + "' and dtime <= '" + toccda
				+ "' and isok  = 0 AND quantity < 4";

		List waitsedninfo = new ArrayList();

		waitsedninfo = this.find(sql);
		if(waitsedninfo!=null && waitsedninfo.size()>=1){

			for (int i = 0; i < waitsedninfo.size(); i++) {
				Mbmessage message = (Mbmessage) waitsedninfo
						.get(i);
				if (message.getIsok() == 0) {
					//发送肉容
					body   = message.getNote();
					//手机号,没有进行验证,在数据保存是要进行验证
					mobile = message.getRevmbno();
					//整个系统中唯一编号
					String seq = String.valueOf(message.getSeq());
					// 进行发送
					rc_id = WebSendMsg.jzgsendinfo(seq, mobile, body);
					// 将发送结果写回到表里
					MbMsGDAO mb = new MbMsGDAO();
					if (rc_id) {
						mb.UpdateSendOK(message.getSeq());
					} else {
						mb.UpdateSendError(message.getSeq());
					}
				}
			}
		}
		System.out.println("短信发送正常：mobile=" + mobile + ",发送内容为：" + body);
		return (rc_id == true ? 1 : -1);
	}

}
*/