/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades.almacen;

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

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "ventalinea")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "VentaLinea.findAll", query = "SELECT v FROM VentaLinea v"),
		@NamedQuery(name = "VentaLinea.findByIdVentaLinea", query = "SELECT v FROM VentaLinea v WHERE v.idVentaLinea = :idVentaLinea"),
		@NamedQuery(name = "VentaLinea.findByVentLinPrecio", query = "SELECT v FROM VentaLinea v WHERE v.ventLinPrecio = :ventLinPrecio"),
		@NamedQuery(name = "VentaLinea.findByVentLinTipo", query = "SELECT v FROM VentaLinea v WHERE v.ventLinTipo = :ventLinTipo"),
		@NamedQuery(name = "VentaLinea.findByVentLinDeposito", query = "SELECT v FROM VentaLinea v WHERE v.ventLinDeposito = :ventLinDeposito") })
public class VentaLinea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVentaLinea")
	private Integer idVentaLinea;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "ventLinPrecio")
	private BigDecimal ventLinPrecio;
	@Basic(optional = false)
	@NotNull
	@Column(name = "ventLinTipo")
	private int ventLinTipo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "ventLinDeposito")
	private BigDecimal ventLinDeposito;
	@JoinColumn(name = "Linea_idLinea", referencedColumnName = "idLinea")
	@ManyToOne(optional = false)
	private Linea lineaidLinea;
	@JoinColumn(name = "Venta_idVenta", referencedColumnName = "idVenta")
	@ManyToOne(optional = false)
	private Venta ventaidVenta;

	@Column(name = "domicilio")
	private Double domicilio;

	@Column(name = "fecha_renovacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRenovacion;

	@Column(name = "descuento")
	private Double descuento;

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 */
	public VentaLinea() {
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param idVentaLinea
	 */
	public VentaLinea(Integer idVentaLinea) {
		this.idVentaLinea = idVentaLinea;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param idVentaLinea
	 * @param ventLinPrecio
	 * @param ventLinTipo
	 * @param ventLinDeposito
	 */
	public VentaLinea(Integer idVentaLinea, BigDecimal ventLinPrecio,
			int ventLinTipo, BigDecimal ventLinDeposito) {
		this.idVentaLinea = idVentaLinea;
		this.ventLinPrecio = ventLinPrecio;
		this.ventLinTipo = ventLinTipo;
		this.ventLinDeposito = ventLinDeposito;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idVentaLinea != null ? idVentaLinea.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof VentaLinea)) {
			return false;
		}
		VentaLinea other = (VentaLinea) object;
		if ((this.idVentaLinea == null && other.idVentaLinea != null)
				|| (this.idVentaLinea != null && !this.idVentaLinea
						.equals(other.idVentaLinea))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.VentaLinea[ idVentaLinea="
				+ idVentaLinea + " ]";
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the idVentaLinea
	 */
	public Integer getIdVentaLinea() {
		return idVentaLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param idVentaLinea
	 *            the idVentaLinea to set
	 */
	public void setIdVentaLinea(Integer idVentaLinea) {
		this.idVentaLinea = idVentaLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the ventLinPrecio
	 */
	public BigDecimal getVentLinPrecio() {
		return ventLinPrecio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param ventLinPrecio
	 *            the ventLinPrecio to set
	 */
	public void setVentLinPrecio(BigDecimal ventLinPrecio) {
		this.ventLinPrecio = ventLinPrecio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the ventLinTipo
	 */
	public int getVentLinTipo() {
		return ventLinTipo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param ventLinTipo
	 *            the ventLinTipo to set
	 */
	public void setVentLinTipo(int ventLinTipo) {
		this.ventLinTipo = ventLinTipo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the ventLinDeposito
	 */
	public BigDecimal getVentLinDeposito() {
		return ventLinDeposito;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param ventLinDeposito
	 *            the ventLinDeposito to set
	 */
	public void setVentLinDeposito(BigDecimal ventLinDeposito) {
		this.ventLinDeposito = ventLinDeposito;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the lineaidLinea
	 */
	public Linea getLineaidLinea() {
		return lineaidLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param lineaidLinea
	 *            the lineaidLinea to set
	 */
	public void setLineaidLinea(Linea lineaidLinea) {
		this.lineaidLinea = lineaidLinea;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the ventaidVenta
	 */
	public Venta getVentaidVenta() {
		return ventaidVenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param ventaidVenta
	 *            the ventaidVenta to set
	 */
	public void setVentaidVenta(Venta ventaidVenta) {
		this.ventaidVenta = ventaidVenta;
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
	 * @return the domicilio
	 */
	public Double getDomicilio() {
		return domicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(Double domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the descuento
	 */
	public Double getDescuento() {
		return descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param descuento
	 *            the descuento to set
	 */
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

}
