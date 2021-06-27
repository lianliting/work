package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Admin;

/**
 * admin管理员dao
 * @author 
 *
 */
@Repository
public interface AdminDao {
	public Admin findByAdminName(String adminName); // 根据管理员姓名查找管理员对象
	public Admin findByAdminNumber(String adminNumber); // 根据工号查找管理员对象
	public List<Admin> adminlist(Map<String, Object> queryMap); // 管理员列表,有条件
	public List<Admin> dorms(Map<String, Object> queryMap); // 管理员列表
	public List<Admin> findList(Map<String, Object> queryMap); // 查找列表

	public int add(Admin admin); // 添加管理员
	public int edit(Admin admin); // 修改管理员信息
	public int editPassword(Admin admin);  // 修改管理员登录密码
	public int delete(String ids);  // 删除管理员
	public int findDormsId(int id);
	
	public int getTotal(Map<String, Object> queryMap); // 查找所有管理员信息
}
