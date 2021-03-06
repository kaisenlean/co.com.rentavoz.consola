/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.linea;

import co.com.rentavoz.logica.jpa.entidades.Linea;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.EmpresaFacade;
import co.com.rentavoz.logica.jpa.fachadas.EstadoLineaFacade;
import co.com.rentavoz.logica.jpa.fachadas.LineaFacade;
import com.invte.rentavoz.vista.StandardAbm;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class LineaBean extends StandardAbm<Linea> {

    @EJB
    private EmpresaFacade facade;
    @EJB
    private EstadoLineaFacade terceroFacade;
    @EJB
    private LineaFacade lineaFacade;
    private String empresa = null;
    private String estadoLinea = null;

    @Override
    public AbstractFacade<Linea> getFacade() {
        return lineaFacade;
    }

    @Override
    public Linea getInstancia() {
        return new Linea();
    }

    @Override
    public String reglaNavegacion() {
        return "/paginas/maestras/linea/index.jsf";
    }

    @Override
    public Linea getObjeto() {
        return obtenerObjeto();
    }

    @Override
    public List<Linea> getListado() {
        return obtenerListado();
    }

    @Override
    public void postFormNuevo() {
        getObjeto().setIdLinea(lineaFacade.nextCodigo());
        getObjeto().setFecha(new Date());
    }

    @Override
    public void initialize() {
    }

    @Override
    public void buscarrPorCriterio() {
    }

    @Override
    public void preRenderizarItem() {
        estadoLinea = getObjeto().getEstadoLineaidEstadoLinea().getIdEstadoLinea() + "";
        empresa = getObjeto().getEmpresaidEmpresa().getIdEmpresa() + "";
    }

    @Override
    public boolean preAction() {
        if (isEdit()) {
            if (empresa == null || estadoLinea == null) {
                mensaje("Error", "Para poder continuar por favor diligencia " + empresa == null ? "empresa" : "" + " " + estadoLinea == null ? "estado linea " : "");
                return false;
            } else {
                
            
                if(lineaFacade.findBNumero2(getObjeto().getLinNumero())) {
                    getObjeto().setEmpresaidEmpresa(facade.find(Integer.valueOf(empresa)));
                    getObjeto().setEstadoLineaidEstadoLinea(terceroFacade.find(Integer.parseInt(estadoLinea)));
                return true;
                }else{
                    mensaje("Error", "El numero de linea ya esta en uso por favor intente con otro numero");
                return false;
                }
            }
        } else {
            if (empresa == null || estadoLinea == null) {
                mensaje("Error", "Para poder continuar por favor diligencia " + empresa == null ? "empresa" : "" + " " + estadoLinea == null ? "estado linea " : "");
                return false;
            } else {
                if (lineaFacade.find(getObjeto().getIdLinea()) == null && lineaFacade.findBNumero(getObjeto().getLinNumero())) {
                    getObjeto().setEmpresaidEmpresa(facade.find(Integer.valueOf(empresa)));
                    getObjeto().setEstadoLineaidEstadoLinea(terceroFacade.find(Integer.parseInt(estadoLinea)));
                    return true;
                } else {
                    mensaje("Error", "El codigo o numero  de linea ya esta siendo utilizado");
                    return false;
                }
            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="CAPSULAS">
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEstadoLinea() {
        return estadoLinea;
    }

    public void setEstadoLinea(String estadoLinea) {
        this.estadoLinea = estadoLinea;
    }
    //</editor-fold>
}
