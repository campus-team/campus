package com.campus.view.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.campus.core.exception.BusinessException;
import com.campus.core.mv.JModelAndView;
import com.campus.core.tools.CommUtil;
import com.campus.core.tools.Md5Encrypt;
import com.campus.core.tools.WebFormHelper;
import com.campus.foundation.domain.Student;
import com.campus.foundation.domain.Teacher;
import com.campus.foundation.domain.User;
import com.campus.foundation.service.IStudentService;
import com.campus.foundation.service.ITeacherService;
import com.campus.foundation.service.IUserService;

@Controller
public class LoginViewController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired
	private ITeacherService teacherService;
	
	@RequestMapping("/login.htm")
	public ModelAndView login(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("login.html", 0, request);
		return mv;
	}
	
	@RequestMapping("/login_submit.htm")
	public ModelAndView login_submit(HttpServletRequest request, HttpServletResponse response, String userName, String password, String user_type){
		JModelAndView mv = new JModelAndView("index.html", 0, request, response);
		User user = this.userService.getObjByProperty("userName", userName);
		if(user != null){
			password = Md5Encrypt.md5(password);
			if(user.getUser_type().toString().equals(user_type) && user.getPassword().equals(password)){
				System.out.println("登录成功");
				WebUtils.setSessionAttribute(request, "current_user_id", user.getId());
			}
			// 登录后跳转回原来请求的页面
			String login_target_url = (String) WebUtils.getSessionAttribute(request, "login_target_url");
			if(CommUtil.isNotNull(login_target_url)){
				try {
					response.sendRedirect(login_target_url);
				} catch (IOException e) {
					e.printStackTrace();
				}
				WebUtils.setSessionAttribute(request, "login_target_url", null);
			}
		}else{
			System.out.println("此用户不存在");
			mv.setViewName("login.html", 0);
		}
		return mv;
	}
	
	@RequestMapping("/logout.htm")
	public ModelAndView logout(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("login.html", 0, request);
		WebUtils.setSessionAttribute(request, "current_user_id", null);
		return mv;
	} 
	
	@RequestMapping("/register.htm")
	public ModelAndView register(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("register.html", 0, request);
		return mv;
	}
	
	@RequestMapping("/register_user.htm")
	public ModelAndView register_user(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("register_user.html", 0, request);
		return mv;
	}
	
	@RequestMapping("/register_org.htm")
	public ModelAndView register_org(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("register_org.html", 0, request);
		return mv;
	}
	
	@RequestMapping("/find_password.htm")
	public ModelAndView find_password(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("find_password.html", 0, request);
		return mv;
	}
	
	@RequestMapping("/register_submit.htm")
	public ModelAndView register_submit(HttpServletRequest request, String user_type){
		JModelAndView mv = new JModelAndView("index.html", 0, request);
		User user = WebFormHelper.toPo(request, User.class);
		String password = request.getParameter("password");
		password = Md5Encrypt.md5(password);System.out.println("password="+password);
		user.setPassword(password);
		boolean result = this.userService.save(user);
		System.out.println("user result "+result);
		if("0".equals(user_type)){
			Student student = new Student();
			student.setUser(user);
			result = this.studentService.save(student);
			System.out.println("student result "+result);
		}else if("1".equals(user_type)){
			
		}else if("2".equals(user_type)){
			
		}
		return mv;
	}
	
	@RequestMapping("/register_test.htm")
	public ModelAndView register_test(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("login.html", 0, request);
		User user = new User();
		user.setUserName("teacher");
		user.setPassword(Md5Encrypt.md5("123456"));
		user.setUser_type(Integer.valueOf(1));
		this.userService.save(user);
		Teacher teacher = new Teacher();
		teacher.setUser(user);
		this.teacherService.save(teacher);
		return mv;
	}
	@RequestMapping("/err.htm")
	public ModelAndView err(HttpServletRequest request, String user_type){
		BusinessException e = new BusinessException("发生错误");
		e.setRedirect_url("index.htm");
		e.setData(new Object[]{false});
		throw e;
		//return null;
	}
}
