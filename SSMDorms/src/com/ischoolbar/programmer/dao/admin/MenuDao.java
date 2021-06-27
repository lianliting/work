package com.ischoolbar.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ischoolbar.programmer.entity.admin.Menu;

/**
 * 菜单管理dao
 * @author llq
 *
 */
@Repository
public interface MenuDao {
	public int add(Menu menu); // 菜单插入操作
	public List<Menu> findList(Map<String, Object> queryMap);// 菜单信息模糊分页搜索查询
	public List<Menu> findTopList(); // 获取顶级菜单信息
	public int getTotal(Map<String, Object> queryMap);// 菜单信息模糊分页搜索查询总记录数
	public int edit(Menu menu);// 修改菜单信息
	public int delete(Long id);// 删除菜单信息
	public List<Menu> findChildernList(Long parentId);// 获取某一分类的子菜单信息
	public List<Menu> findListByIds(String ids);// 根据菜单id获取菜单信息
}
