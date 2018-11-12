package com.ectrip.ticket.provider.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.EdmticketcomposepricetabId;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Lineproduct;
import com.ectrip.ticket.model.provider.PriceModel;
import com.ectrip.ticket.model.salesmanager.Osppostsaleslimitstab;
import com.ectrip.ticket.model.salesmanager.Ospsaleslimitstab;
import com.ectrip.ticket.model.salesmanager.Ospticketwinlimitstab;
import com.ectrip.ticket.provider.dao.ILineproductDao;

/**
 * @author liujianwen
 */
@Repository
public class LineproductDao extends GenericDao implements ILineproductDao {
	public List getCrowdkindprices(Long itickettypeid, String startDate, String endDate) {
		StringBuffer sql = new StringBuffer();
		if (startDate != null) {
			sql.append(
					"from Edmcrowdkindpricetab e where e.itickettypeid=? and  e.startdata>=? and e.enddata<=?    order by e.startdata,e.icrowdkindpriceid asc");
			List list = find(sql.toString(), new Object[] { itickettypeid, startDate, endDate });
			return list;
		} else {
			sql.append(
					"from Edmcrowdkindpricetab e where e.itickettypeid=?   order by e.startdata,e.icrowdkindpriceid asc");
			List list = find(sql.toString(), new Object[] { itickettypeid });
			return list;
		}
	}

