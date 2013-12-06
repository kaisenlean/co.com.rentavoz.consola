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
 * @author ejody
 */
@Entity
@Table(name = "ciudad")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Ciudad.findAll", query = "SELECT c FROM Ciudad c"),
		@NamedQuery(name = "Ciudad.findByIdCiudad", query = "SELECT c FROM Ciudad c WHERE c.idCiudad = :idCiudad"),
		@NamedQuery(name = "Ciudad.findByCiuNombre", query = "SELECT c FROM Ciudad c WHERE c.ciuNombre = :ciuNombre") })
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idCiudad")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCiudad;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "ciuNombre")
	private String ciuNombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadidCiudad")
	private List<Sucursal> sucursalList;
	@JoinColumn(name = "Departamento_idDepartamento", referencedColumnName = "idDepartamento")
	@ManyToOne(optional = false)
	private Departamento departamentoidDepartamento;

	public Ciudad() {
	}

	public Ciudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Ciudad(Integer idCiudad, String ciuNombre) {
		this.idCiudad = idCiudad;
		this.ciuNombre = ciuNombre;
	}

	public Integer getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getCiuNombre() {
		return ciuNombre;
	}

	public void setCiuNombre(String ciuNombre) {
		this.ciuNombre = ciuNombre;
	}

	@XmlTransient
	public List<Sucursal> getSucursalList() {
		return sucursalList;
	}

	public void setSucursalList(List<Sucursal> sucursalList) {
		this.sucursalList = sucursalList;
	}

	public Departamento getDepartamentoidDepartamento() {
		return departamentoidDepartamento;
	}

	public void setDepartamentoidDepartamento(
			Departamento departamentoidDepartamento) {
		this.departamentoidDepartamento = departamentoidDepartamento;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCiudad != null ? idCiudad.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Ciudad)) {
			return false;
		}
		Ciudad other = (Ciudad) object;
		if ((this.idCiudad == null && other.idCiudad != null)
				|| (this.idCiudad != null && !this.idCiudad
						.equals(other.idCiudad))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Ciudad[ idCiudad="
				+ idCiudad + " ]";
	}

}
