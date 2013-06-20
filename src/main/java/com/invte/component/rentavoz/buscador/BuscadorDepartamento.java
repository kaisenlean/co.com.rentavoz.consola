/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;




import java.util.List;

import javax.faces.event.ActionEvent;

import co.com.rentavoz.logica.jpa.entidades.Departamento;
import co.com.rentavoz.logica.jpa.fachadas.DepartamentoFacade;

/**
 *
 * @author ejody
 */
public abstract class BuscadorDepartamento extends Buscador<Departamento> {

    public abstract DepartamentoFacade getFacade();

    public abstract void selCentrope(Departamento centrope);

    
    @Override
        public String buscar() {
        List<Departamento> result = getFacade().findByCriterio(getCriterio());

        getItems().clear();

        for (Departamento centrope : result) {
            getItems().add(new BuscadorItem(centrope.getIdDepartamento()+"", centrope.getDepNombre(), centrope));
        }
        return null;
    }

    @Override
    public void asignar(Departamento t) {
        if (t == null) {
            selCentrope(new Departamento());
        } else {
            selCentrope(t);
        }
    }

    @Override
    public void mostrar(ActionEvent evt) {
           List<Departamento> result = getFacade().findByCriterio(getCriterio());

        getItems().clear();

        for (Departamento centrope : result) {
            getItems().add(new BuscadorItem(centrope.getIdDepartamento()+"", centrope.getDepNombre(), centrope));
        }
        super.mostrar(evt);
    }
    
}
