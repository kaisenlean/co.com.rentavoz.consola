/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista.maestras.sucursal;

import co.com.rentavoz.logica.jpa.entidades.Ciudad;
import co.com.rentavoz.logica.jpa.entidades.Departamento;
import co.com.rentavoz.logica.jpa.entidades.Sucursal;
import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import co.com.rentavoz.logica.jpa.fachadas.CiudadFacade;
import co.com.rentavoz.logica.jpa.fachadas.DepartamentoFacade;
import co.com.rentavoz.logica.jpa.fachadas.SucursalFacade;
import com.invte.component.rentavoz.buscador.BuscadorCiudad;
import com.invte.component.rentavoz.buscador.BuscadorDepartamento;

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
public class BeanSucursal extends StandardAbm<Sucursal> implements Serializable {

    @EJB
    private SucursalFacade sucursalFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    @EJB
    private DepartamentoFacade departamentoFacade;
    private BuscadorDepartamento buscadorDepartamento;
    private BuscadorCiudad buscadorCiudad;
    private Departamento departamento = new Departamento();
    private Ciudad ciudad = new Ciudad();
    private boolean verCiudad = false;

    @Override
    public AbstractFacade<Sucursal> getFacade() {
        return sucursalFacade;
    }

    @Override
    public Sucursal getInstancia() {
        return new Sucursal();
    }

    @Override
    public String reglaNavegacion() {
        return "/paginas/maestras/sucursal/index.jsf";
    }

    @Override
    public Sucursal getObjeto() {
        return obtenerObjeto();
    }

    @Override
    public List<Sucursal> getListado() {
        return obtenerListado();
    }

    @Override
    public void preRenderizarItem() {
        if (getObjeto().getCiudadidCiudad() != null) {
            ciudad = getObjeto().getCiudadidCiudad();
            departamento = ciudad.getDepartamentoidDepartamento();
            verCiudad=true;
        }

    }

    @Override
    public boolean preAction() {
        if (!isEdit()) {
            if (getObjeto().getCiudadidCiudad()==null ) {
                  mensaje("Error", "Debes seleccionar una ciudad a la cual vas a asociar la sucursal");
                return false;
            }
            if (getFacade().find(getObjeto().getIdSucursal()) == null) {
                
                return true;
            } else {
                mensaje("Error", "Este codigo ya esta siendo utilizado por otra sucursal, por favor intente con otro codigo");
                return false;
            }
        } else {
            return true;
        }

    }

    @Override
    public void initialize() {
        buscadorDepartamento = new BuscadorDepartamento() {
            @Override
            public DepartamentoFacade getFacade() {
                return departamentoFacade;
            }

            @Override
            public void selCentrope(Departamento centrope) {
                departamento = centrope;
                verCiudad = true;

            }
        };
        buscadorCiudad = new BuscadorCiudad() {
            @Override
            public CiudadFacade getFacade() {
                return ciudadFacade;
            }

            @Override
            public Departamento getDepartamento() {
                return departamento;
            }

            @Override
            public void selCentrope(Ciudad centrope) {
                ciudad = centrope;
                getObjeto().setCiudadidCiudad(ciudad);
            }
        };
    }

    @Override
    public void buscarrPorCriterio() {
    }
    //<editor-fold defaultstate="collapsed" desc="Capsulas">

    public BuscadorDepartamento getBuscadorDepartamento() {
        return buscadorDepartamento;
    }

    public void setBuscadorDepartamento(BuscadorDepartamento buscadorDepartamento) {
        this.buscadorDepartamento = buscadorDepartamento;
    }

    public BuscadorCiudad getBuscadorCiudad() {
        return buscadorCiudad;
    }

    public void setBuscadorCiudad(BuscadorCiudad buscadorCiudad) {
        this.buscadorCiudad = buscadorCiudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public boolean isVerCiudad() {
        return verCiudad;
    }

    public void setVerCiudad(boolean verCiudad) {
        this.verCiudad = verCiudad;
    }
    //</editor-fold>
}
