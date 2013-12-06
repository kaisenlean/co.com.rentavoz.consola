/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.EstadoLinea;
import co.com.rentavoz.logica.jpa.entidades.almacen.ModalidaVentaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.VentaLinea;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class VentaLineaFacade
 * @date 14/07/2013
 * 
 */
@Stateless
public class VentaLineaFacade extends AbstractFacade<VentaLinea> {
	/**
	 * 
	 */
	private static final int ESTADO_LINEA_ACTIVA = 4;
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 */
	public VentaLineaFacade() {
		super(VentaLinea.class);
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<VentaLinea> buscarLineasPorRenovar(Date start, Date end) {
		Query q = getEntityManager()
				.createQuery(
						"SELECT vl FROM VentaLinea vl WHERE vl.ventaidVenta.modalidadVenta = :modalidadVenta AND vl.fechaRenovacion BETWEEN :start AND :end  AND vl.lineaidLinea.estadoLineaidEstadoLinea = :estado");
		q.setParameter("modalidadVenta", ModalidaVentaEnum.VENTA);
		q.setParameter("start", start);
		q.setParameter("end", end);
		q.setParameter(
				"estado",
				(EstadoLinea) getEm().find(EstadoLinea.class,
						ESTADO_LINEA_ACTIVA));
		List<VentaLinea> resultList = q.getResultList();
		return resultList;
	}

	
	

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
