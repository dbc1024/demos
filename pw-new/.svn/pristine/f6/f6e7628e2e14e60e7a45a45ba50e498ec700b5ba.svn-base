package com.ectrip.oauth.system;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ectrip.oauth.utils.Encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyPasswordEncoder implements PasswordEncoder{

	private static Logger logger = LoggerFactory.getLogger(MyPasswordEncoder.class);
	@Override
	public String encode(CharSequence rawPassword) {
		Encrypt encrypt = new Encrypt();
		return encrypt.passwordEncrypt(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			logger.warn("Empty encoded password");
			return false;
		}
		Encrypt encrypt = new Encrypt();
		if(encrypt.passwordEncrypt(rawPassword.toString()).equals(encodedPassword)) {
			logger.warn("密码匹配成功");
			return true;
		}else {
			logger.warn("密码不匹配");
			return false;
		}
		
	}

}
