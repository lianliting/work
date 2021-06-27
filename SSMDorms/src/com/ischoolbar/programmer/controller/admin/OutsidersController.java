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

import com.ischoolbar.programmer.entity.admin.Outsiders;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.OutsidersService;

/**
 * 外来人员记录管理控制器
 * @author 
 *
 */
@RequestMapping("/admin/outsiders")
@Controller
public class OutsidersController {

	@Autowired
	private OutsidersService outsidersService;
	
	/**
	 * 记录列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("outsidersList", outsidersService.findList(queryMap));
		model.setViewName("outsiders/list");
		return model;
	}
	
	/**
	 * 获取记录列表
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="outName",required=false,defaultValue="") String outName,
			@RequestParam(name="outIdCard",required=false,defaultValue="") String outIdCard,
			@RequestParam(name="sex",required=false) Integer sex,
			@RequestParam(name="startTime",required=false) String startTime,
			@RequestParam(name="endTime",required=false) String endTime,
			HttpServletRequest request
	
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		
		queryMap.put("outName", outName);
		queryMap.put("outIdCard", outIdCard);
		queryMap.put("sex", sex);
		queryMap.put("startTime",startTime);
		queryMap.put("endTime",endTime);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", outsidersService.findList(queryMap));
		ret.put("total", outsidersService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 添加人员记录
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String, String> add(Outsiders outsiders,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(outsiders == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的人员记录信息！");
			return ret;
		}

		if(StringUtils.isEmpty(outsiders.getOutName())){
			ret.put("type", "error");
			ret.put("msg", "请填写姓名！");
			return ret;
		}
		if(StringUtils.isEmpty(outsiders.getOutIdCard())){
			ret.put("type", "error");
			ret.put("msg", "请填写身份证号！");
			return ret;
		}
		if(StringUtils.isEmpty(outsiders.getReason())){
			ret.put("type", "error");
			ret.put("msg", "请填写理由！");
			return ret;
		}
		outsiders.setTime(new Date());
		if(outsidersService.add(outsiders) <= 0){
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
		if(outsidersService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
}
