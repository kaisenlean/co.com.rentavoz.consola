/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.buscador;

/**
 *
 * @author ejody
 */
public class BuscadorItem<T> {

    private String id, descripcion;
    private T obj;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public BuscadorItem(String id, String descripcion, T obj) {
        this.id = id;
        this.descripcion = descripcion;
        this.obj = obj;
    }

    public BuscadorItem() {
    }
}
