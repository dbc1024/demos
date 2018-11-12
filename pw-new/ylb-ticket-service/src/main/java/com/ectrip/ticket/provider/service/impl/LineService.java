package com.ectrip.ticket.provider.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SaleUtil;
import com.ectrip.sys.model.syspar.Orderlog;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Lineproduct;
import com.ectrip.ticket.model.provider.Linetravel;
import com.ectrip.ticket.model.provider.Linetravelpic;
import com.ectrip.ticket.model.provider.Netusermessage;
import com.ectrip.ticket.model.provider.PriceModel;
import com.ectrip.ticket.model.provider.Provider;
import com.ectrip.ticket.provider.dao.ILineproductDao;
import com.ectrip.ticket.provider.dao.ILinetravelDao;
import com.ectrip.ticket.provider.service.ILineService;
import com.ectrip.upload.model.Upfile;

@Service
public class LineService extends GenericService implements ILineService {
	private ILineproductDao lineproductDao;
	private ILinetravelDao linetravelDao;

	public ILineproductDao getLineproductDao() {
		return lineproductDao;
	}

	public void setLineproductDao(ILineproductDao lineproductDao) {
		this.lineproductDao = lineproductDao;
	}

	public ILinetravelDao getLinetravelDao() {
		return linetravelDao;
	}

	public void setLinetravelDao(ILinetravelDao linetravelDao) {
		this.linetravelDao = linetravelDao;
	}

	/**
	 * 根据产品id找行程 Describe:
	 * 
	 * @author liujianwen
	 * @param itickettypeid
	 * @return return:List<Linetravel> Date:2012-7-2
	 */
	public PaginationSupport findLinetravelByTickettypeid(Long itickettypeid,String linename, int page,
			int pagesize, String url) {
		return linetravelDao.findLinetravelByTickettypeid(itickettypeid,linename, page, pagesize, url);
	}

	/**
	 * 批量保存行程 Describe:
	 * 
	 * @author liujianwen return:void Date:2012-7-3
	 */
	public void saveOrUpdateLinetravel(Linetravel linetravel) {
		long id = linetravelDao.getMaxPk("linetravelid", "Linetravel");
		System.out.println("id:" + id + "," + linetravel.getItickettypeid());
		if (linetravel.getLinetravelid() == null)
			linetravel.setLinetravelid(++id);
		linetravelDao.saveOrUpdate(linetravel);
		linetravelDao.deleteLinetravelpicByLinetravelid(linetravel.getLinetravelid());
		id = linetravelDao.getMaxPk("linetravelpicid", "Linetravelpic");
		if (linetravel.getUpids() != null) {
			for (String upid : linetravel.getUpids()) {
				linetravelDao.save(new Linetravelpic(++id, linetravel.getLinetravelid(), Long
						.parseLong(upid)));
			}
		}
	}

	/**
	 * 根据id查找行程 Describe:
	 * 
	 * @see com.ectrip.system.provider.service.iservice.ILineService#findLinetravelById(java.lang.Long)
	 * @param linetravelid
	 * @return
	 * @author liujianwen Date:2012-7-5
	 */
	public Linetravel findLinetravelById(Long linetravelid) {
		return this.linetravelDao.findLinetravelById(linetravelid);
	}

	/**
	 * 删除行程 Describe:
	 * 
	 * @see com.ectrip.system.provider.service.iservice.ILineService#delteLinetravelById(java.lang.Long)
	 * @param Linetravel
	 * @author liujianwen Date:2012-7-5
	 */
	public void deleteLinetravelById(Long linetravelid) {
		this.linetravelDao.delteLinetravelById(linetravelid);
	};

