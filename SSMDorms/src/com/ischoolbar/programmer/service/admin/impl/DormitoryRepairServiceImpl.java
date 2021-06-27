package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.DormitoryRepairDao;
import com.ischoolbar.programmer.entity.admin.DormitoryRepair;
import com.ischoolbar.programmer.service.admin.DormitoryRepairService;

@Service
public class DormitoryRepairServiceImpl implements DormitoryRepairService{

	@Autowired
	private DormitoryRepairDao dormitoryRepairDao;

	@Override
	public int add(DormitoryRepair dormitoryRepair) {
		// TODO Auto-generated method stub
		return dormitoryRepairDao.add(dormitoryRepair);
	}
	
	@Override
	public List<DormitoryRepair> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return dormitoryRepairDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return dormitoryRepairDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return dormitoryRepairDao.delete(ids);
	}

	@Override
	public List<Map<String, String>> getStats(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return dormitoryRepairDao.getStats(queryMap);
	}
	
	@Override
	public DormitoryRepair findByNumbers(String numbers){
		// TODO Auto-generated method stub
		return dormitoryRepairDao.findByNumbers(numbers);
	}

}
