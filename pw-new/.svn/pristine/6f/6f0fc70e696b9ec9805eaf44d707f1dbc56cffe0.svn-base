package com.ectrip.ticket.service.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import com.ectrip.base.util.Debug;

public class WebSendMms {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	/**
	 * 发送彩信
	 *
	 * @param mobileno
	 * @param lr
	 * @return
	 */
	public static int sendMms(String mobileno, String lr) throws Exception {
		// 上海迅赛彩信网关
		if (mobileno == null || lr == null)
			throw new Exception("手机号为空");
		if (mobileno.equals("") || lr.equals(""))
			throw new Exception("手机号为空");
		if (mobileno.length() < 11) {
			throw new Exception("手机位数不对");
		}

		String providermms = "http://mms.xunsai.net:8002/?user=m1012281079&password=13923834675&phonenumber="
				+ mobileno + lr;
		String host = providermms;
		Debug.print("彩信:"+providermms);
		java.net.URL endpointURL = new java.net.URL(host);
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(endpointURL.openStream()));
			String str;
			while ((str = in.readLine()) != null)
				System.out.println(str);
			in.close();
		} catch (MalformedURLException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		return 1;
	}
}
