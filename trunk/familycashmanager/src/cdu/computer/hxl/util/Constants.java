package cdu.computer.hxl.util;

/**
 * ����ϵͳ�ĳ���
 * 
 * @author hxl
 * 
 */
public interface Constants {
	public static final String BASE_PATH = Constants.class.getResource("/")
			.getPath();
	public static final String DATABASE_PATH = BASE_PATH + "/database/";
	public static final String DATABASE_NAME = "cash";
}
