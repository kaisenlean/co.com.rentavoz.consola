/**
 * 
 */
package com.invte.rentavoz.vista.renovacion.linea;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.TerceroVenta;
import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.entidades.almacen.VentaLinea;
import co.com.rentavoz.logica.jpa.fachadas.CuotaFacade;
import co.com.rentavoz.logica.jpa.fachadas.EstadoLineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.VentaLineaFacade;
import co.com.rentavoz.logica.venta.VentaBean;
import co.com.rentavoz.logica.venta.dto.VentaDTO;

import com.invte.rentavoz.vista.BaseBean;

/**
 * Clase para detallar un linea vendida y realizar su correspondiente renovacion
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class DetalleRenovacionLinea
 * @date 14/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class DetalleRenovacionLinea extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final int ESTADO_LINEA_DISPONIBLE = 2;
	/**
	 * 
	 */
	private static final String DEVOLUCION = "devolucion";
	/**
	 * 
	 */
	private static final String ID_VENTA_LINEA = "idVentaLinea";
	static final String BEAN_NAME = "detalleRenovacion";
	private static final long serialVersionUID = 2323529960841599842L;
	private Integer idVentaLinea;

	@EJB
	private VentaLineaFacade ventaLineaFacade;
	@EJB
	private CuotaFacade cuotaFacade;

	@EJB
	private VentaBean ventaBean;

	@EJB
	private LineaFacade lineaFacade;

	private VentaLinea ventaLinea;
	private VentaDTO dto;
	private boolean verFormaPagos;
	private String formaPago;
	private boolean verGridCuotas;
	private Date proxFechaPago;
	private double valorAbonado;
	private double subtotal = 0.0;
	private double totalDepositos = 0.0;
	private double descuentos = 0.0;
	private double total = 0.0;
	private double domicilio = 0.0;
	private boolean continuarRenovacion;
	private boolean devolucion;
	@EJB
	private EstadoLineaFacade estadoLineaFacade;

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 */
	public void cambioFormaPago() {

		verFormaPagos = false;
		if (dto.getTipoPago().equals("2")) {
			verFormaPagos = true;
		}

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 */
	public void cambioTipoPago() {

		verGridCuotas = false;
		if (formaPago.equals("P")) {
			verGridCuotas = true;
		}

	}

	/**
	 * Constructor que recibe como parametro via request el id de la venta
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 */
	@PostConstruct
	public void init() {
		Object parameter = getAttribute(ID_VENTA_LINEA);
		Object parameterDev = getAttribute(DEVOLUCION);

		if (parameterDev != null) {
			if (parameterDev instanceof Boolean) {
				devolucion = (Boolean) parameterDev;
			}
		}

		if (parameter != null) {

			ventaLinea = (VentaLinea) parameter;

		}

		if (ventaLinea == null) {

			// throw new Exception("El detalle de la venta linea es nulo");
		}

		dto = cargarDTO(ventaLinea);

	}

	/**
	 * Metodo que crea un aventaDto a base de una ventaLInea
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param ventaLinea2
	 * @return
	 */
	private VentaDTO cargarDTO(VentaLinea ventaLinea) {

		validarPagoDeCuotas(ventaLinea);

		VentaDTO ventaDTO = new VentaDTO();
		ventaDTO.setBaseData(ventaLinea.getVentaidVenta());
		ventaDTO.setDomicilio(ventaLinea.getVentaidVenta().getVenDomicilio());
		ventaDTO.setFechaPagoCuenta(ventaLinea.getVentaidVenta().getVenFecha());
		ArrayList<VentaLinea> lineas = new ArrayList<VentaLinea>();
		lineas.add(ventaLinea);
		ventaDTO.setLineas(lineas);
		ventaDTO.setLineaTemp(ventaLinea.getLineaidLinea());

		ventaDTO.setObservacion(ventaLinea.getVentaidVenta().getObservacion());
		if (ventaLinea.getVentaidVenta().getPagoList() != null) {

			if (!ventaLinea.getVentaidVenta().getPagoList().isEmpty()) {
				ventaDTO.setPago(ventaLinea.getVentaidVenta().getPagoList()
						.get(0));
			}
		}

		if (ventaLinea.getVentaidVenta().getTerceroVentaList() != null) {

			if (!ventaLinea.getVentaidVenta().getTerceroVentaList().isEmpty()) {
				TerceroVenta tv = ventaLinea.getVentaidVenta()
						.getTerceroVentaList().get(0);
				ventaDTO.setTercero(tv != null ? tv.getTerceroidTecero() : null);
			}
		}

		totalDepositos = ventaLinea.getVentLinDeposito().doubleValue();
		subtotal = ventaLinea.getVentLinPrecio().doubleValue();
		domicilio = ventaLinea.getVentaidVenta().getVenDomicilio()
				.doubleValue();
		total = ventaLinea.getVentLinPrecio()
				.add(ventaLinea.getVentLinDeposito()).doubleValue();

		return ventaDTO;

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param ventaLinea2
	 */
	private void validarPagoDeCuotas(VentaLinea ventaLinea2) {
		Venta venta = ventaLinea2.getVentaidVenta();
		continuarRenovacion = !cuotaFacade.cuotasActivas(venta);

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 */
	public void registrarRenovacion() {

		/* si el pago es parcial entonces realizamos las cuotas respectivas */

		dto.setPagoTotal(verGridCuotas);
		dto.setValorAbono(valorAbonado);
		dto.setDomicilio(BigDecimal.valueOf(domicilio));
		dto.setDescuento(descuentos);

		dto.setPagoConsignacion(verFormaPagos);
		dto.setValorTotal(total);
		try {
			dto.setBaseData(null);
			ventaBean.registrarRenovacion(dto);
			mensaje("Exito", "Se ha registrado exitosamente la renovacion");
		} catch (Exception e) {
			mensaje("Error", e.getMessage());
		}
	}

	/**
	 * Metodo que registra una devolucion de una linea
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 */
	public void registrarDevolucion() {
		Linea linea = ventaLinea.getLineaidLinea();
		linea.setEstadoLineaidEstadoLinea(estadoLineaFacade.find(Integer
				.valueOf(ESTADO_LINEA_DISPONIBLE)));
		lineaFacade.edit(linea);
		mensaje("Exito", "Se ha realizado la devolucion con exito");
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the idVentaLinea
	 */
	public Integer getIdVentaLinea() {
		return idVentaLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param idVentaLinea
	 *            the idVentaLinea to set
	 */
	public void setIdVentaLinea(Integer idVentaLinea) {
		this.idVentaLinea = idVentaLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the ventaLineaFacade
	 */
	public VentaLineaFacade getVentaLineaFacade() {
		return ventaLineaFacade;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param ventaLineaFacade
	 *            the ventaLineaFacade to set
	 */
	public void setVentaLineaFacade(VentaLineaFacade ventaLineaFacade) {
		this.ventaLineaFacade = ventaLineaFacade;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the ventaLinea
	 */
	public VentaLinea getVentaLinea() {
		return ventaLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param ventaLinea
	 *            the ventaLinea to set
	 */
	public void setVentaLinea(VentaLinea ventaLinea) {
		this.ventaLinea = ventaLinea;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the dto
	 */
	public VentaDTO getDto() {
		return dto;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param dto
	 *            the dto to set
	 */
	public void setDto(VentaDTO dto) {
		this.dto = dto;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the verFormaPagos
	 */
	public boolean isVerFormaPagos() {
		return verFormaPagos;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param verFormaPagos
	 *            the verFormaPagos to set
	 */
	public void setVerFormaPagos(boolean verFormaPagos) {
		this.verFormaPagos = verFormaPagos;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the formaPago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param formaPago
	 *            the formaPago to set
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the verGridCuotas
	 */
	public boolean isVerGridCuotas() {
		return verGridCuotas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param verGridCuotas
	 *            the verGridCuotas to set
	 */
	public void setVerGridCuotas(boolean verGridCuotas) {
		this.verGridCuotas = verGridCuotas;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the proxFechaPago
	 */
	public Date getProxFechaPago() {
		return proxFechaPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param proxFechaPago
	 *            the proxFechaPago to set
	 */
	public void setProxFechaPago(Date proxFechaPago) {
		this.proxFechaPago = proxFechaPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the valorAbonado
	 */
	public double getValorAbonado() {
		return valorAbonado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param valorAbonado
	 *            the valorAbonado to set
	 */
	public void setValorAbonado(double valorAbonado) {
		this.valorAbonado = valorAbonado;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param subtotal
	 *            the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the totalDepositos
	 */
	public double getTotalDepositos() {
		return totalDepositos;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param totalDepositos
	 *            the totalDepositos to set
	 */
	public void setTotalDepositos(double totalDepositos) {
		this.totalDepositos = totalDepositos;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the descuentos
	 */
	public double getDescuentos() {
		return descuentos;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param descuentos
	 *            the descuentos to set
	 */
	public void setDescuentos(double descuentos) {
		this.descuentos = descuentos;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the domicilio
	 */
	public double getDomicilio() {
		return domicilio;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(double domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the continuarRenovacion
	 */
	public boolean isContinuarRenovacion() {
		return continuarRenovacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param continuarRenovacion
	 *            the continuarRenovacion to set
	 */
	public void setContinuarRenovacion(boolean continuarRenovacion) {
		this.continuarRenovacion = continuarRenovacion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the devolucion
	 */
	public boolean isDevolucion() {
		return devolucion;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param devolucion
	 *            the devolucion to set
	 */
	public void setDevolucion(boolean devolucion) {
		this.devolucion = devolucion;
	}

}
