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

import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.entidades.bodega.EstadoExistenciaEnum;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class BodegaExistenciaFacade
* @date 21/10/2013
*
 */
@Stateless
public class BodegaExistenciaFacade extends AbstractFacade<BodegaExistencia> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public BodegaExistenciaFacade() {
		super(BodegaExistencia.class);
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param productoId
	 * @return
	 */
	public BodegaExistencia findByBarcode(String productoId) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE e.barCode = :proId")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/10/2013
	* @param productoId
	* @param sucursal
	* @return
	 */
	public BodegaExistencia findByBarcode(String productoId,Sucursal sucursal) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE e.barCode = :proId AND e.sucursal = :suc")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		
		query.setParameter(new StringBuilder("suc").toString(),
				sucursal);

		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 7/10/2013
	* @param productoId
	* @return
	*/
	public BodegaExistencia findByBarcodeActivo(String productoId) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE e.barCode = :proId AND e.estado =:estado")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		query.setParameter("estado", EstadoExistenciaEnum.DISPONIBLE);
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	
	
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	* @param productoId
	* @param sucursal
	* @return
	 */
	public BodegaExistencia findByBarcodeActivo(String productoId,Sucursal sucursal) {
		Query query = getEntityManager()
				.createQuery(
						new StringBuilder(
								"SELECT e FROM BodegaExistencia e WHERE e.barCode = :proId AND e.estado =:estado AND e.sucursal = :suc")
								.toString());
		query.setParameter(new StringBuilder("proId").toString(),
				new StringBuilder(productoId).toString());
		query.setParameter("estado", EstadoExistenciaEnum.DISPONIBLE);
		query.setParameter("suc", sucursal);
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {

			return null;
		} else {

			return (BodegaExistencia) query.getSingleResult();
		}
	}
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 21/10/2013
	* @param sucursal
	* @param bodegaItem
	* @return
	 */
	@SuppressWarnings("unchecked")
	public List<BodegaExistencia> findByItemAndSucursal(Sucursal sucursal, BodegaItem bodegaItem){
		
		Query query = getEntityManager().createQuery("SELECT b FROM BodegaExistencia b WHERE b.bodegaItemBean = :item AND b.sucursal = :sucursal");
		
		query.setParameter("item", bodegaItem);
		query.setParameter("sucursal", sucursal);
		
		return query.getResultList();
		
		
	}

}
