/**
 * 
 */
package com.invte.rentavoz.vista.almacen.linea.consumo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.almacen.LineaConsumo;
import co.com.rentavoz.logica.jpa.fachadas.LineaConsumoFacade;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;

import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.componentes.autocomplete.linea.AutocompleteLinea;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanLineaConsumo
 * @date 15/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class ConsultaLineaConsumo extends BaseBean implements Serializable {

	/**
	 * 15/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private LineaFacade lineaFacade;
	@EJB
	private LineaConsumoFacade lineaConsumoFacade;
	
	private AutocompleteLinea autocompleteLinea;
	private List<LineaConsumo> consumos = new ArrayList<LineaConsumo>();
	
	@PostConstruct
	public void init(){
		
		autocompleteLinea=new AutocompleteLinea() {
			
			@Override
			public void postSelect() {
				consumos=lineaConsumoFacade.consultarConsumoActual(seleccionado);
			}
			
			@Override
			public LineaFacade getFacade() {
				return lineaFacade;
			}
		};
	}
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the autocompleteLinea
	 */
	public AutocompleteLinea getAutocompleteLinea() {
		return autocompleteLinea;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param autocompleteLinea the autocompleteLinea to set
	 */
	public void setAutocompleteLinea(AutocompleteLinea autocompleteLinea) {
		this.autocompleteLinea = autocompleteLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the consumos
	 */
	public List<LineaConsumo> getConsumos() {
		return consumos;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param consumos the consumos to set
	 */
	public void setConsumos(List<LineaConsumo> consumos) {
		this.consumos = consumos;
	}
	
}
