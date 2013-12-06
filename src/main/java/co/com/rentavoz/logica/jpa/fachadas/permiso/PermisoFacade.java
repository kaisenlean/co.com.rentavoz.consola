/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas.permiso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.rentavoz.logica.jpa.entidades.permiso.Permiso;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class PermisoFacade
* @date 15/11/2013
*
 */
@Stateless
public class PermisoFacade extends AbstractFacade<Permiso> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PermisoFacade() {
		super(Permiso.class);
	}

	
}
