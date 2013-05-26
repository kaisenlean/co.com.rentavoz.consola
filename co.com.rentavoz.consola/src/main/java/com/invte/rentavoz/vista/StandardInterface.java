/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista;

import javax.annotation.PostConstruct;

/**
 *
 * @author ejody
 */
public interface StandardInterface<T> {
    
    @PostConstruct
    public  void init();
//      public   abstract AbstractFacade<T > getFacade();
    public  void renderizarItem(T objeto,boolean  showForm);
    public  void eliminarItem(T objeto);
    public void verForm();
    public  void verFormNuevo(boolean showForm);
    public  String  aceptar();
     public  void  buscarrPorCriterio();
    
}
