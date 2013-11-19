/**
 * 
 */
package com.invte.rentavoz.vista.permiso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;
import co.com.rentavoz.logica.jpa.fachadas.UsuarioFacade;

import com.invte.rentavoz.vista.BaseBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanPermiso
 * @date 15/11/2013
 *
 */
@ManagedBean
@ViewScoped
public class BeanPermiso extends BaseBean implements Serializable{

	/**
	 * 15/11/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 15/11/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * usuarios
	 */
	private List<Usuario> usuarios= new ArrayList<Usuario>();
	
	/**
	 * 15/11/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * usuarioFacade
	 */
	@EJB
	private UsuarioFacade usuarioFacade;
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/11/2013
	 */
	@PostConstruct
	public void init(){
		
		usuarios=usuarioFacade.findAll();
	}
	
	
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/11/2013
	 * @return the usuarios
	 */
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/11/2013
	 * @param usuarios the usuarios to set
	 */
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
