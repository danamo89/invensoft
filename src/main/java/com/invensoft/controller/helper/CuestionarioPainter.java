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
import java.util.Objects;
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
    private List<RespuestaPregunta> listRespuestasPreguntasExistente;
    private boolean disableFields = false;

    public List<RespuestaPregunta> paint(HtmlPanelGroup rootPanelGroup, Cuestionario cuestionario, List<RespuestaPregunta> listRespuestasPreguntas) {
        return paint(rootPanelGroup, cuestionario, listRespuestasPreguntas, true);
    }
    
    public List<RespuestaPregunta> paint(HtmlPanelGroup rootPanelGroup, Cuestionario cuestionario, List<RespuestaPregunta> listRespuestasPreguntas, boolean rePaint) {
        this.rootPanelGroup = rootPanelGroup;
        this.cuestionario = cuestionario;
        this.listRespuestasPreguntas = new LinkedList<>();
        this.listRespuestasPreguntasExistente = listRespuestasPreguntas;

        //Por si viene para ser re-pintado
        if (rePaint) this.rootPanelGroup.getChildren().clear(); else this.rootPanelGroup.getChildren().add(CommonViewItems.getBr());

        this.bodyPanelGrid = new PanelGrid();
        this.bodyPanelGrid.setColumns(1);

        addHeader();
        addGruposPreguntas();
        
        return this.listRespuestasPreguntas;
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
                if (Objects.equals(orderItem, grupoPreguntas.getOrden())) {
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
                if (Objects.equals(orderItem, pregunta.getOrden())) {
                    RespuestaPregunta respuestaPregunta = new RespuestaPregunta(pregunta);
                    
                    //Buscamos si ya existe una respuesta a la pregunta
                    for (RespuestaPregunta respuestaPreguntaItem : listRespuestasPreguntasExistente) {
                        if (Objects.equals(respuestaPregunta.getPregunta().getIdPregunta(), respuestaPreguntaItem.getPregunta().getIdPregunta())) {
                            respuestaPregunta = respuestaPreguntaItem;
                            break;
                        }
                    }
                    
                    sublistRespuestasPreguntas.add(respuestaPregunta);
                    listRespuestasPreguntas.add(respuestaPregunta);
                    
                    List<Integer> listOpcionesOrders = new ArrayList<>();
                    for (OpcionRespuesta opcionRespuesta : respuestaPregunta.getPregunta().getOpcioneRespuestaList()) {
                        listOpcionesOrders.add(opcionRespuesta.getOrden());
                    }
                    Collections.sort(listOpcionesOrders);

                    List<OpcionRespuesta> listaOpcionRespuestaOrdenada = new LinkedList<>();
                    for (Integer orderOpcionesItem : listOpcionesOrders) {
                        for (OpcionRespuesta opcionRespuesta : respuestaPregunta.getPregunta().getOpcioneRespuestaList()) {
                            if (Objects.equals(orderOpcionesItem, opcionRespuesta.getOrden())) {
                                listaOpcionRespuestaOrdenada.add(opcionRespuesta);
                            }
                        }
                    }

                    respuestaPregunta.getPregunta().setOpcioneRespuestaList(listaOpcionRespuestaOrdenada);
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
        selectManyCheckbox.setReadonly(disableFields);
        selectManyCheckbox.setLayout("grid");
        selectManyCheckbox.setColumns(1);
        selectManyCheckbox.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.opcionRespuesta.idOpcionRespuesta}", Integer.class));
        selectManyCheckbox.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Check'?true:false}", Boolean.class));
        UISelectItems checkItems = new UISelectItems();
        checkItems.getAttributes().put("var", "opcionRespuesta");
        checkItems.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.opcioneRespuestaList}", List.class));
        checkItems.setValueExpression("itemLabel", FacesUtils.createValueExpression("#{opcionRespuesta.texto}", String.class));
        checkItems.setValueExpression("itemValue", FacesUtils.createValueExpression("#{opcionRespuesta.idOpcionRespuesta}", Integer.class));
        selectManyCheckbox.getChildren().add(checkItems);
        uiRepeat.getChildren().add(selectManyCheckbox);

        SelectOneRadio selectOneRadio = new SelectOneRadio();
        selectOneRadio.setReadonly(disableFields);
        selectOneRadio.setLayout("grid");
        selectOneRadio.setColumns(1);
        selectOneRadio.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.opcionRespuesta.idOpcionRespuesta}", Integer.class));
        selectOneRadio.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Radio'?true:false}", Boolean.class));
        UISelectItems radioItems = new UISelectItems();
        radioItems.getAttributes().put("var", "opcionRespuesta");
        radioItems.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.opcioneRespuestaList}", List.class));
        radioItems.setValueExpression("itemLabel", FacesUtils.createValueExpression("#{opcionRespuesta.texto}", String.class));
        radioItems.setValueExpression("itemValue", FacesUtils.createValueExpression("#{opcionRespuesta.idOpcionRespuesta}", Integer.class));
        selectOneRadio.getChildren().add(radioItems);
        uiRepeat.getChildren().add(selectOneRadio);

        InputText inputText = new InputText();
        inputText.setReadonly(disableFields);
        inputText.setStyle("width: 100%");
        inputText.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Input'?true:false}", Boolean.class));
        inputText.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.textoRespuesta}", String.class));
        uiRepeat.getChildren().add(inputText);

        InputTextarea inputTextarea = new InputTextarea();
        inputTextarea.setReadonly(disableFields);
        inputTextarea.setStyle("width: 100%");
        inputTextarea.setValueExpression("rendered", FacesUtils.createValueExpression("#{respuestaPregunta.pregunta.estiloOpciones eq 'Area'?true:false}", Boolean.class));
        inputTextarea.setValueExpression("value", FacesUtils.createValueExpression("#{respuestaPregunta.textoRespuesta}", String.class));
        uiRepeat.getChildren().add(inputTextarea);

        uiRepeat.getChildren().add(CommonViewItems.getBr());
        bodyPanelGrid.getChildren().add(uiRepeat);
    }

    public void setDisableFields(boolean disableFields) {
        this.disableFields = disableFields;
    }
}
