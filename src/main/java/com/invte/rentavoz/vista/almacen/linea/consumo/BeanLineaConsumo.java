/**
 * 
 */
package com.invte.rentavoz.vista.almacen.linea.consumo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.entidades.almacen.LineaConsumo;
import co.com.rentavoz.logica.jpa.fachadas.LineaConsumoFacade;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;

import com.invte.rentavoz.vista.BaseBean;
import com.sun.faces.context.ExternalContextImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanLineaConsumo
 * @date 15/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanLineaConsumo extends BaseBean implements Serializable {

	/**
	 * 15/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	protected String fileName;
	private String salida;
	protected InputStream stream;
	private StringBuilder builder = new StringBuilder();
	@EJB
	private LineaFacade lineaFacade;
	@EJB
	private LineaConsumoFacade lineaConsumoFacade;

	@SuppressWarnings("resource")
	public void uploadHandler(FileUploadEvent event) {
		try {
			builder = new StringBuilder();
			InputStream in = event.getFile().getInputstream();
			fileName = event.getFile().getFileName();

			ExternalContextImpl request;
			request = (ExternalContextImpl) FacesContext.getCurrentInstance()
					.getExternalContext();
			String path = request.getRealPath("/");
			OutputStream out = new FileOutputStream(path
					+ event.getFile().getFileName().replace(" ", "").trim());
			File f = new File(request.getRealPath(path
					+ event.getFile().getFileName().replace(" ", "").trim()));

			if (in != null) {
				int b = 0;
				while (b != -1) {
					b = in.read();
					if (b != -1) {
						out.write(b);

					}

				}
			}

			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			

			br = new BufferedReader(new FileReader(path
					+ event.getFile().getFileName().replace(" ", "").trim()));
			while ((line = br.readLine()) != null) {

				String[] country = line.split(cvsSplitBy);

				Linea linea = lineaFacade.findByNumeroObjeto(country[0]);
				if (linea == null) {
					builder.append(country[0]);
					builder.append(" No existe. \n")
					;
				} else {
					LineaConsumo lc = new LineaConsumo();
					lc.setClaro(Integer.valueOf(country[1]));
					lc.setOtros(Integer.valueOf(country[2]));
					lc.setFijo(Integer.valueOf(country[3]));

					lc.setLinea(linea);
					lc.setFecha(new Date());
					lineaConsumoFacade.create(lc);
					builder.append(country[0]);
					builder.append(" Se ha ingresado los registros. \n");
				}
			}

			System.out.println("Carga realizada");
			salida=builder.toString();
			mensaje("Realizado", "Carga de archivos realizada");

		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the builder
	 */
	public StringBuilder getBuilder() {
		return builder;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param builder the builder to set
	 */
	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the salida
	 */
	public String getSalida() {
		return salida;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param salida the salida to set
	 */
	public void setSalida(String salida) {
		this.salida = salida;
	}
}
