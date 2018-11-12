package com.ectrip.sys.backuplogin.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.Encrypt;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.lic.EctripLic;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.configuration.EctripInitDataConfig;
import com.ectrip.sys.employee.service.IEsfEmployeeTabService;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Orderlog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 后台员工登录 2011-09-16 2012-10-17 修改，增加了LIC的检测，
 * 过期后软件将不能使用。 2013-01-23
 * 修改，增加isExpritdate 方法，检查是否过期；
 * 李进于  2014-07-11 年，修改了 221 和 271行，增加了当日登录次数
 * 记数不准确的问题，当输入错误两次，再输入对的一次，
 * 登录次数没有复原的问题。
 * 
 * 新修改：2018-02-24 
 * @author LiJin
 * 
 */
@RestController
@RequestMapping
@Api(tags = "系统模块相关接口-登陆相关接口")
public class AdminLoginController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(AdminLoginController.class);
	// 员工类
	@Autowired
	private IEsfEmployeeTabService employeeService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static String expritdate;
	
	/**
	 * 后台员工登录
	 * 登录验证并返回token 修改时间：2018-02-24 
	 * @return
	 */
	@PostMapping(value="v1/login")
	@ApiOperation(value = "登录接口，并返回用户信息")
	public ResponseBean adminaction(@RequestParam(name="empId",required=true) String empId,@RequestParam(name = "empPwd",required=true) String empPwd) {
		Esfemployeetab employee = null;
		LOGGER.info("测试配置服务器="+WebContant.GetKeyValue("CenterUrl"));
		/*try {
			// LIC 文件 路径
			String licfile = com.ectrip.base.util.WebContant.REALPATH + "WEB-INF/classes/ectripkey.lic";
			// 判断文件是否存在
			File file = new File(licfile);
			LOGGER.info("lic文件路径:" + licfile);
			if (file.exists()) {
				// 有LIC文件
				EctripLic ectripLic = new EctripLic();
				// 读取LIC文件
				ectripLic.ReadEctripKey(licfile);
				// 读取LIC文件中的联责；无用
				String emptuy = ectripLic.getEmputy();
				// 公司名称，从LIC文件中读出公司名称
				WebContant.CORPNAME = ectripLic.getCustomerName();
				LOGGER.info("职责编号：" + ectripLic.getEmputy());
				LOGGER.info("有效期：" + ectripLic.getExpritdate());
				LOGGER.info("公司名称：" + WebContant.CORPNAME);
				if (Tools.getDays().compareTo(ectripLic.getExpritdate()) > 0) {
					
					 * this.addActionError("软件LIC 已经过期，请与开发商联系"); return INPUT;
					 
					return new ResponseBean(400, "软件LIC 已经过期，请与开发商联系", null);
				}
				// 设计日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 当时日期
				Date date1 = sdf.parse(Tools.getDays());
				// LIC 中日期
				Date date2 = sdf.parse(ectripLic.getExpritdate());
				int licdate = daysBetween(date2, date1);
				
				 * if (licdate < 30) { // 在后台显示 session.put("licdaysstr", "软件在" + licdate +
				 * "天后过期，"); } else { session.put("licdaysstr", ""); }
				 
			} else {
				// 没有 LIC文件
				
				 * System.out.println("LIC KEY FILE=" + licfile);
				 * this.addActionError("ectripkey.lic 文件丢失，请与开发商联系"); session.put("licdaysstr",
				 * "试用版，"); return INPUT;
				 
				return new ResponseBean(400, "ectripkey.lic 文件丢失，请与开发商联系", null);
			}
		} catch (Exception e) {
			
			 * this.addActionError("ectripkey.lic 文件丢失，请与开发商联系"); return INPUT;
			 
			return new ResponseBean(400, "ectripkey.lic 文件丢失，请与开发商联系", null);
		}*/

		/*
		 * if(codes!=null){ if (session == null || session.get("randomstr") == null ||
		 * session.get("randomstr").equals("")) { this.addActionError("验证码错误");
		 * getRequest().getSession().setAttribute("codes", "codes"); return INPUT; }
		 * else { if (!employee.getRandom().equalsIgnoreCase( (String)
		 * session.get("randomstr"))) { this.addActionError("验证码错误");
		 * getRequest().getSession().setAttribute("codes", "codes");
		 * getRequest().getSession().setAttribute("repeatUser", employee.getEmpid());
		 * 
		 * getRequest().getSession().setAttribute("repeatPsw", employee.getEmppwd());
		 * 
		 * return INPUT; } } }
		 */
		// Debug.print("randomstr session = 111 ");
		/*boolean signIsExpire = JWTUtil.signIsExpire(token);
		if(signIsExpire) {
			return new ResponseBean(400, "token已经过期,请先刷新token", null);
		}*/
		try {
//			Map<String, Object> usernameAndPassword = JWTUtil.getUsernameAndPassword(token);
//			String empId = usernameAndPassword.get("empId").toString();
			List emplist = employeeService.find("from Esfemployeetab where  empid = '" + empId + "'");
			if (emplist == null || emplist.size() == 0) {
				/*
				 * this.addActionError("无效用户"); return INPUT;
				 */
				return new ResponseBean(400, "无效用户", null);
			} else {
				employee = (Esfemployeetab) emplist.get(0);
				
				if (employee.getBissysuser() == 0) {
					/*this.addActionError("您的帐号已经被禁用！");
					getRequest().getSession().setAttribute("codes", "codes");
					return INPUT;*/
					return new ResponseBean(400, "您的帐号已经被禁用！", null);
					
				}
				if (employee.getByisuse() == 0) {
					/*this.addActionError("您的用户被禁用！");
					getRequest().getSession().setAttribute("codes", "codes");
					return INPUT;*/
					return new ResponseBean(400, "您的帐号已经被禁用！", null);
				}
				String loginintime = employee.getLogintime();
				Encrypt encrypt = new Encrypt();
				String password = encrypt.passwordEncrypt(empPwd);//usernameAndPassword.get("empPwd").toString();
				// 何安洲添加，判断密码
				if (!password.equals(employee.getSzpassword())) {
					if (loginintime.substring(0, 10).equals(Tools.getTodayString())) {
						if (employee.getZtimes() >= 5) {
							return new ResponseBean(400, "今日您已经登陆错误次数超过5次", null);
						}
					}
					if(loginintime != null && loginintime.length() > 10) {
						if (loginintime.substring(0, 10).equals(Tools.getTodayString())) {
							if (employee.getZtimes() == null || employee.getZtimes().equals("")) {
								employee.setZtimes(new Long(1));
							} else {
								employee.setZtimes(employee.getZtimes() + 1);
							}
						} else {
							employee.setLogintime(Tools.getDayTimes());
							employee.setZtimes(new Long(1));
						}
					} else {
						employee.setLogintime(Tools.getDayTimes());
						employee.setZtimes(new Long(1));
					}
					employeeService.update(employee);
					return new ResponseBean(400, "密码错误", null);
			} else {
				if (employee.getDnum() == null) {
					employee.setDnum(new Long(1));
				} else {
					employee.setDnum(employee.getDnum() + 1);
				}
				String lasttime = employee.getLogintime();
				String lastipaddress = employee.getIpaddress();
				employee.setLastipaddress(lastipaddress);
				employee.setLasttime(lasttime);

				HttpServletRequest request = getRequest();
				  String ip = request.getHeader("x-forwarded-for");
					if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
						ip = request.getHeader("Proxy-Client-IP");
					}
					if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
						ip = request.getHeader("WL-Proxy-Client-IP");
					}
					if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
						ip = request.getRemoteAddr();
					}
					
					employee.setIpaddress(ip);
				 }
				employee.setLogintime(Tools.getDayTimes());
				// 增加登录成功后，清空次数
				employee.setZtimes(new Long(0));
				this.employeeService.update(employee);
				Long companyid = employee.getIcompanyinfoid();
				Galcompanyinfotab galcompanyinfotab = (Galcompanyinfotab) this.employeeService.get(Galcompanyinfotab.class, companyid);
				employee.setSzcompanyinfoname(galcompanyinfotab.getSzcompanyname());
				employee.setCompanytype(galcompanyinfotab.getCompanytype());
				// 景区公司员工读取对应公司管理的服务商已(scenicid,scenics)保存的esfemployeetab的scenics中
				if (galcompanyinfotab.getCompanytype().equals("02")) {
					List comlist = this.employeeService.find("from Companyscenic where id.icompanyinfoid=" + companyid);
					if (comlist == null || comlist.size() == 0) {
						/*
						 * this.addActionError("您的公司还没有绑定服务商"); return INPUT;
						 */
						return new ResponseBean(400, "您的公司还没有绑定服务商！", null);
					} else {
						String scenics = "";
						for (int i = 0; i < comlist.size(); i++) {
							Companyscenic c = (Companyscenic) comlist.get(i);
							if (i == 0) {
								scenics = c.getId().getIscenicid().toString();
							} else {
								scenics = scenics + "," + c.getId().getIscenicid().toString();
							}
						}

						employee.setScenics(scenics);
					}
				}
				// 读取员工权限ID已（id,id,id）的形式保存到esfemployeetab的Dutys
				List postlist = this.employeeService
						.find("select es.idutyid from Esfemployeepoststab e,Esppostsdutytab es where iemployeeid="
								+ employee.getIemployeeid() + " and e.ipostsid=es.ipostsid");
				if (postlist == null || postlist.size() == 0) {
					/*
					 * this.addActionError("您没有具体的岗位，请与管理员联系"); return INPUT;
					 */
					return new ResponseBean(400, "您没有具体的岗位，请与管理员联系！", null);
				} else {
					String posts = "";
					for (int i = 0; i < postlist.size(); i++) {
						Long e = (Long) postlist.get(i);
						if (i == 0) {
							posts = e.toString();
						} else {
							posts = posts + "," + e.toString();
						}
					}

					employee.setDutys(posts);
				}
				/*
				 * session.put("employee", employee); session.put("usercs", employee.getDnum());
				 * session.put("userlastlogin", lasttime); session.put("adminusername",
				 * employee.getEmpid()); session.put("zhiwu",employee.getSzpost());
				 * session.put("user", employee.getIemployeeid());
				 */
				// 查询用户的按钮权限
				try {
					List employeeBtnDuty = employeeService.getEmployeeBtnDuty(employee.getEmpid());
					if (employeeBtnDuty != null && employeeBtnDuty.size() > 0) {
						// session.put("btnDuty", JSON.toJSONString(employeeBtnDuty));
						employee.setBtnDuty(JSON.toJSONString(employeeBtnDuty));
					} else {
						// session.put("btnDuty", "all");
						employee.setBtnDuty("all");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			

			}
			// ========================= 这里加入直正密码验证方法,注意注入风险
			// 判断密码
			// 修改登录次数
			// 登录错误的问题
			// 读权限
			// 读所管理服务
			// 是否登录
			//
			// ==================================================

			/*
			 * String clienturl = "/system/main.jsp"; this.setRedirectURL(clienturl); //
			 * Debug.print("randomstr session = redirect "); return "redirect";
			 */

			// return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("异常："+e.getMessage());
			return new ResponseBean(400, "未知错误", "具体错误:"+e.getMessage());
		}
		return new ResponseBean(200, "登录成功", employee);
	}
	/**
	 * 计算两个日期差值，返回为天数
	 * 
	 * @param now
	 * @param returnDate
	 * @return
	 */
	private static int daysBetween(Date now, Date returnDate) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.setTime(now);
		cReturnDate.setTime(returnDate);
		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}

	/**
	 * 返回整数，转成秒
	 * 
	 * @param intervalMs
	 * @return
	 */
	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}

	/**
	 * 设置日历日期
	 * 
	 * @param calendar
	 */
	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	/**
	 * 取LIC文件文件中的过期日期。
	 * 
	 * @return
	 */
	public static String getExpritdate() {
		return expritdate;
	}

	/**
	 * 判断程序是否过了有效期；
	 * 
	 * @return true 表示在有效期，false 过期； 返回为 true时，程序可以继续往下下执行。
	 *         这是一个静态方法，直接调用。可以通过getExpritdate 取到当前日期。
	 */
	public static boolean isExpritdate() {
		boolean ib_true = false;
		try {

			// LIC 文件 路径
			String licfile = "";//com.ectrip.base.util.WebContant.REALPATH+ "/WEB-INF/classes/ectripkey.lic";
			// 判断文件是否存在
			// System.out.println(1);
			File file = new File(licfile);
			// System.out.println(2);
			if (file.exists()) {
				System.out.println(3);
				// 有LIC文件
				System.out.println("LIC KEY FILE=" + licfile);
				EctripLic ectripLic = new EctripLic();
				// 读取LIC文件
				ectripLic.ReadEctripKey(licfile);
				// LIC有效期

				// 读取LIC文件中的联责；
				//emptuy = ectripLic.getEmputy();
				// System.out.println("有效期：" + ectripLic.getExpritdate());
				expritdate = ectripLic.getExpritdate();
				if (Tools.getDays().compareTo(ectripLic.getExpritdate()) > 0) {
					ib_true = true;
					return ib_true;
				}
				// System.out.println(4);
				// 设计日期格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// 当时日期
				Date date1 = sdf.parse(Tools.getDays());
				// LIC 中日期
				// System.out.println(5);
				Date date2 = sdf.parse(ectripLic.getExpritdate());
				int licdate = daysBetween(date2, date1);
				// System.out.println(7);
				if (licdate < 30) {
					// System.out.println(6);
					Object o = SpringUtil.getBean("webinfoService");
					if (o != null) {/*
						com.ectrip.system.permitenter.service.iservice.IWebinfotabService webinfoService = (com.ectrip.system.permitenter.service.iservice.IWebinfotabService);
						// 在后台显示
						com.ectrip.model.permitenter.Webinfotab wft = new com.ectrip.model.permitenter.Webinfotab();
						wft.setIhadder(new Long(1));
						wft.setIemployeeid(new Long(1));
						wft.setSzmemo("本系统于:" + ectripLic.getExpritdate()	+ "过期");
						wft.setReminddate(Tools.getNowTime());
						com.ectrip.model.syspar.Syslog syslog = new com.ectrip.model.syspar.Syslog();
						syslog.setIemployeeid(new Long(1));
						webinfoService.addWebinfotab(wft, syslog);
					*/}
					// System.out.println(7);
					ib_true = false;
				} else {
					ib_true = false;
				}
			} else {
				// System.out.println(9);
				// 没有 LIC文件
				System.out.println("LIC KEY FILE=" + licfile);
				System.out.println("ectripkey.lic 文件丢失，请与开发商联系");

				ib_true = true;

			}
			// System.out.println(10);

		} catch (Exception e) {
			System.out.println(12);
			System.out.println("ectripkey.lic 文件丢失，请与开发商联系:" + e);
			ib_true = true;
		}
		// System.out.println(11);
		return ib_true;
	}
	
	/**
	 * 读取系统允计服务商数量
	 * 
	 * @return
	 */
	public static int getProviderQuantity() {
		int rc_id = 0;
		try {

			// LIC 文件 路径
			String licfile = "";/*com.ectrip.base.util.WebContant.REALPATH
					+ "/WEB-INF/classes/ectripkey.lic";*/
			// 判断文件是否存在
			// System.out.println(1);
			File file = new File(licfile);
			// System.out.println(2);
			if (file.exists()) {
				System.out.println(3);
				// 有LIC文件
				System.out.println("LIC KEY FILE=" + licfile);
				EctripLic ectripLic = new EctripLic();
				// 读取LIC文件
				ectripLic.ReadEctripKey(licfile);
				// LIC有效期

				// 读取LIC文件中的联责；
				rc_id  = ectripLic.getProviderQuantity();
				// System.out.println("有效期：" + ectripLic.getExpritdate());
			
			} else {
				// System.out.println(9);
				// 没有 LIC文件
				System.out.println("LIC KEY FILE=" + licfile);
				System.out.println("ectripkey.lic 文件丢失，请与开发商联系");

				rc_id = 10;

			}
			// System.out.println(10);

		} catch (Exception e) {
			System.out.println(12);
			System.out.println("ectripkey.lic 文件丢失，请与开发商联系:" + e);
			rc_id = 10;
		}
		// System.out.println(11);
		return rc_id;
	}
	/**
	 * 售票客户端登录
	 * @param iscenicid
	 * @param userid
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/v1/saleClienLogin")
	public ResultBean saleClienLogin(@RequestParam("iscenicid")Long iscenicid,
									@RequestParam("userid")String userid,
									@RequestParam("password") String password){
		ResultBean rs = new ResultBean();
        try {
			rs.setColumnCount(2);
			rs.setColumnNames(new String[] { "returnstats", "message" });
			Esfemployeetab employee = null;
			List emplist = employeeService.find("from Esfemployeetab where empid='"
			        + userid + "' and byisuse=1");
			if (emplist == null || emplist.size() == 0) {
			    rs.addRow(new String[] { "false", "无效用户" });
			    return rs;
			} else {
			    employee = (Esfemployeetab) emplist.get(0);

			    Long companyid = employee.getIcompanyinfoid();
			    List glist = employeeService
			            .find("from Galcompanyinfotab where icompanyinfoid="
			                    + companyid);
			    Galcompanyinfotab galcompanyinfotab = (Galcompanyinfotab) glist
			            .get(0);
			    employee.setSzcompanyinfoname(galcompanyinfotab.getSzcompanyname());
			    employee.setCompanytype(galcompanyinfotab.getCompanytype());
			    if (galcompanyinfotab.getCompanytype().equals("02")) {
			        List comlist = employeeService
			                .find("from Companyscenic where id.icompanyinfoid="
			                        + companyid);
			        if (comlist == null || comlist.size() == 0) {

			            rs.addRow(new String[] { "false", "您所属公司未绑定服务商" });
			            return rs;
			        } else {
			            boolean b = false;

			            for (int i = 0; i < comlist.size(); i++) {
			                Companyscenic c = (Companyscenic) comlist.get(i);

			                if (iscenicid.longValue() == c.getId().getIscenicid()
			                        .longValue()) {

			                    b = true;

			                    break;
			                }
			            }
			            if (!b) {
			                rs.addRow(new String[] { "false", "你所在公司不能在该售票窗口售票" });
			                return rs;
			            }
			        }
			    }

			    else {
			        rs.addRow(new String[] { "false", "平台公司员工没有售票权限" });
			        return rs;

			    }
			    String loginintime = employee.getLogintime();
			    if (loginintime != null && loginintime.length() > 10) {
			        if (loginintime.substring(0, 10).equals(Tools.getTodayString())) {

			            if (employee.getZtimes() >= 5) {

			                rs.addRow(new String[] { "false", "今日您已经登陆错误次数超过5次" });
			                return rs;
			            }
			        }
			    }
			    Encrypt encrypt = new Encrypt();
			    password = encrypt.passwordEncrypt(password);
			    if (!password.equals(employee.getSzpassword())) {
			        if (loginintime != null && loginintime.length() > 10) {
			            if (loginintime.substring(0, 10).equals(
			                    Tools.getTodayString())) {
			                if (employee.getZtimes() == null
			                        || employee.getZtimes().equals("")) {
			                    employee.setZtimes(new Long(1));
			                } else {
			                    employee.setZtimes(employee.getZtimes() + 1);
			                }
			            } else {
			                employee.setLogintime(Tools.getDayTimes());
			                employee.setZtimes(new Long(1));
			            }
			        } else {
			            employee.setLogintime(Tools.getDayTimes());
			            employee.setZtimes(new Long(1));
			        }
			        employeeService.update(employee);
			        rs.addRow(new String[] { "false",
			                "密码错误 " + employee.getZtimes() + "次，超过5次当日将不能登录！" });
			        return rs;
			    } else { //
			        // 判断是否具有售票权限
			        List epdlist = employeeService
			                .find("select e.empid,epp.idutyid from Esfemployeetab e,Esfemployeepoststab ep,Esppostsdutytab epp,Espdutytab esp where  e.empid='"
			                        + userid
			                        + "' and epp.idutyid=esp.idutyid and  upper(esp.szdutycode)='SALERIGHT' and ep.iemployeeid=e.iemployeeid and ep.ipostsid=epp.ipostsid");

			        if (epdlist == null || epdlist.size() == 0) {

			            rs.addRow(new String[] { "false", "您不具有售票权限，不能登陆" });
			            return rs;
			        } else {
			            // 判断该用户是否有权在该售票窗口登陆
			            if (employee.getDnum() == null) {
			                employee.setDnum(new Long(1));
			            } else {
			                employee.setDnum(employee.getDnum() + 1);
			            }
			            String lasttime = employee.getLogintime();
			            String lastipaddress = employee.getIpaddress();
			            employee.setLasttime(lasttime);
			            employee.setLastipaddress(lastipaddress);
			            employee.setLasttime(Tools.getDayTimes());
			            employee.setZtimes(new Long(0));
			            employeeService.update(employee);
			            rs.addRow(new String[] { "true", "登录成功" });
			            return rs;
			        }
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return rs;
    }
}
