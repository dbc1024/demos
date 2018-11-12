package com.ectrip.ticket.webservice.check.impl;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.ectrip.ticket.sale.service.SaleService;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.MapToResultBean;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.cytterminal.OrderInfo;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.checkticket.model.CheckMethodValue;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.checkticket.service.iservice.ICheckdengjiService;
import com.ectrip.ticket.checkticket.service.iservice.ICheckonetableService;
import com.ectrip.ticket.checkticket.service.iservice.IPassCardService;
import com.ectrip.ticket.common.checkUtils.CommonCheckUtil;
import com.ectrip.ticket.cyt.client.v1.CYTClient;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.order.Stssoldticketsubtab;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.provider.controller.CrowdKindPriceController;
import com.ectrip.ticket.provider.service.IEsbticketWinService;
import com.ectrip.ticket.provider.service.IProviderService;
import com.ectrip.ticket.sale.service.card.core.DesUtil;
import com.ectrip.ticket.sale.service.cytterminal.service.CytSaleMainService;
import com.ectrip.ticket.sale.service.cytterminal.service.iservice.ICytSaleService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;
import com.ectrip.ticket.util.JaxWsDynamicClientFactoryUtil;
import com.ectrip.ticket.util.ServerNameConst;
import com.ectrip.ticket.webservice.check.ICheckWebService;
import com.ectrip.ticket.sale.service.card.webservice.OneCardWebservice;

/**
 * 
 * 检票接口
 * @author jy
 *
 */
@WebService(serviceName = "checkWebService", // 与接口中指定的name一致
targetNamespace = "http://check.webservice.ticket.ectrip.com/", // 与接口中的命名空间一致,一般是接口的包名倒
endpointInterface = "com.ectrip.ticket.webservice.check.ICheckWebService"// 接口地址
)
@Component
public class CheckWebServiceImpl implements ICheckWebService {
	//日志处理
	private static final Logger LOGGER = LogManager.getLogger(CrowdKindPriceController.class);
	
	@Autowired
	private ICheckService checkService;//检票服务
	
	@Autowired
	private ICytSaleService cytSaleService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private  IProviderService providerService;//景区服务商服务
	
	@Autowired
	private ISaleCenterService saleCenterService;
	
	@Autowired
	private  IEsbticketWinService esbticketwintabService;//窗口服务
	
	@Autowired
	private ICheckonetableService checkoneService;
	
	@Autowired
	private SysService sysService;//系统服务接口
	
	@Autowired
	private EcService ecService; //电商服务
	
	@Autowired
	private IPassCardService passCardService;
	
	@Autowired
	private  ICheckdengjiService checkdengjiService;
	
	@Autowired
	private CytSaleMainService cytSaleMainService;//分销系统服务
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private OneCardWebservice oneCardWebservice;
	
	@Autowired
	private ICheckDao checkDao ;

	@Autowired
	private  CYTClient cytClient;
	
	@Autowired
	private  ConstUtils constUtils;
	
	@Autowired
	private CommonCheckUtil commonCheckUtil;
	
	
    /**
     * 根据匝机IP+服务商ID读取匝机信息 Describe:
     *
     * @param ip
     * @param scenic
     * @return return:ResultBean Date:2011-12-8
     * @auth:yuanchengjun
     */

    public String getMyID(String ip) {
    	ResultBean rb = checkService.getMyID(ip);
        return JSON.toJSONString(rb);
    }