	public boolean saveCrowdkindprices(Edmcrowdkindpricetab edmcrowdkindpricetab, Map<String, PriceModel> priceModels,
			Syslog syslog, Lineproduct line) {
		update(line);
		// 起始日期段
		String startDate = edmcrowdkindpricetab.getStartdata();
		// 截至日期段
		String endDate = edmcrowdkindpricetab.getEnddata();
		Long icrowdkindpriceid = getMaxPk("icrowdkindpriceid", Edmcrowdkindpricetab.class.getName()) + 1;// 当前价格编号最大值
		Long logid = this.getMaxPk("logid", "Syslog"); // 当前日志编号最大值
		for (int i = 0; i < priceModels.size(); i++) {
			Edmcrowdkindpricetab crowdkindpricetab = new Edmcrowdkindpricetab(); // 创建新的售价表
			crowdkindpricetab.setIcrowdkindpriceid(icrowdkindpriceid); // 售价编号
			crowdkindpricetab.setIcrowdkindid(1L);// 成人
			crowdkindpricetab.setItickettypeid(edmcrowdkindpricetab.getItickettypeid());// 产品id
			crowdkindpricetab.setByisuse(1L);// 状态
			crowdkindpricetab.setIsscene(new Long(0));// 是否现场销售
			crowdkindpricetab.setIpeoplenumrange(0L);// 基数
			crowdkindpricetab.setIsnet(1L);// 是否可网上销售
			crowdkindpricetab.setScenictype(edmcrowdkindpricetab.getScenictype());// 服务商类别
			crowdkindpricetab.setStrtickettype(edmcrowdkindpricetab.getStrtickettype());// 产品名称
			crowdkindpricetab.setNote1("0000");// 价格分组
			crowdkindpricetab.setIbusinessid(edmcrowdkindpricetab.getIbusinessid());// 业务类型
			PriceModel priceModel = priceModels.get(startDate);

			if (priceModel.getChildcount() == null) {
				priceModel.setChildcount(0);
			}
			if (priceModel.getChildPrice() == null) {
				priceModel.setChildPrice(0.0);
			}
			if (priceModel.getRoomcha() == null) {
				priceModel.setRoomcha(0.0);
			}

			try {
				priceModel.setDate(priceModel.getDate().replace("'", ""));
			} catch (Exception e) {
				// TODO: handle exception
			}
			crowdkindpricetab.setListingprice(Double.valueOf(priceModel.getYoungPrice()));// 价格
			crowdkindpricetab.setMactualsaleprice(Double.valueOf(priceModel.getYoungPrice()));// 实际
			crowdkindpricetab.setJsprice(Double.valueOf(priceModel.getYoungPrice()));// 结算
			crowdkindpricetab.setInote4(Long.valueOf(priceModel.getYoungcount()));// 名额
			crowdkindpricetab.setWeekendprice(Double.valueOf(priceModel.getRoomcha()));// 单房差
			crowdkindpricetab.setStartdata(priceModel.getDate());// 开始日期
			crowdkindpricetab.setEnddata(priceModel.getDate());// 结束日期
			List list = find(
					"from Edmcrowdkindpricetab edm where edm.itickettypeid=? and edm.startdata=? and edm.enddata=? and edm.icrowdkindid=1",
					new Object[] { edmcrowdkindpricetab.getItickettypeid(), priceModel.getDate(),
							priceModel.getDate() });
			if (list != null && list.size() > 0) {// 如果存在数据进行更新操作
				Edmcrowdkindpricetab edmc = (Edmcrowdkindpricetab) list.get(0);
				crowdkindpricetab.setIcrowdkindpriceid(edmc.getIcrowdkindpriceid());
				// 排序
				crowdkindpricetab.setIsequence(edmc.getIcrowdkindpriceid());
				crowdkindpricetab.setIcrowdkindpricecode(edmc.getIcrowdkindpricecode());
				// 使old的entity脱离游离状态
				getHibernateTemplate().evict(edmc);
				update(crowdkindpricetab);
			} else {// 否则进行新增操作
				if (i == 0) {
					crowdkindpricetab.setIcrowdkindpriceid(icrowdkindpriceid);
				} else {
					icrowdkindpriceid = icrowdkindpriceid + 1;
					crowdkindpricetab.setIcrowdkindpriceid(icrowdkindpriceid);
				}
				// 排序
				crowdkindpricetab.setIsequence(crowdkindpricetab.getIcrowdkindpriceid());
				// 保存售价表
				crowdkindpricetab.setIcrowdkindpricecode(
						crowdkindpricetab.getIcrowdkindpriceid() + edmcrowdkindpricetab.getIcrowdkindpricecode());
				save(crowdkindpricetab);
				// 删除组合价格表数据
				String sql = " from Edmticketcomposepricetab where id.icrowdkindpriceid="
						+ crowdkindpricetab.getIcrowdkindpriceid();
				List composepricelist = this.find(sql);
				if (composepricelist.size() >= 1) {
					for (int j = 0; j < composepricelist.size(); j++) {
						Edmticketcomposepricetab composeprice = (Edmticketcomposepricetab) composepricelist.get(i);
						// 删除价格组合拆分表
						delete(composeprice);
					}
				}
				// 组合票价格拆分 表
				Edmticketcomposepricetab edmcomposeprice = new Edmticketcomposepricetab();// 价格拆分表
				EdmticketcomposepricetabId composepriceid = new EdmticketcomposepricetabId();
				// 得到最大主键
				long priceid = this.getMaxPk("id.iticketcomposepriceid", new String[] { "id.icrowdkindpriceid" },
						new String[] { crowdkindpricetab.getIcrowdkindpriceid().toString() },
						"Edmticketcomposepricetab");
				composepriceid.setIticketcomposepriceid(priceid + 1);
				composepriceid.setIcrowdkindpriceid(crowdkindpricetab.getIcrowdkindpriceid());// 售价id
				edmcomposeprice.setId(composepriceid);
				edmcomposeprice.setItickettypeid(crowdkindpricetab.getItickettypeid());// 产品id
				edmcomposeprice.setNumb(Long.valueOf(new String(priceModel.getYoungcount() + "")));// 数量
				edmcomposeprice.setMactualsaleprice(crowdkindpricetab.getMactualsaleprice());// 单价
				edmcomposeprice.setJsprice(crowdkindpricetab.getMactualsaleprice());// 结算价
				save(edmcomposeprice);
			}
			// collection.add(crowdkindpricetab);

			// 添加儿童
			Edmcrowdkindpricetab crowdkindpricetab_child = new Edmcrowdkindpricetab();
			icrowdkindpriceid = icrowdkindpriceid + 1;
			crowdkindpricetab_child.setSzmemo("1");// 标识为儿童
			crowdkindpricetab_child.setIcrowdkindpriceid(icrowdkindpriceid);
			crowdkindpricetab_child.setIcrowdkindid(2L);
			crowdkindpricetab_child.setItickettypeid(edmcrowdkindpricetab.getItickettypeid());// 产品id
			crowdkindpricetab_child.setByisuse(1L);// 状态
			crowdkindpricetab_child.setJsprice(0.0);// 结算价
			crowdkindpricetab_child.setIsnet(new Long(0));
			crowdkindpricetab_child.setIsscene(new Long(0));
			crowdkindpricetab_child.setIpeoplenumrange(0L);// 基数
			crowdkindpricetab_child.setIsnet(1L);// 是否可网上销售
			crowdkindpricetab_child.setIsscene(1L);// 现场销售
			crowdkindpricetab_child.setScenictype(edmcrowdkindpricetab.getScenictype());// 服务商类别
			crowdkindpricetab_child.setStrtickettype(edmcrowdkindpricetab.getStrtickettype());// 产品名称
			crowdkindpricetab_child.setNote1("0000");// 价格分组
			crowdkindpricetab_child.setIbusinessid(edmcrowdkindpricetab.getIbusinessid());
			crowdkindpricetab_child.setIsequence(crowdkindpricetab_child.getIcrowdkindpriceid());// 排序
			crowdkindpricetab_child.setIcrowdkindpricecode(crowdkindpricetab_child.getIcrowdkindpriceid() + "LX"); // 价格编码
			try {
				priceModel.setDate(priceModel.getDate().replace("'", ""));
			} catch (Exception e) {
				// TODO: handle exception
			}
			crowdkindpricetab_child.setListingprice(Double.valueOf(priceModel.getChildPrice()));// 价格
			crowdkindpricetab_child.setMactualsaleprice(Double.valueOf(priceModel.getChildPrice()));
			crowdkindpricetab_child.setJsprice(Double.valueOf(priceModel.getChildPrice()));
			crowdkindpricetab_child.setInote4(Long.valueOf(priceModel.getChildcount()));// 名额
			crowdkindpricetab_child.setWeekendprice(Double.valueOf(priceModel.getRoomcha()));// 单房差
			crowdkindpricetab_child.setStartdata(priceModel.getDate());
			crowdkindpricetab_child.setEnddata(priceModel.getDate());
			List list_child = find(
					"from Edmcrowdkindpricetab edm where edm.itickettypeid=? and edm.startdata=? and edm.icrowdkindid=? and edm.enddata=? and edm.icrowdkindid=13",
					new Object[] { edmcrowdkindpricetab.getItickettypeid(), priceModel.getDate(), 13L,
							priceModel.getDate() });
			if (list_child != null && list_child.size() > 0) {
				Edmcrowdkindpricetab edmc = (Edmcrowdkindpricetab) list_child.get(0);
				crowdkindpricetab_child.setIcrowdkindpriceid(edmc.getIcrowdkindpriceid());
				crowdkindpricetab_child.setIcrowdkindpricecode(edmc.getIcrowdkindpricecode());
				// 使old的entity脱离游离状态
				getHibernateTemplate().evict(edmc);
				update(crowdkindpricetab_child);
			} else {
				// 保存售价表
				crowdkindpricetab_child.setIcrowdkindpricecode(crowdkindpricetab_child.getIcrowdkindpriceid() + "LX");
				save(crowdkindpricetab_child);
				// 删除组合价格表数据
				String sql = " from Edmticketcomposepricetab where id.icrowdkindpriceid="
						+ crowdkindpricetab_child.getIcrowdkindpriceid();
				List composepricelist_child = this.find(sql);
				if (composepricelist_child.size() >= 1) {
					for (int j = 0; j < composepricelist_child.size(); j++) {
						Edmticketcomposepricetab composeprice = (Edmticketcomposepricetab) composepricelist_child
								.get(i);
						// 删除价格组合拆分表
						delete(composeprice);
					}
				}
				// 组合票价格拆分 表
				Edmticketcomposepricetab edmcomposeprice_child = new Edmticketcomposepricetab();// 价格拆分表
				EdmticketcomposepricetabId composepriceid_child = new EdmticketcomposepricetabId();
				// 得到最大主键
				long priceid = this.getMaxPk("id.iticketcomposepriceid", new String[] { "id.icrowdkindpriceid" },
						new String[] { crowdkindpricetab_child.getIcrowdkindpriceid().toString() },
						"Edmticketcomposepricetab");
				composepriceid_child.setIticketcomposepriceid(priceid + 1);
				composepriceid_child.setIcrowdkindpriceid(crowdkindpricetab_child.getIcrowdkindpriceid());// 售价id
				edmcomposeprice_child.setId(composepriceid_child);
				edmcomposeprice_child.setItickettypeid(crowdkindpricetab_child.getItickettypeid());// 产品id
				edmcomposeprice_child.setNumb(Long.valueOf(new String(priceModel.getChildcount() + "")));// 数量
				edmcomposeprice_child.setMactualsaleprice(crowdkindpricetab_child.getMactualsaleprice());// 单价
				edmcomposeprice_child.setJsprice(crowdkindpricetab_child.getMactualsaleprice());// 结算价
				save(edmcomposeprice_child);
			}
			// collection.add(crowdkindpricetab);
			startDate = Tools.getDate(startDate, 1);

		}

		// saveOrUpdateAll(collection);
		syslog.setStlg("0059");
		syslog.setBrief("产品id" + edmcrowdkindpricetab.getItickettypeid());
		syslog.setNote("产品名称：" + edmcrowdkindpricetab.getStrtickettype() + "日期段：开始日期："
				+ edmcrowdkindpricetab.getStartdata() + ",结束日期：" + Tools.getDate(startDate, -1));
		syslog.setLogdatetime(Tools.getDayTimes());
		syslog.setLogid(logid + 1);
		save(syslog);
		return true;
	}

