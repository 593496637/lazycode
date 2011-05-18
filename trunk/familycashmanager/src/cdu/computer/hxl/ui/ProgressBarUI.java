package cdu.computer.hxl.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import cdu.computer.hxl.util.ThreadExecutorUtils;

/**
 * ������
 * 
 * @author Administrator
 * 
 */
public class ProgressBarUI {
	private BaseJFrame parent = null;
	private JDialog barDialog = null;
	private JProgressBar progressBar = null;
	private JLabel lbStatus = null;

	private String statusInfo = "";

	private Thread thread = null;

	public ProgressBarUI(String statusInfo, Thread thread, BaseJFrame parent) {
		this.parent = parent;
		this.statusInfo = statusInfo;
		this.thread = thread;
		initUI();
		startThread();
		barDialog.setVisible(true);
	}

	public void initUI() {
		barDialog = new JDialog(parent, true);
		barDialog.setUndecorated(true); // ��ȥtitle

		final JPanel mainPane = new JPanel(null);
		progressBar = new JProgressBar();
		lbStatus = new JLabel("" + statusInfo);

		progressBar.setIndeterminate(true);
		mainPane.add(progressBar);
		mainPane.add(lbStatus);

		barDialog.getContentPane().add(mainPane);

		barDialog.setResizable(true);
		barDialog.setSize(390, 100);
		barDialog.setLocationRelativeTo(parent); // ���ô˴��������ָ�������λ��

		barDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // ������ر�

		mainPane.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				layout(mainPane.getWidth(), mainPane.getHeight());
			}
		});

	}

	private void layout(int width, int height) {
		progressBar.setBounds(20, 20, 350, 15);
		lbStatus.setBounds(20, 50, 350, 25);
	}

	private void startThread() {
		new Thread() {
			public void run() {
				try {
					thread.start(); // �����ʱ����
					// �ȴ��������߳̽���
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// �رս�����ʾ��
					barDialog.dispose();

					String title = "��ʾ";
					JOptionPane.showMessageDialog(parent, "�ɹ�!", title,

					JOptionPane.INFORMATION_MESSAGE);

				}
			}
		}.start();
	}

	public static void main(String[] args) throws Exception {

		// ҵ���߳�
		Thread thread = new Thread() {
			public void run() {
				int index = 0;

				while (index < 5) {
					try {
						sleep(1000);
						System.out.println(++index);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		new ProgressBarUI("���ڱ���,���Ժ�....", thread, null);

	}
}
