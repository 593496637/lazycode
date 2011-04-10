package cdu.computer.hxl.db;

import java.sql.Connection;

import cdu.computer.hxl.exception.DBConnectionException;

/**
 * �������ݿ�Ľӿ�
 * 
 * @author hxl
 * 
 */
public interface DBConnection {
	/**
	 * ���������ļ�ȡ�����ݵ����ӣ�������ʵ��
	 * 
	 * @return connection ����һ�����ݿ�����ʵ�� *
	 * @throws DBConnectionException
	 *             �׳������쳣
	 */
	public Connection connection() throws DBConnectionException;

}
