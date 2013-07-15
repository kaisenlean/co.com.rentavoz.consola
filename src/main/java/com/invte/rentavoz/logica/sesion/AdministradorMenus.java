/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.logica.sesion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import co.com.rentavoz.logica.jpa.entidades.Menu;
import co.com.rentavoz.logica.jpa.fachadas.MenuFacade;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AdministradorMenus
 * @date 14/07/2013
 * 
 */
@SuppressWarnings("serial")
@ManagedBean(eager = true)
@SessionScoped
public class AdministradorMenus implements Serializable {

	private List<Menu> menus;
	@EJB
	private MenuFacade menuFacade;

	private MenuListener listener;
	private String padre;

	@PostConstruct
	public void init() {
		listener = new MenuListener() {

			@Override
			public void ejecutarAccion() {

				cargarMenus(padre);
			}
		};
		try {
			menus = menuFacade.findAll();
		} catch (Exception e) {
			menus = new ArrayList<Menu>();
		}
	}

	public void cargarMenus(String padre) {
		menus.clear();
		try {
			menus = menuFacade.findTodosByPadre(padre);
		} catch (Exception e) {
			menus = new ArrayList<Menu>();
		}
	}

	public MenuListener getListener() {
		return listener;
	}

	public void setListener(MenuListener listener) {
		this.listener = listener;
	}

	// <editor-fold defaultstate="collapsed" desc="Capsulas">
	public MenuFacade getMenuFacade() {
		return menuFacade;
	}

	public void setMenuFacade(MenuFacade menuFacade) {
		this.menuFacade = menuFacade;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @return
	 */
	public String getPadre() {
		return padre;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param padre
	 */
	public void setPadre(String padre) {
		this.padre = padre;
	}

	// </editor-fold>
}
