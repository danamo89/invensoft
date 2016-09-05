/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.custom.BloqueViewItem;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGroup;

/**
 *
 * @author David
 */
@ManagedBean(name = "cuestionarioController")
@ViewScoped
public class CuestionarioController implements Serializable {
    
    private List<String> lista;
    private HtmlPanelGroup rootPanelGroup;
    private boolean showCuestionariosTable;

    /**
     * Creates a new instance of CuestionarioController
     */
    public CuestionarioController() {
    }
    
    @PostConstruct
    public void postConstruct() {
        try {
            showCuestionariosTable = false;
            rootPanelGroup = new HtmlPanelGroup();
            
            lista = new LinkedList<>();
            lista.add("David");
            lista.add("Navarro");
            lista.add("Mora");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onAddBloque() {
        rootPanelGroup.getChildren().add(new BloqueViewItem(this,null));
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public boolean isShowCuestionariosTable() {
        return showCuestionariosTable;
    }

    public void setShowCuestionariosTable(boolean showCuestionariosTable) {
        this.showCuestionariosTable = showCuestionariosTable;
    }

    public HtmlPanelGroup getRootPanelGroup() {
        return rootPanelGroup;
    }

    public void setRootPanelGroup(HtmlPanelGroup rootPanelGroup) {
        this.rootPanelGroup = rootPanelGroup;
    }
    //</editor-fold>

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

}
