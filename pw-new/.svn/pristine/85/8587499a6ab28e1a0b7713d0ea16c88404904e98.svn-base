package com.ectrip.ec.userxfjf.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ectrip.base.util.Tools;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.user.Userjfxt;
import com.ectrip.ec.userxfjf.dao.idao.IUserjfxtDAO;
import com.ectrip.ec.userxfjf.service.iservice.IUserjfxtService;
import com.ectrip.sys.model.syspar.Sysparv5;

@Service
public class UserjfxtService implements IUserjfxtService {

	private IUserjfxtDAO userjfxtDao;
	private IBalanceCenterDAO balancecenterDao;

	
	public IUserjfxtDAO getUserjfxtDao() {
		return userjfxtDao;
	}

	public void setUserjfxtDao(IUserjfxtDAO userjfxtDao) {
		this.userjfxtDao = userjfxtDao;
	}

	public IBalanceCenterDAO getBalancecenterDao() {
		return balancecenterDao;
	}

	public void setBalancecenterDao(IBalanceCenterDAO balancecenterDao) {
		this.balancecenterDao = balancecenterDao;
	}

	/**
	 * �û���������
	 * @param usid
	 * @param jflb
	 * @param zmont
	 */
	public void addUserjf(String usid,String jflb,double zmont){
		try {
			String jfstdt = Tools.getDays();
			List maxlist = new ArrayList(); // ����seq�б�
			Userjfxt userjf = new Userjfxt(); // �����û�����
			maxlist = this.userjfxtDao.getMaxSeq();

			Integer seq = null; // ���
			if (maxlist != null && maxlist.size() >= 1
					&& maxlist.get(0) != null) {
				seq = (Integer) maxlist.get(0) + 1;
			} else {
				seq = 1;
			}
			userjf.setSeq(seq);
			userjf.setUsid(usid); 
			userjf.setJfstdt(jfstdt);
			userjf.setJfnumb(1); 
			userjf.setIsvalid("1");
			// �������ѻ���
			if (jflb.equals("01")) {
				String hql="from Sysparv5 where id.pmky='JFTP' and id.pmcd='08'";
				List<Sysparv5> list=userjfxtDao.find(hql);
				double jf=Double.parseDouble(list.get(0).getPmvb());
				double d = zmont * jf;
				int c = String.valueOf(d).indexOf(".");
				userjf.setJflb("01");
				userjf.setUserjf(Integer.parseInt((String.valueOf(d).substring(
						0, c))));
				// �û���������
			} else if (jflb.equals("02")) {
				String hql="from Syspar where id.pmky='JFTP' and id.pmcd='09'";
				List<Sysparv5> list=userjfxtDao.find(hql);
				int jf=Integer.parseInt(list.get(0).getPmvb());
				userjf.setUserjf(jf);
				userjf.setJflb("02"); 
				// �û����ۻ���
			} else if(jflb.equals("03")){
				String hql="from Syspar where id.pmky='JFTP' and id.pmcd='10'";
				List<Sysparv5> list=userjfxtDao.find(hql);
				int jf=Integer.parseInt(list.get(0).getPmvb());
				userjf.setUserjf(jf);
				userjf.setJflb("03"); 
			   //�˵���������
			}else{
				String hql="from Syspar where id.pmky='JFTP' and id.pmcd='11'";
				List<Sysparv5> list=userjfxtDao.find(hql);
				double jf=Double.parseDouble(list.get(0).getPmvb());
				double d = -zmont * jf;
				int c = String.valueOf(d).indexOf(".");
				userjf.setJflb("04");
				userjf.setUserjf(Integer.parseInt((String.valueOf(d).substring(
						0, c))));
			}
			this.userjfxtDao.addUserjf(userjf);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error==" + e.getMessage());
		}
	}
	
	/**
	 * ����û����ڻ���
	 * @param usid
	 */
	public void clearUserjf(String usid){
		this.userjfxtDao.clearUserjf(usid);
	}
	
	/**
	 * �����û�Id��ȡ�û�����
	 * @param usid
	 * @return
	 */
	public List getByUsidUserjf(String usid){
		return this.userjfxtDao.getByUsidUserjf(usid);
	}
	
	/**
	 * ��ȡ�û���������
	 * @param usid
	 * @return
	 */
	public List getUserjfNumb(String usid){
		return this.userjfxtDao.getUserjfNumb(usid);
	}
	
	/**
	 * �����û����ֻ�ȡ�������ѱ���
	 * @param userjf
	 * @return
	 */
	public List getblByjf(int userjf ){
		return this.userjfxtDao.getblByjf(userjf);
	}
	
	 
	/**
	 * ����û�����
	 * @param usid
	 */
	public void delUserjfByUsid(String usid){
		this.userjfxtDao.delUserjfByUsid(usid);
	}
	/**
	 * �������תԤ����
	 * Describe:
	 * @auth:huangyuqi
	 * return:void
	 * Date:2011-12-8
	 */
	public void savleUserjftoUseryfk(Useryfk useryfk){
		//����Ԥ����
		balancecenterDao.useryfksave(useryfk);
		//�����û�����
		userjfxtDao.updateUserjfByUsid(useryfk.getUsid());
	}
	
}
