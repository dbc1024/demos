package com.ectrip.ec.book.hotel.dao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.hotel.dao.idao.IHotelDAO;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.model.user.Hscomment;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelproduct;
import com.ectrip.ticket.model.provider.Hotelprovider;

public class HotelDAO extends GenericDao implements IHotelDAO {

	/**
	 * ���ݷ���������ѯ����Դ���б�* Describe:
	 * 
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#getHotelSourceList(java.lang.String)
	 * @param pdtp
	 * @return
	 * @author huangyuqi Date:2011-11-28
	 */
	public List getHotelSourceList(String pdtp) {
		List list = new ArrayList();
		String hsql = " from Esbscenicareatab where scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
				+ pdtp + "' or spmcd='" + pdtp + "')) and isjd=0 ";
		List sourcelist = this.find(hsql);
		StringBuffer source = null;// ��Դ��
		if (sourcelist != null && sourcelist.size() >= 1) {
			source = new StringBuffer();
			for (int i = 0; i < sourcelist.size(); i++) {
				Esbscenicareatab provider = (Esbscenicareatab) sourcelist.get(i);
				if (i == sourcelist.size() - 1) {
					source.append(provider.getSzregionalid());
				} else {
					source.append(provider.getSzregionalid() + ",");
				}
			}
		}
		if (source != null && !"".equals(source)) {
			// ��ѯ����Դ���б�
			String hsql2 = " from Galsourceregiontab where iregionalid in (" + source + ")";
			list = this.find(hsql2);
		}
		return list;
	}

