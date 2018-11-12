package com.ectrip.ticket.sale.service.commonSale.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.sys.model.authcode.CodeType;
import com.ectrip.sys.model.baidu.bean.BaiduTicket;
import com.ectrip.sys.model.baidu.pojo.HttpStatusType;
import com.ectrip.sys.model.baidu.pojo.Ticket;
import com.ectrip.sys.model.baidu.pojo.Visitor;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.cyt.common.util.ConstUtils;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.applyorder.Ordertickettourist;
import com.ectrip.ticket.model.order.Stssalesvouchertab;
import com.ectrip.ticket.model.order.StssalesvouchertabId;
import com.ectrip.ticket.model.order.Stssoldticketattesttab;
import com.ectrip.ticket.model.order.StssoldticketattesttabId;
import com.ectrip.ticket.model.order.Stssoldtickettab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.sale.service.card.core.DesUtil;
import com.ectrip.ticket.sale.service.card.core.Response;
import com.ectrip.ticket.sale.service.commonSale.dao.idao.ICommonSaleDao;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.CheckListPojo;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.Ticketpojo;
import com.ectrip.ticket.sale.service.commonSale.model.pojo.Tourist;
import com.ectrip.ticket.sale.service.commonSale.model.request.CheckFingerPrintRequest;
import com.ectrip.ticket.sale.service.commonSale.model.request.FindCheckListRequest;
import com.ectrip.ticket.sale.service.commonSale.model.request.FindTouristRequest;
import com.ectrip.ticket.sale.service.commonSale.model.request.SendPhotoRequest;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckFingerPrintResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckPhotoResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.CheckRegisterPhotoResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.FindCheckListResponse;
import com.ectrip.ticket.sale.service.commonSale.model.response.FindTouristResponse;
import com.ectrip.ticket.sale.service.commonSale.service.iservice.ICommonSaleService;
import com.ectrip.ticket.util.ValivateIdcard;
/**
 * Created by chenxinhao on 2017/3/13.
 */
public class CommonSaleService extends GenericService implements ICommonSaleService {

    private ICommonSaleDao commonSaleDao;
    @Autowired
    private SysService sysService;

    public ICommonSaleDao getCommonSaleDao() {
        return commonSaleDao;
    }

    public void setCommonSaleDao(ICommonSaleDao commonSaleDao) {
        this.commonSaleDao = commonSaleDao;
    }

    public FindTouristResponse findTourists(FindTouristRequest request) {
        FindTouristResponse response = new FindTouristResponse();
        List<Tourist> tourists = new ArrayList<Tourist>();
        List<Ordertickettourist> orderTourists = commonSaleDao.findTourists(request.getOrid(), request.getProviderId(), request.getProductId(),
                request.getGroupId(), request.getCredentials());
        Esbscenicareatab provider = null;
        Edmtickettypetab product = null;
        Edpcrowdkindtab group = null;
        if (!StringUtils.isBlank(request.getProviderId())) {
            provider = (Esbscenicareatab) commonSaleDao.get(Esbscenicareatab.class, Long.parseLong(request.getProviderId()));
        }
        if (!StringUtils.isBlank(request.getProductId())) {
            product = (Edmtickettypetab) commonSaleDao.get(Edmtickettypetab.class, Long.parseLong(request.getProductId()));
        }
        if (!StringUtils.isBlank(request.getGroupId())) {
            group = (Edpcrowdkindtab) commonSaleDao.get(Edpcrowdkindtab.class, Long.parseLong(request.getGroupId()));
        }
        if (orderTourists != null && !orderTourists.isEmpty()) {
            for (Ordertickettourist orderTourist : orderTourists) {
                Tourist tourist = new Tourist();
                tourist.setOrid(orderTourist.getAorid());
                tourist.setName(orderTourist.getLname());
                tourist.setMobile(orderTourist.getMobile());
                tourist.setCredentials(orderTourist.getZjhm());
                if (provider == null) {
                    provider = (Esbscenicareatab) commonSaleDao.get(Esbscenicareatab.class, orderTourist.getIscenicid());
                }
                if (product == null) {
                    product = (Edmtickettypetab) commonSaleDao.get(Edmtickettypetab.class, orderTourist.getItickettypeid());
                }
                if (group == null) {
                    group = (Edpcrowdkindtab) commonSaleDao.get(Edpcrowdkindtab.class, orderTourist.getIcrowdkindid());
                }
                tourist.setProviderId(provider.getIscenicid().toString());
                tourist.setProviderName(provider.getSzscenicname());
                tourist.setProductId(product.getItickettypeid().toString());
                tourist.setProductName(product.getSztickettypename());
                tourist.setGroupId(group.getIcrowdkindid().toString());
                tourist.setGroupName(group.getSzcrowdkindname());
                tourists.add(tourist);
            }
        }
        response.setCode("0000");
        response.setTourists(tourists);
        response.setDescribe("查询游客信息成功");
        return response;
    }

