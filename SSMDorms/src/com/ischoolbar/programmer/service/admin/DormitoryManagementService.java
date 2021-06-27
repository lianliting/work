package com.ischoolbar.programmer.service.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.DormitoryManagement;


/**
 * 宿舍楼管理
 * @author
 *
 */
@Repository
public interface DormitoryManagementService {
	public int add(DormitoryManagement dormitoryManagement);
	public int edit(DormitoryManagement dormitoryManagement);
	public DormitoryManagement findByDormsname(String dorms);
	public int delete(String ids);
	public List<DormitoryManagement> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public DormitoryManagement find(Long id);

}