	/**
	 * 查询线路价格 Describe:
	 * 
	 * @author liujianwen
	 * @return return:List<Linetravel> Date:2012-7-2
	 */
	public PaginationSupport findLineProductPage(int page, int pagesize, String url,
			Long iscenicid, String title, String starttime, String to, String from,String bycategorytype) {
		PaginationSupport ps = linetravelDao.findLineProductPage(page, pagesize, url, iscenicid,
				title, starttime, to, from,bycategorytype);
		List<Upfile> upfiles;
		List<Edmcrowdkindpricetab> prices;
		long itickettypeid;
		for (Object o : ps.getItems()) {
			// ${prolist.piclist[0].upadder}${prolist.piclist[0].upfilename}
			itickettypeid = Long.parseLong(((Map) o).get("itickettypeid").toString());
			upfiles = this.linetravelDao.findLinerPic(itickettypeid);
			prices = this.linetravelDao.findLinePriceByItickettypeid(itickettypeid);
			String pricenote = "";
			String pricenote1 = "";
			int i = 0;
			for (Edmcrowdkindpricetab edmcrowdkindpricetab : prices) {
				i++;
				pricenote += ("￥" + edmcrowdkindpricetab.getMactualsaleprice() + (i == prices
						.size() ? "" : "/"));
				pricenote1 += ("￥" + edmcrowdkindpricetab.getListingprice() + (i == prices.size() ? ""
						: "/"));
			}

			((Map) o).put("pricenote", pricenote);
			((Map) o).put("pricenote1", pricenote1);
			((Map) o).put("upadder",
					(upfiles == null || upfiles.size() == 0) ? null : upfiles.get(0).getUpadder());
			((Map) o).put("upfilename", (upfiles == null || upfiles.size() == 0) ? null : upfiles
					.get(0).getUpfilename());
			((Map) o).put("pricenote", pricenote);
			((Map) o).put("pricenote1", pricenote1);

			List pingluntemp = this.linetravelDao.findCountHscomment(null, "02", itickettypeid);
			((Map) o).put("pinglunshu",
					(Long) (pingluntemp.get(0) == null ? 0 : pingluntemp.get(0)));
			pingluntemp = this.linetravelDao.findAvgHscomment(null, "02", itickettypeid);
			NumberFormat f = NumberFormat.getInstance(); // 创建一个格式化类f
			f.setMaximumFractionDigits(1);
			((Map) o).put("pingfen",
					(pingluntemp.get(0) == null ? "无评分" : f.format(pingluntemp.get(0))));
		}
		return ps;
	}

