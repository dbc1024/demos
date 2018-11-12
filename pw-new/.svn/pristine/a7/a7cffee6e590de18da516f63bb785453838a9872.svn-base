package com.ectrip.ec.custom.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.custom.dao.idao.IUserCenterOtherDAO;
import com.ectrip.ec.model.user.Custom;

public class UserCenterOtherDAO extends GenericDao implements IUserCenterOtherDAO {
	
	/**
	 * ��ѯ�û��������͵����ö�
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid�û���
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url ��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-13
	 */
	public PaginationSupport queryAllCreditList(String usid,int page,int pageSize,String url){
		PaginationSupport ps = null;
		String hsql ="select new map(c.cseq as cseq,c.usid as usid,c.orid as orid,cus.corpname as corpname,c.creditnumb as creditnumb,sys.pmva as strtype) from Creditdetail c,Sysparv5 sys,Custom cus where c.usid='"+usid+"' and sys.id.pmky='CTLB' and sys.id.pmcd = c.ctype and cus.usid = c.usid order by c.cseq desc";
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}
	
	/**
	 * ��������ѯ��Ӧ��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid�û���
	 * @param ctype ���ö����
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ��
	 * @param url ��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-3-13
	 */
	public PaginationSupport queryCreditListByDates(String usid,String ctype,int page,int pageSize,String url,List list){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(c.cseq as cseq,c.usid as usid,c.orid as orid,cus.corpname as corpname,c.creditnumb as creditnumb) from Creditdetail c,Custom cus ");
		StringBuffer where=new StringBuffer("where");
		if(usid==null||usid.equals("")){
		    if(list!=null&&list.size()>0){
			where.append(" cus.usid in(");
			Custom custom=null;
			for(int i=0;i<list.size();i ++){
			   custom=(Custom) list.get(i);
			   where.append("'"+custom.getUsid()+"'");
			   if(i!=list.size()-1){
			       where.append(",");
			   }
			}
		    }
		    where.append(")");
		}else{
		    where.append(" cus.usid='"+usid+"'");
		}
//		if(!"99".equals(ctype)){
//			hsql.append(" and c.ctype='"+ctype+"' "); 
//		}
		hsql.append(where);
		hsql.append(" and cus.usid = c.usid order by c.cseq desc");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	/**
	 * 
	 * Describe:����������ҷ���
	 * @auth:yangguang
	 * @param usid
	 * @return
	 * return:List
	 * Date:2012-4-8
	 */
	public List findFsUsid(String usid){
	    String hql="from Custom where susid='"+usid+"' and status='01' and lgtp='02' and ustp='02'";
	    List list=find(hql);
	    if(list!=null&&list.size()>0&&list.get(0)!=null){
		return list;
	    }else{
		return null;
	    }
	}
	
	public PaginationSupport getUserJifenDetail(String usid,int page,int pageSize,String url,String starttime,String endtime){
	    PaginationSupport ps = null;
	    String hql="select new map(id.usid as usid,id.orid as orid,point as point,stdt as stdt,zusid as usid,jflb as jflb,jftp as jftp) from Usernumjflist where stdt>='"+starttime+"' and stdt<='"+endtime+"'";
	    if(usid!=null&&!usid.equals("")){
		hql+=" and  zusid='"+usid+"'";
	    }
	    hql+="  order by id.orid desc";
	    ps=this.findPage(hql, pageSize, page, url);
	    return ps;
	}
}

