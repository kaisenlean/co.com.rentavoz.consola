/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.linea;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Plan;
import co.com.rentavoz.logica.jpa.entidades.PlanLinea;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadosSimcardEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.EmpresaFacade;
import co.com.rentavoz.logica.jpa.fachadas.EstadoLineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.PlanFacade;
import co.com.rentavoz.logica.jpa.fachadas.PlanLineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.SimcardFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;

import com.invte.component.rentavoz.buscador.BuscadorPlan;
import com.invte.component.rentavoz.buscador.BuscadorSimCard;
import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class LineaBean
 * @date 17/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class LineaBean extends StandardAbm<Linea> {

	private static final long serialVersionUID = 1L;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         facade
	 */
	@EJB
	private EmpresaFacade facade;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         terceroFacade
	 */
	@EJB
	private EstadoLineaFacade terceroFacade;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         lineaFacade
	 */
	@EJB
	private LineaFacade lineaFacade;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         planFacade
	 */
	@EJB
	private PlanFacade planFacade;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         plFacade
	 */
	@EJB
	private PlanLineaFacade plFacade;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         simcardFacade
	 */
	@EJB
	private SimcardFacade simcardFacade;

	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         empresa
	 */
	private String empresa = null;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         estadoLinea
	 */
	private String estadoLinea = null;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         buscadorPlan
	 */
	private BuscadorPlan buscadorPlan;
	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         buscadorSimCard
	 */
	private BuscadorSimCard buscadorSimCard;

	/**
	 * 23/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         planOLd
	 */
	private Plan planOLd;
	
	
	
	private String idPlan;
	
	
	@EJB
	private SucursalFacade sucursalFacade;
	
	private String idSucursal;

	@Override
	public AbstractFacade<Linea> getFacade() {
		return lineaFacade;
	}

	@Override
	public Linea getInstancia() {
		return new Linea();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/linea/index.jsf";
	}

	@Override
	public Linea getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Linea> getListado() {
		return obtenerListado();
	}

	@Override
	public void postFormNuevo() {
		getObjeto().setIdLinea(lineaFacade.nextCodigo());
		getObjeto().setFecha(new Date());
	}

	@Override
	public void initialize() {
		buscadorPlan = new BuscadorPlan() {

			@Override
			public void selCentrope(Plan centrope) {

				getObjeto().setPlan(centrope);

			}

			@Override
			public PlanFacade getFacade() {
				// TODO Auto-generated method stub
				return planFacade;
			}
		};

		buscadorSimCard = new BuscadorSimCard() {

			@Override
			public void selCentrope(Simcard centrope) {
				getObjeto().setSimcard(centrope);
			}

			@Override
			public SimcardFacade getFacade() {
				return simcardFacade;
			}
		};
	}

	@Override
	public void buscarrPorCriterio() {
	}

	@Override
	public void preRenderizarItem() {
		estadoLinea = getObjeto().getEstadoLineaidEstadoLinea()
				.getIdEstadoLinea() + "";
		empresa = getObjeto().getEmpresaidEmpresa().getIdEmpresa() + "";
		planOLd = getObjeto().getPlan();
		idSucursal=getObjeto().getSucursal()!=null?getObjeto().getSucursal().getIdSucursal()+"":"";
		

	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
//		if (idSucursal==null) {
//			mensajeError("Sucursal es nulo");
//			return false;
//		}
//		Sucursal sucursal =null;
//		try {
//			 sucursal = sucursalFacade.find(Integer.parseInt(idSucursal));
//			 getObjeto().setSucursal(sucursal);
//		} catch (Exception e) {
//			mensajeError(e.toString());
//			return false;
//		}
		
		if (getObjeto().getLinCorte()==0) {
			mensajeError("El corte de la linea no debe ser cero(0)");
			return false;
		}
		
		getObjeto().setSucursal(getObjeto().getSimcard().getSucursal());
		
		Plan plan=null;
		try {
			plan=planFacade.find(Integer.valueOf(idPlan));
			getObjeto().setPlan(plan);
		} catch (Exception e) {
			mensajeError(e.toString());
			return false;
		}
		
		if (isEdit()) {
			if (empresa == null || estadoLinea == null
					|| getObjeto().getPlan() == null
					|| getObjeto().getSimcard() == null) {
				mensajeError("Para poder continuar por favor diligencia "
						+ empresa == null ? "empresa"
						: "" + " " + estadoLinea == null ? "estado linea "
								: " " + getObjeto().getSimcard() == null ? " simcard"
										: " " + getObjeto().getPlan() == null ? " plan "
												: "");
				return false;
			}
			if (!getObjeto().getPlan().equals(planOLd)) {
				if (getObjeto().getPlanLineaList() == null) {
					getObjeto().setPlanLineaList(new ArrayList<PlanLinea>());
					PlanLinea pl = new PlanLinea();
					pl.setFecha(new Date());
					pl.setLineaidLinea(getObjeto());
					pl.setPlaEstado(1);
					pl.setPlanidPlan(getObjeto().getPlan());
					getObjeto().getPlanLineaList().add(pl);
					plFacade.desactivarTodosPlanesLineas(getObjeto());

				}
			}

			if (lineaFacade.findBNumero2(getObjeto().getLinNumero())) {
				getObjeto().setEmpresaidEmpresa(
						facade.find(Integer.valueOf(empresa)));
				getObjeto().setEstadoLineaidEstadoLinea(
						terceroFacade.find(Integer.parseInt(estadoLinea)));
				return true;
			} else {
				mensaje("Error",
						"El numero de linea ya esta en uso por favor intente con otro numero");
				return false;
			}

		} else {

			if (empresa == null || estadoLinea == null
					|| getObjeto().getPlan() == null
					|| getObjeto().getSimcard() == null) {
				mensajeError("Para poder continuar por favor diligencia "
						+ empresa == null ? "empresa"
						: "" + " " + estadoLinea == null ? "estado linea "
								: " " + getObjeto().getSimcard() == null ? " simcard"
										: " " + getObjeto().getPlan() == null ? " plan "
												: "");
				return false;
			}

			if (empresa == null || estadoLinea == null) {
				mensajeError("Para poder continuar por favor diligencia "
						+ empresa == null ? "empresa"
						: "" + " " + estadoLinea == null ? "estado linea " : "");
				return false;
			} else {
				if (lineaFacade.find(getObjeto().getIdLinea()) == null
						&& lineaFacade.findBNumero(getObjeto().getLinNumero())) {
					getObjeto().setEmpresaidEmpresa(
							facade.find(Integer.valueOf(empresa)));
					getObjeto().setEstadoLineaidEstadoLinea(
							terceroFacade.find(Integer.parseInt(estadoLinea)));
					Simcard sim = getObjeto().getSimcard();
					sim.setSimEstado(EstadosSimcardEnum.ASIGNADA);
					simcardFacade.edit(sim);
					return true;
				} else {
					mensajeError("El codigo o numero  de linea ya esta siendo utilizado");
					return false;
				}
			}

		}

	}

	/**
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {
		try {

			plFacade.activarPorLineaYPlan(getObjeto(), getObjeto().getPlan());
		} catch (Exception e) {
			return;
		}
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEstadoLinea() {
		return estadoLinea;
	}

	public void setEstadoLinea(String estadoLinea) {
		this.estadoLinea = estadoLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @return the buscadorPlan
	 */
	public BuscadorPlan getBuscadorPlan() {
		return buscadorPlan;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param buscadorPlan
	 *            the buscadorPlan to set
	 */
	public void setBuscadorPlan(BuscadorPlan buscadorPlan) {
		this.buscadorPlan = buscadorPlan;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 * @return the buscadorSimCard
	 */
	public BuscadorSimCard getBuscadorSimCard() {
		return buscadorSimCard;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 * @param buscadorSimCard
	 *            the buscadorSimCard to set
	 */
	public void setBuscadorSimCard(BuscadorSimCard buscadorSimCard) {
		this.buscadorSimCard = buscadorSimCard;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @return the idSucursal
	 */
	public String getIdSucursal() {
		return idSucursal;
	}
	 /**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 1/08/2013
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(String idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @return the idPlan
	 */
	public String getIdPlan() {
		return idPlan;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @param idPlan the idPlan to set
	 */
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}
}
