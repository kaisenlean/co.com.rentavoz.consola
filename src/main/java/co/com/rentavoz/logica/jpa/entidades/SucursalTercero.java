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

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "sucursaltercero")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "SucursalTercero.findAll", query = "SELECT s FROM SucursalTercero s"),
		@NamedQuery(name = "SucursalTercero.findByIdSucursalTercero", query = "SELECT s FROM SucursalTercero s WHERE s.idSucursalTercero = :idSucursalTercero"),
		@NamedQuery(name = "SucursalTercero.findBySucTerEstado", query = "SELECT s FROM SucursalTercero s WHERE s.sucTerEstado = :sucTerEstado") })
public class SucursalTercero implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idSucursalTercero")
	private Integer idSucursalTercero;
	@Basic(optional = false)
	@NotNull
	@Column(name = "sucTerEstado")
	private int sucTerEstado;
	@JoinColumn(name = "Tercero_idTecero", referencedColumnName = "idTecero")
	@ManyToOne(optional = false)
	private Tercero terceroidTecero;
	@JoinColumn(name = "Sucursal_idSucursal", referencedColumnName = "idSucursal")
	@ManyToOne(optional = false)
	private Sucursal sucursalidSucursal;

	public SucursalTercero() {
	}

	public SucursalTercero(Integer idSucursalTercero) {
		this.idSucursalTercero = idSucursalTercero;
	}

	public SucursalTercero(Integer idSucursalTercero, int sucTerEstado) {
		this.idSucursalTercero = idSucursalTercero;
		this.sucTerEstado = sucTerEstado;
	}

	public Integer getIdSucursalTercero() {
		return idSucursalTercero;
	}

	public void setIdSucursalTercero(Integer idSucursalTercero) {
		this.idSucursalTercero = idSucursalTercero;
	}

	public int getSucTerEstado() {
		return sucTerEstado;
	}

	public void setSucTerEstado(int sucTerEstado) {
		this.sucTerEstado = sucTerEstado;
	}

	public Tercero getTerceroidTecero() {
		return terceroidTecero;
	}

	public void setTerceroidTecero(Tercero terceroidTecero) {
		this.terceroidTecero = terceroidTecero;
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
		hash += (idSucursalTercero != null ? idSucursalTercero.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SucursalTercero)) {
			return false;
		}
		SucursalTercero other = (SucursalTercero) object;
		if ((this.idSucursalTercero == null && other.idSucursalTercero != null)
				|| (this.idSucursalTercero != null && !this.idSucursalTercero
						.equals(other.idSucursalTercero))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.SucursalTercero[ idSucursalTercero="
				+ idSucursalTercero + " ]";
	}

}
