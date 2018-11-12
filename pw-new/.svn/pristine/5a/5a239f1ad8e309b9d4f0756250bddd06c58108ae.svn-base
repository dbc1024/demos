package com.ectrip.ec.report.system.datereport.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.job.ijob.TaskLogService;
import com.ectrip.ec.report.system.datereport.dao.idao.IReportsListDAO;
import com.ectrip.ec.report.system.datereport.service.iservice.IReportListNoService;
import com.ectrip.ec.report.system.datereport.service.iservice.ISaleDataTransferService;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

public class RSaleTicketListService extends TaskLogService{

	private IReportsListDAO reportslistDao;
	private IGenericDao genericDao;
	private IReportListNoService reportlistnoService;
    private ISaleDataTransferService saleDataTransferService;

	public IReportsListDAO getReportslistDao() {
		return reportslistDao;
	}
	public void setReportslistDao(IReportsListDAO reportslistDao) {
		this.reportslistDao = reportslistDao;
	}
	public IGenericDao getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(IGenericDao genericDao) {
		this.genericDao = genericDao;
	}
	public IReportListNoService getReportlistnoService() {
		return reportlistnoService;
	}
	public void setReportlistnoService(IReportListNoService reportlistnoService) {
		this.reportlistnoService = reportlistnoService;
	}
    public ISaleDataTransferService getSaleDataTransferService() {
        return saleDataTransferService;
    }

    public void setSaleDataTransferService(ISaleDataTransferService saleDataTransferService) {
        this.saleDataTransferService = saleDataTransferService;
    }
	/**
	 * ҵ�񷽷�*
	 * Describe:(������û�м�������� )
	 * @see com.ectrip.job.ijob.ReportEngine#jobrun()
	 * @return
	 * @author huangyuqi
	 * Date:2011-12-1
	 */
	synchronized public int jobrun() {
		System.out.println("��ʷ����ʼִ��"+Tools.getDayTimes());
		
		try{
			//��ȡ����
			Sysparv5Id sysid = new Sysparv5Id();
			sysid.setPmcd("01");
			sysid.setPmky("DAYS");
			Sysparv5 sysparv5 = (Sysparv5)genericDao.get(Sysparv5.class, sysid);
			int datenumb=0;
			if(sysparv5!=null){
				datenumb = Integer.parseInt(sysparv5.getPmva());
			}else{
				datenumb = 10;
			}
		
			String dates = Tools.getDate(Tools.getDays(), -datenumb);
			
			//���ݷ�ʽ����ÿ����Ʊƾ֤�ɣġ��ȱ�����Ʊƾ֤��ʷ��  Stssalesvouchertablist,
			//ƾ֤������ʷ��  Stssalessettlementtablist��ƾ֤��ϸ��ʷ��Stssalesvoucherdetailstablist��
			//ƾ֤��ϸ�۸������ʷ��   Stscomticketsalesdetailstabls�������۳���Ʊ����ʷ��Stssoldtickettablist��
			//�۳���Ʊ�ӱ���ʷ��Stssoldticketsubtablist
			
			
			//ɾ����ʽ����ɾ����Ʊ��¼Ticketchecklist����ӡ��¼Ticketprintlist,���۳���Ʊ�����֤�ӱ�Stssoldticketattesttab����
			//Ȼ��ɾ��Stssoldticketsubtab��Stssoldtickettab��Stscomticketsalesdetailstab��Stssalesvoucherdetailstab��
			//Stssalessettlementtab��Stssalesvouchertab
			
			//�õ�Ҫ���ݵ�����
            List list = saleDataTransferService.findSaleId(dates);
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = (Map) list.get(i);
                    Long isalesvoucherid = Long.parseLong(map.get("isalesvoucherid").toString());
                    Long iticketstationid = Long.parseLong(map.get("iticketstationid").toString());
                    try{
                        this.saleDataTransferService.transfer(isalesvoucherid,iticketstationid);
                        System.out.println("ת��ʷ:"+isalesvoucherid+","+iticketstationid);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
		
		
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("��ʷ������쳣");
		}
		
		System.out.println("��ʷ�������ִ��"+Tools.getDayTimes());
		
		return 1;
	}
}

