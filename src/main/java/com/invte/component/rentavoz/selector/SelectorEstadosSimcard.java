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

import co.com.rentavoz.logica.jpa.entidades.almacen.EstadosSimcardEnum;

@ManagedBean
@ViewScoped
public class SelectorEstadosSimcard  {


	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
		EstadosSimcardEnum[] findAll =EstadosSimcardEnum.values();
		items = new ArrayList<SelectItem>();
		items.add(new SelectItem(" ", "-- Seleccione un estado --"));
		for (int i = 0; i < findAll.length; i++) {
			
	
			items.add(new SelectItem(findAll[i].name(), findAll[i].name()));
		}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
