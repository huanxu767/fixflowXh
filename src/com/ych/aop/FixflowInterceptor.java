package com.ych.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.founder.fix.fixflow.core.impl.identity.Authentication;
import com.ych.demo.service.WorkFlowService;

/**
 * fixflow�������������������õ�ProcessEngine�ķ���
 * 1.����ִ��ǰ�����õ�ǰ����Ĳ�����
 * 2.����ִ�к�����̸߳���
 * @author ych
 *
 */
public class FixflowInterceptor implements AfterReturningAdvice,
		MethodBeforeAdvice {


	@Override
	public void before(Method arg0, Object[] arg1, Object arg2)
			throws Throwable {
		//���õ�ǰ�����ˣ�����Ӧ�ôӵ�ǰsession��ȡ����ÿ��ִ�е�ʱ��Ҫ����
		//����ж��service�õ�fixflow���棬��Ӧ�ý�service����ɽӿڽ����жϣ�����ֻ��һ��������ʡ����
		if(arg2 instanceof WorkFlowService){
			Authentication.setAuthenticatedUserId("1200119390");
		}
	}

	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		//����ж��service�õ�fixflow���棬��Ӧ�ý�service����ɽӿڽ����жϣ�����ֻ��һ��������ʡ����
		if(arg3 instanceof WorkFlowService){
			WorkFlowService serivce = (WorkFlowService)arg3;
			serivce.closeEngine();
		}
	}

}
