package com.ectrip.sys.employee.controller;

import java.util.regex.Pattern;

import com.ectrip.base.util.Encrypt;
import com.ectrip.sys.employee.service.IEmployeeService;
import com.ectrip.sys.model.employee.Employee;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.syspar.Syslog;
//后台用户自己管理
public class EmployeeOneselfManageAction{/*
	
	private Employee employee;
	private String newpwd;
	private String newsecondpwd;
	
	private IEmployeeService employService;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getNewsecondpwd() {
		return newsecondpwd;
	}

	public void setNewsecondpwd(String newsecondpwd) {
		this.newsecondpwd = newsecondpwd;
	}
	
	public IEmployeeService getEmployService() {
		return employService;
	}

	public void setEmployService(IEmployeeService employService) {
		this.employService = employService;
	}

	*//**
	 * �޸�����
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-10
	 *//*
	public String Managepassword() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		
		String empid =  esfemployeetab.getEmpid();
		employee = employService.retrieve(empid);
		
		return SUCCESS;
	}
	*//**
	 * �޸����뱣��
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-10
	 *//*
	public String ManagepasswordSave() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		
		String empid =  esfemployeetab.getEmpid();

		//�����û�����ѯ���û���Ϣ
		Employee e =  employService.retrieve(employee.getEmpid());
		if (employee.getSzpassword() == null
				|| "".equals(employee.getSzpassword())) {
			this.addActionError("�����������");
			return INPUT;
		}
		
		Encrypt encrypt = new Encrypt();		
		String oldpassword = encrypt.passwordEncrypt(employee.getSzpassword());
		
		if (!e.getSzpassword().equals(oldpassword)) {
			this.addActionError("�������������");
			return INPUT;
		}
				
		if (newpwd == null || "".equals(newpwd)) {
			this.addActionError("������������");
			return INPUT;
		}
		
		Pattern p = Pattern.compile("^[A-Za-z0-9]{1,25}$");
		boolean b = p.matcher(newpwd).matches();
		if (b == false) {
			this.addActionError("�������ʽ����ֻ������������ֻ���ĸ���ַ�");
			return INPUT;
		}
		if(newpwd!=null && !"".equals(newpwd)){
			if(newpwd.length()>25){
				this.addActionError("���볤�ײ��ܳ���25���ַ�");
				return INPUT;
			}
		}
		
		String password = encrypt.passwordEncrypt(newpwd);	//����֤�����	

		//������ȷ������
		if (newsecondpwd == null || "".equals(newsecondpwd)) {
			this.addActionError("������ȷ������");
			return INPUT;
		}
		if (!newpwd.equals(newsecondpwd)) {
			this.addActionError("������������벻һ��");
			return INPUT;
		}	
		
		if (!empid.equals(employee.getEmpid())) {
			this.addActionError("Ҫ�޸�������û����¼�û���һ�£�");
			return INPUT;
		}
	//	e.setRzpwd(password);//����
		
		e.setSzpassword(password);
		
		//������־
		Syslog syslog = new Syslog();
		//�õ����SEQ
		long seq = this.genericService.getMaxPk("logid", new String[]{}, new Long[]{} , new String[]{}, new String[]{}, "Syslog");
		syslog.setLogid(seq+1);
		//�޸��û����뱣��		
		employService.updateEmployeePassword(e, syslog);

		this.addActionMessage("�޸�����ɹ�");
		return SUCCESS;
	}
	*//**
	 * ��̨�û���Ϣ�޸�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-10
	 *//*
	public String ManageEdit() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		
		String empid =  esfemployeetab.getEmpid();
		employee = employService.retrieve(empid);
		
		return SUCCESS;
	}
	*//**
	 * ��̨�û��޸ı���
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-10
	 *//*
	public String ManageeditSave() {
		
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		
		String empid =  esfemployeetab.getEmpid();
		
		if(employee.getEmpid()==null || "".equals(employee.getEmpid())){
			this.addActionError("�û�������Ϊ��");
			return INPUT;
		}
		
		if(employee.getSzemployeename()==null || "".equals(employee.getSzemployeename())){
			this.addActionError("�û���������Ϊ��");
			return INPUT;
		}
		if(employee.getSzcellphone()==null || "".equals(employee.getSzcellphone())){
			this.addActionError("��ϵ�绰����Ϊ��");
			return INPUT;
		}
		
		
		if(employee.getSzemail()!=null && !"".equals(employee.getSzemail())){
			Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");
			boolean b = p.matcher(employee.getSzemail()).matches();
			if (b == false) {
				this.addActionError("�����ʽ��������������");
				return INPUT;
			}
		}
		
		if(employee.getSzmemo()!=null && !"".equals(employee.getSzmemo())){
			if(employee.getSzmemo().length()>=260){
				this.addActionError("��ע�ַ����������ֻ������260����");
				return INPUT;
			}
		}
		
		//�����û�����ѯ���û���Ϣ
		Employee e =  employService.retrieve(employee.getEmpid());
		if(!e.getEmpid().equals(employee.getEmpid())){			
			this.addActionError("Ҫ�޸���֤����û����¼�û���һ�£����������");
			return INPUT;
		}
		
		
		e.setSzcellphone(employee.getSzcellphone());//�绰
		e.setSzemail(employee.getSzemail());//��������
		e.setSzemployeename(employee.getSzemployeename());//�û���
		e.setSzmemo(employee.getSzmemo());//˵��
		e.setAddr(employee.getAddr());//��ַ

		//������־
		Syslog syslog = new Syslog();
		//�õ����SEQ
		long seq = this.genericService.getMaxPk("logid", new String[]{}, new Long[]{} , new String[]{}, new String[]{}, "Syslog");
		syslog.setLogid(seq+1);
		employService.updateEmployeeInfo(e, syslog);
		
		ActionContext.getContext().getSession().put("adminusername", esfemployeetab.getEmpid());
		
		
		this.addActionMessage("�û���Ϣ�޸ĳɹ���");
		return SUCCESS;
	}
	
	
	*//**
	 * �޸���֤��
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-10
	 *//*
	public String ManageRenZhenpwd() {
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		
		String empid =  esfemployeetab.getEmpid();
		employee = employService.retrieve(empid);
		
		return SUCCESS;
	}
	
	
	*//**
	 * �޸���֤�뱣��
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-10
	 *//*
	public String ManageRzpwdSave(){
		
		Esfemployeetab esfemployeetab = null;
		if (ActionContext.getContext().getSession().get("employee") == null
				|| "".equals(ActionContext.getContext().getSession().get("employee"))) {
			return "login";
		} else {
			esfemployeetab = (Esfemployeetab) ActionContext.getContext()
					.getSession().get("employee");
		}
		
	

		if(employee.getEmpid()==null || "".equals(employee.getEmpid())){
			this.addActionError("�û�������Ϊ��");
			return INPUT;
		}
		
		//�����û�����ѯ���û���Ϣ
		Employee e =  employService.retrieve(employee.getEmpid());
		
		if (employee.getRzpwd() == null				
				|| employee.getRzpwd().equals("")) {
			this.addActionError("���������֤��");
			return INPUT;
		}
		
		Encrypt encrypt = new Encrypt();	
		String oldpassword = encrypt.passwordEncrypt(employee.getRzpwd());
		if (!e.getRzpwd().equals(oldpassword)) {
			this.addActionError("����֤���������");
			return INPUT;
		}
		
		if (newpwd == null || "".equals(newpwd)) {
			this.addActionError("����������֤��");
			return INPUT;
		}
		
		Pattern p = Pattern.compile("^[A-Za-z0-9]{6}$");
		boolean b = p.matcher(newpwd).matches();
		if (b == false) {
			this.addActionError("��֤���ʽ����ֻ������������ֻ���ĸ��6λ�ַ�");
			return INPUT;
		}
		
		String password = encrypt.passwordEncrypt(newpwd);	//����֤�����	

		//������ȷ������
		if (newsecondpwd == null || "".equals(newsecondpwd)) {
			this.addActionError("������ȷ����֤��");
			return INPUT;
		}
		if (!newpwd.equals(newsecondpwd)) {
			this.addActionError("�����������֤�벻һ��");
			return INPUT;
		}	
		
		String empid =  esfemployeetab.getEmpid();
		
		if (!empid.equals(employee.getEmpid())) {
			this.addActionError("Ҫ�޸���֤����û����¼�û���һ�£�");
			return INPUT;
		}
		e.setRzpwd(password);//��֤��
		
		//������־
		Syslog syslog = new Syslog();
		//�õ����SEQ
		long seq = this.genericService.getMaxPk("logid", new String[]{}, new Long[]{} , new String[]{}, new String[]{}, "Syslog");
		syslog.setLogid(seq+1);
		
		//����
		employService.updateEmployeeRzPwd(e, syslog);
			
		this.addActionMessage("�޸���֤��ɹ�");
		
		return SUCCESS;
	}
*/}
