package com.ectrip.ec.report.system.ticketsale.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketSaleDAO;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Columnpar;
import com.ectrip.ticket.model.provider.Esbticketstationtab;

public class TicketSaleDAO extends GenericDao implements ITicketSaleDAO {

	/**
	 * ��Ʊ��ˮ��ѯ Describe:
	 * 
	 * @auth:huangyuqi esfemployeetab��¼��
	 * @param iscenicid
	 *            �����̱��
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param type
	 *            �������ͣ�1������ˮ��2��ʷ��ˮ��
	 * @param orid
	 *            ��ˮ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url
	 * @return return:PaginationSupport Date:2011-12-15
	 */
	public PaginationSupport querySaleTicketList(Esfemployeetab esfemployeetab,
			Long iscenicid, String rzti, String ldti, String type, String orid,
			int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if ("1".equals(type)) {
			hsql.append("select new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta where sale.bysalesvouchertype='01'  ");
		} else if ("2".equals(type)) {
			hsql.append("select new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta where sale.bysalesvouchertype='01'  ");
		}
		if (orid == null || "".equals(orid)) {

			if ("1".equals(type)) {
				hsql.append("  and substr(sale.dtmakedate,1,10)='" + rzti
						+ "' ");
			} else if ("2".equals(type)) {
				hsql.append("  and substr(sale.dtmakedate,1,10)>='" + rzti
						+ "' and substr(sale.dtmakedate,1,10)<='" + ldti + "' ");
			}
			if (0L == iscenicid) {
				// ��ȡ������ҵ��Ӧ�ɹ���ķ����̣��������û�ֻ�ܲ�ѯ��Ӧ��������̵���Ϣ��
				if (esfemployeetab.getCompanytype().equals("02")) {
					String scenics = esfemployeetab.getScenics();
					hsql.append(" and sale.iscenicid  in (" + scenics + ") ");
				}
			} else {
				hsql.append(" and sale.iscenicid =" + iscenicid + " ");
			}

		} else {
			hsql.append(" and sale.szsalesvoucherno='" + orid + "' ");
		}

		hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid ");
		hsql.append(" order by sale.szsalesvoucherno desc ");

		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	/**
	 * ��Ʊ��ˮ��ѯ Describe:
	 * 
	 * @auth:huangyuqi esfemployeetab��¼����Ϣ
	 * @param iscenicid
	 *            �����̱��
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param type
	 *            �������ͣ�1������ˮ��2��ʷ��ˮ��
	 * @param orid
	 *            ��ˮ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url
	 * @return return:PaginationSupport Date:2011-12-15
	 */
	public PaginationSupport queryCheckTicketList(
			Esfemployeetab esfemployeetab, Long iscenicid, String rzti,
			String ldti, String type, String orid, int page, int pageSize,
			String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if ("1".equals(type)) {
			hsql.append("select new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta where sale.bysalesvouchertype='01'  ");
			hsql.append(" and sale.id.isalesvoucherid in ( select sold.id.isalesvoucherid from Stssoldticketsubtab sold where sold.ipassedtimes>0 ) ");
		} else if ("2".equals(type)) {
			hsql.append("select new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta where sale.bysalesvouchertype='01'  ");
			hsql.append(" and sale.id.isalesvoucherid in ( select sold.id.isalesvoucherid from Stssoldticketsubtablist sold where sold.ipassedtimes>0 ) ");
		}
		if (orid == null || "".equals(orid)) {

			if ("1".equals(type)) {
				hsql.append("  and substr(sale.dtmakedate,1,10)='" + rzti
						+ "' ");
			} else if ("2".equals(type)) {
				hsql.append("  and substr(sale.dtmakedate,1,10)>='" + rzti
						+ "' and substr(sale.dtmakedate,1,10)<='" + ldti + "' ");
			}
			if (0L == iscenicid) {
				// ��ȡ������ҵ��Ӧ�ɹ���ķ����̣��������û�ֻ�ܲ�ѯ��Ӧ��������̵���Ϣ��
				if (esfemployeetab.getCompanytype().equals("02")) {
					String scenics = esfemployeetab.getScenics();
					hsql.append(" and sale.iscenicid  in (" + scenics + ") ");
				}
			} else {
				hsql.append(" and sale.iscenicid =" + iscenicid + " ");
			}

		} else {
			hsql.append(" and sale.szsalesvoucherno='" + orid + "' ");
		}

		hsql.append("  and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid  ");
		hsql.append(" order by sale.szsalesvoucherno desc ");

		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	/**
	 * ������Ʊ��ŵõ���Ʊ��ϸ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param isalesvoucherid����ƾ֤ID
	 * @param radiobuttonѡ������
	 * @return return:PaginationSupport Date:2011-12-16
	 */
	public PaginationSupport querySaleDetailBySaleId(Long isalesvoucherid,
			int radiobutton, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		String hsql = "select  new map(sli.id.isalesvoucherid as isalesvoucherid,sli.itickettypeid as itickettypeid,sale.szsalesvoucherno as szsalesvoucherno,sta.szstationname as szstationname ,win.szticketwinname as szticketwinname,prd.sztickettypename as sztickettypename,sli.iticketnum  as iticketnum , sli.mactualsaleprice as mactualsaleprice, sale.iaccountreceivable as iaccountreceivable, emp.szemployeename as szemployeename,pro.szscenicname as szscenicname,sli.dtstartdate as dtstartdate,sli.dtenddate as dtenddate ,sli.ipresentnums as ipresentnums,sli.ideratenums as ideratenums ) from Stssalesvouchertab sale,Stssalesvoucherdetailstab sli,Esbticketstationtab sta,Esbticketwintab win,Edmtickettypetab prd ,Esfemployeetab emp,Esbscenicareatab pro where sale.id.isalesvoucherid="
				+ isalesvoucherid
				+ " and sale.id.isalesvoucherid = sli.id.isalesvoucherid and sli.id.iticketstationid = sta.iticketstationid and sli.iticketwinid = win.iticketwinid  and sli.itickettypeid = prd.itickettypeid and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid ";
		ps = this.findPage(hsql, pageSize, page, url);

		List list = ps.getItems();
		String status = "�ѳ�Ʊ";
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("isalesvoucherid") != null) {
					Long saleid = Long.parseLong(map.get("isalesvoucherid")
							.toString());
					Long iticket = Long.parseLong(map.get("itickettypeid")
							.toString());
					String sql2 = "";
					if (3 != radiobutton) {
						sql2 = "select sale.id.isalesvoucherid as isalesvoucherid,sl.iticketnum  as iticketnum from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl where sale.issalesvoucherid="
								+ saleid
								+ " and sl.itickettypeid="
								+ iticket
								+ " and sale.id.isalesvoucherid = sl.id.isalesvoucherid ";
					} else {
						sql2 = "select sale.id.isalesvoucherid as isalesvoucherid,sl.iticketnum  as iticketnum from Stssalesvouchertab sale,Stssalesvoucherdetailstab sl where sale.id.isalesvoucherid="
								+ saleid
								+ " and sl.itickettypeid="
								+ iticket
								+ " and sale.id.isalesvoucherid = sl.id.isalesvoucherid ";
					}
					List stalis = this.find(sql2);
					if (stalis != null && stalis.size() >= 1) {
						status = "����Ʊ";
						Object[] obj = (Object[]) stalis.get(0);
						int num = Integer.parseInt(obj[1].toString());// ��Ʊ��
						map.put("tnum", num);
					} else {
						String sql3 = " select sold.ipassedtimes as ipassedtimes from Stssoldticketsubtablist sold where sold.id.isalesvoucherid = "
								+ saleid + " and sold.itickettypeid=" + iticket;
						List checklsit = this.find(sql3);
						if (checklsit != null && checklsit.size() >= 1) {
							status = "�Ѽ�Ʊ";
						}
					}

					String sql4 = " select s.szticketprintno as szticketprintno from Stssoldtickettab s where s.id.isalesvoucherid = "
							+ saleid + " and s.itickettypeid=" + iticket;
					List ticketnolist = this.find(sql4);
					if (ticketnolist != null && ticketnolist.size() >= 1) {
						Object obj = ticketnolist.get(0);
						map.put("ticketprintno", obj);
					}
					map.put("status", status);
				}
			}
		}

