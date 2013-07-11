/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 * 
 * @author ejody
 */
public abstract class BuscadorListener implements ActionListener {

	public abstract void listener();

	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		listener();
	}

}
