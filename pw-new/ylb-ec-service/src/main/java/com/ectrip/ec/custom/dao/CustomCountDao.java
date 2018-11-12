package com.ectrip.ec.custom.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.custom.dao.idao.ICustomCountDao;

public class CustomCountDao extends GenericDao implements ICustomCountDao {
	/**
	 * *
	 * Describe:ϵͳ�û�
	 * @see com.ectrip.custom.dao.idao.ICustomCountDao#customlist(java.lang.String, java.lang.String, long, java.lang.String, java.lang.String)
	 * @param lgtp
	 * @param ttlb
	 * @param ibusinessid
	 * @param rzti
	 * @param ldti
	 * @return
	 * @author chenxinhao
	 * Date:2012-11-2
	 */
	public List customlist(String lgtp,String ttlb,long ibusinessid,String rzti,String ldti){
		StringBuffer sql = new StringBuffer();
		sql.append(" from Custom c");
		if(ibusinessid==0L){
			if(ttlb.equals("")){//ɢ��
				sql.append(" where c.lgtp='"+lgtp+"' ");
			}else if(ttlb.equals("01")){//����
				sql.append(" where c.lgtp='"+lgtp+"' and c.ttlb='"+ttlb+"' and c.ibusinessid!=3");
			}else if(ttlb.equals("02")){//����
				sql.append(" where c.lgtp='"+lgtp+"' and c.ttlb='"+ttlb+"' ");
			}
		}else{//�Ӵ�
			sql.append(" where c.lgtp='"+lgtp+"' and c.ttlb='"+ttlb+"' and c.ibusinessid="+ibusinessid);
		}
		if(!rzti.equals("")&&!ldti.equals("")){
			sql.append(" and c.ldate>='"+rzti+"' and c.ldate<='"+ldti+"'");
		}
		List list = this.find(sql.toString());
		return list;
	}
	
}

