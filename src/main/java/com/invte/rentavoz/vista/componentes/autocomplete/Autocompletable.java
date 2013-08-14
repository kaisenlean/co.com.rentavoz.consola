/**
 * 
 */
package com.invte.rentavoz.vista.componentes.autocomplete;

import java.util.List;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class Autocompletable
 * @date 29/07/2013
 * 
 */
public interface Autocompletable<T> {
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param query
	 * @return
	 */
	abstract List<T> completarBusqueda(String query);


}
