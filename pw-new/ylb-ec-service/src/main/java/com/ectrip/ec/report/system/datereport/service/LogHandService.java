package com.ectrip.ec.report.system.datereport.service;

import java.util.List;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.report.system.datereport.dao.LogDataDao;
import com.ectrip.ec.report.system.datereport.service.iservice.ILogHandService;
import com.ectrip.sys.model.syspar.Hcustomlog;
import com.ectrip.sys.model.syspar.Hicopertionlog;
import com.ectrip.sys.model.syspar.Horderlog;
import com.ectrip.sys.model.syspar.Hsyslog;

public class LogHandService implements ILogHandService {
	LogDataDao logDataDao;
	
	public LogDataDao getLogDataDao() {
		return logDataDao;
	}

	public void setLogDataDao(LogDataDao logDataDao) {
		this.logDataDao = logDataDao;
	}
	
	public int jobrun(String dates) {
		System.out.println("-------��־�ֶ����п�ʼ:" + Tools.getDayTimes());
		try {
			//��̨��־��Syslog��
			String date = Tools.getDate(dates, -1);
			// 1   ��̨��־ 	2 ǰ̨��־	3������־
			List sysList = logDataDao.updateOrQueryLog(date,"1");
			if(sysList !=null && sysList.size()>=1){
				for(int i = 0;i < sysList.size();i++){
					Object[] sys = (Object[]) sysList.get(i);
					
					Hsyslog hsyslog = new Hsyslog();
					Long seq = this.logDataDao.getSequenceId("hsyslog_sequence");
					hsyslog.setLogid(seq);
					hsyslog.setIemployeeid(Long.parseLong(sys[0].toString()));
					hsyslog.setStlg(sys[1].toString());
					hsyslog.setBrief(sys[2].toString());
					hsyslog.setNote(sys[3].toString());
					hsyslog.setLogdatetime(sys[4].toString());
					hsyslog.setDtmakedate(Tools.getDayTimes());
					this.logDataDao.save(hsyslog);
				}
			}
			//ǰ̨��־
			List customList = logDataDao.updateOrQueryLog(date,"2");
			if(customList !=null&&customList.size()>=1){
				for(int i = 0;i<customList.size();i++){
					Object[] cu = (Object[]) customList.get(i);
					Hcustomlog hcustomlog = new Hcustomlog();
					Long seq = this.logDataDao.getSequenceId("hcustomlog_sequence");
					hcustomlog.setSysid(seq);
					if(cu[0]==null){
						hcustomlog.setUsid(null);
					}else{
						hcustomlog.setUsid(cu[0].toString());
					}
					hcustomlog.setStlg(cu[1].toString());
					hcustomlog.setLogdatetime(cu[2].toString());
					hcustomlog.setBrief(cu[3].toString());
					hcustomlog.setNote(cu[4].toString());
					hcustomlog.setDtmakedate(Tools.getDayTimes());
					this.logDataDao.save(hcustomlog);
				}
			}
			//������־
			String date2 = Tools.getDate(dates, -30);
			List orderList = this.logDataDao.updateOrQueryLog(date2, "3");
			if(orderList !=null&&orderList.size()>=1){
				for(int i = 0;i<orderList.size();i++){
					Object[] order = (Object[]) orderList.get(i);
					Horderlog horderlog  =new Horderlog();
					Long seq = this.logDataDao.getSequenceId("horderlog_sequence");
					horderlog.setLogid(seq);
					horderlog.setOrid(order[0].toString());
					horderlog.setStlg(order[1].toString());
					horderlog.setBrief(order[2].toString());
					horderlog.setLogtype(Long.parseLong(order[3].toString()));
					if(order[4]==null){
						horderlog.setUsid(null);
					}else{
						horderlog.setUsid(order[4].toString());
					}
					if(order[5]==null){
						horderlog.setIemployeeid(null);
					}else{
						horderlog.setIemployeeid(Long.parseLong(order[5].toString()));
					}
					horderlog.setLogdatetime(order[6].toString());
					horderlog.setNote(order[7].toString());
					horderlog.setDtmakedate(Tools.getDayTimes());
					this.logDataDao.save(horderlog);
				}
			}
			//IC��������־
			List icList = this.logDataDao.updateOrQueryLog(date, "4");
			if(icList !=null && icList.size()>=1){
				for(int i = 0;i<icList.size();i++){
					Object[] ic = (Object[]) icList.get(i);
					Hicopertionlog hic = new Hicopertionlog();
					Long seq = this.logDataDao.getSequenceId("hicopertionlog_sequence");
					hic.setSeq(seq);
					hic.setSzticketprintno(ic[0].toString());
					hic.setOptype(Long.parseLong(ic[1].toString()));
					hic.setEquipmentid(Long.parseLong(ic[2].toString()));
					hic.setIcblock(ic[3].toString());
					hic.setIccontent(ic[4].toString());
					hic.setDtdatemake(ic[5].toString());
					this.logDataDao.save(hic);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("��־�����쳣");
		}
		System.out.println("��־�ֶ�����ִ��" + Tools.getDayTimes());
		return 1;
	}

}

