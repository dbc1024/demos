package com.ectrip.ec.report.system.ticketsale.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.report.system.ticketsale.dao.idao.ITicketSaleDAO;
import com.ectrip.ec.report.system.ticketsale.service.iservice.ITicketSaleService;
import com.ectrip.sys.model.employee.Esfemployeetab;

/**
 * ��Ʊ����Ʊ�����
 * @author huangyuqi
 */
public class TicketSaleService implements ITicketSaleService {

	private ITicketSaleDAO ticketsaleDao;

	public ITicketSaleDAO getTicketsaleDao() {
		return ticketsaleDao;
	}

	public void setTicketsaleDao(ITicketSaleDAO ticketsaleDao) {
		this.ticketsaleDao = ticketsaleDao;
	}
	
	/**
	 * ��Ʊ��ˮ��ѯ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab ��¼��
	 * @param iscenicid �����̱��
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param type �������ͣ�1������ˮ��2��ʷ��ˮ��
	 * @param orid ��ˮ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-15
	 */
	public PaginationSupport querySaleTicketList(Esfemployeetab esfemployeetab,Long iscenicid,String rzti,String ldti,String type,String orid,int page,int pageSize,String url){
		return ticketsaleDao.querySaleTicketList(esfemployeetab,iscenicid, rzti, ldti, type, orid, page, pageSize, url);
	}
	
	/**
	 * ��Ʊ��ˮ��ѯ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab ��¼��
	 * @param iscenicid �����̱��
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param type �������ͣ�1������ˮ��2��ʷ��ˮ��
	 * @param orid ��ˮ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-15
	 */
	public PaginationSupport queryCheckTicketList(Esfemployeetab esfemployeetab,Long iscenicid,String rzti,String ldti,String type,String orid,int page,int pageSize,String url){
		return ticketsaleDao.queryCheckTicketList(esfemployeetab,iscenicid, rzti, ldti, type, orid, page, pageSize, url);
	}

	/**
	 * ������Ʊ��ŵõ���Ʊ��ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param isalesvoucherid����ƾ֤ID
	 * @param radiobuttonѡ������
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-16
	 */
	public PaginationSupport querySaleDetailBySaleId(Long isalesvoucherid,int radiobutton,int page,int pageSize,String url){
		return ticketsaleDao.querySaleDetailBySaleId(isalesvoucherid,radiobutton, page, pageSize, url);
	}

	/**
	 * ������Ʊ��ŵõ���Ʊ��ϸ
	 * Describe:
	 * @auth:huangyuqi
	 * @param isalesvoucherid
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-16
	 */
	public PaginationSupport queryCheckDetialBySaleId(Long isalesvoucherid,int page,int pageSize,String url){
		return ticketsaleDao.queryCheckDetialBySaleId(isalesvoucherid, page, pageSize, url);
	}
	/**
	 * ��Ʊ����Ʊ����Ʊ����Ʊ����ˮ��ѯ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esfemployeetab��¼ ��
	 * @param iscenicid�����̱��
	 * @param tripid �˴α��
	 * @param rzti��ʼʱ��
	 * @param ldti����ʱ��
	 * @param type��ˮ���ͣ�1�����գ�2����ʷ��
	 * @param radiobuttonѡ������
	 * @param oridƾ֤��
	 * @param ticketnoƱ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-1-10
	 */
	public PaginationSupport querySaleOrCheckTicketList(Esfemployeetab esfemployeetab,Long iscenicid,Long tripid,String rzti,String ldti,String type,int radiobutton,String orid,String ticketno,int page,int pageSize,String url){
		return ticketsaleDao.querySaleOrCheckTicketList(esfemployeetab, iscenicid, tripid,rzti, ldti, type, radiobutton, orid, ticketno, page, pageSize, url);
	}
	/**
	 * ��Ʊ����Ʊ����ˮ��ѯ(ǰ̨ר�ã���̨����)
	 * Describe:
	 * @auth:huangyuqi
	 * @param usids�û������ַ�������ʽΪ a,b,c��
	 * @param iscenicid�����̱�� ���ַ�������ʽΪ 1,2,3��
	 * @param tripid �˴α�ţ��ַ�������ʽΪ 1,2,3��
	 * @param rzti��ʼʱ��
	 * @param ldti����ʱ��
	 * @param radiobuttonѡ������
	 * @param oridƾ֤��
	 * @param ticketnoƱ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2012-1-10
	 */
	public PaginationSupport querySaleOrCheckTicketListByPage(String usids,String iscenicid,String tripid,String rzti,String ldti,int radiobutton,String orid,String ticketno,int page,int pageSize,String url){
		return ticketsaleDao.querySaleOrCheckTicketListByPage(usids,iscenicid, tripid, rzti, ldti, radiobutton, orid, ticketno, page, pageSize, url);
	}
	public PaginationSupport querySaleTicketList(String orid,String type,int page,int pageSize,String url){
		return ticketsaleDao.querySaleTicketList( orid, type,page, pageSize, url);
	}
	public PaginationSupport querySaleTicketbyprintno(String ticketno,String type,int page,int pageSize,String url){
		return ticketsaleDao.querySaleTicketbyprintno(ticketno,type, page, pageSize, url);

	}	
	
