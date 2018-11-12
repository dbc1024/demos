package com.ectrip.ec.balance.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.app.model.Vipbalance;
import com.ectrip.ec.balance.dao.idao.IBalanceCenterDAO;
import com.ectrip.ec.common.CommonUtil;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
@Repository
public class BalanceCenterDAO extends GenericDao implements IBalanceCenterDAO {

	/**
	 * �õ��û�Ԥ�������* Describe:
	 * 
	 * @see com.ectrip.balance.dao.idao.IBalanceCenterDAO#getsumMoney(java.lang.String)
	 * @param usid
	 * @return
	 * @author huangyuqi Date:2011-11-11
	 */
	public float getsumMoney(String usid) {
		// Ϊ�˱���ϵͳ���һ��
		try {
			return this.getsumjifen(usid);
		} catch (Exception e) {
			System.out.append("ddd=" + e.toString());
			return 0;
		}
	}

	/**
	 * ��ȡ�������ֵ Describe:
	 * 
	 * @auth:huangyuqi
	 * @return return:int Date:2011-11-16
	 */
	public int getMaxSeq(String tablename, String column) {
		int seq = 0;
		String sql = "select max(h." + column + ") from " + tablename + " h";
		List seqList = this.find(sql);
		if (seqList.size() > 0) {
			if (seqList.get(0) == null) {
				seq = 0;
			} else {
				seq = Integer.parseInt(seqList.get(0) == null ? "0" : seqList.get(0).toString());
			}
		}
		return seq;
	}

	/** ȡ�û���Ԥ������� */
	public float getsumjifen(String usid) {
		// ���㵱ǰ���
		Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, usid);

		if (vipbalance == null) {
			double ye = getInitsumjifen(usid);
			vipbalance = new Vipbalance();
			vipbalance.setUsid(usid);
			vipbalance.setAcctype("01");
			vipbalance.setPoint(ye);
			save(vipbalance);
		}
		// Ĭ�ϲ�ѯ������list���ŵ���һ��Object���飬����������list���ŵĲ�����Ĭ�ϵ�Object�����ˣ�����Map������
		float rc_float = vipbalance.getPoint().floatValue();

		// ��ȥ����δ��˵Ľ��
		List list = this.find("from Yfktoxj where ddzt='97' and usid='" + usid + "'");
		if (list != null && list.size() > 0) {
			list = this.find("select sum(mont) as mont from Yfktoxj where ddzt='97' and usid='" + usid + "'");
			rc_float = rc_float
					- (list != null && list.size() > 0 ? ((Double) list.get(0)).floatValue() : new Float(0));
		}

