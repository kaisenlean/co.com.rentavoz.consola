/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.logica.sesion;

import co.com.rentavoz.logica.jpa.entidades.Menu;
import co.com.rentavoz.logica.jpa.fachadas.MenuFacade;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ejody
 */
@ManagedBean
@SessionScoped
public class AdministradorMenus implements Serializable {

    private List<Menu> menus;
    @EJB
    private MenuFacade menuFacade;

    @PostConstruct
    public void init() {
        try {
           menus = menuFacade.findAll();
        } catch (Exception e) {
           menus=new ArrayList<Menu>();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Capsulas">
    public MenuFacade getMenuFacade() {
        return menuFacade;
    }

    public void setMenuFacade(MenuFacade menuFacade) {
        this.menuFacade = menuFacade;
    }
    
    
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    //</editor-fold>
}
