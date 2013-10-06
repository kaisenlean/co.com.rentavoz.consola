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

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaItemFacade;

@ManagedBean
@ViewScoped
public class SelectorItem implements SelectorBase<BodegaItem> {

	@EJB
	private BodegaItemFacade facade;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<BodegaItem> findAll = facade.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem("0", "-- Seleccione un item --"));
		for (BodegaItem estadoLinea : findAll) {
			items.add(new SelectItem(estadoLinea.getId() + "",
					estadoLinea.getNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
