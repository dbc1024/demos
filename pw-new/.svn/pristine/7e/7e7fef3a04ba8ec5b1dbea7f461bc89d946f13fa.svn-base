package com.ectrip.ticket.webservice.sale.impl;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.Debug;
import com.ectrip.base.util.DecimalUtil;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.QrCodesUtil;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.ticket.OrderPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.checkticket.service.iservice.ICheckdengjiService;
import com.ectrip.ticket.common.checkUtils.CommonUtil;
import com.ectrip.ticket.common.service.iservice.IStockService;
import com.ectrip.ticket.configuration.ReportServerConfig;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.cyt.common.util.JSONUtil;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.centersale.T_orderlist;
import com.ectrip.ticket.model.centersale.T_zorderlist;
import com.ectrip.ticket.model.centersale.Trealname;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.Proordercontroltab;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.model.stock.StockJson;
import com.ectrip.ticket.model.stock.StockOrderInfo;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.model.venuemarketing.Venueprogram;
import com.ectrip.ticket.provider.service.ITicketTypeService;
import com.ectrip.ticket.provider.service.ITimeSharingService;
import com.ectrip.ticket.sale.Returnmodel;
import com.ectrip.ticket.sale.service.SaleCenterService;
import com.ectrip.ticket.sale.service.card.core.CoreDistribution;
import com.ectrip.ticket.sale.service.card.core.DesUtil;
import com.ectrip.ticket.sale.service.cytterminal.service.CytSaleMainService;
import com.ectrip.ticket.sale.service.cytterminal.service.iservice.ICytSaleService;
import com.ectrip.ticket.sale.service.iservice.ISaleAutoService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.sale.service.iservice.ISaleCheckupService;
import com.ectrip.ticket.sale.service.iservice.ISaveVenueService;
import com.ectrip.ticket.stocks.service.iservice.IStocksWareService;
import com.ectrip.ticket.util.JaxWsDynamicClientFactoryUtil;
import com.ectrip.ticket.util.ServerNameConst;
import com.ectrip.ticket.webservice.sale.ISaleWebService;
import com.netflix.client.http.HttpRequest;
/**
 * 售票窗口webService接口
 * @author jy
 *
 */
@WebService(serviceName = "saleService", // 与接口中指定的name一致
targetNamespace = "http://sale.webservice.ticket.ectrip.com/", // 与接口中的命名空间一致,一般是接口的包名倒
endpointInterface = "com.ectrip.ticket.webservice.sale.ISaleWebService"// 接口地址
)
@Component
public class SaleWebServiceImpl implements ISaleWebService{

	private static final Logger LOGGER = LogManager.getLogger(SaleWebServiceImpl.class);
	@Autowired
	private SysService sysService; //远程调用系统服务
	@Autowired
	private ITicketTypeService ticketTypeService;
	@Autowired
	private EcService ecService; //远程调用电商服务
	@Autowired
	private ISaleCenterService saleCenterService; //售票service
	@Autowired
	private IStocksWareService stockswareService;//库存service
	@Autowired
	private CytSaleMainService cytSaleMainService;//分销service
	@Autowired
	private ISaveVenueService saveVenueService; //场馆service
	@Autowired
	private ISaleCheckupService saleCheckupService;//检票Service
	@Autowired
	private RestTemplate restTemplate;//基于ribbon http客户端
	@Autowired
	private IStockService stockService;//库存服务
	@Autowired
	private ReportServerConfig reportServerConfig; //获取报表服务器信息
	@Autowired
	private ISaleAutoService saleAutoService;
	@Autowired
	private ITimeSharingService timeSharingService;
	@Autowired
	private ICheckService checkService;
	@Autowired
	private CommonUtil commonUtil;

	/**
     * 售票员修改密码
     *
     * @param userid
     * @param password
     * @return -1 无效用户 -2 所属公司未绑定服务商 -3 你所在公司不能在该售票窗口售票 -4 密码错误 -5
     *         今日您已经登陆错误次数超过5次 0 您不具有售票权限，不能登陆 1 登陆成功
     */
	
