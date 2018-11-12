package com.ectrip.ec.custom.model;
public class ErrorMessage {
	private String errorUsername;
	private String errorMobile;
	private String errorEmail;
	private String errorCode;
	private String errorpwd;
	private String errorpwd2;
	private String errorImageCode;
	private String errorother;
	private boolean flag;
	public String getErrorMobile() {
		return errorMobile;
	}
	public void setErrorMobile(String errorMobile) {
		this.errorMobile = errorMobile;
	}
	public String getErrorEmail() {
		return errorEmail;
	}
	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorpwd() {
		return errorpwd;
	}
	public void setErrorpwd(String errorpwd) {
		this.errorpwd = errorpwd;
	}
	public String getErrorpwd2() {
		return errorpwd2;
	}
	public void setErrorpwd2(String errorpwd2) {
		this.errorpwd2 = errorpwd2;
	}
	public String getErrorImageCode() {
		return errorImageCode;
	}
	public void setErrorImageCode(String errorImageCode) {
		this.errorImageCode = errorImageCode;
	}
	public String getErrorother() {
		return errorother;
	}
	public void setErrorother(String errorother) {
		this.errorother = errorother;
	}
	public String getErrorUsername() {
		return errorUsername;
	}
	public void setErrorUsername(String errorUsername) {
		this.errorUsername = errorUsername;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}

