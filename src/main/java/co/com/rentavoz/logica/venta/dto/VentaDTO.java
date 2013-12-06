/**
 * 
 */
package co.com.rentavoz.logica.venta.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.com.rentavoz.logica.jpa.entidades.Pago;
import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.almacen.Cuota;
import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.entidades.almacen.VentaLinea;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaDTO
 * @date 2/06/2013
 * 
 */
public class VentaDTO {

	private String numero;
	private Date fecha=new Date();
	private String tipoPago;
	private Tercero tercero ;
	private ArrayList<VentaLinea> lineas = new ArrayList<VentaLinea>();
	private String observacion;
	private Pago pago = new Pago();
	private BigDecimal domicilio = new BigDecimal(0);
	private boolean pagoTotal = false;
	private double valorAbono = 0.0;
	private double valorTotal;
	private double descuento;
	private Date fechaProxPago;
	private Date fechaPagoCuenta = new Date();
	private String lugarPago;
	private boolean pagoConsignacion = false;
	private String selIdCuenta;
	/*
	 * se crea este field para validar la existencia de este campo para un solo
	 * registro
	 */
	private Linea lineaTemp;
	private List<Cuota> cuotas = new ArrayList<Cuota>();

	private Venta baseData;

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 */
	public VentaDTO() {
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the lineas
	 */
	public ArrayList<VentaLinea> getLineas() {
		return lineas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the tipoPago
	 */
	public String getTipoPago() {
		return tipoPago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param lineas
	 *            the lineas to set
	 */
	public void setLineas(ArrayList<VentaLinea> lineas) {
		this.lineas = lineas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param observacion
	 *            the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param tercero
	 *            the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param tipoPago
	 *            the tipoPago to set
	 */
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the pago
	 */
	public Pago getPago() {
		return pago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param pago
	 *            the pago to set
	 */
	public void setPago(Pago pago) {
		this.pago = pago;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the domicilio
	 */
	public BigDecimal getDomicilio() {
		return domicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param domicilio
	 *            the domicilio to set
	 */
	public void setDomicilio(BigDecimal domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the baseData
	 */
	public Venta getBaseData() {
		return baseData;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param baseData
	 *            the baseData to set
	 */
	public void setBaseData(Venta baseData) {
		this.baseData = baseData;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public double getValorAbono() {
		return valorAbono;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public boolean isPagoTotal() {
		return pagoTotal;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @param valorAbono
	 */
	public void setValorAbono(double valorAbono) {
		this.valorAbono = valorAbono;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @param pagoTotal
	 */
	public void setPagoTotal(boolean pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public Date getFechaProxPago() {
		return fechaProxPago;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @param fechaProxPago
	 */
	public void setFechaProxPago(Date fechaProxPago) {
		this.fechaProxPago = fechaProxPago;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public Date getFechaPagoCuenta() {
		return fechaPagoCuenta;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @param fechaPagoCuenta
	 */
	public void setFechaPagoCuenta(Date fechaPagoCuenta) {
		this.fechaPagoCuenta = fechaPagoCuenta;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public String getLugarPago() {
		return lugarPago;
	}

	/***
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @param lugarPago
	 */
	public void setLugarPago(String lugarPago) {
		this.lugarPago = lugarPago;
	}

	public void setPagoConsignacion(boolean pagoConsignacion) {
		this.pagoConsignacion = pagoConsignacion;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public boolean isPagoConsignacion() {
		return pagoConsignacion;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @return
	 */
	public String getSelIdCuenta() {
		return selIdCuenta;
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/06/2013
	 * @param selIdCuenta
	 */
	public void setSelIdCuenta(String selIdCuenta) {
		this.selIdCuenta = selIdCuenta;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the lineaTemp
	 */
	public Linea getLineaTemp() {
		return lineaTemp;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param lineaTemp
	 *            the lineaTemp to set
	 */
	public void setLineaTemp(Linea lineaTemp) {
		this.lineaTemp = lineaTemp;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the descuento
	 */
	public double getDescuento() {
		return descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param descuento
	 *            the descuento to set
	 */
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the valorTotal
	 */
	public double getValorTotal() {
		return valorTotal;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param valorTotal
	 *            the valorTotal to set
	 */
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @return the cuotas
	 */
	public List<Cuota> getCuotas() {
		return cuotas;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/06/2013
	 * @param cuotas
	 *            the cuotas to set
	 */
	public void setCuotas(List<Cuota> cuotas) {
		this.cuotas = cuotas;
	}

}
