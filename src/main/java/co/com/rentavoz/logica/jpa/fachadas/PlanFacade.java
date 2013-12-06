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

import co.com.rentavoz.logica.jpa.entidades.Plan;

/**
 * 
 * @author ejody
 */
@Stateless
public class PlanFacade extends AbstractFacade<Plan> {

	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PlanFacade() {
		super(Plan.class);
	}

	public int nextCodigo() {
		Query q = getEntityManager().createQuery(
				"SELECT MAX(p.idPlan) FROM Plan p ");
		q.setMaxResults(1);
		int resultado = 0;
		try {
			resultado = Integer.parseInt(q.getSingleResult().toString()) + 1;
		} catch (Exception e) {
			resultado = 1;
		}

		return resultado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param criterio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Plan> findByCriterio(String criterio) {
		Query q = getEntityManager().createQuery(
				"SELECT p FROM Plan p WHERE p.plaNombre LIKE :criterio");
		q.setParameter("criterio", "%" + criterio + "%");
		return q.getResultList();

	}
}
