package co.com.rentavoz.logica.jpa.entidades.bodega;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the bodega_salida_referencia database table.
 * 
 */
@Entity
@Table(name="bodega_salida_referencia")
public class BodegaSalidaReferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to BodegaSalida
	@ManyToOne
	@JoinColumn(name="id_salida")
	private BodegaSalida bodegaSalida;

	//bi-directional many-to-one association to BodegaExistencia
	@ManyToOne
	@JoinColumn(name="existencia")
	private BodegaExistencia bodegaExistencia;

	
	
	public BodegaSalidaReferencia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BodegaSalida getBodegaSalida() {
		return this.bodegaSalida;
	}

	public void setBodegaSalida(BodegaSalida bodegaSalida) {
		this.bodegaSalida = bodegaSalida;
	}

	public BodegaExistencia getBodegaExistencia() {
		return this.bodegaExistencia;
	}

	public void setBodegaExistencia(BodegaExistencia bodegaExistencia) {
		this.bodegaExistencia = bodegaExistencia;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((bodegaExistencia == null) ? 0 : bodegaExistencia.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BodegaSalidaReferencia other = (BodegaSalidaReferencia) obj;
		if (bodegaExistencia == null) {
			if (other.bodegaExistencia != null)
				return false;
		} else if (!bodegaExistencia.equals(other.bodegaExistencia))
			return false;
		return true;
	}
	

}