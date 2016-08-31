/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model.custom;

import com.invensoft.model.GrupoPreguntas;
import com.invensoft.util.Utilities;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;

/**
 *
 * @author David
 */
public class GrupoPreguntasViewItem extends PanelGrid implements Serializable {

    private BloqueViewItem bloquePadre;
    private GrupoPreguntas grupoPreguntas;
    private List<HtmlOutputText> preguntasList;

    public GrupoPreguntasViewItem(BloqueViewItem bloquePadre, GrupoPreguntas grupoPreguntas) {
        this.bloquePadre = bloquePadre;
        this.grupoPreguntas = grupoPreguntas;
        this.preguntasList = new LinkedList<>();

        setStyle("width: 100%; padding-left: 20px: padding-right: 20px");
        
        this.constructHeader();
        this.constructPreguntasList();
    }

    private void constructHeader() {
        InputText title = new InputText();
        title.setValue("Titulo de las preguntas");
        
        CommandButton configurarOpcionesDeRespuestaButton = new CommandButton();
        configurarOpcionesDeRespuestaButton.setIcon(Utilities.GEAR_ICON);
        configurarOpcionesDeRespuestaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        configurarOpcionesDeRespuestaButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                
            }
        });

        CommandButton addPreguntaButton = new CommandButton();
        addPreguntaButton.setIcon(Utilities.PLUS_ICON);
        addPreguntaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        addPreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                onAddPregunta();
                constructPreguntasList();
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
        leftColumn.getChildren().add(title);
        rightColumn.setStyle("width: 10%; text-align: right");
        rightColumn.getChildren().add(addPreguntaButton);
        rightColumn.getChildren().add(removeGrupoPreguntaButton);

        Row row = new Row();
        row.getChildren().add(leftColumn);
        row.getChildren().add(rightColumn);

        this.getFacets().put("header", row);
    }

    private void constructPreguntasList() {
        //Limpiamos el listado y las cargamos de nuevo
        getChildren().clear();

        HtmlPanelGrid gridDePreguntas = new HtmlPanelGrid();
        gridDePreguntas.setColumns(2);

        for (final HtmlOutputText pregunta : preguntasList) {
            HtmlPanelGrid grupoPreguntaYRespuestas = new HtmlPanelGrid();
            grupoPreguntaYRespuestas.setColumns(1);

            // Aqui debe venir un FOR con un SWITCH para cada posible respuesta
            // por ahora queda en un comentario.
            // Ejemplo para el caso de opciones en forma de check
            SelectManyCheckbox checkBoxOptions = new SelectManyCheckbox();
            checkBoxOptions.setStyle("text-align: left; padding-left: 20px");
            checkBoxOptions.setLayout("grid");
            checkBoxOptions.setColumns(1);

            List<String> listaDeOpciones = new LinkedList<>();
            listaDeOpciones.add("Bombas de Vapor");
            listaDeOpciones.add("Condensadores");
            listaDeOpciones.add("Purgas");
            listaDeOpciones.add("Ventilador");

            for (String opcion : listaDeOpciones) {
                UISelectItem selectItem = new UISelectItem();
                selectItem.setItemLabel(opcion);
                selectItem.setItemValue(opcion);
                checkBoxOptions.getChildren().add(selectItem);
            }

            grupoPreguntaYRespuestas.getChildren().add(pregunta);
            grupoPreguntaYRespuestas.getChildren().add(checkBoxOptions);
            
            CommandButton removePreguntaButton = new CommandButton();
            removePreguntaButton.setIcon(Utilities.TRASH_ICON);
            removePreguntaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
            removePreguntaButton.addActionListener(new ActionListener() {
                @Override
                public void processAction(ActionEvent event) throws AbortProcessingException {
                    onRemovePregunta(pregunta);
                }
            });

            gridDePreguntas.getChildren().add(grupoPreguntaYRespuestas);
            gridDePreguntas.getChildren().add(removePreguntaButton);
        }

        getChildren().add(getChildCount(), gridDePreguntas);
    }

    private void onAddPregunta() {
        HtmlOutputText pregunta = new HtmlOutputText();
        pregunta.setValue(this.preguntasList.size()+1 + ") ¿Que elementos corresponden a una locomotora?");

        this.preguntasList.add(pregunta);
    }
    
    private void onRemovePregunta(HtmlOutputText pregunta) {
        this.preguntasList.remove(pregunta);
        constructPreguntasList();
    }
    
    private void onRemoveGrupoPregunta() {
        this.bloquePadre.getChildren().remove(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public GrupoPreguntas getGrupoPreguntas() {
        return grupoPreguntas;
    }

    public void setGrupoPreguntas(GrupoPreguntas grupoPreguntas) {
        this.grupoPreguntas = grupoPreguntas;
    }
    //</editor-fold>
}
