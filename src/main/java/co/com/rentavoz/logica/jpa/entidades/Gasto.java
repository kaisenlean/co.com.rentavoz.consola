/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "gasto")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Gasto.findAll", query = "SELECT g FROM Gasto g"),
		@NamedQuery(name = "Gasto.findByIdGasto", query = "SELECT g FROM Gasto g WHERE g.idGasto = :idGasto"),
		@NamedQuery(name = "Gasto.findByGasValor", query = "SELECT g FROM Gasto g WHERE g.gasValor = :gasValor"),
		@NamedQuery(name = "Gasto.findByGasFecha", query = "SELECT g FROM Gasto g WHERE g.gasFecha = :gasFecha"),
		@NamedQuery(name = "Gasto.findByFecha", query = "SELECT g FROM Gasto g WHERE g.fecha = :fecha"),
		@NamedQuery(name = "Gasto.findByGasObservacion", query = "SELECT g FROM Gasto g WHERE g.gasObservacion = :gasObservacion") })
public class Gasto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idGasto")
	private Integer idGasto;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "gasValor")
	private BigDecimal gasValor;
	@Basic(optional = false)
	@NotNull
	@Column(name = "gasFecha")
	@Temporal(TemporalType.DATE)
	private Date gasFecha;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Size(max = 70)
	@Column(name = "gasObservacion")
	private String gasObservacion;
	@JoinColumn(name = "TipoGasto_idTipoGasto", referencedColumnName = "idTipoGasto")
	@ManyToOne(optional = false)
	private TipoGasto tipoGastoidTipoGasto;
	@JoinColumn(name = "Cuentas_idCuentas", referencedColumnName = "idCuentas")
	@ManyToOne(optional = false)
	private Cuentas cuentasidCuentas;

	public Gasto() {
	}

	public Gasto(Integer idGasto) {
		this.idGasto = idGasto;
	}

	public Gasto(Integer idGasto, BigDecimal gasValor, Date gasFecha, Date fecha) {
		this.idGasto = idGasto;
		this.gasValor = gasValor;
		this.gasFecha = gasFecha;
		this.fecha = fecha;
	}

	public Integer getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Integer idGasto) {
		this.idGasto = idGasto;
	}

	public BigDecimal getGasValor() {
		return gasValor;
	}

	public void setGasValor(BigDecimal gasValor) {
		this.gasValor = gasValor;
	}

	public Date getGasFecha() {
		return gasFecha;
	}

	public void setGasFecha(Date gasFecha) {
		this.gasFecha = gasFecha;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getGasObservacion() {
		return gasObservacion;
	}

	public void setGasObservacion(String gasObservacion) {
		this.gasObservacion = gasObservacion;
	}

	public TipoGasto getTipoGastoidTipoGasto() {
		return tipoGastoidTipoGasto;
	}

	public void setTipoGastoidTipoGasto(TipoGasto tipoGastoidTipoGasto) {
		this.tipoGastoidTipoGasto = tipoGastoidTipoGasto;
	}

	public Cuentas getCuentasidCuentas() {
		return cuentasidCuentas;
	}

	public void setCuentasidCuentas(Cuentas cuentasidCuentas) {
		this.cuentasidCuentas = cuentasidCuentas;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idGasto != null ? idGasto.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Gasto)) {
			return false;
		}
		Gasto other = (Gasto) object;
		if ((this.idGasto == null && other.idGasto != null)
				|| (this.idGasto != null && !this.idGasto.equals(other.idGasto))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Gasto[ idGasto=" + idGasto
				+ " ]";
	}

}
