/**
 * 
 */
package co.com.rentavoz.logica.jpa.entidades.almacen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class Cuota
 * @date 15/07/2013
 * 
 */
@Entity
@Table(name = "cuota")
public class Cuota implements Serializable {

	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final String SPACE = " ";

	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final String UNDERLINE = "_";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer numero;

	@JoinColumn(name = "id_venta")
	@ManyToOne
	private Venta venta;

	@Column(name = "estado_cuota")
	@Enumerated(EnumType.STRING)
	private EstadoCuotaEnum estadoCuota;

	@Column(name = "valor_cuota")
	private BigDecimal valorCuota;

	@Column(name = "fecha_pago")
	@Temporal(TemporalType.DATE)
	private Date fechaPago;
	
	
	@Transient
	private boolean seleccionada;

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 */
	public Cuota() {

	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param numero
	 */
	public Cuota(Integer numero) {
		super();
		this.numero = numero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param numero
	 * @param venta
	 * @param estadoCuota
	 * @param valorCuota
	 */
	public Cuota(Integer numero, Venta venta, EstadoCuotaEnum estadoCuota,
			BigDecimal valorCuota) {
		super();
		this.numero = numero;
		this.venta = venta;
		this.estadoCuota = estadoCuota;
		this.valorCuota = valorCuota;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venta
	 *            the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the estadoCuota
	 */
	public EstadoCuotaEnum getEstadoCuota() {
		return estadoCuota;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param estadoCuota
	 *            the estadoCuota to set
	 */
	public void setEstadoCuota(EstadoCuotaEnum estadoCuota) {
		this.estadoCuota = estadoCuota;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the valorCuota
	 */
	public BigDecimal getValorCuota() {
		return valorCuota;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param valorCuota
	 *            the valorCuota to set
	 */
	public void setValorCuota(BigDecimal valorCuota) {
		this.valorCuota = valorCuota;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fechaPago
	 */
	public Date getFechaPago() {
		return fechaPago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fechaPago
	 *            the fechaPago to set
	 */
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @return
	 */
	public String estadoCuotaAsString(){
		
	return estadoCuota.name().replace(UNDERLINE, SPACE);	
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the seleccionada
	 */
	public boolean isSeleccionada() {
		return seleccionada;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param seleccionada the seleccionada to set
	 */
	public void setSeleccionada(boolean seleccionada) {
		this.seleccionada = seleccionada;
	}
	/**
	 * 
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @return
	 */
	public boolean isPagada(){
		if (estadoCuota.equals(EstadoCuotaEnum.PAGADA)) {
			return true;
		}else{
			return false;
			
		}
		
	}
}