		return rc_float;

	}

	/** ȡ�û���Ԥ������� */
	public float getInitsumjifen(String usid) {
		String hql = " select new map( sum ( yfk.point * yfk.yfklb ) as sumjifen ) From Useryfk yfk  where yfk.usid =  '"
				+ usid + "'";
		List list = this.find(hql);
		// Ĭ�ϲ�ѯ������list���ŵ���һ��Object���飬����������list���ŵĲ�����Ĭ�ϵ�Object�����ˣ�����Map������
		float rc_float = 0;
		if (list.size() > 0) {
			// һ����¼�����е��ֶ�ֵ����map���һ��Ԫ��,key���ַ���0,1,2,3....��value���ֶ�ֵ
			// �����hql��Ϊ��String hql =
			// " select new map(name as username,passwd as password) from Users";,��ôkey�������ַ���0,1,2...�ˣ�����"username","password"��
			for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
				HashMap map = (HashMap) iterator2.next();
				Double sumjifen = (Double) map.get("sumjifen"); // ȡ���˶������
				if (sumjifen == null) {
					sumjifen = new Double(0D);
				} else {
					rc_float = sumjifen.floatValue();
				}
			}
		} else {
			rc_float = 0;
		}
		return rc_float;
	}

	/**
	 * Ԥ����棬�����������������������ά��Ԥ����ϸ�����һ�� ��� �޸� 2012 �� 3�� 15 �����˶�������ת��ƽ̨�ʻ��ϡ�
	 */
	public int useryfksave(Useryfk yfk, Double tpsx) {
		// ���㵱ǰ���
		Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, yfk.getUsid());
		if (vipbalance == null) {
			double ye = getInitsumjifen(yfk.getUsid());
			vipbalance = new Vipbalance();
			vipbalance.setUsid(yfk.getUsid());
			vipbalance.setAcctype("01");
			vipbalance.setPoint(0.0);
//			vipbalance.setPoint(vipbalance.getPoint() + yfk.getPoint() * yfk.getYfklb());
		}
		vipbalance.setPoint(vipbalance.getPoint() + yfk.getPoint() * yfk.getYfklb());

		//���ö�Ӧ��ֵ������һ�е�����Э���е���
		float vippoint  = CommonUtil.getUserMaxYfk(yfk.getUsid());  //��ǰ���
		//�ϴ�����ȥ���β���
		yfk.setVippoint(new Double(vippoint  + yfk.getYfklb() * yfk.getPoint()));

		save(yfk);
		// ƽ̨ϵͳ�ʺ�
		if (tpsx != 0) { // 2012-3-15 �޸� �˶�ʱ���ַ���ת��ƽ̨�ʻ��ϡ�
			List sysList = this.find(" from Sysparv5 where id.pmky='SYFK' and id.pmcd='01'");
			Sysparv5 sys = null;

			sys = (Sysparv5) sysList.get(0);

			Useryfk useryfkpd = new Useryfk();
			useryfkpd.setBdate(Tools.getTodayString());
			useryfkpd.setCztp(0);
			useryfkpd.setNote(yfk.getUsid() + "�ͻ��˶�������");
			useryfkpd.setOrderid(yfk.getOrderid());
			useryfkpd.setPoint(new Double(tpsx));
			useryfkpd.setSeq(getMaxSeq("Useryfk", "seq")+1);
			useryfkpd.setUsid(sys.getPmva());
			useryfkpd.setYfklb(1);
			useryfkpd.setYfksc("14");

			//���ö�Ӧ��ֵ������һ�е�����Э���е���
			float vippoint2  = CommonUtil.getUserMaxYfk(useryfkpd.getUsid());  //��ǰ���
			//�ϴ�����ȥ���β���
			useryfkpd.setVippoint(new Double(vippoint2  + useryfkpd.getYfklb() * useryfkpd.getPoint()));

			save(useryfkpd); // �ͻ���Ԥ�����С
		}

		this.saveOrUpdate(vipbalance);
		return 1;
	}

	/**
	 * Ԥ����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param orid������
	 * @param typesԤ�������
	 *            ��1����-1���٣�
	 * @param yfkfsԤ������Դ
	 *            (01��ֵԤ����02�˶�תԤ����03����Ԥ����04Ԥ����ת�ֽ�05��������תԤ����15����תԤ����)
	 * @param mont���
	 * @param note��ע
	 *            return:void Date:2011-12-8
	 */
	public void saveUseryfk(String orid, int types, String yfkfs, Double mont, String note) {
		Useryfk yfk = new Useryfk();
		int seqs = 0;
		seqs = getMaxSeq("Useryfk", "seq");
		yfk.setSeq(seqs + 1);

		MOrder morder = (MOrder) this.get(MOrder.class, orid);

		yfk.setUsid(morder.getUsid());// �û�
		yfk.setBdate(Tools.getTodayString());
		yfk.setOrderid(orid);
		yfk.setPoint(mont);
		yfk.setYfklb(types);
		yfk.setYfksc(yfkfs); // �̻�
		yfk.setCztp(0);

		Sysparv5Id sysid = new Sysparv5Id();
		sysid.setPmcd(yfkfs);
		sysid.setPmky("YFKS");
		Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class, sysid);
		if (sysparv5 != null) {
			yfk.setNote(sysparv5.getPmva());// ��ע
		}
		yfk.setSzmemo(note);

		Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, yfk.getUsid());
		if (vipbalance == null) {

			double ye = getInitsumjifen(yfk.getUsid());
			vipbalance = new Vipbalance();
			vipbalance.setUsid(yfk.getUsid());
			vipbalance.setAcctype("01");
			vipbalance.setPoint(ye);
		}
		vipbalance.setPoint(vipbalance.getPoint() + yfk.getPoint() * yfk.getYfklb());

		this.useryfksave(yfk, 0d);
		this.saveOrUpdate(vipbalance);
	}

	/**
	 * Ԥ����� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid
	 *            Ҫ�ı�Ԥ������û�
	 * @param orid������
	 * @param typesԤ�������
	 *            ��1����-1���٣�
	 * @param yfkfsԤ������Դ
	 *            (01��ֵԤ����02�˶�תԤ����03����Ԥ����04Ԥ����ת�ֽ�05��������תԤ����15����תԤ����)
	 * @param mont���
	 * @param note��ע
	 *            return:void Date:2011-12-8
	 */
	public void saveUseryfk(String usid, String orid, int types, String yfkfs, Double mont, String note) {
		Useryfk yfk = new Useryfk();
		int seqs = 0;
		seqs = getMaxSeq("Useryfk", "seq");
		yfk.setSeq(seqs + 1);

		yfk.setUsid(usid);// �û�
		yfk.setBdate(Tools.getTodayString());
		yfk.setOrderid(orid);
		yfk.setPoint(mont);
		yfk.setYfklb(types);
		yfk.setYfksc(yfkfs); // �̻�
		yfk.setCztp(0);

		Sysparv5Id sysid = new Sysparv5Id();
		sysid.setPmcd(yfkfs);
		sysid.setPmky("YFKS");
		Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class, sysid);
		if (sysparv5 != null) {
			yfk.setNote(sysparv5.getPmva());// ��ע
		}
		yfk.setSzmemo(note);

		Vipbalance vipbalance = (Vipbalance) get(Vipbalance.class, yfk.getUsid());
		if (vipbalance == null) {

			double ye = getInitsumjifen(yfk.getUsid());
			vipbalance = new Vipbalance();
			vipbalance.setUsid(yfk.getUsid());
			vipbalance.setAcctype("01");
			vipbalance.setPoint(ye);
		}
		vipbalance.setPoint(vipbalance.getPoint() + yfk.getPoint() * yfk.getYfklb());

		this.useryfksave(yfk, 0d);
		this.saveOrUpdate(vipbalance);
	}

	/**
	 * ���ݶ����Ų�����һ���û�Ԥ������Ϣ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param orid
	 * @return return:Useryfk Date:2011-12-8
	 */
	public Useryfk queryUseryfkByOrid(String orid) {
		Useryfk useryfk = null;
		String hsql = " from Useryfk where orderid ='" + orid + "' order by bdate desc ";
		List list = this.findTopNumb(hsql, 1);
		if (list != null && list.size() >= 1) {
			useryfk = (Useryfk) list.get(0);
		}
		return useryfk;
	}

	/**
	 * ����Ԥ����
	 */
	public int useryfksave(Useryfk yfk) {
		// TODO Auto-generated method stub
		return useryfksave(yfk, 0d);
	}


}
