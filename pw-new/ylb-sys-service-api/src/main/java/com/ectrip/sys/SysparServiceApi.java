package com.ectrip.sys;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ectrip.base.util.ResultBean;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.baidu.pojo.Visitor;
import com.ectrip.sys.model.baidu.response.Response;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Customlog;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;


public interface SysparServiceApi {

	/**
	 * 根据条件查询员工信息
	 */
	@GetMapping(value = "employee/v1/listByCondition")
	public List getEmployeeListByCondition(@RequestParam("iemployeeid")Long iemployeeid, @RequestParam("icompanyinfoid")Long icompanyinfoid, @RequestParam("szemployeename")String szemployeename);
	
	
	/**
	 * 根据pmky,pmcd集合获取系统参数列表
	 */
	@GetMapping("/syspar/v1/queryByPmkyPmcdwhere")
	public List queryByPmkyPmcds(@RequestParam("pmky") String pmky, @RequestParam("pmcdwhere") String pmcdwhere);

	
	/**
	 * 根据pmcd集合获取系统参数列表 
	 * pmcds数据格式[12,541,625,51]
	 */
	@GetMapping("/syspar/v1/listByPmcds")
	public List getSysparamsByPmcds(@RequestParam("pmcds") String pmcds);

	
	/**
	 * 根据pmcd集合和pmky值获取系统参数列表
	 *  pmcds数据格式[12,541,625,51]
	 */
	@GetMapping("syspar/v1/listByPmkyAndPmcds")
	public List getSysparamsByPmkyAndPmcds(@RequestParam("pmky") String pmky, @RequestParam("pmcds") String pmcds);
	
	/**
	 * 根据pmcd集合和pmky值获取系统参数列表
	 */
	@GetMapping("syspar/v1/findSysparByPmky")
	public List<Sysparv5> findSysparByPmky(@RequestParam("pmky") String pmky, @RequestParam("pmcd") String pmcd);

	/**
	 * 根据comid获取iscenicid列表
	 */
	@GetMapping("company/v1/getComscenicsId")
	public List getComscenicsId(@RequestParam("comid") Long comid);

	/**
	 * 根据客源地id获取客源地信息
	 */
	@GetMapping(value = "sourceRegion/v1/getSourceregionById")
	public Galsourceregiontab getSourceregionById(@RequestParam("iregionalid") Long iregionalid);

	/**
	 * 根据企业id获取景区id列表
	 */
	@GetMapping(value = "company/v1/getIscenicidsByIcompanyinfoid")
	public List getIscenicidsByIcompanyinfoid(@RequestParam(value = "icompanyinfoid", required = false) Long icompanyinfoid);

	@PostMapping("/syspar/findOne")
	public Sysparv5 findOne(@RequestParam("pmky") String pmky, @RequestParam("pmcd") String pmcd);
	
	@PostMapping("/syspar/findOneById")
	public Sysparv5 findOneById(@RequestParam("name") String name, @RequestParam("code") String code);

	
	@GetMapping("/syspar/findMerchant")
	public Map<String, String> findMerchant();

	@GetMapping("/syspar/getMerchantId")
	public String getMerchantId();

	@PostMapping("/syspar/employee")
	public Esfemployeetab getEsfemployeeInfo(@RequestParam("employeeId") Long employeeId);

	@GetMapping("/syspar/findReturnUrle")
	public String findReturnUrle();

	@GetMapping("/syspar/findPayUrld")
	public String findPayUrld();

	
	@GetMapping("/syspar/retrieve")
	public List retrieve(@RequestParam("pmky") String pmky);

	@PostMapping(value = "/galsourcere/findByIds")
	public List<Galsourceregiontab> findByIds(@RequestParam("ids") String ids);

	@PostMapping(value = "/galsourcere/findByCode")
	public Galsourceregiontab findByCode(@RequestParam("code") String code);

	@GetMapping(value = "/sourceRegion/v1/getLowerRegion")
	public ResponseBean sourceRegionJson(@RequestParam("iregionalid") Long iregionalid);

