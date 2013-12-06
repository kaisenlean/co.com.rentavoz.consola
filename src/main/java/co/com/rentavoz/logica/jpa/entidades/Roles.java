/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author ejody
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
		@NamedQuery(name = "Roles.findByRolId", query = "SELECT r FROM Roles r WHERE r.rolId = :rolId") })
public class Roles implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "rolId")
	private Integer rolId;
	@JoinColumn(name = "Rol_idRol", referencedColumnName = "idRol")
	@ManyToOne(optional = false)
	private Rol rolidRol;
	@JoinColumn(name = "Tercero_idTecero", referencedColumnName = "idTecero")
	@ManyToOne(optional = false)
	private Tercero terceroidTecero;

	public Roles() {
	}

	public Roles(Integer rolId) {
		this.rolId = rolId;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public Rol getRolidRol() {
		return rolidRol;
	}

	public void setRolidRol(Rol rolidRol) {
		this.rolidRol = rolidRol;
	}

	public Tercero getTerceroidTecero() {
		return terceroidTecero;
	}

	public void setTerceroidTecero(Tercero terceroidTecero) {
		this.terceroidTecero = terceroidTecero;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (rolId != null ? rolId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Roles)) {
			return false;
		}
		Roles other = (Roles) object;
		if ((this.rolId == null && other.rolId != null)
				|| (this.rolId != null && !this.rolId.equals(other.rolId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.invte.rentavoz.logica.entidades.Roles[ rolId=" + rolId
				+ " ]";
	}

}
