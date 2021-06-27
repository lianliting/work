package com.ischoolbar.programmer.service.admin.impl;
/**
 * 角色role的实现类
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.DormitoryManagementDao;
import com.ischoolbar.programmer.entity.admin.DormitoryManagement;
import com.ischoolbar.programmer.service.admin.DormitoryManagementService;
@Service
public class DormitoryManagementServiceImpl implements DormitoryManagementService {

	@Autowired
	private DormitoryManagementDao dormitoryManagementDao;

	// 宿舍信息添加
	@Override
	public int add(DormitoryManagement dormitoryManagement) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.add(dormitoryManagement);
	}

	@Override
	public int edit(DormitoryManagement dormitoryManagement) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.edit(dormitoryManagement);
	}

	@Override
	public List<DormitoryManagement> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.getTotal(queryMap);
	}

	@Override
	public DormitoryManagement find(Long id) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.find(id);
	}

	@Override
	public DormitoryManagement findByDormsname(String dorms) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.findByDormsname(dorms);
	}

	// 宿舍信息删除
	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return dormitoryManagementDao.delete(ids);
	}

}
