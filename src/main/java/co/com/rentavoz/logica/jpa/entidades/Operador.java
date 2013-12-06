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
@Table(name = "operador")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Operador.findAll", query = "SELECT o FROM Operador o"),
		@NamedQuery(name = "Operador.findByIdOperador", query = "SELECT o FROM Operador o WHERE o.idOperador = :idOperador"),
		@NamedQuery(name = "Operador.findByOpeNombre", query = "SELECT o FROM Operador o WHERE o.opeNombre = :opeNombre") })
public class Operador implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idOperador")
	private Integer idOperador;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "opeNombre")
	private String opeNombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "operadoridOperador")
	private List<Plan> planList;

	public Operador() {
	}

	public Operador(Integer idOperador) {
		this.idOperador = idOperador;
	}

	public Operador(Integer idOperador, String opeNombre) {
		this.idOperador = idOperador;
		this.opeNombre = opeNombre;
	}

	public Integer getIdOperador() {
		return idOperador;
	}

	public void setIdOperador(Integer idOperador) {
		this.idOperador = idOperador;
	}

	public String getOpeNombre() {
		return opeNombre;
	}

	public void setOpeNombre(String opeNombre) {
		this.opeNombre = opeNombre;
	}

	@XmlTransient
	public List<Plan> getPlanList() {
		return planList;
	}

	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idOperador != null ? idOperador.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Operador)) {
			return false;
		}
		Operador other = (Operador) object;
		if ((this.idOperador == null && other.idOperador != null)
				|| (this.idOperador != null && !this.idOperador
						.equals(other.idOperador))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Operador[ idOperador="
				+ idOperador + " ]";
	}

}
