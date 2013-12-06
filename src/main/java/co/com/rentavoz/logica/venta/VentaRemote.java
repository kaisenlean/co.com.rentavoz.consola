/**
 * 
 */
package co.com.rentavoz.logica.venta;

import javax.ejb.Remote;

import co.com.rentavoz.logica.venta.dto.VentaDTO;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaRemote
 * @date 2/06/2013
 * 
 */
@Remote
public interface VentaRemote {

	VentaDTO registrarVenta(VentaDTO dto);
}
