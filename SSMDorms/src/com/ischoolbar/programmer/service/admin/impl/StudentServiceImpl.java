package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.StudentDao;
import com.ischoolbar.programmer.entity.admin.Student;
import com.ischoolbar.programmer.service.admin.StudentService;
/**
 * student学生serviceimpl
 * @author 
 *
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student findByStudentName(String studentName) {
		// TODO Auto-generated method stub
		return studentDao.findByStudentName(studentName);
	}

	@Override
	public int add(Student student) {
		// TODO Auto-generated method stub
		return studentDao.add(student);
	}

	@Override
	public int edit(Student student) {
		// TODO Auto-generated method stub
		return studentDao.edit(student);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return studentDao.delete(ids);
	}

	@Override
	public List<Student> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentDao.getTotal(queryMap);
	}

	@Override
	public Student findByStudentNumber(String studentNumber) {
		// TODO Auto-generated method stub
		return studentDao.findByStudentNumber(studentNumber);
	}


	@Override
	public List<Student> studentlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentDao.studentlist(queryMap);
	}

	@Override
	public List<Student> dorms(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentDao.dorms(queryMap);
	}
}
