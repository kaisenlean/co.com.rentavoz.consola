/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.Ciudad;
import co.com.rentavoz.logica.jpa.entidades.Departamento;
import co.com.rentavoz.logica.jpa.fachadas.CiudadFacade;

/**
 * 
 * @author ejody
 */
public abstract class BuscadorCiudad extends Buscador<Ciudad> {

	public abstract CiudadFacade getFacade();

	public abstract Departamento getDepartamento();

	public abstract void selCentrope(Ciudad centrope);

	@Override
	public String buscar() {
		List<Ciudad> result = getFacade().findByCriterio(getCriterio(),
				getDepartamento());

		getItems().clear();

		for (Ciudad centrope : result) {
			getItems().add(
					new BuscadorItem<Ciudad>(centrope.getIdCiudad() + "", centrope
							.getCiuNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Ciudad t) {
		if (t == null) {
			selCentrope(new Ciudad());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Ciudad> result = getFacade().findByCriterio(getCriterio(),
				getDepartamento());

		getItems().clear();

		for (Ciudad centrope : result) {
			getItems().add(
					new BuscadorItem<Ciudad>(centrope.getIdCiudad() + "", centrope
							.getCiuNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
