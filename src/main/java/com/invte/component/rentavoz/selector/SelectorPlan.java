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

import co.com.rentavoz.logica.jpa.entidades.Plan;
import co.com.rentavoz.logica.jpa.fachadas.PlanFacade;

@ManagedBean
@ViewScoped
public class SelectorPlan implements SelectorBase<Plan> {

	@EJB
	private PlanFacade facade;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		List<Plan> findAll = facade.findAll();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un Plán --"));
		for (Plan empresa : findAll) {
			items.add(new SelectItem(empresa.getIdPlan(), empresa
					.getPlaNombre()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
