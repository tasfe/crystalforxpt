package com.netblizzard.jfreechart;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
 
class DataInfo {
  private int width = 800;
  private int timeunit = DateTickUnit.HOUR;
  private int timenum = 1;
 
  public DataInfo(int width, int timeunit, int timenum) {
    this.width = width;
    this.timeunit = timeunit;
    this.timenum = timenum;
  }
  public int getWidth() {
    return this.width;
  }
  public int getTimeunit() {
    return this.timeunit;
  }
  public int getTimenum() {
    return this.timenum;
  }
}
 
public class TestJFreeChartPanel extends JScrollPane {
 
  public TestJFreeChartPanel(DataInfo datainfo) {
    int width = datainfo.getWidth();
    XYDataset xydataset = createDataset();
    JFreeChart jfreechart = createChart(xydataset, datainfo.getTimeunit(), datainfo.getTimenum());
        ChartPanel chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(width, 270));
        chartpanel.setMaximumDrawWidth(width);
 
        JViewport viewport = new JViewport();
        viewport.setView(chartpanel);
 
    //JScrollPane
    setViewport(viewport);
    setPreferredSize(new Dimension(500, 300));
  }
 
  private static XYDataset createDataset()
    {
        TimeSeries timeseries = new TimeSeries("Series 1", org.jfree.data.time.Hour.class);
        TimeSeries timeseries1 = new TimeSeries("Series 2", org.jfree.data.time.Hour.class);
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        timeseriescollection.addSeries(timeseries);
        timeseriescollection.addSeries(timeseries1);
        return timeseriescollection;
    }
 
  private static JFreeChart createChart(XYDataset xydataset, int timeunit, int timenum)
    {
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Time Series Demo 3", "Time", "Value", xydataset, true, true, false);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();
        dateaxis.setTickUnit(new DateTickUnit(1, 1, new SimpleDateFormat("MMM-yyyy")));
        dateaxis.setVerticalTickLabels(true);
 
        Date[] dateRange = createTimeRange();
        dateaxis.setRange(dateRange[0], dateRange[1]);
 
        DateTickUnit tickunit = new DateTickUnit(timeunit, timenum, new SimpleDateFormat("HH:mm", Locale.US));
        dateaxis.setTickUnit(tickunit);
 
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
        xylineandshaperenderer.setBaseShapesVisible(true);
        xylineandshaperenderer.setSeriesFillPaint(0, Color.red);
        xylineandshaperenderer.setSeriesFillPaint(1, Color.white);
        xylineandshaperenderer.setUseFillPaint(true);
        xylineandshaperenderer.setLegendItemToolTipGenerator(new StandardXYSeriesLabelGenerator("Tooltip {0}"));
        return jfreechart;
    }
 
  private static Date[] createTimeRange() {
    Date[] dateRange = new Date[2];
 
    try
    {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      dateRange[0] = format.parse("2008-04-10 00:00");
      dateRange[1] = format.parse("2008-04-20 00:00");
    }
    catch(Exception ex)
    {
      System.out.println(ex);
    }
 
    return dateRange;
  }
 
  private static void createAndShowGUI()
    {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
 
        //Create and set up the window.
        JFrame frame = new JFrame("TimeSeriesDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
//        DataInfo datainfo = new DataInfo(9300, DateTickUnit.HOUR, 4);
        // DataInfo datainfo = new DataInfo(345720, DateTickUnit.MINUTE, 5);
        DataInfo datainfo = new DataInfo(3000, DateTickUnit.MINUTE, 100);
        TestJFreeChartPanel newContentPane = new TestJFreeChartPanel(datainfo);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

