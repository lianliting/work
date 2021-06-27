package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.AdminDao;
import com.ischoolbar.programmer.entity.admin.Admin;
import com.ischoolbar.programmer.service.admin.AdminService;
/**
 * admin管理员serviceimpl
 * @author 
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin findByAdminName(String adminName) {
		// TODO Auto-generated method stub
		return adminDao.findByAdminName(adminName);
	}

	@Override
	public int add(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.add(admin);
	}

	@Override
	public int edit(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.edit(admin);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return adminDao.delete(ids);
	}

	@Override
	public List<Admin> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return adminDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return adminDao.getTotal(queryMap);
	}

	@Override
	public int editPassword(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.editPassword(admin);
	}

	@Override
	public Admin findByAdminNumber(String adminNumber) {
		// TODO Auto-generated method stub
		return adminDao.findByAdminNumber(adminNumber);
	}


	@Override
	public List<Admin> adminlist(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return adminDao.adminlist(queryMap);
	}


	@Override
	public List<Admin> dorms(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return adminDao.dorms(queryMap);
	}
	@Override
	public int findDormsId(int id) {
		// TODO Auto-generated method stub
		return adminDao.findDormsId(id);
	}
}
