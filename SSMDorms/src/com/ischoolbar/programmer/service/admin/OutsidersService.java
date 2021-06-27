package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.Outsiders;

@Service
public interface OutsidersService {
	public int add(Outsiders outsiders); // 添加一条外来人员记录
	public int addOutName(String outName); 
	public int addOutIdCard(String outIdCard);
	public List<Outsiders> findList(Map<String, Object> queryMap); // 分页查询记录列表
	public int getTotal(Map<String, Object> queryMap); // 获得记录总数
	public int delete(String ids); // 删除一条记录
	public Outsiders findByOutName(String outName); // 通过外来人员姓名查询对应的记录
	public Outsiders findByOutIdCard(String outIdCard); // 通过身份证号查询对应的记录
	public List<Map<String,String>> getStats(Map<String, Object> queryMap);
}
