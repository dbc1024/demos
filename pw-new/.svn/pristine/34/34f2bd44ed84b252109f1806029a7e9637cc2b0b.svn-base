package com.ectrip.ticket.provider.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.DateUtils;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.user.Domain;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.EcService;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.provider.Edmticketproduct;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Lineproduct;
import com.ectrip.ticket.model.provider.Linetravel;
import com.ectrip.ticket.model.provider.TicketPrintNo;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.permitenter.service.iservice.IOpwwicketSetService;
import com.ectrip.ticket.provider.service.ICrowdKindPriceService;
import com.ectrip.ticket.provider.service.IProviderService;
import com.ectrip.ticket.provider.service.ITicketTypeService;
import com.ectrip.ticket.provider.service.ITicletRuleService;
import com.ectrip.ticket.provider.service.impl.TimeSharingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping(value = "ticket")
@Api(tags = "票务服务-票务信息设置-基础篇接口")
public class ProductController extends BaseController{

	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
    public static final int ADD = 1;
    public static final int EDIT = 2;
    public static final int DELETE = 3;
    public static final int VIEW = 4;
    public static final int AUDIT = 5;

    /*private Edmtickettypetab product;//产品
    private Esbscenicareatab provider;//服务商
    private Lineproduct line;//线路附属属性
    private Hotelproduct hotel;//酒店附属属性
    private Edmticketproduct edmticket;
    private PaginationSupport ps;
    private int strutsAction;//操作方式
    private String page;//页码
    private int pageSize = PaginationSupport.PAGESIZE;//每页显示数
    private String url;//访问地址
    private String pdtp;//服务商类型
    private String type;//产品访问类型（01基础产品02套票产品）
    private EcService ecService;
    private String searchkey;//产品模糊查询
    private String printno;//票号
    private String serialnumber;//流水号
    private TicketPrintNo printNo;
    private File file;*/


    @Autowired
    private SysService sysparService;
    @Autowired
    private EcService ecService;
    
    @Autowired
    private IProviderService providerService;
    @Autowired
    private ITicletRuleService ticketruleService;
    @Autowired
    private ITicketTypeService tickettypeService;
    @Autowired
    private ICrowdKindPriceService crowdkindpriceService;
    //@Autowired
    //private ICarlinedetailtabService carlinedetailtabService;
    @Autowired
    private TimeSharingService timeSharingService;

    private String[] hoteltick;   //产品附加服务保存
    private List hotelList;
    private List tickList;

    private List<Linetravel> linetravels;//行程
    private Long time_order;

    /**
     * Describe: 票种下拉列表接口。
     * 			  组合了景区名称，格式为：景区名称_票种名称
     * @auth:ChaoYuHang
     * @return
     * Date:2012-8-23
     */
    @GetMapping(value = "/v1/ticketTypeSelect")
    @ApiOperation(value = "票种下拉列表接口")
    public ResponseBean getTicketTypeSelect(){
    	Esfemployeetab esfemployeetab = sysparService.currentUser();
        String scenics = "";
        if (esfemployeetab.getCompanytype().equals("02")) {
            scenics = esfemployeetab.getScenics();
            if(scenics==null||scenics.equals("")){
                return new ResponseBean(ERROR_CODE_400, "esfemployeetab.scenics.required");
            }
        }
        
        List list = tickettypeService.getTicketTypeSelect(scenics);
        return new ResponseBean(SUCCESS_CODE_200, "操作成功", list);
    }
    
