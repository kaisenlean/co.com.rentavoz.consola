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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

import co.com.rentavoz.logica.jpa.entidades.profile.Usuario;

/**
 * 
 * @author ejody
 */
/**
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class Tercero
* @date 4/09/2013
*
*/
@Entity
@Table(name = "tercero")

public class Tercero implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTecero")
	private Integer idTecero;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "terNombre")
	private String terNombre;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Size(max = 45)
	@Column(name = "terApellidos")
	private String terApellidos;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Basic(optional = false)
	@Column(name = "terTelefono")
	private String terTelefono;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Basic(optional = false)
	@Column(name = "terDireccion")
	private String terDireccion;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "terDocumento")
	private String terDocumento;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoTerceroEnum tipo;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero")
	private List<TerceroVenta> terceroVentaList;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero")
	private List<Roles> rolesList;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero" ,fetch=FetchType.EAGER)
	private List<SucursalTercero> sucursalTerceroList;
	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "terceroidTecero")
	private List<Plan> planList;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Transient
	private String repTerClave;

	/**
	 * co.com.rentavoz.logica.jpa.entidades
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name = "usuario", referencedColumnName = "usuar")
	@ManyToOne
	private Usuario usuario;

	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="centrope" , referencedColumnName="id")
	private Centrope centrope;
	
	
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	*/
	public Tercero() {
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idTecero
	*/
	public Tercero(Integer idTecero) {
		this.idTecero = idTecero;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idTecero
	* @param terNombre
	* @param terTelefono
	* @param terDireccion
	* @param terDocumento
	*/
	public Tercero(Integer idTecero, String terNombre, String terTelefono,
			String terDireccion, String terDocumento) {
		this.idTecero = idTecero;
		this.terNombre = terNombre;
		this.terTelefono = terTelefono;
		this.terDireccion = terDireccion;
		this.terDocumento = terDocumento;

	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public Integer getIdTecero() {
		return idTecero;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param idTecero
	*/
	public void setIdTecero(Integer idTecero) {
		this.idTecero = idTecero;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerNombre() {
		return terNombre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terNombre
	*/
	public void setTerNombre(String terNombre) {
		this.terNombre = terNombre;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerApellidos() {
		return terApellidos;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terApellidos
	*/
	public void setTerApellidos(String terApellidos) {
		this.terApellidos = terApellidos;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerTelefono() {
		return terTelefono;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terTelefono
	*/
	public void setTerTelefono(String terTelefono) {
		this.terTelefono = terTelefono;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerDireccion() {
		return terDireccion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terDireccion
	*/
	public void setTerDireccion(String terDireccion) {
		this.terDireccion = terDireccion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTerDocumento() {
		return terDocumento;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terDocumento
	*/
	public void setTerDocumento(String terDocumento) {
		this.terDocumento = terDocumento;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	@XmlTransient
	public List<TerceroVenta> getTerceroVentaList() {
		return terceroVentaList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param terceroVentaList
	*/
	public void setTerceroVentaList(List<TerceroVenta> terceroVentaList) {
		this.terceroVentaList = terceroVentaList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	@XmlTransient
	public List<Roles> getRolesList() {
		return rolesList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param rolesList
	*/
	public void setRolesList(List<Roles> rolesList) {
		this.rolesList = rolesList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
//	@XmlTransient
	public List<SucursalTercero> getSucursalTerceroList() {
		return sucursalTerceroList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param sucursalTerceroList
	*/
	public void setSucursalTerceroList(List<SucursalTercero> sucursalTerceroList) {
		this.sucursalTerceroList = sucursalTerceroList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	@XmlTransient
	public List<Plan> getPlanList() {
		return planList;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param planList
	*/
	public void setPlanList(List<Plan> planList) {
		this.planList = planList;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTecero != null ? idTecero.hashCode() : 0);
		return hash;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		if (!(object instanceof Tercero)) {
			return false;
		}
		Tercero other = (Tercero) object;
		if ((this.idTecero == null && other.idTecero != null)
				|| (this.idTecero != null && !this.idTecero
						.equals(other.idTecero))) {
			return false;
		}
		return true;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getRepTerClave() {
		return repTerClave;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param repTerClave
	*/
	public void setRepTerClave(String repTerClave) {
		this.repTerClave = repTerClave;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the tipo
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public TipoTerceroEnum getTipo() {
		return tipo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the usuario
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param usuario
	 *            the usuario to set
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param usuario
	*/
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param tipo
	 *            the tipo to set
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @param tipo
	*/
	public void setTipo(TipoTerceroEnum tipo) {
		this.tipo = tipo;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 4/09/2013
	* @return
	*/
	public String getTipoAsString() {
		if (tipo != null) {

			return tipo.name().replace("_", " ");
		} else {
			return "";
		}
	}
	
	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the centrope
	 */
	public Centrope getCentrope() {
		return centrope;
	}
	
	/**
	 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *@date 2/06/2013
	 * @param centrope the centrope to set
	 */
	public void setCentrope(Centrope centrope) {
		this.centrope = centrope;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return terNombre + " " + terApellidos + " [ " + terDocumento + " ]";
	}

}
