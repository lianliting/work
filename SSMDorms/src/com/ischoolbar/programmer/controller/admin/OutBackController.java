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

import com.ischoolbar.programmer.entity.admin.OutBack;
import com.ischoolbar.programmer.entity.admin.Student;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.OutBackService;
import com.ischoolbar.programmer.service.admin.StudentService;

@RequestMapping("/admin/outback")
@Controller
public class OutBackController {

	@Autowired
	private OutBackService outBackService;
	@Autowired
	private StudentService studentService;
	
	/**
	 * 早出晚归列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("studentList", studentService.findList(queryMap));
		model.setViewName("outback/list");
		return model;
	}
	/**
	 * 获取早出晚归列表
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="studentNumber",required=false,defaultValue="") String studentNumber,
			@RequestParam(name="studentName",required=false,defaultValue="") String studentName,
			@RequestParam(name="startTime",required=false) String startTime,
			@RequestParam(name="endTime",required=false) String endTime
			
			
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		if("-1".equals(studentName) || StringUtils.isEmpty(studentName)){
			queryMap.put("studentNumber", studentNumber);
		} else {
			Student student = studentService.findByStudentNumber(studentName);
			System.out.print(student);
			queryMap.put("studentNumber", student.getStudentNumber());
		}
		
		queryMap.put("startTime",startTime);
		queryMap.put("endTime",endTime);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", outBackService.findList(queryMap));
		ret.put("total", outBackService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加记录
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String, String> add(OutBack outBack,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(outBack == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的信息！");
			return ret;
		}

		if(StringUtils.isEmpty(outBack.getStudentNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写学号！");
			return ret;
		}
		if(StringUtils.isEmpty(outBack.getReason())){
			ret.put("type", "error");
			ret.put("msg", "请填写理由！");
			return ret;
		}
		outBack.setTime(new Date());
		if(outBackService.add(outBack) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
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
		if(outBackService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
}
