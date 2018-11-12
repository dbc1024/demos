/*package com.ectrip.system.afcset.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.model.afcset.Esbgardengatetab;
import com.ectrip.model.afcset.EsbgardengatetabId;
import com.ectrip.model.afcset.Gardengatelink;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Esbscenicareatab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.model.syspar.Sysparv5;
import com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService;
import com.ectrip.system.afcset.service.iservice.IEsbgardengatetabService;
import com.ectrip.system.provider.service.iservice.ITicketTypeService;
import com.ectrip.system.syspar.service.iservice.ISysparService;

*//**
 * Created BY Jzhenhua,Time 2011-09-27 09:46 ԰��Action
 *
 * @author lenovo
 *//*
@SuppressWarnings("serial")
public class EsbgardengatetabAction extends BaseAction {

    private IEsbgardengatetabService esbgardengatetabService;
    Esbscenicareatab provider;
    private Esbgardengatetab esbgardengatetab;
    private int pageSize = PaginationSupport.PAGESIZE;// ҳ��С

    private IEsbaccessequiptabService esbaccessequiptabService;  //׼���豸 ����
    private ITicketTypeService tickettypeService;
    private int page;
    private String url;// ��ҳurl
    PaginationSupport ps;// ��ҳ��
    private String result;
    private ISysparService sysparService;
    private Gardengatelink gardengatelink;
    private Long lgateid;
    private Map linkMap;

    public Gardengatelink getGardengatelink() {
        return gardengatelink;
    }

    public void setGardengatelink(Gardengatelink gardengatelink) {
        this.gardengatelink = gardengatelink;
    }

    public Esbscenicareatab getProvider() {
        return provider;
    }

    public void setProvider(Esbscenicareatab provider) {
        this.provider = provider;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public IEsbgardengatetabService getEsbgardengatetabService() {
        return esbgardengatetabService;
    }

    public void setEsbgardengatetabService(
            IEsbgardengatetabService esbgardengatetabService) {
        this.esbgardengatetabService = esbgardengatetabService;
    }

    public Esbgardengatetab getEsbgardengatetab() {
        return esbgardengatetab;
    }

    public void setEsbgardengatetab(Esbgardengatetab esbgardengatetab) {
        this.esbgardengatetab = esbgardengatetab;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PaginationSupport getPs() {
        return ps;
    }

    public void setPs(PaginationSupport ps) {
        this.ps = ps;
    }

    public void setSysparService(ISysparService sysparService) {
        this.sysparService = sysparService;
    }

    public Long getLgateid() {
        return lgateid;
    }

    public void setLgateid(Long lgateid) {
        this.lgateid = lgateid;
    }

    public Map getLinkMap() {
        return linkMap;
    }

    public void setLinkMap(Map linkMap) {
        this.linkMap = linkMap;
    }

    public void setTickettypeService(ITicketTypeService tickettypeService) {
        this.tickettypeService = tickettypeService;
    }

    public void setEsbaccessequiptabService(IEsbaccessequiptabService esbaccessequiptabService) {
        this.esbaccessequiptabService = esbaccessequiptabService;
    }

    *//**
     * ���԰���б�
     *
     * @return
     *//*
    public String gardenGateViewList() {
        StringBuffer sb = new StringBuffer(getRequest().getContextPath());

        try {
            // ��ȡ��ǰ��¼�û�
            Esfemployeetab employee = (Esfemployeetab) getRequest()
                    .getSession().getAttribute("employee");
            String scenics = "";
            if (employee.getCompanytype().equals("02")) {
                scenics = employee.getScenics();
                if (scenics == null || scenics.equals("")) {
                    this.addActionError(getText("esfemployeetab.scenics.required"));
                    return INPUT;
                }
            }

            // ��ȡ��ǰ��¼�û�������ķ�����
            List providers = this.esbaccessequiptabService.findListesbticket(scenics);

            if (provider == null || provider.getIscenicid() == null
                    || provider.getIscenicid().equals("")) {
                if (providers != null && providers.size() > 0) {
                    provider = (Esbscenicareatab) providers.get(0);
                }
            }

            if (page == 0) {
                page = 1;
            }

            getRequest().setAttribute("prdlist", providers);

            // ·���������
            if (null != esbgardengatetab && null != esbgardengatetab.getId()
                    && null != esbgardengatetab.getId().getIscenicid()
                    && esbgardengatetab.getId().getIscenicid() != 0
                    && !"".equals(esbgardengatetab.getId().getIscenicid())) {
                sb.append("?esbgardengatetab.id.iscenicid="
                        + esbgardengatetab.getId().getIscenicid());
            }
            if (null != esbgardengatetab
                    && null != esbgardengatetab.getIsgardengateid()
                    && 0 != esbgardengatetab.getIsgardengateid()
                    && !"".equals(esbgardengatetab.getIsgardengateid())) {
                sb.append("&esbgardengatetab.isgardengateid = "
                        + esbgardengatetab.getIsgardengateid());
            }

            this.url = sb.toString();

            // ��ȡ����԰����Ϣ
            this.ps = this.esbgardengatetabService.getGaredenGatePage(
                    this.esbgardengatetab, employee.getScenics(), pageSize,
                    page, url);

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    *//**
     * ���԰����Ϣ
     *
     * @return
     *//*
    public String addGardenGate() {
        strutsAction = ADD;

        try {
            // ��ȡ��ǰ��¼�û�
            Esfemployeetab employee = (Esfemployeetab) getRequest()
                    .getSession().getAttribute("employee");
            String scenics = "";
            if (employee.getCompanytype().equals("02")) {
                scenics = employee.getScenics();
                if (scenics == null || scenics.equals("")) {
                    this.addActionError(getText("esfemployeetab.scenics.required"));
                    return INPUT;
                }
            }

            // ��ȡ��ǰ��¼�û�������ķ�����
            List providers = this.esbaccessequiptabService.findListesbticket(scenics);

            if (provider == null || provider.getIscenicid() == null
                    || provider.getIscenicid().equals("")) {
                if (providers != null && providers.size() > 0) {
                    provider = (Esbscenicareatab) providers.get(0);
                }
            }

            if (this.esbgardengatetab == null) {
                this.esbgardengatetab = new Esbgardengatetab();
            }

            if (null != this.esbgardengatetab.getIsgardengateid()
                    && !"".equals(this.esbgardengatetab.getIsgardengateid())) {
                EsbgardengatetabId id = new EsbgardengatetabId();
                id.setIgardengateid(this.esbgardengatetab.getIsgardengateid());
                Esbgardengatetab objgarden = this.esbgardengatetabService
                        .getGardenGateById(id);

                this.esbgardengatetab.setParentname(objgarden
                        .getSzgardengatename());
            } else {
                this.esbgardengatetab.setIsgardengateid("0");
            }

            this.esbgardengatetab.setByisuse(new Long(1));
            this.esbgardengatetab.setByiscont(new Long(0));

            // ��ȡ�������б�
            getRequest().setAttribute("prdlist", providers);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    *//**
     * ��ת�޸�԰��
     *
     * @return
     * @throws Exception
     *//*
    public String editGardenGate() throws Exception {
        strutsAction = EDIT;
        try {
            if (this.esbgardengatetab.getId().getIgardengateid() == null
                    || "".equals(this.esbgardengatetab.getId()
                    .getIgardengateid())) {
                this.addActionError(getText("action.loaderror"));
                if (hasActionErrors()) {
                    return INPUT;
                }

            } else {

                if (null == esbgardengatetab
                        || null == esbgardengatetab.getId().getIscenicid()
                        || null == esbgardengatetab.getId().getIgardengateid()
                        || "".equals(esbgardengatetab.getId().getIscenicid())
                        || "".equals(esbgardengatetab.getId()
                        .getIgardengateid())) {
                    this.addActionError(getText("action.loaderror"));
                }

            }

            if (hasActionErrors()) {
                return INPUT;
            }

            // ��ȡ��ǰ��¼�û�
            Esfemployeetab employee = (Esfemployeetab) getRequest()
                    .getSession().getAttribute("employee");
            String scenics = "";
            if (employee.getCompanytype().equals("02")) {
                scenics = employee.getScenics();
                if (scenics == null || scenics.equals("")) {
                    this.addActionError(getText("esfemployeetab.scenics.required"));
                    return INPUT;
                }
            }

            // ��ȡ��ǰ��¼�û�������ķ�����
            //List providers = this.esbaccessequiptabService.findListesbticket(scenics);
            provider = (Esbscenicareatab) this.genericService.get(Esbscenicareatab.class, esbgardengatetab.getId().getIscenicid());
            List providers = new ArrayList();
            providers.add(provider);
            // ��ȡ�������б�
            getRequest().setAttribute("prdlist", providers);

            // ��ȡҪ�޸ĵļ�¼
            this.esbgardengatetab = esbgardengatetabService
                    .getGardenGateById(esbgardengatetab.getId());
            if (null != this.esbgardengatetab.getIsgardengateid()
                    && !"".equals(this.esbgardengatetab.getIsgardengateid())
                    && 0 != this.esbgardengatetab.getIsgardengateid()) {
                // ���ø�԰������
                EsbgardengatetabId id = new EsbgardengatetabId();
                id.setIgardengateid(this.esbgardengatetab.getIsgardengateid());
                Esbgardengatetab objgarden = this.esbgardengatetabService
                        .getGardenGateById(id);
                this.esbgardengatetab.setParentname(objgarden
                        .getSzgardengatename());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    *//**
     * ����޸�԰��
     *
     * @return
     *//*
    public String saveGardenGate() {
        try {
            // ��ȡ��ǰ��¼�û�
            Esfemployeetab employee = (Esfemployeetab) getRequest()
                    .getSession().getAttribute("employee");
            Syslog syslog = new Syslog();

            syslog.setIemployeeid(employee.getIemployeeid());
            syslog.setSzemployeename(employee.getSzemployeename());

            if (strutsAction == ADD) {
                this.esbgardengatetab.getId().setIgardengateid(
                        this.esbgardengatetabService.getMaxId());
                this.esbgardengatetabService.addGardenGate(esbgardengatetab,
                        syslog);
            } else if (strutsAction == EDIT) {
                this.esbgardengatetabService.updateGardenGate(esbgardengatetab,
                        syslog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    *//**
     * ɾ��԰��
     *
     * @return
     *//*
    public String delGardenGate() {
        try {

            // ��ȡ��ǰ��¼�û�
            Esfemployeetab employee = (Esfemployeetab) getRequest()
                    .getSession().getAttribute("employee");
            Syslog syslog = new Syslog();

            syslog.setIemployeeid(employee.getIemployeeid());
            syslog.setSzemployeename(employee.getSzemployeename());

            if (null == esbgardengatetab
                    || null == esbgardengatetab.getId().getIscenicid()
                    || null == esbgardengatetab.getId().getIgardengateid()
                    || "".equals(esbgardengatetab.getId().getIscenicid())
                    || "".equals(esbgardengatetab.getId().getIgardengateid())) {
                this.addActionError(getText("action.deleteError"));
            }

            if (hasActionErrors()) {
                return INPUT;
            }

            this.esbgardengatetabService.deleteGardenGate(
                    this.esbgardengatetab, syslog);

        } catch (Exception e) {
            this.addActionMessage(getText("garandengate.delete.error"));
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }

    *//**
     * ��תɾ��ҳ��
     *
     * @return
     * @throws Exception
     *//*
    public String preformDelGardenGate() throws Exception {
        strutsAction = DELETE;
        if (null == esbgardengatetab
                || null == esbgardengatetab.getId().getIscenicid()
                || null == esbgardengatetab.getId().getIgardengateid()
                || "".equals(esbgardengatetab.getId().getIscenicid())
                || "".equals(esbgardengatetab.getId().getIgardengateid())) {
            this.addActionError(getText("action.loaderror"));
        }

        if (hasActionErrors()) {
            return INPUT;
        }

        this.esbgardengatetab = this.esbgardengatetabService
                .getGardenGateById(esbgardengatetab.getId());

        if (null != this.esbgardengatetab.getIsgardengateid()
                && !"".equals(this.esbgardengatetab.getIsgardengateid())
                && 0 != this.esbgardengatetab.getIsgardengateid()) {
            // ���ø�԰������
            EsbgardengatetabId id = new EsbgardengatetabId();
            id.setIgardengateid(this.esbgardengatetab.getIsgardengateid());
            Esbgardengatetab objgarden = this.esbgardengatetabService
                    .getGardenGateById(id);
            this.esbgardengatetab.setParentname(objgarden
                    .getSzgardengatename());
        }

        return SUCCESS;
    }

    *//**
     * �鿴԰��
     *
     * @return
     *//*
    public String viewGardenGate() {
        strutsAction = VIEW;
        try {

            if (null == this.esbgardengatetab.getId()) {
                this.addActionError(getText("Action.loaderror"));
            }

            if (hasActionErrors()) {
                return INPUT;
            }

            this.esbgardengatetab = this.esbgardengatetabService
                    .getGardenGateById(this.esbgardengatetab.getId());

            if (null != this.esbgardengatetab.getIsgardengateid()
                    && !"".equals(this.esbgardengatetab.getIsgardengateid())
                    && 0 != this.esbgardengatetab.getIsgardengateid()) {
                // ���ø�԰������
                EsbgardengatetabId id = new EsbgardengatetabId();
                id.setIgardengateid(this.esbgardengatetab.getIsgardengateid());
                Esbgardengatetab objgarden = this.esbgardengatetabService
                        .getGardenGateById(id);
                this.esbgardengatetab.setParentname(objgarden
                        .getSzgardengatename());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    *//**
     * ��֤����
     *
     * @throws Exception
     *//*

    public void validateSaveGardenGate() throws Exception {
        if (esbgardengatetab == null) {
            this.addActionError(getText("esbgardengatetab.requuired"));
        } else {
            if (esbgardengatetab.getId().getIscenicid() == 0) {
                this
                        .addActionError(getText("esbgardengatetab.iscenicid.nosel"));
            }
            if (null == esbgardengatetab.getIsgardengateid()) {
                this
                        .addActionError(getText("esbgardengatetab.isgardengateid.null"));
            }

            if (null == esbgardengatetab.getSzgardengatecode()
                    || "".equals(esbgardengatetab.getSzgardengatecode().trim())) {
                this
                        .addActionError(getText("esbgardengatetab.szgardengatecode.null"));
            } else if (esbgardengatetab.getSzgardengatecode().trim().length() * 2 > 30) {
                this
                        .addActionError(getText("esbgardengatetab.szgardengatecode.solong"));
            }

            if (null == esbgardengatetab.getByisuse()
                    || "".equals(esbgardengatetab.getByisuse())) {
                this.addActionError(getText("esbgardengatetab.byisuse.null"));
            }

            if (null == esbgardengatetab.getSzgardengatename()
                    || "".equals(esbgardengatetab.getSzgardengatename().trim())) {
                this
                        .addActionError(getText("esbgardengatetab.szgardengatename.null"));
            } else if (esbgardengatetab.getSzgardengatename().trim().length() * 2 > 50) {
                this
                        .addActionError(getText("esbgardengatetab.szgardengatename.solong"));
            }

            if (null == esbgardengatetab.getBygardengateindex()) {
                this
                        .addActionError(getText("esbgardengatetab.bygardengateindex.null"));
            } else if (!Tools.validation(esbgardengatetab
                    .getBygardengateindex().toString(), Tools.ZHENG_INTEGER)) {
                this
                        .addActionError(getText("esbgardengatetab.bygardengateindex.nonum"));
            }

            if (null == esbgardengatetab.getSzaddress()
                    || "".equals(esbgardengatetab.getSzaddress().trim())) {

            } else if (esbgardengatetab.getSzaddress().length() * 2 > 200) {
                this
                        .addActionError(getText("esbgardengatetab.szaddress.solong"));
            }

            if (null == esbgardengatetab.getSzcontact()
                    || "".equals(esbgardengatetab.getSzcontact())) {

            } else if (esbgardengatetab.getSzcontact().length() * 2 > 50) {
                this
                        .addActionError(getText("esbgardengatetab.szcontact.solong"));
            }

            if (null == esbgardengatetab.getSzphone()
                    || "".equals(esbgardengatetab.getSzphone())) {

            } else if (!Tools.validation(esbgardengatetab.getSzphone()
                    .toString(), Tools.ZHENG_INTEGER)) {
                this.addActionError(getText("esbgardengatetab.szphone.nonum"));
            } else if (esbgardengatetab.getSzphone().toString().length() * 2 > 50) {
                this.addActionError(getText("esbgardengatetab.szphone.solong"));
            }

            if (esbgardengatetab.getSzmemo() != null && esbgardengatetab.getSzmemo().length() * 2 > 520) {
                this.addActionError(getText("esbgardengatetab.szmemo.solong"));
            }

            // ���ش��벻Ϊ�յ������
            if (esbgardengatetab.getSzgardengatecode() != null
                    && !"".equals(esbgardengatetab.getSzgardengatecode())) {
                // ���ֱ����֤���ݿ��Ƿ���ڸô���
                if (strutsAction == ADD) {
                    if (!this.genericService.volidateSole(
                            new String[]{"iscenicid"},
                            new Long[]{this.esbgardengatetab.getId()
                                    .getIscenicid()},
                            new String[]{"szgardengatecode"},
                            new String[]{esbgardengatetab
                                    .getSzgardengatecode()},
                            "Esbgardengatetab")) {
                        this
                                .addActionError(getText("esbgardengatetab.szgardengatecode.isexists"));
                    }
                } else if (strutsAction == EDIT) {
                    // �޸����ж��Ƿ�Ķ�������
                    Esbgardengatetab myvenue = this.esbgardengatetabService
                            .getGardenGateById(this.esbgardengatetab.getId());

                    if (!myvenue.getSzgardengatecode().equals(
                            this.esbgardengatetab.getSzgardengatecode())) {
                        if (!this.genericService.volidateSole(
                                new String[]{"iscenicid"},
                                new Long[]{this.esbgardengatetab.getId()
                                        .getIscenicid()},
                                new String[]{"szgardengatecode"},
                                new String[]{esbgardengatetab
                                        .getSzgardengatecode()},
                                "Esbgardengatetab")) {
                            this
                                    .addActionError(getText("esbgardengatetab.szgardengatecode.isexists"));
                        }
                    }
                }
            }

        }
    }

    public String showLinkList() {
        StringBuffer sb = new StringBuffer(getRequest().getContextPath());
        if (null != esbgardengatetab
                && null != esbgardengatetab.getId().getIgardengateid()
                && 0 != esbgardengatetab.getId().getIgardengateid()
                && !"".equals(esbgardengatetab.getId().getIgardengateid())) {
            sb.append("?esbgardengatetab.id.igardengateid = "
                    + esbgardengatetab.getIsgardengateid());
        } else {
            this.addActionError(getText("԰��ID����"));
            return INPUT;
        }
        this.url = sb.toString();
        if (page == 0) {
            page = 1;
        }
        ps = this.esbgardengatetabService.showLinkListByid(esbgardengatetab.getId().getIgardengateid(), pageSize, page, url);
        return SUCCESS;
    }

    public String bindGardenGate() throws Exception {
        this.strutsAction = ADD;
        // ��ȡ��ǰ��¼�û�
        Esfemployeetab employee = (Esfemployeetab) getRequest()
                .getSession().getAttribute("employee");
        String scenics = "";
        if (employee.getCompanytype().equals("02")) {
            scenics = employee.getScenics();
            if (scenics == null || scenics.equals("")) {
                this.addActionError(getText("esfemployeetab.scenics.required"));
                return INPUT;
            }
        }
        esbgardengatetab = this.esbgardengatetabService.getGardenGateById(new EsbgardengatetabId(esbgardengatetab.getId().getIgardengateid(), esbgardengatetab.getId().getIscenicid()));
        gardengatelink = new Gardengatelink();
        gardengatelink.setIsvalid(1L);
        List gardenGateList = this.esbgardengatetabService.queryOtherGarden(esbgardengatetab.getId().getIscenicid(), esbgardengatetab.getId().getIgardengateid());
        getRequest().setAttribute("gardenGateList", gardenGateList);
        //���Ź�������
        List linkTypeList = this.sysparService.retrieve("GDLK");
        getRequest().setAttribute("linkTypeList", linkTypeList);
        //��Ʒ�б�
        List productList = tickettypeService.getTickettypeListSelect(esbgardengatetab.getId().getIscenicid(),"01");
        getRequest().setAttribute("productList",productList);
        return SUCCESS;
    }

    public String updateGateLink() throws Exception {
        this.strutsAction = EDIT;
        Esfemployeetab employee = (Esfemployeetab) getRequest().getSession().getAttribute("employee");
        String scenics = "";
        if (employee.getCompanytype().equals("02")) {
            scenics = employee.getScenics();
            if (scenics == null || scenics.equals("")) {
                this.addActionError(getText("esfemployeetab.scenics.required"));
                return INPUT;
            }
        }
        gardengatelink = (Gardengatelink) this.genericService.get(Gardengatelink.class, gardengatelink.getLinkid());
        if (gardengatelink != null) {
            esbgardengatetab = this.esbgardengatetabService.getGardenGateById(new EsbgardengatetabId(gardengatelink.getIgardengateid(), gardengatelink.getIscenicid()));
        }
        List linkTypeList = this.sysparService.retrieve("GDLK");
        getRequest().setAttribute("linkTypeList", linkTypeList);
        List gardenGateList = this.esbgardengatetabService.queryOtherGarden(esbgardengatetab.getId().getIscenicid(), esbgardengatetab.getId().getIgardengateid());
        getRequest().setAttribute("gardenGateList", gardenGateList);
        //��Ʒ�б�
        List productList = tickettypeService.getTickettypeListSelect(esbgardengatetab.getId().getIscenicid(),"01");
        getRequest().setAttribute("productList",productList);
        return SUCCESS;
    }

    public String performDelGateLink() {
        this.strutsAction = DELETE;
        linkMap = this.esbgardengatetabService.viewLink(gardengatelink.getLinkid());
        return SUCCESS;
    }

    public String delGateLink() {
        Syslog syslog = new Syslog();
        Esfemployeetab employee = (Esfemployeetab) getRequest().getSession().getAttribute("employee");
        String scenics = "";
        if (employee.getCompanytype().equals("02")) {
            scenics = employee.getScenics();
            if (scenics == null || scenics.equals("")) {
                this.addActionError(getText("esfemployeetab.scenics.required"));
                return INPUT;
            }
        }
        this.esbgardengatetabService.delLink(gardengatelink.getLinkid(), syslog);
        return SUCCESS;
    }

    public String saveGardenGateLink() {
        Syslog syslog = new Syslog();
        List linkList = this.esbgardengatetabService.showGradeLinkList(esbgardengatetab.getId().getIgardengateid(), gardengatelink.getLigardengateid(),0L);
        if (linkList != null && linkList.size() > 0) {
            if (this.strutsAction == ADD) {
                this.addActionError(getText("��԰���ѹ������в�Ʒ!"));
            }
            if (this.strutsAction == EDIT) {
                if (gardengatelink.getLinkid() != ((Gardengatelink) linkList.get(0)).getLinkid()) {
                    this.addActionError(getText("��԰���ѹ������в�Ʒ!"));
                }
            }
        }else{
            if(gardengatelink.getInoteger1() != null && gardengatelink.getInoteger1() != 0L){
                linkList = this.esbgardengatetabService.showGradeLinkList(esbgardengatetab.getId().getIgardengateid(), gardengatelink.getLigardengateid(),gardengatelink.getInoteger1());
                if (linkList != null && linkList.size() > 0) {
                    if (this.strutsAction == ADD) {
                        this.addActionError(getText("԰���ѹ����ò�Ʒ!"));
                    }
                    if (this.strutsAction == EDIT) {
                        if (gardengatelink.getLinkid() != ((Gardengatelink) linkList.get(0)).getLinkid()) {
                            this.addActionError(getText("԰���ѹ����ò�Ʒ!"));
                        }
                    }
                }
            }else{
                linkList = this.esbgardengatetabService.showGradeLinkList(esbgardengatetab.getId().getIgardengateid(), gardengatelink.getLigardengateid(),null);
                if (linkList != null && linkList.size() > 0) {
                    this.addActionError(getText("԰���Ѵ��ڹ������ݣ������ٹ������в�Ʒ!"));
                }
            }
        }
        Esbgardengatetab gate = this.esbgardengatetabService.showGate(gardengatelink.getLigardengateid());
        if (gate == null) {
            this.addActionError(getText("��԰�Ų�����!"));
        }
        if (hasActionErrors()) {
            return INPUT;
        }
        gardengatelink.setIgardengateid(esbgardengatetab.getId().getIgardengateid());
        gardengatelink.setIscenicid(esbgardengatetab.getId().getIscenicid());
        gardengatelink.setLigardengateid(gate.getId().getIgardengateid());
        gardengatelink.setLiscenicid(gate.getId().getIscenicid());
        if (this.strutsAction == ADD) {
            this.esbgardengatetabService.addGradeLink(gardengatelink, syslog);
        }
        if (this.strutsAction == EDIT) {
            this.esbgardengatetabService.updateGradeLink(gardengatelink, syslog);
        }
        return SUCCESS;
    }

    public void validateSaveGardenGateLink() throws Exception {
        if (esbgardengatetab.getId().getIgardengateid() == null || esbgardengatetab.getId().getIgardengateid() == 0L) {
            this.addActionError(getText("��԰�Ų���Ϊ��!"));
        }
        if (gardengatelink.getLigardengateid() == null || gardengatelink.getLigardengateid() == 0L) {
            this.addActionError(getText("��ѡ����԰��!"));
        }
        if (gardengatelink.getLinktype() == null || "".equals(gardengatelink.getLinktype())) {
            this.addActionError(getText("�����ڹ�����ϵ!"));
        }
    }
}
*/