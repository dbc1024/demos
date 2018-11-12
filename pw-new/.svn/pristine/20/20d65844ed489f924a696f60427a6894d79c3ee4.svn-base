package com.ectrip.ticket.afcset.dao;

import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.afcset.EsbgardengatetabId;
import com.ectrip.ticket.model.afcset.Gardengatelink;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

/**
 * Created By Jzhenhua Time:2011-09-26 18:29
 * @author lenovo
 */
public interface IEsbgardengatetabDAO extends IGenericDao{

    /**
     * Created By Jzhenhua Time:2011-09-26 18:33
     * 添加园门
     * @param esbgardenatetad
     */
    public void addGardenGate(Esbgardengatetab esbgardenatetad,Syslog syslog) ;

    /**
     * Created By Jzhenhua Time:2011-09-26 18:34
     * 删除园门
     * @param esbgardenatetad
     */
    public void delGardenGate(Esbgardengatetab esbgardenatetad,Syslog syslog) ;

    /**
     * Created By Jzhenhua Time:2011-09-26 18:36
     * @param esbgardenatetad
     */
    public void updateGardenGate(Esbgardengatetab esbgardenatetad,Syslog syslog) ;

    /**
     * Created By Jzhenhua Time:2011-09-27 09:17
     * 园门分页
     * @return
     */
    public PaginationSupport getGaredenGatePage(Long providerId,Long isgardengateid,String fws,int pageSize,int startIndex,String url);

    /**
     * Created By Jzhenhua Time:2011-09-27 08:56
     * 根据园门编号查询园门
     * @param esbgardengatebId
     * @return
     */
//    public Esbgardengatetab getGardenGateById(EsbgardengatetabId id);
    public Esbgardengatetab getGardenGateById(Long igardengateid);

    /**
     * 获得最大ID
     * @param iscenicid
     * @return 返回在最大ID+1的园门ID
     */
    public Long getMaxId() throws Exception;

    /**
     * 根据园门代码来获取数据
     * @param szgardengatecode
     * @return
     */
    public List getSzgardengatecode(String szgardengatecode);

    public void addEsbscenicareatab(Esbscenicareatab e);
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
