package com.ectrip.ec.job.ijob;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.Tasklog;

public abstract class TaskLogService implements ReportEngine{
	public void insertReportLog(Tasklog log) {
		try {
			System.out.println("---������־");
			IGenericService genericService=(IGenericService) SpringUtil.getBean("genericService");
			System.out.println("===>>genericService:"+genericService);
			log.setLogno(genericService.getMaxPk("logno", "Tasklog")+1);
			log.setLogtime(Tools.getDayTimes());
			genericService.save(log);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(log.getTaskname()+"��־��¼ʧ��");
		}
	}
}
