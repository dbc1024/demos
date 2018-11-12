/*package com.ectrip.system.afcset.action;

import java.util.List;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.model.afcset.Esbgardengatetab;
import com.ectrip.model.afcset.EsbgardengatetabId;
import com.ectrip.model.afcset.Esbgardengatetickettab;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.provider.Edmtickettypetab;
import com.ectrip.model.provider.Esbscenicareatab;
import com.ectrip.system.afcset.service.iservice.IEsbaccessequiptabService;
import com.ectrip.system.afcset.service.iservice.IEsbgardengateTicketService;
import org.apache.commons.lang.StringUtils;

*//**
 * ԰��ͬ��Ʊ
 *
 * @author luoxin
 *//*
public class EsbgardengateTicketAction extends BaseAction {

    *//**
     * EsbgardengateTicketAction.java
     * luoxin
     * 20152015-8-28
     *//*
    private static final long serialVersionUID = 1L;
    private IEsbgardengateTicketService esbgardengateTicketService;//԰��ͬ��Ʊ
    private IEsbaccessequiptabService esbaccessequiptabService;//׼���豸 ����
    private Esbgardengatetickettab gardengateticket;//԰��ͬ��Ʊ
    private Esbgardengatetab esbgardengatetab;//԰�ű�
    private Esbscenicareatab provider;
    private Edmtickettypetab product;

    private int page;
    private String url;// ��ҳurl
    private PaginationSupport ps;// ��ҳ��
    private int pageSize = PaginationSupport.PAGESIZE;// ҳ��С

    public Esbscenicareatab getProvider() {
        return provider;
    }

    public void setProvider(Esbscenicareatab provider) {
        this.provider = provider;
    }

    public Edmtickettypetab getProduct() {
        return product;
    }

    public void setProduct(Edmtickettypetab product) {
        this.product = product;
    }

    public Esbgardengatetickettab getGardengateticket() {
        return gardengateticket;
    }

    public void setGardengateticket(Esbgardengatetickettab gardengateticket) {
        this.gardengateticket = gardengateticket;
    }

    public void setEsbgardengateTicketService(
            IEsbgardengateTicketService esbgardengateTicketService) {
        this.esbgardengateTicketService = esbgardengateTicketService;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PaginationSupport getPs() {
        return ps;
    }

    public void setPs(PaginationSupport ps) {
        this.ps = ps;
    }

    public void setEsbaccessequiptabService(
            IEsbaccessequiptabService esbaccessequiptabService) {
        this.esbaccessequiptabService = esbaccessequiptabService;
    }

    public Esbgardengatetab getEsbgardengatetab() {
        return esbgardengatetab;
    }

    public void setEsbgardengatetab(Esbgardengatetab esbgardengatetab) {
        this.esbgardengatetab = esbgardengatetab;
    }

    *//**
     * ԰��ͬ��Ʊ����
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String gardenGateTicketViewList() {
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

        getRequest().setAttribute("prdlist", providers);

        return SUCCESS;
    }

    *//**
     * ԰��ͬ��Ʊ��ҳ�б�
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String showGardenGateTicketViewList() {
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
//		List providers = this.esbaccessequiptabService.findListesbticket(scenics);
//		
//		if (provider == null || provider.getIscenicid() == null
//				|| provider.getIscenicid().equals("")) {
//			if (providers != null && providers.size() > 0) {
//				provider = (Esbscenicareatab) providers.get(0);
//			}
//		}
//
//		getRequest().setAttribute("prdlist", providers);

        if (null == gardengateticket || null == gardengateticket.getIgardengateid() || "".equals(gardengateticket.getIgardengateid())) {
            this.addActionError(getText("��ѡ��԰�ţ�"));
            return SUCCESS;
        }

        url = getRequest().getContextPath() + getRequest().getServletPath() + "?gardengateticket.igardengateid=" + gardengateticket.getIgardengateid();
        if (page == 0) {
            page = 1;
        }

        ps = this.esbgardengateTicketService.showGardenGateTicketViewList(gardengateticket.getIgardengateid(), pageSize, page, url);

        return SUCCESS;
    }

    *//**
     * ����԰��ͬ��Ʊ
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String addGardenGateTicket() {
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
        strutsAction = 1;
        esbgardengatetab = (Esbgardengatetab) this.genericService.get(Esbgardengatetab.class, esbgardengatetab.getId());

        return SUCCESS;
    }

    *//**
     * �޸�԰��ͬ��Ʊ
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String editGardenGateTicket() throws Exception {
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
        strutsAction = 2;
        gardengateticket = this.esbgardengateTicketService.getGardenGateTicket(gardengateticket.getSeq());
        return SUCCESS;
    }

    *//**
     * ɾ��԰��ͬ��Ʊ
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String delGardenGateTicket() throws Exception {
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

        strutsAction = 3;

        gardengateticket = this.esbgardengateTicketService.getGardenGateTicket(gardengateticket.getSeq());

        return SUCCESS;
    }

    *//**
     * �鿴԰��ͬ��Ʊ
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String viewGardenGateTicket() throws Exception {
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

        strutsAction = 4;

        gardengateticket = this.esbgardengateTicketService.getGardenGateTicket(gardengateticket.getSeq());

        return SUCCESS;
    }

    *//**
     * ��ȡ��Ʒ
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String chooseTicket() {
        if (page == 0) {
            page = 1;
        }
        url = "/afcset/chooseTicket.action?product.iscenicid=" + product.getIscenicid();
        if(product.getByusage() != null){
            url += "&product.byusage=" + product.getByusage();
        }
        if(!StringUtils.isBlank(product.getBycategorytype())){
            url += "&product.bycategorytype=" + product.getBycategorytype();
        }
        if(!StringUtils.isBlank(product.getBymaketicketway())){
            url += "&product.bymaketicketway=" + product.getBymaketicketway();
        }
        if(!StringUtils.isBlank(product.getSztickettypename())){
            url += "&product.sztickettypename=" + product.getSztickettypename();
        }
        this.ps = this.esbgardengateTicketService.queryTicket(product.getIscenicid(), product.getBycategorytype(), product.getByusage(), product.getSztickettypename(), pageSize, page, url);
        return SUCCESS;
    }

    *//**
     * ����԰��ͬ��Ʊ У��
     * Describe:
     *
     * @author:luoxin return:void
     * Date:2015-8-28
     *//*
    public void validateSaveGardenGateTicket() {
        if (strutsAction == ADD || strutsAction == EDIT) {
            if (null == gardengateticket) {
                this.addActionError(getText("԰��ͬ��Ʊ�����쳣���뷵��������ӣ�"));
            }
            if (null == gardengateticket.getIgardengateid() || "".equals(gardengateticket.getIgardengateid())) {
                this.addActionError(getText("��ѡ��԰�ţ�"));
            } else if (null == gardengateticket.getItickettypeoneid() || "".equals(gardengateticket.getItickettypeoneid())) {
                this.addActionError(getText("��ѡ��ͬ���Ʒ��"));
            } else if (null == gardengateticket.getItickettypetwoid() || "".equals(gardengateticket.getItickettypetwoid())) {
                this.addActionError(getText("��ѡ��ͬ���Ʒ��"));
            } else if (gardengateticket.getItickettypeoneid() == gardengateticket.getItickettypetwoid()) {
                this.addActionError(getText("ͬ���Ʒ����Ϊͬһ��Ʒ��"));
            } else {
                if (strutsAction == EDIT && (null == gardengateticket.getSeq() || "".equals(gardengateticket.getSeq()))) {
                    this.addActionError(getText("԰��ͬ��Ʊ��Ų���Ϊ�գ�"));
                } else {
                    List gtls = this.esbgardengateTicketService.searchGardengateTicket(gardengateticket.getSeq(), gardengateticket.getIgardengateid(), gardengateticket.getItickettypeoneid(), gardengateticket.getItickettypetwoid());
                    if (null != gtls && !gtls.isEmpty()) {
                        this.addActionError(getText("��Ʒ����" + gardengateticket.getSztickettypeonename() + "���� ��" + gardengateticket.getSztickettypetwoname() + "���ѱ���԰�����Ϊͬ���Ʒ�������ظ���ӣ�"));
                    }
                }
            }
        }
        if (strutsAction == DELETE && (null == gardengateticket.getSeq() || "".equals(gardengateticket.getSeq()))) {
            this.addActionError(getText("԰��ͬ��Ʊ��Ų���Ϊ�գ�"));
        }
    }

    *//**
     * ����԰��ͬ��Ʊ
     * Describe:
     *
     * @return return:String
     * Date:2015-8-28
     * @author:luoxin
     *//*
    public String saveGardenGateTicket() throws Exception {
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

        if (strutsAction == ADD) {
            this.esbgardengateTicketService.saveGardenGateTicket(gardengateticket);
        }
        if (strutsAction == EDIT) {
            this.esbgardengateTicketService.updateGardenGateTicket(gardengateticket);
        }
        if (strutsAction == DELETE) {
            gardengateticket = (Esbgardengatetickettab) this.genericService.get(Esbgardengatetickettab.class, gardengateticket.getSeq());
            this.genericService.delete(gardengateticket);
        }
        return SUCCESS;
    }

}

*/