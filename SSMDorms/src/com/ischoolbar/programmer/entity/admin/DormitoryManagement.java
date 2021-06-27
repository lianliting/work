package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**

 * @author 
 *
 */
@Component
public class DormitoryManagement {
	private Long id;
	private String dorms;// 寝室楼
	private String adminId;// 所属管理员
	private String notes;// 备注
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDorms() {
		return dorms;
	}
	public void setDorms(String dorms) {
		this.dorms = dorms;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	
}
