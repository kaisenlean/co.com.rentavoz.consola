/**
 * 
 */
package com.invte.rentavoz.vista.componentes.autocomplete;

import java.util.List;

import org.primefaces.event.SelectEvent;

import com.invte.rentavoz.vista.BaseBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class Autocompletar
 * @date 29/07/2013
 * 
 */
public abstract class Autocompletar<T> extends BaseBean implements Autocompletable<T> {

	protected T seleccionado;

	/**
	 * 
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param query
	 * @return
	 */
	public abstract List<T> completarBusqueda(String query);

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param evt
	 */
	public abstract void seleccionar(SelectEvent evt);

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return the seleccionado
	 */
	public T getSeleccionado() {
		return seleccionado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param seleccionado
	 *            the seleccionado to set
	 */
	public void setSeleccionado(T seleccionado) {
		this.seleccionado = seleccionado;
	}
}
