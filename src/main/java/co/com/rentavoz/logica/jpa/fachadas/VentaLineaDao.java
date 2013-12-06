/**
 * 
 */
package co.com.rentavoz.logica.jpa.fachadas;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import co.com.rentavoz.logica.jpa.entidades.almacen.VentaLinea;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.jpa
 * @class VentaLineaDao
 * @date 14/07/2013
 * 
 */
@Local
public interface VentaLineaDao {

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param start
	 * @param end
	 * @return
	 */
	List<VentaLinea> buscarLineasPorRenovar(Date start, Date end);

}
