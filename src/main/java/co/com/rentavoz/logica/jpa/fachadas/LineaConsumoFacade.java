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

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.entidades.almacen.LineaConsumo;

@Stateless
public class LineaConsumoFacade extends AbstractFacade<LineaConsumo> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public LineaConsumoFacade() {
		super(LineaConsumo.class);
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param linea
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LineaConsumo> consultarConsumoActual(Linea linea) {
		Query query = getEntityManager()
				.createQuery(
						"SELECT lc FROM LineaConsumo lc WHERE lc.linea = :line ORDER BY lc.fecha DESC");
		query.setParameter("line", linea);
		return query.getResultList();

	}
}
