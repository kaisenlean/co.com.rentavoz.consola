/**
 *
 */
package co.com.rentavoz.logica.venta;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import co.com.rentavoz.logica.jpa.entidades.TerceroVenta;
import co.com.rentavoz.logica.jpa.entidades.almacen.Cuota;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoCuotaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.ModalidaVentaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.entidades.almacen.VentaLinea;
import co.com.rentavoz.logica.jpa.fachadas.CuentasFacade;
import co.com.rentavoz.logica.jpa.fachadas.EstadoLineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.TipoPagoFacade;
import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;
import co.com.rentavoz.logica.jpa.fachadas.VentaLineaFacade;
import co.com.rentavoz.logica.venta.dto.VentaDTO;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.model.core
 * @class VentaBean
 * @date 2/06/2013
 * 
 */
@Singleton
public class VentaBean implements Serializable {

	/**
	 * co.com.rentavoz.logica.venta
	 * co.com.rentavoz.model.core
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 */
	private static final int ESTADO_LINEA_DISPONIBLE_USO = 4;
	private static final long serialVersionUID = 1L;
	@EJB
	private VentaFacade ventaFacade;
	@EJB
	private TipoPagoFacade tipoPagoFacade;
	@EJB
	private CuentasFacade cuentasFacade;
	@EJB
	private VentaLineaFacade ventaLineaFacade;
	@EJB
	private EstadoLineaFacade estadoLineaFacade;
	@EJB
	private LineaFacade lineaFacade;

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/06/2013
	 */
	@PostConstruct
	public void init() {
	}

