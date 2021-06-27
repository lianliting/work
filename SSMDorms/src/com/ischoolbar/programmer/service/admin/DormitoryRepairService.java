package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.entity.admin.DormitoryRepair;

@Service
public interface DormitoryRepairService {
	public int add(DormitoryRepair dormitoryRepair); // 添加一条宿舍报修记录
	public List<DormitoryRepair> findList(Map<String, Object> queryMap); // 分页查询记录列表
	public int getTotal(Map<String, Object> queryMap); // 获得记录总数
	public int delete(String ids); // 删除一条记录
	public DormitoryRepair findByNumbers(String numbers);
	public List<Map<String,String>> getStats(Map<String, Object> queryMap);
}
