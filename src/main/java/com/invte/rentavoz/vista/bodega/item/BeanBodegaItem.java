/**
 * 
 */
package com.invte.rentavoz.vista.bodega.item;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import co.com.rentavoz.logica.jpa.entidades.bodega.BodegaItem;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.bodega.BodegaItemFacade;

import com.invte.rentavoz.vista.StandardAbm;
import com.sun.faces.context.ExternalContextImpl;

/**
 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
 * @project co.com.rentavoz.consola
 * @class BeanBodegaItem
 * @date 6/10/2013
 * 
 */
@ManagedBean
@ViewScoped
public class BeanBodegaItem extends StandardAbm<BodegaItem> implements
		Serializable {

	/**
	 * 21/10/2013
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * DIAS_GARANTIA_DEFAULT
	 */
	private static final int DIAS_GARANTIA_DEFAULT = 30;

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         REGLA_NAVEGACION
	 */
	private static final String REGLA_NAVEGACION = "/paginas/bodega/item/index.jsf";

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         serialVersionUID
	 */
	private static final long serialVersionUID = -1420225713129719870L;

	/**
	 * 6/10/2013
	 * 
	 * @author <a href="mailto:elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 *         bodegaItemFacade
	 */
	@EJB
	private BodegaItemFacade bodegaItemFacade;

	private InputStream in1;

	private String nombreArchivo;

	private OutputStream out;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getFacade()
	 */
	@Override
	public AbstractFacade<BodegaItem> getFacade() {
		return bodegaItemFacade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getInstancia()
	 */
	@Override
	public BodegaItem getInstancia() {
		return new BodegaItem();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#reglaNavegacion()
	 */
	@Override
	public String reglaNavegacion() {
		return REGLA_NAVEGACION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getObjeto()
	 */
	@Override
	public BodegaItem getObjeto() {
		return obtenerObjeto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#getListado()
	 */
	@Override
	public List<BodegaItem> getListado() {
		return obtenerListado();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#initialize()
	 */
	@Override
	public void initialize() {

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#postAction()
	 */
	@Override
	public void postAction() {

		try {
			if (in1 != null) {

				uploadFile();
			}
		} catch (Exception e) {
			mensajeError(e.toString());
		}
	}

	/* (non-Javadoc)
	 * @see com.invte.rentavoz.vista.StandardAbm#postFormNuevo()
	 */
	@Override
	public void postFormNuevo() {
		getObjeto().setDiasGarantia(DIAS_GARANTIA_DEFAULT);
	}
	
	/**
	 * (non-Javadoc)
	 * 
	 * @see com.invte.rentavoz.vista.StandardAbm#preAction()
	 */
	@Override
	public boolean preAction() {
		if (!isEdit()) {
			boolean existeRef= bodegaItemFacade.existReferencia(getObjeto().getReferencia());
			if (existeRef) {
				
				mensajeError("Esta referéncia ya está registrada en el sistema");
				return false;
			}
			
			
		}
		getObjeto().setReferencia(getObjeto().getReferencia().toUpperCase());
		return true;
	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param event
	 */
	public void uploadHandler(FileUploadEvent event) {
		try {
			nombreArchivo = new StringBuilder().append(
					detectarExtension(event.getFile().getFileName()))
					.toString();
			in1 = event.getFile().getInputstream();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @throws Exception
	 */
	public void uploadFile() throws Exception {
		ExternalContextImpl request;
		request = (ExternalContextImpl) FacesContext.getCurrentInstance()
				.getExternalContext();

		String path = request.getRealPath("/");

		out = new FileOutputStream(path + "foto_item" + "/"
				+ getObjeto().getId() + nombreArchivo);
		getObjeto().setFoto(getObjeto().getId() + nombreArchivo);
		if (in1 != null) {
			int b = 0;
			while (b != -1) {
				b = in1.read();
				if (b != -1) {
					out.write(b);

				}

			}
		}
		getFacade().edit(getObjeto());

	}

	/**
	 * 
	 * @author <a href="elmerdiazlazo@gmail.com">Elmer Jose Diaz Lazo</a>
	 * @date 7/10/2013
	 * @param file
	 * @return
	 */
	public String detectarExtension(String file) {
		for (int i = file.length() - 1; i > 0; i--) {
			if (file.charAt(i) == '.') {
				return file.substring(i, file.length());
			}
		}
		return null;
	}
}
