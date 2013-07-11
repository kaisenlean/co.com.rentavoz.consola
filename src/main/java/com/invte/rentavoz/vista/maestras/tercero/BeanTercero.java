/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.tercero;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class BeanTercero extends StandardAbm<Tercero> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private TerceroFacade terceroFacade;

	@Override
	public AbstractFacade<Tercero> getFacade() {
		return terceroFacade;
	}

	@Override
	public Tercero getInstancia() {
		return new Tercero();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/tercero/index.jsf";
	}

	@Override
	public Tercero getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Tercero> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
	}

	@Override
	public boolean preAction() {
		if (!isEdit()) {

			if (getFacade().find(getObjeto().getIdTecero()) == null) {

				return validarClaves();
			} else {
				mensaje("Error",
						"El codigo de la empresa ya esta siendo utilizado , por favor digita otro código");
				return false;
			}
		} else {

			return validarClaves();
		}
	}

	@Override
	public void buscarrPorCriterio() {
		throw new UnsupportedOperationException("Not supported yet."); // To
																		// change
																		// body
																		// of
																		// generated
																		// methods,
																		// choose
																		// Tools
																		// |
																		// Templates.
	}

	private boolean validarClaves() {
		if (getObjeto().getRepTerClave() != null
				&& getObjeto().getTerClave() != null) {

			if (getObjeto().getTerClave().equals(getObjeto().getRepTerClave())) {
				return true;
			} else {
				mensaje("Claves no coinciden",
						"Las claves que estas intentando ingresar no coinciden");
				return false;
			}
		} else {
			mensaje("Claves no coinciden",
					"Las claves que estas intentando ingresar no coinciden");
			return false;
		}
	}

	@Override
	public void preRenderizarItem() {
		getObjeto().setRepTerClave(getObjeto().getTerClave());
	}

}
