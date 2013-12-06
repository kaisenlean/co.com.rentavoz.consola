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

import co.com.rentavoz.logica.jpa.entidades.almacen.EstadosSimcardEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;

/**
 * 
 * @author ejody
 */
@Stateless
public class SimcardFacade extends AbstractFacade<Simcard> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public SimcardFacade() {
		super(Simcard.class);
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Simcard> findDisponibles(String criteria) {
		Query query = getEntityManager()
				.createQuery(
						"SELECT s FROM Simcard s WHERE s.simEstado = :estado AND s.simIccid LIKE :criterio ");
		query.setParameter("estado", EstadosSimcardEnum.EN_BLANCO);
		query.setParameter("criterio", "%" + criteria + "%");

		return query.getResultList();
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/07/2013
	 * @param simIccid
	 * @return
	 */
	public Simcard findByScId(String simIccid) throws Exception {
		Query query = getEntityManager().createQuery(
				"SELECT s FROM Simcard s WHERE s.simIccid = :id");
		query.setParameter("id", simIccid);
		query.setMaxResults(1);

		if (query.getResultList().isEmpty()) {
			return null;
		} else {

			return (Simcard) query.getSingleResult();
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<Simcard> getByScId(String simIccid) throws Exception {
		Query query = getEntityManager().createQuery(
				"SELECT s FROM Simcard s WHERE s.simIccid LIKE :id AND s.simEstado = :estado");
		query.setParameter("id", "%"+simIccid+"%");
		query.setParameter("estado", EstadosSimcardEnum.EN_BLANCO);
		

	return query.getResultList();
	}
}
