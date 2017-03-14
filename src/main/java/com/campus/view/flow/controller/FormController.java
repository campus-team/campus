package com.campus.view.flow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.campus.core.mv.JModelAndView;

@Controller
@RequestMapping("/form")
public class FormController {

	@RequestMapping("/form_list.htm")
	public ModelAndView form_list(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("form_list.html", 1, request);
		return mv;
	}
	@RequestMapping("/form_designer.htm")
	public ModelAndView form_designer(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("form_designer.html", 1, request);
		return mv;
	}
}
