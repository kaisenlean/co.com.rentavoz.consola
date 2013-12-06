/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class UsuarioFacade
 * @date 13/07/2013
 * 
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public UsuarioFacade() {
		super(Usuario.class);
	}

	public Usuario login(String usuario, String contrasena) throws Exception {

		Query query = getEntityManager()
				.createQuery(
						"SELECT u FROM Usuario u WHERE u.usuario =:usuario AND u.contrasena = :contrasena");
		query.setParameter("usuario", usuario);
		query.setParameter("contrasena", contrasena);

		query.setMaxResults(1);

		return (Usuario) query.getSingleResult();

	}
}
