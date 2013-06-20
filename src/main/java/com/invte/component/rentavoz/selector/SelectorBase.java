/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invte.component.rentavoz.selector;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 *
 * @author ejody
 */
public interface SelectorBase<T> {
    
    List<SelectItem> getItems();
    
    
    
}
