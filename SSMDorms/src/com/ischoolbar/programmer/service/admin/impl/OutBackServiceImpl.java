package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.OutBackDao;
import com.ischoolbar.programmer.entity.admin.OutBack;
import com.ischoolbar.programmer.service.admin.OutBackService;

@Service
public class OutBackServiceImpl implements OutBackService {

	@Autowired
	private OutBackDao outBackDao;
	
	@Override
	public int add(OutBack outBack) {
		// TODO Auto-generated method stub
		return outBackDao.add(outBack);
	}

	@Override
	public List<OutBack> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return outBackDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return outBackDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return outBackDao.delete(ids);
	}

	@Override
	public OutBack findByStudentNumber(String studentNumber) {
		// TODO Auto-generated method stub
		return outBackDao.findByStudentNumber(studentNumber);
	}

	@Override
	public List<Map<String, String>> getStats(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return outBackDao.getStats(queryMap);
	}

}
