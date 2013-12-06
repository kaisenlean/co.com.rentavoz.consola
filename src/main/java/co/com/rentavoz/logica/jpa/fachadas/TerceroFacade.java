/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.TipoTerceroEnum;
import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class TerceroFacade
* @date 6/10/2013
*
 */
@Stateless
public class TerceroFacade extends AbstractFacade<Tercero> implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3830935209390129416L;
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TerceroFacade() {
		super(Tercero.class);
	}

	@SuppressWarnings("unchecked")
	public List<Tercero> findByCriterio(String criterio) {
		Query q = getEntityManager()
				.createQuery(
						"SELECT t FROM Tercero t WHERE t.terApellidos LIKE :criterio OR t.terNombre LIKE :criterio  OR t.terDocumento LIKE :criterio");
		q.setParameter("criterio", "%" + criterio + "%");
		return q.getResultList();
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/06/2013
	 * @param val
	 * @return
	 */
	public Tercero findByDocumento(Integer val) {
		Query query = getEntityManager().createQuery(
				"SELECT t FROM Tercero t WHERE t.terDocumento = :documento");
		query.setParameter("documento", val);
		query.setMaxResults(1);
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			return (Tercero) query.getSingleResult();

		}
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param user
	 * @return
	 */
	public Tercero findByUsuario(Usuario user) {
		Query query = getEntityManager().createQuery(
				"SELECT t FROM Tercero t WHERE t.usuario = :usuario");
		query.setParameter("usuario", user);
		query.setMaxResults(1);
		if (!query.getResultList().isEmpty()) {
			return (Tercero) query.getSingleResult();
		} else {
			return null;

		}
	}
	
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idCentrope
	* @return
	 */
	@SuppressWarnings("unchecked")
	public List<Tercero> findByCentrope(int idCentrope){
		
		Query query= getEntityManager().createQuery("SELECT t FROM Tercero t WHERE t.centrope.id = :centrope");
		query.setParameter("centrope", idCentrope);
		
		
		return query.getResultList();
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/10/2013
	* @param criterio
	* @return
	*/
	@SuppressWarnings("unchecked")
	public List<Tercero> findByCriterioProveedor(String criterio) {
		Query q = getEntityManager()
				.createQuery(
						"SELECT t FROM Tercero t WHERE t.tipo = :tipoTercero AND  t.terApellidos LIKE :criterio OR t.terNombre LIKE :criterio  OR t.terDocumento LIKE :criterio");
		q.setParameter("criterio", "%" + criterio + "%");
		q.setParameter("tipoTercero",  TipoTerceroEnum.PROVEEDOR);
		return q.getResultList();
	}

}
