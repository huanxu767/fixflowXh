优酷会员

系统管理员	admin
王某	0055  项目经理 软件三部
许一项  0012  项目经理 软件一部
许一部  0013  部门经理 软件一部
李强	0056  部门经理 软件三部
孙劳	0057  商务专职
赵某	1001  公司领导

周某	8008

1000002	部门经理
1000003	商务专职
1000004	公司领导
1000001	项目经理

S100001	软件中心
100000	软件一部
100001	软件二部
100003	软件三部
S100002	商务部
S100003	公司领导



import com.founder.fix.fixflow.core.impl.Context;
import com.founder.fix.fixflow.core.impl.identity.GroupTo;

groupTos=Context.getProcessEngineConfiguration().getUserDefinition().getUserInGroups(processInfo.getInitiator());
for (GroupTo groupTo : groupTos) {
	//默认取出用户的第一个所在部门
	if(groupTo.getGroupType().equals("dept")){
		return processInfo.findUserDeptAndRole(groupTo.getGroupId(),"1000002");;
	}
}
return null;

fixflow-root 根项目，用来聚合各个模块

fixflow-core、fixflow-expand 引擎内核模块

fixflow-webapp-common 、fixflow-common-api、fixflow-common-servlet bpmcenter项目公用代码

fixflow-webapp-taskcenter 任务中心模块

fixflow-webapp-managecenter 、fixflow-manage-serlvet 管控中心模块

fixflow-webapp-explorer 资源管理器模块

fixflow-webapp-editor、fixflow-converter web设计器模块



//发起申请
http://127.0.0.1:8080/fixflowSpringDemo-noMaven/startTask.action?userId=1200119390&processDefinitionKey=QJLC


http://127.0.0.1:8080/bpmcenter/queryTaskDetailInfor.action?processInstanceId=&processDefinitionKey=QJLC



[
{id=HandleCommand_2, nodeId=UserTask_1, name=提交, nodeName=请假提交, isVerification=true, isAdmin=false, type=startandsubmit, isSimulationRun=false, isSaveData=true}, 
{id=Advance_ProcessStatus, nodeId=UserTask_1, name=流程状态, nodeName=请假提交, isVerification=true, isAdmin=false, type=processStatus, isSimulationRun=false, isSaveData=false}

]

taskId
bizKey


{taskId=87f99dc6-8caa-4b0e-8c7b-2f350e8fdfee, org.springframework.web.servlet.HandlerMapping.introspectTypeLevelMapping=true, commandType=general, org.springframework.web.servlet.DispatcherServlet.FLASH_MAP_MANAGER=org.springframework.web.servlet.support.SessionFlashMapManager@1388a77, org.springframework.web.servlet.DispatcherServlet.THEME_SOURCE=WebApplicationContext for namespace 'dispatcher-servlet': startup date [Thu Nov 12 09:13:33 CST 2015]; parent: Root WebApplicationContext, org.springframework.web.servlet.DispatcherServlet.THEME_RESOLVER=org.springframework.web.servlet.theme.FixedThemeResolver@10ac19f, org.springframework.web.servlet.DispatcherServlet.CONTEXT=WebApplicationContext for namespace 'dispatcher-servlet': startup date [Thu Nov 12 09:13:33 CST 2015]; parent: Root WebApplicationContext, org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping=completeTask.action, commandId=HandleCommand_3, org.springframework.web.context.request.async.WebAsyncManager.WEB_ASYNC_MANAGER=org.springframework.web.context.request.async.WebAsyncManager@17ead3d, qjbh=123231231, org.springframework.web.servlet.DispatcherServlet.OUTPUT_FLASH_MAP=[Attributes={}, targetRequestPath=null, targetRequestParams={}], org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping.useDefaultSuffixPattern=true, processInstanceId=, bizKey=123231231, taskParams={}, processDefinitionKey=, org.springframework.web.servlet.HandlerMapping.uriTemplateVariables={}, qjsj=2312123, org.springframework.web.servlet.HandlerMapping.bestMatchingPattern=/completeTask.*, qjr=556564, org.springframework.web.servlet.DispatcherServlet.LOCALE_RESOLVER=org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver@a9658}



xuhuan mac


List<Map<String, String>> result = getFlowCenter().queryStartProcess(userId);

[{resourceId=4eda9028-84be-401d-ad3a-f3fc61497ad0, category=null, diagramResourceName=processExpenseXh.png, processDefinitionKey=processExpenseXh, startFormKey=DemoServlet?action=startOneTask, deploymentId=ae86c836-e629-4d71-9ee6-2e3799ebad46, formUrl=DemoServlet?action=startOneTask, processDefinitionName=财务报销流程, processDefinitionId=processExpenseXh:2:8cbe79dc-3f92-4044-82e2-999c59fefb2d, resourceName=processExpenseXh.bpmn, version=2}, 

{resourceId=c64dcb95-09f6-48bf-9fc9-497a793045b3, category=企业资产管理, diagramResourceName=RecipientsAssetsRunningStores.png, processDefinitionKey=RecipientsAssetsRunningStores, startFormKey=DemoServlet?action=startOneTask, deploymentId=34730c69-0103-4f5f-b8ea-1293bc1fd4fc, formUrl=DemoServlet?action=startOneTask, processDefinitionName=企业资产低值易耗品领用流程, processDefinitionId=RecipientsAssetsRunningStores:1:89283151-8008-4908-9072-9d0da20b36ad, resourceName=RecipientsAssetsRunningStores.bpmn, version=1},
 {resourceId=6f87d25c-e974-44b4-adad-499a98b11e33, category=null, diagramResourceName=process_jiekuan.png, processDefinitionKey=process_jiekuan, startFormKey=DemoServlet?action=startOneTask, deploymentId=43baf21e-6285-4c9b-9304-ca397877926b, formUrl=DemoServlet?action=startOneTask, processDefinitionName=立项V12, processDefinitionId=process_jiekuan:19:e1a1d474-cf0b-466d-a5cf-dae5de03ee46, resourceName=process_jiekuan.bpmn, version=19}, 

 {resourceId=5f6c67e9-3c49-406d-aa95-7da98a1a9e3e, category=null, diagramResourceName=QJLC.png, processDefinitionKey=QJLC, startFormKey=dealTask.action, deploymentId=540fa470-19e1-4447-983b-20d2117ab7e6, formUrl=dealTask.action, processDefinitionName=请假流程, processDefinitionId=QJLC:1:644a984e-34fd-445c-a4d6-b95c9a9e806a, resourceName=QJLC.bpmn, version=1}
 ]