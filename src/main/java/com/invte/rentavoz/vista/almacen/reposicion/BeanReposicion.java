package com.invte.rentavoz.vista.almacen.reposicion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.almacen.Cuota;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoCuotaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoVentaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadosSimcardEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Linea;
import co.com.rentavoz.logica.jpa.entidades.almacen.ModalidaVentaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.MotivoRepoEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Reposiciondll;
import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;
import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.ReposiciondllFacade;
import co.com.rentavoz.logica.jpa.fachadas.SimcardFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;
import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;

import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.componentes.autocomplete.linea.AutocompleteLinea;
import com.invte.rentavoz.vista.componentes.autocomplete.simcard.AutocompleteSimcard;
import com.invte.rentavoz.vista.componentes.autocomplete.tercero.AutocompleteTercero;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanReposicion
 * @date 6/09/2013
 *
 */
@ManagedBean
@ViewScoped
public class BeanReposicion extends BaseBean implements Serializable {

	/**
	 * 6/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4079740791477908706L;
	
	
	@EJB
	private LineaFacade lineaFacade;
	
	
	@EJB
	private SimcardFacade simcardFacade;

	@EJB
	private TerceroFacade terceroFacade;
	
	
	@EJB
	private VentaFacade ventaFacade;
	
	@EJB
	private ReposiciondllFacade reposiciondllFacade;
	
	
	private String motivoRepoVal;
	
	private AutocompleteLinea autocompleteLinea;
	private AutocompleteSimcard autocompleteSimcard;
	private AutocompleteTercero autocompleteTercero;
	
	private String observacion;
	
	
	private double valorRepo=15000.0;
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	 */
	@PostConstruct
	public void init(){
		autocompleteLinea=new AutocompleteLinea() {
			
			@Override
			public void postSelect() {
				
			}
			
			@Override
			public LineaFacade getFacade() {
				return lineaFacade;
			}
		};
		
		autocompleteSimcard= new AutocompleteSimcard() {
			
			@Override
			public void postSelect() {
				
			}
			
			@Override
			public SimcardFacade getFacade() {
				return simcardFacade;
			}
		};
		autocompleteTercero = new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
				
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
	* @date 6/09/2013
	* @return
	 */
	public String registrarReposicion(){
		try {
			
		Venta venta = new Venta();
		venta.setModalidadVenta(ModalidaVentaEnum.REPOSICION);
		venta.setFecha(new Date());
		venta.setTercero(autocompleteTercero.getSeleccionado());
		venta.setVenFecha(new Date());
		venta.setVenSaldo(BigDecimal.valueOf(valorRepo));
		venta.setObservacion(observacion);
		List<Cuota> cuotas = new ArrayList<Cuota>();
		Cuota cuota = new Cuota();
		cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
		cuota.setFechaPago(new Date());
		cuota.setValorCuota(BigDecimal.valueOf(valorRepo));
		cuotas.add(cuota);
		venta.setCuotas(cuotas);
		venta.setObservacion(observacion);
		venta.setEstadoVenta(EstadoVentaEnum.ACTIVA);
		venta.setFechaRenovacion(new Date());
		venta.setVenDomicilio(BigDecimal.valueOf(0));
		
		
		ventaFacade.create(venta);
		
		Reposiciondll dll= new Reposiciondll();
		dll.setVenta(venta);
		dll.setMotivoRepo(MotivoRepoEnum.valueOf(motivoRepoVal));
		dll.setObservacion(observacion);
		reposiciondllFacade.create(dll);
		
		
		Simcard simcard=autocompleteLinea.getSeleccionado().getSimcard();
		simcard.setSimEstado(EstadosSimcardEnum.DESECHADA);
		 simcardFacade.edit(simcard);
		
		Linea linea = autocompleteLinea.getSeleccionado();
		linea.setSimcard(autocompleteSimcard.getSeleccionado());
		 lineaFacade.edit(linea);
		 
		 
		 Simcard sim = autocompleteSimcard.getSeleccionado();
		 sim.setSimEstado(EstadosSimcardEnum.ASIGNADA); 
		 simcardFacade.edit(sim);
		 		
		mensaje("Reposición exitosa !!", "Se ha registrado la reposicion con exito");
		
		} catch (Exception e) {
			mensajeError(e.toString());
		}
		
	
		
		
		
		
		return "";
		
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the autocompleteLinea
	 */
	public AutocompleteLinea getAutocompleteLinea() {
		return autocompleteLinea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the autocompleteSimcard
	 */
	public AutocompleteSimcard getAutocompleteSimcard() {
		return autocompleteSimcard;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param autocompleteLinea the autocompleteLinea to set
	 */
	public void setAutocompleteLinea(AutocompleteLinea autocompleteLinea) {
		this.autocompleteLinea = autocompleteLinea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param autocompleteSimcard the autocompleteSimcard to set
	 */
	public void setAutocompleteSimcard(AutocompleteSimcard autocompleteSimcard) {
		this.autocompleteSimcard = autocompleteSimcard;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the autocompleteTercero
	 */
	public AutocompleteTercero getAutocompleteTercero() {
		return autocompleteTercero;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param autocompleteTercero the autocompleteTercero to set
	 */
	public void setAutocompleteTercero(AutocompleteTercero autocompleteTercero) {
		this.autocompleteTercero = autocompleteTercero;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the valorRepo
	 */
	public double getValorRepo() {
		return valorRepo;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param valorRepo the valorRepo to set
	 */
	public void setValorRepo(double valorRepo) {
		this.valorRepo = valorRepo;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	 /**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the motivoRepoVal
	 */
	public String getMotivoRepoVal() {
		return motivoRepoVal;
	}
	 /**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param motivoRepoVal the motivoRepoVal to set
	 */
	public void setMotivoRepoVal(String motivoRepoVal) {
		this.motivoRepoVal = motivoRepoVal;
	}
	
	
}
