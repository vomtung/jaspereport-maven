package jfree;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BarChartExample {
	public static void main(String[] args) {
		// Create a simple pie chart
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.setValue(1,"xAxis","yAxis1");
		categoryDataset.setValue(2,"xAxis1","yAxis2");
		categoryDataset.setValue(3,"xAxis2","yAxis3");
		categoryDataset.setValue(4,"xAxis3","yAxis4");
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
		Plot plot=chart.getPlot();
		plot.setBackgroundAlpha(0);
		BufferedImage bf= chart.createBufferedImage(420, 420);
		
		try {
			File file = new File("src/jfreechart/BarChartExample.png");
			ImageIO.write(bf, "PNG", file);
			System.out.println("save file:"+file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
