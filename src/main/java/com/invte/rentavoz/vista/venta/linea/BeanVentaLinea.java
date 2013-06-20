/**
 *
 */
package com.invte.rentavoz.vista.venta.linea;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;

import co.com.rentavoz.logica.jpa.entidades.Linea;
import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.VentaLinea;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;
import co.com.rentavoz.logica.venta.dto.VentaBean;
import co.com.rentavoz.logica.venta.dto.VentaDTO;

import com.invte.rentavoz.vista.CustomListener;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanVentaLinea
 * @date 2/06/2013
 *
 */
@ManagedBean
@ViewScoped
public class BeanVentaLinea implements Serializable {

    @EJB
    private VentaBean bean;
    @EJB
    private LineaFacade lineaFacade;
    @EJB
    private TerceroFacade terceroFacade;
    private List<Tercero> terceros = new ArrayList<Tercero>();
    private String tipoPago;
    private List<Linea> lineasDisponibles;
    private ArrayList<Linea> lineasSeleccionadas = new ArrayList<Linea>();
    private ListaDataModel model;
    private CustomListener listener;
    private Linea seleccionada;
    /**
     *
     */
    private static final long serialVersionUID = -7337180575872667606L;
    private VentaDTO dto;
    private double subtotal = 0.0;
    private double totalDepositos = 0.0;
    private double descuentos = 0.0;
    private double total = 0.0;
    private double domicilio = 0.0;
    private String formaPago;
    private Date proxFechaPago;
    private double valorAbonado;
    private boolean verGridCuotas = false;
    private boolean verFormaPagos;
    private String selIdCuenta;

    @PostConstruct
    public void init() {
        dto = new VentaDTO();
        model = new ListaDataModel() {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public LineaFacade getFacade() {
                // TODO Auto-generated method stub
                return lineaFacade;
            }
        };
        listener = new CustomListener() {
            @Override
            public void callMethod(ActionEvent evt) {
                Linea linea = (Linea) evt.getComponent().getAttributes()
                        .get("line");
                adicionarCarrito();

            }
        };
    }

    public void cambioTipoPago() {

        verGridCuotas = false;
        if (tipoPago.equals("P")) {
            verGridCuotas = true;
        }



    }

    public void cambioFormaPago() {

        verFormaPagos = false;
        if (dto.getTipoPago().equals("2")) {
            verFormaPagos = true;
        }



    }

    public void registrarVenta() {
        ArrayList<VentaLinea> ventaLineaTemp = new ArrayList<VentaLinea>();
        for (Linea linea : lineasSeleccionadas) {
            VentaLinea vl = new VentaLinea();
            vl.setLineaidLinea(linea);
            vl.setVentLinDeposito(BigDecimal.valueOf(15000.00));
            vl.setVentLinPrecio(BigDecimal.valueOf(linea.getPlan()
                    .getPlaCostoMinuto() * linea.getPlan().getPlaCantidadMinutos()));
            vl.setVentLinTipo(1);

            ventaLineaTemp.add(vl);
        }
        dto.setLineas(ventaLineaTemp);

        dto.setPagoTotal(verGridCuotas);
        dto.setValorAbono(valorAbonado);

        dto.setPagoConsignacion(verFormaPagos);
        bean.registrarVenta(dto);

    }

    public List<Tercero> completarBusqueda(String query) {
        terceros = terceroFacade.findByCriterio(query);
        return terceros;
    }

