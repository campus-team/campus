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

	public JModelAndView(String viewName, int type, HttpServletRequest request, HttpServletResponse response){
		if(type==0){
			super.setViewName("web/"+viewName);
		}
		String webPath = CommUtil.getWebPath(request);
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
		super.addObject("webPath", webPath);
		super.addObject("contextPath", contextPath);
		super.addObject("CommUtil", new CommUtil());
	}
}
