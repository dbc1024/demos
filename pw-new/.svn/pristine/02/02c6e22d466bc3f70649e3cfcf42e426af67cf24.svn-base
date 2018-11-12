package com.ectrip.ec.userxfjf.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.user.Userjfxt;
import com.ectrip.ec.userxfjf.dao.idao.IUserjfxtDAO;
@Repository
public class UserjfxtDAO extends GenericDao implements IUserjfxtDAO {

	//�����û�����
	public void addUserjf(Userjfxt userjf) {
		this.save(userjf);
	}
	//����û����ڻ���
	public void clearUserjf(String usid) {
		
		String year=(Tools.getDate(Tools.getDays(), -730)).substring(0, 4);
		String hql="delete from Userjfxt where substr(jfstdt,1,4)='"+year+"' and usid='"+usid+"'";
		this.bulkUpdate(hql);
		
	}
	//�����û�ID��ȡ�û�����
	public List getByUsidUserjf(String usid) {
		String hql="select sum(userjf) as jf from  Userjfxt where usid=? and isvalid='1' ";
		return this.find(hql,new Object[]{usid});
	}
	//��ȡ�û��Ļ�������
	public List getUserjfNumb(String usid) {
		String hql="select sum(userjfnumb) from Userjfxt where usid=? isvalid='1' ";
		return this.find(hql,new Object[]{usid});
	}
	
	//��ȡ�������ֵ
	public List getMaxSeq() { 
		String hql = "select max(u.seq) as seq from Userjfxt u";
		return this.find(hql);
	}
	
	//���ݻ��ֻ�û��ֱ���
	public List getblByjf(int userjf ){
		String hql=" from Userjfxfgz where isvalid='1' and stnumb<="+userjf+" and ednumb>="+userjf;
		return this.find(hql);
	}	
	
	//����û�����
	public void delUserjfByUsid(String usid){
		String hql="delete from Userjfxt where usid=?";
		this.bulkUpdate(hql,new Object[]{usid});
	}
	/**
	 * �����û��������û����ֵ�״̬
	 * Describe:
	 * @auth:huangyuqi
	 * @param usid
	 * return:void
	 * Date:2011-12-8
	 */
	public void updateUserjfByUsid(String usid){
		String hsql =" from Userjfxt where usid='"+usid+"'";
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			for (int j = 0; j < list.size(); j++) {
				Userjfxt userjf = (Userjfxt)list.get(j);
				userjf.setIsvalid("0");//��Ч
				this.update(userjf);
			}
			
		}
	}
}
