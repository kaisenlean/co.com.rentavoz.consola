/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;
import co.com.rentavoz.logica.jpa.fachadas.SimcardFacade;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.consola
* @class BuscadorSimCard
* @date 22/07/2013
*
 */
public abstract class BuscadorSimCard extends Buscador<Simcard> {

	public abstract SimcardFacade getFacade();

	

	public abstract void selCentrope(Simcard centrope);

	@Override
	public String buscar() {
		List<Simcard> result = getFacade().findDisponibles(getCriterio());

		getItems().clear();

		for (Simcard centrope : result) {
			getItems().add(
					new BuscadorItem<Simcard>(centrope.getSimIccid() + "", centrope.getSimIccid()+"", centrope));
		}
		return null;
	}

	@Override
	public void asignar(Simcard t) {
		if (t == null) {
			selCentrope(new Simcard());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Simcard> result = getFacade().findDisponibles(getCriterio());

		getItems().clear();

		for (Simcard centrope : result) {
			getItems().add(
					new BuscadorItem<Simcard>(centrope.getSimIccid() + "", centrope
							.getSimIccid()+"", centrope));
		}
		super.mostrar(evt);
	}

}
