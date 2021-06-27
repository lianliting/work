package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.OutsidersDao;
import com.ischoolbar.programmer.entity.admin.Outsiders;
import com.ischoolbar.programmer.service.admin.OutsidersService;

@Service
public class OutsidersServiceImpl implements OutsidersService{

	@Autowired
	private OutsidersDao outsidersDao;

	@Override
	public int add(Outsiders outsiders) {
		// TODO Auto-generated method stub
		return outsidersDao.add(outsiders);
	}

	@Override
	public int addOutName(String outName) {
		// TODO Auto-generated method stub
		return outsidersDao.addOutName(outName);
	}

	@Override
	public int addOutIdCard(String outIdCard) {
		// TODO Auto-generated method stub
		return outsidersDao.addOutIdCard(outIdCard);
	}

	@Override
	public List<Outsiders> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return outsidersDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return outsidersDao.getTotal(queryMap);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return outsidersDao.delete(ids);
	}

	@Override
	public Outsiders findByOutName(String outName) {
		// TODO Auto-generated method stub
		return outsidersDao.findByOutName(outName);
	}

	@Override
	public Outsiders findByOutIdCard(String outIdCard) {
		// TODO Auto-generated method stub
		return outsidersDao.findByOutIdCard(outIdCard);
	}

	@Override
	public List<Map<String, String>> getStats(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return outsidersDao.getStats(queryMap);
	}
	
	
}
