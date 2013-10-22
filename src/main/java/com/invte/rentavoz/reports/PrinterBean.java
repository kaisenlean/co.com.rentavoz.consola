package com.invte.rentavoz.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import com.invte.rentavoz.vista.BaseBean;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.consola
* @class PrinterBean
* @date 14/08/2013
*
 */
@ManagedBean
@ApplicationScoped
public class PrinterBean  extends BaseBean{

	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REPORT_PATH
	 */
	private static final String REPORT_PATH = "/reportes/";
	/**
	 * 9/08/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         JASPER_EXTENSION
	 */
	private static final String JASPER_EXTENSION = ".jasper";
	protected JasperPrint jasperPrint;


    
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/08/2013
	* @param reportName
	* @throws JRException
	 */
	public void init(String reportName,HashMap<String,Object> parameters,List<?> secondDatasource) throws JRException {

		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath(REPORT_PATH + reportName + JASPER_EXTENSION);
		try {
                    
                    if (secondDatasource==null) {
                        jasperPrint = JasperFillManager.fillReport(reportPath, parameters,
					establishConnection());
                      JasperPrintManager.printReport(jasperPrint, false);
                        return;
                    }
                    JRBeanCollectionDataSource dts=new JRBeanCollectionDataSource(secondDatasource);
                    jasperPrint=JasperFillManager.fillReport(reportPath, parameters, dts);
                    JasperPrintManager.printReport(jasperPrint, false);
                    
			
		} catch (Exception e) {
	                   System.err.println(e);
		}
	}
/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 14/08/2013
* @param reportName
* @param outputName
* @param outputExtension
 */
	public void exportPdf(String reportName, String outputName,
			HashMap<String,Object> parameters)  {
		try {
			init(reportName,parameters,null);
	
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + outputName + ".pdf");
                
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
                
                
                
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);

		FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 14/08/2013
* @param reportName
* @param outputName
* @param outputExtension
 */
	public void exportPdf(String reportName, String outputName,
			HashMap<String,Object> parameters,List<?> secondDatasource)  {
		try {
			init(reportName,parameters,secondDatasource);
	
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

                
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + outputName + ".pdf");
                
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
                
                
                
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);

		FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 14/08/2013
	* @param reportName
	* @param outputName
	* @param outputExtension
	 */
		public void exportPdf(String reportName, String outputName)  {
			try {
				init(reportName,null,null);
		
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();

	                
			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + outputName + ".pdf");
	                
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
	                
	                
	                
			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);

			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 26/08/2013
	* @param reportName
	* @param outputName
	* @param outputExtension
	 */
	public void exportXls(String reportName, String outputName,
			HashMap<String,Object> parameters)  {
		try {
			init(reportName,parameters,null);
	
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + outputName + ".xls");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter(); 
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		exporterXLS.exportReport(); 
		
		FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 26/08/2013
	* @param reportName
	* @param outputName
	* @param outputExtension
	 */
	public void exportXls(String reportName, String outputName)  {
		try {
			init(reportName,null,null);
	
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + outputName + ".xls");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter(); 
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		exporterXLS.exportReport(); 
		
		FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 26/08/2013
	* @param reportName
	* @param outputName
	* @param outputExtension
	 */
	public void exportXls(String reportName, String outputName,
			HashMap<String,Object> parameters,List<?> secondDatasource)  {
		try {
			init(reportName,parameters,secondDatasource);
	
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + outputName + ".xls");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		JRXlsExporter exporterXLS = new JRXlsExporter(); 
		exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint); 
		exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, servletOutputStream); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		exporterXLS.exportReport(); 
		
		FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 14/08/2013
* @return
* @throws ClassNotFoundException
 */
	public static Connection establishConnection() throws ClassNotFoundException {
		Connection connection = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String oracleURL = "jdbc:mysql://127.0.0.1:3306/minutos";
			connection = DriverManager.getConnection(oracleURL, "root",
					"innovate2013");
			connection.setAutoCommit(false);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return connection;

	}

}