	/**
	 * 根据价格查询线路产品所有信息 Describe:
	 * 
	 * @author liujianwen
	 * @param icrowdkindpriceid
	 * @return return:Edmbusinesstab Date:2012-7-10
	 * @throws ParseException
	 */
	public Edmtickettypetab findLinedetail(Long itickettypeid) throws ParseException {
		// Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)
		// this.linetravelDao.get(Edmcrowdkindpricetab.class,
		// icrowdkindpriceid);
		Edmtickettypetab line = (Edmtickettypetab) this.linetravelDao.get(Edmtickettypetab.class,
				itickettypeid);
		Esbscenicareatab provider=(Esbscenicareatab) linetravelDao.get(Esbscenicareatab.class, line.getIscenicid());
		System.out.println("fsdf:"
				+ this.linetravelDao.find(
						"select avg(h.remarknum) from Hscomment h  where h.ptype='10'").get(0));

		if (line != null) {
			line.setList(this.linetravelDao.findLinerPic(itickettypeid));
			Lineproduct product = (Lineproduct) this.linetravelDao.get(Lineproduct.class,
					line.getItickettypeid());
			List pingluntemp = this.linetravelDao.findCountHscomment(null, "02", itickettypeid);
			product.setPinglunshu((Long) (pingluntemp.get(0).equals("0") ? 0 : pingluntemp.get(0)));
			pingluntemp = this.linetravelDao.findAvgHscomment(null, "02", itickettypeid);
			NumberFormat f = NumberFormat.getInstance(); // 创建一个格式化类f
			f.setMaximumFractionDigits(1);
			product.setPingfen((pingluntemp.get(0) == null ? "无评分" : f.format(pingluntemp.get(0))));
			line.setLineproduct(product);
			if (product != null) {
				//设置服务商
				product.setProvider((Esbscenicareatab) this.lineproductDao.get(
						Esbscenicareatab.class, line.getIscenicid()));
				//设置价格
				List<Edmcrowdkindpricetab> prices = this.linetravelDao
						.findLinePriceByItickettypeid(itickettypeid);
				
				Map<String, String> map = new LinkedHashMap<String,String>();
				Date now = new Date(new Date().getTime());
				long daysBetween = 0;
				for (Edmcrowdkindpricetab edmcrowdkindpricetab : prices) {
					edmcrowdkindpricetab.setStrcrowdkind(
							((Edpcrowdkindtab)(this.getLinetravelDao().get(
									Edpcrowdkindtab.class,edmcrowdkindpricetab.getIcrowdkindid()))).getSzcrowdkindname()
							);
					daysBetween =  (SaleUtil.format3.parse(edmcrowdkindpricetab.getEnddata()).getTime() - 
							SaleUtil.format3.parse(edmcrowdkindpricetab.getStartdata()).getTime())/(3600000*24) +1;
					if(now.before(SaleUtil.format3.parse(edmcrowdkindpricetab.getStartdata()))){//如果现在的时间在开始时间前
						//如果在30天内
						for(int i = 0 ;i < (daysBetween<31?daysBetween:31); i ++){
								SaleUtil.putDatePrice(map, SaleUtil.addDays(edmcrowdkindpricetab.getStartdata(),String.valueOf(i)), 
										edmcrowdkindpricetab.getIcrowdkindpriceid()+","+edmcrowdkindpricetab.getStrcrowdkind()+","+
												String.valueOf(edmcrowdkindpricetab.getMactualsaleprice()));
						}
					}else {
						//因为计算当天的话 就得加2
						daysBetween = (SaleUtil.format3.parse(edmcrowdkindpricetab.getEnddata()).getTime() - now.getTime())/(3600000*24) + 2;
							for(int i = 0 ;i <(daysBetween<31?daysBetween:31); i ++){								
									SaleUtil.putDatePrice(map, SaleUtil.addDays(SaleUtil.format3.format(now),String.valueOf(i)), 
											edmcrowdkindpricetab.getIcrowdkindpriceid()+","+edmcrowdkindpricetab.getStrcrowdkind()+","+
													String.valueOf(edmcrowdkindpricetab.getMactualsaleprice()));
							}
					}
				}
				for (Entry en : map.entrySet()) {
					System.out.print(en.getKey() + ":" + en.getValue());
				}
				product.setDatePrices(map);
				product.setEdmcrowdkindpricetabs(prices);
			}
			List<Linetravel> linetravels = this.linetravelDao.findByTickettypeid(line
					.getItickettypeid());
			line.getLineproduct().setLinetravles(linetravels);
			Upfile upfile;
			for (Linetravel linetravel : linetravels) {
				for (Object pic : this.linetravelDao.findLinetravelpicByLinetravelid(linetravel
						.getLinetravelid())) {
					upfile = (Upfile) this.linetravelDao.get(Upfile.class,
							((Linetravelpic) pic).getUpid());
					if (upfile != null)
						linetravel.getList().add(upfile);
				}
				;
			}
		}
		return line;
	}

	/**
	 * 
	 * Describe: 查询产品及其图片
	 * 
	 * @author liujianwen
	 * @param itickettypeid
	 * @return return:Edmtickettypetab Date:2012-7-17
	 */
	public Edmtickettypetab findEdmtickettypetabAndPic(Long itickettypeid) {
		Edmtickettypetab line = (Edmtickettypetab) this.linetravelDao.get(Edmtickettypetab.class,
				itickettypeid);
		if (line != null)
			line.setList(this.linetravelDao.findLinerPic(itickettypeid));
		return line;
	}

	/**
	 * 
	 * Describe:
	 * 
	 * @author liujianwen
	 * @param pdtp
	 *            服务商类型
	 * @return return:List<Provider> Date:2012-7-14
	 */
	public List<Provider> findLXS(String pdtp) {
		return this.linetravelDao.findLXS(pdtp);
	}

