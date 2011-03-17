package cdu.computer.hxl.ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;

import cdu.computer.hxl.util.Constants;

/**
 * ��ͥ�������ϵͳ����
 * 
 * @author hxl
 * @date 2011-03-16
 */
public class FamilyCashManagerSystem {

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
		props.put("logoString", "");// �˵�����ߵ�����
		props.put("menuSelectionBackgroundColor", "150 160 160");// �˵�����ߵı���ɫ
		NoireLookAndFeel.setCurrentTheme(props);

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Image image = ImageIO.read(BaseJFrame.class
				.getResourceAsStream("/icon.jpg"));

		Image loginImg = ImageIO.read(new File(Constants.BASE_PATH
				+ "/login.jpg"));

		final BaseJFrame mainFrame = new BaseJFrame().setFrameTitle(
				"���˼�ͥ�������ϵͳ").setFrameSize(Constants.LOGIN_WIDTH,
				Constants.LOGIN_HEIGHT).setFrameIconImage(image)
				.setFrameResizable(false).setFrameCenter();// .setFrameIconImage(image);

		LoginUI login = LoginUI.instance().mainFrame(mainFrame).topImage(
				loginImg).build();
		final MainUI mainUI = new MainUI(mainFrame);

		login.getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mainFrame.getContentPane().removeAll();
				// mainFrame.getContentPane().repaint();
				// mainFrame.getContentPane().validate();
				mainUI.initUI();
			}
		});

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}
