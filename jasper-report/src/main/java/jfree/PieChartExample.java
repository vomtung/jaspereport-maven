package jfree;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PieChartExample {
	public static void main(String[] args) {
		// Create a simple pie chart
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		pieDataset.setValue("A", new Integer(75));
		pieDataset.setValue("B", new Integer(10));
		pieDataset.setValue("C", new Integer(10));
		pieDataset.setValue("D", new Integer(5));
		JFreeChart chart = ChartFactory.createPieChart(
				"CSC408 Mark Distribution", // Title
				pieDataset, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		BufferedImage bf= chart.createBufferedImage(420, 420);
		try {
			File file = new File("src/jfreechart/PieChartExample.png");
			System.out.println("sss:"+file.getAbsolutePath());
			ImageIO.write(bf, "PNG", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
