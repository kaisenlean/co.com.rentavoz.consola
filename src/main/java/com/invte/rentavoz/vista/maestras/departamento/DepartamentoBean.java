/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.departamento;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Departamento;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.DepartamentoFacade;

import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class DepartamentoBean extends StandardAbm<Departamento> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private DepartamentoFacade departamentoFacade;

	@Override
	public AbstractFacade<Departamento> getFacade() {
		return departamentoFacade;
	}

	@Override
	public Departamento getInstancia() {
		return new Departamento();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/departamento/index.jsf";
	}

	@Override
	public Departamento getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Departamento> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
		setListado(departamentoFacade.findAll());
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

	@Override
	public boolean preAction() {
		return true;
	}

	@Override
	public void postEliminar() {

		try {

			// Usar el contexto de JSF para invalidar la sesión,
			// NO EL DE SERVLETS (nada de HttpServletRequest)
			// ((HttpSession) ctx.getSession(false)).invalidate();

			// Redirección de nuevo con el contexto de JSF,
			// si se usa una HttpServletResponse fallará.
			// Sin embargo, como ya está fuera del ciclo de vida
			// de JSF se debe usar la ruta completa -_-U
			ctx.redirect(ctxPath + reglaNavegacion());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
