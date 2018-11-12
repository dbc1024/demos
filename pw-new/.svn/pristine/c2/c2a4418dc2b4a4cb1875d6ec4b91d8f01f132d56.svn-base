package com.ectrip.base.util;

import java.util.TimerTask;

import javax.servlet.ServletContext;
/**
 * 天气预报线程,服务启动时加载.
 * @author lijin
 *
 */
public class MyTask extends TimerTask  implements Runnable  {

	private ServletContext context = null;

	
	public MyTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		try {
			System.out.println(Tools.getDayTimes() + ":天气预报开始执行定时器任务");
			Weather weather = new Weather();
			weather.getWeather(context.getRealPath(""));
			System.out.println(Tools.getDayTimes() + ":预报执行完毕定时器任务");
		} catch (Exception e) {
			System.out.println("执行定时器出错:" + e.toString());
		}

	}
}
