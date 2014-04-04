package jasperreport;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class BarChart {
	private String jasperSrcFile;
	private String jprintSrcFile;
	private String sourceFileName;
	public BarChart(String sourceFileName) {
		super();
		this.sourceFileName = sourceFileName;
	}

	public BarChart(String jasperSrcFile, String jprintSrcFile) {
		super();
		this.jasperSrcFile = jasperSrcFile;
		this.jprintSrcFile = jprintSrcFile;
	}

	public class ChartComponent {
		public ChartComponent(String subjectName, String xAxis, Integer yAxis) {
			super();
			this.subjectName = subjectName;
			this.xAxis = xAxis;
			this.yAxis = yAxis;
		}

		private String subjectName;
		private String xAxis;
		private Integer yAxis;

		public String getSubjectName() {
			return subjectName;
		}

		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}

		public String getxAxis() {
			return xAxis;
		}

		public void setxAxis(String xAxis) {
			this.xAxis = xAxis;
		}

		public Integer getyAxis() {
			return yAxis;
		}

		public void setyAxis(Integer yAxis) {
			this.yAxis = yAxis;
		}
	}
	public class ChartComponentList {
		   public ArrayList initDataList() {
		      ArrayList chartComponentList = new ArrayList();

		      chartComponentList.add(new ChartComponent("Iterraction 1","New Zealand - Finance -AR Clerk",1));
		      chartComponentList.add(new ChartComponent("Iterraction 2","New Zealand - Finance -AR Clerk",4));
		      chartComponentList.add(new ChartComponent("Iterraction 3","New Zealand - Finance -AR Clerk",3));
		      chartComponentList.add(new ChartComponent("Iterraction 4","New Zealand - Finance -AR Clerk",2));
		      
		      chartComponentList.add(new ChartComponent("Iterraction 1","New Zealand - Finance -AP Clerk",1));
		      chartComponentList.add(new ChartComponent("Iterraction 2","New Zealand - Finance -AP Clerk",4));
		      chartComponentList.add(new ChartComponent("Iterraction 3","New Zealand - Finance -AP Clerk",3));
		      chartComponentList.add(new ChartComponent("Iterraction 4","New Zealand - Finance -AP Clerk",2));
		      
		      chartComponentList.add(new ChartComponent("Iterraction 1","New Zealand - Finance",9));
		      chartComponentList.add(new ChartComponent("Iterraction 2","New Zealand - Finance",4));
		      chartComponentList.add(new ChartComponent("Iterraction 3","New Zealand - Finance",6));
		      chartComponentList.add(new ChartComponent("Iterraction 4","New Zealand - Finance",2));
		      
		      chartComponentList.add(new ChartComponent("Iterraction 1","Vietname - Finance",5));
		      chartComponentList.add(new ChartComponent("Iterraction 2","Vietname - Finance",6));
		      chartComponentList.add(new ChartComponent("Iterraction 3","Vietname - Finance",7));
		      chartComponentList.add(new ChartComponent("Iterraction 4","Vietname - Finance",3));
		      
		      
		      return chartComponentList;
		   }
		}

	/**
	 *
	 */
	public static void main(String[] args) 
	{
		BarChart imageapp=new BarChart("src/jasper/BarChart.jrxml");
		try{
		imageapp.compileReport();
		imageapp.fill();
		imageapp.test();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void compileReport() throws JRException
	{
		this.jasperSrcFile= JasperCompileManager.compileReportToFile(this.sourceFileName);
		System.out.println("Compile Report success:"+this.jasperSrcFile);
	}
	
	/**
	 *
	 */
	public void test() throws JRException
	{
		saveToImg(0);
		pdf();
		/*xmlEmbed();
		xml();
		html();
		rtf();
		xls();
		jxl();
		csv();
		odt();
		ods();
		docx();
		xlsx();
		pptx();
		xhtml();*/
	}
	
	
	/**
	 *
	 */
	public void fill() throws JRException
	{
		long start = System.currentTimeMillis();
		ChartComponentList chartComponentList = new ChartComponentList();
		ArrayList<ChartComponent> dataList = chartComponentList.initDataList();
		JRBeanCollectionDataSource beanColDataSource =new JRBeanCollectionDataSource(dataList);
		this.jprintSrcFile=JasperFillManager.fillReportToFile(this.jasperSrcFile, null, beanColDataSource);
		System.out.println("Filling success :"+this.jprintSrcFile);
	}
	
	/**
	 * vominhtung
	 */
	 
	public void saveToImg(int page) throws JRException
	{
		System.out.println("PrintImg are called");
		long start = System.currentTimeMillis();
		Image image=JasperPrintManager.printPageToImage(this.jprintSrcFile,page, 1);
		
		System.err.println("PrintImging time : " + (System.currentTimeMillis() - start));
		BufferedImage bufferedImage = (BufferedImage) image;
		System.err.println("height:"+bufferedImage.getHeight());
		try {
			// retrieve image
			//BufferedImage bi = getMyImage();
			File outputfile = new File("src/jasper/saved.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
		}
	}
	
	/**
	 *
	 */
	public void print() throws JRException
	{
		System.err.println("Print are called");
		long start = System.currentTimeMillis();
		JasperPrintManager.printReport(this.jprintSrcFile, true);
		System.err.println("Printing time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void pdf() throws JRException
	{
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile(this.jprintSrcFile);
		System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void xml() throws JRException
	{
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToXmlFile(this.jprintSrcFile, false);
		System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void xmlEmbed() throws JRException
	{
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToXmlFile(this.jprintSrcFile, true);
		System.err.println("XML creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void html() throws JRException
	{
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToHtmlFile(this.jprintSrcFile);
		System.err.println("HTML creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void rtf() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".rtf");
		
		JRRtfExporter exporter = new JRRtfExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		
		exporter.exportReport();

		System.err.println("RTF creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void xls() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");
		
		JRXlsExporter exporter = new JRXlsExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		
		exporter.exportReport();

		System.err.println("XLS creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void jxl() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".jxl.xls");

		JExcelApiExporter exporter = new JExcelApiExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);

		exporter.exportReport();

		System.err.println("jxl creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void csv() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");
		
		JRCsvExporter exporter = new JRCsvExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		
		exporter.exportReport();

		System.err.println("CSV creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void odt() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".odt");
		
		JROdtExporter exporter = new JROdtExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		
		exporter.exportReport();

		System.err.println("ODT creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void ods() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".ods");
		
		JROdsExporter exporter = new JROdsExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		
		exporter.exportReport();

		System.err.println("ODS creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void docx() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".docx");
		
		JRDocxExporter exporter = new JRDocxExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		
		exporter.exportReport();

		System.err.println("DOCX creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void xlsx() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xlsx");
		
		JRXlsxExporter exporter = new JRXlsxExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		
		exporter.exportReport();

		System.err.println("XLSX creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void pptx() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".pptx");
		
		JRPptxExporter exporter = new JRPptxExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

		exporter.exportReport();

		System.err.println("PPTX creation time : " + (System.currentTimeMillis() - start));
	}
	
	
	/**
	 *
	 */
	public void xhtml() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File(this.jprintSrcFile);

		JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".x.html");
		
		JRXhtmlExporter exporter = new JRXhtmlExporter();
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
		
		exporter.exportReport();

		System.err.println("XHTML creation time : " + (System.currentTimeMillis() - start));
	}
	

}