    /**
     * 测试调用的值
     *
     * @return
     * @throws Exception
     */
    public String getResultBean(){
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
//        String version = CommonUtil.getVerison("03");
        rs.setColumnNames(new String[] { "accessToken"});
        String url = "http://zuul-service/uaa/oauth/token?grant_type=client_credentials&scope=select&client_id=webapp&client_secret=webapp";
		String accessToken = restTemplate.postForObject(url, null, String.class);
        rs.addRow(new String[] {accessToken});
        return JSON.toJSONString(rs);
    }
    public String CheckTicket(String ticketstr, String md5str,String... OtherString) {
        //判断是否输入域名
    	
    	ResultBean rb = null;
        try {
			if (OtherString.length > 1) {
			    if (OtherString[0].equals("0")) {
			    	return this.CheckTicketByCheckType(ticketstr, md5str, OtherString[1]);
			    }
			    if (OtherString[0].equals("2")) {
			        rb = this.CheckTickets(ticketstr, md5str);
			    } else {
			        rb = this.CheckTicketoneTable(ticketstr, md5str);
			    }
			} else {
				return this.CheckscTicket(ticketstr, md5str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JSON.toJSONString(rb);
    }

    private ResultBean CheckTickets(String ticketstr, String md5str1)
            throws Exception {
        String[] params = ticketstr.split(",");
        String accid = params[0];
        String printno1 = params[1];
        System.out.println(printno1);
        String printno2 = params[2];
        System.out.println(printno2);
        ResultBean rs = checkService.changeCheckTicket(accid, printno1, printno2);
        /**
         * 调用关联园门检票
         */
        if (rs.getResult(0, 0).equals("3")) {
            checkService.savechecklink(accid, printno1);
            checkService.savechecklink(accid, printno2);
        }
        return rs;
    }

    /**
     * 总体对外检票方法 ticketstr="园门ID,票号" 根据票号返回检票规则 本方法已经修改，为支持畅游通，做了修改，不影响以前程序
     *
     * @param ticketstr 检票用的票号
     * @param md5str    准确性校验方法
     * @return rs rs说明： 第一个值 状态参数 -1 无效票 -2 退订 -3 对应园门不能刷该票 -4 未到检票时间 -5 已过有效期
     * -9 已检过 -10 刷票过快 -99 已挂失 1 登记指纹 2 验证指纹 3 检票成功 请进 98
     * 该身份证不止一张票，不能用身份证检票 99 放行卡放行
     * <p/>
     * 第二个值 票名称 第三个值 单价 第四个值 人群ID 第五个值 人群名称 6 有效开始时间 7 有效截至时间 8 指纹信息 9
     * 图像路径 10 语音路径 11 竹筏seq 12 竹筏趟次名称 13 14 15 16 17 18 19 20 21 检票流水
     * 22 检票返回次数 23 是否该票所有园门已检完 1 检完 0 未检完 最后一位返回检票类别 23 票务 21 导游 22员工
     */
    private ResultBean checkTicket(String ticketstr, String md5str1, String checkType)
            throws Exception {

        //判断是否输入域名
//    	if(newUrl == null || newUrl.length()<1){
//    		newUrl = WebContant.GetKeyValue("DOMAIN");
//    	}
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[]{"values"});
        // return checkService.CheckTicket(ticketstr, md5str);
        String[] params = ticketstr.split(",");
        System.out.println(Arrays.toString(params));
        String accid = params[0];
        String printno = params[1];
        boolean b = false;
        String num = "0";
        if(printno.startsWith("HQYT_")||printno.startsWith("CYT_")||printno.length()==18){
        	rs= checkService.changescCheckTicket(accid, "Time_"+printno, 0, null);
        	num = rs.getResult(0, 0);
        	if(num.toString().equals("-4")||num.toString().equals("-5")||num.toString().equals("-3")){
        		System.out.println(num);
        		return rs;
        	}
        }
        if (printno.length() == 17) {
            String date = printno.substring(0, 4) + "-"
                    + printno.substring(4, 6) + "-" + printno.substring(6, 8);
            Pattern p = Pattern
                    .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
            b = p.matcher(date).matches();
        }
        if (b) {
            ResultBean r3 = checkService.checktorder(accid, printno,null);
            r3.addRow(new String[]{"0"});
            r3.addRow(new String[]{"23"});
            return r3;
        }
        System.out.println("printno:==="+printno);
        if (printno.length() == 18
                || printno.substring(0, 8).toUpperCase().equals("EMPLOYEE") || printno.startsWith("VIP")) {
            String str = passCardService.CheckPassCrad(accid, printno);
            System.out.println("str=" + str);
            if (str.equals("1")) { // 导游
                return checkService.changeCheckDaoyou(accid, printno,null);
            } else if (str.equals("2")) {  // 员工
                return checkService.changeCheckEmployee(accid, printno,null);
            } else if (str.equals("-1")) { // 一卡多票
                rs.addRow(new String[]{"98"});// 无效票
                return rs;
            } else if (str.equals("0")) {
                System.out.println("身份证订单出票检票");
                ResultBean r3 = checkService.checktorder(accid, printno,null);

                r3.addRow(new String[]{"0"});
                r3.addRow(new String[]{"23"});
                return r3;

            } else {
                /**
                 * 根据票号判断闸机是否能检该票 100 非一单一检 101 一单一检 其他是不能检票返回的数据
                 */
                ResultBean rs2 = checkService.querychecktype(accid, str,checkType);
                if (rs2.getResult(0, 0).equals("100")) {
                    ResultBean rs1 = checkService.changeCheckTicket(accid, str,checkType,"");
                    /*if (rs1.getResult(0, 0).equals("3")) {
                        checkService.savechecklink(accid, str);
					}*/
                    if (rs1.getRowsCount() < 22) {
                        for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                            rs1.addRow(new String[]{""});
                        }
                    }

                    Stssoldtickettab stss = checkService.getmyzj(accid, str);
                    rs1.addRow(new String[]{stss.getMyzj()});
                    rs1.addRow(new String[]{stss.getName1()});

                    rs1.addRow(new String[]{"0"});

                    rs1.addRow(new String[]{"23"});

                    return rs1;
                } else if (rs2.getResult(0, 0).equals("101")) {
                    // 一单一检

                    ResultBean rs1 = checkService.changeCheckTicket(accid, str,checkType,"");
                    if (rs1.getResult(0, 0).equals("3")) {
                        //checkService.savechecklink(accid, str);
                        int jpnumb = 0;
                        jpnumb = Integer.parseInt(rs1.getResult(21, 0));
                        System.out.println("jpnumb0=" + jpnumb);
                        List printnolist = checkService.queryprintnolist(accid,
                                str);
                        if (printnolist != null && printnolist.size() > 0) {
                            for (int i = 0; i < printnolist.size(); i++) {
                                Map map1 = (Map) printnolist.get(i);
                                String szprintno = map1.get("SZTICKETPRINTNO")
                                        .toString();
                                if (!szprintno.equals("")
                                        && !szprintno.equals(str)) {
                                    ResultBean rs3 = checkService
                                            .changeCheckTicket(accid, szprintno,checkType,"");
                                    if (rs3.getResult(0, 0).equals("3")) {
                                        jpnumb = jpnumb
                                                + Integer.parseInt(rs3
                                                .getResult(21, 0));
                                        System.out.println("jpnumb=" + jpnumb);
                                        checkService.savechecklink(accid,
                                                szprintno);
                                    }
                                }
                            }
                        }
                        System.out.println("jpnumb111=" + jpnumb);
                        rs1.removeRow(21);
                        rs1.addRow(new String[]{String.valueOf(jpnumb)});
                        Stssoldtickettab stss = checkService
                                .getmyzj(accid, str);
                        rs1.addRow(new String[]{stss.getMyzj()});
                        rs1.addRow(new String[]{stss.getName1()});
                        rs1.addRow(new String[]{"0"});
                        rs1.addRow(new String[]{"23"});
                        return rs1;
                    } else {
                        if (rs1.getRowsCount() < 22) {
                            for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                                rs1.addRow(new String[]{""});
                            }
                        }
                        if (!rs1.getResult(0, 0).equals("-1")) {
                            Stssoldtickettab stss = checkService.getmyzj(accid,
                                    str);
                            rs1.addRow(new String[]{stss.getMyzj()});
                            rs1.addRow(new String[]{stss.getName1()});
                        }
                        rs1.addRow(new String[]{"0"});
                        rs1.addRow(new String[]{"23"});
                        return rs1;
                    }
                } else {
                    if (rs2.getResult(0, 0).equals("-1")
                            && printno.length() == 18) {
                        // 身份证检票没有数据 ，查询 网上订单 是否有可以直接出票的订单
                        ResultBean r3 = checkService
                                .checktorder(accid, printno,null);
                        if (!r3.getResult(0, 0).equals("-1")) {
                            Stssoldtickettab stss = checkService.getmyzj(accid,
                                    str);
                            r3.addRow(new String[]{stss.getMyzj()});
                            r3.addRow(new String[]{stss.getName1()});
                        }
                        r3.addRow(new String[]{"0"});
                        r3.addRow(new String[]{"23"});
                        return r3;
                    } else {
                        if (rs2.getRowsCount() < 22) {
                            for (int a = 0; a <= 22 - rs2.getRowsCount(); a++) {
                                rs2.addRow(new String[]{""});
                            }
                        }
                        if (!rs2.getResult(0, 0).equals("-1")) {
                            Stssoldtickettab stss = checkService.getmyzj(accid,
                                    str);
                            rs2.addRow(new String[]{stss.getMyzj()});
                            rs2.addRow(new String[]{stss.getName1()});
                        }
                        rs2.addRow(new String[]{"0"});
                        rs2.addRow(new String[]{"23"});
                        return rs2;
                    }
                }
            }
        } else {
            ResultBean rs2 = checkService.querychecktype(accid, printno,checkType);
            if (rs2.getResult(0, 0).equals("100")) {
                ResultBean rs1 = checkService.changeCheckTicket(accid, printno,checkType,"");
                /**
                 * 调用关联园门检票
                 */
                if (rs1.getResult(0, 0).equals("3")) {
                    checkService.savechecklink(accid, printno);
                }
                /**
                 * 吞卡回收程序
                 */
                if (rs1.getRowsCount() < 22) {
                    for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                        rs1.addRow(new String[]{""});
                    }
                }
                if (!rs1.getResult(0, 0).equals("-1")) {
                    Stssoldtickettab stss = checkService
                            .getmyzj(accid, printno);
                    rs1.addRow(new String[]{stss.getMyzj()});
                    rs1.addRow(new String[]{stss.getName1()});
                }
                if (rs1.getResult(0, 0).equals("1")
                        || rs1.getResult(0, 0).equals("2")
                        || rs1.getResult(0, 0).equals("3")
                        || rs1.getResult(0, 0).equals("-9")) {
                    // 读取该票号的所有园门的检票数据

                    boolean flag = checkService.isyw(printno);
                    if (flag) {
                        rs1.addRow(new String[]{"1"});
                    } else {
                        rs1.addRow(new String[]{"0"});
                    }
                } else {
                    rs1.addRow(new String[]{"0"});
                }

                rs1.addRow(new String[]{"23"});

                return rs1;
            } else if (rs2.getResult(0, 0).equals("101")) {
                ResultBean rs1 = checkService.changeCheckTicket(accid, printno,checkType,"");
                if (rs1.getResult(0, 0).equals("3")) {
                    checkService.savechecklink(accid, printno);
                    int jpnumb = 0;
                    jpnumb = Integer.parseInt(rs1.getResult(21, 0));
                    List printnolist = checkService.queryprintnolist(accid,
                            printno);
                    if (printnolist != null && printnolist.size() > 0) {
                        for (int i = 0; i < printnolist.size(); i++) {
                            Map map1 = (Map) printnolist.get(i);
                            String szprintno = map1.get("SZTICKETPRINTNO")
                                    .toString();
                            if (!szprintno.equals("")
                                    && !szprintno.equals(printno)) {

                                ResultBean rs3 = checkService
                                        .changeCheckTicket(accid, szprintno,checkType,"");

                                if (rs3.getResult(0, 0).equals("3")) {

                                    jpnumb = jpnumb
                                            + Integer.parseInt(rs3.getResult(
                                            21, 0));

                                    checkService
                                            .savechecklink(accid, szprintno);

                                }

                            }
                        }
                    }

                    rs1.removeRow(21);
                    rs1.addRow(new String[]{String.valueOf(jpnumb)});
                    if (!rs1.getResult(0, 0).equals("-1")) {
                        Stssoldtickettab stss = checkService.getmyzj(accid,
                                printno);
                        rs1.addRow(new String[]{stss.getMyzj()});
                        rs1.addRow(new String[]{stss.getName1()});
                    }
                    rs1.addRow(new String[]{"0"});
                    rs1.addRow(new String[]{"23"});
                    return rs1;
                } else {
                    if (rs1.getRowsCount() < 22) {
                        for (int a = 0; a <= 22 - rs1.getRowsCount(); a++) {
                            rs1.addRow(new String[]{""});
                        }
                    }
                    if (!rs1.getResult(0, 0).equals("-1")) {
                        Stssoldtickettab stss = checkService.getmyzj(accid,
                                printno);
                        rs1.addRow(new String[]{stss.getMyzj()});
                        rs1.addRow(new String[]{stss.getName1()});
                    }
                    rs1.addRow(new String[]{"0"});
                    rs1.addRow(new String[]{"23"});
                    return rs1;
                }
            } else {
                if (rs2.getRowsCount() < 22) {
                    for (int a = 0; a <= 22 - rs2.getRowsCount(); a++) {
                        rs2.addRow(new String[]{""});
                    }
                }
                if (!rs2.getResult(0, 0).equals("-1")) {
                    Stssoldtickettab stss = checkService
                            .getmyzj(accid, printno);
                    rs2.addRow(new String[]{stss.getMyzj()});
                    rs2.addRow(new String[]{stss.getName1()});
                }
                if (rs2.getResult(0, 0).equals("-9")) {
                    // 读取该票号的所有园门的检票数据

                    boolean flag = checkService.isyw(printno);
                    if (flag) {
                        rs2.addRow(new String[]{"1"});
                    } else {
                        rs2.addRow(new String[]{"0"});
                    }
                } else {
                    rs2.addRow(new String[]{"0"});
                }
                rs2.addRow(new String[]{"23"});
                return rs2;
            }
        }

    }

    ;
    /**
     * Describe:把原来的方法原封不动移动到了checkTicket(小写),本方法调用checkTicket，不影响原的检票代码,
     * 只新增支持畅游通
     *
     * @param ticketstr
     * @param md5str1
     * @return
     * @throws Exception return:ResultBean Date:2014-4-30
     * @author liujianwen
     */
    @SuppressWarnings("unchecked")
	public String CheckTicketByCheckType(String ticketstr, String md5str1,String checkType) {

        //判断是否输入域名
//    	if(newUrl == null || newUrl.length()<1){
//    		
//    	}
    	String newUrl = WebContant.GetKeyValue("DOMAIN");
        if(oneCardWebservice.isOneCard(ticketstr)){
        	ResultBean checkTicket = oneCardWebservice.checkTicket(ticketstr);
            return JSON.toJSONString(checkTicket);
        }
        String string = ticketstr;
      
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[] { "values" });
        try {
            //重组检票数据，过滤票号上的二维码信息
            String[] strs = ticketstr.split(",");
            if (strs != null && strs.length > 1) {
                String[] tickets = strs[1].split("#");
                if (tickets != null && tickets.length > 1) {
                    ticketstr = strs[0] + "," + tickets[1];
                }
            }
            //远程畅游通抓取订单出票
           Sysparv5 sys = cytSaleService.findSyspar("CYTT", "01");
            if (sys != null && sys.getIsa() == 0) {
                CYTPojo pojo = dealWithCYTOnline(ticketstr, newUrl);
                if (pojo.isCYT)
                    ticketstr = pojo.posid + "," + pojo.printNum;
                else {
                    String str = dealWithOTO(ticketstr);
                    ticketstr = (str == null ? ticketstr : str);
                }
            } else {
            	String[] parrs = string.split(",");
            	String num = "0";
                if(parrs[1].startsWith("HQYT_")||parrs[1].startsWith("CYT_")||parrs[1].length()==18){
                	rs= checkService.changescCheckTicket(parrs[0], "Time_"+parrs[1], 0, null);
                	num = rs.getResult(0, 0);
                	if(num.toString().equals("-4")||num.toString().equals("-5")||num.toString().equals("-3")||num.toString().equals("5")||num.toString().equals("6") || num.toString().equals("-24")){//�����ؽ��ΪԱ������VIP����δ����Ʊʱ�䡢�ѹ���Ʊʱ�䡢�豸���ܼ��Ʊ,����ʶ�𶩵�ʱ��������Ʊ����
                		System.out.println(num);
                		return JSON.toJSONString(rs);
                	}
                }

                //畅游通检票
                CYTPojo pojo = dealWithCYT(ticketstr, newUrl);
                if (pojo.isCYT)
                	if(pojo.printNum == null){
                		ticketstr = pojo.posid + "," + pojo.orid;
                	}else {
                        //修复实名制检票与领票人检票bug
                		if (parrs[1].length() == 18) {
        					try {

                                // 大于浏览结束日期1一个月，则视为无效票
        						SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        						Date date = new Date();
        						Calendar calendar = Calendar.getInstance();
        						calendar.setTime(date);
        						calendar.add(Calendar.MONTH, -1);// 一个月前的时间
        						dateFormater.format(calendar.getTime());

        						System.out.println(dateFormater.format(calendar.getTime()));// 获取一个月前的时间
        						// to_date('"+calendar.getTime()+"','yyyy-mm-dd')
        						List<Stssoldtickettab> list = checkDao
        								.find(" from Stssoldtickettab where myzj='" + parrs[1] + "' and dtenddate >= '"
        										+ dateFormater.format(calendar.getTime()) + "' order by dtmakedate desc");
        						if (list.size() != 0) {// 用游客身份证取出票号
                                    // 增加游客先买实名制票，检票后再购买非实名票，用领票人身份证检票
        							List<Stssoldtickettab> stList = checkDao
        									.find(" from Stssoldtickettab where szticketprintno='"
        											+ list.get(0).getSzticketprintno() + "'");
        							List<Stssoldticketsubtab> stSubList = checkDao
        									.find(" from Stssoldticketsubtab s where  s.id.szsoldticketid="
        											+ stList.get(0).getId().getSzsoldticketid() + " and s.id.isalesvoucherid="
        											+ stList.get(0).getId().getIsalesvoucherid());
        							if (stSubList.get(0).getIpasstimes() - stSubList.get(0).getIpassedtimes() == 0) {// 
        								parrs[1] = pojo.printNum;
        							}
        						} else {// 用领票人身份证检票
        							parrs[1] = pojo.printNum;
        						}
        					} catch (Exception e) {
        						// TODO: handle exception
        						e.printStackTrace();
        					}
        				} else {
        					parrs[1] = pojo.printNum;
        				}
                		ticketstr = pojo.posid + "," + parrs[1];
					}else {
                    String str = dealWithOTO(ticketstr);
                    ticketstr = (str == null ? ticketstr : str);
                }
            }
            //实名制订单检票
            String[] parrs = ticketstr.split(",");
            String str = passCardService.CheckPassCrad(parrs[0], parrs[1]);
            if(str.equals("0")){
                str = passCardService.CheckPassCrad(parrs[0], "VIP"+parrs[1]);
                if(str.equals("0")){
                    ticketstr = dealWithOTOCard(ticketstr, null);
                }else if(str.equals("2")){
                    ticketstr = parrs[0]+",VIP"+parrs[1];
                }
            }
            //检票
            String[] strs1 = ticketstr.split(",");
            rs = checkTicket(ticketstr, md5str1,checkType);
            return JSON.toJSONString(rs);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("检票接口异常，异常接口为："+StringUtil.toString_02(e));
        }
        return null;
    }
    
    public ResultBean CheckTicketoneTable(String ticketstr, String md5str)
            throws Exception {
    	
    	//判断是否输入域名
    	String newUrl = WebContant.GetKeyValue("DOMAIN");
    	
    	
    	
        System.out.println("单表检票开始调用");
        
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[]{"values"});
        // return checkService.CheckTicket(ticketstr, md5str);
        String[] params = ticketstr.split(",");
        String accid = params[0];

        String printno = params[1];
        System.out.println("单表检票开始调用2");
        if (printno.length() == 18
                || printno.substring(0, 8).toUpperCase().equals("EMPLOYEE")) {
            String str = passCardService.CheckPassCradoneTable(accid, printno);
            System.out.println("str=" + str);
            if (str.equals("1")) { // 导游
                return checkService.changeCheckDaoyou(accid, printno,newUrl);
            } else if (str.equals("2")) { // 员工
                return checkService.changeCheckEmployee(accid, printno,newUrl);
            } else if (str.equals("-1")) { // 一卡多票
                rs.addRow(new String[]{"98"});// 无效票
                rs.addRow(new String[]{"该身份证不止一张票，不能用身份证检票"});
                return rs;
            } else if (str.equals("0")) { // 找不到
                rs.addRow(new String[]{"-1"});// 无效票
                rs.addRow(new String[]{"无效票"});
                return rs;
            } else {
                /**
                 * 根据票号判断闸机是否能检该票 100 非一单一检 101 一单一检 其他是不能检票返回的数据
                 */
                ResultBean rs2 = checkoneService.querychecktypeoneTable(accid,
                        str);
                if (rs2.getResult(0, 0).equals("100")) {
                    ResultBean rs1 = checkoneService.changeCheckTicketonetable(
                            accid, str);

                    return rs1;
                } else if (rs2.getResult(0, 0).equals("101")) {
                    // 一单一检

                    ResultBean rs1 = checkoneService.changeCheckTicketonetable(
                            accid, str);
                    if (rs1.getResult(0, 0).equals("3")) {

                        int jpnumb = 0;
                        jpnumb = Integer.parseInt(rs1.getResult(21, 0));
                        List printnolist = checkoneService
                                .queryprintnolistonetable(accid, str);
                        if (printnolist != null && printnolist.size() > 0) {
                            for (int i = 0; i < printnolist.size(); i++) {
                                Map map1 = (Map) printnolist.get(i);
                                String szprintno = map1.get("SZTICKETPRINTNO")
                                        .toString();
                                if (!szprintno.equals("")
                                        && !szprintno.equals(str)) {
                                    ResultBean rs3 = checkoneService
                                            .changeCheckTicketonetable(accid,
                                                    szprintno);
                                    if (rs3.getResult(0, 0).equals("3")) {
                                        jpnumb = jpnumb
                                                + Integer.parseInt(rs3
                                                .getResult(21, 0));

                                    }

                                }
                            }
                        }
                        rs1.removeRow(21);
                        rs1.addRow(new String[]{String.valueOf(jpnumb)});
                        rs1.addRow(new String[]{"0"});
                        rs1.addRow(new String[]{"23"});
                        return rs1;
                    } else {
                        rs1.addRow(new String[]{"0"});
                        rs1.addRow(new String[]{"23"});
                        return rs1;
                    }
                } else {
                    return rs2;
                }
            }
        } else {
            System.out.println("check1");
            ResultBean rs2 = checkoneService.querychecktypeoneTable(accid,
                    printno);
            System.out.println("rs2.getResult(0, 0)=" + rs2.getResult(0, 0));
            if (rs2.getResult(0, 0).equals("100")) {
                ResultBean rs1 = checkoneService.changeCheckTicketonetable(
                        accid, printno);

                /**
                 * 吞卡回收程序
                 */
                if (rs1.getResult(0, 0).equals("1")
                        || rs1.getResult(0, 0).equals("2")
                        || rs1.getResult(0, 0).equals("3")
                        || rs1.getResult(0, 0).equals("-9")) {
                    // 读取该票号的所有园门的检票数据
                    boolean flag = checkoneService.isyw(printno);
                    if (flag) {
                        rs1.addRow(new String[]{"1"});
                    } else {
                        rs1.addRow(new String[]{"0"});
                    }
                } else {
                    rs1.addRow(new String[]{"0"});
                }
                rs1.addRow(new String[]{"23"});
                System.out.println("rowcount" + rs.getRowsCount());
                return rs1;
            } else if (rs2.getResult(0, 0).equals("101")) {
                System.out.println("check2");
                ResultBean rs1 = checkoneService.changeCheckTicketonetable(
                        accid, printno);
                System.out
                        .println("rs1.getResult(0, 0)=" + rs1.getResult(0, 0));
                if (rs1.getResult(0, 0).equals("3")) {

                    int jpnumb = 0;
                    jpnumb = Integer.parseInt(rs1.getResult(21, 0));
                    List printnolist = checkoneService
                            .queryprintnolistonetable(accid, printno);
                    if (printnolist != null && printnolist.size() > 0) {
                        for (int i = 0; i < printnolist.size(); i++) {
                            Map map1 = (Map) printnolist.get(i);
                            String szprintno = map1.get("SZTICKETPRINTNO")
                                    .toString();
                            if (!szprintno.equals("")
                                    && !szprintno.equals(printno)) {
                                System.out.println("szprintno=" + szprintno);
                                ResultBean rs3 = checkoneService
                                        .changeCheckTicketonetable(accid,
                                                szprintno);
                                if (rs3.getResult(0, 0).equals("3")) {
                                    jpnumb = jpnumb
                                            + Integer.parseInt(rs3.getResult(
                                            21, 0));
                                    checkService
                                            .savechecklink(accid, szprintno);
                                }

                            }
                        }
                    }
                    System.out.println("jpnumb=" + jpnumb);
                    rs1.removeRow(21);
                    rs1.addRow(new String[]{String.valueOf(jpnumb)});
                    rs1.addRow(new String[]{"0"});
                    rs1.addRow(new String[]{"23"});
                    return rs1;
                } else {
                    rs1.addRow(new String[]{"0"});
                    rs1.addRow(new String[]{"23"});
                    return rs1;
                }
            } else {
                if (rs2.getResult(0, 0).equals("-9")) {
                    // 读取该票号的所有园门的检票数据
                    boolean flag = checkoneService.isyw(printno);
                    if (flag) {
                        rs2.addRow(new String[]{"1"});
                    } else {
                        rs2.addRow(new String[]{"0"});
                    }
                } else {
                    rs2.addRow(new String[]{"0"});
                }
                rs2.addRow(new String[]{"23"});
                return rs2;
            }
        }
    }


    /**
     * 读取售票点信息 Describe:
     *
     * @param iscenicid
     * @return return:ResultBean Date:2011-12-15
     * @auth:yuanchengjun
     */

    public String getstation(Long iscenicid) {
    	ResultBean rs = checkService.getstation(iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     * 景区代码信息 Describe:
     *
     * @param iscenicid
     * @return return:ResultBean Date:2011-12-15
     * @auth:yuanchengjun
     */
    public String getscenic(Long iscenicid) {
    	ResultBean rb = checkService.getscenic(iscenicid);
        return JSON.toJSONString(rb);
    }

    /**
     * 读取票务代码信息 Describe:
     *
     * @param iscenicid
     * @return return:ResultBean Date:2011-12-15
     * @auth:yuanchengjun
     */
    public String getTicket(Long iscenicid) {
        ResultBean rs = checkService.getTicket(iscenicid);
        return JSON.toJSONString(rs);
    }

    /**
     * 验证放行卡 Describe:
     *
     * @param accid
     * @param carno
     * @return return:ResultBean Date:2011-12-24
     * @auth:yuanchengjun
     */
    public String CheckEmployee(String accid, String carno) {
        //判断是否输入域名
    	String newUrl = WebContant.GetKeyValue("DOMAIN");
    	ResultBean changeCheckEmployee = checkService.changeCheckEmployee(accid, carno,newUrl);
        return JSON.toJSONString(changeCheckEmployee);
    }

    /**
     * 读取服务器时间
     */
    public String getDayTimes() {

        return Tools.getDayTimes();
    }
    
    /**
     * 读取ftp信息
     */
    public String getFtp(String accid) {
    	
    	ResultBean rb = checkService.getFtp(accid);
        return JSON.toJSONString(rb);
    }
    /**
     * 李进修改，根据通ID取值
     *
     * @param iaccessequipid
     * @return
     */
    public String getEsbaccessequiptab(Long iaccessequipid) {
    	
    	ResultBean rb = checkService.findEsbaccessequiptab(iaccessequipid);
        return JSON.toJSONString(rb);
    }

    public String getCheckcount(Long iaccessequipid) {

    	ResultBean rb = checkService.getCheckcount(iaccessequipid,null);
        return JSON.toJSONString(rb);
    }
    /**
     * 总体对外检票方法 ticketstr="园门ID,票号" 根据票号返回检票规则
     *
     * @param ticketstr 检票用的票号
     * @param md5str    准确性校验方法
     * @return
     */
    public String CheckscTicket(String ticketstr, String md5str) {
        /**
		         * for ( int i =1 ;i <paramsOther.length ; i++ ) {
		         * System.out.println("otherString = "+ i +"  "+paramsOther[i]); } //
		         * 原先检票方法要进行修改,李进修改，增加了参数 System.out.println("	params.length"+
		         * params.length);
		         */
		ResultBean rs = null;
		try {
			// 要据第三个能数时行判断，如果前面两个是JP，就是JP，如果是查询就是查询
			// return checkService.CheckTicket(ticketstr, md5str);
			System.out.println("检票传入参数 =" + ticketstr);
			String[] params = ticketstr.split(",");
			String accid = params[0];
			String printno = params[1];
			int icjp = 0;
			String[] paramsOther = {"", "", "", "", "", "", ""};
			// 李进修改，为了使检标接口满足更多的内容： 增加船号和通行证号 漓江需求
			// 李进增加程序开始 2012- 7-28
			if (params.length > 2) {
			    // 取出第三段值
			    String otherString = params[2];
			    // 将第三段的值转成数组
			    // System.out.println("otherString ="+otherString);
			    paramsOther = otherString.split("[|]");
			    // 清空需要的字符变量
			    System.out.println(paramsOther.length);
			    // 2012 - 11-12 李进增加
			    if (paramsOther[0].substring(0, 2).equals("JP")) {
			        icjp = 0; // 0给进检票
			    } else {

			        icjp = 1; // 1表示查询
			    }

			} else {
			    icjp = 0; // 0给进检票
			}
			rs = new ResultBean();
			if (params.length > 2) {
			    if (paramsOther.length == 1)
			        rs = checkService.changescCheckTicket(accid, printno, icjp,null);
			    if (paramsOther.length == 2)
			        rs = checkService.changescCheckTicket(accid, printno, icjp,
			                paramsOther[1]);
			    if (paramsOther.length == 3)
			        rs = checkService.changescCheckTicket(accid, printno, icjp,
			                paramsOther[1], paramsOther[2]);

			    if (paramsOther.length == 4)
			        rs = checkService.changescCheckTicket(accid, printno, icjp,
			                paramsOther[1], paramsOther[2], paramsOther[3]);

			    if (paramsOther.length == 5)
			        rs = checkService.changescCheckTicket(accid, printno, icjp,
			                paramsOther[1], paramsOther[2], paramsOther[3],
			                paramsOther[4]);

			    if (paramsOther.length > 6)
			        rs = checkService.changescCheckTicket(accid, printno, icjp,
			                paramsOther[1], paramsOther[2], paramsOther[3],
			                paramsOther[4], paramsOther[5]);
			    else
			        rs = checkService.changescCheckTicket(accid, printno, icjp,
			                paramsOther[1], paramsOther[2], paramsOther[3],
			                paramsOther[4], paramsOther[5]);
			} else {
			    rs = checkService.changescCheckTicket(accid, printno, icjp,null);
			}
			if (!rs.getResult(0, 0).equals("-1")) {
			    Stssoldtickettab stss = checkService.getmyzj(accid, printno);
			    rs.addRow(new String[]{stss.getMyzj()});
			    rs.addRow(new String[]{stss.getName1()});
			}
			rs.addRow(new String[]{"0"});
			rs.addRow(new String[]{"23"});
		} catch (Exception e) {
			e.printStackTrace();
		}
        return JSON.toJSONString(rs);
    }

//    public static void main(String[] args) throws Exception {
//        System.out.println(DesUtil.decrypt("041e24cfab9fea806e794cbe3a53c924cc0bc72eb2d02101",
//                ConstUtils.KEY));
//
//    }

    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);  // 获取类属性
        Object obj = type.newInstance();  // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo
                .getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    public ResultBean Checkfangxin(String accid, String carno) {
        ResultBean rs1 = checkService.changeCheckfanxin(accid, carno);

        return rs1;
    }

    /**
     * 更新员工检票数据 Describe:
     *
     * @param accid  闸机编号
     * @param carno  身份证号码
     * @param md5str
     * @return
     * @throws Exception return:ResultBean Date:2011-11-15
     * @auth:yuanchengjun
     */
    public ResultBean employeepass(String accid, String carno, String md5str)
            throws Exception {
        return checkService.changeEmployeepass(accid, carno);
    }

    /**
     * Describe://员工登记指纹信息
     *
     * @param accid   闸机编号
     * @param carno   员工票号
     * @param ziwenno 指纹信息
     * @param md5str
     * @return
     * @throws Exception return:ResultBean Date:2011-11-15
     * @auth:yuanchengjun
     */
    public ResultBean employeezwinput(String accid, String carno,
                                      String ziwenno, String md5str) throws Exception {
        return checkService.changeEmployeezwinput(accid, carno, ziwenno);
    }

    /**
     * 总体对外检票方法 ticketstr="园门ID,票号" 根据票号返回检票规则
     *
     * @param ticketstr 检票用的票号
     * @param md5str    准确性校验方法
     * @return
     */
    public ResultBean zwdengjiTicket(String printno) throws Exception {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[]{"values"});
        System.out.println("printno");
        if (printno.length() == 18
                || printno.substring(0, 8).toUpperCase().equals("EMPLOYEE")) {
            System.out.println("111111111111111");
            String str = checkdengjiService.CheckPassCrad(printno);
            System.out.println("str=" + str);
            if (str.equals("1")) { // 导游
                return checkdengjiService.changeCheckDaoyou(printno);
            } else if (str.equals("2")) {// 员工
                return checkdengjiService.changeCheckEmployee(printno);
            } else if (str.equals("-1")) { // 一卡多票
                rs.addRow(new String[]{"-98"});// 无效票
                rs.addRow(new String[]{"该身份证不止一张票，不能用身份证登记"});
                return rs;
            } else if (str.equals("0")) { // 找不到
                rs.addRow(new String[]{"-1"});// 无效票
                rs.addRow(new String[]{"无效票"});
                return rs;
            } else {
                return checkdengjiService.changeCheckTicket(str);
            }
        } else {
            ResultBean rs1 = checkdengjiService.changeCheckTicket(printno);
            return rs1;
        }
    }

    /**
     * Describe:
     *
     * @param type     类别
     * @param ticketno 票号
     * @param ziwenno  指纹信息
     * @return
     * @throws Exception return:int Date:2011-11-14
     * @auth:yuanchengjun
     */

    public int ticketZwdengji(String type, String ticketno, String ziwenno) {
        System.out.println("type=" + type);
        System.out.println("ticketno=" + ticketno);
        System.out.println("ziwenno=" + ziwenno);
//        ICheckdengjiService checkdengjiService = (ICheckdengjiService) SpringUtil
//                .getBean("checkdengjiService");
        try {

            return checkdengjiService.saveZwdengji(type, ticketno, ziwenno);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 测试调用的值
     *
     * @return
     * @throws Exception
     */
    public void updatestatus(Long accid, String typestatus, Long byisuse) {
        
        checkService.updatestatus(accid, typestatus, byisuse);

    }
    /**
     * 读取配置文件
     *
     * @return return:String Date:2011-12-12
     * @auth:yuanchengjun
     */
    public String getWebContant(String xmlkey) {
        return WebContant.GetKeyValue(xmlkey);

    }

	public String dealWithOTOCard(String ticketstr, String url) {
		if (url == null || url.length() < 1) {
			url = WebContant.GetKeyValue("CenterUrl");
		}

		CYTPojo pojo = new CYTPojo();
		String[] parrs = ticketstr.split(",");
		pojo.posid = parrs[0];
		pojo.orid = parrs[1];
		try {
			// 通过身份证实名制信息查找订单
			String orid = checkService.findOrderByCard(pojo.posid, pojo.orid);
			if (orid != null) {
				pojo.orid = orid;
				pojo.cytdto = checkService.findCYTDto(orid, url);
				if (pojo.cytdto.isGoOn) {
					if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                        try {
//                            javax.xml.rpc.Service service = null;
//                            java.net.URL endpointURL = new java.net.URL(
//                                    "http://"
//                                            +url
//                                            + "/services/centersaleService?wsdl");
//                            CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
//                                    endpointURL, service);
//                            ssl.setMaintainSession(true);
//                            com.ectrip.centersale.client.ResultBean cano = ssl
//                                    .getT_order(pojo.orid);
                        	List<Map> listOrder = ecService.getTOrderByOrid(pojo.orid);
                    		ResultBean cano = MapToResultBean.toResultBean(listOrder);
                            if (cano.getRowsCount() > 0) {
                                TOrder to = new TOrder();
                                to.setId(new TOrderId(pojo.orid, Long
                                        .parseLong(cano.getResult(0,
                                                "iscenicid"))));
                                to.setOrid(pojo.orid);
                                to.setDdzt(cano.getResult(0, "ddzt"));
                                to.setUsid(cano.getResult(0, "usid"));
                                to.setDtstartdate(cano.getResult(0, "dtstartdate"));
                                pojo.cytdto.torder = to;
//                                cano = ssl.getT_orderlist(pojo.orid, to
//                                        .getId().getIscenicid());
                                cano = saleCenterService.getT_orderlist(pojo.orid, to.getId().getIscenicid());
                                if (cano.getRowsCount() > 0) {
                                    TOrderlist tol = new TOrderlist();
                                    tol.setNumb(Long.parseLong(cano
                                            .getResult(0, "numb")));
                                    pojo.cytdto.tOrderlist = tol;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
					TOrder order = pojo.cytdto.torder;
					if (order != null) {
						if (!Calendar.getInstance().getTime()
								.after(new SimpleDateFormat("yyyy-MM-dd").parse(order.getDtstartdate()))) {
							System.out.println("只有在首日浏览日期之后才能在闸机上出票检票");
							return ticketstr;// 只有在首日浏览日期之后才能在闸机上出票检票
						}
						pojo.iscenicid = order.getId().getIscenicid();
						pojo.orderuserid = order.getUsid();
						if ("02".equals(order.getDdzt())) {
							takeCYTTicket(pojo);// 已支付时，出票
							ticketstr = pojo.posid + "," + pojo.printNum;
						} else if ("11".equals(order.getDdzt())) {// 当已经出票时,查出数据
							ResultBean bean = saleService.ticketreprintbylb("03", pojo.orid, pojo.iscenicid, parrs[1],
									url);
							int count;
							System.out.println("bean.getResult(0, 0):" + bean.getResult(0, "szticketprintno"));
							if (bean != null && (count = bean.getRowsCount()) > 0
									&& !"false".equalsIgnoreCase(bean.getResult(0, 0))) {
								for (int i = 0; i < count; i++) {
									pojo.printNum = bean.getResult(i, "szticketprintno");
									if (pojo.printNum.split("#").length > 1) {
										pojo.printNum = pojo.printNum.split("#")[1];
									}
								}
								ticketstr = pojo.posid + "," + pojo.printNum;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketstr;
	}

    // /////////////畅游通订单处理/////////////////////

    /**
     * Describe:畅游通订单处理,畅游通订单中只存在一个价格，并且是一票多人
     *
     * @param ordernumOrIdnum 订单号或者身份证;当身份证时可能存在多个订单,当身份证时无法处理 return:void Date:2014-4-24
     * @author liujianwen
     */
    public CYTPojo dealWithCYT(String orderargs,String url) {
    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        
        CYTPojo pojo = new CYTPojo();
        System.out.println("CYTClient.orderargs:" + orderargs);
        System.out.println("ConstUtils.KEY:" + constUtils.getKEY());
        if (cytClient.getCYTServiceURL() != null
                && !"".equals(cytClient.getCYTServiceURL() )) {
            try {
                String[] parrs = orderargs.split(",");
                boolean as = parrs[1].matches("^\\d{8}999\\d{6}") || parrs[1].matches("^\\d{8}888\\d{6}");
                boolean es = pojo.isCYT;
                System.out.println(as);
                System.out.println(es);
                System.out.println(as=es);

                if (
                		parrs.length > 1&&
                        (
                        		(parrs[1].startsWith("CYT_")&& (parrs[1] = DesUtil.decrypt(parrs[1].substring(4),constUtils.getKEY())) != null)
                        		||(parrs[1].startsWith("HQYT_")&& (parrs[1] = DesUtil.decrypt(parrs[1].substring(5),constUtils.getHQYTKEY())) != null)
                        		||(parrs[1].startsWith("APP_")&& (parrs[1] = DesUtil.decrypt(parrs[1].substring(4),constUtils.getHQYTKEY())) != null)
                        		||(parrs[1].length()==18)
                        )
                        && !(pojo.isCYT = (parrs[1].matches("^\\d{8}888\\d{6}")))
                    )
                {
                    pojo.orid = parrs[1];
                    pojo.posid = parrs[0];

                    //根据闸机ID查找服务商
                    if(!commonCheckUtil.checkConsumeOnline(pojo.posid)){
                        return pojo;
                    }
                    System.out.println("畅游通订单:" + pojo.orid);
                   // ICheckDao checkDao = (ICheckDao) SpringUtil.getBean("checkDao");
                    List list = checkDao.find("from Esbaccessequiptab where id.iaccessequipid = " + pojo.posid);
                    Long iscenicid=0L;
                    if (list != null && !list.isEmpty()) {
                        Esbaccessequiptab access = (Esbaccessequiptab) list.get(0);
                        iscenicid=access.getId().getIscenicid();
                    }
                    pojo.cytdto = checkService.findCYTDto(parrs[1],iscenicid,url);
                    if (pojo.isCYT = pojo.cytdto.isGoOn) {
                        // pojo.oto_code =
                        // pojo.cytdto.Galcompanyinfotab.getSzcompanycode();
                        // ///////////////////Torder与Torderlist从中心服务器获取/////////////////////////////
//                        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
                    try {
                    	 url = "192.168.0.101:8082/ticket";//需要动态获取，测试写死
                    	//Client client = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
         				//Object[] objects = client.invoke("getT_orderOne", pojo.orid);
         				//String canos = String.valueOf(objects[0]);
         				//ResultBean cano = JSON.parseObject(canos, ResultBean.class);
                    	List<Map> listOrder = ecService.getTOrderByOrid(pojo.orid);
                 		ResultBean cano = MapToResultBean.toResultBean(listOrder);
                        if (cano.getRowsCount() > 0) {
                            TOrder to = new TOrder();
                            to.setId(new TOrderId(pojo.orid, Long
                                    .parseLong(cano.getResult(0,
                                            "iscenicid"))));
                            to.setOrid(pojo.orid);
                            to.setDdzt(cano.getResult(0, "ddzt"));
                            to.setUsid(cano.getResult(0, "usid"));
                            to.setDtstartdate(cano.getResult(0, "dtstartdate"));
                            pojo.cytdto.torder = to;
//                            Client clients = JaxWsDynamicClientFactoryUtil.getCfxClientInstance(url, ServerNameConst.SALESERVICE);
//             				Object[] objectss = clients.invoke("getT_orderlist", pojo.orid, to.getId().getIscenicid());
//             				String canoss = String.valueOf(objectss[0]);
//             				cano = JSON.parseObject(canoss, ResultBean.class);
                            //不区分中心和景区不再通过webservice调用中心数据
                            cano = saleCenterService.getT_orderlist(pojo.orid,  to.getId().getIscenicid());
                            if (cano.getRowsCount() > 0) {
                                TOrderlist tol = new TOrderlist();
                                tol.setNumb(Long.parseLong(cano
                                        .getResult(0, "numb")));
                                pojo.cytdto.tOrderlist = tol;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                        
                        // /////////////////////////////////////////////////////////////////
                        TOrder order = pojo.cytdto.torder;
                        if (order != null) {
                            pojo.iscenicid = order.getId().getIscenicid();
                            pojo.orderuserid = order.getUsid();
                            pojo.orid = pojo.cytdto.morder.getOrid();

                            if ("02".equals(order.getDdzt())) {// 已支付，但未出票

                            	takeCYTTicket(pojo); //出票并生成票号，将出票后的票务信息更新到相应的数据表中

                            } else if ("11".equals(order.getDdzt())) {// 当已经出票时,查出数据
                                ResultBean bean = saleService
                                        .ticketreprintbylb("03", pojo.orid,
                                                pojo.iscenicid,parrs[1],url);
                                int count;
                                System.out.println("bean.getResult(0, 0):"
                                        + bean.getResult(0, "szticketprintno"));
                                if (bean != null
                                        && (count = bean.getRowsCount()) > 0
                                        && !"false".equalsIgnoreCase(bean
                                        .getResult(0, 0))) {
                                    for (int i = 0; i < count; i++) {
                                        pojo.printNum = bean.getResult(i,
                                                "szticketprintno");
                                        if (pojo.printNum.split("#").length > 1) {
                                            pojo.printNum = pojo.printNum.split("#")[1];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pojo;
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
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return JSON.toJSONString(rb);
            }
        } else {
            
        	ResultBean rb = saleCenterService.getT_orderlist(orid, iscenicid);
            return JSON.toJSONString(rb);
        }
    }
    

    public CYTPojo dealWithCYTOnline(String orderargs,String url) {
        CYTPojo pojo = new CYTPojo();
        try {
            System.out.println("CYTClient.orderargs:" + orderargs);
            System.out.println("ConstUtils.KEY:" + constUtils.getKEY());
            String[] parrs = orderargs.split(",");
            System.out.println(DesUtil.decrypt(parrs[1].substring(4), constUtils.getKEY()));
            if (parrs.length > 1
                    && parrs[1].startsWith("CYT_")
                    && (parrs[1] = DesUtil.decrypt(parrs[1].substring(4),
                    constUtils.getKEY())) != null
                    && (pojo.isCYT = (parrs[1].matches("^\\d{8}999\\d{6}") || parrs[1]
                    .matches("^\\d{8}888\\d{6}")|| parrs[1].matches("^\\d{8}000\\d{6}")|| parrs[1].matches("^\\d{8}236\\d{6}")))) {

                pojo.orid = parrs[1];
                pojo.posid = parrs[0];
                //根据闸机ID查找服务商
                if(!commonCheckUtil.checkConsumeOnline(pojo.posid)){
                    return pojo;
                }
                System.out.println(">>>>>:" + pojo.orid);
                pojo.isCYT = true;
                //查询订单畅游通订单
                OrderInfo orderInfo = cytSaleMainService.findOrderByOrderId(pojo.posid, pojo.orid);
                if (orderInfo != null) {
                    if (!Calendar.getInstance().getTime().after(new SimpleDateFormat("yyyy-MM-dd").parse(orderInfo.getVisitDate()))) {
                        System.out.println("只有在首日浏览日期之后才能在闸机上出票检票");
                        return pojo;//只有在首日浏览日期之后才能在闸机上出票检票
                    }
                    ;
                    pojo.iscenicid = Long.parseLong(orderInfo.getIscenicid());
                    pojo.orderuserid = orderInfo.getSaleName();
                    pojo.useQuantity = orderInfo.getCount();
                    //查询系统订单
                    Map map = cytSaleMainService.checkOTOOrder(orderInfo.getId(), Long.parseLong(orderInfo.getTotalCount()));
                    String status = map.get("status").toString();
                    if ("consume".equals(status)) {//已出票
                        ResultBean bean = saleService
                                .ticketreprintbylb("03", map.get("orid").toString(),
                                        pojo.iscenicid,parrs[1],url);
                        int count;
                        System.out.println("bean.getResult(0, 0):"
                                + bean.getResult(0, "szticketprintno"));
                        if (bean != null
                                && (count = bean.getRowsCount()) > 0
                                && !"false".equalsIgnoreCase(bean
                                .getResult(0, 0))) {
                        	 pojo.printNum = bean.getResult(0,"szticketprintno");
                            /*for (int i = 0; i < count; i++) {
                                pojo.printNum = bean.getResult(i,"szticketprintno");
                                if (pojo.printNum.split("#").length > 1) {
                                    pojo.printNum = pojo.printNum.split("#")[1];
                                }
                            }*/
                        }
                    } else if ("true".equals(status)) {//未出票
                        takeCYTOnlineTicket(pojo,url);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pojo;
    }

    /**
     * Describe: 出票并返回票号
     *
     * @param ordernum
     * @param iscenicid
     * @return return:String Date:2014-4-24
     * @author liujianwen
     */
    @SuppressWarnings("unchecked")
    private void takeCYTTicket(CYTPojo pojo) {

    	Hotelprovider scen = providerService.queryScenicProvider(pojo.cytdto.torder.getId().getIscenicid());
        Object pa[] = new Object[]{pojo.orid};
        if (scen == null || scen.getInoteger3() != 1) {
            return;
        }
        // ICheckService checkService = (ICheckService) SpringUtil
        // .getBean("checkService");
        // Sysparv5 netprint = pojo.cytdto.sysparv5;
        // Esbticketwintab win =pojo.cytdto.esbticketwintab;
        // 验证是否有出票窗口
        // MOrder morder = pojo.cytdto.morder;
        TOrderlist tolist = pojo.cytdto.tOrderlist;

        if (tolist != null) {
            List<Esbticketwintab> es = esbticketwintabService.find("from Esbticketwintab e where exists(select s.iticketstationid from Esbticketstationtab s where s.iscenicid=? and s.byisuse=1 and e.iticketstationid=s.iticketstationid) and e.byisuse=1 and e.szticketwinname like ?",
                            new Object[]{pojo.iscenicid, "%CYT%"});
            if (es == null || es.size() == 0){
            	return ;
            }

            Esbticketwintab win = es.get(0);
            /*List<Esfemployeetab> ees = orderService
                    .find("from Esfemployeetab e where e.byisuse=1 and e.szemployeename like ? and exists(select b.ideptid from Esfdepttab b where b.byisuse=1 and  e.ideptid=b.ideptid and exists(select g.icompanyinfoid from Galcompanyinfotab g where g.icompanyinfoid=b.icompanyinfoid and exists(select c.id.icompanyinfoid from Companyscenic c where g.icompanyinfoid=c.id.icompanyinfoid and c.id.iscenicid=?)))",
                            new Object[]{"%CYT%", pojo.iscenicid});*/
            List<Esfemployeetab> ees = sysService.getEsfemployeeInfoListByScenicId(pojo.iscenicid);
            if (ees == null || ees.size() == 0)
                return;
            Esfemployeetab emp = ees.get(0);

            System.out.println(pojo.orid + "," + pojo.iscenicid + ","
                    + win.getIticketwinid() + "," + emp.getIemployeeid()
                    + ",4.1");
            // if(pojo.orid.matches("^\\d{8}888\\d{6}"))
            // //poid机具id必须唯一,取闸机id+企业编号
            // if(!CYTClient.verifyConsume(pojo.orderuserid,pojo.oto_code,morder.getNoteh(),
            // pojo.orid, String.valueOf(tolist.getNumb()), morder.getNotei(),
            // pojo.posid+pojo.oto_code))//如果是淘宝OTA订单,需要核销
            // return;
            ResultBean rs = saleService.savetorder(pojo.orid, pojo.iscenicid,
                    win.getIticketwinid(), emp.getIemployeeid(), "4.2",null,null);
            if (rs != null && rs.getRowsCount() > 0
                    && "true".equalsIgnoreCase(rs.getResult(0, 0))) {
                try {
                    pojo.isCallback = true;
                    pojo.orderQuantity = String.valueOf(tolist.getNumb());
                    pojo.useQuantity = pojo.orderQuantity;
                    ResultBean ps = saleService.soldticketlist(
                            Long.parseLong(rs.getResult(0, 1)),
                            win.getIticketstationid());
                    int i, count;
                    for (i = 0, count = ps.getRowsCount(); i < count; i++){
                        pojo.printNum = ps.getResult(i, "szticketprintno");
                        if (pojo.printNum.split("#").length > 1) {
                            pojo.printNum = pojo.printNum.split("#")[1];
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
                // ICheckService service = (ICheckService)
                // SpringUtil.getBean("checkService");
                // service.addCYTPojo(pojo);
            }
        }
    }


    /**
     * Describe: 出票
     *
     * @param ordernum
     * @param iscenicid
     * @return return:String Date:2014-4-24
     * @author chenxinhao
     */
    @SuppressWarnings("unchecked")
    private void takeCYTOnlineTicket(CYTPojo pojo,String url) {
        List<Esbticketwintab> es = esbticketwintabService.find("from Esbticketwintab e where exists(select s.iticketstationid from Esbticketstationtab s where s.iscenicid=? and s.byisuse=1 and e.iticketstationid=s.iticketstationid) and e.byisuse=1 and e.szticketwinname like ?",
                        new Object[]{pojo.iscenicid, "%CYT%"});
        if (es == null || es.size() == 0)//出票窗口为空时，不做任处理，返回为空
            return;
        Esbticketwintab win = es.get(0);//出票窗口为空时，此时无法得到售票窗口，抛异常
       /* List<Esfemployeetab> ees = orderService
                .find("from Esfemployeetab e where e.byisuse=1 and e.szemployeename like ? and exists(select b.ideptid from Esfdepttab b where b.byisuse=1 and  e.ideptid=b.ideptid and exists(select g.icompanyinfoid from Galcompanyinfotab g where g.icompanyinfoid=b.icompanyinfoid and exists(select c.id.icompanyinfoid from Companyscenic c where g.icompanyinfoid=c.id.icompanyinfoid and c.id.iscenicid=?)))",
                        new Object[]{"%CYT%", pojo.iscenicid});*/
        List<Esfemployeetab> ees = sysService.getEsfemployeeInfoListByScenicId(pojo.iscenicid);
        if (ees == null || ees.size() == 0)
            return;
        Esfemployeetab emp = ees.get(0);
        //CytSaleMainService cytSaleMainService = (CytSaleMainService) SpringUtil.getBean("cytSaleMainService");
        ResultBean rs = cytSaleMainService.consumeOrder(pojo.orid, pojo.iscenicid, win.getIticketwinid(), emp.getIemployeeid(),
                pojo.orid, Long.parseLong(pojo.useQuantity), 1, "", "");

        if (!rs.getResult(0, 0).equals("false")) {
            /*List<MOrder> mlist = orderService.find("from MOrder m where m.srid = '" + pojo.orid + "' ");
            MOrder morder = mlist.get(0);*/
            MOrder morder = ecService.getMorderInfo(pojo.orid);
          
            ResultBean bean = saleService
                    .ticketreprintbylb("03", morder.getOrid(),
                            pojo.iscenicid,null,url);
            int count;
            if (bean != null
                    && (count = bean.getRowsCount()) > 0
                    && !"false".equalsIgnoreCase(bean
                    .getResult(0, 0))) {
                for (int i = 0; i < count; i++)
                    pojo.printNum = bean.getResult(i,
                            "szticketprintno");
            }
        }


    }

    /**
     * Describe:OTO二维码检票
     *
     * @return return:String 如果返回为null，则非二维码检票 Date:2014-8-5
     * @author liujianwen
     */
    public String dealWithOTO(String ticketstr) {
        try {
            if (ticketstr != null) {
                String[] parrs = ticketstr.split(",");
                if (parrs.length > 1
                        && parrs[1].startsWith("ECT_")
                        && (parrs[1] = DesUtil.decrypt(parrs[1].substring(4),
                        WebContant.GetKeyValue("qrcodemiyue"))) != null)
                    return parrs[0] + "," + parrs[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String autoMethod(String method, String gardenId, String accid, String jsonInfo) {
        ResultBean rs = new ResultBean();
        rs.setColumnCount(1);
        rs.setColumnNames(new String[]{"values"});
        if (method != null && !"".equals(method)) {
            if (method.equals("0001")) {//直接保存检票数据，记录出园人数使用
                Long numb = 1L;
                if (jsonInfo != null && !"".equals(jsonInfo)) {
                    CheckMethodValue value = JSON.parseObject(jsonInfo, CheckMethodValue.class);
                    if (value != null && !StringUtils.isBlank(value.getNumb())) ;
                    numb = Long.parseLong(value.getNumb());
                }
                checkService.addCheckList(gardenId, accid, numb);
            } else if (method.equals("0002")) {
                Integer numb = checkService.getCheckOutNum(gardenId, accid);
                rs.addRow(new String[]{numb.toString()});
            }else if(method.equals("1001")){
                if(!StringUtils.isBlank(jsonInfo)){
                    if(jsonInfo.startsWith("CYT_")){
                        CYTPojo pojo = dealWithCYT(accid+","+jsonInfo, null);
                        rs.addRow(new String[]{pojo.getPrintNum()});
                    }
                }
            }/*else if(method.equalsIgnoreCase("queryPrice")){
                ISaleCheckService saleCheckService = (ISaleCheckService) SpringUtil.getBean("saleCheckService");
                return saleCheckService.queryPrice(Long.parseLong(accid));
            }else if(method.equalsIgnoreCase("queryBill")){
                ISaleCheckService saleCheckService = (ISaleCheckService) SpringUtil.getBean("saleCheckService");
                return saleCheckService.queryBill(Long.parseLong(accid),jsonInfo);
            }*/else{//一卡通专用。以后可以反射,无需再改动本类本方法。method写全称 如 com.ectrip.Test.main
                try {
                    int index = method.lastIndexOf(".");
                    Class<?> c = Class.forName(method.substring(0, index));
                    Object obj = c.newInstance();
                    rs = (ResultBean) c.getDeclaredMethod(method.substring(index+1, method.length()), String.class,String.class,String.class).invoke(
                            obj, gardenId, accid,  jsonInfo);
                    return JSON.toJSONString(rs);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        } else {
            rs.addRow(new String[]{"-1"});
        }
        return JSON.toJSONString(rs);
    }
}
