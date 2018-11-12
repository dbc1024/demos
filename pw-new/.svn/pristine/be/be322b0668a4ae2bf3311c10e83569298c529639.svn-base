package com.ectrip.sys.other.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.other.Addresslink;
import com.ectrip.sys.other.dao.idao.IAddresslinkDao;

public class AddresslinkDao extends GenericDao implements IAddresslinkDao {

	public PaginationSupport showAddresslinkList(int pageSize, int page,
			String url) {
		PaginationSupport ps = null;
		String sql = "select new map(a.linkid as linkid,a.regionalid as regionalid,a.address as address,a.traffic as traffic,a.dtmakedate as dtmakedate,g.szregionalname as szregionalname) from Addresslink a,Galsourceregiontab g where a.regionalid = g.iregionalid order by iregionalid";
		ps = this.findPage(sql, pageSize, page, url);
		return ps;
	}

	public Addresslink getAddresslink(Long linkid) throws Exception {
		String sql = "select new map(a.linkid as linkid,a.regionalid as regionalid,a.address as address,a.traffic as traffic,a.dtmakedate as dtmakedate,g.szregionalname as szregionalname) from Addresslink a,Galsourceregiontab g where a.regionalid = g.iregionalid and a.linkid="
				+ linkid;
		List list = this.find(sql);
		if (list != null && !list.isEmpty()) {
			Map map = (Map) list.get(0);
			Addresslink addresslink = new Addresslink();
			BeanUtils.populate(addresslink, map);
			return addresslink;
		}
		return null;
	}

	public void addAddresslink(Addresslink address){
		Long maxid = this.getMaxPk("linkid", "Addresslink");
		address.setLinkid(maxid+1L);
		address.setDtmakedate(Tools.getDayTimes());
		this.save(address);
	}
	
	public void updateAddresslink(Addresslink address){
		Addresslink addresslink = (Addresslink) this.get(Addresslink.class, address.getLinkid());
		addresslink.setAddress(address.getAddress());
		addresslink.setRegionalid(address.getRegionalid());
		this.update(addresslink);
	}
	
	public void deleteAddresslink(Addresslink address){
		Addresslink addresslink = (Addresslink) this.get(Addresslink.class, address.getLinkid());
		this.delete(addresslink);
	}
}
