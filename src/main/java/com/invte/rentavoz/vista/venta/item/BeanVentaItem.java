package com.invte.rentavoz.vista.venta.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.TipoTerceroEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoCuotaEnum;
import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaExistencia;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.EstadoVentaItemCuotaEnum;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.EstadoVentaItemEnum;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.ModoPagoEnum;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItem;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItemCuota;
import co.com.rentavoz.logica.jpa.entidades.venta.existencia.VentaItemDetalleItem;
import co.com.rentavoz.logica.jpa.fachadas.CuentasFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaExistenciaFacade;

import com.invte.rentavoz.logica.venta.item.VentaItemEjb;
import com.invte.rentavoz.reports.PrinterBean;
import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.componentes.autocomplete.tercero.AutocompleteTercero;
import com.invte.rentavoz.vista.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanVentaItem
 * @date 29/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanVentaItem extends BaseBean implements Serializable {

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         login
	 */
	@ManagedProperty(value = "#{login}")
	private Login login;

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         ventaItemEjb
	 */
	@EJB
	private VentaItemEjb ventaItemEjb;

	/**
	 * 29/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         venta
	 */
	private VentaItem venta;

	@EJB
	private BodegaExistenciaFacade bodegaExistenciaFacade;

	@EJB
	private TerceroFacade terceroFacade;
	
	@ManagedProperty(value="#{printerBean}")
	private PrinterBean  printerBean;

	private String productoId;

	private Tercero tercero;

	private AutocompleteTercero autocompleteTercero;

	private int idCuenta;

	private String modoPago;

	private VentaItemCuota cuota;

	private String estadoCuota;
	
	@EJB
	private CuentasFacade  cuentasFacade;

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	@PostConstruct
	public void init() {

		inicializarVenta();

		tercero = new Tercero();
		cuota = new VentaItemCuota();
		autocompleteTercero = new AutocompleteTercero() {

			@Override
			public void postSelect() {
				tercero = getSeleccionado();
			}

			@Override
			public TerceroFacade getFacade() {
				return terceroFacade;
			}
		};
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void crearNuevoTercero() {

		tercero.setTipo(TipoTerceroEnum.CLIENTE_MINORISTA);

		terceroFacade.create(tercero);

		autocompleteTercero.setSeleccionado(tercero);

		autocompleteTercero.setQuery(tercero.toString());

		runJavascript("dlgNewCliente.hide();");
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void agregarCuota() {

		cuota.setEstado(EstadoVentaItemCuotaEnum.valueOf(estadoCuota));
		if (estadoCuota.equals(EstadoCuotaEnum.PAGADA.name())) {
			cuota.setFechaPago(new Date());
		}
		venta.getCuotas().add(cuota);
		cuota = new VentaItemCuota();

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cuota
	 */
	public void eliminarCuota(VentaItemCuota cuota) {
		venta.getCuotas().remove(cuota);
		venta.setValorPagar(venta.getValorPagar()-cuota.getValor());

	}
/**
 * 
* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
* @date 29/10/2013
 */
	public void guardarVenta() {
		venta.setValorPagar(venta.getValorPagar()-venta.getDescuento());
		venta.setCuenta(cuentasFacade.find(idCuenta));
		venta.setCliente(tercero);
		venta.setModoPago(ModoPagoEnum.valueOf(modoPago));
		try {
			ventaItemEjb.registrarVenta(venta);
		
			
			runJavascript("Se ha registrado una nueva venta");
			List<VentaItem> ventas= new ArrayList<VentaItem>();
			ventas.add(venta);
			printerBean.exportPdf("facturaIndividual_1", "factura_"+venta.getIdVenta(),null,ventas);
			venta=new VentaItem();
		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}
	
	

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	public void addExistencia() {
		boolean existe = false;
		BodegaExistencia be = bodegaExistenciaFacade.findByBarcode(productoId,
				login.getTercero().getSucursalTerceroList().get(0)
						.getSucursalidSucursal());
		if (be != null) {
			existe = true;
		}
		be = bodegaExistenciaFacade.findByBarcodeActivo(productoId, login
				.getTercero().getSucursalTerceroList().get(0)
				.getSucursalidSucursal());
		if (be != null) {
			VentaItemDetalleItem detalle = new VentaItemDetalleItem();
			detalle.setExistencia(be);
			detalle.setIdVenta(venta);
			if (venta.getExistencias().contains(detalle)) {
				mensajeError(new StringBuilder(
						"Este Producto ya está en la lista").toString());
				return;
			}
			venta.getExistencias().add(detalle);
			/* adicionamos su precio al valor a pagar de la venta */
			venta.setValorPagar(venta.getValorPagar()
					+ detalle.getExistencia().getBodegaItemBean()
							.getPrecioVenta().doubleValue());
			productoId = "";
		} else {
			if (existe) {

				mensajeError(new StringBuilder(
						" este PID se encuentra en otra bodega o punto de venta")
						.toString());
			} else {

				mensajeError(new StringBuilder(
						"Este PID no esta registrado en el inventario")
						.toString());
			}
		}
	}

	public void eliminarExistencia(VentaItemDetalleItem detalle) {
		venta.getExistencias().remove(detalle);

	}

	/**
	 * Metodo que inicializa una venta con los datos claves
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 */
	private void inicializarVenta() {
		venta = new VentaItem();
		venta.setFecha(new Date());
		venta.setEstado(EstadoVentaItemEnum.ACTIVO);
		venta.setVendedor(login.getTercero());
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the venta
	 */
	public VentaItem getVenta() {
		return venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param venta
	 *            the venta to set
	 */
	public void setVenta(VentaItem venta) {
		this.venta = venta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param login
	 */
	public void setLogin(Login login) {
		this.login = login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the productoId
	 */
	public String getProductoId() {
		return productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param productoId
	 *            the productoId to set
	 */
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param tercero
	 *            the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param autocompleteTercero
	 *            the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the idCuenta
	 */
	public int getIdCuenta() {
		return idCuenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param idCuenta
	 *            the idCuenta to set
	 */
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the modoPago
	 */
	public String getModoPago() {
		return modoPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param modoPago
	 *            the modoPago to set
	 */
	public void setModoPago(String modoPago) {
		this.modoPago = modoPago;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the cuota
	 */
	public VentaItemCuota getCuota() {
		return cuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param cuota
	 *            the cuota to set
	 */
	public void setCuota(VentaItemCuota cuota) {
		this.cuota = cuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the estadoCuota
	 */
	public String getEstadoCuota() {
		return estadoCuota;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param estadoCuota
	 *            the estadoCuota to set
	 */
	public void setEstadoCuota(String estadoCuota) {
		this.estadoCuota = estadoCuota;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @param printerBean the printerBean to set
	 */
	public void setPrinterBean(PrinterBean printerBean) {
		this.printerBean = printerBean;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 29/10/2013
	 * @return the printerBean
	 */
	public PrinterBean getPrinterBean() {
		return printerBean;
	}
}
