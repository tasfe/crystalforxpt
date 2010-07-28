package crystal;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2003</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;

public class usePanel extends ApplicationFrame
{
  static TextDataFile useDF = new TextDataFile();
  static TableDataSet useDS = new TableDataSet();

  public usePanel(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(600, 450));
    setContentPane(localJPanel);
  }

  private static CategoryDataset createDataset() {
    if (!new File("ʵʱ���ñ�.txt").exists()) {
      JOptionPane.showMessageDialog(null, "ʵʱ���ñ�������Ч����"); //, "ʵʱ���ñ��");
      return null;
    }
    else {
      useDF.setFileName("ʵʱ���ñ�.txt");
      useDS.setDataFile(useDF);
      useDS.open();
      if (useDS.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "ʵʱ���ñ���������Ϊ0");
        return null;
      }
      else {
        useDS.first();
        int count = 0;
        DefaultCategoryDataset localDefaultCategoryDataset = new
            DefaultCategoryDataset();
        String str = "device/host use rate";
        do {
          if(useDS.getDouble("���ñ�") != 0.0d) {
            count++;
            localDefaultCategoryDataset.addValue(useDS.getDouble("���ñ�"), str,
                                                 useDS.getString("IP"));
          }
        }
        while (useDS.next());// && count <=30);
        return localDefaultCategoryDataset;
      }
    }
/*
    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
    String str = "device/host use rate";
    localDefaultCategoryDataset.addValue(59.0D, str, "Norway");
    localDefaultCategoryDataset.addValue(69.0D, str, "Switzerland");
    localDefaultCategoryDataset.addValue(85.0D, str, "France");
    localDefaultCategoryDataset.addValue(93.0D, str, "Syria");
    localDefaultCategoryDataset.addValue(96.0D, str, "Germany");
    localDefaultCategoryDataset.addValue(111.0D, str, "China");
    localDefaultCategoryDataset.addValue(116.0D, str, "Australia");
    localDefaultCategoryDataset.addValue(121.0D, str, "Egypt");
    localDefaultCategoryDataset.addValue(129.0D, str, "England & Wales");
    localDefaultCategoryDataset.addValue(157.0D, str, "New Zealand");
    localDefaultCategoryDataset.addValue(205.0D, str, "Chile");
    localDefaultCategoryDataset.addValue(229.0D, str, "Iran");
    localDefaultCategoryDataset.addValue(359.0D, str, "Singapore");
    localDefaultCategoryDataset.addValue(404.0D, str, "South Africa");
    localDefaultCategoryDataset.addValue(406.0D, str, "Ukraine");
    localDefaultCategoryDataset.addValue(686.0D, str, "USA");
    return localDefaultCategoryDataset;
 */
  }

  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createBarChart("�豸������ TOP-30", "device/host", "use rate 0% ~ 100%", paramCategoryDataset, PlotOrientation.HORIZONTAL, true, true, true);
    localJFreeChart.addSubtitle(new TextTitle("������", new Font("Dialog", 2, 10)));
    localJFreeChart.setBackgroundPaint(Color.white);
    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
    localCategoryPlot.setBackgroundPaint(Color.lightGray);
    localCategoryPlot.setRangeGridlinePaint(Color.white);
    localCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
    localCategoryPlot.setForegroundAlpha(0.5f);

    BarRenderer localBarRenderer = (BarRenderer)localCategoryPlot.getRenderer();
    //localBarRenderer.setMaximumBarWidth(10.0D);
    //localBarRenderer.setMinimumBarLength(10.0D);
    //localBarRenderer.ZERO
    localBarRenderer.setBaseItemLabelsVisible(true);
    localBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    localBarRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator("{0}, {1} = {2} %", new DecimalFormat("0")));
    localBarRenderer.setDrawBarOutline(true);
    localBarRenderer.setBaseOutlinePaint(Color.white);
    localBarRenderer.setBaseOutlineStroke(new BasicStroke(0.5F));
    //����ͼ�ı߽�ֵ���ٷֱȼ���
    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
    localCategoryAxis.setCategoryMargin(0.02D);
    localCategoryAxis.setUpperMargin(0.02D);
    localCategoryAxis.setLowerMargin(0.02D);
    //x�����᷶Χ��setUpperMargin�ǰ������ֵ��һ���ٷֱȣ���100��0.1D��������110
    //setUpperBound��ֱ�Ӷ�������ֵ
    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    //localNumberAxis.setUpperMargin(0.1D);
    localNumberAxis.setUpperBound(100.0D);
    return localJFreeChart;
  }

  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    return new ChartPanel(localJFreeChart);
  }

  public static void main(String[] paramArrayOfString)
  {
    usePanel localBarChartDemo5 = new usePanel("�����豸�����Է���");
    localBarChartDemo5.pack();
    RefineryUtilities.centerFrameOnScreen(localBarChartDemo5);
    localBarChartDemo5.setVisible(true);
  }
}
