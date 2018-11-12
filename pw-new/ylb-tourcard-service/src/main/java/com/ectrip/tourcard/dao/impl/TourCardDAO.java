package com.ectrip.tourcard.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.dao.ITourCardDAO;
import com.ectrip.tourcard.feign.service.SysService;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.TourCard;

@Repository
public class TourCardDAO extends GenericDao implements ITourCardDAO{
	
	@Autowired
	private SysService sysService;
	
	public void insertTourCard(TourCard tourCard, Syslog syslog){
		this.save(tourCard);
		
		syslog.setStlg("hello");
		syslog.setBrief("旅游卡");
		syslog.setNote("新增旅游卡{旅游卡代码:"+ tourCard.getCode()+",旅游卡名称:"+ tourCard.getName() +"}");
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = this.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		
		sysService.insertSyslog(syslog);
	}
	
	public void updateTourCard(TourCard tourCard, Syslog syslog){
		this.update(tourCard);
		
		syslog.setStlg("hello");
		if("delete".equals(syslog.getNote())){
			
			syslog.setBrief("旅游卡");
			syslog.setNote("删除旅游卡{旅游卡代码:"+ tourCard.getCode()+",旅游卡名称:"+ tourCard.getName() +"}");
		}else if("up".equals(syslog.getNote())){
			
			syslog.setBrief("旅游卡");
			syslog.setNote("旅游卡上架{旅游卡代码:"+ tourCard.getCode()+",旅游卡名称:"+ tourCard.getName() +"}");
		}else if("down".equals(syslog.getNote())){
			
			syslog.setBrief("旅游卡");
			syslog.setNote("旅游卡下架{旅游卡代码:"+ tourCard.getCode()+",旅游卡名称:"+ tourCard.getName() +"}");
		}else if("edit".equals(syslog.getNote())){
			
			syslog.setBrief("旅游卡");
			syslog.setNote("修改旅游卡{旅游卡代码:"+ tourCard.getCode()+",旅游卡名称:"+ tourCard.getName() +"}");
		}
		
//		syslog.setLogdatetime(Tools.getDayTimes());
//		Long logid = this.getMaxPk("logid", "Syslog");
//		syslog.setLogid(logid + 1);
//		this.save(syslog);
		
		sysService.insertSyslog(syslog);
	}
	
	public TourCard getTourCard(Long cardId){
		TourCard tourCard = (TourCard)this.get(TourCard.class, cardId);
		
		return tourCard;
	}

	public PaginationSupport getTourCardList(CardQuery query, int page, int pageSize, String url) {
		
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		StringBuffer where = new StringBuffer();
		hsql.append("select distinct new map(card.id as id, card.code as code, card.name as name, card.tourCommission as tourCommission, card.profitNum as profitNum, card.price as price, card.iscenicids as iscenicids, card.scenics as scenics, card.bankCardFlag as bankCardFlag, card.identityCardFlag as identityCardFlag, card.identityCardIds as identityCardIds, card.identityCardAreas as identityCardAreas, card.periodType as periodType, card.periodNumber as periodNumber, card.periodStartDate as periodStartDate, card.periodEndDate as periodEndDate, card.timesFlag as timesFlag, card.effectiveTimes as effectiveTimes, card.usePeriod as usePeriod, card.status as status, card.descpt as descpt, card.sortWeight as sortWeight, card.imageAddr as imageAddr) from TourCard card");
		where.append(" where card.status != -1");
		
		if(query != null){
			if(!"".equals(query.getName()) && query.getName() != null){
				where.append(" and card.name like '%"+ query.getName() +"%'");
			}
			if(!"".equals(query.getPeriodType()) && query.getPeriodType() != null){
				where.append(" and card.periodType="+ query.getPeriodType());
			}
			if(!"".equals(query.getProfitNum()) && query.getProfitNum() != null){
				where.append(" and card.profitNum='"+ query.getProfitNum() +"'");
			}
			if(!"".equals(query.getUsePeriod()) && query.getUsePeriod() != null && query.getUsePeriod() != -1){
				where.append(" and card.usePeriod="+ query.getUsePeriod());
			}
		}
		
		where.append(" order by card.sortWeight ASC, card.code DESC");
		
		
		ps = this.findPage(hsql.toString() + where.toString(), pageSize, page, url);
		
		return ps;
	}

	public List<Map> getAllAreasAndSceience() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 通过旅游卡代码查询旅游卡
	 * @param code
	 * @return
	 */
	public TourCard getTourCardByCardCode(String code){
		List<TourCard> list = this.find("from TourCard where status!=-1 and code=?", new Object[]{code});
		if(list == null || list.isEmpty()){
			return null;
		}else{
			TourCard tourCard = list.get(0);
			
			return tourCard;
		}
	}
	
	/**
	 * 通过旅游卡代码查询旅游卡
	 * @param code
	 * @return
	 */
	public List<TourCard> getTourCardByName(String name){
		List<TourCard> list = this.find("from TourCard where status!=-1 and name=?", new Object[]{name});
		
		return list;
	}
	
	/**通过分润协议号查询旅游卡*/
	public List<TourCard> getTourCardByProfitNum(String profitNum){
		List<TourCard> list = this.find("from TourCard where status!=-1 and profitNum=?", new Object[]{profitNum});
		
		return list;
	}

}
