/**
 * 
 */
package com.invte.rentavoz.vista.tarea.anulacion.venta;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.almacen.EstadoVentaEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.entidades.tareas.Tarea;
import co.com.rentavoz.logica.jpa.fachadas.TareaFacade;
import co.com.rentavoz.logica.jpa.fachadas.VentaFacade;

import com.invte.rentavoz.logica.tarea.TareaBean;
import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.SessionParams;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class DetalleAnulacionVenta
 * @date 3/09/2013
 *
 */
@ManagedBean
@ViewScoped
public class DetalleAnulacionVenta extends BaseBean implements Serializable {

	/**
	 * 4/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TAREA_BEAN_NAME
	 */
	private static final String TAREA_BEAN_NAME = "#{tareaBean}";

	/**
	 * 4/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * TITLE_ANULACION
	 */
	private static final String TITLE_ANULACION = "VENTA ANULADA";

	/**
	 * 4/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * MSG_ANULADO
	 */
	private static final String MSG_ANULADO = "Se ha anulado la venta con exito";

	/**
	 * 3/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4205537100846826970L;
	
	private Venta venta;
	
	private Tarea tarea;
	
	@EJB
	private VentaFacade ventaFacade;
	
	

	@EJB
	private TareaFacade tareaFacade;
	
	
	@ManagedProperty(value=TAREA_BEAN_NAME)
	private TareaBean tareaBean;
	
	@PostConstruct
	public void init(){
		try {
			
		int idVenta = Integer.valueOf(getAttribute(SessionParams.TASK_CODE_ENTITY).toString());
		removeAttribute(SessionParams.TASK_CODE_ENTITY);
		
		venta=ventaFacade.find(idVenta);
		
		
		int idTarea = Integer.valueOf(getAttribute(SessionParams.TASK_CODE).toString());
		removeAttribute(SessionParams.TASK_CODE);
		
		
		tarea=tareaFacade.find(idTarea);
		
		
		} catch (Exception e) {
		mensajeError(e.toString());
		}
	}

	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 3/09/2013
	 */
	public String anularVenta(){
		venta.setEstadoVenta(EstadoVentaEnum.ANULADA);
		ventaFacade.edit(venta);
		tareaBean.cerrarTarea(tarea);
		mensaje(TITLE_ANULACION, MSG_ANULADO);
		killBean(TAREA_BEAN_NAME);
		return "/dashboard.jsf";
		
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @return the venta
	 */
	public Venta getVenta() {
		return venta;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @param venta the venta to set
	 */
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @return the tarea
	 */
	public Tarea getTarea() {
		return tarea;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @param tarea the tarea to set
	 */
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @param tareaBean the tareaBean to set
	 */
	public void setTareaBean(TareaBean tareaBean) {
		this.tareaBean = tareaBean;
	}
	
	
}

