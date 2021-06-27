package com.ischoolbar.programmer.entity.admin;

import org.springframework.stereotype.Component;

/**
 * 学生实体类
 * @author 
 *
 */
@Component
public class Student {
	private Long id; // id，设置自增
	private String studentNumber; // 学号
	private String studentName; // 学生姓名
	private String photo; // 头像照片地址
	private int sex; // 1代表男，0代表女
	private Long dormsId; // 寝室楼号
	private String dorms; // 寝室楼
	private String numbers; // 寝室号
	private String tel; // 电话
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	
}
