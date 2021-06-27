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

import com.ischoolbar.programmer.entity.admin.Student;
import com.ischoolbar.programmer.page.admin.Page;
import com.ischoolbar.programmer.service.admin.DormitoryManagementService;
import com.ischoolbar.programmer.service.admin.StudentService;

// RequestMapping用来指定控制器可以处理哪些URL请求
@RequestMapping("/admin/student")
//标识是处理器类
@Controller
public class StudentController {
	@Autowired
	private StudentService studentService; // 创建管理员服务
	@Autowired
	private DormitoryManagementService dormitoryManagementService; // 创建宿舍管理服务

	// 显示宿舍列表
	// value指定请求的实际地址为“/list”,method指定请求类型为GET（参数直接暴露在URL上）
	@RequestMapping(value="/list",method=RequestMethod.GET)
	// ModelAndView构造方法可以指定返回的页面名称
	public ModelAndView list(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("dormitoryManagementList", dormitoryManagementService.findList(queryMap)); // 将宿舍列表对象添加到模型中
		model.setViewName("student/list"); // 跳转到指定界面
		return model; // 返回ModelAndView对象model
	}
	
	// 显示宿舍列表
	@RequestMapping(value="/studentlist",method=RequestMethod.GET)
	public ModelAndView studentlist(ModelAndView model){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		model.addObject("dormitoryManagementList", dormitoryManagementService.findList(queryMap)); // 将宿舍列表对象添加到模型中
		model.setViewName("student/studentlist"); // 跳转到指定界面
		return model; // 返回ModelAndView对象model
	}

	// 分页显示学生列表studentlist
	@RequestMapping(value="/studentlist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="studentName",required=false,defaultValue="") String studentName,
			@RequestParam(name="sex",required=false) Integer sex,
			@RequestParam(name="dormsId",required=false) Long dormsId
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 向集合queryMap中插入多个键名及对应键名的键值对象
		queryMap.put("studentName", studentName); // 学生姓名	
		queryMap.put("sex", sex); // 学生性别
		queryMap.put("dormsId", dormsId); // 学生的宿舍楼编号
		queryMap.put("offset", page.getOffset()); // 页面偏移量
		queryMap.put("pageSize", page.getRows()); // 页号
		// 向集合ret中插入多个键名及对应键名的键值对象
		ret.put("rows", studentService.studentlist(queryMap)); // 插入学生列表studentlist
		ret.put("total", studentService.getTotal(queryMap)); // 插入学生总记录数
		return ret; // 返回ret集合
	}

	// 分页显示学生列表findList,根据搜索条件查找
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="studentName",required=false,defaultValue="") String studentName,
			@RequestParam(name="dormsId",required=false) Long dormsId,
			@RequestParam(name="sex",required=false) Integer sex,
			@RequestParam(name="studentNumber",required=false,defaultValue="") String studentNumber,
			@RequestParam(name="numbers",required=false,defaultValue="") String numbers
			){
		// 创建集合
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		// 向集合queryMap中插入多个键名及对应键名的键值对象
		queryMap.put("studentName", studentName); // 学生姓名
		queryMap.put("studentNumber", studentNumber); // 学号
		queryMap.put("sex", sex); // 学生性别
		queryMap.put("dormsId", dormsId); // 学生的宿舍楼编号
		queryMap.put("numbers", numbers); // 寝室号
		queryMap.put("offset", page.getOffset()); // 页面偏移量
		queryMap.put("pageSize", page.getRows()); // 页面编号
		// 向集合ret中插入多个键名及对应键名的键值对象
		ret.put("rows", studentService.findList(queryMap)); // 插入学生列表findList
		ret.put("total", studentService.getTotal(queryMap)); // 插入学生总记录数
		return ret; // 返回ret集合
	}

	// 添加学生
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(HttpServletRequest request,Student student){
		// 接收前端传来的值ֵ
		student.setStudentNumber(request.getParameter("studentNumber"));
		Map<String, String> ret = new HashMap<String, String>();
		// 添加错误
		if(student == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的学生信息！");
			return ret;
		}
		// 添加是学生学号为空
		if(StringUtils.isEmpty(student.getStudentNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写学号！");
			return ret;
		}
		// 添加时学生姓名为空
		if(StringUtils.isEmpty(student.getStudentName())){
			ret.put("type", "error");
			ret.put("msg", "请填写学生姓名！");
			return ret;
		}
		// 添加时所属宿舍楼号为空
		if(student.getDormsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属寝室楼！");
			return ret;
		}
		// 添加时判断学生学号是否重复
		if(isExis(student.getStudentNumber(), 0l)){
			ret.put("type", "error");
			ret.put("msg", "该学工号已经存在，请重新输入！");
			return ret;
		}
		// 添加时服务出错
		if(studentService.add(student) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		// 将成功的信号值添加到集合中
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}

	// 更改学生信息
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(HttpServletRequest request,Student student){
		// 接收前端传来的值ֵ
		student.setStudentNumber(request.getParameter("studentNumber"));
		Map<String, String> ret = new HashMap<String, String>();
		// 更改后student对象值为空时
		if(student == null){
			ret.put("type", "error");
			ret.put("msg", "请填写信息！");
			return ret;
		}
		// 修改时判断学生学号是否为空
		if(StringUtils.isEmpty(student.getStudentNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写学号！");
			return ret;
		}
		// 修改时判断学生姓名是否为空
		if(StringUtils.isEmpty(student.getStudentName())){
			ret.put("type", "error");
			ret.put("msg", "请填写学生姓名！");
			return ret;
		}
		// 修改时学生所属的宿舍楼号是否为空
		if(student.getDormsId() == null){
			ret.put("type", "error");
			ret.put("msg", "请选择所属寝室楼！");
			return ret;
		}
		// 修改时学生学号是否已经存在
		if(isExis(student.getStudentNumber(), student.getId())){
			ret.put("type", "error");
			ret.put("msg", "该学号已经存在，请重新输入！");
			return ret;
		}
		if(studentService.edit(student) <= 0){
			ret.put("type", "error");
			ret.put("msg", "添加失败！");
			return ret;
		}
		// 将成功的信号值添加到集合中
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}

	// 删除学生(********************************************)
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
		if(studentService.delete(ids) <= 0){
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
		ret.put("filepath",request.getServletContext().getContextPath() + "/resources/upload/" + filename ); // 文件的根目录
		return ret;
	}

	// 创建函数：判断学生学号与数据库中已有的学生学号是否重复
	private boolean isExis(String studentNumber,Long id){
		// 根据工号查询数据库对应的学生员对象
		Student student = studentService.findByStudentNumber(studentNumber);
		// 判断查出来的对象是否为空
		if(student == null)return false;
		// 判断学生的记录编号与所查询出来的学生对象的记录编号是否一致
		if(student.getId().longValue() == id.longValue())return false;
		return true;
	}
}

