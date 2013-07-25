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

import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;

@ManagedBean
@ViewScoped
public class SelectorSucursal implements SelectorBase<Sucursal> {

	@EJB
	private SucursalFacade facade;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Sucursal> findAll = facade.findAll();
		items = new ArrayList<SelectItem>();
//		items.add(new SelectItem(" ", "-- Seleccione una sucursal --"));
		for (Sucursal empresa : findAll) {
			items.add(new SelectItem(empresa.getIdSucursal(), empresa
					.getSucNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
