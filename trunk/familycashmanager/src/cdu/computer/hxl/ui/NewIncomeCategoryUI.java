package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.IncomeService;
import cdu.computer.hxl.util.GetSystemTime;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class NewIncomeCategoryUI extends BaseJDialog {

	private static final long serialVersionUID = 3359896724350259509L;
	private BaseJFrame owner = null;
	private JTextField categoryNameTextField = null;
	private JTextArea remarkTextArea = null;

	/**
	 * Create the dialog.
	 */
	public NewIncomeCategoryUI(BaseJFrame owner) {
		super(owner, "添加收入类别", true);
		this.owner = owner;
		initUI();
	}

	@Override
	protected void initUI() {
		setBounds(0, 0, 350, 230);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("\u6DFB\u52A0\u6536\u5165\u7C7B\u522B");
		titleLabel.setBounds(118, 10, 93, 15);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);

		JLabel categoryNameLabel = new JLabel("\u7C7B\u522B\u540D\u79F0\uFF1A");
		categoryNameLabel.setBounds(34, 44, 84, 15);
		categoryNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(categoryNameLabel);

		categoryNameTextField = new JTextField();
		categoryNameTextField.setBounds(128, 41, 146, 21);
		categoryNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(categoryNameTextField);
		categoryNameTextField.setColumns(10);

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setBounds(64, 87, 54, 15);
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(remarkLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 72, 146, 50);
		getContentPane().add(scrollPane);

		remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setColumns(10);
		remarkTextArea.setRows(2);
		scrollPane.setViewportView(remarkTextArea);

		JButton submitbnt = new JButton("保存");
		submitbnt.setBounds(128, 145, 64, 23);

		submitbnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new ThreadExecutorUtils() {

					@Override
					protected void task() {
						setVisible(false);
						owner.setStatusText("正在保存...");

						String title = categoryNameTextField.getText();
						String remark = remarkTextArea.getText();
						IncomeService inService = (IncomeService) ObjectFactory
								.getInstance("incomeService");

						Map<String, Object> data = new HashMap<String, Object>();
						data.put("categoryname", title);
						data.put("remark", remark);
						data.put("datetime", GetSystemTime.get());
						inService.addIncomeCategory(data);
						owner.setStatusText("添加成功");
					}
				}.exec();
			}
		});
		getContentPane().add(submitbnt);

		JButton resetbnt = new JButton("\u6E05\u7A7A");
		resetbnt.setBounds(202, 145, 60, 23);
		getContentPane().add(resetbnt);

		super.initUI();
	}
}
