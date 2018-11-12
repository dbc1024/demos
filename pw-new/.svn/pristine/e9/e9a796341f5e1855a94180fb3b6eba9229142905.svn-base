package com.ectrip.ticket.provider.service;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;

public interface ICrowdKindService {
	
	/**
	 * 人群种类列表
	 * Describe:
	 * @auth:huangyuqi
	 * @param iparentid父编号
	 * @param page页码
	 * @param pageSize每页显示数
	 * @param url访问地址
	 * @param condition查询条件
	 * @return
	 * return:PaginationSupport
	 * Date:2011-9-29
	 */
	public PaginationSupport getCrowdKindList(Long iparentid,int page,int pageSize,String url,String path,String condition);
	/**
	 * 更改层序号
	 * @param ilever 层
	 * @param ileverseq层序号
	 * @param objectId对象id
	 * @param tablename表名
	 */
	public void updateIleverseq(int ilever,int ileverseq,Long objectId,String tablename);
	
	/**
	 * 判断它是否是最大层级数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param icrowdkindId人群种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean boolMaxIlevel(Long icrowdkindId);
	
	/**
	 * 判断它在其它地方被使用到
	 * Describe:
	 * @auth:huangyuqi
	 * @param icrowdkindId 人群种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean boolIsuser(Long icrowdkindId);
	/**
	 * 增加人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param edpcrowkindtab人群种类
	 * @param syslog日志
	 * return:void
	 * Date:2011-9-29
	 */
	public void insertCrowdKind(Edpcrowdkindtab edpcrowkindtab,Syslog syslog);
 
	/**
	 * 修改人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param edpcrowkindtab人群种类
	 * @param syslog日志
	 * return:void
	 * Date:2011-9-29
	 */
	public void updateCrowdKind(Edpcrowdkindtab edpcrowkindtab,Syslog syslog);
 
	/**
	 * 删除人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindId 人群种类Id
	 * return:void
	 * Date:2011-9-29
	 */
	public void deleteCrowdKind(long crowdkindId,Syslog syslog);
 
	/**
	 * 查出所有人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List crowdKindList();
	
	public List getCrowdKindListByIds(String icrowdkindids);

}

