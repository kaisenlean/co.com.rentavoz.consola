/**
 * 
 */
package com.invte.rentavoz.vista.session;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;
import co.com.rentavoz.logica.jpa.fachadas.UsuarioFacade;

import com.invte.rentavoz.logica.caja.CajaEjb;
import com.invte.rentavoz.vista.BaseBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class Login
 * @date 15/07/2013
 * 
 */
@ManagedBean(eager = true)
@SessionScoped
public class Login extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioFacade usuarioFacade;

	@EJB
	private TerceroFacade terceroFacade;
	
	@EJB
	private CajaEjb cajaEjb;

	private Usuario user;
	private String usuario;
	private String contrasena;
	private Tercero tercero;
	private Sucursal sucursal;

	@PostConstruct
	public void init() {

		user = null;
	}

	public String login() {
		RequestContext context = RequestContext.getCurrentInstance();

		boolean loggedIn = false;
		try {

			user = usuarioFacade.login(usuario, contrasena);
			loggedIn = true;
			context.addCallbackParam("loggedIn", loggedIn);
			buscarTercero();
			cajaEjb.abrirCaja(user);
			return "/dashboard.jsf";
		} catch (Exception e) {
			loggedIn = false;
			context.addCallbackParam("loggedIn", loggedIn);
			mensajeError("Credenciales invalidas");
			return null;
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 */
	private void buscarTercero() {
		tercero = terceroFacade.findByUsuario(user);
		if (tercero!=null) {
			if (!tercero.getSucursalTerceroList().isEmpty()) {
				sucursal=tercero.getSucursalTerceroList().get(0).getSucursalidSucursal();
				
			}
		}

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 22/07/2013
	 */
	public void logOut() {
		ExternalContext ctx = FacesContext.getCurrentInstance()
				.getExternalContext();
		String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();

		try {
			// Usar el contexto de JSF para invalidar la sesión,
			// NO EL DE SERVLETS (nada de HttpServletRequest)
			((HttpSession) ctx.getSession(false)).invalidate();

			// Redirección de nuevo con el contexto de JSF,
			// si se usa una HttpServletResponse fallará.
			// Sin embargo, como ya está fuera del ciclo de vida
			// de JSF se debe usar la ruta completa -_-U
			ctx.redirect(ctxPath + "/");
		} catch (IOException ex) {
			mensaje("Error", ex.toString());
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
	public boolean isSession() {

		return user != null ? true : false;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param tercero
	 *            the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
}
