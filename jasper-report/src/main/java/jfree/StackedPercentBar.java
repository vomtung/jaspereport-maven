package jfree;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
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
import java.text.NumberFormat;

import javax.imageio.ImageIO;

public class StackedPercentBar {
	public static void main(String[] args) {
		// Create a simple pie chart
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.setValue(0.10,"Bar1","xAxis11111111 aaaaaaaaaaaaaa");
		categoryDataset.setValue(0.50,"Bar2","xAxis11111111 aaaaaaaaaaaaaa");
		categoryDataset.setValue(0.40,"Bar3","xAxis11111111 aaaaaaaaaaaaaa");
		
		categoryDataset.setValue(0.20,"Bar1","xAxis222222222 aaaaaaaaaaaaaa");
		categoryDataset.setValue(0.30,"Bar2","xAxis222222222 aaaaaaaaaaaaaa");
		categoryDataset.setValue(0.50,"Bar3","xAxis222222222 aaaaaaaaaaaaaa");
		
		categoryDataset.setValue(0.30,"Bar1","xAxis333333333 aaaaaaaaaaaaaa");
		categoryDataset.setValue(0.30,"Bar2","xAxis333333333 aaaaaaaaaaaaaa");
		categoryDataset.setValue(0.40,"Bar3","xAxis333333333 aaaaaaaaaaaaaa");
		
		JFreeChart chart = ChartFactory.createStackedBarChart(
				"CSC408 Mark Distribution", // Title
				"categoryAxisLabel",
				"valueAxisLabel",
				categoryDataset, // Dataset
				PlotOrientation.HORIZONTAL,
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		CategoryPlot  plot=chart.getCategoryPlot();
		plot.setBackgroundAlpha(0);
		chart.setBackgroundPaint(new Color(255,255,255,0));
		CategoryAxis domainAxis = plot.getDomainAxis();
		plot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
		domainAxis.setMaximumCategoryLabelLines(5);
		plot.getRangeAxis().setAutoRangeMinimumSize(1);
		NumberAxis ca= (NumberAxis)plot.getRangeAxis();
		ca.setNumberFormatOverride(NumberFormat.getPercentInstance());
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setBaseItemLabelGenerator(
			    new StandardCategoryItemLabelGenerator(
			        "{3}", NumberFormat.getInstance()));
			renderer.setBaseItemLabelsVisible(true);
		BufferedImage bf= chart.createBufferedImage(420, 420);
		
		try {
			File file = new File("src/jfreechart/StackedPercentBarChart.png");
			ImageIO.write(bf, "PNG", file);
			System.out.println("save file:"+file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
