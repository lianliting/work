package com.ischoolbar.programmer.controller.admin;

import java.io.File;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Admin;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.DormitoryManagementService;
import com.ischoolbar.programmer.service.admin.AdminService;

// RequestMapping用来指定控制器可以处理哪些URL请求
@RequestMapping("/admin/admin")
//标识是处理器类
@Controller
public class AdminController {
	@Autowired
	private AdminService adminService; // 创建管理员服务
	@Autowired
	private DormitoryManagementService dormitoryManagementService; // 创建宿舍管理服务

	// 显示宿舍列表
	// value指定请求的实际地址为“/list”,method指定请求类型为GET（参数直接暴露在URL上）
	@RequestMapping(value="/list",method=RequestMethod.GET)
	// ModelAndView构造方法可以指定返回的页面名称
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("dormitoryManagementList", dormitoryManagementService.findList(queryMap));
		model.setViewName("admin/list"); // 跳转到指定界面
		return model; // 返回ModelAndView对象model
	}
	
	// // 显示宿舍列表
	@RequestMapping(value="/adminlist",method=RequestMethod.GET)
	public ModelAndView adminlist(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("dormitoryManagementList", dormitoryManagementService.findList(queryMap)); 
		model.setViewName("user/adminlist"); // 跳转到指定界面
		return model; // 返回ModelAndView对象model
	}

	//分页显示管理员列表adminlist
	@RequestMapping(value="/adminlist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="adminName",required=false,defaultValue="") String adminName,
			@RequestParam(name="sex",required=false) Integer sex,
			@RequestParam(name="dormsId",required=false) Long dormsId
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 向集合queryMap中插入多个键名及对应键名的键值对象
		queryMap.put("adminName", adminName); // 管理员姓名		
		queryMap.put("sex", sex); // 管理员性别
		queryMap.put("dormsId", dormsId); // 管理员管理的宿舍楼编号
		queryMap.put("offset", page.getOffset()); // 页面偏移量
		queryMap.put("pageSize", page.getRows()); // 页号
		// 向集合ret中插入多个键名及对应键名的键值对象
		ret.put("rows", adminService.adminlist(queryMap)); // 插入管理员列表adminlist
		ret.put("total", adminService.getTotal(queryMap)); // 插入管理员总记录数
		return ret; // 返回ret集合
	}

	// 分页显示管理员列表findList
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="adminName",required=false,defaultValue="") String adminName,
			@RequestParam(name="dormsId",required=false) Long dormsId,
			@RequestParam(name="sex",required=false) Integer sex

			){
		// 创建集合
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		//向集合queryMap中插入多个键名及对应键名的键值对象
		queryMap.put("adminNname", adminName); // 管理员姓名
		queryMap.put("sex", sex); // 管理员性别
		queryMap.put("dormsId", dormsId); // 管理员所管理的宿舍楼编号
		queryMap.put("offset", page.getOffset()); // 页面偏移量
		queryMap.put("pageSize", page.getRows()); // 页面编号
		// 向集合ret中插入多个键名及对应键名的键值对象
		ret.put("rows", adminService.findList(queryMap)); // 插入管理员列表findList
		ret.put("total", adminService.getTotal(queryMap)); // 插入管理员总记录数
		return ret; // 返回ret集合
		
	}

	// 添加管理员
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(HttpServletRequest request,Admin admin){
		// 接收前端传来的值ֵ
		admin.setAdminNumber(request.getParameter("adminNumber"));
		Map<String, String> ret = new HashMap<String, String>();
		// 添加错误
		if(admin == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的管理员信息！");
			return ret;
		}
		if(StringUtils.isEmpty(admin.getAdminNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写工号！");
			return ret;
		}
		// 添加时管理员姓名为空
		if(StringUtils.isEmpty(admin.getAdminName())){
			ret.put("type", "error");
			ret.put("msg", "请填写管理员姓名！");
			return ret;
		}
		// 添加时管理员密码为空
		if(StringUtils.isEmpty(admin.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		// 添加时所属宿舍楼号为空
		if(admin.getDormsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属寝室楼！");
			return ret;
		}
		// 添加时判断管理员工号是否重复
		if(isExis(admin.getAdminNumber(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "该工号已经存在，请重新输入！");
			return ret;
		}
		// 添加时服务出错
		if(adminService.add(admin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		// 将成功的信号值添加到集合中
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}

	// 更改管理员信息
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(HttpServletRequest request,Admin admin){
		// 接收前端传来的值ֵ
		admin.setAdminNumber(request.getParameter("adminNumber"));
		Map<String, String> ret = new HashMap<String, String>();
		// 更改后admin对象值为空时
		if(admin == null){
			ret.put("type", "error");
			ret.put("msg", "请填写信息！");
			return ret;
		}
		// 修改时判断管理员工号是否为空
		if(StringUtils.isEmpty(admin.getAdminNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写工号！");
			return ret;
		}
		// 修改时判断管理员姓名是否为空
		if(StringUtils.isEmpty(admin.getAdminName())){
			ret.put("type", "error");
			ret.put("msg", "请填写管理员姓名！");
			return ret;
		}
		// 修改时管理员所属的宿舍楼号是否为空
		if(admin.getDormsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属寝室楼！");
			return ret;
		}
		// 修改时管理员工号是否已经存在
		if(isExis(admin.getAdminNumber(), admin.getId())){
			ret.put("type", "error");
			ret.put("msg", "该工号已经存在，请重新输入！");
			return ret;
		}
		if(adminService.edit(admin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		// 将成功的信号值添加到集合中
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}

	// 删除管理员(********************************************)
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(String ids){
		Map<String, String> ret = new HashMap<String, String>();
		//
		if(StringUtils.isEmpty(ids)){
			ret.put("type", "error");
			ret.put("msg", "选择要删除的数据！");
			return ret;
		}
		// 
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		//
		if(adminService.delete(ids) <= 0){
			ret.put("type", "error");
			ret.put("msg", "删除失败！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
	
	// 上传头像
	@RequestMapping(value="/upload_photo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadPhoto(MultipartFile photo,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		// 上传头像时没有选择上传的文件
		if(photo == null){
			ret.put("type", "error");
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		// 限制上传头像的大小
		if(photo.getSize() > 1024*1024*1024){
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过10M！");
			return ret;
		}
		// 获取文件后缀
		String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".")+1,photo.getOriginalFilename().length());
		// 上传的文件格式有问题
		if(!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
			return ret;
		}
		// 获取文件的路径
		String savePath = request.getServletContext().getRealPath("/") + "/resources/upload/";
		File savePathFile = new File(savePath);
		if(!savePathFile.exists()){
			//若不存在改目录，则创建目录
			savePathFile.mkdir();
		}
		// 文件名
		String filename = new Date().getTime()+"."+suffix;
		try {
			//将文件保存至指定目录
			photo.transferTo(new File(savePath+filename));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			ret.put("type", "error");
			ret.put("msg", "保存文件异常！");
			e.printStackTrace();
			return ret;
		}
		// 将成功的信号值添加到集合中
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename ); // �ļ��ĸ�Ŀ¼
		return ret;
	}

	// 创建函数：判断管理员工号与数据库中已有的管理员工号是否重复
	private boolean isExis(String adminNumber,Long id){
		// 根据工号查询数据库对应的管理员对象
		Admin admin = adminService.findByAdminNumber(adminNumber);
		// 判断查出来的对象是否为空
		if(admin == null)return false;
		// 判断管理员的记录编号与所查询出来的管理员对象的记录编号是否一致
		if(admin.getId().longValue() == id.longValue())return false;
		return true;
	}
}

