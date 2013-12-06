/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.rentavoz.logica.jpa.entidades.SucursalSimcard;

/**
 * 
 * @author ejody
 */
@Stateless
public class SucursalSimcardFacade extends AbstractFacade<SucursalSimcard> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SucursalSimcardFacade() {
		super(SucursalSimcard.class);
	}

}
