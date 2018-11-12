package com.ectrip.ticket.checkticket.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.Raftcheck;
import com.ectrip.ticket.checkticket.dao.idao.ICheckDao;
import com.ectrip.ticket.model.order.Checkcount;
import com.ectrip.ticket.sale.countList;
@Repository
public class CheckDao extends GenericDao implements ICheckDao {

	
	public String updatecheckcount(Long iscenicid) {
		// 全景区人次
		// 查询数据是否存在
		// 统计数据开始

		String cs = "";
		String today = Tools.getTodayString();
		Checkcount c = new Checkcount();
		int b = 0;
		if (countList.getStdt() == null || countList.getStdt().equals("")) {
			b=1;
			countList.setStdt(today);
			countList.setInsertnumb(0);
		} else {
			if (!countList.getStdt().equals(today)) {
				b=1;
				countList.setStdt(today);
				countList.setInsertnumb(0);
			}
			countList.setInsertnumb(countList.getInsertnumb() + 1);
			if (countList.getInsertnumb() % 100 == 0) {
				b = 2;
			}
		}

		List coulist = countList.getCoulist();
		coulist = new ArrayList();
		List clist = this
				.find("select new map( count(distinct s.szticketprintno) as numb,sum(tc.int1) as cnumb) from Ticketchecklist tc,Stssoldtickettab s where tc.szsoldticketid=s.id.szsoldticketid and tc.isalesvoucherdetailsid=s.id.isalesvoucherdetailsid and tc.isalesvoucherid=s.id.isalesvoucherid and tc.iticketstationid=s.id.iticketstationid  and  substr(tc.dtmakedate,1,10)='"
						+ today + "' and tc.iscenicid=" + iscenicid);

		Long cnumb = new Long(0);
		Long numb = new Long(0);

		if (clist != null && clist.size() > 0) {
			Map map = (Map) clist.get(0);
			numb = new Long(map.get("numb").toString());
			if(map.get("cnumb")==null||map.get("cnumb").equals("")){
				cnumb = new Long(0);
			}else{
				cnumb = new Long(map.get("cnumb").toString());
			}
		}

		c.setCnumb(cnumb);// 人次

		c.setNumb(numb);

		c.setIscenicid(iscenicid);

		c.setStdt(Tools.getTodayString());

		c.setIgardengateid(new Long(0));

		coulist.add(c);
		if(b>0){
			List ls = this.find(" from Checkcount where stdt='"
					+ c.getStdt() + "' and igardengateid=" + c.getIgardengateid()
					+ " and iscenicid=" + c.getIscenicid());
			if (ls != null && ls.size() > 0) {
				Checkcount c2 = (Checkcount) ls.get(0);
				c2.setNumb(c.getNumb());
				c2.setCnumb(c.getCnumb());
				this.update(c2);
			} else {
				Long seq = this.getMaxPk("seq", "Checkcount");
				c.setSeq(seq + 1);
				this.save(c);
			}
		}


		cs = cs + c.getIgardengateid() + "," + c.getCnumb() + "," + c.getNumb()
				+ "," + c.getIscenicid() + "," + c.getStdt() + "#";

		// 园门人数

		List gclist = new ArrayList();
		try {
			gclist = this
					.find("select new map(tc.igardengateid as igardengateid,count(distinct s.szticketprintno) as numb,sum(tc.int1) as cnumb) from Ticketchecklist tc,Stssoldtickettab s where tc.szsoldticketid=s.id.szsoldticketid and tc.isalesvoucherdetailsid=s.id.isalesvoucherdetailsid and tc.isalesvoucherid=s.id.isalesvoucherid and tc.iticketstationid=s.id.iticketstationid  and  substr(tc.dtmakedate,1,10)='"
							+ today + "' group by tc.igardengateid");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for (int i = 0; i < gclist.size(); i++) {

			Map map = (Map) gclist.get(i);
			Long igardengateid = new Long(map.get("igardengateid").toString());
			Checkcount c1 = new Checkcount();
			Long gnumb = new Long(map.get("numb").toString());
			Long gcnumb =new Long(0);
			if(map.get("cnumb")==null||map.get("cnumb").equals("")){
				gcnumb = new Long(0);
			}else{
				gcnumb = new Long(map.get("cnumb").toString());
			}
			c1.setCnumb(gcnumb);// 人次
			c1.setNumb(gnumb);
			c1.setIscenicid(iscenicid);
			c1.setStdt(Tools.getTodayString());
			c1.setIgardengateid(igardengateid);
			if(b>0){
				List ls = this.find(" from Checkcount where stdt='"
						+ c1.getStdt() + "' and igardengateid=" + c1.getIgardengateid()
						+ " and iscenicid=" + c1.getIscenicid());
				if (ls != null && ls.size() > 0) {
					Checkcount c2 = (Checkcount) ls.get(0);
					c2.setNumb(c1.getNumb());
					c2.setCnumb(c1.getCnumb());
					this.update(c2);
				} else {
					Long seq = this.getMaxPk("seq", "Checkcount");
					c1.setSeq(seq + 1);
					this.save(c1);
				}
			}
			coulist.add(c1);
			cs = cs + c1.getIgardengateid() + "," + c1.getCnumb() + ","
					+ c1.getNumb() + "," + c1.getIscenicid() + "," + c1.getStdt() + "#";
		}
		countList.setCoulist(coulist);
		cs = cs.substring(0, cs.length() - 1);
		return cs;
	}

	public void updateraftcheck(Long tripid, String zfdate, Long zfseq,String url) {
		if(url==null||url.length()<1){
			url=WebContant.GetKeyValue("CenterUrl");
		}
		Raftcheck r = null;

		List list = this.find(" from Raftcheck where zfdate='" + zfdate
				+ "' and tripid=" + tripid);
		if (list == null || list.size() == 0) {
			r = new Raftcheck();
			r.setNumb(new Long(1));
			r.setZfdate(zfdate);
			r.setTripid(tripid);
			Long seq = this.getMaxPk("seq", "Raftcheck");
			r.setSeq(seq + 1);
			this.save(r);
		} else {
			r = (Raftcheck) list.get(0);
			r.setNumb(zfseq);
			this.update(r);
		}
		/*if (WebContant.GetKeyValue("IsCenterUrl").equals("1")) {
			try {
				javax.xml.rpc.Service service = null;
				java.net.URL endpointURL = new java.net.URL("http://"
						+ url
						+ "/services/centersaleService?wsdl");
				CentersaleServiceSoapBindingStub ssl = new CentersaleServiceSoapBindingStub(
						endpointURL, service);
				ssl.setMaintainSession(true);
				ssl.updateRaftcheck(r.getSeq() + "," + r.getTripid() + ","
						+ r.getZfdate() + "," + zfseq);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}*/
	}
}
