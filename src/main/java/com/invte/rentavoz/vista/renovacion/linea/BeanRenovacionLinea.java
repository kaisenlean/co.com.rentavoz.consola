/**
 * 
 */
package com.invte.rentavoz.vista.renovacion.linea;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import co.com.rentavoz.logica.jpa.entidades.almacen.VentaLinea;
import co.com.rentavoz.logica.venta.VentaBean;

import com.invte.rentavoz.vista.BaseBean;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanRenovacionLinea
 * @date 2/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanRenovacionLinea extends BaseBean implements Serializable {

	
	private static final String RENOVACION = "renovacion";

	private static final String DEVOLUCION = "devolucion";

	private static final String ID_VENTA_LINEA = "idVentaLinea";

	private static final long serialVersionUID = -1678986432638785613L;

	@EJB
	private VentaBean ventaBean;

	private Date fin;

	private Date inicio;

	private List<VentaLinea> lista;

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 */
	@PostConstruct
	public void init() {
		inicio = new Date();
		fin = new Date();
		lista = ventaBean.cargarLineasPendientesPorReposicion(inicio, fin);
	}

	public void buscarLineas() {
		lista = ventaBean.cargarLineasPendientesPorReposicion(inicio, fin);

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param vl
	 * @return
	 */
	public String goDetalle(VentaLinea vl) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove(ID_VENTA_LINEA);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove(RENOVACION);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(ID_VENTA_LINEA, vl);
		return "/paginas/almacen/venta/linea/detallarLineaVenta";

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 15/07/2013
	 * @param vl
	 * @return
	 */
	public String goDetalleDEvolucion(VentaLinea vl) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.clear();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(ID_VENTA_LINEA, vl);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(DEVOLUCION, true);

		return "/paginas/almacen/venta/linea/detallarLineaVenta";

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @return the ventaBean
	 */
	public VentaBean getVentaBean() {
		return ventaBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param ventaBean
	 *            the ventaBean to set
	 */
	public void setVentaBean(VentaBean ventaBean) {
		this.ventaBean = ventaBean;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @return the fin
	 */
	public Date getFin() {
		return fin;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param fin
	 *            the fin to set
	 */
	public void setFin(Date fin) {
		this.fin = fin;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @return the inicio
	 */
	public Date getInicio() {
		return inicio;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param inicio
	 *            the inicio to set
	 */
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @return the lista
	 */
	public List<VentaLinea> getLista() {
		return lista;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 14/07/2013
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<VentaLinea> lista) {
		this.lista = lista;
	}

}
