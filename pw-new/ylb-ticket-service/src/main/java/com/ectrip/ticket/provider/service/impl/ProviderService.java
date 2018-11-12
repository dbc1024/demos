package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import com.ectrip.base.service.GenericService;
import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.MerchantRequest;
import com.ectrip.hqyt.model.response.JSONMerchant;
import com.ectrip.hqyt.model.response.JSONProviderCompany;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.provider.Secenicpicture;
import com.ectrip.ticket.provider.dao.IProviderDAO;
import com.ectrip.ticket.provider.dao.ISecenicpictureDAO;
import com.ectrip.ticket.provider.dao.ITicketTypeDAO;
import com.ectrip.ticket.provider.service.IProviderService;
import com.ectrip.upload.dao.idao.IUpfileDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import sun.net.www.content.text.Generic;

@Service
public class ProviderService extends GenericService implements IProviderService {
	@Autowired
    private IProviderDAO providerDao;//服务商dao
    private ISecenicpictureDAO secenicpictureDao;//图片dao
    private IUpfileDao upfileDao;//文件上传
    private ITicketTypeDAO tickettypeDao;//票类型dao

   
    /**
     * 根据服务商编号查询服务商信息*
     * Describe:
     *
     * @param scenicid
     * @return
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#query(java.lang.Long)
     */
    public Esbscenicareatab query(Long scenicid) {
        return providerDao.query(scenicid);
    }

    /**
     * 更新层序号*
     * Describe:
     *
     * @param ilever层
     * @param ileverseq层序号
     * @param objectId对象id
     * @param tablename表名
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#updateIleverseq(int, int, java.lang.Long, java.lang.String)
     */
    public void updateIleverseq(int ilever, int ileverseq, Long objectId, String tablename) {
        providerDao.updateIleverseq(ilever, ileverseq, objectId, tablename);
    }

    /**
     * 查询所有服务商列表 *
     * Describe:
     *
     * @param iprentid父编号
     * @param pdtp服务商类别
     * @param page页码
     * @param pagesize每页显示数
     * @param url地址
     * @param path标识(next向下,up向上)
     * @return
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#queryAllList(java.lang.Long, java.lang.String, int, int, java.lang.String, java.lang.String)
     */
    public PaginationSupport queryAllList(Long iprentid, String pdtp, int page, int pagesize, String url, String path, Long employeeid) {
        return providerDao.queryAllList(iprentid, pdtp, page, pagesize, url, path, employeeid);
    }

    /**
     * 得到景点列表
     * Describe:
     *
     * @param iprentid
     * @param pdtp
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param iemployeeid后台登录用户
     * @return return:PaginationSupport
     * Date:2011-10-19
     * @auth:huangyuqi
     */
    public PaginationSupport queryAllList(Long iprentid, int page, int pagesize, String url, String path) {
        return providerDao.queryAllList(iprentid, page, pagesize, url, path);
    }

    /**
     * 根据条件查询服务商*
     * Describe:
     *
     * @param page
     * @param pagesize
     * @param url
     * @param path
     * @param buttontype
     * @param where
     * @param pdtp
     * @param employeeid登录人
     * @param isjd是否是景点
     * @return
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#queryList(int, int, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
     */
    public PaginationSupport queryList(int page, int pagesize, String url, String path, int buttontype, String where, String pdtp, Long employeeid, int isjd) {
        return providerDao.queryList(page, pagesize, url, path, buttontype, where, pdtp, employeeid, isjd);
    }

    /**
     * 根据服务商Id判断是否存在订单*
     * Describe:
     *
     * @param scenicid服务商Id
     * @return
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#retiveOrder(java.lang.Long)
     */
    public int retiveOrder(Long scenicid) {
        return providerDao.retiveOrder(scenicid);
    }

    /**
     * 判断服务商是否是最大层级 *
     * Describe:
     *
     * @param scencid
     * @return
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#boolMaxpro(java.lang.Long)
     */
    public boolean boolMaxpro(Long scencid) {
        return providerDao.boolMaxpro(scencid);
    }

    /**
     * 根据服务商编号得到服务商名称*
     * Describe:
     *
     * @param providerId
     * @return
     * @author huangyuqi
     * Date:2011-9-27
     * @see com.ectrip.system.provider.service.iservice.IProviderService#QueryProviderName(java.lang.Long)
     */
    public String QueryProviderName(Long providerId) {
        return providerDao.QueryProviderName(providerId);
    }

