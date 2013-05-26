/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.rentavoz.vista;


import co.com.rentavoz.logica.jpa.fachadas.AbstractFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ejody
 */
public abstract class StandardAbm<T> implements Serializable, StandardInterface<T> {

    /**
     * variable para manipular el formulario y su vista
     */
    private boolean form = false;
    private T objeto;
    private String navigationRule;
    private String criterio;
    private List<T> listado;
    protected ExternalContext ctx;
    protected String ctxPath;
    protected  FacesContext context;

    

    public StandardAbm() {
    }

    public abstract AbstractFacade<T> getFacade();

    public abstract T getInstancia();

    public abstract String reglaNavegacion();

    /**
     *
     * @return
     */
    /**
     *
     * @param facade
     * @param dirPagina
     */
    public StandardAbm(AbstractFacade<T> facade, String dirPagina) {
//        this.getFacade() = facade;
//        this.dirPagina=dirPagina;
//        t.getJavaType().g
    }

    @PostConstruct
    @Override
    public void init() {
        edit = false;
        listado = getFacade().findAll();
        initialize();
        ctx = FacesContext.getCurrentInstance().getExternalContext();
        ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
       
    }
    /**
     * variable para manipular el campo editar
     */
    private boolean edit = false;

    /**
     * form
     */
    @Override
    public void verForm() {
        form = !form;
    }

    @Override
    public void renderizarItem(T objeto, boolean showForm) {
        edit = true;
        this.objeto = objeto;
        preRenderizarItem();
        if (showForm) {
            verForm();
        }

    }

    @Override
    public String aceptar() {

        try {

            if (preAction()) {


                if (edit) {
                    getFacade().edit(objeto);
                    RequestContext.getCurrentInstance().execute("alert('Se ha realizado la transaccion correctamente !!');");
//                FacesUtils.mensaje("Se ha realizado la transaccion correctamente !!");
                    System.out.println(reglaNavegacion());
                    mensaje("Hecho", "Se ha editado este objeto");
                    return reglaNavegacion();
                } else {
                    getFacade().create(objeto);
                    RequestContext.getCurrentInstance().execute("alert('Se ha realizado la transaccion correctamente !!');");
//                FacesUtils.mensaje("Se ha realizado la transaccion correctamente !!");
                    System.out.println(reglaNavegacion());
                     mensaje("Hecho", "Se ha creado un nuevo objeto");
                    return reglaNavegacion();
                }

            } else {
                return "";
            }


        } catch (Exception e) {
            e.printStackTrace();
//            FacesUtils.mensaje("No se ha finalizado la transaccion con exito . Respuesta del servidor : " + e.toString());
            return "";
        }

    }

    @Override
    public void verFormNuevo(boolean showForm) {
        edit = false;
        try {

            objeto = (T) getInstancia().getClass().newInstance();;
            postFormNuevo();
        } catch (InstantiationException ex) {
            Logger.getLogger(StandardAbm.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al inicializar la clase");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StandardAbm.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al inicializar la clase");
        }
        if (showForm) {
            verForm();

        }
    }

    @Override
    public void eliminarItem(T objeto) {
        try {
            System.out.println("ando aca");
            this.objeto = objeto;
            getFacade().remove(objeto);
            init();
            postEliminar();
             mensaje("Hecho", "Se ha eliminado este objeto");
//            FacesUtils.mensaje("Se  ha eiminado este objeto");
        } catch (Exception ex) {
            mensaje("Error", "NO se puede eliminar este objeto debido a que tiene referencias con otros items");
            Logger.getLogger(StandardAbm.class.getName()).log(Level.SEVERE, null, ex);
//            FacesUtils.mensaje("No se puede eliminar el objeto debido a que tiene  referencias asocidas");
        }
//        return reglaNavegacion();
    }

    //<editor-fold defaultstate="collapsed" desc="CAPSULAS">
    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getNavigationRule() {
        return navigationRule;
    }

    public void setNavigationRule(String navigationRule) {
        this.navigationRule = navigationRule;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public abstract T getObjeto();

    public T obtenerObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }

    public abstract List<T> getListado();

    public List<T> obtenerListado() {
        return listado;
    }

    public void setListado(List<T> listado) {
        this.listado = listado;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    //</editor-fold>

    /**
     *
     * @return
     */
    public boolean preAction() {
        return true;
    }

    public void preRenderizarItem() {
//    throw  new UnsupportedOperationException("No soportado");
    }

    public abstract void initialize();
//    throw  new UnsupportedOperationException("No soportado");

    public void postFormNuevo() {
    }

       
    public void postEliminar() {


        try {


            // Usar el contexto de JSF para invalidar la sesión,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

            // Redirección de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallará.
            // Sin embargo, como ya está fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + reglaNavegacion());


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public  void mensaje(String title,String mensaje){
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(title,  mensaje));
    }
}
