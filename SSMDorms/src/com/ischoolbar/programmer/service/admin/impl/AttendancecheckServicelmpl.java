package com.ischoolbar.programmer.service.admin.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.AttendancecheckDao;
import com.ischoolbar.programmer.entity.admin.Attendancecheck;
import com.ischoolbar.programmer.service.admin.AttendancecheckService;

@Service
public class AttendancecheckServicelmpl implements AttendancecheckService {

	@Autowired
	private AttendancecheckDao attendancecheckDao;
	@Override
	public int add(Attendancecheck attendancecheck) {
		// TODO Auto-generated method stub
		return attendancecheckDao.add(attendancecheck);
	}


	@Override
	public List<Attendancecheck> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return attendancecheckDao.findList(queryMap);
	}


	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return attendancecheckDao.getTotal(queryMap);
	}


	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return attendancecheckDao.delete(ids);
	}


	@Override
	public List<Map<String, String>> getStats(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return attendancecheckDao.getStats(queryMap);
	}


}

  