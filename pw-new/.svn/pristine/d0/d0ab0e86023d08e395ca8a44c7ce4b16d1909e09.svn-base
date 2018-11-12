package com.ectrip.ec.report.system.datereport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportsListDAO;

public class ReportsListDAO extends GenericDao implements IReportsListDAO {

	/**
	 * ��������ƾ֤������(������ƾ֤��õ�������Դ) Describe:
	 * 
	 * @auth:huangyuqi return:void Date:2011-12-17
	 */
	public void updateSaleTicketList(String dates) {
		Connection con = null;
		PreparedStatement pstms = null;
		try {
			con = this.getConnection();

			String hsql = " delete Stssalesvouchertablist where substr(dtmakedate,1,10)='"
					+ dates + "' ";
			String sql = "insert into Stssalesvouchertablist l (l.isalesvoucherid,l.iticketstationid,l.iscenicid,l.iticketwinid,l.szsalesvoucherno,l.ibusinessid,l.bisintegral,l.byprintinvoice,l.bysplitway,l.bisreturn,l.bysalesvouchertype,l.isticketstationid,l.issalesvoucherid,l.forcedrefund,l.sztravelbillno,l.isalesmanid,l.iregionalid,l.usid,l.tdlx,l.dyusid,l.bypostrecord,l.iaccountreceivable,l.iacceptmoney,l.igivechange,l.ihandler,l.ipayeer,l.imaker,l.iauditor,l.iyear,l.imonth,l.iday,l.dtmakedate,l.dtauditdate,l.bysalesvoucherstate,l.bispay,l.bispayee,l.mhandcharge) select s.isalesvoucherid,s.iticketstationid,s.iscenicid,s.iticketwinid,s.szsalesvoucherno,s.ibusinessid,s.bisintegral,s.byprintinvoice,s.bysplitway,s.bisreturn,s.bysalesvouchertype,s.isticketstationid,s.issalesvoucherid,s.forcedrefund,s.sztravelbillno,s.isalesmanid,s.iregionalid,s.usid,s.tdlx,s.dyusid,s.bypostrecord,s.iaccountreceivable,s.iacceptmoney,s.igivechange,s.ihandler,s.ipayeer,s.imaker,s.iauditor,s.iyear,s.imonth,s.iday,s.dtmakedate,s.dtauditdate,s.bysalesvoucherstate,s.bispay,s.bispayee,s.mhandcharge from Stssalesvouchertab s where substr(s.dtmakedate,1,10)='"
					+dates + "' ";
			pstms = con.prepareStatement(hsql);
			int b = pstms.executeUpdate();

			pstms = con.prepareStatement(sql);
			int a = pstms.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("�׳�������ʱ�쳣");
			}
			throw new RuntimeException("�׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("�׳�������ʱ�쳣");
			}
		}

	}

	/**
	 * �����Ʊ��ʷ����(����Ʊ��Ʊ�ӱ�õ�������Դ) Describe:
	 * 
	 * @auth:huangyuqi
	 * @param datenums
	 *            ��ǰ���� return:void
	 * 
	 *            Date:2011-12-17
	 */
	public void updateCheckTicketList(String dates,int datenums) {
		Connection con = null;
		PreparedStatement pstms = null;
		try {
			con = this.getConnection();

			String sql = " insert into Stssoldticketsubtablist l ( l.szsoldticketsubid,l.szsoldticketid,l.isalesvoucherdetailsid,l.isalesvoucherid,l.iticketstationid,l.igardengateid,l.iscenicid,l.icrowdkindid,l.itickettypeid,l.iztickettypeid,l.bychecktype,l.dtlastcheckdate,l.bylastcheckdir,l.byconsumemode,l.ipasstimes,l.msingletimes,l.ipassedtimes,l.mlimitconsume,l.msingleconsume,l.mconsumed,l.ipartitionsign,l.iversion,l.dtbegindate,l.dtenddate,l.szwicketsetinfo,l.byisout,l.tripid ) select s.szsoldticketsubid,s.szsoldticketid,s.isalesvoucherdetailsid,s.isalesvoucherid,s.iticketstationid,s.igardengateid,s.iscenicid,s.icrowdkindid,s.itickettypeid,s.iztickettypeid,s.bychecktype,s.dtlastcheckdate,s.bylastcheckdir,s.byconsumemode,s.ipasstimes,s.msingletimes,s.ipassedtimes,s.mlimitconsume,s.msingleconsume,s.mconsumed,s.ipartitionsign,s.iversion,s.dtbegindate,s.dtenddate,s.szwicketsetinfo,s.byisout,s.tripid from Stssoldticketsubtab s  where s.ipassedtimes>0 and  substr(s.dtenddate,1,10)='"
					+ Tools.getDate(dates, datenums) + "' ";

			pstms = con.prepareStatement(sql);
			int a = pstms.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("�׳�������ʱ�쳣");
			}
			throw new RuntimeException("�׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("�׳�������ʱ�쳣");
			}
		}
	}
	
	/**
	 * ��ʷ�������ݲ�ѯ
	 * Describe:
	 * @auth:huangyuqi
	 * @param dates��������
	 * return:void
	 * Date:2012-3-30
	 */
	public List queryReportList(String dates){
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement pstms = null;
		ResultSet rs = null;		
		try {
			con = this.getConnection();
			//��ѯ��Ҫ�������ڵ����� 
			String today=Tools.getDays();
			String hsql = "select s.isalesvoucherid as isalesvoucherid from Stssalesvouchertab s where substr(s.dtmakedate,1,10)<'"+dates+"' and s.isalesvoucherid not in (select isalesvoucherid from Stssoldtickettab where dtenddate>='"+today+"') ";
				pstms = con.prepareStatement(hsql);
			rs = pstms.executeQuery();
			
			while(rs.next()){
				Long isalesvoucherid = rs.getLong("isalesvoucherid");//ƾ֤���
				list.add(isalesvoucherid);
			}
			System.out.println("list size hql size:"+list.size());
		} catch (SQLException e) {
			e.printStackTrace();
			try { 
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ�����׳�������ʱ�쳣");
			}
			throw new RuntimeException("��ʷ�����׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ�����׳�������ʱ�쳣");
			}
		}
		return list;		
	}
	/**
	 * ����ƾ֤�Ų�ѯ��ˮ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param dates��������
	 * return:void
	 * Date:2012-3-30
	 */
	public List querySzsalesvoucherno(Long isalesvoucherid){
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement pstms = null;
		ResultSet rs = null;		
		try {
			con = this.getConnection();
			//��ѯ��Ҫ�������ڵ����� 
			String hsql = "select szsalesvoucherno as szsalesvoucherno from Stssalesvouchertab s where isalesvoucherid="+isalesvoucherid;
			pstms = con.prepareStatement(hsql);
			rs = pstms.executeQuery();
			while(rs.next()){
				String szsalesvoucherno = rs.getString("szsalesvoucherno");//ƾ֤
				list.add(szsalesvoucherno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ�����׳�������ʱ�쳣");
			}
			throw new RuntimeException("��ʷ�����׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ�����׳�������ʱ�쳣");
			}
		}
		return list;		
	}
	/**
	 * �ж������Ƿ���ڣ����������ɾ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablename
	 * @param colsname
	 * @param dates
	 * return:void
	 * Date:2012-3-30
	 */
	public void deleteReportListDates(String tablename,String colsname,Long dates){
		Connection con = null;
		PreparedStatement pstms = null;
		ResultSet rs = null;		
		try {
			con = this.getConnection();
			//��ѯ��Ҫ�������ڵ����� 
			String hsql = "select s."+colsname+" as isalesvoucherid from "+tablename+" s where s."+colsname+"="+dates;
			pstms = con.prepareStatement(hsql);
			rs = pstms.executeQuery();
			boolean isuse = false;
			while(rs.next()){
				Long isalesvoucherid = rs.getLong("isalesvoucherid");//ƾ֤���
				isuse= true;
				break;				
			}
			if(isuse){
				String hsql2 = "delete "+tablename+" where "+colsname+"="+dates;
				pstms = con.prepareStatement(hsql2);
				pstms.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ����ɾ�����׳�������ʱ�쳣");
			}
			throw new RuntimeException("��ʷ����ɾ�����׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ����ɾ�����׳�������ʱ�쳣");
			}
		}
	}
	/**
	 * ɾ����ӡ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param tablename
	 * @param colsname
	 * @param dates
	 * return:void
	 * Date:2012-3-30
	 */
	public void deleteSalePrint(String tablename,String colsname,String dates){
		Connection con = null;
		PreparedStatement pstms = null;
		ResultSet rs = null;		
		try {
			con = this.getConnection();
			//��ѯ��Ҫ�������ڵ����� 
			String hsql = "select s."+colsname+" as saleno from "+tablename+" s where s."+colsname+"='"+dates+"' ";
			pstms = con.prepareStatement(hsql);
			rs = pstms.executeQuery();
			boolean isuse = false;
			while(rs.next()){
				String saleno = rs.getString("saleno");
				isuse= true;
				break;				
			}
			if(isuse){
				//ɾ�����ڵ�����
				String hsql2 = "delete "+tablename+" where "+colsname+"="+dates;
				pstms = con.prepareStatement(hsql2);
				pstms.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ����ɾ�����׳�������ʱ�쳣");
			}
			throw new RuntimeException("��ʷ����ɾ�����׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ����ɾ�����׳�������ʱ�쳣");
			}
		}
	}
	/**
	 *  ��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param colsname������
	 * @param tablename�����飨���ݱ��ڵ�һ������ ���ݵı��ڵڶ��������ϸ�ִ�У����������쳣��
	 * @param cols Ҫ�������ݵĲ�ѯ����
	 * @param dates Ҫ����ֵ
	 * return:void
	 * Date:2012-3-30
	 */
	public void addReportListDates(String[] colsname,String[] tablename,String cols,Long dates){
		Connection con = null;
		PreparedStatement pstms = null;
		ResultSet rs = null;	
		
		try {
			con = this.getConnection();
			//��ѯ��Ҫ�������ڵ����� 
			StringBuffer hsql = new StringBuffer();
			hsql.append(" insert into "+tablename[0]+" l (");
			for (int i = 0; i < colsname.length; i++) {
				if(i==colsname.length-1){
					hsql.append("l."+colsname[i]);
				}else{
					hsql.append("l."+colsname[i]+",");
				}
			}			
			hsql.append(" ) select ");
			for (int j = 0; j < colsname.length; j++) {
				if(j==colsname.length-1){
					hsql.append("s."+colsname[j]);
				}else{
					hsql.append("s."+colsname[j]+",");
				}
			}			
			hsql.append("  from "+tablename[1]+" s ");
			hsql.append(" where s."+cols+"="+dates);	
			
			System.out.println("data insert sql:=="+hsql);
			pstms = con.prepareStatement(hsql.toString());			
			pstms.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ����������ʱ�׳�������ʱ�쳣"+e1.getMessage());
			}
			throw new RuntimeException("��ʷ����������ʱ�׳�������ʱ�쳣");
		} finally {
			try {
				pstms.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("��ʷ����������ʱ�׳�������ʱ�쳣");
			}
		}
	}
	
	

}
