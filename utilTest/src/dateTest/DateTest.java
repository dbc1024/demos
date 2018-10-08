package dateTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class DateTest {

	@Test
	public void getNow() {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = df.format(new Date());
		
		System.out.println(now);
	}
	
	
	@Test
	public void getDateAfterOneDay() {
		
		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。    
		cal.add(Calendar.YEAR, 1);//年加减   
		cal.add(Calendar.MONTH, -1);//月加减
		cal.add(Calendar.DAY_OF_MONTH, 1);//日加减
		
		Date time = cal.getTime();
		System.out.println(time);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(df.format(time));
	}
	
	
	@Test
	public void isRightDate() {
		String dateString = "2017-2-28";
		
		boolean convertSuccess=true;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		try {
			format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			convertSuccess = false;
		}
		
		System.out.println(convertSuccess);
	}
	
	
	@Test
	public void shunxuDate() {
		String dateString1 = "2017-2-27";
		String dateString2 = "2017-2-28";
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			Date date1 = format.parse(dateString1);
			Date date2 = format.parse(dateString2);
			
			boolean before = date1.before(date2);
			System.out.println(before);
			
			boolean after = date1.after(date2);
			System.out.println(after);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//HH:mm

	
	@Test
	public void isRightTime() {
		String dateString = "8:00";
		
		boolean convertSuccess=true;
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setLenient(true);
		try {
			format.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			convertSuccess = false;
		}
		
		System.out.println(convertSuccess);
		
	}
	
	
	
	
	
	
	@Test
	public void ticketData() {
		Map<Object, Object> data = new HashMap();
		
		//1.获取景区营业时间
		//2.根据系统当前时间以半小时为单位计算需要进行统计的时段
		//3.根据票种组装各时间段的数据
		
		
		
		//1.获取景区营业时间
		String businesshours = "8:00-18:00";	//模拟数据
		String[] split = businesshours.split("-");
		String string_startTime = split[0];
		String string_endTime = split[1];
		
		
		
		SimpleDateFormat format;
		
		//判断时间格式是否正确
		format= new SimpleDateFormat("HH:mm");
		format.setLenient(false);
		try {
			format.parse(string_startTime);
			format.parse(string_startTime);
		} catch (ParseException e) {
			
			data.put("error", "景区营业时间格式有误，应如8:00-18:00");
		}
		
		
		
		format = new SimpleDateFormat("yyyy-MM-dd");    
		String date_today = format.format(new java.util.Date()); 
		
		String string_full_tempStartTime = date_today + " " + string_startTime +":00";
		
		String string_full_tempAddTime = getHalfHourLater(string_full_tempStartTime);
	        
		
		//如果加出来的结束时间小于当前时间，就继续查询
		while(isBeforeNow(string_full_tempAddTime)){
			
			//a.查出这个时段的数据
			//saleService.getSaleData(string_full_tempStartTime,string_full_tempAddTime);
			
			//b.取出数据进行封装
			
			//c.累加时间段
			string_full_tempStartTime = string_full_tempAddTime;
			string_full_tempAddTime = getHalfHourLater(string_full_tempStartTime);
		}
	}
	
	
	public boolean isBeforeNow(String string_full_tempAddTime){
		
		boolean before = true;
		
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		
		try {
			Date addTime = format.parse(string_full_tempAddTime);
			before = addTime.before(new Date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return before;
	}
	
	
	
	public String getHalfHourLater(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date_full_nowTime;
		Calendar c = Calendar.getInstance();;
		try {
			date_full_nowTime = format.parse(time);
	        c.setTime(date_full_nowTime);
	        c.add(Calendar.MINUTE, 30);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        String addTime = format.format(c.getTime());
        
        return addTime;
	}
	
	@Test
	public void compareTwoDate() throws ParseException {
		
//		String startTime = "2012-12-12 12:45:45"; 
//		String endTime = "2012-04-12 12:45:40"; 
//		String SYSendTime = "2012-11-12 12:45:40";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//		Date startDate = sdf.parse(startTime); 
//		Date endDate = sdf.parse(endTime); 
//		
//		
//		Calendar start = Calendar.getInstance(); 
//		Calendar end = Calendar.getInstance(); 
//		start.setTime(startDate); 
//		end.setTime(endDate); 
//		
//		
//		if(start.before(end)){ 
//			System.err.println("开始时间小于结束时间"); 
//		}else if(start.after(end)){ 
//			System.err.println("开始时间大于结束时间"); 
//		}else if(start.equals(end)){ 
//			System.err.println("开始时间等于结束时间"); 
//		}
		
		Calendar openDate = Calendar.getInstance(); 
		openDate.add(Calendar.DAY_OF_YEAR, 90);
		Calendar today = Calendar.getInstance();
		if(openDate.before(today)) {
			System.out.println("1111111");
		}else {
			System.out.println("2222222");
		}
		
	}
	
	
}
