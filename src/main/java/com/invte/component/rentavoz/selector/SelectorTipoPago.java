/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.selector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.com.rentavoz.logica.jpa.entidades.TipoPago;
import co.com.rentavoz.logica.jpa.fachadas.TipoPagoFacade;

@ManagedBean
@ViewScoped
public class SelectorTipoPago implements SelectorBase<TipoPago> {

	@EJB
	private TipoPagoFacade facade;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<TipoPago> findAll = facade.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un tipo de pago --"));
		for (TipoPago empresa : findAll) {
			items.add(new SelectItem(empresa.getIdTipoPago(), empresa
					.getTPagoNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
