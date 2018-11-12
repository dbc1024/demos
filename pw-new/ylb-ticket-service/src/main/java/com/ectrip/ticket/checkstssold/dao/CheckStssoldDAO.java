package com.ectrip.ticket.checkstssold.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.checkstssold.dao.idao.ICheckStssoldDAO;
import com.ectrip.ticket.model.order.Stssalesvouchertab;

public class CheckStssoldDAO extends GenericDao implements ICheckStssoldDAO{

	/**
	 * *
	 * Describe:判断此票印刷号是否存在
	 * @see com.ectrip.checkstssold.dao.idao.ICheckStssoldDAO#getStssoldticketLookview(java.lang.String)
	 * @param szticketprintno
	 * @return
	 * @author lijingrui
	 * Date:Mar 13, 2012
	 */
	public boolean getStssoldticketLookview(String szticketprintno) {
		String sql="select new map(st.szticketprintno as szticketprintno,st.iserialnum as iserialnum) from Stssoldtickettab st,Stssalesvouchertab sv where sv.id.isalesvoucherid=st.id.isalesvoucherid and sv.szsalesvoucherno='01' and st.szticketprintno='"+szticketprintno+"' ";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * *
	 * Describe:判断此票是否检票
	 * @see com.ectrip.checkstssold.dao.idao.ICheckStssoldDAO#getQuerystssold(java.lang.String)
	 * @param szticketprintno
	 * @return
	 * @author lijingrui
	 * Date:Mar 13, 2012
	 */
	public boolean getQuerystssold(String szticketprintno) {
		String sql="select new map(sk.ipassedtimes as ipassedtimes) from Stssoldtickettab st,Stssalesvouchertab sv,Stssoldticketsubtab sk where sv.id.isalesvoucherid=st.id.isalesvoucherid and sk.id.szsoldticketid=st.id.szsoldticketid and sv.szsalesvoucherno='01' and st.szticketprintno='"+szticketprintno+"' ";
		List lst=this.find(sql);
		if(lst!=null&&lst.size()>0){
			Long ipassedtimes=(Long) lst.get(0);
			if(ipassedtimes!=null&&ipassedtimes>0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}

	}

	/**
	 * *
	 * Describe:显示此票的检票信息
	 * @see com.ectrip.checkstssold.dao.idao.ICheckStssoldDAO#showViewStssoldticket(java.lang.String)
	 * @param szticketprintno
	 * @return
	 * @author lijingrui
	 * Date:Mar 13, 2012
	 * @throws Exception
	 */
	public Stssalesvouchertab showViewStssoldticket(String szticketprintno) throws Exception {
		String sql="select new map(st.szticketprintno as szticketprintno,sv.szsalesvoucherno as szsalesvoucherno,gb.szregionalname as striregionalid,sk.ipassedtimes as ipassedtimes,sk.dtlastcheckdate as dtlastcheckdate,ebg.szgardengatename as szgardengatename) from Stssoldtickettab st,Stssalesvouchertab sv,Stssoldticketsubtab sk,Galsourceregiontab gb,Esbgardengatetab ebg  where sv.id.isalesvoucherid=st.id.isalesvoucherid and sk.id.szsoldticketid=st.id.szsoldticketid and sv.iregionalid=gb.iregionalid and ebg.igardengateid=sk.igardengateid and sv.szsalesvoucherno='01' and st.szticketprintno='"+szticketprintno+"' ";
		List lst=this.find(sql);
		Stssalesvouchertab svouchert=new Stssalesvouchertab();
		BeanUtils.populate(svouchert, (Map) lst.get(0));
		return svouchert;
	}

}

