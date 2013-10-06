/**
 * 
 */
package com.invte.rentavoz.vista.bodega.ingreso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaIngreso;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.entidades.bodega.EstadoExistenciaEnum;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaExistenciaFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaIngresoFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaItemFacade;

import com.invte.rentavoz.vista.StandardAbm;
import com.invte.rentavoz.vista.componentes.autocomplete.tercero.AutocompleteTerceroProveedor;
import com.invte.rentavoz.vista.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanIngresoBodega
 * @date 6/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanIngresoBodega extends StandardAbm<BodegaIngreso> implements
		Serializable {

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/ingreso/index.jsf";

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 3204121622329092358L;

	
	private String productoId;
	
	private AutocompleteTerceroProveedor autocompleteProveedor;

	@EJB
	private TerceroFacade terceroFacade;

	@EJB
	private BodegaIngresoFacade bodegaIngresoFacade;
	
	@EJB
	private BodegaItemFacade bodegaItemFacade;
	
	@EJB
	private SucursalFacade sucursalFacade;
	
	
	@EJB
	private BodegaExistenciaFacade bodegaExistenciaFacade;
	
	
	
	@ManagedProperty(value="#{login}")
	private Login login;
	
	
	private int selItem=0;
	private int selSucursal=0;

	private BodegaItem item;
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public AbstractFacade<BodegaIngreso> getFacade() {
		return bodegaIngresoFacade;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public BodegaIngreso getInstancia() {
		return new BodegaIngreso();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public BodegaIngreso getObjeto() {
		return obtenerObjeto();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<BodegaIngreso> getListado() {
		return obtenerListado();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		autocompleteProveedor = new AutocompleteTerceroProveedor() {

			@Override
			public void postSelect() {
				getObjeto().setProveedor(seleccionado);
			}

			@Override
			public TerceroFacade getFacade() {
				return terceroFacade;
			}
		};
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the autocompleteProveedor
	 */
	public AutocompleteTerceroProveedor getAutocompleteProveedor() {
		return autocompleteProveedor;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param autocompleteProveedor
	 *            the autocompleteProveedor to set
	 */
	public void setAutocompleteProveedor(
			AutocompleteTerceroProveedor autocompleteProveedor) {
		this.autocompleteProveedor = autocompleteProveedor;
	}

	/**
	 * (non-Javadoc)
	 * @see com.invte.rentavoz.vista.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		autocompleteProveedor.setQuery(getObjeto().getProveedor().toString());
		if(getObjeto().getSucursal()!=null){
			selSucursal=getObjeto().getSucursal().getIdSucursal();
		}
	}

/** (non-Javadoc)
 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
 */
@Override
public void postAction() {
	
	if (!isEdit()) {
		
		generarNumeroFactura();
	}
}

/* (non-Javadoc)
 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
 */
@Override
public void postFormNuevo() {
getObjeto().setFechaIngreso(new Date());
}


/** (non-Javadoc)
 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
 */
@Override
public boolean preAction() {
	if (selSucursal==0) {
		mensajeError("Por favor selecciona una sucursal válida");
		return false;
	}
	
	Sucursal suc = sucursalFacade.find(selSucursal);
	
	if (suc!=null) {
		getObjeto().setSucursal(suc);
	}
	
	if (!isEdit()) {
		for (BodegaExistencia ext : getObjeto().getBodegaExistencias()) {
			ext.setBodegaIngreso(getObjeto());
			ext.setEstado(EstadoExistenciaEnum.DISPONIBLE);
			if (bodegaExistenciaFacade.findByBarcode(ext.getBarCode())!=null) {
			mensajeError("Este PID "+ext.getBarCode()+" ya se encuentra en el inventario");
			return false;
			}
		}
	}
	
	return true;
}

/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 7/10/2013
* @param evt
 */
public void cambioItem(){
	 item=bodegaItemFacade.find(selItem);
	
}

/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 6/10/2013
 */
public void addExistencia(){
	if (item==null) {
		mensajeError("Selecciona un item");
		return;
	}
	if (productoId==null || productoId.equals("")) {
		mensajeError("Digita un PID válido");
		return;
	}
	BodegaExistencia existemp=new BodegaExistencia();
	existemp.setBarCode(productoId);
	existemp.setBodegaItemBean(item);
	existemp.setSucursal(login.getSucursal());
	if (getObjeto().getBodegaExistencias().contains(existemp)) {
		mensajeError("Este PID ya está en la lista");
		return;
	}
	getObjeto().addBodegaExistencia(existemp);
	productoId="";

}

	/**
	 * Método que genera el numero de la factura de compra
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/10/2013
	*/
	private void generarNumeroFactura() {
		Date now=new Date();
		
		StringBuilder builder= new StringBuilder();
		builder.append(new SimpleDateFormat("yyyy").format(now));
		builder.append(new SimpleDateFormat("MM").format(now));
		builder.append(new SimpleDateFormat("dd").format(now));
	
		builder.append("-");
		builder.append(getObjeto().getId());
		getObjeto().setNroFactura(builder.toString());
		getFacade().edit(getObjeto());
		
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the selItem
	 */
	public int getSelItem() {
		return selItem;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param selItem the selItem to set
	 */
	public void setSelItem(int selItem) {
		this.selItem = selItem;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param productoId the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @return the selSucursal
	 */
	public int getSelSucursal() {
		return selSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param selSucursal the selSucursal to set
	 */
	public void setSelSucursal(int selSucursal) {
		this.selSucursal = selSucursal;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/10/2013
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
