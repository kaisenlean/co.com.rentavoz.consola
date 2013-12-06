/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "plan")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p"),
		@NamedQuery(name = "Plan.findByIdPlan", query = "SELECT p FROM Plan p WHERE p.idPlan = :idPlan"),
		@NamedQuery(name = "Plan.findByPlaNombre", query = "SELECT p FROM Plan p WHERE p.plaNombre = :plaNombre"),
		@NamedQuery(name = "Plan.findByPlaCantidadMinutos", query = "SELECT p FROM Plan p WHERE p.plaCantidadMinutos = :plaCantidadMinutos"),
		@NamedQuery(name = "Plan.findByPlaCostoMinuto", query = "SELECT p FROM Plan p WHERE p.plaCostoMinuto = :plaCostoMinuto"),
		@NamedQuery(name = "Plan.findByPlaCostoMin", query = "SELECT p FROM Plan p WHERE p.plaCostoMin = :plaCostoMin"),
		@NamedQuery(name = "Plan.findByPlaCostoMax", query = "SELECT p FROM Plan p WHERE p.plaCostoMax = :plaCostoMax"),
		@NamedQuery(name = "Plan.findByPlaFechaVenc", query = "SELECT p FROM Plan p WHERE p.plaFechaVenc = :plaFechaVenc"),
		@NamedQuery(name = "Plan.findByFecha", query = "SELECT p FROM Plan p WHERE p.fecha = :fecha") })
public class Plan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPlan")
	private Integer idPlan;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "plaNombre")
	private String plaNombre;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCantidadMinutos")
	private int plaCantidadMinutos;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCostoMinuto")
	private int plaCostoMinuto;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCostoMin")
	private int plaCostoMin;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaCostoMax")
	private int plaCostoMax;
	@Basic(optional = false)
	@NotNull
	@Column(name = "plaFechaVenc")
	@Temporal(TemporalType.DATE)
	private Date plaFechaVenc;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "planidPlan")
	private List<Convenio> convenioList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "planidPlan")
	private List<PlanLinea> planLineaList;
	@JoinColumn(name = "Tercero_idTecero", referencedColumnName = "idTecero")
	@ManyToOne(optional = false)
	private Tercero terceroidTecero;
	@JoinColumn(name = "Operador_idOperador", referencedColumnName = "idOperador")
	@ManyToOne(optional = false)
	private Operador operadoridOperador;

	public Plan() {
	}

	public Plan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public Plan(Integer idPlan, String plaNombre, int plaCantidadMinutos,
			int plaCostoMinuto, int plaCostoMin, int plaCostoMax,
			Date plaFechaVenc, Date fecha) {
		this.idPlan = idPlan;
		this.plaNombre = plaNombre;
		this.plaCantidadMinutos = plaCantidadMinutos;
		this.plaCostoMinuto = plaCostoMinuto;
		this.plaCostoMin = plaCostoMin;
		this.plaCostoMax = plaCostoMax;
		this.plaFechaVenc = plaFechaVenc;
		this.fecha = fecha;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}

	public String getPlaNombre() {
		return plaNombre;
	}

	public void setPlaNombre(String plaNombre) {
		this.plaNombre = plaNombre;
	}

	public int getPlaCantidadMinutos() {
		return plaCantidadMinutos;
	}

	public void setPlaCantidadMinutos(int plaCantidadMinutos) {
		this.plaCantidadMinutos = plaCantidadMinutos;
	}

	public int getPlaCostoMinuto() {
		return plaCostoMinuto;
	}

	public void setPlaCostoMinuto(int plaCostoMinuto) {
		this.plaCostoMinuto = plaCostoMinuto;
	}

	public int getPlaCostoMin() {
		return plaCostoMin;
	}

	public void setPlaCostoMin(int plaCostoMin) {
		this.plaCostoMin = plaCostoMin;
	}

	public int getPlaCostoMax() {
		return plaCostoMax;
	}

	public void setPlaCostoMax(int plaCostoMax) {
		this.plaCostoMax = plaCostoMax;
	}

	public Date getPlaFechaVenc() {
		return plaFechaVenc;
	}

	public void setPlaFechaVenc(Date plaFechaVenc) {
		this.plaFechaVenc = plaFechaVenc;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlTransient
	public List<Convenio> getConvenioList() {
		return convenioList;
	}

	public void setConvenioList(List<Convenio> convenioList) {
		this.convenioList = convenioList;
	}

	@XmlTransient
	public List<PlanLinea> getPlanLineaList() {
		return planLineaList;
	}

	public void setPlanLineaList(List<PlanLinea> planLineaList) {
		this.planLineaList = planLineaList;
	}

	public Tercero getTerceroidTecero() {
		return terceroidTecero;
	}

	public void setTerceroidTecero(Tercero terceroidTecero) {
		this.terceroidTecero = terceroidTecero;
	}

	public Operador getOperadoridOperador() {
		return operadoridOperador;
	}

	public void setOperadoridOperador(Operador operadoridOperador) {
		this.operadoridOperador = operadoridOperador;
	}

	public String getVencimiento() {
		return new SimpleDateFormat("dd/MM/yyyy").format(plaFechaVenc)
				.toString();

	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idPlan != null ? idPlan.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Plan)) {
			return false;
		}
		Plan other = (Plan) object;
		if ((this.idPlan == null && other.idPlan != null)
				|| (this.idPlan != null && !this.idPlan.equals(other.idPlan))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return plaNombre;
	}

}
