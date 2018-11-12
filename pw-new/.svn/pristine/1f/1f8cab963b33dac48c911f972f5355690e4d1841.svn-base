package com.ectrip.ticket.sale.service;

import java.io.File;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.DecimalUtil;
import com.ectrip.base.util.DesUtil;
import com.ectrip.base.util.QrCodesUtil;
import com.ectrip.base.util.ResultBean;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.checkticket.service.iservice.ICheckService;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.centersale.T_order;
import com.ectrip.ticket.model.centersale.T_orderlist;
import com.ectrip.ticket.model.centersale.T_zorderlist;
import com.ectrip.ticket.model.centersale.Trealname;
import com.ectrip.ticket.model.provider.Esbticketstationtab;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.provider.service.ITimeSharingService;
import com.ectrip.ticket.sale.service.card.core.CoreDistribution;
import com.ectrip.ticket.sale.service.cytterminal.service.CytSaleMainService;
import com.ectrip.ticket.sale.service.cytterminal.service.iservice.ICytSaleService;
import com.ectrip.ticket.sale.service.iservice.ISaleAutoService;
import com.ectrip.ticket.sale.service.iservice.ISaleCenterService;

public class AutoSaleService {
	@Autowired
	private SysService sysService;
    /**
     * 登录接口 Describe:
     *
     * @auth:yuanchengjun
     * @param iscenicid
     * @param userid
     * @param password
     * @return
     * @throws Exception
     *             return:ResultBean Date:2013-1-3
     */
    public ResultBean saleautoservice(String md5str, String userid,
                                      String password, Long method,String newUrl, String... parameters)
            throws Exception {
        /**
         * 判断用户
         */
        System.out.println("saleautoservice0");
        
        
      //判断是否输入域名
    	if(newUrl == null || newUrl.length()<1){
    		newUrl = WebContant.GetKeyValue("DOMAIN");
    	}
        
        
        
        Esfemployeetab esfemployeetab = null;
        if (method != 1 && method < 1001) {
            esfemployeetab = this.isyuxiao(md5str, userid, password);
            if (esfemployeetab == null) {
                ResultBean rs = new ResultBean();
                rs.setColumnCount(2);
                rs.setColumnNames(new String[] { "returnstats", "message" });
                rs.addRow(new String[] { "false", "接口调用验证不成功" });
                return rs;
            }
        }
        System.out.println("saleautoservice1");
        if (method == 1) {
            // 用户登录
            return this.emplogin(new Long(parameters[0]), userid, password);
        } else if (method == 1001) {
            return this.zhuceticketwin(parameters[0], parameters[1], new Long(
                    parameters[2]),newUrl);
        } else if (method == 1002) {
            return this.getWinAndStationInfo(new Long(parameters[0]),
                    parameters[1]);
        } else if (method == 2) {

            return this.getT_order(parameters[0], new Long(parameters[1]),
                    parameters[2],newUrl);
        } else if (method == 3) {
            return this.getT_orderlist(parameters[0], new Long(parameters[1]),newUrl);
        } else if (method == 4) {
            ICytSaleService cytSaleService = (ICytSaleService) SpringUtil.getBean("cytSaleService");
            Sysparv5 v5 = cytSaleService.findSyspar("CYTT", "01");
            if(v5 == null || v5.getIsa() == 1L){
                return this.savetorder(parameters[0], new Long(parameters[1]),
                        new Long(parameters[2]), esfemployeetab.getIemployeeid(),
                        "",newUrl);
            }else{
                CytSaleMainService cytSaleMainService = (CytSaleMainService) SpringUtil.getBean("cytSaleMainService");
                return cytSaleMainService.consumeOrder(parameters[0], new Long(parameters[1]), new Long(parameters[2]), esfemployeetab.getIemployeeid(),
                        parameters[4], new Long(parameters[5]), 2, "", "");
            }

        } else if (method == 5) {
            updateprintbyprintno(parameters[0], parameters[1], "01",
                    esfemployeetab.getIemployeeid());
            ResultBean rs = new ResultBean();
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "true", "打印更新成功" });
            return rs;
        } else if (method == 6) {
            String message = this.getSalevoucher(parameters[0], parameters[1],
                    Integer.parseInt(parameters[2]));
            ResultBean rs = new ResultBean();
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "true", message });
            return rs;
        } else if (method == 7) {
            return saveLogs(parameters);
        } else if (method == 8) {
            return makeTwoBarcode(parameters[0], new Long(parameters[1]),
                    parameters[2],newUrl);
        }else if(method == 10){
            return cancelorder(parameters[0],newUrl);
        }else if (method == 27) {//

            return ticketchupiao(new Long(parameters[0]), new Long(
                    parameters[1]));
        } else if(method == 3001){
            //记录售票软件操作日志
            //[0] 窗口ID [1]日志类型 01，常规日志 02，操作日志 [2]日志内容
            saveSaleLog(userid,parameters[0],parameters[1],parameters[2]);
            return null;
        }else {
            ResultBean rs = new ResultBean();
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[]{"false", "调用方法参数不对"});
            return rs;
        }

    }

    public void saveSaleLog(String userid,String winid,String logType,String note){
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        saleCenterService.saveSaleLog(userid,winid,logType,note);
    }

    public ResultBean cancelorder(String orid,String url){
        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                .getBean("saleAutoService");
        return saleAutoService.cancelorder(orid,url);
    }

    public ResultBean ticketchupiao(Long isalesvoucherid, Long iticketstationid) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.ticketchupiao(isalesvoucherid,
                iticketstationid,null);
    }
    public ResultBean makeTwoBarcode(String orid, Long isdx, String orph,String newUrl) {
    	
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
        return rs;
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

    /**
     * Describe:
     *
     * @author liujianwen
     * @param args
     *            售票员id，类别，内容，时间
     * @return return:ResultBean Date:2014-5-21
     */
    private ResultBean saveLogs(String... args) {
        ResultBean rs = new ResultBean();
        rs.setColumnNames(new String[] { "returnstats", "message" });
        if (args != null && args.length > 3) {
            try {
                /*ISyslogService service = (ISyslogService) SpringUtil
                        .getBean("syslogService");*/
                Syslog syslog = new Syslog();
                syslog.setIemployeeid(Long.parseLong(args[0]));
                syslog.setBrief(args[1]);
                syslog.setNote(args[2]);
                syslog.setStlg("0400");
                syslog.setLogdatetime(args[3]);
                /*service.insertSyslog(syslog);*/
                sysService.saveSysLog(syslog);
                rs.addRow(new String[] { "true", "" });
            } catch (Exception e) {
                rs.addRow(new String[] { "false", e.getMessage() });
            }
        } else {
            rs.addRow(new String[] { "false", "参数长度小于4" });
        }
        return rs;
    }

    private Esfemployeetab isyuxiao(String md5str, String userid,
                                    String password) {
        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                .getBean("saleAutoService");
        return saleAutoService.isyuxiao(md5str, userid, password);

    }

    public ResultBean emplogin(Long iscenicid, String userid, String password)
            throws Exception {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        ResultBean rs = saleCenterService.emplogin(iscenicid, userid, password);
        String returnstats = rs.getResult(0, 0);
        if (returnstats.equals("false")) {
            return rs;
        } else {
            return saleCenterService.getEmployee(userid);
        }
    }

    public ResultBean getWinAndStationInfo(Long iscenicid, String mac) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        ResultBean rs1 = saleCenterService.getEsbticketwintabByIP(iscenicid,
                mac, new Long(1));
        String returnstats = rs1.getResult(0, 0);
        if (returnstats.equals("false")) {
            return rs1;
        } else {
            ResultBean rs = saleCenterService.getWinAndStationInfo(iscenicid,
                    mac);
            return rs;
        }
    }

    /**
     * 取出景区类型的服务商
     */
    public ResultBean getscenic() {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.getscenic();

    }

    public ResultBean saleReport(Long iemployeeid, String rzti, String ldti) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.saleReport(iemployeeid, rzti, ldti);
    }

    /**
     * 出票口注册售票点 Describe:
     * @auth:yuanchengjun
     * @param mac
     * @param iscenicid
     * @return return:ResultBean Date:2012-1-7
     */
    public ResultBean zhuceticketwin(String mac, String ip, Long iscenicid,String url) {
    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        ResultBean rs = new ResultBean();

        rs.setColumnCount(2);
        rs.setColumnNames(new String[] { "returnstats", "message" });
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            rs.addRow(new String[] { "false", "本地服务器不可注册窗口" });
            return rs;
        }
        Esbticketwintab etw = saleCenterService.getEsbticketwintabBymac(
                iscenicid, mac);
        // 根据MAC地址查询是否有该MAC注册后的地址
        if (etw != null) {
            rs.addRow(new String[] { "false", "该地址已注册售票窗口" });
            return rs;
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
                rs.addRow(new String[] { "false", "请先添加该酒店售票点" });
                return rs;
            }
            Esbticketstationtab esb = (Esbticketstationtab) ist.get(0);
            etw.setIticketstationid(esb.getIticketstationid());
            etw.setBywintype("0002");
            etw.setIversion(new Long(57));
            etw.setSzticketwincode("1");
            etw.setSzticketwinname("1");
            etw.setSzmemo("1");
            etw.setIticketwinid(new Long(0));
            etw.setIsty(0L);
            Long iticketwinid = saleCenterService.savezhuceticketwin(etw);
            if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {/*
                try {
                    javax.xml.rpc.Service service = null;
                    java.net.URL endpointURL = new java.net.URL("http://"
                            + url
                            + "/services/centersaleService?wsdl");
                    CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                            endpointURL, service);
                    ssl.setMaintainSession(true);
                    ssl.zhuceticketwin(new Long(0), etw.getIticketstationid(),
                            iscenicid, etw.getSzticketwincode(),
                            etw.getSzticketwinname(), etw.getSzipaddress(),
                            etw.getDtsellstart(), etw.getDtsellend(),
                            etw.getBywintype(), etw.getByisuse(),
                            etw.getSzmemo(), etw.getIversion(),
                            etw.getIpaddress());
                } catch (Exception e1) {
                    System.out.print(e1);
                }

            */}
            rs.addRow(new String[] { "true", iticketwinid.toString() });
            return rs;
        }
    }

    /**
     * 取出该出票员所在窗口的所售票的价格
     */
    public ResultBean getTicketPrice(Long iticketwinid, Long iemployeeid,
                                     String stdt) {

        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.getTicketPrice(iticketwinid, iemployeeid,
                new Long(1), stdt);
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
    public ResultBean getProductdatacontrol(Long itickettypeid, String stdt,String url) {
    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {/*
            try {
                javax.xml.rpc.Service service = null;
                java.net.URL endpointURL = new java.net.URL("http://"
                        + url
                        + "/services/centersaleService?wsdl");

                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                        endpointURL, service);
                ssl.setMaintainSession(true);
                com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                        .getProductdatacontrol(itickettypeid, stdt);
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
                return rb;
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return rb;
            }
        */
        	return null;
        	} else {
            ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                    .getBean("saleCenterService");
            return saleCenterService.getProductdatacontrol(itickettypeid, stdt);

        }

    }

    public String getDayTimes() {

        return Tools.getDayTimes();
    }

    public String senseServiceStatu() {
        return "1";
    }
    /**
     * describle 自助机保存订单状态
     * @param message
     * @param iticketwinid
     * @param iemployeeid
     * @param timeParam
     * @return
     */
    public ResultBean saveorder(String message, Long iticketwinid,
                                Long iemployeeid,String url) {

        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(iticketwinid);
        int ticketstation = esbticketwintab.getIticketstationid().intValue();
        String szticketstation = "";
        if (0 < ticketstation && ticketstation < 10) {
            szticketstation = "00" + ticketstation;
        } else if (ticketstation >= 10 && ticketstation < 100) {
            szticketstation = "0" + ticketstation;
        } else {
            szticketstation = "" + ticketstation;
        }
        String orid = "";
        try {
            orid = saleCenterService.updateMaxNo(szticketstation);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 拆分单个产品
        System.out.println("orid=" + orid);
        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                .getBean("saleAutoService");
        ResultBean rb = saleAutoService.saveorder(message, iticketwinid,
                iemployeeid, orid, esbticketwintab.getIscenicid(),url);

        return rb;
    }
    
    /**
     * describle 自助机保存订单状态
     * @param message
     * @param iticketwinid
     * @param iemployeeid
     * @param timeParam
     * @return
     * @throws Exception 
     */
    public ResultBean autoSaveorder(String message, Long iticketwinid,
                                Long iemployeeid,String timeParam,String url) throws Exception {

        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(iticketwinid);
        ResultBean rb = null;
        if(timeParam != null && !"".equals(timeParam)) {
        	rb = new ResultBean();
        	JSONArray parseArray = JSON.parseArray(timeParam);
        	for (Object object : parseArray) {
        		JSONObject jsonObject = (JSONObject) object;
        		Long timeId = jsonObject.getLongValue("timeId");
        		String productId = jsonObject.getString("productId");
        		int count = jsonObject.getIntValue("count");
        		String hql = "from TimeSharingTicketTab where id = "+ timeId +" and productId = '"+productId+"'";
            	GenericDao genericDao = (GenericDao) SpringUtil.getBean("genericDao");
            	ITimeSharingService timeSharingService = (ITimeSharingService) SpringUtil.getBean("timeSharingService");
            	List find = genericDao.find(hql);
            	if(find != null && find.size() > 0) {
            		TimeSharingTicketTab timeSharingTicketTab = (TimeSharingTicketTab) find.get(0);
            		Integer currStock = timeSharingTicketTab.getCurrStock()==null?0:timeSharingTicketTab.getCurrStock();
                    Integer saleStock = timeSharingTicketTab.getSaleStock()==null?0:timeSharingTicketTab.getSaleStock();
                    Integer totalStock=timeSharingTicketTab.getTatalStock()==null?0:timeSharingTicketTab.getTatalStock();
            		if(totalStock!=-1 && count > currStock) {
            			rb.addRow(new String[] { "false", "分时预约票库存不足" });
                        return rb;
            		}else {
            			timeSharingService.UpdateStock(timeId, productId, count, "minus");
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
        String orid = "";
        try {
            orid = saleCenterService.updateMaxNo(szticketstation);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 拆分单个产品
        System.out.println("orid=" + orid);
        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                .getBean("saleAutoService");
        rb = saleAutoService.autoSaveorder(message, iticketwinid,
                iemployeeid, orid, esbticketwintab.getIscenicid(),timeParam,url);
        if("false".equals(rb.getResult(0, 0))){
        	//更新库存
        	JSONArray parseArray = JSON.parseArray(timeParam);
        	for (Object object : parseArray) {
        		JSONObject jsonObject = (JSONObject) object;
        		Long timeId = jsonObject.getLongValue("timeId");
        		String productId = jsonObject.getString("productId");
        		int count = jsonObject.getIntValue("count");
            	ITimeSharingService timeSharingService = (ITimeSharingService) SpringUtil.getBean("timeSharingService");
            	timeSharingService.UpdateStock(timeId, productId, count, "add");
        	}
        }
        return rb;
    }
    public ResultBean payOrder(String orderno, Double yingshou, Double shishou,
                               Double zhaoling, Long iticketwinid, Long iemployeeid, String zffs) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        Esbticketwintab esbticketwintab = saleCenterService
                .getEsbticketwintab(iticketwinid);
        if ((shishou - yingshou) != zhaoling) {
            ResultBean rs = new ResultBean();
            rs.setColumnCount(2);
            rs.setColumnNames(new String[] { "returnstats", "message" });
            rs.addRow(new String[] { "false", "找零金额不对" });
            return rs;
        }
        Long maxid = saleCenterService.updatevouchersequence();
        System.out.println("maxid=" + maxid);
        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                .getBean("saleAutoService");
        ResultBean rb = saleAutoService.payOrder(orderno,
                esbticketwintab.getIscenicid(), yingshou, shishou, zhaoling,
                iticketwinid, iemployeeid, zffs, maxid);
        return rb;
    }

    /**
     * 根据身份证和服务商编号读取服务商网上已付款的订单信息
     *
     *
     * @auth:yuanchengjun
     * @param carno
     * @return return:ResultBean Date:2011-11-7
     */
    public ResultBean getT_order(String carno, Long iscenicid, String mac,String url) {
    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        System.out.println(" getT_order in run ");
        ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                .getBean("saleAutoService");
        Esbticketwintab esbticketwintab = saleAutoService.getEsbticketwintab(
                iscenicid, mac);
        System.out.println(" getT_order in run 1");
        Long aiscenicid = 0L;
        if (esbticketwintab == null) {
            ResultBean rs = new ResultBean();
            rs.setColumnCount(2);
            rs.setColumnNames(new String[] { "returnstats", "message" });
            System.out.println("售票窗口设置不对");
            rs.addRow(new String[] { "false", "售票窗口设置不对" });
            return rs;
        } else {
            if (esbticketwintab.getIsty() == null) {
                aiscenicid = iscenicid;
            } else {
                if (esbticketwintab.getIsty() == 0) {
                    aiscenicid = iscenicid;
                }
            }
        }

        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {/*

            try {
                javax.xml.rpc.Service service = null;
                java.net.URL endpointURL = new java.net.URL("http://"
                        + url
                        + "/services/centersaleService?wsdl");

                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                        endpointURL, service);
                ssl.setMaintainSession(true);

                com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                        .getT_orderByWin(carno, aiscenicid,
                                esbticketwintab.getIticketwinid());

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

                return rb;
            } catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return rb;
            }
        */
        	return null;
        	} else {
            ICytSaleService cytSaleService = (ICytSaleService) SpringUtil.getBean("cytSaleService");
            Sysparv5 v5 = cytSaleService.findSyspar("CYTT", "01");
            if(v5 == null || v5.getIsa() == 1L){
                ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                        .getBean("saleCenterService");
                return saleCenterService.getT_orderByWin(carno, aiscenicid,
                        esbticketwintab.getIticketwinid());
            }else{
                CytSaleMainService cytSaleMainService = (CytSaleMainService) SpringUtil.getBean("cytSaleMainService");
                return cytSaleMainService.queryCytOrder(carno,iscenicid,2);
            }
        }

    }

    public ResultBean getT_orderlist(String orid, Long iscenicid,String url) {
    	if(url==null || url.length()<1){
    		url=WebContant.GetKeyValue("CenterUrl");
    	}
        if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
            try {/*
                javax.xml.rpc.Service service = null;
                java.net.URL endpointURL = new java.net.URL("http://"
                        + url
                        + "/services/centersaleService?wsdl");

                CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
                        endpointURL, service);
                ssl.setMaintainSession(true);
                com.ectrip.ticket.centersale.client.ResultBean cano = ssl
                        .getAutoT_orderlist(orid, iscenicid);
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
                return rb;
            */
            	return null;
            	} catch (Exception e) {
                ResultBean rb = new ResultBean();
                rb.setColumnCount(2);
                rb.setColumnNames(new String[] { "returnstats", "message" });
                System.out.print(e);
                rb.addRow(new String[] { "false", e.getMessage() });
                return rb;
            }
        } else {
            ICytSaleService cytSaleService = (ICytSaleService) SpringUtil.getBean("cytSaleService");
            Sysparv5 v5 = cytSaleService.findSyspar("CYTT", "01");
            if(v5 == null || v5.getIsa() == 1L){
                ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                        .getBean("saleAutoService");
                return saleAutoService.getAutoT_orderlist(orid, iscenicid);
            }else{
                CytSaleMainService cytSaleMainService = (CytSaleMainService) SpringUtil.getBean("cytSaleMainService");
                try {
                    return cytSaleMainService.queryOrderDetail(orid,iscenicid,2);
                } catch (Exception e) {
                    ResultBean rb = new ResultBean();
                    rb.setColumnCount(2);
                    rb.setColumnNames(new String[] { "returnstats", "message" });
                    rb.addRow(new String[] { "false", e.getMessage() });
                    return rb;
                }
            }

        }
    }

    private ResultBean savetorder(String orid, Long iscenicid,
                                  Long iticketwinid, Long iemployeeid, String param,String url) {
    	if(url==null || url.length()<1){
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
        CYTPojo pojo = new CYTPojo();
        ICheckService checkService = (ICheckService) SpringUtil.getBean("checkService");
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
        	return null;
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
                return rs;
            }

            try {
                ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                        .getBean("saleAutoService");

                ResultBean rs1 = saleAutoService.savetorder(t_order, listorder,
                        listzorder, new Long(iemployeeid), new Long(
                                iticketwinid), new Long(maxid), Trealnamelist);

                if (rs1.getResult(0, 0).equals("false")) {
                    rs.addRow(new String[] { rs1.getResult(0, 0),
                            rs1.getResult(0, 1) });
                    return rs;
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
                        return rs;
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
                    return rs;
                }
            }

        } else {
            ISaleAutoService saleAutoService = (ISaleAutoService) SpringUtil
                    .getBean("saleAutoService");
            try {
                System.out.println("本地保存订单");
                rs = saleAutoService.savebenditorder(orid, iscenicid,
                        iemployeeid, iticketwinid, maxid);
                if (rs != null && rs.getRowsCount() > 0 && !"false".equalsIgnoreCase(rs.getResult(0, 0))
                        && pojo.isCYT) {
                    checkService.addCYTPojo(pojo);
                }
                return rs;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print(e);
                rs.addRow(new String[] { "false", "保存失败" });
                return rs;
            }
        }
        return null;
    }

    public int updateprintbyprintno(String szsalesvoucherno,
                                    String szticketprintno, String printtype, Long iemployeeid) {
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.updateprintbyprintno(szsalesvoucherno,
                szticketprintno, printtype, iemployeeid,szticketprintno);
    }

    /**
     * 打印回执
     *
     * @param iscenicid
     * @param isalevocherid
     * @param type
     *            1:冲打印,0默认
     * @return
     */
    private String getSalevoucher(String iscenicid, String isalevocherid,
                                  int type) {
        /*IReceiptService receiptService = (IReceiptService) SpringUtil
                .getBean("receiptService");
        return receiptService.getSalesVoucher(isalevocherid, iscenicid, type);*/
        return sysService.getSalesVoucher(isalevocherid, iscenicid, type);
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
    
    
    public ResultBean getTicketTimeCount(String productCode,String choiceDate){
    	ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.getTicketTimeCount(productCode,choiceDate);
    }

    public ResultBean isExistIDcardToday(String iscenicID,String idCard,String choiceDate,String url){
        ISaleCenterService saleCenterService = (ISaleCenterService) SpringUtil
                .getBean("saleCenterService");
        return saleCenterService.isExistIDcardToday(iscenicID,idCard,choiceDate,url);
    }
}
