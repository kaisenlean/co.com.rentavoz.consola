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
@Table(name = "tipogasto")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "TipoGasto.findAll", query = "SELECT t FROM TipoGasto t"),
		@NamedQuery(name = "TipoGasto.findByIdTipoGasto", query = "SELECT t FROM TipoGasto t WHERE t.idTipoGasto = :idTipoGasto"),
		@NamedQuery(name = "TipoGasto.findByTGastoNombre", query = "SELECT t FROM TipoGasto t WHERE t.tGastoNombre = :tGastoNombre"),
		@NamedQuery(name = "TipoGasto.findByTGastoDescripcion", query = "SELECT t FROM TipoGasto t WHERE t.tGastoDescripcion = :tGastoDescripcion") })
public class TipoGasto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idTipoGasto")
	private Integer idTipoGasto;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "tGastoNombre")
	private String tGastoNombre;
	@Size(max = 70)
	@Column(name = "tGastoDescripcion")
	private String tGastoDescripcion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoGastoidTipoGasto")
	private List<Gasto> gastoList;

	public TipoGasto() {
	}

	public TipoGasto(Integer idTipoGasto) {
		this.idTipoGasto = idTipoGasto;
	}

	public TipoGasto(Integer idTipoGasto, String tGastoNombre) {
		this.idTipoGasto = idTipoGasto;
		this.tGastoNombre = tGastoNombre;
	}

	public Integer getIdTipoGasto() {
		return idTipoGasto;
	}

	public void setIdTipoGasto(Integer idTipoGasto) {
		this.idTipoGasto = idTipoGasto;
	}

	public String getTGastoNombre() {
		return tGastoNombre;
	}

	public void setTGastoNombre(String tGastoNombre) {
		this.tGastoNombre = tGastoNombre;
	}

	public String getTGastoDescripcion() {
		return tGastoDescripcion;
	}

	public void setTGastoDescripcion(String tGastoDescripcion) {
		this.tGastoDescripcion = tGastoDescripcion;
	}

	@XmlTransient
	public List<Gasto> getGastoList() {
		return gastoList;
	}

	public void setGastoList(List<Gasto> gastoList) {
		this.gastoList = gastoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipoGasto != null ? idTipoGasto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoGasto)) {
			return false;
		}
		TipoGasto other = (TipoGasto) object;
		if ((this.idTipoGasto == null && other.idTipoGasto != null)
				|| (this.idTipoGasto != null && !this.idTipoGasto
						.equals(other.idTipoGasto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.TipoGasto[ idTipoGasto="
				+ idTipoGasto + " ]";
	}

}
