package com.campus.core.mv;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.campus.core.tools.CommUtil;
import com.campus.core.tools.HttpInclude;
import com.campus.foundation.domain.User;
import com.campus.foundation.service.IUserService;
import com.campus.foundation.service.impl.UserServiceImpl;


/**
 * 基础视图模型
 * @author 刘汉升
 *
 */
public class JModelAndView extends ModelAndView{
	
	private static final Logger log = LoggerFactory.getLogger(JModelAndView.class);

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
		}else if(type==12){
			super.setViewName("user/teacher/"+viewName);
		}else if(type==13){
			super.setViewName("user/business/"+viewName);
		}
		if(request != null){
			String ctx = CommUtil.getContextPath(request);
			super.addObject("ctx", ctx);
//			String webPath = CommUtil.getWebPath(request);
//			super.addObject("webPath", webPath);
		}
		super.addObject("CommUtil", CommUtil.getInstance());
		if(request!=null && response!=null){
			super.addObject("HttpInclude", new HttpInclude(request, response));
		}
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		if(WebUtils.getSessionAttribute(request, "current_user_id")!=null){
			Long current_user_id = (Long)WebUtils.getSessionAttribute(request, "current_user_id");
			IUserService userService = (IUserService) context.getBean(IUserService.class);
			User user = userService.getObjById(current_user_id);
			super.addObject("current_user", user);
			log.info("当前用户："+user.getUserName());
		}
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
		}else if(type==12){
			super.setViewName("user/teacher/"+viewName);
		}else if(type==13){
			super.setViewName("user/business/"+viewName);
		}
	}
}
