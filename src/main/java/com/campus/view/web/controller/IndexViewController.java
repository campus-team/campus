package com.campus.view.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.campus.core.mv.JModelAndView;
import com.campus.foundation.domain.Student;
import com.campus.foundation.service.IStudentService;

/**
 * 主页控制器
 * @author 刘汉升
 */
@Controller
public class IndexViewController {

	@Autowired  
    private IStudentService studentService;
	
	@RequestMapping("/index.htm")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("index.html",0 , request, response);
		return mv;
	}
	
	@RequestMapping("/header.htm")
	public ModelAndView header(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("header.html",0 , request, response);
		return mv;
	}
	
	@RequestMapping("/test.htm")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("index.html",0 , request, response);
		Student stu = new Student();
		stu.setName("Hanson");
		stu.setSex(true);
		stu.setNumber("1412180205");
		this.studentService.save(stu);
		mv.addObject("aa", "测试"); 
		return mv;
	}
}
