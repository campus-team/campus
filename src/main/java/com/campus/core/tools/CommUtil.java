package com.campus.core.tools;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共工具类
 * @author:刘汉升
 */
public class CommUtil {

	private static final Logger log = LoggerFactory.getLogger(CommUtil.class);
	
	private static CommUtil instance = new CommUtil();
	
	private CommUtil(){}
	
	/**
	 * 单例模式
	 * @return
	 */
	public static CommUtil getInstance(){
		return instance;
	}
	
	/**
    * 判断对象是否为null或者空字符串
    * @author:刘汉升
    */
	public static boolean isNotNull(Object obj)
	{
	   return (obj != null) && (!obj.toString().equals(""));
	}
	
	public static boolean isNull(Object obj)
	{
	   return (obj == null) || (obj.toString().equals(""));
	}
	
	/**
	 * 转整型
	 * @param obj
	 * @author:刘汉升
	 */
	public static int null2Int(Object obj){
		int value = 0;
		try{
			value = Integer.parseInt(obj.toString());
		}catch(Exception e){
			log.info("整型转换错误", e);
		}
	    return value;
	}
	
	/**
	 * 获取请求的上下文路径
	 * @author 刘汉升
	 * @param request
     * @return 上下文路径（/+项目名）
	 */
	public static String getContextPath(HttpServletRequest request){
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
		return contextPath;
	}
	
	/**
     * 获取请求的绝对路径（URL去掉资源路径）
     * @author 刘汉升
     * @param request
     * @return 请求的绝对路径（协议+服务器名+端口+项目名）
     */
	public static String getWebPath(HttpServletRequest request) {
		String url = "http://" + request.getServerName();
			if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
				url = url + ":" + null2Int(Integer.valueOf(request.getServerPort())) + getContextPath(request);
			else {
				url = url + getContextPath(request);
			}
		return url;
	}
	
	/**
     * 获得工程的classpath根目录
     * @author 刘汉升
     * @return String
     */
	public static String getClasspath() {
	    try {
	    	// 随便填一个class类
	        return Class.forName("com.campus.core.domain.IdEntity").getResource("/").getPath();
	    } catch (Exception e) {
	    	log.info("获取classpath错误", e);
	    }
	    return "";
	}
	 
	/**
	 * 获得工程的WebRoot根目录
	 * @author 刘汉升
	 * @return String
	 */
	public static String getWebRootPath() {
	    try {
	        String classpath = getClasspath();// classes 目录的物理路径
	        String webInfoPath = new File(classpath).getParent();// WEB-INF 目录的物理路径
	        return new File(webInfoPath).getParent();// WebRoot 目录的物理路径
	    } catch (Exception e) {
	    	log.info("获取WebRoot根目录错误", e);
	    }
	    return "";
	}
}