    public int changepassword(String userid, String oldpassword,
                              String newpassword) throws Exception {
    	String url = WebContant.GetKeyValue("CenterUrl");
        int rc_id = 0;
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            	Object[] objects = client.invoke("changepassword",userid,oldpassword,newpassword);
            	rc_id = Integer.valueOf(objects[0].toString());
            } catch (Exception e) {
                rc_id = -2;
            }
        } else {
            rc_id = 1;
        }
        if (rc_id == 1) {
            saleCenterService.changepassword(userid, oldpassword, newpassword);
        }
        return rc_id;
    }

    /**
     * 强制退订授权登录 Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param userid
     * @param password
     * @return
     * @throws Exception
     *             return:int Date:2012-1-8
     */
    public String empztlogin(Long iscenicid, String userid, String password)
            throws Exception {
        
        ResultBean rs = saleCenterService.empztlogin(iscenicid, userid,
                password);
        return JSON.toJSONString(rs);
    }

    public String getEmployee(String userid) {
    	ResultBean rs = saleCenterService.getEmployee(userid);
        return JSON.toJSONString(rs);
    }

    /**
     * 测试ResultBean
     *
     * @return
     * @throws Exception
     */
    public String getResultBean() throws Exception {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
//        String version = CommonUtil.getVerison("02");
        rs.setColumnNames(new String[] { "version"});
        rs.addRow(new String[] {""});
        String reportServerUrl = reportServerConfig.getHost()+":"
        +reportServerConfig.getPort()+reportServerConfig.getContextPath();
        rs.setColumnNames(new String[] { "reportServerUrl"});
        rs.addRow(new String[] {reportServerUrl});
        return JSON.toJSONString(rs);
        
    }

    /**
     * 根据ID、byisuse查找售票点
     *
     * @param id
     * @return
     */

    public String getEsbticketstationtabByID(Long id, Long byisuse) {
        ResultBean rs = saleCenterService.getEsbticketstationtabByID(id,
                byisuse);
        return JSON.toJSONString(rs);
    }

    /**
     * 根据景区ID（iscenicid） IP、byisuse查找售票窗口
     *
     * @param ip
     * @return
     */

    public String getEsbticketwintabByIP(Long iscenicid, String ip,
                                             Long byisuse) {
        ResultBean rs = saleCenterService.getEsbticketwintabByIP(iscenicid, ip,
                byisuse);
        return JSON.toJSONString(rs);
    }

    /**
     * 取出景区类型的服务商
     */
    public String getscenic() {
    	ResultBean rs = saleCenterService.getscenic();
    	
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param ibusinessid
     * @return return:ResultBean Date:2012-3-26
     */
    public String getprintmanage(Long iscenicid, Long ibusinessid) {
    	ResultBean rs = saleCenterService.getprintmanage(iscenicid, ibusinessid);
        return JSON.toJSONString(rs);
    }

    /**
     * 取出所有业务
     */
    public String getbusiness() {
    	ResultBean rs = saleCenterService.getbusiness();
        return JSON.toJSONString(rs);
    }

    /**
     * 取出该出票员所在窗口的所售票的价格
     */
    public String getTicketPriceByJsfz(Long iticketwinid, Long iemployeeid,
		    Long ibusinessid, String stdt) {
	    ResultBean rs = saleCenterService.getTicketPrice(iticketwinid, iemployeeid,
			    ibusinessid, stdt);
	    return JSON.toJSONString(rs);
    }

    /**
     * 取出该出票员所在窗口的所售票的价格
     * getTicketPricebyiscenicid
     * getTicketPrice
     */
    public String getTicketPrice(Long iticketwinid, Long iemployeeid,
                                     Long ibusinessid, String stdt, String jsfz) {

    	ResultBean rs = saleCenterService.getTicketPrice(iticketwinid, iemployeeid,
                ibusinessid, stdt, jsfz);
        return JSON.toJSONString(rs);
    }

    /**
     * 取出该出票员所在窗口的所售的指定服务商的票的价格
     */
    public String getTicketPricebyiscenicid(Long iticketwinid,Long iemployeeid, Long ibusinessid, String stdt, Long iscenicid) {

    	ResultBean rs = saleCenterService.getTicketPricebyiscenicid(iticketwinid,
                iemployeeid, ibusinessid, stdt, iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     * 取出该出票员所在窗口的所售的指定服务商的票的价格
     */
    public String getTicketPricebyiscenicidByJsfz(Long iticketwinid,
                                                Long iemployeeid, Long ibusinessid, String stdt, Long iscenicid,
                                                String jsfz) {

    	ResultBean rs =  saleCenterService.getTicketPricebyiscenicid(iticketwinid,
                iemployeeid, ibusinessid, stdt, iscenicid, jsfz);
    	return JSON.toJSONString(rs);
    }

    /**
     * 读取竹筏数量控制数量信息 Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param stdt
     * @return return:ResultBean Date:2011-10-11
     */
	public String getTripcontrol(Long iscenicid, String stdt, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getTripcontrol", iscenicid, stdt);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
				if (cano.getRowsCount() > 0) {

					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());

					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());

					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {

							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				rb.addRow(new String[] { "false", e.getMessage() });
				return JSON.toJSONString(rb);

			}
		} else {
			ResultBean rb = saleCenterService.getTripcontrol(iscenicid, stdt);
			return JSON.toJSONString(rb);
		}

	}

    /**
     * 取出该出票员所在窗口的所售票的价格
     */
    public String getTicket(Long iscenicid) {
        
    	ResultBean rs = saleCenterService.getTicket(iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     * 根据业务读取用户 Describe:
     *
     * @auth:yuanchengjun
     * @param ibusinessid
     * @return return:ResultBean Date:2011-10-27
     */
    public String getcustom(Long ibusinessid) {

    	ResultBean rs = saleCenterService.getcustom(ibusinessid);
        return JSON.toJSONString(rs);
    }

    public String getemployeecard(Long iscenicid) {
        
    	ResultBean rs = saleCenterService.getemployeecard(iscenicid);
    	
        return JSON.toJSONString(rs);
    }

	public String getTrip(Long itickettypeid, String stdt, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getTrip", itickettypeid, stdt);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
				if (cano.getRowsCount() > 0) {
					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());
					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				System.out.print(e);
				rb.addRow(new String[] { "false", e.getMessage() });
				return JSON.toJSONString(rb);
			}
		} else {
			ResultBean rb = saleCenterService.getTrip(itickettypeid, stdt);
			return JSON.toJSONString(rb);
		}

	}

	public String getAllTrip(Long iscenicid, String stdt, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getAllTrip", iscenicid, stdt);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
				if (cano.getRowsCount() > 0) {
					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());
					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				e.printStackTrace();
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				rb.addRow(new String[] { "false", e.getMessage() });
				return JSON.toJSONString(rb);
			}
		} else {
			ResultBean rb = saleCenterService.getAllTrip(iscenicid, stdt);
			return JSON.toJSONString(rb);
		}
	}

    /**
     *
     * 销售受限产品的产品,趟次和日期读取该产品趟次数量控制:
     *
     * @see com.ectrip.sale.service.iservice.ISaleCenterService#getProductcontrol(java.lang.Long,java.lang.Long,
     *      java.lang.String)
     * @param iscenicid
     * @param stdt
     * @return byisduty=1 现场销售数量控制 0 现场和网上销售同时数量控制
     * @author yuanchengjun Date:2011-10-27 返回数据为空时
     *
     */
    public String getProductcontrol(Long itickettypeid, Long tripid,
			String stdt, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getProductcontrol", itickettypeid, tripid, stdt);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
				if (cano.getRowsCount() > 0) {
					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());
					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				e.printStackTrace();
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				rb.addRow(new String[] { "false", e.getMessage() });
				return JSON.toJSONString(rb);
			}
		} else {
			ResultBean rb = saleCenterService.getProductcontrol(itickettypeid, tripid, stdt);
			return JSON.toJSONString(rb);
		}
	}

    /**
     *
     * 销售受限产品的产品,趟次和日期读取该产品趟次数量控制和日控制数量:
     *
     * @see com.ectrip.sale.service.iservice.ISaleCenterService#getProductcontrol(java.lang.Long,java.lang.Long,
     *      java.lang.String)
     * @param iscenicid
     * @param stdt
     * @return byisduty=1 现场销售数量控制 0 现场和网上销售同时数量控制
     * @author yuanchengjun Date:2011-10-27 返回数据为空时
     *
     */
	public String getProductdatacontrol(Long itickettypeid, String stdt, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getProductdatacontrol", itickettypeid, stdt);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
				if (cano.getRowsCount() > 0) {
					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());

					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				e.printStackTrace();
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				rb.addRow(new String[] { "false", e.getMessage() });
				return JSON.toJSONString(rb);
			}
		} else {
			ResultBean rb = saleCenterService.getProductdatacontrol(itickettypeid, stdt);
			return JSON.toJSONString(rb);
		}

	}

    public String getAllcustom() {
        
    	ResultBean rb = saleCenterService.getAllcustom();
    	return JSON.toJSONString(rb); 
    }

    /**
     * 销售凭证:salesvouchers=
     * "iscenicid&iticketwinid&ibusinessid&iemployeeid&iaccountreceivable&iacceptmoney&igivechange&usid&forceemid&pzlb"
     * ;服务商&窗口ID&业务ID&出票员ID&实收&应收&找零&用户名&forceemid&凭证类别 当业务ID=1 散客业务
     * usid='guest',其他业务时 usid是选择的用户usid 销售凭证明细:salesvoucherdetails =
     * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iticketnum&dtstartdate&dtenddate"
     * ;明细ID&价格ID&产品ID&数量&开始日期&截至日期 子票明细: comticketsalesdetails=
     * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iztickettypeid&isplitamount&tripid&ivenueareaid&ivenueseatsid&dtstartdate&dtenddate"
     * 明细ID&价格ID&产品ID&子产品ID&数量&竹筏趟次ID&码头ID&座位ID&开始日期&截至日期
     * 产品控制数量:productcontrols=
     * "itickettypeid&controltype&tripid&stdata&soldnumber"
     * 产品编号&控制方式&趟次&日期&销售数量:产品编号&控制方式&趟次&日期&销售数量 Describe:
     *
     * @auth:yuanchengjun
     * @param salesvouchers
     * @param salesvoucherdetails
     * @param comticketsalesdetails
     * @param productcontrols
     * @return return:ResultBean Date:2011-10-29
     */
    private ResultBean saveorder40(String salesvouchers,
                                   String salesvoucherdetails, String comticketsalesdetails,
                                   String productcontrols,String url) {
    	if(url==null || url.length()<1){
    		url = WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        LOGGER.debug("进入 saveorder40 方法");
        rs.setColumnNames(new String[] { "returnstats", "message" });
        String returnstats = "true";
        String message = "";
        ResultBean cano = null;

        if (salesvouchers == null || salesvouchers.equals("")
                || salesvoucherdetails == null
                || salesvoucherdetails.equals("")
                || comticketsalesdetails == null
                || comticketsalesdetails.equals("")) {
            rs.addRow(new String[] { "false", "数据不完整，不能保存" });
            return rs;
        }
        String[] salesvoucher = salesvouchers.split("&");

        Long iticketwinid = new Long(salesvoucher[1]);

        Long ibusinessid = new Long(salesvoucher[2]);
        String usid = salesvoucher[7];
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            if (productcontrols != null && !productcontrols.equals("")) {
                try {
                    if (ibusinessid == 3) {
                        // 预留量更新
                        LOGGER.info("开始更新预留量");
                        Object[] objects = client.invoke("updateProductReserve", usid);
        				cano = (ResultBean) objects[0];
        				LOGGER.info("更新预留量成功");
                    } else {
                        // 判断单竹筏是否可以销售

                        String[] comticketsalesdetail = comticketsalesdetails
                                .split(":");

                        for (int i = 0; i < comticketsalesdetail.length; i++) {

                            String[] zdetail = comticketsalesdetail[i]
                                    .split("&");

                            Long itickettypeid = new Long(zdetail[2]);

                            Long iztickettypeid = new Long(zdetail[3]);
                            Long tripid = new Long(zdetail[5]);

                            String dtstartdate = zdetail[8];

                            if (itickettypeid == 1 && iztickettypeid == 1) {
                            	Object[] objects = client.invoke("getProductcontrol", itickettypeid,
                                        tripid, dtstartdate);
                				cano = (ResultBean) objects[0];
                                if (null == cano.getResult(0, "ISXIANJIN")
                                        || cano.getResult(0, "ISXIANJIN")
                                        .equals("0")
                                        || cano.getResult(0, "ISXIANJIN")
                                        .equals("NULL")) {

                                    rs.addRow(new String[] {
                                            "false",
                                            "现金窗口目前不能销售" + dtstartdate + "第"
                                                    + tripid + "趟的单竹筏票" });
                                    return rs;
                                }
                            }
                        }
                        // 可售量更新
        				Object[] objects = client.invoke("getProductdatacontrol", productcontrols);
        				cano = (ResultBean) objects[0];
                    }
                    returnstats = cano.getResult(0, 0);
                } catch (Exception e) {
                    rs.addRow(new String[] { "false", "连接中心服务器失败，不能保存订单" });
                    return rs;
                }
            }

            if (returnstats.equals("false")) {
                rs.addRow(new String[] { "false", cano.getResult(0, 1) });
                return rs;
            } else {
                try {
                    Long maxid = saleCenterService.updatevouchersequence();
                    Esbticketwintab esbticketwintab = saleCenterService
                            .getEsbticketwintab(iticketwinid);
                    int ticketstation = esbticketwintab.getIticketstationid()
                            .intValue();
                    String szticketstation = "";
                    if (0 < ticketstation && ticketstation < 10) {
                        szticketstation = "00" + ticketstation;
                    } else if (ticketstation >= 10 && ticketstation < 100) {
                        szticketstation = "0" + ticketstation;
                    } else {
                        szticketstation = "" + ticketstation;
                    }
                    String szsalesvoucherno = "";
                    szsalesvoucherno = saleCenterService
                            .updateMaxNo(szticketstation);
                    LOGGER.info("保存订单");
                    ResultBean rs1 = saleCenterService.saveorder(salesvouchers,
                            salesvoucherdetails, comticketsalesdetails, maxid,
                            szsalesvoucherno,url);
                    returnstats = rs1.getResult(0, 0);
                    message = rs1.getResult(0, 1);
                    if (returnstats.equals("false")) {
                    	LOGGER.info("保存订单失败，失败原因：" + message);
                        if (productcontrols != null
                                && !productcontrols.equals("")) {
                            try {
                                if (ibusinessid == 3) {
                                	Object[] objects = client.invoke("updatecancelProductreserve", productcontrols, usid);
                    				cano = (ResultBean) objects[0];
                                } else {
                                	Object[] objects = client.invoke("updatecancelProductcontrol", productcontrols);
                    				cano = (ResultBean) objects[0];
                                }
                            } catch (Exception e1) {
                            	LOGGER.error(e1.getMessage());
                                message = "保存失败";
                            }
                            if (cano.getResult(0, 0).equals("false")) {
                                message = "保存失败";
                            }
                        }
                    }
                    rs.addRow(new String[] { returnstats, message });
                    return rs;

                } catch (Exception e) {
                    LOGGER.error("保存订单错误，异常为："+e.getMessage());
                    message = "保存失败";
                    try {
                        if (ibusinessid == 3) {
                        	 LOGGER.info("返还预留量");
                             Object[] objects = client.invoke("updatecancelProductreserve", productcontrols, usid);
                             cano = (ResultBean) objects[0];
                        } else {
                           Object[] objects = client.invoke("updatecancelProductcontrol", productcontrols);
                           cano = (ResultBean) objects[0];
                        }
                    } catch (Exception e1) {
                    	LOGGER.error("保存订单错误，异常为："+e1.getMessage());
                        message = "保存失败";
                    }
                    if (cano.getResult(0, 0).equals("false")) {
                        message = "保存失败";
                    }
                }
                rs.addRow(new String[] { returnstats, message });
                return rs;
            }
        } else {
        	LOGGER.info("本地现金票，本地数据库");
            try {
                Long maxid = saleCenterService.updatevouchersequence();
                Esbticketwintab esbticketwintab = saleCenterService
                        .getEsbticketwintab(iticketwinid);
                int ticketstation = esbticketwintab.getIticketstationid()
                        .intValue();
                String szticketstation = "";
                if (0 < ticketstation && ticketstation < 10) {
                    szticketstation = "00" + ticketstation;
                } else if (ticketstation >= 10 && ticketstation < 100) {
                    szticketstation = "0" + ticketstation;
                } else {
                    szticketstation = "" + ticketstation;
                }
                String szsalesvoucherno = "";
                szsalesvoucherno = saleCenterService
                        .updateMaxNo(szticketstation);
                return saleCenterService.savebendiorder(salesvouchers,
                        salesvoucherdetails, comticketsalesdetails,
                        productcontrols, maxid, szsalesvoucherno);

            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return rb;
            }
        }
    }

    /**
     * 根据售票凭证id和售票点读取所有票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return return:ResultBean Date:2011-12-6
     */
    public String soldticketlist(Long isalesvoucherid, Long iticketstationid) {
        ResultBean rb = saleCenterService.soldticketlist(isalesvoucherid,
                iticketstationid);
        return JSON.toJSONString(rb);
    }

    /**
     * 根据退订单凭证id和售票点读取所有票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return return:ResultBean Date:2011-12-6
     */
    public String salezdetaillist(Long isalesvoucherid,Long iticketstationid) {
    	ResultBean rb = saleCenterService.salezdetaillist(isalesvoucherid,iticketstationid);
        return JSON.toJSONString(rb);
    }

    /**
     * 根据身份证和服务商编号读取服务商网上已付款的订单信息
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:ResultBean Date:2011-11-7
     */
    public String getT_order(String carno, Long iscenicid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        carno = orderDecode(carno);
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {

			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getT_order", carno, iscenicid);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
				if (cano.getRowsCount() > 0) {

					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());
					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				e.printStackTrace();
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
        	ResultBean rb = saleCenterService.getT_order(carno, iscenicid);
            return JSON.toJSONString(rb);
        }

    }

    /**
     * 根据身份证和服务商编号读取服务商网上已付款的订单信息
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:ResultBean Date:2011-11-7
     */
    public String getT_orderbyorid(String orid, Long iscenicid,String url) {
        orid = orderDecode(orid);
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getT_orderbyorid", orid, iscenicid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
        	ResultBean rb = saleCenterService.getT_orderbyorid(orid, iscenicid);
        	return JSON.toJSONString(rb);
        }

    }

    public String getT_orderlist(String orid, Long iscenicid,String url) {

        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getT_orderlist", orid, iscenicid);
            	
            	
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();

                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());

                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
            	LOGGER.error("本地服务器查询中心服务器订单详细接口异常："+StringUtil.toString_02(e));
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
        	ResultBean rb = saleCenterService.getT_orderlist(orid, iscenicid);
            return JSON.toJSONString(rb);
        }
    }

    public String getT_zorderlistbyorderlistid(Long orderlistid,
			String orid, Long iscenicid, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getT_zorderlistbyorderlistid", orderlistid, orid, iscenicid);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();

				if (cano.getRowsCount() > 0) {
					rb.setColumnCount(cano.getColumnCount());
					rb.setColumnNames(cano.getColumnNames());

					rb.setColumnCount(cano.getColumnCount());
					rb.setRowsCount(cano.getRowsCount());
					for (int i = 0; i < cano.getRowsCount(); i++) {
						String[] values = new String[cano.getColumnCount()];
						for (int j = 0; j < cano.getColumnCount(); j++) {
							values[j] = cano.getResult(i, j);
						}
						rb.addRow(values);
					}
				}
				return JSON.toJSONString(rb);
			} catch (Exception e) {
				ResultBean rb = new ResultBean();
				rb.setColumnCount(2);
				rb.setColumnNames(new String[] { "returnstats", "message" });
				System.out.print(e);
				rb.addRow(new String[] { "false", e.getMessage() });
				return JSON.toJSONString(rb);
			}
		} else {
			
			ResultBean rb = saleCenterService.getT_zorderlistbyorderlistid(orderlistid, orid, iscenicid);
			return JSON.toJSONString(rb);
		}
	}

    private ResultBean savetorder40(String orid, Long iscenicid,
			Long iticketwinid, Long iemployeeid, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });

		String message = "";
		T_order t_order = null;
		List<T_orderlist> listorder = new ArrayList<T_orderlist>();
		List<T_zorderlist> listzorder = new ArrayList<T_zorderlist>();
		if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("chupiaoT_order", orid, iscenicid);
				ResultBean cano = (ResultBean) objects[0];
				Field[] fields = T_order.class.getDeclaredFields();
				Constructor[] cons = T_order.class.getConstructors();

				if (cano.getRowsCount() > 0) {
					for (int i = 0; i < cano.getRowsCount(); i++) {
						Object[] values = new Object[fields.length];
						for (int j = 0; j < fields.length; j++) {
							values[j] = cano.getResult(i, fields[j].getName());
						}
						for (Constructor<T_order> constructor : cons) {
							try {
								t_order = constructor.newInstance(values);
							} catch (Exception e) {
								LOGGER.error("savetorder40 保存订单接口异常" + e.getMessage());
							}
						}
					}
					if (t_order.getDdzt().equals("11")) {
						message = "订单已出票，无需再次出票";
						rs.addRow(new String[] { "false", message });
						return rs;
					}
					if (!t_order.getIbusinessid().equals("3")) {

						if (!t_order.getDdzt().equals("02")) {
							message = "订单未付款";
							rs.addRow(new String[] { "false", message });
							return rs;
						}
					}
				} else {
					message = "订单不存在";
					rs.addRow(new String[] { "false", message });
					return rs;
				}

				fields = T_orderlist.class.getDeclaredFields();
				cons = T_orderlist.class.getConstructors();
				Object[] objectsList = client.invoke("chupiaoT_orderlist", orid, iscenicid);
				cano = (ResultBean) objectsList[0];
				if (cano.getRowsCount() > 0) {
					for (int i = 0; i < cano.getRowsCount(); i++) {
						Object[] values = new Object[fields.length];
						for (int j = 0; j < fields.length; j++) {
							values[j] = cano.getResult(i, fields[j].getName());
						}
						T_orderlist t1 = null;
						for (Constructor<T_orderlist> constructor : cons) {
							try {
								t1 = constructor.newInstance(values);

							} catch (Exception e) {
							}
						}

						listorder.add(t1);
					}
				} else {
					message = "订单已全部退订";
					rs.addRow(new String[] { "false", message });
					return rs;
				}
				fields = T_zorderlist.class.getDeclaredFields();
				cons = T_zorderlist.class.getConstructors();
				Object[] objectsZOrderList = client.invoke("chupiaoT_zorderlist", orid, iscenicid);
				cano = (ResultBean) objectsZOrderList[0];
				for (int i = 0; i < cano.getRowsCount(); i++) {
					Object[] values = new Object[fields.length];
					for (int j = 0; j < fields.length; j++) {
						values[j] = cano.getResult(i, fields[j].getName());
					}
					T_zorderlist t2 = null;
					for (Constructor<T_zorderlist> constructor : cons) {
						try {
							t2 = constructor.newInstance(values);
							if (Long.parseLong(t2.getTripid()) > 0) {
								// 查看竹筏是否停排
								Object[] objectsProductControl = client.invoke("getProductcontrol",
										new Long(t2.getIztickettypeid()), new Long(t2.getTripid()),
										t2.getDtstartdate().substring(0, 10));
								ResultBean cano1 = (ResultBean) objectsProductControl[0];
								String stats = cano1.getResult(0, "BYSTATE");
								String tripname = cano1.getResult(0, "TRIPNAME");

								if (stats.equals("0")) {
									rs.addRow(new String[] { "false", tripname + "准备停排,要出票请先退订竹筏后,再出票" });
									return rs;
								}
								LOGGER.info("stats=" + stats);
								if (stats.equals("-1")) {
									rs.addRow(new String[] { "false", tripname + "已停排,要出票请先退订竹筏后,再出票" });
									return rs;
								}
							}

						} catch (Exception e) {
						}
						listzorder.add(t2);
					}
				}

			} catch (Exception e) {
				LOGGER.error("连接中心服务器失败，不能读取订单");
				rs.addRow(new String[] { "false", "连接中心服务器失败，不能读取订单" });
				return rs;
			}

			ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil.getBean("saleCenterService");
			Long maxid = saleCenterService.updatevouchersequence();
			try {
				ResultBean rs1 = saleCenterService.savetorder(t_order, listorder, listzorder, iemployeeid, iticketwinid,
						maxid, url);

				return rs1;
			} catch (Exception e) {

				try {
					Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url,
							ServerNameConst.SALESERVICE);
					Object[] objects = client.invoke("updatecphfT_order", t_order.getOrid(),
							new Long(t_order.getIscenicid()));
					ResultBean cano = (ResultBean) objects[0];
				} catch (Exception e4) {
					rs.addRow(new String[] { "false", "出票失败，与管理员联系" });
					return rs;
				}
				rs.addRow(new String[] { "false", "出票失败，与管理员联系" });
				return rs;
			}
		} else {
			Long maxid = saleCenterService.updatevouchersequence();
			try {
				return saleCenterService.savebenditorder(orid, iscenicid, iemployeeid, iticketwinid, maxid);
			} catch (Exception e) {
				rs.addRow(new String[] { "false", e.getMessage() });
				return rs;
			}
		}
	}

    /**
     * *根据服务商,趟次和日期,接待用户usid读取该服务商产品的趟次预留量数据 Describe: p.byisuse=1 数量控制已经启用
     *
     * @param iscenicid
     * @param tripid
     * @param usid
     * @param stdt
     *
     * @return
     * @author yuanchengjun Date:2011-10-27
     */
    public String getReservecontrol(Long itickettypeid, String usid,
                                        String stdt, Long tripid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getReservecontrol", itickettypeid, usid, stdt, tripid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());

                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
        	ResultBean rb = saleCenterService.getReservecontrol(itickettypeid, usid,
                    stdt, tripid);
            return JSON.toJSONString(rb);
        }

    }

    /**
     * 根据票号读取该票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     *            票号
     * @return return:ResultBean Date:2011-11-18
     */
    public String getTicketmesssage(String szticketprintno) {
        // if (szticketprintno.length() != 14) {
        // Pattern p = Pattern.compile("^[0-9]+$");
        // boolean b = p.matcher(szticketprintno.substring(1)).matches();
        // if (b == false) {
        // return null;
        // }
        // }
    	ResultBean rb = saleCenterService.getTicketmesssage(szticketprintno);
    	return JSON.toJSONString(rb);
    }

    /**
     * 根据一张票号读取同一订单中所有票数据 Describe:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:ResultBean Date:2011-11-22
     */
    public String getOrderTicketmesssage(String szticketprintno) {
        
    	ResultBean rb =  saleCenterService.getOrderTicketmesssage(szticketprintno);
    	return JSON.toJSONString(rb);
    }

    /**
     * 根据票号(多票号为&连接),退订子票类,产生退订销售记录 Describe:退订同一个销售单号里面的同一种票(销售日期/竹筏趟次)等数据都相等的票
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @param iztickettypeid
     * @return return:ResultBean Date:2011-11-22
     */
    public String savereturnticket(String szticketprintnos,
                                       String iztickettypeids, Long newiticketwinid, Long iemployeeid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        String message = "";
        String returnstats = "false";
        String[] szticketprintno = szticketprintnos.split("&");// 退订的票号数组
        Stssalesvouchertab s = saleCenterService.getStssalesvouchertabbyprintno(szticketprintno[0]);
        // 根据票号及退订产品读取占用库存值
        // "票号#产品&产品：票号#产品&产品"
        Long isnet = new Long(0);
        if (s.getSzsalesvoucherno().substring(8, 11).equals("000")) {
            isnet = new Long(1);
        }
        // 现金票退订
        String productcontrols = saleCenterService.getreturnticketconsole(
                szticketprintnos, iztickettypeids);
        if (!productcontrols.equals("")) {
            ResultBean cano = null;
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("updatecancelProductcontrol", productcontrols);
				cano = (ResultBean) objects[0];
            } catch (Exception e) {
                message = "保存失败" + ",退订的数量已更新到可售量";
                rs.addRow(new String[] { returnstats, message });
                return JSON.toJSONString(rs);
            }
            if (cano.getResult(0, 0).equals("false")) {
                message = "保存失败" + ",退订的数量已更新到可售量";
                rs.addRow(new String[] { returnstats, message });
                return JSON.toJSONString(rs);
            }
        }
        // 保存到凭证明细表中

        // 网上订单，将本地凭证明细转换成订单明细保存到远程服务器
        if (isnet == 0) {
            // 现金票已经退订完成
            ResultBean rs1 = saleCenterService.savereturnticket(s,
                    szticketprintnos, iztickettypeids, newiticketwinid,
                    iemployeeid);

            return JSON.toJSONString(rs1);
        } else {
            return null;
        }

    }

    /**
     * 根据票号(多票号为&连接),退订子票类,产生退订销售记录 Describe:退订同一个销售单号里面的同一种票(销售日期/竹筏趟次)等数据都相等的票
     * //"票号#产品&产品:票号#产品&产品"
     *
     * @auth:yuanchengjun
     * @param tickets
     * @param iztickettypeid
     * @return return:ResultBean Date:2011-11-22
     */
    public String saveorderreturnticket(String tickets,
                                            Long newiticketwinid, Long iemployeeid, Long isqt, Long forceemid,
                                            String forcenote, Long issx) {
    	String url = WebContant.GetKeyValue("CenterUrl");
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        String message = "";
        String returnstats = "false";
        Long maxid = saleCenterService.updatevouchersequence();
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(newiticketwinid);
        int ticketstation = esbticketwintab.getIticketstationid().intValue();
        String szticketstation = "";
        if (0 < ticketstation && ticketstation < 10) {
            szticketstation = "00" + ticketstation;
        } else if (ticketstation >= 10 && ticketstation < 100) {
            szticketstation = "0" + ticketstation;
        } else {
            szticketstation = "" + ticketstation;
        }
        String szsalesvoucherno = "";
        String productcontrols = "";
        ResultBean cano = null;
        try {
            szsalesvoucherno = saleCenterService.updateMaxNo(szticketstation);
        } catch (SQLException e1) {
            message = "读取最大订单号失败";
            rs.addRow(new String[] { returnstats, message });
            return JSON.toJSONString(rs);
        }

        String[] ticket = tickets.split(":");
        List returnmodellist = new ArrayList();
        Stssalesvouchertab stssalesvouchertab = null;
        for (int i = 0; i < ticket.length; i++) {
            Returnmodel rt = new Returnmodel();
            String[] t1 = ticket[i].split("#");
            rt.setSzticketprintno(t1[0]);
            if (i == 0) {
                stssalesvouchertab = saleCenterService
                        .getStssalesvouchertabbyprintno(t1[0]);
                // //////////如果是易旅宝的订单，则不允许退票////////////////
                if ((stssalesvouchertab.getSzsalesvoucherno().matches(
                        "^\\d{8}999\\d{6}")
                        || stssalesvouchertab.getSzsalesvoucherno().matches(
                        "^\\d{8}888\\d{6}")
                        || stssalesvouchertab.getSzsalesvoucherno().matches(
                        "^\\d{8}777\\d{6}")) && isqt==0) {
                    Sysparv5 sysparv5 = saleCenterService.getSysparv5("OTAT", "****");
                    Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                    try {
                    	Object[] objects = client.invoke("getOrderZffs", stssalesvouchertab.getSzsalesvoucherno());
                    	String rsb = objects[0].toString();
                        if(sysparv5 == null){
                            if (!"05".equalsIgnoreCase(rsb)) {
                                rs.addRow(new String[] { "false",
                                        "非现场支付的易旅宝订单不允许在此退票！" });
                                return JSON.toJSONString(rs);
                            }
                        }else{
                            rs.addRow(new String[] { "false1",
                                    "易旅宝订单退订，需授权退订！" });
                            return JSON.toJSONString(rs);
                        }
                    } catch (Exception e) {
                        e.printStackTrace(System.out);
                        rs.addRow(new String[] { "false", "出现异常无法退票！" });
                        return JSON.toJSONString(rs);
                    }

                }
            }
            rt.setItickets(t1[1]);
            String[] t2 = t1[1].split("&");
            rt.setItickettypeids(t2);
            returnmodellist.add(rt);
        }

        String orid = stssalesvouchertab.getSzsalesvoucherno();
        List plist = new ArrayList();

        ResultBean rs1 = saleCenterService.getreturnticketsconsole(
                returnmodellist, stssalesvouchertab, isqt);

        if (rs1.getResult(0, 0).equals("false")) {
            return JSON.toJSONString(rs1);
        } else if (rs1.getResult(0, 0).equals("false1")) {
        	return JSON.toJSONString(rs1);
        } else {
            productcontrols = rs1.getResult(0, 1);
            if (!orid.substring(8, 11).equals("000") && !orid.substring(8, 11).equals("888") && !orid.substring(8, 11).equals("999")) {
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                        System.out.println("savereturntickets");
                        rs = saleCenterService.savereturntickets(
                                stssalesvouchertab, returnmodellist,
                                esbticketwintab, iemployeeid, szsalesvoucherno,
                                maxid, isqt, forceemid, forcenote, issx,
                                productcontrols,url);
                        return JSON.toJSONString(rs);
                    } catch (Exception e) {
                        if (productcontrols != null
                                || productcontrols.equals("")) {
                            try {
                            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                            	client.invoke("updatefhcancelProductcontrol", productcontrols);
                            } catch (Exception e2) {
                                System.out.println(e2.getMessage());
                            }
                        }
                        System.out.println(e.getMessage());
                        message = "保存失败";
                        rs.addRow(new String[] { returnstats, message });
                        return JSON.toJSONString(rs);
                    }
                } else {

                    try {
                        rs = saleCenterService.savebendireturntickets(
                                stssalesvouchertab, returnmodellist,
                                esbticketwintab, iemployeeid, szsalesvoucherno,
                                maxid, isqt, forceemid, forcenote, issx,
                                productcontrols,url);
                        return JSON.toJSONString(rs);
                    } catch (Exception e) {
                    	LOGGER.info("savebendireturntickets方法异常："+StringUtil.toString_02(e));
                        rs.addRow(new String[] { returnstats, e.getMessage() });
                        return JSON.toJSONString(rs);
                    }
                }
            } else {
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                    	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                    	Object[] objects = client.invoke("savebendireturntickets", 
                                stssalesvouchertab, returnmodellist,
                                productcontrols, esbticketwintab, iemployeeid,
                                szsalesvoucherno, maxid, isqt, forceemid,
                                forcenote, issx,url);
        				rs = (ResultBean) objects[0];
        				return JSON.toJSONString(rs);
                    } catch (Exception e1) {
                        rs.addRow(new String[] { "false", e1.getMessage() });
                        return JSON.toJSONString(rs);
                    }
                } else {
                    try {
                        System.out.println("savebenditorderreturntickets");
                        rs = saleCenterService.savebenditorderreturntickets(
                                stssalesvouchertab, returnmodellist,
                                productcontrols, esbticketwintab, iemployeeid,
                                szsalesvoucherno, maxid, isqt, forceemid,
                                forcenote, issx,url);
                        return JSON.toJSONString(rs);
                    } catch (Exception e1) {
                        rs.addRow(new String[] { "false", e1.getMessage() });
                        return JSON.toJSONString(rs);
                    }

                }
            }
        }

    }

    /**
     * 根据退订凭证ID+售票点ID 读取退票信息 Describe:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     *             return:ResultBean 列数:3 1：mont 退订金额 2 settlementid 退款类别 3
     *             message 信息: 票号,票名,单价,金额,手续费&子票名称,开始时间,截至时间,实际售价
     *             ,数量,金额,手续费#子票名称,开始时间,截至时间,
     *             实际售价,数量,金额,手续费@票号,票名,单价,金额,手续费&子票名称,开始时间,
     *             截至时间,实际售价,数量,金额,手续费#子票名称,开始时间,截至时间,实际售价,数量,金额,手续费
     *             AWYA150008G6CB,团队三联票,100.0,100.0,40.0&竹筏票,2011-12-02
     *             17:00:00,2011-12-02
     *             17:30:00,100.0,1,100.0,40.0@AWYA150008G729,团队三联票
     *             ,100.0,100.0,40.0&竹筏票,2011-12-02 17:00:00,2011-12-02
     *             17:30:00,100.0,1,100.0,40.0 Date:2011-12-2
     */
    public String getStssalesvouchertab(Long isalesvoucherid,Long iticketstationid) throws IllegalAccessException,
            InvocationTargetException {
        try {
        	ResultBean rs = saleCenterService.getStssalesvouchertab(isalesvoucherid,
                    iticketstationid);
           return JSON.toJSONString(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 售票员登录
     * @param userid
     * @param password
     * @return -1 无效用户 -2 所属公司未绑定服务商 -3 你所在公司不能在该售票窗口售票 -4 密码错误 -5
     *         今日您已经登陆错误次数超过5次 0 您不具有售票权限，不能登陆 1 登陆成功
     */

    public String emplogin(Long iscenicid, String userid, String password)
            throws Exception {
        // adminlogin a=new adminlogin();

//        boolean flag = adminlogin.isExpritdate();
//
//        if (flag) {
//            ResultBean rs = new ResultBean();
//            rs.setColumnCount(2);
//            rs.setColumnNames(new String[] { "returnstats", "message" });
//            rs.addRow(new String[] { "false",
//                    "有效期：" + adminlogin.getExpritdate() + ",软件过期" });
//            return rs;
//        }
//        ResultBean rs = saleCenterService.emplogin(iscenicid, userid, password);
//        return JSON.toJSONString(rs);
	    
//	    ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
//		                .getBean("saleCenterService");
	    
		        ResultBean rs = saleCenterService.emplogin(iscenicid, userid, password);
		        Vector selectResult = rs.getSelectResult();
		        ArrayList<String> object = (ArrayList<String>) selectResult.get(0);
		        String returnstats = object.get(0);
		        if (returnstats.equals("false")) {
		            return JSON.toJSONString(rs);
		        } else {
		            return JSON.toJSONString(saleCenterService.getEmployee(userid));
		        }
    }

    public String getDayTimes() {

        return Tools.getDayTimes();
    }

    /**
     * 票务重新打印 Describe:
     *
     * @auth:yuanchengjun
     * @param szticketprintnos
     * @return return:ResultBean Date:2011-12-6
     */
    public String ticketreprint(String iserialnums, Long iscenicid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        String[] szticket = iserialnums.split(",");
        List list = new ArrayList();
        for (int i = 0; i < szticket.length; i++) {
            System.out.println("szticket[i]=" + szticket[i]);
            Stssoldtickettab st = saleCenterService
                    .queryStssoldtickettab(szticket[i]);
            Long iserialnum = new Long(0);
            if (st == null) {
            	
                return reprintbyorid(szticket[i], iscenicid,url);
				/*
				 * ResultBean rs = new ResultBean(); rs.setColumnCount(2);
				 * rs.setColumnNames(new String[] { "returnstats", "message" });
				 * rs.addRow(new String[] { "false", szticket[i] + "没有票务数据" });
				 * return rs;
				 */
            } else {
                iserialnum = st.getIserialnum();
            }
            String ornm = "";
            String szregionalname = "";
            String corpname = "";
            List list1 = saleCenterService.ticketreprint(iserialnum, st.getId()
                    .getIticketstationid());
            if (list1 == null || list1.size() == 0) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", szticket[i] + "没有票务数据" });
                return JSON.toJSONString(rs);
            } else {
                Map map = (Map) list1.get(0);
                String zdail = map.get("ZDAIL").toString();
                if (zdail.equals("")) {
                    ResultBean rs = new ResultBean();
                    rs.setColumnCount(2);
                    rs.setColumnNames(new String[] { "returnstats", "message" });
                    rs.addRow(new String[] { "false", szticket[i] + "已全部退订" });
                    return JSON.toJSONString(rs);
                }
                String szsalesvoucherno = map.get("SZSALESVOUCHERNO")
                        .toString();
                if (szsalesvoucherno.substring(8, 11).equals("000")) {
                    if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                        try {
                        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            				Object[] objects = client.invoke("getT_orderbyorid", szsalesvoucherno,
                                    iscenicid);
            				ResultBean cano = (ResultBean) objects[0];

                            if (cano.getRowsCount() > 0) {
                                ornm = cano.getResult(0, "ORNM");
                                szregionalname = cano.getResult(0,
                                        "SZREGIONALNAME");
                                corpname = cano.getResult(0, "CORPNAME");
                            }
                        } catch (Exception e1) {

                        }
                    } else {
                        ResultBean cano = saleCenterService.getT_orderbyorid(
                                szsalesvoucherno, iscenicid);
                        if (cano.getRowsCount() > 0) {
                            ornm = cano.getResult(0, "ORNM");
                            szregionalname = cano
                                    .getResult(0, "SZREGIONALNAME");
                            corpname = cano.getResult(0, "CORPNAME");
                        }
                    }
                }
                if (!ornm.equals("")) {
                    map.put("ORNM", ornm);
                }
                if (!szregionalname.equals("")) {
                    map.put("SZREGIONALNAME", szregionalname);
                }
                if (!corpname.equals("")) {
                    map.put("CORPNAME", corpname);
                }
                list.add(map);
            }
        }
        ResultBean rs = MapToResultBean.toResultBean(list);
        return JSON.toJSONString(rs);
    }

    public String getcancelprint(Long isalesvoucherid, Long iticketstationid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        String ornm = "";
        String szregionalname = "";
        String corpname = "";
        // 读取退票凭证
        Stssalesvouchertab ts = saleCenterService.queryStssalesvouchertab(
                isalesvoucherid, iticketstationid);

        Stssalesvouchertab ys = saleCenterService.queryStssalesvouchertab(
                ts.getIssalesvoucherid(), ts.getIsticketstationid());
        List<Map> list = saleCenterService.getcancelprint(ts, ys);
        String szsalesvoucherno = ys.getSzsalesvoucherno();

        if (szsalesvoucherno.substring(8, 11).equals("000")) {
            if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
    				Object[] objects = client.invoke("getT_orderbyorid", szsalesvoucherno,
                            ys.getIscenicid());
    				ResultBean cano = (ResultBean) objects[0];

                    if (cano.getRowsCount() > 0) {
                        ornm = cano.getResult(0, "ORNM");
                        szregionalname = cano.getResult(0, "SZREGIONALNAME");
                        corpname = cano.getResult(0, "CORPNAME");
                    } else {
                        ornm = "";
                        szregionalname = "";
                        corpname = "";
                    }
                } catch (Exception e1) {
                    ornm = "";
                    szregionalname = "";
                    corpname = "";
                }
            } else {
                ResultBean cano = saleCenterService.getT_orderbyorid(
                        szsalesvoucherno, ys.getIscenicid());
                if (cano.getRowsCount() > 0) {
                    ornm = cano.getResult(0, "ORNM");
                    szregionalname = cano.getResult(0, "SZREGIONALNAME");
                    corpname = cano.getResult(0, "CORPNAME");
                } else {
                    ornm = "";
                    szregionalname = "";
                    corpname = "";
                }
            }
        }
        for (Map map : list) {
            if (!ornm.equals("")) {
                map.put("ORNM", ornm);
            }
            if (!szregionalname.equals("")) {
                map.put("SZREGIONALNAME", szregionalname);
            }
            if (!corpname.equals("")) {
                map.put("CORPNAME", corpname);
            }
        }
        ResultBean rs = MapToResultBean.toResultBean(list);
         return JSON.toJSONString(rs);
    }

    /**
     * message itickettypeid,icrowdkindpriceid,pric,dtstartdate,dtenddate#
     * iztickettypeid ,znumb,dtstartdate,dtenddate&
     * iztickettypeid,znumb,dtstartdate,dtenddate,tripid
     * &iztickettypeid,znumb,dtstartdate,dtenddate,tripid
     *
     * @itickettypeid,icrowdkindpriceid,pric,dtstartdate,dtenddate#iztickettypeid,znumb,dtstartdate,dtenddate,tripid&iztickettypeid,znumb,dtstartdate,dtenddate,tripid&iztickettypeid,znumb,dtstartdate,dtenddate,tripid
     *                                                                                                                                                                                                                   Describe
     *                                                                                                                                                                                                                   :
     * @auth:yuanchengjun
     * @param orid
     * @param iscenicid
     * @param mont
     *            新订单总金额
     * @param newiticketwinid
     * @param iemployeeid
     * @param message
     * @return return:ResultBean Date:2011-12-13
     */

    public String cancelt_order(String orid, Long iscenicid, Double mont,
                                    Long newiticketwinid, Long iemployeeid, String message, Long isqt,
                                    Long forceemid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (orid.matches("^\\d{8}999\\d{6}")
                || orid.matches("^\\d{8}888\\d{6}")) {
        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            try {
				Object[] objects = client.invoke("getOrderZffs", orid);
                String rsb = objects[0].toString();
                if (!"05".equalsIgnoreCase(rsb)) {
                    ResultBean rb = new ResultBean();
                    rb.setColumnCount(2);
                    rb.setColumnNames(new String[] { "returnstats", "message" });
                    rb.addRow(new String[] { "false", "非现场支付的易旅宝订单不允许修改！" });
                    return JSON.toJSONString(rb);
                }
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                rb.addRow(new String[] { "false", "出现异常,无法修改!" });
                return JSON.toJSONString(rb);
            }
        }
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(newiticketwinid);
        int ticketstation = esbticketwintab.getIticketstationid().intValue();
        String szticketstation = "";
        if (0 < ticketstation && ticketstation < 10) {
            szticketstation = "00" + ticketstation;
        } else if (ticketstation >= 10 && ticketstation < 100) {
            szticketstation = "0" + ticketstation;
        } else {
            szticketstation = "" + ticketstation;
        }
        String szsalesvoucherno = "";
        try {
            szsalesvoucherno = saleCenterService.updateMaxNo(szticketstation);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            	Object[] objects = client.invoke("cancelt_order", orid, iscenicid, mont, iemployeeid,
                        szsalesvoucherno, message, isqt, forceemid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                            System.out.println("values[j]=" + values[j]);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            try {
                ResultBean rb = saleCenterService.savecancelt_order(orid,
                        iscenicid, mont, iemployeeid, szsalesvoucherno,
                        message, isqt, forceemid,url);
                System.out.println("cancelt_order2");
                try {
                    saleCenterService.addOrderLog(szsalesvoucherno, iscenicid,
                            iemployeeid, orid);
                } catch (Exception e) {
                    LOGGER.error("---->>订单窗口修改日志写入失败" + e.getMessage());
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        }

    }

    /**
     * 网上订单出票密码控制 Describe:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getPasswordcontrol() {
        return WebContant.GetKeyValue("Passwordcontrol");

    }

    /**
     * 停排退订是否能出票口退订:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String gettptd() {
        return WebContant.GetKeyValue("tptd");

    }

    /**
     * 出票口是否可以退换票:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getcptd() {
        return WebContant.GetKeyValue("cptd");

    }

    /**
     * 出票口是否可以过期退订:
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getgqptd() {
        return WebContant.GetKeyValue("gqtd");

    }

    /**
     * 读取用户预付款
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:ResultBean Date:2011-11-7
     */
    public double getsumjifen(String usid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        float yfk = 0;
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
                Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getsumjifen", usid);
				yfk = Float.valueOf(objects[0].toString());
            } catch (Exception e) {
                LOGGER.error("获取用户积分异常："+StringUtil.toString_02(e));
            }
        } else {
        	
            yfk = saleCenterService.getsumjifen(usid);
        }
        return yfk;
    }

    public String cancelstopraftorder(String orid, Long iscenicid,
                                          Long newiticketwinid, Long iemployeeid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("tptd").equals("0")) {
            ResultBean rs = new ResultBean();
            rs.setColumnCount(2);
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "false", "系统不允许系统直接退订停排订单" });
        }
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(newiticketwinid);
        int ticketstation = esbticketwintab.getIticketstationid().intValue();
        String szticketstation = "";
        if (0 < ticketstation && ticketstation < 10) {
            szticketstation = "00" + ticketstation;
        } else if (ticketstation >= 10 && ticketstation < 100) {
            szticketstation = "0" + ticketstation;
        } else {
            szticketstation = "" + ticketstation;
        }
        String szsalesvoucherno = "";
        try {
            szsalesvoucherno = saleCenterService.updateMaxNo(szticketstation);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {

            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("cancelstopraftorder", orid, iscenicid, szsalesvoucherno,
                        iemployeeid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", e.getMessage() });
            }
        } else {
            try {
                ResultBean cano = saleCenterService.savecancelstopraftorder(
                        orid, iscenicid, szsalesvoucherno, iemployeeid);
                return JSON.toJSONString(cano);
            } catch (Exception e) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rs);
            }
        }
        return null;

    }

    /**
     * 根据售票员读取日期段的销售记录 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @param stdt
     * @param eddt
     * @return return:ResultBean Date:2011-12-31
     */
    public String getStssalesvouchertablist(Long iemployeeid, String stdt,
                                                String eddt) {
        
    	ResultBean rs = saleCenterService.getStssalesvouchertablist(iemployeeid, stdt,
                eddt);
        return JSON.toJSONString(rs);
    }

    /**
     * 重打印时根据售票凭证id和售票点读取所有票信息:
     *
     * @auth:yuanchengjun
     * @param isalesvoucherid
     * @param iticketstationid
     * @return return:ResultBean Date:2011-12-6
     */
    public String resoldticketlist(Long isalesvoucherid,
                                       Long iticketstationid, String szsalesvoucherno, Long iscenicid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        String ornm = "";
        String szregionalname = "";
        String corpname = "";
        if (szsalesvoucherno.substring(8, 11).equals("000")) {
            if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
    				Object[] objects = client.invoke("getT_orderbyorid", szsalesvoucherno, iscenicid);
    				ResultBean cano = (ResultBean) objects[0];
                    if (cano.getRowsCount() > 0) {
                        ornm = cano.getResult(0, "ORNM");
                        szregionalname = cano.getResult(0, "SZREGIONALNAME");
                        corpname = cano.getResult(0, "CORPNAME");
                    } else {
                        ornm = "";
                        szregionalname = "";
                        corpname = "";
                    }
                } catch (Exception e1) {
                    ornm = "";
                    szregionalname = "";
                    corpname = "";
                }
            } else {
                ResultBean rs = saleCenterService.getT_orderbyorid(
                        szsalesvoucherno, iscenicid);
                if (rs.getRowsCount() > 0) {
                    ornm = rs.getResult(0, "ORNM");
                    szregionalname = rs.getResult(0, "SZREGIONALNAME");
                    corpname = rs.getResult(0, "CORPNAME");
                } else {
                    ornm = "";
                    szregionalname = "";
                    corpname = "";
                }
            }
        }

        ResultBean rs = saleCenterService.resoldticketlist(isalesvoucherid,
                iticketstationid, ornm, szregionalname, corpname);
        return JSON.toJSONString(rs);

    }

    /**
     * 出票口注册售票点 Describe:
     *
     * @auth:yuanchengjun
     * @param mac
     * @param iscenicid
     * @return return:ResultBean Date:2012-1-7
     */
    public String zhuceticketwin(String mac, String ip, Long iscenicid) {
    	String url = WebContant.GetKeyValue("CenterUrl");
        ResultBean rs = new ResultBean();

        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            rs.addRow(new String[] { "false", "本地服务器不可注册窗口" });
            return JSON.toJSONString(rs);
        }
        Esbticketwintab etw = saleCenterService.getEsbticketwintabBymac(
                iscenicid, mac);
        // 根据MAC地址查询是否有该MAC注册后的地址
        if (etw != null) {
            rs.addRow(new String[] { "false", "该地址已注册售票窗口" });
            return JSON.toJSONString(rs);
        } else {
            etw = new Esbticketwintab();
            etw.setByisuse(new Long(0));
            etw.setIscenicid(iscenicid);
            etw.setSzipaddress(mac.toUpperCase());
            etw.setIpaddress(ip);
            etw.setDtsellstart("08:00");
            etw.setDtsellend("18:00");
            // 根据景点编号读取售票点
            List ist = saleCenterService.getEsbticketstationtabByiscenicid(
                    iscenicid, new Long(1));
            if (ist == null || ist.size() == 0) {
                rs.addRow(new String[] { "false", "请先添加该服务商售票点" });
                return JSON.toJSONString(rs);
            }
            Esbticketstationtab esb = (Esbticketstationtab) ist.get(0);
            etw.setIticketstationid(esb.getIticketstationid());
            etw.setBywintype("0002");
            etw.setIversion(new Long(57));
            etw.setSzticketwincode("1");
            etw.setSzticketwinname("1");
            etw.setSzmemo("1");
            etw.setIsty(0L);
            etw.setIticketwinid(new Long(0));
            Long iticketwinid = saleCenterService.savezhuceticketwin(etw);
            if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
    				client.invoke("zhuceticketwin", new Long(0), etw.getIticketstationid(),
                            iscenicid, etw.getSzticketwincode(),
                            etw.getSzticketwinname(), etw.getSzipaddress(),
                            etw.getDtsellstart(), etw.getDtsellend(),
                            etw.getBywintype(), etw.getByisuse(),
                            etw.getSzmemo(), etw.getIversion(),
                            etw.getIpaddress());
                } catch (Exception e1) {
                    LOGGER.error("注册窗口异常："+StringUtil.toString_02(e1));
                }

            }
            rs.addRow(new String[] { "true", iticketwinid.toString() });
            return JSON.toJSONString(rs);
        }

    }

    /**
     * 取服务器上是FTP配置程序
     */

    public String getFtp(String iticketstationid) {
        
    	ResultBean rs = saleCenterService.getFtp(iticketstationid);
        return JSON.toJSONString(rs);
    }

    public String getForce() {
        
    	ResultBean rs = saleCenterService.getForce();
        return JSON.toJSONString(rs);
    }

    /**
     * 根据票号读取 票信息和检票信息:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:ResultBean Date:2011-11-22
     */
    public String getcheckTicketmesssage(String szticketprintno,String url) {
    	ResultBean rs = saleCenterService.getcheckTicketmesssage(szticketprintno,url);
    	return JSON.toJSONString(rs);
    }

    /**
     * 根据票号读取 票信息和检票信息:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:ResultBean Date:2011-11-22
     */
    public String getoridbyticketprintno(String szticketprintno) {

        
        return saleCenterService.getoridbyticketprintno(szticketprintno);
    }

    /**
     * 根据票号,订单号，打印模式更新打印表: Describe:
     *
     * @auth:yuanchengjun
     * @param szsalesvoucherno
     * @param szticketprintno
     * @param printtype
     *            当printtype="03" 时 不用传 szsalesvoucherno
     * @return return:int Date:2012-3-21
     */
    public int updateprintbyprintnoAndnewprintno(String szsalesvoucherno,
                                    String szticketprintno, String printtype, Long iemployeeid,String newprintno) {
        
        return saleCenterService.updateprintbyprintno(szsalesvoucherno,
                szticketprintno, printtype, iemployeeid,newprintno);
    }
    public int updateprintbyprintno(String szsalesvoucherno,
                                    String szticketprintno, String printtype, Long iemployeeid) {
        
        return saleCenterService.updateprintbyprintno(szsalesvoucherno,
                szticketprintno, printtype, iemployeeid,szticketprintno);
    }

    public String updateprintbyprintno2(String szsalesvoucherno,
                                            String szticketprintno, String printtype, Long iemployeeid,String newprintno) {
        
        ResultBean rs = new ResultBean();

        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        saleCenterService.updateprintbyprintno(szsalesvoucherno,
                szticketprintno, printtype, iemployeeid,newprintno);
        rs.addRow(new String[] {"true","1"});
        return JSON.toJSONString(rs);
    }

    /**
     * prcs Describe:
     *
     * @auth:yuanchengjun
     * @param
     * @param
     * @return return:int Date:2012-3-21
     */
    public String getsysparcs(String pmky, String pmcd) {

        
        ResultBean rs = saleCenterService.getsysparcs(pmky, pmcd);
        return JSON.toJSONString(rs);
    }

    /**
     * 登录后根据ticketprintlist中读取未能打印完成的打印任务显示出来 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @return return:ResultBean Date:2012-3-21
     */
    public String querynotprint(Long iemployeeid,String url) {
        
        ResultBean rs = saleCenterService.querynotprint(iemployeeid,url);
        return JSON.toJSONString(rs);
    }

    /**
     * 提取系统版本号
     *
     * @auth:yuanchengjun
     * @return return:String Date:2011-12-12
     */
    public String getclientversion() {
        return WebContant.GetKeyValue("clientversion");
    }

    /**
     * 读取在WebContant控制的数据 Describe:
     *
     * @auth:yuanchengjun
     * @return return:ResultBean Date:2012-3-27
     */
    public String getWebContant() {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(6);
        rs.setColumnNames(new String[]{"DayTimes","gqtd", "clientversion", "cptd",
                "tptd", "Passwordcontrol"});
        rs.addRow(new String[]{Tools.getDayTimes(),WebContant.GetKeyValue("gqtd"),
                WebContant.GetKeyValue("clientversion"),
                WebContant.GetKeyValue("cptd"), WebContant.GetKeyValue("tptd"),
                WebContant.GetKeyValue("Passwordcontrol")});
        return JSON.toJSONString(rs);
    }

    /**
     * 凭证保证
     *
     * @param salesvouchers
     *            凭证主表
     * @param salesvoucherdetails
     *            凭证 细
     * @param comticketsalesdetails
     *            检票明细
     * @param productcontrols
     *            打印制制
     * @param version
     *            版本
     * @param param
     *            参数
     * @return
     */
    public String saveorder(String salesvouchers,
                                String salesvoucherdetails, String comticketsalesdetails,
                                String productcontrols, String version,String url, String... param) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        Debug.print("version:" + version);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        try {
            if (version.equals("4.0")) {
            	rs = this.saveorder40(salesvouchers, salesvoucherdetails,
                        comticketsalesdetails, productcontrols,url);
            	return JSON.toJSONString(rs);
            } else if (version.equals("4.1")) {

                System.out.println("41");
                return this.saveorder41(salesvouchers, salesvoucherdetails,
                        comticketsalesdetails, productcontrols,url, param);

            } else if (version.equals("4.2")) {
                System.out.println("42");
                ResultBean saveorder42 = this.saveorder42(salesvouchers, salesvoucherdetails,
                        comticketsalesdetails, productcontrols,url, param);
                if(saveorder42.getRowsCount()==2){          //if("false".equals(saveorder42.getResult(0, 0))) {
                	 String[] comticketsalesdetail = comticketsalesdetails.split(":");
                     for (int i = 0; i < comticketsalesdetail.length; i++) {
                         String[] zdetail = comticketsalesdetail[i].split("&");
                         Integer isplitamount = new Integer(zdetail[4]);
                         if(zdetail.length == 14) {
                         	//更新分时时段库存
                         	//TimeSharingTicketTab
                        	 Long timeId = Long.parseLong(zdetail[12]);
                         	String productId = zdetail[13];
                         	ITimeSharingService timeSharingService = (ITimeSharingService) SpringUtil.getBean("timeSharingService");
                        	timeSharingService.UpdateStock(timeId, productId, isplitamount, "add");
                         }
                     }
                }
                return JSON.toJSONString(saveorder42);
                

            } else if(version.equals("99")){

            }
        } catch (Exception e) {
        	LOGGER.info(StringUtil.toString_02(e));
            rs.addRow(new String[] { "fasle", "保存存失败" });

        }
        return JSON.toJSONString(rs);
    }

    public String savetorder(String orid, Long iscenicid,
                                 Long iticketwinid, Long iemployeeid, String version,String url,
			String... param) {
		/*if (url == null || url.length() < 2) {
			url = WebContant.GetKeyValue("CenterUrl");
		}
		LOGGER.info("网上订单，窗口取票接口");
		ResultBean rs = new ResultBean();
		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		// ///////////////////////易旅宝////////////////////////////////////
		CYTPojo pojo = new CYTPojo();
		try {

			if (orid.matches("^\\d{8}888\\d{6}") || orid.matches("^\\d{8}999\\d{6}")) {// 核销

				pojo = commonUtil.findCYTPojo(orid, url);

				if (orid.matches("^\\d{8}888\\d{6}")) {
					Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url,
							ServerNameConst.SALESERVICE);
					Object[] objects = client.invoke("cythexiao", orid);
					ResultBean cano = (ResultBean) objects[0];
					if (cano == null || !"true".equalsIgnoreCase(cano.getResult(0, "returnstats"))) {
						rs.addRow(new String[] { "false", cano.getResult(0, "message") });
						return JSON.toJSONString(rs);
					}
				}
			}
				// //////////////////////////////////////////////////////////////////////////////////////////
				if (version.equals("4.0")) {
					rs = this.savetorder41(orid, iscenicid, iticketwinid, iemployeeid, "", "", url);
					// return this.savetorder40(orid, iscenicid, iticketwinid,
					// iemployeeid);
				} else if (version.equals("4.1")) {

					// param[0]=orderlistid,szticketprintno#orderlistid,szticketprintno
					ISaleCheckupService saleCheckupService = (ISaleCheckupService) SpringUtil
							.getBean("saleCheckupService");
					Esbscenicareatab esbscenic = saleCheckupService.getEsbscenicidshowup(iscenicid);

					if (param == null) {
						System.out.println("r3");
						if (esbscenic.getScenictype() != null && esbscenic.getScenictype().equals("01")) {
							rs = this.savetorder41(orid, iscenicid, iticketwinid, iemployeeid, "", "", url);
						} else {
							ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
									.getBean("saleCenterService");
							Long maxid = saleCenterService.updatevouchersequence();

							rs = saleCheckupService.savehoteltorder(orid, iscenicid, iemployeeid, iticketwinid, maxid,
									param[0]);
						}

					} else {
						System.out.println("rx");
						if (esbscenic.getScenictype() != null && esbscenic.getScenictype().equals("01")) {
							if (param.length > 1) {
								rs = this.savetorder41(orid, iscenicid, iticketwinid, iemployeeid, param[0], param[1],
										url);
							} else {
								rs = this.savetorder41(orid, iscenicid, iticketwinid, iemployeeid, param[0], "", url);
							}

						} else {
							ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
									.getBean("saleCenterService");
							Long maxid = saleCenterService.updatevouchersequence();

							rs = saleCheckupService.savehoteltorder(orid, iscenicid, iemployeeid, iticketwinid, maxid,
									param[0]);
						}

					}

				} else if (version.equals("4.2")) {

					// param[0]=orderlistid,szticketprintno#orderlistid,szticketprintno
					LOGGER.info("客户端当前版本："+version);
					Esbscenicareatab esbscenic = saleCheckupService.getEsbscenicidshowup(iscenicid);
					if (param == null) {
						if (esbscenic.getScenictype() != null && esbscenic.getScenictype().equals("01")) {
							rs = this.savetorder42(orid, iscenicid, iticketwinid, iemployeeid, "", "", url);
						} else {
							Long maxid = saleCenterService.updatevouchersequence();
							rs = saleCheckupService.savehoteltorder(orid, iscenicid, iemployeeid, iticketwinid, maxid,param[0]);
						}

					} else {
						if (esbscenic.getScenictype() != null && esbscenic.getScenictype().equals("01")) {

							if (param.length > 1) {

								rs = this.savetorder42(orid, iscenicid, iticketwinid,iemployeeid, param[0], param[1], url);
							} else {
								rs = this.savetorder42(orid, iscenicid, iticketwinid, iemployeeid, param[0], "", url);
								return JSON.toJSONString(rs);

							}
						} else {
							Long maxid = saleCenterService.updatevouchersequence();
							rs = saleCheckupService.savehoteltorder(orid, iscenicid, iemployeeid, iticketwinid, maxid,param[0]);
						}

					}

				}
		} catch (Exception e) {
			LOGGER.error("网上订单线下出票接口异常："+StringUtil.toString_02(e));
			rs.addRow(new String[] { "false", "保存失败" });
		}
		// /////////////////////易旅宝专用//////////////////////////////////////////////////
		if ("true".equalsIgnoreCase(rs.getResult(0, "returnstats")) && pojo.isCYT) {
			try {
				checkService.addCYTPojo(pojo);
			} catch (Exception e) {
				LOGGER.info("保存畅游通订单信息异常："+StringUtil.toString_02(e));
			}
		}
		return JSON.toJSONString(rs);*/
    	///////////////////////////

    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });

        String message = "";
        Map m = new HashMap();
        List printnolist = new ArrayList();// 激活票号list
        CYTPojo pojo = new CYTPojo();
        try {/*
            if (orid.matches("^\\d{8}888\\d{6}")
                    || orid.matches("^\\d{8}999\\d{6}")) {// 核销

                pojo = CommonUtil.findCYTPojo(orid,url);

                if (orid.matches("^\\d{8}888\\d{6}")) {
                    javax.xml.rpc.Service service = null;
                    java.net.URL endpointURL = new java.net.URL("http://"
                            + url
                            + "/services/centersaleService?wsdl");
                    CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                            endpointURL, service);
                    ssl.setMaintainSession(true);
                    com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                            .cythexiao(orid);
                    if (cano == null
                            || !"true".equalsIgnoreCase(cano.getResult(0,
                            "returnstats"))) {
                        rs.addRow(new String[] { "false",
                                cano.getResult(0, "message") });
                        return rs;
                    }
                }
            }
        */
//        	return null;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            rs.addRow(new String[] { "false", "保存存失败" });
        }
        Long maxid = saleCenterService.updatevouchersequence();
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {

            T_order t_order = null;
            List<T_orderlist> listorder = new ArrayList<T_orderlist>();
            List<T_zorderlist> listzorder = new ArrayList<T_zorderlist>();
            List<Trealname> Trealnamelist = new ArrayList<Trealname>();
            try {/*

                javax.xml.rpc.Service service = null;
                java.net.URL endpointURL = new java.net.URL("http://"
                        + url
                        + "/services/centersaleService?wsdl");
                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                        endpointURL, service);
                ssl.setMaintainSession(true);
                com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                        .chupiaoT_order(orid, iscenicid);
                System.out.println("saleautoservice4.5");
                if (cano.getRowsCount() > 0) {
                    System.out.println("saleautoservice4.6");
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        System.out.println("saleautoservice4.7");
                        Map returnMap = new HashMap();
                        String[] ss = cano.getColumnNames();
                        for (int j = 0; j < ss.length; j++) {
                            returnMap.put(ss[j].toLowerCase(),
                                    cano.getResult(i, ss[j]));

                        }
                        System.out.println("saleautoservice4.8");
                        t_order = (T_order) SaleCenterService.convertMap(
                                T_order.class, returnMap);
                        System.out.println("saleautoservice4.9");
                        if (t_order.getDdzt().equals("11")) {
                            message = "订单已出票，无需再次出票";
                            rs.addRow(new String[] { "false", message });
                            return rs;
                        }
                        if (t_order.getDdzt().equals("00")) {
                            message = "订单未支付，不能出票";
                            rs.addRow(new String[] { "false", message });
                            return rs;
                        }
                    }
                } else {
                    message = "订单不存在";
                    rs.addRow(new String[] { "false", message });
                    return rs;
                }
                System.out.println("saleautoservice5");
                cano = ssl.chupiaoT_orderlist(orid, iscenicid);
                if (cano.getRowsCount() > 0) {
                    for (int i = 0; i < cano.getRowsCount(); i++) {

                        Map returnMap = new HashMap();
                        String[] ss = cano.getColumnNames();
                        for (int j = 0; j < ss.length; j++) {
                            returnMap.put(ss[j].toLowerCase(),
                                    cano.getResult(i, ss[j]));

                        }
                        T_orderlist t1 = (T_orderlist) SaleCenterService
                                .convertMap(T_orderlist.class, returnMap);
                        Edmtickettypetab eticket = saleCenterService
                                .getEdmtickettypetab(new Long(t1
                                        .getItickettypeid()));
                        t1.setBymaketicketway(eticket.getBymaketicketway());
						
						 * if (eticket.getBymaketicketway().equals("01")) { if
						 * (eticket.getBymediatype().equals("00") ||
						 * eticket.getBymediatype().equals("01")) { boolean b =
						 * false; for (int k = 0; k < printnolist.size(); k++) {
						 * TOrderlist to = (TOrderlist) printnolist .get(k); if
						 * (to.getOrderlistid().equals( t1.getOrderlistid())) {
						 * t1.setSzticketprintno(to.getOrid()); b = true; } } if
						 * (!b) { rs.addRow(new String[] { "false",
						 * "现场激活票必须填写初始票号" }); return rs; } } }
						 
                        listorder.add(t1);

                    }
                } else {
                    message = "订单明细不存在";
                    rs.addRow(new String[] { "false", message });
                    return rs;
                }

                cano = ssl.chupiaoT_zorderlist(orid, iscenicid);

                if (cano.getRowsCount() > 0) {
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        Map returnMap = new HashMap();
                        String[] ss = cano.getColumnNames();
                        for (int j = 0; j < ss.length; j++) {

                            returnMap.put(ss[j].toLowerCase(),
                                    cano.getResult(i, ss[j]));
                        }
                        T_zorderlist t2 = (T_zorderlist) SaleCenterService
                                .convertMap(T_zorderlist.class, returnMap);

                        listzorder.add(t2);
                    }
                } else {
                    message = "订单子明细不存在";
                    rs.addRow(new String[] { "false", message });
                    return rs;
                }
                System.out.println("saleautoservice7");
                cano = ssl.chupiaoTRealname(orid, iscenicid);
                if (cano.getRowsCount() > 0) {
                    System.out.println("saleautoservice7.1");
                    System.out.println("cano.getRowsCount()="
                            + cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        System.out.println("i=" + i);
                        Map returnMap = new HashMap();
                        String[] ss = cano.getColumnNames();
                        for (int j = 0; j < ss.length; j++) {

                            System.out.println(ss[j].toLowerCase());
                            // System.out.println(cano.getResult(i, ss[j]));
                            returnMap.put(ss[j].toLowerCase(),
                                    cano.getResult(i, ss[j]));
                        }
                        System.out.println("aaaaaaaaaaaa");
                        Trealname tr = (Trealname) SaleCenterService
                                .convertMap(Trealname.class, returnMap);
                        System.out.println("i=" + i);
                        Trealnamelist.add(tr);
                    }
                }
            */
            	} catch (Exception e) {
                System.out.print(e);
                rs.addRow(new String[] { "false", "连接中心服务器失败，不能读取订单" });
                return JSON.toJSONString(rs);
            }

            try {
                ResultBean rs1 = saleAutoService.savetorder(t_order, listorder,
                        listzorder, new Long(iemployeeid), new Long(
                                iticketwinid), new Long(maxid), Trealnamelist);

                if (rs1.getResult(0, 0).equals("false")) {
                    rs.addRow(new String[] { rs1.getResult(0, 0),
                            rs1.getResult(0, 1) });
                    return JSON.toJSONString(rs);
                } else {
                    System.out.println("savetorder1");
                    if (pojo.isCYT) {
                        checkService.addCYTPojo(pojo);
                    }
                    try {/*
                        javax.xml.rpc.Service service = null;
                        java.net.URL endpointURL = new java.net.URL("http://"
                                + url
                                + "/services/centersaleService?wsdl");

                        CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                                endpointURL, service);
                        ssl.setMaintainSession(true);
                        com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                                .updateT_order(orid, iscenicid, iemployeeid,
                                        new Double(t_order.getZfmont()));
                        if (cano.getResult(0, 0).equals("false")) {
                            try {
                                javax.xml.rpc.Service service1 = null;
                                java.net.URL endpointURL1 = new java.net.URL(
                                        "http://"
                                                + url
                                                + "/services/centersaleService?wsdl");
                                CentersaleServiceSoapBindingStub ss2 = new CentersaleServiceSoapBindingStub(
                                        endpointURL1, service1);
                                ssl.setMaintainSession(true);
                                com.ectrip.ticket.centersale.client.ResultBean cano2 = ss2
                                        .updatecphfT_order(orid, iscenicid);

                                rs.addRow(new String[] { "false",
                                        "保存失败," + cano.getResult(0, 1) });

                                return rs;
                            } catch (Exception e1) {
                                System.out.print(e1);
                                rs.addRow(new String[] { "false", "保存失败" });
                                return rs;
                            }
                        } else {
                            System.out.println("savetorder2");
                            try {
                                Orderlog log = new Orderlog();
                                log.setOrid(orid);
                                log.setIemployeeid(iemployeeid);
                                log.setStlg("0186");
                                log.setLogtype(new Long(2));
                                log.setLogdatetime(Tools.getDayTimes());
                                log.setNote("售票口出票");
                                log.setBrief("售票口出票");
                                IGenericService genericService = (IGenericService) SpringUtil
                                        .getBean("genericService");
                                Long id = genericService.getMaxPk("logid",
                                        "Orderlog");
                                log.setLogid(id + 1);
                                genericService.save(log);
                                System.out.println("savetorder3");
                            } catch (Exception e) {
                            }
                        }
                        return rs1;
                    */
                    	return null;
                    	} catch (Exception e) {
                        System.out.print(e);
                        rs.addRow(new String[] { "false",
                                "本地出票成功,连接中心服务器失败，更新中心服务器订单状态失败,请联系中心管理员" });
                        return JSON.toJSONString(rs);
                    }

                }
            } catch (Exception e) {

                System.out.println(e.getMessage());
                System.out.println("savetorder4");
                try {/*
                    javax.xml.rpc.Service service = null;
                    java.net.URL endpointURL = new java.net.URL("http://"
                            + url
                            + "/services/centersaleService?wsdl");
                    CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                            endpointURL, service);
                    ssl.setMaintainSession(true);
                    com.ectrip.ticket.centersale.client.ResultBean cano = ssl.updatecphfT_order(orid, iscenicid);

                    rs.addRow(new String[] { "false", "保存失败" });

                    return rs;
                */} catch (Exception e1) {
                    System.out.print(e1);
                    rs.addRow(new String[] { "false", "保存失败" });
                    return JSON.toJSONString(rs);
                }
            }

        } else {
            try {
                rs = saleAutoService.savebenditorder(orid, iscenicid,
                        iemployeeid, iticketwinid, maxid);
                if (rs != null && rs.getRowsCount() > 0 && !"false".equalsIgnoreCase(rs.getResult(0, 0))
                        && pojo.isCYT) {
                    checkService.addCYTPojo(pojo);
                }
                return JSON.toJSONString(rs);
            } catch (Exception e) {
                e.printStackTrace();
                rs.addRow(new String[] { "false", "保存失败" });
                return JSON.toJSONString(rs);
            }
        }
        return null;
    
	}

    /**
     * 销售凭证:salesvouchers=
     * "iscenicid&iticketwinid&ibusinessid&iemployeeid&iaccountreceivable&iacceptmoney&igivechange&usid&forceemid&pzlb"
     * ;服务商&窗口ID&业务ID&出票员ID&实收&应收&找零&用户名 当业务ID=1 散客业务 usid='guest',其他业务时
     * usid是选择的用户usid 销售凭证明细:salesvoucherdetails=
     * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iticketnum&dtstartdate&dtenddate&bymaketicketway&bymediatype&szticketprintno"
     * ;明细ID&价格ID&产品ID&数量&开始日期&截至日期&出票方式&出票介质&初始票号 子票明细: comticketsalesdetails=
     * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iztickettypeid&isplitamount&tripid&ivenueareaid&ivenueseatsid&dtstartdate&dtenddate"
     * 明细ID&价格ID&产品ID&子产品ID&数量&竹筏趟次ID&码头ID&座位ID&开始日期&截至日期
     * 产品控制数量:productcontrols=
     * "itickettypeid&controltype&tripid&stdata&soldnumber"
     * 产品编号&控制方式&趟次&日期&销售数量:产品编号&控制方式&趟次&日期&销售数量 Describe:
     *
     * @auth:yuanchengjun
     * @param salesvouchers
     * @param salesvoucherdetails
     * @param comticketsalesdetails
     * @param productcontrols
     * @return return:ResultBean Date:2011-10-29
     */
    private String saveorder41(String salesvouchers,
                                   String salesvoucherdetails, String comticketsalesdetails,
                                   String productcontrols,String url ,String... param) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        Debug.print("My is saveorder41");
        rs.setColumnNames(new String[] { "returnstats", "message" });
        // System.out.println("saveorder41  Hi is ok ");
        String returnstats = "true";
        String message = "";
        try {
            ResultBean cano = null;

            if (salesvouchers == null || salesvouchers.equals("")
                    || salesvoucherdetails == null
                    || salesvoucherdetails.equals("")
                    || comticketsalesdetails == null
                    || comticketsalesdetails.equals("")) {
                rs.addRow(new String[] { "false", "数据不完整，不能保存" });
                return JSON.toJSONString(rs);
            }
            String[] salesvoucher = salesvouchers.split("&");

            Long iticketwinid = new Long(salesvoucher[1]);

            Long ibusinessid = new Long(salesvoucher[2]);
            Long empid = new Long(salesvoucher[3]); // 出票员ID
            String usid = salesvoucher[7];
            if (productcontrols != null && !productcontrols.equals("")) {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                    if (ibusinessid == 3) {
                        // 预留量更新
                    	Object[] objects = client.invoke("updateProductReserve", productcontrols, usid);
        				cano = (ResultBean) objects[0];
                    } else {
                        // 可售量更新
                    	Object[] objects = client.invoke("updateProductcontrol", productcontrols);
                        cano = (ResultBean) objects[0];
                    }
                    returnstats = cano.getResult(0, 0);
                } catch (Exception e) {
                    rs.addRow(new String[] { "false", "连接中心服务器失败，不能保存订单" });
                    return JSON.toJSONString(rs);
                }
            }
            // 判断售出的预制票不能重复 2013-04-15 lijingrui
            String[] salesvoucherdetail = salesvoucherdetails.split(":");
            Edmtickettypetab edmtick = null;
            List<Object[]> jectlist = new ArrayList<Object[]>();
            StringBuffer codelist = new StringBuffer();
            if (salesvoucherdetail != null && salesvoucherdetail.length > 0) {
                for (int x = 0; x < salesvoucherdetail.length; x++) {
                    String isalesvoucherdetail = salesvoucherdetail[x];
                    String[] voudeta = isalesvoucherdetail.split("&");
                    Long itickettypeid = new Long(voudeta[2]);
                    Long amount = new Long(voudeta[3]);
                    String bymaketicketway = "00";
                    String szticketprintno = "";
                    edmtick = (Edmtickettypetab) saleCenterService
                            .getEdmtickettypetab(itickettypeid);
                    if (voudeta.length > 6) {
                        bymaketicketway = voudeta[6];
                        if (bymaketicketway.equals("01")) {

                            // 介质类型为 一维条码 二维条码的 是预制票
                            if (edmtick.getBymediatype().equals("00")
                                    || edmtick.getBymediatype().equals("01")) {

                                szticketprintno = voudeta[8];
                                String[] sperialnum = szticketprintno
                                        .split("[|]");
                                if (sperialnum != null && sperialnum.length > 0) {
                                    for (int i = 0; i < sperialnum.length; i++) {
                                        boolean b = saleCenterService
                                                .isExistcode(empid,
                                                        itickettypeid,
                                                        sperialnum[i]
                                                                .toString());
                                        if (!b) {
                                            rs.addRow(new String[] { "false",
                                                    "手中无此票号，已出售或者未领用!!!" });
                                            return JSON.toJSONString(rs);
                                        }

                                        codelist.append(sperialnum[i] + ",");
                                    }
                                }

                            }
                        }
                    }

                    Object[] o = new Object[] { edmtick.getIscenicid(),
                            itickettypeid, amount, voudeta[4].toString(),
                            voudeta[5].toString() };
                    jectlist.add(o);
                }


                if (ibusinessid == 1) {
                    // 判断库存是否超量
                    int kc = stockswareService.checkTicketware(jectlist);
                    if (kc == 0) {
                        rs.addRow(new String[] { "false", "您购买的票务数量超过库存,请重新选择!" });
                        return JSON.toJSONString(rs);
                    }
                } else {
                    Custom stom = ecService.getCustomByUserId(usid);

                    int yhkc = stockswareService.checkTicketwareCustom(
                            jectlist, stom.getUsid());
                    if (yhkc == 0) {
                        rs.addRow(new String[] { "false", "您购买的票务数量超过库存,请重新选择!" });
                        return JSON.toJSONString(rs);
                    } else if (yhkc == 2) {
                        rs.addRow(new String[] { "false", "接待单位需配置库存量才允许销售!" });
                        return JSON.toJSONString(rs);
                    } else {
                        if (stom.getIbusinessid() != 3) {
                            int kcpd = stockswareService
                                    .checkTicketware(jectlist);
                            if (kcpd == 0) {
                                rs.addRow(new String[] { "false",
                                        "您购买的票务数量超过库存,请重新选择!" });
                                return JSON.toJSONString(rs);
                            }
                        }
                    }

                }

                String[] code = codelist.toString().split(",");
                for (int i = 0; i < code.length; i++) {
                    String printcode = (String) code[i];
                    for (int j = i + 1; j < code.length; j++) {
                        String endcode = (String) code[j];
                        if (printcode.equals(endcode)) {
                            rs.addRow(new String[] {
                                    "false",
                                    "同一凭证下" + edmtick.getSztickettypename()
                                            + "售出的预制票号不能重复!" });
                            return JSON.toJSONString(rs);
                        }
                    }
                }

            }

            if (returnstats.equals("false")) {
                rs.addRow(new String[] { "false", cano.getResult(0, 1) });
                return JSON.toJSONString(rs);
            } else {
                try {

                    Long maxid = saleCenterService.updatevouchersequence();
                    Esbticketwintab esbticketwintab = saleCenterService
                            .getEsbticketwintab(iticketwinid);
                    int ticketstation = esbticketwintab.getIticketstationid()
                            .intValue();
                    String szticketstation = "";
                    if (0 < ticketstation && ticketstation < 10) {
                        szticketstation = "00" + ticketstation;
                    } else if (ticketstation >= 10 && ticketstation < 100) {
                        szticketstation = "0" + ticketstation;
                    } else {
                        szticketstation = "" + ticketstation;
                    }
                    String szsalesvoucherno = "";
                    szsalesvoucherno = saleCenterService
                            .updateMaxNo(szticketstation);
                    System.out.println("------------");
                    rs = saleCenterService.saveorder41(salesvouchers,
                            salesvoucherdetails, comticketsalesdetails, maxid,
                            szsalesvoucherno, param);

                    returnstats = rs.getResult(0, 0);

                    if (returnstats.equals("true")) {
                    	return JSON.toJSONString(rs);
                    } else {
                        message = "保存出错，与管理员联系。";
                        if (productcontrols != null
                                && !productcontrols.equals("")) {
                            try {
                            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                                if (ibusinessid == 3) {
                                	Object[] objects = client.invoke("updatecancelProductreserve", productcontrols, usid);
                    				cano = (ResultBean) objects[0];
                                    /*cano = ssl.updatecancelProductreserve(
                                            productcontrols, usid);*/
                                } else {
                                	Object[] objects = client.invoke("updatecancelProductcontrol", productcontrols);
                    				cano = (ResultBean) objects[0];
                                   /* cano = ssl
                                            .updatecancelProductcontrol(productcontrols);*/
                                }
                            } catch (Exception e1) {
                                message = "保存失败,可售量已扣除";
                            }
                        }
                        rs.addRow(new String[] { returnstats, message });
                        return JSON.toJSONString(rs);
                    }

                } catch (Exception e) {
                    message = "保存出错，与管理员联系。";
                    // System.out.println("Exception= "+e.toString());
                    // System.out.println("正常返回Exception"+returnstats);
                    if (productcontrols != null && !productcontrols.equals("")) {
                        try {
                        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                            if (ibusinessid == 3) {
                            	Object[] objects = client.invoke("updatecancelProductreserve", productcontrols, usid);
                				cano = (ResultBean) objects[0];
                                /*cano = ssl.updatecancelProductreserve(
                                        productcontrols, usid);*/
                            } else {
                            	Object[] objects = client.invoke("updatecancelProductcontrol", productcontrols);
                				cano = (ResultBean) objects[0];
                               /* cano = ssl
                                        .updatecancelProductcontrol(productcontrols);*/
                            }
                        } catch (Exception e1) {
                            message = "保存失败" + ",可售量已扣除";
                        }
                    }
                    rs.addRow(new String[] { "false", message });
                    return JSON.toJSONString(rs);
                }
            }
        } catch (Exception e) {
            System.out.println("returnstats= " + returnstats);
            rs.addRow(new String[] { "fale", "保存失败11" });
            return JSON.toJSONString(rs);
        }

    }

    /**
     * param 参数 orderlistid,szticketprintno#orderlistid,szticketprintno
     * Describe:
     *
     * @auth:yuanchengjun
     * @param orid
     * @param iscenicid
     * @param iticketwinid
     * @param iemployeeid
     * @param param
     * @return return:ResultBean Date:2012-3-28
     */
    @SuppressWarnings("unchecked")
	private ResultBean savetorder41(String orid, Long iscenicid,
                                    Long iticketwinid, Long iemployeeid, String param, String param1,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        String message = "";
        Map m = new HashMap();
        List printnolist = new ArrayList();// 激活票号list
        if (!param.equals("")) {
            String[] pn = param.split("#");
            for (int i = 0; i < pn.length; i++) {
                String[] pzn = pn[i].split(",");
                String[] s2 = pzn[1].split("[|]");

                for (int j = 0; j < s2.length; j++) {
                    if (m.containsKey(s2[j])) {
                        message = "预制票号重复,请重新输入!";
                        rs.addRow(new String[] { "false", message });
                        return rs;
                    } else {
                        m.put(s2[j], null);
                    }
                }

                TOrderlist t = new TOrderlist();
                t.setOrderlistid(pzn[0]);
                t.setOrid(pzn[1]);// 票号存在这个字段
                printnolist.add(t);
            }
        }

        Long maxid = saleCenterService.updatevouchersequence();
        // if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
        T_order t_order = null;
        List<T_orderlist> listorder = new ArrayList<T_orderlist>();
        List<T_zorderlist> listzorder = new ArrayList<T_zorderlist>();
        List<Trealname> Trealnamelist = new ArrayList<Trealname>();
        try {
        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
			Object[] objects = client.invoke("chupiaoT_order", orid, iscenicid);
			ResultBean cano = (ResultBean) objects[0];
            /*client.ResultBean cano = ssl.chupiaoT_order(
                    orid, iscenicid);*/
            if (cano.getRowsCount() > 0) {
                for (int i = 0; i < cano.getRowsCount(); i++) {

                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));

                    }
                    t_order = (T_order) saleCenterService.convertMap(
                            T_order.class, returnMap);

                    if (t_order.getDdzt().equals("11")) {
                        message = "订单已出票，无需再次出票";
                        rs.addRow(new String[] { "false", message });
                        return rs;
                    }
                    if (t_order.getDdzt().equals("00")) {
                        if (param1.equals("")) {
                            message = "前台现付订单应传入支付方式和金额";
                            rs.addRow(new String[] { "false", message });
                            return rs;
                        }
                    }
                    if (t_order.getDdzt().equals("02")) {
                        param1 = "";
                    }
                }
            } else {
                message = "订单不存在";
                rs.addRow(new String[] { "false", message });
                return rs;
            }
            Object[] objectsOrderList = client.invoke("chupiaoT_orderlist", orid, iscenicid);
			cano = (ResultBean) objectsOrderList[0];
