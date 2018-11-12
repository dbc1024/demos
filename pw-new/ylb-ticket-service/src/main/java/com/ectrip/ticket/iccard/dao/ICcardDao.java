package com.ectrip.ticket.iccard.dao;

import java.util.List;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.iccard.dao.idao.IICcardDao;

public class ICcardDao extends GenericDao implements IICcardDao {
	/**
	 * * Describe:ȡ����ز�����ICID
	 * 
	 * @see com.ectrip.iccard.dao.idao.IICcardDao#getICID(java.lang.String, java.lang.Long)
	 * @param typeid
	 *            01--Ʊ��,02--ҵ��,03--��Ⱥ,04--��Ʊ��,05--��ƱԱ
	 * @param id
	 * @return
	 * @author chenxinhao Date:2012-8-29
	 */
	public String getICID(String typeid, Long id) {
		String sql = "";
		String icid = "";
		if ("01".equals(typeid)) {
			sql = " select icid from Edmtickettypetab where itickettypeid = " + id;
		} else if ("02".equals(typeid)) {
			sql = " select szbusinesscode from Edmbusinesstab where ibusinessid = " + id;
		} else if ("03".equals(typeid)) {
			sql = " select szcrowdkindcode from Edpcrowdkindtab where icrowdkindid = " + id;
		} else if ("04".equals(typeid)) {
			sql = " select icid from Esbticketwintab where iticketwinid = " + id;
		} else if ("05".equals(typeid)) {
			sql = " select iemployeeid from Esfemployeetab where iemployeeid = " + id;
			List lst = this.find(sql);
			if (lst != null && lst.size() > 0) {
				icid = Long.toHexString((Long) lst.get(0));
				return icid;
			}
		}
		List list = this.find(sql);
		if (list != null && list.size() > 0) {
			icid = (String) list.get(0);
		}
		return icid;
	}

}
