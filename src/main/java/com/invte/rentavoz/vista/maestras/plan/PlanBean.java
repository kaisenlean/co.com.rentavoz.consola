/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.plan;

import co.com.rentavoz.logica.jpa.entidades.Operador;
import co.com.rentavoz.logica.jpa.entidades.Plan;
import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.OperadorFacade;
import co.com.rentavoz.logica.jpa.fachadas.PlanFacade;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacadeImpl;
import com.invte.component.rentavoz.buscador.BuscadorOperador;
import com.invte.component.rentavoz.buscador.BuscadorTercero;
import com.invte.rentavoz.vista.StandardAbm;
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
public class PlanBean extends  StandardAbm<Plan>{

    @EJB
    private PlanFacade facade;
    
    @EJB
    private TerceroFacadeImpl terceroFacade;
    
    @EJB
    private OperadorFacade operadorFacade;
    
    private  BuscadorTercero buscadorTercero;
    private  BuscadorOperador buscadorOperador;
    
    private  Tercero tercero;
    private Operador operador;
    
    @Override
    public AbstractFacade<Plan> getFacade() {
       return  facade;
    }

    @Override
    public Plan getInstancia() {
     return  new Plan();
    }

    @Override
    public String reglaNavegacion() {
       return  "/paginas/maestras/plan/";
    }

    @Override
    public Plan getObjeto() {
       return obtenerObjeto();
    }

    @Override
    public List<Plan> getListado() {
       return obtenerListado();
    }

    @Override
    public void initialize() {
       buscadorOperador=new BuscadorOperador() {

           @Override
           public OperadorFacade getFacade() {
              return operadorFacade;
           }

           @Override
           public void selCentrope(Operador centrope) {
              operador=centrope;
           }
       };
       buscadorTercero=new BuscadorTercero() {

           @Override
           public TerceroFacadeImpl getFacade() {
              return terceroFacade;
           }

           @Override
           public void selCentrope(Tercero centrope) {
              tercero=centrope;
           }
       };
    }

    @Override
    public void buscarrPorCriterio() {
       
    }
        

    //<editor-fold defaultstate="collapsed" desc="CAPSULAS">
    public BuscadorTercero getBuscadorTercero() {
        return buscadorTercero;
    }

    public void setBuscadorTercero(BuscadorTercero buscadorTercero) {
        this.buscadorTercero = buscadorTercero;
    }

    public BuscadorOperador getBuscadorOperador() {
        return buscadorOperador;
    }

    public void setBuscadorOperador(BuscadorOperador buscadorOperador) {
        this.buscadorOperador = buscadorOperador;
    }

    public Tercero getTercero() {
        return tercero;
    }

    public void setTercero(Tercero tercero) {
        this.tercero = tercero;
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }
        //</editor-fold>

}
