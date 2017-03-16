package com.campus.view.user.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.campus.core.mv.JModelAndView;

@Controller
@RequestMapping("/student")
public class DynamicViewController {

	@RequestMapping("/dynamic.htm")
	public ModelAndView activity_list(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("dynamic.html",11 , request, response);
		return mv;
	}
}
