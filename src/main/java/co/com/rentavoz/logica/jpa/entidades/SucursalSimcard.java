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

import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "sucursalSimcard")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "SucursalSimcard.findAll", query = "SELECT s FROM SucursalSimcard s"),
		@NamedQuery(name = "SucursalSimcard.findByIdSucSim", query = "SELECT s FROM SucursalSimcard s WHERE s.idSucSim = :idSucSim"),
		@NamedQuery(name = "SucursalSimcard.findByFecha", query = "SELECT s FROM SucursalSimcard s WHERE s.fecha = :fecha"),
		@NamedQuery(name = "SucursalSimcard.findBySucSimObservacion", query = "SELECT s FROM SucursalSimcard s WHERE s.sucSimObservacion = :sucSimObservacion"),
		@NamedQuery(name = "SucursalSimcard.findBySucSimEstado", query = "SELECT s FROM SucursalSimcard s WHERE s.sucSimEstado = :sucSimEstado") })
public class SucursalSimcard implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idSucSim")
	private Integer idSucSim;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Size(max = 70)
	@Column(name = "sucSimObservacion")
	private String sucSimObservacion;
	@Basic(optional = false)
	@NotNull
	@Column(name = "sucSimEstado")
	private int sucSimEstado;
	@JoinColumn(name = "Simcard_idSimcard", referencedColumnName = "idSimcard")
	@ManyToOne(optional = false)
	private Simcard simcardidSimcard;
	@JoinColumn(name = "Sucursal_idSucursal", referencedColumnName = "idSucursal")
	@ManyToOne(optional = false)
	private Sucursal sucursalidSucursal;

	public SucursalSimcard() {
	}

	public SucursalSimcard(Integer idSucSim) {
		this.idSucSim = idSucSim;
	}

	public SucursalSimcard(Integer idSucSim, Date fecha, int sucSimEstado) {
		this.idSucSim = idSucSim;
		this.fecha = fecha;
		this.sucSimEstado = sucSimEstado;
	}

	public Integer getIdSucSim() {
		return idSucSim;
	}

	public void setIdSucSim(Integer idSucSim) {
		this.idSucSim = idSucSim;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSucSimObservacion() {
		return sucSimObservacion;
	}

	public void setSucSimObservacion(String sucSimObservacion) {
		this.sucSimObservacion = sucSimObservacion;
	}

	public int getSucSimEstado() {
		return sucSimEstado;
	}

	public void setSucSimEstado(int sucSimEstado) {
		this.sucSimEstado = sucSimEstado;
	}

	public Simcard getSimcardidSimcard() {
		return simcardidSimcard;
	}

	public void setSimcardidSimcard(Simcard simcardidSimcard) {
		this.simcardidSimcard = simcardidSimcard;
	}

	public Sucursal getSucursalidSucursal() {
		return sucursalidSucursal;
	}

	public void setSucursalidSucursal(Sucursal sucursalidSucursal) {
		this.sucursalidSucursal = sucursalidSucursal;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idSucSim != null ? idSucSim.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SucursalSimcard)) {
			return false;
		}
		SucursalSimcard other = (SucursalSimcard) object;
		if ((this.idSucSim == null && other.idSucSim != null)
				|| (this.idSucSim != null && !this.idSucSim
						.equals(other.idSucSim))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.SucursalSimcard[ idSucSim="
				+ idSucSim + " ]";
	}

}
