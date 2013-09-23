/**
 * 
 */
package com.invte.rentavoz.vista.almacen.pago;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.almacen.Cuota;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoCuotaEnum;
import co.com.rentavoz.logica.jpa.fachadas.CuotaFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.componentes.autocomplete.tercero.AutocompleteTercero;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class ConsultaPagosBean
 * @date 4/09/2013
 *
 */
@ManagedBean
@ViewScoped
public class ConsultaPagosBean extends BaseBean implements Serializable {

	/**
	 * 6/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * REGLA_NAVEGACION
	 */
	@SuppressWarnings("unused")
	private static final String REGLA_NAVEGACION = "/paginas/almacen/pago/inicial.jsf";

	/**
	 * 4/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5009223951628373250L;

	@EJB
	private CuotaFacade cuotaFacade;
	
	private List<Cuota> lista;
	
	
	@EJB
	private TerceroFacade  terceroFacade;
	
	private Tercero tercero;
	
	
	private AutocompleteTercero autocompleteTercero;
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 6/09/2013
	 */
	@PostConstruct
	public void init(){
		autocompleteTercero=new AutocompleteTercero() {
			
			@Override
			public void postSelect() {
			tercero=seleccionado;	
			lista=cuotaFacade.buscarCuotasPorCliente(tercero);
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
	public 	 String realizarPago(){
	
		for (Cuota cuota : lista) {
			if (cuota.isSeleccionada()) {
				cuota.setEstadoCuota(EstadoCuotaEnum.PAGADA);
				cuotaFacade.edit(cuota);
				
			}
		}
		runJavascript("confirmation.hide();");
		
		return "";
		
	}
	


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @return the tercero
	 */
	public Tercero getTercero() {
		return tercero;
	}


	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param tercero the tercero to set
	 */
	public void setTercero(Tercero tercero) {
		this.tercero = tercero;
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
	 * @return the lista
	 */
	public List<Cuota> getLista() {
		return lista;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 6/09/2013
	 * @param lista the lista to set
	 */
	public void setLista(List<Cuota> lista) {
		this.lista = lista;
	}
	
	
	
	
}
