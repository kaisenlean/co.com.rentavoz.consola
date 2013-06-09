/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.selector;

import co.com.rentavoz.logica.jpa.entidades.EstadoLinea;
import co.com.rentavoz.logica.jpa.fachadas.EstadoLineaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class SelectorEstadoLinea implements SelectorBase<EstadoLinea> {
    
    @EJB
    private EstadoLineaFacade facade;
    private ArrayList<SelectItem> items;
    
    @PostConstruct
    public void init() {
        List<EstadoLinea> findAll = facade.findAll();
        items = new ArrayList<SelectItem>();
        items.add(new SelectItem(" ", "-- Seleccione un estado --"));
        for (EstadoLinea estadoLinea : findAll) {
            items.add(new SelectItem(estadoLinea.getIdEstadoLinea()+"", estadoLinea.getEstLinNombre()));
        }
    }

    
    public List<SelectItem> getItems() {
        return items;
    }
}
