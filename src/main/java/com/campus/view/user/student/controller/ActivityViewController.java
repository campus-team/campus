package com.campus.view.user.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.campus.core.exception.BusinessException;
import com.campus.core.mv.JModelAndView;
import com.campus.core.tools.CommUtil;

@Controller
@RequestMapping("/student")
public class ActivityViewController {
	
	@RequestMapping("/activity_list.htm")
	public ModelAndView activity_list(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("activity_list.html",11 , request, response);
		return mv;
	}
	
	@RequestMapping("/activity_add.htm")
	public ModelAndView activity_add(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("activity_add.html",11 , request, response);
		return mv;
	}
}


