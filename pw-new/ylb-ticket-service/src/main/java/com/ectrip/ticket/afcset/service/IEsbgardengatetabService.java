package com.ectrip.ticket.afcset.service;

import java.util.List;
import java.util.Map;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.afcset.Gardengatelink;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

/**
 * Created By Jzhenhua,Time: 2011-09-27 09:05
 *
 * @author lenovo
 */
public interface IEsbgardengatetabService extends IGenericService{

    /**
     * 删除园门
     *
     * @param esbgardengatetab
     */
    public void deleteGardenGate(Esbgardengatetab esbgardengatetab,Syslog syslog)
            throws Exception;

    /**
     * 修改园门
     *
     * @param esbgardengatetab
     */
    public void updateGardenGate(Esbgardengatetab esbgardengatetab,Syslog syslog)
            throws Exception;

    /**
     * 添加园门
     *
     * @param esbgardengatetab
     */
    public void addGardenGate(Esbgardengatetab esbgardengatetab,Syslog syslog)
            throws Exception;

    /**
     * 根据ID查询园门
     *
     * @param id
     * @return
     */
//    public Esbgardengatetab getGardenGateById(EsbgardengatetabId id)
//            throws Exception;
    public Esbgardengatetab getGardenGateById(Long igardengateid) throws Exception;

    /**
     * Description：查询所有园门信息
     * @param esbgardengatetab
     * @param fws
     * @param pageSize
     * @param startIndex
     * @param url
     * @return 所有园门信息
     * @author jzhenhua Date 2011-10-14
     */
    public PaginationSupport getGaredenGatePage(
    		Long providerId, Long isgardengateid, String fws, int pageSize,
            int startIndex, String url) throws Exception ;

    /**
     * 获得最大园门ID
     *
     * @return
     */
    public Long getMaxId() throws Exception;

    /**
     * 判断当前输入的园门代码是否存在
     *
     * @return
     */
    public boolean getEgardengatecode(String szgardengatecode) throws Exception;

    public void addEsbscenicareatab(Esbscenicareatab e) throws Exception;
    /**
     * 得到园门列表
     * Describe:
     * @auth:huangyuqi
     * @param esfemployeetab
     * @return
     * return:List
     * Date:2012-1-10
     */
    public List getGradeList(Esfemployeetab esfemployeetab);

    public List showProductList(String iscenicids);

    public List getGradeList(Esfemployeetab esfemployeetab,Long gateid);

    public List showGradeLinkList(Long igardengateid,Long ligardengateid,Long itickettypeid);

    public Esbgardengatetab showGate(Long igardengateid);

    public void addGradeLink(Gardengatelink link,Syslog syslog);

    public PaginationSupport showLinkListByid(Long igardengateid, int pageSize,int startIndex, String url);

    public void updateGradeLink(Gardengatelink link,Syslog syslog);

    public Map viewLink(Long linkid);

    public void delLink(Long linkid,Syslog syslog);

    /**
     * 根据当前园门查询该服务商下的其它园门
     * @param iscenicid 服务商ID
     * @param gateid 园门ID
     * @return
     */
    public List queryOtherGarden(Long iscenicid,Long gateid);
    
    /**
	 * 根据景区id获取园门下拉列表
	 */
    public List getGardenGateSelect(Long iscenicid);
}
