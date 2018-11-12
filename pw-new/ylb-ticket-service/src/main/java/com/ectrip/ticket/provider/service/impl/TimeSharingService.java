package com.ectrip.ticket.provider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.service.GenericService;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.ticket.provider.dao.ITimeSharingDAO;
import com.ectrip.ticket.provider.dao.impl.TimeSharingDAO;
import com.ectrip.ticket.provider.service.ITimeSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TimeSharingService extends GenericService implements ITimeSharingService{

	
	private ITimeSharingDAO timeSharingDAO;
	
	@Autowired
	public void setTimeSharingDAO(ITimeSharingDAO timeSharingDAO) {
		setGenericDao(timeSharingDAO);
		this.timeSharingDAO = timeSharingDAO;
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public Map<String,Object> updateTicket(Long timeId, String productId,String timeStart,String timeEnd,int totalStock) throws Exception {
		// TODO Auto-generated method stub
		//TimeSharingTicketTab
		Map<String,Object> result = new HashMap<String, Object>();
		int code = 201;
		String describle = "修改失败";
		if(timeId > 0) {
			String hql = "from TimeSharingTicketTab where id = "+ timeId + " and productId = '"+productId+"' and totalStock = currStock";
			List list = timeSharingDAO.find(hql);
			if(list != null && list.size()>0) {
				//当前时段没有卖出,可更新
				TimeSharingTicketTab timeSharingTicketTab = (TimeSharingTicketTab) list.get(0);
				timeSharingTicketTab.setTatalStock(totalStock);
				timeSharingTicketTab.setStartDate(timeStart);
				timeSharingTicketTab.setEndDate(timeEnd);
				timeSharingDAO.update(timeSharingTicketTab);
				code = 200;
				describle = "修改成功";
			}else {
				//当前时段有卖出，不允许修改
				code = 202;
				describle = "此时段允许修改不允许修改";
			}
		}else {
			String hql = "select max(id) from TimeSharingTicketTab where productId = '"+productId+"'";
			String maxNum = timeSharingDAO.getMaxNo(hql);
			TimeSharingTicketTab timeSharingTicketTab = new TimeSharingTicketTab(new Long(timeId),timeStart,timeEnd,totalStock,totalStock,0,productId);
			timeSharingDAO.save(timeSharingTicketTab);
			code = 200;
			describle = "新增成功";
		}
		result.put("code", code);
		result.put("desc", describle);
		return result;
		
	}


	public List findTimeTicket(String productId, Long timeId) throws Exception {
		String hql = "from TimeSharingTicketTab where id = " + timeId + " and productId = '" + productId + "'";
		List list = timeSharingDAO.find(hql);
		return list;
	}

	/**
	 * 库存更新
	 * timeId 时段ID
	 * productId 产品ID
	 * totalStock 总库存
	 * count 更新数量
	 * flag 增加或减少操作 add minus 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void UpdateStock(Long timeId, String productId, int count,String flag) throws Exception {
		String hql = "from TimeSharingTicketTab where id = " + timeId + " and productId = '" + productId + "'";
		List list = timeSharingDAO.find(hql);
		if(list != null && list.size()>0) {
			TimeSharingTicketTab timeSharingTicketTab = (TimeSharingTicketTab) list.get(0);
			if (timeSharingTicketTab.getTatalStock() > 0) {
				// 保存同时操纵库存的只有一个线程
				synchronized (timeSharingTicketTab) {
					int totalStock=timeSharingTicketTab.getTatalStock();
					int currStock = timeSharingTicketTab.getCurrStock();
					int saleStock = timeSharingTicketTab.getSaleStock();
					// 增加
					if ("add".equalsIgnoreCase(flag)) {
						currStock = currStock + count;
						if (saleStock >= count && currStock<=totalStock) {
							saleStock = saleStock - count;
						}else
						{
							throw new RuntimeException("当前时段库存不合法");
						}
						// 减少
					} else if ("minus".equalsIgnoreCase(flag)) {
						if (currStock >= count && saleStock+count<=totalStock) {
							currStock = currStock - count;
						}else
						{
							throw new RuntimeException("当前时段库存不足");
						}
						saleStock = saleStock + count;
					}
					timeSharingTicketTab.setCurrStock(currStock);
					timeSharingTicketTab.setSaleStock(saleStock);
				}
				timeSharingDAO.saveOrUpdate(timeSharingTicketTab);
			}
		}else {
			throw new RuntimeException("当前时段产品不存在");
		}
	}

	@Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
	public void saveTimes(TimeSharingTicketTab _SharingTicketTab) throws Exception {
		timeSharingDAO.save(_SharingTicketTab);
	}


	public List findTimeTicketByProductCode(String productId) throws Exception {
		//String hql = "from TimeSharingTicketTab where productId = '"+productId+"' and dayTime= '"+DateUtils.formatDate(new Date(), "yyyy-MM-dd")+"'";
		String hql = "select distinct new map(ts.startDate as startDate,ts.endDate as endDate,ts.tatalStock  as tatalStock,ts.productId as productId )from TimeSharingTicketTab ts where productId = '"+productId+"' order by ts.startDate";
		return timeSharingDAO.find(hql);
	}
	public List findTimeTicketsByProductCode(String productId) throws Exception {
		String hql = "from TimeSharingTicketTab where productId = '"+productId+"'";
		return timeSharingDAO.find(hql);
	}
	
	public List findTimeTicketByProductCode(String productId,String startTime,String endTime) throws Exception {
		String hql = "from TimeSharingTicketTab where productId = '"+productId+"' and dayTime>= '"+startTime+"' and dayTime<= '"+endTime+"'";
		return timeSharingDAO.find(hql);
	}


	public List findTimeTicketByProductCodeAnddate(String sztickettypecode, String date) {
		String hql = "from TimeSharingTicketTab where productId = '"+sztickettypecode+"' and dayTime= '"+date+"' order by id";
		return timeSharingDAO.find(hql);
	}
}
