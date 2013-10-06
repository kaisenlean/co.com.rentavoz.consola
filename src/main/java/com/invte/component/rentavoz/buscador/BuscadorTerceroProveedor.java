/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.consola
* @class BuscadorTerceroProveedor
* @date 6/10/2013
*
 */
public abstract class BuscadorTerceroProveedor extends Buscador<Tercero> {

	public abstract TerceroFacade getFacade();

	public abstract void selCentrope(Tercero centrope);

	@Override
	public String buscar() {
		List<Tercero> result = getFacade().findByCriterioProveedor(getCriterio());

		getItems().clear();

		for (Tercero centrope : result) {
			getItems().add(
					new BuscadorItem<Tercero>(centrope.getIdTecero() + "",
							centrope.toString(), centrope));
		}
		return null;
	}

	@Override
	public void asignar(Tercero t) {
		if (t == null) {
			selCentrope(new Tercero());
		} else {
			selCentrope(t);
		}
	}

	/**
	 * 
	 * @param evt
	 */
	@Override
	public void mostrar(ActionEvent evt) {
		List<Tercero> result = getFacade().findByCriterio(getCriterio());

		getItems().clear();

		for (Tercero centrope : result) {
			getItems().add(
					new BuscadorItem<Tercero>(centrope.getIdTecero() + "",
							centrope.toString(), centrope));
		}
		super.mostrar(evt);
	}

}