	public void deleteCrowdKindPirce(Long crowdkindpriceId, Syslog syslog,
			List<Edmcrowdkindpricetab> crowdkindpriceIds) {
		int index = crowdkindpriceIds.size();
		for (int c = 0; c < crowdkindpriceIds.size(); c++) {
			crowdkindpriceId = crowdkindpriceIds.get(c).getIcrowdkindpriceid();
			Edmcrowdkindpricetab edmcrowdkindprice = (Edmcrowdkindpricetab) this.get(Edmcrowdkindpricetab.class,
					crowdkindpriceId);
			try {
				// 删除价格组合拆分表
				String sql = " from Edmticketcomposepricetab where id.icrowdkindpriceid=" + crowdkindpriceId;
				List composepricelist = this.find(sql);
				if (composepricelist.size() >= 1) {
					for (int i = 0; i < composepricelist.size(); i++) {
						Edmticketcomposepricetab composeprice = (Edmticketcomposepricetab) composepricelist.get(i);
						delete(composeprice);
					}
				}
				String sql2 = " from Ospsaleslimitstab where icrowdkindpriceid=" + crowdkindpriceId;
				List salelimitlist = this.find(sql2);
				if (salelimitlist != null && salelimitlist.size() >= 1) {
					for (int j = 0; j < salelimitlist.size(); j++) {
						Ospsaleslimitstab salelimit = (Ospsaleslimitstab) salelimitlist.get(j);
						this.delete(salelimit);
					}
				}
				// 删除岗位销售权限表数据
				String sql3 = " from Osppostsaleslimitstab where icrowdkindpriceid=" + crowdkindpriceId;
				List postlimitlist = this.find(sql3);
				if (postlimitlist != null && postlimitlist.size() >= 1) {
					for (int j = 0; j < postlimitlist.size(); j++) {
						Osppostsaleslimitstab postlimit = (Osppostsaleslimitstab) postlimitlist.get(j);
						this.delete(postlimit);
					}
				}
				// 删除窗口销售权限表数据
				String sql4 = " from Ospticketwinlimitstab where icrowdkindpriceid=" + crowdkindpriceId;
				List winlimitlist = this.find(sql4);
				if (winlimitlist != null && winlimitlist.size() >= 1) {
					for (int j = 0; j < winlimitlist.size(); j++) {
						Ospticketwinlimitstab winlimit = (Ospticketwinlimitstab) winlimitlist.get(j);
						this.delete(winlimit);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			this.delete(edmcrowdkindprice);
		}
		// 删除日志
		syslog.setStlg("0061");
		syslog.setNote("售价删除：日期段：开始日期" + crowdkindpriceIds.get(0).getStartdata() + ",结束日期："
				+ crowdkindpriceIds.get(crowdkindpriceIds.size() - 1).getStartdata());
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = this.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		this.save(syslog);
	}

	public void updateLineOrder(String orid, String ddzt, Long iticketId, Syslog syslog, Orderlog orderlog) {
		String hsql1 = " from MOrder where orid='" + orid + "' ";
		String hsql2 = " from TOrder where id.orid='" + orid + "'";
		String hsql3 = " from YOrder where id.orid='" + orid + "'";
		List morderlist = this.find(hsql1);
		List torderlist = this.find(hsql2);
		List yorderlist = this.find(hsql3);

		String ddzts = "00";
		if (ddzt.equals("04")) {// 02 审核通过前台现付 03 审核通过需担保04 审核通过未支付（需网上支付）
			ddzts = "00";
		} else if (ddzt.equals("05")) {// 审核通过已付款
			ddzts = "02";
		} else if (ddzt.equals("14")) {// 审核未通过出行人数已满
			ddzts = "20";
		} else if (ddzt.equals("15")) {// 已消费
			ddzts = "11";
		}

		if (!"01".equals(ddzt)) {

			if (torderlist != null && torderlist.size() >= 1) {
				for (int i = 0; i < torderlist.size(); i++) {
					TOrder torder = (TOrder) torderlist.get(i);
					torder.setDdzt(ddzts);
					this.update(torder);
				}
			}

			if (yorderlist != null && yorderlist.size() >= 1) {
				for (int i = 0; i < yorderlist.size(); i++) {
					YOrder yorder = (YOrder) yorderlist.get(i);
					yorder.setDdzt(ddzts);
					this.update(yorder);
				}
			}
			String sql = " from TOrder where orid='" + orid + "' ";
			List list = this.find(sql);
			int numb = 0;
			if (list != null && list.size() >= 1) {
				for (int x = 0; x < list.size(); x++) {
					TOrder td = (TOrder) list.get(x);
					if (td.getDdzt().equals(ddzts)) {
						numb = numb + 1;
					}
				}
			}
			if (numb == list.size()) {
				if (morderlist != null && morderlist.size() >= 1) {
					for (int i = 0; i < morderlist.size(); i++) {
						MOrder morder = (MOrder) morderlist.get(i);
						morder.setDdzt(ddzts);
						this.update(morder);
					}
				}
			}

			syslog.setStlg("0188");
			syslog.setBrief("审核订单，订单号：" + orid);
			syslog.setNote("审核订单，订单号：" + orid);
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);

			Esbscenicareatab esb = (Esbscenicareatab) this.get(Esbscenicareatab.class, iticketId);
			orderlog.setLogid(getMaxPk("logid", "Orderlog") + 1);
			orderlog.setOrid(orid);
			orderlog.setStlg("0157");
			if ("20".equals(ddzts)) {
				orderlog.setBrief(esb.getSzscenicname() + "操作未通过");
				orderlog.setNote("操作未通过");
			} else {
				orderlog.setBrief(esb.getSzscenicname() + "操作成功");
				orderlog.setNote("操作成功");
			}

			orderlog.setUsid("");
			orderlog.setLogtype(Long.parseLong("1"));
			orderlog.setLogdatetime(Tools.getDayTimes());
			this.save(orderlog);
		}
	}
}