	/**
	 * 查询线路产品的评论 Describe:
	 * 
	 * @see com.ectrip.system.provider.service.iservice.ILineService#queryHscomment(java.lang.String,
	 *      int, int, java.lang.String)
	 * @param itickettypeid
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author liujianwen Date:2012-7-16
	 */
	public PaginationSupport queryHscomment(String type, String ptype, Long itickettypeid,
			int page, int pageSize, String url) {
		return this.linetravelDao.queryHscomment(type, ptype, itickettypeid, page, pageSize, url);
	}

	/**
	 * 
	 * Describe: 查询留言
	 * 
	 * @author liujianwen
	 * @param status
	 *            审核状态 00未审核，01审核
	 * @param productid
	 *            产品id
	 * @param iscenicid
	 *            服务商id
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return return:PaginationSupport Date:2012-7-17
	 */
	public PaginationSupport queryNetusermessage(String status, Long productid, Long iscenicid,
			int page, int pageSize, String url) {
		return this.linetravelDao.queryNetusermessage(status, productid, iscenicid, page, pageSize,
				url);
	}

	/**
	 * 保存网友评论* Describe:
	 * 
	 * @see com.ectrip.system.provider.service.iservice.ILineService#saveNetMessage(com.ectrip.model.provider.Netusermessage)
	 * @param message
	 * @author liujianwen Date:2012-7-17
	 */
	public void saveNetMessage(Netusermessage message) {
		Long seq = this.linetravelDao.getMaxPk("netid", "Netusermessage");
		message.setNetid(++seq);
		message.setStatus("00");// 求审合
		message.setCreatedate(SaleUtil.format2.format(new Date()));
		this.linetravelDao.saveNetMessage(message);
	}

	public static void main(String[] args) {
		double a = 3.12345678912345678911234;
		NumberFormat f = NumberFormat.getInstance(); // 创建一个格式化类f
		f.setMaximumFractionDigits(2); // 设置小数位的格式
		String s = f.format(a); // 格式化数据a,将a格式化为f
		System.out.println(s); // 输出f
	}
	
	/*
	 * 得到价格
	 */
	public List getCrowdkindprices(Long itickettypeid,String startDate,String endDate){
		return lineproductDao.getCrowdkindprices(itickettypeid,startDate,endDate);
	}
	/*
	 * 批量保存价格(non-Javadoc)
	 * @see com.ectrip.system.provider.service.iservice.ILineService#saveCrowdkindprices(com.ectrip.model.provider.Edmcrowdkindpricetab, java.util.Map)
	 */
	public boolean saveCrowdkindprices(Edmcrowdkindpricetab edmcrowdkindpricetab,Map<String, PriceModel> priceModels,Syslog syslog,List<Edmcrowdkindpricetab> list,Lineproduct line) {
		//开始删除操作
		/*for (int i = 0; i < list.size(); i++) {
			this.lineproductDao.deleteByKey(Edmcrowdkindpricetab.class, list.get(i).getIcrowdkindpriceid());
		}*/ 
		return lineproductDao.saveCrowdkindprices(edmcrowdkindpricetab, priceModels, syslog,line);
	}

	public void deleteCrowdKindPirce(Long crowdkindpriceId, Syslog syslog,
			List<Edmcrowdkindpricetab> crowdkindpriceIds) {
		lineproductDao.deleteCrowdKindPirce(crowdkindpriceId, syslog, crowdkindpriceIds);
	}

	public List autoFindRegion(String likeStr) {
		String sql = "select new map( g.iregionalid as iregionalid,g.szinnername as szinnername,g.szregionalname as szregionalname )  from Galsourceregiontab g where g.szinnername like '%"+likeStr+"%' and rownum<=10 order by g.ilevelsequence";
		return lineproductDao.find(sql);
	}

	public void updateLineOrder(String orid,String ddzt,Long iticketId,Syslog syslog,Orderlog orderlog){
		this.lineproductDao.updateLineOrder(orid, ddzt, iticketId, syslog, orderlog);
	}
}
