package com.ectrip.sys.employee.controller;

import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.service.IEmployeeService;
import com.ectrip.sys.employee.service.IEsfEmployeeTabService;
import com.ectrip.sys.model.employee.Esfemployeetab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 员工管理相关
 * 
 * @author yangguang
 */
@RestController
@Api(tags = "系统服务模块-员工管理相关接口")
@RequestMapping(value = "employee")
public class EsfEmployeeTabController {
	
	private static final Logger LOGGER = LogManager.getLogger(EsfEmployeeTabController.class);
	@Autowired
	private IEsfEmployeeTabService employeeService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IEmployeeService iemployeeService;
	/**
	 * 根据登录名获取员工信息
	 * @param empId
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "/v1/currentUser")
	@ApiOperation(value = "根据登录名获取员工信息")
	public Esfemployeetab getEsfemployeetabInfo() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String access_token = request.getParameter("access_token");
		String url = "http://zuul-service/uaa/user?access_token="+access_token;
		String result = restTemplate.getForObject(url, String.class);
		Esfemployeetab employeeInfo = JSON.parseObject(JSON.parseObject(result).getString("principal"),Esfemployeetab.class);
		return employeeInfo;
	}
	/**
	 * 
	 * Describe:获取员工信息列表
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-15
	 */
	@GetMapping(value = "/v1/getEmployeeList")
	@ApiOperation(value = "获取员工信息列表")
	public ResponseBean getEmployeeList(Principal user,@RequestParam int pageSize,@RequestParam int page) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			Esfemployeetab employeeInfo = employeeService.getEmployeeInfoByEmpId(user.getName());
			ps = employeeService.getEmployeeList(employeeInfo.getIdeptid(),employeeInfo.getSzemployeename(), pageSize, page, null);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.info("获取员工信息列表", e.getCause());
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	
	/**
	 * 
	 * Describe:员工信息更新
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-15
	 */
	@PostMapping(value = "/v1/update")
	public ResponseBean update(@RequestBody Object entity) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			employeeService.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
		}
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	/**
	 * 查询符合条件的员工信息
	 * （供其他服务调用，如有需要可加条件进行复用）
	 * @return
	 */
	
	@GetMapping(value = "/v1/listByCondition")
	@ApiOperation(value = "查询符合条件的员工列表(供其他服务调用)")
	public List getEmployeeListByCondition(@RequestParam(required=false)Long iemployeeid, @RequestParam(required=false)Long icompanyinfoid, @RequestParam(required=false)String szemployeename) {
		
		List list = employeeService.getEmployeeListByCondition(iemployeeid, icompanyinfoid, szemployeename);
		
		return list;
	}
	/**
	 * 查询符合条件的员工信息
	 * （供其他服务调用，如有需要可加条件进行复用）
	 * @return
	 */
	
	@GetMapping(value = "/v1/getEmployeeListByIemployeeid")
	@ApiOperation(value = "根据员工id,企业id获取员工信息(供其他服务调用)")
	public List getEmployeeListByIemployeeid(String iemployeeids,Long icompanyinfoid) {
		List list = employeeService.getEmployeeListByIemployeeid(iemployeeids,icompanyinfoid);
		return list;
	}

	/**
	 * 
	 * Describe:��ת ����ҳ��
	 * @auth:yangguang
	 * @return
	 * return:String
	 * Date:2011-9-15
	 */
	@GetMapping(value = "/v1/preformAddEmployee")
	@ApiOperation(value = "员工信息添加页面，预先获取信息")
	public ResponseBean preformAddEmployee(){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		/*dept=(Esfdepttab) this.genericService.get(Esfdepttab.class, employee.getIdeptid());
		company=(Galcompanyinfotab) this.genericService.get(Galcompanyinfotab.class,dept.getIcompanyinfoid());
		postlist=employeeService.getPostlist(employee.getIdeptid());
		//��¼��ʽ
		getRequest().setAttribute("dpList", this.sysparService.retrieve("DLTP"));
		//ѧ��
		getRequest().setAttribute("xlList", this.sysparService.retrieve("XLTP"));
		//רҵ
		getRequest().setAttribute("zyList", this.sysparService.retrieve("ZYTP"));
		//����  
		getRequest().setAttribute("mzList", this.sysparService.retrieve("MNZH"));
		//ְ��  
		getRequest().setAttribute("zwList", this.sysparService.retrieve("ZWTP"));
		//ְ��  
		getRequest().setAttribute("zcList", this.sysparService.retrieve("ZCTP"));
		
		//����  ���ӿ�Դ�ر��л�ȡ��
//		getRequest().setAttribute("jgList", this.employeeService.getSourceregion());
		
			
		employee.setBissysuser(new Long(1));
		employee.setByisuse(new Long(1));
		employee.setIsex(new Long(0));
		employee.setBismarry(new Long(0));*/	
		return result;
	}
	
	
	/**
	 * 
	 * Describe:��ת �޸�
	 * @auth:yangguang
	 * @return
	 * return:String
	 * Date:2011-9-15
	 */
	@GetMapping(value = "/v1/preformEditEmployee")
	@ApiOperation(value = "员工信息编辑页面，预先获取信息")
	public ResponseBean preformEditEmployee(){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		/*
		if(employee==null||employee.getIemployeeid()==null||employee.getIemployeeid().equals("")){
			this.addActionError(getText("employee.iemployeeid.required"));
		}else{
			employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
			dept=(Esfdepttab) this.genericService.get(Esfdepttab.class, employee.getIdeptid());
			company=(Galcompanyinfotab) this.genericService.get(Galcompanyinfotab.class,employee.getIcompanyinfoid());
			
			postlist=employeeService.getPostlist(employee.getIdeptid());
			list=employeeService.getPostEmployeelist(employee.getIemployeeid());
			
			//��¼��ʽ
			getRequest().setAttribute("dpList", this.sysparService.retrieve("DLTP"));
			//ѧ��
			getRequest().setAttribute("xlList", this.sysparService.retrieve("XLTP"));
			//רҵ
			getRequest().setAttribute("zyList", this.sysparService.retrieve("ZYTP"));
			//����  
			getRequest().setAttribute("mzList", this.sysparService.retrieve("MNZH"));
			//ְ��  
			getRequest().setAttribute("zwList", this.sysparService.retrieve("ZWTP"));
			//ְ��  
			getRequest().setAttribute("zcList", this.sysparService.retrieve("ZCTP"));
			
			//����  ���ӿ�Դ�ر��л�ȡ��
	//		getRequest().setAttribute("jgList", this.employeeService.getSourceregion());
			//������ʾ
			if(employee.getIborthaddress()!=null&&!employee.getIborthaddress().equals("")){
				sour=(Galsourceregiontab) this.genericService.get(Galsourceregiontab.class, employee.getIborthaddress());
				if(sour==null){
					employee.setSzinnername(null);
				}else{
					employee.setSzinnername(sour.getSzinnername());
				}
			}
			
		}*/
		
		return result;
	}
	
	/**
	 * 
	 * Describe:��ת  ɾ��
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-22
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@GetMapping(value = "/v1/preformDelEmployee")
	@ApiOperation(value = "员工信息删除页面，预先获取信息")
	public ResponseBean preformDelEmployee() {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		/*if(employee==null||employee.getIemployeeid()==null||employee.getIemployeeid().equals("")){
			this.addActionError(getText("employee.iemployeeid.required"));
		}else{
			employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
			
			list=employeeService.getPostEmployeelist(employee.getIemployeeid());
			
			//��������
			dept=(Esfdepttab) this.genericService.get(Esfdepttab.class, employee.getIdeptid());
			//��˾����
			company=(Galcompanyinfotab) this.genericService.get(Galcompanyinfotab.class,employee.getIcompanyinfoid());
			
			employee=(Esfemployeetab)employeeService.reviters(employee.getIemployeeid());
			//������ʾ
			sour=(Galsourceregiontab) this.genericService.get(Galsourceregiontab.class, employee.getIborthaddress());
			if(sour==null){
				employee.setSzinnername(null);
			}else{
				employee.setSzinnername(sour.getSzinnername());
			}
			
		}
		return SUCCESS;*/
		return result;
	}
	
	/**
	 * 
	 * Describe:��ת �鿴
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-22
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@GetMapping(value = "/v1/preformViewEmployee")
	@ApiOperation(value = "查看员工信息")
	public ResponseBean preformViewEmployee(){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		/*if(employee==null||employee.getIemployeeid()==null||employee.getIemployeeid().equals("")){
			this.addActionError(getText("employee.iemployeeid.required"));
		}else{
			employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
			
			list=employeeService.getPostEmployeelist(employee.getIemployeeid());
			
			//��������
			dept=(Esfdepttab) this.genericService.get(Esfdepttab.class, employee.getIdeptid());
			//��˾����
			company=(Galcompanyinfotab) this.genericService.get(Galcompanyinfotab.class,employee.getIcompanyinfoid());
			
			employee=(Esfemployeetab)employeeService.reviters(employee.getIemployeeid());
			//������ʾ
			if(employee.getIborthaddress()!=null&&!employee.getIborthaddress().equals("")){
			sour=(Galsourceregiontab) this.genericService.get(Galsourceregiontab.class, employee.getIborthaddress());
			if(sour==null){
				employee.setSzinnername(null);
			}else{
				employee.setSzinnername(sour.getSzinnername());
			}
			}
		}
		return SUCCESS;*/
		return result;
	}
	
	/**
	 * 
	 * Describe:��¼������ʼ��
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-10-17
	 *//*
	public String preformupdateDunm(){
		this.employeeService.updateEmpdnum(employee.getIemployeeid());
		this.addActionMessage(getText("success.employee.editDnum")+"����ӭʹ��"+WebContant.DOMAINAME);
		return SUCCESS;
	}
	
	
	*//**
	 * 
	 * Describe: ��ӡ��޸ġ�ɾ�� ����
	 * @auth:yangguang
	 * @return
	 * return:String
	 * Date:2011-9-15
	 *//*
	public String employeeSave(){
		Syslog syslog = new Syslog();
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		}
		Esfemployeetab esfemployeetab = (Esfemployeetab) ActionContext.getContext()
				.getSession().get("employee");
		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		
		if(strutsAction==ADD){
			if(employeeService.genEmpId(employee.getEmpid())){
				this.addActionError(getText("errors.employee.empidname.required"));
				return INPUT;
			}
			//�������
			Encrypt encrypt = new Encrypt();
			String password=encrypt.passwordEncrypt(employee.getSzpassword());
			//��������
			dept=(Esfdepttab) this.genericService.get(Esfdepttab.class, employee.getIdeptid());
			employee.setSzpassword(password);
			employee.setRzpwd(encrypt.passwordEncrypt("666666"));
			employee.setIcompanyinfoid(dept.getIcompanyinfoid());
			company=(Galcompanyinfotab) this.genericService.get(Galcompanyinfotab.class,dept.getIcompanyinfoid());   //��ҵ��Ϣ
			if(company.getCompanytype().equals("01")){
				employee.setEmptype(new Long(0));
			}
			if(company.getCompanytype().equals("02")){
				employee.setEmptype(new Long(1));
			}
			
			employee.setIdeptid(dept.getIdeptid());
			if(employee.getPhotoupid()==null||employee.getPhotoupid().equals(" ")){
				employee.setPhotoupid(0L);
			}
			employeeService.saveEmployee(employee,ipostsids,syslog);
			this.addActionMessage(getText("success.employee.add")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(strutsAction==EDIT){
			if(employee.getPhotoupid()==null||employee.getPhotoupid().equals(" ")){
				employee.setPhotoupid(0L);
			}
			employeeService.updateEmployee(employee,ipostsids,syslog);
			this.addActionMessage(getText("success.employee.edit")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		if(strutsAction==DELETE){
			employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
			if(employee.getIemployeeid()==esfemployeetab.getIemployeeid()&&employee.getIemployeeid().equals(esfemployeetab.getIemployeeid())){
				this.addActionError("errors.employee.employeeidnote.required");   //Ա���Լ�����ɾ���Լ�
				return INPUT;
			}
			if(employee.getEmpid()=="admin"||employee.getEmpid().equals("admin")){
				this.addActionError("errors.employee.empidnotdel.required");	//adminΪ��������Ա  ����ɾ��
				return INPUT;
			}
			this.employeeService.deleteEmployee(employee,syslog);
			this.addActionMessage(getText("success.employee.del")+"����ӭʹ��"+WebContant.DOMAINAME);
		}
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:��֤
	 * @auth:lijingrui
	 * return:void
	 * Date:2011-10-17
	 *//*
	public void validateEmployeeSave(){
		if(strutsAction==ADD||strutsAction==EDIT){
			if(getEmployee()==null){
				this.addActionError("errors.inputError");
			}else{
				if(getEmployee().getBissysuser()==null||getEmployee().getBissysuser().equals("")){
					this.addActionError(getText("errors.employee.bissysuser.required"));	 //�Ƿ��ʹ�ñ�ϵͳ
				}
				if(getEmployee().getByisuse()==null||getEmployee().getByisuse().equals("")){
					this.addActionError(getText("errors.employee.byisuse.required"));     //�Ƿ�����
				}
				if(getEmployee().getSzemployeename()==null||getEmployee().getSzemployeename().trim().equals("")){
					this.addActionError(getText("errors.employee.szemployeename.required"));//Ա������
				}
				if(getEmployee().getSzemployeename()!=null&&!getEmployee().getSzemployeename().equals("")){
					if(getEmployee().getSzemployeename().length()>50){
						this.addActionError(getText("errors.employee.szemployeename.length"));//�ж�Ա����������
					}
					
				}
				if(getEmployee().getSzcardid()==null||getEmployee().getSzcardid().equals("")){
					this.addActionError(getText("errors.employee.szcardid.required"));   //���֤����
				}

//				if(getEmployee().getSzcardid()!=null&&!getEmployee().getSzcardid().trim().equals("")){
//			//		Pattern p = Pattern.compile("^ \\d{15}|\\d{17}([0-9]|X)$");    ^\d{15}$|^\d{17}(?:\d|x|X)$ 		
//					Pattern p = Pattern.compile("(^\\d{14}([0-9]|X)$)|(^\\d{18})$");
//					boolean b = p.matcher(getEmployee().getSzcellphone()).matches();
//					if (b == false) { 
//						this.addActionError(getText("errors.employee.cardid.required"));//���֤����
//					}
//				}
				
				
				if(getEmployee().getSzcellphone()==null||getEmployee().getSzcellphone().equals("")){
					this.addActionError(getText("errors.employee.szcellphone.required"));   //��ϵ�绰
				}
			
				if(getEmployee().getSzcellphone()!=null && !"".equals(getEmployee().getSzcellphone())){   //��ϵ�绰
					Pattern p = Pattern.compile("^ \\d{3}-\\d{8}|\\d{4}-\\d{7}$");
					Pattern p1 = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
					
					boolean b = p.matcher(getEmployee().getSzcellphone()).matches();
					boolean c = p1.matcher(getEmployee().getSzcellphone()).matches();
					if (b == false && c==false) {
						this.addActionError(getText("errors.employee.phone.required"));//��ϵ�绰��ʽ����ȷ����13288888888��023-55328751��0755-2423354
					}
				}
				
				
				if(getEmployee().getSzemail()==null||getEmployee().getSzemail().equals("")){
					this.addActionError(getText("errors.employee.szemail.required"));    //��������
				}
				
				if (getEmployee().getSzemail() != null
						&& !getEmployee().getSzemail().trim().equals("")) {             //��������
					if (!Tools.isEmail(getEmployee().getSzemail())) {
						this.addActionError(getText("employee.errorEmail"));     
					}
					if(getEmployee().getSzemail().length()>25){
						this.addActionError(getText("employee.email.length"));			//�жϵ������䳤��
					}
				}
				if(getEmployee().getSzhomeaddress()!=null&&!getEmployee().getSzhomeaddress().equals("")){
					if(getEmployee().getSzhomeaddress().length()>100){
						this.addActionError(getText("errors.employee.szhomeaddress.length"));   //�жϼ�ͥ��ַ����
					}
				}
				if(getEmployee().getSzschool()!=null&&!getEmployee().getSzschool().equals("")){
					if(getEmployee().getSzschool().length()>25){
						this.addActionError(getText("errors.employee.szschool.length"));   //�жϱ�ҵѧУ����
					}
				}
				if(getEmployee().getSzaccountlocation()!=null&&!getEmployee().getSzaccountlocation().equals("")){
					if(getEmployee().getSzaccountlocation().length()>100){
						this.addActionError(getText("errors.employee.szaccountlocation.length"));   //�жϻ������ڵس���
					}
				}
				
				
//				if(getEmployee().getDtgraduatedate()==null||getEmployee().getDtgraduatedate().equals("")){
//					this.addActionError(getText("errors.employee.dtgraduatedate.required"));   //��ҵ����
//				}
//				if(getEmployee().getDtentrydate()==null||getEmployee().getDtentrydate().equals("")){
//					this.addActionError(getText("errors.employee.dtentrydate.required"));   
//				}
				
			}
		}
		if(strutsAction==ADD){
			if(getEmployee().getEmpid()==null||getEmployee().getEmpid().trim().equals("")){
				this.addActionError(getText("errors.employee.empid.required"));       //��¼�˺�
			}
			if(getEmployee().getEmpid()!=null&&!getEmployee().getEmpid().equals("")){
				Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{1,15}$");
				boolean b = p.matcher(getEmployee().getEmpid()).matches();
				if (b == false) {
					this.addActionError(getText("errors.employee.errorempid.required"));//��¼�˺�
				}
				if(getEmployee().getEmpid().length()>15){
					this.addActionError(getText("errors.employee.errorempid.length"));//��¼�˺ų���
				}
			}
			if(getEmployee().getSzpassword()==null||getEmployee().getSzpassword().equals("")){
				this.addActionError(getText("errors.employee.szpassword.required"));  //��¼����
			}
			if(getEmployee().getSzpassword()!=null&&!getEmployee().getSzpassword().equals("")){
				Pattern p = Pattern.compile("^[a-zA-Z0-9]{8,25}$");
				boolean b = p.matcher(getEmployee().getSzpassword()).matches();
				if (b == false) {
					this.addActionError(getText("��¼������������������[8-25]���֡���ĸ(��Сд����)��ɵ����룡"));//��¼����
				}
				
				if(getEmployee().getSzpassword().length()>25){
					this.addActionError(getText("errors.employee.szpassword.length"));  //��¼���볤��
				}
			}
		}
		
	}
	
	*//**
	 * 
	 * Describe:ѡ��ĳ��������¼�
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-28
	 *//*
	public String listviewborthress(){
		try{
			String regionid = getRequest().getParameter("regionid");
			getRequest().setAttribute("jgList", employeeService.getSourceRegionJson(Long.parseLong(regionid)));
		}catch (Exception e) {
			System.out.println("sourceRegionAction execetption:"+e.getMessage());
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	*//**
	 * 
	 * Describe:�޸� ��ʼ������
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-26
	 *//*
	public String preformUpempword(){
		employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:���� ��ʼ������
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-26
	 *//*
	public String preformempWordSave(){
		if(password==null||password.equals("")){
			this.addActionError(getText("errors.employee.empword.required"));   
			return INPUT;
		}else{
			Pattern p = Pattern.compile("^[a-zA-Z0-9]{8,25}$");
			boolean b = p.matcher(password).matches();
			if (b == false) {
				this.addActionError(getText("��¼������������������[8-25]���֡���ĸ(��Сд����)��ɵ����룡"));//��ʼ������
				return INPUT;
			}
		}
		employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
		//�������
		Encrypt encrypt = new Encrypt();
		String pwd=encrypt.passwordEncrypt(password);
		
		this.employeeService.updateEmppassword(employee, pwd);
		this.addActionMessage(getText("success.employee.editpassword")+"����ӭʹ��"+WebContant.DOMAINAME);
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:�޸� ��ʼ����֤��
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-26
	 *//*
	public String preformUpemprzpwd(){
		employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
		return SUCCESS;
	}
	
	*//**
	 * 
	 * Describe:���� ��ʼ����֤��
	 * @auth:lijingrui
	 * @return
	 * return:String
	 * Date:2011-9-26
	 *//*
	public String preformemprzpwdSave(){
		if(password==null||password.equals("")){
			this.addActionError(getText("errors.employee.rzpwd.required"));   
			return INPUT;
		}else{
			Pattern p = Pattern.compile("^[a-zA-Z0-9]*$");
			boolean b = p.matcher(password).matches();
			if (b == false) {
				this.addActionError(getText("errors.employee.rzpwd.matches.required"));//��ʼ����֤��
				return INPUT;
			}
		}
		employee=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
		//�������
		Encrypt encrypt = new Encrypt();
		String pwd=encrypt.passwordEncrypt(password);
		
		Esfemployeetab esf=(Esfemployeetab) this.genericService.get(Esfemployeetab.class, employee.getIemployeeid());
		esf.setRzpwd(pwd);
		this.genericService.update(esf);
		
		this.addActionMessage(getText("success.employee.editrzpwd")+"����ӭʹ��"+WebContant.DOMAINAME);
		return SUCCESS;
	}
*/
	
	@PostMapping("/getEsfemployeeByGalcompanyScenicid")
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicid(@RequestParam("scenicId") Long scenicId){
		return iemployeeService.getEsfemployeeByGalcompanyScenicid(scenicId);
	}
	
	@PostMapping("/getEsfemployeeByGalcompanyScenicids")
	public List<Esfemployeetab> getEsfemployeeByGalcompanyScenicids(@RequestParam("scenicId")Long iscenicid, @RequestParam("keys")String keys){
		return iemployeeService.getEsfemployeeByGalcompanyScenicids(iscenicid, keys);
	}
}
