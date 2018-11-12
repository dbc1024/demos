package com.ectrip.ec.custom.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.custom.dao.idao.ICustomDAO;
import com.ectrip.ec.custom.service.iservice.ICustomService;
import com.ectrip.ec.feign.service.SysparService;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Vcitable;
import com.ectrip.ec.userxfjf.service.iservice.IUserjfService;
import com.ectrip.sys.model.syspar.Customlog;
/**
*
* @ClassName: CustomService
* @Description: 我的账户- 用户信息  业务处理类
* @author Dicky
* @date 2011-10-17 下午02:53:07
* 李进于2012-08-28 日下午修改
* 增加了注册增加积分。修改了这个方法 insertCustom
*/
@Service
public class CustomService extends GenericService implements ICustomService{

    private ICustomDAO customsDao;
    @Autowired
    public void setCustomsDao(ICustomDAO customsDao) {
		this.customsDao = customsDao;
		super.setGenericDao(customsDao);
	}
    @Autowired
	private IUserjfService userjfService;

	@Autowired
    private SysparService sysparService;
//    private ISysparDao sysparDao;
    
//    private ISyslogDao syslogDao;

//    private IUserjfDao  iUserjfDao;  // 李进修改，2012 -08 -28

    /**
     * (非 Javadoc)
     * <p>Title: getCustom</p>
     * <p>Description: 根据id获取单个用户信息</p>
     * @param usid
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#getCustom(java.lang.String)
     */
    public Custom getCustom(String usid) {
        return customsDao.getCustom(usid);
    }
    /**
     * (非 Javadoc)
     * <p>Title: updateCustom</p>
     * <p>Description:更新用户信息 </p>
     * @param custom
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#updateCustom(com.ectrip.model.user.Custom)
     */
    public boolean updateCustom(Custom custom,Customlog log) {
    	sysparService.insertTomlog(log);
        boolean b = customsDao.updateCustom(custom);
        return b;
    }
    /**
     * (非 Javadoc) 
     * <p>Title: executeHQL</p>
     * <p>Description:执行sql </p>
     * @param oldpassword
     * @param newpassword
     * @param usid
     * @return int
     * @see com.ectrip.custom.service.iservice.ICustomService#executeHQL(java.lang.String, java.lang.String, java.lang.String)
     */
    public int executeHQL(String oldpassword,String newpassword,String usid,Customlog log){
    	sysparService.insertTomlog(log);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String times= sdf.format(d);
        String hql=" UPDATE Custom SET password='"+newpassword+"',lpdate='"+times+"'  where usid='"+usid+"'";
        if(oldpassword!=null){
            hql+=" and password='"+oldpassword+"'";
        }
        return customsDao.executeHQL(hql);
    }

