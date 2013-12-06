/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class Sucursal
* @date 7/10/2013
*
 */
@Entity
@Table(name = "sucursal")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s"),
		@NamedQuery(name = "Sucursal.findByIdSucursal", query = "SELECT s FROM Sucursal s WHERE s.idSucursal = :idSucursal"),
		@NamedQuery(name = "Sucursal.findBySucNombre", query = "SELECT s FROM Sucursal s WHERE s.sucNombre = :sucNombre"),
		@NamedQuery(name = "Sucursal.findBySucDireccion", query = "SELECT s FROM Sucursal s WHERE s.sucDireccion = :sucDireccion"),
		@NamedQuery(name = "Sucursal.findBySucTelefono", query = "SELECT s FROM Sucursal s WHERE s.sucTelefono = :sucTelefono"),
		@NamedQuery(name = "Sucursal.findBySucEstado", query = "SELECT s FROM Sucursal s WHERE s.sucEstado = :sucEstado") })
public class Sucursal implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSucursal")
	private Integer idSucursal;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "sucNombre")
	private String sucNombre;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "sucDireccion")
	private String sucDireccion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "sucTelefono")
	private String sucTelefono;
	@Basic(optional = false)
	@NotNull
	@Column(name = "sucEstado")
	private int sucEstado;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalidSucursal")
	private List<SucursalLinea> sucursalLineaList;
	@JoinColumn(name = "Ciudad_idCiudad", referencedColumnName = "idCiudad")
	@ManyToOne(optional = false)
	private Ciudad ciudadidCiudad;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalidSucursal")
	private List<SucursalSimcard> sucursalSimcardList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sucursalidSucursal")
	private List<SucursalTercero> sucursalTerceroList;

	public Sucursal() {
	}

	public Sucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public Sucursal(Integer idSucursal, String sucNombre, String sucDireccion,
			String sucTelefono, int sucEstado) {
		this.idSucursal = idSucursal;
		this.sucNombre = sucNombre;
		this.sucDireccion = sucDireccion;
		this.sucTelefono = sucTelefono;
		this.sucEstado = sucEstado;
	}

	public Integer getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getSucNombre() {
		return sucNombre;
	}

	public void setSucNombre(String sucNombre) {
		this.sucNombre = sucNombre;
	}

	public String getSucDireccion() {
		return sucDireccion;
	}

	public void setSucDireccion(String sucDireccion) {
		this.sucDireccion = sucDireccion;
	}

	public String getSucTelefono() {
		return sucTelefono;
	}

	public void setSucTelefono(String sucTelefono) {
		this.sucTelefono = sucTelefono;
	}

	public int getSucEstado() {
		return sucEstado;
	}

	public void setSucEstado(int sucEstado) {
		this.sucEstado = sucEstado;
	}

	@XmlTransient
	public List<SucursalLinea> getSucursalLineaList() {
		return sucursalLineaList;
	}

	public void setSucursalLineaList(List<SucursalLinea> sucursalLineaList) {
		this.sucursalLineaList = sucursalLineaList;
	}

	public Ciudad getCiudadidCiudad() {
		return ciudadidCiudad;
	}

	public void setCiudadidCiudad(Ciudad ciudadidCiudad) {
		this.ciudadidCiudad = ciudadidCiudad;
	}

	@XmlTransient
	public List<SucursalSimcard> getSucursalSimcardList() {
		return sucursalSimcardList;
	}

	public void setSucursalSimcardList(List<SucursalSimcard> sucursalSimcardList) {
		this.sucursalSimcardList = sucursalSimcardList;
	}

	@XmlTransient
	public List<SucursalTercero> getSucursalTerceroList() {
		return sucursalTerceroList;
	}

	public void setSucursalTerceroList(List<SucursalTercero> sucursalTerceroList) {
		this.sucursalTerceroList = sucursalTerceroList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idSucursal != null ? idSucursal.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Sucursal)) {
			return false;
		}
		Sucursal other = (Sucursal) object;
		if ((this.idSucursal == null && other.idSucursal != null)
				|| (this.idSucursal != null && !this.idSucursal
						.equals(other.idSucursal))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return sucNombre;
	}

}
