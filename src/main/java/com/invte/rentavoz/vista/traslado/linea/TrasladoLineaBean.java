/**
 * 
 */
package com.invte.rentavoz.vista.traslado.linea;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;

import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.componentes.autocomplete.linea.AutocompleteLinea;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class TrasladoLineaBean
 * @date 29/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class TrasladoLineaBean extends BaseBean implements Serializable {

	/**
	 * 5/08/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * LINEA_DESTINO_VALIDA
	 */
	private static final String LINEA_DESTINO_VALIDA = "Por favor seleccione una linea de destino válida";

	/**
	 * 5/08/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * ERROR_IGUAL_SUCURSALES
	 */
	private static final String ERROR_IGUAL_SUCURSALES = "La sucursal de origen no puede ser la misma de destino";

	/**
	 * 5/08/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * MSG_LINEA_SELECCIONADA
	 */
	private static final String MSG_LINEA_SELECCIONADA = "Esta linea ya esta seleccionada!!";

	/**
	 * 29/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = -6594992858446072136L;

	private AutocompleteLinea autocompleteLinea;
	
	
	@EJB
	private LineaFacade lineaFacade;
	

	@EJB
	private SucursalFacade sucursalFacade;
	
	private int lineaOrigen;
	
	private int lineaDestino;
	
	private List<Linea> seleccionadas=new ArrayList<Linea>();
//	private String tipoTraslado;
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 5/08/2013
	 */
	@PostConstruct
	public void init(){
		autocompleteLinea=new AutocompleteLinea() {
			
			@Override
			public void postSelect() {
				if (!seleccionadas.contains(seleccionado)) {
					seleccionadas.add(seleccionado);
				}else{
					mensajeError(MSG_LINEA_SELECCIONADA);
					
				}
			}
					
			@Override
			public LineaFacade getFacade() {
				return lineaFacade;
			}
		};
		
	}
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 5/08/2013
	 */
	public void trasladarLineas(ActionEvent evt){
		if (autocompleteLinea.getIdSucursal()==lineaDestino) {
			mensajeError(ERROR_IGUAL_SUCURSALES);
			return;
		}
		if (lineaDestino==0) {
			mensajeError(LINEA_DESTINO_VALIDA);
		}
		for (Linea lineaTmp : seleccionadas) {
			
			lineaTmp.setSucursal(sucursalFacade.find(lineaDestino));
			lineaFacade.edit(lineaTmp);
			
		}
		mensaje("Realizado", new StringBuilder("Se han trasladado ").append(seleccionadas.size()).append(" lineas a la sucursal indicada").toString());
		
		init();
		
	}
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/08/2013
	* @param linea
	 */
	public void eliminarLinea(Linea linea){
		seleccionadas.remove(linea);
		
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the lineaDestino
	 */
	public int getLineaDestino() {
		return lineaDestino;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the autocompleteLinea
	 */
	public AutocompleteLinea getAutocompleteLinea() {
		return autocompleteLinea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the lineaFacade
	 */
	public LineaFacade getLineaFacade() {
		return lineaFacade;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the lineaOrigen
	 */
	public int getLineaOrigen() {
		return lineaOrigen;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @param lineaDestino the lineaDestino to set
	 */
	public void setLineaDestino(int lineaDestino) {
		this.lineaDestino = lineaDestino;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @param autocompleteLinea the autocompleteLinea to set
	 */
	public void setAutocompleteLinea(AutocompleteLinea autocompleteLinea) {
		this.autocompleteLinea = autocompleteLinea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @param lineaOrigen the lineaOrigen to set
	 */
	public void setLineaOrigen(int lineaOrigen) {
		this.lineaOrigen = lineaOrigen;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the seleccionadas
	 */
	public List<Linea> getSeleccionadas() {
		return seleccionadas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @param seleccionadas the seleccionadas to set
	 */
	public void setSeleccionadas(List<Linea> seleccionadas) {
		this.seleccionadas = seleccionadas;
	}
}
