package com.ectrip.ticket.provider.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;

public interface ISecenicpictureDAO extends IGenericDao {
	/**
	 * 根据服务商编号来删除图库中的数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param providrId服务商Id 
	 * @param strdo 操作标识（只能输入delete或update)
	 * return:void
	 * Date:2011-9-27
	 */
	public void deletePictureByProvidre(Long providrId,String strdo);
	/**
	 * 根据产品编号来删除图库中的数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId产品Id
	 * return:void
	 * Date:2011-9-27
	 */
	public void deletePictureByProduct(Long productId);
	
	/**
	 * 根据产品Id判断是否存在图片
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId
	 * @return
	 * return:List
	 * Date:2011-9-28
	 */
	public List QueryPictureByProduct(Long productId);
	/**
	 * 根据服务商Id判断是否存在图片
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId
	 * @return
	 * return:List
	 * Date:2011-9-28
	 */
	public List QueryPictureByProvider(Long providerId);

}

