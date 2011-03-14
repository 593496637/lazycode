package cdu.computer.hxl.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import cdu.computer.hxl.util.Constants;

/**
 * ��ͥ�������ϵͳ����ʾ����
 * 
 * @author hxl
 * @date 2011-03-06
 */
public class CashManagerMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();// �����Ļ�Ĵ�С

	private int width = 300;// ����Ĭ�ϵĿ��
	private int height = 200;// ����Ĭ�ϵĸ߶�

	public CashManagerMainFrame() {
		// this.getContentPane().setSize(new Dimension(width, height));
		this.setSize(width, height);
	}

	/**
	 * ���ò������ϵͳ����ı���
	 */
	public CashManagerMainFrame setFrameTitle(String title) {
		this.setTitle(title);
		return this;
	}

	/**
	 * ���ò������ϵͳ�Ŀ�Ⱥ͸߶�
	 * 
	 * @param width
	 * @param height
	 * @return this
	 */
	public CashManagerMainFrame setFrameSize(double width, double height) {

		/*
		 * ���Ŷ����Ŀ�Ȼ��߸߶�С�ڵ�����0����ô�ͰѴ�������ΪĬ�ϸ߶ȡ����
		 */
		if (width <= 0.0 || height <= 0.0) {
			width = this.width;
			height = this.height;
		}

		Dimension dimension = new Dimension();
		dimension.setSize(width, height);
		this.setSize(dimension);
		return this;
	}

	/**
	 * ���ô���ͼ����ʾ��ͼ��
	 * 
	 * @param image
	 * @return this
	 */
	public CashManagerMainFrame setFrameIconImage(Image image) {
		this.setIconImage(image);
		return this;
	}

	/**
	 * ���ô˴����Ƿ�����û�������С
	 * 
	 * @return this
	 */
	public CashManagerMainFrame setFrameResizable(boolean b) {
		this.setResizable(b);
		return this;
	}

	/**
	 * ���ô����λ�þ���
	 * 
	 * @return this object
	 */
	public CashManagerMainFrame setFrameCenter() {
		double cashManagerWidth = this.getSize().getWidth();
		double cashManagerHeight = this.getSize().getHeight();
		double screenWidth = screen.getWidth();
		double screenHeight = screen.getHeight();

		this.setLocation(((int) (screenWidth - cashManagerWidth) / 2),
				(int) ((screenHeight - cashManagerHeight) / 2));

		return this;
	}

	public static void main(String[] args) throws IOException {

		Image image = ImageIO.read(CashManagerMainFrame.class
				.getResourceAsStream("/icon.jpg"));

		Image loginImg = ImageIO.read(new File(Constants.BASE_PATH
				+ "/login.jpg"));

		CashManagerMainFrame mainFrame = new CashManagerMainFrame()
				.setFrameTitle("���˼�ͥ�������ϵͳ").setFrameResizable(false)
				.setFrameCenter().setFrameIconImage(image);
		LoginPanel login = new LoginPanel(mainFrame).setTopImage(loginImg);
	

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
}
