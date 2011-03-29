package cdu.computer.hxl.util;

import java.io.InputStream;
import java.net.URL;

/**
 * �����Դ
 * 
 * @author hxl
 * 
 */
public final class Resource {

	/**
	 * 
	 * @param relativePath
	 *            ���src��һ��·��
	 * @return ��Դ��
	 */
	public static InputStream getResourceStream(String relativePath) {
		return Resource.class.getClassLoader()
				.getResourceAsStream(relativePath);
	}

	/**
	 * 
	 * @param relativePath
	 *            ���src��һ��·��
	 * @return ��Դ��һ��URL
	 */
	public static URL getResourceURL(String relativePath) {
		return Resource.class.getClassLoader().getResource(relativePath);

	}

	public static void main(String[] args) {
		System.out.println(Resource.getResourceURL("images/trayIcon.jpg"));
	}
}
