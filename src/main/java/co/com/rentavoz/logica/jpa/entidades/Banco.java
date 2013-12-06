/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "banco")
/*
 * bancoNombre VARCHAR( 255) , bancoDireccion VARCHAR(150) , bancoTelefono
 * VARCHAR(10)
 */
public class Banco implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBanco")
	private Integer idBanco;
	@Basic(optional = false)
	@NotNull
	@Size(max = 255)
	@Column(name = "bancoNombre")
	private String bancoNombre;

	@Basic(optional = false)
	@NotNull
	@Size(max = 150)
	@Column(name = "bancoDireccion")
	private String bancoDireccion;

	@Basic(optional = false)
	@NotNull
	@Size(max = 10)
	@Column(name = "bancoTelefono")
	private String bancoTelefono;

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 */
	public Banco() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the bancoDireccion
	 */
	public String getBancoDireccion() {
		return bancoDireccion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the bancoNombre
	 */
	public String getBancoNombre() {
		return bancoNombre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the bancoTelefono
	 */
	public String getBancoTelefono() {
		return bancoTelefono;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the idBanco
	 */
	public Integer getIdBanco() {
		return idBanco;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param bancoDireccion
	 *            the bancoDireccion to set
	 */
	public void setBancoDireccion(String bancoDireccion) {
		this.bancoDireccion = bancoDireccion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param bancoNombre
	 *            the bancoNombre to set
	 */
	public void setBancoNombre(String bancoNombre) {
		this.bancoNombre = bancoNombre;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param bancoTelefono
	 *            the bancoTelefono to set
	 */
	public void setBancoTelefono(String bancoTelefono) {
		this.bancoTelefono = bancoTelefono;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param idBanco
	 *            the idBanco to set
	 */
	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

}
