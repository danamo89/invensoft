/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.util.FacesUtils;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author David
 */
@Named(value = "menuController")
@SessionScoped
public class MenuController implements Serializable {

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
        
    }
    
    public void onRedirectTo(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/InvenSoft/pages/secure/"+url+".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
