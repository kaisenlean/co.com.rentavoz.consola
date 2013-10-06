package com.invte.rentavoz.vista.componentes.autocomplete.tercero;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.primefaces.event.SelectEvent;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

import com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteTerceroProveedor extends Autocompletar<Tercero> {

	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract TerceroFacade getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<Tercero> completarBusqueda(String query) {
		
		
		List<Tercero> lista;
			lista=
			 getFacade().findByCriterioProveedor(query);
		
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
		String id = obtenerId(valor);
		Integer val = Integer.parseInt(id);
		seleccionado= getFacade().findByDocumento(val);
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
