package com.campus.core.tools;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.BeanFactoryUtils;

import com.campus.core.annotation.Lock;
import com.campus.foundation.domain.User;

/**
 * 将表单提交的内容自动注入到对象当中
 * @author 刘汉升
 *
 */
public class WebFormHelper {

	/**
	 * 将request提交的数据中字段与obj一致的属性进行赋值
	 * @param request 
	 * @param obj
	 * @return
	 */
	public static Object toPo(HttpServletRequest request, Object obj){
		Enumeration names = request.getParameterNames();
		List<Map> maps = new ArrayList<Map>();
		while(names.hasMoreElements()){
			String paramName = (String)names.nextElement();
			String paramValue = request.getParameter(paramName);
			Map map = new HashMap();
			map.put(paramName, paramValue);
			maps.add(map);
		}
		Map2Obj(maps, obj);
		return obj;
	}
	
	/**
	 * 同上面的方法一样，只不过传入的是一个class，最会返回一个新实例
	 * @param request
	 * @param classType
	 * @return
	 */
	public static <T> T toPo(HttpServletRequest request, Class<T> classType){
		Enumeration names = request.getParameterNames();
		List<Map> maps = new ArrayList<Map>();
		while(names.hasMoreElements()){
			String paramName = (String)names.nextElement();
			String paramValue = request.getParameter(paramName);
			Map map = new HashMap();
			map.put(paramName, paramValue);
			maps.add(map);
		}
		Object obj = null;
		try {
			obj = classType.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		Map2Obj(maps, obj);
		return (T)obj;
	}

	/**
	 * 将map数据对应赋值到obj这个bean当中
	 * @param maps
	 * @param obj
	 */
	private static void Map2Obj(List<Map> maps, Object obj) {
		BeanWrapper wrapper = new BeanWrapperImpl(obj);
		PropertyDescriptor properties[] = wrapper.getPropertyDescriptors();
		for(int i=0; i<properties.length; i++){
			String propertyName = properties[i].getName();
			if(!wrapper.isWritableProperty(propertyName) || properties[i].getWriteMethod() == null){
				continue; // 属性不可写 或者 没有get方法 则跳过
			}
			for(int j=0; j<maps.size(); j++){
				Map map = maps.get(j);
				Iterator keys = map.keySet().iterator();
				while(keys.hasNext()){
					String key = (String)keys.next();
					if(key.equals(propertyName)){
						Lock lock = null;
						lock = (Lock)properties[i].getWriteMethod().getAnnotation(Lock.class);
						// 类的属性的写方法上有没有标注lock
						if(lock == null){
							try {
								Field f = properties[i].getWriteMethod().getDeclaringClass().getDeclaredField(propertyName);
								// 属性上边有没有标注lock注解
								lock = f.getAnnotation(Lock.class);
							} catch (NoSuchFieldException e) {
								e.printStackTrace();
							} catch (SecurityException e) {
								e.printStackTrace();
							}
						}
						if(lock != null) continue;
						Object propertyValue = null;
						try {
							Class<?> clazz = properties[i].getPropertyType();
							propertyValue = ConvertUtils.convert(map.get(key), clazz);;
						}catch (Exception e) {
							System.out.println("转换报错"+properties[i].getPropertyType());
							if(properties[i].getPropertyType().toString().equals("int")){
								propertyValue = Integer.valueOf(0);
							}
							if(properties[i].getPropertyType().toString().toLowerCase().indexOf("boolean") >= 0){
								propertyValue = Boolean.valueOf(false);
							}
			            }
						wrapper.setPropertyValue(properties[i].getName(), propertyValue);
					}
					
				}
			}
		}
		
	}
}
