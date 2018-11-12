package com.ectrip.ticket.checkticket.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.ResultBean;
import com.ectrip.ticket.cyt.model.CYTDto;
import com.ectrip.ticket.cyt.model.CYTPojo;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.order.Stssoldtickettab;

/**
 * 检票用服务,所有检票的服务都在这里
 *
 * @author lijin
 *
 */
public interface ICheckService extends IGenericService{

    public ResultBean getMyID(String ip);

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param iaccessequipid
     * @return return:ResultBean Date:2011-12-8
     */

    public Esbaccessequiptab getEsbaccessequiptab(Long iaccessequipid);

    /**
     * 总体对外检票方法
     *
     * @param ticketstr
     *            检票用的票号
     * @param md5str
     *            准确性校验方法
     * @return 李进于 2012-07-28 进行了重大修改，增加了 第个参数
     */

    public ResultBean  changeCheckTicket(String accid, String printno, String checkType, String... OtherString) throws Exception;


    public ResultBean  changeCheckTicket(String accid) throws Exception;

    public ResultBean  changeCheckTicket(String accid, String printno) throws Exception;

    /**
     * 导游验证 Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     * @param carno
     * @return
     * @throws Exception
     *             return:ResultBean Date:2011-11-14
     */
    public ResultBean changeCheckDaoyou(String accid, String carno, String newUrl) throws Exception;

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     *            闸机编码
     * @param ticketno
     *            票号
     * @param md5str
     * @return
     * @throws Exception
     *             return:int Date:2011-11-14
     */
    public int changeticketPassIdintput(String accid, String ticketno, String md5str) throws Exception;

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param ticketno
     *            票编号
     * @param ziwenno
     *            指纹信息
     * @param szidcord
     *            身份证号
     * @param szimagepath
     *            头像存放地址
     * @param md5str
     * @return
     * @throws Exception
     *             return:int Date:2011-11-4
     */
    public int changeticketZwintput(String accid, String ticketno, String ziwenno, String szidcord, String szimagepath, String md5str);

    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     *            闸机编号
     * @param carno
     *            身份证号码
     * @param ziwenno
     *            指纹特征
     * @param md5str
     * @return
     * @throws Exception
     *             return:ResultBean Date:2011-11-15
     */
    public ResultBean changeDaoyouzwinput(String accid, String carno, String ziwenno) throws Exception;

    /**
     *
     * Describe: 更新导游通过记录
     *
     * @auth:yuanchengjun
     * @param accid
     *            闸机编号
     * @param carno
     *            身份证号码
     * @param md5str
     * @return
     * @throws Exception
     *             return:ResultBean Date:2011-11-15
     */
    public ResultBean changeDaoyoupass(String accid, String carno);
    /**
     *
     * Describe: 更新用工通过记录
     *
     * @auth:yuanchengjun
     * @param accid
     *            闸机编号
     * @param carno
     *            身份证号码
     * @param md5str
     * @return
     * @throws Exception
     *             return:ResultBean Date:2011-11-15
     */
    public ResultBean changeEmployeepass(String accid, String carno);
    /**
     * 验证员工卡 Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     * @param carno
     * @return return:ResultBean Date:2011-12-24
     */
    public ResultBean changeCheckEmployee(String accid, String carno, String newUrl);

    /**
     * 员工发行卡使用登记 Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     *            闸机编号
     * @param carno
     *            员工卡号
     * @param printno
     *            票号
     * @return return:ResultBean Date:2011-12-23
     */
    public ResultBean changeemployeeinput(String accid, String carno, String printno);

    public ResultBean getzwcs();

    public ResultBean getxpcs();

    public ResultBean getstation(Long iscenicid);

    public ResultBean getscenic(Long iscenicid);

    public ResultBean getzhiwen(String carno);

    public ResultBean getTicket(Long iscenicid);

    public ResultBean getFtp(String accid);

    public ResultBean findEsbaccessequiptab(Long iaccessequipid);

    public ResultBean getCheckcount(Long iaccessequipid, String url);

