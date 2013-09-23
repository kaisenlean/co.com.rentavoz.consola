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

import co.com.rentavoz.logica.jpa.entidades.TipoTerceroEnum;
import co.com.rentavoz.logica.jpa.fachadas.CuentasFacade;

import com.invte.rentavoz.vista.traslado.linea.TipoTrasladoEnum;

@ManagedBean
@ViewScoped
public class SelectorTipoTraslado  {

	@EJB
	private CuentasFacade facade;
	private ArrayList<SelectItem> items;

	@PostConstruct
	public void init() {
	for (int i = 0; i < TipoTerceroEnum.values().length; i++) {
		items.add(new SelectItem(TipoTrasladoEnum.values()[i].name(),TipoTrasladoEnum.values()[i].name().replace("_", " ") ));
	}
	}

	public List<SelectItem> getItems() {
		return items;
	}
}
