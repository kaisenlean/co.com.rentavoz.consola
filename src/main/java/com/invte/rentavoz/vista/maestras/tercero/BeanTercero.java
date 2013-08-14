/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.tercero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.entidades.TipoTerceroEnum;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

import com.invte.rentavoz.vista.SessionParams;
import com.invte.rentavoz.vista.StandardAbm;

/**
 * 
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanTercero
 * @date 24/07/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanTercero extends StandardAbm<Tercero> implements Serializable {

	/**
	 * 9/08/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * MSG_SELECCIONA_UN_TERCERO
	 */
	private static final String MSG_SELECCIONA_UN_TERCERO = "- - Selecciona un tipo de tercero - -";
	/**
	 * 9/08/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * SPACE
	 */
	private static final String SPACE = " ";
	/**
	 * 9/08/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * UNDERLINE_VALUE
	 */
	private static final String UNDERLINE_VALUE = "_";
	private static final long serialVersionUID = 1L;
	@EJB
	private TerceroFacade terceroFacade;

	private String tipo;

	private List<SelectItem> itemTipo = new ArrayList<SelectItem>();
	private boolean callByOtherModule;

	@Override
	public AbstractFacade<Tercero> getFacade() {
		return terceroFacade;
	}

	@Override
	public Tercero getInstancia() {
		return new Tercero();
	}

	@Override
	public String reglaNavegacion() {
		return "/paginas/maestras/tercero/index.jsf";
	}

	@Override
	public Tercero getObjeto() {
		return obtenerObjeto();
	}

	@Override
	public List<Tercero> getListado() {
		return obtenerListado();
	}

	@Override
	public void initialize() {
		setObjeto(new Tercero());
		itemTipo.clear();
		TipoTerceroEnum[] enums = TipoTerceroEnum.values();
		itemTipo.add(new SelectItem("", MSG_SELECCIONA_UN_TERCERO));
		for (int i = 0; i < enums.length; i++) {
			itemTipo.add(new SelectItem(enums[i].name(), enums[i].name()
					.replace(UNDERLINE_VALUE, SPACE)));
		}
		if (getAttribute(SessionParams.CREATE_TERCERO_ON_LOAD)!=null) {
			callByOtherModule=true;
			form=true;
			
		}
	}

/**
 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
 */
@Override
public void postAction() {
if (callByOtherModule) {
	String reglaNav=(String) getAttribute(SessionParams.MODULE_URI);
	removeAttribute(SessionParams.MODULE_URI);
	removeAttribute(SessionParams.CREATE_TERCERO_ON_LOAD);
	addAttribute(SessionParams.ENTITY_BACK, getObjeto());
	reglaNavegacionAlterna=reglaNav;
}
}
	
	@Override
	public boolean preAction() {
		if (tipo != null) {
			getObjeto().setTipo(
					TipoTerceroEnum.valueOf(TipoTerceroEnum.class, tipo));
			return true;
		} else {
			return true;
		}
		// return validarClaves();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preRenderizarItem()
	 */
	@Override
	public void preRenderizarItem() {
		if (getObjeto().getTipo() != null) {
			tipo = getObjeto().getTipo().name();
		}
	}

	@Override
	public void buscarrPorCriterio() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the itemTipo
	 */
	public List<SelectItem> getItemTipo() {
		return itemTipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param itemTipo
	 *            the itemTipo to set
	 */
	public void setItemTipo(List<SelectItem> itemTipo) {
		this.itemTipo = itemTipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 24/07/2013
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @return the callByOtherModule
	 */
	public boolean isCallByOtherModule() {
		return callByOtherModule;
	}
	/**
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 9/08/2013
	 * @param callByOtherModule the callByOtherModule to set
	 */
	public void setCallByOtherModule(boolean callByOtherModule) {
		this.callByOtherModule = callByOtherModule;
	}
}
