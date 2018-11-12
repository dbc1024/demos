package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelduct;
import com.ectrip.ticket.model.provider.HotelductId;
import com.ectrip.ticket.model.provider.Secenicpicture;
import com.ectrip.ticket.model.provider.TicketPrintNo;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;
import com.ectrip.ticket.provider.dao.ISecenicpictureDAO;
import com.ectrip.ticket.provider.dao.ITicketTypeDAO;
import com.ectrip.ticket.provider.service.ITicketTypeService;
import com.ectrip.upload.dao.idao.IUpfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketTypeService extends GenericService implements
        ITicketTypeService {
   
    private ITicketTypeDAO tickettypeDao;
    @Autowired
    public void setTickettypeDao(ITicketTypeDAO tickettypeDao) {
        this.tickettypeDao = tickettypeDao;
        setGenericDao(tickettypeDao);
    }
    private ISecenicpictureDAO secenicpictureDao;
    private IUpfileDao upfileDao;
    

    /**
     * 查看某服务商下所有产品列表*
     * Describe:
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#getTickettypeList(java.lang.Long, int, int, java.lang.String)
     * @param providerId服务商编号
     * @param page页码
     * @param pagesize每页显示数
     * @param url访问地址
     * @return
     * @author huangyuqi
     * Date:2011-9-22
     */
    public PaginationSupport getTickettypeList(String searchkey,Long providerId,int page, int pagesize,
                                               String url,String type) {

        return tickettypeDao.getTickettypeList(searchkey,providerId,page, pagesize, url,type);
    }
    /**
     * 查看某服务商下所有有产品列表
     * Describe:
     * @auth:chenyidong
     * @param providerId服务商编号
     * @param type票类型（基础、套票）
     * @return
     * return:List
     * Date:2013-03-22
     */
    public List getTickettypeListSelect(Long providerId,String type){
        List list = tickettypeDao.getTickettypeListSelect(providerId, type);
        return list;
    }
    /**
     * 根据产品编号查询产品信息*
     * Describe:
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#queryTickettype(java.lang.Long)
     * @param tickettypeId产品编号
     * @return
     * @author huangyuqi
     * Date:2011-9-22
     */
    public Edmtickettypetab queryTickettype(Long tickettypeId){
        return tickettypeDao.queryTickettype(tickettypeId);
    }
    /**
     * 判断产品在订单中是否存在
     * Describe:
     * @auth:huangyuqi
     * @param tickettypeId
     * @return
     * return:boolean
     * Date:2011-9-22
     */
    public boolean querytickettypeByOrder(Long tickettypeId){
        return tickettypeDao.querytickettypeByOrder(tickettypeId);
    }

    /**
     * 新增产品
     * Describe:
     * @auth:huangyuqi
     * @param tickettype
     * return:void
     * Date:2011-9-26
     */
    public void insertTicketType(Edmtickettypetab tickettype,String[] hoteltick,Syslog syslog){
        //陈新浩修改		时间：2012-08-28		增加icid写入
//		String sql = " select max(icid) from Edmtickettypetab where iscenicid="+tickettype.getIscenicid();
//		List list = this.find(sql);
//		String maxid = "0";
//		if(list!=null&&list.size()>0){
//			maxid = (String) list.get(0);
//			if(maxid==null){
//				maxid = "0";
//			}
//		}
//		tickettype.setIcid(Long.toHexString(Long.valueOf(maxid,16)+1L).toUpperCase());
        this.save(tickettype);

        //当产品的产品类别不为服务商附加服务和产品附加服务时，当选择相关联的产品附加服务时添加。
        Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.get(Esbscenicareatab.class, tickettype.getIscenicid());
        //酒店服务商下的产品管理   在新增非附加服务类产品时，将酒店产品和产品附加服务类产品关联
        if(esbscenicareatab.getScenictype().equals("06")){
            if(!tickettype.getBycategorytype().equals("119")&&!tickettype.getBycategorytype().equals("120")){
                if(hoteltick!=null){
                    for(int i=0;i<hoteltick.length;i++){
                        Hotelduct hotel=new Hotelduct();
                        HotelductId ducid=new HotelductId();
                        ducid.setDuctid(tickettype.getItickettypeid());
                        ducid.setHotelid(new Long(hoteltick[i]));
                        hotel.setId(ducid);
                        this.save(hotel);
                    }
                }
            }

        }

        if(tickettype.getUpids()!=null){//图片列表

            for (int i = 0; i < tickettype.getUpids().length; i++) {
                Secenicpicture picture = new Secenicpicture();
                picture.setIscenicid(tickettype.getIscenicid());// 服务商id
                picture.setItickettypeid(tickettype.getItickettypeid());//产品id
                picture.setUpid(Long
                        .parseLong(tickettype.getUpids()[i]));

                Long maxseq = getMaxPk("isecenicpictureid",
                        "Secenicpicture");
                picture.setIsecenicpictureid(maxseq+1);// 图库Id
                this.save(picture);
            }
        }

        syslog.setStlg("0015");
        syslog.setBrief("产品：" + tickettype.getSztickettypename() );
        syslog.setNote("产品新增：" + tickettype.getSztickettypename());
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);
    }

    /**
     * 修改产品
     * Describe:
     * @auth:huangyuqi
     * @param tickettype
     * return:void
     * Date:2011-9-26
     */
    public void updateTicketType(Edmtickettypetab tickettype,String[] hoteltick,Syslog syslog){
        this.update(tickettype);

        //当产品的产品类别不为服务商附加服务和产品附加服务时，当选择相关联的产品附加服务时添加。
        Esbscenicareatab esbscenicareatab = (Esbscenicareatab) this.get(Esbscenicareatab.class, tickettype.getIscenicid());
        //酒店服务商下的产品管理   在新增非附加服务类产品时，将酒店产品和产品附加服务类产品关联
        if(esbscenicareatab.getScenictype().equals("06")){
            if(!tickettype.getBycategorytype().equals("119")&&!tickettype.getBycategorytype().equals("120")){
                String sql=" from Hotelduct d where d.id.ductid="+tickettype.getItickettypeid();
                List lst=this.find(sql);
                if(lst!=null&&lst.size()>0){
                    for(int x=0;x<lst.size();x++){
                        this.delete(lst.get(x));
                    }
                }

                if(hoteltick!=null){
                    for(int i=0;i<hoteltick.length;i++){
                        Hotelduct hotel=new Hotelduct();
                        HotelductId ducid=new HotelductId();
                        ducid.setDuctid(tickettype.getItickettypeid());
                        ducid.setHotelid(new Long(hoteltick[i]));
                        hotel.setId(ducid);
                        this.save(hotel);
                    }
                }

            }

        }



        if(tickettype.getUpids()!=null){//图片列表
            //查询此产品先前的图片，如有则先全删除，
            List piclist = secenicpictureDao.QueryPictureByProduct(tickettype.getItickettypeid());

            if(piclist.size()>0){
                //图库
                secenicpictureDao.deletePictureByProduct(tickettype.getItickettypeid());
                //删除upfile中的数据
                upfileDao.deleteUpfilesbyProduct(tickettype.getItickettypeid());
            }

            for (int i = 0; i < tickettype.getUpids().length; i++) {
                Secenicpicture picture = new Secenicpicture();
                picture.setIscenicid(tickettype.getIscenicid());// 服务商id
                picture.setItickettypeid(tickettype.getItickettypeid());//产品id
                picture.setUpid(Long
                        .parseLong(tickettype.getUpids()[i]));

                Long maxseq = getMaxPk("isecenicpictureid",
                        "Secenicpicture");
                picture.setIsecenicpictureid(maxseq+1);// 图库Id
                this.saveOrUpdate(picture);
            }
        }

        syslog.setStlg("0016");
        syslog.setBrief("产品：" + tickettype.getSztickettypename() );
        syslog.setNote("产品修改：" + tickettype.getSztickettypename() +"("+tickettype.getItickettypeid()+")");
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);

    }
    /**
     * 删除产品
     * Describe:
     * @auth:huangyuqi
     * @param tickettypeId
     * return:void
     * Date:2011-9-26
     */
    public void deleteTicketType(Long tickettypeId,Syslog syslog){
        //根据产品编号得到产品信息
        Edmtickettypetab tickettype = (Edmtickettypetab)this.get(Edmtickettypetab.class, tickettypeId);
        //根据服务商编号查询服务商信息
        Esbscenicareatab provider = (Esbscenicareatab)this.get(Esbscenicareatab.class, tickettype.getIscenicid());

        //增加日志
        syslog.setStlg("0017");
        syslog.setBrief("产品：" + tickettype.getSztickettypename() );
        syslog.setNote("产品删除：" + tickettype.getSztickettypename() +"("+tickettype.getItickettypeid()+")");
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);


        //删除附属属性数据
        if("06".equals(provider.getScenictype())){//酒店
            tickettypeDao.deletOtherProduct(tickettypeId, provider.getScenictype());

            //删除产品附加服务
            String sql=" from Hotelduct d where d.id.ductid="+tickettypeId+" or d.id.hotelid="+tickettypeId;
            List lst=this.find(sql);
            if(lst!=null&&lst.size()>0){
                for(int x=0;x<lst.size();x++){
                    this.delete(lst.get(x));
                }
            }
        }
        if("10".equals(provider.getScenictype())){//租车
            //删除附属属性
            tickettypeDao.deletOtherProduct(tickettypeId, provider.getScenictype());
        }
        if("07".equals(provider.getScenictype())){//旅行社
            //删除附属属性
            tickettypeDao.deletOtherProduct(tickettypeId, provider.getScenictype());
            //删除线路表数据

        }
        //图库
        secenicpictureDao.deletePictureByProduct(tickettypeId);
        //删除upfile中的数据
        upfileDao.deleteUpfilesbyProduct(tickettypeId);

        //删除价格表及价格折分表数据
        tickettypeDao.deleteProductOhter(tickettypeId);

        this.deleteByKey(Edmtickettypetab.class, tickettypeId);//删除产品
    }

    /**
     * 查询产品附属属性
     * Describe:
     * @auth:huangyuqi
     * @param productId产品Id
     * @param providerpdtp服务商类型
     * @return
     * return:Object
     * Date:2011-9-27
     */
    public Object getOthersProduct(Long productId,String providerpdtp){
        return tickettypeDao.getOthersProduct(productId,providerpdtp);
    }

    /**
     * *
     * Describe:显示出某服务商下的产品信息（显示的产品的产品类别为产品附加服务）
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#quckListedmticket(java.lang.Long)
     * @param iscenicid
     * @return
     * @author lijingrui
     * Date:Feb 9, 2012
     */
    public List quckListedmticket(Long iscenicid){
        return tickettypeDao.quckListedmticket(iscenicid);
    }

    /**
     * *
     * Describe:显示出某产品的附加产品信息
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#showAllhotelduct(java.lang.Long)
     * @param itickettypeid
     * @return
     * @author lijingrui
     * Date:Feb 9, 2012
     */
    public List showAllhotelduct(Long itickettypeid){
        return tickettypeDao.showAllhotelduct(itickettypeid);
    }

    /**
     * *
     * Describe:显示出某产品的以附加产品的信息（查看）
     * @see com.ectrip.system.provider.service.iservice.ITicketTypeService#quickviewhotelticket(java.lang.Long)
     * @param itickettypeid
     * @return
     * @author lijingrui
     * Date:Feb 10, 2012
     */
    public List quickviewhotelticket(Long itickettypeid){
        return tickettypeDao.quickviewhotelticket(itickettypeid);
    }

    public PaginationSupport queryPrintno(Long itickettypeid,String printno,String serialnumber,int page, int pagesize,String url){
        return tickettypeDao.queryPrintno(itickettypeid, printno, serialnumber, page, pagesize, url);
    }

    public void addPrintNo(TicketPrintNo printNo,Syslog syslog){
        tickettypeDao.addPrintNo(printNo,syslog);
    }

    public void editPrintNo(TicketPrintNo printNo,Syslog syslog){
        tickettypeDao.editPrintNo(printNo, syslog);
    }

	@Override
	public List<Edmticketcomposepricetab> getSonPrice(Long icrowkindid, Long itckettypeid) {
		// TODO Auto-generated method stub
		return tickettypeDao.getSonPrice(icrowkindid, itckettypeid);
	}

	@Override
	public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid,
			String startdate) {
		// TODO Auto-generated method stub
		return tickettypeDao.getScheme(iscenicid, ibusinessid, icrowkind, itickettypeid, startdate);
	}

	@Override
	public Productcontrol getTripControl(Long tripid, Long ivenueid, Long ivenueareaid, String tourdate) {
		// TODO Auto-generated method stub
		return tickettypeDao.getTripControl(tripid, ivenueid, ivenueareaid, tourdate);
	}

	@Override
	public List getTicketTypeListByIds(String itickettypeids) {
		// TODO Auto-generated method stub
		return tickettypeDao.getTicketTypeListByIds(itickettypeids);
	}

	@Override
	public List getTicketTypeSelect(String scenics) {
		// TODO Auto-generated method stub
		return tickettypeDao.getTicketTypeSelect(scenics);
	}

	@Override
	public List getSonTicketTypeSelect(Long itickettypeid) {
		// TODO Auto-generated method stub
		return tickettypeDao.getSonTicketTypeSelect(itickettypeid);
	}
}

