package co.com.rentavoz.logica.jpa.entidades.venta.existencia;

import java.io.Serializable;

import javax.persistence.*;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;


/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class VentaItemDetalleItem
* @date 29/10/2013
*
 */
@Entity
@Table(name="venta_item_detalle_item")
public class VentaItemDetalleItem implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@ManyToOne
	@JoinColumn(name="existencia")
	private BodegaExistencia existencia;

	/**
	 * co.com.rentavoz.logica.jpa.entidades.venta.existencia
	 * co.com.rentavoz.model.jpa
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	@JoinColumn(name="idVenta")
	private VentaItem idVenta;

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	*/
	public VentaItemDetalleItem() {
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public int getId() {
		return this.id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param id
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public BodegaExistencia getExistencia() {
		return this.existencia;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param existencia
	*/
	public void setExistencia(BodegaExistencia existencia) {
		this.existencia = existencia;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @return
	*/
	public VentaItem getIdVenta() {
		return this.idVenta;
	}

	/**
	* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param idVenta
	*/
	public void setIdVenta(VentaItem idVenta) {
		this.idVenta = idVenta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((existencia == null) ? 0 : existencia.hashCode());
		result = prime * result + ((idVenta == null) ? 0 : idVenta.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VentaItemDetalleItem other = (VentaItemDetalleItem) obj;
		if (existencia == null) {
			if (other.existencia != null)
				return false;
		} else if (!existencia.equals(other.existencia))
			return false;
		if (idVenta == null) {
			if (other.idVenta != null)
				return false;
		} else if (!idVenta.equals(other.idVenta))
			return false;
		return true;
	}
	
	

}