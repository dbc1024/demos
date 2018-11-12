package com.ectrip.ec.user.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.model.user.Customvip;
import com.ectrip.ec.model.user.Daoyou;
import com.ectrip.ec.model.user.DaoyouId;
import com.ectrip.ec.user.dao.idao.ICustomInfoDAO;
import com.ectrip.hqyt.client.HqytClient;
import com.ectrip.hqyt.model.request.MerchantRequest;
import com.ectrip.hqyt.model.response.JSONMerchant;
import com.ectrip.hqyt.model.response.JSONProviderCompany;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.model.provider.ProviderCompany;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardtab;
import com.ectrip.ticket.model.salesmanager.Opcemployeefingerprinttab;

/**
 * 用户信息
 * 
 * @author huangyuqi
 */
@Repository
public class CustomInfoDAO extends GenericDao implements ICustomInfoDAO {

	/**
	 * 根据编号查询用户信息
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Custom getCustionById(String usid) throws Exception {
		Custom custom = (Custom) this.get(Custom.class, usid);
		if (custom != null) {
			Long regionid = custom.getIregionalid();
			//客源地可能为空
			if (regionid==null) {
				regionid = 1L;
			}
			Galsourceregiontab source = (Galsourceregiontab) this.get(
					Galsourceregiontab.class, regionid);
			if (source != null) {
				custom.setSzregionalname(source.getSzinnername());
			}

			Edmbusinesstab business = (Edmbusinesstab) this.get(Edmbusinesstab.class,
					custom.getIbusinessid());
			if (business != null) {
				custom.setSzbusinessname(business.getSzbusinessname());
			}
			//从旅游卡明细中查询用户身份证号
			String sql = "select idname,idnumber from userbank where userId = ? group by idname,idnumber";
			List<Map> findBySqlToMap = this.findBySqlToMap(sql, new Object[] {usid});
			if(findBySqlToMap != null && findBySqlToMap.size() > 0 ) {
				//一个用户只会有一个身份证号
				Map map = findBySqlToMap.get(0);
				String identityNum = map.get("IDNUMBER").toString();
				String userName = map.get("IDNAME").toString();
				//如果联系人为空，则将旅游卡详细中的用户姓名显示为联系人
				if(custom.getLname() == null) {
					custom.setLname(userName);
				}
				//将身份证号，设置入用户实体中
				custom.setNote10(identityNum);
			}
		}
		return custom;
	}

	/**
	 * 获取所有用户信息
	 */
	public PaginationSupport getCustomViewList(String lgtp, String ttlb,
			int pageSize, int startIndex, String url) {
		PaginationSupport ps = null;
		String hsql = "";
		if ("01".equals(lgtp)) {
			hsql = "select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from  Custom c where c.lgtp = '"
					+ lgtp + "' order by bname,status, ldate desc, c.usid ";
		}
		if ("02".equals(lgtp)) {
			hsql = " select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from  Custom c where c.lgtp = '"
					+ lgtp + "' and ttlb='" + ttlb + "' and ustp='01' order by bname, status, ldate desc, c.usid  ";
		}
		ps = this.findPage(hsql, pageSize, startIndex, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if ("01".equals(ttlb) && "02".equals(lgtp)) {// 旅行社
					if (map.get("usid") != null) {// 用户
						String usid = map.get("usid").toString();
						String hsqls = " from Custom where susid='" + usid + "'";
						List lists = this.find(hsqls);
						map.put("susidnum", lists.size());
					}
				}
			}
		}
		return ps;
	}

	/**
	 * 获取子用户列表 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid主用户名
	 * @param pageSize
	 * @param startIndex
	 * @param path
	 * @return return:PaginationSupport Date:2011-11-25
	 */
	public PaginationSupport getSonUserList(String usid, int pageSize,
			int startIndex, String path) {
		PaginationSupport ps = null;
		String hsql = "";
		hsql = " select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname) from  Custom c where c.lgtp = '02' and c.ttlb='01' and c.susid='"
				+ usid + "' order by bname, status, ldate desc,c.usid  ";
		ps = this.findPage(hsql, pageSize, startIndex, path);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("usid") != null) {// 用户
					String susid = map.get("usid").toString();
					String hsqls = " from Custom where susid='" + susid + "'";
					List lists = this.find(hsqls);
					map.put("susidnum", lists.size());

					ProviderCompany pc = findProviderCompanyByUisd(susid);
					if(pc != null){
						map.put("pc",pc.getStatus());
					}else{
						map.put("pc",0);
					}
				}
			}

		}
		return ps;
	}

	/**
	 * 获取所有用户信息
	 */
	public PaginationSupport getCustomViewList(String lgtp, int pageSize,
			int startIndex, String url) {
		String hsql = "";
		hsql = " select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname) FROM Custom c WHERE c.lgtp = '"
				+ lgtp + "' order by bname,status, ldate desc, c.usid ";

		return this.findPage(hsql, pageSize, startIndex, url);
	}

	/**
	 * 设置审核用户
	 */
	public void updateCustomInfo(Custom custom, Syslog syslog) {
		this.update(custom);// 更改用户信息

		if(custom.getIbusinessid()!=3){
			if (custom.getStatus().equals("01")) {
				if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")||(custom.getLgtp().equals("02") && custom.getTtlb().equals("03"))) {
					String hql = " from Opcemployeecardtab where usid='" + custom.getUsid()
							+ "'";
					List list = this.find(hql);
					//如果已经存在员工卡证信息 那么将员工卡证信息修改下 
					//判断员工卡证子表是否存在数据，如果存在 就删除掉 重新读取圆门信息添加
					if (list != null && list.size() > 0) {
						Opcemployeecardtab opcate = (Opcemployeecardtab) list.get(0);
						if (!opcate.getIcardno().equals(custom.getZjhm())) {
							opcate.setIcardno(custom.getZjhm());
							this.update(opcate); 
						}
						String hsql = " from Opcemployeecardsubtab where iemployeecardid="
							+ opcate.getIemployeecardid();
						List est = this.find(hsql);
						if (est != null && est.size() > 0) {
							for (int y = 0; y < est.size(); y++) {
								Opcemployeecardsubtab subemp = (Opcemployeecardsubtab) est.get(y);
								this.delete(subemp);
							}
						}
						
						String sql = " from Esbgardengatetab e where e.byisuse=1";
						List lst = this.find(sql);
						for (int i = 0; i < lst.size(); i++) {
							Esbgardengatetab esb = (Esbgardengatetab) lst.get(i);
							Opcemployeecardsubtab opc = new Opcemployeecardsubtab();
							Long carid = getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
							opc.setIemployeecardsubid(carid + 1);
							opc.setIemployeecardid(opcate.getIemployeecardid());
							opc.setIgardengateid(esb.getId().getIgardengateid());
							opc.setIpasstimes(new Long(0));// 可通过次数
							opc.setMsingletimes(new Long(1));// 单次消费次数
							opc.setIpassedtimes(new Long(0));// 已通过次数
							opc.setMlimitconsume(new Double(0));// 可消费金额
							opc.setMsingleconsume(new Double(0));// 单次消费金额
							opc.setMconsumed(new Double(0));// 已消费金额
							opc.setDtmakedate(Tools.getNowString());
							this.save(opc);
						}
						
					} else{
						//如果没有员工卡证信息，那么就添加
						Opcemployeecardtab op = new Opcemployeecardtab();
						Long maxid = getMaxPk("iemployeecardid", "Opcemployeecardtab");
						op.setIemployeecardid(maxid + 1);
						op.setIemployeeid(new Long(0));// 员工表中的虚拟导游
						op.setItickettypeid(new Long(0)); // 产品表中的员工卡证
						op.setIagentno("0");
						op.setIcardno(custom.getZjhm());
						op.setSzticketprintno("0");
						op.setIserialnum("0");
						op.setByticketstate("0");
						op.setMremainmoney(new Double(0));
						op.setMpresentmoney(new Double(0));
						op.setIpresentnum(new Long(0));
						op.setIremainnum(new Long(0));
						op.setByconsumetype("00");
						op.setUsid(custom.getUsid());
						if(custom.getTtlb().equals("02")){
							op.setByisdaoyou(new Long(1)); //是否导游
						}
						if(custom.getTtlb().equals("03")){  //VIP用户
							op.setByisdaoyou(new Long(2));
						}
						
						this.save(op);
						
						String sql = " from Esbgardengatetab e where e.byisuse=1";
						List lst = this.find(sql);
						for (int i = 0; i < lst.size(); i++) {
							Esbgardengatetab esb = (Esbgardengatetab) lst.get(i);
							Opcemployeecardsubtab opc = new Opcemployeecardsubtab();
							Long carid = getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
							opc.setIemployeecardsubid(carid + 1);
							opc.setIemployeecardid(op.getIemployeecardid());
							opc.setIgardengateid(esb.getId().getIgardengateid());
							opc.setIpasstimes(new Long(0));// 可通过次数
							opc.setMsingletimes(new Long(1));// 单次消费次数
							opc.setIpassedtimes(new Long(0));// 已通过次数
							opc.setMlimitconsume(new Double(0));// 可消费金额
							opc.setMsingleconsume(new Double(0));// 单次消费金额
							opc.setMconsumed(new Double(0));// 已消费金额
							opc.setDtmakedate(Tools.getNowString());
							this.save(opc);
						}
					}
						
						
					//如果是ＶＩＰ，那么需要在导游表中添加一条数据
					if(custom.getLgtp().equals("02") && custom.getTtlb().equals("03")){
						String heql=" from Daoyou where id.usid='"+custom.getUsid()+"'";
						List daolist=this.find(heql);
						if(daolist.size()<1){
							Daoyou dy=new Daoyou();
							DaoyouId dyid=new DaoyouId();
							dyid.setUsid(custom.getUsid());
							dyid.setDyusid(custom.getUsid());
							dy.setId(dyid);
							dy.setLname(custom.getLname());
							dy.setIdcard(custom.getZjhm());
							dy.setTourcard("");//导游证
							dy.setMobile(custom.getMobile());
							dy.setTelno(custom.getTelno());
							
							this.save(dy);
						}
					}
					
				}
			} else {
				if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")||(custom.getLgtp().equals("02") && custom.getTtlb().equals("03"))) {
					String hql = " from Opcemployeecardtab where usid='" + custom.getUsid()
					+ "'";
					List list = this.find(hql);
					if (list != null && list.size() > 0) {
						for (int x = 0; x < list.size(); x++) {
							Opcemployeecardtab opcemp = (Opcemployeecardtab) list.get(x);
							String hsql = " from Opcemployeecardsubtab where iemployeecardid="
								+ opcemp.getIemployeecardid();
							List est = this.find(hsql);
							if (est != null && est.size() > 0) {
								for (int y = 0; y < est.size(); y++) {
									Opcemployeecardsubtab subemp = (Opcemployeecardsubtab) est.get(y);
									this.delete(subemp);
								}
							}
							
							this.delete(opcemp);
						}
					}
					//如果是ＶＩＰ
					if(custom.getLgtp().equals("02") && custom.getTtlb().equals("03")){
						String heql2=" from Daoyou where id.usid='"+custom.getUsid()+"'";
						List cslist=this.find(heql2);
						if(cslist!=null&&cslist.size()>0){
							Daoyou dao=(Daoyou) cslist.get(0);
							this.delete(dao);
						}
					}
					
				}
			}
		}
		
		if(custom.getLgtp().equals("02")&&custom.getTtlb().equals("01")){
//			Custom zstom=(Custom)this.get(Custom.class, custom.getUsid());
//			if(!custom.getNote2().equals(zstom.getNote2())){}
//			

			String ssql=" from Custom where susid='"+custom.getUsid()+"'";
			List lst=this.find(ssql);
			if(lst!=null&&lst.size()>0){
				for(int x=0;x<lst.size();x++){
					Custom cusfs=(Custom)lst.get(x);
					cusfs.setNote2(custom.getNote2());
					this.update(cusfs);
					
					String csql=" from Custom where susid='"+cusfs.getUsid()+"'";
					List czylist=this.find(csql);
					if(czylist!=null&&czylist.size()>0){
						for(int y=0;y<czylist.size();y++){
							Custom czytom=(Custom)czylist.get(y);
							czytom.setNote2(custom.getNote2());
							this.update(czytom);
						}
					}
				}
			}
		
		}
		
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);

		this.save(syslog);

	}
	
	/**
	 * *
	 * Describe:初始化密码和初始化错误登录次数
	 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#updateCustom(com.ectrip.model.user.Custom, com.ectrip.model.syspar.Syslog)
	 * @param custom
	 * @param syslog
	 * @author lijingrui
	 * Date:Apr 12, 2012
	 */
	public void updateCustom(Custom custom,Syslog syslog){
		this.update(custom);// 更改用户信息
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}
	
	/**
	 * 查询用户列表
	 * 
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @param radiobutton查询方式
	 * @param custom用户类
	 * @return
	 */
	public PaginationSupport searchCustom(int pageSize, int startIndex,
			String url, int radiobutoon, Custom custom) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if ("01".equals(custom.getLgtp())) {
			hsql
					.append(" select  new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from Custom c  where c.lgtp='"
							+ custom.getLgtp() + "' ");
		} else {
			if (custom.getTtlb() == null || "".equals(custom.getTtlb())) {
				custom.setTtlb("01");
			}else{
				hsql.append(" select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from Custom c  where c.lgtp='"
						+ custom.getLgtp()
						+ "' and c.ttlb='"
						+ custom.getTtlb()
						+ "' and  ustp='01'  ");
			}
			
		}
		if(custom.getIbusinessid()>0){
		hsql.append(" and c.ibusinessid=" + custom.getIbusinessid());
		}
		if (radiobutoon == 0) {// 按用户编号查询
			hsql.append(" and c.usid='" + custom.getUsid() + "' ");
		}
		if (radiobutoon == 1) {// 按联系人查询
			hsql.append(" and c.lname like '%" + custom.getLname() + "%' ");
		}
		if (radiobutoon == 2) {// 按公司名称查询
			hsql.append(" and c.corpname like '%" + custom.getCorpname() + "%' ");
		}
		if(radiobutoon == 5){
			System.out.println(custom.getEmail()==null);
			try {
				if(Tools.isEmail(custom.getEmail())){
					hsql.append(" and c.email = '"+custom.getEmail()+"' ");
				}else{
					hsql.append(" and c.mobile = '"+custom.getEmail()+"' ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (custom.getStatus() == null || "".equals(custom.getStatus())) {
			hsql.append("  order by c.ldate desc,c.usid ");
		} else {
			hsql
					.append(" and c.status='" + custom.getStatus() + "' order by  c.bname, c.status, c.ldate desc, c.usid ");
		}
		ps = this.findPage(hsql.toString(), pageSize, startIndex, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("usid") != null) {// 用户
					String susid = map.get("usid").toString();
					String hsqls = " from Custom where susid='" + susid + "'";
					List lists = this.find(hsqls);
					map.put("susidnum", lists.size());
				}
			}

		}

		return ps;
	}

	/**
	 * 增加用户信息 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 *          return:void Date:2011-10-24
	 */
	public void addCustomInfo(Custom custom, Syslog syslog) {
		if(custom.getLgtp().equals("02")) {
            if (! "99".equals(custom.getTtlb()) ) {
                Custom scus=(Custom) this.get(Custom.class, custom.getSusid());
                if(scus!=null){
                    custom.setNote2(scus.getNote2());
                }else{
                	custom.setNote2("0000");
                }
            }
		}else{
			custom.setNote2("0000");
		}
		this.save(custom);
		
		if(custom.getIbusinessid()!=3){
			if (custom.getStatus().equals("01")) {
				if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")||custom.getLgtp().equals("02") && custom.getTtlb().equals("03")) {
					String hql = " from Opcemployeecardtab where usid='" + custom.getUsid()
					+ "'";
					List list = this.find(hql);
					if (list != null && list.size() > 0) {
						for (int x = 0; x < list.size(); x++) {
							Opcemployeecardtab opcemp = (Opcemployeecardtab) list.get(x);
							String hsql = " from Opcemployeecardsubtab where iemployeecardid="
								+ opcemp.getIemployeecardid();
							List est = this.find(hsql);
							if (est != null && est.size() > 0) {
								for (int y = 0; y < est.size(); y++) {
									Opcemployeecardsubtab subemp = (Opcemployeecardsubtab) est.get(y);
									this.delete(subemp);
								}
							}
							this.delete(opcemp);
						}
					}
						Opcemployeecardtab op = new Opcemployeecardtab();
						Long maxid = getMaxPk("iemployeecardid", "Opcemployeecardtab");
						op.setIemployeecardid(maxid + 1);
						op.setIemployeeid(new Long(0));// 员工表中的虚拟导游
						op.setItickettypeid(new Long(0)); // 产品表中的员工卡证
						op.setIagentno("0");
						op.setIcardno(custom.getZjhm());
						op.setSzticketprintno("0");
						op.setIserialnum("0");
						op.setByticketstate("0");
						op.setMremainmoney(new Double(0));
						op.setMpresentmoney(new Double(0));
						op.setIpresentnum(new Long(0));
						op.setIremainnum(new Long(0));
						op.setByconsumetype("00");
						op.setUsid(custom.getUsid());
						if(custom.getTtlb().equals("02")){
							op.setByisdaoyou(new Long(1)); //是否导游
						}
						if(custom.getTtlb().equals("03")){  //VIP用户
							op.setByisdaoyou(new Long(2));
						}
						
						this.save(op);
						String sql = " from Esbgardengatetab e where e.byisuse=1";
						List lst = this.find(sql);
						for (int i = 0; i < lst.size(); i++) {
							Esbgardengatetab esb = (Esbgardengatetab) lst.get(i);
							Opcemployeecardsubtab opc = new Opcemployeecardsubtab();
							Long carid = getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
							opc.setIemployeecardsubid(carid + 1);
							opc.setIemployeecardid(op.getIemployeecardid());
							opc.setIgardengateid(esb.getId().getIgardengateid());
							opc.setIpasstimes(new Long(0));// 可通过次数
							opc.setMsingletimes(new Long(1));// 单次消费次数
							opc.setIpassedtimes(new Long(0));// 已通过次数
							opc.setMlimitconsume(new Double(0));// 可消费金额
							opc.setMsingleconsume(new Double(0));// 单次消费金额
							opc.setMconsumed(new Double(0));// 已消费金额
							opc.setDtmakedate(Tools.getNowString());
							this.save(opc);
						}
						
						//如果是ＶＩＰ，那么需要在导游表中添加一条数据
						if(custom.getLgtp().equals("02") && custom.getTtlb().equals("03")){
							String heql=" from Daoyou where id.usid='"+custom.getUsid()+"'";
							List daolist=this.find(heql);
							if(daolist.size()<1){
								Daoyou dy=new Daoyou();
								DaoyouId dyid=new DaoyouId();
								dyid.setUsid(custom.getUsid());
								dyid.setDyusid(custom.getUsid());
								dy.setId(dyid);
								dy.setLname(custom.getLname());
								dy.setIdcard(custom.getZjhm());
								dy.setTourcard("");//导游证
								dy.setMobile(custom.getMobile());
								dy.setTelno(custom.getTelno());
								
								this.save(dy);
							}
						}
				}
			} 
		}
		
		syslog.setStlg("0081");
		syslog.setBrief("用户信息注册: 用户名：" + custom.getUsid());
		syslog.setNote("用户信息注册: 公司名称：" + custom.getLname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 增加用户信息 Describe:
	 *
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 *          return:void Date:2011-10-24
	 */
	public void addCustomInfo(Custom custom, ProviderCompany pc, Syslog syslog) {
		if(custom.getLgtp().equals("02")) {
			if (! "99".equals(custom.getTtlb()) ) {
				Custom scus=(Custom) this.get(Custom.class, custom.getSusid());
				if(scus!=null){
					custom.setNote2(scus.getNote2());
				}else{
					custom.setNote2("0000");
				}
			}
		}else{
			custom.setNote2("0000");
		}
		this.save(custom);

		if(custom.getIbusinessid()!=3){
			if (custom.getStatus().equals("01")) {
				if (custom.getLgtp().equals("02") && custom.getTtlb().equals("02")||custom.getLgtp().equals("02") && custom.getTtlb().equals("03")) {
					String hql = " from Opcemployeecardtab where usid='" + custom.getUsid()
							+ "'";
					List list = this.find(hql);
					if (list != null && list.size() > 0) {
						for (int x = 0; x < list.size(); x++) {
							Opcemployeecardtab opcemp = (Opcemployeecardtab) list.get(x);
							String hsql = " from Opcemployeecardsubtab where iemployeecardid="
									+ opcemp.getIemployeecardid();
							List est = this.find(hsql);
							if (est != null && est.size() > 0) {
								for (int y = 0; y < est.size(); y++) {
									Opcemployeecardsubtab subemp = (Opcemployeecardsubtab) est.get(y);
									this.delete(subemp);
								}
							}
							this.delete(opcemp);
						}
					}
					Opcemployeecardtab op = new Opcemployeecardtab();
					Long maxid = getMaxPk("iemployeecardid", "Opcemployeecardtab");
					op.setIemployeecardid(maxid + 1);
					op.setIemployeeid(new Long(0));// 员工表中的虚拟导游
					op.setItickettypeid(new Long(0)); // 产品表中的员工卡证
					op.setIagentno("0");
					op.setIcardno(custom.getZjhm());
					op.setSzticketprintno("0");
					op.setIserialnum("0");
					op.setByticketstate("0");
					op.setMremainmoney(new Double(0));
					op.setMpresentmoney(new Double(0));
					op.setIpresentnum(new Long(0));
					op.setIremainnum(new Long(0));
					op.setByconsumetype("00");
					op.setUsid(custom.getUsid());
					if(custom.getTtlb().equals("02")){
						op.setByisdaoyou(new Long(1)); //是否导游
					}
					if(custom.getTtlb().equals("03")){  //VIP用户
						op.setByisdaoyou(new Long(2));
					}

					this.save(op);
					String sql = " from Esbgardengatetab e where e.byisuse=1";
					List lst = this.find(sql);
					for (int i = 0; i < lst.size(); i++) {
						Esbgardengatetab esb = (Esbgardengatetab) lst.get(i);
						Opcemployeecardsubtab opc = new Opcemployeecardsubtab();
						Long carid = getMaxPk("iemployeecardsubid", "Opcemployeecardsubtab");
						opc.setIemployeecardsubid(carid + 1);
						opc.setIemployeecardid(op.getIemployeecardid());
						opc.setIgardengateid(esb.getId().getIgardengateid());
						opc.setIpasstimes(new Long(0));// 可通过次数
						opc.setMsingletimes(new Long(1));// 单次消费次数
						opc.setIpassedtimes(new Long(0));// 已通过次数
						opc.setMlimitconsume(new Double(0));// 可消费金额
						opc.setMsingleconsume(new Double(0));// 单次消费金额
						opc.setMconsumed(new Double(0));// 已消费金额
						opc.setDtmakedate(Tools.getNowString());
						this.save(opc);
					}

					//如果是ＶＩＰ，那么需要在导游表中添加一条数据
					if(custom.getLgtp().equals("02") && custom.getTtlb().equals("03")){
						String heql=" from Daoyou where id.usid='"+custom.getUsid()+"'";
						List daolist=this.find(heql);
						if(daolist.size()<1){
							Daoyou dy=new Daoyou();
							DaoyouId dyid=new DaoyouId();
							dyid.setUsid(custom.getUsid());
							dyid.setDyusid(custom.getUsid());
							dy.setId(dyid);
							dy.setLname(custom.getLname());
							dy.setIdcard(custom.getZjhm());
							dy.setTourcard("");//导游证
							dy.setMobile(custom.getMobile());
							dy.setTelno(custom.getTelno());

							this.save(dy);
						}
					}
				}
			}
		}

		if(custom.getIbusinessid() == 2L) {
			//保存服务商开户信息
			Long pseq = this.getMaxPk("seq", "ProviderCompany") + 1L;
			pc.setCompanyName(custom.getCorpname());
			pc.setSeq(pseq);
			pc.setApplicationName(custom.getUsid());
			pc.setStatus(0);
			pc.setType("01");
			pc.setUsid(custom.getUsid());
			this.save(pc);
			if(custom.getUstp().equalsIgnoreCase("02") && custom.getTtlb().equalsIgnoreCase("01") && custom.getUsqx().substring(0,1).equalsIgnoreCase("1")) {
				//接口开户
				HqytClient client = new HqytClient();
				
				JSONProviderCompany jpc = new JSONProviderCompany();
	            try {
					BeanUtils.copyProperties(jpc, pc);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MerchantRequest request = MerchantRequest.from(jpc);
				JSONMerchant merchant = client.createMerchants(request);
				if (merchant != null) {
					pc.setStatus(1);
					pc.setAdminLogin(merchant.getAdminLogin());
					pc.setHqytId(merchant.getId());
					pc.setMerchantKey(merchant.getMerchantKey());
					this.update(pc);
				}
			}
		}

		syslog.setStlg("0081");
		syslog.setBrief("用户信息注册: 用户名：" + custom.getUsid());
		syslog.setNote("用户信息注册: 公司名称：" + custom.getLname());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 修改用户信息 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 *          return:void Date:2011-10-24
	 */
	public void editCustomInfo(Custom custom, Syslog syslog) {
        if (! "99".equals(custom.getTtlb()) ) {
            if(custom.getLgtp().equals("02")&&custom.getUstp().equals("02")){
                Custom scus=(Custom) this.get(Custom.class, custom.getSusid());
                custom.setNote2(scus.getNote2());
            }else{
                custom.setNote2("0000");
            }
        }
		
		this.update(custom);
	
		syslog.setStlg("0092");
		syslog.setBrief("用户信息修改：" + custom.getUsid());
		syslog.setNote("将（" + custom.getUsid() + "）用户信息修改");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 删除用户信息 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param custom
	 * @param syslog
	 * return:void Date:2011-10-24
	 */
	public void deleteCustomInfo(String usid, Syslog syslog) {
		Custom ts=(Custom) this.get(Custom.class, usid);
		
		if(ts.getIbusinessid()!=3){
			if (ts.getStatus().equals("01")) {
				if (ts.getLgtp().equals("02") && ts.getTtlb().equals("02")||ts.getLgtp().equals("02") && ts.getTtlb().equals("03")) {
					String hql = " from Opcemployeecardtab where usid='" + ts.getUsid()
					+ "'";
					List list = this.find(hql);
					if (list != null && list.size() > 0) {
						for (int x = 0; x < list.size(); x++) {
							Opcemployeecardtab opcemp = (Opcemployeecardtab) list.get(x);
							String hsql = " from Opcemployeecardsubtab where iemployeecardid="
								+ opcemp.getIemployeecardid();
							List est = this.find(hsql);
							if (est != null && est.size() > 0) {
								for (int y = 0; y < est.size(); y++) {
									Opcemployeecardsubtab subemp = (Opcemployeecardsubtab) est.get(y);
									this.delete(subemp);
								}
							}
							this.delete(opcemp);
						}
					}
					
					//如果是ＶＩＰ
					if(ts.getLgtp().equals("02") && ts.getTtlb().equals("03")){
						String heql2=" from Daoyou where id.usid='"+ts.getUsid()+"'";
						List cslist=this.find(heql2);
						if(cslist!=null&&cslist.size()>0){
							Daoyou dao=(Daoyou) cslist.get(0);
							this.delete(dao);
						}
					}
				}
			}
		}
		
		this.deleteByKey(Custom.class, usid);

		syslog.setStlg("0093");
		syslog.setBrief("用户信息删除：" + usid);
		syslog.setNote("将（" + usid + "）用户信息删除");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * 判断用户在订单中是否存在数据 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:boolean Date:2011-10-24
	 */
	public boolean queryCustomByorder(String usid) {
		boolean isuse = false;
		int num = 0;
		String hsql1 = " from MOrder where usid='" + usid + "'";
		List list1 = this.find(hsql1);
		if (list1 != null && list1.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}

		String hsql2 = " from TOrder where usid='" + usid + "'";
		List list2 = this.find(hsql2);
		if (list2 != null && list2.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}
		String hsql3 = " from YOrder where usid='" + usid + "'";
		List list3 = this.find(hsql3);
		if (list3 != null && list3.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}
		String hsql4 = " from Stssalesvouchertab where usid='" + usid + "'";
		List list4 = this.find(hsql4);
		if (list4 != null && list4.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}
		String hsql5 = " from Stssoldtickettab where usid='" + usid + "'";
		List list5 = this.find(hsql5);
		if (list5 != null && list5.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}

		if (num > 0) {
			isuse = true;
		} else {
			isuse = false;
		}

		return isuse;
	}
	
	
	/**
	 * 判断用户在订单中是否存在数据 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:boolean Date:2011-10-24
	 */
	public boolean queryDaoyouByorder(String usid) {
		boolean isuse = false;
		int num = 0;
		String hsql2 = " from TOrder where dyusid='" + usid + "'";
		List list2 = this.find(hsql2);
		if (list2 != null && list2.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}
		String hsql3 = " from YOrder where dyusid='" + usid + "'";
		List list3 = this.find(hsql3);
		if (list3 != null && list3.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}
		String hsql4 = " from Stssalesvouchertab where dyusid='" + usid + "'";
		List list4 = this.find(hsql4);
		if (list4 != null && list4.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}
		String hsql5 = " from Stssoldtickettab where dyusid='" + usid + "'";
		List list5 = this.find(hsql5);
		if (list5 != null && list5.size() >= 1) {
			num = num + 1;
		} else {
			num = num + 0;
		}

		if (num > 0) {
			isuse = true;
		} else {
			isuse = false;
		}

		return isuse;
	}

	/**
	 * 它是否是主用户，并且下面是否有子用户 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:boolean Date:2011-10-24
	 */
	public boolean queryCustomBysusid(String usid) {
		boolean isuse = false;
		String hsql = " from Custom where susid='" + usid + "'";
		List list = this.find(hsql);
		if (list.size() >= 1) {
			isuse = true;
		}
		return isuse;
	}

	/**
	 * 根据子用户查询上级用户 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:String Date:2011-12-3
	 */
	public String queryParentUsid(String usid) {
		String Susid = "";
		String hsql = " from Custom where usid ='" + usid + "' ";
		List list = this.find(hsql);
		if (list != null && list.size() >= 1) {
			Susid = ((Custom) list.get(0)).getSusid();
		}
		return Susid;
	}

	/**
	 * 根据子用户得到根用户（最高级用户） Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 * @return return:String Date:2011-12-31
	 */
	public String queryRootUsid(String usid) {
		String rootusid = "";
		List list = new ArrayList();
		list.add(usid);
		List toplsit = getTopUsid(list, usid);
		for (int i = 0; i < toplsit.size(); i++) {
			rootusid = toplsit.get(0).toString();
		}

		return rootusid;
	}

	public List getTopUsid(List rootusid, String usid) {
		List topusid = new ArrayList();
		Custom custom = (Custom) this.get(Custom.class, usid);
		if (custom != null) {
			if (!"01".equals(custom.getUstp())) {
				rootusid = getTopUsid(rootusid, custom.getSusid());
			} else {
				topusid.add(custom.getUsid());
			}
		}
		return topusid;
	}

	/**
	 * 查询用户列表 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param lgtp注册类别
	 *          01散客 02团体
	 * @param ttlb团休类别
	 *          01旅行社 02 导游
	 * @param ustp用户类别
	 *          01总社 02分社 03部门
	 * @return return:List Date:2011-12-31
	 */
	public List queryCustomList(String lgtp, String ttlb, String ustp) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Custom where status='01' ");
		if (lgtp != null && !"".equals(lgtp)) {
			hsql.append(" and lgtp = '" + lgtp + "' ");
		}
		if (ttlb != null && !"".equals(ttlb)) {
			hsql.append(" and ttlb = '" + ttlb + "' ");
		}
		if (ustp != null && !"".equals(ustp)) {
			hsql.append(" and ustp = '" + ustp + "' ");
		}
		if(lgtp.equals("02")&&ttlb.equals("01")&&ustp.equals("01")){
			hsql.append(" and ibusinessid =2 ");
		}
		list = this.find(hsql.toString());
		return list;
	}

	public List getSonCustom(List list, String usid) {
		String hsql = " from Custom where susid = '" + usid + "' ";
		List list1 = this.find(hsql);
		if (list1 != null && list1.size() >= 1) {
			for (int i = 0; i < list1.size(); i++) {
				Custom custom = (Custom) list1.get(i);
				list.add(custom.getUsid());
				list = getSonCustom(list, custom.getUsid());
			}
		}
		return list;
	}

	/**
	 * 根据主用户查找子用户（用于团体） Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid主用户名
	 * @return return:String Date:2011-12-31
	 */
	public String queryCustoms(String usid) {
		StringBuffer customs = new StringBuffer();
		List list = new ArrayList();
		list.add(usid);
		
		list = getSonCustom(list,usid);
		if(list!=null && list.size()>=1){
			for (int i = 0; i <list.size(); i++) {
				if(i==list.size()-1){
					customs.append("'"+list.get(i)+"'");
				}else{
					customs.append("'"+list.get(i)+"'"+",");
				}
			}
		}

		return customs.toString();
	}

	public void initdaoyouzhiwen(String usid, Syslog syslog) {
		List opcelist = this.find(" from  Opcemployeefingerprinttab where usid='"
				+ usid + "'");
		for (int i = 0; i < opcelist.size(); i++) {
			Opcemployeefingerprinttab opce = (Opcemployeefingerprinttab) opcelist
					.get(i);
			this.delete(opce);
		}
		syslog.setStlg("0180");
		syslog.setBrief("导游指纹信息删除：" + usid);
		syslog.setNote("将（" + usid + "）用导游指纹信息删除");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	public List queryCustomjson(String usid) {
		List<Map> list = new ArrayList<Map>();
		if (usid == null || usid.equals("")) {
			list = this
					.find(" select new map(usid as usid,corpname as corpname,lgtp as lgtp,ibusinessid as ibusinessid,lname as lname,bname as bname) from Custom where  lgtp='01' or (lgtp='02' and ustp='01' and ttlb='01' and substr(usqx,0,1)='1' and  ibusinessid in (2,3)) order by lgtp desc,ibusinessid,bname ");
		} else {
			list = this
					.find(" select new map(usid as usid,corpname as corpname,lgtp as lgtp,ibusinessid as ibusinessid,lname as lname,bname as bname) from Custom where susid='"
							+ usid + "' order by bname");
		}
		for (Map map : list) {
			String lgtp = (String) map.get("lgtp");
			if (lgtp.equals("01")) {
				map.put("hasnext", "false");
			} else {
				String corpname=(String)map.get("corpname");
				String lname=(String)map.get("lname");
				map.put("corpname", usid+"<"+corpname+">");
				String susid = (String) map.get("usid");
				List zlist = this.find("from Custom where susid='" + susid + "'");
				if (zlist == null || zlist.size() == 0) {
					map.put("hasnext", "false");
				} else {
					map.put("hasnext", "true");
				}
			}

		}
		return list;
	}
	public List querylxsList(Long ibusinessid,String ustp){
		List<Map> list = new ArrayList<Map>();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Custom where ibusinessid="+ibusinessid+" and ustp='"+ustp+"' and substr(usqx,0,4)='1111' order by bname");
		list=this.find(hsql.toString());
		return list;
	}
	
	
	/**
	 * 获取所有用户信息
	 */
	public PaginationSupport getCustomViewList(String lgtp, String ttlb,Long ibusinessid,
			int pageSize, int startIndex, String url) {
		PaginationSupport ps = null;
		String hsql = "";
		if(ibusinessid==0){
			if ("01".equals(lgtp)) {
				hsql = "select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from  Custom c where c.lgtp = '"
						+ lgtp + "'  order by  c.ldate desc,c.usid";
			}
			if ("02".equals(lgtp)) {
				hsql = " select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from  Custom c where c.lgtp = '"
						+ lgtp + "' and ttlb='" + ttlb + "' and ustp='01'  order by  c.ldate desc,c.usid  ";
			}
		}else{
		if ("01".equals(lgtp)) {
			hsql = "select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from  Custom c where c.lgtp = '"
					+ lgtp + "' and c.ibusinessid="+ibusinessid+" order by  c.ldate desc,c.usid ";
		}
		if ("02".equals(lgtp)) {
			hsql = " select new map(c.usid as usid,c.susid as susid,c.corpname as corpname,c.lgtp as lgtp,c.ustp as ustp,c.ttlb as ttlb,c.usqx as usqx,c.status as status,c.usdj as usdj,c.lname as lname,c.mobile as mobile,c.telno as telno,c.ldate as ldate,c.logtimes as logtimes,c.cdate as cdate,c.zjhm as zjhm,c.daoyouno as daoyouno,c.bname as bname,c.note6 as note6,c.email as email) from  Custom c where c.lgtp = '"
					+ lgtp + "' and ttlb='" + ttlb + "' and ustp='01' and c.ibusinessid="+ibusinessid+" order by c.ldate desc,c.usid  ";
		}
		}
		ps = this.findPage(hsql, pageSize, startIndex, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if ("01".equals(ttlb) && "02".equals(lgtp)&&2==ibusinessid) {// 旅行社
					if (map.get("usid") != null) {// 用户
						String usid = map.get("usid").toString();
						String hsqls = " from Custom where susid='" + usid + "'";
						List lists = this.find(hsqls);
						map.put("susidnum", lists.size());
					}
				}

			}

		}
		return ps;
	}
	
	/**
	 * *
	 * Describe:查看某个ＶＩＰ属性信息
	 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#getlookvipcustom(java.lang.String)
	 * @param usid
	 * @return
	 * @author lijingrui
	 * Date:Apr 12, 2012
	 */
	public Customvip getlookvipcustom(String usid){
		Customvip csv=(Customvip) this.get(Customvip.class, usid);
		String sql=" from Sysparv5 sy where sy.id.pmky='CVBY' and sy.id.pmcd='"+csv.getCvtype()+"'";
		Sysparv5 sys1=(Sysparv5) this.find(sql).get(0);
		csv.setPmva(sys1.getPmva());
		return csv;
	}

	/**
	 * *
	 * Describe:根据vip类型查出金额和人数
	 * @see com.ectrip.system.user.service.iservice.ICustomInfoService#getviewvip(java.lang.String)
	 * @param cvtype
	 * @return
	 * @author lijingrui
	 * Date:Apr 12, 2012
	 */
	public Sysparv5 getviewvip(String cvtype)throws Exception{
		StringBuffer json = new StringBuffer();
		String sql="select new map(sy.pmvb as pmvb,sy.pmvc as pmvc) from Sysparv5 sy where sy.id.pmky='CVBY' and sy.id.pmcd='"+cvtype+"'";
		List list=this.find(sql);
		Sysparv5 sys1=new Sysparv5();
		BeanUtils.populate(sys1, (Map)list.get(0));
		return sys1;
	}
	

	/**
	 * Describe: 座位锁定-获取公司名称(旅行社名称)
	 * @author luoxin
	 * Date：2013-12-03
	 * */
	public List getCustomList() {
		// TODO Auto-generated method stub
		return this.find("select new map(ct.usid as usid,ct.corpname as corpname) from Custom ct where ct.lgtp='02' and ct.ttlb='01' and ct.ustp='02' and substr(ct.usqx,1,4)='1111' and ct.status='01'");
	}

	public ProviderCompany findProviderCompanyByUisd(String usid){
		String hsql = "from ProviderCompany where type='01' and usid = '"+usid+"'";
		List list = this.find(hsql);
		if(list != null && !list.isEmpty()){
			return (ProviderCompany) list.get(0);
		}
		return null;
	}
}
