/**

 * 
 */
package com.invte.rentavoz.vista.bodega.salida;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaSalida;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaSalidaReferencia;
import co.com.rentavoz.logica.jpa.entidades.bodega.EstadoExistenciaEnum;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaExistenciaFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaSalidaFacade;

import com.invte.rentavoz.vista.StandardAbm;
import com.invte.rentavoz.vista.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanSalidaItem
 * @date 6/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanSalidaItem extends StandardAbm<BodegaSalida> implements
		Serializable {

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/salida/index.jsf";

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BodegaSalidaFacade bodegaSalidaFacade;
	@EJB
	private BodegaExistenciaFacade bodegaExistenciaFacade;

	@EJB
	private SucursalFacade sucursalFacade;
	private int sucursalOrigen = 0;
	private int sucursalDestino = 0;

	private String productoId;
	
	@ManagedProperty(value="#{login}")
	private Login login;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public AbstractFacade<BodegaSalida> getFacade() {
		return bodegaSalidaFacade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public BodegaSalida getInstancia() {
		return new BodegaSalida();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public BodegaSalida getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<BodegaSalida> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		sucursalOrigen=login.getSucursal()==null?0:login.getSucursal().getIdSucursal();
	}
	
	/* (non-Javadoc)
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
getObjeto().setFechaSalida(new Date());
	}

	public void addExistencia() {
		boolean existe=false;
		BodegaExistencia be = bodegaExistenciaFacade.findByBarcode(productoId,login.getTercero().getSucursalTerceroList().get(0).getSucursalidSucursal());
		if (be!=null) {
			existe=true;
		}
		 be = bodegaExistenciaFacade.findByBarcodeActivo(productoId,login.getTercero().getSucursalTerceroList().get(0).getSucursalidSucursal());
		if (be != null) {
			BodegaSalidaReferencia bodegaSalidaReferencia = new BodegaSalidaReferencia();
			bodegaSalidaReferencia.setBodegaExistencia(be);
			bodegaSalidaReferencia.setBodegaSalida(getObjeto());
			if (getObjeto().getBodegaSalidaReferencias().contains(bodegaSalidaReferencia)) {
				mensajeError(new StringBuilder("Este PID ya está en la lista").toString());
				return;
			}
			getObjeto().getBodegaSalidaReferencias().add(bodegaSalidaReferencia);

			productoId = "";
		} else {
			if (existe) {
				
				mensajeError(new StringBuilder("Ya se ha realizado una salida de inventario con este PID a otro bodega o punto de venta").toString());
			}else{
				
				mensajeError(new StringBuilder("Este PID no esta registrado en el inventario").toString());
			}
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {

		if (sucursalOrigen == 0) {
			mensajeError("Selecciona una sucursal de origen");
			return false;
		}

		getObjeto().setSucursalOrigen(sucursalFacade.find(sucursalOrigen));
		
		if (sucursalDestino == 0) {
			mensajeError("Selecciona una sucursal de destino");
			return false;
		}
		getObjeto().setSucursalDestino(sucursalFacade.find(sucursalDestino));
		
		if (sucursalOrigen == sucursalDestino) {
			mensajeError("La sucursal de destino no puede ser la misma de origen");

			return false;
		}

		return true;

	}
	
	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {
		
		if (!isEdit()) {
			generarNumeroFactura();
			for (BodegaSalidaReferencia sr : getObjeto().getBodegaSalidaReferencias()) {
				BodegaExistencia be=sr.getBodegaExistencia();
				be.setEstado(EstadoExistenciaEnum.DISPONIBLE);
				be.setSucursal(getObjeto().getSucursalDestino());
				bodegaExistenciaFacade.edit(be);
				
			}
		}
		
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the sucursalDestino
	 */
	public int getSucursalDestino() {
		return sucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @return the sucursalOrigen
	 */
	public int getSucursalOrigen() {
		return sucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param sucursalDestino
	 *            the sucursalDestino to set
	 */
	public void setSucursalDestino(int sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/10/2013
	 * @param sucursalOrigen
	 *            the sucursalOrigen to set
	 */
	public void setSucursalOrigen(int sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param productoId
	 *            the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
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
	 * @date 15/10/2013
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
