package com.its.sims.model;

import java.util.Date;

/**
 * @author csz
 * 学生基本信息
 */
public class Student {
	
	private Long id;
	
	private String name;
	
	private String gender;//性别
	
	private Integer age;

	private String headImgUrl;//头像地址
	
	private String grade;//年级
	
	private String classes;//班级
	
	private String address;//家庭住址
	
	private String emergencyContact;//紧急联系人
	
	private String emergencyContactPhone;//紧急联系人电话

	private String password;//密码,password

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", gender='" + gender + '\'' +
				", age=" + age +
				", headImgUrl='" + headImgUrl + '\'' +
				", grade='" + grade + '\'' +
				", classes='" + classes + '\'' +
				", address='" + address + '\'' +
				", emergencyContact='" + emergencyContact + '\'' +
				", emergencyContactPhone='" + emergencyContactPhone + '\'' +
				", password='" + password + '\'' +
				", updateTime=" + updateTime +
				'}';
	}
}
