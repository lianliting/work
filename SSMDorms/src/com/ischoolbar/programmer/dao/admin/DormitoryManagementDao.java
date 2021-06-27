package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.DormitoryManagement;

/**

 * @author 
 *
 */
@Repository
public interface DormitoryManagementDao {
	// 宿舍信息添加
	public int add(DormitoryManagement dormitoryManagement);
	public DormitoryManagement findByDormsname(String dorms);
	public List<DormitoryManagement> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int edit(DormitoryManagement dormitoryManagement);
	// 宿舍信息删除
	public int delete(String ids);
	public DormitoryManagement find(Long id);
	
}
