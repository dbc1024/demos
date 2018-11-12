package com.ectrip.ticket.sale.service.card.dao;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.ticket.model.provider.Esbticketwintab;
import com.ectrip.ticket.sale.service.card.dao.idao.IPosWebDao;
import com.ectrip.ticket.sale.service.card.model.PosLoginRequest;
import com.ectrip.ticket.sale.service.card.model.PosLoginResponse;

public class PosWebDao extends GenericDao implements IPosWebDao {
	
	/**
	 * ��ȡ�������б�
	 * @param request
	 * @return GetEsbscenicareatabResponse res
	 */
	public List<?> getScenicList() {
		List list = new ArrayList();
		String hsql2 = "select new map(es.iscenicid as iscenicid, " +    
		         "es.szsceniccode as szsceniccode, " +
		         "es.scenictype as scenictype, " +
		         "es.szscenicname as szscenicname) from Esbscenicareatab es where es.isjd = 0 and es.byisuse =1 and es.scenictype in( select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
						+ "01" + "'or spmcd='" + "01" + "')) ";
		list = this.find(hsql2.toString());
		System.out.println(list.toString());
		return list;
	}
	
	/**
	 * POS����½
	 * @param request
	 * @return PosLoginResponse res
	 */
	public PosLoginResponse posLogin(PosLoginRequest request) {
		PosLoginResponse res = new PosLoginResponse();
		List<Esfemployeetab> emplist = new ArrayList();
		List<Esbticketwintab> winlist = new ArrayList();
		List<Sysparv5> sysparvlist = new ArrayList();
			String emphsql = "from Esfemployeetab es where es.empid = '"+request.getUsername()+"'" + " and es.szpassword = '"+request.getPwd()+"'";
			
			String winsql = "from Esbticketwintab es where es.iscenicid = "+request.getIscenicid()+ " and es.szipaddress = '" + request.getMac() + "'";
			
			String sysparvsql = "select new map(es.spmcd as spmcd, " +
					"es.systp as systp, " + 
					"es.pmva as pmva, " + 
					"es.isa as isa, " +  
					"es.id.pmky as pmky, " +
					"es.id.pmcd as pmcd, " +
					"es.isvalue as isvalue) from Sysparv5 es where es.id.pmky = 'ZFFS' and es.id.pmcd = '00'";
		
		emplist = this.find(emphsql);
		winlist = this.find(winsql);
		sysparvlist = this.find(sysparvsql);
		if(emplist.size()!=0){
		res.setEmployee(emplist.get(0));}
		if(winlist.size()!=0){
		res.setEsbticketwintab(winlist.get(0));}
		if(sysparvlist.size()!=0){
		res.setZffses(sysparvlist);}
		return res;
	}
}
