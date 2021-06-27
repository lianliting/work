package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Attendancecheck;

/**
 * 系统日志类dao
 * @author llq
 *
 */
@Repository
public interface AttendancecheckDao {
	public int add(Attendancecheck attendancecheck);
	
	public List<Attendancecheck> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
	
	public Attendancecheck findByAdminNumber(String adminNumber);
	public List<Map<String,String>> getStats(Map<String, Object> queryMap);

}