	public String queryszticketprintno(String ticketno,String type){
		return ticketsaleDao.queryszticketprintno(ticketno,type);
	}	
	public PaginationSupport querySaleTicketList(String rzti,String ldti,String type,String saletype,int radiobutton,Long iscenicid,Long iticketstationid,Long iemployeeid,Long iticketwinid,int page,int pageSize,String url)
	{
		return ticketsaleDao.querySaleTicketList( rzti, ldti, type, saletype, radiobutton, iscenicid, iticketstationid, iemployeeid,iticketwinid, page, pageSize, url);
	}
	public List querySaledetail(Long isalesvoucherid,Long iticketstationid,String saletype,String type){
		return ticketsaleDao.querySaledetail(isalesvoucherid, iticketstationid,saletype, type);
	}
	public PaginationSupport queryCheckdetail(Long isalesvoucherid, Long iticketstationid,String type,int page, int pageSize,
			String url){
		return ticketsaleDao.queryCheckdetail(isalesvoucherid, iticketstationid, type, page, pageSize, url);
	}
	
	/**
	 * *
	 * Describe:������Ʊƾ֤�Ų�ѯ ������ˮ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListvouchertab(java.lang.String, java.lang.String, java.lang.String[], int, int, java.lang.String)
	 * @param orid
	 * @param type
	 * @param columnslist
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-29
	 */
	public  PaginationSupport searchListvouchertab(String orid,String type,String [] columnslist,int page,int pageSize,String url){
		return ticketsaleDao.searchListvouchertab(orid, type, columnslist, page, pageSize, url);
	}
	
	/**
	 * 
	 * Describe:������Ʊƾ֤�Ų�ѯ ������ˮ,����һ��list����
	 * @author:lifei
	 * @param orid
	 * @param type
	 * @param columnslist
	 * @return
	 * return:List
	 * Date:2015-4-16
	 */
	
	public List listvouchertab(String orid, String type,String[] columnslist){
		
		return ticketsaleDao.listvouchertab(orid, type, columnslist);
	}
	
	/**
	 * *
	 * Describe:����Ʊ�Ż������֤�Ų�ѯ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketbyprintno(java.lang.String, java.lang.String, java.lang.String[], int, int, java.lang.String)
	 * @param ticketno
	 * @param type
	 * @param columnslist
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-29
	 */
	public PaginationSupport searchListTicketbyprintno(String ticketno,String type,String [] columnslist,int page,int pageSize,String url){
		return ticketsaleDao.searchListTicketbyprintno(ticketno, type, columnslist, page, pageSize, url);
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
		
		return ticketsaleDao.listTicketbyprintno(ticketno, type, columnslist);
	}
	
	/**
	 * *
	 * Describe:������ˮ��ѯ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketab(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.String[], int, int, java.lang.String)
	 * @param saletype  ��������
	 * @param rzti   ��ʼ����
	 * @param ldti   ��ֹ����
	 * @param type
	 * @param radiobutton
	 * @param iscenicid  ������ID
	 * @param iticketstationid ��Ʊ��ID
	 * @param iemployeeid    ��ƱԱID
	 * @param itickettypeid  ��ƷID
	 * @param zffs  ���㷽ʽ
	 * @param icrowdkindid ��Ⱥ����
	 * @param ibusinessid ҵ��
	 * @param usid�ͻ�
	 * @param iborthaddress ��Դ��
	 * @param columnslist ��ʾ����
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-29
	 */
	public PaginationSupport searchListTicketab(String saletype,String rzti,String ldti,String type,int radiobutton,Long iscenicid,Long iticketstationid,Long iemployeeid,Long itickettypeid,String zffs,Long icrowdkindid,Long ibusinessid,String usid,Long iborthaddress,String [] columnslist, int page,int pageSize,String url){
		return ticketsaleDao.searchListTicketab(saletype, rzti, ldti, type, radiobutton, iscenicid, iticketstationid, iemployeeid, itickettypeid, zffs, icrowdkindid, ibusinessid, usid, iborthaddress, columnslist, page, pageSize, url);
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
		
		return ticketsaleDao.listTicketab(saletype, rzti, ldti, type, radiobutton, iscenicid, iticketstationid, iemployeeid, itickettypeid, zffs, icrowdkindid, ibusinessid, usid, iborthaddress, columnslist);
	}
	
