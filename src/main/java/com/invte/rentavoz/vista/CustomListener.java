/**
 * 
 */
package com.invte.rentavoz.vista;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class CustomListener
 * @date 3/06/2013
 * 
 */
public abstract class CustomListener implements ActionListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent
	 * )
	 */

	public void processAction(ActionEvent evt) throws AbortProcessingException {
		callMethod(evt);

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 */
	public abstract void callMethod(ActionEvent evt);

}
