package com.invte.rentavoz.vista.componentes.autocomplete.linea;

import java.util.List;

import org.primefaces.event.SelectEvent;

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;

import com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AutocompleteLinea
 * @date 29/07/2013
 * 
 */
public abstract class AutocompleteLinea2 extends Autocompletar<Linea> {

	private int idSucursal;
	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public abstract LineaFacade getFacade();
	

	/**
	 * @see com.invte.rentavoz.vista.componentes.autocomplete.Autocompletar#completarBusqueda(java.lang.String)
	 */
	@Override
	public List<Linea> completarBusqueda(String query) {
		
		
		List<Linea> lista;
		if (getIdSucursal()==0) {
			lista=
			 getFacade().findByCriteria(query);
		}else{
			
			lista= getFacade().findByCriteria(query,getIdSucursal());
		}
		
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
		seleccionado = getFacade().findBNumeroObjeto(valor);
		query=seleccionado.getLinNumero();
		postSelect();
	}
	
	public abstract void postSelect();


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/08/2013
	 * @return the idSucursal
	 */
	public int getIdSucursal() {
		return idSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 5/08/2013
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
}
