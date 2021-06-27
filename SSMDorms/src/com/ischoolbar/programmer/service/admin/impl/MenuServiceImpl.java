package com.ischoolbar.programmer.service.admin.impl;
/**
 * 菜单管理实现类
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.MenuDao;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.service.admin.MenuService;
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao; // 创建menuDao对象
	
	// 菜单插入操作
	@Override
	public int add(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.add(menu);
	}

	// 菜单信息模糊分页搜索查询
	@Override
	public List<Menu> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menuDao.findList(queryMap);
	}

	// 获取顶级菜单信息
	@Override
	public List<Menu> findTopList() {
		// TODO Auto-generated method stub
		return menuDao.findTopList();
	}

	// 菜单信息模糊分页搜索查询总记录数
	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return menuDao.getTotal(queryMap);
	}

	// 修改菜单信息
	@Override
	public int edit(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.edit(menu);
	}

	// 删除菜单信息
	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return menuDao.delete(id);
	}

	// 获取某一分类的子菜单信息
	@Override
	public List<Menu> findChildernList(Long parentId) {
		// TODO Auto-generated method stub
		return menuDao.findChildernList(parentId);
	}

	// 根据菜单id获取菜单信息
	@Override
	public List<Menu> findListByIds(String ids) {
		// TODO Auto-generated method stub
		return menuDao.findListByIds(ids);
	}

}
