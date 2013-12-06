package co.com.rentavoz.logica.jpa.entidades.almacen;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the reposiciondll database table.
 * 
 */
@Entity
@Table(name = "reposiciondll")
@NamedQuery(name = "Reposiciondll.findAll", query = "SELECT r FROM Reposiciondll r")
public class Reposiciondll implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	
	
	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column(name = "motivo_repo")
	@Enumerated(EnumType.STRING)
	private MotivoRepoEnum motivoRepo;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Column
	private String observacion;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.almacen
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name="venta" ,referencedColumnName="idVenta")
	private Venta venta;

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	*/
	public Reposiciondll() {
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @return
	*/
	public int getId() {
		return this.id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @param id
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the motivoRepo
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @return
	*/
	public MotivoRepoEnum getMotivoRepo() {
		return motivoRepo;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param motivoRepo
	 *            the motivoRepo to set
	 */
	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @param motivoRepo
	*/
	public void setMotivoRepo(MotivoRepoEnum motivoRepo) {
		this.motivoRepo = motivoRepo;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @return
	*/
	public String getObservacion() {
		return this.observacion;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	* @param observacion
	*/
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @date 2/06/2013
 * @return the venta
 */
public Venta getVenta() {
	return venta;
}


/**
 *@author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 *@date 2/06/2013
 * @param venta the venta to set
 */
public void setVenta(Venta venta) {
	this.venta = venta;
}


}