package com.ectrip.ec.book.rentrl.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.rentrl.dao.idao.IRentrlDao;
import com.ectrip.ec.model.rentrl.Carticketrecordtab;
import com.ectrip.ec.model.rentrl.Rentrlcartickettab;
import com.ectrip.ec.model.user.Hscomment;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public class RentrlDao extends GenericDao implements IRentrlDao {

	/**
	 * ���ݷ���������ѯ����Դ���б�* Describe:
	 * 
	 * @see com.ectrip.book.Rentrl.dao.idao.IRentrlDAO#getRentrlSourceList(java.lang.String)
	 * @param pdtp
	 * @return
	 * @author hejiahua Date:2013��11��2�� 14:46:36
	 */
	@SuppressWarnings("unchecked")
	public List getRentrlSourceList(String pdtp) {
		List list = new ArrayList();
		String hsql = " from Esbscenicareatab where scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
				+ pdtp + "' or spmcd='" + pdtp + "')) and isjd=0 ";
		List sourcelist = this.find(hsql);
		StringBuffer source = null;// ��Դ��
		if (sourcelist != null && sourcelist.size() >= 1) {
			source = new StringBuffer();
			for (int i = 0; i < sourcelist.size(); i++) {
				Esbscenicareatab provider = (Esbscenicareatab) sourcelist
						.get(i);
				if (i == sourcelist.size() - 1) {
					source.append(provider.getSzregionalid());
				} else {
					source.append(provider.getSzregionalid() + ",");
				}
			}
		}
		if (source != null && !"".equals(source)) {
			// ��ѯ����Դ���б�
			String hsql2 = " from Galsourceregiontab where iregionalid in ("
					+ source + ")";
			list = this.find(hsql2);
		}
		return list;
	}

	/**
	 * * Describe:��ѯ�������̲�Ʒ(�⳵)
	 * 
	 * @see com.ectrip.book.Rentrl.dao.idao.IRentrlDAO#getRentrlProductList(java.lang.String,
	 *      java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author hejiahua Date:2013��11��2�� 14:47:05
	 */
	public PaginationSupport getRentrlProductList(String rzti, String ldti,
			String lgtp, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		ldti = Tools.getDate(ldti, -1);

		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber ) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='10' and pro.byisuse=1 and pro.isjd =0 and "
				+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
				+ rzti
				+ "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
				+ ldti + "' order by pro.iordernumber desc";
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
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
							+ rzti
							+ "' "
							+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
							+ ldti + "' ) ";
					List productList = this.find(hsql2);
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {
							Edmtickettypetab product = (Edmtickettypetab) productList
									.get(j);
							// /�ж�ѡ����⳵�����Ƿ��м۸�
							int fk = 0;
							int num = Tools.getDayNumb(rzti, ldti);
							for (int x = 0; x < num; x++) {
								String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid()
										+ ") <='"
										+ rzti
										+ "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid()
										+ "))>='"
										+ ldti
										+ "' and pri.itickettypeid="
										+ product.getItickettypeid()
										+ "  and pri.startdata<='"
										+ Tools.getDate(rzti, x)
										+ "' and pri.enddata>='"
										+ Tools.getDate(rzti, x) + "' ";
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
										+ product.getItickettypeid()
										+ ") <='"
										+ rzti
										+ "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid()
										+ "))>='"
										+ ldti
										+ "' and pri.itickettypeid="
										+ product.getItickettypeid()
										+ "  and pri.startdata<='"
										+ rzti
										+ "' and pri.enddata>='" + rzti + "' ";
								List priceList = this.find(hsql3);
								if (priceList != null && priceList.size() >= 1) {
									Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) priceList
											.get(0);
									/*if (Tools.getDayOfWeek(rzti) == 6
											|| Tools.getDayOfWeek(rzti) == 7) {
										price.setMactualsaleprice(price
												.getWeekendprice());
									}*/
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
					//������Ʒ����
					List rentrlList = this.find("from Rentrlcartickettab");
					
					map.put("rentrlList", rentrlList);
					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid)
							+ " and p.itickettypeid=0 and p.upid = u.upid ";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid="
							+ new Long(iscenicid) + " and status='01' ";
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
	 * ��ѯ��ĳ�����µķ����̲�Ʒ(�⳵) Describe:
	 * 
	 * @auth:hejiahua
	 * @param regionId��Դ��
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2013��11��2�� 14:53:21
	 */
	public PaginationSupport getRentrlProductList(Long regionId, String rzti,
			String ldti, String lgtp, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		ldti = Tools.getDate(ldti, -1);

		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress,pro.iordernumber as iordernumber ) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='10' and pro.byisuse=1 and pro.isjd =0 and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid="
				+ regionId
				+ " or g.iregionalid="
				+ regionId
				+ ") and "
				+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
				+ rzti
				+ "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
				+ ldti + "' order by pro.iordernumber desc";
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
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
							+ rzti
							+ "' "
							+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
							+ ldti + "' ) ";
					List productList = this.find(hsql2);
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {

							Edmtickettypetab product = (Edmtickettypetab) productList
									.get(j);
							// /�ж�ѡ�����ס�����Ƿ��м۸�
							int fk = 0;
							int num = Tools.getDayNumb(rzti, ldti);
							for (int x = 0; x < num; x++) {
								String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid()
										+ ") <='"
										+ rzti
										+ "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid()
										+ "))>='"
										+ ldti
										+ "' and pri.itickettypeid="
										+ product.getItickettypeid()
										+ "  and pri.startdata<='"
										+ Tools.getDate(rzti, x)
										+ "' and pri.enddata>='"
										+ Tools.getDate(rzti, x) + "' ";
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
										+ product.getItickettypeid()
										+ ") <='"
										+ rzti
										+ "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ product.getItickettypeid()
										+ "))>='"
										+ ldti
										+ "' and pri.itickettypeid="
										+ product.getItickettypeid()
										+ "  and pri.startdata<='"
										+ rzti
										+ "' and pri.enddata>='" + rzti + "' ";
								List priceList = this.find(hsql3);
								if (priceList != null && priceList.size() >= 1) {
									Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) priceList
											.get(0);
									if (Tools.getDayOfWeek(rzti) == 6
											|| Tools.getDayOfWeek(rzti) == 7) {
										price.setMactualsaleprice(price
												.getWeekendprice());
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
					
					//������Ʒ����
					List rentrlList = this.find("from Rentrlcartickettab");
					
					map.put("rentrlList", rentrlList);
					
					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid)
							+ " and p.itickettypeid=0 and p.upid = u.upid ";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid="
							+ new Long(iscenicid) + " and status='01' ";
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
	 * ��ѯ���� Describe:
	 * 
	 * @auth:hejiahua
	 * @param provider
	 * @param price
	 * @param product
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return return:PaginationSupport Date:2013��11��2�� 15:01:44
	 */
	public PaginationSupport getRentrlProductSearchList(
			Esbscenicareatab provider, String rzti, String ldti, String lgtp,
			int page, int pageSize, String url) {
		PaginationSupport ps = null;
		ldti = Tools.getDate(ldti, -1);
		StringBuffer hsql = new StringBuffer();
		// �鿴������
		hsql
				.append(" select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress ) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='10' and pro.byisuse=1 and pro.isjd =0 and pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid="
						+ provider.getSzregionalid()
						+ " or g.iregionalid="
						+ provider.getSzregionalid()
						+ ") and "
						+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ "  and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
						+ rzti
						+ "' "
						+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
						+ ldti + "' ");
		// ����������
		if (provider.getSzscenicname() != null
				&& !"".equals(provider.getSzscenicname())) {
			hsql.append(" and pro.szscenicname like '%"
					+ provider.getSzscenicname() + "%' ");
		}
		/*// �����̵ȼ�
		if (provider.getSzgrade() != null && !"".equals(provider.getSzgrade())) {
			if (!provider.getSzgrade().equals("100")) {
				hsql
						.append(" and pro.szgrade='" + provider.getSzgrade()
								+ "' ");
			}
		}*/
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
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
							+ rzti
							+ "' "
							+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
							+ ldti + "' ) ";

					List productList = this.find(hsql2);
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {

							Edmtickettypetab products = (Edmtickettypetab) productList
									.get(j);
							// /�ж�ѡ����⳵�����Ƿ��м۸�
							int fk = 0;
							int num = Tools.getDayNumb(rzti, ldti);
							for (int x = 0; x < num; x++) {
								String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid()
										+ ") <='"
										+ rzti
										+ "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid()
										+ "))>='"
										+ ldti
										+ "' and pri.itickettypeid="
										+ products.getItickettypeid()
										+ "  and pri.startdata<='"
										+ Tools.getDate(rzti, x)
										+ "' and pri.enddata>='"
										+ Tools.getDate(rzti, x) + "' ";
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
										+ products.getItickettypeid()
										+ ") <='"
										+ rzti
										+ "' "
										+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
										+ Long.parseLong(lgtp)
										+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
										+ products.getItickettypeid()
										+ "))>='"
										+ ldti
										+ "' and pri.itickettypeid="
										+ products.getItickettypeid()
										+ "  and pri.startdata<='"
										+ rzti
										+ "' and pri.enddata>='" + rzti + "' ";

								List priceList = this.find(hsql3);
								if (priceList != null && priceList.size() >= 1) {
									Edmcrowdkindpricetab prices = (Edmcrowdkindpricetab) priceList
											.get(0);
									if (Tools.getDayOfWeek(rzti) == 6
											|| Tools.getDayOfWeek(rzti) == 7) {
										prices.setMactualsaleprice(prices
												.getWeekendprice());
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
					
					//������Ʒ����
					List rentrlList = this.find("from Rentrlcartickettab");
					
					map.put("rentrlList", rentrlList);
					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid)
							+ " and p.itickettypeid=0 and p.upid = u.upid ";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid="
							+ new Long(iscenicid) + " and status='01' ";
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
					//��ť״̬��ȡ��ʽ���жϳ�����Ʒ���Ƿ��п���״̬

				}
			}
		}

		return ps;

	}

	/**
	 * 
	 * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * 
	 * @auth:hejiahua
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return return:Esbscenicareatab Date:2013��11��2�� 15:07:31
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public Esbscenicareatab getRentrlTicketduct(Long iscenicid, String rzti,
			String ldti, String lgtp) throws Exception {
		ldti = Tools.getDate(ldti, -1);
		// �鿴������
		String hsql = " select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szgrade as szgrade,pro.scenictype as scenictype,pro.szregionalid as szregionalid,pro.szsimpleintroduction as szsimpleintroduction,pro.szphone as szphone,pro.szbookdescription as szbookdescription,pro.szlocation as szlocation,pro.szaddress as szaddress ) from  Esbscenicareatab pro,Edmtickettypetab prd where pro.iscenicid = prd.iscenicid and pro.byisuse=1 and prd.byisuse=1 and pro.scenictype='10' and pro.byisuse=1 and pro.isjd =0 and pro.iscenicid="
				+ iscenicid
				+ " and "
				+ " (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
				+ rzti
				+ "' "
				+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
				+ Long.parseLong(lgtp)
				+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
				+ ldti + "' ";

		List list = this.find(hsql);
		Map map = null;
		if (list != null && list.size() >= 1) {
			String pdno = "";

			map = (Map) list.get(0);
			if (map.get("iscenicid") != null) {// �����̱��
				// ���Ҳ�Ʒ
				String hsql2 = " from Edmtickettypetab prd where prd.bycategorytype!='120' and prd.byisuse=1 and prd.iscenicid="
						+ iscenicid
						+ " and prd.itickettypeid in (select distinct pri.itickettypeid from Edmcrowdkindpricetab pri where pri.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid) <='"
						+ rzti
						+ "' "
						+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
						+ Long.parseLong(lgtp)
						+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid=prd.itickettypeid))>='"
						+ ldti + "' ) ";
				List productList = this.find(hsql2);
				if (productList != null && productList.size() >= 1) {
					for (int j = 0; j < productList.size(); j++) {

						Edmtickettypetab product = (Edmtickettypetab) productList
								.get(j);

						// /�ж�ѡ�����ס�����Ƿ��м۸�
						int fk = 0;
						int num = Tools.getDayNumb(rzti, ldti);
						for (int x = 0; x < num; x++) {
							String hsqlend = " from Edmcrowdkindpricetab pri where pri.ibusinessid="
									+ Long.parseLong(lgtp)
									+ " and pri.byisuse=1 and pri.isnet=1 and (select min(edm.startdata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
									+ Long.parseLong(lgtp)
									+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
									+ product.getItickettypeid()
									+ ") <='"
									+ rzti
									+ "' "
									+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
									+ Long.parseLong(lgtp)
									+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
									+ product.getItickettypeid()
									+ "))>='"
									+ ldti
									+ "' and pri.itickettypeid="
									+ product.getItickettypeid()
									+ "  and pri.startdata<='"
									+ Tools.getDate(rzti, x)
									+ "' and pri.enddata>='"
									+ Tools.getDate(rzti, x) + "' ";
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
									+ product.getItickettypeid()
									+ ") <='"
									+ rzti
									+ "' "
									+ " and ((select max(edm.enddata) from Edmcrowdkindpricetab edm where edm.ibusinessid="
									+ Long.parseLong(lgtp)
									+ " and edm.byisuse=1 and edm.isnet=1 and edm.itickettypeid="
									+ product.getItickettypeid()
									+ "))>='"
									+ ldti
									+ "' and pri.itickettypeid="
									+ product.getItickettypeid()
									+ " and pri.startdata<='"
									+ rzti
									+ "' and pri.enddata>='" + rzti + "' ";
							List priceList = this.find(hsql3);
							if (priceList != null && priceList.size() >= 1) {
								Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) priceList
										.get(0);
								if (Tools.getDayOfWeek(rzti) == 6
										|| Tools.getDayOfWeek(rzti) == 7) {
									price.setMactualsaleprice(price
											.getWeekendprice());
								}
								product.setPrice(price);
							}

							// ͼ��
							// String
							// hsqls="select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="+product.getItickettypeid();
							// List piclist = this.find(hsqls);
							// product.setList(piclist);
						}

					}

				}
				map.put("productList", productList);
				// ��ѯ������ͼƬ
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
						+ iscenicid
						+ " and p.itickettypeid=0 and p.upid = u.upid ";
				List piclist = this.find(sql);
				map.put("piclist", piclist);

				// ����˷������µ����в�ƷͼƬ
				String hql = " select new map( u.upadder as upadder,u.upfilename as upfilename,edm.sztickettypename as sztickettypename) from Upfile u,Secenicpicture p,Edmtickettypetab edm where p.iscenicid="
						+ iscenicid
						+ " and p.upid = u.upid and p.itickettypeid=edm.itickettypeid ";
				List ticlist = this.find(hql);
				map.put("ticlist", ticlist);

				// ��ѯ����
				String sql2 = " from Hscomment where oeid=" + iscenicid
						+ " and status='01' ";
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

				map.put("commentList", commentList);// �����б�
				map.put("sumremarknum", marknum);

			}

		}
		Esbscenicareatab esbscen = new Esbscenicareatab();
		BeanUtils.populate(esbscen, map);

		return esbscen;

	}

	/**
	 * * Describe:�⳵�����еĳ����Ƽ�
	 * 
	 * @see com.ectrip.book.Rentrl.dao.idao.IRentrlDAO#shopAllticketcenter(java.lang.Long,
	 *      java.lang.String, int, int, java.lang.String)
	 * @param szregionalid
	 *            ��Դ��
	 * @param lgtp
	 *            ��¼�˵�ע����� ɢ�� ������
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * @author hejiahua Date:2013��11��2�� 15:08:12
	 */
	public PaginationSupport shopAllticketcenter(Long szregionalid,
			String lgtp, int page, int pageSize, String url) {
		PaginationSupport ps = null;
		String sql = "select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,pro.szaddress as szaddress,pro.szgrade as szgrade) from Esbscenicareatab pro,Edmtickettypetab prd,Edmcrowdkindpricetab prc where pro.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid="
				+ szregionalid
				+ " or g.iregionalid="
				+ szregionalid
				+ ") and prc.startdata<='"
				+ Tools.getDays()
				+ "' and prc.enddata>='"
				+ Tools.getDays()
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
					String Rentrlsql = "select distinct new map(prc.mactualsaleprice as mactualsaleprice,prc.weekendprice as weekendprice,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename ) from Edmtickettypetab prd,Edmcrowdkindpricetab prc where prc.startdata<='"
							+ Tools.getDays()
							+ "' and prc.enddata>='"
							+ Tools.getDays()
							+ "' and prd.bycategorytype not in('120','119') and prc.ibusinessid="
							+ Long.parseLong(lgtp)
							+ " and prc.icrowdkindid = 1 and prd.byisuse = 1 and prc.byisuse = 1  and prc.isnet = 1 and prd.iscenicid="
							+ Long.parseLong(iscenicid)
							+ " and prd.itickettypeid = prc.itickettypeid  ";
					List RentrlList = this.find(Rentrlsql);
					map.put("Rentrllist", RentrlList);
				}
			}
		}
		return ps;
	}

	public boolean checkRentrl(Long tickettypeid) {
		String sql = "from Rentrlcartickettab r where r.itickettypeid=? and r.carloan!=r.carnum";
		List list = find(sql,new Object[]{tickettypeid});
		return list.size()>0?true:false;
	}

	public void saveCarRecord(Carticketrecordtab carticketrecordtab) {
		Long seq = getMaxPk("seq", "Carticketrecordtab")+1;
		carticketrecordtab.setSeq(seq);
		save(carticketrecordtab);
	}

	public void updateNumb(Long itickettypeid) {
		String sql = "from Rentrlcartickettab r where r.itickettypeid=?";
		List list = find(sql,new Object[]{itickettypeid});
		if (list.size()>0) {
			Rentrlcartickettab rentrlcartickettab = (Rentrlcartickettab) list.get(0);
			//���������һ
			rentrlcartickettab.setCarloan(rentrlcartickettab.getCarloan()+1);
			update(rentrlcartickettab);//����
		}else{
			throw new RuntimeException("����ʧ�ܣ�");
		}
	}

	public boolean btncheck(Long itickettypeid) {
		String sql = "from Rentrlcartickettab r where r.itickettypeid=? and status ='1' or carnum=carloan";
		List list = find(sql, new Object[]{itickettypeid});
		return list.size()>0?false:true;
	}
	/*
	 * ����(non-Javadoc)
	 * @see com.ectrip.book.rentrl.dao.idao.IRentrlDao#findAvgPrice(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public double findAvgPrice(Long itickettypeid, String startDate,
			String endDate) {
		StringBuffer hsql = new StringBuffer();
		//�õ�����
		int day = Tools.getDayNumbCha(startDate=startDate.split(" ")[0], endDate=endDate.split(" ")[0]);
		//�õ��ܽ��
		int count=0;
		for (int i = 0; i < day; i++) {
			hsql.append("from Edmcrowdkindpricetab where itickettypeid=? and startdata<=? and enddata>=?");
			List list = find(hsql.toString(),new Object[]{itickettypeid,startDate});
			if (list.size()>0 && list.size()<2) {
				Edmcrowdkindpricetab edmcrowdkindpricetab = (Edmcrowdkindpricetab) list.get(0);
				count+=edmcrowdkindpricetab.getJsprice();
			}else {
				throw new RuntimeException("�۸��������쳣��");
			}
			//���ڼ�һ������ִ��
			startDate = Tools.getDate(startDate, i);
			if (Tools.getDayNumbCha(startDate, endDate)<0) {
				break;
			}
		}
		//�õ�����
		double avgprice=count/day;
		return avgprice;
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(Tools.getDayNumbCha("2012-11-21", "2013-10-21"));
		}
	}

	public boolean findPrice(Long itickettypeid, String date) {
		String sql = "from Edmcrowdkindpricetab where itickettypeid=? and startdata<=? and enddata>=?";
		List list = find(sql,new Object[]{itickettypeid,date});
		return list.size()>0?true:false;
	}

}
