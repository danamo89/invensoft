/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller.helper;

import com.invensoft.model.Cuestionario;
import com.invensoft.model.GrupoPreguntas;
import com.invensoft.model.OpcionRespuesta;
import com.invensoft.model.Pregunta;
import com.invensoft.model.RespuestaPregunta;
import com.invensoft.model.custom.CommonViewItems;
import com.invensoft.util.FacesUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.faces.component.UISelectItem;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.repeat.UIRepeat;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;
import org.primefaces.component.selectoneradio.SelectOneRadio;

/**
 *
 * @author David
 */
public class CuestionarioPainter {

    private HtmlPanelGroup rootPanelGroup;
    private Cuestionario cuestionario;
    private PanelGrid bodyPanelGrid;
    private List<RespuestaPregunta> listRespuestasPreguntas;

    public List<RespuestaPregunta> paint(HtmlPanelGroup rootPanelGroup, Cuestionario cuestionario) {
        this.rootPanelGroup = rootPanelGroup;
        this.cuestionario = cuestionario;
        this.listRespuestasPreguntas = new LinkedList<>();

        //Por si viene para ser re-pintado
        this.rootPanelGroup.getChildren().clear();

        this.bodyPanelGrid = new PanelGrid();
        this.bodyPanelGrid.setColumns(1);

        addHeader();
        addGruposPreguntas();

        return listRespuestasPreguntas;
    }

    private void addHeader() {
        PanelGrid panelGrid = new PanelGrid();
        HtmlOutputText outputText = new HtmlOutputText();
        outputText.setEscape(false);
        outputText.setValue("<h2>" + cuestionario.getTitulo() + "</h2>");
        panelGrid.getFacets().put("header", outputText);

        rootPanelGroup.getChildren().add(panelGrid);
    }

    private void addGruposPreguntas() {
        List<Integer> listOrders = new ArrayList<>();
        for (GrupoPreguntas grupoPreguntas : cuestionario.getGrupoPreguntasList()) {
            listOrders.add(grupoPreguntas.getOrden());
        }
        Collections.sort(listOrders);

        for (Integer orderItem : listOrders) {
            for (GrupoPreguntas grupoPreguntas : cuestionario.getGrupoPreguntasList()) {
                if (orderItem == grupoPreguntas.getOrden()) {
                    //Titulo del grupo de grupo de preguntas
                    if (grupoPreguntas.getTitulo() != null) {
                        HtmlOutputText outputTextH3 = new HtmlOutputText();
                        outputTextH3.setEscape(false);
                        outputTextH3.setValue("<h3>" + grupoPreguntas.getTitulo() + "</h3>");

                        bodyPanelGrid.getChildren().add(outputTextH3);
                    }

                    addPreguntas(grupoPreguntas);
                }
            }
        }

        rootPanelGroup.getChildren().add(bodyPanelGrid);
    }

    private void addPreguntas(GrupoPreguntas grupoPreguntas) {
        List<Integer> listOrders = new ArrayList<>();
        for (Pregunta pregunta : grupoPreguntas.getPreguntaList()) {
            listOrders.add(pregunta.getOrden());
        }
        Collections.sort(listOrders);

        List<RespuestaPregunta> sublistRespuestasPreguntas = new LinkedList<>();
        
        for (Integer orderItem : listOrders) {
            for (Pregunta pregunta : grupoPreguntas.getPreguntaList()) {
                if (orderItem == pregunta.getOrden()) {
                    sublistRespuestasPreguntas.add(new RespuestaPregunta(pregunta));
                    listRespuestasPreguntas.add(new RespuestaPregunta(pregunta));
                    
                    List<Integer> listOpcionesOrders = new ArrayList<>();
                    for (OpcionRespuesta opcionRespuesta : pregunta.getOpcioneRespuestaList()) {
                        listOpcionesOrders.add(opcionRespuesta.getOrden());
                    }
                    Collections.sort(listOpcionesOrders);

                    List<OpcionRespuesta> listaOpcionRespuestaOrdenada;

                    listaOpcionRespuestaOrdenada = new LinkedList<>();
                    for (Integer orderOpcionesItem : listOpcionesOrders) {
                        for (OpcionRespuesta opcionRespuesta : pregunta.getOpcioneRespuestaList()) {
                            if (orderOpcionesItem == opcionRespuesta.getOrden()) {
                                listaOpcionRespuestaOrdenada.add(opcionRespuesta);
                            }
                        }
                    }

                    pregunta.setOpcioneRespuestaList(listaOpcionRespuestaOrdenada);
                }
            }
        }

        UIRepeat uiRepeat = new UIRepeat();
        uiRepeat.setValue(sublistRespuestasPreguntas);
        uiRepeat.setVar("respuestaPregunta");
        uiRepeat.setVarStatus("status");

        HtmlOutputText outputText = new HtmlOutputText();
        outputText.setValueExpression("value", FacesUtils.createValueExpression("#{status.index+1}). #{respuestaPregunta.pregunta.texto}", String.class));
        uiRepeat.getChildren().add(outputText);

        SelectManyCheckbox selectManyCheckbox = new SelectManyCheckbox();
        selectManyCheckbox.setLayout("grid");
        selectManyCheckbox.setColumns(1);
        selectManyCheckbox.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.opcionRespuesta.texto}", String.class));
        selectManyCheckbox.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Check'?true:false}", Boolean.class));
        UISelectItems checkItems = new UISelectItems();
        checkItems.getAttributes().put("var", "opcionRespuesta");
        checkItems.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.opcioneRespuestaList}", List.class));
        checkItems.setValueExpression("itemLabel", FacesUtils.createValueExpression("#{opcionRespuesta.texto}", String.class));
        checkItems.setValueExpression("itemValue", FacesUtils.createValueExpression("#{opcionRespuesta.texto}", String.class));
        selectManyCheckbox.getChildren().add(checkItems);
        uiRepeat.getChildren().add(selectManyCheckbox);

        SelectOneRadio selectOneRadio = new SelectOneRadio();
        selectOneRadio.setLayout("grid");
        selectOneRadio.setColumns(1);
        selectOneRadio.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.opcionRespuesta.texto}", String.class));
        selectOneRadio.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Radio'?true:false}", Boolean.class));
        UISelectItems radioItems = new UISelectItems();
        radioItems.getAttributes().put("var", "opcionRespuesta");
        radioItems.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.opcioneRespuestaList}", List.class));
        radioItems.setValueExpression("itemLabel", FacesUtils.createValueExpression("#{opcionRespuesta.texto}", String.class));
        radioItems.setValueExpression("itemValue", FacesUtils.createValueExpression("#{opcionRespuesta.texto}", String.class));
        selectOneRadio.getChildren().add(radioItems);
        uiRepeat.getChildren().add(selectOneRadio);

        InputText inputText = new InputText();
        inputText.setStyle("width: 100%");
        inputText.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Input'?true:false}", Boolean.class));
        inputText.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.textoRespuesta}", String.class));
        uiRepeat.getChildren().add(inputText);

        InputTextarea inputTextarea = new InputTextarea();
        inputTextarea.setStyle("width: 100%");
        inputTextarea.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Area'?true:false}", Boolean.class));
        inputTextarea.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.textoRespuesta}", String.class));
        uiRepeat.getChildren().add(inputTextarea);

        uiRepeat.getChildren().add(CommonViewItems.getBr());
        bodyPanelGrid.getChildren().add(uiRepeat);
    }
}
