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

import co.com.rentavoz.logica.jpa.entidades.Cuentas;
import co.com.rentavoz.logica.jpa.fachadas.CuentasFacade;

@ManagedBean
@ViewScoped
public class SelectorCuentas implements SelectorBase<Cuentas> {

	@EJB
	private CuentasFacade facade;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Cuentas> findAll = facade.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione una cuenta --"));
		for (Cuentas empresa : findAll) {
			items.add(new SelectItem(empresa.getIdCuentas(), empresa
					.getCueNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