    public void seleccionarTercero(SelectEvent evt) {
        String valor = evt.getObject().toString();

        String id = obtenerId(valor);
        Integer val = Integer.parseInt(id);
        Tercero tercero = terceroFacade.findByDocumento(val);

        dto.setTercero(tercero);

    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @param valor
     *  @return
     */
    private String obtenerId(String valor) {

        String id = "";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(valor);
        while (m.find()) {
            id += m.group();
        }

        return id;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 2/06/2013
     * @return the bean
     */
    public VentaBean getBean() {
        return bean;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 2/06/2013
     * @param bean the bean to set
     */
    public void setBean(VentaBean bean) {
        this.bean = bean;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 2/06/2013
     * @param dto the dto to set
     */
    public void setDto(VentaDTO dto) {
        this.dto = dto;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 2/06/2013
     * @return the dto
     */
    public VentaDTO getDto() {
        return dto;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 2/06/2013
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 2/06/2013
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @return the lineasDisponibles
     */
    public List<Linea> getLineasDisponibles() {
        return lineasDisponibles;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @param lineasDisponibles the lineasDisponibles to set
     */
    public void setLineasDisponibles(List<Linea> lineasDisponibles) {
        this.lineasDisponibles = lineasDisponibles;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @return the lineasSeleccionadas
     */
    public ArrayList<Linea> getLineasSeleccionadas() {
        return lineasSeleccionadas;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @param lineasSeleccionadas the lineasSeleccionadas to set
     */
    public void setLineasSeleccionadas(ArrayList<Linea> lineasSeleccionadas) {
        this.lineasSeleccionadas = lineasSeleccionadas;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @return the model
     */
    public ListaDataModel getModel() {
        model = new ListaDataModel() {
            @Override
            public LineaFacade getFacade() {
                // TODO Auto-generated method stub
                return lineaFacade;
            }
        };
        return model;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @param model the model to set
     */
    public void setModel(ListaDataModel model) {
        this.model = model;
    }

    public String adicionarCarrito() {

        if (!lineasSeleccionadas.contains(seleccionada)) {
            lineasSeleccionadas.add(seleccionada);
            double d = seleccionada.getPlan().getPlaCostoMinuto()
                    * seleccionada.getPlan().getPlaCantidadMinutos();
            subtotal += (d);
            totalDepositos += (15000.00);
            total = subtotal + totalDepositos + descuentos + domicilio;

        }

        return null;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @return the listener
     */
    public CustomListener getListener() {
        return listener;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 3/06/2013
     * @param listener the listener to set
     */
    public void setListener(CustomListener listener) {
        this.listener = listener;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the descuentos
     */
    public double getDescuentos() {
        return descuentos;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param descuentos the descuentos to set
     */
    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the domicilio
     */
    public double getDomicilio() {
        return domicilio;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(double domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the lineaFacade
     */
    public LineaFacade getLineaFacade() {
        return lineaFacade;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param lineaFacade the lineaFacade to set
     */
    public void setLineaFacade(LineaFacade lineaFacade) {
        this.lineaFacade = lineaFacade;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the seleccionada
     */
    public Linea getSeleccionada() {
        return seleccionada;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param seleccionada the seleccionada to set
     */
    public void setSeleccionada(Linea seleccionada) {
        this.seleccionada = seleccionada;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param subtotal the subtotal to set
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the terceroFacade
     */
    public TerceroFacade getTerceroFacade() {
        return terceroFacade;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param terceroFacade the terceroFacade to set
     */
    public void setTerceroFacade(TerceroFacade terceroFacade) {
        this.terceroFacade = terceroFacade;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the terceros
     */
    public List<Tercero> getTerceros() {
        return terceros;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param terceros the terceros to set
     */
    public void setTerceros(List<Tercero> terceros) {
        this.terceros = terceros;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the totalDepositos
     */
    public double getTotalDepositos() {
        return totalDepositos;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param totalDepositos the totalDepositos to set
     */
    public void setTotalDepositos(double totalDepositos) {
        this.totalDepositos = totalDepositos;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @return the formaPago
     */
    public String getFormaPago() {
        return formaPago;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 10/06/2013
     * @param formaPago the formaPago to set
     */
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 11/06/2013
     * @return the proxFechaPago
     */
    public Date getProxFechaPago() {
        return proxFechaPago;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 11/06/2013
     * @param proxFechaPago the proxFechaPago to set
     */
    public void setProxFechaPago(Date proxFechaPago) {
        this.proxFechaPago = proxFechaPago;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 11/06/2013
     * @return the valorAbonado
     */
    public double getValorAbonado() {
        return valorAbonado;
    }

    /**
     * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
     * @date 11/06/2013
     * @param valorAbonado the valorAbonado to set
     */
    public void setValorAbonado(double valorAbonado) {
        this.valorAbonado = valorAbonado;
    }

    public boolean isVerGridCuotas() {
        return verGridCuotas;
    }

    public void setVerGridCuotas(boolean verGridCuotas) {
        this.verGridCuotas = verGridCuotas;
    }

    public boolean isVerFormaPagos() {
        return verFormaPagos;
    }

    public void setVerFormaPagos(boolean verFormaPagos) {
        this.verFormaPagos = verFormaPagos;
    }

    public void setSelIdCuenta(String selIdCuenta) {
        this.selIdCuenta = selIdCuenta;
    }

    public String getSelIdCuenta() {
        return selIdCuenta;
    }
}
