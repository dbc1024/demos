package com.ectrip.ticket.salesmanager.dao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ticket.model.salesmanager.Opcemployeecardsubtab;

/**
 * 员工卡证子表DAO
 *
 * @author Jzhenhua,Time 2011-10-07 09:29
 */
public interface IOpcemployeecardsubtabDAO extends IGenericDao {

	/**
	 * 添加员工卡证子表信息
	 *
	 * @param opcemployeecardsubtab
	 */
	public void addPloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab);

	/**
	 * 删除员工卡证子表信息
	 *
	 * @param opcemployeecardsubtab
	 */
	public void delPloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab);

	/**
	 * 修改员工卡证子表
	 *
	 * @param opcemployeecardsubtab
	 */
	public void updatePloyeecardSub(Opcemployeecardsubtab opcemployeecardsubtab);

	/**
	 * 根据员工卡编号查询拥有信息
	 *
	 * @param ployeecardId
	 * @return 该员工拥有的卡信息
	 */
	public PaginationSupport getPloyeecardSubByCardId(Long ployeecardId,
													  int pageSize, int startIndex, String url);

	/**
	 * 根据编号查询卡证子表信息
	 *
	 * @param id
	 * @return
	 */
	public Opcemployeecardsubtab getPloyeecardSubById(Long id) throws Exception;

	/**
	 * 获取最大ID
	 *
	 * @return
	 */
	public List getMaxId();

	/**
	 *
	 * Describe:在某一员工卡证下，子表中园门不能重复添加。
	 * @auth:lijingrui
	 * @param igardengateid
	 * @param iemployeecardid
	 * @return
	 * return:boolean
	 * Date:Nov 15, 2011
	 */
	public boolean getPloyeecardSubGard(Long igardengateid,Long iemployeecardid);
}
