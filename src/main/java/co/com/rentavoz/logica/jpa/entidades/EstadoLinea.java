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
@Table(name = "estadoLinea")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "EstadoLinea.findAll", query = "SELECT e FROM EstadoLinea e"),
		@NamedQuery(name = "EstadoLinea.findByIdEstadoLinea", query = "SELECT e FROM EstadoLinea e WHERE e.idEstadoLinea = :idEstadoLinea"),
		@NamedQuery(name = "EstadoLinea.findByEstLinNombre", query = "SELECT e FROM EstadoLinea e WHERE e.estLinNombre = :estLinNombre") })
public class EstadoLinea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idEstadoLinea")
	private Integer idEstadoLinea;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "estLinNombre")
	private String estLinNombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoLineaidEstadoLinea")
	private List<Linea> lineaList;

	public EstadoLinea() {
	}

	public EstadoLinea(Integer idEstadoLinea) {
		this.idEstadoLinea = idEstadoLinea;
	}

	public EstadoLinea(Integer idEstadoLinea, String estLinNombre) {
		this.idEstadoLinea = idEstadoLinea;
		this.estLinNombre = estLinNombre;
	}

	public Integer getIdEstadoLinea() {
		return idEstadoLinea;
	}

	public void setIdEstadoLinea(Integer idEstadoLinea) {
		this.idEstadoLinea = idEstadoLinea;
	}

	public String getEstLinNombre() {
		return estLinNombre;
	}

	public void setEstLinNombre(String estLinNombre) {
		this.estLinNombre = estLinNombre;
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
		hash += (idEstadoLinea != null ? idEstadoLinea.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EstadoLinea)) {
			return false;
		}
		EstadoLinea other = (EstadoLinea) object;
		if ((this.idEstadoLinea == null && other.idEstadoLinea != null)
				|| (this.idEstadoLinea != null && !this.idEstadoLinea
						.equals(other.idEstadoLinea))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.EstadoLinea[ idEstadoLinea="
				+ idEstadoLinea + " ]";
	}

}
