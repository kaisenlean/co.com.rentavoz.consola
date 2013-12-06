package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the centrope database table.
 * 
 */
@Entity
@Table(name="centrope")
@NamedQuery(name="Centrope.findAll", query="SELECT c FROM Centrope c")
public class Centrope implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column
	private String descripcion;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column
	private String nombre;

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	*/
	public Centrope() {
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public int getId() {
		return this.id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param id
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param descripcion
	*/
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getNombre() {
		return this.nombre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param nombre
	*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}