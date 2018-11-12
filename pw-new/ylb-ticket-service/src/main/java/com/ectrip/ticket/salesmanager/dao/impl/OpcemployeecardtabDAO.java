package com.ectrip.ticket.salesmanager.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.report.sales.Stssoldfingerprint;
import com.ectrip.sys.model.employee.Companyscenic;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeefingerprinttab;
import com.ectrip.ticket.salesmanager.dao.IOpcemployeecardtabDAO;


/**
 * @author Jzhenhua,Created Time 2011-10-06
 */
@Repository
public class OpcemployeecardtabDAO extends GenericDao implements
        IOpcemployeecardtabDAO {

	@Autowired
	private SysService sysService;
	
	
    /**
     * 添加
     */
    public void addOpcemployeecard(Opcemployeecardtab opcemployeecardtab) {
        opcemployeecardtab.setItickettypeid(new Long(0));
        opcemployeecardtab.setSzticketprintno("0");
        opcemployeecardtab.setByisdaoyou(new Long(0));
        opcemployeecardtab.setMremainmoney(new Double(0));
        opcemployeecardtab.setMpresentmoney(new Double(0));
        opcemployeecardtab.setIpresentnum(new Long(0));
        opcemployeecardtab.setIremainnum(new Long(0));
        opcemployeecardtab.setIsa(0L);
        this.save(opcemployeecardtab);

        Esfemployeetab esfemployee=(Esfemployeetab) this.get(Esfemployeetab.class, opcemployeecardtab.getIemployeeid());
        String scenics="";
        if(esfemployee!=null){
            Galcompanyinfotab company=(Galcompanyinfotab)this.get(Galcompanyinfotab.class, esfemployee.getIcompanyinfoid());
            if(company!=null&&company.getCompanytype().equals("02")){
                String hsql=" from Companyscenic cs where cs.id.icompanyinfoid="+company.getIcompanyinfoid();
                List hsqlList=this.find(hsql);
                if(hsqlList!=null&&hsqlList.size()>0){
                    for(int x=0;x<hsqlList.size();x++){
                        Companyscenic pany=(Companyscenic) hsqlList.get(x);
                        scenics+=pany.getId().getIscenicid();
                        if(x!=hsqlList.size()-1){
                            scenics+=",";
                        }

                    }
                }
            }

            String sql=" from Esbgardengatetab esb";
            if(scenics!=null&&!scenics.equals("")){
                sql+=" where esb.id.iscenicid in ("+scenics+")";
            }
            List lst=this.find(sql);
            if(lst!=null&&lst.size()>0){
                for(int i=0;i<lst.size();i++){
                    Esbgardengatetab esbgard=(Esbgardengatetab) lst.get(i);

                    Long maxid=this.getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
                    Opcemployeecardsubtab t=new Opcemployeecardsubtab();
                    t.setIemployeecardsubid(maxid+1);
                    t.setIemployeecardid(opcemployeecardtab.getIemployeecardid());
                    t.setIgardengateid(esbgard.getId().getIgardengateid());
                    t.setDtlastcheckdate("");  //最后验票时间
                    t.setBylastcheckdir(new Long(0));      //'0':'进','1':'出'
                    t.setIpasstimes(new Long(0));    //可通过次数
                    t.setMsingletimes(new Long(1));    //单次消费次数
                    t.setIpassedtimes(new Long(0));    // 已通过次数
                    t.setMlimitconsume(new Double(0)); // 可消费金额
                    t.setMsingleconsume(new Double(0)); // 单次消费金额
                    t.setMconsumed(new Double(0));      // 已消费金额
                    t.setSzwicketsetinfo("");   // 检票设置信息
                    t.setDtmakedate(Tools.getNowString());
                    this.save(t);
                }
            }

        }



    }

    /**
     * 删除
     */
    public void delOpcemployeecard(Opcemployeecardtab opcemployeecardtab) {
        String sql=" from Opcemployeecardsubtab opc where opc.iemployeecardid="+opcemployeecardtab.getIemployeecardid();
        List list=this.find(sql);
        for(int x=0;x<list.size();x++){
            Opcemployeecardsubtab emp=(Opcemployeecardsubtab) list.get(x);
            this.delete(emp);
        }


        this.delete(opcemployeecardtab);

    }

    /**
     * 获取所有员工信息
     */
    public String getAllEmployee() {
        StringBuffer json = new StringBuffer();
        String hsql = "FROM Esfemployeetab";
        List list = this.find(hsql);

        json.append("[");
        for (int i = 0; i < list.size(); i++) {
            Esfemployeetab employee = (Esfemployeetab) list.get(i);

            json.append("{\"iemployeeid\":" + employee.getIemployeeid()
                    + ",\"empname\":" + employee.getSzemployeename() + "}");

            if (i != list.size() - 1) {
                json.append(",");
            }
        }

        return json.toString();
    }

    /**
     * 获得所有信息
     */
    public PaginationSupport getOpcemployeecard(int flag,String querydata,String iscenicids,String icompanyid,int pageSize, int startIndex,String url) {
    	
//        StringBuffer hsql = new StringBuffer("select new map("
//        		+ " empcard.iemployeecardid as iemployeecardid,empcard.iemployeeid as iemployeeid,empcard.itickettypeid as itickettypeid,empcard.iagentno as iagentno,empcard.icardno as icardno,"
//        		+ " empcard.szticketprintno as szticketprintno,empcard.iserialnum as iserialnum,empcard.byticketstate as byticketstate,empcard.mremainmoney as mremainmoney,empcard.mpresentmoney as mpresentmoney," 
//        		+ " empcard.ipresentnum as ipresentnum,empcard.iremainnum as iremainnum,empcard.byconsumetype as byconsumetype,empcard.iversion as iversion,"
//        		+ " emp.szemployeename as striemployeeid,"
//        		+ " company.szcompanyname as companyname"
//        		+ " )"
//        		+ " FROM Opcemployeecardtab empcard, Esfemployeetab emp, Galcompanyinfotab company "
//        		+ " where empcard.iemployeeid=emp.iemployeeid and emp.icompanyinfoid=company.icompanyinfoid and empcard.isa!=1");
        
        StringBuffer hsql = new StringBuffer("select new map("
        		+ " empcard.iemployeecardid as iemployeecardid,empcard.iemployeeid as iemployeeid,empcard.itickettypeid as itickettypeid,empcard.iagentno as iagentno,empcard.icardno as icardno,"
        		+ " empcard.szticketprintno as szticketprintno,empcard.iserialnum as iserialnum,empcard.byticketstate as byticketstate,empcard.mremainmoney as mremainmoney,empcard.mpresentmoney as mpresentmoney," 
        		+ " empcard.ipresentnum as ipresentnum,empcard.iremainnum as iremainnum,empcard.byconsumetype as byconsumetype,empcard.iversion as iversion"
        		+ " )"
        		+ " FROM Opcemployeecardtab empcard"
        		+ " where empcard.isa!=1");
        
        
        Long condition_iemployeeid=null;
        String condition_szemployeename=null;
        
        //判断是否是平台企业
//        Galcompanyinfotab company=(Galcompanyinfotab) get(Galcompanyinfotab.class, new Long(icompanyid));
//        if(company.getCompanytype().equals("02")){//景区企业
//            hsql.append(" and emp.icompanyinfoid="+company.getIcompanyinfoid()+" ");
//        }
        if(querydata!=null&&!querydata.equals("")){
            if(flag==0){
                //hsql.append(" and emp.iemployeeid="+querydata+"");
            	condition_iemployeeid = Long.valueOf(querydata);
            }else if(flag==1){
                //hsql.append(" and emp.szemployeename like '%"+querydata+"%'");
            	condition_szemployeename = querydata;
            }else{
                hsql.append(" and empcard.icardno like '%"+querydata.toUpperCase()+"%' ");
            }
        }
        
        
        List<Map> employeeList = sysService.getEmployeeListByCondition(condition_iemployeeid, Long.valueOf(icompanyid), condition_szemployeename);
        if(null != employeeList && employeeList.size()>0) {
        	
        	StringBuilder iemployeeidS = new StringBuilder();
        	
        	for (Map employee : employeeList) {
        		String iemployeeid = employee.get("iemployeeid").toString();
        		iemployeeidS.append(iemployeeid+ ",");
			}
        	iemployeeidS.deleteCharAt(iemployeeidS.length() - 1);
        	
//        	empcard.iemployeeid in()
        	hsql.append(" and empcard.iemployeeid in("+ iemployeeidS +")");
  
        	 PaginationSupport ps = this.findPage(hsql.toString(), pageSize, startIndex, url);
        	 
             List<Map> items = ps.getItems();
             if(null!=items && items.size()>0) {
             	for (Map item : items) {
             		String empcard_iemployeeid = item.get("iemployeeid").toString();
             		
             		for (Map employee : employeeList) {
                		String iemployeeid = employee.get("iemployeeid").toString();
                		if(empcard_iemployeeid.equals(iemployeeid)) {
                			String szemployeename = employee.get("szemployeename").toString();
                			String szcompanyname = employee.get("szcompanyname").toString();
                			
//                         	+ " emp.szemployeename as striemployeeid,"
//                     		+ " company.szcompanyname as companyname"
                			item.put("striemployeeid", szemployeename);
                			item.put("companyname", szcompanyname);
                			
                		}
        			}
     			}
             }
             
             return ps;
        }
        
        return null;
    }

    /**
     * 查询
     */
    public PaginationSupport searchOpcemployeecard(
            Opcemployeecardtab opcemployeecardtab, int pageSize,
            int startIndex, String url) {
        return null;
    }

    /**
     * 更新
     */
    public void updateOpcemlopyeecard(Opcemployeecardtab opcemployeecardtab) {
        Opcemployeecardtab opcard=(Opcemployeecardtab) this.get(Opcemployeecardtab.class, opcemployeecardtab.getIemployeecardid());
        //	opcard.setItickettypeid(opcemployeecardtab.getItickettypeid());
        opcard.setIcardno(opcemployeecardtab.getIcardno());
//		opcard.setIagentno("0");
//		opcard.setSzticketprintno(opcemployeecardtab.getSzticketprintno());
//		opcard.setIserialnum(opcemployeecardtab.getIserialnum());
        opcard.setByticketstate(opcemployeecardtab.getByticketstate());
        opcard.setSzmemo(opcemployeecardtab.getSzmemo());
        opcard.setIagentno(opcemployeecardtab.getIagentno());
        opcard.setIserialnum(opcemployeecardtab.getIserialnum());
        opcard.setIversion(opcemployeecardtab.getIversion());
        opcard.setByconsumetype(opcemployeecardtab.getByconsumetype());
        this.update(opcard);

        String sql=" from Opcemployeecardsubtab opc where opc.iemployeecardid="+opcemployeecardtab.getIemployeecardid();
        List list=this.find(sql);
        for(int x=0;x<list.size();x++){
            Opcemployeecardsubtab emp=(Opcemployeecardsubtab) list.get(x);
            this.delete(emp);
        }

        Esfemployeetab esfemployee=(Esfemployeetab) this.get(Esfemployeetab.class, opcemployeecardtab.getIemployeeid());
        String scenics="";
        if(esfemployee!=null){
            Galcompanyinfotab company=(Galcompanyinfotab)this.get(Galcompanyinfotab.class, esfemployee.getIcompanyinfoid());
            if(company!=null&&company.getCompanytype().equals("02")){
                String hsql=" from Companyscenic cs where cs.id.icompanyinfoid="+company.getIcompanyinfoid();
                List hsqlList=this.find(hsql);
                if(hsqlList!=null&&hsqlList.size()>0){
                    for(int x=0;x<hsqlList.size();x++){
                        Companyscenic pany=(Companyscenic) hsqlList.get(x);
                        scenics+=pany.getId().getIscenicid();
                        if(x!=hsqlList.size()-1){
                            scenics+=",";
                        }
                    }
                }
            }

            String hql=" from Esbgardengatetab esb";
            if(scenics!=null&&!scenics.equals("")){
                hql+=" where esb.id.iscenicid in ("+scenics+")";
            }
            List lst=this.find(hql);
            if(lst!=null&&lst.size()>0){
                for(int i=0;i<lst.size();i++){
                    Esbgardengatetab esbgard=(Esbgardengatetab) lst.get(i);

                    Long maxid=this.getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
                    Opcemployeecardsubtab t=new Opcemployeecardsubtab();
                    t.setIemployeecardsubid(maxid+1);
                    t.setIemployeecardid(opcemployeecardtab.getIemployeecardid());
                    t.setIgardengateid(esbgard.getId().getIgardengateid());
                    t.setDtlastcheckdate("");  //最后验票时间
                    t.setBylastcheckdir(new Long(0));      //'0':'进','1':'出'
                    t.setIpasstimes(new Long(0));    //可通过次数
                    t.setMsingletimes(new Long(1));    //单次消费次数
                    t.setIpassedtimes(new Long(0));    // 已通过次数
                    t.setMlimitconsume(new Double(0)); // 可消费金额
                    t.setMsingleconsume(new Double(0)); // 单次消费金额
                    t.setMconsumed(new Double(0));      // 已消费金额
                    t.setSzwicketsetinfo("");   // 检票设置信息
                    t.setDtmakedate(Tools.getNowString());
                    this.save(t);
                }
            }

        }

    }

    /**
     * 编号查询
     */
    public Opcemployeecardtab getPloyCardById(Long id) throws Exception {
        StringBuffer hsql = new StringBuffer("select new map(empcard.iemployeecardid as iemployeecardid,empcard.iemployeeid as iemployeeid,empcard.itickettypeid as itickettypeid,empcard.iagentno as iagentno,empcard.icardno as icardno," +
                "empcard.szticketprintno as szticketprintno,empcard.iserialnum as iserialnum,empcard.byticketstate as byticketstate,empcard.mremainmoney as mremainmoney,empcard.mpresentmoney as mpresentmoney," +
                "empcard.ipresentnum as ipresentnum,empcard.iremainnum as iremainnum,empcard.byconsumetype as byconsumetype,empcard.iversion as iversion,emp.szemployeename as striemployeeid,company.szcompanyname as companyname) " +
                "FROM Opcemployeecardtab empcard,Esfemployeetab emp,Galcompanyinfotab company where empcard.iemployeeid=emp.iemployeeid " +
                " and emp.icompanyinfoid=company.icompanyinfoid and empcard.iemployeecardid="+id);
        List list = super.find(hsql.toString());
        if (list == null || list.size() < 1) {
            return null;
        } else {
            Opcemployeecardtab ts=new Opcemployeecardtab();
            BeanUtils.populate(ts, (Map) list.get(0));
            return ts;
        }
    }

    /**
     *
     * Describe:根据所管辖的服务商获取可使用的员工卡产品
     * @auth:yangguang
     * @param iscenicids
     * @return
     * return:List
     * Date:2011-10-14
     */
    public List getAllTicketType(String iscenicids) {
        String hql = "select new map(product.itickettypeid as itickettypeid,product.sztickettypename as sztickettypename) from Esbscenicareatab provider,Edmtickettypetab product,Sysparv5 syspar where provider.iscenicid=product.iscenicid and  product.bycategorytype=syspar.id.pmcd and syspar.id.pmky='PRTP' and syspar.id.pmcd='0013' and product.iscenicid="+iscenicids+"";
        return this.find(hql);
    }

    /**
     * *
     * Describe:根据所管辖的服务商获取可使用的员工卡产品
     * @see com.ectrip.system.salesmanager.dao.idao.IOpcemployeecardtabDAO#getListticketlookup(java.lang.String)
     * @param iscenicids
     * @return
     * @author lijingrui
     * Date:Nov 14, 2011
     * @throws Exception
     */
    public String getListticketlookup(String iscenicids) throws Exception{
        StringBuffer json = new StringBuffer();
        //String hql = "select new map(product.itickettypeid as itickettypeid,product.sztickettypename as sztickettypename) from Esbscenicareatab provider,Edmtickettypetab product,Sysparv5 syspar where provider.iscenicid=product.iscenicid and  product.bycategorytype=syspar.id.pmcd and syspar.id.pmky='PRTP' and syspar.id.pmcd='0013' and product.iscenicid="+iscenicids+"";
        String hql = "select new map(product.itickettypeid as itickettypeid,product.sztickettypename as sztickettypename) from Esbscenicareatab provider,Edmtickettypetab product,Sysparv5 syspar where provider.iscenicid=product.iscenicid and  product.bycategorytype=syspar.id.pmcd and syspar.id.pmky='PRTP' and syspar.id.pmcd='0013' and product.iscenicid="+iscenicids+"";
        List list = this.find(hql);
        json.append("[");
        for (int i = 0; i < list.size(); i++) {
            Edmtickettypetab e=new Edmtickettypetab();
            BeanUtils.populate(e, (Map) list.get(i));
            json.append("{\"itickettypeid\":\"" + e.getItickettypeid()+ "\",\"sztickettypename\":\""
                    +e.getSztickettypename()+ "\"}");
            if (i != list.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }

    /**
     * 获取当前最大ID
     */
    public Long getMaxId() {
        String hsql = "SELECT max(o.iemployeecardid) FROM Opcemployeecardtab o";
        List list = this.find(hsql);
        Long id = new Long(0);
        if (list.get(0) != null) {
            id = Long.parseLong(list.get(0).toString());
        }
        return id + 1;
    }

    /**
     * 查询是否存在这个员工
     */
    public List getPloyee(Long id) {
        String hsql = "FROM Esfemployeetab e WHERE e.iemployeeid=" + id;
        return this.find(hsql);
    }



    /**
     *
     * Describe:根据员工获取此员工可管理的服务商编号
     * @auth:yangguang
     * @param iemployeeid
     * @return
     * return:String
     * Date:2011-10-17
     */
    public String getControlProvider(String iemployeeid){
        Esfemployeetab  employee=(Esfemployeetab) get(Esfemployeetab.class, new Long(iemployeeid));
        //	List comlist = find(" from Companyscenic csc,Esbscenicareatab provider where csc.id.iscenicid=provider.iscenicid and  provider.byisuse=1 and csc.id.icompanyinfoid=" +employee.getIcompanyinfoid());
        List comlist = find(" from Esbscenicareatab provider where provider.byisuse=1 and provider.iscenicid in (select csc.id.iscenicid from Companyscenic csc where csc.id.icompanyinfoid=" +employee.getIcompanyinfoid()+")");
//		String scenics = "";
//			for (int i = 0; i < comlist.size(); i++) {
//				Companyscenic c = (Companyscenic) comlist.get(i);
//				if (i == 0) {
//					scenics = c.getId().getIscenicid().toString();
//				} else {
//					scenics = scenics + "," + c.getId().getIscenicid().toString();
//				}
//			}
        String scenics = "";
        for (int i = 0; i < comlist.size(); i++) {
            Esbscenicareatab c = (Esbscenicareatab) comlist.get(i);
            if (i == 0) {
                scenics = c.getIscenicid().toString();
            } else {
                scenics = scenics + "," + c.getIscenicid().toString();
            }
        }

        employee.setScenics(scenics);
        return employee.getScenics();
    }

    /**
     * *
     * Describe:判断某个员工是否添加了员工卡证
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#shoplookemployee(java.lang.Long)
     * @param iemployeeid
     * @return
     * @author lijingrui
     * Date:Nov 30, 2011
     */
    public boolean shoplookemployee(Long iemployeeid) {
        boolean b=false;
        String sql=" from Opcemployeecardtab card where card.iemployeeid="+iemployeeid;
        List lst=this.find(sql);
        if(lst!=null&&lst.size()>0){
            b=true;
        }else{
            b=false;
        }
        return b;
    }

    /**
     *
     * Describe:获取所有员工卡证信息
     * @author:lijingrui
     * @return
     * return:List
     * Date:2015-2-11
     */
    public List checkOpcemployee(){
        String hsql="select new map(op.iemployeecardid as iemployeecardid,esf.iemployeeid as iemployeeid,esf.szemployeename as szemployeename) from Opcemployeefingerprinttab opc,Opcemployeecardtab op,Esfemployeetab esf where esf.iemployeeid=op.iemployeeid and op.iemployeecardid=opc.iemployeecardid and op.isa!=1 ";
        return this.find(hsql);
    }

    /**
     *
     * Describe:删除员工卡证的指纹信息
     * @author:lijingrui
     * @param iemployeecardid
     * return:void
     * Date:2015-2-11
     */
    public void delOpcemployeePrint(Long iemployeecardid){
        String hsql=" from Opcemployeefingerprinttab where iemployeecardid="+iemployeecardid;
        List lst=this.find(hsql);
        if(lst!=null&&lst.size()>0){
            for(int i=0;i<lst.size();i++){
                Opcemployeefingerprinttab opc=(Opcemployeefingerprinttab)lst.get(i);

                this.delete(opc);
            }
        }
    }

    /**
     * *
     * Describe:删除导游验证指纹
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#delStssoldPrint(java.lang.String)
     * @param myzj
     * @author lijingrui
     * Date:2015-3-13
     */
    public void delStssoldPrint(String myzj){
        String hsql=" from Stssoldfingerprint where zjhm='"+myzj+"' ";
        List lst=this.find(hsql);
        if(lst!=null&&lst.size()>0){
            for(int i=0;i<lst.size();i++){
                Stssoldfingerprint opc=(Stssoldfingerprint)lst.get(i);

                this.delete(opc);
            }
        }

    }

    /**
     * *
     * Describe:查询VIP卡信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#checkListEmployeeCard(java.lang.String, java.lang.String, int, int, java.lang.String)
     * @param flag
     * @param queryString
     * @param pageSize
     * @param startIndex
     * @param url
     * @return
     * @author lijingrui
     * Date:2015-11-21
     */
    public PaginationSupport checkListEmployeeCard(String flag,String queryString,int pageSize,int startIndex, String url){
        StringBuffer hsql=new StringBuffer(" select new map(opc.iemployeecardid as iemployeecardid,opc.iemployeeid as iemployeeid,opc.iagentno as iagentno,substr(opc.icardno,4) as icardno,opc.szticketprintno as szticketprintno,opc.iserialnum as iserialnum,opc.szmemo as szmemo,opc.byticketstate as byticketstate,opc.byconsumetype as byconsumetype,opc.iversion as iversion,opc.usid as usid,opc.byisdaoyou as byisdaoyou,opc.szemployname as szemployname,opc.dtstartdate as dtstartdate,opc.dtenddate as dtenddate,opc.emptype as emptype,opc.jpnumbs as jpnumbs,opc.yjnumbs as yjnumbs,opc.memoyy as memoyy,sys1.pmva as striemployeeid) from Opcemployeecardtab opc,Sysparv5 sys1 where opc.emptype=sys1.id.pmcd and sys1.id.pmky='YHLX' and opc.isa=1");
        if(queryString!=null&&!queryString.equals("")){
            if(flag.equals("0")){
                hsql.append(" and opc.szemployname like '%"+queryString+"%'");
            }else if(flag.equals("1")){
                hsql.append(" and opc.icardno like '%"+queryString.toUpperCase()+"%' ");
            }
        }
        return this.findPage(hsql.toString(), pageSize, startIndex, url);
    }

    /**
     * *
     * Describe:新增VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#insertVipCardemp(com.ectrip.model.salesmanager.Opcemployeecardtab, com.ectrip.model.syspar.Syslog)
     * @param opcemployeecardtab
     * @param syslog
     * @author lijingrui
     * Date:2015-11-21
     */
    public void insertVipCardemp(Opcemployeecardtab opcemployeecardtab,Syslog syslog){
        Long maxids=this.getMaxPk("iemployeecardid", "Opcemployeecardtab");
        opcemployeecardtab.setIemployeecardid(maxids+1L);

        opcemployeecardtab.setItickettypeid(new Long(0));
        opcemployeecardtab.setSzticketprintno("0");
        opcemployeecardtab.setByisdaoyou(new Long(0));
        opcemployeecardtab.setMremainmoney(new Double(0));
        opcemployeecardtab.setMpresentmoney(new Double(0));
        opcemployeecardtab.setIpresentnum(new Long(0));
        opcemployeecardtab.setIremainnum(new Long(0));
        opcemployeecardtab.setIagentno("0");
        opcemployeecardtab.setIserialnum("0");
        opcemployeecardtab.setIemployeeid(1L);
        opcemployeecardtab.setByconsumetype("1");//入园卡
        opcemployeecardtab.setIsa(1L);
        opcemployeecardtab.setYjnumbs(0L);
        opcemployeecardtab.setIcardno("VIP"+opcemployeecardtab.getIcardno().trim());
        this.save(opcemployeecardtab);

        String sql=" from Esbgardengatetab esb where byisuse=1";

        List lst=this.find(sql);
        if(lst!=null&&lst.size()>0){
            for(int i=0;i<lst.size();i++){
                Esbgardengatetab esbgard=(Esbgardengatetab) lst.get(i);

                Long maxid=this.getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
                Opcemployeecardsubtab t=new Opcemployeecardsubtab();
                t.setIemployeecardsubid(maxid+1);
                t.setIemployeecardid(opcemployeecardtab.getIemployeecardid());
                t.setIgardengateid(esbgard.getId().getIgardengateid());
                t.setDtlastcheckdate("");  //最后验票时间
                t.setBylastcheckdir(new Long(0));      //'0':'进','1':'出'
                t.setIpasstimes(new Long(0));    //可通过次数
                t.setMsingletimes(new Long(1));    //单次消费次数
                t.setIpassedtimes(new Long(0));    // 已通过次数
                t.setMlimitconsume(new Double(0)); // 可消费金额
                t.setMsingleconsume(new Double(0)); // 单次消费金额
                t.setMconsumed(new Double(0));      // 已消费金额
                t.setSzwicketsetinfo("");   // 检票设置信息
                t.setDtmakedate(Tools.getNowString());
                this.save(t);
            }
        }

        syslog.setStlg("1041");
        syslog.setBrief("VIP卡证设置：" + opcemployeecardtab.getIemployeecardid());
        syslog.setNote("添加VIP卡证设置：" + opcemployeecardtab.getIemployeecardid()+"   "+opcemployeecardtab.getSzemployname()+"  "+opcemployeecardtab.getIcardno());
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);

    }

    /**
     * *
     * Describe:修改VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#editVipCardemp(com.ectrip.model.salesmanager.Opcemployeecardtab, com.ectrip.model.syspar.Syslog)
     * @param opcemployeecardtab
     * @param syslog
     * @author lijingrui
     * Date:2015-11-21
     */
    public void editVipCardemp(Opcemployeecardtab opcemployeecardtab,Syslog syslog){
        opcemployeecardtab.setIcardno("VIP"+opcemployeecardtab.getIcardno().trim());
        this.update(opcemployeecardtab);

        String sql=" from Opcemployeecardsubtab opc where opc.iemployeecardid="+opcemployeecardtab.getIemployeecardid();
        List list=this.find(sql);
        for(int x=0;x<list.size();x++){
            Opcemployeecardsubtab emp=(Opcemployeecardsubtab) list.get(x);
            this.delete(emp);
        }

        String hsql=" from Esbgardengatetab esb where byisuse=1";
        List lst=this.find(hsql);
        if(lst!=null&&lst.size()>0){
            for(int i=0;i<lst.size();i++){
                Esbgardengatetab esbgard=(Esbgardengatetab) lst.get(i);

                Long maxid=this.getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
                Opcemployeecardsubtab t=new Opcemployeecardsubtab();
                t.setIemployeecardsubid(maxid+1);
                t.setIemployeecardid(opcemployeecardtab.getIemployeecardid());
                t.setIgardengateid(esbgard.getId().getIgardengateid());
                t.setDtlastcheckdate("");  //最后验票时间
                t.setBylastcheckdir(new Long(0));      //'0':'进','1':'出'
                t.setIpasstimes(new Long(0));    //可通过次数
                t.setMsingletimes(new Long(1));    //单次消费次数
                t.setIpassedtimes(new Long(0));    // 已通过次数
                t.setMlimitconsume(new Double(0)); // 可消费金额
                t.setMsingleconsume(new Double(0)); // 单次消费金额
                t.setMconsumed(new Double(0));      // 已消费金额
                t.setSzwicketsetinfo("");   // 检票设置信息
                t.setDtmakedate(Tools.getNowString());
                this.save(t);
            }
        }

        syslog.setStlg("1042");
        syslog.setBrief("VIP卡证设置：" + opcemployeecardtab.getIemployeecardid());
        syslog.setNote("修改VIP卡证设置：" + opcemployeecardtab.getIemployeecardid()+"   "+opcemployeecardtab.getSzemployname()+"  "+opcemployeecardtab.getIcardno());
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);
    }

    /**
     * *
     * Describe:删除VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#delVipCardemp(java.lang.Long, com.ectrip.model.syspar.Syslog)
     * @param iemployeecardid
     * @param syslog
     * @author lijingrui
     * Date:2015-11-21
     */
    public void delVipCardemp(Long iemployeecardid,Syslog syslog){
        String sql=" from Opcemployeecardsubtab opc where opc.iemployeecardid="+iemployeecardid;
        List list=this.find(sql);
        for(int x=0;x<list.size();x++){
            Opcemployeecardsubtab emp=(Opcemployeecardsubtab) list.get(x);
            this.delete(emp);
        }

        Opcemployeecardtab opcemp=(Opcemployeecardtab)this.get(Opcemployeecardtab.class, iemployeecardid);

        syslog.setStlg("1043");
        syslog.setBrief("VIP卡证设置：" + opcemp.getIemployeecardid());
        syslog.setNote("删除VIP卡证设置：" + opcemp.getIemployeecardid()+"   "+opcemp.getSzemployname()+"  "+opcemp.getIcardno());
        syslog.setLogdatetime(Tools.getDayTimes());
        Long logid = getMaxPk("logid", "Syslog");
        syslog.setLogid(logid + 1);
        this.save(syslog);

        this.delete(opcemp);
    }

    /**
     * *
     * Describe:查看VIP信息
     * @see com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService#viewVipCardemp(java.lang.Long)
     * @param iemployeecardid
     * @return
     * @throws Exception
     * @author lijingrui
     * Date:2015-11-21
     */
    public Opcemployeecardtab viewVipCardemp(Long iemployeecardid) throws Exception{
        StringBuffer hsql=new StringBuffer(" select new map(opc.iemployeecardid as iemployeecardid,opc.iemployeeid as iemployeeid,opc.iagentno as iagentno,substr(opc.icardno,4) as icardno,opc.szticketprintno as szticketprintno,opc.iserialnum as iserialnum,opc.szmemo as szmemo,opc.byticketstate as byticketstate,opc.byconsumetype as byconsumetype,opc.iversion as iversion,opc.usid as usid,opc.byisdaoyou as byisdaoyou,opc.szemployname as szemployname,opc.dtstartdate as dtstartdate,opc.dtenddate as dtenddate,opc.emptype as emptype,opc.jpnumbs as jpnumbs,opc.yjnumbs as yjnumbs,opc.memoyy as memoyy,sys1.pmva as striemployeeid) from Opcemployeecardtab opc,Sysparv5 sys1 where opc.emptype=sys1.id.pmcd and sys1.id.pmky='YHLX' and opc.isa=1");
        hsql.append(" and opc.iemployeecardid="+iemployeecardid);

        List list = super.find(hsql.toString());
        if (list == null || list.size() < 1) {
            return null;
        } else {
            Opcemployeecardtab ts=new Opcemployeecardtab();
            BeanUtils.populate(ts, (Map) list.get(0));
            return ts;
        }

    }

}