//            cano = ssl.chupiaoT_orderlist(orid, iscenicid);
            if (cano.getRowsCount() > 0) {
                for (int i = 0; i < cano.getRowsCount(); i++) {

                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));

                    }
                    T_orderlist t1 = (T_orderlist) saleCenterService
                            .convertMap(T_orderlist.class, returnMap);

                    Edmtickettypetab eticket = saleCenterService
                            .getEdmtickettypetab(new Long(t1.getItickettypeid()));
                    t1.setBymaketicketway(eticket.getBymaketicketway());
                    if (eticket.getBymaketicketway().equals("01")) {
                        // 李经锐 2012-09-06修改 增加判断 介质类型
                        if (eticket.getBymediatype().equals("00")
                                || eticket.getBymediatype().equals("01")) {
                            // 现场激活 介质类型为 一维条码 二维条码的 是预制票
                            boolean b = false;
                            for (int k = 0; k < printnolist.size(); k++) {
                                TOrderlist to = (TOrderlist) printnolist.get(k);
                                if (to.getOrderlistid().equals(
                                        t1.getOrderlistid())) {
                                    t1.setSzticketprintno(to.getOrid());
                                    b = true;
                                }
                            }
                            if (!b) {
                                rs.addRow(new String[] { "false",
                                        "现场激活票必须填写初始票号" });
                                return rs;
                            }
                        }
                    }
                    listorder.add(t1);

                }
            } else {
                message = "订单明细不存在";
                rs.addRow(new String[] { "false", message });
                return rs;
            }
            Object[] objectsZOrderlist = client.invoke("chupiaoT_zorderlist", orid, iscenicid);
			cano = (ResultBean) objects[0];
