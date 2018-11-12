package com.ectrip.ec.job.ijob;

import com.ectrip.ec.model.report.Tasklog;

/**
 * 
 * @ClassName: ReportEngine
 * @Description: ��������ӿ�
 * @author Dicky
 * @date 2011-11-30 ����08:48:16
 * 
 */
public interface ReportEngine {
	public int jobrun();
	
	public void insertReportLog(Tasklog log);
	
}
