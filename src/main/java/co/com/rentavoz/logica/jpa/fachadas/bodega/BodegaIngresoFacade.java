/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas.bodega;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaIngreso;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;

/**
 * 
 * @author ejody
 */
@Stateless
public class BodegaIngresoFacade extends AbstractFacade<BodegaIngreso> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BodegaIngresoFacade() {
		super(BodegaIngreso.class);
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BodegaExistencia> findByFechas(Date start, Date end) {
List<BodegaExistencia> existencias = new ArrayList<BodegaExistencia>();
		Query query = getEntityManager()
				.createQuery(
						"SELECT i FROM BodegaIngreso i WHERE i.fechaIngreso BETWEEN :start AND :end");
		query.setParameter("start", start);
		query.setParameter("end", end);
		
		List<BodegaIngreso> ingresos = query.getResultList();
		
		for (BodegaIngreso bodegaIngreso : ingresos) {
			existencias.addAll(bodegaIngreso.getBodegaExistencias());
		}
		return existencias;
	}

}