	/**
	 * *
	 * Describe:���ۻ��ܲ�ѯ
	 * @see com.ectrip.report.system.ticketsale.service.iservice.ITicketSaleService#searchListTicketab(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long, java.lang.String[], int, int, java.lang.String)
	 * @param saletype  ��������
	 * @param rzti   ��ʼ����
	 * @param ldti   ��ֹ����
	 * @param type
	 * @param radiobutton
	 * @param iscenicid  ������ID
	 * @param iticketstationid ��Ʊ��ID
	 * @param iemployeeid    ��ƱԱID
	 * @param itickettypeid  ��ƷID
	 * @param zffs  ���㷽ʽ
	 * @param icrowdkindid ��Ⱥ����
	 * @param ibusinessid ҵ��
	 * @param usid�ͻ�
	 * @param iborthaddress ��Դ��
	 * @param columnslist ��ʾ����
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui
	 * Date:2012-12-29
	 */
	public PaginationSupport searchListrsalecount(String saletype,String rzti,String ldti,String type,int radiobutton,Long iscenicid,Long iticketstationid,Long iemployeeid,Long itickettypeid,String zffs,Long icrowdkindid,Long ibusinessid,String usid,Long iborthaddress,String [] columnslist, int page,int pageSize,String url){
		return ticketsaleDao.searchListrsalecount(saletype, rzti, ldti, type, radiobutton, iscenicid, iticketstationid, iemployeeid, itickettypeid, zffs, icrowdkindid, ibusinessid, usid, iborthaddress, columnslist, page, pageSize, url);
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
		
		return ticketsaleDao.listTicketfapiao(fapiao, type, columnslist);
	}
	
	/**
	 * *
	 * Describe:������ʾ����
	 * @see com.ectrip.report.system.ticketsale.dao.idao.ITicketSaleDAO#queryColoumnList(List, java.lang.String)
	 * @param columnslist
	 * @param tablename
	 * @return
	 * @author lijingrui
	 * Date:2013-1-9
	 */
	public List queryColoumnList(String [] columnslist,String tablename){
		return ticketsaleDao.queryColoumnList(columnslist, tablename);
	}
	
	public PaginationSupport querySaleTicketbymanyouno(String manyouno,String type,int page,int pageSize,String url){
		return ticketsaleDao.querySaleTicketbymanyouno(manyouno,type, page, pageSize, url);

	}
	
	/**
	 * ��ѯ�۳���Ʊ
	 * @param type 1:������ˮ 2:��ʷ��ˮ
	 * @param rzti
	 * @param ldti
	 * @param page
	 * @param pageSize
	 * @param url
	 * @param orid
	 * @param icardno
	 * @return
	 */
	public PaginationSupport querySoldticketList(String type,String rzti,String ldti,int page,int pageSize,String url,String orid,String icardno,Long iscenicid){
		return ticketsaleDao.querySoldticketList(type,rzti, ldti, page, pageSize,url,orid,icardno,iscenicid);
	}

	
	/**
	 * Describe:��λ����
	 * @return PaginationSupport
	 * @author luoxin
	 * Date:2013-09-25
	 */
	public PaginationSupport showSeatDetail(Long isalesvoucherid,
			Long iticketstationid, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		return ticketsaleDao.showSeatDetail(isalesvoucherid, iticketstationid, page, pageSize, url);
	}
	
	/**
	 * Describe:��ѯ��λ���ƣ��������ƣ��������ƣ���Ŀ����
	 * @return List
	 * @author luoxin
	 * Date:2013-09-25
	 */
	public List getAllNameById(PaginationSupport ps){
		// TODO Auto-generated method stub
		return ticketsaleDao.getAllNameById(ps);
		
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
		return ticketsaleDao.searchListTicketfapiao(fapiao, type, columnslist, page, pageSize, url);
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
		
		return ticketsaleDao.imageUrlList(pzh);
	}

}
