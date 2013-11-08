/**
 * 
 */
package com.invte.rentavoz.logica.caja;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;
import co.com.rentavoz.logica.jpa.fachadas.CajaFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class CajaEjb
 * @date 7/11/2013
 *
 */
@Stateless
public class CajaEjb implements Serializable{

	/**
	 * 7/11/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private CajaFacade facade;
	
	
	public void abrirCaja(Usuario usuario){
		facade.abrirCaja(usuario);
		
	}
	
	
	public double valorCaja(){
		
		return facade.valorCaja();
	}
	

}
