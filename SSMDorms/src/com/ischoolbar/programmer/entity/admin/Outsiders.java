package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

public class Outsiders {
	private Long id;// 记录编号
	private String outName; // 外来人员姓名
	private String outIdCard; // 外来人员身份证֤
	private Date time; // 创建时间
	private String notes; // 备注
	private int sex; // 性别
	private String reason; // 理由
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOutName() {
		return outName;
	}
	public void setOutName(String outName) {
		this.outName = outName;
	}
	public String getOutIdCard() {
		return outIdCard;
	}
	public void setOutIdCard(String outIdCard) {
		this.outIdCard = outIdCard;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
