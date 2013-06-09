/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.selector;

import co.com.rentavoz.logica.jpa.entidades.Empresa;
import co.com.rentavoz.logica.jpa.fachadas.EmpresaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class SelectorEmpresa implements SelectorBase<Empresa> {
    
    @EJB
    private EmpresaFacade facade;
    private ArrayList<SelectItem> items;
    
    @PostConstruct
    public void init() {
        List<Empresa> findAll = facade.findAll();
        items = new ArrayList<SelectItem>();
        items.add(new SelectItem(" ", "-- Seleccione una empresa --"));
        for (Empresa empresa : findAll) {
            items.add(new SelectItem(empresa.getIdEmpresa(), empresa.getEmpNombre()));
        }
    }

    
    public List<SelectItem> getItems() {
        return items;
    }
}
