package com.ectrip.upload.dao;

import java.util.List;



import com.ectrip.base.dao.GenericDao;
import com.ectrip.upload.dao.idao.IUpfileDao;
import com.ectrip.upload.model.Attach;
import com.ectrip.upload.model.Upfile;

public class UpfileDao extends GenericDao implements IUpfileDao {

	public List findUpfiles(String abelong, String avalue) {
		String hsql = "select new map(a.upid as upid,a.abelong as abelong,a.avalue as avalue,a.ordernumb as ordernumb,u.upname as upname,u.upfilename as upfilename,u.upadder as upadder,u.filetype as filetype,u.filesize as filesize,u.updatetime as updatetime,u.upfrom as upfrom,u.note as note,u.author as author) from Attach a,Upfile u where a.abelong=? and a.avalue=? and a.upid=u.upid order by a.ordernumb";
		return find(hsql, new Object[] { abelong, avalue });
	}

	/**
	 * 根据服务商编号删除图片 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param providerId服务商编号
	 * @param strdo操作标识
	 *            return:void Date:2011-9-27
	 */
	public void deleteUpfilesbyProvider(Long providerId, String strdo) {
		String hsql = "";
		if ("delete".equals(strdo)) {
			hsql = " from Upfile where upid in(select upid from Secenicpicture where iscenicid =" + providerId + ")";
		}
		if ("update".equals(strdo)) {
			hsql = " from Upfile where upid in(select upid from Secenicpicture where iscenicid =" + providerId
					+ " and itickettypeid=0)";
		}
		List list = this.find(hsql);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Upfile upfile = (Upfile) list.get(i);
				this.delete(upfile);
			}
		}
	}

	/**
	 * 根据产品编号删除图片 Describe:
	 * 
	 * @auth:huangyuqi
	 * @param productId
	 *            return:void Date:2011-9-27
	 */
	public void deleteUpfilesbyProduct(Long productId) {
		String hsql = " from Upfile where upid in(select upid from Secenicpicture where itickettypeid =" + productId
				+ ")";
		List list = this.find(hsql);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Upfile upfile = (Upfile) list.get(i);
				this.delete(upfile);
			}
		}
	}

	/**
	 * 增加附件
	 */
	public void updateAdd(String[] upids, String avalue) {
		if (upids == null) {
			System.out.println("============upids si null");
			return;
		} else {
			Attach attach = null;
			int ordernumb = 1;
			for (int i = 0; i < upids.length; i++) {
				System.out.println("============upids:" + upids[i]);
				attach = (Attach) this.get(Attach.class, new Long(upids[i]));
				if (attach == null) {
					continue;
				} else {
					attach.setAvalue(avalue);
					attach.setOrdernumb(new Long(ordernumb));
					this.saveOrUpdate(attach);
					ordernumb++;
				}
			}
		}
	}

	/**
	 * 文章附什增加
	 */
	public void updateEdit(String[] upids, String abelong, String avalue) {
		String hsql = "from Attach where abelong=? and avalue=? order by ordernumb";
		List list = this.find(hsql, new Object[] { abelong, avalue });
		if (list.size() == 0) {
			if (upids == null) {
				return;
			} else {
				this.updateAdd(upids, avalue);
				return;
			}
		} else {
			Attach attach = null;
			for (int i = 0; i < list.size(); i++) {
				Attach a = (Attach) list.get(i);
				this.delete(a);
			}
			// this.deleteAll(list);// 把原来的附件都删除，然后重新添加一次
			// getSession().clear();
			if (upids != null) {
				int ordernumb = 1;
				for (int i = 0; i < upids.length; i++) {
					attach = new Attach();
					attach.setUpid(new Long(upids[i]));
					attach.setAbelong(abelong);
					attach.setAvalue(avalue);
					attach.setOrdernumb(new Long(ordernumb));
					this.save(attach);
					ordernumb++;
				}
			}
		}
	}

}
