package com.invte.rentavoz.vista.componentes.autocomplete.bodega.item;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.event.SelectEvent;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaItemFacade;

import com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteItem extends Autocompletar<BodegaItem> {

	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract BodegaItemFacade getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<BodegaItem> completarBusqueda(String query) {
		
		
		List<BodegaItem> lista;
			lista=
			 getFacade().findByCriterio(query);
		
		if (lista.isEmpty()) {
			mensajeError("NO hay resultados para la busqueda");
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
		seleccionado= getFacade().findByNombre(valor);
		query = seleccionado!=null?seleccionado.toString():null;
		postSelect();
	}
	
	public abstract void postSelect();

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param valor
	 * @return
	 */
	@SuppressWarnings("unused")
	private String obtenerId(String valor) {

		String id = "";
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(valor);
		while (m.find()) {
			id += m.group();
		}

		return id;
	}
}
