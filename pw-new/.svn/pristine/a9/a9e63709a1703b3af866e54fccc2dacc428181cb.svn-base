package com.ectrip.tourcard.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.WebContant;
import com.ectrip.tourcard.dao.ITourCardSearchDAO;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;

public class TourCardSearchDAO extends GenericDao implements ITourCardSearchDAO {
	
	SessionFactory sessionFactory = (SessionFactory) SpringUtil.getBean("sessionFactory");
	  

    /**
     * 获取旅游卡列表
     *  已存在的旅游卡明细，在有效期内，并且有效次数大于0，视为已绑定
     * @param userId 用户ID
     * @return
     */
    public List<Map> getTourCardList(String userId) {
        String imghost= WebContant.GetKeyValue("IMGHOST")+"/";
        String imgDefgault = "http://"+ WebContant.GetKeyValue("DOMAIN")+"/images/tourcard/tourcard.jpg";
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("select tc.ID as id, tc.NAME as name, tc.price as price, case when exists (select 1 from TOURCARDDETAIL tcd where tc.CODE=tcd.code and tcd.USERID = :userId  and tcd.status>0)  then 'true' else 'false' end as isBinding, NVL2(tc.imageaddr,'"+imghost+"'||tc.imageaddr,'"+imgDefgault+"') as imageurl" +
                " from TOURCARD tc " +
                " where tc.status = 1 order by tc.code desc");
        query.setString("userId", userId);
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        List<Map> list = query.list();
        logger.debug(list.size());
        return list;
    }
    
    /*
     * 复制于ISearchDAO中getTourCard，用于替代原方法
     * */
    public  List<TourCard> getTourCard(String userId){
		String sql = "select distinct c.*" +
				"  from TOURCARDDETAIL t, TOURCARD c" +
				" where" +
				"   t.CODE = c.CODE" +
				"   AND t.STATUS = '1'" +//0未开通；1正常；-1已过期
				"   AND (t.EFFECTIVETIMES  >=t.USEDTIMES OR t.EFFECTIVETIMES IS NULL)" +
				"   AND to_date(t.PERIODSTARTDATE, 'yyyy-mm-dd') <= to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')" +
				"   AND to_date(t.PERIODENDDATE,'yyyy-mm-dd') >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')"+
				"   AND t.USERID=? ";
		Session session = sessionFactory.getCurrentSession();
		try{
			SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(TourCard.class);
			sqlQuery.setParameter(0, userId);
			return sqlQuery.list();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}

	}

    /**
     * 复制于ISearchDAO中getTourCarDetail，用于替代原方法
	 * 获取用户旅游卡
	 * @param carNo 旅游卡卡号
	 * @return
	 */
	public TourCardDetail getTourCarDetail(Object carNo) {
		String hql = " from TourCardDetail where cardNumber=?";
		try{
			List list =  this.find(hql, new Object[]{carNo});
			if(list==null||list.size()<=0){
				return null;
			}else {
				return (TourCardDetail) list.get(0);
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
    
    

}
