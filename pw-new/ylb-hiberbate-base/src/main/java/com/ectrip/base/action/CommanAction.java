/*package com.ectrip.base.action;

import java.util.List;

import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.employee.Galcompanyinfotab;
*//**
 * 公司架构树处理类
 * @author lijin
 * 主要处理公司架构的，选择公司人员，组织架构等
 *//*
public class CommanAction extends BaseAction{

	
	private List jsonlist;
	private String errMsg;
*/
//	/**
//	 * 
//	 * Describe:获取组织架构树
//	 * @auth:yangguang
//	 * @return
//	 * return:String
//	 * Date:2011-10-19
//	 */
//	public String getJsonDate(){
//		String[] skeys=getRequest().getParameterValues("selectkeys");
//		String sk=(String) getRequest().getParameter("stringKeys");
//		String sv=(String) getRequest().getParameter("stringValues");
//		String ik=(String) getRequest().getParameter("intKeys");
//		String iv=(String) getRequest().getParameter("intValues");
//		String tb=(String) getRequest().getParameter("tbname");
//		if(skeys==null||skeys.equals("")){
//			errMsg="要查询的字段不能为空";
//		}else if(tb==null||tb.equals("")){
//			errMsg="表名不能为空";
//		}else{
//			String[] strKeys=null;
//			String[] strValues=null;
//			String[] intKeys=null;
//			String[] intValues=null;
//			if(sk!=null&&!sk.equals("")){
//				strKeys=sk.split(",");
//			}
//			if(sv!=null&&!sv.equals("")){
//				strValues=sv.split(",");
//			}
//			if(ik!=null&&!ik.equals("")){
//				intKeys=ik.split(",");
//			}
//			if(iv!=null&&!iv.equals("")){
//				intValues=iv.split(",");
//			}
//			jsonlist=this.genericService.getJsonDate(skeys, intKeys, intValues, strKeys, strValues, tb);
//		}
//		
//		return SUCCESS;
//	}
//	
	
	/**
	 * 
	 * Describe:获取公司组织架构书
	 * @auth:yangguang
	 * @return
	 * return:String
	 * Date:2011-10-20
	 */
	 /*public String getComapntTree(){
		Esfemployeetab employee=(Esfemployeetab) getRequest().getSession().getAttribute("employee");
		String id=(String) getRequest().getParameter("id");
		Galcompanyinfotab company=(Galcompanyinfotab) this.genericService.get(Galcompanyinfotab.class, employee.getIcompanyinfoid());
		if((id==null||id.equals(""))&&company.getCompanytype().equals("01")){
			if(id==null||id.equals("")){
				id="0";
			}else{
				id=employee.getIcompanyinfoid().toString();
			}
		}
		String type=(String) getRequest().getParameter("type");
		if(type==null||type.equals("")){
			type="0";
		}
		String icompanid=(String) getRequest().getParameter("icompanyid"); 
		String deptid=(String) getRequest().getParameter("deptid"); 
		jsonlist=this.genericService.getCompanyTree(id,type,icompanid,deptid);
		return SUCCESS;
	}
	
	public List getJsonlist() {
		return jsonlist;
	}


	public void setJsonlist(List jsonlist) {
		this.jsonlist = jsonlist;
	}
	

	
	public String getErrMsg() {
		return errMsg;
	}


	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}

*/