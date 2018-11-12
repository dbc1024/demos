package com.ectrip.upload.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;



public interface IUpfileDao extends IGenericDao {

	
	public List findUpfiles(String abelong, String avalue);
	/**
	 * 根据服务商编号删除图片
	 * Describe:
	 * @auth:huangyuqi
	 * @param providerId服务商编号
	 * @param strdo操作标识(只能输入delete或update)
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteUpfilesbyProvider(Long providerId,String strdo);
	/**
	 * 根据产品编号删除图片
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId
	 * return:void
	 * Date:2011-9-27
	 */
	public void deleteUpfilesbyProduct(Long productId);
	
	public void updateAdd(String[] upids, String avalue);

	public void updateEdit(String[] upids, String abelong, String avalue);

	
	
}
