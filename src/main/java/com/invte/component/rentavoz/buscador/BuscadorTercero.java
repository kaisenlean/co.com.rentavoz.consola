/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;




import co.com.rentavoz.logica.jpa.entidades.Tercero;
import co.com.rentavoz.logica.jpa.fachadas.TerceroFacade;

import java.util.List;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ejody
 */
public abstract class BuscadorTercero extends Buscador<Tercero> {

    public abstract TerceroFacade getFacade();


    public abstract void selCentrope(Tercero centrope);
    
    
    @Override
        public String buscar() {
        List<Tercero> result = getFacade().findByCriterio(getCriterio());

        getItems().clear();

        for (Tercero centrope : result) {
            getItems().add(new BuscadorItem(centrope.getIdTecero()+"", centrope.toString(), centrope));
        }
        return null;
    }

    @Override
    public void asignar(Tercero t) {
        if (t == null) {
            selCentrope(new Tercero());
        } else {
            selCentrope(t);
        }
    }

    /**
     *
     * @param evt
     */
    @Override
    public void mostrar(ActionEvent evt) {
        List<Tercero> result = getFacade().findByCriterio(getCriterio());

        getItems().clear();

        for (Tercero centrope : result) {
            getItems().add(new BuscadorItem(centrope.getIdTecero()+"", centrope.toString(), centrope));
        }
        super.mostrar(evt);
    }
    
}
