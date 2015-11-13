package com.ych.demo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.founder.fix.fixflow.core.ProcessEngine;
import com.founder.fix.fixflow.core.TaskService;
import com.founder.fix.fixflow.core.impl.bpmn.behavior.TaskCommandInst;
import com.founder.fix.fixflow.core.impl.command.ExpandTaskCommand;
import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.founder.fix.fixflow.core.task.TaskInstance;
import com.founder.fix.fixflow.core.task.TaskQuery;
import com.founder.fix.fixflow.service.FlowCenterService;
import com.ych.util.SpringBeanUtil;

/**
 * fixflow ��������service
 *
 */
@Service("workFlowService")
public class WorkFlowService {

	// �������
	@Autowired
	protected TaskService taskService;
	
	@Autowired
	protected ProcessEngine processEngine;
	
	/**
	 * ����ӿ�Ӧ�ý��ղ�ѯ�����ͷ�ҳ�����ģ���ϸ�ο�bpmcenter�е�ʾ����ʱ���ϵ���򵥴�����
	 * @return
	 */
	public List<Map<String,Object>> getToDoTask(){
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
		return taskResult;
	}
	
	/**
	 * ִ������
	 * �˴��߼����Ǻ�������ֻ��������ͨ��ť�������߼���bpmcenter��ʾ��
	 * @param parasMap
	 */
	public void executeCommand(Map<String,Object> parasMap){
		
		String taskId = StringUtil.getString(parasMap.get("taskId"));
		String commandType = StringUtil.getString(parasMap.get("commandType"));
		String commandId = StringUtil.getString(parasMap.get("commandId"));
		String processDefinitionKey = StringUtil.getString(parasMap.get("processDefinitionKey"));
		String businessKey = StringUtil.getString(parasMap.get("bizKey"));
		String taskComment = StringUtil.getString(parasMap.get("_taskComment"));
		
		@SuppressWarnings("unchecked")
		Map<String,Object> taskVariable = (Map<String, Object>) parasMap.get("taskVariable");
		

		ExpandTaskCommand expandTaskCommand = new ExpandTaskCommand();
		
		expandTaskCommand.setCommandType(commandType);
		expandTaskCommand.setUserCommandId(commandId);
		expandTaskCommand.setTaskComment(taskComment);
		
		if(taskVariable != null){
			//���������ó־û�����  5.2��������bug,���github  issue#221
			expandTaskCommand.setVariables(taskVariable);
		}

		if (StringUtil.isNotEmpty(taskId)) {
			expandTaskCommand.setTaskId(taskId);
		} else {
			//����ִ��ǰ���Ѿ����ù���ǰ�����ˣ������������õ������fixflowInterceptor
			String userId = Authentication.getAuthenticatedUserId();
			expandTaskCommand.setInitiator(userId);
			expandTaskCommand.setBusinessKey(businessKey);
			expandTaskCommand.setProcessDefinitionKey(processDefinitionKey);
		}
		
		taskService.expandTaskComplete(expandTaskCommand, null);
		
	}
	
	/**
	 * ��ȡ����toobar��Ϣ
	 * �������taskId����ȡ�����ϵĴ�������
	 * ����ͨ��processDefinitionKeyȡ��ʼ�ڵ��ϵ��������ã����ڿ�ʼ����
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getToolbarInfo(Map<String,String> params){
		List<Map<String,Object>> toolbarInfo = new ArrayList<Map<String,Object>>();
		String processKey = params.get("processDefinitionKey");
		String taskId = params.get("taskId");
		List<TaskCommandInst> taskCommands = null;
		if(taskId != null){
			taskCommands = taskService.GetTaskCommandByTaskId(taskId, false);
		}
		else if(processKey != null){
			taskCommands = taskService.getSubTaskTaskCommandByKey(processKey);
		}else{
			throw new RuntimeException("��������");
		}
		
		if(taskCommands != null){
			for(TaskCommandInst taskCommand :taskCommands){
				Map<String,Object> commandMap = taskCommand.getPersistentState();
				toolbarInfo.add(commandMap);
			}
		}
		return toolbarInfo;
	}
	
	public void closeEngine(){
		if(processEngine != null){
			processEngine.contextClose(true, false);
		}
	}
	/**
	 * ����ҵ�����
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> getMyProcess() throws SQLException {
//		1200119391
		List<Map<String, String>> result = getFlowCenter().queryStartProcess("1200119391");
		System.out.println("result:");
		return null;
	}
	public FlowCenterService getFlowCenter() {
		return (FlowCenterService) SpringBeanUtil.getBean("flowCenterServiceImpl");
	}

}
