/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "sucursalLinea")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "SucursalLinea.findAll", query = "SELECT s FROM SucursalLinea s"),
		@NamedQuery(name = "SucursalLinea.findByIdSucursalLinea", query = "SELECT s FROM SucursalLinea s WHERE s.idSucursalLinea = :idSucursalLinea"),
		@NamedQuery(name = "SucursalLinea.findByFecha", query = "SELECT s FROM SucursalLinea s WHERE s.fecha = :fecha"),
		@NamedQuery(name = "SucursalLinea.findBySucLinEstado", query = "SELECT s FROM SucursalLinea s WHERE s.sucLinEstado = :sucLinEstado"),
		@NamedQuery(name = "SucursalLinea.findBySucLinObservacion", query = "SELECT s FROM SucursalLinea s WHERE s.sucLinObservacion = :sucLinObservacion") })
public class SucursalLinea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idSucursalLinea")
	private Integer idSucursalLinea;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Basic(optional = false)
	@NotNull
	@Column(name = "sucLinEstado")
	private int sucLinEstado;
	@Size(max = 70)
	@Column(name = "sucLinObservacion")
	private String sucLinObservacion;
	@JoinColumn(name = "Sucursal_idSucursal", referencedColumnName = "idSucursal")
	@ManyToOne(optional = false)
	private Sucursal sucursalidSucursal;
	@JoinColumn(name = "Linea_idLinea", referencedColumnName = "idLinea")
	@ManyToOne(optional = false)
	private Linea lineaidLinea;

	public SucursalLinea() {
	}

	public SucursalLinea(Integer idSucursalLinea) {
		this.idSucursalLinea = idSucursalLinea;
	}

	public SucursalLinea(Integer idSucursalLinea, Date fecha, int sucLinEstado) {
		this.idSucursalLinea = idSucursalLinea;
		this.fecha = fecha;
		this.sucLinEstado = sucLinEstado;
	}

	public Integer getIdSucursalLinea() {
		return idSucursalLinea;
	}

	public void setIdSucursalLinea(Integer idSucursalLinea) {
		this.idSucursalLinea = idSucursalLinea;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getSucLinEstado() {
		return sucLinEstado;
	}

	public void setSucLinEstado(int sucLinEstado) {
		this.sucLinEstado = sucLinEstado;
	}

	public String getSucLinObservacion() {
		return sucLinObservacion;
	}

	public void setSucLinObservacion(String sucLinObservacion) {
		this.sucLinObservacion = sucLinObservacion;
	}

	public Sucursal getSucursalidSucursal() {
		return sucursalidSucursal;
	}

	public void setSucursalidSucursal(Sucursal sucursalidSucursal) {
		this.sucursalidSucursal = sucursalidSucursal;
	}

	public Linea getLineaidLinea() {
		return lineaidLinea;
	}

	public void setLineaidLinea(Linea lineaidLinea) {
		this.lineaidLinea = lineaidLinea;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idSucursalLinea != null ? idSucursalLinea.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SucursalLinea)) {
			return false;
		}
		SucursalLinea other = (SucursalLinea) object;
		if ((this.idSucursalLinea == null && other.idSucursalLinea != null)
				|| (this.idSucursalLinea != null && !this.idSucursalLinea
						.equals(other.idSucursalLinea))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.SucursalLinea[ idSucursalLinea="
				+ idSucursalLinea + " ]";
	}

}
