package co.com.rentavoz.logica.jpa.entidades.venta.existencia;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class VentaItemCuota
* @date 29/10/2013
*
 */
@Entity
@Table(name="venta_item_cuota")
public class VentaItemCuota implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(length=20)
	@Enumerated(EnumType.STRING)
	private EstadoVentaItemCuotaEnum estado;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_cierre")
	private Date fechaCierre;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_pago")
	private Date fechaPago;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name="idVenta")
	private VentaItem idVenta;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private double valor;

	
	@Transient
	private boolean pagada;
	
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	*/
	public VentaItemCuota() {
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public int getId() {
		return this.id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param id
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public EstadoVentaItemCuotaEnum getEstado() {
		return this.estado;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param estado
	*/
	public void setEstado(EstadoVentaItemCuotaEnum estado) {
		this.estado = estado;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public Date getFechaCierre() {
		return this.fechaCierre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param fechaCierre
	*/
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public Date getFechaPago() {
		return this.fechaPago;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param fechaPago
	*/
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public VentaItem getIdVenta() {
		return this.idVenta;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param idVenta
	*/
	public void setIdVenta(VentaItem idVenta) {
		this.idVenta = idVenta;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public double getValor() {
		return this.valor;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param valor
	*/
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the pagada
	 */
	public boolean getPagada() {
		return pagada;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param pagada the pagada to set
	 */
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

}