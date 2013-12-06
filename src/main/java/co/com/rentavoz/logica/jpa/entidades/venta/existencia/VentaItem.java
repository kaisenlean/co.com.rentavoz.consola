package co.com.rentavoz.logica.jpa.entidades.venta.existencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.rentavoz.logica.jpa.entidades.Cuentas;
import co.com.rentavoz.logica.jpa.entidades.Tercero;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class VentaItem
 * @date 29/10/2013
 * 
 */
@Entity
@Table(name = "venta_item")
public class VentaItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVenta;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name = "cliente")
	private Tercero cliente;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private double descuento;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(length = 50)
	@Enumerated(EnumType.STRING)
	private EstadoVentaItemEnum estado;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Temporal(TemporalType.DATE)
	private Date fecha;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Lob
	private String observacion;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private double valorPagar;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name="vendedor")
	private Tercero vendedor;

	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name="cuenta")
	private Cuentas cuenta;
	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name="modo_pago")
	@Enumerated(EnumType.STRING)
	private ModoPagoEnum modoPago;
	
	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(mappedBy = "idVenta", cascade = { CascadeType.ALL })
	private List<VentaItemDetalleItem> existencias = new ArrayList<VentaItemDetalleItem>();

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(mappedBy = "idVenta", cascade = { CascadeType.ALL })
	private List<VentaItemCuota> cuotas = new ArrayList<VentaItemCuota>();

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public VentaItem() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public int getIdVenta() {
		return this.idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param idVenta
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Tercero getCliente() {
		return this.cliente;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cliente
	 */
	public void setCliente(Tercero cliente) {
		this.cliente = cliente;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public double getDescuento() {
		return this.descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param descuento
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public EstadoVentaItemEnum getEstado() {
		return this.estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param estado
	 */
	public void setEstado(EstadoVentaItemEnum estado) {
		this.estado = estado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Date getFecha() {
		return this.fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public String getObservacion() {
		return this.observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param observacion
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public double getValorPagar() {
		return this.valorPagar;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param valorPagar
	 */
	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return
	 */
	public Tercero getVendedor() {
		return this.vendedor;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param vendedor
	 */
	public void setVendedor(Tercero vendedor) {
		this.vendedor = vendedor;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the existencias
	 */
	public List<VentaItemDetalleItem> getExistencias() {
		return existencias;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param existencias
	 *            the existencias to set
	 */
	public void setExistencias(List<VentaItemDetalleItem> existencias) {
		this.existencias = existencias;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cuotas
	 */
	public List<VentaItemCuota> getCuotas() {
		return cuotas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(List<VentaItemCuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cuenta
	 */
	public Cuentas getCuenta() {
		return cuenta;
	} 
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(Cuentas cuenta) {
		this.cuenta = cuenta;
	}
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the modoPago
	 */
	public ModoPagoEnum getModoPago() {
		return modoPago;
	}

/**
 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 *@date 2/06/2013
 * @param modoPago the modoPago to set
 */
public void setModoPago(ModoPagoEnum modoPago) {
	this.modoPago = modoPago;
}	

}