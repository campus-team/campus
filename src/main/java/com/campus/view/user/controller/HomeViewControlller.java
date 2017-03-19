package com.campus.view.user.controller;

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
import com.campus.foundation.domain.User;
import com.campus.foundation.service.IUserService;

@Controller
@RequestMapping("/user")
public class HomeViewControlller {

	@Autowired
	private IUserService userService;
	
	@RequestMapping("/home.htm")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new JModelAndView("home.html",10 , request, response);
		Long current_user_id = (Long) WebUtils.getSessionAttribute(request, "current_user_id");
		if(CommUtil.isNull(current_user_id)){
			BusinessException e = new BusinessException("您还未登录");
			e.setRedirect_url(CommUtil.getContextPath(request) + "/login.htm");
			e.setError_code(BusinessException.Code.NOT_LOGIN.ordinal());
			throw e;
		}
		return mv;
	}
	
	@RequestMapping("/home_left.htm")
	public ModelAndView home_left(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = null;
		Long current_user_id = (Long) WebUtils.getSessionAttribute(request, "current_user_id");
		if(CommUtil.isNull(current_user_id)){
			BusinessException e = new BusinessException("您还未登录");
			e.setRedirect_url(CommUtil.getContextPath(request) + "/login.htm");
			e.setError_code(BusinessException.Code.NOT_LOGIN.ordinal());
			throw e;
		}
		User user = this.userService.getObjById(current_user_id);
		if(user.getUser_type().intValue() == 0){
			mv = new JModelAndView("home_left.html",11 , request, response);
		}else if(user.getUser_type().intValue() == 1){
			mv = new JModelAndView("home_left.html",12 , request, response);
		}
		return mv;
	}
}
