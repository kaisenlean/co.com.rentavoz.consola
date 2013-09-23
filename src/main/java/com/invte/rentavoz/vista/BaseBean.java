/**
 * 
 */
package com.invte.rentavoz.vista;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
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
	 * Metodo que manda un mensaje de error al cliente
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 * @param mensaje
	 */
	public void mensajeError(String mensaje) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", mensaje));
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param codigo
	 * @return
	 */
	public Object getParameter(String codigo) {

		Object element = RequestContext.getCurrentInstance()
				.getCallbackParams().get(codigo);

		return element;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param codigo
	 * @return
	 */
	public Object getAttribute(String codigo) {
		Object atributo = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(codigo);
		return atributo;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @param key
	 * @param value
	 */
	public void addAttribute(String key, Object value) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(key, value);

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @param key
	 */
	public void removeAttribute(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove(key);

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 4/09/2013
	 * @param c
	 */
	public void killBean(String beanName) {
		ExternalContext ctx = FacesContext.getCurrentInstance()
				.getExternalContext();

		try {
			
			ctx.getSessionMap().put(beanName, null);
			
		} catch (Exception ex) {
			mensaje("Error", ex.toString());
		}

	}
	
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @param script
	 */
	public void runJavascript(String script){
		RequestContext.getCurrentInstance().execute(script);
		
	}

}
