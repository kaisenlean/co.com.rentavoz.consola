/**
 * 
 */
package com.invte.rentavoz.logica.sesion;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class MenuListener
 * @date 2/06/2013
 *
 */
public abstract class MenuListener implements ActionListener{

	/* (non-Javadoc)
	 * @see javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent)
	 */
	
	
	
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
	ejecutarAccion();
		
	}

	/**
	* @author <a href="mailto:juanm.caicedo@premize.com">Juan Manuel Caicedo</a>
	* @date 2/06/2013
	*/
	public abstract void ejecutarAccion();

}
