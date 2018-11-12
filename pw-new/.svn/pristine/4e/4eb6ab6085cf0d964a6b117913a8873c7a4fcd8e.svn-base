package com.ectrip.tourcard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 有的人不满足旅游卡购买权限，但有为这些人开通绿色购买权限的需求。
 * 此类用于记录这些人物信息
 * @author Administrator
 *
 */
@Entity
@Table(name="PASSPEOPLE")
public class PassPeople {
	
	@Id
	private Long id;
	
	@Column
	private String cardCode;	//关联的旅游卡代码
	
	@Column
	private String name;		//姓名
	
	@Column
	private String identityNum;	//身份证号

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

}
