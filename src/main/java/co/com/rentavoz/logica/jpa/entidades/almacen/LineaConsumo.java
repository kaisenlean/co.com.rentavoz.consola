package co.com.rentavoz.logica.jpa.entidades.almacen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class LineaConsumo
 * @date 15/10/2013
 * 
 */
@Entity
@Table(name = "linea_consumo")
public class LineaConsumo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int id;

	private int claro;

	private int fijo;

	@ManyToOne
	@JoinColumn(name = "linea")
	private Linea linea;

	private int otros;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	public LineaConsumo() {
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
	 * @return the claro
	 */
	public int getClaro() {
		return claro;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 15/10/2013
	* @param claro
	*/
	public void setClaro(int claro) {
		this.claro = claro;
	}

	public int getFijo() {
		return this.fijo;
	}

	public void setFijo(int fijo) {
		this.fijo = fijo;
	}

	public Linea getLinea() {
		return this.linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public int getOtros() {
		return this.otros;
	}

	public void setOtros(int otros) {
		this.otros = otros;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}