	/**
	 * * Describe:��ѯ�������̲�Ʒ(�Ƶ�)
	 * 
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#getHotelProductList(java.lang.String,
	 *      java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author chenxinhao Date:2012-11-7
	 */
	public PaginationSupport getHotelProductList(String rzti, String ldti, String lgtp, int page, int pageSize,
			String url) {
		PaginationSupport ps = null;
		ldti = Tools.getDate(ldti, -1);

		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber ) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='06' and pro.byisuse=1 and pro.isjd =0 and "
				+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti + "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
				+ "' order by pro.iordernumber desc";
		ps = this.findPage(hsql, pageSize, page, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ���Ҳ�Ʒ
					String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
							+ Long.parseLong(iscenicid)
							+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti
							+ "' "
							+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
							+ "' ) ";
					List productList = this.find(hsql2);
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {

							Edmtickettypetab product = (Edmtickettypetab) productList.get(j);
							// �Ƶ��Ʒ��������
							Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,
									product.getItickettypeid());

							if (hotelproduct != null) {
								Sysparv5Id sys = new Sysparv5Id();
								sys.setPmcd(hotelproduct.getBedtype());
								sys.setPmky("BETP");
								Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
								hotelproduct.setStrbedtype(syspar.getPmva());

								product.setHotelproduct(hotelproduct);
							}

							// /�ж�ѡ�����ס�����Ƿ��м۸�
							int fk = 0;
							int num = Tools.getDayNumb(rzti, ldti);
							for (int x = 0; x < num; x++) {
								String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ product.getItickettypeid() + "  and pri.startdata<='" + Tools.getDate(rzti, x)
										+ "' and pri.enddata>='" + Tools.getDate(rzti, x) + "' ";
								List lst = this.find(hsqlend);
								if (lst == null || lst.size() <= 0) {
									fk = 1;
									break;
								} else {
									fk = 2;
									continue;
								}

							}
							if (fk == 2) {
								// ���Ҽ۸�
								String hsql3 = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ product.getItickettypeid() + "  and pri.startdata<='" + rzti
										+ "' and pri.enddata>='" + rzti + "' ";
								List priceList = this.find(hsql3);
								if (priceList != null && priceList.size() >= 1) {
									Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) priceList.get(0);
									if (Tools.getDayOfWeek(rzti) == 6 || Tools.getDayOfWeek(rzti) == 7) {
										price.setMactualsaleprice(price.getWeekendprice());
									}
									product.setPrice(price);
								}

								// ͼ��
								String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
										+ product.getItickettypeid();
								List piclist = this.find(hsqls);
								product.setList(piclist);
							}

						}

					}
					map.put("productList", productList);

					// ��ѯ�Ƶ�����
					Hotelprovider hotelprovider = new Hotelprovider();
					hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, Long.parseLong(iscenicid));
					// if(hotelprovider!=null){
					// //�Ƶ����
					// String
					// hsqls=" select new map(s.id.iscenicid as
					// iscenicid,s.id.svid as svid,sys.pmva as strsvc ) from
					// Hotelsvc s,Sysparv5 sys where s.id.iscenicid =
					// "+Long.parseLong(iscenicid)+" and sys.id.pmky='HTSC' and
					// sys.id.pmcd=s.id.svid ";
					// List hotelsvclist = this.find(hsqls);
					// hotelprovider.setHotelsvclist(hotelsvclist);
					// }
					map.put("hotelprovider", hotelprovider);

					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid) + " and p.itickettypeid=0 and p.upid = u.upid ";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid=" + new Long(iscenicid) + " and status='01' ";
					List commentList = this.find(sql2);
					double remarknum = 0;
					double marknum = 0;
					if (commentList != null && commentList.size() >= 1) {
						for (int x = 0; x < commentList.size(); x++) {
							Hscomment comment = (Hscomment) commentList.get(x);
							remarknum = remarknum + comment.getRemarknum();
						}
						marknum = remarknum / commentList.size();
					}

					map.put("commentsize", commentList.size());
					map.put("sumremarknum", marknum);

				}

			}
		}
		return ps;

	}

	/**
	 * ��ѯ��ĳ�����µķ����̲�Ʒ(�Ƶ�) Describe:
	 * 
	 * @auth:huangyuqi
	 * @param regionId��Դ��
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2011-11-28
	 */
	public PaginationSupport getHotelProductList(Long regionId, String rzti, String ldti, String lgtp, int page,
			int pageSize, String url) {
		PaginationSupport ps = null;
		ldti = Tools.getDate(ldti, -1);

		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber ) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='06' and pro.byisuse=1 and pro.isjd =0 and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid="
				+ regionId + " or g.iregionalid=" + regionId + ") and "
				+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti + "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
				+ "' order by pro.iordernumber desc";
		ps = this.findPage(hsql, pageSize, page, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ���Ҳ�Ʒ
					String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
							+ Long.parseLong(iscenicid)
							+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti
							+ "' "
							+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
							+ "' ) ";
					List productList = this.find(hsql2);
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {

							Edmtickettypetab product = (Edmtickettypetab) productList.get(j);
							// �Ƶ��Ʒ��������
							Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,
									product.getItickettypeid());

							if (hotelproduct != null) {
								Sysparv5Id sys = new Sysparv5Id();
								sys.setPmcd(hotelproduct.getBedtype());
								sys.setPmky("BETP");
								Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
								hotelproduct.setStrbedtype(syspar.getPmva());

								product.setHotelproduct(hotelproduct);
							}

							// /�ж�ѡ�����ס�����Ƿ��м۸�
							int fk = 0;
							int num = Tools.getDayNumb(rzti, ldti);
							for (int x = 0; x < num; x++) {
								String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ product.getItickettypeid() + "  and pri.startdata<='" + Tools.getDate(rzti, x)
										+ "' and pri.enddata>='" + Tools.getDate(rzti, x) + "' ";
								List lst = this.find(hsqlend);
								if (lst == null || lst.size() <= 0) {
									fk = 1;
									break;
								} else {
									fk = 2;
									continue;
								}

							}
							if (fk == 2) {
								// ���Ҽ۸�
								String hsql3 = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ product.getItickettypeid() + "  and pri.startdata<='" + rzti
										+ "' and pri.enddata>='" + rzti + "' ";
								List priceList = this.find(hsql3);
								if (priceList != null && priceList.size() >= 1) {
									Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) priceList.get(0);
									if (Tools.getDayOfWeek(rzti) == 6 || Tools.getDayOfWeek(rzti) == 7) {
										price.setMactualsaleprice(price.getWeekendprice());
									}
									product.setPrice(price);
								}

								// ͼ��
								String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
										+ product.getItickettypeid();
								List piclist = this.find(hsqls);
								product.setList(piclist);
							}

						}

					}
					map.put("productList", productList);

					// ��ѯ�Ƶ�����
					Hotelprovider hotelprovider = new Hotelprovider();
					hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, Long.parseLong(iscenicid));
					// if(hotelprovider!=null){
					// //�Ƶ����
					// String
					// hsqls=" select new map(s.id.iscenicid as
					// iscenicid,s.id.svid as svid,sys.pmva as strsvc ) from
					// Hotelsvc s,Sysparv5 sys where s.id.iscenicid =
					// "+Long.parseLong(iscenicid)+" and sys.id.pmky='HTSC' and
					// sys.id.pmcd=s.id.svid ";
					// List hotelsvclist = this.find(hsqls);
					// hotelprovider.setHotelsvclist(hotelsvclist);
					// }
					map.put("hotelprovider", hotelprovider);

					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid) + " and p.itickettypeid=0 and p.upid = u.upid ";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid=" + new Long(iscenicid) + " and status='01' ";
					List commentList = this.find(sql2);
					double remarknum = 0;
					double marknum = 0;
					if (commentList != null && commentList.size() >= 1) {
						for (int x = 0; x < commentList.size(); x++) {
							Hscomment comment = (Hscomment) commentList.get(x);
							remarknum = remarknum + comment.getRemarknum();
						}
						marknum = remarknum / commentList.size();
					}

					map.put("commentsize", commentList.size());
					map.put("sumremarknum", marknum);

				}

			}
		}

		return ps;

	}

	/**
	 * ��ѯ�Ƶ� Describe:
	 * 
	 * @auth:huangyuqi
	 * @param provider
	 * @param price
	 * @param product
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2011-11-30
	 */
	public PaginationSupport getHotelProductSearchList(Esbscenicareatab provider, Hotelprovider hotelpro, String rzti,
			String ldti, String lgtp, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		ldti = Tools.getDate(ldti, -1);
		StringBuffer hsql = new StringBuffer();
		// �鿴������
		hsql.append(
				" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress ) from  Esbscenicareatab pro,Edmtickettypetab prd,Hotelprovider hpd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='06' and pro.byisuse=1 and pro.isjd =0 and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid="
						+ provider.getSzregionalid() + " or g.iregionalid=" + provider.getSzregionalid() + ") and "
						+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ "  and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti
						+ "' " + " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
						+ "' ");
		// �Ƶ�����
		if (provider.getSzscenicname() != null && !"".equals(provider.getSzscenicname())) {
			hsql.append(" and pro.szscenicname like '%" + provider.getSzscenicname() + "%' ");
		}
		// �����̵ȼ�
		if (provider.getSzgrade() != null && !"".equals(provider.getSzgrade())) {
			if (!provider.getSzgrade().equals("100")) {
				hsql.append(" and pro.szgrade='" + provider.getSzgrade() + "' ");
			}
		}

		if (hotelpro != null) {
			// �۸�����
			if (hotelpro.getJwqj() != null && !hotelpro.getJwqj().equals("")) {
				if (!hotelpro.getJwqj().equals("99")) {
					hsql.append(" and hpd.jwqj='" + hotelpro.getJwqj() + "' ");
				}
			}
			// �Ƶ�����
			if (hotelpro.getZxjb() != null && !hotelpro.getZxjb().equals("")) {
				if (!hotelpro.getZxjb().equals("99")) {
					hsql.append(" and hpd.zxjb='" + hotelpro.getZxjb() + "' ");
				}
			}
		}
		System.out.println(hsql.toString());
		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			String pdno = "";
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ���Ҳ�Ʒ
					String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
							+ Long.parseLong(iscenicid)
							+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti
							+ "' "
							+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
							+ "' ) ";

					List productList = this.find(hsql2);
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {

							Edmtickettypetab products = (Edmtickettypetab) productList.get(j);
							// �Ƶ���������
							Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,
									products.getItickettypeid());

							if (hotelproduct != null) {
								Sysparv5Id sys = new Sysparv5Id();
								sys.setPmcd(hotelproduct.getBedtype());
								sys.setPmky("BETP");
								Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
								hotelproduct.setStrbedtype(syspar.getPmva());

								products.setHotelproduct(hotelproduct);
							}

							// /�ж�ѡ�����ס�����Ƿ��м۸�
							int fk = 0;
							int num = Tools.getDayNumb(rzti, ldti);
							for (int x = 0; x < num; x++) {
								String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ products.getItickettypeid() + "  and pri.startdata<='"
										+ Tools.getDate(rzti, x) + "' and pri.enddata>='" + Tools.getDate(rzti, x)
										+ "' ";
								List lst = this.find(hsqlend);
								if (lst == null || lst.size() <= 0) {
									fk = 1;
									break;
								} else {
									fk = 2;
									continue;
								}

							}
							if (fk == 2) {
								// ���Ҽ۸�
								String hsql3 = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ products.getItickettypeid() + "  and pri.startdata<='" + rzti
										+ "' and pri.enddata>='" + rzti + "' ";

								List priceList = this.find(hsql3);
								if (priceList != null && priceList.size() >= 1) {
									Edmcrowdkindpricetab prices = (Edmcrowdkindpricetab) priceList.get(0);
									if (Tools.getDayOfWeek(rzti) == 6 || Tools.getDayOfWeek(rzti) == 7) {
										prices.setMactualsaleprice(prices.getWeekendprice());
									}
									products.setPrice(prices);
								}

								// ͼ��
								String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
										+ products.getItickettypeid();
								List piclist = this.find(hsqls);
								products.setList(piclist);
							}

						}

					}
					map.put("productList", productList);

					// ��ѯ�Ƶ�����
					Hotelprovider hotelprovider = new Hotelprovider();
					hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, Long.parseLong(iscenicid));
					// if(hotelprovider!=null){
					// //�Ƶ����
					// String
					// hsqls=" select new map(s.id.iscenicid as
					// iscenicid,s.id.svid as svid,sys.pmva as strsvc ) from
					// Hotelsvc s,Sysparv5 sys where s.id.iscenicid =
					// "+Long.parseLong(iscenicid)+" and sys.id.pmky='HTSC' and
					// sys.id.pmcd=s.id.svid ";
					// List hotelsvclist = this.find(hsqls);
					// hotelprovider.setHotelsvclist(hotelsvclist);
					// }
					map.put("hotelprovider", hotelprovider);

					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid) + " and p.itickettypeid=0 and p.upid = u.upid ";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid=" + new Long(iscenicid) + " and status='01' ";
					List commentList = this.find(sql2);
					double remarknum = 0;
					double marknum = 0;
					if (commentList != null && commentList.size() >= 1) {
						for (int x = 0; x < commentList.size(); x++) {
							Hscomment comment = (Hscomment) commentList.get(x);
							remarknum = remarknum + comment.getRemarknum();
						}
						marknum = remarknum / commentList.size();
					}

					map.put("commentsize", commentList.size());
					map.put("sumremarknum", marknum);

				}

			}
		}

		return ps;

	}

	/**
	 * 
	 * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * 
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return return:Esbscenicareatab Date:Feb 15, 2012
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti, String ldti, String lgtp) throws Exception {
		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.longitude as longitude) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='06' and pro.byisuse=1 and pro.isjd =0 and pro.iscenicid="
				+ iscenicid;

		List list = this.find(hsql);
		Map map = null;
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
			if (map.get("iscenicid") != null) {
				String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
						+ iscenicid
						+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti
						+ "' " + " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
						+ "' )  order by prd.sztickettypename";
				List productList = this.find(hsql2);
				if (productList != null && productList.size() >= 1) {
					for (int j = 0; j < productList.size(); j++) {

						Edmtickettypetab product = (Edmtickettypetab) productList.get(j);
						String[] names = product.getSztickettypename().split("-");
						product.setSztickettypename(names[0]);
						if (names.length > 1) {
							product.setBname(names[1]);
						}
						// �Ƶ��Ʒ��������
						Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,
								product.getItickettypeid());

						if (hotelproduct != null) {
							Sysparv5Id sys = new Sysparv5Id();
							sys.setPmcd(hotelproduct.getBedtype());
							sys.setPmky("BETP");
							Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
							hotelproduct.setStrbedtype(syspar.getPmva());

							product.setHotelproduct(hotelproduct);
						}

						// /�ж�ѡ�����ס�����Ƿ��м۸�

						long num = getDayNumb(rzti, ldti);
						double totalprice = 0;
						List pricelist = new ArrayList();
						Date enddate = null;
						Edmcrowdkindpricetab price = null;
						double singleprice = 0;
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						for (int x = 0; x < num; x++) {
							if (enddate != null && enddate.after(sf.parse(Tools.getDate(rzti, x)))) {
								singleprice = price.getMactualsaleprice();
								if (hotelproduct != null) {
									switch (hotelproduct.getWeektype()) {
									case 0:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									case 1:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
											singleprice = price.getWeekendprice();
										break;
									case 2:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									case 3:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									default:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									}
								}
								totalprice += singleprice;
								pricelist.add(singleprice);
							} else {
								String hsqlend = "select new map(pri.mactualsaleprice as mactualsaleprice,pri.weekendprice as weekendprice,pri.enddata as enddata)  from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ product.getItickettypeid() + "  and pri.startdata<='" + Tools.getDate(rzti, x)
										+ "' and pri.enddata>='" + Tools.getDate(rzti, x) + "' ";
								List lst = this.find(hsqlend);
								if (lst != null && lst.size() > 0) {
									Map cmap = (Map) lst.get(0);
									price = new Edmcrowdkindpricetab();
									BeanUtils.populate(price, cmap);
									enddate = sf.parse(price.getEnddata());
									singleprice = price.getMactualsaleprice();
									if (hotelproduct != null) {
										switch (hotelproduct.getWeektype()) {
										case 0:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										case 1:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
												singleprice = price.getWeekendprice();
											break;
										case 2:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										case 3:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										default:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										}
									}
									totalprice += singleprice;
									pricelist.add(singleprice);
								}
							}

						}
						product.setPriceList(pricelist);
						double jprice = Math.ceil(totalprice / num);
						product.setJprice(jprice);

						String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
								+ product.getItickettypeid() + " and p.upid = u.upid order by p.isecenicpictureid";
						List piclist = this.find(sql);
						product.setList(piclist);
					}
				}
				map.put("productList", productList);
				// ��ѯ�Ƶ�����
				Hotelprovider hotelprovider = new Hotelprovider();
				hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, iscenicid);
				if (hotelprovider != null) {
					Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class,
							new Sysparv5Id("HOTP", hotelprovider.getZxjb()));
					hotelprovider.setStrzxjb(sysparv5.getPmva());
				}
				map.put("hotelprovider", hotelprovider);

				// ��ѯ������ͼƬ
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.find(sql);
				map.put("piclist", piclist);
			}

		} else {// ��ѯ������
			return null;
		}
		Esbscenicareatab esbscen = new Esbscenicareatab();
		BeanUtils.populate(esbscen, map);

		this.bulkUpdate("update Esbscenicareatab s set s.popupoint = s.popupoint+1 where s.iscenicid = "
				+ esbscen.getIscenicid());

		return esbscen;

	}

	/**
	 * * Describe:�Ƶ������еľ����Ƽ�
	 * 
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#shopAllticketcenter(java.lang.Long,
	 *      java.lang.String, int, int, java.lang.String)
	 * @param szregionalid
	 *            ��Դ��
	 * @param lgtp
	 *            ��¼�˵�ע����� ɢ�� ������
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author lijingrui Date:Mar 2, 2012
	 */
	public PaginationSupport shopAllticketcenter(Long szregionalid, String lgtp, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		String sql = "select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szaddress as szaddress,pro.szgrade as szgrade) from Esbscenicareatab pro,Edmtickettypetab prd,Edmcrowdkindpricetab prc where pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid="
				+ szregionalid + " or g.iregionalid=" + szregionalid + ") and prc.startdata<='" + Tools.getDays()
				+ "' and prc.enddata>='" + Tools.getDays()
				+ "'  and pro.scenictype in('01','02','03','04') and pro.isjd=0 and prd.bycategorytype not in('120','119') and prc.ibusinessid="
				+ Long.parseLong(lgtp)
				+ "  and prc.icrowdkindid = 1 and pro.byisuse = 1 and prd.byisuse = 1 and prc.byisuse = 1  and prc.isnet = 1 and pro.iscenicid = prd.iscenicid and prd.itickettypeid = prc.itickettypeid  ";
		ps = this.findPage(sql, pageSize, page, url);
		List lst = ps.getItems();
		if (lst != null && lst.size() > 0) {
			Map map = null;
			for (int i = 0; i < lst.size(); i++) {
				map = (Map) lst.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ���Ҳ�Ʒ
					String hotelsql = "select distinct new map(prc.mactualsaleprice as mactualsaleprice,prc.weekendprice as weekendprice,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename ) from Edmtickettypetab prd,Edmcrowdkindpricetab prc where prc.startdata<='"
							+ Tools.getDays() + "' and prc.enddata>='" + Tools.getDays()
							+ "' and prd.bycategorytype not in('120','119') and prc.ibusinessid=" + Long.parseLong(lgtp)
							+ " and prc.icrowdkindid = 1 and prd.byisuse = 1 and prc.byisuse = 1  and prc.isnet = 1 and prd.iscenicid="
							+ Long.parseLong(iscenicid) + " and prd.itickettypeid = prc.itickettypeid  ";
					List hotelList = this.find(hotelsql);
					map.put("hotellist", hotelList);
				}
			}
		}
		return ps;
	}

	/**
	 * * Describe:��ʾ���еľƵ��Ʒ��Ϣ����Ѷ����ҳʹ�ã�
	 * 
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#queryHotelList(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.Long)
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param rowid
	 * @return
	 * @author lijingrui Date:Mar 12, 2012
	 */
	public List queryHotelList(String rzti, String ldti, String lgtp, Long rowid) {
		StringBuffer hsql = new StringBuffer();
		// �ж��Ƿ���ĩ��
		if (Tools.getDayOfWeek(rzti) == 6 || Tools.getDayOfWeek(rzti) == 7) {
			hsql.append(
					"select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname, prd.itickettypeid as itickettypeid, prd.sztickettypename as sztickettypename,pri.weekendprice as mactualsaleprice ) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro where pro.scenictype='06' and pri.startdata<='"
							+ rzti + "' and pri.enddata>='" + ldti
							+ "' and pri.byisuse=1 and prd.byisuse =1 and pri.isnet=1  and pro.byisuse = 1  and  prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid ");
		} else {
			hsql.append(
					"select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname, prd.itickettypeid as itickettypeid, prd.sztickettypename as sztickettypename,pri.mactualsaleprice as mactualsaleprice ) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro where pro.scenictype='06' and pri.startdata<='"
							+ rzti + "' and pri.enddata>='" + ldti
							+ "' and pri.byisuse=1 and prd.byisuse =1 and pri.isnet=1  and pro.byisuse = 1  and  prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid ");
		}
		if (lgtp != null) {
			if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
				hsql.append(" and pri.ibusinessid=2 ");
			} else {
				hsql.append(" and pri.ibusinessid=1 ");
			}
		} else {
			hsql.append(" and pri.ibusinessid=1 ");
		}
		if (rowid != null && rowid > 0) {
			hsql.append(" and rownum<=" + rowid);
		}
		hsql.append(" order by pro.iscenicid ");
		List list = this.find(hsql.toString());
		return list;
	}

	public Edmcrowdkindpricetab getTicketPrice(String itickettypeid, String tourDate, String icrowdkind,
			String ibusinessid, String groupno) throws RuntimeException {
		Edmcrowdkindpricetab price = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Edmcrowdkindpricetab p where p.itickettypeid=" + itickettypeid + " ");
		hsql.append("  and p.byisuse=1 and p.isnet=1 and p.ibusinessid =" + ibusinessid + " ");
		hsql.append(" and p.icrowdkindid =" + icrowdkind + " and p.note1='" + groupno + "' ");
		hsql.append(" and p.startdata<='" + tourDate + "' and p.enddata>='" + tourDate + "' ");
		hsql.append(" order by p.startdata ");
		System.out.println(hsql);
		List list = this.find(hsql.toString());
		if (list != null && list.size() > 0) {
			price = (Edmcrowdkindpricetab) list.get(0);
			String sql = "select p.weektype as weektype from Edmtickettypetab t, Esbscenicareatab s, Hotelproduct p where t.iscenicid = s.iscenicid and s.scenictype = '06' and t.itickettypeid = p.itickettypeid and t.itickettypeid="
					+ itickettypeid;
			List list2 = this.find(sql);
			if (list2 != null && !list2.isEmpty()) {
				int weektype = Integer.parseInt(list2.get(0).toString());
				switch (weektype) {
				case 0:
					if (Tools.getDayOfWeek(tourDate) == 6 || Tools.getDayOfWeek(tourDate) == 7)
						price.setMactualsaleprice(price.getWeekendprice());
					break;
				case 1:
					if (Tools.getDayOfWeek(tourDate) == 6 || Tools.getDayOfWeek(tourDate) == 7
							|| Tools.getDayOfWeek(tourDate) == 1)
						price.setMactualsaleprice(price.getWeekendprice());
					break;
				case 2:
					if (Tools.getDayOfWeek(tourDate) == 5 || Tools.getDayOfWeek(tourDate) == 6
							|| Tools.getDayOfWeek(tourDate) == 7)
						price.setMactualsaleprice(price.getWeekendprice());
					break;
				case 3:
					if (Tools.getDayOfWeek(tourDate) == 1 || Tools.getDayOfWeek(tourDate) == 5
							|| Tools.getDayOfWeek(tourDate) == 6 || Tools.getDayOfWeek(tourDate) == 7)
						price.setMactualsaleprice(price.getWeekendprice());
					break;
				default:
					if (Tools.getDayOfWeek(tourDate) == 6 || Tools.getDayOfWeek(tourDate) == 7)
						price.setMactualsaleprice(price.getWeekendprice());
					break;
				}
			} else {
				if ((Tools.getDayOfWeek(tourDate) == 6 || Tools.getDayOfWeek(tourDate) == 7)
						&& (price.getWeekendprice() != null && price.getWeekendprice() != 0
								&& !price.getWeekendprice().equals(""))) {
					price.setMactualsaleprice(price.getWeekendprice());
				}
			}
		}
		return price;
	}

	public PaginationSupport getHotelProductList(SearchKey keys, String rzti, String ldti, String lgtp, int page,
			int pageSize, String url) throws Exception {
		PaginationSupport ps = null;
		// String ssql =
		// "select distinct new map(p.iscenicid as iscenicid) from
		// Edmtickettypetab product,Esbscenicareatab p where "
		// +
		// " p.iscenicid = product.iscenicid and p.scenictype = '06' and
		// p.byisuse=1 and product.byisuse=1";
		String ssql = "select distinct new map(p.iscenicid as iscenicid,min(price.mactualsaleprice) as mactualsaleprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p  where price.itickettypeid = product.itickettypeid and price.ibusinessid ="
				+ Long.parseLong(lgtp) + " and price.note1 = '0000' "
				+ "and p.iscenicid = product.iscenicid and p.scenictype = '06' and product.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid) <='" + rzti + "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid))>='" + ldti
				+ "' ) and '" + rzti + "' between price.startdata and price.enddata ";
		if (keys.getLowprice() != null && !keys.getLowprice().equals("")) {
			ssql += " and price.mactualsaleprice>=" + Double.parseDouble(keys.getLowprice());
		}
		if (keys.getHeight() != null && !keys.getHeight().equals("")) {
			ssql += " and price.mactualsaleprice<=" + Double.parseDouble(keys.getHeight());
		}
		ssql += " group by p.iscenicid";
		if (keys.getOrderby() != null) {
			if (keys.getOrderby().equals("2")) {
				ssql += " order by min(price.mactualsaleprice) desc";
			} else {
				ssql += " order by min(price.mactualsaleprice)";
			}
		} else {
			ssql += " order by min(price.mactualsaleprice)";
		}
		List slist = this.find(ssql);
		StringBuffer order1 = new StringBuffer();
		if (slist != null && !slist.isEmpty()) {
			StringBuffer s = new StringBuffer();
			for (int i = 0; i < slist.size(); i++) {
				Map smap = (Map) slist.get(i);
				order1.append(smap.get("iscenicid").toString() + "," + (i + 1) + ",");
				s.append(smap.get("iscenicid").toString() + ",");
			}

			if (keys.getScenicname() == null || keys.getScenicname().trim().equals("")) {
				String iscenicids = s.toString().substring(0, s.toString().length() - 1);
				StringBuffer hsql = new StringBuffer();
				hsql.append(
						" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber, s1.pmva as strgrade,pro.popupoint as popupoint,pro.commentpoint as commentpoint,pro.longitude as longitude,hp.zxjb as zxjb,s2.pmva as strzxjb) from  Esbscenicareatab pro,Hotelprovider hp,Sysparv5 s1,Sysparv5 s2,Galsourceregiontab gal where gal.iregionalid=pro.szregionalid and pro.iscenicid = hp.iscenicid and pro.byisuse=1 and pro.isjd =0 and s1.id.pmcd=pro.szgrade and s1.id.pmky='DENJ' and s2.id.pmcd=hp.zxjb and s2.id.pmky='HOTP' and pro.iscenicid in ("
								+ iscenicids + ") ");
				if (keys.getArea() != null && !keys.getArea().equals("")) {
					hsql.append(
							" and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iregionalid ="
									+ Long.parseLong(keys.getArea()) + " or g.iparentid ="
									+ Long.parseLong(keys.getArea()) + ") ");
				}
				if (keys.getScenicname() != null && !keys.getScenicname().equals("")) {
					hsql.append(" and ( pro.szscenicname like '%" + keys.getScenicname() + "%' or hp.noted1 like '%"
							+ keys.getScenicname() + "%' or pro.szlocation like '%" + keys.getScenicname()
							+ "%'  or pro.szaddress like '%" + keys.getScenicname() + "%' or gal.szinnername like '%"
							+ keys.getScenicname() + "%'  )");
				}
				if (keys.getGrade() != null && !keys.getGrade().equals("")) {
					String[] grades = keys.getGrade().replace(" ", "").split(",");
					StringBuffer grade = new StringBuffer();
					for (int i = 0; i < grades.length; i++) {
						grade.append("'" + grades[i] + "',");
					}
					hsql.append(" and pro.szgrade in (" + grade.toString().substring(0, grade.toString().length() - 1)
							+ ") ");
				}
				if (keys.getOrderby() != null && (keys.getOrderby().equals("1") || keys.getOrderby().equals("2"))) {
					String order = order1.toString().substring(0, order1.toString().length() - 1);
					hsql.append(" order by decode(pro.iscenicid," + order + ")");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("3")) {
					hsql.append(" order by decode(pro.szgrade,'99',0,'08',1,'09',2,'10',3) ");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("4")) {
					hsql.append(" order by decode(pro.szgrade,'10',0,'09',1,'08',2,'99',3) ");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("5")) {
					hsql.append(" order by pro.commentpoint desc");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("6")) {
					hsql.append(" order by pro.popupoint desc");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("7")) {
					hsql.append(" order by pro.iordernumber desc");
				}
				ps = findPage(hsql.toString(), pageSize, page, url);
			} else {
				// ��һ����ѯ
				String iscenicids = s.toString().substring(0, s.toString().length() - 1);
				StringBuffer hsql = new StringBuffer();
				hsql.append(
						" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber, s1.pmva as strgrade,pro.popupoint as popupoint,pro.commentpoint as commentpoint,pro.longitude as longitude,hp.zxjb as zxjb,s2.pmva as strzxjb) from  Esbscenicareatab pro,Hotelprovider hp,Sysparv5 s1,Sysparv5 s2,Galsourceregiontab gal where gal.iregionalid=pro.szregionalid and pro.iscenicid = hp.iscenicid and pro.byisuse=1 and pro.isjd =0 and s1.id.pmcd=pro.szgrade and s1.id.pmky='DENJ' and s2.id.pmcd=hp.zxjb and s2.id.pmky='HOTP' and pro.iscenicid in ("
								+ iscenicids + ") ");
				if (keys.getArea() != null && !keys.getArea().equals("")) {
					hsql.append(
							" and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iregionalid ="
									+ Long.parseLong(keys.getArea()) + " or g.iparentid ="
									+ Long.parseLong(keys.getArea()) + ") ");
				}
				if (keys.getScenicname() != null && !keys.getScenicname().equals("")) {
					hsql.append(" and  pro.szscenicname like '%" + keys.getScenicname() + "%'  ");
				}
				if (keys.getGrade() != null && !keys.getGrade().equals("")) {
					String[] grades = keys.getGrade().replace(" ", "").split(",");
					StringBuffer grade = new StringBuffer();
					for (int i = 0; i < grades.length; i++) {
						grade.append("'" + grades[i] + "',");
					}
					hsql.append(" and pro.szgrade in (" + grade.toString().substring(0, grade.toString().length() - 1)
							+ ") ");
				}
				if (keys.getOrderby() != null && (keys.getOrderby().equals("1") || keys.getOrderby().equals("2"))) {
					String order = order1.toString().substring(0, order1.toString().length() - 1);
					hsql.append(" order by decode(pro.iscenicid," + order + ")");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("3")) {
					hsql.append(" order by decode(pro.szgrade,'99',0,'08',1,'09',2,'10',3) ");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("4")) {
					hsql.append(" order by decode(pro.szgrade,'10',0,'09',1,'08',2,'99',3) ");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("5")) {
					hsql.append(" order by pro.commentpoint desc");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("6")) {
					hsql.append(" order by pro.popupoint desc");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("7")) {
					hsql.append(" order by pro.iordernumber desc");
				}
				// ����������
				List list4 = find(hsql.toString());

				// �ڶ�����ѯ
				hsql.setLength(0);
				StringBuffer not_s = new StringBuffer();
				for (int i = 0; i < list4.size(); i++) {
					Map smap = (Map) list4.get(i);
					not_s.append(smap.get("iscenicid").toString() + ",");
				}
				String not_iscenicid = s.toString().substring(0, s.toString().length() - 1);
				hsql.append(
						" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber, s1.pmva as strgrade,pro.popupoint as popupoint,pro.commentpoint as commentpoint,pro.longitude as longitude,hp.zxjb as zxjb,s2.pmva as strzxjb) from  Esbscenicareatab pro,Hotelprovider hp,Sysparv5 s1,Sysparv5 s2,Galsourceregiontab gal where gal.iregionalid=pro.szregionalid and pro.iscenicid = hp.iscenicid and pro.byisuse=1 and pro.isjd =0 and s1.id.pmcd=pro.szgrade and s1.id.pmky='DENJ' and s2.id.pmcd=hp.zxjb and s2.id.pmky='HOTP' and pro.iscenicid in ("
								+ iscenicids + ") and pro.iscenicid not in (" + not_iscenicid + ")");
				if (keys.getArea() != null && !keys.getArea().equals("")) {
					hsql.append(
							" and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iregionalid ="
									+ Long.parseLong(keys.getArea()) + " or g.iparentid ="
									+ Long.parseLong(keys.getArea()) + ") ");
				}
				if (keys.getScenicname() != null && !keys.getScenicname().equals("")) {
					hsql.append(" and ( hp.noted1 like '%" + keys.getScenicname() + "%' or pro.szlocation like '%"
							+ keys.getScenicname() + "%'  or pro.szaddress like '%" + keys.getScenicname()
							+ "%' or gal.szinnername like '%" + keys.getScenicname() + "%' )");
				}
				if (keys.getGrade() != null && !keys.getGrade().equals("")) {
					String[] grades = keys.getGrade().replace(" ", "").split(",");
					StringBuffer grade = new StringBuffer();
					for (int i = 0; i < grades.length; i++) {
						grade.append("'" + grades[i] + "',");
					}
					hsql.append(" and pro.szgrade in (" + grade.toString().substring(0, grade.toString().length() - 1)
							+ ") ");
				}
				if (keys.getOrderby() != null && (keys.getOrderby().equals("1") || keys.getOrderby().equals("2"))) {
					String order = order1.toString().substring(0, order1.toString().length() - 1);
					hsql.append(" order by decode(pro.iscenicid," + order + ")");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("3")) {
					hsql.append(" order by decode(pro.szgrade,'99',0,'08',1,'09',2,'10',3) ");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("4")) {
					hsql.append(" order by decode(pro.szgrade,'10',0,'09',1,'08',2,'99',3) ");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("5")) {
					hsql.append(" order by pro.commentpoint desc");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("6")) {
					hsql.append(" order by pro.popupoint desc");
				}
				if (keys.getOrderby() != null && keys.getOrderby().equals("7")) {
					hsql.append(" order by pro.iordernumber desc");
				}
				// ��������
				List list5 = find(hsql.toString());

				list4.addAll(list5);

				if (list4 == null || list4.size() < 0) {
					return null;
				} /*
					 * else { for (int i = 0; i < list4.size(); i++) { int index
					 * = 0; Map map = (Map) list4.get(i); Long iscenicid =
					 * Long.valueOf(map.get("iscenicid").toString()); for (int j
					 * = 0; j < list4.size(); j++) { Map map2 = (Map)
					 * list4.get(j); Long iscenicid2 =
					 * Long.valueOf(map2.get("iscenicid").toString()); if
					 * (iscenicid.equals(iscenicid2) ||
					 * iscenicid.intValue()==iscenicid2.intValue()){ index++; }
					 * }
					 * 
					 * if (index>1) { //System.out.println("ȥ����");
					 * list4.remove(i); } } }
					 */

				// ��ʼ��PaginationSupport�ı���Ϣ, pageSize, page, url
				int start = (page - 1) * pageSize;
				int end = page * pageSize;
				if (end > list4.size()) {
					end = list4.size();
				}

				ps = new PaginationSupport(list4.subList(start, end), list4.size(), page, pageSize, url);
			}

			List list = ps.getItems();

			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					if (map.get("iscenicid") != null) {
						Long iscenicid = Long.parseLong(map.get("iscenicid").toString());

						// ���Ҳ�Ʒ
						String hsql2 = "select distinct new map(prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename) from Edmtickettypetab prd,Edmcrowdkindpricetab p where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
								+ iscenicid
								+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
								+ Long.parseLong(lgtp)
								+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
								+ Long.parseLong(lgtp)
								+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
								+ rzti + "' "
								+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
								+ Long.parseLong(lgtp)
								+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
								+ ldti + "' ) and p.itickettypeid = prd.itickettypeid and '" + rzti
								+ "' between p.startdata and p.enddata ";
						if (keys.getLowprice() != null && !keys.getLowprice().equals("")) {
							hsql2 += " and p.mactualsaleprice >=" + Double.parseDouble(keys.getLowprice());
						}
						if (keys.getHeight() != null && !keys.getHeight().equals("")) {
							hsql2 += " and p.mactualsaleprice <=" + Double.parseDouble(keys.getHeight());
						}
						hsql2 += " order by prd.sztickettypename ";
						System.out.println(hsql2);
						List productList = this.find(hsql2);
						List productList2 = new ArrayList();
						if (productList != null && productList.size() > 0) {
							for (int j = 0; j < productList.size(); j++) {
								Edmtickettypetab product = new Edmtickettypetab();
								Map promap = (Map) productList.get(j);
								BeanUtils.populate(product, promap);
								String[] names = product.getSztickettypename().split("-");
								product.setSztickettypename(names[0]);
								if (names.length > 1) {
									product.setBname(names[1]);
								}
								// �Ƶ��Ʒ��������
								Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,
										product.getItickettypeid());

								if (hotelproduct != null) {
									Sysparv5Id sys = new Sysparv5Id();
									sys.setPmcd(hotelproduct.getBedtype());
									sys.setPmky("BETP");
									Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
									hotelproduct.setStrbedtype(syspar.getPmva());

									product.setHotelproduct(hotelproduct);
								}

								// /�ж�ѡ�����ס�����Ƿ��м۸�
								long num = getDayNumb(rzti, ldti);
								double totalprice = 0;
								List pricelist = new ArrayList();
								Date enddate = null;
								Edmcrowdkindpricetab price = null;
								double singleprice = 0;
								SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
								for (int x = 0; x < num; x++) {
									if (enddate != null && enddate.after(sf.parse(Tools.getDate(rzti, x)))) {
										singleprice = price.getMactualsaleprice();
										if (hotelproduct != null) {
											switch (hotelproduct.getWeektype()) {
											case 0:
												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
													singleprice = price.getWeekendprice();
												break;
											case 1:
												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
													singleprice = price.getWeekendprice();
												break;
											case 2:
												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
													singleprice = price.getWeekendprice();
												break;
											case 3:
												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
													singleprice = price.getWeekendprice();
												break;
											default:
												if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
														|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
													singleprice = price.getWeekendprice();
												break;
											}
										}
										totalprice += singleprice;
										pricelist.add(singleprice);
									} else {
										String hsqlend = "select distinct new map(pri.mactualsaleprice as mactualsaleprice,pri.weekendprice as weekendprice,pri.enddata as enddata)  from Edmcrowdkindpricetab pri where pri.ibusinessid="
												+ Long.parseLong(lgtp)
												+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
												+ Long.parseLong(lgtp)
												+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
												+ product.getItickettypeid() + ") <='" + rzti + "' "
												+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
												+ Long.parseLong(lgtp)
												+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
												+ product.getItickettypeid() + "))>='" + ldti
												+ "' and pri.itickettypeid=" + product.getItickettypeid()
												+ "  and pri.startdata<='" + Tools.getDate(rzti, x)
												+ "' and pri.enddata>='" + Tools.getDate(rzti, x) + "' ";
										List lst = this.find(hsqlend);
										if (lst != null && lst.size() > 0) {
											Map cmap = (Map) lst.get(0);
											price = new Edmcrowdkindpricetab();
											BeanUtils.populate(price, cmap);
											enddate = sf.parse(price.getEnddata());
											singleprice = price.getMactualsaleprice();

											if (hotelproduct != null) {
												switch (hotelproduct.getWeektype()) {
												case 0:
													if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
														singleprice = price.getWeekendprice();
													break;
												case 1:
													if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
														singleprice = price.getWeekendprice();
													break;
												case 2:
													if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
														singleprice = price.getWeekendprice();
													break;
												case 3:
													if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
														singleprice = price.getWeekendprice();
													break;
												default:
													if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
															|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
														singleprice = price.getWeekendprice();
													break;
												}
											}
											totalprice += singleprice;
											pricelist.add(singleprice);
										}
									}

								}
								product.setPriceList(pricelist);
								double jprice = Math.ceil(totalprice / num);
								product.setJprice(jprice);
								productList2.add(product);
							}

						}
						map.put("productList", productList2);

						// ��ѯ������ͼƬ
						String sql = " select distinct new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
								+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid ";
						List piclist = this.find(sql);
						map.put("piclist", piclist);

						Map lowprice = getMinPrice(iscenicid, rzti);
						double listingprice = 0L;
						double sprice = 0L;
						if (lowprice != null) {
							listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
							sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
						}
						map.put("lowprice", sprice);
						map.put("height", listingprice);
						map.put("discount", String.valueOf(Math.round(sprice / listingprice * 100) / 10));

						int total = 0;
						int htotal = 0;
						String comsql = " from Hscomment where oeid=" + iscenicid + " and status='01' ";
						List clist = this.find(comsql);
						if (clist != null && !clist.isEmpty()) {
							total = clist.size();
						}
						comsql += " and  remarknum>=3 ";
						clist = this.find(comsql);
						if (clist != null && !clist.isEmpty()) {
							htotal = clist.size();
						}
						map.put("ctotal", total);
						if (total == 0 || htotal == 0) {
							map.put("tjs", "100");
						} else {
							map.put("tjs", Math.ceil(total / htotal * 100));
						}
					}
				}
			}
		}
		return ps;
	}

	public Map getMinPrice(Long iscenicid, String rzti) {
		String hql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab     p  where price.itickettypeid = product.itickettypeid and price.ibusinessid = 1 and price.note1 = '0000'  and p.iscenicid = "
				+ iscenicid + " and p.iscenicid = product.iscenicid and '" + rzti
				+ "' between  price.startdata and  price.enddata order by price.mactualsaleprice";
		List list = find(hql);
		Map pricemap = null;
		if (list != null && list.size() > 0) {
			pricemap = (Map) list.get(0);
		}
		return pricemap;
	}

	public List searchProduct(Long iscenicid, String rzti) {
		Hotelprovider hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, iscenicid);
		if (hotelprovider != null) {
			String productid = hotelprovider.getNoted8();
			if (productid != null && !productid.equals("")) {
				StringBuffer hsql = new StringBuffer();
				hsql.append(
						"select new map(s.iscenicid as iscenicid,s.scenictype as scenictype,t.itickettypeid as itickettypeid,t.sztickettypename as sztickettypename) from Edmtickettypetab t, Esbscenicareatab s ");
				hsql.append(" where t.itickettypeid in (" + productid + ")   and s.iscenicid = t.iscenicid");
				System.out.println("hsql:" + hsql.toString());
				List list = this.find(hsql.toString());
				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						Map map = (Map) list.get(i);
						// ͼƬ
						String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
								+ Long.parseLong(map.get("itickettypeid").toString())
								+ " and p.upid = u.upid order by p.isecenicpictureid";
						List piclist = this.find(sql);
						if (piclist != null && !piclist.isEmpty()) {
							Map map2 = (Map) piclist.get(0);
							map.put("upadder", map2.get("upadder").toString());
							map.put("upfilename", map2.get("upfilename").toString());
							map.put("upname", map2.get("upname").toString());
						}
						// �۸�
						String sqlp = "select new map(p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice) from Edmcrowdkindpricetab p where  p.note1 = '0000' and p.icrowdkindid = 1 and p.ibusinessid = 1 and p.itickettypeid="
								+ Long.parseLong(map.get("itickettypeid").toString()) + " and p.startdata <= '" + rzti
								+ "' and p.enddata>='" + rzti + "'";
						System.out.println(sqlp);
						List pirList = this.find(sqlp);

						if (pirList != null && !pirList.isEmpty()) {
							Map map3 = (Map) pirList.get(0);
							map.put("listingprice", map3.get("listingprice").toString());
							map.put("mactualsaleprice", map3.get("mactualsaleprice").toString());
						} else {
							map.put("listingprice", 0);
							map.put("mactualsaleprice", 0);
							list.remove(i);
							i--;
						}
					}
					return list;
				}
			}
		}
		return null;
	}

	public List searchAddress(Long regionalid) {
		List resultList = null;
		String sql = "select new map(a.address as address) from Addresslink a where a.regionalid=" + regionalid;
		List list = this.find(sql);
		if (list == null || list.isEmpty()) {
			sql = "select new map(a.address as address) from Addresslink a,Galsourceregiontab g where a.regionalid=g.iparentid and g.iregionalid="
					+ regionalid;
			list = this.find(sql);
		}
		if (list != null && !list.isEmpty()) {
			resultList = new ArrayList();
			Map map = (Map) list.get(0);
			String[] address = map.get("address").toString().split("\\s+");
			for (int i = 0; i < address.length; i++) {
				Map map2 = new HashMap();
				map2.put("address", address[i]);
				resultList.add(map2);
			}
			Map map2 = new HashMap();
			map2.put("address", "����");
			resultList.add(0, map2);
		}
		return resultList;
	}

	public List getHotHotel(Long iregionalid, Long iscenicid, int top) {
		/*
		 * String ssql =
		 * "select distinct new map(p.iscenicid as iscenicid) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p  where price.itickettypeid = product.itickettypeid and price.ibusinessid ="
		 * + Long.parseLong("01") + " and price.note1 = '0000' " +
		 * "and p.iscenicid = product.iscenicid and p.scenictype = '06' and product.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
		 * + Long.parseLong("01") +
		 * " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
		 * + Long.parseLong("01") +
		 * " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid) <='"
		 * + Tools.getDays() + "' " +
		 * " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
		 * + Long.parseLong("01") +
		 * " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid))>='"
		 * + Tools.getDays() + "' ) and '" + Tools.getDays() +
		 * "' between price.startdata and price.enddata ";
		 */
		String ssql = "select distinct new map(p.iscenicid as iscenicid) from Edmtickettypetab product, Esbscenicareatab p where p.iscenicid = product.iscenicid and p.scenictype = '06' ";
		System.out.println(ssql);
		List sslist = this.find(ssql);
		if (sslist != null && !sslist.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			for (int i = 0; i < sslist.size(); i++) {
				Map smap = (Map) sslist.get(i);
				ids.append(smap.get("iscenicid").toString() + ",");
			}
			String iscenicids = ids.toString().substring(0, ids.toString().length() - 1);
			StringBuffer hsql = new StringBuffer();
			hsql.append(
					"select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.szgrade as szgrade,sv.pmva as strgrade,s.szaddress as szaddress,p.noted1 as address,p.noted4 as zdate,p.zxjb as zxjb,sys.pmva as strzxjb) from Esbscenicareatab s,Hotelprovider p,Sysparv5 sv,Sysparv5 sys ");
			hsql.append(
					" where s.byisuse =1 and  s.iscenicid = p.iscenicid and s.scenictype = '06' and sv.id.pmky = 'DENJ' and sv.id.pmcd = s.szgrade and sys.id.pmky='HOTP' and sys.id.pmcd=p.zxjb and s.iscenicid in ("
							+ iscenicids + ") ");
			if (iregionalid != null && !"".equals(iregionalid) && iregionalid != 0) {
				hsql.append(" and s.szregionalid =" + iregionalid);
			}
			if (iscenicid != null && !"".equals(iscenicid) && iscenicid != 0) {
				hsql.append(" and p.noted6 is not null");
				hsql.append(" and (p.noted6 like '" + iscenicid + "' or p.noted6 like '" + iscenicid
						+ ",%' or p.noted6 like '%," + iscenicid + "' or p.noted6 like '%," + iscenicid + ",%') ");
			}
			hsql.append(" order by s.iordernumber desc");
			List list = this.findTopNumb(hsql.toString(), top);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					// ��ѯ������ͼƬ
					Long providerid = Long.parseLong(map.get("iscenicid").toString());
					if (map.get("address") == null) {
						map.put("address", "");
					}
					if (map.get("zdate") == null) {
						map.put("zdate", "");
					}
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
							+ providerid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.findTopNumb(sql, 1);
					String upadder = "";
					String upfilename = "";
					if (piclist != null && !piclist.isEmpty()) {
						Map picMap = (Map) piclist.get(0);
						upadder = picMap.get("upadder").toString();
						upfilename = picMap.get("upfilename").toString();
					}
					map.put("upadder", upadder);
					map.put("upfilename", upfilename);

					Map lowprice = getMinPrice(providerid, Tools.getDays());
					double sprice = 0L;
					if (lowprice != null) {
						sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
					}
					map.put("lowprice", sprice);

					String sql2 = " select new map(h.note as note) from Hscomment h where h.oeid=" + providerid
							+ " and h.status='01' and h.remarknum>=4 order by remarknum desc,seq desc";
					List comList = this.findTopNumb(sql2, 1);
					String comment = "";
					if (comList != null && !comList.isEmpty()) {
						Map comMap = (Map) comList.get(0);
						comment = comMap.get("note").toString();
					}
					map.put("comment", comment);
				}
			}
			return list;
		}
		return null;
	}

	public List getSpecialHotel(int top) {
		String ssql = "select distinct new map(p.iscenicid as iscenicid) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p,Hotelproduct pd  where price.itickettypeid = product.itickettypeid and product.itickettypeid = pd.itickettypeid and pd.inoteger1 = 1 and price.ibusinessid ="
				+ Long.parseLong("01") + " and price.note1 = '0000' "
				+ "and p.iscenicid = product.iscenicid and p.scenictype = '06' and product.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
				+ Long.parseLong("01")
				+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong("01")
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid) <='"
				+ Tools.getDays() + "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong("01")
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid))>='"
				+ Tools.getDays() + "' ) and '" + Tools.getDays() + "' between price.startdata and price.enddata ";
		System.out.println(ssql);
		List sslist = this.find(ssql);
		if (sslist != null && !sslist.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			for (int i = 0; i < sslist.size(); i++) {
				Map smap = (Map) sslist.get(i);
				ids.append(smap.get("iscenicid").toString() + ",");
			}
			String iscenicids = ids.toString().substring(0, ids.toString().length() - 1);
			String hsql = "select distinct new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.iordernumber as iordernumber) from "
					+ "Esbscenicareatab s, Edmtickettypetab t, Hotelproduct p where s.iscenicid = t.iscenicid and t.itickettypeid = p.itickettypeid and t.byisuse = 1 and s.iscenicid in ("
					+ iscenicids + ") " + " and s.byisuse = 1 and p.inoteger1 = 1 order by s.iordernumber desc";
			List list = this.findTopNumb(hsql, top);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
					String sql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p,Hotelproduct hp  where price.itickettypeid = product.itickettypeid and price.ibusinessid = 1 and price.note1 = '0000'  and p.iscenicid = "
							+ iscenicid
							+ " and p.iscenicid = product.iscenicid and hp.itickettypeid = product.itickettypeid and hp.inoteger1 = 1 and '"
							+ Tools.getDays()
							+ "' between  price.startdata and  price.enddata order by price.mactualsaleprice";

					double listingprice = 0L;
					double sprice = 0L;
					List priceList = this.findTopNumb(sql, 1);
					if (priceList != null && !priceList.isEmpty()) {
						Map lowprice = (Map) priceList.get(0);
						listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
						sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
					}
					map.put("lowprice", sprice);
					map.put("height", listingprice);

					String sql2 = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
							+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.findTopNumb(sql2, 1);
					String upadder = "";
					String upfilename = "";
					if (piclist != null && !piclist.isEmpty()) {
						Map picMap = (Map) piclist.get(0);
						upadder = picMap.get("upadder").toString();
						upfilename = picMap.get("upfilename").toString();
					}
					map.put("upadder", upadder);
					map.put("upfilename", upfilename);
				}
			}
			return list;
		}
		return null;
	}

	public Edmtickettypetab getHotel(Long hotelid) throws Exception {
		Edmtickettypetab hotel = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select new map(t.itickettypeid as itickettypeid,t.iscenicid as iscenicid,t.sztickettypename as sztickettypename,t.bycategorytype as bycategorytype,sys.pmva as strbycategorytype) from Edmtickettypetab t,Sysparv5 sys where t.bycategorytype=sys.id.pmcd and sys.id.pmky='PRTP' and t.itickettypeid="
						+ hotelid);
		List list = this.find(hsql.toString());
		if (list != null && !list.isEmpty()) {
			hotel = new Edmtickettypetab();
			Map map = (Map) list.get(0);
			BeanUtils.populate(hotel, map);
			String sql2 = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
					+ hotelid + " and p.upid = u.upid order by p.isecenicpictureid";
			List piclist = this.find(sql2);
			if (piclist != null && !piclist.isEmpty()) {
				hotel.setList(piclist);
			}
		}
		return hotel;
	}

	public long getDayNumb(String rzti, String ldti) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startdate = sdf.parse(rzti);
		Date enddate = sdf.parse(ldti);
		long time = enddate.getTime() - startdate.getTime();
		long time2 = 24 * 3600 * 1000;
		return time / time2;
	}

	/**
	 * * Describe:��ѯ�Ƽ������̣���Ѷ��ר��
	 * 
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#queryProviders(java.lang.String,
	 *      java.lang.String, int)
	 * @param keys
	 *            �ؼ��֣��Զ��Ÿ���
	 * @param type
	 *            ����������
	 * @param top
	 * @return
	 * @author chenxinhao Date:2013-12-24
	 */
	public List queryProviders(String keys, String type, int top) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select distinct new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.commentpoint as commentpoint,g.szinnername as szinnername,sys.pmva as strgrade,s.iordernumber as iordernumber) from Esbscenicareatab s,Galsourceregiontab g,Sysparv5 sys  where s.byisuse=1 and s.szregionalid = g.iregionalid and sys.id.pmcd= s.szgrade and sys.id.pmky='DENJ' and s.scenictype='"
						+ type + "' ");
		if (keys != null && !keys.equals("")) {
			String[] key = keys.split(",");
			hsql.append(" and (");
			for (int i = 0; i < key.length; i++) {
				if (i == 0) {
					hsql.append(
							"  s.szscenicname like '%" + key[i] + "%' or g.szinnername  like '% " + key[i] + " %' ");
				} else {
					hsql.append(
							" or s.szscenicname like '%" + key[i] + "%' or g.szinnername  like '% " + key[i] + " %' ");
				}
			}
			hsql.append(" )");
		}
		List list = this.findTopNumb(hsql.toString(), top);
		StringBuffer iscenicid_where = new StringBuffer();
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				if (i != 0) {
					iscenicid_where.append(",");
				}
				Long iscenicid = Long.valueOf(map.get("iscenicid").toString());
				iscenicid_where.append(iscenicid);
			}
		}

		if (list.size() < top) {
			hsql.setLength(0);
			hsql.append(
					"select distinct new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.commentpoint as commentpoint,g.szinnername as szinnername,sys.pmva as strgrade,s.iordernumber as iordernumber) from Esbscenicareatab s,Galsourceregiontab g,Sysparv5 sys  where s.byisuse=1 and s.szregionalid = g.iregionalid and sys.id.pmcd= s.szgrade and sys.id.pmky='DENJ' and s.scenictype='"
							+ type + "' and  s.iscenicid not in(" + iscenicid_where.toString() + ")");
			if (keys != null && !keys.equals("")) {
				String[] key = keys.split(",");
				hsql.append(" and (");
				for (int i = 0; i < key.length; i++) {
					if (i == 0) {
						hsql.append(" s.szaddress like '%" + key[i] + "%' or s.szlocation like '%" + key[i] + "%' ");
					} else {
						hsql.append(
								" or s.szaddress like '%" + key[i] + "%' or s.szlocation like '%" + key[i] + "%'  ");
					}
				}
				hsql.append(" )");
			}
			List list1 = this.findTopNumb(hsql.toString(), top - list.size());
			list.addAll(list1);
		}

		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long iscenicid = Long.parseLong(map.get("iscenicid").toString());

				String sql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p where price.itickettypeid = product.itickettypeid and price.ibusinessid = 1 and price.note1 = '0000'  and p.iscenicid = "
						+ iscenicid + " and p.iscenicid = product.iscenicid and '" + Tools.getDays()
						+ "' between  price.startdata and  price.enddata order by price.mactualsaleprice";

				Hotelprovider hp = (Hotelprovider) this.get(Hotelprovider.class, iscenicid);
				if (hp != null) {
					Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class, new Sysparv5Id("HOTP", hp.getZxjb()));
					map.put("strzxjb", sysparv5.getPmva());
				} else {
					map.put("strzxjb", "");
				}
				double listingprice = 0L;
				double sprice = 0L;
				List priceList = this.findTopNumb(sql, 1);
				if (priceList != null && !priceList.isEmpty()) {
					Map lowprice = (Map) priceList.get(0);
					listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
					sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
				}
				map.put("lowprice", sprice);
				map.put("height", listingprice);

				String comsql = " from Hscomment where oeid=" + iscenicid + " and status='01' ";
				List commentList = this.find(comsql);
				long count = 0;
				if (commentList != null && !commentList.isEmpty()) {
					count = commentList.size();
				}
				map.put("commentCount", count);

				// ��ѯ������ͼƬ
				String ssql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.findTopNumb(ssql, 1);
				String upadder = "";
				String upfilename = "";
				if (piclist != null && !piclist.isEmpty()) {
					Map picMap = (Map) piclist.get(0);
					upadder = picMap.get("upadder").toString();
					upfilename = picMap.get("upfilename").toString();
				}
				map.put("upadder", upadder);
				map.put("upfilename", upfilename);
			}
		}
		List list2 = new ArrayList();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String szscenicname = map.get("szscenicname").toString();
				if (szscenicname != null && keys != null) {
					if (szscenicname.contains(keys)) {
						list2.add(map);
						list.remove(i);
					}
				}
			}
		}
		list2.addAll(list);

		return list2;
	}

	public List getHotJinqu() {

		String hsql = "select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.szregionalid as szregionalid) from Esbscenicareatab s where s.scenictype='01' and s.byisuse=1  and s.irootid=0 order by s.iordernumber desc,s.popupoint desc";
		List list = this.findTopNumb(hsql, 3);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long szregionalid = Long.parseLong(map.get("szregionalid").toString());
				Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
				String sql = "select new map(s.iscenicid as iscenicid) from Esbscenicareatab s where s.scenictype='06' and s.byisuse=1 and  s.szregionalid in (select g.iregionalid as iregionalid from Galsourceregiontab g where g.iregionalid="
						+ szregionalid + " or g.iparentid=" + szregionalid + ")";
				List slist = this.find(sql);
				if (list != null && !list.isEmpty()) {
					map.put("total", slist.size());
				} else {
					map.put("total", 0);
				}
				String sql2 = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.findTopNumb(sql2, 1);
				String upadder = "";
				String upfilename = "";
				if (piclist != null && !piclist.isEmpty()) {
					Map picMap = (Map) piclist.get(0);
					upadder = picMap.get("upadder").toString();
					upfilename = picMap.get("upfilename").toString();
				}
				map.put("upadder", upadder);
				map.put("upfilename", upfilename);
			}
		}
		return list;
	}

	/**
	 * 
	 * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * 
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return return:Esbscenicareatab Date:Feb 15, 2012
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti, String ldti, Long ibusinessid)
			throws Exception {
		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.longitude as longitude) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='06' and pro.byisuse=1 and pro.isjd =0 and pro.iscenicid="
				+ iscenicid;

		List list = this.find(hsql);
		Map map = null;
		if (list != null && list.size() > 0) {
			map = (Map) list.get(0);
			if (map.get("iscenicid") != null) {
				String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
						+ iscenicid
						+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
						+ ibusinessid
						+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ ibusinessid
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='" + rzti
						+ "' " + " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ ibusinessid
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='" + ldti
						+ "' )  order by prd.sztickettypename";
				List productList = this.find(hsql2);
				if (productList != null && productList.size() >= 1) {
					for (int j = 0; j < productList.size(); j++) {

						Edmtickettypetab product = (Edmtickettypetab) productList.get(j);
						String[] names = product.getSztickettypename().split("-");
						product.setSztickettypename(names[0]);
						if (names.length > 1) {
							product.setBname(names[1]);
						}
						// �Ƶ��Ʒ��������
						Hotelproduct hotelproduct = (Hotelproduct) this.get(Hotelproduct.class,
								product.getItickettypeid());

						if (hotelproduct != null) {
							Sysparv5Id sys = new Sysparv5Id();
							sys.setPmcd(hotelproduct.getBedtype());
							sys.setPmky("BETP");
							Sysparv5 syspar = (Sysparv5) this.get(Sysparv5.class, sys);
							hotelproduct.setStrbedtype(syspar.getPmva());

							product.setHotelproduct(hotelproduct);
						}

						// /�ж�ѡ�����ס�����Ƿ��м۸�

						long num = getDayNumb(rzti, ldti);
						double totalprice = 0;
						List pricelist = new ArrayList();
						Date enddate = null;
						Edmcrowdkindpricetab price = null;
						double singleprice = 0;
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						for (int x = 0; x < num; x++) {
							if (enddate != null && enddate.after(sf.parse(Tools.getDate(rzti, x)))) {
								singleprice = price.getMactualsaleprice();
								if (hotelproduct != null) {
									switch (hotelproduct.getWeektype()) {
									case 0:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									case 1:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
											singleprice = price.getWeekendprice();
										break;
									case 2:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									case 3:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									default:
										if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
												|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
											singleprice = price.getWeekendprice();
										break;
									}
								}
								totalprice += singleprice;
								pricelist.add(singleprice);
							} else {
								String hsqlend = "select new map(pri.mactualsaleprice as mactualsaleprice,pri.weekendprice as weekendprice,pri.enddata as enddata)  from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ ibusinessid
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ ibusinessid + " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + ") <='" + rzti + "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ ibusinessid + " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid() + "))>='" + ldti + "' and pri.itickettypeid="
										+ product.getItickettypeid() + "  and pri.startdata<='" + Tools.getDate(rzti, x)
										+ "' and pri.enddata>='" + Tools.getDate(rzti, x) + "' ";
								List lst = this.find(hsqlend);
								if (lst != null && lst.size() > 0) {
									Map cmap = (Map) lst.get(0);
									price = new Edmcrowdkindpricetab();
									BeanUtils.populate(price, cmap);
									enddate = sf.parse(price.getEnddata());
									singleprice = price.getMactualsaleprice();
									if (hotelproduct != null) {
										switch (hotelproduct.getWeektype()) {
										case 0:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										case 1:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1)
												singleprice = price.getWeekendprice();
											break;
										case 2:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										case 3:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 1
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 5
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										default:
											if (Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 6
													|| Tools.getDayOfWeek(Tools.getDate(rzti, x)) == 7)
												singleprice = price.getWeekendprice();
											break;
										}
									}
									totalprice += singleprice;
									pricelist.add(singleprice);
								}
							}

						}
						product.setPriceList(pricelist);
						// TODO ԭ���߼����ȸ�Ϊȡʵ�ʵ�ֵ
						// double jprice = Math.ceil(totalprice / num);
						BigDecimal jpriceDec = new BigDecimal(totalprice / num);
						double jprice = jpriceDec.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						product.setJprice(jprice);

						String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
								+ product.getItickettypeid() + " and p.upid = u.upid order by p.isecenicpictureid";
						List piclist = this.find(sql);
						product.setList(piclist);
					}
				}
				map.put("productList", productList);
				// ��ѯ�Ƶ�����
				Hotelprovider hotelprovider = new Hotelprovider();
				hotelprovider = (Hotelprovider) this.get(Hotelprovider.class, iscenicid);
				if (hotelprovider != null) {
					Sysparv5 sysparv5 = (Sysparv5) this.get(Sysparv5.class,
							new Sysparv5Id("HOTP", hotelprovider.getZxjb()));
					hotelprovider.setStrzxjb(sysparv5.getPmva());
				}
				map.put("hotelprovider", hotelprovider);

				// ��ѯ������ͼƬ
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
						+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.find(sql);
				map.put("piclist", piclist);
			}

		} else {// ��ѯ������
			return null;
		}
		Esbscenicareatab esbscen = new Esbscenicareatab();
		BeanUtils.populate(esbscen, map);

		this.bulkUpdate("update Esbscenicareatab s set s.popupoint = s.popupoint+1 where s.iscenicid = "
				+ esbscen.getIscenicid());

		return esbscen;

	}

	public PaginationSupport getSpecialHotel(Long ibusinessid, int top, List hotelIds, int pageSize, int startInt,
			String url,Boolean isHqyatu) {
		String ssql = "select distinct new map(p.iscenicid as iscenicid) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p,Hotelproduct pd  where price.itickettypeid = product.itickettypeid and product.itickettypeid = pd.itickettypeid  and price.ibusinessid ="
				+ ibusinessid + " and price.note1 = '0000' "
				+ " and price.note3 <> '1' "
				+ " and p.iscenicid = product.iscenicid and p.scenictype = '06' and product.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
				+ ibusinessid
				+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ ibusinessid + " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid) <='"
				+ Tools.getDays() + "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid=" + ibusinessid
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=product.itickettypeid))>='"
				+ Tools.getDays() + "' ) and '" + Tools.getDays() + "' between price.startdata and price.enddata ";
		System.out.println(ssql);
		List sslist = this.find(ssql);
		StringBuffer ids = new StringBuffer();
		if (sslist != null && !sslist.isEmpty()) {
			
			for (int i = 0; i < sslist.size(); i++) {
				if(!isHqyatu){
				Map smap = (Map) sslist.get(i);
				if (hotelIds != null && hotelIds.size() > 0) { // hotelids�ѹ�ѡ��
					if (hotelIds.contains(smap.get("iscenicid"))) {
						ids.append(smap.get("iscenicid").toString() + ",");
					}
				}else{
					ids.append(smap.get("iscenicid").toString() + ",");
				}
			}else{
				Map smap = (Map) sslist.get(i);
				ids.append(smap.get("iscenicid").toString() + ",");
			}
			}
			
			String iscenicids = "";
			if (ids.length() < 2) {
				return null;
			}
			iscenicids = ids.toString().substring(0, ids.toString().length() - 1);
			String hsql = "select distinct new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.iordernumber as iordernumber,s.szaddress as szaddress,s.szphone as szphone,y.pmva as pmva) from "
					+ "Sysparv5 y,Esbscenicareatab s, Edmtickettypetab t, Hotelproduct p where s.szgrade = y.id.pmcd and y.id.pmky = 'DENJ' and  s.iscenicid = t.iscenicid and t.itickettypeid = p.itickettypeid and t.byisuse = 1 ";
			if (iscenicids != null && iscenicids.length() > 1) {
				hsql += "	and s.iscenicid in (" + iscenicids + ") ";
			}

			hsql += " and s.byisuse = 1 ";
			if (url.indexOf("hotelindex.action") == -1) {
				hsql += "and p.inoteger1 = 1 ";
			}
			hsql += "order by s.iordernumber desc";

			PaginationSupport ps = this.findPage(hsql.toString(), pageSize, startInt, url);

			List list = ps.getItems(); // this.findTopNumb(hsql, top);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map map = (Map) list.get(i);
					Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
					String sql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab p,Hotelproduct hp  where price.itickettypeid = product.itickettypeid and price.ibusinessid ="
							+ ibusinessid + " and price.note1 = '0000'  and p.iscenicid = " + iscenicid
							+ " and p.iscenicid = product.iscenicid and hp.itickettypeid = product.itickettypeid and hp.inoteger1 = 1 and '"
							+ Tools.getDays()
							+ "' between  price.startdata and  price.enddata order by price.mactualsaleprice";

					double listingprice = 0L;
					double sprice = 0L;
					List priceList = this.findTopNumb(sql, 1);
					if (priceList != null && !priceList.isEmpty()) {
						Map lowprice = (Map) priceList.get(0);
						listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
						sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
					}
					map.put("lowprice", sprice);
					map.put("height", listingprice);

					String sql2 = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.iscenicid="
							+ iscenicid + " and p.itickettypeid=0 and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.findTopNumb(sql2, 1);
					String upadder = "";
					String upfilename = "";
					if (piclist != null && !piclist.isEmpty()) {
						Map picMap = (Map) piclist.get(0);
						upadder = picMap.get("upadder").toString();
						upfilename = picMap.get("upfilename").toString();
					}
					map.put("picaddress", upadder);
					map.put("picname", upfilename);
				}
			}
			return ps;
		}
		return null;
	}

	public boolean hadOrder(String usid) {
		boolean b = false;
		String sql = " from Horderlog where usid = '" + usid + "'";
		List list = this.find(sql);
		if (null != list && list.size() > 0) {
			b = true;
		}
		return b;
	}
}
