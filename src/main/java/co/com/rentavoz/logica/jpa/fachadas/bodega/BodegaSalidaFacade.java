/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas.bodega;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaSalida;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaSalidaReferencia;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class BodegaSalidaFacade
* @date 16/10/2013
*
 */
@Stateless
public class BodegaSalidaFacade extends AbstractFacade<BodegaSalida> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BodegaSalidaFacade() {
		super(BodegaSalida.class);
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/10/2013
	* @param start
	* @param end
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<BodegaSalidaReferencia> findByFechas(Date start, Date end) {

		Query query = getEntityManager().createQuery("SELECT s FROM BodegaSalidaReferencia s WHERE s.bodegaSalida.fechaSalida BETWEEN :start AND :end");
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		return query.getResultList();
	}

}