    public Response findEmpPhoto(SendPhotoRequest request) {
        Response res = new Response();
        if (StringUtils.isBlank(request.getTicketNo())) {
            res.setCode("2001");
            res.setDescribe("员工卡证不能为空");
            return res;
        }
        AuthCode authCode = commonSaleDao.getAuthCode(CodeType.SYSTEM.getCode(), null);
        String visitorId = "";
        Opcemployeecardtab op = commonSaleDao.findEmp(request.getTicketNo());
        if (op != null) {
            visitorId = authCode.getAuthCode() + "_" + op.getIcardno();
        }
        com.ectrip.sys.model.baidu.response.Response response = sysService.checkVisitorPhoto(visitorId);
        if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
            if ("1000".equals(response.getCode())) {
                res.setCode("0000");
                res.setDescribe("成功");
            } else {
                res.setCode("9999");
                res.setDescribe("员工未注册头像信息");
            }
        } else {
            throw new RuntimeException(response.getMessage());
        }
        return res;
    }

    public Response findPhoto(SendPhotoRequest request) {
        Response res = new Response();
        if (StringUtils.isBlank(request.getTicketNo())) {
            res.setCode("2001");
            res.setDescribe("票号不能为空");
            return res;
        }
        AuthCode authCode = commonSaleDao.getAuthCode(CodeType.SYSTEM.getCode(), null);
        String visitorId = "";
        if ("01".equalsIgnoreCase(request.getType())) {
            visitorId = request.getTicketNo();
        } else {
            Stssoldtickettab stsso = commonSaleDao.findStsso(request.getTicketNo());
            if (stsso != null) {
                if (!StringUtils.isBlank(stsso.getMyzj()) && ValivateIdcard.valiIdcard(stsso.getMyzj())) {
                    visitorId = stsso.getMyzj();
                } else {
                    visitorId = authCode.getAuthCode() + "_" + request.getTicketNo();
                }
            } else {
                visitorId = authCode.getAuthCode() + "_" + request.getTicketNo();
            }
        }
        com.ectrip.sys.model.baidu.response.Response response = sysService.checkVisitorPhoto(visitorId);
        if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
            if ("1000".equals(response.getCode())) {
                res.setCode("0000");
                res.setDescribe("成功");
            } else {
                res.setCode("9999");
                res.setDescribe("未注册头像信息");
            }
        } else {
            throw new RuntimeException(response.getMessage());
        }
        return res;
    }

    public Response sendEmpPhoto(SendPhotoRequest request) {
        Response res = new Response();
        if (StringUtils.isBlank(request.getTicketNo())) {
            res.setCode("2001");
            res.setDescribe("证件号码不能为空");
            return res;
        }
        try {
            Opcemployeecardtab op = commonSaleDao.findEmp(request.getTicketNo());
            if (op != null) {
                List<String> groupIds = commonSaleDao.findEmpGardenids();
                AuthCode authCode = commonSaleDao.getAuthCode(CodeType.SYSTEM.getCode(), null);
                if (groupIds == null || groupIds.isEmpty()) {
                    res.setCode("2001");
                    res.setDescribe("无设备组列表数据");
                } else {
                    Visitor visitor = new Visitor();
                    if (op.getByisdaoyou() == 1) {
                        if (!StringUtils.isBlank(op.getUsid())) {
                            Custom custom = (Custom) commonSaleDao.get(Custom.class, op.getUsid());
                            if (custom != null) {
                                visitor.setVisitorName("导游(" + custom.getLname() + ")");
                            } else {
                                res.setCode("2001");
                                res.setDescribe("导游已注销");
                            }
                        }
                    } else {
                        if (op.getIemployeeid() != 0) {
                            Esfemployeetab emp = (Esfemployeetab) commonSaleDao.get(Esfemployeetab.class, op.getIemployeeid());
                            if (emp != null) {
                                visitor.setVisitorName("员工(" + emp.getSzemployeename() + ")");
                            } else {
                                res.setCode("2001");
                                res.setDescribe("员工已注销");
                            }
                        }
                    }
                    visitor.setPhoto(request.getPhotoImg());
                    visitor.setVisitorId(authCode.getAuthCode() + "_" + request.getTicketNo());
                    visitor.setCardType("other");
                    visitor.setCardNumber("1");
                    if (!"2001".equalsIgnoreCase(res.getCode())) {
                        Ticket ticket = new Ticket();
                        ticket.setTicketId(request.getTicketNo());
                        ticket.setGroupIds(groupIds);
                        ticket.setStartTime(Tools.getDays() + " 00:00:00");
                        ticket.setEndTime((Integer.parseInt(Tools.getYear()) + 10) + ticket.getStartTime().substring(4));

                        //保存门票人脸数据
                        BaiduTicket baiduTicket = new BaiduTicket();
                        baiduTicket.setTicketNo(request.getTicketNo());
                        baiduTicket.setStatus("0");
                        baiduTicket.setDtmakedate(Tools.getDayTimes());
                        baiduTicket.setVisitorId(visitor.getVisitorId());
                        commonSaleDao.save(baiduTicket);

                        com.ectrip.sys.model.baidu.response.Response response = sysService.addTicket(visitor, "00000000000000000", JSON.toJSONString(ticket));
                        if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
                            if ("1000".equals(response.getCode())) {
                                res.setCode("0000");
                                res.setDescribe("成功");
                            } else if ("3422".equals(response.getCode())) {
                                throw new RuntimeException("该票已注册人脸信息,无需重新注册");
                            } else {
                                throw new RuntimeException(response.getMessage());
                            }
                        } else {
                            throw new RuntimeException(response.getMessage());
                        }
                    }

                }
            } else {
                res.setCode("2001");
                res.setDescribe("证件号码不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

    public Response sendPhoto(SendPhotoRequest request) {
        Response res = new Response();
        if (StringUtils.isBlank(request.getTicketNo())) {
            res.setCode("2001");
            res.setDescribe("票号不能为空");
            return res;
        }
        try {
            Stssoldtickettab stsso = commonSaleDao.findStsso(request.getTicketNo());
            if (stsso != null) {
                List stsist = commonSaleDao
                        .find("from Stssoldticketattesttab where id.isalesvoucherid="
                                + stsso.getId().getIsalesvoucherid()
                                + "and id.isalesvoucherdetailsid="
                                + stsso.getId().getIsalesvoucherdetailsid()
                                + " and id.iticketstationid="
                                + stsso.getId().getIticketstationid()
                                + " and id.szsoldticketid="
                                + stsso.getId().getSzsoldticketid());
                if(stsist != null && !stsist.isEmpty()){
                    Stssoldticketattesttab s = (Stssoldticketattesttab) stsist.get(0);
                    if(!"RL".equalsIgnoreCase(s.getBsfilebinary())){
                        throw new RuntimeException("该票已绑定指纹信息,注册人脸失败");
                    }
                }
                Stssalesvouchertab sale = (Stssalesvouchertab) commonSaleDao.get(Stssalesvouchertab.class,
                        new StssalesvouchertabId(stsso.getId().getIsalesvoucherid(), stsso.getId().getIticketstationid()));
                List<String> groupIds = commonSaleDao.findGardenids(stsso.getId());
                AuthCode authCode = commonSaleDao.getAuthCode(CodeType.SYSTEM.getCode(), null);
                if (groupIds == null || groupIds.isEmpty()) {
                    res.setCode("2001");
                    res.setDescribe("无设备组列表数据");
                } else {
                    Visitor visitor = new Visitor();
                    visitor.setVisitorName(stsso.getName1());
                    visitor.setPhoto(request.getPhotoImg());
                    if (!StringUtils.isBlank(stsso.getMyzj()) && ValivateIdcard.valiIdcard(stsso.getMyzj())) {
                        visitor.setCardType("other");
                        visitor.setCardNumber(stsso.getMyzj());
                        visitor.setVisitorId(stsso.getMyzj());
                    } else {
                        visitor.setVisitorId(authCode.getAuthCode() + "_" + request.getTicketNo());
                        visitor.setCardType("other");
                        visitor.setCardNumber("1");
                    }

                    Ticket ticket = new Ticket();
                    ticket.setTicketId(request.getTicketNo());
                    ticket.setGroupIds(groupIds);
                    ticket.setStartTime(stsso.getDtstartdate() + " 00:00:00");
                    ticket.setEndTime(stsso.getDtenddate() + " 23:59:59");

                    //保存门票人脸数据
                    BaiduTicket baiduTicket = new BaiduTicket();
                    baiduTicket.setTicketNo(request.getTicketNo());
                    baiduTicket.setStatus("0");
                    baiduTicket.setDtmakedate(Tools.getDayTimes());
                    baiduTicket.setVisitorId(visitor.getVisitorId());
                    commonSaleDao.save(baiduTicket);

                    com.ectrip.sys.model.baidu.response.Response response = sysService.addTicket(visitor, sale.getSzsalesvoucherno(), JSON.toJSONString(ticket));
                    if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
                        if ("1000".equals(response.getCode())) {
                            try{
                                StssoldticketattesttabId id = new StssoldticketattesttabId();
                                id.setIsalesvoucherid(stsso.getId().getIsalesvoucherid());
                                id.setIsalesvoucherdetailsid(stsso.getId().getIsalesvoucherdetailsid());
                                id.setIticketstationid(stsso.getId().getIticketstationid());
                                id.setSzsoldticketid(stsso.getId().getSzsoldticketid());
                                Stssoldticketattesttab sts = new Stssoldticketattesttab();
                                sts.setId(id);
                                sts.setBsfilebinary("RL");
                                sts.setByfactregtype("05");
                                sts.setSzidcard("");
                                sts.setSzimagepath("");
                                sts.setIpartitionsign(new Long(0));
                                sts.setDtmakedate(Tools.getDayTimes());
                                sts.setByisout(new Long(1));
                                commonSaleDao.save(sts);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            //保存头像数据
                            try{
                                BufferedImage img = ImageIO.read(new ByteArrayInputStream(
                                        org.apache.commons.codec.binary.Base64.decodeBase64(request.getPhotoImg())));
                                File file = new File(WebContant.GetKeyValue("baiduImg")+stsso.getDtstartdate());
                                if (!file.isFile()) {
                                    file.mkdir();
                                    if (!file.isFile()) {
                                        file.mkdirs();
                                    }
                                }
                                File f = new File(WebContant.GetKeyValue("baiduImg")+stsso.getDtstartdate()+"/"+request.getTicketNo()+".jpg");
                                ImageIO.write(img, "jpg", f);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            res.setCode("0000");
                            res.setDescribe("成功");
                        } else if ("3422".equals(response.getCode())) {
                            throw new RuntimeException("该票已注册人脸信息,无需重新注册");
                        } else {
                            throw new RuntimeException(response.getMessage());
                        }
                    } else {
                        throw new RuntimeException(response.getMessage());
                    }
                }
            } else {
                res.setCode("2001");
                res.setDescribe("票号不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

    public CheckPhotoResponse checkEmpPhoto(SendPhotoRequest request) {
        CheckPhotoResponse res = new CheckPhotoResponse();
        if (StringUtils.isBlank(request.getTicketNo())) {
            res.setCode("2001");
            res.setDescribe("证件号码不能为空");
            return res;
        }
        try {
            Opcemployeecardtab op = commonSaleDao.findEmp(request.getTicketNo());
            List<Ticketpojo> pojos = new ArrayList<Ticketpojo>();
            if (op != null) {
                if (op.getByisdaoyou() == 1) {
                    if (!StringUtils.isBlank(op.getUsid())) {
                        Custom custom = (Custom) commonSaleDao.get(Custom.class, op.getUsid());
                        if (custom != null) {
                            Ticketpojo pojo = new Ticketpojo();
                            pojo.setSztickettypename("导游");
                            pojo.setName1(custom.getLname());
                            pojo.setMyzj(op.getIcardno());
                            pojos.add(pojo);
                        } else {
                            res.setCode("2001");
                            res.setDescribe("导游已注销");
                        }
                    }
                } else {
                    if (op.getIemployeeid() != 0) {
                        Esfemployeetab emp = (Esfemployeetab) commonSaleDao.get(Esfemployeetab.class, op.getIemployeeid());
                        if (emp != null) {
                            Ticketpojo pojo = new Ticketpojo();
                            pojo.setSztickettypename("员工");
                            pojo.setName1(emp.getSzemployeename());
                            pojo.setMyzj(op.getIcardno());
                            pojos.add(pojo);
                        } else {
                            res.setCode("2001");
                            res.setDescribe("员工已注销");
                        }
                    }
                }
                if (!"2001".equalsIgnoreCase(res.getCode())) {
                    res.setCode("0000");
                    res.setDescribe("成功");
                    res.setTicketList(pojos);
                }
            } else {
                res.setCode("2001");
                res.setDescribe("无效证件号码");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询异常");
        }
        return res;
    }

    public CheckPhotoResponse checkPhoto(SendPhotoRequest request) {
        CheckPhotoResponse res = new CheckPhotoResponse();
        if (StringUtils.isBlank(request.getTicketNo())) {
            res.setCode("2001");
            res.setDescribe("票号不能为空");
            return res;
        }
        try {
            List stssList = commonSaleDao.findStssticket(request
                    .getTicketNo());
            if (stssList != null && stssList.size() > 0) {

                List<Ticketpojo> ticketList = new ArrayList<Ticketpojo>();

                for (int x = 0; x < stssList.size(); x++) {
                    Stssoldtickettab stsso = (Stssoldtickettab) stssList.get(x);

                    Edmtickettypetab ticket = (Edmtickettypetab) commonSaleDao.get(
                            Edmtickettypetab.class, stsso.getItickettypeid());
                    if (ticket != null) {
                        Esbscenicareatab scenic = (Esbscenicareatab) commonSaleDao
                                .get(Esbscenicareatab.class, ticket.getIscenicid());
                        if (scenic != null) {
                            Ticketpojo pojo = new Ticketpojo();

                            pojo.setSzscenicname(scenic.getSzscenicname());
                            pojo.setSztickettypename(ticket.getSztickettypename());
                            pojo.setSzticketprintno(stsso.getSzticketprintno());
                            pojo.setDtstartdate(stsso.getDtstartdate());
                            pojo.setDtenddate(stsso.getDtenddate());
                            pojo.setIplayerperticket(stsso.getIplayerperticket());
                            pojo.setName1(stsso.getName1());
                            pojo.setMyzj(stsso.getMyzj());
                            if (stsso.getByvalidity() != null
                                    && stsso.getByvalidity().equals("")) {
                                if (stsso.getByvalidity().equals("00")) {
                                    pojo.setByvalidity("有效");
                                } else if (stsso.getByvalidity().equals("01")) {
                                    pojo.setByvalidity("退票");
                                } else {
                                    pojo.setByvalidity("挂失");
                                }
                            }

                            ticketList.add(pojo);
                        } else {
                            res.setCode("2001");
                            res.setDescribe("景区服务商不存在");
                        }

                    } else {
                        res.setCode("2001");
                        res.setDescribe("门票不存在");
                    }

                }

                res.setCode("0000");
                res.setDescribe("成功");
                res.setTicketList(ticketList);

            } else {
                res.setCode("2001");
                res.setDescribe("票号不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询异常");
        }
        return res;
    }

    public CheckRegisterPhotoResponse checkRegisterPhoto(String ticketNo) {
        CheckRegisterPhotoResponse res = new CheckRegisterPhotoResponse();
        if (StringUtils.isBlank(ticketNo)) {
            throw new RuntimeException("票号或者身份证不能为空");
        }
        List<Stssoldtickettab> solds=new ArrayList<Stssoldtickettab>();
        try {
            //CYT或者HQYT开头的网络订单,查询出票号
            if(ticketNo.startsWith("HQYT_") || ticketNo.startsWith("CYT_")){
                String orderNo = "";
                if(ticketNo.startsWith("HQYT_"))
                {
                    orderNo=DesUtil.decrypt(ticketNo.substring(5),ConstUtils.HQYTKEY);
                }else if(ticketNo.startsWith("CYT_"))
                {
                    orderNo=DesUtil.decrypt(ticketNo.substring(4),ConstUtils.KEY);
                }
                GenericDao genericDao = (GenericDao) SpringUtil.getBean("genericDao");
                List<Stssalesvouchertab>  vouchers= genericDao.find("from Stssalesvouchertab " +
                        " where szsalesvoucherno='" + orderNo + "' order by dtmakedate desc");
                if(vouchers!=null && !vouchers.isEmpty())
                {
                    solds= genericDao.find("from Stssoldtickettab " +
                            " where isalesvoucherid=" + vouchers.get(0).getIsalesvoucherid() + " order by ticket.dtmakedate desc");
                }
            } else
            {
                solds= commonSaleDao.findStssticket(ticketNo);
            }
        } catch (Exception e)
        {
           e.printStackTrace();
        }
        if(solds != null && !solds.isEmpty()){
            AuthCode authCode = commonSaleDao.getAuthCode(CodeType.SYSTEM.getCode(), null);
            String visitorId = "";
            Stssoldtickettab stsso = solds.get(0);
            if (!StringUtils.isBlank(stsso.getMyzj()) && ValivateIdcard.valiIdcard(stsso.getMyzj())) {
                visitorId = stsso.getMyzj();
            } else {
                visitorId = authCode.getAuthCode() + "_" + stsso.getSzticketprintno();
            }
            Stssalesvouchertab sale = (Stssalesvouchertab) commonSaleDao.get(Stssalesvouchertab.class,
                    new StssalesvouchertabId(stsso.getId().getIsalesvoucherid(), stsso.getId().getIticketstationid()));
            List<String> groupIds = commonSaleDao.findGardenids(stsso.getId());
            if (groupIds == null || groupIds.isEmpty()) {
                throw new RuntimeException("无设备组列表数据");
            }
            Visitor visitor = new Visitor();
            visitor.setVisitorName(stsso.getName1());
            if (!StringUtils.isBlank(stsso.getMyzj()) && ValivateIdcard.valiIdcard(stsso.getMyzj())) {
                visitor.setCardType("other");
                visitor.setCardNumber(stsso.getMyzj());
                visitor.setVisitorId(stsso.getMyzj());
            } else {
                visitor.setVisitorId(authCode.getAuthCode() + "_" + stsso.getSzticketprintno());
                visitor.setCardType("other");
                visitor.setCardNumber("1");
            }

            Ticket ticket = new Ticket();
            ticket.setTicketId(stsso.getSzticketprintno());
            ticket.setGroupIds(groupIds);
            ticket.setStartTime(stsso.getDtstartdate() + " 00:00:00");
            ticket.setEndTime(stsso.getDtenddate() + " 23:59:59");

            res.setVisitor(visitor);
            res.setTicket(ticket);
            res.setOrid(sale.getSzsalesvoucherno());
            com.ectrip.sys.model.baidu.response.Response response = sysService.checkVisitorPhoto(visitorId);
            if (HttpStatusType.SUCCESS.getCode() == response.getHttpStatus()) {
                if ("1000".equals(response.getCode())) {//已注册头像信息
                    res.setCode("0000");
                    res.setDescribe("已注册头像信息");
                } else {//未注册头像信息
                    res.setCode("0001");
                    res.setDescribe("查询成功");
                }
            } else {
                throw new RuntimeException(response.getMessage());
            }
        }else{
            throw new RuntimeException("无该票号或身份证数据");
        }
        return res;
    }

    public Response saveRegisterPhoto(String ticketNo,String visitorId){
        //保存门票人脸数据
        BaiduTicket baiduTicket = new BaiduTicket();
        baiduTicket.setTicketNo(ticketNo);
        baiduTicket.setStatus("0");
        baiduTicket.setDtmakedate(Tools.getDayTimes());
        baiduTicket.setVisitorId(visitorId);
        baiduTicket.setPicStatus(0L);
        commonSaleDao.save(baiduTicket);
        Response response = new Response();
        response.setCode("0000");
        return response;
    }

    public CheckFingerPrintResponse checkFingerPrint(CheckFingerPrintRequest request){
        CheckFingerPrintResponse res = commonSaleDao.findFingerPrints(request);
        res.setCode("0000");
        res.setDescribe("指纹信息查询成功");
        return res;
    }

    public FindCheckListResponse findCheckList(FindCheckListRequest request){
        FindCheckListResponse res = new FindCheckListResponse();
        try{
            Stssoldtickettab sold = commonSaleDao.findStsso(request.getTicketNo());
            if(sold != null){
                List<CheckListPojo> pojos = commonSaleDao.getchecklist(sold);
                res.setCheckList(pojos);
                res.setCode("0000");
                res.setDescribe("成功");
            }else{
                res.setCode("2001");
                res.setDescribe("未获取到票号信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;
    }
}
