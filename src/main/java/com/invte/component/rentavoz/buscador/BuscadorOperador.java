/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.Operador;
import co.com.rentavoz.logica.jpa.fachadas.OperadorFacade;

/**
 * 
 * @author ejody
 */
public abstract class BuscadorOperador extends Buscador<Operador> {

	public abstract OperadorFacade getFacade();

	public abstract void selCentrope(Operador centrope);

	@Override
	public String buscar() {
		List<Operador> result = getFacade().findByCriterio(getCriterio());

		getItems().clear();

		for (Operador centrope : result) {
			getItems().add(
					new BuscadorItem<Operador>(centrope.getIdOperador() + "",
							centrope.getOpeNombre(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Operador t) {
		if (t == null) {
			selCentrope(new Operador());
		} else {
			selCentrope(t);
		}
	}

	@Override
	public void mostrar(ActionEvent evt) {
		List<Operador> result = getFacade().findByCriterio(getCriterio());

		getItems().clear();

		for (Operador centrope : result) {
			getItems().add(
					new BuscadorItem<Operador>(centrope.getIdOperador() + "",
							centrope.getOpeNombre(), centrope));
		}
		super.mostrar(evt);
	}

}
