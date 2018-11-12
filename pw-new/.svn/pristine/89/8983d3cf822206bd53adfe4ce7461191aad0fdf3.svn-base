package com.ectrip.ticket.provider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ticket.model.provider.Secenicpicture;
import com.ectrip.ticket.provider.dao.ISecenicpictureDAO;

@Repository
public class SecenicpictureDAO extends GenericDao implements ISecenicpictureDAO {

	/**
	 * 根据产品Id来删除图库中的数据*
	 * Describe:
	 * @see com.ectrip.system.provider.dao.idao.ISecenicpictureDAO#deletePictureByProduct(java.lang.Long)
	 * @param productId产品id
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public void deletePictureByProduct(Long productId) {
		String hsql=" from Secenicpicture where itickettypeid="+productId;
		List list = this.find(hsql);
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Secenicpicture picture = (Secenicpicture)list.get(i);
				this.delete(picture);
			}
		}
		

	}
	/**
	 * 根据服务商编号来删除图库中的数据*
	 * Describe:
	 * @see com.ectrip.system.provider.dao.idao.ISecenicpictureDAO#deletePictureByProvidre(java.lang.Long)
	 * @param providrId服务商id
	 * @param strdo操作标识
	 * @author huangyuqi
	 * Date:2011-9-27
	 */
	public void deletePictureByProvidre(Long providerId,String strdo) {
		String hsql="";
		if("delete".equals(strdo)){
			hsql=" from Secenicpicture where iscenicid="+providerId;
		}if("update".equals(strdo)){
			hsql=" from Secenicpicture where iscenicid="+providerId+" and itickettypeid=0";
		}
		List list = this.find(hsql);
		if(list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Secenicpicture picture = (Secenicpicture)list.get(i);
				this.delete(picture);
			}
		}
	}
	
	/**
	 * 根据产品Id判断是否存在图片
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId
	 * @return
	 * return:List
	 * Date:2011-9-28
	 */
	public List QueryPictureByProduct(Long productId){
		List list =null;
		String hsql="  from Secenicpicture where itickettypeid="+productId;
		list = this.find(hsql);
		return list;
	}
	/**
	 * 根据服务商Id判断是否存在图片
	 * Describe:
	 * @auth:huangyuqi
	 * @param productId
	 * @return
	 * return:List
	 * Date:2011-9-28
	 */
	public List QueryPictureByProvider(Long providerId){
		List list =null;
		String hsql=" from Secenicpicture where iscenicid="+providerId+" and itickettypeid=0";
		list = this.find(hsql);
		return list;
	}
}

