/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas.bodega;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class BodegaItemFacade
 * @date 7/10/2013
 * 
 */
@Stateless
public class BodegaItemFacade extends AbstractFacade<BodegaItem> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BodegaItemFacade() {
		super(BodegaItem.class);
	}

	public boolean existReferencia(String ref) {
		Query query = getEntityManager().createQuery(
				"SELECT i FROM BodegaItem i WHERE i.referencia = :ref");
		query.setParameter("ref", new StringBuilder(ref).toString());
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {
			return false;
		} else {

			return true;
		}
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BodegaItem> findByCriterio(String param) {

		Query query = getEntityManager().createQuery(
				"SELECT t FROM BodegaItem t WHERE t.nombre LIKE :param");
		query.setParameter(new StringBuilder("param").toString(),
				new StringBuilder("%").append(param).append("%").toString());

		return query.getResultList();
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 21/10/2013
	 * @param nombre
	 * @return
	 */
	public BodegaItem findByNombre(String nombre) {

		Query query = getEntityManager().createQuery(
				"SELECT b FROM BodegaItem b WHERE b.nombre = :nombre");
		query.setParameter("nombre", nombre);
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {
			return null;
		}

		return (BodegaItem) query.getSingleResult();

	}
	

}
