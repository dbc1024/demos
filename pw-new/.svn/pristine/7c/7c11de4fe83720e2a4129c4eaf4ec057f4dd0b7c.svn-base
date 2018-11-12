/*package com.ectrip.system.salesmanager.action;

import java.util.List;
import java.util.regex.Pattern;

import com.ectrip.base.action.BaseAction;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.WebContant;
import com.ectrip.model.employee.Esfemployeetab;
import com.ectrip.model.salesmanager.Opcemployeecardtab;
import com.ectrip.model.syspar.Syslog;
import com.ectrip.system.salesmanager.service.iservice.IOpcemployeecardtabService;
import com.ectrip.system.syspar.service.iservice.ISysparService;
import com.opensymphony.xwork2.ActionContext;

*//**
 * @author Jzhenhua,Created Time 2011-10-06
 *//*
public class OpcemployeecardtabAction extends BaseAction {

    private PaginationSupport ps;
    private int pageSize =PaginationSupport.PAGESIZE;
    private String url;
    private Opcemployeecardtab opcemployeecardtab;
    private IOpcemployeecardtabService opcemployeecardtabService;
    private ISysparService sysparService;   //ϵͳ����
    public String result;
    private String queryString;//��ѯ����
    private String page;//��ҳ��ȡҳ��
    private int flag;//��ѯ����
    private String errMsg;
    private List jsonlist;

    private String myzj;//���֤����

    *//**
     * ���ݴ��ݵ�Ա�����,��ȡ��Ա����ʹ�õ�Ա��������
     *
     * @return
     * @throws Exception
     *//*
    public String getAllTicketType() throws Exception {
        //��ȡҪ��ӵ�Ա�����
        Esfemployeetab employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, new Long(getRequest().getParameter("iemployeeid").toString()));
        //����Ա����ȡԱ����ҵ������������µ�Ա����
        result = opcemployeecardtabService.getListticketlookup(this.opcemployeecardtabService.getControlProvider(employee.getIemployeeid().toString()));
        return SUCCESS;
    }

    *//**
     * ��ȡ����Ա����֤
     *
     * @return
     *//*
    public String opcemployeecardViewList() {
        if(page==null||page.equals("")){
            page="1";
        }
        Esfemployeetab employee=(Esfemployeetab) getRequest().getSession().getAttribute("employee");
        url = getRequest().getContextPath() + getRequest().getServletPath()+"?flag="+flag;
        if(queryString!=null&&!queryString.equals("")){
            if(flag==0){
                Pattern p = Pattern.compile("^[0-9]+$");
                boolean b = p.matcher(queryString).matches();
                if(b==false){
                    this.addActionError(getText("Ա�����ֻ��Ϊ����"));//Ա�����ֻ��Ϊ����
                    return INPUT;
                }
            }
            url+="&queryString="+queryString;
        }
        this.ps = this.opcemployeecardtabService.getOpcemployeecard(flag,queryString,employee.getScenics(),employee.getIcompanyinfoid().toString(),
                pageSize, Integer.parseInt(page),url);
        return SUCCESS;
    }

    *//**
     * ���
     *
     * @return
     *//*
    public String addPloyeeCard() {
        strutsAction = ADD;
//		//��֤״̬
//		getRequest().setAttribute("kpList", this.sysparService.retrieve("KZZT"));
        //��������
//		getRequest().setAttribute("xfList", this.sysparService.retrieve("XFLX"));

        opcemployeecardtab=new Opcemployeecardtab();
        opcemployeecardtab.setByticketstate("0");
        opcemployeecardtab.setByconsumetype("00");
        opcemployeecardtab.setIagentno("1");
        opcemployeecardtab.setIserialnum("0");
        opcemployeecardtab.setIversion(1L);
        opcemployeecardtab.setByconsumetype("0");

        return SUCCESS;
    }

    *//**
     * �޸�
     *
     * @return
     *//*
    public String editPloyeeCard() {
        strutsAction = EDIT;
        try {
            this.opcemployeecardtab = this.opcemployeecardtabService.getPloyeeCardById(this.opcemployeecardtab
                    .getIemployeecardid());
            //		Esfemployeetab employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, opcemployeecardtab.getIemployeeid());
            //����Ա����ȡԱ����ҵ������������µ�Ա����
            //		resultlist = opcemployeecardtabService.getAllTicketType(this.opcemployeecardtabService.getControlProvider(employee.getIemployeeid().toString()));
            //��֤״̬
//			getRequest().setAttribute("kpList", this.sysparService.retrieve("KZZT"));
            //��������
//			getRequest().setAttribute("xfList", this.sysparService.retrieve("XFLX"));
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    *//**
     * ����
     *
     * @return
     *//*
    public String savePloyeeCard() {

        if (strutsAction == ADD) {
            this.opcemployeecardtab.setIemployeecardid(this.opcemployeecardtabService
                    .getMaxId());
            this.opcemployeecardtab.setIcardno(this.opcemployeecardtab.getIcardno().toUpperCase());
            this.opcemployeecardtabService
                    .addOpcemployeecard(opcemployeecardtab);
        } else if (strutsAction == EDIT) {
            this.opcemployeecardtab.setIcardno(this.opcemployeecardtab.getIcardno().toUpperCase());
            this.opcemployeecardtabService
                    .updateOpcemlopyeecard(opcemployeecardtab);
        }
        return SUCCESS;
    }

    *//**
     * ��ת��ɾ��ҳ��
     *
     * @return
     *//*
    public String preFromDelPloyeeCard() throws Exception{
        strutsAction = DELETE;
        this.opcemployeecardtab = this.opcemployeecardtabService.getPloyeeCardById(this.opcemployeecardtab.getIemployeecardid());

        return SUCCESS;
    }

    *//**
     * ��ת���޸�ҳ��
     *
     * @return
     * @throws Exception
     *//*
    public String viewPloyeeCard() throws Exception {
        strutsAction = VIEW;
        this.opcemployeecardtab = this.opcemployeecardtabService
                .getPloyeeCardById(this.opcemployeecardtab
                        .getIemployeecardid());
        return SUCCESS;
    }

    *//**
     * ɾ��
     *
     * @return
     *//*
    public String delPloyeeCard() {
        opcemployeecardtab=(Opcemployeecardtab) this.genericService.get(Opcemployeecardtab.class, opcemployeecardtab.getIemployeecardid());
        this.opcemployeecardtabService
                .delOpcemployeecard(opcemployeecardtab);
        return SUCCESS;

    }


    *//**
     * ��֤
     *//*
    public void validateSavePloyeeCard() {
        if (this.opcemployeecardtab == null) {
            this.addActionError(getText("opcemployeecardtab.required"));
        } else {
            if (null == opcemployeecardtab.getIemployeeid()
                    || "".equals(opcemployeecardtab.getIemployeeid())) {
                this.addActionError(getText("Ա����Ų���Ϊ��"));
            }

			if (null == opcemployeecardtab.getItickettypeid()
					|| "".equals(opcemployeecardtab.getItickettypeid())) {
				this.addActionError(getText("��ѡ����ȷ��Ʊ����"));
			}

            if (null == opcemployeecardtab.getIcardno()
                    || "".equals(opcemployeecardtab.getIcardno().trim())) {
                this.addActionError(getText("�����뿨��"));
            }

            if (null == opcemployeecardtab.getByticketstate()
                    || "".equals(opcemployeecardtab.getByticketstate())) {
                this.addActionError(getText("��ѡ��֤״̬"));
            }

            if(opcemployeecardtab.getIagentno()==null||opcemployeecardtab.getIagentno().equals("")){
                this.addActionError(getText("��ѡ���Ƿ����״̬"));
            }

            if(opcemployeecardtab.getIserialnum()==null||opcemployeecardtab.getIserialnum().equals("")){
                this.addActionError(getText("��ѡ���Ƿ���ܿ�״̬"));
            }

            if(opcemployeecardtab.getByconsumetype()==null||opcemployeecardtab.getByconsumetype().equals("")){
                this.addActionError(getText("��ѡ���Ƿ���԰��Ϣ"));
            }

            if(opcemployeecardtab.getByconsumetype()!=null&&!opcemployeecardtab.getByconsumetype().equals("")&&opcemployeecardtab.getIserialnum()!=null&&!opcemployeecardtab.getIserialnum().equals("")){
                if(opcemployeecardtab.getByconsumetype().equals(opcemployeecardtab.getIserialnum())&&opcemployeecardtab.getIserialnum().equals("1")){
                    this.addActionError(getText("��Ա��������ͬΪ��˿�����԰��"));
                }
//				if(opcemployeecardtab.getByconsumetype().equals(opcemployeecardtab.getIserialnum())&&opcemployeecardtab.getIserialnum().equals("0")){
//					this.addActionError(getText("��Ա����ֻ������˿�������԰��"));
//				}
            }

            if(opcemployeecardtab.getIagentno().equals("0")&&opcemployeecardtab.getByconsumetype().equals("0")&&opcemployeecardtab.getIserialnum().equals("0")){
                this.addActionError(getText("��Ա����������һ��Ȩ��(���С���ˡ���԰)!"));
            }

            if(null==opcemployeecardtab.getIversion()||"".equals(opcemployeecardtab.getIversion())){
                this.addActionError(getText("��ѡ��֤����!"));
            }
            //�ж���ѡ��֤���Ͷ�Ӧ���Ź����Ƿ���ȷ
            if(opcemployeecardtab.getIcardno()!=null&&!opcemployeecardtab.getIcardno().equals("")&&opcemployeecardtab.getIversion()!=null&&!opcemployeecardtab.getIversion().equals("")){
                if(opcemployeecardtab.getIversion()==1){
                    Pattern p = Pattern.compile("^\\d{17}([0-9]|x|X)$");
                    boolean b = p.matcher(opcemployeecardtab.getIcardno()).matches();
                    if(b==false){
                        this.addActionError(getText("���֤�Ÿ�ʽ�д�"));//���֤�Ÿ�ʽ�д���������ȷ�����֤��
                    }
                }else if(opcemployeecardtab.getIversion()==2){
                    if(opcemployeecardtab.getIcardno().equals("EMPLOYEE")||opcemployeecardtab.getIcardno().equals("employee")){
                        this.addActionError(getText("IC����ʽ��EMPLOYEEΪǰ׺�Ҵ���8λ"));
                    }
                    Pattern p1 = Pattern
                            .compile("(^([0-9]|[a-zA-Z]){1,14})$");
                    boolean c = p1.matcher(opcemployeecardtab.getIcardno()).matches();
                    if(opcemployeecardtab.getIcardno().length()>=8){
                        String icardno=opcemployeecardtab.getIcardno().substring(0,8);
                        if (c== false||!("EMPLOYEE".equals(icardno)||"employee".equals(icardno))) {
                            this.addActionError(getText("IC����ʽ�д�"));//IC����ʽ�д���������ȷ��IC����
                        }
                    }else{
                        this.addActionError(getText("IC����ʽ�д�"));//IC����ʽ�д���������ȷ��IC����
                    }
                }else if(opcemployeecardtab.getIversion()==3){
                    if(opcemployeecardtab.getIcardno().equals("EMPLOYEE")||opcemployeecardtab.getIcardno().equals("employee")){
                        this.addActionError(getText("IC����ʽ��EMPLOYEEΪǰ׺�Ҵ���8λ"));
                    }
                    Pattern p1 = Pattern
                            .compile("(^([0-9]|[a-zA-Z]){1,14})$");
                    boolean c = p1.matcher(opcemployeecardtab.getIcardno()).matches();
                    if(opcemployeecardtab.getIcardno().length()>=8){
                        String icardno=opcemployeecardtab.getIcardno().substring(0,8);
                        if (c== false||!("EMPLOYEE".equals(icardno)||"employee".equals(icardno))) {
                            this.addActionError(getText("�����ʽ�д�"));//�����ʽ�д���������ȷ�������
                        }
                    }else{
                        this.addActionError(getText("�����ʽ�д�"));//�����ʽ�д���������ȷ�������
                    }
                }
            }

            if (null != opcemployeecardtab.getIemployeeid()
                    && !"".equals(opcemployeecardtab
                    .getIemployeeid())) {
                if (opcemployeecardtabService.getPloyee(opcemployeecardtab
                        .getIemployeeid())) {
                    this.addActionError(getText("��Ա��������"));
                }

                if(this.strutsAction==ADD){
                    //�ж�ĳ��Ա���Ƿ������Ա����֤
                    if(opcemployeecardtabService.shoplookemployee(opcemployeecardtab.getIemployeeid())){
                        this.addActionError(getText("��Ա���Ѿ������Ա����֤��Ϣ."));
                    }
                }

            }

            if(opcemployeecardtab.getIcardno()!=null&&!opcemployeecardtab.getIcardno().equals("")){
                if(this.strutsAction==ADD){
                    boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"icardno"}, new String[]{opcemployeecardtab.getIcardno()}, "Opcemployeecardtab");
                    if(!isuse){
                        this.addActionError(getText("�����Ѵ���,����������!"));
                    }
                }else if(this.strutsAction==EDIT){
                    Opcemployeecardtab opcemployee=(Opcemployeecardtab)this.genericService.get(Opcemployeecardtab.class, opcemployeecardtab.getIemployeecardid());
                    if(!opcemployee.getIcardno().equals(opcemployeecardtab.getIcardno())){
                        boolean isuse = this.genericService.volidateSole(new String[]{},new Long[]{},new String[]{"icardno"}, new String[]{opcemployeecardtab.getIcardno()}, "Opcemployeecardtab");
                        if(!isuse){
                            this.addActionError(getText("�����Ѵ���,����������!"));
                        }
                    }

                }

            }



        }
    }

    *//**
     *
     * Describe:ɾ�� Ա����֤�е�Ա��ָ����Ϣ
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-2-11
     *//*
    public String checkListCardtab(){
        List lst=this.opcemployeecardtabService.checkOpcemployee();
        getRequest().setAttribute("lst", lst);

        return SUCCESS;
    }

    *//**
     *
     * Describe:ɾ��Ա��ָ��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-2-11
     *//*
    public String lookDelprint(){
        if(opcemployeecardtab==null||opcemployeecardtab.getIemployeecardid()==null||opcemployeecardtab.getIemployeecardid().equals("")){
            this.addActionError(getText("��ѡ��Ա��!"));
            return INPUT;
        }

        opcemployeecardtabService.delOpcemployeePrint(opcemployeecardtab.getIemployeecardid());
        this.addActionMessage(getText("Ա����ָ֤�Ƴ�ʼ���ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
        return SUCCESS;
    }

    *//**
     *
     * Describe:ɾ��������ָ֤��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-3-13
     *//*
    public String delFingerprint(){

        if (myzj == null || myzj.equals("")) {
            this.addActionError(getText("errors.employee.szcardid.required"));
            return INPUT;
        } else {
            if (myzj != null
                    && !"".equals(myzj)) {
                Pattern p1 = Pattern
                        .compile("^\\d{14}(\\d{1}|(\\d{3})([0-9]|X|x))$");

                boolean c = p1.matcher(myzj).matches();
                if (c == false) {
                    this.addActionError(getText("error.custom.zjhm.duplicate"));// ���֤�Ÿ�ʽ�д���������ȷ�����֤��
                    return INPUT;
                }

            }
        }

        opcemployeecardtabService.delStssoldPrint(myzj);
        this.addActionMessage(getText("������ָ֤�Ƴ�ʼ���ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);

        return SUCCESS;
    }

    *//**
     *
     * Describe:VIP��֤����  ��ʾ����VIP��֤��Ϣ
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-11-21
     *//*
    public String showListEmployeeCard(){
        url = getRequest().getContextPath() + getRequest().getServletPath();
        if (page == null || page.equals("")) {
            page = "1";
        }
        Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext().getSession().get("employee");
        String scenics = "";
        if (esfemployeetab.getCompanytype().equals("02")) {
            scenics = esfemployeetab.getScenics();
            if(scenics==null||scenics.equals("")){
                this.addActionError(getText("esfemployeetab.scenics.required"));
                return INPUT;
            }
        }

        url = getRequest().getContextPath() + getRequest().getServletPath()+"?flag="+flag;
        url+="&queryString="+queryString;

        ps=opcemployeecardtabService.checkListEmployeeCard(String.valueOf(flag), queryString, pageSize, Integer.parseInt(page), url);

        return SUCCESS;
    }

    *//**
     *
     * Describe:����VIP��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-11-21
     *//*
    public String preformAddEmployeeCard(){
        this.strutsAction=ADD;

        getRequest().setAttribute("typeList", this.sysparService.retrieve("YHLX"));
        opcemployeecardtab=new Opcemployeecardtab();
        opcemployeecardtab.setByticketstate("0");
        opcemployeecardtab.setEmptype("01");
        opcemployeecardtab.setJpnumbs(0L);

        return SUCCESS;
    }

    *//**
     *
     * Describe:�޸�VIP��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-11-21
     *//*
    public String preformEditEmployeeCard(){
        this.strutsAction=EDIT;

        getRequest().setAttribute("typeList", this.sysparService.retrieve("YHLX"));

        opcemployeecardtab=(Opcemployeecardtab)this.genericService.get(Opcemployeecardtab.class, opcemployeecardtab.getIemployeecardid());
        opcemployeecardtab.setIcardno(opcemployeecardtab.getIcardno().substring(3));

        return SUCCESS;
    }

    *//**
     *
     * Describe:ɾ��VIP��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-11-21
     * @throws Exception
     *//*
    public String preformDelEmployeeCard() throws Exception{
        this.strutsAction=DELETE;
        opcemployeecardtab=opcemployeecardtabService.viewVipCardemp(opcemployeecardtab.getIemployeecardid());
        return SUCCESS;
    }

    *//**
     *
     * Describe:�鿴VIP��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-11-21
     * @throws Exception
     *//*
    public String preformViewEmployeeCard() throws Exception{
        this.strutsAction=VIEW;
        opcemployeecardtab=opcemployeecardtabService.viewVipCardemp(opcemployeecardtab.getIemployeecardid());
        return SUCCESS;
    }

    *//**
     *
     * Describe:����VIP��
     * @author:lijingrui
     * @return
     * return:String
     * Date:2015-11-21
     *//*
    public String saveEmployeeCard(){
        Syslog syslog = new Syslog();
        if (ActionContext.getContext().getSession().get("employee") == null
                || "".equals(ActionContext.getContext().getSession().get("employee"))) {
            return "login";
        }
        Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
                .getSession().get("employee");
        syslog.setIemployeeid(esfemployeetab.getIemployeeid());

        if(this.strutsAction==ADD||this.strutsAction==EDIT){
            if(opcemployeecardtab.getSzemployname()==null||opcemployeecardtab.getSzemployname().equals("")){
                this.addActionError(getText("�û����Ʋ���Ϊ��!"));
            }

            if(opcemployeecardtab.getIcardno()==null||opcemployeecardtab.getIcardno().trim().equals("")){
                this.addActionError(getText("���Ų���Ϊ��!"));
            }

            if(opcemployeecardtab.getJpnumbs()==null||opcemployeecardtab.getJpnumbs().equals("")){
                this.addActionError(getText("��Ʊ��������Ϊ��!"));
            }

            if(opcemployeecardtab.getJpnumbs()!=null&&!opcemployeecardtab.getJpnumbs().equals("")){
                Pattern p = Pattern.compile("^[0-9]*$");
                boolean b = p.matcher(opcemployeecardtab.getJpnumbs().toString()).matches();
                if (b == false) {
                    this.addActionError(getText("��Ʊ����Ϊ����!"));
                }
            }

            if (opcemployeecardtab.getDtstartdate() == null
                    || "".equals(opcemployeecardtab.getDtstartdate())) {
                this.addActionError(getText("error.startdate.required"));// �������ڲ���Ϊ��
            }
            if (opcemployeecardtab.getDtenddate() == null
                    || "".equals(opcemployeecardtab.getDtenddate())) {
                this.addActionError(getText("error.enddate.required"));// �������ڲ���Ϊ��
            }
            if (opcemployeecardtab.getDtstartdate() != null
                    && !"".equals(opcemployeecardtab.getDtstartdate())
                    && opcemployeecardtab.getDtenddate() != null
                    && !"".equals(opcemployeecardtab.getDtenddate())) {

                // У��ʱ���ʽ
                if (opcemployeecardtab.getDtstartdate() != null
                        && !"".equals(opcemployeecardtab.getDtstartdate())) {
                    Pattern p = Pattern
                            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
                    boolean b = p.matcher(opcemployeecardtab.getDtstartdate())
                            .matches();
                    if (b == false) {
                        this.addActionError(getText("error.startdate"));// ��ʼ���������д���������yyyy-mm-dd��ʽ����Ч����
                    }
                }
                if (opcemployeecardtab.getDtenddate() != null
                        && !"".equals(opcemployeecardtab.getDtenddate())) {
                    Pattern p = Pattern
                            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
                    boolean b = p.matcher(opcemployeecardtab.getDtenddate())
                            .matches();
                    if (b == false) {
                        this.addActionError(getText("error.enddate"));// ��ֹ���������д���������yyyy-mm-dd��ʽ����Ч����
                    }
                }

                // �ж���ֹ���ڵĴ�С
                if (opcemployeecardtab.getDtstartdate().compareTo(opcemployeecardtab.getDtenddate()) > 0) {
                    this.addActionError(getText("error.startdate.big.enddate"));// ��ʼ���ڲ��ܴ��ڽ�ֹ����
                    return INPUT;
                }

            }
        }

        if(this.strutsAction==ADD){
            if (!this.genericService.volidateSole(new String[] {},new Long[] {},new String[] { "icardno" },new String[] { this.opcemployeecardtab.getIcardno().trim() },
                    "Opcemployeecardtab")) {
                this.addActionError(getText("�����Ѵ���!"));
            }

            this.opcemployeecardtabService.insertVipCardemp(opcemployeecardtab, syslog);
            this.addActionMessage(getText("VIP��֤��Ϣ�����ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
        }
        if(this.strutsAction==EDIT){
            Opcemployeecardtab opc=(Opcemployeecardtab)this.genericService.get(Opcemployeecardtab.class, opcemployeecardtab.getIemployeecardid());
            if(!opc.getIcardno().trim().equals(opcemployeecardtab.getIcardno().trim())){
                if (!this.genericService.volidateSole(new String[] {},new Long[] {},new String[] { "icardno" },new String[] { this.opcemployeecardtab.getIcardno().trim() },"Opcemployeecardtab")) {
                    this.addActionError(getText("�����Ѵ���!"));
                }
            }

            this.opcemployeecardtabService.editVipCardemp(opcemployeecardtab, syslog);
            this.addActionMessage(getText("VIP��֤��Ϣ�޸ĳɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
        }
        if(this.strutsAction==DELETE){
            this.opcemployeecardtabService.delVipCardemp(opcemployeecardtab.getIemployeecardid(), syslog);
            this.addActionMessage(getText("VIP��֤��Ϣɾ���ɹ�")+"����ӭʹ��"+WebContant.DOMAINAME);
        }

        return SUCCESS;
    }

    public PaginationSupport getPs() {
        return ps;
    }

    public void setPs(PaginationSupport ps) {
        this.ps = ps;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Opcemployeecardtab getOpcemployeecardtab() {
        return opcemployeecardtab;
    }

    public void setOpcemployeecardtab(Opcemployeecardtab opcemployeecardtab) {
        this.opcemployeecardtab = opcemployeecardtab;
    }

////	public IOpcemployeecardtabService getOpcemployeecardtabService() {
////		return opcemployeecardtabService;
////	}
//
//	public void setOpcemployeecardtabService(
//			IOpcemployeecardtabService opcemployeecardtabService) {
//		this.opcemployeecardtabService = opcemployeecardtabService;
//	}

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    public List getJsonlist() {
        return jsonlist;
    }

    public void setJsonlist(List jsonlist) {
        this.jsonlist = jsonlist;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setOpcemployeecardtabService(IOpcemployeecardtabService opcemployeecardtabService) {
        this.opcemployeecardtabService = opcemployeecardtabService;
    }

    public ISysparService getSysparService() {
        return sysparService;
    }

    public void setSysparService(ISysparService sysparService) {
        this.sysparService = sysparService;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public IOpcemployeecardtabService getOpcemployeecardtabService() {
        return opcemployeecardtabService;
    }

    public String getMyzj() {
        return myzj;
    }

    public void setMyzj(String myzj) {
        this.myzj = myzj;
    }

}
*/