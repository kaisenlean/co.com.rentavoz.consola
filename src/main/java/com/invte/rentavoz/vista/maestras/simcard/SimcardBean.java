/**
 * 
 */
package com.invte.rentavoz.vista.maestras.simcard;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.SucursalSimcard;
import co.com.rentavoz.logica.jpa.entidades.almacen.EstadosSimcardEnum;
import co.com.rentavoz.logica.jpa.entidades.almacen.Simcard;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.SimcardFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalSimcardFacade;

import com.invte.rentavoz.vista.StandardAbm;
import com.invte.rentavoz.vista.session.Login;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class SimcardBean
 * @date 17/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class SimcardBean extends StandardAbm<Simcard> {

	/**
	 * 22/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/maestras/simcard/index.jsf";

	private static final long serialVersionUID = -1689462037286702729L;

	/**
	 * 22/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         estadoSim
	 */
	private String estadoSim;

	private String sucursal;

	/**
	 * 22/07/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         simcardFacade
	 */
	@EJB
	private SimcardFacade simcardFacade;

	@EJB
	private SucursalFacade sucursalFacade;

	@EJB
	private SucursalSimcardFacade sucursalSimcardFacade;

	@ManagedProperty(value = "#{login}")
	private Login login;

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public AbstractFacade<Simcard> getFacade() {

		return simcardFacade;
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
		getObjeto().setFecha(new Date());
		getObjeto().setSimEstado(EstadosSimcardEnum.DISPONIBLE);
		estadoSim = getObjeto().getEstadoAsString();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public Simcard getInstancia() {

		return new Simcard();
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {

		return REGLA_NAVEGACION;
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public Simcard getObjeto() {

		return obtenerObjeto();
	}

	/**
	 * 
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<Simcard> getListado() {
		return obtenerListado();
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 23/07/2013
	 * @return
	 */
	public String guardarSim() {
		try {
			if (!isEdit()) {

				if (simcardFacade.findByScId(getObjeto().getSimIccid()) == null) {

					aceptar();
					guardarHistoriaSucursal();
					setObjeto(getInstancia());
					getObjeto().setFecha(new Date());
					return "";
				} else {
					mensajeError("Ya existe una Sim card con este SCID");
					return "";

				}
			} else {
				aceptar();

				setObjeto(getInstancia());
				getObjeto().setFecha(new Date());
				return reglaNavegacion();
			}

		} catch (Exception e) {
			mensajeError("Error al guardar los datos " + e.toString());
			return "";
		}
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 */
	public void guardarHistoriaSucursal() {

		if (!isEdit()) {
			SucursalSimcard ss = new SucursalSimcard();
			ss.setSimcardidSimcard(getObjeto());
			ss.setSucursalidSucursal(getObjeto().getSucursal());
			ss.setSucSimEstado(0);
			ss.setSucSimObservacion(" ");
			ss.setFecha(new Date());
			sucursalSimcardFacade.create(ss);

		}
	}

	/**
	 * @see com.invte.rentavoz.vista.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {

		estadoSim = getObjeto().getEstadoAsString();
		if (getObjeto().getSucursal() != null) {
			sucursal = String
					.valueOf(getObjeto().getSucursal().getIdSucursal());
		}
	}

	/**
	 * 
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {
		estadoSim = "";

	}

	/**
	 * * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		try {

			getObjeto().setSimEstado(
					EstadosSimcardEnum.valueOf(EstadosSimcardEnum.class,
							estadoSim));
			getObjeto().setSucursal(
					sucursalFacade.find(Integer.valueOf(sucursal)));
			if (!isEdit()) {

				if (simcardFacade.findByScId(getObjeto().getSimIccid()) == null) {
					return true;
				} else {
					mensajeError("Este ICCID ya está siendo utilizado , por favor intente con otro");
					return false;
				}
			} else {

				return true;
			}
		} catch (Exception e) {
			mensajeError(e.toString());
			return false;
		}

	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @return the estadoSim
	 */
	public String getEstadoSim() {
		return estadoSim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 17/07/2013
	 * @param estadoSim
	 *            the estadoSim to set
	 */
	public void setEstadoSim(String estadoSim) {
		this.estadoSim = estadoSim;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the login
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param login
	 *            the login to set
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
}
