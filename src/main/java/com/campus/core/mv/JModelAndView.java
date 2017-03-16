package com.campus.core.mv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.campus.core.tools.CommUtil;

/**
 * 基础视图模型
 * @author 刘汉升
 *
 */
public class JModelAndView extends ModelAndView{

	public JModelAndView(String viewName, int type, HttpServletRequest request){
		this(viewName, type, request, null);
	}
	
	public JModelAndView(String viewName, int type, HttpServletRequest request, HttpServletResponse response){
		if(type==0){
			super.setViewName("web/"+viewName);
		}else if(type==1){
			super.setViewName("flow/"+viewName);
		}else if(type==10){
			super.setViewName("user/"+viewName);
		}else if(type==11){
			super.setViewName("user/student/"+viewName);
		}
		if(request != null){
			String ctx = CommUtil.getContextPath(request);
			super.addObject("ctx", ctx);
//			String webPath = CommUtil.getWebPath(request);
//			super.addObject("webPath", webPath);
		}
		super.addObject("CommUtil", CommUtil.getInstance());
	}
	
	public void setViewName(String viewName, int type) {
		if(type==0){
			super.setViewName("web/"+viewName);
		}else if(type==1){
			super.setViewName("flow/"+viewName);
		}else if(type==10){
			super.setViewName("user/"+viewName);
		}else if(type==11){
			super.setViewName("user/student/"+viewName);
		}
	}
}
