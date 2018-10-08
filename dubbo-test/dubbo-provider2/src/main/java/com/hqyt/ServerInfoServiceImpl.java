package com.hqyt;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service("serverInfoService")
public class ServerInfoServiceImpl implements ServerInfoService {

	public String getServerDate() {
		Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(cal.getTime());
        return date;
	}
	

}
