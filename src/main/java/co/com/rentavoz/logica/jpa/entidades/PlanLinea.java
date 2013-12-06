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

import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "planlinea")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "PlanLinea.findAll", query = "SELECT p FROM PlanLinea p"),
		@NamedQuery(name = "PlanLinea.findByIdPlanLinea", query = "SELECT p FROM PlanLinea p WHERE p.idPlanLinea = :idPlanLinea"),
		@NamedQuery(name = "PlanLinea.findByFecha", query = "SELECT p FROM PlanLinea p WHERE p.fecha = :fecha"),
		@NamedQuery(name = "PlanLinea.findByPlaEstado", query = "SELECT p FROM PlanLinea p WHERE p.plaEstado = :plaEstado") })
public class PlanLinea implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idPlanLinea")
	private Integer idPlanLinea;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaEstado")
	private int plaEstado;
	@JoinColumn(name = "Linea_idLinea", referencedColumnName = "idLinea")
	@ManyToOne(optional = false)
	private Linea lineaidLinea;
	@JoinColumn(name = "Plan_idPlan", referencedColumnName = "idPlan")
	@ManyToOne(optional = false)
	private Plan planidPlan;

	public PlanLinea() {
	}

	public PlanLinea(Integer idPlanLinea) {
		this.idPlanLinea = idPlanLinea;
	}

	public PlanLinea(Integer idPlanLinea, Date fecha, int plaEstado) {
		this.idPlanLinea = idPlanLinea;
		this.fecha = fecha;
		this.plaEstado = plaEstado;
	}

	public Integer getIdPlanLinea() {
		return idPlanLinea;
	}

	public void setIdPlanLinea(Integer idPlanLinea) {
		this.idPlanLinea = idPlanLinea;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getPlaEstado() {
		return plaEstado;
	}

	public void setPlaEstado(int plaEstado) {
		this.plaEstado = plaEstado;
	}

	public Linea getLineaidLinea() {
		return lineaidLinea;
	}

	public void setLineaidLinea(Linea lineaidLinea) {
		this.lineaidLinea = lineaidLinea;
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
		hash += (idPlanLinea != null ? idPlanLinea.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof PlanLinea)) {
			return false;
		}
		PlanLinea other = (PlanLinea) object;
		if ((this.idPlanLinea == null && other.idPlanLinea != null)
				|| (this.idPlanLinea != null && !this.idPlanLinea
						.equals(other.idPlanLinea))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.PlanLinea[ idPlanLinea="
				+ idPlanLinea + " ]";
	}

}
