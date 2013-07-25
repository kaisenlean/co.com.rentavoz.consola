/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.ciudad;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;

import co.com.rentavoz.logica.jpa.entidades.Ciudad;
import co.com.rentavoz.logica.jpa.entidades.Departamento;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.CiudadFacade;
import co.com.rentavoz.logica.jpa.fachadas.DepartamentoFacade;
import co.com.rentavoz.logica.venta.VentaBean;

import com.invte.component.rentavoz.buscador.BuscadorDepartamento;
import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.consola
* @class BeanCiudad
* @date 14/07/2013
*
 */
@ManagedBean
@ViewScoped
public class BeanCiudad extends StandardAbm<Ciudad> implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private CiudadFacade ciudadFacade;
	@EJB
	private DepartamentoFacade departamentoFacade;

	@EJB
	private VentaBean bean;

	private BuscadorDepartamento buscadorDepartamento;
	private Departamento departamento = new Departamento();

	@Override
	public AbstractFacade<Ciudad> getFacade() {
		return ciudadFacade;
	}

	@Override
	public Ciudad getInstancia() {
		return new Ciudad();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/ciudad/index.jsf";
	}

	@Override
	public Ciudad getObjeto() {
		return obtenerObjeto();

	}

	@Override
	public List<Ciudad> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
		buscadorDepartamento = new BuscadorDepartamento() {

			@Override
			public DepartamentoFacade getFacade() {
				return departamentoFacade;
			}

			@Override
			public void selCentrope(Departamento centrope) {
				if (getObjeto() != null) {
					departamento = centrope;
					getObjeto().setDepartamentoidDepartamento(centrope);
				}
			}
		};
		bean.init();
		// System.out.println(ejb.registrarVenta(null));
	}

	@Override
	public boolean preAction() {
	return true;
	}

	@Override
	public void preRenderizarItem() {
		departamento = getObjeto().getDepartamentoidDepartamento();
	}

	@Override
	public void buscarrPorCriterio() {

	}

	public BuscadorDepartamento getBuscadorDepartamento() {
		return buscadorDepartamento;
	}

	public void setBuscadorDepartamento(
			BuscadorDepartamento buscadorDepartamento) {
		this.buscadorDepartamento = buscadorDepartamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	

}
