/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.empresa;

import co.com.rentavoz.logica.jpa.entidades.Empresa;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.EmpresaFacade;
import com.invte.rentavoz.vista.StandardAbm;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.invte.rentavoz.vista.StandardAbm;
import java.io.Serializable;
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
public class BeanEmpresa extends StandardAbm<Empresa>implements  Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6878840473274695937L;
	@EJB
    private  EmpresaFacade empresaFacade;
    
    @Override
    public AbstractFacade<Empresa> getFacade() {
       return empresaFacade;
    }

    @Override
    public Empresa getInstancia() {
     return    new Empresa();
    }

    @Override
    public String reglaNavegacion() {
        return "/paginas/maestras/empresa/index.jsf";
    }

    @Override
    public Empresa getObjeto() {
        return  obtenerObjeto();
    }

    @Override
    public List<Empresa> getListado() {
        return  obtenerListado();
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public boolean preAction() {
        if(!isEdit()){
            
        if (getFacade().find(getObjeto().getIdEmpresa())==null) {
            return true;
        }else{
            mensaje("Error", "El codigo de la empresa ya esta siendo utilizado , por favor digita otro código");
        return false;
        }
        }else{
        return true;
        }
    }

    
    @Override
    public void buscarrPorCriterio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
