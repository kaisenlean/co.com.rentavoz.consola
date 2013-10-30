/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.selector;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.com.rentavoz.logica.jpa.entidades.venta.existencia.ModoPagoEnum;

@ManagedBean
@ViewScoped
public class SelectorModoPago {

	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		ModoPagoEnum[] findAll = ModoPagoEnum.values();
		items = new ArrayList<SelectItem>();
//		items.add(new SelectItem(" ", "-- Seleccione un estado --"));
		for (int i = 0; i < findAll.length; i++) {

			items.add(new SelectItem(findAll[i].name(), findAll[i].name()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
