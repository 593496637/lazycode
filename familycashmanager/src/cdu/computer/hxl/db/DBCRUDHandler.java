package cdu.computer.hxl.db;

import java.util.List;
import java.util.Map;

/**
 * �������ݵ������ޡ�ɾ�������
 * 
 * @author hxl
 * 
 */
public interface DBCRUDHandler {

	/**
	 * �������
	 * 
	 * @param dataMap
	 * @param table
	 */
	public void add(Map<String, Object> dataMap, String table);

	/**
	 * ɾ�����ݣ�����id
	 * 
	 * @param id
	 * @param table
	 */
	public void delete(Integer id, String table);

	/**
	 * ��������,����id
	 * 
	 * @param id
	 * @param updateDataMap
	 * @param table
	 */
	public void update(Integer id, Map<String, Object> updateDataMap,
			String table);

	/**
	 * ��ȡһ������
	 * 
	 * @param id
	 * @param table
	 * @return map
	 */
	public Map<String, Object> readOne(Integer id, String table);

	/**
	 * ��ѯ����
	 * 
	 * @param whereDataMap
	 * @param table
	 * @return list of map
	 */
	public List<Map<String, Object>> search(Map<String, Object> whereDataMap,
			String table);

}
