package cdu.computer.hxl.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cdu.computer.hxl.util.Constants;

/**
 * ��ͥ�������ϵͳ����ʾ����
 * 
 * @author hxl
 * @date 2011-03-06
 */
public class BaseJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();// �����Ļ�Ĵ�С

	private int width = 300;// ����Ĭ�ϵĿ��
	private int height = 210;// ����Ĭ�ϵĸ߶�

	public BaseJFrame() {
		// this.getContentPane().setSize(new Dimension(width, height));
		this.setSize(width, height);
	}

	/**
	 * ���ò������ϵͳ����ı���
	 */
	public BaseJFrame setFrameTitle(String title) {
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
	public BaseJFrame setFrameSize(double width, double height) {

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
	public BaseJFrame setFrameIconImage(Image image) {
		this.setIconImage(image);
		return this;
	}

	/**
	 * ���ô˴����Ƿ�����û�������С
	 * 
	 * @return this
	 */
	public BaseJFrame setFrameResizable(boolean b) {
		this.setResizable(b);
		return this;
	}

	/**
	 * ���ô����λ�þ���
	 * 
	 * @return this object
	 */
	public BaseJFrame setFrameCenter() {
		double cashManagerWidth = this.getSize().getWidth();
		double cashManagerHeight = this.getSize().getHeight();
		double screenWidth = screen.getWidth();
		double screenHeight = screen.getHeight();

		this.setLocation(((int) (screenWidth - cashManagerWidth) / 2),
				(int) ((screenHeight - cashManagerHeight) / 2));

		return this;
	}

	public static void main(String[] args) throws IOException {

	}
}
