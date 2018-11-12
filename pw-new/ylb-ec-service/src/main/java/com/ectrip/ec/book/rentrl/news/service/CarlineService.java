package com.ectrip.ec.book.rentrl.news.service;

import java.util.ArrayList;
import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.rentrl.news.dao.idao.ICarlineDao;
import com.ectrip.ec.book.rentrl.news.model.CarTypeModel;
import com.ectrip.ec.book.rentrl.news.service.iservice.ICarlineService;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.rentrl.Carlinedetailtab;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Edmtickettypetab;

public class CarlineService extends GenericService implements ICarlineService {

	private ICarlineDao carlineDao;

	public ICarlineDao getCarlineDao() {
		return carlineDao;
	}

	public void setCarlineDao(ICarlineDao carlineDao) {
		this.carlineDao = carlineDao;
	}

	public List findAllLine() {
		return carlineDao.findAllLine();
	}

	public List findByLine(String pmcd) {
		return carlineDao.findByLine(pmcd);
	}

	public List searchLine(String bycategorytype) {
		return carlineDao.searchLine(bycategorytype);
	}

	public List searchLineByType(String bycategorytype) {
		return carlineDao.searchLineByType(bycategorytype);
	}

	public List findAllImg(Long iscenicid, String itickettypeid) {
		return carlineDao.findAllImg(iscenicid, itickettypeid);
	}

	public List changeCarType(Long iscenicid, String bycategorytype,
			String enddate, String stratdate) {
		return carlineDao.changeCarType(iscenicid, bycategorytype, enddate,
				stratdate);
	}
	public List changeCarType(Long iscenicid, String bycategorytype,
			String enddate, String stratdate,Long ibusinessid) {
		return carlineDao.changeCarType(iscenicid, bycategorytype, enddate,
				stratdate,ibusinessid);
	}

	public List findCarType(String bycategorytype) {
		return carlineDao.findCarType(bycategorytype);
	}

	public List findCarType(Long iscenicid) {
		return carlineDao.findCarType(iscenicid);
	}

	public List findCarTypeImg(Long iscenicid) {
		return carlineDao.findCarTypeImg(iscenicid);
	}

	public List findLineRelated(Long iscenicid) {
		return carlineDao.findLineRelated(iscenicid);
	}
	public List findLineRelatedbyibusinessid(Long iscenicid,Long ibusinessid){
		return carlineDao.findLineRelatedbyibusinessid(iscenicid, ibusinessid);
	}
	public List reserveCar(Long iscenicid, String bycategorytype,String date) {
		return carlineDao.reserveCar(iscenicid, bycategorytype,date);
	}

	public List checkPrice(Long iscenicid, String bycategorytype, String date) {
		return carlineDao.checkPrice(iscenicid, bycategorytype, date);
	}
	/*
	 * ת��DTO(non-Javadoc)
	 * @see com.ectrip.book.rentrl.news.service.iservice.ICarlineService#getOrderCombinDTO(java.lang.String, com.ectrip.model.user.Custom, com.ectrip.book.rentrl.news.model.CarTypeModel, com.ectrip.ticket.model.LprPojo)
	 */
	public OrderCombinDTO getOrderCombinDTO(String orid, Custom custom,
			List<CarTypeModel> carTypeModels, LprPojo lpr) {
		OrderCombinDTO dto = new OrderCombinDTO();
		//������쳣
		if (orid==null || custom==null || carTypeModels==null || carTypeModels.size()==0 || lpr==null) {
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
		String startDate="";
		String endDate="";
		List<OrderProduct> productlist=new ArrayList<OrderProduct>();
		for (CarTypeModel carTypeModel : carTypeModels) {
			if(startDate==null||startDate.equals("")||Tools.getDayNumb(startDate, carTypeModel.getStartdate())<1){
				startDate=carTypeModel.getStartdate();
			}
			if(endDate==null||endDate.equals("")||Tools.getDayNumb(endDate, carTypeModel.getStartdate())>1){
				endDate=carTypeModel.getStartdate();
			}
			OrderProduct product = new OrderProduct();
			product.setIticketid(Long.valueOf(carTypeModel.getItickettypeid()));
			Edmtickettypetab ticket = (Edmtickettypetab) carlineDao.get(Edmtickettypetab.class, product.getIticketid());
			product.setIscenicid(ticket.getIscenicid());
			lpr.setIscenicid(String.valueOf(ticket.getIscenicid()));
			product.setStartdate(carTypeModel.getStartdate());
			product.setIscenicid(ticket.getIscenicid());
			product.setCrowdkindid(1l);
			product.setEnddate(carTypeModel.getStartdate());
			product.setNum(new Long(carTypeModel.getReservenumb()));
			//��ע��Ϣ
			StringBuffer note = new StringBuffer();
			//����������
			note.append("������"+carTypeModel.getHasperson()+",");
			//��ע
			note.append(""+carTypeModel.getMarket());
			//dto.setNote(note.toString());
			product.setNote(note.toString());
			productlist.add(product);
		}
		dto.setNote(carTypeModels.get(0).getMarket()+"");
		lpr.setRzti(startDate);
		dto.setProducts(productlist);
		return dto;
	}

	public List getSafeList(String date) {
		return carlineDao.getSafeList(date);
	}

	public List findCarList(Long iscenicid, String bycategorytype) {
		return carlineDao.findCarList(iscenicid, bycategorytype);
	}

	public Carlinedetailtab findCarlinedetailtab(String pmcd) {
		return carlineDao.findCarlinedetailtab(pmcd);
	}

	public List findCarListByDate(String bycategorytype, String date) {
		return carlineDao.findCarListByDate(bycategorytype, date);
	}
}