	/**
	 * 根据景区id查询员工信息
	 * @param scenicId
	 * @return
	 */
	@PostMapping("/employee/getEsfemployeeInfoListByScenicId")
	public List<Esfemployeetab> getEsfemployeeInfoListByScenicId(@RequestParam("scenicId") Long scenicId);

	/**
	 * 发送短信功能
	 * @param telno
	 * @param type
	 * @param content
	 */
	@PostMapping("/message/sendMessageNew")
	public void sendMessageNew(@RequestParam("telno") String telno, @RequestParam("type") String type,@RequestParam("content") String content);

	/* @RequestMapping("/message/sendMessageNew") 
	 public void sendMessageNew(String telno,String type,String[] content); */

	/**
	 * 系统日志添加
	 * @param syslog
	 */
	@PostMapping("/sysLog/add")
	public void saveSysLog(@RequestBody Syslog syslog);

	/**
	 * 
	 * Describe:添加前台用户操作日志
	 * @auth:lijingrui
	 * @param customlog
	 *            return:void
	 *             Date:2011-10-6
	 */
	@PostMapping("syslog/insertTomlog")
	public void insertTomlog(@RequestBody Customlog customlog);

	/**
	 * 
	 * Describe:添加后台操作日志
	 * 
	 * @auth:lijingrui
	 * @param syslog
	 *            return:void Date:2011-10-6
	 */
	@PostMapping("syslog/v1/insertSyslog")
	public void insertSyslog(@RequestBody Syslog syslog);

	/**
	 * 
	 * Describe:添加订单操作日志
	 * 
	 * @auth:lijingrui
	 * @param orderlog
	 *            return:void Date:2011-10-6
	 */
	@PostMapping("syslog/insertOrderlog")
	public void insertOrderlog(@RequestBody Orderlog orderlog);

	/**
	 * 获取打印回执
	 * 
	 * @param isalevoucherid
	 * @param iscenicid
	 * @param type
	 * @return
	 */
	@PostMapping("/voucher/getSalesVoucher")
	public String getSalesVoucher(@RequestParam("isalevoucherid") String isalevoucherid,
			@RequestParam("iscenicid") String iscenicid, @RequestParam("type") int type);

	/**
	 * 鏍规嵁璁㈠崟Id锛岃幏鍙栬鍗曞洖鎵т俊鎭�
	 * 
	 * @param orid
	 * @param iscenicid
	 * @param type
	 * @return
	 */
	@PostMapping("/voucher/getordermessage")
	public String getordermessage(@RequestParam("orid") String orid, @RequestParam("iscenicid") String iscenicid,
			@RequestParam("type") int type);

	/**
	 * 鏌ヨ绯荤粺鍙傛暟 query("DYSJ", "sys.id.pmcd='0001'")
	 * 
	 * @param pmky
	 * @param pmcdwhere
	 * @return
	 */
	@PostMapping("/syspar/query")
	public List<?> query(@RequestParam("pmky") String pmky, @RequestParam("pmcdwhere") String pmcdwhere);

	@PostMapping("/visito/checkVisitorPhoto")
	public Response checkVisitorPhoto(@RequestParam("visitorId") String visitorId);

	/**
	 * 
	 * @param visitor
	 * @param flag
	 * @param ticketJSON:
	 *            Ticket ticketJSON
	 * @return
	 */
	@PostMapping("/visito/addTicket")
	public Response addTicket(@RequestBody Visitor visitor, @RequestParam("visitorId") String flag,
			@RequestParam("ticketJSON") String ticketJSON);

	@GetMapping("/message/dbSendInfo")
	public int DbSendInfo() throws Exception;


	/**
	 * 计算用户的预付款余额 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:float Date:2011-11-11
	 */
	@PostMapping("/balanceCenter/getsumMoney")
	public float getsumMoney(@RequestParam("usid") String usid);

