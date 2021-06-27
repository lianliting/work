package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Attendancecheck;

/**
 * 巡楼检查
 * @author 
 *
 */
@Service
public interface AttendancecheckService {
	public int add(Attendancecheck attendancecheck);
	
	public List<Attendancecheck> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
	
	public List<Map<String,String>> getStats(Map<String, Object> queryMap);
}
