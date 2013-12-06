///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.com.rentavoz.logica.jpa.fachadas;
//
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import co.com.rentavoz.logica.jpa.entidades.almacen.Factura;
//
///**
// * 
// * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
// * @project co.com.rentavoz.model.jpa
// * @class FacturaFacade
// * @date 14/07/2013
// * 
// */
//@Stateless
//public class FacturaFacade extends AbstractFacade<Factura> {
//	@PersistenceContext(unitName = "com.innovate.rentavozPU")
//	private EntityManager em;
//
//	@Override
//	protected EntityManager getEntityManager() {
//		return em;
//	}
//
//	/**
//	 * 
//	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
//	 * @date 14/07/2013
//	 */
//	public FacturaFacade() {
//		super(Factura.class);
//	}
//
//}
