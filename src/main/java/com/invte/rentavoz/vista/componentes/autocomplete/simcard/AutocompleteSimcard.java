package com.invte.rentavoz.vista.componentes.autocomplete.simcard;

import java.util.List;

import org.primefaces.event.SelectEvent;

import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;
import co.com.rentavoz.logica.jpa.fachadas.SimcardFacade;

import com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteSimcard extends Autocompletar<Simcard> {

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract SimcardFacade getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<Simcard> completarBusqueda(String query) {
		List<Simcard> lista = null;
		try {
		
		
			lista= getFacade().getByScId(query);
		
		if (lista.isEmpty()) {
			mensajeError("NO hay resultados para la busqueda");
		}
		
		} catch (Exception e) {
			logger.error(e);
			mensajeError(e.toString());
		}

		return lista;
		
	
	}

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#seleccionar(org.primefaces.event.SelectEvent)
	 */
	@Override
	public void seleccionar(SelectEvent evt) {
		if (evt.getObject()==null) {
			return ;
		}
		String valor = evt.getObject().toString();
		try {		
		seleccionado = getFacade().findByScId(valor);
		query=seleccionado.getSimIccid();
		} catch (Exception e) {
		mensajeError(e.toString());
		}
		
		postSelect();
	}
	
	public abstract void postSelect();


	
}
