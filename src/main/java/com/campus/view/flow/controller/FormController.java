package com.campus.view.flow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.campus.core.mv.JModelAndView;
import com.campus.flow.domain.FormFolder;
import com.campus.flow.service.IFormFolderService;

@Controller
@RequestMapping("/form")
public class FormController {

	@Autowired
	private IFormFolderService formFolderService;
	
	@RequestMapping("/form_list.htm")
	public ModelAndView form_list(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("form_list.html", 1, request);
		List<FormFolder> first_folders = this.formFolderService.query("select obj from FormFolder obj where obj.parent is null",null,-1,-1);
		mv.addObject("first_folders", first_folders);
		return mv;
	}
	@RequestMapping("/form_designer.htm")
	public ModelAndView form_designer(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("form_designer.html", 1, request);
		return mv;
	}
}
