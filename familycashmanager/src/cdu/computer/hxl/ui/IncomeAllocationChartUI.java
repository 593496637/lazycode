package cdu.computer.hxl.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.IncomeService;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class IncomeAllocationChartUI extends BaseJPanel {

	private static final long serialVersionUID = -8114364422893235600L;
	private static final IncomeService inService = (IncomeService) ObjectFactory
			.getInstance("incomeService");

	public IncomeAllocationChartUI() {

	}

	@Override
	protected void init() {

		new ThreadExecutorUtils() {

			@Override
			protected void task() {

				// DefaultPieDataset dataset = new DefaultPieDataset();
				//
				// Map<String, Object> data = inService.statistiIncome();
				//
				// Set<Map.Entry<String, Object>> dataSet = data.entrySet();
				// Iterator<Map.Entry<String, Object>> iter =
				// dataSet.iterator();
				//
				// while (iter.hasNext()) {
				// Map.Entry<String, Object> entry = iter.next();
				// dataset.setValue(entry.getKey(), (Double) entry.getValue());
				// }
				//
				// JFreeChart chart = ChartFactory.createPieChart("����ֲ�ͼ", //
				// chart
				// // title
				// dataset, // data
				// true, // include legend
				// true, false);
				//
				// Font font = new Font("����", Font.PLAIN, 12);
				//
				// chart.getTitle().setFont(new Font("����", Font.PLAIN, 18));
				// chart.getLegend().setItemFont(font);
				//
				// PiePlot plot = (PiePlot) chart.getPlot();
				// plot.setLabelFont(font);
				// plot.setNoDataMessage("û������");
				// plot.setCircular(false);
				// plot.setLabelGap(0.02);
				//
				// JPanel panel = new ChartPanel(chart);
				// panel.setPreferredSize(new Dimension(400, 280));
				addComponent(createAllocationPanel());

				addComponent(createIncomeMoneyAllocationPanel());
			}
		}.exec();

	}

	protected JPanel createIncomeMoneyAllocationPanel() {

		Map<String, Double> data = inService.statistiIncomeForMoneyallocation();

		Bar3DChartUI barChart = new Bar3DChartUI();

		JPanel panel = barChart.createBar3DChartPanel("�ʽ�������״ͼ", data);
		panel.setPreferredSize(new Dimension(400, 280));
		return panel;
	}

	protected JPanel createAllocationPanel() {
		Map<String, Object> data = inService.statistiIncome();
		PieChartUI pie = new PieChartUI();
		return pie.createPieChartPanel("����ֲ�ͼ", data);
	}

	protected void addComponent(Component comp) {
		this.add(comp);
		validate();
	}
}
