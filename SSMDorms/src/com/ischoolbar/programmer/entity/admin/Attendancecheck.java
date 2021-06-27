package com.ischoolbar.programmer.entity.admin;

import java.util.Date;

import org.springframework.stereotype.Component;



/**
 * 系统日志
 * @author 
 *
 */
@Component
public class Attendancecheck {
	private Long id;
	
	
	private String adminNumber;

	private Date time;// 创建时间
	private String notes;
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
