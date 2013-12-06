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

/**
 * 
 * @author ejody
 */
@Stateless
public class LineaFacade extends AbstractFacade<Linea> {
	/**
	 * co.com.rentavoz.logica.jpa.fachadas
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final int ESTADO_LINEA_DISPONIBLE_CON_USO = 1;
	/**
	 * co.com.rentavoz.logica.jpa.fachadas
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final int ESTADO_LINEA_DISPONIBLE_USO = 4;
	private static final Integer ESTADO_LINEA_ACTIVO = 1;
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public LineaFacade() {
		super(Linea.class);
	}

	public Integer nextCodigo() {
		Query q = getEntityManager().createQuery(
				"SELECT MAX(l.idLinea) FROM Linea l ");
		q.setMaxResults(1);
		Integer resultado = Integer.valueOf("0");

		try {
			resultado = Integer.valueOf(q.getSingleResult().toString()) + 1;
		} catch (Exception e) {
			resultado = Integer.valueOf("1");
		}
		return resultado;
	}

	public boolean findBNumero(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero");
		q.setParameter("numero", linNumero);
		q.setMaxResults(1);
		if (q.getResultList().isEmpty()) {
			return true;
		} else if (q.getSingleResult() == null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param linNumero
	 * @return
	 */
	public Linea findBNumeroObjeto(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero AND  l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		q.setParameter("numero", linNumero);
		q.setParameter("estado", ESTADO_LINEA_ACTIVO);
		q.setMaxResults(1);
		if (q.getResultList().isEmpty()) {
			return null;
		} else if (q.getSingleResult() != null) {
			return (Linea) q.getSingleResult();
		} else {
			return null;
		}

	}
	
	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param linNumero
	 * @return
	 */
	public Linea findByNumeroObjeto(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero ");
		q.setParameter("numero", linNumero);
		q.setMaxResults(1);
		if (q.getResultList().isEmpty()) {
			return null;
		} else if (q.getSingleResult() != null) {
			return (Linea) q.getSingleResult();
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param linNumero
	 * @return
	 */
	public boolean findBNumero2(String linNumero) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero = :numero");
		q.setParameter("numero", linNumero);

		if (q.getResultList().size() == 1) {
			return true;
		} else
			return false;
	}

	@SuppressWarnings("unchecked")
	public List<Linea> findDisponibles() {
		// buscar las lineas en estado disponibles
		Query q = getEntityManager()
				.createQuery(
						"SELECT l FROM Linea l WHERE l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		q.setParameter("estado", ESTADO_LINEA_ACTIVO);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Linea> findPlayers(int startingAt, int maxPerPage) {

		// regular query that will search for players in the db
		Query query = getEntityManager()
				.createQuery(
						"SELECT l FROM Linea l WHERE l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		query.setParameter("estado", ESTADO_LINEA_ACTIVO);
		
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Linea> findPlayers2(int startingAt, int maxPerPage) {

		// regular query that will search for players in the db
		Query query = getEntityManager()
				.createQuery(
						"SELECT l FROM Linea l WHERE l.estadoLineaidEstadoLinea.idEstadoLinea = :estado  OR l.estadoLineaidEstadoLinea.idEstadoLinea = :estado2");
		query.setParameter("estado", ESTADO_LINEA_ACTIVO);
		query.setParameter("estado2", ESTADO_LINEA_DISPONIBLE_CON_USO);
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		return query.getResultList();
	}


	/**
	 * 
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @return
	 */
	public int countPlayersTotal() {

		Query query = getEntityManager()
				.createQuery(
						"select COUNT(p) from Linea p WHERE p.estadoLineaidEstadoLinea.idEstadoLinea = :estado");

		query.setParameter("estado", ESTADO_LINEA_ACTIVO);

		Number result = (Number) query.getSingleResult();

		return result.intValue();

	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/07/2013
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Linea> findByCriteria(String query) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero LIKE :query AND  l.estadoLineaidEstadoLinea.idEstadoLinea = :estado");
		q.setParameter("query", "%" + query + "%");
		q.setParameter("estado", ESTADO_LINEA_ACTIVO);
		return q.getResultList();
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 1/08/2013
	* @param query
	* @param idSucursal
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Linea> findByCriteria(String query, int idSucursal) {
		Query q = getEntityManager().createQuery(
				"SELECT l FROM Linea l WHERE l.linNumero LIKE :query AND ( l.estadoLineaidEstadoLinea.idEstadoLinea = :estado OR l.estadoLineaidEstadoLinea.idEstadoLinea = :estado2) AND l.sucursal.idSucursal = :sucursal");
		q.setParameter("query", "%" + query + "%");
		q.setParameter("sucursal", idSucursal);
		q.setParameter("estado", ESTADO_LINEA_ACTIVO);
		q.setParameter("estado2", ESTADO_LINEA_DISPONIBLE_USO);
		return q.getResultList();
	}

}
