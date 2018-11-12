package com.ectrip.ticket.afcset.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.afcset.dao.IEsbgardengatetabDAO;
import com.ectrip.ticket.afcset.service.IEsbgardengatetabService;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.afcset.Gardengatelink;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

@Service
public class EsbgardengatetabService extends GenericService implements IEsbgardengatetabService {

    private IEsbgardengatetabDAO esbgardengatetabDao;

    @Autowired
    public void setEsbgardengatetabDao(IEsbgardengatetabDAO esbgardengatetabDao) {
        this.esbgardengatetabDao = esbgardengatetabDao;
        super.setGenericDao(esbgardengatetabDao);
    }

    /**
     * 添加园门
     *
     * @throws Exception
     */
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void addGardenGate(Esbgardengatetab esbgardengatetab,Syslog syslog)
            throws Exception {
        this.esbgardengatetabDao.addGardenGate(esbgardengatetab,syslog);
    }

    /**
     * 删除园门
     *
     * @throws Exception
     */
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void deleteGardenGate(Esbgardengatetab esbgardengatetab,Syslog syslog)
            throws Exception {
        this.esbgardengatetabDao.delGardenGate(esbgardengatetab,syslog);
    }

    /**
     * 园门分页查询
     */
    public PaginationSupport getGaredenGatePage(
    		Long providerId, Long isgardengateid, String fws, int pageSize,
            int startIndex, String url) throws Exception {
        return this.esbgardengatetabDao.getGaredenGatePage(providerId, isgardengateid,
                fws, pageSize, startIndex, url);
    }

    /**
     * 更新园门
     */
    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void updateGardenGate(Esbgardengatetab esbgardengatetab,Syslog syslog)
            throws Exception {
        this.esbgardengatetabDao.updateGardenGate(esbgardengatetab,syslog);
    }

    /**
     * 获得最大ID
     */
    public Long getMaxId() throws Exception {
        return esbgardengatetabDao.getMaxId();
    }

    public boolean getEgardengatecode(String szgardengatecode) throws Exception {
        List list = this.esbgardengatetabDao
                .getSzgardengatecode(szgardengatecode);
        if (list.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addEsbscenicareatab(Esbscenicareatab e) throws Exception {
        this.esbgardengatetabDao.addEsbscenicareatab(e);
    }

    /**
     * 根据编号查询园门信息
     */
//    public Esbgardengatetab getGardenGateById(EsbgardengatetabId id)
//            throws Exception {
//        return this.esbgardengatetabDao.getGardenGateById(id);
//    }
    public Esbgardengatetab getGardenGateById(Long igardengateid) throws Exception {
        return this.esbgardengatetabDao.getGardenGateById(igardengateid);
    }
    /**
     * 得到园门列表
     * Describe:
     * @auth:huangyuqi
     * @param esfemployeetab
     * @return
     * return:List
     * Date:2012-1-10
     */
    public List getGradeList(Esfemployeetab esfemployeetab){
        return esbgardengatetabDao.getGradeList(esfemployeetab);
    }

    public List showProductList(String iscenicids){
        return esbgardengatetabDao.showProductList(iscenicids);
    }

    public List getGradeList(Esfemployeetab esfemployeetab,Long gateid){
        return esbgardengatetabDao.getGradeList(esfemployeetab, gateid);
    }

    public List showGradeLinkList(Long igardengateid,Long ligardengateid,Long itickettypeid){
        return this.esbgardengatetabDao.showGradeLinkList(igardengateid, ligardengateid,itickettypeid);
    }

    public Esbgardengatetab showGate(Long igardengateid){
        return this.esbgardengatetabDao.showGate(igardengateid);
    }

    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void addGradeLink(Gardengatelink link,Syslog syslog){
        this.esbgardengatetabDao.addGradeLink(link, syslog);
    }

    public PaginationSupport showLinkListByid(Long igardengateid, int pageSize,int startIndex, String url){
        return this.esbgardengatetabDao.showLinkListByid(igardengateid, pageSize, startIndex, url);
    }

    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void updateGradeLink(Gardengatelink link,Syslog syslog){
        this.esbgardengatetabDao.updateGradeLink(link, syslog);
    }

    public Map viewLink(Long linkid){
        return this.esbgardengatetabDao.viewLink(linkid);
    }

    @Transactional(propagation=Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void delLink(Long linkid,Syslog syslog){
        this.esbgardengatetabDao.delLink(linkid, syslog);
    }

    public List queryOtherGarden(Long iscenicid, Long gateid) {
        return this.esbgardengatetabDao.queryOtherGarden(iscenicid, gateid);
    }

    /**
	 * 根据景区id获取园门下拉列表
	 */
	public List getGardenGateSelect(Long iscenicid) {
		// TODO Auto-generated method stub
		return esbgardengatetabDao.getGardenGateSelect(iscenicid);
	}
}
