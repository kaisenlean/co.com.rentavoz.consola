/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.Plan;
import co.com.rentavoz.logica.jpa.entidades.PlanLinea;
import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;

/**
 * 
 * @author ejody
 */
@Stateless
public class PlanLineaFacade extends AbstractFacade<PlanLinea> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PlanLineaFacade() {
		super(PlanLinea.class);
	}

	public void desactivarTodosPlanesLineas(Linea l) {
		Query q = getEntityManager()
				.createQuery(
						"UPDATE PlanLinea pl SET pl.plaEstado=0 WHERE pl.lineaidLinea = :linea");
		q.setParameter("linea", l);
		q.executeUpdate();

	}

	public void activarPorLineaYPlan(Linea l, Plan p) {
		Query q = getEntityManager()
				.createQuery(
						"UPDATE PlanLinea pl SET pl.plaEstado=1 WHERE pl.lineaidLinea = :linea AND pl.planidPlan = :plan");
		q.setParameter("linea", l);
		q.setParameter("plan", p);
		q.executeUpdate();

	}
}
