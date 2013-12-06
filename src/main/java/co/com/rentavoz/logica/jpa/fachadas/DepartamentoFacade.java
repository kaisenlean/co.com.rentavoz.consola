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

import co.com.rentavoz.logica.jpa.entidades.Departamento;

/**
 * 
 * @author ejody
 */
@Stateless
public class DepartamentoFacade extends AbstractFacade<Departamento> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DepartamentoFacade() {
		super(Departamento.class);
	}

	@SuppressWarnings("unchecked")
	public List<Departamento> findByCriterio(String criterio) {
		Query q = getEntityManager()
				.createQuery(
						"SELECT d FROM Departamento d WHERE d.depNombre LIKE :criterio")
				.setParameter("criterio", "%" + criterio + "%");

		List<Departamento> resultList = q.getResultList();
		return resultList;

	}

}
