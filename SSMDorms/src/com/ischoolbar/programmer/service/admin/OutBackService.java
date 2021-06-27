package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.OutBack;

@Service
public interface OutBackService {
	public int add(OutBack outBack); // 添加一条记录
	public List<OutBack> findList(Map<String, Object> queryMap); // 获得列表
	public int getTotal(Map<String, Object> queryMap);  // 获得记录的条数
	public int delete(String ids);// 删除记录
	public OutBack findByStudentNumber(String studentNumber); // 根据学号查找记录
	public List<Map<String,String>> getStats(Map<String, Object> queryMap);
}
