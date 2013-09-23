/**
 * 
 */
package com.invte.rentavoz.logica.tarea;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.almacen.Venta;
import co.com.rentavoz.logica.jpa.entidades.tareas.EstadoTareaEnum;
import co.com.rentavoz.logica.jpa.entidades.tareas.Tarea;
import co.com.rentavoz.logica.jpa.fachadas.CentropeFacade;
import co.com.rentavoz.logica.jpa.fachadas.TareaFacade;
import co.com.rentavoz.logica.owner.OwnerManager;

import com.invte.rentavoz.vista.BaseBean;
import com.invte.rentavoz.vista.Constants;
import com.invte.rentavoz.vista.PageLocations;
import com.invte.rentavoz.vista.SessionParams;
import com.invte.rentavoz.vista.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class TareaBean
 * @date 2/09/2013
 *
 */
@ManagedBean
@ViewScoped
public class TareaBean extends BaseBean implements Serializable
{

	/**
	 * 2/09/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	
	@EJB
	private TareaFacade tareaFacade;

	@ManagedProperty(value="#{login}")
	private Login login;
	
	
	@EJB
	private OwnerManager ownerManager;
	
	
	@EJB
	private CentropeFacade centropeFacade;
	
	
	private List<Tarea> tareas;
	
	
	@PostConstruct
	public void init(){
	
		if (login!=null) {
			if (login.getTercero()!=null) {
				tareas=tareaFacade.findByCentrope(login.getTercero().getCentrope().getId());
			}
		}
		
	}
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 3/09/2013
	* @param tarea
	* @return
	 */
	public String loadTask(Tarea tarea){
		
		addAttribute(SessionParams.TASK_CODE, tarea.getIdTarea());
		addAttribute(SessionParams.TASK_CODE_ENTITY,tarea.getCodTarea());
	
		return tarea.getGoDir();
	}
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 2/09/2013
	* @param v
	* @return
	 */
	public boolean enviarSolicitudAnulacionVenta(Venta v){
		try {
		Tarea temp= new Tarea();
		temp.setNombre("Solicitud de anulación de venta");
		temp.setDescripcion("El tercero : "+login.getTercero().toString()+" solicita la anulacion de la venta con código : "+v.getIdVenta());
		temp.setFechaCreacion(new Date());
		temp.setEstado(EstadoTareaEnum.PENDIENTE);
		temp.setCreador(login.getUsuario());
		temp.setCodTarea(String.valueOf(v.getIdVenta()));
		temp.setGoDir(PageLocations.URI_DETALLE_ANULACION);
		
		temp.setOwner(centropeFacade.find(Constants.CENTROPE_ADMINISTRATIVO));
		tareaFacade.create(temp);
		
		mensaje("Terminado", "Se ha creado la tarea satisfactoriamente");
		return true;
		} catch (Exception e) {
		mensajeError(e.toString());
		
		return false;
		}
		
		
	}
	
	/**
	 * 
	* @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	* @date 2/09/2013
	* @param tarea
	 */
	public void cerrarTarea(Tarea tarea){
		tarea.setEstado(EstadoTareaEnum.FINALIZADA);
		tareaFacade.edit(tarea);
		mensaje("Terminado", "Se ha cerrado la tarea satisfactoriamente");
		
	}
	
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @param login the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 2/09/2013
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}
	
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @return the tareas
	 */
	public List<Tarea> getTareas() {
		return tareas;
	}
	
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 3/09/2013
	 * @param tareas the tareas to set
	 */
	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
	
}