		return ps;
	}

	/**
	 * ������Ʊ��ŵõ���Ʊ��ϸ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param isalesvoucherid
	 * @return return:PaginationSupport Date:2011-12-16
	 */
	public PaginationSupport queryCheckDetialBySaleId(Long isalesvoucherid,
			int page, int pageSize, String url) {
		PaginationSupport ps = null;
		String hsql = " select new map( sold.id.isalesvoucherid as isalesvoucherid,sold.itickettypeid as itickettypeid,sale.szsalesvoucherno as szsalesvoucherno,sta.szstationname as szstationname, pro.szscenicname as szscenicname,gra.szgardengatename as szgardengatename,prd1.sztickettypename as pasztickettypename, prd2.sztickettypename as sztickettypename,sold.ipassedtimes as ipassedtimes,sold.dtbegindate as dtbegindate,sold.dtenddate as dtenddate ) from Stssoldticketsubtab sold,Esbticketstationtab sta,Esbscenicareatab pro,Edmtickettypetab prd1 ,Edmtickettypetab prd2,Esbgardengatetab gra,Stssalesvouchertab sale where sold.id.isalesvoucherid="
				+ isalesvoucherid
				+ " and sold.id.iticketstationid = sta.iticketstationid and pro.iscenicid = sold.iscenicid and prd1.itickettypeid = sold.itickettypeid and prd2.itickettypeid = sold.iztickettypeid and sold.igardengateid = gra.id.igardengateid  and sale.id.isalesvoucherid = sold.id.isalesvoucherid ";

		ps = this.findPage(hsql, pageSize, page, url);

		List list = ps.getItems();
		String status = "�Ѽ�Ʊ";
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("isalesvoucherid") != null) {
					Long saleid = Long.parseLong(map.get("isalesvoucherid")
							.toString());
					Long iticket = Long.parseLong(map.get("itickettypeid")
							.toString());

					String sql4 = " select s.szticketprintno as szticketprintno from Stssoldtickettab s where s.id.isalesvoucherid = "
							+ saleid + " and s.itickettypeid=" + iticket;
					List ticketnolist = this.find(sql4);
					if (ticketnolist != null && ticketnolist.size() >= 1) {
						Object obj = ticketnolist.get(0);
						map.put("ticketprintno", obj);
					}
				}

				map.put("status", status);
			}
		}
		return ps;
	}

	/**
	 * ��Ʊ����Ʊ����Ʊ����Ʊ����ˮ��ѯ Describe:
	 * 
	 * @auth:huangyuqi
	 * @param esfemployeetab��¼
	 *            ��
	 * @param iscenicid�����̱��
	 * @param tripid
	 *            �˴α��
	 * @param rzti��ʼʱ��
	 * @param ldti����ʱ��
	 * @param type��ˮ����
	 *            ��1�����գ�2����ʷ��
	 * @param radiobuttonѡ������
	 * @param oridƾ֤��
	 * @param ticketnoƱ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2012-1-10
	 */
	public PaginationSupport querySaleOrCheckTicketList(
			Esfemployeetab esfemployeetab, Long iscenicid, Long tripid,
			String rzti, String ldti, String type, int radiobutton,
			String orid, String ticketno, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();

		if ("1".equals(type)) {// ������ˮ
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta where   ");
		} else if ("2".equals(type)) {// ��ʷ��ˮ
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta where  ");
		}
		if (0 == radiobutton) {// ��Ʊ
			hsql.append(" sale.bysalesvouchertype='01' ");
		} else if (1 == radiobutton) {// ��Ʊ
			hsql.append(" sale.id.isalesvoucherid in ( select distinct sold.id.isalesvoucherid from Stssoldticketsubtablist sold where sold.ipassedtimes>0 ) ");
		} else if (2 == radiobutton) {// ��Ʊ
			hsql.append(" sale.bysalesvouchertype='04' ");
		} else if (3 == radiobutton) {// ��Ʊ
			hsql.append(" sale.bysalesvouchertype in('02','07') ");
		}

		if (orid != null && !"".equals(orid)) {// ƾ֤��
			hsql.append(" and sale.szsalesvoucherno='" + orid + "' ");
		}
		if (ticketno != null && !"".equals(ticketno)) {// Ʊ��
			hsql.append(" and sale.id.isalesvoucherid in (select distinct s.id.isalesvoucherid  from Stssoldtickettab s where szticketprintno='"
					+ ticketno + "' ) ");
		} else {
			if ("1".equals(type)) {// ������ˮ
				hsql.append("  and substr(sale.dtmakedate,1,10)='" + rzti
						+ "' ");
			} else if ("2".equals(type)) {// ��ʷ��ˮ
				hsql.append("  and substr(sale.dtmakedate,1,10)>='" + rzti
						+ "' and substr(sale.dtmakedate,1,10)<='" + ldti + "' ");
			}
			if (0L == iscenicid) {
				// ��ȡ������ҵ��Ӧ�ɹ���ķ����̣��������û�ֻ�ܲ�ѯ��Ӧ��������̵���Ϣ��
				if (esfemployeetab.getCompanytype().equals("02")) {
					String scenics = esfemployeetab.getScenics();
					hsql.append(" and sale.iscenicid  in (" + scenics + ") ");
				}
			} else {
				hsql.append(" and sale.iscenicid =" + iscenicid + " ");
			}

			if (0L != tripid) {// �˴�
				hsql.append(" and sale.id.isalesvoucherid in (select distinct o.id.isalesvoucherid from Stssoldticketsubtab o where o.tripid="
						+ tripid + ") ");
			}
		}

		hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid ");
		hsql.append(" order by sale.szsalesvoucherno desc ");

		ps = this.findPage(hsql.toString(), pageSize, page, url);

		return ps;
	}

	/**
	 * ��Ʊ����Ʊ����ˮ��ѯ(ǰ̨ר�ã���̨����) Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usids�û�
	 *            �����ַ�������ʽΪ a,b,c��
	 * @param iscenicid�����̱��
	 *            ���ַ�������ʽΪ 1,2,3��
	 * @param tripid
	 *            �˴α�ţ��ַ�������ʽΪ 1,2,3��
	 * @param rzti��ʼʱ��
	 * @param ldti����ʱ��
	 * @param radiobuttonѡ������
	 * @param oridƾ֤��
	 * @param ticketnoƱ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2012-1-10
	 */
	public PaginationSupport querySaleOrCheckTicketListByPage(String usids,
			String iscenicid, String tripid, String rzti, String ldti,
			int radiobutton, String orid, String ticketno, int page,
			int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();

		hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u where   ");
		if (0 == radiobutton) {// ��Ʊ
			hsql.append(" sale.bysalesvouchertype='01' ");
		} else if (1 == radiobutton) {// ��Ʊ
			hsql.append(" sale.id.isalesvoucherid in ( select distinct sold.id.isalesvoucherid from Stssoldticketsubtablist sold where sold.ipassedtimes>0 ) ");
		} else if (2 == radiobutton) {// ��Ʊ
			hsql.append(" sale.bysalesvouchertype='04' ");
		} else if (3 == radiobutton) {// ��Ʊ
			hsql.append(" sale.bysalesvouchertype in('02','07') ");
		}

		if (usids != null && !"".equals(usids)) {
			hsql.append(" and sale.usid in(" + usids + ") ");
		}

		if (orid != null && !"".equals(orid)) {// ƾ֤��
			hsql.append(" and sale.szsalesvoucherno='" + orid + "' ");
		}
		if (ticketno != null && !"".equals(ticketno)) {// Ʊ��
			hsql.append(" and sale.id.isalesvoucherid in (select distinct s.id.isalesvoucherid  from Stssoldtickettab s where szticketprintno='"
					+ ticketno + "' ) ");
		} else {
			hsql.append("  and substr(sale.dtmakedate,1,10)>='" + rzti
					+ "' and substr(sale.dtmakedate,1,10)<='" + ldti + "' ");
			if (iscenicid != null && !"".equals(iscenicid)) {
				hsql.append(" and sale.iscenicid in (" + iscenicid + ") ");
			}

			if (tripid != null && !"".equals(tripid)) {// �˴�
				hsql.append(" and sale.id.isalesvoucherid in (select distinct o.id.isalesvoucherid from Stssoldticketsubtab o where o.tripid in ("
						+ tripid + ")) ");
			}
		}

		hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid ");
		hsql.append(" order by sale.szsalesvoucherno desc ");

		ps = this.findPage(hsql.toString(), pageSize, page, url);

		return ps;
	}

	public PaginationSupport querySaleTicketList(String orid, String type,
			int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if (type.equals("1")) {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5 where sale.szsalesvoucherno='"
					+ orid + "' ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype");
			hsql.append(" order by sale.szsalesvoucherno desc ");
		} else {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5 where sale.szsalesvoucherno='"
					+ orid + "' ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype");
			hsql.append(" order by sale.szsalesvoucherno desc ");
		}
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	public PaginationSupport querySaleTicketbyprintno(String ticketno,
			String type, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if (type.equals("1")) {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettab stk where  (stk.szticketprintno='"
					+ ticketno + "' or stk.myzj='" + ticketno + "' ) ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
			hsql.append(" order by sale.szsalesvoucherno desc ");
		} else {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettablist stk where ( stk.szticketprintno='"
					+ ticketno + "' or stk.myzj='" + ticketno + "' ) ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
			hsql.append(" order by sale.szsalesvoucherno desc ");
		}
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		StringBuffer hsql2 = new StringBuffer();
		if (type.equals("1")) {
			hsql2.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssalesvoucherdetailstab sall where  sall.szstartserial='"
					+ ticketno + "' ");
			hsql2.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype   and sall.id.isalesvoucherid=sale.id.isalesvoucherid and sall.id.iticketstationid=sale.id.iticketstationid");
			hsql2.append(" order by sale.szsalesvoucherno desc ");
		} else {
			hsql2.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssalesvoucherdetailstablist sall where  sall.szstartserial='"
					+ ticketno + "'  ");
			hsql2.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and sall.id.isalesvoucherid=sale.id.isalesvoucherid  and sall.id.iticketstationid=sale.id.iticketstationid");
			hsql2.append(" order by sale.szsalesvoucherno desc ");
		}
		System.out.println("====>>>>hsql2:"+hsql2);
		List list = this.find(hsql2.toString());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				ps.getItems().add(map);
			}
		}
		return ps;
	}

	public String queryszticketprintno(String ticketno, String type) {
		List list = new ArrayList();

		List slist = this
				.find(" from Esbticketstationtab where szstationcode='"
						+ ticketno.substring(0, 1) + "'");
		if (slist != null && slist.size() > 0) {
			Esbticketstationtab et = (Esbticketstationtab) slist.get(0);
			if (type.equals("1")) {
				list = this
						.find("select szticketprintno from Stssoldtickettab where iserialnum="
								+ ticketno.substring(1)
								+ " and id.iticketstationid="
								+ et.getIticketstationid());

			} else {
				list = this
						.find("select szticketprintno from Stssoldtickettablist where iserialnum="
								+ ticketno.substring(1)
								+ " and id.iticketstationid="
								+ et.getIticketstationid());

			}
			if (null == list || list.size() == 0) {
				return "";
			} else {
				return ((String) list.get(0));
			}
		} else {
			return ticketno;
		}

	}

	public PaginationSupport querySaleTicketList(String rzti, String ldti,
			String type, String saletype, int radiobutton, Long iscenicid,
			Long iticketstationid, Long iemployeeid, Long iticketwinid,
			int page, int pageSize, String url) {
		PaginationSupport ps = null;

		StringBuffer hsql = new StringBuffer();
		if (type.equals("1")) {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,substr(sale.dtmakedate,1,10) as dtmakedate,sale.ornote1 as ornote1,sale.ornote2 as ornote2,sale.ornote3 as ornote3,sale.ornote4 as ornote4,sale.ornote5 as ornote5,sale.ornote6 as ornote6,sale.ornote7 as ornote7,sale.ornote8 as ornote8,sale.ornote9 as ornote9,sale.ornote10 as ornote10) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5 where ");

		} else {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate,sale.ornote1 as ornote1,sale.ornote2 as ornote2,sale.ornote3 as ornote3,sale.ornote4 as ornote4,sale.ornote5 as ornote5,sale.ornote6 as ornote6,sale.ornote7 as ornote7,sale.ornote8 as ornote8,sale.ornote9 as ornote9,sale.ornote10 as ornote10) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5 where ");

		}
		hsql.append("  substr(sale.dtmakedate,1,10)>='" + rzti
				+ "' and substr(sale.dtmakedate,1,10)<='" + ldti + "' ");
		if (!saletype.equals("00")) {
			hsql.append(" and sale.bysalesvouchertype='" + saletype + "'");
		}
		if (radiobutton == 0) {
			hsql.append(" and sale.iscenicid=" + iscenicid);
		}
		if (radiobutton == 1) {
			hsql.append(" and sale.id.iticketstationid=" + iticketstationid);
		}
		if (radiobutton == 2) {
			hsql.append(" and sale.ihandler=" + iemployeeid);
		}
		if (radiobutton == 3) {
			hsql.append(" and sale.iticketwinid=" + iticketwinid);
		}
		hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype ");
		hsql.append(" order by sale.szsalesvoucherno desc ");
		System.out.println("===>>>.sql:" + hsql);
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	public List querySaledetail(Long isalesvoucherid, Long iticketstationid,
			String saletype, String type) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		if (type.equals("1")) {
			hsql.append("select distinct new map (detail.id.isalesvoucherid as isalesvoucherid,detail.id.iticketstationid as iticketstationid,detail.id.isalesvoucherdetailsid as isalesvoucherdetailsid,detail.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,detail.mactualsaleprice as mactualsaleprice,detail.iticketnum as iticketnum,detail.dtstartdate as dtstartdate,detail.dtenddate as dtenddate,detail.mtotalamount as mtotalamount,detail.szstartserial as szstartserial,detail.istartid as istartid,detail.mhandcharge as mhandcharge,detail.mderatemoney as mderatemoney,detail.ideratenums as ideratenums,detail.meventmoney as meventmoney,detail.mderatemoney as mderatemoney,detail.iplayerperticket as iplayerperticket) from Stssalesvoucherdetailstab detail,Edmtickettypetab e where ");

		} else {
			hsql.append("select distinct new map (detail.id.isalesvoucherid as isalesvoucherid,detail.id.iticketstationid as iticketstationid,detail.id.isalesvoucherdetailsid as isalesvoucherdetailsid,detail.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,detail.mactualsaleprice as mactualsaleprice,detail.iticketnum as iticketnum,detail.dtstartdate as dtstartdate,detail.dtenddate as dtenddate,detail.mtotalamount as mtotalamount,detail.szstartserial as szstartserial,detail.istartid as istartid,detail.mhandcharge as mhandcharge,detail.mderatemoney as mderatemoney,detail.ideratenums as ideratenums,detail.meventmoney as meventmoney,detail.mderatemoney as mderatemoney,detail.iplayerperticket as iplayerperticket) from Stssalesvoucherdetailstablist detail,Edmtickettypetab e where  ");

		}
		hsql.append(" detail.id.isalesvoucherid=" + isalesvoucherid
				+ " and detail.id.iticketstationid=" + iticketstationid
				+ " and e.itickettypeid=detail.itickettypeid");
		list = this.find(hsql.toString());
		System.out.println("===>>>��Ʊ��ˮ:" + hsql.toString());

		Map map;
		String printno = "";
		String triptime = "";
		Long isalesvoucherdetailsid;
		if (saletype.equals("01")) {
			// ��������

			for (int i = 0; i < list.size(); i++) {
				printno = "";
				map = (Map) list.get(i);
				// ��ϸID
				isalesvoucherdetailsid = Long.parseLong(map.get(
						"isalesvoucherdetailsid").toString());
				// ��ѯƱ��

				List tlist = new ArrayList();
				if (type.equals("1")) {
					tlist = this
							.find("select szticketprintno as szticketprintno,myzj as myzj from Stssoldtickettab st where st.id.isalesvoucherid="
									+ isalesvoucherid
									+ " and st.id.iticketstationid="
									+ iticketstationid
									+ " and st.id.isalesvoucherdetailsid="
									+ isalesvoucherdetailsid);
				} else {
					tlist = this
							.find("select szticketprintno as szticketprintno,myzj as myzj from Stssoldtickettablist st where st.id.isalesvoucherid="
									+ isalesvoucherid
									+ " and st.id.iticketstationid="
									+ iticketstationid
									+ " and st.id.isalesvoucherdetailsid="
									+ isalesvoucherdetailsid);
				}
				for (int j = 0; j < tlist.size(); j++) {
					Object[] obj = (Object[]) tlist.get(j);
					if (printno.equals("")) {
						if (obj[1] == null) {
							printno = (String) obj[0];
						} else {
							printno = (String) obj[0] + "/" + obj[1];
						}

					} else {
						if (obj[1] == null) {
							printno = printno + "," + (String) obj[0];
						} else {
							printno = printno + "," + (String) obj[0] + "/"
									+ obj[1];
						}

					}
				}
				map.put("printno", printno);
				// ��ѯ��ӦƱ�����˴�ʱ��
				List plist = new ArrayList();
				if (type.equals("1")) {
					plist = this
							.find("select dtstartdate from Stscomticketsalesdetailstab st where st.id.isalesvoucherid="
									+ isalesvoucherid
									+ " and st.id.iticketstationid="
									+ iticketstationid
									+ " and st.id.isalesvoucherdetailsid="
									+ isalesvoucherdetailsid + " and tripid>0");
				} else {
					plist = this
							.find("select dtstartdate from Stscomticketsalesdetailstabls st where st.id.isalesvoucherid="
									+ isalesvoucherid
									+ " and st.id.iticketstationid="
									+ iticketstationid
									+ " and st.id.isalesvoucherdetailsid="
									+ isalesvoucherdetailsid + " and tripid>0");
				}
				if (plist != null && plist.size() > 0) {

					triptime = ((String) plist.get(0)).substring(0, 16);
				}
				map.put("triptime", triptime);
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				isalesvoucherdetailsid = Long.parseLong(map.get(
						"isalesvoucherdetailsid").toString());
				printno = map.get("szstartserial").toString();
				map.put("printno", printno);
				List plist = new ArrayList();
				if (type.equals("1")) {
					plist = this
							.find("select dtstartdate from Stscomticketsalesdetailstab st where st.id.isalesvoucherid="
									+ isalesvoucherid
									+ " and st.id.iticketstationid="
									+ iticketstationid
									+ " and st.id.isalesvoucherdetailsid="
									+ isalesvoucherdetailsid + " and tripid>0");
				} else {
					plist = this
							.find("select dtstartdate from Stscomticketsalesdetailstabls st where st.id.isalesvoucherid="
									+ isalesvoucherid
									+ " and st.id.iticketstationid="
									+ iticketstationid
									+ " and st.id.isalesvoucherdetailsid="
									+ isalesvoucherdetailsid + " and tripid>0");
				}
				if (plist != null && plist.size() > 0) {
					triptime = ((String) plist.get(0)).substring(0, 16);
				}
				map.put("triptime", triptime);
			}
		}
		return list;
	}

	public PaginationSupport queryCheckdetail(Long isalesvoucherid,
			Long iticketstationid, String type, int page, int pageSize,
			String url) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		PaginationSupport ps = null;
		if (type.equals("1")) {
			hsql.append("select distinct new map(s.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno, s.szticketprintno as szticketprintno,s.iserialnum as iserialnum,su.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,su.iztickettypeid as iztickettypeid,ez.sztickettypename as zsztickettypename,g.szgardengatename as szgardengatename,su.ipassedtimes as ipassedtimes,su.dtbegindate as dtbegindate,su.dtenddate as dtenddate,su.dtmakedate as dtmakedate,su.isvalid as isvalid ,s.manyouno as manyouno) from Stssalesvouchertab sale,Stssoldtickettab s ,Stssoldticketsubtab su,Edmtickettypetab e,Edmtickettypetab ez,Esbgardengatetab g  where ");
		} else {
			hsql.append("select distinct new map(s.id.isalesvoucherid as isalesvoucherid,sale.szsalesvoucherno as szsalesvoucherno, s.szticketprintno as szticketprintno,s.iserialnum as iserialnum,su.itickettypeid as itickettypeid,e.sztickettypename as sztickettypename,su.iztickettypeid as iztickettypeid,ez.sztickettypename as zsztickettypename,g.szgardengatename as szgardengatename,su.ipassedtimes as ipassedtimes,su.dtbegindate as dtbegindate,su.dtenddate as dtenddate,su.dtmakedate as dtmakedate,su.isvalid as isvalid ,s.manyouno as manyouno) from Stssalesvouchertablist sale,Stssoldtickettablist s ,Stssoldticketsubtablist su,Edmtickettypetab e,Edmtickettypetab ez,Esbgardengatetab g  where  ");
		}
		hsql.append(" s.id.isalesvoucherid="
				+ isalesvoucherid
				+ " and s.id.iticketstationid="
				+ iticketstationid
				+ " and s.id.szsoldticketid=su.id.szsoldticketid and s.id.isalesvoucherdetailsid =su.id.isalesvoucherdetailsid and s.id.isalesvoucherid=su.id.isalesvoucherid and s.id.iticketstationid=su.id.iticketstationid and su.itickettypeid=e.itickettypeid and su.iztickettypeid=ez.itickettypeid  and g.id.igardengateid=su.igardengateid  and sale.id.isalesvoucherid=s.id.isalesvoucherid and s.id.iticketstationid=sale.id.iticketstationid");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	/**
	 * * Describe:������Ʊƾ֤�Ų�ѯ ������ˮ
	 * 
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListvouchertab(java.lang.String,
	 *      java.lang.String, java.lang.String[], int, int, java.lang.String)
	 * @param orid
	 * @param type
	 * @param columnslist
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui Date:2012-12-29
	 */
	public PaginationSupport searchListvouchertab(String orid, String type,
			String[] columnslist, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		// hsql.append(" select szsalesvoucherno,id.isalesvoucherid as isalesvoucherid,id.iticketstationid as iticketstationid,");
		hsql.append(" select szsalesvoucherno,");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hsql.append(columnslist[i]);
			} else {
				hsql.append(columnslist[i] + ",");
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount where szsalesvoucherno='"
					+ orid + "'");
		} else {
			hsql.append("  from Ticketsalecountlist where szsalesvoucherno='"
					+ orid + "'");
		}
		hsql.append(" order by dtmakedate desc ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}
	
	/**
	 * *
	 * Describe:������Ʊƾ֤�Ų�ѯ ������ˮ
	 * @see com.ectrip.report.system.ticketsale.dao.idao.ITicketSaleDAO#Listvouchertab(java.lang.String, java.lang.String, java.lang.String[])
	 * @param orid
	 * @param type
	 * @param columnslist
	 * @return
	 * @author lifei
	 * Date:2015-4-16
	 */
	
	public List listvouchertab(String orid, String type,String[] columnslist){
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" select szsalesvoucherno,");
		
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hql.append(columnslist[i]);
			} else {
				hql.append(columnslist[i] + ",");
			}
		}	
		
		if (type.equals("1")) {
			hql.append("  from Ticketsalecount where szsalesvoucherno='"
					+ orid + "'");
		} else {
			hql.append("  from Ticketsalecountlist where szsalesvoucherno='"
					+ orid + "'");
		}
		
		hql.append(" order by dtmakedate desc ");
		
		List list = this.find(hql.toString());
		
		return list;
	}
	
	/**
	 * * Describe:����Ʊ�Ż������֤�Ų�ѯ
	 * 
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketbyprintno(java.lang.String,
	 *      java.lang.String, java.lang.String[], int, int, java.lang.String)
	 * @param ticketno
	 * @param type
	 * @param columnslist
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui Date:2012-12-29
	 */
	public PaginationSupport searchListTicketbyprintno(String ticketno,
			String type, String[] columnslist, int page, int pageSize,
			String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select t.szsalesvoucherno,");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hsql.append("t." + columnslist[i]);
			} else {
				hsql.append("t." + columnslist[i] + ",");
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount t,Stssoldtickettab st ");
		} else {
			hsql.append("  from Ticketsalecountlist t,Stssoldtickettablist st ");
		}
		hsql.append("where st.id.isalesvoucherid=t.id.isalesvoucherid and st.id.iticketstationid=t.id.iticketstationid  and (st.szticketprintno='"
				+ ticketno + "' or st.myzj='" + ticketno + "' )");
		hsql.append(" order by t.dtmakedate desc ");
		ps = this.findPage(hsql.toString(), pageSize, page, url);

		return ps;
	}
	
	/**
	 * 
	 * Describe:����Ʊ�Ż������֤�Ų�ѯ
	 * @author:lifei
	 * @return
	 * return:List
	 * Date:2015-4-16
	 */
	
	public List listTicketbyprintno(String ticketno,String type, String[] columnslist){
		
		StringBuffer hql = new StringBuffer();
		
		hql.append(" select t.szsalesvoucherno,");
		
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hql.append("t." + columnslist[i]);
			} else {
				hql.append("t." + columnslist[i] + ",");
			}
		}
		
		if (type.equals("1")) {
			hql.append("  from Ticketsalecount t,Stssoldtickettab st ");
		} else {
			hql.append("  from Ticketsalecountlist t,Stssoldtickettablist st ");
		}
		hql.append("where st.id.isalesvoucherid=t.id.isalesvoucherid and st.id.iticketstationid=t.id.iticketstationid  and (st.szticketprintno='"
				+ ticketno + "' or st.myzj='" + ticketno + "' )");
		hql.append(" order by t.dtmakedate desc ");
		
		List list = this.find(hql.toString());
		
		return list;
	}

	/**
	 * * Describe:������ˮ��ѯ
	 * 
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketab(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.String, java.lang.Long, java.lang.Long, java.lang.String,
	 *      java.lang.Long, java.lang.String[], int, int, java.lang.String)
	 * @param saletype
	 *            ��������
	 * @param rzti
	 *            ��ʼ����
	 * @param ldti
	 *            ��ֹ����
	 * @param type
	 * @param radiobutton
	 * @param iscenicid
	 *            ������ID
	 * @param iticketstationid
	 *            ��Ʊ��ID
	 * @param iemployeeid
	 *            ��ƱԱID
	 * @param itickettypeid
	 *            ��ƷID
	 * @param zffs
	 *            ���㷽ʽ
	 * @param icrowdkindid
	 *            ��Ⱥ����
	 * @param ibusinessid
	 *            ҵ��
	 * @param usid�ͻ�
	 * @param iborthaddress
	 *            ��Դ��
	 * @param columnslist
	 *            ��ʾ����
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui Date:2012-12-29
	 */
	public PaginationSupport searchListTicketab(String saletype, String rzti,
			String ldti, String type, int radiobutton, Long iscenicid,
			Long iticketstationid, Long iemployeeid, Long itickettypeid,
			String zffs, Long icrowdkindid, Long ibusinessid, String usid,
			Long iborthaddress, String[] columnslist, int page, int pageSize,
			String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select t.szsalesvoucherno,");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hsql.append("t." + columnslist[i]);
			} else {
				hsql.append("t." + columnslist[i] + ",");
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount t");
		} else {
			hsql.append("  from Ticketsalecountlist t");
		}
		hsql.append(" where substr(t.dtmakedate,1,10)>='" + rzti
				+ "' and substr(t.dtmakedate,1,10)<='" + ldti + "' ");
		if (!saletype.equals("00")) {
			hsql.append(" and t.bysalesvouchertype='" + saletype + "'");
		}
		if (radiobutton == 0) {
			hsql.append(" and t.iscenicid=" + iscenicid);
			if (itickettypeid != null && itickettypeid != 0L) {
				hsql.append(" and t.itickettypeid=" + itickettypeid);
			}

		}
		if (radiobutton == 1) {
			hsql.append(" and t.id.iticketstationid=" + iticketstationid);
		}
		if (radiobutton == 2) {
			hsql.append(" and t.ihandler=" + iemployeeid);
		}

		if (zffs != null && !zffs.equals("100")) {
			hsql.append(" and t.isettlementid='" + zffs + "'");
		}
		if (icrowdkindid != null && icrowdkindid != 0) {
			hsql.append(" and t.icrowdkindid=" + icrowdkindid);
		}
		if (ibusinessid != null && !ibusinessid.equals("") && ibusinessid != 0) {
			if(ibusinessid.intValue() == 99 ){
				hsql.append(" and t.ibusinessid=2");
			}else{
				hsql.append(" and t.ibusinessid=" + ibusinessid);
			}
		}
		if (usid != null && !usid.equals("") && !usid.equals("0")) {
			String usids = queryCustoms(usid);
			hsql.append("and t.usid in (" + usids + ")");
		}else{
			if(ibusinessid != null && !ibusinessid.equals("") && ibusinessid.intValue() == 99 ){
				hsql.append(" and t.usid in (select usid from Custom where ttlb='99' and  ibusinessid= 2 and status='01' and ustp ='01')");
			}
		}
		if (iborthaddress != null && !iborthaddress.equals("")) {
			hsql.append("and t.iregionalid=" + iborthaddress);
		}
		hsql.append(" order by t.dtmakedate desc ");
		System.out.println(hsql.toString());
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}
	
	/**
	 * * Describe:������ˮ��ѯ
	 * 
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketab(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.String, java.lang.Long, java.lang.Long, java.lang.String,
	 *      java.lang.Long, java.lang.String[], int, int, java.lang.String)
	 * @param saletype
	 *            ��������
	 * @param rzti
	 *            ��ʼ����
	 * @param ldti
	 *            ��ֹ����
	 * @param type
	 * @param radiobutton
	 * @param iscenicid
	 *            ������ID
	 * @param iticketstationid
	 *            ��Ʊ��ID
	 * @param iemployeeid
	 *            ��ƱԱID
	 * @param itickettypeid
	 *            ��ƷID
	 * @param zffs
	 *            ���㷽ʽ
	 * @param icrowdkindid
	 *            ��Ⱥ����
	 * @param ibusinessid
	 *            ҵ��
	 * @param usid�ͻ�
	 * @param iborthaddress
	 *            ��Դ��
	 * @param columnslist
	 *            ��ʾ����
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lifei 
	 * Date:2015-4-16
	 */
	
	public List listTicketab(String saletype, String rzti,
			String ldti, String type, int radiobutton, Long iscenicid,
			Long iticketstationid, Long iemployeeid, Long itickettypeid,
			String zffs, Long icrowdkindid, Long ibusinessid, String usid,
			Long iborthaddress, String[] columnslist){
		
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select t.szsalesvoucherno,");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hsql.append("t." + columnslist[i]);
			} else {
				hsql.append("t." + columnslist[i] + ",");
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount t");
		} else {
			hsql.append("  from Ticketsalecountlist t");
		}
		hsql.append(" where substr(t.dtmakedate,1,10)>='" + rzti
				+ "' and substr(t.dtmakedate,1,10)<='" + ldti + "' ");
		if (!saletype.equals("00")) {
			hsql.append(" and t.bysalesvouchertype='" + saletype + "'");
		}
		if (radiobutton == 0) {
			hsql.append(" and t.iscenicid=" + iscenicid);
			if (itickettypeid != null && itickettypeid != 0L) {
				hsql.append(" and t.itickettypeid=" + itickettypeid);
			}

		}
		if (radiobutton == 1) {
			hsql.append(" and t.id.iticketstationid=" + iticketstationid);
		}
		if (radiobutton == 2) {
			hsql.append(" and t.ihandler=" + iemployeeid);
		}

		if (zffs != null && !zffs.equals("100")) {
			hsql.append(" and t.isettlementid='" + zffs + "'");
		}
		if (icrowdkindid != null && icrowdkindid != 0) {
			hsql.append(" and t.icrowdkindid=" + icrowdkindid);
		}
		if (ibusinessid != null && !ibusinessid.equals("") && ibusinessid != 0) {
			if(ibusinessid.intValue() == 99 ){
				hsql.append(" and t.ibusinessid=2");
			}else{
				hsql.append(" and t.ibusinessid=" + ibusinessid);
			}
		}
		if (usid != null && !usid.equals("") && !usid.equals("0")) {
			String usids = queryCustoms(usid);
			hsql.append("and t.usid in (" + usids + ")");
		}else{
			if(ibusinessid.intValue() == 99 ){
				hsql.append(" and t.usid in (select usid from Custom where ttlb='99' and  ibusinessid= 2 and status='01' and ustp ='01')");
			}
		}
		if (iborthaddress != null && !iborthaddress.equals("")) {
			hsql.append("and t.iregionalid=" + iborthaddress);
		}
		hsql.append(" order by t.dtmakedate desc ");
		
		List list = this.find(hsql.toString());
		
		return list;
	}

	/**
	 * * Describe:���ۻ��ܲ�ѯ
	 * 
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketab(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String, int,
	 *      java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
	 *      java.lang.String, java.lang.Long, java.lang.Long, java.lang.String,
	 *      java.lang.Long, java.lang.String[], int, int, java.lang.String)
	 * @param saletype
	 *            ��������
	 * @param rzti
	 *            ��ʼ����
	 * @param ldti
	 *            ��ֹ����
	 * @param type
	 * @param radiobutton
	 * @param iscenicid
	 *            ������ID
	 * @param iticketstationid
	 *            ��Ʊ��ID
	 * @param iemployeeid
	 *            ��ƱԱID
	 * @param itickettypeid
	 *            ��ƷID
	 * @param zffs
	 *            ���㷽ʽ
	 * @param icrowdkindid
	 *            ��Ⱥ����
	 * @param ibusinessid
	 *            ҵ��
	 * @param usid�ͻ�
	 * @param iborthaddress
	 *            ��Դ��
	 * @param columnslist
	 *            ��ʾ����
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui Date:2012-12-29
	 */
	public PaginationSupport searchListrsalecount(String saletype, String rzti,
			String ldti, String type, int radiobutton, Long iscenicid,
			Long iticketstationid, Long iemployeeid, Long itickettypeid,
			String zffs, Long icrowdkindid, Long ibusinessid, String usid,
			Long iborthaddress, String[] columnslist, int page, int pageSize,
			String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select ");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				if (columnslist[i].equals("iuseablenessnum")
						|| columnslist[i].equals("mtotalamount")) {
					hsql.append("sum(" + columnslist[i] + ")");
				} else {
					hsql.append(columnslist[i]);
				}
			} else {
				if (columnslist[i].equals("iuseablenessnum")
						|| columnslist[i].equals("mtotalamount")
						|| columnslist[i].equals("mhandcharge")) {
					hsql.append("sum(" + columnslist[i] + "),");
				} else {
					hsql.append(columnslist[i] + ",");
				}
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount ");
		} else {
			hsql.append("  from Ticketsalecountlist ");
		}
		hsql.append(" where substr(dtmakedate,1,10)>='" + rzti
				+ "' and substr(dtmakedate,1,10)<='" + ldti + "' ");
		if (!saletype.equals("00")) {
			hsql.append(" and bysalesvouchertype='" + saletype + "'");
		}
		if (radiobutton == 0) {
			hsql.append(" and iscenicid=" + iscenicid);
			if (itickettypeid != null && itickettypeid != 0) {
				hsql.append(" and itickettypeid=" + itickettypeid);
			}

		}
		if (radiobutton == 1) {
			hsql.append(" and id.iticketstationid=" + iticketstationid);
		}
		if (radiobutton == 2) {
			hsql.append(" and ihandler=" + iemployeeid);
		}

		if (zffs != null && !zffs.equals("100")) {
			hsql.append(" and isettlementid='" + zffs + "'");
		}
		if (icrowdkindid != null && icrowdkindid != 0) {
			hsql.append(" and icrowdkindid=" + icrowdkindid);
		}
		if (ibusinessid != null && !ibusinessid.equals("") && ibusinessid != 0) {
			hsql.append(" and ibusinessid=" + ibusinessid);
		}
		if (usid != null && !usid.equals("") && !usid.equals("0")) {
			hsql.append("and usid='" + usid + "'");
		}
		if (iborthaddress != null && !iborthaddress.equals("")) {
			hsql.append("and iregionalid=" + iborthaddress);
		}

		for (int i = 0; i < columnslist.length; i++) {
			if (!columnslist[i].equals("iuseablenessnum")
					&& !columnslist[i].equals("mtotalamount")
					&& !columnslist[i].equals("mhandcharge")) {

				if (i == 0) {
					hsql.append(" group by " + columnslist[i]);
				} else {
					hsql.append("," + columnslist[i]);
				}
			}

		}

		ps = this.findPage(hsql.toString(), pageSize, page, url);

		return ps;
	}

	/**
	 * * Describe:������ʾ����
	 * 
	 * @see com.ectrip.report.system.ticketsale.dao.idao.ITicketSaleDAO#queryColoumnList(java.lang.String,
	 *      java.lang.String)
	 * @param columnslist
	 * @param tablename
	 * @return
	 * @author lijingrui Date:2013-1-9
	 */
	public List queryColoumnList(String[] columnslist, String tablename) {
		List lst = new ArrayList();
		StringBuffer cols = new StringBuffer();
		for (int j = 0; j < columnslist.length; j++) {
			String sql = " from Columnpar where tablename='" + tablename
					+ "' and id.columnname='" + columnslist[j] + "'";
			Columnpar column = (Columnpar) this.find(sql).get(0);
			lst.add(j, column);

			if (j == columnslist.length - 1) {
				cols.append("'" + columnslist[j] + "'");
			} else {
				cols.append("'" + columnslist[j] + "',");
			}

		}

		String hsql = " from Columnpar where tablename='" + tablename
				+ "' and id.columnname not in (" + cols.toString()
				+ ") order by to_number(note) ";
		List hsqlList = this.find(hsql);
		if (hsqlList != null && hsqlList.size() > 0) {
			for (int i = 0; i < hsqlList.size(); i++) {
				Columnpar columnpar = (Columnpar) hsqlList.get(i);
				lst.add(columnslist.length + i, columnpar);
			}
		}

		return lst;
	}

	public PaginationSupport querySaleTicketbymanyouno(String manyouno,
			String type, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		if (type.equals("1")) {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertab sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettab stk where  stk.manyouno='"
					+ manyouno + "' ");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
			hsql.append(" order by sale.szsalesvoucherno desc ");
		} else {
			hsql.append("select distinct new map( sale.id.isalesvoucherid as isalesvoucherid,sale.id.iticketstationid as iticketstationid,sale.bysalesvouchertype as bysalesvouchertype,v5.pmva as strtype,sale.szsalesvoucherno as szsalesvoucherno,sale.usid as usid,u.lname as lname,u.corpname as corpname,pro.szscenicname as szscenicname,emp.szemployeename as szemployeename,win.szticketwinname as szticketwinname,sale.iaccountreceivable as iaccountreceivable,sta.szstationname as  szstationname ,sale.dtmakedate as dtmakedate) from Stssalesvouchertablist sale,Esfemployeetab emp, Esbscenicareatab pro ,Esbticketwintab win ,Esbticketstationtab sta,Custom u,Sysparv5 v5,Stssoldtickettablist stk where  stk.manyouno='"
					+ manyouno + "'");
			hsql.append(" and emp.iemployeeid = sale.ihandler and pro.iscenicid = sale.iscenicid and win.iticketwinid = sale.iticketwinid and sta.iticketstationid = sale.id.iticketstationid and sale.usid = u.usid and v5.id.pmky='PZLX' and v5.id.pmcd=sale.bysalesvouchertype and stk.id.isalesvoucherid=sale.id.isalesvoucherid and stk.id.iticketstationid=sale.id.iticketstationid  ");
			hsql.append(" order by sale.szsalesvoucherno desc ");
		}
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		StringBuffer hsql2 = new StringBuffer();

		return ps;
	}
	
	
	
	
	public PaginationSupport querySoldticketList(String type,String rzti,String ldti,int page,int pageSize,String url,String orid,String icardno,Long iscenicid){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		System.out.println("=========>>type="+type);
		if(type.equals("1")){//����
			if(orid!=null&&!orid.equals("")){
				hsql=new StringBuffer("select new map(s.id.isalesvoucherid as isalesvoucherid,s.szsalesvoucherno as szsalesvoucherno,s.usid as usid,c.corpname as corpname,c.lname as lname,t.szticketprintno as szticketprintno,t.manyouno as manyouno,ticket.sztickettypename as sztickettypename, emp.empid as imaker,s.dtmakedate as dtmakedate) from Stssoldtickettab t,Stssalesvouchertab s,Custom c,Edmtickettypetab ticket, Esfemployeetab emp where emp.iemployeeid = s.imaker and (s.id.isalesvoucherid="+orid+" or s.szsalesvoucherno='"+orid+"')  and s.id.isalesvoucherid=t.id.isalesvoucherid and  s.usid=c.usid  and t.itickettypeid=ticket.itickettypeid");
			}else{
				hsql=new StringBuffer("select new map(s.id.isalesvoucherid as isalesvoucherid,s.szsalesvoucherno as szsalesvoucherno,s.usid as usid,c.corpname as corpname,c.lname as lname,t.szticketprintno as szticketprintno,t.manyouno as manyouno,ticket.sztickettypename as sztickettypename, emp.empid as imaker,s.dtmakedate as dtmakedate) from Stssoldtickettab t,Stssalesvouchertab s,Custom c,Edmtickettypetab ticket, Esfemployeetab emp where emp.iemployeeid = s.imaker and substr(s.dtmakedate,1,10)>='" + rzti
						+ "' and substr(s.dtmakedate,1,10)<='" + ldti + "' and s.id.isalesvoucherid=t.id.isalesvoucherid  and  s.usid=c.usid  and t.itickettypeid=ticket.itickettypeid and s.iscenicid="+iscenicid+"");
			}
			if(icardno!=null&&!icardno.equals("")){
				hsql.append(" and t.manyouno='"+icardno+"'");
			}
		}else{//��ʷ
			if(orid!=null&&!orid.equals("")){
				hsql=new StringBuffer("select new map(s.id.isalesvoucherid as isalesvoucherid,s.szsalesvoucherno as szsalesvoucherno,s.usid as usid,c.corpname as corpname,c.lname as lname,t.szticketprintno as szticketprintno,t.manyouno as manyouno,ticket.sztickettypename as sztickettypename, emp.empid as imaker,s.dtmakedate as dtmakedate) from Stssoldtickettablist t,Stssalesvouchertablist s,Custom c,Edmtickettypetab ticket, Esfemployeetab emp where emp.iemployeeid = s.imaker and (s.id.isalesvoucherid="+orid+" or s.szsalesvoucherno='"+orid+"')  and s.id.isalesvoucherid=t.id.isalesvoucherid  and  s.usid=c.usid  and t.itickettypeid=ticket.itickettypeid");
			}else{
				hsql=new StringBuffer("select new map(s.id.isalesvoucherid as isalesvoucherid,s.szsalesvoucherno as szsalesvoucherno,s.usid as usid,c.corpname as corpname,c.lname as lname,t.szticketprintno as szticketprintno,t.manyouno as manyouno,ticket.sztickettypename as sztickettypename, emp.empid as imaker,s.dtmakedate as dtmakedate) from Stssoldtickettablist t,Stssalesvouchertablist s,Custom c,Edmtickettypetab ticket, Esfemployeetab emp where emp.iemployeeid = s.imaker and substr(s.dtmakedate,1,10)>='" + rzti
						+ "' and substr(s.dtmakedate,1,10)<='" + ldti + "' and s.id.isalesvoucherid=t.id.isalesvoucherid  and  s.usid=c.usid  and t.itickettypeid=ticket.itickettypeid and s.iscenicid="+iscenicid+"");
			}
			if(icardno!=null&&!icardno.equals("")){
				hsql.append(" and t.manyouno='"+icardno+"'");
			}
		}
		System.out.println("==>>>sql::::>"+hsql.toString());
		ps=this.findPage(hsql.toString(), pageSize, page, url);
		return ps;
	}

	public PaginationSupport showSeatDetail(Long isalesvoucherid,
			Long iticketstationid, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		PaginationSupport ps = null;
		String hsql = "select new map(s.ivenueid as ivenueid, s.iprogramid as iprogramid, s.itripid as itripid, s.iseatid as iseatid, s.startdate as startdate, s.isvalid as isvalid) from Seatsaletab as s where s.id.isalesvoucherid="+isalesvoucherid+" and iticketstationid="+iticketstationid;
		ps = this.findPage(hsql, pageSize, page, url);
		return ps;
	}
	
	public List getAllNameById(PaginationSupport ps){
		List list = new ArrayList();
		String sql = "select vs.szvenueseatsname as szvenueseatsname,v.venueidname as venueidname,vp.szprogramname as szprogramname,t.tripname as tripname from Venueseats vs,Venue v,Venueprogram vp,Trip t where vs.ivenueseatsid=? and vs.ivenueid=? and v.ivenueid=? and vp.iprogramid=? and t.tripid=?";
		for(int i=0;i<ps.getItems().size();i++){
			try {
				List ls = this.findBySqlToMap(sql, ((Map)ps.getItems().get(i)).get("iseatid"), ((Map)ps.getItems().get(i)).get("ivenueid"), ((Map)ps.getItems().get(i)).get("ivenueid"), ((Map)ps.getItems().get(i)).get("iprogramid"),  ((Map)ps.getItems().get(i)).get("itripid"));
				list.add(ls);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * *
	 * Describe:���ݷ�Ʊ�����ѯ��Ϣ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketfapiao(java.lang.String, java.lang.String, java.lang.String[], int, int, java.lang.String)
	 * @param fapiao
	 * @param type
	 * @param columnslist
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2015-3-4
	 */
	public PaginationSupport searchListTicketfapiao(String fapiao,String type,String [] columnslist,int page,int pageSize,String url){
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select t.szsalesvoucherno,");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hsql.append("t." + columnslist[i]);
			} else {
				hsql.append("t." + columnslist[i] + ",");
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount t ");
		} else {
			hsql.append("  from Ticketsalecountlist t ");
		}
		hsql.append(" where t.ornote9='"+fapiao+"' ");
		hsql.append(" order by t.dtmakedate desc ");
		System.out.println(hsql);
		ps = this.findPage(hsql.toString(), pageSize, page, url);

		return ps;
	}
	
	/**
	 * 
	 * Describe:���ݷ�Ʊ�����ѯ��Ϣ
	 * @author:lifei
	 * @param fapiao
	 * @param type
	 * @param columnslist
	 * @return
	 * return:List
	 * Date:2015-4-16
	 */
	public List listTicketfapiao(String fapiao,String type,String [] columnslist){
		
		StringBuffer hsql = new StringBuffer();
		hsql.append(" select t.szsalesvoucherno,");
		for (int i = 0; i < columnslist.length; i++) {
			if (i == columnslist.length - 1) {
				hsql.append("t." + columnslist[i]);
			} else {
				hsql.append("t." + columnslist[i] + ",");
			}
		}
		if (type.equals("1")) {
			hsql.append("  from Ticketsalecount t ");
		} else {
			hsql.append("  from Ticketsalecountlist t ");
		}
		hsql.append(" where t.ornote9='"+fapiao+"' ");
		hsql.append(" order by t.dtmakedate desc ");
		
		List list = this.find(hsql.toString());
		
		return list;
	}
	
	/**
	 * 
	 * Describe:����ƾ֤�Ų�ѯ���Ƶ��˺��Ƶ�����
	 * @author:lifei
	 * @param pzh
	 * @return
	 * return:List
	 * Date:2015-4-15
	 */
	public List imageUrlList(String pzh){
		
		String hql = "select new map(emp.empid as imaker,s.dtmakedate as dtmakedate,s.szsalesvoucherno as szsalesvoucherno) from Stssalesvouchertab s,Esfemployeetab emp where emp.iemployeeid = s.imaker and  s.id.isalesvoucherid = "+pzh;
		
		List list = this.find(hql);
		
		return list;
	}

	public List getSonCustom(List list, String usid) {
		String hsql = " from Custom where susid = '" + usid + "' ";
		List list1 = this.find(hsql);
		if (list1 != null && list1.size() >= 1) {
			for (int i = 0; i < list1.size(); i++) {
				Custom custom = (Custom) list1.get(i);
				list.add(custom.getUsid());
				list = getSonCustom(list, custom.getUsid());
			}
		}
		return list;
	}

	/**
	 * �������û��������û����������壩 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param usid���û���
	 * @return return:String Date:2011-12-31
	 */
	public String queryCustoms(String usid) {
		StringBuffer customs = new StringBuffer();
		List list = new ArrayList();
		list.add(usid);
		
		list = getSonCustom(list,usid);
		if(list!=null && list.size()>=1){
			for (int i = 0; i <list.size(); i++) {
				if(i==list.size()-1){
					customs.append("'"+list.get(i)+"'");
				}else{
					customs.append("'"+list.get(i)+"'"+",");
				}
			}
		}

		return customs.toString();
	}
}
