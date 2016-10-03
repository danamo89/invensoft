/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.util.FacesUtils;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author David
 */
@ManagedBean(name = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private boolean readOnly;
    private boolean cantViewCuestionario;
    
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }
    
    @PostConstruct
    public void postConstruct() {
        readOnly = FacesUtils.sessionUserIsReadOnly();
        cantViewCuestionario = FacesUtils.sessionUserCantViewCuestionario();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean isCantViewCuestionario() {
        return cantViewCuestionario;
    }

    public void setCantViewCuestionario(boolean cantViewCuestionario) {
        this.cantViewCuestionario = cantViewCuestionario;
    }
    //</editor-fold>
}
