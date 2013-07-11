/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.Plan;
import co.com.rentavoz.logica.jpa.fachadas.PlanFacade;

/**
 * 
 * @author ejody
 */
public abstract class BuscadorPlan extends Buscador<Plan> {

	public abstract PlanFacade getFacade();

	public abstract void selCentrope(Plan centrope);

	@Override
	public String buscar() {
		List<Plan> result = getFacade().findByCriterio(getCriterio());

		getItems().clear();

		for (Plan centrope : result) {
			getItems().add(
					new BuscadorItem<Plan>(centrope.getIdPlan() + "", centrope
							.getPlaNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Plan t) {
		if (t == null) {
			selCentrope(new Plan());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Plan> result = getFacade().findByCriterio(getCriterio());

		getItems().clear();

		for (Plan centrope : result) {
			getItems().add(
					new BuscadorItem<Plan>(centrope.getIdPlan() + "", centrope
							.getPlaNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
