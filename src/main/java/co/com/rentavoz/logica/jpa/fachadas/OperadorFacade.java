/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.Operador;

/**
 * 
 * @author ejody
 */
@Stateless
public class OperadorFacade extends AbstractFacade<Operador> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public OperadorFacade() {
		super(Operador.class);
	}

	@SuppressWarnings("unchecked")
	public List<Operador> findByCriterio(String criterio) {
		Query query = getEntityManager().createQuery(
				"SELECT o FROM Operador o WHERE o.opeNombre LIKE :criterio");
		query.setParameter("criterio", "%" + criterio + "%");
		return query.getResultList();
	}

}
