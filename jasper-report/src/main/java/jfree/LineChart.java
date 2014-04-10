package jfree;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.NumberFormat;

import javax.imageio.ImageIO;

public class LineChart {
	public static void main(String[] args) {
		// Create a simple pie chart
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.setValue(1,"Line1","xAxis1");
		categoryDataset.setValue(1,"Line1","xAxis2");
		categoryDataset.setValue(2,"Line1","xAxis3");
		categoryDataset.setValue(3,"Line1","xAxis4");
		
		categoryDataset.setValue(2,"Line2","xAxis2");
		categoryDataset.setValue(3,"Line2","xAxis3");
		categoryDataset.setValue(4,"Line2","xAxis4");
		categoryDataset.setValue(3,"Line2","xAxis5");
		
		JFreeChart chart = ChartFactory.createLineChart(
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
		CategoryItemRenderer renderer = plot.getRenderer();
		renderer.setBaseItemLabelGenerator(
			    new StandardCategoryItemLabelGenerator(
			        "{2}", NumberFormat.getInstance()));
			renderer.setBaseItemLabelsVisible(true);
		BufferedImage bf= chart.createBufferedImage(420, 420);
		
		try {
			File file = new File("src/jfreechart/LineChart.png");
			ImageIO.write(bf, "PNG", file);
			System.out.println("save file:"+file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
