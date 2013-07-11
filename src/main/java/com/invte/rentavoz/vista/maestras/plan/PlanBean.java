/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.plan;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Operador;
import co.com.rentavoz.logica.jpa.entidades.Plan;
import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.OperadorFacade;
import co.com.rentavoz.logica.jpa.fachadas.PlanFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

import com.invte.component.rentavoz.buscador.BuscadorOperador;
import com.invte.component.rentavoz.buscador.BuscadorTercero;
import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class PlanBean extends StandardAbm<Plan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PlanFacade facade;
	@EJB
	private TerceroFacade terceroFacade;
	@EJB
	private OperadorFacade operadorFacade;
	private BuscadorTercero buscadorTercero;
	private BuscadorOperador buscadorOperador;
	private Tercero tercero;
	private Operador operador;

	@Override
	public AbstractFacade<Plan> getFacade() {
		return facade;
	}

	@Override
	public Plan getInstancia() {
		return new Plan();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/plan/index.jsf";
	}

	@Override
	public Plan getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Plan> getListado() {
		return obtenerListado();
	}

	@Override
	public void postFormNuevo() {
		getObjeto().setIdPlan(facade.nextCodigo());
		getObjeto().setFecha(new Date());
	}

	@Override
	public void initialize() {
		buscadorOperador = new BuscadorOperador() {
			@Override
			public OperadorFacade getFacade() {
				return operadorFacade;
			}

			@Override
			public void selCentrope(Operador centrope) {
				operador = centrope;
			}
		};
		buscadorTercero = new BuscadorTercero() {
			@Override
			public TerceroFacade getFacade() {
				return terceroFacade;
			}

			@Override
			public void selCentrope(Tercero centrope) {
				tercero = centrope;
			}
		};
	}

	@Override
	public void buscarrPorCriterio() {
	}

	@Override
	public boolean preAction() {

		if (facade.find(getObjeto().getIdPlan()) == null) {

			if (operador == null || tercero == null) {
				StringBuilder builder = new StringBuilder(
						"Por favor selecciona ");
				if (operador == null) {
					builder.append(" un  operador ");
				}
				if (tercero == null) {
					builder.append("y un tercero");
				}
				builder.append("para poder continuar.");
				mensaje("Error", builder.toString());
				return false;
			} else {
				getObjeto().setTerceroidTecero(tercero);
				getObjeto().setOperadoridOperador(operador);
				return true;
			}
		} else {
			mensaje("Error",
					"Este codigo de plan ya esta siendo utilizado por favor digita otro");
			return false;
		}
	}

	@Override
	public void preRenderizarItem() {
		tercero = getObjeto().getTerceroidTecero();
		operador = getObjeto().getOperadoridOperador();
	}

	// <editor-fold defaultstate="collapsed" desc="CAPSULAS">
	public BuscadorTercero getBuscadorTercero() {
		return buscadorTercero;
	}

	public void setBuscadorTercero(BuscadorTercero buscadorTercero) {
		this.buscadorTercero = buscadorTercero;
	}

	public BuscadorOperador getBuscadorOperador() {
		return buscadorOperador;
	}

	public void setBuscadorOperador(BuscadorOperador buscadorOperador) {
		this.buscadorOperador = buscadorOperador;
	}

	public Tercero getTercero() {
		return tercero;
	}

	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	// </editor-fold>
}
