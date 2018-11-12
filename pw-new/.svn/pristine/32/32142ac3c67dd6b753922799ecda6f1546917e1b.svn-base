package com.ectrip.ticket.permitenter.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.permitenter.dao.INumjifenSetlistDAO;
import com.ectrip.ticket.permitenter.service.iservice.INumjifenSetlistService;

public class NumjifenSetlistService implements INumjifenSetlistService{
	
	private INumjifenSetlistDAO numjifenlistDao;
	
	public INumjifenSetlistDAO getNumjifenlistDao() {
		return numjifenlistDao;
	}

	public void setNumjifenlistDao(INumjifenSetlistDAO numjifenlistDao) {
		this.numjifenlistDao = numjifenlistDao;
	}

	/**
	 * *
	 * Describe:��ʾ��ĳ�����µ�������������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#showAllListnumjifen(java.lang.Long, int, int, java.lang.String)
	 * @param nid
	 * @param pageSize
	 * @param startIndex
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public PaginationSupport showAllListnumjifen(Long nid,int pageSize,int startIndex, String url){
		return numjifenlistDao.showAllListnumjifen(nid, pageSize, startIndex, url);
	}
	
	/**
	 * *
	 * Describe:�����������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#insertnumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public void insertnumjifensetlist(Numjifensetlist numsetlist){
		numjifenlistDao.insertnumjifensetlist(numsetlist);
	}
	
	/**
	 * *
	 * Describe:�޸���������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#updatenumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public void updatenumjifensetlist(Numjifensetlist numsetlist){
		numjifenlistDao.updatenumjifensetlist(numsetlist);
	}
	
	/**
	 * *
	 * Describe:ɾ����������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#deletenumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public void deletenumjifensetlist(Numjifensetlist numsetlist){
		numjifenlistDao.deletenumjifensetlist(numsetlist);
	}
	
	/**
	 * *
	 * Describe:�鿴��������ͳ��
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#viewnumjifensetlist(com.ectrip.model.permitenter.Numjifensetlist)
	 * @param numsetlist
	 * @return
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public Numjifensetlist viewnumjifensetlist(Numjifensetlist numsetlist) throws Exception{
		return numjifenlistDao.viewnumjifensetlist(numsetlist);
	}
	
	/**
	 * *
	 * Describe:��ʾ��ĳ�������µĲ�Ʒ��Ϣ
	 * @see com.ectrip.system.permitenter.service.iservice.INumjifenSetlistService#getEdmticketlist(java.lang.Long)
	 * @param iscenicid
	 * @return
	 * @author lijingrui
	 * Date:Mar 30, 2012
	 */
	public List getEdmticketlist(Long iscenicid){
		return numjifenlistDao.getEdmticketlist(iscenicid);
	}
	
	
	public  Numjifensetlist getJfset(Long nid,Long iticketid,Long ibusiness,Long icrowid,String starttime,String endtime){
		StringBuffer sql=new StringBuffer("from Numjifensetlist where id.nid="+nid+" and itickettypeid="+iticketid+" and iint2="+ibusiness+" and iint1="+icrowid+" and stdt<='"+starttime+"' and etdt>='"+endtime+"' and isvalid=1");
		List list=numjifenlistDao.find(sql.toString());
		if(list!=null&&list.size()>0){
			return (Numjifensetlist) list.get(0);
		}else{
			return null;
		}
	}
	
	
}

