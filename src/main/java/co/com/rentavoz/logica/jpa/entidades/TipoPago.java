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
@Table(name = "tipopago")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "TipoPago.findAll", query = "SELECT t FROM TipoPago t"),
		@NamedQuery(name = "TipoPago.findByIdTipoPago", query = "SELECT t FROM TipoPago t WHERE t.idTipoPago = :idTipoPago"),
		@NamedQuery(name = "TipoPago.findByTPagoNombre", query = "SELECT t FROM TipoPago t WHERE t.tPagoNombre = :tPagoNombre"),
		@NamedQuery(name = "TipoPago.findByTPagoDescripcion", query = "SELECT t FROM TipoPago t WHERE t.tPagoDescripcion = :tPagoDescripcion") })
public class TipoPago implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idTipoPago")
	private Integer idTipoPago;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "tPagoNombre")
	private String tPagoNombre;
	@Size(max = 70)
	@Column(name = "tPagoDescripcion")
	private String tPagoDescripcion;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPagoidTipoPago")
	private List<Pago> pagoList;

	public TipoPago() {
	}

	public TipoPago(Integer idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	public TipoPago(Integer idTipoPago, String tPagoNombre) {
		this.idTipoPago = idTipoPago;
		this.tPagoNombre = tPagoNombre;
	}

	public Integer getIdTipoPago() {
		return idTipoPago;
	}

	public void setIdTipoPago(Integer idTipoPago) {
		this.idTipoPago = idTipoPago;
	}

	public String getTPagoNombre() {
		return tPagoNombre;
	}

	public void setTPagoNombre(String tPagoNombre) {
		this.tPagoNombre = tPagoNombre;
	}

	public String getTPagoDescripcion() {
		return tPagoDescripcion;
	}

	public void setTPagoDescripcion(String tPagoDescripcion) {
		this.tPagoDescripcion = tPagoDescripcion;
	}

	@XmlTransient
	public List<Pago> getPagoList() {
		return pagoList;
	}

	public void setPagoList(List<Pago> pagoList) {
		this.pagoList = pagoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTipoPago != null ? idTipoPago.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TipoPago)) {
			return false;
		}
		TipoPago other = (TipoPago) object;
		if ((this.idTipoPago == null && other.idTipoPago != null)
				|| (this.idTipoPago != null && !this.idTipoPago
						.equals(other.idTipoPago))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.TipoPago[ idTipoPago="
				+ idTipoPago + " ]";
	}

}
