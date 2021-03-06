package jfree;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.encoders.KeypointPNGEncoderAdapter;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.NumberFormat;

import javax.imageio.ImageIO;

public class BarChartExample {
	public static void main(String[] args) {
		// Create a simple pie chart
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.setValue(1,"xAxis1","yAxis11111111");
		categoryDataset.setValue(2,"xAxis2","yAxis22222222");
		categoryDataset.setValue(3,"xAxis3","yAxis33333333");
		categoryDataset.setValue(2,"xAxis4","yAxis44444444");
		JFreeChart chart = ChartFactory.createBarChart(
				"CSC408 Mark Distribution", // Title
				"categoryAxisLabel",
				"valueAxisLabel",
				categoryDataset, // Dataset
				PlotOrientation.VERTICAL,
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		CategoryPlot  plot=chart.getCategoryPlot();
		plot.setBackgroundAlpha(0);
		chart.setBackgroundPaint(new Color(255,255,255,0));
		plot.getRangeAxis().setAutoRangeMinimumSize(1);
		plot.getDomainAxis().setMaximumCategoryLabelLines(5);
		plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setBaseItemLabelGenerator(
			    new StandardCategoryItemLabelGenerator(
			        "{2}", NumberFormat.getInstance()));
			renderer.setBaseItemLabelsVisible(true);
			
		
			BufferedImage bf= chart.createBufferedImage(420, 420, BufferedImage.TYPE_INT_ARGB, null);
		
		try {
			File file = new File("src/jfreechart/BarChartExample.png");
			ImageIO.write(bf, "PNG", file);
			System.out.println("save file:"+file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
