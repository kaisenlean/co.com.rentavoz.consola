/**
 * 
 */
package com.invte.rentavoz.vista.bodega.reporte.existencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaExistenciaFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaItemFacade;

import com.invte.rentavoz.reports.PrinterBean;
import com.invte.rentavoz.vista.componentes.autocomplete.bodega.item.AutocompleteItem;
import com.invte.rentavoz.vista.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanReporteExistencias
 * @date 21/10/2013
 */
@ManagedBean
@ViewScoped
public class BeanReporteExistencias implements Serializable {

	/**
	 * 21/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REPORTE_EXISTENCIAS_REPORT_NAME
	 */
	private static final String REPORTE_EXISTENCIAS_REPORT_NAME = "reporteExistencias";


	/**
	 * 21/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 7163458109535837804L;


	private static final String REPORTE_EXISTENCIAS_GENERAL_REPORT_NAME = "reporteGeneralExistencias";

	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean printerBean;
	
	
	private AutocompleteItem autocompleteItem;

	@EJB
	private BodegaItemFacade bodegaItemFacade;
	
	@EJB
	private BodegaExistenciaFacade bodegaExistenciaFacade;
	
	private BodegaItem bodegaItem;
	
	private List<BodegaItem> bodegaItems=new ArrayList<BodegaItem>();
	
	private List<BodegaExistencia> existencias = new ArrayList<BodegaExistencia>();

	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	 */
	@PostConstruct
	public void init() {
		autocompleteItem = new AutocompleteItem() {

			@Override
			public void postSelect() {
				bodegaItem=getSeleccionado();
				existencias=bodegaExistenciaFacade.findByItemAndSucursal( login.getSucursal(),bodegaItem);
			}

			@Override
			public BodegaItemFacade getFacade() {
				return bodegaItemFacade;
			}
		};

	}
	
	

	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	 */
	public void consultar(){
		
		bodegaItems=bodegaItemFacade.findAll();
		for (Iterator<BodegaItem> iterator = bodegaItems.iterator(); iterator
				.hasNext();) {
			BodegaItem bi = iterator.next();
			bi.setExistenciasPorSucursal(bodegaExistenciaFacade.findByItemAndSucursal(login.getSucursal(), bi));
		}
	}
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	 */
	public void imprimirReporte(){
		HashMap<String, Object> mapa= new HashMap<String, Object>();
		mapa.put("NOMBRE_ITEM", bodegaItem.getNombre());
		mapa.put("ID_SUCURSAL", login.getSucursal().getIdSucursal());
		mapa.put("ID_ITEM", bodegaItem.getId());
		mapa.put("CANTIDAD_ITEM", String.valueOf(existencias.size()));
		printerBean.exportPdf(REPORTE_EXISTENCIAS_REPORT_NAME,"reporte_existencias",mapa);
		
	}
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 22/10/2013
	 */
	public void imprimirReporteGeneral(){
	
		printerBean.exportPdf(REPORTE_EXISTENCIAS_GENERAL_REPORT_NAME,"reporte_general_existencias",null,bodegaItems);
		
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @return the autocompleteItem
	 */
	public AutocompleteItem getAutocompleteItem() {
		return autocompleteItem;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @return the bodegaItem
	 */
	public BodegaItem getBodegaItem() {
		return bodegaItem;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param autocompleteItem the autocompleteItem to set
	 */
	public void setAutocompleteItem(AutocompleteItem autocompleteItem) {
		this.autocompleteItem = autocompleteItem;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param bodegaItem the bodegaItem to set
	 */
	public void setBodegaItem(BodegaItem bodegaItem) {
		this.bodegaItem = bodegaItem;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @return the existencias
	 */
	public List<BodegaExistencia> getExistencias() {
		return existencias;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param existencias the existencias to set
	 */
	public void setExistencias(List<BodegaExistencia> existencias) {
		this.existencias = existencias;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @return the bodegaItems
	 */
	public List<BodegaItem> getBodegaItems() {
		return bodegaItems;
	}/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param bodegaItems the bodegaItems to set
	 */
	public void setBodegaItems(List<BodegaItem> bodegaItems) {
		this.bodegaItems = bodegaItems;
	}
}
