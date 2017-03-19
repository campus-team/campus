package com.campus.view.flow.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.campus.core.tools.CommUtil;
import com.campus.core.tools.WebFormHelper;
import com.campus.flow.domain.FormFolder;
import com.campus.flow.service.IFormFolderService;
import com.campus.flow.service.IFormService;

@Controller
@RequestMapping("/form_folder")
public class FormFolderController {

	@Autowired
	private IFormFolderService formFolderService;
	
	@Autowired 
	private IFormService formService;
	
	@RequestMapping("/form_folder_save.htm")
	public void form_folder_save(HttpServletRequest request, HttpServletResponse response, String id, String name, String sequence, String parent_id){
		boolean result = false;System.out.println("handle "+name+" "+sequence);
		String msg = "";
		FormFolder form_folder = null;
		if(CommUtil.isNull(id)){
			form_folder = WebFormHelper.toPo(request, FormFolder.class);
		}else{
			form_folder = this.formFolderService.getObjById(Long.valueOf(id));
			form_folder = (FormFolder) WebFormHelper.toPo(request, form_folder);
		}
		if(CommUtil.isNotNull(parent_id)){
			FormFolder parent = this.formFolderService.getObjById(Long.valueOf(parent_id));
			form_folder.setParent(parent);
		}
		if(CommUtil.isNull(id)){
			result = this.formFolderService.save(form_folder);
		}else{
			result =this.formFolderService.update(form_folder);
		}
		JSONObject data = new JSONObject();
		data.put("result", result);
		data.put("msg", msg);
		response.setContentType("text/plain");
		response.setHeader("cache-control", "no-cache");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(data.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
