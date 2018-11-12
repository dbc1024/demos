package com.ectrip.ec.order.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.order.dao.idao.IYOrderDAO;

/**
 * 
 * @ClassName: YOrderDAO
 * @Description: 网上预定服务商订票
 * @author Dicky
 * @date Oct 11, 2011 2:50:08 PM
 * 
 */
@Repository
public class YOrderDAO extends GenericDao implements IYOrderDAO {

    /**
     * (非 Javadoc)
     * <p>
     * Title: saveYOrder
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param yorder
     * @see com.ectrip.order.dao.idao.IYOrderDAO#saveYOrder(com.ectrip.model.order.YOrder)
     */
    public void saveYOrder(YOrder yorder) {
	this.save(yorder);
    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getYOrderList
     * </p>
     * <p>
     * Description:网上预订服务商订单List
     * </p>
     * 
     * @param orid
     * @see com.ectrip.order.dao.idao.IYOrderDAO#getYOrderList(java.lang.String)
     */
    public List getYOrderList(String orid) {
	return this
		.find("select new map(y.id.orid as orid,y.id.iscenicid as iscenicid,y.scenictype as scenictype,m.ortp as ortp,e.szscenicname as szscenicname,y.dtstartdate as dtstartdate,y.dtenddate as dtenddate,y.mont as mont,y.zfmont as zfmont,y.tpmont as tpmont,y.tpsx as tpsx,y.yhamnt as yhamnt) from YOrder y,Esbscenicareatab e,MOrder m where y.id.orid='"
			+ orid + "' and y.id.iscenicid=e.iscenicid and y.id.orid=m.orid");

    }

    /**
     * (非 Javadoc)
     * <p>
     * Title: getYOrderChildList
     * </p>
     * <p>
     * Description: 获取 orid订单的子订单的增量、退订 网上预订服务商订单 信息 变更2017-08-16 增加退款状态，退订时间字段
     * </p>
     * 
     * @param orid
     * @return
     * @see com.ectrip.order.dao.idao.IYOrderDAO#getYOrderChildList(java.lang.String)
     */
    public List getYOrderChildList(String orid) {
	String hql = "select new map(y.id.orid as orid,y.ddzt as ddzt,y.id.iscenicid as iscenicid,y.scenictype as scenictype,m.ortp as ortp,e.szscenicname as szscenicname,y.dtstartdate  as dtstartdate,y.dtenddate as dtenddate,y.mont as mont,y.zfmont as zfmont,y.tpmont as tpmont,y.tpsx as tpsx,y.tpfs as tpfs,y.yhamnt as yhamnt,m.notec as rstate,m.tpdate as rdatetime) from YOrder y, Esbscenicareatab e, MOrder m where y.id.orid  in (select o.orid from MOrder o where o.srid='"
		+ orid + "') and y.id.iscenicid = e.iscenicid and y.id.orid = m.orid order by m.tpdate";
	return this.find(hql);
    }

	@Override
	public YOrder getYOrderInfoById(String orId, Long iscenicid) throws Exception {
		
		return (YOrder) this.get(YOrder.class, new YOrderId(orId,iscenicid));
	}

}
