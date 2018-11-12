package com.ectrip.ec.line.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.line.dao.idao.ILineDao;
import com.ectrip.ec.line.service.iservice.ILineService;
import com.ectrip.ec.model.line.LineModel;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class LineService extends GenericService implements ILineService {

	private ILineDao lineDao;

	public ILineDao getLineDao() {
		return lineDao;
	}

	public void setLineDao(ILineDao lineDao) {
		this.lineDao = lineDao;
	}

	public List findByLineType(String type, boolean isImage,String condition) {
		return lineDao.findByLineType(type, isImage,condition);
	}

	public PaginationSupport searchLine(String searchKey, String searchString,
			String title, int pageSize, String startIndex, String url) {
		return lineDao.searchLine(searchKey, searchString, title, pageSize,
				startIndex, url);
	}

	public List findImg(Long itickettypeid) {
		return lineDao.findImg(itickettypeid);
	}

	public List findByItickettypeid(Long itickettypeid) {
		return lineDao.findByItickettypeid(itickettypeid);
	}

	public List tJLine() {
		return lineDao.tJLine();
	}

	public List findPriceByDate(Long itickettypeid, String date) {
		return lineDao.findPriceByDate(itickettypeid, date);
	}

	public OrderCombinDTO getOrderCombinDTO(String orid, Custom custom,
			LineModel lineModel, LprPojo lpr) {
		OrderCombinDTO dto = new OrderCombinDTO();
		// ������쳣
		if (orid == null || custom == null || lineModel == null || lpr == null) {
			throw new RuntimeException("���쳣");
		}
		dto.setOrid(orid);
		dto.setUsid(custom.getUsid());
		dto.setIbusiness(custom.getIbusinessid());
		dto.setGroupno(custom.getNote2());
		dto.setAskOrder("");
		List<LprPojo> lprs = new ArrayList<LprPojo>();
		lpr.setSzregionalid("1");
		lprs.add(lpr);
		dto.setLprs(lprs);
		String startDate = lineModel.getStartdate();
		String endDate = lineModel.getStartdate();
		List<OrderProduct> productlist = new ArrayList<OrderProduct>();
		OrderProduct product = new OrderProduct();
		product.setIticketid(new Long(lineModel.getItickettypeid()));
		Edmtickettypetab ticket = (Edmtickettypetab) lineDao.get(
				Edmtickettypetab.class, product.getIticketid());
		product.setIscenicid(ticket.getIscenicid());
		lpr.setIscenicid(String.valueOf(ticket.getIscenicid()));
		product.setStartdate(lineModel.getStartdate());
		product.setIscenicid(ticket.getIscenicid());
		product.setCrowdkindid(01L);//��ʶ����
		product.setEnddate(lineModel.getStartdate());
		product.setNum(new Long(lineModel.getYoungcount()));
		// ��ע��Ϣ
		StringBuffer note = new StringBuffer();
		/*// ����������
		note.append("����������" +lineModel.getYoungcount() + ",");*/
		// ��ע
		note.append(lineModel.getMarket()+"");
		product.setNote(note.toString());
		productlist.add(product);
		if (lineModel.getChildcount()!=null) {
			if (lineModel.getChildcount()>0) {
				OrderProduct p = new OrderProduct();
				p.setIticketid(new Long(lineModel.getItickettypeid()));
				p.setIscenicid(ticket.getIscenicid());
				lpr.setIscenicid(String.valueOf(ticket.getIscenicid()));
				p.setStartdate(lineModel.getStartdate());
				p.setIscenicid(ticket.getIscenicid());
				p.setCrowdkindid(13L);//��ʶ��ͯ
				p.setEnddate(lineModel.getStartdate());
				p.setNum(new Long(lineModel.getChildcount()));
				// ��ע��Ϣ
				StringBuffer note2 = new StringBuffer();
				/*// ����������
				note2.append("��ͯ������" +lineModel.getChildcount() + ",");*/
				// ��ע
				note2.append("");
				p.setNote(note.toString());
				productlist.add(p);
			}
		}
		lpr.setRzti(startDate);                  
		dto.setProducts(productlist);
		dto.setNote(note.toString());
		return dto;
	}

	public boolean updateLineNumbByPay(String orid) {
		return lineDao.updateLineNumbByPay(orid);
	}

	public boolean checkLineNumbByPay(String orid) {
		return lineDao.checkLineNumbByPay(orid);
	}

	public List getJsonTJLine(String top,String keyword) {
		return lineDao.getJsonTJLine(top,keyword);
	}
}
