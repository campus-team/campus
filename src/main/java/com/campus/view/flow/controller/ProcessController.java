package com.campus.view.flow.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.campus.core.mv.JModelAndView;

/**
 * 流程的 过程
 * @author 刘汉升
 *
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

	@RequestMapping("/process_designer.htm")
	public ModelAndView process_designer(HttpServletRequest request){
		JModelAndView mv = new JModelAndView("process_designer.html", 1, request);
		return mv;
	}
	
	/**
	 * 新建流程定义[web流程设计器]
	 * @param model
	 * @return
	 
	@RequestMapping(value = "designer", method=RequestMethod.GET)
	public String processDesigner(String processId, Model model) {
		if(StringUtils.isNotEmpty(processId)) {
			Process process = facets.getEngine().process().getProcessById(processId);
			AssertHelper.notNull(process);
			ProcessModel processModel = process.getModel();
			if(processModel != null) {
				String json = SnakerHelper.getModelJson(processModel);
				model.addAttribute("process", json);
			}
			model.addAttribute("processId", processId);
		}
		return "snaker/processDesigner";
	}*/
}
