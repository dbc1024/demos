package com.ectrip.ticket.service;

/**
 * 九网新接口
 */
public class JwOtoService {
	   //这是MD5加密用的KEY，交给调用的开发商
	
    private String MD5KEY =  "34344345443231356565" ;
	public String getMessInfo(String method, String params, String md5str) throws Exception {
		 /**
		  * 转换方法
		  */
		 return this.getMessInfo(Integer.valueOf(method).intValue(), params, md5str);
	}
	
	
	private String getMessInfo(int method, String params, String md5str) throws Exception {

		System.out.println("调用试开始=============>>>> ");
		System.out.println("method = " + method);
        System.out.println("params" + params);
		System.out.println("md5str传入  = " + md5str);
	

		EctripMd5 md5 = new EctripMd5(params,MD5KEY);
		md5.calc();
		String ls_temp = md5.toString();
		
		System.out.println("md5str服务器端运算为： = " + ls_temp);
		System.out.println("md5str.equals(md5.toString())  " + md5str.equals(md5.toString())  );
		
		if (! md5str.equals(md5.toString())   )
		{
			
			return "<ectrip><error>加密方式不对</error></ectrip>";	
			
		}
		

		// 结临时变量赋值
		int parmas =0; 
		
		String orid  = "";
		switch (method) {
	     case 0: // 读取景区列表
	    	  
	       	   

		case 1: // 景区景点图片路径集合、文字描述 获取景点景区信息
			parmas = 2;
			return "";

		case 2: // 用户名，密码，是否登陆成功 用户登陆
			parmas = 2;
			return "";

		case 3: // 用户的所有信息 获取用户的所有信息
			parmas = 2;
			return "";

		case 4: // 注册人，注册类别，手机号码，身份证号，所属公司，上次登陆时间，注册时间 获取我的帐户信息
			parmas = 2;
			
			return "";
		
		//	break;
		case 5: // 是否修改成功 修改帐户密码
			parmas = 3;
			//2013-11-15 修改密码成功。
			
		
		case 6: // 订单列表 根据用户名、密码获取我的订单列表
		
			
			return "";

		default:
			System.out.println("you are the last!");

		}
		
		return "<ectrip><error>未知错误</error></ectrip>";
		//return mobileDao.getMessInfo(method, params, md5str);
		
	}
	
	public static void main(String[] args) {
		
		JwOtoService  jos = new JwOtoService();
		
		EctripMd5 md5 = new EctripMd5("<prno>00001</prno>",jos.MD5KEY);
		md5.calc();
		System.out.println("md5.calc  " + md5.toString());
		
		try {
			String ls_str = jos.getMessInfo("0001", "<prno>00001</prno>", "32e9ec2e27913c34e3f88ac52e2af944");
			System.out.println("ls_str " +ls_str);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
