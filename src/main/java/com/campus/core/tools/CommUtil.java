package com.campus.core.tools;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共工具类
 * @author:刘汉升
 */
public class CommUtil {

	/**
    * 判断对象是否为null或者空字符串
    * @author:刘汉升
    */
	public static boolean isNotNull(Object obj)
	{
	   return (obj != null) && (!obj.toString().equals(""));
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
		}
	    return value;
	}
	
	/**
     * 获取请求的绝对路径（URL去掉资源路径）
     * @author 刘汉升
     * @param request
     * @return 请求的绝对路径（协议+服务器名+端口+项目名）
     */
	public static String getWebPath(HttpServletRequest request) {
		String contextPath = request.getContextPath().equals("/") ? "" : request.getContextPath();
		String url = "http://" + request.getServerName();
			if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
				url = url + ":" + null2Int(Integer.valueOf(request.getServerPort())) + contextPath;
			else {
				url = url + contextPath;
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
	        e.printStackTrace();
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
	        e.printStackTrace();
	    }
	    return "";
	}
}
