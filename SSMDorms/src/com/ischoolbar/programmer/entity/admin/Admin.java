package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 宿舍管理员实体类
 * @author 
 *
 */
@Component
public class Admin {
	private Long id; // id，设置自增
	private String adminNumber; // 工号
	private String adminName; // 管理员姓名
	private String password; // 登录密码
	private String photo; // 头像照片地址
	private int sex; // 1代表男，0代表女
	private Long dormsId; // 寝室楼号
	private String dorms; // 寝室楼
	private String tel; // 电话
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(String adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Long getDormsId() {
		return dormsId;
	}
	public void setDormsId(Long dormsId) {
		this.dormsId = dormsId;
	}
	public String getDorms() {
		return dorms;
	}
	public void setDorms(String dorms) {
		this.dorms = dorms;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
