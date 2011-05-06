package cdu.computer.hxl.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import cdu.computer.hxl.factory.MenuFactory;

/**
 * ��ͥ�������ϵͳ����ʾ����
 * 
 * @author hxl
 * @date 2011-03-06
 */
public class BaseJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final StatusPanel status = new StatusPanel();

	private final Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();// �����Ļ�Ĵ�С
	private final static SystemTray sTray = SystemTray.getSystemTray();// ���ϵͳ����

	private static final int DEFAULT_WIDTH = 300;// ����Ĭ�ϵĿ��
	private static final int DEFAULT_HEIGHT = 210;// ����Ĭ�ϵĸ߶�

	private TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit()
			.getImage(this.getClass().getResource("/images/trayIcon.jpg")));// ϵͳ����ͼ��

	/*
	 * ����һ��ϵͳ���̵����˵����������Ӧ���¼�
	 */
	private PopupMenu trayMenu = MenuFactory.createPopupMenu("", new String[] {
			"��ԭ", "�˳�" }, new ActionListener[] { new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			sTray.remove(trayIcon);// ��ԭ��ϵͳ����ͼ���Ƴ�
			setVisible(true);
			setExtendedState(JFrame.NORMAL);
		}
	}, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	} });

	public BaseJFrame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// this.getContentPane().setSize(new Dimension(width, height));
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setLayout(new BorderLayout());// ���ò��ֹ�����

		trayIcon.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sTray.remove(trayIcon);
				setVisible(true);
				setExtendedState(JFrame.NORMAL);
			}

		});
		trayIcon.setPopupMenu(trayMenu);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				new CloseDialog(BaseJFrame.this).showDialog();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				try {
					setVisible(false);
					sTray.add(trayIcon);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}

		});

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
	@SuppressWarnings("static-access")
	public BaseJFrame setFrameSize(double width, double height) {

		/*
		 * �������Ŀ�Ȼ��߸߶�С�ڵ�����0����ô�ͰѴ�������ΪĬ�ϸ߶ȡ����
		 */
		if (width <= 0.0 || height <= 0.0) {
			width = this.DEFAULT_WIDTH;
			height = this.DEFAULT_HEIGHT;
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

	/**
	 * ��ʾ�ײ�״̬��
	 */
	public void showStatus() {
		this.add(status, BorderLayout.SOUTH);
	}

	/**
	 * ��ʾ����
	 */
	public void showFrame() {
		setVisible(true);
	}

	/**
	 * �ı�״̬����ߵ���ʾ��Ϣ
	 * 
	 * @param text
	 */
	public void setStatusText(String text) {
		status.chanageStatusText(text);
	}

	/**
	 * �ײ�״̬��
	 * 
	 * @author hxl
	 * 
	 */
	private class StatusPanel extends BaseJPanel {

		private static final long serialVersionUID = -7563273600503012769L;
		private JLabel status = null;

		@Override
		protected void init() {
			setLayout(new BorderLayout());
			@SuppressWarnings("deprecation")
			JLabel time = new JLabel(new Date().toLocaleString());
			time.setFont(new Font("����", Font.ITALIC, 10));
			add(time, BorderLayout.EAST);

			chanageStatusText("׼������");

		}

		public void chanageStatusText(String text) {
			status = new JLabel();
			status.setText(text);
			status.setFont(new Font("����", Font.ITALIC, 10));
			if (this.getComponentCount() >= 2)
				remove(1);
			add(status, BorderLayout.WEST);
			validate();
		}

	}

	/**
	 * �ر�Frameʱ�������Ի������û�ѡ��رջ�����С��ϵͳ����
	 * 
	 * @author hxl
	 * 
	 */
	private class CloseDialog extends BaseJDialog {
		private int close = 1;
		private JFrame frame = null;

		private CloseDialog(BaseJFrame frame) {
			super(frame, "ϵͳ����", true);
			this.frame = frame;
			// System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}

		@Override
		protected void initUI() {
			setLayout(null);
			setSize(300, 160);
			setResizable(false);
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new RoundBorder(), "��ѡ��",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			ButtonGroup group = new ButtonGroup();
			JRadioButton trayBnt = new JRadioButton("��С��ϵͳ����");
			JRadioButton exitBnt = new JRadioButton("ֱ���Ƴ�����");
			group.add(trayBnt);
			group.add(exitBnt);
			group.setSelected(trayBnt.getModel(), true);

			JButton okBnt = new JButton("ȷ��");
			JButton closeBnt = new JButton("�ر�");

			trayBnt.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					close = 1;
				}
			});

			exitBnt.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					close = 0;

				}
			});

			okBnt.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					if (isClose() == 0) {
						System.exit(0);
					} else if (isClose() == 1) {
						frame.setVisible(false);
						try {
							sTray.add(trayIcon);// ���ϵͳ����
						} catch (AWTException e1) {
							e1.printStackTrace();
						}
					}

				}
			});

			closeBnt.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					close = -1;
					setVisible(false);
				}
			});

			// panel.setPreferredSize(new Dimension(100, 100));
			panel.setBounds(15, 10, 265, 110);
			panel.add(trayBnt);
			panel.add(exitBnt);
			panel.add(okBnt);
			panel.add(closeBnt);

			add(panel);

		}

		protected int isClose() {
			return close;
		}
	}

	public static void main(String[] args) {
		BaseJFrame frame = new BaseJFrame();
		frame.setVisible(true);
	}
}
