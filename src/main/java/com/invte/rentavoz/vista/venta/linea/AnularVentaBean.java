/**
 * 
 */
package com.invte.rentavoz.vista.venta.linea;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;

import com.invte.rentavoz.logica.tarea.TareaBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class AnularVentaBean
 * @date 15/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class AnularVentaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private VentaFacade ventaFacade;
	private List<Venta> lista;
	
	
	@ManagedProperty(value="#{tareaBean}")
	private TareaBean tareaBean;
	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 */
	@PostConstruct
	public void init() {
		lista = ventaFacade.findAll();

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the ventaFacade
	 */
	public VentaFacade getVentaFacade() {
		return ventaFacade;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param ventaFacade
	 *            the ventaFacade to set
	 */
	public void setVentaFacade(VentaFacade ventaFacade) {
		this.ventaFacade = ventaFacade;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @return the lista
	 */
	public List<Venta> getLista() {
		return lista;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<Venta> lista) {
		this.lista = lista;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 */
	public void anularVentaSeleccionadas() {
		for (Venta venta : lista) {
			if (venta.isSeleccionado()) {
				tareaBean.enviarSolicitudAnulacionVenta(venta);
			}
		}
		init();

	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param tareaBean the tareaBean to set
	 */
	public void setTareaBean(TareaBean tareaBean) {
		this.tareaBean = tareaBean;
	}
	
	
	

}