    public ResultBean changeemployeechangeraft(String accid, String carno, String printno, String url);

    // 李进修改，检票方法 ，增加 OtherString 参数，可以传更多的参数
    public ResultBean changescCheckTicket(String accid, String szticketprintno, int icjp, String url, String... OtherString) throws Exception;

    /**
     *
     * Describe:线下检票 保存信息
     *
     * @author:chenxinhao
     * @param gateid
     *            园门ID
     * @param printno
     *            票号
     * @return return:ResultBean Date:2012-9-7
     */
    public ResultBean LoclOffLineCheckTicket(String accid, String printno, String checktime, Long srid, Long igrardid);

    /**
     * *
     * Describe:总体对外检票方法 ticketstr="园门ID(闸机)?,票号,检票时间，服务器编号" 根据票号返回检票规则
     * @see ICheckService#OffLineCheckTicketTrans(String, String)
     * @param ticketstr
     * @param md5str 准确性校验方法
     * @return
     * @throws Exception
     * @author yangguang
     * Date:2012-10-8
     */
    public ResultBean OffLineCheckTicketTrans(String ticketstr, String md5str, String url) throws Exception;


    /**
     * 总体对外检票方法
     *
     * @param ticketstr
     *            检票用的票号
     * @param md5str
     *            准确性校验方法
     * @return 李进于 2012-07-28 进行了重大修改，增加了 第个参数
     */

    public ResultBean readCheckTicket(String accid, String printno, String urls, String... OtherString) throws Exception;
    /**
     * 验证员工卡 Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     * @param carno
     * @return return:ResultBean Date:2011-12-24
     */
    public ResultBean changeCheckfanxin(String accid, String carno);
    /**
     *
     * Describe:
     *
     * @auth:yuanchengjun
     * @param accid
     *            闸机编号
     * @param carno
     *            身份证号码
     * @param ziwenno
     *            指纹特征
     * @param md5str
     * @return
     * @throws Exception
     *             return:ResultBean Date:2011-11-15
     */
    public ResultBean changeEmployeezwinput(String accid, String carno, String ziwenno) throws Exception;
    /**
     * 闸机状态上传
     * Describe:
     * @auth:yuanchengjun
     * @param accid
     * @param typestatus
     * @param byisuse
     * return:void
     * Date:2013-2-21
     */
    public void updatestatus(Long accid, String typestatus, Long byisuse);

    /**
     * 判断该票是否已用完
     * @param printno
     * @return
     */

    public boolean isyw(String printno);


    public List getprintno(Long accid);
    /**
     * 关联检票
     * @param accid
     * @return
     */
    public void savechecklink(String accid, String printno);
    /**
     * 根据票号判断闸机是否能检该票
     * 100  非一单一检
     * 101 一单一检
     * 其他是不能检票返回的数据
     */
    public ResultBean querychecktype(String accid, String printno) throws Exception;

    public ResultBean querychecktype(String accid, String printno, String checkType) throws Exception;

    public List queryprintnolist(String accid, String printno) throws Exception;
    public int stopCheckTicket(Long accid, String szprintno, Long synumb);
    public ResultBean checktorder(String accid, String printno, String url) throws Exception;
    public ResultBean changeCheckTicket(String accid, String printno1, String printno2) throws Exception;
    ////////////////常用通专用///////////////////////
    public CYTDto findCYTDto(String orderid, String url);
    public CYTDto findCYTDto(String orderid, Long iscenicid, String urls);
    public void addCYTPojo(CYTPojo pojo);
    public void updatePojo(CYTPojo pojo);
    public List<CYTPojo> findNotCallbackCYTPojo();
    public Stssoldtickettab getmyzj(String accid, String szticketprintno);
    public void addCheckList(String gardenId, String accid, Long numb);
    public void updateCheckList(String gardenId, String accid, Long numb);
    public int getCheckOutNum(String gardenId, String accid);
    public int getEquipCheckNum(String gardenId, String accid);
    public String findOrderByCard(String accid, String card);
}
