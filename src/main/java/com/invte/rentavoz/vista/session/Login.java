/**
 * 
 */
package com.invte.rentavoz.vista.session;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.invte.rentavoz.vista.BaseBean;

import co.com.rentavoz.logica.jpa.entidades.Usuario;
import co.com.rentavoz.logica.jpa.fachadas.UsuarioFacade;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class Login
 * @date 15/07/2013
 * 
 */
@ManagedBean(eager=true)
@SessionScoped
public class Login extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioFacade usuarioFacade;

	private Usuario user;
	private String usuario;
	private String contrasena;
	

	@PostConstruct
	public void init() {

		user = null;
	}

	public String login() {
		try {

			user = usuarioFacade.login(usuario, contrasena);
			return "/dashboard.jsf";
		} catch (Exception e) {
			mensaje("Error", "Credenciales invalidas");
			return null;
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the usuarioFacade
	 */
	public UsuarioFacade getUsuarioFacade() {
		return usuarioFacade;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param usuarioFacade
	 *            the usuarioFacade to set
	 */
	public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
		this.usuarioFacade = usuarioFacade;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param user
	 *            the user to set
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param contrasena
	 *            the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/07/2013
	* @return
	 */
	public boolean isSession(){
		
		return user!=null?true:false;
	}

}
