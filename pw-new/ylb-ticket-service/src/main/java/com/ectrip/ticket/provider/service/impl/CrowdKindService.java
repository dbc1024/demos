package com.ectrip.ticket.provider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.provider.dao.ICrowdKindDAO;
import com.ectrip.ticket.provider.service.ICrowdKindService;

@Service
public class CrowdKindService implements ICrowdKindService {
	
	@Autowired
	private ICrowdKindDAO crowdkindDao;
	
	
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
	public PaginationSupport getCrowdKindList(Long iparentid,int page,int pageSize,String url,String path,String condition){
		return crowdkindDao.getCrowdKindList(iparentid, page, pageSize, url, path,condition);
	}
	/**
	 * 更改层序号
	 * @param ilever 层
	 * @param ileverseq层序号
	 * @param objectId对象id
	 * @param tablename表名
	 */
	public void updateIleverseq(int ilever,int ileverseq,Long objectId,String tablename){
		crowdkindDao.updateIleverseq(ilever, ileverseq, objectId, tablename);
	}
	
	/**
	 * 判断它是否是最大层级数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param icrowdkindId人群种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean boolMaxIlevel(Long icrowdkindId){
		return crowdkindDao.boolMaxIlevel(icrowdkindId);
	}
	
	/**
	 * 判断它在其它地方被使用到
	 * Describe:
	 * @auth:huangyuqi
	 * @param icrowdkindId 人群种类Id
	 * @return
	 * return:boolean
	 * Date:2011-9-29
	 */
	public boolean boolIsuser(Long icrowdkindId){
		return crowdkindDao.boolIsuser(icrowdkindId);
	}
	/**
	 * 增加人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param edpcrowkindtab人群种类
	 * @param syslog日志
	 * return:void
	 * Date:2011-9-29
	 */
	public void insertCrowdKind(Edpcrowdkindtab edpcrowkindtab,Syslog syslog){
		crowdkindDao.save(edpcrowkindtab);
		
		syslog.setStlg("0051");
		syslog.setBrief("人群种类：" + edpcrowkindtab.getSzcrowdkindname() );
		syslog.setNote("人群种类增加：" + edpcrowkindtab.getSzcrowdkindname() +"("+edpcrowkindtab.getIcrowdkindid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = crowdkindDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		crowdkindDao.save(syslog);
		
	}
 
	/**
	 * 修改人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param edpcrowkindtab人群种类
	 * @param syslog日志
	 * return:void
	 * Date:2011-9-29
	 */
	public void updateCrowdKind(Edpcrowdkindtab edpcrowkindtab,Syslog syslog){
		crowdkindDao.update(edpcrowkindtab);
		
		syslog.setStlg("0052");
		syslog.setBrief("人群种类：" + edpcrowkindtab.getSzcrowdkindname() );
		syslog.setNote("人群种类修改：" + edpcrowkindtab.getSzcrowdkindname() +"("+edpcrowkindtab.getIcrowdkindid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = crowdkindDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		crowdkindDao.save(syslog);
	}
 
	/**
	 * 删除人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @param crowdkindId 人群种类Id
	 * return:void
	 * Date:2011-9-29
	 */
	public void deleteCrowdKind(long crowdkindId,Syslog syslog){
		
		Edpcrowdkindtab edpcrowdkindtab =(Edpcrowdkindtab) crowdkindDao.get(Edpcrowdkindtab.class, crowdkindId); 
		
		crowdkindDao.deleteByKey(Edpcrowdkindtab.class, crowdkindId);//删除
		
		syslog.setStlg("0053");
		syslog.setBrief("人群种类：" + edpcrowdkindtab.getSzcrowdkindname() );
		syslog.setNote("人群种类删除：" + edpcrowdkindtab.getSzcrowdkindname() +"("+edpcrowdkindtab.getIcrowdkindid()+")");
		syslog.setLogdatetime(Tools.getDayTimes());
		Long logid = crowdkindDao.getMaxPk("logid", "Syslog");
		syslog.setLogid(logid + 1);
		crowdkindDao.save(syslog);
	}
	
	/**
	 * 查出所有人群种类
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2011-9-30
	 */
	public List crowdKindList(){
		return crowdkindDao.crowdKindList();
	}
	@Override
	public List getCrowdKindListByIds(String icrowdkindids) {
		return crowdkindDao.getCrowdKindListByIds(icrowdkindids);
	}

}

