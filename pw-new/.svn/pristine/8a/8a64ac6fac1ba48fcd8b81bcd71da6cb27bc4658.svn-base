package com.ectrip.ec.book.ticket.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.beanutils.BeanUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.book.ticket.dao.idao.ITicketBookDAO;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.user.Hscomment;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.ticket.model.permitenter.Numjifenset;
import com.ectrip.ticket.model.permitenter.Numjifensetlist;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Edpofferschemetab;
import com.ectrip.ticket.model.provider.Esbprovicerq;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.QueryProviderBook;
import com.ectrip.ticket.model.provider.Ticketxgz;
import com.ectrip.ticket.model.provider.TimeSharingTicketTab;
import com.ectrip.upload.model.Upfile;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TicketBookDAO extends GenericDao implements ITicketBookDAO {

	public Map getProductPic(int itickettypeid) {
		String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
				+ itickettypeid + " order by isecenicpictureid ";
		List piclist = this.find(hsqls);
		Map upfile = null;
		if (piclist != null && piclist.size() > 0) {
			upfile = (Map) piclist.get(0);
		}
		return upfile;
	}

	/**
	 * ������������ѯ���� Describe:
	 *
	 * @auth:huangyuqi
	 * @param rzti���ѿ�ʼʱ��
	 * @param ldti���ѽ���ʱ��
	 * @return return:List Date:2012-1-4
	 */
	public List queryTicket(String rzti, String ldti, String lgtp) {
		List list = new ArrayList();
		List prolist = new ArrayList();
		StringBuffer hsql = new StringBuffer();

		hsql.append(
				" select distinct pri.iscenicid as iscenicid,pri.szscenicname as szscenicname from Esbscenicareatab pri,Edmtickettypetab pro,Edmcrowdkindpricetab prc where  ");
		hsql.append(" prc.startdata<='" + rzti + "' and prc.enddata>='" + ldti + "' ");
		hsql.append(
				" and pri.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01' or spmcd='01')) ");
		hsql.append(
				" and pri.iscenicid = pro.iscenicid and pro.itickettypeid = prc.itickettypeid and pri.byisuse=1 and pro.byisuse=1 and pri.isjd=0 and prc.isnet=1 and prc.byisuse=1 ");
		if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
			hsql.append(" and prc.ibusinessid=2 ");
		} else {
			hsql.append(" and prc.ibusinessid=1 ");
		}
		hsql.append(" order by pri.iscenicid ");
		list = this.find(hsql.toString());

		Esbscenicareatab provider = null;
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				provider = new Esbscenicareatab();
				Object[] obj = (Object[]) list.get(i);
				provider.setIscenicid(Long.parseLong(obj[0].toString()));
				provider.setSzscenicname(obj[1].toString());

				// ��ѯ������ͼƬ
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid= "
						+ Long.parseLong(obj[0].toString()) + " and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.find(sql);
				provider.setPiclist(piclist);

				prolist.add(provider);
			}
		}

		return prolist;
	}

	/**
	 * ����������ѯ���������̣��з�ҳ�� Describe:
	 *
	 * @auth:huangyuqi
	 * @param rzti��ʼ����
	 * @param ldti��������
	 * @param providerbook
	 *            �����̲�ѯ��
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url���ʵ�ַ
	 * @return return:PaginationSupport Date:2012-1-5
	 */
	public PaginationSupport queryTicketProviderSearch(String rzti, String ldti, QueryProviderBook providerbook,
			int page, int pageSize, String url) {
		PaginationSupport ps = null;
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				" select distinct new map(pri.iscenicid as iscenicid,pri.szscenicname as szscenicname,pri.szgrade as szgrade, pri.szsimpleintroduction as szsimpleintroduction,pri.longitude as longitude,pri.topics as topics ) from Esbscenicareatab pri,Edmtickettypetab pro,Edmcrowdkindpricetab prc where ");
		hsql.append(" prc.startdata<='" + rzti + "' and prc.enddata>='" + ldti + "' ");
		hsql.append(
				" and pri.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='01' or spmcd='01')) and prc.ibusinessid=1 and pri.isjd=0 ");
		if (providerbook != null) {
			// ����
			if (providerbook.getSzregionalid() != null && !"".equals(providerbook.getSzregionalid())
					&& 0L != providerbook.getSzregionalid()) {
				hsql.append(" and pri.szregionalid=" + providerbook.getSzregionalid() + " ");
			}
			// ��Ʒ���ƣ�ģ��������
			if (providerbook.getSztickettypename() != null && !"".equals(providerbook.getSztickettypename())) {
				hsql.append(" and pro.sztickettypename like '%" + providerbook.getSztickettypename() + "%' ");
			}
			// ��ʼ�۸�
			if (providerbook.getBprice() != null && !"".equals(providerbook.getBprice())) {
				hsql.append(" and prc.mactualsaleprice>=" + providerbook.getBprice() + " ");
			}
			// �����۸�
			if (providerbook.getEprice() != null && !"".equals(providerbook.getEprice())) {
				hsql.append(" and prc.mactualsaleprice<=" + providerbook.getEprice() + " ");
			}
			// ������
			if (providerbook.getTopics() != null && !"".equals(providerbook.getTopics())) {
				hsql.append(" and pri.topics like '%" + providerbook.getTopics() + "%' ");
			}
			// ��Ʒ����
			if (providerbook.getSztickettypename() != null && !"".equals(providerbook.getSztickettypename())) {
				hsql.append(" and pro.sztickettypename like '%" + providerbook.getSztickettypename() + "%' ");
			}
		}
		hsql.append(
				" and pri.byisuse=1 and pro.byisuse=1 and pri.isjd=0 and prc.isnet=1 and prc.byisuse=1 and pri.iscenicid = pro.iscenicid and pro.itickettypeid = prc.itickettypeid ");

		ps = this.findPage(hsql.toString(), pageSize, page, url);
		List list = ps.getItems();
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ���Ҳ�Ʒ
					StringBuffer hsql2 = new StringBuffer();
					hsql2.append(
							"select prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prc.listingprice as listingprice,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid,prd.isequence as isequence from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Edmcrowdkindpricetab edm where prd.iscenicid= "
									+ iscenicid + " and prc.startdata<='" + Tools.getDays() + "' and prc.enddata>='"
									+ Tools.getDate(Tools.getDays(), 1)
									+ "' and prd.bycategorytype not in ( '0013','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid and prd.itickettypeid = edm.itickettypeid ");
					if (providerbook != null) {
						if (providerbook.getLgtp() != null && !"".equals(providerbook.getLgtp())
								&& "02".equals(providerbook.getLgtp())) {
							hsql2.append(" and prc.ibusinessid=2 ");
						} else {
							hsql2.append(" and prc.ibusinessid=1 ");
						}
					} else {
						hsql2.append(" and prc.ibusinessid=1 ");
					}
					hsql2.append(
							" group by prd.itickettypeid,prd.sztickettypename,prc.listingprice,prc.mactualsaleprice,prc.icrowdkindpriceid,prd.isequence ");
					hsql2.append(" order by prd.isequence desc");
					List productList = this.find(hsql2.toString());
					List productAllList = new ArrayList();
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {
							Edmtickettypetab product = new Edmtickettypetab();
							Object[] obj = (Object[]) productList.get(j);
							product.setItickettypeid(Long.parseLong(obj[0].toString()));
							product.setSztickettypename(obj[1].toString());
							Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.get(Edmcrowdkindpricetab.class,
									new Long(obj[4].toString()));
							// ���º��޸� 2012-09-03 �����Ⱥ��������
							Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) this.get(Edpcrowdkindtab.class,
									price.getIcrowdkindid());
							price.setStrcrowdkind(crowdkind.getSzcrowdkindname());
							price.setCrowdkindmemo(crowdkind.getSzmemo());
							product.setPrice(price);
							// ��Ʒͼ��
							String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
									+ product.getItickettypeid() + " order by pic.isecenicpictureid";
							List piclist = this.find(hsqls);
							product.setList(piclist);

							productAllList.add(product);
						}

					}
					map.put("productList", productAllList);

					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid) + " and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.find(sql);
					map.put("piclist", piclist);

					// ��ѯ����
					String sql2 = " from Hscomment where oeid=" + new Long(iscenicid)
							+ " and status='01' and ptype='01'  ";
					List commentList = this.find(sql2);
					double remarknum = 0;
					double marknum = 0;
					StringBuffer effects = null;
					if (commentList != null && commentList.size() >= 1) {
						effects = new StringBuffer();
						for (int j = 0; j < commentList.size(); j++) {
							Hscomment comment = (Hscomment) commentList.get(j);
							remarknum = remarknum + comment.getRemarknum();
							if (comment.getEffect() != null && !"".equals(comment.getEffect())) {
								effects.append(comment.getEffect() + "��");
							}
						}
						marknum = remarknum / commentList.size();
					}
					if (effects != null) {
						String[] eff = effects.toString().split("��");
						Map comlist = new HashMap();
						for (int j = 0; j < eff.length; j++) {
							String htype = eff[j];
							Sysparv5Id sysid = new Sysparv5Id();
							sysid.setPmcd(htype);
							sysid.setPmky("COMY");
							Sysparv5 sys = (Sysparv5) this.get(Sysparv5.class, sysid);
							if (sys != null) {
								comlist.put(sys.getPmva(), sys.getPmva());
							}
						}
						map.put("commenteffect", comlist.keySet());
					}
					map.put("commentsize", commentList.size());
					map.put("sumremarknum", marknum);

				}
			}
		}
		return ps;
	}

	/**
	 * �õ��������������� Describe:
	 *
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������
	 *            ��0��,1�ǣ�
	 * @return return:List Date:2012-1-6
	 */
	public List queryProviderTopics(String pdtp, int isson) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select pri.topics as topics from Esbscenicareatab pri where ");
		if (1 == isson) {
			hsql.append(
					" pri.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
							+ pdtp + "' or spmcd='" + pdtp + "'))");
		} else {
			hsql.append(" pri.scenictype = '" + pdtp + "' ");
		}
		list = this.find(hsql.toString());
		List topiclist = new ArrayList();
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || "".equals(list.get(i))) {
					continue;
				} else {
					if (list.get(i).toString().trim() == null || "".equals(list.get(i).toString().trim())) {
						continue;
					} else {
						String pro = list.get(i).toString();
						String[] top = pro.split(" ");
						if (top != null && top.length >= 1) {
							for (int j = 0; j < top.length; j++) {
								topiclist.add(top[j]);
							}
						}
					}
				}
			}
		}
		return topiclist;
	}

	/**
	 * ���ݷ��������õ����а� Describe:
	 *
	 * @auth:huangyuqi
	 * @param pdtp���������
	 * @param isson�Ƿ�����ӷ��������
	 *            ��0��,1�ǣ�
	 * @param pageSize��ʾ����
	 * @return return:List Date:2012-1-6
	 */
	public List queryProviderbyRemarkNum(String pdtp, String lgtp, int isson, int pageSize) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		List pailist = new ArrayList();
		hsql.append(
				" select p.iscenicid as iscenicid,p.szscenicname as szscenicname,sum(m.remarknum) as sumremarknum from Esbscenicareatab p,Hscomment m where m.ptype='01'  ");
		if (1 == isson) {
			hsql.append(
					" and p.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
							+ pdtp + "' or spmcd='" + pdtp + "')) ");
		} else {
			hsql.append(" and p.scenictype = '" + pdtp + "' ");
		}
		hsql.append(" and p.byisuse=1  ");// ��Ч������
		hsql.append("  and p.iscenicid = m.oeid  ");
		hsql.append(
				" and m.ptype='01' and m.status='01' group by p.iscenicid,p.szscenicname order by sum(remarknum) desc ");
		list = this.findTopNumb(hsql.toString(), pageSize);
		Esbscenicareatab provider = null;
		if (list != null && list.size() >= 1) {
			for (int i = 0; i < list.size(); i++) {
				provider = new Esbscenicareatab();
				Object[] obj = (Object[]) list.get(i);
				provider.setIscenicid(Long.parseLong(obj[0].toString()));
				provider.setSzscenicname(obj[1].toString());

				Double sumnums = Double.parseDouble(obj[2].toString());// �ܷ�
				// ��ѯ����
				String sql2 = " from Hscomment where oeid=" + Long.parseLong(obj[0].toString())
						+ " and status='01' and ptype='01'  ";
				List commentList = this.find(sql2);

				Double nums = 0d;
				if (commentList != null && commentList.size() >= 1) {
					nums = sumnums / commentList.size();
				}

				provider.setSumremarknum(nums);

				String lgtpsql = "";
				if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
					lgtpsql = " ibusinessid=2 ";
				} else {
					lgtpsql = " ibusinessid=1 ";
				}
				// ���Ҳ�Ʒ
				String hsql2 = " from Edmtickettypetab prd where prd.iscenicid=" + Long.parseLong(obj[0].toString())
						+ " and prd.bycategorytype not in ( '0013','120','119') and prd.itickettypeid in (select itickettypeid from Edmcrowdkindpricetab  where "
						+ lgtpsql + " ) ";
				List productList = this.findTopNumb(hsql2, 1);

				if (productList != null && productList.size() >= 1) {
					for (int j = 0; j < productList.size(); j++) {
						Edmtickettypetab product = (Edmtickettypetab) productList.get(j);
						// ���Ҽ۸�
						String hsql3 = " from Edmcrowdkindpricetab  where " + lgtpsql + " and itickettypeid="
								+ product.getItickettypeid() + " and startdata<='" + Tools.getDays()
								+ "' and enddata>='" + Tools.getDays() + "' ";
						List priceList = this.find(hsql3);
						if (priceList != null && priceList.size() >= 1) {
							Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) priceList.get(0);
							provider.setPrice(price.getMactualsaleprice());// ���ϼ۸�
						}
					}

				}

				pailist.add(provider);
			}
		}

		return pailist;
	}

	/**
	 * ���ݷ����̱�ŵõ����������� Describe:
	 *
	 * @auth:huangyuqi
	 * @param providerId
	 * @return return:Esbscenicareatab Date:2012-1-31
	 */
	public Esbscenicareatab queryProviderDetail(Long providerId, String lgtp) {
		Esbscenicareatab provider = new Esbscenicareatab();
		provider = (Esbscenicareatab) this.get(Esbscenicareatab.class, providerId);
		if (provider != null) {

			if (provider.getSznote() != null || "".equals(provider.getSznote())) {
				String strnote;
				try {
					strnote = Tools.getStrByBlob(provider.getSznote());
					provider.setNote(strnote);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// �����̱��
			Long iscenicid = provider.getIscenicid();

			// ���Ҳ�Ʒ
			StringBuffer hsql2 = new StringBuffer();
			hsql2.append(
					"select distinct prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prc.listingprice as listingprice,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Edmcrowdkindpricetab edm where prd.iscenicid="
							+ iscenicid + " and prc.startdata<='" + Tools.getDays() + "' and prc.enddata>='"
							+ Tools.getDate(Tools.getDays(), 1)
							+ "' and prd.bycategorytype not in ( '0013','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid and prd.itickettypeid = edm.itickettypeid");
			if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
				hsql2.append(" and prc.ibusinessid=2 ");
			} else {
				hsql2.append(" and prc.ibusinessid=1 ");
			}
			List productList = this.find(hsql2.toString());
			List productAllList = new ArrayList();
			if (productList != null && productList.size() >= 1) {
				for (int j = 0; j < productList.size(); j++) {
					Edmtickettypetab product = new Edmtickettypetab();
					Object[] obj = (Object[]) productList.get(j);
					product.setItickettypeid(Long.parseLong(obj[0].toString()));
					product.setSztickettypename(obj[1].toString());
					Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.get(Edmcrowdkindpricetab.class,
							new Long(obj[4].toString()));
					product.setPrice(price);

					// // ���Ҽ۸�
					// String hsql3 =
					// " from Edmcrowdkindpricetab where itickettypeid="
					// + product.getItickettypeid();
					// List priceList = this.find(hsql3);
					// if (priceList != null && priceList.size() >= 1) {
					// Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)
					// priceList
					// .get(0);
					// product.setPrice(price);
					// }

					// ��Ʒͼ��
					String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
							+ product.getItickettypeid() + " order by pic.isecenicpictureid";
					List piclist = this.find(hsqls);
					product.setList(piclist);

					productAllList.add(product);
				}
			}

			provider.setProductList(productAllList);// �����Ʒ��۸��б�

			// ��ѯ������ͼƬ
			String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname ) from Upfile u,Secenicpicture p where p.iscenicid="
					+ new Long(iscenicid) + " and p.upid = u.upid order by p.isecenicpictureid";
			List piclist = this.find(sql);
			provider.setPiclist(piclist);

			// ��ѯ����
			String sql2 = " from Hscomment where oeid=" + new Long(iscenicid) + " and status='01' and ptype='01' ";
			List commentList = this.find(sql2);
			double remarknum = 0;
			double marknum = 0;
			StringBuffer effects = null;
			if (commentList != null && commentList.size() >= 1) {
				effects = new StringBuffer();
				for (int j = 0; j < commentList.size(); j++) {
					Hscomment comment = (Hscomment) commentList.get(j);
					remarknum = remarknum + comment.getRemarknum();
					if (comment.getEffect() != null && !"".equals(comment.getEffect())) {
						effects.append(comment.getEffect() + "��");
					}
				}
				marknum = remarknum / commentList.size();
			}
			if (effects != null) {
				String[] eff = effects.toString().split("��");

				Map comlist = new HashMap();
				for (int j = 0; j < eff.length; j++) {
					String htype = eff[j];
					Sysparv5Id sysid = new Sysparv5Id();
					sysid.setPmcd(htype);
					sysid.setPmky("COMY");
					Sysparv5 sys = (Sysparv5) this.get(Sysparv5.class, sysid);
					if (sys != null) {
						comlist.put(sys.getPmva(), sys.getPmva());
					}
				}
				String[] streff = new String[comlist.size()];

				for (Object o : comlist.keySet()) {
					streff[0] = o.toString();
				}

				// ӡ��
				provider.setCommenteffect(streff);
			}
			provider.setCommentList(commentList);// �����б�
			provider.setSumremarknum(marknum);// �ۺ�������

			String busi = "";
			if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
				busi = " and prc.ibusinessid=2 ";
			} else {
				busi = " and prc.ibusinessid=1 ";
			}
			// �õ��ܱ߾Ƶ�
			String hotelsql = "select distinct new map(pro.szscenicname as szscenicname,pro.szaddress as szaddress,prc.mactualsaleprice as mactualsaleprice,prc.weekendprice as weekendprice,pro.szgrade as szgrade,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename ) from Esbscenicareatab pro,Edmtickettypetab prd,Edmcrowdkindpricetab prc where pro.szregionalid="
					+ provider.getSzregionalid() + " and prc.startdata<='" + Tools.getDays() + "' and prc.enddata>='"
					+ Tools.getDays() + "'  and pro.scenictype='06' and prd.bycategorytype not in ('120','119') " + busi
					+ " and prc.icrowdkindid = 1 and pro.byisuse = 1 and prd.byisuse = 1 and prc.byisuse = 1  and prc.isnet = 1 and pro.iscenicid = prd.iscenicid and prd.itickettypeid = prc.itickettypeid  ";
			List hotellist = this.findTopNumb(hotelsql, 8);
			if (hotellist != null && hotellist.size() >= 1) {
				Map hotelmap = null;
				for (int i = 0; i < hotellist.size(); i++) {
					hotelmap = (Map) hotellist.get(i);
					if (Tools.getDayOfWeek(Tools.getDays()) == 6 || Tools.getDayOfWeek(Tools.getDays()) == 7) {
						hotelmap.put("proprice", hotelmap.get("mactualsaleprice"));
					} else {
						hotelmap.put("proprice", hotelmap.get("weekendprice"));
					}
				}
			}
			provider.setHotellist(hotellist);

		}

		return provider;
	}

	/**
	 * �õ��ܱ߷����� Describe:
	 *
	 * @auth:huangyuqi
	 * @param providerbook
	 *            Ҫ�õ��ķ��������
	 * @param isson
	 *            �Ƿ������ǰ�������µ��ӷ��������
	 * @return return:List Date:2012-1-31
	 */
	public List queryProviderZhouBianList(QueryProviderBook providerbook, int isson) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				" select distinct new map(pri.iscenicid as iscenicid,pri.szscenicname as szscenicname,pri.szgrade as szgrade, pri.szsimpleintroduction as szsimpleintroduction,pri.longitude as longitude,pri.topics as topics ) from Esbscenicareatab pri,Edmtickettypetab pro,Edmcrowdkindpricetab prc where ");
		hsql.append(" prc.startdata<='" + Tools.getDays() + "' and prc.enddata>='" + Tools.getDate(Tools.getDays(), 1)
				+ "' ");
		if (providerbook != null) {
			// ����
			if (providerbook.getSzregionalid() != null && !"".equals(providerbook.getSzregionalid())
					&& 0L != providerbook.getSzregionalid()) {
				hsql.append(" and pri.szregionalid=" + providerbook.getSzregionalid() + " ");
			}
			if (1 == isson) {
				hsql.append(
						" and pri.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
								+ providerbook.getScenictype() + "' or spmcd='" + providerbook.getScenictype() + "'))");
			} else {
				hsql.append(" and pri.scenictype = '" + providerbook.getScenictype() + "' ");
			}
		}
		hsql.append(" and pri.iscenicid <> " + providerbook.getIscenicid());
		hsql.append(
				" and pri.byisuse=1 and pro.byisuse=1 and pri.isjd=0 and prc.isnet=1 and prc.byisuse=1 and pri.iscenicid = pro.iscenicid and pro.itickettypeid = prc.itickettypeid ");
		hsql.append(" order by pri.iscenicid ");

		list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ���Ҳ�Ʒ
					StringBuffer hsql2 = new StringBuffer();
					// hsql2.append("select distinct prd.itickettypeid as
					// itickettypeid,prd.sztickettypename as sztickettypename
					// from Edmtickettypetab prd,Edmcrowdkindpricetab prc where
					// prd.iscenicid="
					// + Long.parseLong(iscenicid)
					// + " and prc.startdata<='"
					// + Tools.getDays()
					// + "' and prc.enddata>='"
					// + Tools.getDate(Tools.getDays(), 1)
					// +
					// "' and prd.bycategorytype not in( '0013','120','119') and
					// prc.icrowdkindid=1 and prd.itickettypeid =
					// prc.itickettypeid ");
					//
					// if(providerbook!=null){
					// if(providerbook.getLgtp()!=null &&
					// !"".equals(providerbook.getLgtp()) &&
					// "02".equals(providerbook.getLgtp())){
					// hsql2.append(" and prc.ibusinessid=2 ");
					// }else{
					// hsql2.append(" and prc.ibusinessid=1 ");
					// }
					// }else{
					// hsql2.append(" and prc.ibusinessid=1 ");
					// }
					// List productList = this.find(hsql2.toString());
					// List productAllList = new ArrayList();
					// if (productList != null && productList.size() >= 1) {
					// for (int j = 0; j < productList.size(); j++) {
					// Edmtickettypetab product = new Edmtickettypetab();
					// Object[] obj = (Object[]) productList.get(j);
					// product.setItickettypeid(Long.parseLong(obj[0]
					// .toString()));
					// product.setSztickettypename(obj[1].toString());
					//
					// // ���Ҽ۸�
					// String hsql3 =
					// " from Edmcrowdkindpricetab where itickettypeid="
					// + product.getItickettypeid();
					// List priceList = this.find(hsql3);
					// if (priceList != null && priceList.size() >= 1) {
					// Edmcrowdkindpricetab price = (Edmcrowdkindpricetab)
					// priceList
					// .get(0);
					// product.setPrice(price);
					// }

					hsql2.append(
							"select distinct prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prc.listingprice as listingprice,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid from Edmtickettypetab prd,Edmcrowdkindpricetab prc where prd.iscenicid="
									+ iscenicid + " and prc.startdata<='" + Tools.getDays() + "' and prc.enddata>='"
									+ Tools.getDate(Tools.getDays(), 1)
									+ "' and prd.bycategorytype not in ( '0013','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid ");
					if (providerbook != null) {
						if (providerbook.getLgtp() != null && !"".equals(providerbook.getLgtp())
								&& "02".equals(providerbook.getLgtp())) {
							hsql2.append(" and prc.ibusinessid=2 ");
						} else {
							hsql2.append(" and prc.ibusinessid=1 ");
						}
					} else {
						hsql2.append(" and prc.ibusinessid=1 ");
					}
					List productList = this.find(hsql2.toString());
					List productAllList = new ArrayList();
					if (productList != null && productList.size() >= 1) {
						for (int j = 0; j < productList.size(); j++) {
							Edmtickettypetab product = new Edmtickettypetab();
							Object[] obj = (Object[]) productList.get(j);
							product.setItickettypeid(Long.parseLong(obj[0].toString()));
							product.setSztickettypename(obj[1].toString());
							Edmcrowdkindpricetab price = (Edmcrowdkindpricetab) this.get(Edmcrowdkindpricetab.class,
									new Long(obj[4].toString()));
							product.setPrice(price);

							// // ���Ҽ۸�
							// String hsql3 =
							// " from Edmcrowdkindpricetab where itickettypeid="
							// + product.getItickettypeid();
							// List priceList = this.find(hsql3);
							// if (priceList != null && priceList.size() >= 1) {
							// Edmcrowdkindpricetab price =
							// (Edmcrowdkindpricetab) priceList
							// .get(0);
							// product.setPrice(price);
							// }

							// ��Ʒͼ��
							String hsqls = "select new map(up.upid as upid,up.upname as upname,up.upfilename as upfilename,up.upadder as upadder,pic.upid as upid,pic.isecenicpictureid as isecenicpictureid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid="
									+ product.getItickettypeid() + " order by pic.isecenicpictureid";
							List piclist = this.find(hsqls);
							product.setList(piclist);

							productAllList.add(product);
							break;
						}

					}
					map.put("productList", productAllList);

					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid) + " and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.find(sql);
					map.put("piclist", piclist);
					Upfile newPic = getProviderPic(Integer.parseInt(iscenicid));
					if (newPic != null) {
						map.put("upadder", newPic.getUpadder());
						map.put("upfilename", newPic.getUpfilename());
					} else {
						map.put("upadder", null);
						map.put("upfilename", null);
					}

					// ��ѯ����
					String sql2 = " from Hscomment where oeid=" + new Long(iscenicid)
							+ " and status='01' and ptype='01'  ";
					List commentList = this.find(sql2);
					double remarknum = 0;
					double marknum = 0;
					StringBuffer effects = null;
					if (commentList != null && commentList.size() >= 1) {
						effects = new StringBuffer();
						for (int j = 0; j < commentList.size(); j++) {
							Hscomment comment = (Hscomment) commentList.get(i);
							remarknum = remarknum + comment.getRemarknum();
							if (comment.getEffect() != null && !"".equals(comment.getEffect())) {
								effects.append(comment.getEffect() + "��");
							}
						}
						marknum = remarknum / commentList.size();
					}
					if (effects != null) {
						String[] eff = effects.toString().split("��");
						Map comlist = new HashMap();
						for (int j = 0; j < eff.length; j++) {
							String htype = eff[j];
							Sysparv5Id sysid = new Sysparv5Id();
							sysid.setPmcd(htype);
							sysid.setPmky("COMY");
							Sysparv5 sys = (Sysparv5) this.get(Sysparv5.class, sysid);
							if (sys != null) {
								comlist.put(sys.getPmva(), sys.getPmva());
							}
						}
						map.put("commenteffect", comlist.keySet());
					}

					map.put("commentsize", commentList.size());
					map.put("sumremarknum", marknum);

				}
			}
		}
		return list;
	}

	public List queryZhouBianList(QueryProviderBook providerbook, int isson) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				" select distinct new map(pri.iscenicid as iscenicid,pri.szscenicname as szscenicname,pri.szgrade as szgrade,sys.pmva as strszgrade, pri.szsimpleintroduction as szsimpleintroduction,pri.longitude as longitude,pri.topics as topics ) from Esbscenicareatab pri,Edmtickettypetab pro,Edmcrowdkindpricetab prc,Sysparv5 sys where ");
		hsql.append(" sys.id.pmky='DENJ' and sys.id.pmcd=pri.szgrade and prc.startdata<='" + Tools.getDays()
				+ "' and prc.enddata>='" + Tools.getDate(Tools.getDays(), 1) + "' ");
		if (providerbook != null) {
			// ����
			if (providerbook.getSzregionalid() != null && !"".equals(providerbook.getSzregionalid())
					&& 0L != providerbook.getSzregionalid()) {
				hsql.append(" and pri.szregionalid=" + providerbook.getSzregionalid() + " ");
			}
			if (1 == isson) {
				hsql.append(
						" and pri.scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
								+ providerbook.getScenictype() + "' or spmcd='" + providerbook.getScenictype() + "'))");
			} else {
				hsql.append(" and pri.scenictype = '" + providerbook.getScenictype() + "' ");
			}
		}
		hsql.append(" and pri.iscenicid != " + providerbook.getIscenicid());
		hsql.append(
				" and pri.byisuse=1 and pro.byisuse=1 and pri.isjd=0 and prc.isnet=1 and prc.byisuse=1 and pri.iscenicid = pro.iscenicid and pro.itickettypeid = prc.itickettypeid ");
		hsql.append(" order by pri.iscenicid ");
		list = this.find(hsql.toString());
		if (list != null && list.size() >= 1) {
			Map map = null;
			for (int i = 0; i < list.size(); i++) {
				map = (Map) list.get(i);
				if (map.get("iscenicid") != null) {// �����̱��
					String iscenicid = map.get("iscenicid").toString();
					// ��ѯ������ͼƬ
					String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
							+ new Long(iscenicid) + " and p.upid = u.upid order by p.isecenicpictureid";
					List piclist = this.find(sql);
					map.put("piclist", piclist);
					Upfile newPic = getProviderPic(Integer.parseInt(iscenicid));
					if (newPic != null) {
						map.put("upadder", newPic.getUpadder());
						map.put("upfilename", newPic.getUpfilename());
					} else {
						map.put("upadder", "");
						map.put("upfilename", "");
					}

					Map lowprice = getMinPrice(Integer.parseInt(iscenicid));
					if (lowprice != null) {
						double listingprice = Double.parseDouble(lowprice.get("listingprice").toString());// ���Ƽ�
						double sprice = Double.parseDouble(lowprice.get("mactualsaleprice").toString());// ʵ���ۼ�
						map.put("lowprice", sprice);
						map.put("discount", String.valueOf(Math.round(sprice / listingprice * 100) / 10));
						map.put("heighe", listingprice);
					}
				}
			}
		}
		return list;
	}

	/**
	 * ��ѯ������Ʊ�б���ҳʹ�ã� Describe:
	 *
	 * @auth:huangyuqi
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return return:List Date:2012-3-12
	 */
	public List queryTicketList(String rzti, String ldti, String lgtp) {
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select distinct new map( prd.itickettypeid as itickettypeid, prd.sztickettypename as sztickettypename,pri.mactualsaleprice as mactualsaleprice ) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro where pro.scenictype in ('01','02','03','04','05') and pri.startdata<='"
						+ rzti + "' and pri.enddata>='" + ldti
						+ "' and pri.byisuse=1 and prd.byisuse =1 and pri.isnet=1  and pro.byisuse = 1  and  prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid ");
		if (lgtp != null) {
			if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
				hsql.append(" and pri.ibusinessid=2 ");
			} else {
				hsql.append(" and pri.ibusinessid=1 ");
			}
		} else {
			hsql.append(" and pri.ibusinessid=1 ");
		}
		hsql.append(" order by prd.itickettypeid ");
		list = this.find(hsql.toString());
		return list;
	}

	public List getProviderByArea(String type, int top, int areaid) {
		String hql = "";
		if (type.equals("06")) {
			hql = "select new map(p.iscenicid as iscenicid,p.szscenicname as providername,s1.pmva as strgrade,s2.pmva as hoteltypename,s2.id.pmcd as hoteltypeid) from Esbscenicareatab p,Sysparv5 s1,Hotelprovider hp,Sysparv5 s2 where scenictype='"
					+ type + "' and p.szregionalid=" + areaid
					+ " and p.szgrade=s1.id.pmcd and s1.id.pmky='DENJ' and hp.iscenicid = p.iscenicid and hp.zxjb=s2.id.pmcd and s2.id.pmky='HOTP' order by iordernumber";
		} else {
			hql = "select new map(p.iscenicid as iscenicid,p.szscenicname as providername,s1.pmva as strgrade) from Esbscenicareatab p,Sysparv5 s1 where scenictype='"
					+ type + "' and p.szregionalid=" + areaid
					+ " and p.szgrade=s1.id.pmcd and s1.id.pmky='DENJ' order by iordernumber";
		}
		System.out.println(hql);
		return findTopNumb(hql, top);
	}

	public List getProductByProvider(int iscenicid, int top) {
		String hql = "select new map(p.itickettypeid as itickettypeid,p.sztickettypename as sztickettypename,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice) from Edmtickettypetab p,Edmcrowdkindpricetab price where p.itickettypeid = price.itickettypeid and price.icrowdkindid = 1 and price.ibusinessid = 1 and price.note1='0000' and '"
				+ Tools.getDays() + "' between price.startdata and  price.enddata";
		return this.findTopNumb(hql, top);
	}

	public Map getMinPrice(int iscenicid) {
		String hql = "select new map(price.mactualsaleprice as mactualsaleprice,price.listingprice  as listingprice) from Edmcrowdkindpricetab price,Edmtickettypetab product,Esbscenicareatab     p  where price.itickettypeid = product.itickettypeid and price.ibusinessid = 1 and price.note1 = '0000'  and p.iscenicid = "
				+ iscenicid + " and p.iscenicid = product.iscenicid and '" + Tools.getDays()
				+ "' between  price.startdata and  price.enddata order by price.listingprice";
		List list = find(hql);
		Map pricemap = null;
		if (list != null && list.size() > 0) {
			pricemap = (Map) list.get(0);
		}
		return pricemap;
	}

	public Upfile getProviderPic(int iscenicid) {
		String hql = "select new map(up.upid as upid) from Secenicpicture pic,Upfile up where up.upid = pic.upid and pic.itickettypeid=0 and pic.iscenicid="
				+ iscenicid + " order by pic.isecenicpictureid";
		List list = find(hql);
		Upfile upfile = null;
		if (list != null && list.size() > 0) {
			Map map = (Map) list.get(0);
			if (map.get("upid") != null) {
				upfile = (Upfile) get(Upfile.class, Long.parseLong(map.get("upid").toString()));
			}
		}
		return upfile;
	}

	public PaginationSupport searchTicketByKey(SearchKey keys, int pageSize, int startIndex, String url) {
		StringBuffer hql = new StringBuffer(
				"select new map(p.topics as topics,sys.pmva as strszgrade,ticket.sztickettypename as ticketname,ticket.itickettypeid as itickettypeid,p.iscenicid as iscenicid,p.szscenicname as providername,price.mactualsaleprice as mactualsaleprice,price.listingprice as listingprice,source.szregionalname as szregionalname,p.longitude as longitude) from Edmtickettypetab ticket,Esbscenicareatab p,Edmcrowdkindpricetab price,Sysparv5 sys,Galsourceregiontab source where source.iregionalid=p.szregionalid and p.szgrade=sys.id.pmcd and sys.id.pmky='DENJ' and  ticket.iscenicid=p.iscenicid and ticket.itickettypeid=price.itickettypeid and price.icrowdkindid = 1 and price.ibusinessid = 1 and price.note1 = '0000'");
		if (keys != null) {
			if (keys.getAutosearch() != null && !keys.getAutosearch().equals("")) {
				hql.append(" and (p.szscenicname like '%" + keys.getAutosearch() + "%' or p.topics like '%"
						+ keys.getAutosearch() + "%')");
			} else {
				if (keys.getArea() != null && !keys.getArea().equals("")) {
					hql.append(" and p.szregionalid=" + keys.getArea());
				}
				if (keys.getGrade() != null && !keys.getGrade().equals("")) {
					hql.append(" and p.szgrade=" + keys.getGrade());
				}
				if (keys.getTheme() != null && !keys.getTheme().equals("")) {
					hql.append(" and p.topics like '%" + keys.getTheme() + "%'");
				}
				if (keys.getPrice() != null && !keys.getPrice().equals("")) {
					String[] str = keys.getPrice().split("-");
					hql.append(" and price.mactualsaleprice>=" + str[0] + " and price.mactualsaleprice<=" + str[1]);
				}
				if (keys.getScenicname() != null && !keys.getScenicname().equals("")) {
					hql.append(" and p.szinnername like '%" + keys.getScenicname() + "%'");
				}
			}
		}
		if (keys.getOrderby() != null && !keys.getOrderby().equals("")) {
			if (keys.getOrderby().equals("1")) {
				hql.append(" order by price.mactualsaleprice");
			} else if (keys.getOrderby().equals("2")) {
				hql.append("order by price.mactualsaleprice desc");
			} else if (keys.getOrderby().equals("3")) {
				hql.append(" order by p.iordernumber");
			} else if (keys.getOrderby().equals("4")) {
				hql.append(" order by p.iordernumber desc");
			} else {
				hql.append(" order by p.iordernumber desc");
			}
		} else {
			hql.append(" order by p.iordernumber desc");
		}
		PaginationSupport ps = findPage(hql.toString(), pageSize, startIndex, url);
		if (ps != null && ps.getItems() != null && ps.getItems().size() > 0) {
			List list = ps.getItems();
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				double mactualsaleprice = Double.parseDouble(map.get("mactualsaleprice").toString());
				double listingprice = Double.parseDouble(map.get("listingprice").toString());
				map.put("discount", Math.round(mactualsaleprice / listingprice * 100) / 10);
				Map picture = getProductPic(Integer.parseInt(map.get("itickettypeid").toString()));
				if (picture != null) {
					map.put("upfilename", picture.get("upfilename"));
					map.put("upadder", picture.get("upadder"));
				}
			}
		}
		return ps;
	}

	/**
	 * date ��������ɴ���
	 */
	public List getTicketCrowd(Long iscenicid, String date, int ibusinessid) {
		String hql = "select new map(c.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname) from Edmcrowdkindpricetab price,Edpcrowdkindtab c,Edmtickettypetab ticket where ticket.itickettypeid=price.itickettypeid and ticket.iscenicid="
				+ iscenicid + " and price.icrowdkindid=c.icrowdkindid and price.ibusinessid=" + ibusinessid
				+ " and price.note1='0000' and '" + date
				+ "' between  price.startdata and price.enddata and price.isnet=1 group by c.icrowdkindid,c.szcrowdkindname ";
		return find(hql);
	}

	public List getTicketByCrowd(Long kindid, Long iscenicid, String date, int ibusinessid) {
		String hql = "select new map(ticket.itickettypeid as itickettypeid,ticket.sztickettypename as ticketname,price.listingprice as listingprice,price.mactualsaleprice as mactualsaleprice,ticket.szmemo as szmemo) from Edmtickettypetab ticket,Edmcrowdkindpricetab price where ticket.itickettypeid=price.itickettypeid and price.note1='0000' and ibusinessid="
				+ ibusinessid + " and ticket.iscenicid=" + iscenicid + " and price.icrowdkindid=" + kindid + " and '"
				+ date + "' between  price.startdata and price.enddata  and price.isnet=1";
		return find(hql);
	}

	/**
	 *
	 * * Describe:
	 *
	 * @see com.ectrip.book.ticket.dao.idao.ITicketBookDAO#getProviderByRegional(java.lang.String,
	 *      java.lang.Long, int)
	 * @param type
	 *            ���������
	 * @param iregionalid
	 *            ��Դ��ID
	 * @param tip
	 * @return
	 * @author chenxinhao Date:2013-12-17
	 */
	public List getProviderByRegional(String type, Long iregionalid, int tip) {
		String hsql = "";
		if (tip == 1) {
			hsql = "select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname) from Esbscenicareatab s where s.scenictype = '"
					+ type
					+ "' and s.byisuse = 1 and s.szregionalid in (select g.iregionalid from Galsourceregiontab g where g.iparentid = "
					+ iregionalid + ") order by s.iordernumber desc";
		} else {
			hsql = "select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname) from Esbscenicareatab s where s.scenictype = '"
					+ type
					+ "' and s.byisuse = 1 and s.szregionalid not in (select g.iregionalid from Galsourceregiontab g where g.iparentid = "
					+ iregionalid + ") order by s.iordernumber desc";
		}
		return this.findTopNumb(hsql, 10);
	}

	/**
	 *
	 * Describe:��ҳר��(ֻ��ѯ�˳���Ʊ)
	 *
	 * @author:chenxinhao
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param top
	 * @param tip
	 *            1�ؼ�
	 * @return return:List Date:2013-12-17
	 */
	public List queryProduct(String rzti, String ldti, String lgtp, int top, int tip) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,pri.mactualsaleprice as mactualsaleprice,prd.isequence as isequence) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro,Edmticketproduct ep");
		hsql.append(" where pro.scenictype='01' and pri.startdata <='" + rzti + "' and pri.enddata >='" + ldti
				+ "' and pri.byisuse = 1 and prd.byisuse = 1 and pro.byisuse = 1 and prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid and pri.icrowdkindid = 1 and ep.itickettypeid = prd.itickettypeid and  prd.bycategorytype not in ( '0013','0004','120','119')   ");
		if (lgtp != null && !lgtp.equals("")) {
			if (lgtp.equals("02")) {
				hsql.append(" and pri.ibusinessid = 2");
			} else {
				hsql.append(" and pri.ibusinessid = 1");
			}
		} else {
			hsql.append(" and pri.ibusinessid = 1");
		}
		if (tip == 1) {
			hsql.append(" and ep.inoteger5=1 ");
		}
		hsql.append(" order by prd.isequence desc");
		List list = null;
		System.out.println(hsql.toString());
		if (top != 0) {
			list = this.findTopNumb(hsql.toString(), top);
		} else {
			list = this.find(hsql.toString());
		}
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);

				Long prid = new Long(0);

				Object o = map.get("itickettypeid");
				if (o != null) {
					prid = (Long) map.get("itickettypeid");

				}

				System.out.println("itickettypeid=" + map.get("itickettypeid"));
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
						+ Long.parseLong(map.get("iscenicid").toString())
						// 2014��10.21 ������޸�
						+ " and p.itickettypeid=" + prid + " and  p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.findTopNumb(sql, 1);
				if (piclist != null && !piclist.isEmpty()) {
					Map picmap = (Map) piclist.get(0);
					map.put("upadder", picmap.get("upadder").toString());
					map.put("upfilename", picmap.get("upfilename").toString());
				} else {
					map.put("upadder", "");
					map.put("upfilename", "");
				}
			}
		}
		return list;
	}

	public List searchTicketByProid(Long iscenicid, String rzti, String lgtp) throws Exception {
		StringBuffer hsql2 = new StringBuffer();

		Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, iscenicid);
		Long ibusinessid = 1L;
		String szlasttime = esb.getSzlasttime();
		if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
			ibusinessid = 2L;
		}
		Esbprovicerq esbprovicerq = this.findEsbprovicerq(esb.getIscenicid(), ibusinessid);
		if (esbprovicerq != null) {
			szlasttime = esbprovicerq.getSzlasttime();
		}
		Date date = new Date();
		TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setTimeZone(tZone);
		String time = format.format(date);
		if (Tools.getDays().equals(rzti)) {
			// �ж������Ƿ񳬹���������Ԥ��
			int hour = time.compareTo(szlasttime);
			if (hour >= 0) {// ���ڵ���0��ʾ��������
				rzti = Tools.getDate(rzti, 1);
			}
		}

		hsql2.append(
				"select distinct new map(s.pmvc as pmvc,prd.szmemo  as szmemo,s.pmva as pmva,prc.icrowdkindid as icrowdkindid,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype,prd.isequence as isequence,prd.sztickettypecode as sztickettypecode) from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Sysparv5 s where prd.iscenicid="
						+ iscenicid + " and prc.startdata<='" + rzti + "' and prc.enddata>='" + rzti
						+ "' and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not in ( '0013','0004','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1 and prc.note1='0000' and prd.itickettypeid = prc.itickettypeid");
		if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
			hsql2.append(" and prc.ibusinessid=2  ");
		} else {
			hsql2.append(" and prc.ibusinessid=1 ");
		}
		hsql2.append(" order by pmvc, prd.isequence desc,prc.icrowdkindid desc");
		List productList = this.find(hsql2.toString());
		List productAll = new ArrayList();

		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				int index = 0;
				Map map = (Map) productList.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				for (int j = 0; j < productList.size(); j++) {
					Map map2 = (Map) productList.get(j);
					Long itickettypeid2 = Long.valueOf(map2.get("itickettypeid").toString());
					if (itickettypeid.equals(itickettypeid2)) {
						index++;
					}
				}

				if (index > 1) {
					// System.out.println("ȥ����");
					productList.remove(i);
				}
			}
		}

		if (productList != null && !productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				Edmtickettypetab ticket = new Edmtickettypetab();
				Map map = (Map) productList.get(i);
				ticket.setStrbycategorytype(map.get("pmva").toString());
				BeanUtils.populate(ticket, map);
				String sql = "select new map(p.szmemo as szmemo,p.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.szmemo as szmemo,p.ipeoplenumrange as isrealname) from Edmcrowdkindpricetab p, Edpcrowdkindtab c"
						+ " where p.isnet = 1 and p.note1='0000' and p.byisuse = 1 and p.icrowdkindid = c.icrowdkindid and p.startdata <='"
						+ rzti + "' and p.enddata >='" + rzti + "' and p.itickettypeid =" + ticket.getItickettypeid()
						+ " " + (lgtp.equals("02") ? " and p.ibusinessid=2 " : " and p.ibusinessid=1 ")
						+ " order by p.icrowdkindid";
				List priceList = this.find(sql);
				ticket.setPriceList(priceList);
				productAll.add(ticket);
			}
		}
		return productAll;
	}
  
    public PaginationSupport searchProduct(String scenicIds,SearchKey keys, String rzti,
                                           String lgtp, int page, int pageSize, String url) throws Exception {
        List list = null;
        PaginationSupport ps = null;
        if (keys.getScenicname() != null && !keys.getScenicname().equals("")) {
            StringBuffer hsql = new StringBuffer();
            hsql.append("from Esbscenicareatab s where s.scenictype='01' and s.byisuse = 1");
            if(scenicIds != null && !"".equals(scenicIds)) {
            	
            	if(scenicIds.contains(",")) {
            		hsql.append(" and s.iscenicid in (");
            		String[] splits = scenicIds.split(",");
            		for (int i = 0;i < splits.length;i++) {
            			if(i == (splits.length-1)) {
            				hsql.append(splits[i]+")");
            			}else {
            				hsql.append(splits[i]+",");
            			}
					}
            	}else {
            		hsql.append(" and s.iscenicid="+scenicIds);
            	}
            }
            if (keys.getScenicname() != null
                    && !keys.getScenicname().equals("")) {
                hsql.append(" and s.szscenicname like '%"
                        + keys.getScenicname() + "%' ");
            }
            if (keys.getGrade() != null && !keys.getGrade().equals("")) {
                String[] grades = keys.getGrade().replace(" ", "").split(",");
                StringBuffer grade = new StringBuffer();
                for (int i = 0; i < grades.length; i++) {
                    grade.append("'" + grades[i] + "',");
                }
                hsql.append(" and s.szgrade in ("
                        + grade.toString().substring(0,
                        grade.toString().length() - 1) + ") ");
            }
            if (keys.getArea() != null && !keys.getArea().equals("")) {
                hsql.append(" and s.szregionalid in (select g.iregionalid as iregionalid from Galsourceregiontab g where g.iparentid="
                        + Long.parseLong(keys.getArea())
                        + " or g.iregionalid="
                        + Long.parseLong(keys.getArea()) + " )");
            }
            hsql.append(" order by s.iordernumber desc ");
            // �����Ǹ��ݷ��������ƽ��в�ѯ��
            List list1 = find(hsql.toString());
            // �����Ǹ��ݷ����̵�ַ���в�ѯ��
            hsql.setLength(0);
            hsql.append("from Esbscenicareatab s where s.scenictype='01' and s.byisuse = 1 ");
            if (keys.getScenicname() != null
                    && !keys.getScenicname().equals("")) {
                hsql.append(" and s.szlocation like '%" + keys.getScenicname()
                        + "%' ");
            }
               List list2 = find(hsql.toString());
			if (list2 != null && list2.size() > 0) {
				list1.addAll(list2);

				for (int i = 0; i < list1.size(); i++) {
					int index = 0;
					Esbscenicareatab ticket = (Esbscenicareatab) list1.get(i);
					Long iscenicid = ticket.getIscenicid();
					for (int j = 0; j < list1.size(); j++) {
						Esbscenicareatab ticket2 = (Esbscenicareatab) list1.get(j);
						Long iscenicid2 = ticket2.getIscenicid();
						if (iscenicid.intValue() == iscenicid2.intValue()) {
							index++;
						}
					}

					if (index > 1) {
						list1.remove(i);
					}
				}

			}
			int start = (page - 1) * pageSize;
			int end = page * pageSize;
			if (end > list1.size()) {
				end = list1.size();
			}

			ps = new PaginationSupport(list1.subList(start, end), list1.size(), page, pageSize, url);
			list = ps.getItems();
		} else {
			StringBuffer hsql = new StringBuffer();
			hsql.append("from Esbscenicareatab s where s.scenictype='01' and s.byisuse = 1 ");

			if (keys.getGrade() != null && !keys.getGrade().equals("")) {
				String[] grades = keys.getGrade().replace(" ", "").split(",");
				StringBuffer grade = new StringBuffer();
				for (int i = 0; i < grades.length; i++) {
					grade.append("'" + grades[i] + "',");
				}
				hsql.append(
						" and s.szgrade in  (" + grade.toString().substring(0, grade.toString().length() - 1) + ") ");
			}
			if (keys.getArea() != null && !keys.getArea().equals("")) {
				hsql.append(
						" and s.szregionalid in (select g.iregionalid as iregionalid from Galsourceregiontab g where g.iparentid="
								+ Long.parseLong(keys.getArea()) + " or g.iregionalid=" + Long.parseLong(keys.getArea())
								+ " )");
			}
            if(scenicIds != null && !"".equals(scenicIds)) {
            	
            	if(scenicIds.contains(",")) {
            		hsql.append(" and s.iscenicid in (");
            		String[] splits = scenicIds.split(",");
            		for (int i = 0;i < splits.length;i++) {
            			if(i == (splits.length-1)) {
            				hsql.append(splits[i]+")");
            			}else {
            				hsql.append(splits[i]+",");
            			}
					}
            	}else {
            		hsql.append(" and s.iscenicid="+scenicIds);
            	}
            }
			hsql.append(" and s.isjd=0 order by s.iordernumber desc ");
			System.out.println(hsql.toString());
			ps = findPage(hsql.toString(), pageSize, page, url);
			list = ps.getItems();
		}

		if (list != null && !list.isEmpty()) {

			for (int i = 0; i < list.size(); i++) {
				Esbscenicareatab provider = (Esbscenicareatab) list.get(i);
				Long ibusinessid = 1L;
				if (lgtp != null && !"".equals(lgtp) && "02".equals(lgtp)) {
					ibusinessid = 2L;
				}
				Esbprovicerq esbprovicerq = this.findEsbprovicerq(provider.getIscenicid(), ibusinessid);
				if (esbprovicerq != null) {
					provider.setSzlasttime(esbprovicerq.getSzlasttime());
					provider.setImaxdata(esbprovicerq.getImaxdata().intValue());
				}
				List productList = searchTicketByProid(provider.getIscenicid(), rzti, lgtp);

				for (Object object : productList) {
					Edmtickettypetab tick = (Edmtickettypetab) object;
					String sztickettypecode = tick.getSztickettypecode();
					String timesql = "from TimeSharingTicketTab where productId='" + sztickettypecode
							+ "' and dayTime='" + rzti + "' ";
					List timelist = this.find(timesql);

					TimeSharingTicketTab timesharingtickettab = new TimeSharingTicketTab();
					timesharingtickettab.setTimes(timelist);
					System.out.println("timelist:" + timelist);
					tick.setTimesharingtickettab(timesharingtickettab);
					tick.setTimeList(timelist);

				}
				provider.setProductList(productList);

				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.itickettypeid=0 and p.iscenicid="
						+ provider.getIscenicid() + " and p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.find(sql);
				provider.setPiclist(piclist);
				//��Ӿ�����ͼ�
				StringBuffer hsql = new StringBuffer("select distinct new map(prc.icrowdkindid as icrowdkindid,prc.mactualsaleprice as mactualsaleprice,prc.icrowdkindpriceid as icrowdkindpriceid,pro.isequence as isequence,prc.listingprice as listingprice,pro.sztickettypename as sztickettypename,pro.itickettypeid as itickettypeid) "
						+ " from Edmtickettypetab pro, Edmcrowdkindpricetab prc "
						+ " where pro.itickettypeid = prc.itickettypeid and prc.startdata <= '"+ Tools.getDays()+ "' and prc.enddata >= '"+ Tools.getDays() + "' and pro.iscenicid="+ provider.getIscenicid()
						+ " and pro.bycategorytype not in ( '0013','0004','120','119')  and  pro.byisuse=1 and prc.isnet=1 and prc.byisuse=1 and prc.note1='0000' and prc.note3 !='1'");
		
				hsql.append(" group by prc.icrowdkindid,pro.itickettypeid,pro.sztickettypename,prc.listingprice,prc.mactualsaleprice,prc.icrowdkindpriceid,pro.isequence");
				hsql.append(" order by prc.mactualsaleprice asc");//pro.isequence desc,
		             
				List list2 = this.find(hsql.toString());
				if (!list2.isEmpty()) {
					String lowPrice = ((Map) list2.get(0)).get("mactualsaleprice").toString();
					provider.setPrice(Double.valueOf(lowPrice));
				}
				String sql2 = " from Hscomment where oeid=" + provider.getIscenicid()
						+ " and status='01' and ptype='01'  ";
				List comList = this.find(sql2);
				provider.setCommentList(comList);

				}
			
		}
		return ps;
	}

	public List getHotel(Long[] iscenicids) {
		List hotelList = new ArrayList();
		int tip = 0;
		StringBuffer id = new StringBuffer();
		for (int i = 0; i < iscenicids.length; i++) {
			Long iscenicid = iscenicids[i];
			StringBuffer hsql = new StringBuffer();
			hsql.append(
					"select new map(s.iscenicid as iscenicid,s.szscenicname as szscenicname,s.iordernumber as iordernumber) from Esbscenicareatab s,Hotelprovider p ");
			hsql.append(" where s.iscenicid = p.iscenicid and s.scenictype = '06' ");
			if (iscenicid != null && !"".equals(iscenicid) && iscenicid != 0) {
				hsql.append(" and p.noted6 is not null");
				hsql.append(" and (p.noted6 like '" + iscenicid + "' or p.noted6 like '" + iscenicid
						+ ",%' or p.noted6 like '%," + iscenicid + "' or p.noted6 like '%," + iscenicid + ",%') ");
			}
			if (tip == 1) {
				String str = id.toString().substring(0, id.toString().length() - 1);
				hsql.append(" and s.iscenicid not in (" + str + ") ");
			}
			hsql.append(" order by s.iordernumber desc");
			List slist = this.findTopNumb(hsql.toString(), 2);
			if (slist != null && !slist.isEmpty()) {
				Map map = (Map) slist.get(0);
				Long providerid = Long.parseLong(map.get("iscenicid").toString());
				tip = 1;
				id.append(providerid + ",");
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
				if (sprice != 0) {
					hotelList.add(map);
				}
			}
		}
		return hotelList;
	}

	public List getWanhui(Long[] iscenicids, String type) {
		List whlist = new ArrayList();
		for (int i = 0; i < iscenicids.length; i++) {
			Long iscenicid = iscenicids[i];
			StringBuffer hsql = new StringBuffer();
			hsql.append(
					"select new map(t.iscenicid as iscenicid,t.itickettypeid as itickettypeid,t.sztickettypename as sztickettypename,t.isequence as isequence,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice) from Edmtickettypetab t,Edmcrowdkindpricetab p where t.byisuse=1 and t.bycategorytype='"
							+ type + "' and t.iscenicid=" + iscenicid);
			hsql.append(
					" and t.itickettypeid = p.itickettypeid and p.byisuse = 1 and p.note1 = '0000' and p.isnet = 1 and p.icrowdkindid = 1 and p.startdata <='"
							+ Tools.getDays() + "' and p.enddata >='" + Tools.getDays() + "' ");
			hsql.append(" order by t.isequence desc");
			List list = this.findTopNumb(hsql.toString(), 1);
			if (list != null && !list.isEmpty()) {
				Map map = (Map) list.get(0);
				Long itickettypeid = Long.parseLong(map.get("itickettypeid").toString());
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename,u.upname as upname) from Upfile u,Secenicpicture p where p.itickettypeid="
						+ itickettypeid + " and p.upid = u.upid order by p.isecenicpictureid";
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

				whlist.add(map);
			}
		}
		return whlist;
	}

	public List getZhuche(Long[] iscenicids) {
		List zcList = new ArrayList();
		for (int i = 0; i < iscenicids.length; i++) {
			Long iscenicid = iscenicids[i];
			StringBuffer hsql = new StringBuffer();
		}
		return null;
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

	/**
	 * ���ݷ���������ѯ����Դ���б�* Describe:
	 *
	 * @see
	 * @param pdtp
	 * @return
	 * @author Ԭ�ɾ� ������ѯ ���������ڿ�Դ�� ,�������̷ŵ���Ӧ�Ŀ�Դ�������sceniclist ��
	 */
	public List getscenicSourceList(String pdtp) {
		List list = new ArrayList();
		String hsql = " from Esbscenicareatab where scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
				+ pdtp + "' or spmcd='" + pdtp + "')) and isjd=0 and byisuse=1 ";
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
			for (int i = 0; i < list.size(); i++) {
				Galsourceregiontab g = (Galsourceregiontab) list.get(i);
				List slist = new ArrayList();
				for (int j = 0; j < sourcelist.size(); j++) {
					Esbscenicareatab provider = (Esbscenicareatab) sourcelist.get(j);
					if (provider.getSzregionalid().longValue() == g.getIregionalid().longValue()) {
						slist.add(provider);

					}
				}
				g.setSceniclist(slist);
			}
		}
		return list;
	}

	/**
	 *
	 */
	public List queryscenicSource(String pdtp) {
		List list = new ArrayList();
		String hsql = " from Esbscenicareatab where scenictype in (select sys1.id.pmcd from Sysparv5 sys1 where sys1.id.pmky='PDTP' and (sys1.id.pmcd='"
				+ pdtp + "' or spmcd='" + pdtp + "')) and isjd=0 and byisuse=1";
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
	 *
	 * Describe:��ҳר��(ֻ��ѯ�˳���Ʊ)
	 *
	 * @author:chenxinhao
	 * @param rzti
	 * @param ldti
	 * @param ibusinessid
	 * @param top
	 * @param tip
	 *            1�ؼ�
	 * @return return:List Date:2013-12-17
	 */
	public List queryProduct(String rzti, String ldti, Long ibusinessid, int top, int tip) {
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,pri.mactualsaleprice as mactualsaleprice,prd.isequence as isequence) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro,Edmticketproduct ep");
		hsql.append(" where pro.scenictype='01' and pri.startdata <='" + rzti + "' and pri.enddata >='" + ldti
				+ "' and pri.byisuse = 1 and prd.byisuse = 1 and pro.byisuse = 1 and pri.isnet=1 and pri.note1='0000' and prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid and pri.icrowdkindid = 1 and ep.itickettypeid = prd.itickettypeid and  prd.bycategorytype not in ( '0013','0004','120','119')   ");

		hsql.append(" and pri.ibusinessid = " + ibusinessid);

		if (tip == 1) {
			hsql.append(" and ep.inoteger5=1 ");
		}
		hsql.append(" order by prd.isequence desc");
		List list = null;
		// System.out.println(hsql.toString());
		if (top != 0) {
			list = this.findTopNumb(hsql.toString(), top);
		} else {
			list = this.find(hsql.toString());
		}
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long prid = new Long(0);

				Object o = map.get("itickettypeid");
				if (o != null) {
					prid = (Long) map.get("itickettypeid");

				}

				System.out.println("prid=" + prid);
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
						+ Long.parseLong(map.get("iscenicid").toString()) + " and p.itickettypeid=" + prid
						+ "  and  p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.findTopNumb(sql, 1);
				if (piclist != null && !piclist.isEmpty()) {
					Map picmap = (Map) piclist.get(0);
					map.put("upadder", picmap.get("upadder").toString());
					map.put("upfilename", picmap.get("upfilename").toString());
				} else {
					map.put("upadder", "");
					map.put("upfilename", "");
				}
			}
		}
		return list;
	}

	/**
	 * ��ҳ��ѯ��Ʊ��Ϣ �¼����������������Ʊ���� 2017 10 18 xl
	 */
	public List queryProductIndex(String scenicIds, String rzti, String ldti, Long ibusinessid, int top, int tip,
			boolean isAll) {
		if (!isAll) {
			return null;
		}
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,pri.mactualsaleprice as mactualsaleprice,prd.isequence as isequence) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro,Edmticketproduct ep");
		hsql.append(" where pro.scenictype='01' and pri.startdata <='" + rzti + "' and pri.enddata >='" + ldti + "' "
				+ " and pri.note3 <> '1'"
				+ " and pri.byisuse = 1 and prd.byisuse = 1 and pro.byisuse = 1 and pri.isnet=1 and pri.note1='0000' and prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid and pri.icrowdkindid = 1 and ep.itickettypeid = prd.itickettypeid and  prd.bycategorytype not in ( '0013','0004','120','119')   ");

		hsql.append(" and pri.ibusinessid = " + ibusinessid);
		if (!"".equals(scenicIds) && scenicIds.length() > 0) {
			hsql.append(" and pro.iscenicid in (" + scenicIds + ") ");
		}
		if (tip == 1) {
			hsql.append(" and ep.inoteger5=1 ");
		}
		hsql.append(" order by prd.isequence desc");
		List list = null;
		// System.out.println(hsql.toString());
		if (top != 0) {
			list = this.findTopNumb(hsql.toString(), top);
		} else {
			list = this.find(hsql.toString());
		}
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long prid = new Long(0);

				Object o = map.get("itickettypeid");
				if (o != null) {
					prid = (Long) map.get("itickettypeid");

				}
				System.out.println("prid=" + prid);
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
						+ Long.parseLong(map.get("iscenicid").toString()) + " and p.itickettypeid=" + prid
						+ "  and  p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.findTopNumb(sql, 1);
				if (piclist != null && !piclist.isEmpty()) {
					Map picmap = (Map) piclist.get(0);
					map.put("upadder", picmap.get("upadder").toString());
					map.put("upfilename", picmap.get("upfilename").toString());
				} else {
					map.put("upadder", "");
					map.put("upfilename", "");
				}
			}
		}
		return list;
	}

	public PaginationSupport queryProductIndexs(String scenicIds, String rzti, String ldti, Long ibusinessid, int top, int tip,
			boolean isAll,int pageSize, int startInt, String url) {
		if (!isAll) {
			return null;
		}
		StringBuffer hsql = new StringBuffer();
		hsql.append(
				"select distinct new map(pro.iscenicid as iscenicid,pro.szscenicname as szscenicname,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,pri.mactualsaleprice as mactualsaleprice,prd.isequence as isequence) from Edmtickettypetab prd,Edmcrowdkindpricetab pri,Esbscenicareatab pro,Edmticketproduct ep");
		hsql.append(" where pro.scenictype='01' and pri.startdata <='" + rzti + "' and pri.enddata >='" + ldti+"' "
				+ " and pri.note3<>1 " //�������ο���Ʊ
				+ " and pri.byisuse = 1 and prd.byisuse = 1 and pro.byisuse = 1 and pri.isnet=1 and pri.note1='0000' and prd.itickettypeid = pri.itickettypeid and pro.iscenicid = prd.iscenicid and pri.icrowdkindid = 1 and ep.itickettypeid = prd.itickettypeid and  prd.bycategorytype not in ( '0013','0004','120','119')   ");

		hsql.append(" and pri.ibusinessid = " + ibusinessid);
		if (!"".equals(scenicIds) && scenicIds.length() > 0) {
			hsql.append(" and pro.iscenicid in (" + scenicIds + ") ");
		}
		if (tip == 1) {
			hsql.append(" and ep.inoteger5=1 ");
		}
		hsql.append(" order by prd.isequence desc");
		PaginationSupport ps = this.findPage(hsql.toString(), pageSize, startInt, url);
		// System.out.println(hsql.toString());
		List list = ps.getItems();
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				Long prid = new Long(0);
				Object o = map.get("itickettypeid");
				if (o != null) {
					prid = (Long) map.get("itickettypeid");

				}
				System.out.println("prid=" + prid);
				String sql = " select new map( u.upadder as upadder,u.upfilename as upfilename ) from Upfile u,Secenicpicture p where p.iscenicid="
						+ Long.parseLong(map.get("iscenicid").toString()) + " and p.itickettypeid=" + prid
						+ "  and  p.upid = u.upid order by p.isecenicpictureid";
				List piclist = this.findTopNumb(sql, 1);
				if (piclist != null && !piclist.isEmpty()) {
					Map picmap = (Map) piclist.get(0);
					map.put("upadder", picmap.get("upadder").toString());
					map.put("upfilename", picmap.get("upfilename").toString());
				} else {
					map.put("upadder", "");
					map.put("upfilename", "");
				}
			}
		}
		return ps;
	}

	public List searchTicketByProid(Long iscenicid, String rzti, Long ibusinessid) throws Exception {
		StringBuffer hsql2 = new StringBuffer();

		Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, iscenicid);
		String szlasttime = esb.getSzlasttime();
		Esbprovicerq esbprovicerq = this.findEsbprovicerq(iscenicid, ibusinessid);
		if (esbprovicerq != null) {
			szlasttime = esbprovicerq.getSzlasttime();
		}
		Date date = new Date();
		TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setTimeZone(tZone);
		String time = format.format(date);
		if (Tools.getDays().equals(rzti)) {
			// �ж������Ƿ񳬹���������Ԥ��
			int hour = time.compareTo(szlasttime);
			if (hour >= 0) {// ���ڵ���0��ʾ��������
				rzti = Tools.getDate(rzti, 1);
			}
		}

		hsql2.append("select distinct new map(s.pmvc as pmvc,to_char(prd.szmemo)  as szmemo,s.pmva as pmva,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype,prd.isequence as isequence,prd.int3 as int3) from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Sysparv5 s where prd.iscenicid="
                + iscenicid
                + " and prc.startdata<='"
                + rzti
                + "' and prc.enddata>='"
                + rzti
                + "' and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not in ( '0013','0004','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1 and prc.note1='0000' AND prd.itickettypeid = prc.itickettypeid");

        hsql2.append(" and prc.ibusinessid=" + ibusinessid);

        hsql2.append(" order by pmvc, prd.isequence desc,prd.itickettypeid");
		List productList = this.find(hsql2.toString());
		List productAll = new ArrayList();

		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				int index = 0;
				Map map = (Map) productList.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				for (int j = 0; j < productList.size(); j++) {
					Map map2 = (Map) productList.get(j);
					Long itickettypeid2 = Long.valueOf(map2.get("itickettypeid").toString());
					if (itickettypeid.equals(itickettypeid2)) {
						index++;
					}
				}

				if (index > 1) {
					productList.remove(i);
				}

			}
		}

		if (productList != null && !productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				Edmtickettypetab ticket = new Edmtickettypetab();
				Map map = (Map) productList.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				ticket.setStrbycategorytype(map.get("pmva").toString());
				BeanUtils.populate(ticket, map);
				String sql = "select new map(p.szmemo as szmemo,p.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.szmemo as szmemo,p.ipeoplenumrange as isrealname) from Edmcrowdkindpricetab p, Edpcrowdkindtab c"
                        + " where p.isnet = 1 and p.byisuse = 1 and p.note1='0000' and p.note3 !='1' and p.icrowdkindid = c.icrowdkindid and p.startdata <='"
                        + rzti
                        + "' and p.enddata >='"
                        + rzti
                        + "' and p.itickettypeid ="
                        + ticket.getItickettypeid()
                        + "  and p.ibusinessid="
                        + ibusinessid
                        + " order by p.icrowdkindid";
				List priceList = this.find(sql);
				if(priceList != null && priceList.size()>0) {
					ticket.setPriceList(priceList);
					if (ticket.getInt3() == 1) {
						String sqltime = "select new map(tst.id as id,ett.iscenicid as iscenicid,tst.startDate as startdate,tst.endDate as enddate,tst.startDate||'-'||tst.endDate as date,tst.tatalStock as tatalstock,tst.currStock as currstock,tst.saleStock as salestock,tst.productId as productid,tst.id as id,ett.itickettypeid as itickettypeid) from TimeSharingTicketTab tst,Edmtickettypetab ett where tst.productId=ett.sztickettypecode ";
	                    sqltime+=" and ett.itickettypeid="+itickettypeid;
	    	            if(iscenicid!=0){
	    	            	sqltime+=" and  ett.iscenicid=" + iscenicid;
	    				}
						sqltime += " and tst.dayTime='" + rzti + "'";
						List timelist = this.find(sqltime);
						ticket.setTimeList(timelist);
					}
					productAll.add(ticket);
				}
			}
		}
		return productAll;
	}

	public List searchTicketByProid(Long iscenicid, Long isscancode, String rzti, Long ibusinessid) throws Exception {

		StringBuffer hsql2 = new StringBuffer();

		Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, iscenicid);
		String szlasttime = esb.getSzlasttime();
		Esbprovicerq esbprovicerq = this.findEsbprovicerq(iscenicid, ibusinessid);
		if (esbprovicerq != null) {
			szlasttime = esbprovicerq.getSzlasttime();
		}
		Date date = new Date();
		TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setTimeZone(tZone);
		String time = format.format(date);
		if (Tools.getDays().equals(rzti)) {
			// �ж������Ƿ񳬹���������Ԥ��
			int hour = time.compareTo(szlasttime);
			if (hour >= 0) {// ���ڵ���0��ʾ��������
				rzti = Tools.getDate(rzti, 1);
			}
		}

		hsql2.append(
				"select distinct new map(s.pmvc as pmvc,prd.szmemo  as szmemo,s.pmva as pmva,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype,prd.isequence as isequence) from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Sysparv5 s where prd.iscenicid="
						+ iscenicid + " and prc.isscancode=" + isscancode + " and prc.startdata<='" + rzti
						+ "' and prc.enddata>='" + rzti + " and prd.int3=0 "// ȥ����ʱԤԼ
						+ "'  and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not in ( '0013','0004','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid and prc.note1='0000' ");

		hsql2.append(" and prc.ibusinessid=" + ibusinessid);

		hsql2.append(" order by pmvc, prd.isequence desc,prd.itickettypeid");
		List productList = this.find(hsql2.toString());
		List productAll = new ArrayList();

		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				int index = 0;
				Map map = (Map) productList.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				for (int j = 0; j < productList.size(); j++) {
					Map map2 = (Map) productList.get(j);
					Long itickettypeid2 = Long.valueOf(map2.get("itickettypeid").toString());
					if (itickettypeid.equals(itickettypeid2)) {
						index++;
					}
				}

				if (index > 1) {
					productList.remove(i);
				}
			}
		}

		if (productList != null && !productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				Edmtickettypetab ticket = new Edmtickettypetab();
				Map map = (Map) productList.get(i);
				ticket.setStrbycategorytype(map.get("pmva").toString());
				BeanUtils.populate(ticket, map);
				String sql = "select new map(p.szmemo as szmemo,p.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.szmemo as szmemo,p.ipeoplenumrange as isrealname) from Edmcrowdkindpricetab p, Edpcrowdkindtab c"
						+ " where p.isnet = 1 and p.byisuse = 1 and p.note1='0000' and p.icrowdkindid = c.icrowdkindid and p.startdata <='"
						+ rzti + "' and p.enddata >='" + rzti + "' and p.itickettypeid =" + ticket.getItickettypeid()
						+ " and p.isscancode=" + isscancode + "  and p.ibusinessid=" + ibusinessid
						+ " order by p.icrowdkindid";
				List priceList = this.find(sql);
				ticket.setPriceList(priceList);
				productAll.add(ticket);
			}
		}
		return productAll;

	}

	public List searchTicketByProid(Long iscenicid, String rzti, Long ibusinessid, String groupno) throws Exception {
		StringBuffer hsql2 = new StringBuffer();

		Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, iscenicid);
		Date date = new Date();
		TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setTimeZone(tZone);
		String time = format.format(date);
		if (Tools.getDays().equals(rzti)) {
			// �ж������Ƿ񳬹���������Ԥ��
			int hour = time.compareTo(esb.getSzlasttime());
			if (hour >= 0) {// ���ڵ���0��ʾ��������
				rzti = Tools.getDate(rzti, 1);
			}
		}

		Numjifenset jfset = new Numjifenset();

		String nhql = "from Numjifenset where iscenicid=" + iscenicid + " and jflb=-1";
		List list = find(nhql);
		if (list != null && list.size() > 0) {
			jfset = (Numjifenset) list.get(0);
		}

		hsql2.append(
				"select distinct new map(s.pmvc as pmvc,prd.szmemo  as szmemo,s.pmva as pmva,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prd.sztickettypecode as sztickettypecode,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype,prd.isequence as isequence,prd.int3 as int3) from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Sysparv5 s where prd.iscenicid="
						+ iscenicid + " and prc.startdata<='" + rzti + "' and prc.enddata>='" + rzti
						+ "' and prc.note1 in ('" + groupno
						+ "') and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not in ( '0013','0004','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid and prc.note3 != '1' ");

		hsql2.append(" and prc.ibusinessid=" + ibusinessid);

		hsql2.append(" order by pmvc, prd.isequence desc,prd.itickettypeid");
		List productList = this.find(hsql2.toString());
		List productAll = new ArrayList();

		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				int index = 0;
				Map map = (Map) productList.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				for (int j = 0; j < productList.size(); j++) {
					Map map2 = (Map) productList.get(j);
					Long itickettypeid2 = Long.valueOf(map2.get("itickettypeid").toString());
					if (itickettypeid.equals(itickettypeid2)) {
						index++;
					}
				}

				if (index > 1) {
					productList.remove(i);
				}
			}
		}

		if (productList != null && !productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				Edmtickettypetab ticket = new Edmtickettypetab();
				TimeSharingTicketTab timesharingtickettab = new TimeSharingTicketTab();
				Map map = (Map) productList.get(i);
				ticket.setStrbycategorytype(map.get("pmva").toString());
				BeanUtils.populate(ticket, map);
				String sql = "select new map(p.icrowdkindpriceid as icrowdkindpriceid,p.szmemo as szmemo,p.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.szmemo as szmemo,p.ipeoplenumrange as isrealname) from Edmcrowdkindpricetab p, Edpcrowdkindtab c"
						+ " where p.isnet = 1 and p.byisuse = 1 and p.icrowdkindid = c.icrowdkindid and p.startdata <='"
						+ rzti + "' and p.enddata >='" + rzti + "' and p.itickettypeid =" + ticket.getItickettypeid()
						+ "  and p.ibusinessid=" + ibusinessid + " and p.note1 in ('" + groupno
						+ "')  order by p.icrowdkindid";
				List priceList = this.find(sql);

				if (ticket.getInt3() == 1) {// �Д��Ƿ��֕r�A�s
					String timesql = " from TimeSharingTicketTab tst where 1=1 ";
					if (map.get("sztickettypecode").toString() != null
							&& !"".equals(map.get("sztickettypecode").toString())) {
						timesql += " and  tst.productId='" + map.get("sztickettypecode").toString() + "'";
					}
					/*
					 * if (Tools.getDays().equals(rzti)) {
					 * timesql+=" and tst.endDate>='"+time+"'"; }
					 */
					timesql += " and tst.dayTime='" + rzti + "'";
					// timesql+=" and tst.currStock>0";
					timesql += " order by tst.id";
					// String sql = " select new map(iscenicid as iscenicid)from
					// Edmtickettypetab where 1=1";
					System.out.println("timesql:" + timesql);
					List timelist = this.find(timesql);
					if (timelist != null && timelist.size() > 0) {
						// List time =
						// ticketDao.getTimeStock(ttp.getSztickettypecode());
						timesharingtickettab.setTimes(timelist);
					}

				}

				for (int j = 0; j < priceList.size(); j++) {// ���û���
					Map maps = (Map) priceList.get(j);
					if (jfset != null) {
						Numjifensetlist detail = this.getSalesRule(ticket.getItickettypeid(), jfset.getNid(),
								Long.parseLong(maps.get("icrowdkindid").toString()), ibusinessid, rzti);
						if (detail != null) {
							maps.put("type", detail.getXffs());
							maps.put("isyh", 1);
							maps.put("point", detail.getIint4());
							maps.put("tnum", detail.getIint3());
						}
					}
					// TOOD ��ȡ�Ż�����,ָ���Ż�����

					Edpofferschemetab scheme = this.getScheme(ticket.getIscenicid(), ibusinessid,
							Long.parseLong(maps.get("icrowdkindid").toString()), ticket.getItickettypeid(), rzti);
					if (scheme != null) {
						maps.put("scheme", 1);
						maps.put("basnum", scheme.getImultiples());
						maps.put("schemetype", scheme.getBycategorytype());
						maps.put("scnum", scheme.getIoffernum());
						// ָ���Żݶ��󲻽�����,���Ҫ���������������չ
					}
				}

				ticket.setPriceList(priceList);
				ticket.setTimesharingtickettab(timesharingtickettab);
				productAll.add(ticket);
			}
		}
		return productAll;
	}

	public List searchTicketByProid(Long iscenicid, Long icrowdkindpiceid, String rzti, Long ibusinessid,
			String groupno) throws Exception {
		StringBuffer hsql2 = new StringBuffer();

		Esbscenicareatab esb = (Esbscenicareatab) get(Esbscenicareatab.class, iscenicid);
		Date date = new Date();
		TimeZone tZone = TimeZone.getTimeZone("Asia/Shanghai");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		format.setTimeZone(tZone);
		String time = format.format(date);
		if (Tools.getDays().equals(rzti)) {
			// �ж������Ƿ񳬹���������Ԥ��
			int hour = time.compareTo(esb.getSzlasttime());
			if (hour >= 0) {// ���ڵ���0��ʾ��������
				rzti = Tools.getDate(rzti, 1);
			}
		}

		Numjifenset jfset = new Numjifenset();

		String nhql = "from Numjifenset where iscenicid=" + iscenicid + " and jflb=-1";
		List list = find(nhql);
		if (list != null && list.size() > 0) {
			jfset = (Numjifenset) list.get(0);
		}

		hsql2.append(
				"select distinct new map(s.pmvc as pmvc,prd.szmemo  as szmemo,s.pmva as pmva,prd.itickettypeid as itickettypeid,prd.sztickettypename as sztickettypename,prd.iscenicid as iscenicid,prd.bycategorytype as bycategorytype,prd.isequence as isequence) from Edmtickettypetab prd,Edmcrowdkindpricetab prc,Sysparv5 s where prd.iscenicid="
						+ iscenicid + " and int3=0"// ȥ����ʱԤԼ
						+ " and prc.isscancode=" + icrowdkindpiceid + " and prc.startdata<='" + rzti
						+ "' and prc.enddata>='" + rzti + "' and prc.note1 in ('" + groupno
						+ "') and s.id.pmky='PRTP' and s.id.pmcd=prd.bycategorytype and prd.bycategorytype not in ( '0013','0004','120','119')  and  prd.byisuse=1 and prc.isnet=1 and prc.byisuse=1  and prd.itickettypeid = prc.itickettypeid");

		hsql2.append(" and prc.ibusinessid=" + ibusinessid);

		hsql2.append(" order by pmvc, prd.isequence desc,prd.itickettypeid");
		List productList = this.find(hsql2.toString());
		List productAll = new ArrayList();

		if (productList != null && productList.size() > 0) {
			for (int i = 0; i < productList.size(); i++) {
				int index = 0;
				Map map = (Map) productList.get(i);
				Long itickettypeid = Long.valueOf(map.get("itickettypeid").toString());
				for (int j = 0; j < productList.size(); j++) {
					Map map2 = (Map) productList.get(j);
					Long itickettypeid2 = Long.valueOf(map2.get("itickettypeid").toString());
					if (itickettypeid.equals(itickettypeid2)) {
						index++;
					}
				}

				if (index > 1) {
					productList.remove(i);
				}
			}
		}

		if (productList != null && !productList.isEmpty()) {
			for (int i = 0; i < productList.size(); i++) {
				Edmtickettypetab ticket = new Edmtickettypetab();
				Map map = (Map) productList.get(i);
				ticket.setStrbycategorytype(map.get("pmva").toString());
				BeanUtils.populate(ticket, map);
				String sql = "select new map(p.icrowdkindpriceid as icrowdkindpriceid,p.szmemo as szmemo,p.icrowdkindid as icrowdkindid,c.szcrowdkindname as szcrowdkindname,p.listingprice as listingprice,p.mactualsaleprice as mactualsaleprice,p.szmemo as szmemo,p.ipeoplenumrange as isrealname) from Edmcrowdkindpricetab p, Edpcrowdkindtab c"
						+ " where p.isnet = 1 and p.byisuse = 1 and p.icrowdkindid = c.icrowdkindid and p.startdata <='"
						+ rzti + "' and p.isscancode=" + icrowdkindpiceid + " and p.enddata >='" + rzti
						+ "' and p.itickettypeid =" + ticket.getItickettypeid() + "  and p.ibusinessid=" + ibusinessid
						+ " and p.note1 in ('" + groupno + "')  order by p.icrowdkindid";
				List priceList = this.find(sql);

				for (int j = 0; j < priceList.size(); j++) {// ���û���
					Map maps = (Map) priceList.get(j);
					if (jfset != null) {
						Numjifensetlist detail = this.getSalesRule(ticket.getItickettypeid(), jfset.getNid(),
								Long.parseLong(maps.get("icrowdkindid").toString()), ibusinessid, rzti);
						if (detail != null) {
							maps.put("type", detail.getXffs());
							maps.put("isyh", 1);
							maps.put("point", detail.getIint4());
							maps.put("tnum", detail.getIint3());
						}
					}
					// TOOD ��ȡ�Ż�����,ָ���Ż�����

					Edpofferschemetab scheme = this.getScheme(ticket.getIscenicid(), ibusinessid,
							Long.parseLong(maps.get("icrowdkindid").toString()), ticket.getItickettypeid(), rzti);
					if (scheme != null) {
						maps.put("scheme", 1);
						maps.put("basnum", scheme.getImultiples());
						maps.put("schemetype", scheme.getBycategorytype());
						maps.put("scnum", scheme.getIoffernum());
						// ָ���Żݶ��󲻽�����,���Ҫ���������������չ
					}
				}

				ticket.setPriceList(priceList);
				productAll.add(ticket);
			}
		}
		return productAll;
	}

	public Numjifensetlist getSalesRule(Long itickettypeid, Long nid, Long icrowid, Long ibusinessid, String date) {
		String hql = "from Numjifensetlist where nid=" + nid + " and itickettypeid=" + itickettypeid + " and iint1="
				+ icrowid + " and iint2=" + ibusinessid + " and stdt<='" + date + "' and etdt>='" + date + "'";
		System.out.println("====.....sql" + hql);
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Numjifensetlist) list.get(0);
		} else {
			return null;
		}
	}

	public Edpofferschemetab getScheme(Long iscenicid, Long ibusinessid, Long icrowkind, Long itickettypeid,
			String startdate) {
		String hql = "from Edpofferschemetab where  icrowdkindid=" + icrowkind + "  and  iscenicid=" + iscenicid
				+ " and ibusinessid=" + ibusinessid + " and itickettypeid=" + itickettypeid + " and startdata<='"
				+ startdate + "' and enddata>='" + startdate + "' and byisuse=1 and ioffertype=0 ";
		System.out.println("-===>>" + hql);
		List list = find(hql);
		if (list != null && list.size() > 0) {
			return (Edpofferschemetab) list.get(0);
		} else {
			return null;
		}
	}

	public Esbprovicerq findEsbprovicerq(Long iscenicid, Long ibusinessid) {
		String hql = " from Esbprovicerq where ibusinessid=" + ibusinessid + " and  iscenicid=" + iscenicid;
		List list = this.find(hql);
		if (list != null && !list.isEmpty()) {
			return (Esbprovicerq) list.get(0);
		}
		return null;
	}

	public List findTimeticket(long iscenicid, long i) {
		try {
			String sql = "select new map(tst.id as id,ett.iscenicid as iscenicid,tst.startDate as startdate,tst.endDate as enddate,tst.startDate||'-'||tst.endDate as date,tst.tatalStock as tatalstock,tst.currStock as currstock,tst.saleStock as salestock,tst.productId as productid,tst.id as id,ett.itickettypeid as itickettypeid) from TimeSharingTicketTab tst,Edmtickettypetab ett where tst.productId=ett.sztickettypecode ";
			if (iscenicid != 0) {
				sql += " and  ett.iscenicid=" + iscenicid;
			}
			if (i != 0) {
				sql += " and tst.id =" + i;
			}
			sql += " order by tst.id";
			// String sql = " select new map(iscenicid as iscenicid)from
			// Edmtickettypetab where 1=1";
			List list = this.find(sql);
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List searchTimeticketsizeByIscenicid(long iscenicid) {
		try {
			String sql = "select distinct new map( ett.iscenicid as iscenicid,ett.itickettypeid as itickettypeid ) from TimeSharingTicketTab tst,Edmtickettypetab ett where tst.productId=ett.sztickettypecode ";
			if (iscenicid != 0) {
				sql += " and  ett.iscenicid=" + iscenicid;
			}
			// sql +=" order by tst.id";
			// String sql = " select new map(iscenicid as iscenicid)from
			// Edmtickettypetab where 1=1";
			List list = this.find(sql);
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// �˶�����
	public Ticketxgz searchTicketRefused(Long ticketid) throws Exception {
		String hql = " from Ticketxgz t where  t.isvalid=1 ";
		hql += " and t.itickettypeid=";
		hql += ticketid;
		List list = this.find(hql);
		if (list != null && !list.isEmpty()) {
			return (Ticketxgz) list.get(0);
		}
		return null;
	}

	// ������Ϣ
	public MOrder queryOrder(Long orderId, String ddzt) {
		String hql = " from MOrder t where t.orid=" + orderId + " and t.ddzt='" + ddzt + "'";
		List list = this.find(hql);
		if (list != null && !list.isEmpty()) {
			return (MOrder) list.get(0);
		}
		return null;
	}

	public List<TOrder> queryTOrder(String orderId) throws Exception {
		String hql = " from TOrder where orid='" + orderId + "'";
		List list = this.find(hql);
		return list;

	}
}
