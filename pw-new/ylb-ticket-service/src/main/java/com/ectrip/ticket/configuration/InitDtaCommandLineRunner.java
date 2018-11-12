package com.ectrip.ticket.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ectrip.base.util.WebContant;
import com.ectrip.hqyt.client.HqytClient;
/**
 * 描述系统启动后执行的方法有两种：1.实现CommandLineRunner接口 
 * 2.实现ApplicationRunner接口 以@Order设置启动顺序
* @ClassName: MyCommandLineRunner  
* @Description: TODO 项目启动后执行run方法 
* @author jiyong  
* @date 2018年5月12日  
*
 */
@Component
public class InitDtaCommandLineRunner implements CommandLineRunner{

	@Autowired
	private EctripInitDataConfig ectripInitDataConfig;
	@Autowired
	private HqytInitDataConfig hqytInitDataConfig;
	@Override
	public void run(String... args) throws Exception {
		//初始化WebContant keyMap值
		WebContant.KeyMap = ectripInitDataConfig.getSystemMap();
		//初始化HqytClient可变参数
		HqytClient.url = hqytInitDataConfig.getURL();
		HqytClient.key = hqytInitDataConfig.getKEY();
		HqytClient.appPayUrl = hqytInitDataConfig.getAPP_DOWNLOAD_URL();
	}
}