    /**
    *
    * Describe:显示产品的子产品（如是套票，显示时组合成套票的基本票）
    * @auth:lijingrui
    * @return
    * Date:2011-10-22
    */
    @GetMapping(value = "/v1/sonTicketTypeSelect/{itickettypeid}")
    @ApiOperation(value = "子票下拉列表接口")
    public ResponseBean getSonTicketTypeSelect(@PathVariable Long itickettypeid){
    	
        
        List list = tickettypeService.getSonTicketTypeSelect(itickettypeid);
        return new ResponseBean(SUCCESS_CODE_200, "操作成功", list);
    }
    
    
    /**
     * 产品增加页面,初始化接口 Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @PostMapping(value = "/v1/productAdd")
    @ApiOperation(value = "基础票接口，产品增加数据初始化接口")
    public ResponseBean productAdd() {
        ResponseBean responseBean=new ResponseBean();
        Edmtickettypetab product = new Edmtickettypetab();
        try {
            if (product.getIscenicid() == null) {// 服务商编号
                responseBean.setCode(400);
                responseBean.setMsg("服务商编号不能为空");
                responseBean.setData(null);
                return responseBean;
            }
            Esbscenicareatab scenic = (Esbscenicareatab)providerService.get(
                    Esbscenicareatab.class, product.getIscenicid());
            product.setSzscenicname(scenic.getSzscenicname()); // 服务商名称

            /*if (scenic.getScenictype().equals("06")) {
                //产品附加服务
                tickList = tickettypeService.quckListedmticket(product.getIscenicid());
            }*/
            product.setByuselimit(new Long(0));// 使用限制（0一次性，1长期性）
            product.setByisuse(new Long(1));// 状态（0禁用，1启用）
            product.setBispersontimestat(new Long(0));// 是否参与入园统计(1是，0否)
            product.setValidityday(new Long(1));// 有效天数
            product.setIssale(new Long(0)); // 是否销售受限
            product.setIscansale(new Long(0));// 销售受限产品没有销售受限数据时是否当销售不受限产品销售
            product.setIscontrol(new Long(0));// 是否数量受限
            product.setIscontrolsale(new Long(0));// 数量受限产品没有数量受限数据时是否当数量不受限产品销售
            product.setIsequence(new Long(0));// 排序
            product.setIticketnoruleid(0L);//票号规则
            product.setMcostprice(0.0);//成本价
            product.setMnominalfee(0.0);//工本费
            product.setInt1(0L);
            product.setInt3(0L);
            product.setTimes("[]");
            product.setByusage(new Long(0));// 使用人群（0 -- 常规票, 1-- 员工卡,2 -- 居民卡,3 --
            // 充值票,4 -- 结算卡,5 -- 贵宾票,6 -- 贵宾票+ 结算）
            product.setByuselimit(new Long(0));// 使用限制（0一次性，1长期性）
            product.setBymaketicketway("00");// 出票方式（00现场激活,01现场打印，02打印激活03不做打印）
            product.setBymediatype("00");// 介质类型（00条码，01感应卡RI型，02感应卡RWI型，03感应卡RWII型）
            product.setSzticketprintcode("无");// 印刷代码
            responseBean.setCode(200);
            responseBean.setMsg("增加成功");
            responseBean.setData(product);
            return responseBean;

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("添加基础接口异常",e.getCause());
            responseBean.setCode(400);
            responseBean.setMsg("服务器错误");
            responseBean.setData(null);
            return responseBean;
        }
    }
    /**
     * 描述：获取产品添加页面产品种类，出票方式，介质类型
     * @param scenicType 景区类型
     * @return
     */
    @SuppressWarnings("rawtypes")
	@PostMapping(value = "/v1/queryInitData")
    @ApiOperation(value = "基础票接口，产品增加初始化产品种类，出票方式，介质类型三个数据接口")
    public ResponseBean queryInitData(@RequestParam String scenicType) {
    	ResponseBean responseBean = new ResponseBean();
    	Map<String,Object> data = new HashMap<String,Object>();
    	int code = 200;
    	String msg = "接口请求成功";
    	try {
			// 产品种类
			List protype = sysparService.querypdtpList(scenicType, "01");
			// 出票方式
			List cpfslist = sysparService.retrieve("CPFS");
			// 介质类型
			List mediatypelist = sysparService.retrieve("CKFS");
			data = new HashMap<String,Object>();
			data.put("protype", protype);
			data.put("cpfslist", cpfslist);
			data.put("mediatypelist", mediatypelist);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info("初始化产品种类，出票方式，介质类型数据接口异常",e.getCause());
			code = 400;
	    	msg = "接口请求异常";
		}
    	 responseBean.setCode(code);
         responseBean.setMsg(msg);
         if(data != null) {
        	 responseBean.setData(data);
         }
        return responseBean;
    }
    /**
     * 产品修改 Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @PostMapping(value = "/v1/productEdit")
    @ApiOperation(value = "基础票接口，产品修改")
    public ResponseBean productEdit(@RequestParam Long itickettypeid) {
        //this.strutsAction = EDIT;
        ResponseBean responseBean=new ResponseBean();
        try {
            Esfemployeetab esfemployeetab = null;

            // 查询产品信息
           Edmtickettypetab product = tickettypeService.queryTickettype(itickettypeid);
            Long int3 = product.getInt3();
            if(int3 != null &&int3 == 1L) {
                //List findTimeTicketByProductCode = timeSharingService.findTimeTicketByProductCode(product.getSztickettypecode());
                List findTimeTicketByProductCode = timeSharingService.findTimeTicketByProductCodeAnddate(product.getSztickettypecode(),product.getNote3());
                if(findTimeTicketByProductCode != null && findTimeTicketByProductCode.size()>0 ) {
                    product.setTimes(JSON.toJSONString(findTimeTicketByProductCode));
                }else {
                    product.setTimes("0");
                }
                product.setTimeList(findTimeTicketByProductCode);
                List findTimeTicketByProductCode2 = timeSharingService.findTimeTicketByProductCode(product.getSztickettypecode(), product.getNote3(), product.getNote4());
                if(findTimeTicketByProductCode2 != null && findTimeTicketByProductCode2.size()>0) {
                    StringBuffer timeIds = new StringBuffer();
                    for (Object object : findTimeTicketByProductCode2) {
                        TimeSharingTicketTab _SharingTicketTab = (TimeSharingTicketTab)object;
                        timeIds.append(_SharingTicketTab.getId()).append(",");
                    }
                    product.setTimes(timeIds.toString());
                }
            }else{
                product.setTimes("0");
                product.setInt3(0L);
                product.setTimeList(null);
            }
            Esbscenicareatab provider = (Esbscenicareatab) this.providerService
                    .get(Esbscenicareatab.class, product.getIscenicid());
            product.setSzscenicname(provider.getSzscenicname()); // 服务商名称
            //this.setTime_order(product.getInt3());

           /* if (provider.getScenictype().equals("06")) {
                //产品附加服务
                tickList = tickettypeService.quckListedmticket(product.getIscenicid());

                hotelList = tickettypeService.showAllhotelduct(product.getItickettypeid());
            }*/
            responseBean.setCode(200);
            responseBean.setMsg("请求成功");
            responseBean.setData(null);
            return responseBean;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("修改基础接口异常",e.getCause());
            responseBean.setCode(400);
            responseBean.setMsg("服务器异常");
            responseBean.setData(null);
            return responseBean;
        }
    }

    /**
     * 产品删除 Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @PostMapping(value = "/v1/productDel")
    @ApiOperation(value = "基础票接口，产品删除")
    public ResponseBean productDel(@RequestBody Edmtickettypetab product) {
        //this.strutsAction = DELETE;
        ResponseBean responseBean=new ResponseBean();
        try {
            Esfemployeetab esfemployeetab = null;

            if (product.getItickettypeid() == null) {// 产品Id
                responseBean.setCode(400);
                responseBean.setMsg("产品编号不为能为空");
                responseBean.setData(null);
                return responseBean;
            }
            product = tickettypeService.queryTickettype(product.getItickettypeid());

            Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.providerService
                    .get(Esbscenicareatab.class, product.getIscenicid());
            Long int3 = product.getInt3();
            if(int3!= null && int3 == 1L) {
                List findTimeTicketByProductCode = timeSharingService.findTimeTicketByProductCode(product.getSztickettypecode());
                product.setTimeList(findTimeTicketByProductCode);
            }
            //如是酒店服务商下的产品，显示出附加产品信息。
            if (esbscenicareatab.getScenictype().equals("06")) {
                if (!product.getBycategorytype().equals("119") && !product.getBycategorytype().equals("120")) {
                    hotelList = tickettypeService.quickviewhotelticket(product.getItickettypeid());
                }
            }

            product.setSzscenicname(esbscenicareatab.getSzscenicname()); // 服务商名称
            if ("01".equals(esbscenicareatab.getScenictype())
                    || "02".equals(esbscenicareatab.getScenictype())
                    || "03".equals(esbscenicareatab.getScenictype())
                    || "04".equals(esbscenicareatab.getScenictype())
                    || "05".equals(esbscenicareatab.getScenictype())) {// 景区
                responseBean.setCode(200);
                responseBean.setMsg("跳转");
                responseBean.setData("jingqu");
                return responseBean;
            } else {
                responseBean.setCode(200);
                responseBean.setMsg("成功");
                responseBean.setData(null);
                return responseBean;
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("删除基础接口异常",e.getCause());
            responseBean.setCode(400);
            responseBean.setMsg("服务器错误");
            responseBean.setData(null);
            return responseBean;
        }
    }

    /**
     * 产品查看 Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @PostMapping("v1/productView")
    @ApiOperation(value = "基础票接口，产品查看")
    public ResponseBean productView(@RequestBody Edmtickettypetab product) {
        //this.strutsAction = VIEW;
        ResponseBean responseBean=new ResponseBean();
        try {
            if (product.getItickettypeid() == null
                    || "".equals(product.getItickettypeid())) {// 产品Id
                // 产品编号不为能为空
                responseBean.setCode(400);
                responseBean.setMsg("产品编号不为能为空");
                responseBean.setData(null);
                return responseBean;
            }
            product = tickettypeService.queryTickettype(product.getItickettypeid());
            Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.providerService
                    .get(Esbscenicareatab.class, product.getIscenicid());

            Long int3 = product.getInt3();
            if(int3!= null && int3 == 1L) {
                List findTimeTicketByProductCode = timeSharingService.findTimeTicketByProductCode(product.getSztickettypecode());
                product.setTimeList(findTimeTicketByProductCode);
            }

            //如是酒店服务商下的产品，显示出附加产品信息。
            if (esbscenicareatab.getScenictype().equals("06")) {
                if (!product.getBycategorytype().equals("119") && !product.getBycategorytype().equals("120")) {
                    hotelList = tickettypeService.quickviewhotelticket(product.getItickettypeid());
                }
            }

            product.setSzscenicname(esbscenicareatab.getSzscenicname()); // 服务商名称

            if ("01".equals(esbscenicareatab.getScenictype())
                    || "02".equals(esbscenicareatab.getScenictype())
                    || "03".equals(esbscenicareatab.getScenictype())
                    || "04".equals(esbscenicareatab.getScenictype())
                    || "05".equals(esbscenicareatab.getScenictype())) {// 景区
                responseBean.setCode(200);
                responseBean.setMsg("跳转");
                responseBean.setData("jingqu");
                return responseBean;
            } else {
                responseBean.setCode(200);
                responseBean.setMsg("成功");
                responseBean.setData(null);
                return responseBean;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("查看基础接口异常",e.getCause());
            responseBean.setCode(400);
            responseBean.setMsg("服务器异常");
            responseBean.setData(null);
            return responseBean;
        }
    }

    /**
     * 产品保存 Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @PostMapping("v1/productSave")
    @ApiOperation(value = "基础票接口，产品查看")
    public ResponseBean productSave(HttpServletRequest request,@RequestParam String pdtp,@RequestBody Edmtickettypetab product,@RequestParam int strutsAction) {
        ResponseBean responseBean=new ResponseBean();
        try {
            Esfemployeetab esfemployeetab = null;
            if (strutsAction == ADD || strutsAction == EDIT) {// 增加、修改

                if (product.getIscenicid() == null || "".equals(product.getIscenicid())) {
                    responseBean.setCode(400);
                    responseBean.setMsg("服务商编号不为能为空");
                    responseBean.setData(null);
                    return responseBean;
                }
                if (product.getSzmemo().length()>3800 ) {
                    responseBean.setCode(400);
                    responseBean.setMsg("保存的长度过长，不能超过3000");
                    responseBean.setData(null);
                    return responseBean;
                }
                if (strutsAction == EDIT) {
                    if (product.getItickettypeid() == null
                            || "".equals(product.getItickettypeid())) {// 产品Id
                        responseBean.setCode(400);
                        responseBean.setMsg("产品编号不能为空");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }

                if (pdtp != null && !pdtp.equals("10")) {
                    if (product.getSztickettypecode() == null
                            || "".equals(product.getSztickettypecode().trim())) {
                        responseBean.setCode(400);
                        responseBean.setMsg("产品编码不能为空");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }

                if (product.getSztickettypecode() != null && !"".equals(product.getSztickettypecode())) {
                    Esbscenicareatab esbscen = (Esbscenicareatab) this.providerService.get(Esbscenicareatab.class, product.getIscenicid());
                    if ("06".equals(esbscen.getScenictype()) || "07".equals(esbscen.getScenictype()) || "08".equals(esbscen.getScenictype()) || "09".equals(esbscen.getScenictype())) {
                        Pattern p = Pattern.compile("^[A-Za-z0-9]{1,20}$");
                        boolean a = p.matcher(product.getSztickettypecode().toString()).matches();
                        if (a == false) {
                            responseBean.setCode(400);
                            responseBean.setMsg("产品编码格式不对，只能输入由数字或大写英文字母组成!");
                            responseBean.setData(null);
                            return responseBean;
                        }
                    } else {
                        Pattern p = Pattern.compile("^[A-Z0-9]{2,3}$");
                        boolean a = p.matcher(product.getSztickettypecode().toString()).matches();
                        if (a == false) {
                            responseBean.setCode(400);
                            responseBean.setMsg("产品编码格式不对，只能输入由数字或大写英文字母组成的3位字符");
                            responseBean.setData(null);
                            return responseBean;
                        }

                        if (product.getSztickettypecode().length() >= 4) {
                            responseBean.setCode(400);
                            responseBean.setMsg("产品编码长度不能只能为3位字符");
                            responseBean.setData(null);
                            return responseBean;
                        }
                    }

                }
                if (product.getSztickettypename() == null
                        || "".equals(product.getSztickettypename().trim())) {
                    responseBean.setCode(400);
                    responseBean.setMsg("产品名称不能为空");
                    responseBean.setData(null);
                    return responseBean;
                }
                if (product.getSztickettypename() != null && !"".equals(product.getSztickettypename())) {
                    if (product.getSztickettypename().length() > 25) {
                        responseBean.setCode(400);
                        responseBean.setMsg("您输入的产品名字字符太长,请重新输入!");
                        responseBean.setData(null);
                        return responseBean;
                    }

                }

                if (pdtp != null && !pdtp.equals("01")) {
                    if (product.getBycategorytype() == null || product.getBycategorytype().equals("")) {
                        responseBean.setCode(400);
                        responseBean.setMsg("产品类别不能为空");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }
                if (product.getMnominalfee() == null
                        || "".equals(product.getMnominalfee())) {
                    responseBean.setCode(400);
                    responseBean.setMsg("产品工本费不能为空");
                    responseBean.setData(null);
                    return responseBean;
                }
                if (product.getMnominalfee() != null
                        && !"".equals(product.getMnominalfee())) {
                    Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
                    boolean b = p.matcher(product.getMnominalfee().toString()).matches();
                    if (b == false) {
                        responseBean.setCode(400);
                        responseBean.setMsg("格式不正确，最多可精确到小数点后两位");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }
                if (product.getByusage() == 1 && product.getBymaketicketway().equals("01") && (product.getBymediatype().equals("00") || product.getBymediatype().equals("01"))) {
                    responseBean.setCode(400);
                    responseBean.setMsg("预制票不支持一票多人检票");
                    responseBean.setData(null);
                    return responseBean;
                }

                if (product.getByusage() == 1 && product.getBymaketicketway().equals("02")) {
                    responseBean.setCode(400);
                    responseBean.setMsg("身份证出票的不支持一票多人检票方式!");
                    responseBean.setData(null);
                    return responseBean;
                }

                // 查询服务商
                Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.providerService
                        .get(Esbscenicareatab.class, product.getIscenicid());
                if (product.getValidityday() == null || product.getValidityday().equals("")) {
                    responseBean.setCode(400);
                    responseBean.setMsg("有效天数不能为空!");
                    responseBean.setData(null);
                    return responseBean;
                }
                if (product.getValidityday() != null
                        && !"".equals(product.getValidityday())) {
                    Pattern p = Pattern.compile("^\\d+$");
                    boolean b = p.matcher(product.getValidityday().toString()).matches();
                    if (b == false) {
                        responseBean.setCode(400);
                        responseBean.setMsg("有效天数格式不正确，请输入大于等于0的整数");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }
                if (product.getIsequence() == null || "".equals(product.getIsequence())) {
                    product.setIsequence(new Long(0));
                }
                if (product.getIsequence() != null
                        && !"".equals(product.getIsequence())) {
                    Pattern p = Pattern.compile("^\\d+$");
                    boolean b = p.matcher(product.getIsequence().toString()).matches();
                    if (b == false) {
                        responseBean.setCode(400);
                        responseBean.setMsg("排序格式不正确，请输入大于等于0的整数");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }

                if (strutsAction == EDIT) {
                    if (product.getByusage() != null && product.getByusage() == 1) {
                        boolean isuse = this.providerService.volidateSole(new String[]{"itickettypeid", "byisuse", "ipeoplenumrange"}, new Long[]{product.getItickettypeid(), 1L, 1L}, new String[]{}, new String[]{}, "Edmcrowdkindpricetab");
                        if (!isuse) {
                            responseBean.setCode(400);
                            responseBean.setMsg("一票多人的产品不支持实名制,请先修改产品价格信息!");
                            responseBean.setData(null);
                            return responseBean;
                        }
                    }
                }

            }
            if (strutsAction == DELETE) {
                if (product.getItickettypeid() == null
                        || "".equals(product.getItickettypeid())) {// 产品Id
                    responseBean.setCode(400);
                    responseBean.setMsg("产品编号不为能为空");
                    responseBean.setData(null);
                    return responseBean;
                }
            }

            Syslog syslog = new Syslog();
            syslog.setIemployeeid(esfemployeetab.getIemployeeid());// 后台操作人

            if (strutsAction == ADD) {

                // 得到最大编号
                long maxseq = this.providerService.getMaxPk("itickettypeid",
                        "Edmtickettypetab");
                product.setItickettypeid(maxseq + 1);
                if (pdtp != null && pdtp.equals("10")) {
                    product.setSztickettypecode(String.valueOf(maxseq + 1));
                }

                //判断同一个服务商下产品编码是否存在
                boolean isuse = this.providerService.volidateSole(new String[]{"iscenicid"}, new Long[]{product.getIscenicid()}, new String[]{"sztickettypecode"}, new String[]{product.getSztickettypecode()}, "Edmtickettypetab");
                if (!isuse) {
                    responseBean.setCode(400);
                    responseBean.setMsg("产品编码在此服务商下已经存在");
                    responseBean.setData(null);
                    return responseBean;
                }

                product.setSzticketprintcode(product.getSztickettypecode());//印刷代码与产品编码一样

                //新增分时时间段
                long istimeSharing = product.getInt3();//判断是否分时
                if (istimeSharing == 1) {
                    String timeStartVal[] =request.getParameterValues("time_start");
                    String timeEndVal[] =request.getParameterValues("time_end");
                    String stock[] =request.getParameterValues("stock");
                    String note3 = product.getNote3();
                    String note4 = product.getNote4();
                    int days = DateUtils.diffDate(DateUtils.convertDate(note4),DateUtils.convertDate(note3));
                    Long id = 1L;
                    if(days>0) {
                        for(int j=0;j <= days;j++) {
                            String dayTime = DateUtils.formatDate(DateUtils.addDay(DateUtils.convertDate(note3), j), "yyyy-MM-dd");
                            for (int i = 0; i < timeStartVal.length; i++) {
                                Long maxId = timeSharingService.getMaxPk("id", "TimeSharingTicketTab");
                                String startDate = timeStartVal[i];
                                String endDate = timeEndVal[i];
                                int totalStock = Integer.parseInt(stock[i]);
                                TimeSharingTicketTab _SharingTicketTab = new TimeSharingTicketTab();
                                _SharingTicketTab.setId(maxId+id);
                                _SharingTicketTab.setStartDate(startDate);
                                _SharingTicketTab.setEndDate(endDate);
                                _SharingTicketTab.setCurrStock(totalStock);
                                _SharingTicketTab.setTatalStock(totalStock);
                                _SharingTicketTab.setSaleStock(0);
                                _SharingTicketTab.setProductId(product.getSztickettypecode());
                                _SharingTicketTab.setDayTime(dayTime);
                                timeSharingService.saveTimes(_SharingTicketTab);
                                id++;
                            }
                        }

                    }

                }
                // 保存产品
                tickettypeService.insertTicketType(product, hoteltick, syslog);
                responseBean.setCode(200);
                responseBean.setMsg("产品新增成功");
                responseBean.setData("欢迎使用,"+WebContant.DOMAINAME);
                return responseBean;
            }
            if (strutsAction == EDIT) {

                Edmtickettypetab tickettype = tickettypeService.queryTickettype(product.getItickettypeid());
                if (!product.getSztickettypecode().equals(tickettype.getSztickettypecode())) {

                    //判断同一个服务商下产品编码是否存在
                    boolean isuse = this.providerService.volidateSole(new String[]{"iscenicid"}, new Long[]{product.getIscenicid()}, new String[]{"sztickettypecode"}, new String[]{product.getSztickettypecode()}, "Edmtickettypetab");
                    if (!isuse) {
                        responseBean.setCode(200);
                        responseBean.setMsg("产品编码在此服务商下已经存在");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }
                if (tickettype.getBycategorytype().equals("0004")) { //产品类别由剧院票改成别的票种时，判断剧院票是否绑定节目销售
                    if (!product.getBycategorytype().equals(tickettype.getBycategorytype())) {
                        responseBean.setCode(400);
                        responseBean.setMsg("产品类别为剧院票的产品不能修改成别的类别票种!");
                        responseBean.setData(null);
                        return responseBean;
                    }
                }
                product.setSzticketprintcode(product.getSztickettypecode());//印刷代码与产品编码一样
                //修改判断分时预约
                //判断票修改前是否分时预约
                Edmtickettypetab _Edmtickettypetab = tickettypeService.queryTickettype(product.getItickettypeid());
                Long int3 = _Edmtickettypetab.getInt3();
                if(int3 != null && int3 == 1L && product.getInt3() == 1L) {
                    //1.判断是否可以修改
                    if(product.getInt3() == 1L) {
                        boolean  isExist=tickettypeService.querytickettypeByOrder(product.getItickettypeid());
                        //新增已经卖出去的票提示不能修改
                        if(isExist)
                        {
                            responseBean.setCode(400);
                            responseBean.setMsg("该产品已有预订,不能修改");
                            responseBean.setData(null);
                            return responseBean;
                        }
                        String timeStartVal[] = request.getParameterValues("time_start");
                        String timeEndVal[] = request.getParameterValues("time_end");
                        String stock[] = request.getParameterValues("stock");
                        String timeIds = request.getParameter("timeIds");
//                     Edmtickettypetab _Edmtickettypetab = tickettypeService.queryTickettype(product.getItickettypeid());
                        String startDate = _Edmtickettypetab.getNote3();//原始开始时间
                        String endDate = _Edmtickettypetab.getNote4();//原始结束时间
                        String note4 = product.getNote4(); //新的结束时间
                        int updateDays = DateUtils.diffDate(DateUtils.convertDate(endDate),DateUtils.convertDate(startDate));
                        int newDays = DateUtils.diffDate(DateUtils.convertDate(note4),DateUtils.convertDate(endDate));
                        for (int j=0;j<=updateDays;j++) {
                            String dayTime = DateUtils.formatDate(DateUtils.addDay(DateUtils.convertDate(startDate), j),
                                    "yyyy-MM-dd");
                            List findTimeTicket = timeSharingService.findTimeTicketByProductCode(product.getSztickettypecode(), dayTime, dayTime);
                            for(int i=0;i<timeStartVal.length;i++) {
                                TimeSharingTicketTab _SharingTicketTab = (TimeSharingTicketTab)findTimeTicket.get(i);
                                String timeStart = timeStartVal[i];
                                String timeEnd = timeEndVal[i];
                                String stocks = stock[i];
                                if(_SharingTicketTab.getSaleStock() == 0) {
                                    _SharingTicketTab.setStartDate(timeStart);
                                    _SharingTicketTab.setEndDate(timeEnd);
                                    _SharingTicketTab.setTatalStock(Integer.parseInt(stocks));
                                    _SharingTicketTab.setCurrStock(Integer.parseInt(stocks));
                                    timeSharingService.update(_SharingTicketTab);
                                }else {
                                    responseBean.setCode(400);
                                    responseBean.setMsg("该时段已经有卖票，不允许修改时间段");
                                    responseBean.setData(null);
                                    return responseBean;
                                }
                            }
                        }
                        //新增
                        if (newDays > 0) {
                            for (int j = 1; j <= newDays; j++) {
                                int id = 1;
                                String dayTime = DateUtils.formatDate(DateUtils.addDay(DateUtils.convertDate(endDate), j),
                                        "yyyy-MM-dd");
                                for (int i = 0; i < timeStartVal.length; i++) {
                                    Long maxId = timeSharingService.getMaxPk("id", "TimeSharingTicketTab");
                                    String startDate1 = timeStartVal[i];
                                    String endDate1 = timeEndVal[i];
                                    int totalStock = Integer.parseInt(stock[i]);
                                    TimeSharingTicketTab _SharingTicketTab = new TimeSharingTicketTab();
                                    _SharingTicketTab.setId(maxId + id);
                                    _SharingTicketTab.setStartDate(startDate1);
                                    _SharingTicketTab.setEndDate(endDate1);
                                    _SharingTicketTab.setCurrStock(totalStock);
                                    _SharingTicketTab.setTatalStock(totalStock);
                                    _SharingTicketTab.setSaleStock(0);
                                    _SharingTicketTab.setProductId(product.getSztickettypecode());
                                    _SharingTicketTab.setDayTime(dayTime);
                                    timeSharingService.saveTimes(_SharingTicketTab);
                                    id++;
                                }
                            }
                        }
                        //2.删除分时时间段
                    }
                }else if(int3 != null && int3 == 1L && product.getInt3() == 0L) {
                    //将分时预约票修改为普通票
                    boolean  isExist=tickettypeService.querytickettypeByOrder(product.getItickettypeid());
                    if(isExist)
                    {
                        responseBean.setCode(400);
                        responseBean.setMsg("该产品已有预订,不能修改");
                        responseBean.setData(null);
                        return responseBean;
                    }
                    List findTimeTicketsByProductCode = timeSharingService.findTimeTicketsByProductCode(product.getSztickettypecode());
                    if(findTimeTicketsByProductCode != null && findTimeTicketsByProductCode.size()>0) {
                        timeSharingService.deleteAll(findTimeTicketsByProductCode);
                    }

                }else if(product.getInt3() == 1L && (int3 == null || int3 == 0L)) {
                    //将分时预约票修改为普通票
                    boolean  isExist=tickettypeService.querytickettypeByOrder(product.getItickettypeid());
                    if(isExist)
                    {
                        responseBean.setCode(400);
                        responseBean.setMsg("该产品已有预订,不能修改");
                        responseBean.setData(null);
                        return responseBean;
                    }

                    String timeStartVal[] = request.getParameterValues("time_start");
                    String timeEndVal[] = request.getParameterValues("time_end");
                    String stock[] = request.getParameterValues("stock");
                    String note3 = product.getNote3();
                    String note4 = product.getNote4();
                    int days = DateUtils.diffDate(DateUtils.convertDate(note4),DateUtils.convertDate(note3));
                    Long id = 1L;
                    if(days>0) {
                        for(int j=0;j <= days;j++) {
                            String dayTime = DateUtils.formatDate(DateUtils.addDay(DateUtils.convertDate(note3), j), "yyyy-MM-dd");
                            for (int i = 0; i < timeStartVal.length; i++) {
                                Long maxId = timeSharingService.getMaxPk("id", "TimeSharingTicketTab");
                                String startDate = timeStartVal[i];
                                String endDate = timeEndVal[i];
                                int totalStock = Integer.parseInt(stock[i]);
                                TimeSharingTicketTab _SharingTicketTab = new TimeSharingTicketTab();
                                _SharingTicketTab.setId(maxId+id);
                                _SharingTicketTab.setStartDate(startDate);
                                _SharingTicketTab.setEndDate(endDate);
                                _SharingTicketTab.setCurrStock(totalStock);
                                _SharingTicketTab.setTatalStock(totalStock);
                                _SharingTicketTab.setSaleStock(0);
                                _SharingTicketTab.setProductId(product.getSztickettypecode());
                                _SharingTicketTab.setDayTime(dayTime);
                                timeSharingService.saveTimes(_SharingTicketTab);
                                id++;
                            }
                        }

                    }
                }
                // 修改产品信息
                tickettypeService.updateTicketType(product, hoteltick, syslog);
                responseBean.setCode(200);
                responseBean.setMsg("产品修改成功");
                responseBean.setData("欢迎使用"
                        + WebContant.DOMAINAME);
                return responseBean;
            }
            if (strutsAction == DELETE) {
                // 判断在订单中是否存在此产品信息
                boolean isuse = tickettypeService.querytickettypeByOrder(product
                        .getItickettypeid());
                if (isuse) {
                    responseBean.setCode(400);
                    responseBean.setMsg("此产品信息在订单中存在，无法删除");
                    responseBean.setData(null);
                    return responseBean;
                }
                List plist = this.crowdkindpriceService.getpriceListbyprno(product.getItickettypeid());
                System.out.println(plist.size());
                if (plist != null && plist.size() > 0) {
                    responseBean.setCode(400);
                    responseBean.setMsg("该产品存在价格信息，不能删除");
                    responseBean.setData(null);
                    return responseBean;
                } else {
                    Edmtickettypetab _Edmtickettypetab = tickettypeService.queryTickettype(product.getItickettypeid());
                    List findTimeTicketsByProductCode = timeSharingService.findTimeTicketsByProductCode(_Edmtickettypetab.getSztickettypecode());
                    if(findTimeTicketsByProductCode != null && findTimeTicketsByProductCode.size()>0) {
                        timeSharingService.deleteAll(findTimeTicketsByProductCode);
                    }
                    // 删除产品
                    tickettypeService
                            .deleteTicketType(product.getItickettypeid(), syslog);

                    responseBean.setCode(200);
                    responseBean.setMsg("产品删除成功");
                    responseBean.setData("欢迎使用"+ WebContant.DOMAINAME);
                    return responseBean;
                }
            }
            responseBean.setCode(200);
            responseBean.setMsg("成功");
            responseBean.setData("欢迎使用"+ WebContant.DOMAINAME);
            return responseBean;
        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setCode(400);
            responseBean.setMsg("服务器错误");
            responseBean.setData(null);
            return responseBean;
        }
    }

    /**
     * 查看列表 Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @PostMapping("v1/productViewList")
    @ApiOperation(value = "基础票接口，查看产品列表")
    public ResponseBean productViewList(HttpServletRequest request,String type,String pdtp,int page,int pageSize) {
        ResponseBean responseBean=new ResponseBean();
        PaginationSupport ps = null;
        try {
            Esfemployeetab esfemployeetab = null;
            if (type == null || "".equals(type)) {
                type = "01";//表示基础类
            }
            //获取域名
            String url = request.getServerName();
            List<Domain> listDomain = ecService.getDomain(url,"1");
            boolean isHqyatu = false;

            String groupId = null;
            Long seq = 0L;
            if(listDomain!=null){
                Domain domain = listDomain.get(0);
                groupId = domain.getGroupId();
                seq = domain.getSeq();
            }

            if(1==seq || 2 == seq){
                isHqyatu = true;
            }
            Esbscenicareatab provider = new Esbscenicareatab();
            if ("01".equals(type)) {//票础票
                //得到服务商列表
//				List providerlist = providerService.getScenicList(esfemployeetab, "",0,"01");
                List providerlist = providerService.getScenicList(esfemployeetab, pdtp, 0, "01",groupId,isHqyatu);
                request.setAttribute("providerlist", providerlist);
                if (providerlist != null && providerlist.size() > 0) {
                    if (pdtp.equals("01")) {
                        provider.setIscenicid(((Map) providerlist.get(0)).get("iscenicid").toString());
                    }
                } else {
                    responseBean.setCode(200);
                    responseBean.setMsg("无服务商，请先添加服务商！");
                    responseBean.setData(null);
                    return responseBean;
                }
            } else if ("02".equals(type)) {//套票
                //得到服务商列表
                List providerlist = providerService.getScenicList(esfemployeetab, "01", 0, "01",groupId,isHqyatu);
                request.setAttribute("providerlist", providerlist);
                if (providerlist != null && providerlist.size() > 0) {
                    if (pdtp.equals("01")) {
                        provider.setIscenicid(((Map) providerlist.get(0)).get("iscenicid").toString());
                    }
                } else {
                    responseBean.setCode(400);
                    responseBean.setMsg("无服务商，请先添加服务商！");
                    responseBean.setData(null);
                    return responseBean;
                }
            } else if ("06".equals(type)) {
                //得到服务商列表
                List providerlist = providerService.getScenicList(esfemployeetab, pdtp, 0, "01",groupId,isHqyatu);
                request.setAttribute("providerlist", providerlist);
            }

            request.setAttribute("providerId", provider.getIscenicid());// 保存服务商列表
            String name = providerService.QueryProviderName(provider.getIscenicid());
            request.setAttribute("providername", name);// 保存服务商名称

            // 查询某服务商下所有产品
            ps = tickettypeService.getTickettypeList(null, provider.getIscenicid(), page, pageSize, url, type);

        } catch (Exception e) {
            e.printStackTrace();
            responseBean.setCode(400);
            responseBean.setMsg("服务器错误");
            responseBean.setData(null);
            return responseBean;
        }
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(ps);
        return responseBean;
    }

    /**
     * Describe:显示票务信息
     *
     * @return return:list
     * Date:2013-03-22
     * @auth:chenyidong
     */
    @GetMapping("v1/showticketSelectOption/{iscenicid}/{type}")
    @ApiOperation("获取景区服务商列表")
    public ResponseBean showListviewticktype(@PathVariable Long iscenicid,@PathVariable String type) {
        ResponseBean responseBean=new ResponseBean();
        if (type == null || "".equals(type)) {//票类型
            type = "01";//表示基础类
        }
        if (iscenicid== null || iscenicid==0L) {// 服务商编号
            responseBean.setCode(400);
            responseBean.setMsg("没有服务商编号");
            responseBean.setData(null);
            return responseBean;
        }
        tickList = tickettypeService.getTickettypeListSelect(iscenicid, type);
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(tickList);
        return responseBean;
    }

    /**
     * 产品查询
     * Describe:
     *
     * @return return:String Date:2011-9-22
     * @auth:huangyuqi
     */
    @GetMapping("v1/productviewlistsearch")
    @ApiOperation("产品查询")
    public ResponseBean productViewListSearch(HttpServletRequest request,
                                              Long iscenicid,
                                              Long itickettypeid,String type,String searchkey,String pdtp,int page,int pageSize) {
        ResponseBean responseBean=new ResponseBean();
        PaginationSupport ps = null;
        try {
            Esfemployeetab esfemployeetab = null;

            if (iscenicid == null) {// 服务商编号
                responseBean.setCode(400);
                responseBean.setMsg("服务商编号不能为空");
                responseBean.setData(null);
                return responseBean;
            }

            if (type == null || "".equals(type)) {//票类型
                type = "01";//表示基础类
            }

            request.setAttribute("providerId", iscenicid);// 保存服务商编号
            //获取服务商名称
            String name = providerService.QueryProviderName(iscenicid);
            request.setAttribute("providername", name);// 保存服务商名称
            //获取域名
            String url = request.getServerName();
            List<Domain> listDomain = ecService.getDomain(url,"1");
            List listScenic = null;
            boolean isHqyatu = false;
            Domain domain = listDomain.get(0);
            if(1==domain.getSeq() || 2 == domain.getSeq()){
                isHqyatu = true;
            }else{
                listScenic = sysparService.getComscenicsId(Long.parseLong(domain.getGroupId()));
            }
            if ("01".equals(type)) {//票础票
                //得到服务商列表
                List providerlist = providerService.getScenicList(esfemployeetab, pdtp, 0, "01",domain.getGroupId(),isHqyatu);
                request.setAttribute("providerlist", providerlist);
            } else if ("02".equals(type)) {
                //得到服务商列表
                //	List providerlist = providerService.getScenicList(esfemployeetab, "01",0,"02");
                List providerlist = providerService.getScenicList(esfemployeetab, "01", 0, "01",domain.getGroupId(),isHqyatu);
                request.setAttribute("providerlist", providerlist);
            }
            if (searchkey != null && !searchkey.equals("")) {
                url += "&searchkey=" + searchkey;

            }

            if (itickettypeid != null && itickettypeid != 0L) {
                Edmtickettypetab product = tickettypeService.queryTickettype(itickettypeid);
            } else {
                if (searchkey != null && !"".equals(searchkey)) {
                    // 查询某服务商下所有产品.添加了产品模糊字段
                    ps = tickettypeService.getTickettypeList(searchkey, iscenicid,page, pageSize, url, type);
                } else {
                    // 查询某服务商下所有产品
                    ps = tickettypeService.getTickettypeList(null, iscenicid, page, pageSize, url, type);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            responseBean.setCode(400);
            responseBean.setMsg("服务商错误");
            responseBean.setData(null);
            return responseBean;
        }
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 增加酒店附加属性 Describe:
     *
     * @return return:String Date:2011-9-26
     * @auth:huangyuqi
     */
    @GetMapping("v1/productHotelAdd")
    @ApiOperation("产品添加")
    public ResponseBean productHotelAdd(HttpServletRequest request,Edmtickettypetab product,String pdtp) {
        ResponseBean  responseBean=new ResponseBean();
        if (product.getItickettypeid() == null
                || "".equals(product.getItickettypeid())) {
            responseBean.setCode(400);
            responseBean.setMsg("产品编号不能为空");
            responseBean.setData(null);
            return responseBean;
        }

        List bedtypelist = sysparService.retrieve("BETP");
        request.setAttribute("bedtypelist", bedtypelist);


        // 根据产品编号查找酒店附属属性
        Hotelproduct hotel = (Hotelproduct) tickettypeService.getOthersProduct(product
                .getItickettypeid(), pdtp);
        if (hotel != null) {
            if (hotel.getInoteger1() == null || "".equals(hotel.getInoteger1())) {
                hotel.setInoteger1(0L);
            }
        }
        if (hotel == null) {
            hotel = new Hotelproduct();
            hotel.setBreakfasttype(0);// 早餐类型
            hotel.setWidebandtype(0);// 宽带类型
            hotel.setWeektype(0);// 周未类型
            hotel.setDeposittype(1);// 定金方式  1表示全额支付、0标识定金支付
            hotel.setNumter1(0);
            hotel.setInoteger1(0L);
        }
        product = (Edmtickettypetab) tickettypeService.get(Edmtickettypetab.class,
                product.getItickettypeid());
        Esbscenicareatab provider = (Esbscenicareatab) tickettypeService.get(Esbscenicareatab.class,
                product.getIscenicid());
        product.setSzscenicname(provider.getSzscenicname()); // 服务商名称
        hotel.setItickettypeid(product.getItickettypeid());
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 增加酒店附加属性保存 Describe:
     *
     * @return return:String Date:2011-9-26
     * @auth:huangyuqi
     */
    @PostMapping("v1/productHotelSave")
    @ApiOperation("酒店添加")
    public ResponseBean productHotelSave(@RequestBody Hotelproduct hotel) {
        ResponseBean  responseBean=new ResponseBean();
        Esbscenicareatab provider = null;
        if (hotel.getBedtype() == null || "".equals(hotel.getBedtype())) {
            responseBean.setCode(400);
            responseBean.setMsg("请选择床型");
            responseBean.setData(null);
            return responseBean;
        }
        if (hotel.getBreakfasttype() == null || "".equals(hotel.getBreakfasttype())) {// 早餐形式
            responseBean.setCode(400);
            responseBean.setMsg("请选择早餐类型");
            responseBean.setData(null);
            return responseBean;
        }
        if (hotel.getWidebandtype() == null || "".equals(hotel.getWidebandtype())) {// 宽带类型
            responseBean.setCode(400);
            responseBean.setMsg("请选择宽带类型");
            responseBean.setData(null);
            return responseBean;
        }
        if (hotel.getWeektype() == null || "".equals(hotel.getWeektype())) {// 周未类型
            responseBean.setCode(400);
            responseBean.setMsg("请选择周未价类型");
            responseBean.setData(null);
            return responseBean;
        }
        if (hotel.getPanoramaaddr() != null && !"".equals(hotel.getPanoramaaddr())) {// 全景图网址
            if (!"http".equals(hotel.getPanoramaaddr().substring(0, 4))) {
                provider.setSznetaddr("http://" + hotel.getPanoramaaddr());
            }
        }
        if (hotel.getDeposittype() == null || "".equals(hotel.getDeposittype())) {// 定金方式
            responseBean.setCode(400);
            responseBean.setMsg("请选择定金方式");
            responseBean.setData(null);
            return responseBean;
        } else if (hotel.getDeposittype().intValue() == 0) {
            if ("".equals(hotel.getDeposit())) {
                responseBean.setCode(400);
                responseBean.setMsg("定金数据不能为空");
                responseBean.setData(null);
                return responseBean;
            }
            if (hotel.getDeposit() == 0) {
                responseBean.setCode(400);
                responseBean.setMsg("定金金额不能为0");
                responseBean.setData(null);
                return responseBean;
            }
            if (!"".equals(hotel.getDeposit())) {
                // 判断输入格式
                Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
                boolean b = p.matcher(String.valueOf(hotel.getDeposit())).matches();
                if (b == false) {
                    responseBean.setCode(400);
                    responseBean.setMsg("格式不对");
                    responseBean.setData(null);
                    return responseBean;
                }
            }
        }
        if ("".equals(hotel.getNumter1())) {
            responseBean.setCode(400);
            responseBean.setMsg("加床价不能为空!");
            responseBean.setData(null);
            return responseBean;
        }
        if (!"".equals(hotel.getNumter1())) {
            // 判断输入格式
            Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
            boolean b = p.matcher(String.valueOf(hotel.getNumter1())).matches();
            if (b == false) {
                responseBean.setCode(400);
                responseBean.setMsg("加床价格式不正确");
                responseBean.setData(null);
                return responseBean;
            }
        }
        if (1 == hotel.getDeposittype() && !"".equals(hotel.getDeposit())) {// 定金按百分比
            if (hotel.getDeposit() > 100) {
                responseBean.setCode(400);
                responseBean.setMsg("定金百分比不正确，请输入小于100的数据");
                responseBean.setData(null);
                return responseBean;
            }
        }
        // 保存酒店其它属性
        tickettypeService.saveOrUpdate(hotel);

        responseBean.setCode(200);
        responseBean.setMsg("增加成功");
        responseBean.setData(WebContant.DOMAINAME);
        return responseBean;
    }

    /**
     * 租车产品属性添加
     * productRentrlAdd(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     *
     * @return String
     * @throws
     * @auth hejiahua
     * @date:2013-10-30 上午10:54:24
     * @since 1.0.0
     */
    /*public String productRentrlAdd() {
        return null;
    	if (product.getItickettypeid() == null
				|| "".equals(product.getItickettypeid())) {
			this.addActionError(getText("error.tickettype.ticketid.required"));// 产品编号不能为空
			return INPUT;
		}
		// 根据产品编号查找产品
		Object object = tickettypeService.getOthersProduct(product
				.getItickettypeid(), pdtp);
		if (object != null) {
			cartickettab = (Cartickettab) object;
		}
		if (cartickettab == null) {
			cartickettab = new Cartickettab();
			cartickettab.setItickettypeid(product.getItickettypeid());//设置产品id
		}

		product = (Edmtickettypetab) tickettypeService.get(Edmtickettypetab.class,
				product.getItickettypeid());
		provider = (Esbscenicareatab) tickettypeService.get(Esbscenicareatab.class,
				product.getIscenicid());
		product.setSzscenicname(provider.getSzscenicname()); // 服务商名称

		return SUCCESS;
    }*/

    /**
     * 租车产品属性保存
     * productRentrlAdd(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     *
     * @return String
     * @throws
     * @auth hejiahua
     * @date:2013-10-30 上午10:54:24
     * @since 1.0.0
     */
    /*public String productRentrlSave() {
		if (cartickettab.getLinetravelname()==null || cartickettab.getLinetravelname().trim().equals("")) {
			this.addActionError("请输入线路名称。");
			return INPUT;
		}
		if (cartickettab.getCarbrand()==null || cartickettab.getCarbrand().trim().equals("")) {
			this.addActionError("请输入线路品牌。");
			return INPUT;
		}
		if (cartickettab.getRentaltype()==null) {
			this.addActionError("请选择是否配司机。");
			return INPUT;
		}
		if (cartickettab.getSeating()==null) {
			this.addActionError("请输入座位数。");
			return INPUT;
		}
		if (cartickettab.getRegion()==null || cartickettab.getRegion().trim().equals("")) {
			this.addActionError("请输入路线区域。");
			return INPUT;
		}
		if (cartickettab.getDeposit()==null) {
			this.addActionError("请输入定金。");
			return INPUT;
		}
<<<<<<< .mine
		// 保存租车产品属性
=======
		if (rentrlcarvartab.getSzrentrlcarnum()<rentrl.getCarnum()) {
			this.addActionError("车辆总数量大于车库库存量，请重新输入。");
			return INPUT;
		}
		rentrl.setDtmakedate(Tools.getDayTimes());
		// 保存租车其它属性
>>>>>>> .r4916
<<<<<<< .mine
		try {
			cartickettab.setDtmakedate(Tools.getDayTimes());
			cartickettabService.saveOrUpdate(cartickettab);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
        return null;
    }*/


    /**
     * 增加旅行社附加属性 Describe:
     *
     * @return return:String Date:2011-9-26
     * @auth:huangyuqi
     */
    @PostMapping("/v1/productlinedd")
    @ApiOperation("增加旅行社附加属性")
    public ResponseBean productLvxingsheAdd(HttpServletRequest request,@RequestBody Edmtickettypetab product,String pdtp) {
        ResponseBean  responseBean=new ResponseBean();
        if (product.getItickettypeid() == null
                || "".equals(product.getItickettypeid())) {
            responseBean.setCode(400);
            responseBean.setMsg("产品编号不能为空");
            responseBean.setData(null);
            return responseBean;

        }

        // 得到省列表
        List regionList = sysparService.getAllSourceRegion();
        request.setAttribute("regionList", regionList);
        // 出发方式
        List cffslist = sysparService.retrieve("CFFS");
        request.setAttribute("cffslist", cffslist);

        // 根据产品编号查找产品及产品附属属性
        Lineproduct line = (Lineproduct) tickettypeService.getOthersProduct(product
                .getItickettypeid(), pdtp);
        if (line == null) {
            line = new Lineproduct();
            line.setStartingmethod("01");// 出发方式
            line.setDeposittype(0l);// 定金方式
        }
        product = (Edmtickettypetab) tickettypeService.get(Edmtickettypetab.class,
                product.getItickettypeid());
        Esbscenicareatab provider = (Esbscenicareatab) tickettypeService.get(Esbscenicareatab.class,
                product.getIscenicid());
        product.setSzscenicname(provider.getSzscenicname()); // 服务商名称

        line.setItickettypeid(product.getItickettypeid());

        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 增加旅行社附加属性保存 Describe:
     *
     * @return return:String Date:2011-9-26
     * @auth:huangyuqi
     */
    @PostMapping("/v1/productlinesave")
    @ApiOperation("增加旅行社附加属性")
    public ResponseBean productLvxingsheSave(@RequestBody Lineproduct line) {
        ResponseBean responseBean=new ResponseBean();
        if (line.getDatanumber() == null || "".equals(line.getDatanumber())) {// 天数
            responseBean.setCode(400);
            responseBean.setMsg("天数不能为空");
            responseBean.setData(null);
            return responseBean;
        }
        if ("02".equals(line.getStartingmethod())
                || "03".equals(line.getStartingmethod())) {
            if (line.getStartingdata() == null || "".equals(line.getStartingdata())) {
                responseBean.setCode(400);
                responseBean.setMsg("出发日期不能为空");
                responseBean.setData(null);
                return responseBean;
            } else {
                if ("02".equals(line.getStartingmethod())) {// 定期出发
                    String mdays[] = line.getStartingdata().split(",");
                    String mday = "";
                    int md = 0;
                    for (int i = 0; i < mdays.length; i++) {
                        if (mdays[i].trim().length() != 5) {
                            responseBean.setCode(400);
                            responseBean.setMsg("定期出发日期格式不正确");
                            responseBean.setData(null);
                            return responseBean;
                        } else {
                            mday = mdays[i].trim().substring(0, 2);
                            try {
                                md = Integer.parseInt(mday);
                                if (md > 12 || md <= 0) {
                                    responseBean.setCode(400);
                                    responseBean.setMsg("定期出发日期格式不正确");
                                    responseBean.setData(null);
                                    return responseBean;
                                }
                            } catch (Exception e) {
                                responseBean.setCode(400);
                                responseBean.setMsg("定期出发日期格式不正确");
                                responseBean.setData(null);
                                return responseBean;
                            }
                            mday = mdays[i].trim().substring(2, 3);
                            if (!mday.equals("-")) {
                                responseBean.setCode(400);
                                responseBean.setMsg("定期出发日期格式不正确");
                                responseBean.setData(null);
                                return responseBean;
                            }
                            mday = mdays[i].trim().substring(3, 5);
                            try {
                                md = Integer.parseInt(mday);
                                if (md > 31 || md <= 0) {
                                    responseBean.setCode(400);
                                    responseBean.setMsg("定期出发日期格式不正确");
                                    responseBean.setData(null);
                                    return responseBean;
                                }
                            } catch (Exception e) {
                                responseBean.setCode(400);
                                responseBean.setMsg(" 定期出发日期格式不正确");
                                responseBean.setData(null);
                                return responseBean;
                            }
                        }
                    }
                } else if ("03".equals(line.getStartingmethod())) {// 每周定期出发
                    String weeks[] = line.getStartingdata().split(",");
                    for (int i = 0; i < weeks.length; i++) {
                        int week = 0;
                        try {
                            week = Integer.parseInt(weeks[i].trim());
                        } catch (Exception e) {
                            responseBean.setCode(400);
                            responseBean.setMsg(" 每周定期出发日期格式不正确");
                            responseBean.setData(null);
                            return responseBean;
                        }
                        if (week > 7 || week < 1) {
                            responseBean.setCode(400);
                            responseBean.setMsg(" 每周定期出发日期格式不正确");
                            responseBean.setData(null);
                            return responseBean;
                        }
                    }
                }
            }
        }

        if (line.getDeposittype() == null || "".equals(line.getDeposittype())) {// 定金方式
            responseBean.setCode(400);
            responseBean.setMsg(" 请选择定金方式");
            responseBean.setData(null);
            return responseBean;
        }
        if ("".equals(line.getDeposit())) {
            responseBean.setCode(400);
            responseBean.setMsg(" 定金数据不能为空");
            responseBean.setData(null);
            return responseBean;
        }
        if (!"".equals(line.getDeposit())) {
            // 判断输入格式
            Pattern p = Pattern.compile("^[0-9]+(.[0-9]{0,2})?$");
            boolean b = p.matcher(String.valueOf(line.getDeposit())).matches();
            if (b == false) {
                responseBean.setCode(400);
                responseBean.setMsg(" 格式不对");
                responseBean.setData(null);
                return responseBean;
            }
        }
        if (1 == line.getDeposittype() && !"".equals(line.getDeposit())) {// 定金按百分比
            if (line.getDeposit() > 100) {
                responseBean.setCode(400);
                responseBean.setMsg(" 定金百分比不正确，请输入小于100的数据");
                responseBean.setData(null);
                return responseBean;
            }
        }
        // 保存旅行社其它属性
        tickettypeService.saveOrUpdate(line);
        responseBean.setCode(200);
        responseBean.setMsg(" 增加成功");
        responseBean.setData(WebContant.DOMAINAME);
        return responseBean;

    }

    /**
     * 线路行程
     * Describe:
     *
     * @return return:String
     * Date:2011-12-13
     * @auth:huangyuqi
     */
    @GetMapping("/v1/linedetail")
    @ApiOperation("线路行程")
    public ResponseBean editLineDetail(@RequestBody Edmtickettypetab product) {
        ResponseBean responseBean=new ResponseBean();
        if (product.getItickettypeid() == null
                || "".equals(product.getItickettypeid())) {
            responseBean.setCode(400);
            responseBean.setMsg("  产品编号不能为空");
            responseBean.setData(null);
            return responseBean;
        }

        //根据产品编号查找产品信息
        product = (Edmtickettypetab) this.providerService.get(Edmtickettypetab.class, product.getItickettypeid());

        //根据产品编号查询线路产品属性
        Lineproduct line = (Lineproduct) this.providerService.get(Lineproduct.class, product.getItickettypeid());
        if (line == null) {
            responseBean.setCode(400);
            responseBean.setMsg("该产品没有线路属性数据，请增加线路属性");
            responseBean.setData(null);
            return responseBean;
        }
        if (product != null) {
            product.setLineproduct(line);
        }

        responseBean.setCode(200);
        responseBean.setMsg("线路行程");
        responseBean.setData(null);
        return responseBean;
    }
    @GetMapping("/v1/productticketAdd")
    @ApiOperation("线路行程")
    public ResponseBean productticketAdd(@RequestBody Edmtickettypetab product,String pdtp) {
        ResponseBean responseBean=new ResponseBean();
        if ((product.getItickettypeid() == null) || ("".equals(product.getItickettypeid()))) {
            responseBean.setCode(400);
            responseBean.setMsg("票类型不能为空");
            responseBean.setData(null);
            return responseBean;
        }

        Edmticketproduct edmticket = (Edmticketproduct) this.tickettypeService.getOthersProduct(product.getItickettypeid(), pdtp);
        if (edmticket == null) {
            edmticket = new Edmticketproduct();
            edmticket.setIszhiwen(0L);
            edmticket.setNoted4("0");
            edmticket.setNoted5("0");
            edmticket.setInoteger1(0L);
            edmticket.setInoteger2(0L);
            edmticket.setInoteger4(0L);
            edmticket.setInoteger5(0L);
        }
        product = ((Edmtickettypetab) this.tickettypeService.get(Edmtickettypetab.class, product.getItickettypeid()));
        Esbscenicareatab provider = ((Esbscenicareatab) this.tickettypeService.get(Esbscenicareatab.class, product.getIscenicid()));
        product.setSzscenicname(provider.getSzscenicname());
        edmticket.setItickettypeid(product.getItickettypeid());
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(edmticket);
        return responseBean;
    }

    @GetMapping("/v1/productticketSave")
    @ApiOperation("产品票添加")
    public ResponseBean productticketSave(@RequestBody Edmticketproduct edmticket,@RequestBody Edmtickettypetab product) {
        ResponseBean responseBean=new ResponseBean();
        if (edmticket.getIszhiwen() == null) {
        	edmticket.setIszhiwen(0L);
        }
        if (!product.getBymediatype().equals("00") && !product.getBymediatype().equals("01")) {
            if (edmticket.getNoted4() == null || edmticket.getNoted4().equals("")) {
                edmticket.setNoted4("0");
            } else {
                System.out.println(edmticket.getNoted4());
                Pattern p = Pattern.compile("^[0A-Z]$");
                boolean b = p.matcher(edmticket.getNoted4()).matches();

                if (b == false) {
                    responseBean.setCode(400);
                    responseBean.setMsg("IC卡类型格式输入错误！");
                    responseBean.setData(null);
                    return responseBean;
                }
            }
        }
        if (product.getBycategorytype() != null && product.getBycategorytype().equals("0010")) {
            if (edmticket.getInoteger1() == null) {
                edmticket.setInoteger1(0L);
            }
        }
        this.tickettypeService.saveOrUpdate(edmticket);

        responseBean.setCode(200);
        responseBean.setMsg("票务属性增加成功" + WebContant.DOMAINAME);
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 租车路线加载
     * Itickettype(这里用一句话描述这个方法的作用)
     * (这里描述这个方法适用条件 – 可选)
     *
     * @throws
     * @auth hejiahua
     * void
     * @date:2013-12-18 上午11:50:24
     * @since 1.0.0
     */
    //@GetMapping("/v1/itickettype/{iscenicid}")
    //@ApiOperation("租车路线加载")
    /*public void Itickettype(HttpServletRequest request,@PathVariable Long iscenicid) {
        List list = carlinedetailtabService.findItickettype(iscenicid);
        request.setAttribute("itickettype", list);

    }*/

    /**
     * 查看票号信息列表
     * @return
     */
    @GetMapping("/v1/queryPrintNo")
    @ApiOperation("查看票号信息列表")
    public ResponseBean queryPrintNo(HttpServletRequest request,
                                     Long itickettypeid,
                                     String printno,
                                     String serialnumber,
                                     int page,int pageSize){
        ResponseBean responseBean=new ResponseBean();
        Esfemployeetab esfemployeetab = null;
       
        PaginationSupport   ps = this.tickettypeService.queryPrintno(itickettypeid,printno,serialnumber,page,pageSize,null);
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(ps);
        return responseBean;
    }

    @GetMapping("/v1/performAddPrintno")
    @ApiOperation("添加票号")
    public ResponseBean performAddPrintno(String itickettypeid){
        ResponseBean responseBean=new ResponseBean();
        TicketPrintNo printNo = new TicketPrintNo();
        printNo.setStatus(1L);
        Edmtickettypetab ticket = (Edmtickettypetab) this.providerService.get(Edmtickettypetab.class,itickettypeid);
        printNo.setSztickettypename(ticket.getSztickettypename());
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(printNo);
        return responseBean;
    }

    @GetMapping("/v1/performEditPrintno")
    @ApiOperation("编辑票号")
    public ResponseBean performEditPrintno(@RequestBody TicketPrintNo printNo){
        ResponseBean responseBean=new ResponseBean();
        printNo = (TicketPrintNo) this.providerService.get(TicketPrintNo.class,printNo.getSeq());
        Edmtickettypetab ticket = (Edmtickettypetab) this.providerService.get(Edmtickettypetab.class,printNo.getTickettypeid());
        printNo.setSztickettypename(ticket.getSztickettypename());
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(printNo);
        return responseBean;
    }

    @PostMapping("/v1/savePrintno")
    @ApiOperation("保存票号")
    public ResponseBean savePrintno(@RequestBody TicketPrintNo printNo,int strutsAction){
        ResponseBean responseBean=new ResponseBean();
        Syslog syslog = new Syslog();
        if(strutsAction == ADD){
            //printNo.setTickettypeid(itickettypeid);
            try{
                this.tickettypeService.addPrintNo(printNo,syslog);
            }catch (Exception e){
                e.printStackTrace();
                responseBean.setCode(400);
                responseBean.setMsg(e.getMessage());
                responseBean.setData(null);
                return responseBean;
            }
        }
        if(strutsAction == EDIT){
            try{
                this.tickettypeService.editPrintNo(printNo,syslog);
            }catch (Exception e){
                e.printStackTrace();
                responseBean.setCode(400);
                responseBean.setMsg(e.getMessage());
                responseBean.setData(null);
                return responseBean;
            }
        }
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(printNo);
        return responseBean;
    }

    @GetMapping("/v1/validateSavePrintno")
    @ApiOperation("验证保存票号")
    public ResponseBean validateSavePrintno(@RequestBody TicketPrintNo printNo,int strutsAction){
        ResponseBean responseBean=new ResponseBean();
        if(strutsAction == ADD || strutsAction == EDIT){
            if(printNo.getSerialFirstIndex() == null){
                responseBean.setCode(400);
                responseBean.setMsg("流水号起始位置不能空");
                responseBean.setData(null);
                return responseBean;
            }
            if(printNo.getSerialLastIndex() == null){
                responseBean.setCode(400);
                responseBean.setMsg("流水号截止位置不能为空");
                responseBean.setData(null);
                return responseBean;
            }
            if(strutsAction == ADD){
                if(StringUtils.isBlank(printNo.getTicketPrintno())){
                    responseBean.setCode(400);
                    responseBean.setMsg("票号不能为空");
                    responseBean.setData(null);
                    return responseBean;
                }
                //判断票号不能重复
                boolean b = providerService.volidateSole(null,null,new String[]{"ticketPrintno"},
                        new String[]{printNo.getTicketPrintno()},"TicketPrintNo");
                if(!b){
                    responseBean.setCode(400);
                    responseBean.setMsg("重复票号");
                    responseBean.setData(null);
                    return responseBean;
                }
            }
        }
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(null);
        return responseBean;
    }

    @PostMapping("/v1/performImportPrintno")
    @ApiOperation("导入票号")
    public ResponseBean performImportPrintno(Long itickettypeid){
        ResponseBean responseBean=new ResponseBean();
        TicketPrintNo printNo = new TicketPrintNo();
        printNo.setStatus(1L);
        Edmtickettypetab ticket = (Edmtickettypetab) this.providerService.get(Edmtickettypetab.class,itickettypeid);
        printNo.setSztickettypename(ticket.getSztickettypename());
        responseBean.setCode(200);
        responseBean.setMsg("成功");
        responseBean.setData(printNo);
        return responseBean;
    }
    
    @GetMapping(value = "v1/ticketTypeListByIds")
	@ApiOperation(value = "根据票种id集合获取票种信息(供其他服务调用)")
	public List getTicketTypeListByIds(@ApiParam("itickettypeids数据格式[1,2,3,4]") @RequestParam("itickettypeids") String itickettypeids) {
		
		return tickettypeService.getTicketTypeListByIds(itickettypeids);
	}

    /*@GetMapping("/v1/batchInsertPrintNo")
    @ApiOperation("batchInsertPrintNo")
    public ResponseBean batchInsertPrintNo(Long itickettypeid) throws Exception{
        ResponseBean responseBean=new ResponseBean();
        if(itickettypeid == null){
            responseBean.setCode(400);
            responseBean.setMsg("产品ID不能为空");
            responseBean.setData(null);
            return responseBean;
        }
        if(file == null){
            responseBean.setCode(400);
            responseBean.setMsg("请上传文本");
            responseBean.setData(null);
            return responseBean;
        }
        if(printNo.getSerialFirstIndex() == null){
            responseBean.setCode(400);
            responseBean.setMsg("流水号起始位置不能空");
            responseBean.setData(null);
            return responseBean;
        }
        if(printNo.getSerialLastIndex() == null){
            responseBean.setCode(400);
            responseBean.setMsg("流水号截止位置不能为空");
            responseBean.setData(null);
            return responseBean;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
        String line = "";
        Syslog syslog = new Syslog();
        int i = 0;
        while ((line = in.readLine())!=null) {
            if(!StringUtils.isBlank(line)){
                try{
                    printNo.setTickettypeid(itickettypeid);
                    printNo.setTicketPrintno(line.trim());
                    printNo.setStatus(1L);
                    tickettypeService.addPrintNo(printNo, syslog);
                    i++;
                }catch (Exception e){
                    System.out.println("错误票号:"+line);
                    e.printStackTrace();
                }
            }
        }
        in.close();
        responseBean.setCode(400);
        responseBean.setMsg("添加成功票数量:"+i);
        responseBean.setData(null);
        return responseBean;
    }*/

}
