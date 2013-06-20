/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.operador;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import co.com.rentavoz.logica.jpa.entidades.Operador;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.OperadorFacade;

import com.invte.rentavoz.vista.StandardAbm;

/**
 *
 * @author ejody
 */
@ManagedBean
@ViewScoped
public class BeanOperador extends StandardAbm<Operador> implements Serializable {

    @EJB
    private OperadorFacade departamentoFacade;

    @Override
    public AbstractFacade<Operador> getFacade() {
        return departamentoFacade;
    }

    @Override
    public Operador getInstancia() {
        return new Operador();
    }

    @Override
    public String reglaNavegacion() {
        return "/paginas/maestras/operador/index.jsf";
    }

    @Override
    public Operador getObjeto() {
        return obtenerObjeto();
    }

    @Override
    public List<Operador> getListado() {
        return obtenerListado();
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void buscarrPorCriterio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public boolean preAction() {
//        if (!isEdit()) {
//
//            if (getFacade().find(getObjeto().getIdOperador()) == null) {
//                return true;
//            } else {
//                mensaje("Codigo ya esta en uso", "Este codigo ya esta en uso para el nuevo operador");
//                return false;
//            }
//        } else {
//            return true;
//        }
//    }

   
}
