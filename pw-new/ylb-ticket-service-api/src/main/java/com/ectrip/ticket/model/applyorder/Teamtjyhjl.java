package com.ectrip.ticket.model.applyorder;

import java.io.Serializable;

/**
 * 团队类型关联表
 * @author lijingrui
 */
public class Teamtjyhjl implements Serializable {
	private Long gltyjid;//主键
	private Long ttypeid;//团队类型id
	private String gltype;//关联类型（01 成团条件;02 优惠;03 奖励政策）
	private Long tjyhjlid;//条件优惠政策id（条件、优惠、政策表id(三表共用一个序列号)）
	private String note1;//备用
	private Long inote1;//
	public Teamtjyhjl() {
		super();
	}
	public Teamtjyhjl(Long gltyjid, Long ttypeid, String gltype, Long tjyhjlid,
					  String note1, Long inote1) {
		super();
		this.gltyjid = gltyjid;
		this.ttypeid = ttypeid;
		this.gltype = gltype;
		this.tjyhjlid = tjyhjlid;
		this.note1 = note1;
		this.inote1 = inote1;
	}
	public Long getGltyjid() {
		return gltyjid;
	}
	public void setGltyjid(Long gltyjid) {
		this.gltyjid = gltyjid;
	}
	public Long getTtypeid() {
		return ttypeid;
	}
	public void setTtypeid(Long ttypeid) {
		this.ttypeid = ttypeid;
	}
	public String getGltype() {
		return gltype;
	}
	public void setGltype(String gltype) {
		this.gltype = gltype;
	}
	public Long getTjyhjlid() {
		return tjyhjlid;
	}
	public void setTjyhjlid(Long tjyhjlid) {
		this.tjyhjlid = tjyhjlid;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public Long getInote1() {
		return inote1;
	}
	public void setInote1(Long inote1) {
		this.inote1 = inote1;
	}



}
