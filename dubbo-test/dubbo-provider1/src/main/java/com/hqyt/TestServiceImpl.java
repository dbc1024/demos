package com.hqyt;

import org.springframework.stereotype.Service;

@Service("testService")
public class TestServiceImpl implements TestService {

	public String getServerTime() {
		long currentTimeMillis = System.currentTimeMillis();
		return Long.toString(currentTimeMillis);
	}

}
