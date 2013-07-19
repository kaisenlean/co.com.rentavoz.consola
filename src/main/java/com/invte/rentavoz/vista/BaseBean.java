/**
 * 
 */
package com.invte.rentavoz.vista;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BaseBean
 * @date 2/07/2013
 *
 */
public class BaseBean {
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/07/2013
	* @param title
	* @param mensaje
	 */
	public void mensaje(String title, String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(title, mensaje));
	}

	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/07/2013
	* @param codigo
	* @return
	 */
	public Object getParameter(String codigo){
		
		Object element = RequestContext.getCurrentInstance().getCallbackParams().get(codigo);
		
		return element;
	}
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/07/2013
	* @param codigo
	* @return
	 */
	public Object getAttribute(String codigo){
		Object atributo =FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(codigo);
		return atributo;
	}

}