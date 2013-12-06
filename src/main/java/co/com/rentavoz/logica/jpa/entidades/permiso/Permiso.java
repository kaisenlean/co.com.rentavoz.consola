package co.com.rentavoz.logica.jpa.entidades.permiso;

import java.io.Serializable;

import javax.persistence.*;

import co.com.rentavoz.logica.jpa.entidades.Menu;
import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;

/**
 * The persistent class for the permiso database table.
 * 
 */
@Entity
@Table(name = "permiso")
public class Permiso implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private boolean acceso;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name = "menu")
	private Menu menu;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.permiso co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	
//	@JoinColumn(name = "usuario")
//	private Usuario usuario;

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/11/2013
	 */
	public Permiso() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the usuario
	 */
//	public Usuario getUsuario() {
//		return usuario;
//	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param acceso
	 *            the acceso to set
	 */
	public void setAcceso(boolean acceso) {
		this.acceso = acceso;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param usuario
	 *            the usuario to set
	 */
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the acceso
	 */
	public boolean isAcceso() {
		return acceso;
	}

}