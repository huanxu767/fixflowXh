package com.ych.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.ych.demo.service.WorkFlowService;

/**
 * ��������������action
 * @author xuhuan
 *
 */
@Controller
public class TaskController {

	@Autowired
	protected TaskService taskService;
	
	@Autowired
	protected ProcessEngine processEngine;
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	@RequestMapping("queryToDoTask")
	public ModelAndView queryToDoTask(){
		ModelAndView modelAndView = new ModelAndView("/fixflow/toDoTask");
		List<Map<String,Object>> taskResult = new ArrayList<Map<String,Object>>();
		TaskQuery taskQuery = taskService.createTaskQuery();
		//��ѯadmin�Ĺ���Ͷ�ռ���񣬴˴�Ӧ�ô�session���õ�ǰ��¼�û�
		taskQuery.taskAssignee("1200119390");
		taskQuery.taskCandidateUser("1200119390");
		taskQuery.taskNotEnd(); 
		List<TaskInstance> tasks = taskQuery.list();
		for(TaskInstance task : tasks){
			taskResult.add(task.getPersistentState());
		}
		modelAndView.addObject("taskList", taskResult);
		return modelAndView;
	}
	
}
