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

import com.ischoolbar.programmer.entity.admin.Admin;
import com.ischoolbar.programmer.entity.admin.Attendancecheck;
//import com.ischoolbar.programmer.entity.admin.Admin;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.AttendancecheckService;
import com.ischoolbar.programmer.service.admin.AdminService;


/**
 * 考勤管理控制器
 * @author 
 *
 */
@RequestMapping("/admin/attendancecheck")
@Controller
public class AttendancecheckController {
	@Autowired
	private AttendancecheckService attendancecheckService;
	@Autowired
	
	private AdminService adminService;
	/**
	 * 考勤列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		model.addObject("adminList", adminService.dorms(queryMap));
		model.setViewName("attendancecheck/list");
		return model;
	}
	/**
	 * 获取考勤列表
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			
	    
	    @RequestParam(name="adminNumber",required=false,defaultValue="") String adminNumber,
			@RequestParam(name="startTime",required=false) String startTime,
			@RequestParam(name="endTime",required=false) String endTime,
			HttpServletRequest request
			
			){
		
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		
		queryMap.put("adminNumber", adminNumber);
		
		queryMap.put("startTime",startTime);
		queryMap.put("endTime",endTime);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", attendancecheckService.findList(queryMap));
		ret.put("total", attendancecheckService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加考勤
	 * @param admin
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String, String> add(Attendancecheck attendancecheck,HttpServletRequest request){
	    attendancecheck.setAdminNumber(request.getParameter("adminNumber"));
	    System.out.print(attendancecheck.getAdminNumber());
	    Map<String, String> ret = new HashMap<String, String>();
		if(attendancecheck == null)
		  {
			ret.put("type", "error");
			ret.put("msg", "请填写正确的考勤信息！");
			return ret;
		}

		if(StringUtils.isEmpty(attendancecheck.getAdminNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写工号！");
			return ret;
		}
		attendancecheck.setTime(new Date());
		if(attendancecheckService.add(attendancecheck) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	
	/**
	 * 批量删除考勤
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
		if(attendancecheckService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
}
