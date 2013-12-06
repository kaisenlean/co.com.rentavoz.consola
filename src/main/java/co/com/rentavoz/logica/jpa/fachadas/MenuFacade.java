/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.rentavoz.logica.jpa.entidades.Menu;

/**
 * 
 * @author ejody
 */
@Stateless
public class MenuFacade extends AbstractFacade<Menu> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public MenuFacade() {
		super(Menu.class);
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findTodos() {
		return getEntityManager().createQuery("SELECT m FROM Menu m ")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findTodosByPadre(String padre) {
		return getEntityManager()
				.createQuery("SELECT m FROM Menu m  WHERE m.padre = :padre")
				.setParameter("padre", padre).getResultList();
	}
}
