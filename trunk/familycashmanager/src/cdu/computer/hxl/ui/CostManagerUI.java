package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.UIManager;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;

/**
 * ֧����¼�������
 * 
 * @author hxl
 * 
 */
public class CostManagerUI extends BaseJPanel {

	private static final long serialVersionUID = -9039061244691608492L;
	private final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");
	private JTextField textField = null;
	private JTextField textField_1 = null;
	private JTable costDataTable = null;

	public CostManagerUI() {
		setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.BLACK);
		add(searchPanel, BorderLayout.NORTH);

		JLabel label = new JLabel("\u91D1\u989D\uFF1A");
		searchPanel.add(label);

		textField = new JTextField();
		searchPanel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		searchPanel.add(label_1);

		textField_1 = new JTextField();
		searchPanel.add(textField_1);
		textField_1.setColumns(10);

		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchPanel.add(searchButton);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));

		costDataTable = new JTable() {

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
//		costDataTable.setSelectionBackground(new Color(240, 188, 66));
//		costDataTable.setSelectionForeground(Color.WHITE);
		costDataTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "���к�", "���", "��;", "��Դ", "��ע", "ʱ��" }));
		tablePanel.add(costDataTable.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(costDataTable, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	protected void init() {

	}

	public void loadData() {
		DefaultTableModel dtm = (DefaultTableModel) costDataTable.getModel();
		Object[][] o = cService.loadCostForObject(null);
		int len = o.length;
		for (int i = 0; i < len; i++) {
			dtm.addRow(o[i]);
		}
	}
}
