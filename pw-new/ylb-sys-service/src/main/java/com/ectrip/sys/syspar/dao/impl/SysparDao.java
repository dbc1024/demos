package com.ectrip.sys.syspar.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.syspar.dao.ISysparDao;


@SuppressWarnings("unchecked")
@Repository
public class SysparDao extends GenericDao implements ISysparDao {
	
	/**
	 * *
	 * Describe:显示
	 * @see com.ectrip.system.syspar.dao.idao.ISysparDao#sysparQueryList(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param pmky
	 * @param pmcd
	 * @param path
	 * @param page
	 * @param pagesize
	 * @param url
	 * @return
	 * @author huying
	 * Date:2015-6-23
	 */
	public PaginationSupport sysList(String pmky,String pmcd,String path,int page,int pagesize,String url){
		PaginationSupport ps = null;
		String hsql="";
		Sysparv5 sysparv5 = null;
		sysparv5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id(pmky, pmcd));
		sysparv5.setPmky(sysparv5.getId().getPmky());
		sysparv5.setPmcd(sysparv5.getId().getPmcd());
		hsql = " from Sysparv5 where id.pmky='PRSZ' and spmcd='****' order by isvalue desc,id.pmky,id.pmcd ";
		ps = this.findPage(hsql, pagesize,page, url);
		return ps;
	}
	
	public PaginationSupport sysparQueryList(String pmky,String pmcd,String path,int page,int pagesize,String url){
		PaginationSupport ps = null;
		String hsql="";
		Sysparv5 sysparv5 = null;
		if (pmky == null || "".equals(pmky)) {
			hsql = "from Sysparv5 where systp='0' order by id.pmky,id.pmcd";
		} else {
			sysparv5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id(pmky, pmcd));
			sysparv5.setPmky(sysparv5.getId().getPmky());
			sysparv5.setPmcd(sysparv5.getId().getPmcd());
			int next = 0;
			if (path.equals("up")) {
				if (sysparv5.getSystp().equals("1")) {
					hsql = "from Sysparv5 where systp='0' order by id.pmky,id.pmcd";
				} else {
					Sysparv5 psysparv5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id(sysparv5.getPmky(), sysparv5.getSpmcd()));
					hsql = "from Sysparv5 where id.pmky='" + sysparv5.getPmky() + "' and spmcd='" + psysparv5.getSpmcd() + "' order by id.pmky,id.pmcd";
				}
			} else {
				hsql = "from Sysparv5 where id.pmky='" + sysparv5.getPmky() + "' and spmcd='" + sysparv5.getPmcd() + "' order by id.pmky,id.pmcd";
			}
		}
		System.out.println("aaaaaaaaaaaaa:"+hsql);
		ps = this.findPage(hsql, pagesize,page, url);
		return ps;
	}
	
	private String SYSPAR_HSQL = "select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.spmcd as spmcd,sys.systp as systp,sys.pmva as pmva,sys.pmvb as pmvb,sys.pmvc as pmvc,sys.pmvd as pmvd,sys.pmve as pmve,sys.pmvf as pmvf,sys.isa as isa,sys.isb as isb,sys.isc as isc,sys.isd as isd,sys.ise as ise,sys.isf as isf,sys.isvalue as isvalue,sys.note as note) from Sysparv5 sys where ";

	public List findAll(String pmky, String systp, String type, boolean isvalue, boolean isAll) {
		List returnList = new ArrayList();
		List findList = null;
		String hsql = SYSPAR_HSQL;
		if (pmky == null || pmky.equals("")) {
			if (systp == null || systp.equals("")) {
				return null;
			} else {
				if (type == null || systp.equals("")) {
					if (isvalue) {
						hsql += "sys.systp='0' and sys.isvalue=1 order by sys.id.pmky,sys.id.pmcd";
					} else {
						hsql += "sys.systp='0' order by sys.id.pmky,sys.id.pmcd";
					}
				} else {
					if (isvalue) {
						hsql += "sys.systp='0' and sys.is" + type + "=1 and sys.isvalue=1 order by sys.id.pmky,sys.id.pmcd";
					} else {
						hsql += "sys.systp='0' and sys.is" + type + "=1 order by sys.id.pmky,sys.id.pmcd";
					}
				}
				findList = find(hsql);
				if (findList.size() == 0) {
					return null;
				} else {
					Sysparv5 sysparv5 = null;
					for (int i = 0; i < findList.size(); i++) {
						returnList.add(findList.get(i));
						if (isAll) {
							try {
								BeanUtils.populate(sysparv5, (Map) findList.get(i));
								findByPsysid(sysparv5.getId().getPmky(), sysparv5.getId().getPmcd(), type, isvalue, isAll, returnList);
							} catch (Exception e) {
								System.out.println(e.getMessage());
								e.printStackTrace();
							}
						}
					}
				}
			}
		} else {
			pmky = pmky.toUpperCase();
			if (systp == null || systp.equals("")) {
				if (type == null || type.equals("")) {
					if (isvalue) {
						hsql += "sys.id.pmky=? and sys.systp='0' and sys.isvalue=1 order by sys.id.pmky,sys.id.pmcd";
					} else {
						hsql += "sys.id.pmky=? and sys.systp='0' order by sys.id.pmky,sys.id.pmcd";
					}
				} else {
					if (isvalue) {
						hsql += "sys.id.pmky=? and sys.systp='0' and sys.is" + type + "=1 and sys.isvalue=1 order by sys.id.pmky,sys.id.pmcd";
					} else {
						hsql += "sys.id.pmky=? and sys.systp='0' and sys.is" + type + "=1 order by sys.id.pmky,sys.id.pmcd";
					}
				}
				findList = find(hsql, new Object[] { pmky });
			} else {
				if (type == null || type.equals("")) {
					if (isvalue) {
						hsql += "sys.id.pmky=? and sys.systp=? and sys.isvalue=1 order by sys.id.pmcd,sys.id.pmcd";
					} else {
						hsql += "sys.id.pmky=? and sys.systp=? order by sys.id.pmcd,sys.id.pmcd";
					}
				} else {
					if (isvalue) {
						hsql += "sys.id.pmky=? and sys.systp=? and sys.is" + type + "=1 and sys.isvalue=1 order by sys.id.pmky,sys.id.pmcd";
					} else {
						hsql += "sys.id.pmky=? and sys.systp=? and sys.is" + type + "=1 order by sys.id.pmky,sys.id.pmcd";
					}
				}
				findList = find(hsql, new Object[] { pmky, systp });
			}
			if (findList.size() == 0) {
				return null;
			} else {
				Sysparv5 sysparv5 = null;
				for (int i = 0; i < findList.size(); i++) {
					returnList.add(findList.get(i));
					if (isAll) {
						try {
							BeanUtils.populate(sysparv5, (Map) findList.get(i));
							findByPsysid(sysparv5.getId().getPmky(), sysparv5.getId().getPmcd(), type, isvalue, isAll, returnList);
						} catch (Exception e) {
							System.out.println(e.getMessage());
							e.printStackTrace();
						}
					}
				}
			}
		}
		return returnList;
	}

	public void findByPsysid(String pmky, String spmcd, String type, boolean isvalue, boolean isAll, List returnList) {
		List findList = null;
		String hsql = SYSPAR_HSQL;
		if (type == null || type.equals("")) {
			if (isvalue) {
				hsql += "sys.id.pmky=? and sys.spmcd=? and sys.isvalue=1 order by sys.id.pmcd";
			} else {
				hsql += "sys.id.pmky=? and sys.spmcd=? order by sys.id.pmcd";
			}
		} else {
			if (isvalue) {
				hsql += "sys.id.pmky=? and sys.spmcd=? and sys.is" + type + "=1 and sys.isvalue=1 order by sys.id.pmcd";
			} else {
				hsql += "sys.id.pmky=? and sys.spmcd=? and sys.is" + type + "=1 order by sys.id.pmcd";
			}
		}
		findList = find(hsql, new Object[] { pmky, spmcd });
		if (findList.size() == 0) {
			return;
		} else {
			Sysparv5 sysparv5 = null;
			for (int i = 0; i < findList.size(); i++) {
				returnList.add(findList.get(i));
				if (isAll) {
					try {
						BeanUtils.populate(sysparv5, (Map) findList.get(i));
						findByPsysid(sysparv5.getId().getPmky(), sysparv5.getId().getPmcd(), type, isvalue, isAll, returnList);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Sysparv5 findSysparOne(String pmky, String pmcd, String type) {
		List findList = null;
		if (type == null || type.equals("")) {
			findList = find("select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.pmva as pmva) from Sysparv5 sys where sys.id.pmky=? and sys.id.pmcd=? and sys.isvalue=1", new Object[] { pmky, pmcd });
		} else {
			findList = find("select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.pmva as pmva) from Sysparv5 sys where sys.id.pmky='" + pmky + "' and sys.id.pmcd='" + pmcd + "' and sys.is" + type + "=1 and sys.isvalue=1");
		}
		Sysparv5 sys=new Sysparv5();
		if(findList.size()>0){
			try {
				BeanUtils.populate(sys, (Map)findList.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return sys;
		}else{
			return null;
		}

	}

	// 联动
	public List findOneList(String pmky, String spmcd) {
		String hsql = SYSPAR_HSQL + "sys.id.pmky=? and sys.spmcd=? and sys.isvalue=1 order by sys.id.pmcd";
		return find(hsql, new Object[] { pmky, spmcd });
	}

	public Map findDoubleList(String pmky, String spmcd) {
		List list = null;
		Map map = new HashMap();
		list = this.findOneList(pmky, spmcd);
		if (list.size() == 0) {
			return null;
		} else {
			List sublist = null;
			String hsql = SYSPAR_HSQL + "sys.id.pmky=? and sys.spmcd=? and sys.isvalue=1 order by sys.id.pmcd";
			for (int i = 0; i < list.size(); i++) {
				sublist = find(hsql, new Object[] { pmky, ((Map) list.get(i)).get("pmcd") });
				if (sublist.size() == 0) {
					continue;
				} else {
					map.put(((Map) list.get(i)).get("pmcd"), sublist);
				}
			}
		}
		return map;
	}
	public List retrieve(String pmky){
		List list = new ArrayList();
		String hsql = SYSPAR_HSQL;
		 hsql += "  sys.id.pmky='"+ pmky + "' and sys.id.pmcd not like'%*%' and isvalue=1 order by pmcd";
		list = this.find(hsql);
		return list;
	}
	
	public List query(String pmky,String pmcdwhere){
		String hsql = SYSPAR_HSQL;
		 hsql += " sys.id.pmky='"+ pmky + "' and " + pmcdwhere;
		 List list = this.find(hsql);
		 
		return list;
	}
	/**
	 * 查看产品种类*
	 * Describe:
	 * @see com.ectrip.system.syspar.dao.idao.ISysparDao#querypdtpList(java.lang.String)
	 * @param pdtp服务商类别
	 * @return
	 * @author huangyuqi
	 * Date:2011-9-23
	 */
	public List querypdtpList(String pdtp,String type){
		List list = new ArrayList();
		String hsql="";
		if(type==null || "".equals(type)){
			hsql="select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.pmva as pmva) from Sysparv5 sys where sys.isvalue = 1 and sys.id.pmky='PRTP' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where sys1.isvalue = 1 and sys1.id.pmky='PDTP' and (sys1.id.pmcd='"+pdtp+"'or spmcd='"+pdtp+"'))";
		}
		if(type!=null && !"".equals(type)){
			if("01".equals(type)){//基础票
				hsql="select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.pmva as pmva) from Sysparv5 sys where sys.isvalue = 1 and sys.id.pmky='PRTP' and pmcd <> '0010' and pmvb in(select sys1.id.pmcd from Sysparv5 sys1 where sys1.isvalue = 1 and sys1.id.pmky='PDTP' and (sys1.id.pmcd='"+pdtp+"'or spmcd='"+pdtp+"'))";
			}
			if("02".equals(type)){//组合票
				hsql="select new map(sys.id.pmky as pmky,sys.id.pmcd as pmcd,sys.pmva as pmva) from Sysparv5 sys where sys.isvalue = 1 and sys.id.pmky='PRTP' and pmcd = '0010'";
			}
		}
		list = this.find(hsql);
		return list;
	}
	/**
	 * 根据类型，名称查询系统参数
	 * Describe:
	 * @auth:huangyuqi
	 * @param pmky
	 * @param pmva
	 * @return
	 * return:Sysparv5
	 * Date:2011-10-10
	 */
	public Sysparv5 findSysOne(String pmky,String pmva){
		List list = this.find(" from Sysparv5 where id.pmky=? and pmva=?", new Object[] { pmky, pmva });
		if (list.size() == 0) {
			return null;
		} else {
			return (Sysparv5) list.get(0);
		}
	}
	/**
	 * Describe:根据订单号查询座位信息
	 * @author luoxin
	 * @param orid
	 * @return PaginationSupport
	 * Date: 2013-09-24
	 */
	public PaginationSupport getSeatList(String orid, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		PaginationSupport ps = null;
		String hsql = "select new map(s.ivenueid as ivenueid, s.itripid as itripid, s.iprogramid as iprogramid, s.iseatid as iseatid, s.startdate as startdate) from Seatordertab as s where s.id.orid='"+orid+"'";
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}

	public List queryPrintList(Long icrowdkindpriceid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List retrieve(String pmky, Long type) {
		List list = new ArrayList();
		String hsql = SYSPAR_HSQL;
		 hsql += "  sys.id.pmky='"+ pmky + "' and sys.id.pmcd not like'%*%' and isvalue=1";
		 if(type==1){
			 hsql +=  " and sys.id.pmcd='01' ";
		 }
		 hsql +=  " order by pmcd";
		list = this.find(hsql);
		return list;
	}

	@Override
	public List getSysparamsByPmcds(String pmcds) {
		String[] split = pmcds.split(",");
		
		StringBuilder newPmcds = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			if(i!=0) {
				newPmcds.append(",");
			}
			newPmcds.append("'"+ split[i]+ "'");
		}
		
		String sql = "select distinct new map(v5.pmva AS strddzt, v5.id.pmky as pmky, v5.id.pmcd as pmcd) from Sysparv5 v5 where v5.id.pmky='DDZT' and v5.id.pmcd in("+ newPmcds +")";
		
		List list = this.find(sql);
		
		return list;
	}
	
	@Override
	public List getSysparamsByPmkyAndPmcds(String pmky,String pmcds) {
		String PMKY = pmky.toUpperCase();
		String[] split = pmcds.split(",");
		StringBuilder newPmcds = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			if(i!=0) {
				newPmcds.append(",");
			}
			newPmcds.append("'"+split[i]+"'");
		}
		
		String sql = "select distinct new map(v5.pmva AS pmva, v5.id.pmky as pmky, v5.id.pmcd as pmcd) from Sysparv5 v5 where v5.id.pmky='"+PMKY+"' and v5.id.pmcd in("+ newPmcds +")";
		
		List list = this.find(sql);
		
		return list;
	}

	public List getSysparamsByParms(String pmky, String pmcd, String pmvbs, String spmcd) {
		//参数处理
		String PMKY = null;
		StringBuffer newPmvbs = new StringBuffer();
		if(!("").equals(pmky) && null!=pmky) {
			PMKY = pmky.toUpperCase();
		}
		if(!("").equals(pmvbs) && null!=pmvbs) {
			String[] split = pmvbs.split(",");
			//参数处理
			for (int i = 0; i < split.length; i++) {
				if(i!=0) {
					newPmvbs.append(",");
				}
				newPmvbs.append("'"+split[i]+"'");
			}
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct new map(v5.pmva AS strddzt,v5.id.pmky as pmky,v5.id.pmcd as pmcd) from Sysparv5 v5");
		StringBuffer where = new StringBuffer();
		if(!("").equals(pmky) && null!=pmky) {
			where.append(" where v5.id.pmky='"+PMKY+"'");
		}
		if(!("").equals(newPmvbs.toString()) && null!=newPmvbs.toString()) {
			where.append(" and v5.pmvb in("+newPmvbs.toString()+")");
		}
		if(!"".equals(pmcd) && null!=pmcd) {
			if(!"".equals(spmcd) && null!=spmcd) {
				where.append(" and (v5.id.pmcd = '"+pmcd+"' or v5.spmcd='"+spmcd+"')");
			}else {
				where.append(" and v5.id.pmcd = '"+pmcd+"'");
			}
		}else {
			if(!"".equals(spmcd) && null!=spmcd) {
				where.append(" and v5.spmcd='"+spmcd+"'");
			}
		}
		String hql=sql.toString()+where.toString();
		List list = this.find(hql);
		
		return list;
	}

	@Override
	public List<Sysparv5> findSysparBypmky(String pmky, String pmcd) {
		StringBuffer hql = new StringBuffer("from Sysparv5 where isvalue=1");
		StringBuffer where=new StringBuffer();
		if(null!=pmky&&!("").equals(pmky)) {
			where.append(" and pmky='"+pmky+"'");
		}
		if(null!=pmcd&&!("").equals(pmcd)) {
			where.append(" and pmcd='"+pmcd+"'");
		}
		hql.append(where);
		List find = this.find(hql.toString());
		if(find.size()>0) {
			return find;
		}else {
			return null;
		}
	}
}
