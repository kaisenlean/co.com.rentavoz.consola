/**
 * 
 */
package com.invte.rentavoz.vista.maestras.banco;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Banco;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.BancoFacade;

import com.invte.rentavoz.vista.StandardAbm;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BancoBean
 * @date 22/07/2013
 *
 */
@ManagedBean
@ViewScoped
public class BancoBean extends StandardAbm<Banco> implements Serializable {

	/**
	 * 22/07/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4006490918700541124L;

	@EJB
	private BancoFacade bancoFacade;
	
	
	
	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public AbstractFacade<Banco> getFacade() {
		
		return bancoFacade;
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public Banco getInstancia() {
		 
		return new Banco();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		
		return "/paginas/maestras/banco/index.jsf";
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public Banco getObjeto() {
		 
		return obtenerObjeto();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<Banco> getListado() {
		 
		return obtenerListado();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		 
		
	}
	
	
	
	

}
