/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades.almacen;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.entidades.SucursalSimcard;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class Simcard
 * @date 24/07/2013
 * 
 */
@Entity
@Table(name = "simcard")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Simcard.findAll", query = "SELECT s FROM Simcard s"),
		@NamedQuery(name = "Simcard.findByIdSimcard", query = "SELECT s FROM Simcard s WHERE s.idSimcard = :idSimcard"),
		@NamedQuery(name = "Simcard.findBySimIccid", query = "SELECT s FROM Simcard s WHERE s.simIccid = :simIccid"),
		@NamedQuery(name = "Simcard.findBySimEstado", query = "SELECT s FROM Simcard s WHERE s.simEstado = :simEstado"),
		@NamedQuery(name = "Simcard.findByFecha", query = "SELECT s FROM Simcard s WHERE s.fecha = :fecha") })
public class Simcard implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idSimcard")
	private Integer idSimcard;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 40)
	@Column(name = "simIccid")
	private String simIccid;

	@Column(name = "simEstado")
	@Enumerated(EnumType.STRING)
	private EstadosSimcardEnum simEstado;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "simcardidSimcard")
	private List<SucursalSimcard> sucursalSimcardList;
	@OneToMany(mappedBy = "simcard")
	private List<Linea> lineas;

	@JoinColumn(name = "sucursal", referencedColumnName = "idSucursal")
	@ManyToOne
	private Sucursal sucursal;

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 */
	public Simcard() {
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param idSimcard
	 */
	public Simcard(Integer idSimcard) {
		this.idSimcard = idSimcard;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param idSimcard
	 * @param simIccid
	 * @param simEstado
	 * @param fecha
	 */
	public Simcard(Integer idSimcard, String simIccid,
			EstadosSimcardEnum simEstado, Date fecha) {
		this.idSimcard = idSimcard;
		this.simIccid = simIccid;
		this.simEstado = simEstado;
		this.fecha = fecha;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idSimcard != null ? idSimcard.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Simcard)) {
			return false;
		}
		Simcard other = (Simcard) object;
		if ((this.idSimcard == null && other.idSimcard != null)
				|| (this.idSimcard != null && !this.idSimcard
						.equals(other.idSimcard))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return simIccid;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the idSimcard
	 */
	public Integer getIdSimcard() {
		return idSimcard;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param idSimcard
	 *            the idSimcard to set
	 */
	public void setIdSimcard(Integer idSimcard) {
		this.idSimcard = idSimcard;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the simIccid
	 */
	public String getSimIccid() {
		return simIccid;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param simIccid
	 *            the simIccid to set
	 */
	public void setSimIccid(String simIccid) {
		this.simIccid = simIccid;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the simEstado
	 */
	public EstadosSimcardEnum getSimEstado() {
		return simEstado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param simEstado
	 *            the simEstado to set
	 */
	public void setSimEstado(EstadosSimcardEnum simEstado) {
		this.simEstado = simEstado;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the sucursalSimcardList
	 */
	public List<SucursalSimcard> getSucursalSimcardList() {
		return sucursalSimcardList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param sucursalSimcardList
	 *            the sucursalSimcardList to set
	 */
	public void setSucursalSimcardList(List<SucursalSimcard> sucursalSimcardList) {
		this.sucursalSimcardList = sucursalSimcardList;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the lineas
	 */
	public List<Linea> getLineas() {
		return lineas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param lineas
	 *            the lineas to set
	 */
	public void setLineas(List<Linea> lineas) {
		this.lineas = lineas;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @return
	 */
	public String getEstadoAsString() {
		return simEstado == null ? "" : simEstado.name();

	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the sucursal
	 */
	public Sucursal getSucursal() {
		return sucursal;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
