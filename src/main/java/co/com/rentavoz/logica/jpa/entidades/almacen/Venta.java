/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades.almacen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import co.com.rentavoz.logica.jpa.entidades.Pago;
import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.TerceroVenta;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class Venta
 * @date 14/07/2013
 * 
 */
@Entity
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
		@NamedQuery(name = "Venta.findByIdVenta", query = "SELECT v FROM Venta v WHERE v.idVenta = :idVenta"),
		@NamedQuery(name = "Venta.findByVenFecha", query = "SELECT v FROM Venta v WHERE v.venFecha = :venFecha"),
		@NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
		@NamedQuery(name = "Venta.findByVenDomicilio", query = "SELECT v FROM Venta v WHERE v.venDomicilio = :venDomicilio"),
		@NamedQuery(name = "Venta.findByVenSaldo", query = "SELECT v FROM Venta v WHERE v.venSaldo = :venSaldo") })
public class Venta implements Serializable {
	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVenta")
	private Integer idVenta;
	@Basic(optional = false)
	@NotNull
	@Column(name = "venFecha")
	@Temporal(TemporalType.DATE)
	private Date venFecha;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "venDomicilio")
	private BigDecimal venDomicilio;
	@Basic(optional = false)
	@NotNull
	@Column(name = "venSaldo")
	private BigDecimal venSaldo;
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "ventaidVenta")
	private List<Pago> pagoList;
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "ventaidVenta")
	private List<TerceroVenta> terceroVentaList;
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "ventaidVenta")
	private List<VentaLinea> ventaLineaList;
	@Basic(optional = true)
	@Column(name = "observacion")
	private String observacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "modalidad_venta")
	private ModalidaVentaEnum modalidadVenta;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_venta")
	private EstadoVentaEnum estadoVenta;

	@Column(name = "descuento")
	private BigDecimal descuento;

	@Column(name = "fecha_renovacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRenovacion;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "venta")
	private List<Cuota> cuotas = new ArrayList<Cuota>();

	
	@JoinColumn(name="tercero", referencedColumnName="idTecero")
	private Tercero tercero;
	
	
	@Transient
	private boolean seleccionado;

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 */
	public Venta() {
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param idVenta
	 */
	public Venta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param idVenta
	 * @param venFecha
	 * @param fecha
	 * @param venDomicilio
	 * @param venSaldo
	 */
	public Venta(Integer idVenta, Date venFecha, Date fecha,
			BigDecimal venDomicilio, BigDecimal venSaldo) {
		this.idVenta = idVenta;
		this.venFecha = venFecha;
		this.fecha = fecha;
		this.venDomicilio = venDomicilio;
		this.venSaldo = venSaldo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idVenta != null ? idVenta.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Venta)) {
			return false;
		}
		Venta other = (Venta) object;
		if ((this.idVenta == null && other.idVenta != null)
				|| (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Venta[ idVenta=" + idVenta
				+ " ]";
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the idVenta
	 */
	public Integer getIdVenta() {
		return idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param idVenta
	 *            the idVenta to set
	 */
	public void setIdVenta(Integer idVenta) {
		this.idVenta = idVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the venFecha
	 */
	public Date getVenFecha() {
		return venFecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venFecha
	 *            the venFecha to set
	 */
	public void setVenFecha(Date venFecha) {
		this.venFecha = venFecha;
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

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the venDomicilio
	 */
	public BigDecimal getVenDomicilio() {
		return venDomicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venDomicilio
	 *            the venDomicilio to set
	 */
	public void setVenDomicilio(BigDecimal venDomicilio) {
		this.venDomicilio = venDomicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the venSaldo
	 */
	public BigDecimal getVenSaldo() {
		return venSaldo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param venSaldo
	 *            the venSaldo to set
	 */
	public void setVenSaldo(BigDecimal venSaldo) {
		this.venSaldo = venSaldo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the pagoList
	 */
	public List<Pago> getPagoList() {
		return pagoList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param pagoList
	 *            the pagoList to set
	 */
	public void setPagoList(List<Pago> pagoList) {
		this.pagoList = pagoList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the terceroVentaList
	 */
	public List<TerceroVenta> getTerceroVentaList() {
		return terceroVentaList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param terceroVentaList
	 *            the terceroVentaList to set
	 */
	public void setTerceroVentaList(List<TerceroVenta> terceroVentaList) {
		this.terceroVentaList = terceroVentaList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the ventaLineaList
	 */
	public List<VentaLinea> getVentaLineaList() {
		return ventaLineaList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param ventaLineaList
	 *            the ventaLineaList to set
	 */
	public void setVentaLineaList(List<VentaLinea> ventaLineaList) {
		this.ventaLineaList = ventaLineaList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the modalidadVenta
	 */
	public ModalidaVentaEnum getModalidadVenta() {
		return modalidadVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param modalidadVenta
	 *            the modalidadVenta to set
	 */
	public void setModalidadVenta(ModalidaVentaEnum modalidadVenta) {
		this.modalidadVenta = modalidadVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fechaRenovacion
	 */
	public Date getFechaRenovacion() {
		return fechaRenovacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fechaRenovacion
	 *            the fechaRenovacion to set
	 */
	public void setFechaRenovacion(Date fechaRenovacion) {
		this.fechaRenovacion = fechaRenovacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the descuento
	 */
	public BigDecimal getDescuento() {
		return descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param descuento
	 *            the descuento to set
	 */
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cuotas
	 */
	public List<Cuota> getCuotas() {
		return cuotas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return
	 */
	public Tercero getFirstTercero() {
		if (terceroVentaList != null) {
			if (!terceroVentaList.isEmpty()) {
				return terceroVentaList.get(0).getTerceroidTecero();
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param seleccionado
	 *            the seleccionado to set
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the estadoVenta
	 */
	public EstadoVentaEnum getEstadoVenta() {
		return estadoVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param estadoVenta
	 *            the estadoVenta to set
	 */
	public void setEstadoVenta(EstadoVentaEnum estadoVenta) {
		this.estadoVenta = estadoVenta;
	}
	
	public String getModalidadVentaAsString(){
		return modalidadVenta.name();
		
	
	}

	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param tercero the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}
	
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}
	
}
