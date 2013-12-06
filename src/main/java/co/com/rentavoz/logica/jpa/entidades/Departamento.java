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
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
		@NamedQuery(name = "Departamento.findByIdDepartamento", query = "SELECT d FROM Departamento d WHERE d.idDepartamento = :idDepartamento"),
		@NamedQuery(name = "Departamento.findByDepNombre", query = "SELECT d FROM Departamento d WHERE d.depNombre = :depNombre") })
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "idDepartamento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDepartamento;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "depNombre")
	private String depNombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "departamentoidDepartamento")
	private List<Ciudad> ciudadList;

	public Departamento() {
	}

	public Departamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Departamento(Integer idDepartamento, String depNombre) {
		this.idDepartamento = idDepartamento;
		this.depNombre = depNombre;
	}

	public Integer getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getDepNombre() {
		return depNombre;
	}

	public void setDepNombre(String depNombre) {
		this.depNombre = depNombre;
	}

	@XmlTransient
	public List<Ciudad> getCiudadList() {
		return ciudadList;
	}

	public void setCiudadList(List<Ciudad> ciudadList) {
		this.ciudadList = ciudadList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Departamento)) {
			return false;
		}
		Departamento other = (Departamento) object;
		if ((this.idDepartamento == null && other.idDepartamento != null)
				|| (this.idDepartamento != null && !this.idDepartamento
						.equals(other.idDepartamento))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Departamento[ idDepartamento="
				+ idDepartamento + " ]";
	}

}
