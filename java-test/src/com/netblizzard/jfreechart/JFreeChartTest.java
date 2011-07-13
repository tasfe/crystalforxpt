package com.netblizzard.jfreechart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import com.netblizzard.util.Tools;

public class JFreeChartTest extends JFrame {
	private static final long serialVersionUID = 3257566209007171634L;
	

	private double lastValue1;

	private double lastValue2;

	private int dateTickUnit = DateTickUnit.SECOND;
	private int dateTickInterval = 30;
	private final int VIEW_WIDTH = 1000 * 60 * 5;				// 实际可见视图的宽度 
	private final int WIDTH_ONE_DAY = 60 * 60 * 24; 
	private final int SECOND_ONE_HOUR = 1000 * 60 * 60; 
	private final int SECOND_ONE_DAY = 1000 * 60 * 60 * 24; 
//	private final String dateFormat = "yyyy-MM-dd HH:mm:ss";		// X轴时间单位的显示格式
	private final String dateFormat = "HH:mm:ss";
	private int pollInterval = 1;	// 轮询间隔10s,30s,60s

	
	private JSlider slider;
	private final int sliderMax = 1000 * 60 *60 * 24 * 7 / VIEW_WIDTH;
	private int SLIDER_INITIAL_VALUE = sliderMax;
	private DateAxis domainAxis;
//	private int delta = 1000 * 60 * 60 * 24 * 30;
	
	class DataGenerator extends Timer implements ActionListener, ChangeListener {
		private static final long serialVersionUID = 3977867288743720504L;

		public void actionPerformed(ActionEvent actionevent) {

			double d1 = 0.90000000000000002D + 0.20000000000000001D * Math.random();
			lastValue1 = lastValue1 * d1;

			double d2 = 0.90000000000000002D + 0.20000000000000001D * Math.random();
			lastValue2 = lastValue2 * d2;

			addTotalObservation(lastValue1);
			addFreeObservation(lastValue2);
			if(slider.getValue() == sliderMax)
				domainAxis.setRange(System.currentTimeMillis() - VIEW_WIDTH, System.currentTimeMillis());
		}

		DataGenerator() {
			super(pollInterval * 1000, null);
			addActionListener(this);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
		
		}
	}

	public JFreeChartTest() {
		lastValue1 = 100D;
		lastValue2 = 100D;

		total = new TimeSeries("Cpu00", org.jfree.data.time.Second.class);
		// total.setHistoryCount(30000);
		free = new TimeSeries("Cpu01", org.jfree.data.time.Second.class);
		// free.setHistoryCount(30000);

		TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
		timeseriescollection.addSeries(total);
		timeseriescollection.addSeries(free);
		BasicStroke basicstroke = new BasicStroke(2.0F, 0, 2);

		DateAxis horizontaldateaxis = new DateAxis("Time");
		NumberAxis verticalnumberaxis = new NumberAxis("Value");

		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(true, true);

		xylineandshaperenderer.setSeriesPaint(0, Color.red);
		xylineandshaperenderer.setSeriesPaint(1, Color.green);
		xylineandshaperenderer.setStroke(new BasicStroke(3F, 0, 2));

		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("利用率", "Time", "Value", timeseriescollection, true, true, false);

		XYPlot xyplot = jfreechart.getXYPlot();
		xyplot.setDomainCrosshairVisible(true);
		xyplot.setRangeCrosshairVisible(true);
		xyplot.addRangeMarker(new ValueMarker(100D));
		ValueAxis valueaxis = xyplot.getDomainAxis();
		XYLineAndShapeRenderer renderer1 = (XYLineAndShapeRenderer)xyplot.getRenderer();
		Ellipse2D.Double localDouble = new Ellipse2D.Double(-1.0D, -1.0D, 2.0D, 2.0D);
		renderer1.setBaseShapesVisible(true);
		renderer1.setSeriesShape(0, localDouble);
		renderer1.setSeriesPaint(0, Color.red);
		renderer1.setSeriesShape(1, localDouble);
		renderer1.setSeriesPaint(1, Color.green);
//		renderer1.setSeriesFillPaint(0, Color.yellow);
//		renderer1.setSeriesOutlinePaint(0, Color.gray);

		valueaxis.setAutoRange(true);
//		valueaxis.setFixedAutoRange(VIEW_WIDTH_ONE_DAY * 1000D);
		valueaxis.setFixedAutoRange(100 * 1000D);
//		DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
		domainAxis = (DateAxis)xyplot.getDomainAxis();
		domainAxis.setTickUnit( new DateTickUnit(dateTickUnit, dateTickInterval) );
		domainAxis.setDateFormatOverride(new SimpleDateFormat(dateFormat));
		domainAxis.setRange(System.currentTimeMillis() - WIDTH, System.currentTimeMillis());
		ValueAxis valueaxis2 = xyplot.getRangeAxis();
		valueaxis2.setRange(0.0D, 200D);

		
		ChartPanel chartPanel = new ChartPanel(jfreechart);
		chartPanel.setPreferredSize(new Dimension(900,500));
		chartPanel.setDomainZoomable(true);
		chartPanel.setRangeZoomable(true);
//		chartpanel.setPreferredSize(new Dimension(WIDTH_ONE_DAY / pollInterval,700));
		chartPanel.setPopupMenu(null);
		getContentPane().add(chartPanel);
		
		JPanel dashboard = new JPanel(new BorderLayout());
		dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		this.slider = new JSlider(0, sliderMax, SLIDER_INITIAL_VALUE);
		this.slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int value = slider.getValue();
//				long minimum = domainAxis.getMinimumDate().getTime();
//				long maximum = domainAxis.getMaximumDate().getTime();
//				if (value < lastValue) { // left
//					minimum = minimum - delta;
//					maximum = maximum - delta;
//				} else { // right
//					minimum = minimum + delta;
//					maximum = maximum + delta;
//				}
				long now = System.currentTimeMillis();
				long maximum = now - (sliderMax - value) * VIEW_WIDTH;
				long minimum = maximum - VIEW_WIDTH;
				DateRange range = new DateRange(minimum, maximum);
				domainAxis.setRange(range);
			}
		});
		dashboard.add(this.slider);
		getContentPane().add(dashboard, BorderLayout.SOUTH);
		
		(new DataGenerator()).start();
		
		// this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				this_windowClosing(e);
			}
		});
	}
	
	void this_windowClosing(WindowEvent e) {
		this.dispose();
	}

	private void addTotalObservation(double d) {
		total.addOrUpdate(new Second(), d);
	}

	private void addFreeObservation(double d) {
		free.addOrUpdate(new Second(), d);
	}

	private TimeSeries total;

	private TimeSeries free;
	
	public static void main(String args[]) {
		JFreeChartTest frame = new JFreeChartTest();
		frame.setPreferredSize(new Dimension(1000, 600));
		Tools.dispWin(frame);
	}
}
