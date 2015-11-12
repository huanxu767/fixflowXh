package com.ych.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.founder.fix.fixflow.core.impl.util.StringUtil;
import com.ych.demo.dao.LeaveDao;
import com.ych.demo.model.LeaveModel;

/**
 * ���service����
 * @author ych
 */
@Service("leaveService")
public class LeaveService {

	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private LeaveDao leaveDao;
	
	/**
	 * ��ٴ���������ٿ�ʼ������
	 * ���taskIdΪ�գ��򱣴�ҵ�����ݣ����з�����������򣬲�����ҵ�����ݣ�������������
	 * @param params
	 */
	public void saveLeave(Map<String,Object> params){
		
		String qjbh = params.get("qjbh").toString();
		String nextAssgine = StringUtil.getString(params.get("nextAssgine"));
		
		Object taskId = params.get("taskId");
		//������taskId,��ʾ��һ���������̣���Ҫ����ҵ������
		if(taskId == null || taskId.equals("")){
			String qjr = params.get("qjr").toString();
			String qjsj = params.get("qjsj").toString();
			
			LeaveModel leave = new LeaveModel();
			leave.setQjbh(qjbh);
			leave.setQjr(qjr);
			leave.setQjsj(qjsj);
			
			leaveDao.saveLeave(leave);
		}
		
		//��������Ϊ����������������
		params.put("bizKey", qjbh);
		//���������ñ�����nextAssignee�����̶����б�����Ϊ�����ڵ�Ĵ����ˣ�������̶���
		
		if(StringUtil.isNotEmpty(nextAssgine)){
			Map<String,Object> taskVariable = new HashMap<String,Object>();
			taskVariable.put("nextAssignee", nextAssgine);
			params.put("taskVariable", taskVariable);
		}
		
		workFlowService.executeCommand(params);
		
	}
	
	/**
	 * ��ѯ�����Ϣ���������������չ��
	 * @param qjbh
	 * @return
	 */
	public Map<String,Object> selectLeaveById(String qjbh){
		return leaveDao.selectLeaveById(qjbh);
	}

}
