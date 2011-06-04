package cdu.computer.hxl.factory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;

import cdu.computer.hxl.util.Resource;

/**
 * IOC���󹤳�
 * 
 * @author hxl
 * 
 */
public final class ObjectFactory {

	private static final String SPRING_CONFIG = "spring.xml";
	private static XmlBeanFactory xmlBeanFactory = null;
	private static org.springframework.core.io.Resource resouce = null;

	/*
	 * ��ʼ�����󹤳�
	 */
	static {
		long bf = System.currentTimeMillis();
		try {

			InputStream in = Resource.getResourceStream(SPRING_CONFIG);
			System.out.println((System.currentTimeMillis() - bf) + "1....");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[512];
			int len = -1;
			while ((len = in.read(b)) > -1) {
				bos.write(b, 0, len);
			}
			System.out.println((System.currentTimeMillis() - bf) + "2....");
			resouce = new ByteArrayResource(bos.toByteArray());
			System.out.println((System.currentTimeMillis() - bf) + "3....");
			xmlBeanFactory = new XmlBeanFactory(resouce);
			System.out.println((System.currentTimeMillis() - bf) + "4....");
		} catch (Exception e) {
			System.err.println("����spring.xml�쳣...");
			e.printStackTrace();
		}
		System.out.println((System.currentTimeMillis() - bf) + "5,over!");
	}

	/**
	 * ����bean�� name��ö���
	 * 
	 * @param beanName
	 * @return Object
	 */
	public static Object getInstance(String beanName) {

		return xmlBeanFactory.getBean(beanName);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectFactory.getInstance("costService");
	}

}