	/**
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 19/06/2013
	 * @param dto
	 * @return
	 */
	public VentaDTO registrarVenta(VentaDTO dto) {

//		ArrayList<TerceroVenta> tercerosDeVenta = new ArrayList<TerceroVenta>();

		Venta venta = new Venta();
		venta.setIdVenta(ventaFacade.findSgteNumero());
		venta.setFecha(new Date());
		venta.setVenFecha(dto.getFecha());
		venta.setObservacion(dto.getObservacion());
		venta.setModalidadVenta(ModalidaVentaEnum.VENTA);
		venta.setVenDomicilio(dto.getDomicilio());
		venta.setDescuento(BigDecimal.valueOf(dto.getDescuento()));
		venta.setTercero(dto.getTercero());

		/* Bigdecimal para guardar el saldo la suma de las linesas */
		double saldo = 0.0;

		/* asignamos el valor del domicilio a la venta asi sea 0.0 */
		saldo += (dto.getDomicilio().doubleValue());

		int pos = 0;
		/* recorremos lineas para calcular saldo a pagar */
		for (VentaLinea vlTemp : dto.getLineas()) {
			// suma el precio de la linea al total
			saldo += (vlTemp.getVentLinPrecio().doubleValue());
			// suma el deposito
			saldo += (vlTemp.getVentLinDeposito().doubleValue());
			vlTemp.setVentaidVenta(venta);

			vlTemp.setFechaRenovacion(cargarFechaRenovacion(vlTemp));
			dto.getLineas().set(pos, vlTemp);
			pos++;
		}
		// asignamos las ventaslineas a la venta
		venta.setVentaLineaList(dto.getLineas());

		/* adicionamos los terceros a la venta(generalmente siempre sera 1) */
//		tercerosDeVenta.add(new TerceroVenta(dto.getTercero(), venta));
//		venta.setTerceroVentaList(tercerosDeVenta);

		if (!dto.isPagoTotal()) {
			Cuota cuota = new Cuota();
			cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
			cuota.setVenta(venta);
			cuota.setValorCuota(BigDecimal.valueOf(saldo));
			venta.getCuotas().add(cuota);
		} else {

			Cuota cuota = new Cuota();
			cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
			cuota.setVenta(venta);
			cuota.setValorCuota(BigDecimal.valueOf(dto.getValorAbono()));
			venta.getCuotas().add(cuota);

			Cuota cuota2 = new Cuota();
			cuota2.setEstadoCuota(EstadoCuotaEnum.PENDIENTE);
			cuota2.setVenta(venta);
			cuota2.setValorCuota(BigDecimal.valueOf(saldo - dto.getValorAbono()));
			cuota2.setFechaPago(dto.getFechaProxPago());
			venta.getCuotas().add(cuota2);

		}

		venta.setVenDomicilio(dto.getDomicilio());
		venta.setVenSaldo(BigDecimal.valueOf(saldo));

		/* registramos la venta en la bd */
		try {
			ventaFacade.create(venta);
			for (VentaLinea linea : dto.getLineas()) {
				linea.getLineaidLinea().setEstadoLineaidEstadoLinea(estadoLineaFacade.find(ESTADO_LINEA_DISPONIBLE_USO));
				lineaFacade.edit(linea.getLineaidLinea());
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}

		dto.setBaseData(venta);
		return dto;
	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param vlTemp
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private Date cargarFechaRenovacion(VentaLinea vlTemp) {
		Calendar calendar = Calendar.getInstance();
		Date now = new Date();
		calendar.set(Calendar.MONTH, (now.getMonth()) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, vlTemp.getLineaidLinea()
				.getLinCorte());

		return calendar.getTime();
	}

	/**
	 * metodo que consulta las fechas de reposicion de una linea entre un rango
	 * de fechas
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param fechaReposicionI
	 * @param fechaReposicionF
	 * @return
	 */
	public List<VentaLinea> cargarLineasPendientesPorReposicion(
			Date fechaReposicionI, Date fechaReposicionF) {
		return ventaLineaFacade.buscarLineasPorRenovar(fechaReposicionI,
				fechaReposicionF);

	}

	/**
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param dto
	 */
	public void registrarRenovacion(VentaDTO dto) throws Exception {

		ArrayList<TerceroVenta> tercerosDeVenta = new ArrayList<TerceroVenta>();

		Venta venta = new Venta();
		// venta.setIdVenta(ventaFacade.findSgteNumero());
		venta.setFecha(new Date());
		venta.setVenFecha(new Date());
		venta.setObservacion(dto.getObservacion());
		venta.setModalidadVenta(ModalidaVentaEnum.RENOVACION);
		venta.setVenDomicilio(dto.getDomicilio());
		venta.setDescuento(BigDecimal.valueOf(dto.getDescuento()));

		/* Bigdecimal para guardar el saldo la suma de las linesas */
		double saldo = 0.0;

		/* asignamos el valor del domicilio a la venta asi sea 0.0 */
		saldo += (dto.getDomicilio().doubleValue());

		int pos = 0;
		/* recorremos lineas para calcular saldo a pagar */
		for (VentaLinea vlTemp : dto.getLineas()) {
			// suma el precio de la linea al total
			saldo += (vlTemp.getVentLinPrecio().doubleValue());
			// suma el deposito
			saldo += (vlTemp.getVentLinDeposito().doubleValue());

			vlTemp.setIdVentaLinea(null);
			vlTemp.setFechaRenovacion(cargarFechaRenovacion(vlTemp));

			dto.getLineas().set(pos, vlTemp);
			pos++;
		}
		// asignamos las ventaslineas a la venta
		venta.setVentaLineaList(dto.getLineas());

		/* adicionamos los terceros a la venta(generalmente siempre sera 1) */
		tercerosDeVenta.add(new TerceroVenta(dto.getTercero(), venta));
		venta.setTerceroVentaList(tercerosDeVenta);

		if (!dto.isPagoTotal()) {
			Cuota cuota = new Cuota();
			cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
			cuota.setVenta(venta);
			cuota.setValorCuota(BigDecimal.valueOf(saldo));
			venta.getCuotas().add(cuota);
		} else {

			Cuota cuota = new Cuota();
			cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
			cuota.setVenta(venta);
			cuota.setValorCuota(BigDecimal.valueOf(dto.getValorAbono()));
			venta.getCuotas().add(cuota);

			Cuota cuota2 = new Cuota();
			cuota2.setEstadoCuota(EstadoCuotaEnum.PENDIENTE);
			cuota2.setVenta(venta);
			cuota2.setValorCuota(BigDecimal.valueOf(saldo - dto.getValorAbono()));
			cuota2.setFechaPago(dto.getFechaProxPago());
			venta.getCuotas().add(cuota2);

		}

		venta.setVenDomicilio(dto.getDomicilio());
		venta.setVenSaldo(BigDecimal.valueOf(saldo));

		/* registramos la venta en la bd */
		try {
			ventaFacade.create(venta);

		} catch (Exception e) {

			throw e;
		}

		dto.setBaseData(venta);

	}

}
