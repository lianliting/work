package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

public class DormitoryRepair {
	private Long id;// 记录编号
	private int dorms; // 宿舍楼
	private String numbers; // 宿舍号
	private Date time; // 创建时间
	private String contents; // 报修内容
	private int adminId; // 报修人
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getDorms() {
		return dorms;
	}
	public void setDorms(int dorms) {
		this.dorms = dorms;
	}
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
}
