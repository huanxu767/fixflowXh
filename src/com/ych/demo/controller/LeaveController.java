package com.ych.demo.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ych.demo.service.LeaveService;
import com.ych.demo.service.WorkFlowService;

@Controller
public class LeaveController {

	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private LeaveService leaveService;
	
	/**
	 * ������ٿ�ʼ���棬���س���Ӧ��toolbar
	 * @param request
	 * @return
	 */
	@RequestMapping("startTask")
	public ModelAndView startTask(HttpServletRequest request){
		String processKey = request.getParameter("processDefinitionKey");
		Map<String,String> params = new HashMap<String, String>();
		params.put("processDefinitionKey", processKey);
		List<Map<String,Object>> toolbarInfo = workFlowService.getToolbarInfo(params);
		ModelAndView modelView = new ModelAndView("fixflow/startTask");
		modelView.addObject("commandList", toolbarInfo);
		return modelView;
	}
	
	/**
	 * �����ٶ������������������
	 * startTask��doTask�����е�������Ǵ˴�����
	 * @param request
	 * @return
	 */
	@RequestMapping("completeTask")
	public ModelAndView completeLeave(HttpServletRequest request){
		Map<String,Object> params = getRequestParams(request);
		leaveService.saveLeave(params);
		ModelAndView modelView = new ModelAndView("redirect:toDoTask.action");
		return modelView;
	}
	
	/**
	 * ��������������toolbar��Ϣ
	 * @param request
	 * @return
	 */
	@RequestMapping("doTask")
	public ModelAndView doTask(HttpServletRequest request){
		String taskId = request.getParameter("taskId");
		String bizKey = request.getParameter("bizKey");
		Map<String,Object> leave = leaveService.selectLeaveById(bizKey);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("taskId", taskId);
		List<Map<String,Object>> toolbarInfo = workFlowService.getToolbarInfo(params);
		ModelAndView modelView = new ModelAndView("fixflow/doTask");
		modelView.addObject("commandList", toolbarInfo);
		modelView.addObject("leave", leave);
		return modelView;
	}
	
	
	/**
	 * http request ���������ȡ
	 * 
	 * @param request
	 *            http ����
	 * @return ���ػ�ȡ��http�������
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> getRequestParams(HttpServletRequest request) {
		// �������
		Map<String, Object> requestParams = new HashMap<String, Object>();
		requestParams.putAll(request.getParameterMap());
		try {
			Enumeration<String> enumeration = null;
				// ��ȡparmeter�в���
			enumeration = request.getParameterNames();
			if (null != enumeration) {
				String key = null;
				String value = null;
				while (enumeration.hasMoreElements()) {
					key = enumeration.nextElement();
					value = request.getParameter(key);
					requestParams.put(key, new String(value.getBytes("ISO8859-1"), "utf-8"));
				}
			}
			// ��ȡattribute�в���
			enumeration = request.getAttributeNames();
			if (null != enumeration) {
				String key = null;
				while (enumeration.hasMoreElements()) {
					key = enumeration.nextElement();
					requestParams.put(key, request.getAttribute(key));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return requestParams;
	}
	
	
}
