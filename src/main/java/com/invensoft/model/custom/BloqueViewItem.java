/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model.custom;

import com.invensoft.controller.CuestionarioController;
import com.invensoft.model.Bloque;
import com.invensoft.util.Utilities;
import java.io.Serializable;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;

/**
 *
 * @author David
 */
public class BloqueViewItem extends HtmlPanelGroup implements Serializable {

    private CuestionarioController cuestionarioController;
    private Bloque bloque;
    private PanelGrid headerGrid;

    public BloqueViewItem(CuestionarioController cuestionarioController, Bloque bloque) {
        this.cuestionarioController = cuestionarioController;
        this.bloque = bloque;

        this.constructHeader();
        getChildren().add(CommonViewItems.getBr());
        this.addDescription();
        getChildren().add(CommonViewItems.getBr());
    }

    private void constructHeader() {
        headerGrid = new PanelGrid();
        headerGrid.setStyle("width: 100%");

        OutputLabel title = new OutputLabel();
//        title.setValue(bloque.getTitulo());
        title.setValue("Bloque " + cuestionarioController.getRootPanelGroup().getChildCount());

        CommandButton plusButton = new CommandButton();
        plusButton.setIcon(Utilities.PLUS_ICON);
        plusButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                onAddGrupoPreguntas();
            }
        });
        
        CommandButton removeButton = new CommandButton();
        removeButton.setIcon(Utilities.TRASH_ICON);
        removeButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                onRemoveBloque();
            }
        });

        Column leftColumn = new Column();
        Column centerColumn = new Column();
        Column rightColumn = new Column();

        leftColumn.setStyle("width: 33%");
        centerColumn.getChildren().add(title);
        centerColumn.setStyle("width: 33%");
        rightColumn.setStyle("width: 33%; text-align: right");
        rightColumn.getChildren().add(plusButton);
        rightColumn.getChildren().add(removeButton);

        Row row = new Row();
        row.getChildren().add(leftColumn);
        row.getChildren().add(centerColumn);
        row.getChildren().add(rightColumn);

        headerGrid.getFacets().put("header", row);
        this.getChildren().add(headerGrid);
    }

    private void addDescription() {
        OutputLabel description = new OutputLabel();
//        description.setValue(bloque.getDescripcion());
        description.setValue("Aqui va la descripcion del bloque de preguntas en caso de que la tenga.");
        this.getChildren().add(description);
        this.getChildren().add(CommonViewItems.getBr());
    }
    
    private void onAddGrupoPreguntas() {
        this.getChildren().add(this.getChildCount(), new GrupoPreguntasViewItem(this,null));
    }
    
    private void onRemoveBloque() {
        cuestionarioController.getRootPanelGroup().getChildren().remove(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public PanelGrid getHeaderGrid() {
        return headerGrid;
    }

    public void setHeaderGrid(PanelGrid headerGrid) {
        this.headerGrid = headerGrid;
    }
    //</editor-fold>
}
