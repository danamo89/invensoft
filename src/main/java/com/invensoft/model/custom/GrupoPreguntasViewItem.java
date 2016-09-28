/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model.custom;

import com.invensoft.controller.CuestionarioController;
import com.invensoft.model.GrupoPreguntas;
import com.invensoft.model.Pregunta;
import com.invensoft.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;

/**
 *
 * @author David
 */
public class GrupoPreguntasViewItem extends PanelGrid implements Serializable {

    private GrupoPreguntas grupoPreguntas;
    private final CuestionarioController cuestionarioController;
    private InputText titulo;

    public GrupoPreguntasViewItem(CuestionarioController cuestionarioController, GrupoPreguntas grupoPreguntas) {
        setStyle("width: 100%; padding-left: 20px: padding-right: 20px");
        
        this.cuestionarioController = cuestionarioController;
        if (grupoPreguntas != null) {
            this.grupoPreguntas = grupoPreguntas;
            this.addHeader();
            
//            Collections.sort(this.grupoPreguntas.getPreguntaList());
            List<Integer> listOrders = new ArrayList<>();
            for (Pregunta pregunta : grupoPreguntas.getPreguntaList()) {
                listOrders.add(pregunta.getOrden());
            }
            Collections.sort(listOrders);
            
            for (Integer orderItem : listOrders) {
                for (Pregunta pregunta : grupoPreguntas.getPreguntaList()) {
                    if (Objects.equals(orderItem, pregunta.getOrden())) {
                        onAddPregunta(pregunta);
                    }
                }
            }
            
//            for (Pregunta pregunta : this.grupoPreguntas.getPreguntaList()) {
//                
//            }
        } else {
            this.grupoPreguntas = new GrupoPreguntas(this.cuestionarioController.getCuestionario());
            this.grupoPreguntas.setOrden(this.cuestionarioController.getRootPanelGroup().getChildCount()+1);
            this.addHeader();
        }
    }

    private void addHeader() {
        titulo = new InputText();
        titulo.setPlaceholder("Titulo del grupo de preguntas");
        titulo.setValue(grupoPreguntas.getTitulo());

        CommandButton addPreguntaButton = new CommandButton();
        addPreguntaButton.setIcon(Utilities.PLUS_ICON);
        addPreguntaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        addPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                onAddPregunta();
            }
        });

        CommandButton removeGrupoPreguntaButton = new CommandButton();
        removeGrupoPreguntaButton.setIcon(Utilities.TRASH_ICON);
        removeGrupoPreguntaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        removeGrupoPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                onRemoveGrupoPregunta();
            }
        });

        Column leftColumn = new Column();
        Column rightColumn = new Column();

        leftColumn.setStyle("width: 90%");
        leftColumn.getChildren().add(titulo);
        rightColumn.setStyle("width: 10%; text-align: right");
        rightColumn.getChildren().add(addPreguntaButton);
        rightColumn.getChildren().add(removeGrupoPreguntaButton);

        Row row = new Row();
        row.getChildren().add(leftColumn);
        row.getChildren().add(rightColumn);

        this.getFacets().put("header", row);
    }

    private void onAddPregunta() {
        onAddPregunta(null);
    }
    
    private void onAddPregunta(Pregunta pregunta) {
        this.getChildren().add(new PreguntaViewItem(this,pregunta));
    }

    private void onRemoveGrupoPregunta() {
        this.cuestionarioController.getRootPanelGroup().getChildren().remove(this);
    }
    
    public void onSave() {
        //Guardamos las preguntas con sus opciones
        for (int i = 0; i < this.getChildren().size(); i++) {
            PreguntaViewItem preguntaViewItem = (PreguntaViewItem) this.getChildren().get(i);
            preguntaViewItem.getPregunta().setOrden(i+1);
            preguntaViewItem.onSave();
        }
        
        this.cuestionarioController.getCuestionario().getGrupoPreguntasList().add(this.grupoPreguntas);
        grupoPreguntas.setTitulo(titulo.getValue().toString());
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public InputText getTitulo() {
        return titulo;
    }

    public void setTitulo(InputText titulo) {
        this.titulo = titulo;
    }

    public GrupoPreguntas getGrupoPreguntas() {
        return grupoPreguntas;
    }

    public void setGrupoPreguntas(GrupoPreguntas grupoPreguntas) {
        this.grupoPreguntas = grupoPreguntas;
    }
    //</editor-fold>
}
