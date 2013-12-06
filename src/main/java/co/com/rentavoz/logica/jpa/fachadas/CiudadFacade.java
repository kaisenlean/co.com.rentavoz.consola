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

import co.com.rentavoz.logica.jpa.entidades.Ciudad;
import co.com.rentavoz.logica.jpa.entidades.Departamento;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class CiudadFacade
 * @date 30/06/2013
 * 
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CiudadFacade() {
		super(Ciudad.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ciudad> findAll() {
		Query q = getEntityManager()
				.createQuery(
						"SELECT c FROM Ciudad c ORDER BY c.departamentoidDepartamento.depNombre ASC");
		return q.getResultList();
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 30/06/2013
	 * @param criterio
	 * @param departamento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ciudad> findByCriterio(String criterio,
			Departamento departamento) {
		Query q = getEntityManager()
				.createQuery(
						"SELECT c FROM Ciudad c WHERE c.ciuNombre LIKE :criterio AND c.departamentoidDepartamento = :departamento  ORDER BY c.departamentoidDepartamento.depNombre ASC");
		q.setParameter("criterio", "%" + criterio + "%");
		q.setParameter("departamento", departamento);
		return q.getResultList();
	}

}
