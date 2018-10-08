package com.hqyt;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service("testService1")
public class TestServiceImplPuls implements TestService {

	public String getServerTime() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(cal.getTime());
        return date;
	}

}
