package com.netblizzard.jfreechart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DateRange;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/** @see http://stackoverflow.com/questions/5048852 */
public class DTSCTest extends ApplicationFrame {
	private static final String TITLE = "Dynamic Series";
	private static final String START = "Start";
	private static final String STOP = "Stop";
	private static final float MINMAX = 100;
//	private static final int COUNT = 24 * 60;
	private static final int FAST = 1000;
	private static final int SLOW = FAST * 5;
	private static final Random random = new Random();
	private Timer timer;
	
	private String dateFormat = "yyyy:MM:dd HH:mm:ss";		// X轴时间单位的显示格式
	private static int SLIDER_INITIAL_VALUE = 100;
	private static int ALL_LENGTH = 1000 * 60 * 60;
	private static final int COUNT = ALL_LENGTH / 1000;
	private JSlider slider;
	private DateAxis domainAxis;
	private int lastValue = SLIDER_INITIAL_VALUE; // one month
													// (milliseconds,
													// seconds, minutes,
													// hours, days)
	private int delta = ALL_LENGTH / 100;
	GregorianCalendar begin;
	GregorianCalendar end;
	GregorianCalendar now;

	public DTSCTest(final String title) {
		super(title);
		now = new GregorianCalendar();
		now.add(GregorianCalendar.MINUTE, -1);
		begin = new GregorianCalendar();
		begin.set(GregorianCalendar.HOUR_OF_DAY, 0);
		begin.set(GregorianCalendar.MINUTE, 0);
		begin.set(GregorianCalendar.SECOND, 0);
		begin.set(GregorianCalendar.MILLISECOND, 0);
		end = new GregorianCalendar();
		end.add(GregorianCalendar.DAY_OF_MONTH, -7);
		
		final DynamicTimeSeriesCollection dataset = new DynamicTimeSeriesCollection(2, COUNT / 100, new Second());
		dataset.setTimeBase(new Second(now.get(GregorianCalendar.SECOND), now.get(GregorianCalendar.MINUTE), now.get(GregorianCalendar.HOUR_OF_DAY),
				now.get(GregorianCalendar.DAY_OF_MONTH), now.get(GregorianCalendar.MONTH) + 1, now.get(GregorianCalendar.YEAR)));
		//dataset.addSeries(gaussianData(), 0, "Gaussian data");
		dataset.addSeries(new float[COUNT], 0, "data 0");
		dataset.addSeries(gaussianData(), 1, "Gaussian data");
		JFreeChart chart = createChart(dataset);
		final JButton run = new JButton(STOP);
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				if (STOP.equals(cmd)) {
					timer.stop();
					run.setText(START);
				} else {
					timer.start();
					run.setText(STOP);
				}
			}
		});
		final JComboBox combo = new JComboBox();
		combo.addItem("Fast");
		combo.addItem("Slow");
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("Fast".equals(combo.getSelectedItem())) {
					timer.setDelay(FAST);
				} else {
					timer.setDelay(SLOW);
				}
			}
		});
		this.add(new ChartPanel(chart), BorderLayout.CENTER);
		JPanel btnPanel = new JPanel(new FlowLayout());
		btnPanel.add(run);
		btnPanel.add(combo);
		this.add(btnPanel, BorderLayout.SOUTH);
		timer = new Timer(FAST, new ActionListener() {
			float[] newData = new float[1];

			@Override
			public void actionPerformed(ActionEvent e) {
				newData[0] = randomValue();
				dataset.advanceTime();
				dataset.appendData(newData);
			}
		});
		
		JPanel dashboard = new JPanel(new BorderLayout());
		dashboard.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
		this.slider = new JSlider(0, 100, SLIDER_INITIAL_VALUE);
		this.slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int value = slider.getValue();
				long minimum = domainAxis.getMinimumDate().getTime();
				long maximum = domainAxis.getMaximumDate().getTime();
				if (value < lastValue) { // left
					minimum = minimum - delta;
					maximum = maximum - delta;
				} else { // right
					minimum = minimum + delta;
					maximum = maximum + delta;
				}
				
				// domainAxis.setRange(System.currentTimeMillis() - SECOND_ONE_DAY, System.currentTimeMillis());
				
				
				if(minimum < begin.getTimeInMillis()) {
					minimum = begin.getTimeInMillis();
				}
				System.out.println("today begin ...  " + value);
				long tmp = System.currentTimeMillis();
				maximum = tmp + ALL_LENGTH - ALL_LENGTH / 100 * (100 - value);
				minimum = tmp + ALL_LENGTH - ALL_LENGTH / 100 * (100 - value) - delta;
				DateRange range = new DateRange(minimum, maximum);
				domainAxis.setRange(range);
				lastValue = value;
			}
		});
		dashboard.add(this.slider);
		getContentPane().add(dashboard, BorderLayout.NORTH);
		setPreferredSize(new Dimension(800, 600));
	}

	private float randomValue() {
		return (float) (random.nextGaussian() * MINMAX / 3);
	}

	private float[] gaussianData() {
		float[] a = new float[COUNT * 1000];
		for (int i = 0; i < a.length - 1; i++) {
			a[i] = 30;
		}
		a[a.length - 1] = 30;
//		a[a.length - 1] = randomValue();
		return a;
	}

	private JFreeChart createChart(final XYDataset dataset) {
		final JFreeChart result = ChartFactory.createTimeSeriesChart(TITLE, "hh:mm:ss", "milliVolts", dataset, true, true, false);
		final XYPlot plot = result.getXYPlot();
		ValueAxis domain = plot.getDomainAxis();
		domain.setAutoRange(true);
		ValueAxis range = plot.getRangeAxis();
		range.setRange(0, MINMAX);
		
//		DateAxis domainDate = (DateAxis)plot.getDomainAxis();
		domainAxis = (DateAxis)plot.getDomainAxis();
//		domainAxis.setRange(new DateRange(System.currentTimeMillis(), System.currentTimeMillis() - delta));
//		domainAxis.setInverted(true);
		domainAxis.setDateFormatOverride(new SimpleDateFormat(dateFormat));
		return result;
	}

	public void start() {
		timer.start();
	}

	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				DTSCTest demo = new DTSCTest(TITLE);
				demo.pack();
				RefineryUtilities.centerFrameOnScreen(demo);
				demo.setVisible(true);
				demo.start();
			}
		});
	}
}
