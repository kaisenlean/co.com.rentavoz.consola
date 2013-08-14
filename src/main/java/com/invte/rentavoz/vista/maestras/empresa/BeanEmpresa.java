/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.empresa;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Empresa;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.EmpresaFacade;

import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class BeanEmpresa extends StandardAbm<Empresa> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6878840473274695937L;
	@EJB
	private EmpresaFacade empresaFacade;

	@Override
	public AbstractFacade<Empresa> getFacade() {
		return empresaFacade;
	}

	@Override
	public Empresa getInstancia() {
		return new Empresa();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/empresa/index.jsf";
	}

	@Override
	public Empresa getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Empresa> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {

	}

	@Override
	public boolean preAction() {
		return true;
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

}
