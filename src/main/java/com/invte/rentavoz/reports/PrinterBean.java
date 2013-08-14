package com.invte.rentavoz.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * 
 * @author ramki
 */
@ManagedBean
@SessionScoped
public class PrinterBean {

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
	JasperPrint jasperPrint;

	public void init(String reportName) throws JRException {

		String reportPath = FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRealPath(REPORT_PATH + reportName + JASPER_EXTENSION);
		try {
			jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap<String, Object>(),
					establishConnection());
		} catch (Exception e) {
	e.printStackTrace();
		}
	}

	public void exportPdf(String reportName, String outputName,
			String outputExtension)  {
		try {
			init(reportName);
	
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

	public static Connection establishConnection() throws ClassNotFoundException {
		Connection connection = null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			String oracleURL = "jdbc:mysql://127.0.0.1:3306/Minutos";
			connection = DriverManager.getConnection(oracleURL, "root",
					"jsepee1855");
			connection.setAutoCommit(false);
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return connection;

	}

}