	/**
	 * 获取主键最大值 Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:int Date:2011-11-16
	 */
	@PostMapping("/balanceCenter/getMaxSeq")
	public int getMaxSeq(@RequestParam("tablename") String tablename, @RequestParam("column") String column);


	@PostMapping("/bank/updateOrderStatus")
	public int updateOrderStatus(@RequestParam("orid") String orid, @RequestParam("payid") String payid,
			@RequestParam("mont") String mont, @RequestParam("bank") String bank, @RequestParam("isok") int isok,
			@RequestParam("ddzt") String ddzt, @RequestParam("orderType") String orderType,
			@RequestParam("zffs") String zffs, @RequestParam("zfusid") String zfusid, @RequestParam("note") String note)
			throws Exception;

	@PostMapping("/pullSeat/pullSeat")
	public String pullSeat(@RequestParam("orid") String orid) throws Exception;

	@PostMapping("/querypdtpList")
	List querypdtpList(@RequestParam("scenictype") String scenictype, @RequestParam("type") String type);

	@GetMapping("/getAllSourceRegion")
	List getAllSourceRegion();

	@GetMapping(value = "/employee/v1/currentUser")
	public Esfemployeetab currentUser();

	@GetMapping(value = "syspar/v1/listByParms")
	public List getSysparamsByParms(@RequestParam("pmky") String pmky,@RequestParam("pmcd")  String pmcd,@RequestParam("pmvbs") String pmvbs,@RequestParam("spmcd") String spmcd);
	
	@GetMapping(value = "/syspar/getMaxPk")
	public Long getMaxPk(@RequestParam("columnName") String columnName, @RequestParam("tableName") String tableName);
	
	@GetMapping(value = "/employee/v1/getEmployeeListByIemployeeid")
	public List getEmployeeListByIemployeeid(@RequestParam("iemployeeids") String iemployeeids,@RequestParam("icompanyinfoid") Long icompanyinfoid);
	
	/**
	 * 售票客户端登录
	 * @param iscenicid
	 * @param userid
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/v1/saleClienLogin")
	public ResultBean saleClienLogin(@RequestParam("iscenicid")Long iscenicid, @RequestParam("userid")String userid, @RequestParam("password") String password); 
	
	/**
	* @Title: getPrintticketmanageList  
	* @Description: TODO 根据景区服务商Id和业务Id查询打印设置信息
	* @param @param scenicId 景区服务商id
	* @param @param ibusinessId 业务Id
	* @param @return    参数  
	* @return List<?>    返回类型  
	* @throws
	 */
	@GetMapping(value = "/printticket/v1/getPrintticketmanageList")
	public List<?> getPrintticketmanageList(@RequestParam("scenicId")Long scenicId,@RequestParam("ibusinessId") Long ibusinessId);
	
	@GetMapping(value = "/company/v1/viewCompanyInfo")
	public ResponseBean viewCompanyInfo(@RequestParam("icompanyinfoid") Long icompanyinfoid);

	
	@PostMapping(value = "/company/v1/getCompanyscenic")
	public Companyscenic getCompanyscenic(@RequestParam("iscenicid") Long iscenicid);

	/**
	 * 根据hql获取系统参数
	 * @param sysparv5Id
	 * @return
	 */
	@GetMapping(value = "syspar/v1/find")
	public List find(@RequestParam("query") String query);
	
	@GetMapping("/balanceCenter/findVipbalanceByUsid")
	public Object findVipbalanceByUsid(@RequestParam("usid")Long usid);


	@GetMapping("/company/getGalcompanyinfo")
	public Galcompanyinfotab getGalcompanyinfo(@RequestParam("iscenicid") Long iscenicid);
	
	@PostMapping("/employee/getEsfemployeeByGalcompanyScenicid")
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicid(@RequestParam("scenicId") Long scenicId);
	
	@PostMapping("/getEsfemployeeByGalcompanyScenicids")
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicids(@RequestParam("scenicId")Long iscenicid, @RequestParam("keys")String keys);
	

	
	@PostMapping(value = "employee/v1/update")
	public ResponseBean update(@RequestBody Object entity);

}
