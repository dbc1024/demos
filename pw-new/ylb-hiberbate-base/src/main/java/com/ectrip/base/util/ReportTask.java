package com.ectrip.base.util;

import java.util.TimerTask;

/**
 * 报表引擎运算类，项目启动 5分钟开始运行。
 * 
 * @author lijin
 * 
 */
public class ReportTask extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("开始查询定时任务 指定包");
		ClassUtil.runMain();  //引擎运行
		System.out.println("开始初始化指定包内接口类");
	}

}
