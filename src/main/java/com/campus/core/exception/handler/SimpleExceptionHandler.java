package com.campus.core.exception.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.campus.core.exception.BusinessException;
import com.campus.core.mv.JModelAndView;
import com.campus.core.tools.CommUtil;

/**
 * 统一的异常处理类
 * @author 刘汉升
 *
 */
public class SimpleExceptionHandler implements HandlerExceptionResolver{
	
	private static final Logger log = LoggerFactory.getLogger(SimpleExceptionHandler.class); 
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		JModelAndView mv = new JModelAndView("error.html", 0, request);
		log.info(ex.getMessage());
		// 判断是否ajax请求
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            mv.addObject("result", false);
            if (ex instanceof BusinessException) {
                mv.addObject("error_msg", ex.getMessage());
        		mv.addObject("original_url", request.getRequestURI());
        		BusinessException e = (BusinessException)ex;
        		if(CommUtil.isNotNull(e.getRedirect_url())){
        			mv.addObject("redirect_url", e.getRedirect_url());
        			// 如果是未登录，  登录后则自动跳转到原来请求的页面
    				if(e.getError_code() == BusinessException.Code.NOT_LOGIN.ordinal()){
    					WebUtils.setSessionAttribute(request, "login_target_url", request.getRequestURI());
    				}
        			if(e.getRedirect_now()){ // 不经过提示页面直接跳转
        				try {
            				response.sendRedirect(e.getRedirect_url());
            			} catch (IOException e1) {
            				e1.printStackTrace();
            			}
        			}
        		}
            } else {
                mv.addObject("error_msg", "系统异常！");
                log.error(ex.getMessage(), ex); // 系统异常则抛出
            }
            
    		return mv;
        } else {
        // 如果是ajax请求，JSON格式返回
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("result", false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (ex instanceof BusinessException) {
                    map.put("error_msg", ex.getMessage());
                } else {
                    map.put("error_msg", "系统异常！");
                }
                writer.write(new JSONObject(map).toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
	}

}
