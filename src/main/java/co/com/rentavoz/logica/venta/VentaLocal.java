/**
 * 
 */
package co.com.rentavoz.logica.venta;

import co.com.rentavoz.logica.venta.dto.VentaDTO;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaLocal
 * @date 2/06/2013
 * 
 */
// @Local
public interface VentaLocal {
	VentaDTO registrarVenta(VentaDTO dto);
}
