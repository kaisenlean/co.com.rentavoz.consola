/**
 * 
 */
package com.invte.rentavoz.logica.venta.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.EstadoExistenciaEnum;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItem;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItemCuota;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItemDetalleItem;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaExistenciaFacade;
import co.com.rentavoz.logica.jpa.fachadas.venta.item.VentaItemCuotaFacade;
import co.com.rentavoz.logica.jpa.fachadas.venta.item.VentaItemFacade;

/**
 * Clase EJB que controla toda la transaccion de una venta de un articulo de bodega
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class VentaItemEjb
 * @date 29/10/2013
 *
 */
@Stateless
public class VentaItemEjb implements Serializable {

	/**
	 * 29/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VentaItemFacade ventaItemFacade;
	
	@EJB
	private BodegaExistenciaFacade bodegaExistenciaFacade;
	
	@EJB
	private VentaItemCuotaFacade cuotaFacade;
	
	
	/**
	 * Metodo que registra la venta de las existencias seleccionadas , cambia el estado de las mismas 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 29/10/2013
	* @param venta
	* @return {@link VentaItem}
	 */
	public VentaItem registrarVenta(VentaItem venta) throws Exception{
		List<VentaItemDetalleItem> detallesVenta= venta.getExistencias();
		List<BodegaExistencia>  existencias = new ArrayList<BodegaExistencia>();
		/*sacamos las existencias involucradas en la venta*/
		for (VentaItemDetalleItem detalleItem : detallesVenta) {
			BodegaExistencia beTemp=detalleItem.getExistencia();
			beTemp.setEstado(EstadoExistenciaEnum.VENDIDO);
			existencias.add(beTemp);
		}
		/*registramos la venta */		
		ventaItemFacade.create(venta);
		
		/*luego editamos las existencias a vendida*/
		for (BodegaExistencia bodegaExistencia : existencias) {
			bodegaExistenciaFacade.edit(bodegaExistencia);
		}
		
		for (VentaItemCuota cuota : venta.getCuotas()) {
			cuota.setIdVenta(venta);
			cuotaFacade.edit(cuota);
			
		}
		
		return venta;
		
	}
	
	
	

}