    public int executeHQL2(String oldpassword,String newpassword,String usid,Customlog log){
    	sysparService.insertTomlog(log);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String times= sdf.format(d);
        String hql=" UPDATE Custom SET password='"+newpassword+"',lpdate='"+times+"'  where usid='"+usid+"'";

        return customsDao.executeHQL(hql);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getDaoYouViewList</p>
     * <p>Description:分页系统导游 </p>
     * @param hql
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#getDaoYouViewList(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getDaoYouViewList(String usid,String hql, int pageSize,
                                               int startIndex, String url) {
        return customsDao.getDaoYouViewList(usid,hql, pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getChildCustom</p>
     * <p>Description:获取当前用户的子用户 </p>
     * @param usid
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#getChildCustom(java.lang.String)
     */
    public List getChildCustom(String usid) {
        return customsDao.find("select new map(c.usid as usid,c.lname as lname) from Custom c where c.ustp='02' and c.susid='"+usid+"'");
    }
    /**
     * (非 Javadoc)
     * <p>Title: getDaoYouPageList</p>
     * <p>Description:分类之后的导游分页列表 </p>
     * @param usdj
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#getDaoYouPageList(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getDaoYouPageList(String usdj, int pageSize,
                                               int startIndex, String url) {
        return customsDao.getDaoYouPageList(usdj, pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getDaoYouViewList</p>
     * <p>Description:导游首页列表 分类 </p>
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#getDaoYouViewList()
     */
    public List getDaoYouViewList(String usdj) {
        return customsDao.getDaoYouViewList(usdj);
    }
    /**
     * (非 Javadoc)
     * <p>Title: getChildCustomViewList</p>
     * <p>Description:分页子用户 </p>
     * @param hql
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @see com.ectrip.custom.service.iservice.ICustomService#getChildCustomViewList(java.lang.String, int, int, java.lang.String)
     */
    public PaginationSupport getChildCustomViewList(String usid,String hql,int pageSize,int startIndex,String url){
        return customsDao.getChildCustomViewList(usid,hql, pageSize, startIndex, url);
    }
    /**
     * (非 Javadoc)
     * <p>Title: saveCustom</p>
     * <p>Description:保存新用户 </p>
     * @param custom
     * @return
     */
    public void saveCustom(Custom custom, Customlog log) {
    	sysparService.insertTomlog(log);
        customsDao.saveCustom(custom);
    }


    /**
     * 增加用户
     * Describe:
     * @auth:huangyuqi
     * @param custom
     * @param customlog前台用户操作日志
     * return:void
     * Date:2011-10-7
     */
    public void insertCustom(Custom custom,Customlog customlog){
        customsDao.save(custom);
        //李进修改，增加注册时赠送积分
//        sysparService.addUserJf(custom.getUsid(), "04", new Long(1));
        userjfService.addUserJf(custom.getUsid(), "04", new Long(1));
        sysparService.insertTomlog(customlog);
    }
    /**
     * 根据用户编号查询用户信息
     * Describe:
     * @auth:huangyuqi
     * @param usid用户编号
     * @return
     * return:Custom
     * Date:2011-10-8
     */
    public Custom retrieve(String usid){
        return customsDao.retrieve(usid);
    }

    /**
     * 根据主用户查询子用户
     * Describe:
     * @auth:huangyuqi
     * @param usid主用户编号
     * @param bs
     * @return当前主用户及所有子用户列表，如(a,b,c,d)
     * return:String
     * Date:2011-10-31
     */
    public String getUsids(String usid,StringBuffer bs){
        return customsDao.getUsids(usid, bs);
    }


    /**
     *
     * Describe:验证常用导游唯一
     * @auth:yangguang
     * @param usid
     * @param dyid
     * @param zjhm
     * @return
     * return:boolean
     * Date:2011-12-8
     */
    public boolean volidateDaoyou(String usid,String dyid,String zjhm){
        return customsDao.volidateDaoyou(usid, dyid, zjhm);
    }
    /**
     * 判断用户密码答案是否正确
     * Describe:
     * @auth:huangyuqi
     * @param usid
     * @param mmqt
     * @param mmda
     * @return
     * return:int
     * Date:2012-1-9
     */
    public int getCustomMmda(String usid,String mmqt,String mmda){
        return customsDao.getCustomMmda(usid, mmqt, mmda);
    }
    /*
     * 新增验证码标识(non-Javadoc)
     * @see com.ectrip.custom.service.iservice.ICustomService#addVciTable(com.ectrip.model.user.Vcitable)
     */
    public void addVciTable(Vcitable vcitable) {
        customsDao.addVciTable(vcitable);
    }
    /*
     * 查询激活用户标识(non-Javadoc)
     * @see com.ectrip.custom.service.iservice.ICustomService#findVciTable(java.lang.String)
     */
    public Vcitable findVciTable(String code) {
        return customsDao.findVciTable(code);
    }

    public boolean checkUser(int tip,String username){
        return customsDao.checkUser(tip, username);
    }
    /*
     * 检查用户是否激活(non-Javadoc)
     * @see com.ectrip.custom.service.iservice.ICustomService#checkStatus(java.lang.String)
     */
    public boolean checkStatus(String emailOrMobile) {
        return customsDao.checkStatus(emailOrMobile);
    }

    public String sendEmailTemp(String type, String[] content){
        return customsDao.sendEmailTemp(type, content);
    }


    /***
     * 生成用户根据用户类别随机
     * @param ustp  注册类型 01邮箱02手机03QQ04新浪微博05支付宝06匿名订单
     * @param value  根据ustp的值不同,value 可是邮箱  QQ 支付宝等
     * @return
     */
    @Transactional(propagation=Propagation.REQUIRED,readOnly=false)
    public Custom anonymityUserCreate(String createType,String value) {
        Custom custom=new Custom();
        
        custom.setLgtp("01");
        custom.setIbusinessid(new Long("01"));
        custom.setUstp("01");
        custom.setNote2("0000");
        custom.setNote3("0");// 邮箱 0未激活 1激活
        custom.setNote4("1");// 手机0未激活 1激活
        custom.setStatus("01");
        custom.setTtlb("00");
        custom.setAutofapiao(0);
        custom.setLdate(Tools.getDays());
        custom.setLpdate(Tools.getDays());
        custom.setIregionalid(1L);
        custom.setInote2(1);
        custom.setUsqx("01110000000000000000");
        if(createType.equals("02")){
            custom.setMobile(value);
        }else if(createType.equals("01")){
            custom.setEmail(value);
        }
        int i = (int) ((Math.random()*9+1)*100000);
        Long l = Long.parseLong(Tools.getSecNowString())/i;

        List zjnclist=sysparService.retrieve("ZJNC");
        Map map=(Map)zjnclist.get(0);
        custom.setNote6(map.get("pmva")+"_"+l);
        UUID uuid = UUID.randomUUID();
        String usid = uuid.toString().replace("-", "");
        custom.setUsid("P" + ShortText(usid)[0]);
        custom.setNote5(createType);
        Customlog customlog = new Customlog();
        customlog.setUsid(custom.getUsid());
        customlog.setStlg("0081");
        customlog.setBrief("用户注册: 用户名：" + custom.getUsid());
        String lb = custom.getLgtp().equals("01")?"散客":"团体";
        customlog.setNote("前台用户注册,增加用户: " + custom.getNote6() + "，注册类别："
                +lb);
        
        //废弃此写法，这种写法会导致生成的密码位数不定
//        int pwd =(int)((Math.random()*99999999));
//        while (pwd==0) {
//            pwd = (int)((Math.random()*99999999));
//        }
        
        double random = Math.random();
        while(random <= 0.1){
        	random = Math.random();
        }
        int pwd = (int)(random*99999999);
        
        custom.setPassword(Tools.md5Encode(pwd+""));
        this.insertCustom(custom, customlog);
        
//        sysparService.sendMessageNew(custom.getMobile(), "0012", new String[] {custom.getMobile(),""+pwd});
        sysparService.sendMessageNew(custom.getMobile(), "0012", custom.getMobile()+","+pwd);
        return custom;
    }
    /**
     *
     * Describe:
     * @author:chenxinhao
     * @param username	用户名
     * @param pwd	密码
     * @param type	类别 01 邮箱 02 手机
     * @return
     * return:Custom
     * Date:2013-12-3
     */
    public Custom createCustom(String username,String pwd,String type) {
        Custom custom=new Custom();
        custom.setPassword(Tools.md5Encode(pwd));
        custom.setLgtp("01");
        custom.setIbusinessid(new Long("01"));
        custom.setUstp("01");
        custom.setNote2("0000");
        custom.setStatus("01");
        custom.setTtlb("00");
        custom.setAutofapiao(0);
        custom.setLdate(Tools.getDays());
        custom.setLpdate(Tools.getDays());
        custom.setIregionalid(1L);
        custom.setInote2(1);
        custom.setUsqx("01110000000000000000");

        int i = (int) ((Math.random()*9+1)*100000);
        Long l = Long.parseLong(Tools.getSecNowString())/i;

        List zjnclist=sysparService.retrieve("ZJNC");
        Map map=(Map)zjnclist.get(0);
        custom.setNote6(map.get("pmva")+"_"+l);
        UUID uuid = UUID.randomUUID();
        String usid = uuid.toString().replace("-", "");
        if(type.equals("02")){
            custom.setNote3("0");// 邮箱 0未激活 1激活
            custom.setNote4("1");// 手机0未激活 1激活
            custom.setMobile(username);
            custom.setUsid("P" + ShortText(usid)[0]);
        }else if(type.equals("01")){
            custom.setNote3("0");// 邮箱 0未激活 1激活
            custom.setNote4("0");// 手机0未激活 1激活
            custom.setEmail(username);
            custom.setUsid("E" + ShortText(usid)[0]);
        }
        custom.setNote5(type);
        Customlog customlog = new Customlog();
        customlog.setUsid(custom.getUsid());
        customlog.setStlg("0081");
        customlog.setBrief("用户注册: 用户名：" + custom.getUsid());
        String lb = custom.getLgtp().equals("01")?"散客":"团体";
        customlog.setNote("前台用户注册,增加用户: " + custom.getNote6() + "，注册类别："
                +lb);
        try {
            this.insertCustom(custom, customlog);
        } catch (Exception e) {
            return null;
        }
        return custom;
    }

    private  String[] ShortText(String string){
        String[] chars = new String[]{          //要使用生成URL的字符
                "a","b","c","d","e","f","g","h",
                "i","j","k","l","m","n","o","p",
                "q","r","s","t","u","v","w","x",
                "y","z","0","1","2","3","4","5",
                "6","7","8","9","A","B","C","D",
                "E","F","G","H","I","J","K","L",
                "M","N","O","P","Q","R","S","T",
                "U","V","W","X","Y","Z"
        };

        String hex = Tools.md5Encode(string);
        int hexLen = hex.length();
        int subHexLen = hexLen / 8;
        String[] ShortStr = new String[4];

        for (int i = 0; i < subHexLen; i++) {
            String outChars = "";
            int j = i + 1;
            String subHex = hex.substring(i * 8, j * 8);
            long idx = Long.valueOf("3FFFFFFF", 16) & Long.valueOf(subHex, 16);

            for (int k = 0; k < 6; k++) {
                int index = (int) (Long.valueOf("0000003D", 16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            ShortStr[i] = outChars;
        }

        return ShortStr;
    }


    /*
     * 手机查询用户(non-Javadoc)
     * @see com.ectrip.custom.service.iservice.ICustomService#findByEmail(java.lang.String)
     */
    public Custom findByMobile(String mobile) {
        return customsDao.findByMobile(mobile);
    }

    public Map<String,String> checkMessageCode(Custom custom,String code) throws Exception{
        Map<String, String> datajson = new HashMap<String, String>();
        if(code!=null&&!code.equals("")){//未填验证码
            Vcitable vcitable = this.findVcitable("", "02", custom.getMobile(), code);
            if(vcitable!=null){//验证码错误
                if(!vcitable.getUsid().equals(custom.getUsid())){//验证码与用户是否匹配
                    datajson.put("status", "2");
                    datajson.put("msg", "验证码输入错误！");
                }else{
                    DateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    long newTime = dateFormat.parse(Tools.getDayTimes()).getTime();
                    long oldTime = dateFormat.parse(vcitable.getDtmakedate()).getTime();
                    long time = newTime-oldTime;
                    if(time>vcitable.getUsetime()){//验证码是否过期
                        datajson.put("status", "2");
                        datajson.put("msg", "验证码已失效，请重新发送！");
                    }else{
                        datajson.put("status", "1");
                        datajson.put("vciName", vcitable.getUsername());
                        this.delete(vcitable);
                    }
                }
            }else{
                datajson.put("status", "2");
                datajson.put("msg", "验证码输入错误！");
            }
        }else{
            datajson.put("status", "2");
            datajson.put("msg", "请输入验证码！");
        }
        return datajson;
    }
    
    public Map<String,String> checkMessageCode(Custom custom,String code,String type) throws Exception{
        Map<String, String> datajson = new HashMap<String, String>();
        if(code!=null&&!code.equals("")){//未填验证码
            Vcitable vcitable = this.findVcitable("", type, custom.getMobile(), code);
            if(vcitable!=null){//验证码错误
                if(!vcitable.getUsid().equals(custom.getUsid())){//验证码与用户是否匹配
                    datajson.put("status", "2");
                    datajson.put("msg", "验证码输入错误！");
                }else{
                    DateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    long newTime = dateFormat.parse(Tools.getDayTimes()).getTime();
                    long oldTime = dateFormat.parse(vcitable.getDtmakedate()).getTime();
                    long time = newTime-oldTime;
                    if(time>vcitable.getUsetime()){//验证码是否过期
                        datajson.put("status", "2");
                        datajson.put("msg", "验证码已失效，请重新发送！");
                    }else{
                        datajson.put("status", "1");
                        datajson.put("vciName", vcitable.getUsername());
                        this.delete(vcitable);
                    }
                }
            }else{
                datajson.put("status", "2");
                datajson.put("msg", "验证码输入错误！");
            }
        }else{
            datajson.put("status", "2");
            datajson.put("msg", "请输入验证码！");
        }
        return datajson;
    }

    public Custom findByUsername(String username,String type){
        return customsDao.findByUsername(username,type);
    }
    /**
     *
     * Describe:
     * @author:chenxinhao
     * @param usid	用户主键
     * @param type	验证码类别
     * @param username	用户名
     * @param code	验证码
     * @return
     * return:Vcitable
     * Date:2013-12-3
     */
    public Vcitable findVcitable(String usid,String type,String username,String code){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from Vcitable v where 1=1 ");
        if(usid!=null&&!usid.equals("")){
            hsql.append(" and v.usid='"+usid+"' ");
        }
        /*if(type!=null&&!type.equals("")){
            hsql.append(" and v.type='"+type+"' ");
        }*/
        if(username!=null&&!username.equals("")){
            hsql.append(" and v.username='"+username+"' ");
        }
        if(code!=null&&!code.equals("")){
            hsql.append(" and v.code='"+code+"' ");
        }
        hsql.append(" order by v.dtmakedate desc ");
        List list = this.find(hsql.toString());
        if(list!=null&&!list.isEmpty()){
            return (Vcitable) list.get(0);
        }else{
            return null;
        }
    }

    /**
     *
     * Describe:
     * @author:chenxinhao
     * @param usid	用户主键
     * @param type	验证码类别
     * @param username	用户名
     * @param code	验证码
     * @return
     * return:
     * Date:2013-12-3
     */
    public void deleteVcitable(String usid,String type,String username,String code){
        StringBuffer hsql = new StringBuffer();
        hsql.append(" from Vcitable v where 1=1 ");
        if(usid!=null&&!usid.equals("")){
            hsql.append(" and v.usid='"+usid+"' ");
        }
        if(type!=null&&!type.equals("")){
            hsql.append(" and v.type='"+type+"' ");
        }
        if(username!=null&&!username.equals("")){
            hsql.append(" and v.username='"+username+"' ");
        }
        if(code!=null&&!code.equals("")){
            hsql.append(" and v.code='"+code+"' ");
        }
        hsql.append(" order by v.dtmakedate desc ");
        List list = this.find(hsql.toString());
        if(list!=null&&!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                Vcitable vc = (Vcitable) list.get(i);
                this.delete(vc);
            }
        }
    }

    public Custom AddCustomByWx(String createType, String value, String openId) {
        Custom cust = new Custom();
        cust.setLgtp("01");
        cust.setIbusinessid(new Long("01"));
        cust.setUstp("01");
        cust.setNote2("0000");
        cust.setNote3("0");// 邮箱 0未激活 1激活
        cust.setNote4("0");// 手机0未激活 1激活
        cust.setStatus("01");
        cust.setTtlb("00");
        cust.setAutofapiao(0);
        cust.setLdate(Tools.getDays());
        cust.setLpdate(Tools.getDays());
        cust.setIregionalid(1L);
        cust.setInote2(1);
        cust.setUsqx("01110000000000000000");
        if (createType.equals("02")) {
            cust.setMobile(value);
        } else if (createType.equals("01")) {
            cust.setEmail(value);
        }
        int i = (int) ((Math.random() * 9 + 1) * 100000);
        Long l = Long.parseLong(Tools.getSecNowString()) / i;
        cust.setNote6("jw_" + l);
        cust.setUsid(openId);
        cust.setNote5(createType);// 08微信用户
        Customlog customlog = new Customlog();
        customlog.setUsid(cust.getUsid());
        customlog.setStlg("0081");
        customlog.setBrief("用户注册: 用户名：" + cust.getUsid());
        String lb = cust.getLgtp().equals("01") ? "散客" : "团体";
        customlog.setNote("前台用户注册,增加用户: " + cust.getNote6() + "，注册类别：" + lb);
        int pwd = (int) ((Math.random() * 99999999));
        while (pwd == 0) {
            pwd = (int) ((Math.random() * 99999999));
        }
        cust.setPassword(Tools.md5Encode(pwd + ""));
        System.out.println("用户：" + cust.getUsid() + " 密码：" + cust.getPassword()
                + " 类型：" + cust.getNote5());
        this.insertCustom(cust, customlog);
        return cust;
    }
    
    public Custom queryByOrderId(String orderId){
    	return customsDao.queryByOrderId(orderId);
    }

    public Custom getCustomBymobile(String mobile) {
        return customsDao.getCustomBymobile(mobile);

    }
	@Override
	public List getCustomsByUserIds(String userIds) {
		// TODO Auto-generated method stub
		return customsDao.getCustomsByUserIds(userIds);
	}
	/**
	 * 根据用户的动态信息查询用户信息
	 */
	@Override
	public List<?> queryCustomByCondition(Custom custom) throws Exception{
		List<?> customList = null;
		if(custom != null) {
			
			customList = customsDao.queryCustomByCondition(custom);
		}
		return customList;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void updateCustom(Custom custom) {
		customsDao.update(custom);
		
	}
}