    /**
     * 服务商保存*
     * Describe:
     *
     * @param esbscenicareatab服务商
     * @param picturelist图片库列表
     * @author huangyuqi
     * Date:2011-9-24
     * @see com.ectrip.system.provider.service.iservice.IProviderService#insertProvider(com.ectrip.model.provider.Esbscenicareatab, java.util.List)
     */
    public void insertProvider(Esbscenicareatab esbscenicareata, Syslog syslog) throws Exception {
        providerDao.save(esbscenicareata);
        try {
            if (esbscenicareata.getUpids() != null) {
                for (int i = 0; i < esbscenicareata.getUpids().length; i++) {
                    Secenicpicture picture = new Secenicpicture();
                    picture.setIscenicid(esbscenicareata.getIscenicid());// 服务商id
                    picture.setItickettypeid(0L);
                    picture.setUpid(Long
                            .parseLong(esbscenicareata.getUpids()[i]));

                    Long maxseq = providerDao.getMaxPk("isecenicpictureid",
                            "Secenicpicture");
                    picture.setIsecenicpictureid(maxseq + 1);// 图库Id
                    providerDao.save(picture);
                }
            }

            syslog.setStlg("0007");
            syslog.setBrief("服务商：" + esbscenicareata.getSzscenicname());
            syslog.setNote("服务商增加：" + esbscenicareata.getSzinnername() + "(" + esbscenicareata.getIscenicid() + ")");
            syslog.setLogdatetime(Tools.getDayTimes());
            Long logid = providerDao.getMaxPk("logid", "Syslog");
            syslog.setLogid(logid + 1);
            providerDao.save(syslog);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    /**
     * 修改服务商
     * Describe:
     *
     * @param esbscenicareatab return:void
     *                         Date:2011-9-24
     * @auth:huangyuqi
     */
    public void updateProvider(Esbscenicareatab esbscenicareatab, Syslog syslog) {
        providerDao.update(esbscenicareatab);

        if (esbscenicareatab.getUpids() != null) {
            //查询此产品先前的图片，如有则先全删除，
            List piclist = secenicpictureDao.QueryPictureByProvider(esbscenicareatab.getIscenicid());
            if (piclist.size() > 0) {
                //删除图库中的数据
                secenicpictureDao.deletePictureByProvidre(esbscenicareatab.getIscenicid(), "update");
                //删除upfile数据
                upfileDao.deleteUpfilesbyProvider(esbscenicareatab.getIscenicid(), "update");

            }

            for (int i = 0; i < esbscenicareatab.getUpids().length; i++) {
                Secenicpicture picture = new Secenicpicture();
                picture.setIscenicid(esbscenicareatab.getIscenicid());// 服务商id
                picture.setItickettypeid(0L);
                picture.setUpid(Long.parseLong(esbscenicareatab.getUpids()[i]));

                Long maxseq = providerDao.getMaxPk("isecenicpictureid",
                        "Secenicpicture");
                picture.setIsecenicpictureid(maxseq + 1);// 图库Id
                providerDao.save(picture);
            }
        }

        syslog.setStlg("0012");
        syslog.setBrief("服务商：" + esbscenicareatab.getSzscenicname());
        syslog.setNote("服务商修改：" + esbscenicareatab.getSzinnername() + "(" + esbscenicareatab.getIscenicid() + ")");
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = providerDao.getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        providerDao.save(syslog);

    }

    /**
     * 删除服务商
     * Describe:
     *
     * @param providerId return:void
     *                   Date:2011-9-24
     * @auth:huangyuqi
     */
    public void deletePorvider(Long providerId, Syslog syslog) {

        Esbscenicareatab scenic = (Esbscenicareatab) providerDao.get(Esbscenicareatab.class, providerId);

        syslog.setStlg("0013");
        syslog.setBrief("服务商：" + scenic.getSzscenicname());
        syslog.setNote("服务商删除：" + scenic.getSzinnername() + "(" + scenic.getIscenicid() + ")");
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = providerDao.getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        providerDao.save(syslog);


        //删除upfile数据
        upfileDao.deleteUpfilesbyProvider(providerId, "delete");
        //删除图库中的数据
        secenicpictureDao.deletePictureByProvidre(providerId, "delete");

        //删除线路
        //酒店附加服务

        //删除产品附属属性
        tickettypeDao.deleteOtherProductByPdno(providerId, scenic.getScenictype());
        //删除公司与服务商关联数据
        providerDao.deleteOhters(providerId);

        //删除酒店属性数据
        String hsql = " from Hotelprovider where iscenicid = " + providerId;
        List hotelproviderlist = this.providerDao.find(hsql);
        if (hotelproviderlist != null && hotelproviderlist.size() > 0) {
            for (int i = 0; i < hotelproviderlist.size(); i++) {
                Hotelprovider hotelprovider = (Hotelprovider) hotelproviderlist.get(i);
                this.providerDao.delete(hotelprovider);
            }
        }

        //删除其它数据(价格及价格拆分表，景点。删除酒店属性表、删除酒店服务表)
//		providerDao.deleteProvider(providerId);
        //删除产品表中的数据
//		tickettypeDao.deleteProduct(providerId);


        providerDao.deleteByKey(Esbscenicareatab.class, providerId);//删除服务商
    }

    /**
     * 根据登录人得到服务商列表
     * Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @param esfemployeetab
     * @param scenictype     服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd           是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq       是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List
     * Date:2011-10-28
     * @auth:huangyuqi
     */
    public List getScenicList(Esfemployeetab esfemployeetab, String scenictype, int isjd, String isonlyjq,String groupid,boolean isHqyatu) {
        return providerDao.getScenicList(esfemployeetab, scenictype, isjd, isonlyjq,groupid,isHqyatu);
    }

    /**
     * 根据得到服务商列表(前台专用)
     * Describe:当服务商类型为01时可包含服务商，景点，也可只包含一种
     *
     * @param esfemployeetab
     * @param scenictype     服务商类型（如要查询所有服务商，此参数为空）
     * @param isjd           是否含景点（0为服务商，1为景点，2为服务商与景点）
     * @param isonlyjq       是否包含所属于些服务商类型下的所有服务商（01是，02否）
     * @return return:List
     * Date:2011-10-28
     * @auth:huangyuqi
     */
    public List getScenicListByPage(String scenictype, int isjd, String isonlyjq) {
        return providerDao.getScenicListByPage(scenictype, isjd, isonlyjq);
    }

    /**
     * 根据酒店编号查询出酒店属性
     * Describe:
     *
     * @param iscenicid
     * @return return:Hotelprovider
     * Date:2011-12-12
     * @auth:huangyuqi
     */
    public Hotelprovider queryHotelProvider(Long iscenicid) {
        return providerDao.queryHotelProvider(iscenicid);
    }

    /**
     * 保存酒店服务商属性
     * Describe:
     *
     * @param hotelprovier return:void
     *                     Date:2011-12-12
     * @auth:huangyuqi
     */
    public void saveHotelProvider(Hotelprovider hotelprovier) {
        //品牌
        if (hotelprovier.getNoted2() != null && !hotelprovier.getNoted2().equals("") && !hotelprovier.getNoted2().equals("99")) {
            Sysparv5 sys = (Sysparv5) providerDao.get(Sysparv5.class, new Sysparv5Id("HTPP", hotelprovier.getNoted2()));
            hotelprovier.setNoted2(sys.getPmva());
        } else {
            hotelprovier.setNoted2("");
        }
        //位置
        if (hotelprovier.getSqids() != null && hotelprovier.getSqids().length > 0) {
            String[] serviceids = hotelprovier.getSqids();
            StringBuffer sq = new StringBuffer();
            for (int i = 0; i < serviceids.length; i++) {
                sq.append(serviceids[i].replace(" ", "") + " ");
            }
            hotelprovier.setNoted1(sq.toString());
        }
        //设施
        if (hotelprovier.getServiceids() != null && hotelprovier.getServiceids().length > 0) {
            String[] serviceids = hotelprovier.getServiceids();
            StringBuffer service = new StringBuffer();
            for (int i = 0; i < serviceids.length; i++) {
                Sysparv5 sys = (Sysparv5) providerDao.get(Sysparv5.class, new Sysparv5Id("HTSS", serviceids[i].toString()));
                service.append(sys.getPmva() + " ");
            }
            hotelprovier.setHotelservice(service.toString());
        }
        if (hotelprovier.getNoted6() != null && !hotelprovier.getNoted6().equals("")) {
            hotelprovier.setNoted6(hotelprovier.getNoted6().replace(" ", ""));
        }
        if (hotelprovier.getNoted7() != null && !hotelprovier.getNoted7().equals("")) {
            hotelprovier.setNoted7(hotelprovier.getNoted7().replace(" ", ""));
        }
        if (hotelprovier.getNoted8() != null && !hotelprovier.getNoted8().equals("")) {
            hotelprovier.setNoted8(hotelprovier.getNoted8().replace(" ", ""));
        }
        providerDao.saveHotelProvider(hotelprovier);
    }

    /**
     * 根据服务商编号查询出酒店属性
     * Describe:
     *
     * @param iscenicid
     * @return return:Hotelprovider
     * Date:2012-08-20
     * @auth:aozhuozu
     */
    public Hotelprovider queryScenicProvider(Long iscenicid) {
        return providerDao.queryScenicProvider(iscenicid);
    }

    /**
     * 保存服务商-景区属性
     * Describe:
     *
     * @param hotelprovier return:void
     *                     Date:2012-08-20
     * @auth:huangyuqi
     */
    public void saveScenicprovider(Hotelprovider hotelprovier) {
        providerDao.saveScenicprovider(hotelprovier);
    }

    /**
     * 获取服务商数量  包含各种服务商  禁用的也包含在内
     */
    public int getProviderCount() {
        return providerDao.getProviderCount();
    }

    /**
     * *
     * Describe:搜索服务商
     *
     * @param type       服务商类别
     * @param notinIds   不包含的服务商ID,以逗号隔开
     * @param regionalid 客源地ID
     * @param inIds      包含的服务商ID,以逗号隔开
     * @return
     * @author chenxinhao
     * Date:2013-12-9
     * @see com.ectrip.system.provider.dao.idao.IProviderDAO#searchNearbyProvider(java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
     */
    public List searchNearbyProvider(String type, String notinIds, Long regionalid, String inIds) {
        return providerDao.searchNearbyProvider(type, notinIds, regionalid, inIds);
    }

    /**
     * Describe:搜索产品
     *
     * @param type       服务商类别
     * @param notinIds   为选择产品ID
     * @param inIds      以选择产品ID
     * @param regionalid 客源地
     * @param radio      0为未选择列表  1为已选择列表
     * @return return:List
     * Date:2013-12-9
     * @author:chenxinhao
     */
    public List searchProduct(String type, String notinIds, String inIds,
                              Long regionalid, int radio) {
        return providerDao.searchProduct(type, notinIds, inIds, regionalid, radio);
    }

    public List searchAddress(Long regionalid) {
        return providerDao.searchAddress(regionalid);
    }

    public ProviderCompany queryProviderCompany(Long iscenicid) {
//        String hsql = "from ProviderCompany where type='02' ";
//        StringBuffer sb = new StringBuffer(hsql);
//        if(iscenicid!=0){
//        	sb.append("and iscenicid = " + iscenicid);
//        }
//        List list = providerDao.find(sb.toString());
//        if (list != null && !list.isEmpty()) {
//            return (ProviderCompany) list.get(0);
//        }
    	
        return providerDao.queryProviderCompany(iscenicid);
    }

    public void saveProviderCompany(ProviderCompany pc) {
        try{
            //接口开户
            HqytClient client = new HqytClient();
            
            JSONProviderCompany jpc = new JSONProviderCompany();
            BeanUtils.copyProperties(jpc, pc);
            MerchantRequest request = MerchantRequest.from(jpc);
            JSONMerchant merchant = client.createMerchants(request);
            if (merchant != null) {
                pc.setStatus(1);
                pc.setAdminLogin(merchant.getAdminLogin());
                pc.setHqytId(merchant.getId());
                pc.setMerchantKey(merchant.getMerchantKey());
            }
            providerDao.saveOrUpdate(pc);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    

	public List getScenicListByAreaIds(String ids) throws Exception {
		// TODO Auto-generated method stub
		return providerDao.getScenicListByAreaIds(ids);
	}

	public List getScenicListSelect(String providerType) {
		// TODO Auto-generated method stub
		return providerDao.getScenicListSelect(providerType);
	}

	public List getProviderList(String providerType, String keyword) {
		// TODO Auto-generated method stub
		return providerDao.getProviderList(providerType, keyword);
	}

    public String findpmcd(String pmky, String pmva){
        return providerDao.findpmcd(pmky, pmva);
    }

	@Override
	public List getProvidersByIds(String ids) {
		// TODO Auto-generated method stub
		return providerDao.getProvidersByIds(ids);
	}

	
}
