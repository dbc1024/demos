package com.ectrip.ticket.checkticket.service.iservice;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.ResultBean;

public interface ICheckonetableService extends IGenericService {
	public ResultBean querychecktypeoneTable(String accid,String printno) throws Exception;
	/**
	 * 总体对外检票方法
	 *
	 * @param ticketstr
	 *            检票用的票号
	 * @param md5str
	 *            准确性校验方法
	 * @return 李进于 2012-07-28 进行了重大修改，增加了 第个参数
	 */

	public ResultBean changeCheckTicketonetable(String accid, String printno, String... OtherString) throws Exception;
	public List queryprintnolistonetable(String accid, String printno)
			throws Exception;
	public boolean isyw(String printno);
	public int changeticketPassIdintput(String accid, String ticketno,
										String md5str) throws Exception;
	public int changeticketZwintput(String accid, String ticketno,
									String ziwenno, String szidcard, String szimagepath, String md5str);
	public int stopCheckTicket(Long accid,String szprintno,Long synumb);
}
