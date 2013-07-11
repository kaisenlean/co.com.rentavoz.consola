/**
 * 
 */
package com.invte.rentavoz.vista;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BaseBean
 * @date 2/07/2013
 *
 */
public class BaseBean {
	
	
	public void mensaje(String title, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(title, mensaje));
	}

}
