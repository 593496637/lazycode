package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IncomeManagerUI extends BaseJPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public IncomeManagerUI() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u6765\u6E90\uFF1A");
		panel.add(label);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		panel.add(label_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u5E8F\u5217\u53F7", "\u91D1\u989D", "\u6536\u5165\u6765\u6E90", "\u5B58\u5165", "\u65F6\u95F4"
			}
		));
		panel_1.add(table.getTableHeader() , BorderLayout.NORTH);
		panel_1.add(table, BorderLayout.CENTER);

	}

	@Override
	protected void init() {
          
	}

}
