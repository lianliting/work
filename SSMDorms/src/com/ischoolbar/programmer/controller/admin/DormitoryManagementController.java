package com.ischoolbar.programmer.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.DormitoryManagement;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.DormitoryManagementService;
import com.ischoolbar.programmer.service.admin.AdminService;


@RequestMapping("/admin/dormitoryManagement")
@Controller
public class DormitoryManagementController {
	

	@Autowired
	private DormitoryManagementService dormitoryManagementService;

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		model.addObject("adminList", adminService.dorms(queryMap));
		model.setViewName("/dormitoryManagement/list");
		return model;
	}

	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="dorms",required=false,defaultValue="") String dorms,
			@RequestParam(name="adminId",required=false,defaultValue="") String adminId
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("dorms", dorms);
		queryMap.put("adminId", adminId);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", dormitoryManagementService.findList(queryMap));
		ret.put("total", dormitoryManagementService.getTotal(queryMap));
		return ret;
	}

	// 宿舍信息添加
	// value用于表明路径，这个路径会转到该方法里面来（就是根据URL进入方法）
	// method用于表明请求的方法是post
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody // 表示返回的时候只返回一个JSON字符串
	// 设置一个后台宿舍信息添加拦截器
	// 这里意味着DormitoryManagement类的变量dormitoryManagement是从界面传过来的
	public Map<String, String> add(HttpServletRequest request,DormitoryManagement dormitoryManagement) {
		// 接收从前端界面传来的值
		dormitoryManagement.setAdminId(request.getParameter("adminId"));
		// 这里使用ajax请求。ajax的使用就是不想刷新整个页面，所以我们不用重定向，只用返回ajax需要的判断信息
		/* ajax是一种用于创建快速动态网页的技术。
		 * 通过在后台与服务器进行少量数据交换，ajax可以使网页实现异步更新。
		 * 这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
		 * 而传统的网页(不使用 ajax)如果需要更新内容，就必须重载整个网页界面。 */
		// 声明一个Map集合的对象
		Map<String, String> ret = new HashMap<String, String>();
		
		// 如果还没有输入数据信息，就无法进行添加，所以显示输入提醒
		if(dormitoryManagement == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的寝室楼信息！");
			return ret;
		}
		
		// 如果还没有输入寝室楼，就显示输入提醒
		if(StringUtils.isEmpty(dormitoryManagement.getDorms())){
			ret.put("type", "error");
			ret.put("msg", "请填写寝室楼名称！");
			return ret;
		}
		
		// 如果输入的寝室楼已经存在，就无法进行重复添加，所以显示已存在提醒
		if(isExist(dormitoryManagement.getDorms(),0l)){
			ret.put("type", "error");
			ret.put("msg", "该寝室楼已经存在，请重新输入！");
			return ret;
		}
		
		// 如果还没有选择所属管理员，就显示提示信息
		if(dormitoryManagement.getAdminId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属管理员！");
			return ret;
		}
		
		// 如果由于系统自身问题造成的添加失败，就显示提示信息
		if(dormitoryManagementService.add(dormitoryManagement) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		
		// 这里表示是普通链接跳转，若信息添加成功，则直接重定向到"添加成功"提示界面
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(HttpServletRequest request,DormitoryManagement dormitoryManagement){
		dormitoryManagement.setAdminId(request.getParameter("adminId"));
		Map<String, String> ret = new HashMap<String, String>();
		if(dormitoryManagement == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的寝室楼信息！");
			return ret;
		}
		if(StringUtils.isEmpty(dormitoryManagement.getDorms())){
			ret.put("type", "error");
			ret.put("msg", "请填写寝室楼名称！");
			return ret;
		}
		if(dormitoryManagement.getAdminId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属管理员！");
			return ret;
		}
		if(isExist(dormitoryManagement.getDorms(), dormitoryManagement.getId())){
			ret.put("type", "error");
			ret.put("msg", "该用户名已经存在，请重新输入！");
			return ret;
		}
		if(dormitoryManagementService.edit(dormitoryManagement) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	// 宿舍信息删除
	// value用于表明路径，这个路径会转到该方法里面来（就是根据URL进入方法）
	// method用于表明请求的方法是post
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody // 表示返回的时候只返回一个JSON字符串
	// 设置一个后台宿舍信息删除拦截器
	// 这里意味着字符串变量ids是从界面传过来的
	public Map<String, String> delete(String ids){
		// 这里使用ajax请求。ajax的使用就是不想刷新整个页面，所以我们不用重定向，只用返回ajax需要的判断信息
		/* ajax是一种用于创建快速动态网页的技术。
		 * 通过在后台与服务器进行少量数据交换，ajax可以使网页实现异步更新。
		 * 这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
		 * 而传统的网页(不使用 ajax)如果需要更新内容，就必须重载整个网页界面。 */
		// 声明一个Map集合的对象
		Map<String, String> ret = new HashMap<String, String>();
		
		// 如果字符串变量ids为空，意味着此时还没有勾选要删除的信息，还不能进行删除，所以要选择要删除的数据
		if(StringUtils.isEmpty(ids)){
			ret.put("type", "error");
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		
		// 如果字符串ids包含","，意味着此时已经勾选了要删除的数据，每项数据与每项数据之间用","隔开
	    // 所以可以进行删除操作，这里使用subString方法一项一项的删除数据
		if(ids.contains(",")){
			// subString是String的一个方法，作用是返回一个新字符串，它是此字符串的一个子字符串。
			// 新的ids字符串是从原始的ids字符串的第一个字符到倒数第二个字符
			ids = ids.substring(0,ids.length()-1);
		}
		
		// 如果由于系统自身问题造成的添加失败，就显示提示信息
		if(dormitoryManagementService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		
		// 这里表示是普通链接跳转，若信息删除成功，则直接重定向到"删除成功"提示界面
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}

	private boolean isExist(String dorms,Long id){
		DormitoryManagement dormitoryManagement = dormitoryManagementService.findByDormsname(dorms);
		if(dormitoryManagement == null)return false;
		if(dormitoryManagement.getId().longValue() == id.longValue())return false;
		return true;
	}
}
