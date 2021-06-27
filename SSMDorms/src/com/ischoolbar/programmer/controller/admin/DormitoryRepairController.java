package com.ischoolbar.programmer.controller.admin;

import java.util.Date;
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

import com.ischoolbar.programmer.entity.admin.DormitoryRepair;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AdminService;
import com.ischoolbar.programmer.service.admin.DormitoryManagementService;
import com.ischoolbar.programmer.service.admin.StudentService;
import com.ischoolbar.programmer.service.admin.DormitoryRepairService;

/**
 * 宿舍报修记录管理控制器
 * @author 
 *
 */
@RequestMapping("/admin/dormitoryRepair")
@Controller
public class DormitoryRepairController {

	@Autowired
	private DormitoryRepairService dormitoryRepairService;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DormitoryManagementService dormitoryManagementService;
	
	/**
	 * 记录列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		model.addObject("adminList", adminService.dorms(queryMap));
		model.addObject("studentList", studentService.studentlist(queryMap));
		model.addObject("dormitoryManagementList",dormitoryManagementService.findList(queryMap));
		model.setViewName("/dormitoryRepair/list");
		return model;
	}

	/**
	 * 获取记录列表
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="dorms",required=false) Integer dorms,
			@RequestParam(name="numbers",required=false,defaultValue="") String numbers,
			@RequestParam(name="contents",required=false,defaultValue="") String contents,
			@RequestParam(name="adminId",required=false,defaultValue="") String adminId,
			@RequestParam(name="startTime",required=false) String startTime,
			@RequestParam(name="endTime",required=false) String endTime,
			HttpServletRequest request
	
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		queryMap.put("dorms", dorms);
		queryMap.put("numbers", numbers);
		queryMap.put("contents", contents);
		queryMap.put("adminId", adminId);
		queryMap.put("startTime",startTime);
		queryMap.put("endTime",endTime);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", dormitoryRepairService.findList(queryMap));
		ret.put("total", dormitoryRepairService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加宿舍报修记录
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String, String> add(DormitoryRepair dormitoryRepair,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(dormitoryRepair == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的宿舍报修记录信息！");
			return ret;
		}
		
		if(StringUtils.isEmpty(dormitoryRepair.getNumbers())){
			ret.put("type", "error");
			ret.put("msg", "请填写宿舍号！");
			return ret;
		}
		
		if(StringUtils.isEmpty(dormitoryRepair.getContents())){
			ret.put("type", "error");
			ret.put("msg", "请填写报修内容！");
			return ret;
		}
		
		if(dormitoryRepair.getAdminId() == 0){
			ret.put("type", "error");
			ret.put("msg", "请选择报修人！");
			return ret;
		}
		
		dormitoryRepair.setTime(new Date());
		int dormsId = adminService.findDormsId(dormitoryRepair.getAdminId());
		dormitoryRepair.setDorms(dormsId);
		if(dormitoryRepairService.add(dormitoryRepair) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	
	/**
	 * 批量删除宿舍报修记录
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String ids){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(ids)){
			ret.put("type", "error");
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		
		if(dormitoryRepairService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败！");
			return ret;
		}
		
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
}
