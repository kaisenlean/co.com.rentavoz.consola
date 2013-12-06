/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
		@NamedQuery(name = "Pago.findByIdPago", query = "SELECT p FROM Pago p WHERE p.idPago = :idPago"),
		@NamedQuery(name = "Pago.findByFecha", query = "SELECT p FROM Pago p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "Pago.findByPagValor", query = "SELECT p FROM Pago p WHERE p.pagValor = :pagValor"),
		@NamedQuery(name = "Pago.findByPagFechaProx", query = "SELECT p FROM Pago p WHERE p.pagFechaProx = :pagFechaProx") })
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPago")
	private Integer idPago;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "pagValor")
	private BigDecimal pagValor;
	@Column(name = "pagFechaProx")
	@Temporal(TemporalType.DATE)
	private Date pagFechaProx;
	@JoinColumn(name = "TipoPago_idTipoPago", referencedColumnName = "idTipoPago")
	@ManyToOne(optional = false)
	private TipoPago tipoPagoidTipoPago;
	@JoinColumn(name = "Cuentas_idCuentas", referencedColumnName = "idCuentas")
	@ManyToOne
	private Cuentas cuentasidCuentas;
	@JoinColumn(name = "Venta_idVenta", referencedColumnName = "idVenta")
	@ManyToOne(optional = false)
	private Venta ventaidVenta;

	public Pago() {
	}

	public Pago(Integer idPago) {
		this.idPago = idPago;
	}

	public Pago(Integer idPago, Date fecha, BigDecimal pagValor) {
		this.idPago = idPago;
		this.fecha = fecha;
		this.pagValor = pagValor;
	}

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getPagValor() {
		return pagValor;
	}

	public void setPagValor(BigDecimal pagValor) {
		this.pagValor = pagValor;
	}

	public Date getPagFechaProx() {
		return pagFechaProx;
	}

	public void setPagFechaProx(Date pagFechaProx) {
		this.pagFechaProx = pagFechaProx;
	}

	public TipoPago getTipoPagoidTipoPago() {
		return tipoPagoidTipoPago;
	}

	public void setTipoPagoidTipoPago(TipoPago tipoPagoidTipoPago) {
		this.tipoPagoidTipoPago = tipoPagoidTipoPago;
	}

	public Cuentas getCuentasidCuentas() {
		return cuentasidCuentas;
	}

	public void setCuentasidCuentas(Cuentas cuentasidCuentas) {
		this.cuentasidCuentas = cuentasidCuentas;
	}

	public Venta getVentaidVenta() {
		return ventaidVenta;
	}

	public void setVentaidVenta(Venta ventaidVenta) {
		this.ventaidVenta = ventaidVenta;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPago != null ? idPago.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Pago)) {
			return false;
		}
		Pago other = (Pago) object;
		if ((this.idPago == null && other.idPago != null)
				|| (this.idPago != null && !this.idPago.equals(other.idPago))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Pago[ idPago=" + idPago
				+ " ]";
	}

}
