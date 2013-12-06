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

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e"),
		@NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa"),
		@NamedQuery(name = "Empresa.findByEmpNombre", query = "SELECT e FROM Empresa e WHERE e.empNombre = :empNombre"),
		@NamedQuery(name = "Empresa.findByEmpNit", query = "SELECT e FROM Empresa e WHERE e.empNit = :empNit"),
		@NamedQuery(name = "Empresa.findByEmpDireccion", query = "SELECT e FROM Empresa e WHERE e.empDireccion = :empDireccion"),
		@NamedQuery(name = "Empresa.findByEmpTelefono", query = "SELECT e FROM Empresa e WHERE e.empTelefono = :empTelefono"),
		@NamedQuery(name = "Empresa.findByEmpContacto", query = "SELECT e FROM Empresa e WHERE e.empContacto = :empContacto"),
		@NamedQuery(name = "Empresa.findByEmpAlias", query = "SELECT e FROM Empresa e WHERE e.empAlias = :empAlias") })
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmpresa")
	private Integer idEmpresa;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "empNombre")
	private String empNombre;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 15)
	@Column(name = "empNit")
	private String empNit;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "empDireccion")
	private String empDireccion;
	@Size(max = 45)
	@Column(name = "empTelefono")
	private String empTelefono;
	@Size(max = 45)
	@Column(name = "empContacto")
	private String empContacto;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "empAlias")
	private String empAlias;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaidEmpresa")
	private List<Convenio> convenioList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaidEmpresa")
	private List<Linea> lineaList;

	public Empresa() {
	}

	public Empresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Empresa(Integer idEmpresa, String empNombre, String empNit,
			String empDireccion, String empAlias) {
		this.idEmpresa = idEmpresa;
		this.empNombre = empNombre;
		this.empNit = empNit;
		this.empDireccion = empDireccion;
		this.empAlias = empAlias;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getEmpNombre() {
		return empNombre;
	}

	public void setEmpNombre(String empNombre) {
		this.empNombre = empNombre;
	}

	public String getEmpNit() {
		return empNit;
	}

	public void setEmpNit(String empNit) {
		this.empNit = empNit;
	}

	public String getEmpDireccion() {
		return empDireccion;
	}

	public void setEmpDireccion(String empDireccion) {
		this.empDireccion = empDireccion;
	}

	public String getEmpTelefono() {
		return empTelefono;
	}

	public void setEmpTelefono(String empTelefono) {
		this.empTelefono = empTelefono;
	}

	public String getEmpContacto() {
		return empContacto;
	}

	public void setEmpContacto(String empContacto) {
		this.empContacto = empContacto;
	}

	public String getEmpAlias() {
		return empAlias;
	}

	public void setEmpAlias(String empAlias) {
		this.empAlias = empAlias;
	}

	@XmlTransient
	public List<Convenio> getConvenioList() {
		return convenioList;
	}

	public void setConvenioList(List<Convenio> convenioList) {
		this.convenioList = convenioList;
	}

	@XmlTransient
	public List<Linea> getLineaList() {
		return lineaList;
	}

	public void setLineaList(List<Linea> lineaList) {
		this.lineaList = lineaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Empresa)) {
			return false;
		}
		Empresa other = (Empresa) object;
		if ((this.idEmpresa == null && other.idEmpresa != null)
				|| (this.idEmpresa != null && !this.idEmpresa
						.equals(other.idEmpresa))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return empNombre + " [ " + empNit + " ]";
	}

}
