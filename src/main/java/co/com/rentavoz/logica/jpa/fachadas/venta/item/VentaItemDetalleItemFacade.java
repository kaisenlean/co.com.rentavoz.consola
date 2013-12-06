/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rentavoz.logica.jpa.fachadas.venta.item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItemDetalleItem;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;

/**
 * 
* @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @project co.com.rentavoz.model.jpa
* @class VentaItemDetalleItemFacade
* @date 29/10/2013
*
 */
@Stateless
public class VentaItemDetalleItemFacade extends AbstractFacade<VentaItemDetalleItem> {
	@PersistenceContext(unitName = "com.innovate.rentavozPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public VentaItemDetalleItemFacade() {
		super(VentaItemDetalleItem.class);
	}

}
