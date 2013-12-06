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

import co.com.rentavoz.logica.jpa.entidades.tareas.EstadoTareaEnum;
import co.com.rentavoz.logica.jpa.entidades.tareas.Tarea;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class TareaFacade
 * @date 2/09/2013
 * 
 */
@Stateless
public class TareaFacade extends AbstractFacade<Tarea> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TareaFacade() {
		super(Tarea.class);
	}
/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 4/09/2013
* @param owner
* @return
 */
	@SuppressWarnings("unchecked")
	public List<Tarea> findByCentrope(int owner) {

		Query q = getEntityManager()
				.createQuery(
						"SELECT t FROM Tarea t WHERE   t.estado = :estado AND   t.owner.id  = :owner ");
		q.setParameter("owner",owner) ;
		q.setParameter("estado", EstadoTareaEnum.PENDIENTE);

		return q.getResultList();
	}

}
