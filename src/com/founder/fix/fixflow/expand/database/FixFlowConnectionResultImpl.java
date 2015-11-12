package com.founder.fix.fixflow.expand.database;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceUtils;

import com.founder.fix.fixflow.core.exception.FixFlowDbException;
import com.founder.fix.fixflow.core.impl.db.FixConnectionResult;
import com.ych.util.SpringBeanUtil;

/**
 * ���ิд��fixflow��Ĭ������Դ�����࣬������Դ����spring������
 * ���ڴ�����뵽classes�£���������fixflow-core���е������
 * 
 * �ڶ��ַ������½�һ��connectionManagerment����fixflowconfig.xml�ļ������ã������е㸴�ӣ�ʱ���ϵ����д�ˡ�
 * @author ych
 *
 */
public class FixFlowConnectionResultImpl implements FixConnectionResult {

	protected String dbId;

	protected Connection connection;

	public FixFlowConnectionResultImpl(String dbId) {
		this.dbId = dbId;
	}

	public FixFlowConnectionResultImpl(String dbId, Connection connection) {
		this.dbId = dbId;
		this.connection = connection;
	}

	public FixFlowConnectionResultImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		try {
			if (this.connection == null) {
				//ͨ��springȡ����Դ���˴������Լ���չ����springע��ⷽʽ��װ����֤���
				//�˷�����ȡ������Դ���Ա�֤��spring��������������
				this.connection = DataSourceUtils.getConnection((DataSource)SpringBeanUtil.getBean("dataSource"));
				return this.connection;
			} else {
				return this.connection;

			}
		} catch (Exception e) {
			throw new FixFlowDbException(e.getMessage(), e);
		}
	}

	public void colseConnection() {
		//����spring����
	}

	public void commitConnection() {
		//����spring����
	}

	public void rollBackConnection() {
		//����spring����
	}

	public void openConnection() {

	}

	public String getDataBaseId() {
		return this.dbId;
	}

}
