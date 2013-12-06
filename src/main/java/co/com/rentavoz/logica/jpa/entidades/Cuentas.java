/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "cuentas")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c"),
		@NamedQuery(name = "Cuentas.findByIdCuentas", query = "SELECT c FROM Cuentas c WHERE c.idCuentas = :idCuentas"),
		@NamedQuery(name = "Cuentas.findByCueNombre", query = "SELECT c FROM Cuentas c WHERE c.cueNombre = :cueNombre"),
		@NamedQuery(name = "Cuentas.findByCueNumero", query = "SELECT c FROM Cuentas c WHERE c.cueNumero = :cueNumero"),
		@NamedQuery(name = "Cuentas.findByCueSaldo", query = "SELECT c FROM Cuentas c WHERE c.cueSaldo = :cueSaldo") })
public class Cuentas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idCuentas")
	private Integer idCuentas;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "cueNombre")
	private String cueNombre;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "cueNumero")
	private String cueNumero;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Basic(optional = false)
	@NotNull
	@Column(name = "cueSaldo")
	private BigDecimal cueSaldo;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentasidCuentas")
	private List<Pago> pagoList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuentasidCuentas")
	private List<Gasto> gastoList;
	@JoinColumn(name = "banco", referencedColumnName = "idBanco")
	@ManyToOne
	private Banco banco;

	public Cuentas() {
	}

	public Cuentas(Integer idCuentas) {
		this.idCuentas = idCuentas;
	}

	public Cuentas(Integer idCuentas, String cueNombre, String cueNumero,
			BigDecimal cueSaldo) {
		this.idCuentas = idCuentas;
		this.cueNombre = cueNombre;
		this.cueNumero = cueNumero;
		this.cueSaldo = cueSaldo;
	}

	public Integer getIdCuentas() {
		return idCuentas;
	}

	public void setIdCuentas(Integer idCuentas) {
		this.idCuentas = idCuentas;
	}

	public String getCueNombre() {
		return cueNombre;
	}

	public void setCueNombre(String cueNombre) {
		this.cueNombre = cueNombre;
	}

	public String getCueNumero() {
		return cueNumero;
	}

	public void setCueNumero(String cueNumero) {
		this.cueNumero = cueNumero;
	}

	public BigDecimal getCueSaldo() {
		return cueSaldo;
	}

	public void setCueSaldo(BigDecimal cueSaldo) {
		this.cueSaldo = cueSaldo;
	}

	@XmlTransient
	public List<Pago> getPagoList() {
		return pagoList;
	}

	public void setPagoList(List<Pago> pagoList) {
		this.pagoList = pagoList;
	}

	@XmlTransient
	public List<Gasto> getGastoList() {
		return gastoList;
	}

	public void setGastoList(List<Gasto> gastoList) {
		this.gastoList = gastoList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCuentas != null ? idCuentas.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Cuentas)) {
			return false;
		}
		Cuentas other = (Cuentas) object;
		if ((this.idCuentas == null && other.idCuentas != null)
				|| (this.idCuentas != null && !this.idCuentas
						.equals(other.idCuentas))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Cuentas[ idCuentas="
				+ idCuentas + " ]";
	}

}
