/**
 * 
 */
package com.invte.rentavoz.vista.maestras.simcard;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.almacen.EstadosSimcardEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.SimcardFacade;

import com.invte.rentavoz.vista.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class SimcardBean
 * @date 17/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class SimcardBean extends StandardAbm<Simcard> {

	
	
	/**
	 * 22/07/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/maestras/simcard/index.jsf";

	private static final long serialVersionUID = -1689462037286702729L;
	
	/**
	 * 22/07/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * estadoSim
	 */
	private String estadoSim;

	
	/**
	 * 22/07/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * simcardFacade
	 */
	@EJB
	private SimcardFacade simcardFacade;

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public AbstractFacade<Simcard> getFacade() {

		return simcardFacade;
	}

	
	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
		getObjeto().setFecha(new Date());
		getObjeto().setSimEstado(EstadosSimcardEnum.DISPONIBLE);
		estadoSim=getObjeto().getEstadoAsString();
	}
	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public Simcard getInstancia() {

		return new Simcard();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {

		return REGLA_NAVEGACION;
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public Simcard getObjeto() {

		return obtenerObjeto();
	}

	/**
	 * 
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<Simcard> getListado() {
		return obtenerListado();
	}

	/**
	 * 
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		estadoSim="";

	}

	/**
	 * 	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		getObjeto().setSimEstado(EstadosSimcardEnum.valueOf(EstadosSimcardEnum.class, estadoSim));
		return true;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @return the estadoSim
	 */
	public String getEstadoSim() {
		return estadoSim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param estadoSim the estadoSim to set
	 */
	public void setEstadoSim(String estadoSim) {
		this.estadoSim = estadoSim;
	}
	
	

}
