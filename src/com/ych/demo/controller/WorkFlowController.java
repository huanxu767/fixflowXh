package com.ych.demo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ych.demo.service.WorkFlowService;

/**
 * ��������������action���������죬����׷�٣�������̵���ҵ���޹صĲ�����
 * @author xuhuan
 *
 */
@Controller
public class WorkFlowController {

	@Resource(name = "workFlowService")
	private WorkFlowService workFlowService;
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	@RequestMapping("toDoTask")
	public ModelAndView toDoTask(){
		
		ModelAndView modelAndView = new ModelAndView("/fixflow/toDoTask");
		List<Map<String,Object>> taskList = workFlowService.getToDoTask();
		modelAndView.addObject("taskList", taskList);
		return modelAndView;
	}
}
