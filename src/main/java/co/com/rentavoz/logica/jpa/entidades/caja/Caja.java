package co.com.rentavoz.logica.jpa.entidades.caja;

import java.io.Serializable;

import javax.persistence.*;

import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;

import java.util.Date;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class Caja
* @date 30/10/2013
*
 */
@Entity
@Table(name = "caja")
public class Caja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.STRING)
	private EstadoCaja estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@JoinColumn(name="usuario")
	private Usuario usuario;

	public Caja() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the estado
	 */
	public EstadoCaja getEstado() {
		return estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(EstadoCaja estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}