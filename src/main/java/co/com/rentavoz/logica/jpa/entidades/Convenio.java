/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "convenio")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Convenio.findAll", query = "SELECT c FROM Convenio c"),
		@NamedQuery(name = "Convenio.findByIdConvenio", query = "SELECT c FROM Convenio c WHERE c.idConvenio = :idConvenio"),
		@NamedQuery(name = "Convenio.findByConComision", query = "SELECT c FROM Convenio c WHERE c.conComision = :conComision"),
		@NamedQuery(name = "Convenio.findByConFechaInicio", query = "SELECT c FROM Convenio c WHERE c.conFechaInicio = :conFechaInicio"),
		@NamedQuery(name = "Convenio.findByConValorFijo", query = "SELECT c FROM Convenio c WHERE c.conValorFijo = :conValorFijo"),
		@NamedQuery(name = "Convenio.findByConEstado", query = "SELECT c FROM Convenio c WHERE c.conEstado = :conEstado") })
public class Convenio implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idConvenio")
	private Integer idConvenio;
	@Basic(optional = false)
	@NotNull
	@Column(name = "conComision")
	private short conComision;
	@Basic(optional = false)
	@NotNull
	@Column(name = "conFechaInicio")
	@Temporal(TemporalType.DATE)
	private Date conFechaInicio;
	@Basic(optional = false)
	@NotNull
	@Column(name = "conValorFijo")
	private short conValorFijo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "conEstado")
	private int conEstado;
	@JoinColumn(name = "Empresa_idEmpresa", referencedColumnName = "idEmpresa")
	@ManyToOne(optional = false)
	private Empresa empresaidEmpresa;
	@JoinColumn(name = "Plan_idPlan", referencedColumnName = "idPlan")
	@ManyToOne(optional = false)
	private Plan planidPlan;

	public Convenio() {
	}

	public Convenio(Integer idConvenio) {
		this.idConvenio = idConvenio;
	}

	public Convenio(Integer idConvenio, short conComision, Date conFechaInicio,
			short conValorFijo, int conEstado) {
		this.idConvenio = idConvenio;
		this.conComision = conComision;
		this.conFechaInicio = conFechaInicio;
		this.conValorFijo = conValorFijo;
		this.conEstado = conEstado;
	}

	public Integer getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(Integer idConvenio) {
		this.idConvenio = idConvenio;
	}

	public short getConComision() {
		return conComision;
	}

	public void setConComision(short conComision) {
		this.conComision = conComision;
	}

	public Date getConFechaInicio() {
		return conFechaInicio;
	}

	public void setConFechaInicio(Date conFechaInicio) {
		this.conFechaInicio = conFechaInicio;
	}

	public short getConValorFijo() {
		return conValorFijo;
	}

	public void setConValorFijo(short conValorFijo) {
		this.conValorFijo = conValorFijo;
	}

	public int getConEstado() {
		return conEstado;
	}

	public void setConEstado(int conEstado) {
		this.conEstado = conEstado;
	}

	public Empresa getEmpresaidEmpresa() {
		return empresaidEmpresa;
	}

	public void setEmpresaidEmpresa(Empresa empresaidEmpresa) {
		this.empresaidEmpresa = empresaidEmpresa;
	}

	public Plan getPlanidPlan() {
		return planidPlan;
	}

	public void setPlanidPlan(Plan planidPlan) {
		this.planidPlan = planidPlan;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idConvenio != null ? idConvenio.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Convenio)) {
			return false;
		}
		Convenio other = (Convenio) object;
		if ((this.idConvenio == null && other.idConvenio != null)
				|| (this.idConvenio != null && !this.idConvenio
						.equals(other.idConvenio))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Convenio[ idConvenio="
				+ idConvenio + " ]";
	}

}
