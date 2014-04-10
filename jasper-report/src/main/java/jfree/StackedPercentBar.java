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
import org.jfree.chart.renderer.category.StackedBarRenderer;
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
		categoryDataset.setValue(40,"Bar1","xAxis1");
		categoryDataset.setValue(50,"Bar2","xAxis1");
		categoryDataset.setValue(40,"Bar3","xAxis1");
		
		categoryDataset.setValue(20,"Bar1","xAxis2");
		categoryDataset.setValue(30,"Bar2","xAxis2");
		categoryDataset.setValue(50,"Bar3","xAxis2");
		
		categoryDataset.setValue(30,"Bar1","xAxis3");
		categoryDataset.setValue(30,"Bar2","xAxis3");
		categoryDataset.setValue(40,"Bar3","xAxis3");
		
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
		StackedBarRenderer renderer = (StackedBarRenderer)plot.getRenderer();
		renderer.setBaseItemLabelGenerator(
			    new StandardCategoryItemLabelGenerator(
			        "{2}", NumberFormat.getInstance()));
		renderer.setBaseItemLabelsVisible(true);
		renderer.setRenderAsPercentages(true);
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
