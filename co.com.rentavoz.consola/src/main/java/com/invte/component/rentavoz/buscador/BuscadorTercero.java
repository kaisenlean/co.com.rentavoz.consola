/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;




import co.com.rentavoz.logica.jpa.entidades.Ciudad;
import co.com.rentavoz.logica.jpa.entidades.Departamento;
import co.com.rentavoz.logica.jpa.entidades.Operador;
import co.com.rentavoz.logica.jpa.fachadas.CiudadFacade;
import co.com.rentavoz.logica.jpa.fachadas.OperadorFacade;

import java.util.List;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ejody
 */
public abstract class BuscadorTercero extends Buscador<Operador> {

    public abstract OperadorFacade getFacade();


    public abstract void selCentrope(Operador centrope);
    
    
    @Override
        public String buscar() {
        List<Operador> result = getFacade().findByCriterio(getCriterio());

        getItems().clear();

        for (Operador centrope : result) {
            getItems().add(new BuscadorItem(centrope.getIdOperador()+"", centrope.getOpeNombre(), centrope));
        }
        return null;
    }

    @Override
    public void asignar(Operador t) {
        if (t == null) {
            selCentrope(new Operador());
        } else {
            selCentrope(t);
        }
    }

    @Override
    public void mostrar(ActionEvent evt) {
        List<Operador> result = getFacade().findByCriterio(getCriterio());

        getItems().clear();

        for (Operador centrope : result) {
            getItems().add(new BuscadorItem(centrope.getIdOperador()+"", centrope.getOpeNombre(), centrope));
        }
        super.mostrar(evt);
    }
    
}