//            cano = ssl.chupiaoT_zorderlist(orid, iscenicid);
            if (cano.getRowsCount() > 0) {
                for (int i = 0; i < cano.getRowsCount(); i++) {
                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));
                    }
                    T_zorderlist t2 = (T_zorderlist) saleCenterService
                            .convertMap(T_zorderlist.class, returnMap);

                    listzorder.add(t2);
                }
            } else {
                message = "订单子明细不存在";
                rs.addRow(new String[] { "false", message });
                return rs;
            }
            Object[] objectsTRealName = client.invoke("chupiaoTRealname", orid, iscenicid);
			cano = (ResultBean) objects[0];
//            cano = ssl.chupiaoTRealname(orid, iscenicid);
            if (cano.getRowsCount() > 0) {
                for (int i = 0; i < cano.getRowsCount(); i++) {
                    Map returnMap = new HashMap();
                    String[] ss = cano.getColumnNames();
                    for (int j = 0; j < ss.length; j++) {
                        returnMap.put(ss[j].toLowerCase(),
                                cano.getResult(i, ss[j]));
                    }
                    Trealname tr = (Trealname) saleCenterService.convertMap(
                            Trealname.class, returnMap);

                    Trealnamelist.add(tr);
                }
            }
        } catch (Exception e) {
            System.out.print(e);
            rs.addRow(new String[] { "false", "连接中心服务器失败，不能读取订单" });
            return rs;
        }

        try {
            System.out.println("保存开始");
            ResultBean rs1 = saleCenterService.savetorder41(t_order, listorder,
                    listzorder, iemployeeid, iticketwinid, maxid,
                    Trealnamelist, param1);

            if (rs1.getResult(0, 0).equals("false")) {
                rs.addRow(new String[] { rs1.getResult(0, 0),
                        rs1.getResult(0, 1) });
                return rs;
            } else {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
    				Object[] objects = client.invoke("updateT_order", orid, iscenicid, iemployeeid,
                            new Double(t_order.getZfmont()));
    				ResultBean cano = (ResultBean) objects[0];
                    /*client.ResultBean cano = ssl
                            .updateT_order(orid, iscenicid, iemployeeid,
                                    new Double(t_order.getZfmont()));*/
                    if (cano.getResult(0, 0).equals("false")) {

                        rs.addRow(new String[] { "false",
                                "本地出票成功,连接中心服务器失败，更新中心服务器订单状态失败,请联系中心管理员" });
                        return rs;
                    }
                    rs.addRow(new String[] { rs1.getResult(0, 0),
                            rs1.getResult(0, 1) });
                    try {
                        Orderlog log = new Orderlog();
                        log.setOrid(orid);
                        log.setIemployeeid(iemployeeid);
                        log.setStlg("0186");
                        log.setLogtype(new Long(2));
                        log.setLogdatetime(Tools.getDayTimes());
                        log.setNote("售票口出票");
                        log.setBrief("售票口出票");
                        Long id = sysService.getMaxPk("logid", "Orderlog");
                        log.setLogid(id + 1);
                        sysService.insertOrderlog(log);;
                    } catch (Exception e) {
                    }
                    return rs;
                } catch (Exception e) {
                    rs.addRow(new String[] { "false",
                            "本地出票成功,连接中心服务器失败，更新中心服务器订单状态失败,请联系中心管理员" });
                    return rs;
                }
            }
        } catch (Exception e) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("updatecphfT_order", orid, iscenicid);
				ResultBean cano = (ResultBean) objects[0];
                rs.addRow(new String[] { "false", "保存失败" });
                return rs;
            } catch (Exception e1) {
                System.out.print(e1);
                rs.addRow(new String[] { "false", "保存失败" });
                return rs;
            }
        }

    }

    public String updatenojinquT_order(String orid, Long iscenicid,
                                           Long iemployeeid, Double mont) {
        
        ResultBean rs = saleCenterService.updatenojinquT_order(orid, iscenicid,
                iemployeeid, mont);
        return JSON.toJSONString(rs);
    }
    
    public void updatestsprint(Long isalesvoucherid, Long iticketstationid) {
        
        saleCenterService.updatestsprint(isalesvoucherid, iticketstationid);
    }

    public String reprintbyorid(String szsalesvoucherno, Long iscenicid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        String ornm = "";
        String szregionalname = "";
        String corpname = "";
        if (szsalesvoucherno.substring(8, 11).equals("000")) {
            if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                try {
                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
    				Object[] objects = client.invoke("getT_orderbyorid", szsalesvoucherno, iscenicid);
    				ResultBean cano = (ResultBean) objects[0];
                    /*client.ResultBean cano = ssl
                            .getT_orderbyorid(szsalesvoucherno, iscenicid);*/
                    if (cano.getRowsCount() > 0) {
                        ornm = cano.getResult(0, "ORNM");
                        szregionalname = cano.getResult(0, "SZREGIONALNAME");
                        corpname = cano.getResult(0, "CORPNAME");
                    } else {
                        ornm = "";
                        szregionalname = "";
                        corpname = "";
                    }
                } catch (Exception e1) {
                    ornm = "";
                    szregionalname = "";
                    corpname = "";
                }
            } else {
                ResultBean rs = saleCenterService.getT_orderbyorid(
                        szsalesvoucherno, iscenicid);
                if (rs.getRowsCount() > 0) {
                    ornm = rs.getResult(0, "ORNM");
                    szregionalname = rs.getResult(0, "SZREGIONALNAME");
                    corpname = rs.getResult(0, "CORPNAME");
                } else {
                    ornm = "";
                    szregionalname = "";
                    corpname = "";
                }
            }
        }

        ResultBean rs = saleCenterService.reprintbyorid(szsalesvoucherno, iscenicid,
                ornm, szregionalname, corpname);
        return JSON.toJSONString(rs);
    }

    /**
     * 根据订单号读取 票信息和检票信息:
     *
     * @auth:yuanchengjun
     * @param szticketprintno
     * @return return:ResultBean Date:2011-11-22
     */
    public String getorderbyorid(String orid, Long iscenicid) {
        orid = orderDecode(orid);
        
        ResultBean rs = saleCenterService.getorderbyorid(orid, iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     * 返回售票员当前最小库存票号
     *
     * @param iscenicid
     *            服务商编号
     * @param ticketid
     *            票类
     * @param userid
     *            售票员
     * @param tourDate
     *            日 期
     * @param otherParm
     *            其它参数
     * @return
     */
    public String checkingFirstBarcodet(Long iscenicid, Long ticketpriceid,
                                            Long userid, String tourDate, String... otherParm) {
        
        ResultBean rs = saleCenterService.checkingFirstBarcodet(iscenicid,
                ticketpriceid, userid, tourDate, otherParm);
        return JSON.toJSONString(rs);
    }
    /**
     * 返回当前用户的所售票类的数量组合 按段划分
     *
     * @param iscenicid
     *            服务商编号
     * @param firstBarcode
     *            初始条码
     * @param count
     *            数量
     * @param tourDate
     *            日期
     * @param userid
     *            编号
     * @param otherParm
     *            其它需要的参数
     * @return
     */
    public String checkingPrefabricateTicket(Long iscenicid,
                                                 Long ticketpriceid, String firstBarcode, int count,
                                                 String tourDate, Long userid, String... otherParm) {

		/*
		 * 1WADASSSSSD434 1 1WADASSSSSD435 1
		 * 
		 * 1WADASSSSSD460 2 1WADASSSSSD461 2 1WADASSSSSD470 3 1WADASSSSSD471 3
		 */

        // ResultBean rs = new ResultBean();
        // rs.addRow(new String[] { "1WADASSSSSD434", "1" });
        // rs.addRow(new String[] { "1WADASSSSSD435", "1" });
        // rs.addRow(new String[] { "1WADASSSSSD436", "1" });
        // rs.addRow(new String[] { "1WADASSSSSD460", "2" });
        // rs.addRow(new String[] { "1WADASSSSSD461", "2" });
        // rs.addRow(new String[] { "1WADASSSSSD470", "3" });
        // rs.addRow(new String[] { "1WADASSSSSD471", "3" });
        
        ResultBean rs = saleCenterService.checkingPrefabricateTicket(iscenicid, ticketpriceid,
                        firstBarcode, count, tourDate, userid, otherParm);
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param iserialnums
     * @param iscenicid
     * @return return:ResultBean Date:2012-8-10
     */
    @SuppressWarnings("unchecked")
	public String ticketreprintbylb(String cdcs, String iserialnums,
                                        Long iscenicid,String param,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        iserialnums = orderDecode(iserialnums);
        List list = new ArrayList();
        if (cdcs.equals("01")) {
        	
            return ticketreprint(iserialnums, iscenicid,url);
        } else {
            // 读取售票信息
            List<Map> list1 = saleCenterService.ticketreprintbylb(cdcs,
                    iserialnums, iscenicid,param);

            if (list1 == null || list1.size() == 0) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", "没有票务数据" });
                return JSON.toJSONString(rs);
            } else {
                Map map1 = (Map) list1.get(0);
                String ornm = "";
                String szregionalname = "";
                String corpname = "";
                String szsalesvoucherno = map1.get("SZSALESVOUCHERNO")
                        .toString();
                if (szsalesvoucherno.substring(8, 11).equals("000")) {
                    if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                        try {
                        	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
            				Object[] objects = client.invoke("getT_orderbyorid", szsalesvoucherno, iscenicid);
            				ResultBean cano = (ResultBean) objects[0];
                            if (cano.getRowsCount() > 0) {
                                ornm = cano.getResult(0, "ORNM");
                                szregionalname = cano.getResult(0,
                                        "SZREGIONALNAME");
                                corpname = cano.getResult(0, "CORPNAME");
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        ResultBean cano = saleCenterService.getT_orderbyorid(
                                szsalesvoucherno, iscenicid);
                        if (cano.getRowsCount() > 0) {
                            ornm = cano.getResult(0, "ORNM");
                            szregionalname = cano
                                    .getResult(0, "SZREGIONALNAME");
                            corpname = cano.getResult(0, "CORPNAME");
                        }
                    }
                }
                for (Map map : list1) {
                    String zdail = map.get("ZDAIL").toString();
                    if (!ornm.equals("")) {
                        map.put("ORNM", ornm);
                    }
                    if (!szregionalname.equals("")) {
                        map.put("SZREGIONALNAME", szregionalname);
                    }
                    if (!corpname.equals("")) {
                        map.put("CORPNAME", corpname);
                    }
                    list.add(map);

                }
            }

            if (list == null || list.size() == 0) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", "没有找到票务数据或已全部退订" });
                return JSON.toJSONString(rs);
            }
            ResultBean rs = MapToResultBean.toResultBean(list);
            return JSON.toJSONString(rs);
        }
    }

    // 李进增加方法 getICID
    public String getICID(String typeid, Long id) {
        
        ResultBean rs = saleCenterService.getICID(typeid, id);
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:判断IC库存数量
     *
     * @auth:lijingrui
     * @param iscenicid
     * @param ticketpriceid
     * @param count
     * @param tourDate
     * @param userid
     * @param otherParm
     * @return return:String Date:2012-8-31
     */
    public String personCheckoutiaMount(Long iscenicid, Long ticketpriceid,
                                        int count, String tourDate, Long userid, String... otherParm) {
        
        return saleCenterService.personCheckoutiaMount(iscenicid,
                ticketpriceid, count, tourDate, userid, otherParm);
    }

    /**
     * 增加写IC卡日志的类
     *
     * @param szTicketPrintNo
     *            票号
     * @param EquipmentID
     *            机器编号
     * @param ICBLOCK
     *            块编号
     * @param ICContent
     *            内容
     * @return
     */
    public int IcSaleWriteLog(String szTicketPrintNo, int EquipmentID,
                              String ICBLOCK, String ICContent) {
        ISaleCheckupService saleCheckupService = (ISaleCheckupService) SpringUtil
                .getBean("saleCheckupService");
        return saleCheckupService.IcWriteLog(szTicketPrintNo, 1, EquipmentID,
                ICBLOCK, ICContent);
    }

    /**
     * 读取写入IC卡的内容
     *
     * @param szTicketPrintNo
     *            卡号
     * @param type
     *            1--当天日志 2--历史日志
     * @return
     */

    public String GetICData(String szTicketPrintNo, int type) {
        ISaleCheckupService saleCheckupService = (ISaleCheckupService) SpringUtil
                .getBean("saleCheckupService");
        ResultBean rs = saleCheckupService.GetICData(szTicketPrintNo, type);
        return JSON.toJSONString(rs);
    }

    /**
     * * Describe:显示圆门和检票次数
     *
     * @see com.ectrip.sale.service.iservice.ISaleCheckupService#getGateTicketCount(java.lang.Long,
     *      java.lang.Long)
     * @param iscenicid
     *            服务商ID
     * @param iticktypeid
     *            产品ID
     * @param tickettypeid
     *            子产品ID
     * @return
     * @author lijingrui Date:2012-9-5
     */
    public String getGateTicketCount(Long iscenicid, Long iticktypeid,
                                         Long tickettypeid) {
        ISaleCheckupService saleCheckupService = (ISaleCheckupService) SpringUtil
                .getBean("saleCheckupService");
        ResultBean rs = saleCheckupService.getGateTicketCount(iscenicid, iticktypeid,
                tickettypeid);
        return JSON.toJSONString(rs);
        
    }

    /**
     *
     * Describe:对IC卡票 修改售出门票表中的票号 并激活
     *
     * @auth:lijingrui
     * @param iscenicid
     *            服务商
     * @param oldszticketno
     *            旧票号
     * @param newszticketno
     *            新票号
     * @return return:boolean Date:2012-9-13
     */
    public boolean getVaildcheckCodeup(Long iscenicid, String oldszticketno,
                                       String newszticketno, Long iscz) {
        
        return saleCheckupService.getVaildcheckCodeup(iscenicid, oldszticketno,
                newszticketno, iscz);
    }

    /**
     *
     * Describe: 副卷打印设置
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param ibusinessid
     * @return return:ResultBean Date:2012-11-01
     */
    public String getSodeprintmanage(Long iscenicid, Long ibusinessid) {
        
    	ResultBean rs = saleCenterService.getsodeprintmanage(iscenicid, ibusinessid);
    	return JSON.toJSONString(rs);
    }

    /**
     * 挂失票务 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @param szticketprintno
     * @return return:ResultBean Date:2012-12-11
     */
    public String guashi(Long iemployeeid, String szticketprintno) {
        
    	ResultBean rs = saleCenterService.saveguashi(iemployeeid, szticketprintno);
    	return JSON.toJSONString(rs);
    }

    /**
     * 解挂票务 Describe:
     *
     * @auth:yuanchengjun
     * @param iemployeeid
     * @param szticketprintno
     * @return return:ResultBean Date:2012-12-11
     */
    public String jiegua(Long iemployeeid, String szticketprintno) {
        
    	ResultBean rs = saleCenterService.savejiegua(iemployeeid, szticketprintno);
    	return JSON.toJSONString(rs);
    }

	public String yanqi(Long iemployeeid, Long iscenicid, Long iticketwinid, String szticketprintno,
			double iaccountreceivable, double iacceptmoney, double igivechange, String zffs) {
        
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        try {
            Long maxid = saleCenterService.updatevouchersequence();
            Esbticketwintab esbticketwintab = saleCenterService
                    .getEsbticketwintab(iticketwinid);
            int ticketstation = esbticketwintab.getIticketstationid()
                    .intValue();
            String szticketstation = "";
            if (0 < ticketstation && ticketstation < 10) {
                szticketstation = "00" + ticketstation;
            } else if (ticketstation >= 10 && ticketstation < 100) {
                szticketstation = "0" + ticketstation;
            } else {
                szticketstation = "" + ticketstation;
            }
            String szsalesvoucherno = "";
            szsalesvoucherno = saleCenterService.updateMaxNo(szticketstation);
            rs = saleCenterService.saveyanqi(iemployeeid, iscenicid,
                    iticketwinid, szticketprintno, iaccountreceivable,
                    iacceptmoney, igivechange, zffs, szsalesvoucherno, maxid);
            return JSON.toJSONString(rs);
        } catch (Exception e) {
            rs.addRow(new String[] { "fale", "保存失败," + e.getMessage() });
            return JSON.toJSONString(rs);
        }
    }

    public String gettickmont(Long icrowdkindpriceid, Long numb, String stdt) {
        
    	ResultBean rs = saleCenterService.gettickmont(icrowdkindpriceid, numb, stdt);
    	return JSON.toJSONString(rs);
    }
    /**
     * 根据导游信息 Describe:
     *
     * @auth:yuanchengjun
     * @param ibusinessid
     * @return return:ResultBean Date:2011-10-27
     */
    public String getDaoyou(String lname) {
        
    	ResultBean rs = saleCenterService.getDaoyou(lname);
    	return JSON.toJSONString(rs);
    }

    public void updatestatus(Long accid, String typestatus, Long byisuse) {
        
        saleCenterService.updatestatus(accid, typestatus, byisuse);
    }

    public String getordercs(Long ibusinessid) {
        
    	ResultBean rs = saleCenterService.getordercs(ibusinessid);
    	return JSON.toJSONString(rs);
    }
    
    public String getGalsourceregiontab() {

    	ResultBean rs = saleCenterService.getGalsourceregiontab();
    	return JSON.toJSONString(rs);
    }

    /**
     * 打印回执
     *
     * @param iscenicid
     * @param isalevocherid
     * @param type
     *            1:重打印,0默认  2退票
     * @return
     */
    public String getSalevoucher(String iscenicid, String isalevocherid,
                                 int type) {
        isalevocherid = orderDecode(isalevocherid);
        /*IReceiptService receiptService = (IReceiptService) SpringUtil
                .getBean("receiptService");*/
        return sysService.getSalesVoucher(isalevocherid, iscenicid, type);
    }

    public String getordermessageyedy(String iscenicid, String orid, int type) {
        orid = orderDecode(orid);
        
        return sysService.getordermessage(orid, iscenicid, type);
    }

    public String getICcardType() {
        
    	ResultBean rs = saleCenterService.getICcardtypelist();
    	return JSON.toJSONString(rs);
    }

    public String getfapiao(Long isalesvoucherid, Long iticketstationid,
                                Long iemployeeid, Long iticketwinid, String corpname, String fpno,
                                String... param) {
        
    	ResultBean rs = saleCenterService.getfapiao(isalesvoucherid, iticketstationid,iemployeeid, iticketwinid, corpname, fpno);
    	return JSON.toJSONString(rs);
    }
    public void updatefapiaobyorid(String orid, Long iscenicid) {

        saleCenterService.updatefapiaobyorid(orid, iscenicid);
    }
    

    public void updatefapiao(Long isalesvoucherid, Long iticketstationid) {

        saleCenterService.updatefapiao(isalesvoucherid, iticketstationid);
    }

    /**
     *
     * Describe:业务暂无涉及该业务 2018-05-03 jiyong
     *
     * @auth:yuanchengjun
     * @param type
     *            类别
     * @param ticketno
     *            票号
     * @param ziwenno
     *            指纹信息
     *
     *
     * @return
     * @throws Exception
     *             return:int Date:2011-11-14
     */
    @Deprecated
    public int ticketZwdengji(String type, String ticketno, String ziwenno) {

        ICheckdengjiService checkdengjiService = (ICheckdengjiService) SpringUtil
                .getBean("checkdengjiService");
        try {

            return checkdengjiService.saveZwdengji(type, ticketno, ziwenno);
        } catch (Exception e) {
            return 0;
        }
    }
    /**
     * 最新售票软件总的服务方法入口
     * @param md5str      加密串
     * @param userid      用户ID
     * @param password    密码
     * @param method      方法编号
     * @param parameters  参数
     * @return            OBJECT
     * @throws NumberFormatException 
     * @throws Exception
     */
    public String saleautoservice(String md5str, String userid,String password, Long method, String... parameters) throws NumberFormatException, Exception{/*
				/**
				 * 判断用户
				 */
    	/*Esfemployeetab esfemployeetab = null;
				if (method != 1 && method < 1001) {
				    esfemployeetab = saleAutoService.isyuxiao(md5str, userid, password);
				    if (esfemployeetab == null) {
				        ResultBean rs = new ResultBean();
				        rs.setColumnCount(2);
				        rs.setColumnNames(new String[] { "returnstats", "message" });
				        rs.addRow(new String[] { "false", "接口调用验证不成功" });
				        return JSON.toJSONString(rs);
				    }
				}
				if (method == 1) {
				    // 用户登录
				    return this.emplogin(new Long(parameters[0]), userid, password);
				} else if (method == 2) {
					String getprogram = this.getprogram(new Long(parameters[0]), parameters[1],parameters[2]);
					return getprogram;
				} else if (method == 3) {
				    return this.Getseatstusts(new Long(parameters[0]), parameters[1],
				            new Long(parameters[2]),null);
				} else if (method == 4) {
				    return this.getseat(new Long(parameters[0]));
				} else if (method == 5) {
				    return this.Getareapricve(new Long(parameters[0]), parameters[1],
				            parameters[2], new Long(parameters[3]));
				} else if (method == 6) {
				    if (parameters.length > 5) {
				        String[] parm = new String[] {};
				        for (int i = 0; i < parameters.length - 5; i++) {
				            parm[i] = parameters[5 + i];
				        }
				        return this.saveorder(parameters[0], parameters[1],
				                parameters[2], parameters[3], parameters[4],null, parm);
				    } else {
				        return this.saveorder(parameters[0], parameters[1],
				                parameters[2], parameters[3], parameters[4], null);
				    }
				} else if (method == 10) {
				    return this.Getvenue(new Long(parameters[0]));
				} else if (method == 11) {
				    return this.getprogrambyproductid(new Long(parameters[0]),
				            parameters[1],parameters[2]);
				} else if (method == 12) {
				    return this.Getareaseatstusts(new Long(parameters[0]), new Long(
				            parameters[1]), parameters[2], new Long(parameters[3]),null);
				} else if (method == 13) {
				    boolean flag = this.jihuobyzjno(new Long(parameters[0]),
				            parameters[1], parameters[2], parameters[3]);

				    ResultBean rs = new ResultBean();
				    rs.setColumnNames(new String[] { "returnstats", "message" });
				    if (flag) {
				        rs.addRow(new String[] { "true", "" });
				    } else {
				        rs.addRow(new String[] { "false", "身份证出票不成功" });
				    }

				    return JSON.toJSONString(rs);
				} else if (method == 14) {
				    if (parameters.length == 1) {
				        return this.getempordermassage(new Long(parameters[0]),
				                Tools.getTodayString(),null);
				    } else {
				        return this.getempordermassage(new Long(parameters[0]),
				                parameters[1],null);
				    }

				} else if (method == 15) {
				    return this.Getseatlocklist(parameters[0],null);

				} else if (method == 16) {
				    return this.GetareapricveByIvenueareaid(new Long(parameters[0]), parameters[1],
				            parameters[2], new Long(parameters[3]), new Long(
				                    parameters[4]));

				} else if (method == 17) { // 服务商优惠判断
				    return this.checkEdpofferschemetab(parameters[0]);
				} else if (method == 18) { // 获取接待用户的接待量
				    return this.queryStockWarenumb(parameters[0], parameters[1]);
				} else if (method == 19) {// 根据票号获取指纹
				    return checkTicketzhiwe(parameters[0]);
				} else if (method == 20) {// 根据身份证号码获取导游计时票信息
				    return queryListzjhmPrint(parameters[0]);
				} else if (method == 21) {// 保存指纹截止日期 或者录入导游指纹
				    return checkSaveprintStssold(parameters[0], parameters[1],
				            parameters[2], parameters[3], parameters[4], parameters[5]);
				} else if (method == 22) {// 根据订单号（身份证）,服务商编号，窗口编号查询订单
				    return this.getT_orderByWin(parameters[0], new Long(parameters[1]),
				            new Long(parameters[2]),null);
				} else if (method == 23) {// 保存导游派遣单数据
				    return saveGuidenitices(parameters);
				} else if (method == 24) {// 保存结算单数据
				    return saveGuideniticesJs(parameters);
				} else if (method == 25) {// 根据姓名、证件号码、销售凭证号查询导游通知单

				    return getGuidenitices(parameters[0], parameters[1], parameters[2]);
				} else if (method == 26) {
				    String message = getordermessageyedy(parameters[0], parameters[1],
				            Integer.parseInt(parameters[2]));
				    ResultBean rs = new ResultBean();
				    rs.setColumnNames(new String[] { "message" });
				    rs.addRow(new String[] { message });
				    return JSON.toJSONString(rs);
				} else if (method == 27) {//
				    if(parameters.length > 2){//重打印
				        return ticketchupiao(new Long(parameters[0]), new Long(
				                parameters[1]),new Long(parameters[2]));
				    }else{
				        return ticketchupiao(new Long(parameters[0]), new Long(
				                parameters[1]),null);
				    }
				} else if (method == 28) {//

				    return ticketreprintjxzh(parameters[0], parameters[1], new Long(
				            parameters[2]));
				} else if(method == 50){
				    return updateprintbyprintno2(parameters[0],parameters[1],parameters[2],
				            Long.parseLong(parameters[3]),parameters[4]);

				}else if(method == 51){//根据票号和数量查询批量票号
				    return findTicketPrinNo(parameters[0],Long.parseLong(parameters[1]),Long.parseLong(parameters[2]));

				}else if (method == 1001) {
				    return this.zhuceticketwin(parameters[0], parameters[1], new Long(
				            parameters[2]));
				} else if (method == 1003) {
				    ticketZwdengji(parameters[0], parameters[1], parameters[2]);
				    ResultBean rs = new ResultBean();
				    rs.setColumnCount(2);
				    rs.setColumnNames(new String[] { "returnstats", "message" });
				    rs.addRow(new String[] { "true", "登记指纹成功" });
				    return JSON.toJSONString(rs);
				} else if(method == 2001){//易旅宝订单查询
				    return queryCytOrder(parameters[0],Long.parseLong(parameters[1]));
				} else if(method == 2002){//易旅宝订单明细
				    return queryCytOrderDetail(parameters[0], Long.parseLong(parameters[1]));
				} else if(method == 2003){//易旅宝出票
				    //[0]订单号 [1]服务商id [2]窗口ID [4]售票员ID [5]订单标识 [6]消费数量 [7]预制票号 [8]现付数据
				    return consumeOrder(parameters[0], Long.parseLong(parameters[1]),
				            Long.parseLong(parameters[2]), Long.parseLong(parameters[3]), parameters[4],
				            Long.parseLong(parameters[5]), parameters[6],parameters[7]);
				} else if(method == 3001){
				    //记录售票软件操作日志
				    //[0] 窗口ID [1]日志类型 01，常规日志 02，操作日志 [2]日志内容
				    saveSaleLog(userid,parameters[0],parameters[1],parameters[2]);
				    return null;
				}else {
				    ResultBean rs = new ResultBean();
				    rs.setColumnNames(new String[] { "returnstats", "message" });
				    rs.addRow(new String[] { "false", "调用方法参数不对" });
				    return JSON.toJSONString(rs);
				}*/
    	//////////////////////////////////

        /**
         * 判断用户
         */
        Esfemployeetab esfemployeetab = null;
        if (method != 1 && method < 1001) {
            esfemployeetab = saleAutoService.isyuxiao(md5str, userid, password);
            if (esfemployeetab == null) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", "接口调用验证不成功" });
                return JSON.toJSONString(rs);
            }
        }
        if (method == 1) {
            // 用户登录
            return this.emplogin(new Long(parameters[0]), userid, password);
        } else if (method == 1001) {
            return this.zhuceticketwin(parameters[0], parameters[1], new Long(
                    parameters[2]));
        } else if (method == 1002) {
            return this.getWinAndStationInfo(new Long(parameters[0]),
                    parameters[1]);
        } else if (method == 2) {

            return this.getT_order(parameters[0], new Long(parameters[1]),
                    parameters[2]);
        } else if (method == 3) {
            return this.getT_orderlist(parameters[0], new Long(parameters[1]),null);
        } else if (method == 4) {
        	Sysparv5 v5 = sysService.findOne("CYTT", "01");
            if(v5 == null || v5.getIsa() == 1L){
                return this.savetorder(parameters[0], new Long(parameters[1]),
                        new Long(parameters[2]), esfemployeetab.getIemployeeid(),
                        "",null,null);
            }else{
                ResultBean consumeOrder = cytSaleMainService.consumeOrder(parameters[0], new Long(parameters[1]), new Long(parameters[2]), esfemployeetab.getIemployeeid(),
                        parameters[4], new Long(parameters[5]), 2, "", "");
                return JSON.toJSONString(consumeOrder);
            }

        } else if (method == 5) {
            updateprintbyprintno(parameters[0], parameters[1], "01",
                    esfemployeetab.getIemployeeid());
            ResultBean rs = new ResultBean();
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "true", "打印更新成功" });
            return JSON.toJSONString(rs);
        } else if (method == 6) {
            String message = this.getSalevoucher(parameters[0], parameters[1],
                    Integer.parseInt(parameters[2]));
            ResultBean rs = new ResultBean();
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "true", message });
            return JSON.toJSONString(rs);
        }else if (method == 8) {
            return makeTwoBarcode(parameters[0], new Long(parameters[1]),
                    parameters[2],null);
        }else if(method == 10){
            return cancelorder(parameters[0],null);
        }else if (method == 27) {//

             ResultBean ticketchupiao = saleCenterService.ticketchupiao(new Long(parameters[0]), new Long(
                    parameters[1]),null);
             return JSON.toJSONString(ticketchupiao);
        } else if(method == 3001){
            //记录售票软件操作日志
            //[0] 窗口ID [1]日志类型 01，常规日志 02，操作日志 [2]日志内容
            saveSaleLog(userid,parameters[0],parameters[1],parameters[2]);
            return null;
        }else {
            ResultBean rs = new ResultBean();
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[]{"false", "调用方法参数不对"});
            return JSON.toJSONString(rs);
        }
    	
    }

    public String findTicketPrinNo(String ticketPrintNo,Long tickettypeid,Long numb) {
        
    	ResultBean rs = saleCenterService.findTicketPrinNo(ticketPrintNo, tickettypeid, numb);
    	return JSON.toJSONString(rs);
    }

    public void saveSaleLog(String userid,String winid,String logType,String note){
       
        saleCenterService.saveSaleLog(userid,winid,logType,note);
    }

    /**
     *
     * Describe:导游通知单查询
     *
     * @author:chenxinhao
     * @param szsalesvoucherno
     * @param daoyouname
     * @param cardno
     * @return
     * @throws Exception
     *             return:ResultBean Date:2015-3-18
     */
    public String getGuidenitices(String szsalesvoucherno,String daoyouname, String cardno) throws Exception {
        
    	ResultBean rs = saleCenterService.getGuidenitices(szsalesvoucherno, daoyouname,cardno);
        return JSON.toJSONString(rs);
    }

    public String ticketchupiao(Long isalesvoucherid, Long iticketstationid, Long rePrint) {
        
    	ResultBean rs = saleCenterService.ticketchupiao(isalesvoucherid,iticketstationid, rePrint);
        return JSON.toJSONString(rs);
    }

    public String ticketreprintjxzh(String cdcs, String iserialnums,
                                        Long iscenicid) {
        
    	ResultBean rs = saleCenterService.ticketreprintjxzh(cdcs, iserialnums, iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:导游派遣单保存数据
     *
     * @author:chenxinhao
     * @param parameters
     *            [0]销售凭证 [1]导游姓名 [2]证件号码 [3]人数 [4]游客类型
     * @return
     * @throws Exception
     *             return:ResultBean Date:2015-3-18
     */
    public String saveGuidenitices(String[] parameters) throws Exception {
        
    	ResultBean rs = saleCenterService.saveGuidenitices(parameters);
    	return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:导游结算单保存数据
     *
     * @author:chenxinhao
     * @param parameters
     *            [0]销售凭证 [1]窗口ID [2]员工ID
     * @return
     * @throws Exception
     *             return:ResultBean Date:2015-3-18
     */
    public String saveGuideniticesJs(String[] parameters) throws Exception {
        
    	ResultBean rs = saleCenterService.saveGuideniticesJs(parameters);
    	return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:根据订单号(身份证),服务商,窗口查询订单
     *
     * @author:chenxinhao
     * @param carno
     *            订单号,身份证
     * @param iscenicid
     *            服务商
     * @param winId
     *            窗口
     * @return return:ResultBean Date:2015-2-27
     */
    public String getT_orderByWin(String carno, Long iscenicid, Long winid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        carno = orderDecode(carno);
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {

            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getT_orderByWin", carno, iscenicid, winid);
				ResultBean cano = (ResultBean) objects[0];
                /*client.ResultBean cano = ssl
                        .getT_orderByWin(carno, iscenicid, winid);*/
                ResultBean rb = new ResultBean();

                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
        	ResultBean rs = saleCenterService.getT_orderByWin(carno, iscenicid, winid);
        	return JSON.toJSONString(rs);
        }

    }

    /**
     * 身份证出票激活
     *
     * @param iscenicid
     * @param szticketno
     * @param name
     * @param zjno
     * @return
     */
    public boolean jihuobyzjno(Long iscenicid, String szticketno, String name,
                               String zjno) {
    	
        return saleCheckupService.jihuobyzjno(iscenicid, szticketno, name, zjno);
    }

    public String getempordermassage(Long iemployeeid, String date,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (date == null || date.equals("")) {
            date = Tools.getTodayString();
        }

        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            String senic = saveVenueService.GetEmployeesenic(iemployeeid);
            ResultBean rb = new ResultBean();
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getVenueseatsalecountbyiscenicid", senic, date);
				ResultBean cano = (ResultBean) objects[0];
                /*client.ResultBean cano = ssl
                        .getVenueseatsalecountbyiscenicid(senic, date);*/
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
            } catch (Exception e) {
            	LOGGER.error(StringUtil.toString_02(e));
            }
            ResultBean rb2 = saleCenterService.getempordermassage(iemployeeid);
            for (int i = 0; i < rb2.getRowsCount(); i++) {
                String message = rb2.getResult(i, 0);
                rb.addRow(new String[] { message });
            }
            return JSON.toJSONString(rb);
        } else {
            ResultBean rb = saveVenueService.getVenueseatsalecount(iemployeeid,
                    date);
            ResultBean rb2 = saleCenterService.getempordermassage(iemployeeid);
            for (int i = 0; i < rb2.getRowsCount(); i++) {
                String message = rb2.getResult(i, 0);
                rb.addRow(new String[] { message });
            }
            return JSON.toJSONString(rb);
        }

    }

    /**
     * 销售凭证:salesvouchers=
     * "iscenicid&iticketwinid&ibusinessid&iemployeeid&iaccountreceivable&iacceptmoney&igivechange&usid&forceemid&pzlb"
     * ;服务商&窗口ID&业务ID&出票员ID&实收&应收&找零&用户名 当业务ID=1 散客业务 usid='guest',其他业务时
     * usid是选择的用户usid 销售凭证明细:salesvoucherdetails=
     * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iticketnum&dtstartdate&dtenddate&bymaketicketway&bymediatype&szticketprintno"
     * ;明细ID&价格ID&产品ID&数量&开始日期&截至日期&出票方式&出票介质&初始票号 子票明细: comticketsalesdetails=
     * "isalesvoucherdetailsid&icrowdkindpriceid&itickettypeid&iztickettypeid&isplitamount&tripid&ivenueareaid&ivenueseatsid&dtstartdate&dtenddate"
     * 明细ID&价格ID&产品ID&子产品ID&数量&竹筏趟次ID&码头ID&座位ID&开始日期&截至日期
     * 产品控制数量:productcontrols=
     * "itickettypeid&controltype&tripid&stdata&soldnumber"
     * 产品编号&控制方式&趟次&日期&销售数量:产品编号&控制方式&趟次&日期&销售数量 Describe:
     *
     * @auth:yuanchengjun
     * @param salesvouchers
     * @param salesvoucherdetails
     * @param comticketsalesdetails
     * @param productcontrols
     * @return return:ResultBean Date:2011-10-29
     */
    private ResultBean saveorder42(String salesvouchers,
                                   String salesvoucherdetails, String comticketsalesdetails,
                                   String productcontrols,String url, String... param) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        Debug.print("My is saveorder42");
        rs.setColumnNames(new String[] { "returnstats", "message" });
        String returnstats = "true";
        String message = "";
        int haveseat = 0;
        try {
            ResultBean cano = null;
            LOGGER.info("saveorder42:开始验证参数完整性");
            if (salesvouchers == null || salesvouchers.equals("")
                    || salesvoucherdetails == null
                    || salesvoucherdetails.equals("")
                    || comticketsalesdetails == null
                    || comticketsalesdetails.equals("")) {
                rs.addRow(new String[] { "false", "数据不完整，不能保存" });
                return rs;
            }
            String[] salesvoucher = salesvouchers.split("&");

            Long iticketwinid = new Long(salesvoucher[1]);

            Long ibusinessid = new Long(salesvoucher[2]);
            Long empid = new Long(salesvoucher[3]); // 出票员ID
            String usid = salesvoucher[7];
            // 判断售出的预制票不能重复 2013-04-15 lijingrui
            String[] salesvoucherdetail = salesvoucherdetails.split(":");
            Edmtickettypetab edmtick = null;
            List<StockOrderInfo> stocks = new ArrayList<StockOrderInfo>();
            String stockJson = "";
            StringBuffer codelist = new StringBuffer();
            if (salesvoucherdetail != null && salesvoucherdetail.length > 0) {
                for (int x = 0; x < salesvoucherdetail.length; x++) {
                    String isalesvoucherdetail = salesvoucherdetail[x];
                    String[] voudeta = isalesvoucherdetail.split("&");
                    Long itickettypeid = new Long(voudeta[2]);
                    Long amount = new Long(voudeta[3]);
                    String bymaketicketway = "00";
                    String szticketprintno = "";
                    edmtick = (Edmtickettypetab) saleCenterService
                            .getEdmtickettypetab(itickettypeid);
                    if (voudeta.length > 6) {
                        bymaketicketway = voudeta[6];
                        if (bymaketicketway.equals("01")) {

                            // 介质类型为 一维条码 二维条码的 是预制票
                            if (edmtick.getBymediatype().equals("00")
                                    || edmtick.getBymediatype().equals("01")) {

                                szticketprintno = voudeta[8];
                                String[] sperialnum = szticketprintno
                                        .split("[|]");
                                if (sperialnum != null && sperialnum.length > 0) {
                                    for (int i = 0; i < sperialnum.length; i++) {
                                        boolean b = saleCenterService.isExistcode(empid, itickettypeid,sperialnum[i].toString());
                                        if (!b) {
                                            rs.addRow(new String[] { "false", "手中无此票号，已出售或者未领用!!!" });
                                            return rs;
                                        }

                                        codelist.append(sperialnum[i] + ",");
                                    }
                                }

                            }
                        }else if(bymaketicketway.equals("03")){//自选票号
                            // 介质类型为 一维条码 二维条码的 是预制票
                            if (edmtick.getBymediatype().equals("00")
                                    || edmtick.getBymediatype().equals("01")) {

                                szticketprintno = voudeta[8];
                                String[] sperialnum = szticketprintno
                                        .split("[|]");
                                if (sperialnum != null && sperialnum.length > 0) {
                                    for (int i = 0; i < sperialnum.length; i++) {
                                        codelist.append(sperialnum[i] + ",");
                                    }
                                }

                            }
                        }
                    }

                    StockOrderInfo stockOrderInfo = new StockOrderInfo();
                    stockOrderInfo.setProviderId(edmtick.getIscenicid());
                    stockOrderInfo.setProductId(itickettypeid);
                    stockOrderInfo.setPriceId(Long.parseLong(voudeta[1]));
                    stockOrderInfo.setUsid(usid);
                    stockOrderInfo.setNumb(amount);
                    stockOrderInfo.setStockDate(voudeta[4].toString());
                    stocks.add(stockOrderInfo);
                }
                //判断库存信息
                StockJson stockObect = new StockJson();
                stockObect.setStocks(stocks);
                stockJson = JSON.toJSONString(stockObect);
                LOGGER.info("saveorder42:开始验证预制票库存");
                if(WebContant.GetKeyValue("IsCenterUrl").equals("1")){
                    try {
                    	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
        				Object[] objects = client.invoke("checkStock", stockJson);
        				cano = (ResultBean) objects[0];
                        // 连接中心判断库存
//                        cano = ssl.checkStock(stockJson);
                        returnstats = cano.getResult(0, 0);
                        if(returnstats.equalsIgnoreCase("false")){
                            rs.addRow(new String[] {"false",cano.getResult(0, 1)});
                            return rs;
                        }
                    } catch (Exception e) {
                        String stockMess = stockService.checkCustomStock(stocks);
                        if(StringUtils.isBlank(stockMess)){
                            stockMess = stockService.checkStock(stocks);
                        }
                        if(!StringUtils.isBlank(stockMess)){
                            rs.addRow(new String[] {"false",stockMess});
                            return rs;
                        }
                    }
                }else{
                    String stockMess = stockService.checkCustomStock(stocks);
                    if(StringUtils.isBlank(stockMess)){
                        stockMess = stockService.checkStock(stocks);
                    }
                    if(!StringUtils.isBlank(stockMess)){
                        rs.addRow(new String[] {"false",stockMess});
                        return rs;
                    }
                }
                String[] code = codelist.toString().split(",");
                for (int i = 0; i < code.length; i++) {
                    String printcode = (String) code[i];
                    for (int j = i + 1; j < code.length; j++) {
                        String endcode = (String) code[j];
                        if (printcode.equals(endcode)) {
                            rs.addRow(new String[] {"false","同一凭证下" + edmtick.getSztickettypename() + "售出的票号不能重复!" });
                            return rs;
                        }
                    }
                }
            }

            String orderseat = "";
            LOGGER.info("saveorder42:开始验证分时预约票库存");
            long tiknumb = 0;
            String[] comticketsalesdetail = comticketsalesdetails.split(":");
            for (int i = 0; i < comticketsalesdetail.length; i++) {
                String[] zdetail = comticketsalesdetail[i].split("&");
                Long isplitamount = new Long(zdetail[4]);
                if(zdetail.length == 14) {
                	//更新分时时段库存
                	//TimeSharingTicketTab 
                	long timeId = Long.parseLong(zdetail[12]);
                	String productId = zdetail[13];
                	String hql = "select new map(t.tatalStock as totalStock,t.currStock as currStock,t.saleStock as saleStock) from TimeSharingTicketTab t where t.id = "+ timeId +" and t.productId = '"+productId+"'";
                	List<Map> find = stockService.find(hql);
                	if(find != null && find.size() > 0) {
                		Map timeSharingTicketTab = find.get(0);
                		int currStock = Integer.parseInt(timeSharingTicketTab.get("currStock").toString());
                		int saleStock = Integer.parseInt(timeSharingTicketTab.get("saleStock").toString());
                		int totalStock=Integer.parseInt(timeSharingTicketTab.get("totalStock").toString());
                		if(totalStock!=-1 && (currStock-isplitamount) < 0) {
                			rs.addRow(new String[] { "false", "分时预约票库存不足" });
                            return rs;
                		}
                		
                	}
                }
                Long iprogramid = 0L;
                if (zdetail.length >= 11) {
                    iprogramid = new Long(zdetail[10]);
                }
                if (zdetail.length >= 12 && zdetail.length != 14) {
                    if (!zdetail[10].equals("null") && !zdetail[10].equals("NULL")) {
                        Venueprogram v = saveVenueService.getprogram(iprogramid);
                        if (v!=null &&  v.getBycashsaletype() == 0) {
                            rs.addRow(new String[] { "false", "该节目不能现金售票" });
                            return rs;
                        }

                        String seats = zdetail[11];
                        if (iprogramid > 0) {
                            // 存在剧院票
                            tiknumb = tiknumb + isplitamount.longValue();
                            if (seats != null) {
                                String[] seat = seats.split(">");
                                haveseat = haveseat + seat.length;
                                if (seat.length != isplitamount.longValue()) {
                                    rs.addRow(new String[] { "false","预定数量与座位数不等" });
                                    return rs;
                                }
                            }
                        }

                    }
                }
                
            }

            if (haveseat > 0) {
                if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                    	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
        				Object[] objects = client.invoke("updateseatstuts", comticketsalesdetails);
        				cano = (ResultBean) objects[0];
                        // 预留量更新
//                        cano = ssl.updateseatstuts(comticketsalesdetails);
                        returnstats = cano.getResult(0, 0);
                    } catch (Exception e) {
                        Debug.print("e=" + e.toString());
                        System.out.println(e.getMessage());
                        rs.addRow(new String[] { "false", "连接中心服务器失败，不能保存订单" });
                        return rs;
                    }
                }
            }

            Debug.print("验证票数与座位数3");
            if (returnstats.equals("false")) {
                rs.addRow(new String[] { "false", cano.getResult(0, 1) });
                return rs;
            } else {
                try {

                    Long maxid = saleCenterService.updatevouchersequence();
                    Esbticketwintab esbticketwintab = saleCenterService
                            .getEsbticketwintab(iticketwinid);
                    int ticketstation = esbticketwintab.getIticketstationid()
                            .intValue();
                    String szticketstation = "";
                    if (0 < ticketstation && ticketstation < 10) {
                        szticketstation = "00" + ticketstation;
                    } else if (ticketstation >= 10 && ticketstation < 100) {
                        szticketstation = "0" + ticketstation;
                    } else {
                        szticketstation = "" + ticketstation;
                    }
                    String szsalesvoucherno = saleCenterService
                            .updateMaxNo(szticketstation);
                    if(StringUtil.isEmpty(szsalesvoucherno)) {
                    	LOGGER.info("生成订单号失败");
                    	rs.addRow(new String[] { "false","生成订单号失败"});
                    	return rs;
                    }
                    rs = saveVenueService.saveorder42(salesvouchers,
                            salesvoucherdetails, comticketsalesdetails, maxid,
                            szsalesvoucherno,url, param);
                    System.out.println("42保存结束");
                    returnstats = rs.getResult(0, 0);
                    LOGGER.info("saveorder42:保存订单状态"+returnstats);
                    if (returnstats.equals("true")) {
                        return rs;
                    } else {
                        if (haveseat > 0) {
                            if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                                try {
                                	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                    				Object[] objects = client.invoke("updatehfseatstuts", comticketsalesdetails);
                    				// 预留量更新
                    				cano = (ResultBean) objects[0];
                                    /*// 预留量更新
                                    cano = ssl.updatehfseatstuts(comticketsalesdetails);*/

                                    returnstats = cano.getResult(0, 0);
                                } catch (Exception e2) {
                                    System.out.println(e2.getMessage());
                                    rs.addRow(new String[] { "false",
                                            "连接中心服务器失败，不能保存订单" });
                                    return rs;
                                }
                            }
                        }
                        message = "保存失败，与管理员联系。";
                        rs.addRow(new String[] { returnstats, message });
                        return rs;

                    }

                } catch (Exception e) {
                	LOGGER.info(StringUtil.toString_02(e));
                    message = "保存出错，与管理员联系。";
                    if (haveseat > 0) {
                        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                            try {
                            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
                				Object[] objects = client.invoke("updatehfseatstuts", comticketsalesdetails);
                				// 预留量更新
                				cano = (ResultBean) objects[0];
                                // 预留量更新
                                /*cano = ssl
                                        .updatehfseatstuts(comticketsalesdetails);*/

                                returnstats = cano.getResult(0, 0);
                            } catch (Exception e2) {
                                System.out.println(e2.getMessage());
                                rs.addRow(new String[] { "false",
                                        "连接中心服务器失败，不能保存订单" });
                                return rs;
                            }
                        }
                    }
                    rs.addRow(new String[] { "false", e.getMessage() + message });
                    return rs;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("returnstats= " + returnstats);
            rs.addRow(new String[] { "fale", "保存失败" });
            return rs;
        }

    }

    /**
     * param 参数 orderlistid,szticketprintno#orderlistid,szticketprintno
     * Describe:
     *
     * @auth:yuanchengjun
     * @param orid
     * @param iscenicid
     * @param iticketwinid
     * @param iemployeeid
     * @param param
     * @return return:ResultBean Date:2012-3-28
     */
    @SuppressWarnings("unchecked")
	private ResultBean savetorder42(String orid, Long iscenicid,
                                    Long iticketwinid, Long iemployeeid, String param, String param1,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        String message = "";
        Map m = new HashMap();
        List printnolist = new ArrayList();// 激活票号list
        if (!param.equals("")) {
            String[] pn = param.split("#");
            for (int i = 0; i < pn.length; i++) {
                String[] pzn = pn[i].split(",");
                String[] s2 = pzn[1].split("[|]");

                for (int j = 0; j < s2.length; j++) {
                    if (m.containsKey(s2[j])) {
                        message = "预制票号重复,请重新输入!";
                        rs.addRow(new String[] { "false", message });
                        return rs;
                    } else {
                        m.put(s2[j], null);
                    }
                }

                TOrderlist t = new TOrderlist();
                t.setOrderlistid(pzn[0]);
                t.setOrid(pzn[1]);// 票号存在这个字段
                printnolist.add(t);
            }
        }
        Long maxid = saleCenterService.updatevouchersequence();
		T_order t_order = null;
		List<T_orderlist> listorder = new ArrayList<T_orderlist>();
		List<T_zorderlist> listzorder = new ArrayList<T_zorderlist>();
		List<Trealname> Trealnamelist = new ArrayList<Trealname>();
			try {
				/*Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("chupiaoT_order", orid, iscenicid);
				ResultBean cano = (ResultBean) objects[0];*/
				ResultBean cano = saleCenterService.ChupiaoT_order(orid, iscenicid);
				if (cano.getRowsCount() > 0) {
					for (int i = 0; i < cano.getRowsCount(); i++) {

						Map returnMap = new HashMap();
						String[] ss = cano.getColumnNames();
						for (int j = 0; j < ss.length; j++) {
							returnMap.put(ss[j].toLowerCase(), cano.getResult(i, ss[j]));

						}
						t_order = (T_order) saleCenterService.convertMap(T_order.class, returnMap);

						String dtmakedate = t_order.getDtmakedate();
						Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date d1 = df.parse(dtmakedate);
						Calendar begcalendar = Calendar.getInstance();
						begcalendar.setTime(d1);
						Sysparv5 sys = saveVenueService.Getsyspar("CPJG", "01");
						String minutimes = "";
						if (sys != null && !sys.getPmva().equals("")) {
							minutimes = sys.getPmva();
							begcalendar.add(Calendar.MINUTE, Integer.parseInt(minutimes));
							if (now.before(begcalendar)) {
								System.out.println("未到出票时间,请于" + Tools.getDayTimes(begcalendar) + "取票");
								message = "未到出票时间,请于" + Tools.getDayTimes(begcalendar) + "后取票";
								rs.addRow(new String[] { "false", message });
								return rs;
							}
						}
						if (t_order.getDdzt().equals("11")) {
							message = "订单已出票，无需再次出票";
							rs.addRow(new String[] { "false", message });
							return rs;
						}
						if (t_order.getDdzt().equals("00")) {
							if (param1.equals("")) {
								message = "前台现付订单应传入支付方式和金额";
								rs.addRow(new String[] { "false", message });
								return rs;
							}
						}

						Custom c = ecService.getCustomByUserId(t_order.getUsid());
						Sysparv5 s = sysService.findOne("COMM", "0007");
						if (s == null) {
							s = new Sysparv5();
							s.setIsvalue(1L);
						}
						/*if (c == null) {
							// 下载custom
							if (s.getIsvalue() == 1L) {
								try {
									c = CommonUtil.xiazaiCustom(t_order.getUsid(), url);
									if (c == null) {
										message = "请先下载中心用户后出票";
										rs.addRow(new String[] { "false", message });
										return rs;
									}
								} catch (Exception e) {
									message = "请先下载中心用户后出票";
									rs.addRow(new String[] { "false", message });
									return rs;
								}
							} else {
								message = "请先下载中心用户后出票";
								rs.addRow(new String[] { "false", message });
								return rs;
							}
						}*/
					}

				} else {
					message = "订单不存在";
					rs.addRow(new String[] { "false", message });
					return rs;
				}
				/*Object[] objectsOrderlist = client.invoke("chupiaoT_orderlist", orid, iscenicid);
				cano = (ResultBean) objects[0]*/;
				// cano = ssl.chupiaoT_orderlist(orid, iscenicid);
				cano = saleCenterService.ChupiaoT_orderlist(orid, iscenicid);
				if (cano.getRowsCount() > 0) {
					for (int i = 0; i < cano.getRowsCount(); i++) {

						Map returnMap = new HashMap();
						String[] ss = cano.getColumnNames();
						for (int j = 0; j < ss.length; j++) {
							returnMap.put(ss[j].toLowerCase(), cano.getResult(i, ss[j]));

						}
						T_orderlist t1 = (T_orderlist) saleCenterService.convertMap(T_orderlist.class, returnMap);

						Edmtickettypetab eticket = saleCenterService
								.getEdmtickettypetab(new Long(t1.getItickettypeid()));
						t1.setBymaketicketway(eticket.getBymaketicketway());
						if (eticket.getBymaketicketway().equals("01")) {

							// 李经锐 2012-09-06修改 增加判断 介质类型
							if (eticket.getBymediatype().equals("00") || eticket.getBymediatype().equals("01")) {
								// 现场激活 介质类型为 一维条码 二维条码的 是预制票
								boolean b = false;
								for (int k = 0; k < printnolist.size(); k++) {
									TOrderlist to = (TOrderlist) printnolist.get(k);
									if (to.getOrderlistid().equals(t1.getOrderlistid())) {
										t1.setSzticketprintno(to.getOrid());
										b = true;
									}
								}
								if (!b) {
									rs.addRow(new String[] { "false", "现场激活票必须填写初始票号" });
									return rs;
								}
							}
						}
						listorder.add(t1);

					}
				} else {
					message = "订单明细不存在";
					rs.addRow(new String[] { "false", message });
					return rs;
				}
				/*Object[] objectsZorderlist = client.invoke("chupiaoT_zorderlist", orid, iscenicid);
				cano = (ResultBean) objects[0];*/
				// cano = ssl.chupiaoT_zorderlist(orid, iscenicid);
				cano = saleCenterService.ChupiaoT_zorderlist(orid, iscenicid);
				if (cano.getRowsCount() > 0) {
					for (int i = 0; i < cano.getRowsCount(); i++) {
						Map returnMap = new HashMap();
						String[] ss = cano.getColumnNames();
						for (int j = 0; j < ss.length; j++) {
							returnMap.put(ss[j].toLowerCase(), cano.getResult(i, ss[j]));
						}
						T_zorderlist t2 = (T_zorderlist) saleCenterService.convertMap(T_zorderlist.class, returnMap);
						listzorder.add(t2);
					}
				} else {
					message = "订单子明细不存在";
					rs.addRow(new String[] { "false", message });
					return rs;
				}
				/*Object[] objectsTRealname = client.invoke("chupiaoTRealname", orid, iscenicid);
				cano = (ResultBean) objects[0];*/
				// cano = ssl.chupiaoTRealname(orid, iscenicid);
				cano = saleCenterService.ChupiaoTRealname(orid, iscenicid);
				if (cano.getRowsCount() > 0) {
					for (int i = 0; i < cano.getRowsCount(); i++) {
						Map returnMap = new HashMap();
						String[] ss = cano.getColumnNames();
						for (int j = 0; j < ss.length; j++) {
							returnMap.put(ss[j].toLowerCase(), cano.getResult(i, ss[j]));
						}
						Trealname tr = (Trealname) saleCenterService.convertMap(Trealname.class, returnMap);

						Trealnamelist.add(tr);
					}
				}
			} catch (Exception e) {
				LOGGER.error(StringUtil.toString_02(e));
				rs.addRow(new String[] { "false", "连接中心服务器失败，不能读取订单" });
				return rs;
			}

			try {
				LOGGER.debug("保存线下订单开始");;
				ResultBean rs1 = saveVenueService.savetorder42(t_order, listorder, listzorder, iemployeeid,
						iticketwinid, maxid, Trealnamelist, param1);
				if (rs1.getResult(0, 0).equals("false")) {
					rs.addRow(new String[] { rs1.getResult(0, 0), rs1.getResult(0, 1) });
					return rs;
				} else {
					try {
						/*Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url,
								ServerNameConst.SALESERVICE);
						Object[] objects = client.invoke("updateT_order", orid, iscenicid, iemployeeid,
								new Double(t_order.getZfmont()));
						ResultBean cano = (ResultBean) objects[0];*/
						ResultBean cano = saleCenterService.updateT_order(orid, iscenicid, iemployeeid,new Double(t_order.getZfmont()));
						if (cano.getResult(0, 0).equals("false")) {
							rs.addRow(new String[] { "false", "本地出票成功,更新网上订单信息状态失败,请联系中心管理员" });
							return rs;
						}
						rs.addRow(new String[] { rs1.getResult(0, 0), rs1.getResult(0, 1) });
						try {
							Orderlog log = new Orderlog();
							log.setOrid(orid);
							log.setIemployeeid(iemployeeid);
							log.setStlg("0186");
							log.setLogtype(new Long(2));
							log.setLogdatetime(Tools.getDayTimes());
							log.setNote("售票口出票");
							log.setBrief("售票口出票");
							Long id = sysService.getMaxPk("logid", "Orderlog");
							log.setLogid(id + 1);
							sysService.insertOrderlog(log);
						} catch (Exception e) {
						}
						return rs;
					} catch (Exception e) {
						LOGGER.error(StringUtil.toString_02(e));
						rs.addRow(new String[] { "false", "本地出票成功,更新网上订单信息状态失败,请联系中心管理员" });
						return rs;
					}
				}
			} catch (Exception e) {
				LOGGER.error(StringUtil.toString_02(e));
				try {
					saleCenterService.updatecphfT_order(orid, iscenicid);
				} catch (Exception e1) {
					LOGGER.error("updatecphfT_order 执行失败"+StringUtil.toString_02(e));;
				}
				rs.addRow(new String[] { "false", "保存失败" });
				return rs;
			}
    }

    public String Getvenue(Long ivenueid) {
        
        ResultBean rs =  saveVenueService.Getvenue(ivenueid);
        return JSON.toJSONString(rs);
    }

    public String getprogrambyproductid(Long iproductid, String stdt,String url) {
        
        ResultBean rs = saveVenueService.getprogrambyproductid(iproductid, stdt,url);
        return JSON.toJSONString(rs);
    }

    public String getprogram(Long iscenicid, String stdt,String url) {
        
			ResultBean rs = saveVenueService.getprogram(iscenicid, stdt,url);
			return JSON.toJSONString(rs);
    }

    public String Getseatstusts(Long ivenueid, String stdt, Long tripid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getseatstusts", ivenueid, stdt, tripid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                System.out.println("cano.getRowsCount()=" + cano.getRowsCount());
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
            ResultBean rb = saveVenueService.Getseatstusts(ivenueid, stdt, tripid);
            return JSON.toJSONString(rb);
        }
    }

    public String getseat(Long ivenueid) {
       
        ResultBean rs = saveVenueService.Getseat(ivenueid);
        return JSON.toJSONString(rs);
    }
    
    public String Getareapricve(Long Itripprdcontrolid, String stdt,
                                    String groupid, Long ibusinessid) {
        
        ResultBean rs = saveVenueService.Getareapricve(Itripprdcontrolid, stdt, groupid,
                ibusinessid);
        return JSON.toJSONString(rs);
    }
    
    public String GetareapricveByIvenueareaid(Long Itripprdcontrolid, String stdt,
                                    String groupid, Long ibusinessid, Long ivenueareaid) {
        
		ResultBean rs = saveVenueService.Getareapricve(Itripprdcontrolid, stdt, groupid, ibusinessid, ivenueareaid);
		return JSON.toJSONString(rs);
    }

    public String Getareaseatstusts(Long ivenueid, Long ivenueareaid,
                                        String stdt, Long tripid,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getareaseatstusts", ivenueid, ivenueareaid, stdt, tripid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
			ResultBean rs = saveVenueService.Getareaseatstusts(ivenueid, ivenueareaid, stdt, tripid);
			return JSON.toJSONString(rs);
        }
    }

    public String Getseatlocklist(String date,String url) {
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("getseatlocklist", date);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
            ResultBean rs = saveVenueService.Getseatlocklist(date);
            return JSON.toJSONString(rs);

        }
    }

    public String checkEdpofferschemetab(String offerschme) {
        
        ResultBean rs = saveVenueService.checkEdpofferschemetab(offerschme);
        return JSON.toJSONString(rs);
    }

    private static String orderDecode(String in) {
        String temp = in;
        try {
            if (temp != null) {
                if (temp.startsWith("ECT_")) {
                    temp = DesUtil.decrypt(in.split("[,]")[0].substring(4),
                            WebContant.GetKeyValue("qrcodemiyue"));
                } else if (temp.startsWith("CYT_")) {
                    temp = DesUtil.decrypt(in.split("[,]")[0], ConstUtils.KEY);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     *
     * Describe:获取接待用户的控制量
     *
     * @author:lijingrui
     * @param usid
     * @param iscenicid
     * @return return:ResultBean Date:2015-1-5
     */
    public String queryStockWarenumb(String usid, String iscenicid) {
        
        ResultBean rs = saveVenueService.queryStockWarenumb(usid, iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:根据票号获取指纹信息
     *
     * @author:lijingrui
     * @return return:ResultBean Date:2015-1-15
     */
    public String checkTicketzhiwe(String szticketprintno) {
       
        ResultBean rs = saveVenueService.checkTicketzhiwe(szticketprintno);
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:根据身份证号码获取导游计时票信息
     *
     * @author:lijingrui
     * @param zjhm
     * @return return:ResultBean Date:2015-2-4
     */
    public String queryListzjhmPrint(String zjhm) {
        
        ResultBean rs = saveVenueService.queryListzjhmPrint(zjhm);
        return JSON.toJSONString(rs);
    }

    /**
     *
     * Describe:保存导游截止日期 或者录入指纹
     *
     * @author:lijingrui
     * @param szsoldticketid
     * @param isalesvoucherid
     * @param isalesvoucherdetailsid
     * @param iticketstationid
     * @param zjhm
     * @param bsfilebinary
     * @return return:ResultBean Date:2015-2-10
     */
	public String checkSaveprintStssold(String szsoldticketid, String isalesvoucherid,
			String isalesvoucherdetailsid, String iticketstationid, String zjhm, String bsfilebinary) {

		ResultBean rs = saveVenueService.checkSaveprintStssold(szsoldticketid, isalesvoucherid, isalesvoucherdetailsid,
				iticketstationid, zjhm, bsfilebinary);
		return JSON.toJSONString(rs);
	}

    public String queryCytOrder(String queryMessage,Long iscenicid){
    	
    	ResultBean rs = cytSaleMainService.queryCytOrder(queryMessage, iscenicid, 1);
        return JSON.toJSONString(rs);
    }

    public String queryCytOrderDetail(String orid,Long iscenicid) throws Exception{
    	
    	ResultBean rs = cytSaleMainService.queryOrderDetail(orid, iscenicid,1);
        return JSON.toJSONString(rs);
    }

    public String consumeOrder(String orid, Long iscenicid,Long iticketwinid, Long iemployeeid, String sign,
                                   Long numb,String yzp,String cash){
        
        ResultBean rs = cytSaleMainService.consumeOrder(orid, iscenicid, iticketwinid, iemployeeid, sign, numb,1, yzp, cash);
        return JSON.toJSONString(rs);
    }

    /**
     * Describe: ResultBean不适合Android,新增统一接口
     * @author liujianwen
     * @param data
     * @return
     * return:String
     * Date:2016-4-29
     */
    public String work(String data){
        return CoreDistribution.distribution(data);
    }
    /**
      * 取票前部分退订
	  * @param type  类型
	  * @param torderlists 原订单列表
	  * @param newticketinfo 新订单列表
	  * @param user 用户ID
	  * @param orid 订单号
	  * @param iscenicid 景区服务商
	  * @author lizhaodong  2017-04-27 添加
	  * @return
	  */
    @SuppressWarnings("unchecked")
	public String debookTicket(String type,String torderlistStr,String newticketinfoStr,String user,String orid,String iscenicid,String url){
    	if(url==null||url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
    	List<TOrderlist> torderlists=new ArrayList<TOrderlist>();
    	List<OrderPojo> newticketinfo = null ;//= new ArrayList<OrderPojo>();
    	ResultBean rs = new ResultBean();
    	torderlists= JSON.parseArray(torderlistStr,TOrderlist.class);//原订单数据以数据库为准
    	newticketinfo = JSON.parseArray(newticketinfoStr,OrderPojo.class);
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
       /* ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");*/
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {
            	Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("debookTicket", type, torderlistStr,newticketinfoStr,user,orid,iscenicid);
				ResultBean cano = (ResultBean) objects[0];
                ResultBean rb = new ResultBean();
                if (cano.getRowsCount() > 0) {

                    rb.setColumnCount(cano.getColumnCount());
                    rb.setColumnNames(cano.getColumnNames());
                    rb.setColumnCount(cano.getColumnCount());
                    rb.setRowsCount(cano.getRowsCount());
                    for (int i = 0; i < cano.getRowsCount(); i++) {
                        String[] values = new String[cano.getColumnCount()];
                        for (int j = 0; j < cano.getColumnCount(); j++) {
                            values[j] = cano.getResult(i, j);
                        }
                        rb.addRow(values);
                    }
                }
                return JSON.toJSONString(rb);
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        }else
        {

            /*IGenericService genericService = (IGenericService) SpringUtil
                    .getBean("genericService");
            IOrderService  orderService =(IOrderService) SpringUtil.
                    getBean("orderService");
            ITicketService ticketService=(ITicketService)SpringUtil.
                    getBean("ticketService");;
            IShopCartService shopcartService=(IShopCartService) SpringUtil.getBean("shopcartService");*/
            if(orid==null||orid.equals("")){
                rs.addRow(new String[] { "false","订单编号不能为空" });
            }
            if(iscenicid==null||iscenicid.equals("")){
                rs.addRow(new String[] { "false","服务商编号不能为空" });
            }
            MOrder morder = ecService.getMorderInfo(orid);
            if(morder==null)
            {
                rs.addRow(new String[] { "false","订单不存在" });
                return JSON.toJSONString(rs);
            }
		   TOrder order = ecService.getTOrderInfo(orid, iscenicid);
		   if(order != null && order.getIschupiao() != null && order.getIschupiao() == 1L){
	            rs.addRow(new String[] { "false","窗口锁定订单不可修改，请联系工作人员解锁" });
				return JSON.toJSONString(rs);
	        }
            Map  returnmap = new HashMap();
            if(type.equals("01"))//散客部分退订
            {
                Custom custom =(Custom)ticketTypeService.get(Custom.class, morder.getUsid());
                // returnmap 键result代表验证状态,false表示不通过,true表示通过.
                // result值为false时,errtp值表示错误类型.
                // errtp:0代表日控制不足，即票已售完,1代表生产量不足，也可以认为票数量不满足当前预定的数量.当状态为0、1时，键errlist对应的值记录着错误的详细信息
                // errtp:2代表用户剩余预付款不足以支付当前订单修改以后要支付的金额,3代表订单已经超过可修改时间
                // 4代表订单状态为不可修改状态,5代表订单无修改 6代表信誉度超过预定趟次积分为正数的排
                // errlist中对象属性errtp:0代表停排，1数量不足，2已售完，3暂时不可销售
                returnmap.clear();
                if (!morder.getDdzt().equals("02")) {
                    rs.addRow(new String[] { "false","订单状态不为已支付" });
                    return JSON.toJSONString(rs);
                } else {
                    List tlist = ecService.showscenicidview(orid,iscenicid);
                    if (tlist != null && tlist.size() > 0) {
                        for (int i = 0; i < tlist.size(); i++) {
                            TOrder td = (TOrder) tlist.get(i);
                            if (td.getScenictype().equals("01")) {
                                try {
						    List  tlist2 = ecService.getTOrderlists(orid, Long.valueOf(iscenicid));
					        TOrder t;
					        for (int j = 0; j < tlist2.size(); j++) {
					            t = new TOrder();
					            BeanUtils.populate(t, (Map) tlist2.get(j));
					            List tlistlist = ecService.getTOrderlists(t.getOrid(),
					                    Long.valueOf(t.getIscenicid()));
					            TOrderlist tt = null;
					            for (int k = 0; k < tlistlist.size(); k++) {
					                tt = new TOrderlist();
					                BeanUtils.populate(tt, (Map) tlistlist.get(k));
					                if (tt.getIoffersschemeid() != null
					                        && !tt.getIoffersschemeid().equals("")
					                        && tt.getIoffersschemeid().intValue() > 0) {
					                    Edpofferschemetab schema = (Edpofferschemetab) 
					                    		ticketTypeService.get(Edpofferschemetab.class,
					                                    tt.getIoffersschemeid());
					                    tt.setBasenum(schema.getImultiples().intValue());
					                    tt.setOffernum(schema.getIoffernum().intValue());
					                }
					                torderlists.add(tt);
					            }
					        }	
                                    // 验证退订后的订单是否满足各种可修改的条件
                                    returnmap = ecService.validateOrderInfo(orid,torderlists, newticketinfo, morder.getZfusid(), morder.getStdt(), custom.getIbusinessid().toString(), iscenicid);
                                    Boolean result = (Boolean) returnmap.get("result");
                                    if (result.booleanValue()) {
                                        String[] orids = new String[2];
                                        orids[0] = ecService.getMaxNo("000");
                                        orids[1] = ecService.getMaxNo("000");
                                        Map<String,Object> oridsMap = new HashMap<String,Object>();
                                        oridsMap.put("orids", orids);
                                        returnmap = ecService.editOrderSankeCenter(JSON.toJSONString(torderlists), JSON.toJSONString(newticketinfo), JSON.toJSONString(oridsMap), orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), morder.getUsid());
                                        for (int j = 0; j < orids.length; j++) {
                                            MOrder editmorder = (MOrder) ticketTypeService.get(MOrder.class, orids[j]);// 获取修改的订单
                                            if (editmorder != null) {
                                                Orderlog log = new Orderlog();
                                                log.setLogid(sysService.getMaxPk("logid", "Orderlog") + 1);
                                                log.setOrid(orid);
                                                log.setStlg("0158");
                                                log.setBrief("网上订单出票前退订订单");
                                                log.setNote("");
                                                List editlist = ecService.getYOrderListChildList(orids[j], iscenicid);
                                                if (editlist != null && editlist.size() > 0) {
                                                    if (editmorder.getOrtp().equals("02")) {
                                                        if (log.getNote() != null && !log.getNote().trim().equals("")) {
                                                            log.setNote("</br>");
                                                        }
                                                        log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
                                                    } else if (editmorder.getOrtp().equals("03")) {
                                                        if (log.getNote() != null && !log.getNote().trim().equals("")) {
                                                            log.setNote("</br>");
                                                        }
                                                        log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
                                                    }
                                                    for (int x = 0; x < editlist.size(); x++) {
                                                        Map editMap = (Map) editlist.get(x);
                                                        System.out.println("map value" + editMap);
                                                        log.setNote(log.getNote() + "[" + editMap.get("szscenicname").toString() + "]&nbsp;&nbsp;[" + editMap.get("sztickettypename").toString()
                                                                + "]&nbsp;&nbsp;[" + editMap.get("szcrowdkindname").toString() + "]&nbsp;&nbsp;" + editMap.get("numb").toString() + "张");
                                                    }
                                                }
                                                log.setIemployeeid(null);
                                                log.setUsid(morder.getUsid());
                                                log.setLogtype(Long.parseLong("0"));
                                                log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
                                                sysService.insertOrderlog(log);
                                            }
                                        }
                                    }else
                                    {
                                        rs.addRow(new String[] { "false","退订失败" });
                                        return JSON.toJSONString(rs);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    rs.addRow(new String[] { "false","服务器错误" });
                                    return JSON.toJSONString(rs);
                                }
                            }
                        }
                        rs.addRow(new String[] { "true","退订成功" });
                        return JSON.toJSONString(rs);
                    }else
                    {
                        List list = ecService.showscenicidview(orid);
                        if(list!=null)
                        {
                            rs.addRow(new String[] { "false","订单服务商不一致" });
                            return JSON.toJSONString(rs);
                        }else
                        {
                            rs.addRow(new String[] { "false","订单数据不存在" });
                            return JSON.toJSONString(rs);
                        }
                    }
                }
            }else if(type.equals("02"))//旅行社部分退订
            {
                returnmap.clear();
//                Custom custom =(Custom)ticketTypeService.get(Custom.class, morder.getUsid());
                Custom custom = ecService.getCustomByUserId(morder.getUsid());
                Custom orderuser = ecService.getCustomByUserId(morder.getUsid());
                // returnmap 键result代表验证状态,false表示不通过,true表示通过.
                // result值为false时,errtp值表示错误类型.
                // errtp:0代表日控制不足，即票已售完,1代表生产量不足，也可以认为票数量不满足当前预定的数量.当状态为0、1时，键errlist对应的值记录着错误的详细信息
                // errtp:2代表用户剩余预付款不足以支付当前订单修改以后要支付的金额,3代表订单已经超过可修改时间
                // 4代表订单状态为不可修改状态,5代表订单无修改 6代表信誉度超过预定趟次积分为正数的排7:积分不足8:
                // errlist中对象属性errtp:0代表停排，1数量不足，2已售完，3暂时不可销售
                if (!morder.getDdzt().equals("02")) {
                    rs.addRow(new String[] { "false","订单状态不为已支付" });
                    return JSON.toJSONString(rs);
                } else {

                    if (newticketinfo != null && newticketinfo.size() > 0
                            && morder.getIsjl() != null
                            && morder.getIsjl().intValue() == 1) {
                        rs.addRow(new String[] { "false","奖励订单不可以新增票种" });
                    } else {
                        // 获取价格分组
                        try{
                            String groupno = ecService.searchJgfz(orderuser.getUsid(),
                                    Long.parseLong(iscenicid));
                            returnmap = ecService.validateInfoIntegrity(newticketinfo,
                                    orderuser.getIbusinessid().toString(), iscenicid, orid,
                                    groupno);

                            if (returnmap != null && returnmap.size() > 0) {
                                rs.addRow(new String[] {"false","退订失败"});
                                return JSON.toJSONString(rs);
                            }
                            if (newticketinfo != null) {
                                for (int x = 0; x < newticketinfo.size(); x++) {
                                    OrderPojo newproduct = newticketinfo.get(x);
                                    Edmtickettypetab ticket = (Edmtickettypetab)ticketTypeService
                                            .get(Edmtickettypetab.class, Long
                                                    .parseLong(newproduct
                                                            .getItickettypeid()));
                                    if (ticket.getIscenicid().intValue() != Integer
                                            .parseInt(iscenicid)) {
                                        rs.addRow(new String[] { "false","参数错误" });
                                        return JSON.toJSONString(rs);
                                    }
                                }
                            }
                            if (newticketinfo != null) {
                                for (TOrderlist t : torderlists) {
                                    /*TOrderlist orderlist = (TOrderlist) ticketTypeService.get(
                                            TOrderlist.class,
                                            new TOrderlistId(Long.parseLong(t
                                                    .getOrderlistid()), orid, Long
                                                    .parseLong(iscenicid)));*/
                                    TOrderlist orderlist = ecService.getTOrderList(t
                                            .getOrderlistid(), orid, Long
                                            .parseLong(iscenicid));
                                    t.setItickettypeid(orderlist.getItickettypeid());
                                    t.setIcrowdkindid(orderlist.getIcrowdkindid());
                                    for (OrderPojo o : newticketinfo) {
                                        if (t.getItickettypeid().intValue() == Integer
                                                .parseInt(o.getItickettypeid())
                                                && Integer.parseInt(o.getIcrowdkindid()) == t
                                                .getIcrowdkindid().intValue()) {
                                            if (o.getTourdate() == null
                                                    || o.getTourdate().equals("")) {
                                                t.setNumb(new Long(t.getNumb().intValue()
                                                        + Integer.parseInt(o.getNumb())));
                                                newticketinfo.remove(o);
                                                break;
                                            } else {
                                                List<TZorderlist> tzlist = ecService
                                                        .getTZorderlist(Long.parseLong(t
                                                                        .getOrderlistid()), orid,
                                                                Long.parseLong(iscenicid));
                                                // 且newTicket有浏览日期(有浏览日期说明票含竹筏)
                                                // 且浏览日期相同
                                                Productcontrol tripinfo = (Productcontrol) ticketTypeService
                                                        .get(Productcontrol.class,
                                                                Long.parseLong(o
                                                                        .getProductcontrolid()));
                                                boolean isadd = false;
                                                //比较子 票的趟次编号和新增票的趟次编号
												for (TZorderlist zlist : tzlist) {// 循环有差异的子票列表
													if (zlist.getTripid() != null && zlist.getTripid() != 0
															&& tripinfo.getTripid().intValue() == zlist.getTripid()
																	.intValue()
															&& t.getIcrowdkindid().intValue() == Integer
																	.parseInt(o.getIcrowdkindid())
															&& tripinfo.getStdata().equals(o.getTourdate())) {
														t.setNumb(t.getNumb() + Long.parseLong(o.getNumb()));
														t.setAmnt(t.getAmnt()
																+ Integer.parseInt(o.getNumb()) * orderlist.getPric());
														isadd = true;
													}
												}
                                                if (isadd) {
                                                    newticketinfo.remove(o);
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            Long ydznum = 0L; // 预订总数量
                            // 判断产品库存控制
                            List<Object[]> jectlist = new ArrayList<Object[]>();
                            /*TOrder torder = (TOrder)ticketTypeService.get(TOrder.class,
                                    new TOrderId(orid, Long.parseLong(iscenicid)));*/
                            TOrder torder = ecService.getTOrderInfo(orid, iscenicid);
                            for (TOrderlist t : torderlists) {
                                /*TOrderlist orderlist = (TOrderlist) ticketTypeService.get(
                                        TOrderlist.class,
                                        new TOrderlistId(
                                                Long.parseLong(t.getOrderlistid()), orid,
                                                Long.parseLong(iscenicid)));*/
                                TOrderlist orderlist = ecService.getTOrderList(t
                                        .getOrderlistid(), orid, Long
                                        .parseLong(iscenicid));
                                if(orderlist==null)
                                {
                                    rs.addRow(new String[] { "false","请到对应服务商窗口退票" });
                                    return JSON.toJSONString(rs);
                                }

                                // 价格表中 是否允许成团人数控制 当为是(1)的时候 可控制
                                Edmcrowdkindpricetab crowprice = (Edmcrowdkindpricetab)ticketTypeService
                                        .get(Edmcrowdkindpricetab.class,
                                                orderlist.getIcrowdkindpriceid());
                                if (crowprice != null && crowprice.getInote3() != null
                                        && crowprice.getInote3() == 1) {
                                    ydznum += t.getNumb();// 获取预订数量
                                }

                                if (t.getNumb() - orderlist.getNumb() == 0) {
                                    continue;
                                }
                                Object[] obj = new Object[] {
                                        torder.getId().getIscenicid(),
                                        orderlist.getItickettypeid(),
                                        t.getNumb() - orderlist.getNumb(),
                                        torder.getDtstartdate(), torder.getDtenddate() };
                                jectlist.add(obj);

                            }
                            if (newticketinfo != null && newticketinfo.size() > 0) {
                                for (int x = 0; x < newticketinfo.size(); x++) {
                                    OrderPojo newproduct = newticketinfo.get(x);
                                    Object[] obj = new Object[] {
                                            torder.getId().getIscenicid(),
                                            newproduct.getItickettypeid(),
                                            newproduct.getNumb(), torder.getDtstartdate(),
                                            torder.getDtenddate() };
                                    jectlist.add(obj);

                                    // 价格表中 是否允许成团人数控制 当为是(1)的时候 可控制
                                    Edmcrowdkindpricetab crowprice = (Edmcrowdkindpricetab)ticketTypeService
                                            .get(Edmcrowdkindpricetab.class, newproduct
                                                    .getIcrowdkindpriceid().longValue());
                                    if (crowprice != null && crowprice.getInote3() != null
                                            && crowprice.getInote3() == 1) {
                                        ydznum += Long.parseLong(newproduct.getNumb());// 获取预订数量
                                    }

                                }
                            }

                            Custom cstm = (Custom) ticketTypeService.get(Custom.class,
                                    morder.getUsid());

                            /*List list = shopcartService.findList(Long.parseLong(iscenicid),
                                    cstm.getIbusinessid());*/
                            List list = ecService.findList(Long.parseLong(iscenicid),
                                    cstm.getIbusinessid());
                            Hotelprovider h = (Hotelprovider)ticketTypeService.get(
                                    Hotelprovider.class, Long.parseLong(iscenicid));
                            if (list != null && list.size() > 0) {
                                Esbprovicerq esbprovicerq = (Esbprovicerq) list.get(0);
                                if (esbprovicerq.getInt1() != null
                                        && esbprovicerq.getInt1() > 0) {
                                    Long kznum = esbprovicerq.getInt1();
                                    if (ydznum < kznum) {
                                        rs.addRow(new String[] { "false","数量不对" });
                                        return JSON.toJSONString(rs);
                                    }
                                }
                            } else {
                                if (h != null && h.getInoteger10() != null
                                        && h.getInoteger10() > 0) {
                                    Long kznum = h.getInoteger10();
                                    if (ydznum < kznum) {
                                        rs.addRow(new String[] { "false","数量不对" });
                                        return JSON.toJSONString(rs);
                                    }
                                }
                            }

                            // 根据服务商判断订单修改权限 2015-01-29 陈新浩
                            Proordercontroltab proorder = (Proordercontroltab)ticketTypeService
                                    .get(Proordercontroltab.class,
                                            Long.parseLong(iscenicid));
                            if (proorder != null) {
                                if (proorder.getIsorderscontrol() == 1) {
                                    int oldtotal = 0;
                                    List yorderlist = ecService.getYorderlists(
                                            orid, iscenicid);
                                    if (yorderlist != null && !yorderlist.isEmpty()) {
                                        for (int i = 0; i < yorderlist.size(); i++) {
                                            YOrderlist orderlist = (YOrderlist) yorderlist
                                                    .get(i);
                                            oldtotal += orderlist.getNumb();
                                        }
                                    }
                                    if (oldtotal >= proorder.getOrdersdegree().intValue()) {
                                        int total = 0;
                                        if (torderlists != null && !torderlists.isEmpty()) {
                                            for (int i = 0; i < torderlists.size(); i++) {
                                                TOrderlist pojo = torderlists.get(i);
                                                total += pojo.getNumb();
                                            }
                                        }
                                        if (newticketinfo != null
                                                && !newticketinfo.isEmpty()) {
                                            for (int i = 0; i < newticketinfo.size(); i++) {
                                                OrderPojo pojo = newticketinfo.get(i);
                                                total += Integer.parseInt(pojo.getNumb());
                                            }
                                        }
                                        double maxnum = Math.ceil(((double) proorder
                                                .getOrdersup() / 100 + 1) * oldtotal);
                                        double minnum = Math.floor((1 - (double) proorder
                                                .getOrdersdown() / 100) * oldtotal);
                                        if (total > maxnum) {
                                            rs.addRow(new String[] { "false","订单退订失败，超过总数量" });
                                            return JSON.toJSONString(rs);
                                        }
                                        if (total < minnum) {
                                            rs.addRow(new String[] { "false","订单退订失败，超过总小数量" });
                                            return JSON.toJSONString(rs);
                                        }
                                    }
                                }
                            }
                            // 验证修改过后的订单是否满足各种可修改的条件
                            returnmap = ecService.validateOrderInfo(orid, torderlists,
                                    newticketinfo, morder.getZfusid(), morder.getStdt(),
                                    custom.getIbusinessid().toString(), iscenicid);
                            Boolean result = (Boolean) returnmap.get("result");
                            if (result.booleanValue()) {
                                String[] orids = new String[2];
                                orids[0] = ticketTypeService.getMaxNo("000");
                                orids[1] = ticketTypeService.getMaxNo("000");
                                returnmap = ecService.editOrderCenter(torderlists,
                                        newticketinfo, orids, orid, iscenicid, morder
                                                .getStdt(), custom.getIbusinessid()
                                                .toString(), user);
                                for (int i = 0; i < orids.length; i++) {
                                    /*MOrder editmorder = (MOrder) ticketTypeService.get(
                                            MOrder.class, orids[i]);*/// 获取修改的订单 若为空则下次循环
                                	MOrder editmorder = ecService.getMorderInfo(orids[i]);
                                    if (editmorder != null) {
                                        Orderlog log = new Orderlog();
                                        log.setLogid(sysService.getMaxPk("logid",
                                                "Orderlog") + 1);
                                        log.setOrid(orid);
                                        log.setStlg("0158");
                                        log.setBrief("修改订单");
                                        log.setNote("");
                                        List editlist = ecService
                                                .getYOrderListChildList(orids[i],
                                                        iscenicid);
                                        if (editlist != null && editlist.size() > 0) {
                                            if (editmorder.getOrtp().equals("02")) {
                                                if (log.getNote() != null
                                                        && !log.getNote().trim()
                                                        .equals("")) {
                                                    log.setNote(log.getNote() + "</br>");
                                                }
                                                log.setNote(log.getNote()
                                                        + "退票&nbsp;&nbsp;");
                                            } else if (editmorder.getOrtp()
                                                    .equals("03")) {
                                                if (log.getNote() != null
                                                        && !log.getNote().trim()
                                                        .equals("")) {
                                                    log.setNote(log.getNote() + "</br>");
                                                }
                                                log.setNote(log.getNote()
                                                        + "添加&nbsp;&nbsp;");
                                            }
                                            for (int j = 0; j < editlist.size(); j++) {
                                                Map editMap = (Map) editlist.get(j);
                                                System.out.println("map value"
                                                        + editMap);
                                                log.setNote(log.getNote()
                                                        + "["
                                                        + editMap.get("szscenicname")
                                                        .toString()
                                                        + "]&nbsp;&nbsp;["
                                                        + editMap.get(
                                                        "sztickettypename")
                                                        .toString()
                                                        + "]&nbsp;&nbsp;["
                                                        + editMap
                                                        .get("szcrowdkindname")
                                                        .toString()
                                                        + "]&nbsp;&nbsp;"
                                                        + editMap.get("numb")
                                                        .toString() + "张");
                                            }
                                        }
                                        log.setIemployeeid(null);
                                        log.setUsid(custom.getUsid());
                                        log.setLogtype(Long.parseLong("0"));
                                        log.setLogdatetime(Tools.getDays() + " "
                                                + Tools.getNowTime());
                                        sysService.insertOrderlog(log);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            returnmap = new HashMap();
                            returnmap.put("result", false);
                            returnmap.put("errtp", "11");
                            if(!StringUtils.isBlank(e.getMessage()) && e.getMessage().contains("库存")){
                                rs.addRow(new String[] { "false","服务器错误"});
                                return JSON.toJSONString(rs);
                            }else{
                                e.printStackTrace();
                                rs.addRow(new String[] { "false","服务器错误"});
                                return JSON.toJSONString(rs);
                            }
                        }
                    }
                    rs.addRow(new String[] { "true","退订成功" });
                    return JSON.toJSONString(rs);
                }
            }else {
                rs.addRow(new String[] { "false","未知游客类型" });
                return JSON.toJSONString(rs);
            }
        }
  }
    
   
    /**
     *  取票前全部退订
	  * @param type  类型
	  * @param user 用户ID
	  * @param orid 订单号
	  * @param iscenicid 景区服务商
	  * @author lizhaodong  2017-04-27 添加
	  * @editor  lizhaodong  2017-05-03 修改
	  * @return
	  */
   public String debookAllTicket(String type,String user,String orid,String iscenicid,String url){
	   if(url==null||url.length()<1){
   			url=WebContant.GetKeyValue("CenterUrl");
   		}
	   List<TOrderlist> torderlists=new ArrayList<TOrderlist>();
	   ResultBean rs = new ResultBean();
       rs.setColumnCount(2);
       rs.setColumnNames(new String[] { "returnstats", "message" });
       //注释本地服务
       /*if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
           try {
        	   Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
				Object[] objects = client.invoke("debookAllTicket", type, user,orid,iscenicid);
				ResultBean cano = (ResultBean) objects[0];
				ResultBean rb = new ResultBean();
               if (cano.getRowsCount() > 0) {
                   rb.setColumnCount(cano.getColumnCount());
                   rb.setColumnNames(cano.getColumnNames());
                   rb.setColumnCount(cano.getColumnCount());
                   rb.setRowsCount(cano.getRowsCount());
                   for (int i = 0; i < cano.getRowsCount(); i++) {
                       String[] values = new String[cano.getColumnCount()];
                       for (int j = 0; j < cano.getColumnCount(); j++) {
                           values[j] = cano.getResult(i, j);
                       }
                       rb.addRow(values);
                   }
               }
               return JSON.toJSONString(rb);
           } catch (Exception e) {
        	   LOGGER.error("退订接口异常："+StringUtil.toString_02(e));
               ResultBean rb = new ResultBean();
               rb.setColumnCount(2);
               rb.setColumnNames(new String[] { "returnstats", "message" });
               rb.addRow(new String[] { "false", e.getMessage() });
               return JSON.toJSONString(rs);
           }
       }else {*/
           
           if (orid == null || orid.equals("")) {
               rs.addRow(new String[]{"false", "订单编号不能为空"});
               return JSON.toJSONString(rs);
           }
           if (iscenicid == null || iscenicid.equals("")) {
               rs.addRow(new String[]{"false", "服务商编号不能为空"});
               return JSON.toJSONString(rs);
           }
           MOrder morder = (MOrder) ecService.getMorderInfo(orid);
           if (morder == null) {
               rs.addRow(new String[]{"false", "订单不存在"});
               return JSON.toJSONString(rs);
           }
           Map returnmap = new HashMap();
           if (type.equals("01"))//散客类型
           {
               try {
//                   Custom custom = (Custom) ticketTypeService.get(Custom.class, morder.getUsid());
            	   Custom custom = ecService.getCustomByUserId(morder.getUsid());
            	   List mlist = ecService.getMOrderList(orid);
                   MOrder morder1 = new MOrder();
                   BeanUtils.populate(morder1, (Map) mlist.get(0));
                   String[] orids = new String[2];
//                   TOrder tds = (TOrder) genericService.get(TOrder.class, new TOrderId(orid, Long.parseLong(iscenicid)));
                   TOrder tds = ecService.getTOrderInfo(orid, iscenicid);
                   if (tds == null) {
                       List list = ecService.showscenicidview(orid);
                       if (list != null) {
                           rs.addRow(new String[]{"false", "订单服务商不一致"});
                           return JSON.toJSONString(rs);
                       } else {
                           rs.addRow(new String[]{"false", "订单不存在"});
                           return JSON.toJSONString(rs);
                       }
                   } else {
                       String ddzt = tds.getDdzt();
                       if (!ddzt.equals("02") && !ddzt.equals("23")) {
                           rs.addRow(new String[]{"false", "只有订单状态为已支付的订单才可以退订"});
                           return JSON.toJSONString(rs);
                       }
                   }
                   if(tds.getIschupiao() != null && tds.getIschupiao() == 1L){
                         rs.addRow(new String[] { "false","该订单已被锁定，不允许退订" });
                         return JSON.toJSONString(rs);
                    }
                   torderlists = ecService.getTOrderlists(orid, Long.parseLong(iscenicid));
                   orids[0] = "";
                   orids[1] = stockService.getMaxNo("000");
                   Map<String,Object> orIdsMap = new HashMap<String,Object>();
                   orIdsMap.put("orids", orids);
                   returnmap = ecService.editOrderSankeCenter(JSON.toJSONString(torderlists), null, JSON.toJSONString(orIdsMap), orid, iscenicid, morder.getStdt(), custom.getIbusinessid().toString(), custom.getUsid());
                   boolean result = Boolean.valueOf(returnmap.get("result").toString());
                   if(result) {
                   for (int i = 0; i < orids.length; i++) {
                       MOrder editmorder = ecService.getMorderInfo(orids[i]);// 获取修改的订单
                       // 若为空则下次循环
                       if (editmorder != null) {
                           Orderlog log = new Orderlog();
                           log.setLogid(Long.valueOf((sysService.getMaxPk("logid","Orderlog") + 1)));
                           log.setOrid(orid);
                           log.setStlg("0158");
                           log.setBrief("修改订单");
                           log.setNote("");
                           List editlist = ecService.getYOrderListChildList(orids[i], iscenicid);
                           if (editlist != null && editlist.size() > 0) {
                               if (editmorder.getOrtp().equals("02")) {
                                   if (log.getNote() != null && !log.getNote().trim().equals("")) {
                                       log.setNote("</br>");
                                   }
                                   log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
                               } else if (editmorder.getOrtp().equals("03")) {
                                   if (log.getNote() != null && !log.getNote().trim().equals("")) {
                                       log.setNote("</br>");
                                   }
                                   log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
                               }
                               for (int j = 0; j < editlist.size(); j++) {
                                   Map editMap = (Map) editlist.get(j);
                                   System.out.println("map value" + editMap);
                                   log.setNote(log.getNote() + "[" + editMap.get("szscenicname").toString() + "]&nbsp;&nbsp;[" + editMap.get("sztickettypename").toString() + "]&nbsp;&nbsp;["
                                           + editMap.get("szcrowdkindname").toString() + "]&nbsp;&nbsp;" + editMap.get("numb").toString() + "张");
                               }
                           }
                           log.setIemployeeid(null);
                           log.setUsid(custom.getUsid());
                           log.setLogtype(Long.parseLong("0"));
                           log.setLogdatetime(Tools.getDays() + " " + Tools.getNowTime());
                           sysService.insertOrderlog(log);
                       }
                   }
                   rs.addRow(new String[]{"true", "退订成功"});
                   return JSON.toJSONString(rs);
                  }else {
                	  rs.addRow(new String[]{"false", "退订失败"});
                      return JSON.toJSONString(rs);
                  }
               } catch (Exception e) {
            	   LOGGER.error("退订接口异常："+StringUtil.toString_02(e));
                   rs.addRow(new String[]{"false", "服务器错误"});
                   return JSON.toJSONString(rs);
               }
               
           } else if (type.equals("02"))//旅行社全退票
           {
               try {
            	   Custom custom = ecService.getCustomByUserId(user);
                   List mlist = ecService.getMOrderList(orid);
                   MOrder morder1 = ecService.getMorderInfo(orid);
                   BeanUtils.populate(morder1, (Map) mlist.get(0));
                   if (!morder.getDdzt().equals("02") && !morder.getDdzt().equals("23")) {
                       rs.addRow(new String[]{"false", "只有订单状态为已支付的订单才可以退订"});
                       return JSON.toJSONString(rs);
                   }

                   TOrder tor = ecService.getTOrderInfo(orid, iscenicid);
                   if(tor != null && tor.getIschupiao() != null && tor.getIschupiao() == 1L){
                        rs.addRow(new String[] { "false","该订单已被锁定，不允许出票" });
                        return JSON.toJSONString(rs);
                    }
                   String[] orids = new String[2];
                   torderlists = ecService.getTOrderlists(orid,
                           Long.parseLong(iscenicid));
                   orids[0] = "";
                   orids[1] = stockService.getMaxNo("000");
                   returnmap = ecService.editOrderCenter(torderlists, null, orids,
                           orid, iscenicid, morder.getStdt(), custom.getIbusinessid()
                                   .toString(), user);
                   for (int i = 0; i < orids.length; i++) {
                       MOrder editmorder = ecService.getMorderInfo(orids[i]);// 获取修改的订单
                       // 若为空则下次循环
                       if (editmorder != null) {
                           Orderlog log = new Orderlog();
                           log.setLogid(sysService.getMaxPk("logid", "Orderlog") + 1);
                           log.setOrid(orid);
                           log.setStlg("0158");
                           log.setBrief("修改订单");
                           log.setNote("");
                           List editlist = ecService.getYOrderListChildList(
                                   orids[i], iscenicid);
                           if (editlist != null && editlist.size() > 0) {
                               if (editmorder.getOrtp().equals("02")) {
                                   if (log.getNote() != null
                                           && !log.getNote().trim().equals("")) {
                                       log.setNote("</br>");
                                   }
                                   log.setNote(log.getNote() + "退票&nbsp;&nbsp;");
                               } else if (editmorder.getOrtp().equals("03")) {
                                   if (log.getNote() != null
                                           && !log.getNote().trim().equals("")) {
                                       log.setNote("</br>");
                                   }
                                   log.setNote(log.getNote() + "添加&nbsp;&nbsp;");
                               }
                               for (int j = 0; j < editlist.size(); j++) {
                                   Map editMap = (Map) editlist.get(j);
                                   System.out.println("map value" + editMap);
                                   log.setNote(log.getNote()
                                           + "["
                                           + editMap.get("szscenicname").toString()
                                           + "]&nbsp;&nbsp;["
                                           + editMap.get("sztickettypename")
                                           .toString() + "]&nbsp;&nbsp;["
                                           + editMap.get("szcrowdkindname").toString()
                                           + "]&nbsp;&nbsp;"
                                           + editMap.get("numb").toString() + "张");
                               }
                           }
                           log.setIemployeeid(null);
                           log.setUsid(custom.getUsid());
                           log.setLogtype(Long.parseLong("0"));
                           log.setLogdatetime(Tools.getDays() + " "
                                   + Tools.getNowTime());
                           sysService.insertOrderlog(log);
                       }
                   }
               } catch (Exception e) {
                   LOGGER.error("退订接口异常："+StringUtil.toString_02(e));
                   rs.addRow(new String[]{"false", "服务器错误"});
                   return JSON.toJSONString(rs);
               }
               rs.addRow(new String[]{"true", "退订成功"});
               return JSON.toJSONString(rs);
           } else {
               rs.addRow(new String[]{"false", "未知游客类型"});
               return JSON.toJSONString(rs);
           }
 }
   
   /**
    * 前台售票当前最后一条售票数据
    * @auth:lizd
    * @param type   售票软件重打印搜索时的类型
    * @param iscenicid
    *            服务商
    * @param winId
    * @param empid  售票软件登陆用户
    *            窗口
    * Date:2017-05-24
    */
   public String searchLastSaleTicket(String type,String iscenicid, String iticketwinid,String iemployeeid){
		
		String carno = saleCenterService.getLastSaleData(iscenicid,iticketwinid,iemployeeid);
		return ticketreprintjxzh(type,carno,Long.valueOf(iscenicid));
   }
   
   /**
    * 获取分时预约库存和时段信息
    * @auth:lizd
    * @param productCode   产品code
    * @param choiceDate  选中日期
    * Date:2017-07-31
    */
   public String getTicketTimeCount(String productCode,String choiceDate){
		
		ResultBean rs = saleCenterService.getTicketTimeCount(productCode,choiceDate);
        return JSON.toJSONString(rs);
   }


    /**
     * 查询身份证当天是否已经预约
     * @auth:lizd
     * @param IDCard   产品code
     * @param choiceDate 选中日期
     * Date:2017-07-31
     */
    public String isExistIDcardToday(String iscenicID,String IDCard,String choiceDate,String url){
        ResultBean rs = saleCenterService.isExistIDcardToday(iscenicID,IDCard,choiceDate,url);
        return JSON.toJSONString(rs);
    }
    
    /**
     * 供客户端获取token
     */
	@Override
	public String getTokenByClient() {
		
		String url = "http://zuul-service/uaa/oauth/token?grant_type=client_credentials&scope=select&client_id=webapp&client_secret=webapp";
		return restTemplate.postForObject(url, null, String.class);
	}

	/**
	 * 自助机获取窗口信息
	 * @param iscenicid
	 * @param mac
	 * @return
	 */
	public String getWinAndStationInfo(Long iscenicid, String mac) {
		ResultBean rs1 = saleCenterService.getEsbticketwintabByIP(iscenicid, mac, new Long(1));
		String returnstats = rs1.getResult(0, 0);
		if (returnstats.equals("false")) {
			return JSON.toJSONString(rs1);
		} else {
			ResultBean rs = saleCenterService.getWinAndStationInfo(iscenicid, mac);
			return JSON.toJSONString(rs);
		}
	}
	
	@Override
	public String getTicketPriceBySelf(Long iticketwinid, Long iemployeeid, String stdt) {
		ResultBean rs = saleCenterService.getTicketPrice(iticketwinid, iemployeeid,
		                new Long(1), stdt);
		return JSON.toJSONString(rs);
	}

	@Override
	public String senseServiceStatu() {
		return 1+"";
	}

	@Override
	public String cancelorder(String orid, String url) {
//		ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
//                .getBean("saleAutoService");
         ResultBean cancelorder = saleAutoService.cancelorder(orid,url);
         return JSON.toJSONString(cancelorder);
	}

	@Override
	public String makeTwoBarcode(String orid, Long isdx, String orph, String newUrl) {
		
    	//判断是否输入域名
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	
    	
        ResultBean rs = new ResultBean();
        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        String key = WebContant.GetKeyValue("qrcodemiyue").equals("") ? "OTOECTRIP"
                : WebContant.GetKeyValue("qrcodemiyue");
        Map map = createTwoBarCode(orid, key,newUrl);// 生成二维码并返回地址
        if (isdx == 1) {
            /*IMbMessageService service = (IMbMessageService) SpringUtil
                    .getBean("mbmessageService");
            service.sendMessageNew(orph, "0002", new String[] { orid });*/
        	sysService.sendMessageNew(orph, "0002", orid);
        }
        String qrcodeimgpath = (String) map.get("url");
        rs.addRow(new String[] { "true", qrcodeimgpath });
        return JSON.toJSONString(rs);
    }

    Map createTwoBarCode(String orid, String twobarcodekey,String newUrl) {
    	
    	//判断是否输入域名
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
    	
        Map map = new HashMap();
        String TowCodePath = com.ectrip.base.util.WebContant
                .GetKeyValue("TowCodePath");
        String domain = newUrl;
        String oriddata = orid.substring(0, 8);
        String url = null;// 发个顾客的路径
        String filename = null;// 图片存在地址
        boolean flag = false;// 生成图片是否
        if (TowCodePath != null || !"".equals(TowCodePath) && domain != null
                || !"".equals(domain)) {
            try {
                // 生成文件夹
                File fl = new File(TowCodePath + "/" + oriddata);
                if (!fl.isFile()) {
                    fl.mkdir();
                    if (!fl.isFile()) {
                        fl.mkdirs();
                    }
                }
                // 生成随机数字（进制）
                int max = 35;
                int min = 25;
                Random random = new Random();
                int bases = random.nextInt(max) % (max - min + 1) + min;
                // 生成36进制
                BigInteger orids = new BigInteger(orid);
                BigInteger base = new BigInteger(bases + "");
                String dm = DecimalUtil.toAnyConversion(orids, base);
                String dmlegth = DecimalUtil.getzm(dm.length());
                // 异或算法
                String yhstr = DecimalUtil.getmdecimal(dm);
                // 转换36进制
                BigInteger yhstr1 = new BigInteger(yhstr);
                BigInteger ba = new BigInteger("36");
                String yhstr2 = DecimalUtil.toAnyConversion(yhstr1, ba);

                // 得到图片名称
                BigInteger nameorid = new BigInteger(orid);
                BigInteger basename = new BigInteger("36");
                String dmname = DecimalUtil.toAnyConversion(nameorid, basename);

                // 生成二维码图片
                // ECT_32756aef5b0dea9b9046c04e4f3b8dea513ba5fe64e515ed,0,acb1f342ee7789a7d950e4a86c56b47a

                flag = QrCodesUtil.createCode(
                        "ECT_" + DesUtil.encrypt(orid, twobarcodekey),
                        TowCodePath, dmname, oriddata);
                url = domain + "/code.jsp?code=" + DecimalUtil.getzm(bases)
                        + dmlegth + dm + yhstr2;
                filename = TowCodePath + "/" + oriddata + "/" + dmname + ".png";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("flag", flag);
        map.put("url", url);
        map.put("fliename", filename);
        return map;
    }

	@Override
	public String saleReport(Long iemployeeid, String rzti, String ldti) {
		ResultBean saleReport = saleCenterService.saleReport(iemployeeid, rzti, ldti);
		return JSON.toJSONString(saleReport);
	}
	
	 /**
     * Describe:易旅宝专用
     *
     * @author liujianwen
     * @param orid
     * @return return:ResultBean Date:2014-7-24
     */
	@Override
    public String getT_orderOne(String orid) {
		List<Map> listOrder = ecService.getTOrderByOrid(orid);
		ResultBean resultBean = MapToResultBean.toResultBean(listOrder);
        return JSON.toJSONString(resultBean);
    }

	@Override
	public String autoSaveorder(String message, Long iticketwinid, Long iemployeeid, String timeParam,String url)
	{
		ResultBean rb = null;
		try {
			Esbticketwintab esbticketwintab = saleCenterService.getEsbticketwintab(iticketwinid);
			if (timeParam != null && !"".equals(timeParam)) {
				rb = new ResultBean();
				JSONArray parseArray = JSON.parseArray(timeParam);
				for (Object object : parseArray) {
					JSONObject jsonObject = (JSONObject) object;
					Long timeId = jsonObject.getLongValue("timeId");
					String productId = jsonObject.getString("productId");
					int count = jsonObject.getIntValue("count");
					String hql = "from TimeSharingTicketTab where id = " + timeId + " and productId = '" + productId
							+ "'";
//					GenericDao genericDao = (GenericDao) SpringUtil.getBean("genericDao");
//					ITimeSharingService timeSharingService = (ITimeSharingService) SpringUtil
//							.getBean("timeSharingService");
					List find = timeSharingService.find(hql);
					if (find != null && find.size() > 0) {
						TimeSharingTicketTab timeSharingTicketTab = (TimeSharingTicketTab) find.get(0);
						Integer currStock = timeSharingTicketTab.getCurrStock() == null ? 0
								: timeSharingTicketTab.getCurrStock();
						Integer saleStock = timeSharingTicketTab.getSaleStock() == null ? 0
								: timeSharingTicketTab.getSaleStock();
						Integer totalStock = timeSharingTicketTab.getTatalStock() == null ? 0
								: timeSharingTicketTab.getTatalStock();
						if (totalStock != -1 && count > currStock) {
							rb.addRow(new String[] { "false", "分时预约票库存不足" });
							return JSON.toJSONString(rb);
						} else {
							try {
								timeSharingService.UpdateStock(timeId, productId, count, "minus");
							} catch (Exception e) {
								LOGGER.info("更新分时预约票库存信息异常:"+StringUtil.toString_02(e));
							}
						}

					}
				}
			}
			int ticketstation = esbticketwintab.getIticketstationid().intValue();
			String szticketstation = "";
			if (0 < ticketstation && ticketstation < 10) {
				szticketstation = "00" + ticketstation;
			} else if (ticketstation >= 10 && ticketstation < 100) {
				szticketstation = "0" + ticketstation;
			} else {
				szticketstation = "" + ticketstation;
			}
			String orid = saleCenterService.updateMaxNo(szticketstation);
			// 拆分单个产品
			rb = saleAutoService.autoSaveorder(message, iticketwinid, iemployeeid, orid, esbticketwintab.getIscenicid(),
					timeParam, url);
			if ("false".equals(rb.getResult(0, 0))) {
				// 更新库存
				JSONArray parseArray = JSON.parseArray(timeParam);
				for (Object object : parseArray) {
					JSONObject jsonObject = (JSONObject) object;
					Long timeId = jsonObject.getLongValue("timeId");
					String productId = jsonObject.getString("productId");
					int count = jsonObject.getIntValue("count");
//					ITimeSharingService timeSharingService = (ITimeSharingService) SpringUtil
//							.getBean("timeSharingService");
					try {
						timeSharingService.UpdateStock(timeId, productId, count, "add");
					} catch (Exception e) {
						LOGGER.info("更新分时预约票库存信息异常:"+StringUtil.toString_02(e));
					}
				}
			}
			
		} catch (Exception e) {
			LOGGER.info("autoSaveorder保存订单信息异常:"+StringUtil.toString_02(e));
		}
		return JSON.toJSONString(rb);
	}

	@Override
	public String payOrder(String orderno, Double yingshou, Double shishou, Double zhaoling, Long iticketwinid,
			Long iemployeeid, String zffs) {

//        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
//                .getBean("saleCenterService");
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(iticketwinid);
        if ((shishou - yingshou) != zhaoling) {
            ResultBean rs = new ResultBean();
            rs.setColumnCount(2);
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "false", "找零金额不对" });
            return JSON.toJSONString(rs);
        }
        Long maxid = saleCenterService.updatevouchersequence();
        System.out.println("maxid=" + maxid);
//        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
//                .getBean("saleAutoService");
        ResultBean rb = saleAutoService.payOrder(orderno,
                esbticketwintab.getIscenicid(), yingshou, shishou, zhaoling,
                iticketwinid, iemployeeid, zffs, maxid);
        return JSON.toJSONString(rb);
    
	}
	
}
