package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Student;

/**
 * student学生dao
 * @author 
 *
 */
@Repository
public interface StudentDao {
	public Student findByStudentName(String studentName); // 根据学生姓名查找学生对象
	public Student findByStudentNumber(@Param("studentNumber") String studentNumber); // 根据学号查找学生对象
	public List<Student> studentlist(Map<String, Object> queryMap); // 学生列表
	public List<Student> dorms(Map<String, Object> queryMap); // 宿舍楼列表
	public List<Student> findList(Map<String, Object> queryMap); // 查找列表

	public int add(Student student); // 添加学生
	public int edit(Student student); // 修改学生信息
	public int delete(String ids);  // 删除学生信息

	public int getTotal(Map<String, Object> queryMap); // 查找所有学生信息
}
