/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.Cuestionario;
import com.invensoft.model.GrupoPreguntas;
import com.invensoft.model.custom.GrupoPreguntasViewItem;
import com.invensoft.service.ICuestionarioService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGroup;

/**
 *
 * @author David
 */
@ManagedBean(name = "cuestionarioController")
@ViewScoped
public class CuestionarioController implements Serializable {
    
    private Cuestionario cuestionario;
    private List<Cuestionario> cuestionariosList;
    private HtmlPanelGroup rootPanelGroup;
    private boolean showCuestionariosTable;
    
    //Services
    @ManagedProperty(value = "#{cuestionarioService}")
    private ICuestionarioService cuestionarioService;

    /**
     * Creates a new instance of CuestionarioController
     */
    public CuestionarioController() {
    }
    
    @PostConstruct
    public void postConstruct() {
        showCuestionariosTable = true;
        rootPanelGroup = new HtmlPanelGroup();
        
        if (cuestionariosList == null) {
            cuestionariosList = cuestionarioService.findAll();

            // Por si no existen registros en la base de datos
            if (cuestionariosList == null) {
                cuestionariosList = new LinkedList<>();
            }
        }
    }
    
    public void onCreateCuestionario() {
        cuestionario = new Cuestionario();
        rootPanelGroup.getChildren().clear();
    }
    
    public void onViewCuestionarioDetail(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
        rootPanelGroup.getChildren().clear();
        
        List<Integer> listOrders = new ArrayList<>();
        for (GrupoPreguntas grupoPreguntas : cuestionario.getGrupoPreguntasList()) {
            listOrders.add(grupoPreguntas.getOrden());
        }
        Collections.sort(listOrders);
        
        for (Integer orderItem : listOrders) {
            for (GrupoPreguntas grupoPreguntas : cuestionario.getGrupoPreguntasList()) {
                if (Objects.equals(orderItem, grupoPreguntas.getOrden())) {
                    onAddGrupoPreguntas(grupoPreguntas);
                }
            }
        }
        
//        for (GrupoPreguntas grupoPreguntas : this.cuestionario.getGrupoPreguntasList()) {
//            
//        }
        
        showCuestionariosTable = false;
    }
    
    public void onAddGrupoPreguntas() {
        onAddGrupoPreguntas(null);
    }
    
    private void onAddGrupoPreguntas(GrupoPreguntas grupoPreguntas) {
        rootPanelGroup.getChildren().add(new GrupoPreguntasViewItem(this,grupoPreguntas));
    }
    
    public void onSaveCuestionario() {
        try {
            //Guardamos los grupos de preguntas
            for (UIComponent children : rootPanelGroup.getChildren()) {
                GrupoPreguntasViewItem grupoPreguntasViewItem = (GrupoPreguntasViewItem) children;
                grupoPreguntasViewItem.onSave();
            }
            
            cuestionarioService.save(this.cuestionario);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public List<Cuestionario> getCuestionariosList() {
        return cuestionariosList;
    }

    public void setCuestionariosList(List<Cuestionario> cuestionariosList) {
        this.cuestionariosList = cuestionariosList;
    }

    public ICuestionarioService getCuestionarioService() {
        return cuestionarioService;
    }

    public void setCuestionarioService(ICuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
    }
    //</editor-fold>

}
