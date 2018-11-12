package com.ectrip.ticket.service.dao.idao;
import java.sql.SQLException;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.sys.model.syspar.Orderlog;

public interface IMbMsGDAO extends IGenericDao {

	/**
	 * 检查字符串长度
	 *
	 * @param strParameter
	 * @param limitLength
	 * @return
	 */

	public boolean validateStrByLength(String strParameter, int limitLength);
	/**
	 * 用于旧方法,一个参数,新方法有两个参数
	 *
	 * @param orid
	 */
	public void OrderSend(String orid) ;

	/**
	 * 短信发送
	 *
	 * @param orid
	 */
	public void OrderSendSk(String orid);
	/**
	 * 短信发送
	 *
	 * @param orid
	 */
	public void OrderSendReturn(String orid);

	/**
	 * 知信发送
	 *
	 * @param orid
	 */
	public void OrderSendInfo(String orid, String Strinfo) ;

	/**
	 * 短信发送
	 *
	 * @param orid
	 */
	public void OrderSendOrhm(String orid, String Strinfo) ;

	/**
	 * 短信发送，不用到系统中查询是订单，手机号由调用系统进行提供。
	 *
	 * @param MobileNo
	 *            手机号
	 * @param Strinfo
	 *            发送内容
	 */
	public void SendInfo(String MobileNo, String Strinfo);


	/**
	 * 注册发送短信
	 *
	 * @param orid
	 */
	public void OrderSendReg(String mobileno, String Strinfo);

	/**
	 * 短信发送,酒店订单短信发送
	 *
	 * @param orid
	 */
	public void OrderSendHotel(Orderlog orderLog);

	/**
	 * 短信发送,酒店订单短信发送
	 *
	 * @param orid
	 */
	public void OrderSendMobile(Orderlog orderLog) ;

	/**
	 * 知信发送,酒店订单短信发送
	 *
	 * @param orid
	 */
	public void OrderSendLine(Orderlog orderLog);

	/**
	 * 知信发送,mbstr 是内具体内容
	 *
	 * @param orid
	 */
	public void OrderSend(String orid, String taobarcode);
	/**
	 * 短信发送,mbstr 是内具体内容
	 *
	 * @param orid
	 */
	public void OrderSendC2C(String MobileNo, String orfl, String orid, String lr);

	/**
	 * 发送成功
	 *
	 * @param seq
	 * @throws SQLException
	 * @throws javax.naming.NamingException
	 */

	public int UpdateSendOK(Long seq) ;

	/**
	 * 批量更新已经发送状态
	 *
	 * @param seq
	 * @throws SQLException
	 * @throws javax.naming.NamingException
	 */
	public void UpdateSendBathOK(String seq);
	/**
	 * 发送失败
	 *
	 * @param seq
	 * @throws SQLException
	 * @throws javax.naming.NamingException
	 */
	public int UpdateSendError(Long seq) ;

	/**
	 * 读取待发送的信息,保存在LIST中.
	 *
	 * @param fromccda开始时间(2011-11-11 10:13:13)
	 * @param toccda结事时间
	 * @param md5str
	 * @return
	 * @throws Exception
	 */
	public List<Mbmessage> getAllWaitSendInfo(String fromccda, String toccda, String md5str);

	/**
	 * 短信发送,mbstr 是内具体内容
	 *
	 * @param orid
	 */
	public void OrderZfOkSendTwoCode(String orid, String taobarcode) ;
	/**
	 * 知信发送,mbstr 是内具体内容
	 *
	 * @param orid
	 */
	public void OrderZfOkSendTwoCode(String orid) ;

	public void OrderPzSendTwoCode(String revmbno, String orid, String ls_note) ;

	//读取没有发送成功的信息
	public int UpdateMbMeaage( Long seq);

}

