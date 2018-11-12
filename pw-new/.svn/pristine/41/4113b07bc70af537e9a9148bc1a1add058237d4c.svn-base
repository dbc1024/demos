package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Edmbusinesstab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.provider.dao.IEdpofferschemetabDAO;

/**
 * Description:优惠方案
 * 
 * @version 1.0
 * @author Jzhenhua<br>
 *         Date 2011-10-24
 * 
 */
@Repository
public class EdpofferschemetabDAO extends GenericDao implements
		IEdpofferschemetabDAO {

	/**
	 * Description:添加优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void addEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog) {
		this.save(edpofferschemetab);
		
		// 保存日志信息
		syslog.setStlg("0131");
		syslog.setBrief("优惠方案操作信息：" + edpofferschemetab.getIoffersschemeid());
		syslog.setNote("添加优惠方案信息：" + edpofferschemetab.getSzoffersschemename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * Description:删除优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void deleteEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog) {
		this.delete(edpofferschemetab);
		
		// 保存日志信息
		syslog.setStlg("0132");
		syslog.setBrief("优惠方案操作信息：" + edpofferschemetab.getIoffersschemeid());
		syslog.setNote("删除优惠方案信息：" + edpofferschemetab.getSzoffersschemename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * Description:修改优惠方案
	 * 
	 * @param edpofferschemetab
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public void updateEdpofferschemetab(Edpofferschemetab edpofferschemetab,Syslog syslog) {
		this.update(edpofferschemetab);
		
		// 保存日志信息
		syslog.setStlg("0133");
		syslog.setBrief("优惠方案操作信息：" + edpofferschemetab.getIoffersschemeid());
		syslog.setNote("修改优惠方案信息：" + edpofferschemetab.getSzoffersschemename());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	/**
	 * Description:根据编号查询优惠方案
	 * 
	 * @param ioffersschemeid
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	@SuppressWarnings("unchecked")
	public Edpofferschemetab getEdpofferschemetabbyId(Long ioffersschemeid) {
		// SQL
		String hsql = "from Edpofferschemetab offerscheme,Esbscenicareatab provider,Edmbusinesstab business,Edmtickettypetab tickettype,Edpcrowdkindtab crowd where offerscheme.iscenicid = provider.iscenicid and offerscheme.ibusinessid = business.ibusinessid and offerscheme.itickettypeid = tickettype.itickettypeid and offerscheme.icrowdkindid = crowd.icrowdkindid and offerscheme.ioffersschemeid="
				+ ioffersschemeid;

		// 获取数据
		List list = this.find(hsql);

		// 获取出List中的Object[]并转换成实体
		Object[] object = (Object[]) list.get(0);
		Edpofferschemetab offerscheme = (Edpofferschemetab) object[0];
		Esbscenicareatab provider = (Esbscenicareatab) object[1];
		Edmbusinesstab business = (Edmbusinesstab) object[2];
		Edmtickettypetab ticket = (Edmtickettypetab) object[3];
		Edpcrowdkindtab crowd = (Edpcrowdkindtab) object[4];

		// 
		List sys1 = this
				.find("from Sysparv5 sys where sys.id.pmky='OSTP' and systp=1 and sys.id.pmcd='"
						+ offerscheme.getBycategorytype() + "'");
		List sys2 = this
				.find("from Sysparv5 sys where sys.id.pmky='OOTP' and systp=1 and sys.id.pmcd='"
						+ offerscheme.getIoffertype() + "'");

		// 设置非数据库字段参数
		if (sys1.size() != 0) {
			offerscheme.setStrbycategorytype(((Sysparv5) (sys1.get(0)))
					.getPmva());
		}
		if (sys2.size() != 0) {
			offerscheme.setStrioffertype(((Sysparv5) (sys2.get(0))).getPmva());
		}
		offerscheme.setStriscenicid(provider.getSzscenicname());// 服务商名
		offerscheme.setStribusinessid(business.getSzbusinessname());// 业务类型名
		offerscheme.setStritickettypeid(ticket.getSztickettypename());// 产品类型名
		offerscheme.setStricrowdkindid(crowd.getSzcrowdkindname());// 人群种族名

		return offerscheme;
	}

	/**
	 * Description:获取最大编号
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	@SuppressWarnings("unchecked")
	public Long getMaxId() {
		// SQL
		String sql = "select max(e.ioffersschemeid) from Edpofferschemetab e";

		// 执行SQL
		List list = this.find(sql);

		// 判断是否有数据,如果没有数据则返回1
		if (null == list.get(0) || list.size() == 0) {
			return new Long(1);
		}

		// 有数据则返回查询到的最大ID+1
		return Long.parseLong(list.get(0).toString()) + 1;
	}

	/**
	 * Description:获取优惠方案列表
	 * 
	 * @param edpofferschemetab
	 *            查询条件存储实体
	 * @param page
	 *            当前页数
	 * @param pageSize
	 *            每页大小
	 * @param url
	 *            连接URL
	 * @return Edpofferschemetab List
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	public PaginationSupport getEdpofferschemetabListView(
			Edpofferschemetab edpofferschemetab, String scenics, int page,
			int pageSize, String url) {
		PaginationSupport ps=null;
		// SQL
		StringBuffer hsql = new StringBuffer(
				"select new map(offerscheme.ioffersschemeid as ioffersschemeid,offerscheme.iscenicid as iscenicid,offerscheme.ibusinessid as ibusinessid,offerscheme.szoffersschemecode as szoffersschemecode,offerscheme.szoffersschemename as szoffersschemename,offerscheme.bycategorytype as bycategorytype,offerscheme.enddata as enddata,offerscheme.startdata as startdata,offerscheme.imultiples as imultiples,offerscheme.ioffernum as ioffernum,offerscheme.ioffertype as ioffertype,offerscheme.itickettypeid as itickettypeid,offerscheme.icrowdkindid as icrowdkindid,offerscheme.byisuse as byisuse,offerscheme.szmemo as szmemo,provider.szscenicname as striscenicid,business.szbusinessname as stribusinessid,tickettype.sztickettypename as stritickettypeid,crowd.szcrowdkindname as stricrowdkindid) from Edpofferschemetab offerscheme,Esbscenicareatab provider,Edmbusinesstab business,Edmtickettypetab tickettype,Edpcrowdkindtab crowd where offerscheme.iscenicid = provider.iscenicid and offerscheme.ibusinessid = business.ibusinessid and offerscheme.itickettypeid = tickettype.itickettypeid and offerscheme.icrowdkindid = crowd.icrowdkindid ");

		// 根据当前服务商用户管理获取优惠方案
		if (null != scenics && !"".equals(scenics)) {
			hsql.append("and offerscheme.iscenicid in (" + scenics + ") ");
		}

		// 判断查询条件
		if (null != edpofferschemetab) {
			// 选择服务商查询
			if (null != edpofferschemetab.getIscenicid()
					&& !"".equals(edpofferschemetab.getIscenicid())
					&& 0 != edpofferschemetab.getIscenicid()) {
				hsql.append("and offerscheme.iscenicid = "
						+ edpofferschemetab.getIscenicid() + " ");
			} else if (null != edpofferschemetab.getIbusinessid()
					&& !"".equals(edpofferschemetab.getIbusinessid())
					&& 0 != edpofferschemetab.getIbusinessid()) { // 选择业务查询
				hsql.append("and offerscheme.ibusinessid ="
						+ edpofferschemetab.getIbusinessid() + " ");
			} else if (null != edpofferschemetab.getSzoffersschemename()
					&& !"".equals(edpofferschemetab.getSzoffersschemename())) { // 按名称模糊查询
				hsql.append("and offerscheme.szoffersschemename like '%"
						+ edpofferschemetab.getSzoffersschemename() + "%' ");
			}
		}
		System.out.println(hsql);
		ps=this.findPage(hsql.toString(), pageSize, page, url);
		List lst=ps.getItems();
		if(lst!=null&&lst.size()>0){
			for(int x=0;x<lst.size();x++){
				Map map=(Map) lst.get(x);
				List sys2 = this
						.find("from Sysparv5 sys where sys.id.pmky='OOTP' and systp=1 and sys.id.pmcd='"
								+ map.get("ioffertype") + "'");
				if (sys2.size() != 0) {
					map.put("strioffertype", ((Sysparv5) (sys2.get(0))).getPmva());
				}
			}
		}
		return ps;
	}

	/**
	 * Description:获得所有产品类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	@SuppressWarnings("unchecked")
	public List getTickettypeList(String scenics) {

		String sql = "from Edmtickettypetab e where byisuse=1 ";
		if (null != scenics && !"".equals(scenics)) {
			sql += "and e.iscenicid in " + scenics;
		}

		return this.find(sql);
	}

	/**
	 * Description:获得所有人群种族
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	@SuppressWarnings("unchecked")
	public List getCrowdList() {
		return this.find("from Edpcrowdkindtab where byisuse=1 ");
	}

	/**
	 * Description:获得所有业务类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 */
	@SuppressWarnings("unchecked")
	public List getBusinessList() {
		return this.find("from Edmbusinesstab where byisuse=1 and ibusinessid!=1");
	}

	/**
	 * Description:获得所有优惠类型
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-25
	 */
	public List getOSTPList() {
		return this
				.find("from Sysparv5 sys where sys.id.pmky='OSTP' and sys.systp=1");
	}

	/**
	 * Descrption:获得所有优惠对象方式
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-25
	 */
	public List getOOTPList() {
		return this
				.find("from Sysparv5 sys where sys.id.pmky='OOTP' and sys.systp=1");
	}

	/**
	 * *
	 * Describe:查找pmky='OOTP' pmcd = '0' 的信息
	 * @see com.ectrip.system.provider.dao.idao.IEdpofferschemetabDAO#showOOTPList()
	 * @return
	 * @author chenxinhao
	 * Date:2013-1-7
	 */
	public List showOOTPList() {
		return this
				.find("from Sysparv5 sys where sys.id.pmky='OOTP' and sys.systp=1 and sys.id.pmcd = '0'");
	}
	/**
	 * *
	 * Describe:查找优惠信息
	 * @see com.ectrip.system.provider.dao.idao.IEdpofferschemetabDAO#showEdpofferschemetabList(com.ectrip.model.provider.Edpofferschemetab)
	 * @param scheme
	 * @return
	 * @author chenxinhao
	 * Date:2013-1-7
	 */
	public boolean showEdpofferschemetabList(Edpofferschemetab scheme){
		boolean b=false;
		if(scheme!=null&&!scheme.equals("")){
			if(scheme.getIoffertype()!=null&&!scheme.getIoffertype().equals("")&&scheme.getIoffertype()==1){
				String hsql=" from Edpofferschemetab edp where edp.byisuse=1 and edp.ibusinessid="+scheme.getIbusinessid()+" and edp.iscenicid = "+scheme.getIscenicid()+" and ( (edp.startdata<='"+scheme.getStartdata()+"' and edp.enddata>='"+scheme.getEnddata()+"' ) or (edp.startdata>='"+scheme.getStartdata()+"' and  edp.enddata<='"+scheme.getEnddata()+"' ) or (edp.startdata<='"+scheme.getEnddata()+"' and  edp.enddata>='"+scheme.getEnddata()+"' ) or (edp.startdata<='"+scheme.getStartdata()+"' and  edp.enddata>='"+scheme.getStartdata()+"' ) )";
				if(scheme.getStrioffertype()!=null&&!scheme.getStrioffertype().equals("")&&scheme.getStrioffertype().equals("2")){
					hsql+=" and edp.ioffersschemeid!="+scheme.getIoffersschemeid();
				}
				List list=this.find(hsql);
				if(list!=null&&list.size()>0){
					return true;
				}

				return b;
			}else{
				String msql=" from Edpofferschemetab edp where edp.byisuse=1 and edp.ibusinessid="+scheme.getIbusinessid()+" and edp.ioffertype =0 and  edp.iscenicid = "+scheme.getIscenicid()+" and edp.itickettypeid = "+scheme.getItickettypeid()+" and edp.ibusinessid = "+scheme.getIbusinessid()+" and edp.icrowdkindid = "+scheme.getIcrowdkindid()+" and ( (edp.startdata<='"+scheme.getStartdata()+"' and edp.enddata>='"+scheme.getEnddata()+"' ) or (edp.startdata>='"+scheme.getStartdata()+"' and  edp.enddata<='"+scheme.getEnddata()+"' ) or (edp.startdata<='"+scheme.getEnddata()+"' and  edp.enddata>='"+scheme.getEnddata()+"' ) or (edp.startdata<='"+scheme.getStartdata()+"' and  edp.enddata>='"+scheme.getStartdata()+"' ) )";
				if(scheme.getStrioffertype()!=null&&!scheme.getStrioffertype().equals("")&&scheme.getStrioffertype().equals("2")){
					msql+=" and edp.ioffersschemeid!="+scheme.getIoffersschemeid();
				}
				List lst=this.find(msql);
				if(lst!=null&&lst.size()>0){
					return true;
				}
				
				String hsql=" from Edpofferschemetab edp where edp.byisuse=1 and edp.ibusinessid="+scheme.getIbusinessid()+" and edp.ioffertype =1 and edp.iscenicid = "+scheme.getIscenicid()+" and ( (edp.startdata<='"+scheme.getStartdata()+"' and edp.enddata>='"+scheme.getEnddata()+"' ) or (edp.startdata>='"+scheme.getStartdata()+"' and  edp.enddata<='"+scheme.getEnddata()+"' ) or (edp.startdata<='"+scheme.getEnddata()+"' and  edp.enddata>='"+scheme.getEnddata()+"' ) or (edp.startdata<='"+scheme.getStartdata()+"' and  edp.enddata>='"+scheme.getStartdata()+"' ) )";
				if(scheme.getStrioffertype()!=null&&!scheme.getStrioffertype().equals("")&&scheme.getStrioffertype().equals("2")){
					hsql+=" and edp.ioffersschemeid!="+scheme.getIoffersschemeid();
				}
				List list=this.find(hsql);
				if(list!=null&&list.size()>0){
					return true;
				}
				
				return b;
			}
		}else{
			return b;
		}
	}
	
	/**
	 * 
	 * Describe:判断该优惠方案是否已存在订单
	 * @author:chenxinhao
	 * @param ioffersschemeid
	 * @return
	 * return:int
	 * Date:2013-5-30
	 */
	public int checkorder(Long ioffersschemeid){
		int count = 0;
		List tlist = this.find("from TOrderlist t where t.ioffersschemeid="+ioffersschemeid);
		List slist = this.find("from Stssalesvoucherdetailstab s where ioffersschemeid="+ioffersschemeid);
		if(tlist!=null&&!tlist.isEmpty()){
			count+=tlist.size();
		}
		if(slist!=null&&!slist.isEmpty()){
			count+=slist.size();
		}
		return count;
	}
}
