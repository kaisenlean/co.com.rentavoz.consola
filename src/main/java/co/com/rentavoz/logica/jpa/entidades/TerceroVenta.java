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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "terceroventa")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "TerceroVenta.findAll", query = "SELECT t FROM TerceroVenta t"),
		@NamedQuery(name = "TerceroVenta.findByIdTerVen", query = "SELECT t FROM TerceroVenta t WHERE t.idTerVen = :idTerVen"),
		@NamedQuery(name = "TerceroVenta.findByTerVenTipo", query = "SELECT t FROM TerceroVenta t WHERE t.terVenTipo = :terVenTipo") })
public class TerceroVenta implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTerVen")
	private Integer idTerVen;
	@Basic(optional = false)
	@NotNull
	@Column(name = "terVenTipo")
	private int terVenTipo;
	@JoinColumn(name = "Venta_idVenta", referencedColumnName = "idVenta")
	@ManyToOne(optional = false)
	private Venta ventaidVenta;
	@JoinColumn(name = "Tercero_idTecero", referencedColumnName = "idTecero")
	@ManyToOne(optional = false)
	private Tercero terceroidTecero;

	public TerceroVenta() {
	}

	public TerceroVenta(Tercero tercero) {
		this.terceroidTecero = tercero;
	}

	public TerceroVenta(Tercero tercero, Venta venta) {
		this.terceroidTecero = tercero;
		this.ventaidVenta = venta;
		this.terVenTipo = 0;
	}

	public TerceroVenta(Integer idTerVen) {
		this.idTerVen = idTerVen;
	}

	public TerceroVenta(Integer idTerVen, int terVenTipo) {
		this.idTerVen = idTerVen;
		this.terVenTipo = terVenTipo;
	}

	public Integer getIdTerVen() {
		return idTerVen;
	}

	public void setIdTerVen(Integer idTerVen) {
		this.idTerVen = idTerVen;
	}

	public int getTerVenTipo() {
		return terVenTipo;
	}

	public void setTerVenTipo(int terVenTipo) {
		this.terVenTipo = terVenTipo;
	}

	public Venta getVentaidVenta() {
		return ventaidVenta;
	}

	public void setVentaidVenta(Venta ventaidVenta) {
		this.ventaidVenta = ventaidVenta;
	}

	public Tercero getTerceroidTecero() {
		return terceroidTecero;
	}

	public void setTerceroidTecero(Tercero terceroidTecero) {
		this.terceroidTecero = terceroidTecero;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTerVen != null ? idTerVen.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TerceroVenta)) {
			return false;
		}
		TerceroVenta other = (TerceroVenta) object;
		if ((this.idTerVen == null && other.idTerVen != null)
				|| (this.idTerVen != null && !this.idTerVen
						.equals(other.idTerVen))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.TerceroVenta[ idTerVen="
				+ idTerVen + " ]";
	}

}
