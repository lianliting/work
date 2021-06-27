package com.ischoolbar.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ischoolbar.programmer.entity.admin.Admin;
import com.ischoolbar.programmer.entity.admin.Menu;
import com.ischoolbar.programmer.service.admin.AdminService;
import com.ischoolbar.programmer.service.admin.LogService;
import com.ischoolbar.programmer.service.admin.MenuService;
import com.ischoolbar.programmer.util.CpachaUtil;
import com.ischoolbar.programmer.util.MenuUtil;

/**
 * 系统操作类控制器
 * @author
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private AdminService adminService;
	
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LogService logService;
	
	/**
	 * 系统登录后的主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model,HttpServletRequest request){
		List<Menu> adminMenus = (List<Menu>)request.getSession().getAttribute("adminMenus");
		model.addObject("topMenuList", MenuUtil.getAllTopMenu(adminMenus));
		model.addObject("secondMenuList", MenuUtil.getAllSecondMenu(adminMenus));
		model.setViewName("system/index");
		return model;//WEB-INF/views/+system/index+.jsp = WEB-INF/views/system/index.jsp
	}
	
	/**
	 * 系统登录后的欢迎页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model){
		model.setViewName("system/welcome");
		return model;
	}
	/**
	 * 登陆页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("system/login");
		return model;
	}
	
	/**
	 * 登录表单提交处理控制器
	 * @param admin
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> loginAct(Admin admin,String cpacha,HttpServletRequest request){
		admin.setAdminNumber(request.getParameter("adminNumber"));
		Map<String, String> ret = new HashMap<String, String>();
		// 当管理员admin对象为空时
		if(admin == null){
			ret.put("type", "error");
			ret.put("msg", "请填写信息！");
			return ret;
		}
		// 当验证码为空时
		if(StringUtils.isEmpty(cpacha)){
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			return ret;
		}
		// 当管理员的工号为空时
		if(StringUtils.isEmpty(admin.getAdminNumber())){
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		// 当管理员的密码为空时
		if(StringUtils.isEmpty(admin.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		// 接收前端的验证码
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		// 判断前端验证码是否还存在
		if(loginCpacha == null){
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		// 判断用户所输入的验证码是否与前端所要求的验证码一致
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			logService.add("工号为"+admin.getAdminNumber()+"登录时输入验证码错误!");
			return ret;
		}
		// 根据管理员工号查找到对应的管理员对象
		Admin findByAdminNumber = adminService.findByAdminNumber(admin.getAdminNumber());
		if(findByAdminNumber == null){
			ret.put("type", "error");
			ret.put("msg", "该用户不存在！");
			logService.add("登录时，工号为"+admin.getAdminNumber()+"不存在!");
			return ret;
		}
		if(!admin.getPassword().equals(findByAdminNumber.getPassword())){
			ret.put("type", "error");
			ret.put("msg", "密码错误！");
			//logService.add(user.getUsername()+"登录时");
			return ret;
		}
		//说明工号密码及验证码都正确
		String menuIds = "1,14,15,24,25,26,35,36,37,38,40,41,42,43,45,46,47,48,49,50,51,54,55,56,57,58,59,60,61,62,63,64";
		List<Menu> adminMenus = menuService.findListByIds(menuIds);
		//把角色信息、菜单信息放到session中
		request.getSession().setAttribute("admin", findByAdminNumber);
		request.getSession().setAttribute("adminMenus", adminMenus);
		ret.put("type", "success");
		ret.put("msg", "登录成功！");
		logService.add("管理员{"+admin.getAdminNumber()+"}登录成功!");
		return ret;
	}
	
	/**
	 * 后台退出注销功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
		request.getSession().setAttribute("adminMenus", null);
		return "redirect:login";
	}
	
	/**
	 * 修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.GET)
	public ModelAndView editPassword(ModelAndView model){
		model.setViewName("system/edit_password");
		return model;
	}
	
	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(newpassword)){
			ret.put("type", "error");
			ret.put("msg", "请填写新密码！");
			return ret;
		}
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(!admin.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误！");
			return ret;
		}
		admin.setPassword(newpassword);
		if(adminService.editPassword(admin) <= 0){
			ret.put("type", "error");
			ret.put("msg", "密码修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功！");
		logService.add("工号为{"+admin.getAdminNumber()+"}，成功修改密码!");
		return ret;
	} 
	
	/**
	 * 本系统所有的验证码均采用此方法
	 * @param vcodeLen
	 * @param width
	 * @param height
	 * @param cpachaType:用来区别验证码的类型，传入字符串
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl",required=false,defaultValue="4") Integer vcodeLen,
			@RequestParam(name="w",required=false,defaultValue="100") Integer width,
			@RequestParam(name="h",required=false,defaultValue="30") Integer height,
			@RequestParam(name="type",required=true,defaultValue="loginCpacha") String cpachaType,
			HttpServletRequest request,
			HttpServletResponse response){
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
