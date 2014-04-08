/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package image;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
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
import net.sf.jasperreports.engine.util.AbstractSampleApp;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ImagesApp.java 5876 2013-01-07 19:05:05Z teodord $
 */
public class ImagesReport {

	String jrxmlFileName;
	String jasperSrcFile;
	String jprintSrcFile;

	/**
	 *
	 */
	public static void main(String[] args) {
		ImagesReport ir = new  ImagesReport("src/imagesreports/ImagesReport.jrxml");
		try {
			ir.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImagesReport(String jrxmlFileName) {
		super();
		this.jrxmlFileName = jrxmlFileName;
	}

	/**
	 * @throws IOException 
	 *
	 */
	public void test() throws JRException, IOException {
		compile();
		fill();
		//printToImg();
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
	 * @throws IOException 
	 *
	 */
	public void fill() throws JRException, IOException {
		long start = System.currentTimeMillis();
		DataBeanList DataBeanList = new DataBeanList();
		ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				dataList);
		Map parameters=new HashMap();
		BufferedImage image = ImageIO.read(new File("src/imagesreports/example.jpg"));
		parameters.put("logo", image );
		this.jprintSrcFile=JasperFillManager.fillReportToFile("src/imagesreports/ImagesReport.jasper",
				parameters, beanColDataSource);
	}

	public void compile() throws JRException {
		this.jasperSrcFile= JasperCompileManager.compileReportToFile(this.jrxmlFileName);
		System.out.println("Compile Report success:"+this.jasperSrcFile);
	}
	
	
	public class DataBean {
		   private String subjectName;
		   private Integer marks;

		   public String getSubjectName() {
		      return subjectName;
		   }

		   public void setSubjectName(String subjectName) {
		      this.subjectName = subjectName;
		   }

		   public Integer getMarks() {
		      return marks;
		   }

		   public void setMarks(Integer marks) {
		      this.marks = marks;
		   }

		}
	
	
	public class DataBeanList {
		public ArrayList getDataBeanList() {
			ArrayList dataBeanList = new ArrayList();
			dataBeanList.add(produce("English", 58));
			dataBeanList.add(produce("SocialStudies", 68));
			dataBeanList.add(produce("Maths", 38));
			dataBeanList.add(produce("Hindi", 88));
			dataBeanList.add(produce("Scince", 78));
			return dataBeanList;
		}

		/*
		 * This method returns a DataBean object, with subjectName , and marks
		 * set in it.
		 */
		private DataBean produce(String subjectName, Integer marks) {
			DataBean dataBean = new DataBean();

			dataBean.setSubjectName(subjectName);
			dataBean.setMarks(marks);

			return dataBean;
		}
	}
	/**
	 * vominhtung
	 */

	public void printToImg() throws JRException {
		System.err.println("PrintImg are called");
		long start = System.currentTimeMillis();
		Image image = JasperPrintManager.printPageToImage(
				"build/reports/ImagesReport.jrprint", 0, 1);

		System.err.println("PrintImging time : "
				+ (System.currentTimeMillis() - start));
		BufferedImage bufferedImage = (BufferedImage) image;
		System.err.println("height:" + bufferedImage.getHeight());
		try {
			// retrieve image
			// BufferedImage bi = getMyImage();
			File outputfile = new File("build/reports/saved.png");
			ImageIO.write(bufferedImage, "png", outputfile);
		} catch (IOException e) {
		}
	}

	/**
	 *
	 */
	public void print() throws JRException {
		System.err.println("Print are called");
		long start = System.currentTimeMillis();
		JasperPrintManager.printReport("build/reports/ImagesReport.jrprint",
				true);
		System.err.println("Printing time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void pdf() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile(this.jprintSrcFile);
		System.err.println("PDF creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void xml() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToXmlFile(
				"build/reports/ImagesReport.jrprint", false);
		System.err.println("XML creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void xmlEmbed() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToXmlFile(
				"build/reports/ImagesReport.jrprint", true);
		System.err.println("XML creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void html() throws JRException {
		long start = System.currentTimeMillis();
		JasperExportManager
				.exportReportToHtmlFile("build/reports/ImagesReport.jrprint");
		System.err.println("HTML creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void rtf() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".rtf");

		JRRtfExporter exporter = new JRRtfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("RTF creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void xls() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".xls");

		JRXlsExporter exporter = new JRXlsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);

		exporter.exportReport();

		System.err.println("XLS creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void jxl() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".jxl.xls");

		JExcelApiExporter exporter = new JExcelApiExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.TRUE);

		exporter.exportReport();

		System.err.println("XLS creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void csv() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".csv");

		JRCsvExporter exporter = new JRCsvExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("CSV creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void odt() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".odt");

		JROdtExporter exporter = new JROdtExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("ODT creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void ods() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".ods");

		JROdsExporter exporter = new JROdsExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("ODS creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void docx() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".docx");

		JRDocxExporter exporter = new JRDocxExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("DOCX creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void xlsx() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".xlsx");

		JRXlsxExporter exporter = new JRXlsxExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
				Boolean.FALSE);

		exporter.exportReport();

		System.err.println("XLSX creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void pptx() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".pptx");

		JRPptxExporter exporter = new JRPptxExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("PPTX creation time : "
				+ (System.currentTimeMillis() - start));
	}

	/**
	 *
	 */
	public void xhtml() throws JRException {
		long start = System.currentTimeMillis();
		File sourceFile = new File("build/reports/ImagesReport.jrprint");

		JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(sourceFile);

		File destFile = new File(sourceFile.getParent(), jasperPrint.getName()
				+ ".x.html");

		JRXhtmlExporter exporter = new JRXhtmlExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				destFile.toString());

		exporter.exportReport();

		System.err.println("XHTML creation time : "
				+ (System.currentTimeMillis() - start));
	}

}
