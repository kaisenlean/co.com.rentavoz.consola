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

import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoVentaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;

/**
 * 
 * @author ejody
 */
@Stateless
public class VentaFacade extends AbstractFacade<Venta> {
	/**
	 * 
	 */
	private static final String FIELD_ESTADO = "estado";
	/**
	 * 
	 */
	private static final EstadoVentaEnum ESTADO_VENTA_ACTIVA = EstadoVentaEnum.ACTIVA;
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public VentaFacade() {
		super(Venta.class);
	}

	public Integer findSgteNumero() {
		Query query = getEntityManager().createQuery(
				"SELECT MAX(v.idVenta) FROM Venta v");
		query.setMaxResults(1);

		if (query.getSingleResult() == null) {
			return Integer.valueOf("1");
		} else {
			return Integer.valueOf(query.getSingleResult().toString()) + 1;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.com.rentavoz.logica.jpa.fachadas.AbstractFacade#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Venta> findAll() {
		Query query = getEntityManager().createQuery(
				"SELECT v FROM Venta v WHERE v.estadoVenta = :estado");
		query.setParameter(FIELD_ESTADO, ESTADO_VENTA_ACTIVA);
		return query.getResultList();
	}
}
