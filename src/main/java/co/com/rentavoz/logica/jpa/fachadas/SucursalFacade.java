/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.rentavoz.logica.jpa.entidades.Sucursal;

/**
 * 
 * @author ejody
 */
@Stateless
public class SucursalFacade extends AbstractFacade<Sucursal> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SucursalFacade() {
		super(Sucursal.class);
	}

	@Override
	public List<Sucursal> findAll() {
		return super.findAll(); // To change body of generated methods, choose
								// Tools | Templates.
	}

}
