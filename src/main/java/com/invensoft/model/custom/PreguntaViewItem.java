/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model.custom;

import com.invensoft.model.OpcionRespuesta;
import com.invensoft.model.Pregunta;
import com.invensoft.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.checkbox.Checkbox;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.radiobutton.RadioButton;
import org.primefaces.component.row.Row;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.component.spacer.Spacer;

/**
 *
 * @author David
 */
public class PreguntaViewItem extends PanelGrid implements Serializable {

    private Pregunta pregunta;
    private final GrupoPreguntasViewItem grupoPreguntasViewItemPadre;

    private InputText textoPregunta;
    
    private Map<String, String> estiloDeOpcionesMap;

    private String selectedOpcion;
    private List<OpcionRespuesta> opcionesList;

    private Column secondRowFirstColumn;
    private UIComponent opcionDeRespuestaVisual;

    private boolean renderCheckDataTable = true;
    private boolean renderRadioDataTable;
    private boolean renderInputText;
    private boolean renderInputTextArea;

    public PreguntaViewItem(GrupoPreguntasViewItem grupoPreguntasViewItemPadre, Pregunta pregunta) {
        setStyle("width: 70%");

        this.grupoPreguntasViewItemPadre = grupoPreguntasViewItemPadre;
        
        if (pregunta != null) {
            this.pregunta = pregunta;
        } else {
            this.pregunta = new Pregunta(this.grupoPreguntasViewItemPadre.getGrupoPreguntas());
            this.pregunta.setEstiloOpciones("Radio");
            this.pregunta.setOrden(this.grupoPreguntasViewItemPadre.getChildCount()+1);
        }
        
        //En caso de que existan opciones para la pregunta.
        if (this.pregunta.getOpcioneRespuestaList() != null) {
            this.opcionesList = this.pregunta.getOpcioneRespuestaList();
            Collections.sort(this.opcionesList);
        } else {
            this.opcionesList = new ArrayList<>();
            this.pregunta.setOpcioneRespuestaList(this.opcionesList);
        }
        
        this.estiloDeOpcionesMap = new HashMap<>();
        this.secondRowFirstColumn = new Column();
        
        fillLists();
        configure();
    }

    private void fillLists() {
        estiloDeOpcionesMap.put("Seleccion multiple", "Check");
        estiloDeOpcionesMap.put("Seleccion simple", "Radio");
        estiloDeOpcionesMap.put("Texto corto", "Input");
        estiloDeOpcionesMap.put("Texto largo", "Area");

        //Con esto se ordenta automaticamente el mapa
        estiloDeOpcionesMap = new TreeMap<>(estiloDeOpcionesMap);
    }

    private void configure() {
        //|---------------------|------------|---|
        //|Pregunta...          |Presentacion|   |
        //|---------------------|------------| X |
        //|Opcion(es)...                     |   |
        //|----------------------------------|---|

        Column firstRowFirstColumn = new Column();
        firstRowFirstColumn.setStyle("width: 85%");
        firstRowFirstColumn.getChildren().add(configurePregunta());

        Column firstRowSecondColumn = new Column();
        firstRowSecondColumn.setStyle("width: 10%");
        firstRowSecondColumn.getChildren().add(configurePresentacionesDropdown());

        Column firstRowThirdColumn = new Column();
        firstRowThirdColumn.setStyle("width: 85%");
        firstRowThirdColumn.setRowspan(2);
        firstRowThirdColumn.getChildren().add(configureRemovePreguntaButton());

        secondRowFirstColumn.setColspan(2);
//        configureOpcionesDeRespuesta();
        renderResponseOption(pregunta.getEstiloOpciones());
//        secondRowFirstColumn.getChildren().add(opcionDeRespuestaVisual);
        
        Row firstRow = new Row();
        Row secondRow = new Row();

        firstRow.getChildren().add(firstRowFirstColumn);
        firstRow.getChildren().add(firstRowSecondColumn);
        firstRow.getChildren().add(firstRowThirdColumn);
        secondRow.getChildren().add(secondRowFirstColumn);

        this.getChildren().add(firstRow);
        this.getChildren().add(secondRow);
    }

    private InputText configurePregunta() {
        textoPregunta = new InputText();
        textoPregunta.setStyle("width: 100%");
        textoPregunta.setPlaceholder("Pregunta...");
        textoPregunta.setValue(pregunta.getTexto());

        return textoPregunta;
    }

    private HtmlPanelGroup configurePresentacionesDropdown() {
//        SelectOneMenu presentacionesDropdown = new SelectOneMenu();
//        presentacionesDropdown.setValue(estiloDeOpciones);
//        presentacionesDropdown.setStyle("width: 80%");
//        for (String key : estiloDeOpcionesMap.keySet()) {
//            UISelectItem selectItem = new UISelectItem();
//            selectItem.setItemLabel(key);
//            selectItem.setItemValue(estiloDeOpcionesMap.get(key));
//            presentacionesDropdown.getChildren().add(selectItem);
//        }
//
//        AjaxBehavior ajaxChangeEvent = new AjaxBehavior();
//        ajaxChangeEvent.setUpdate(Utilities.CUESTIONARIOS_FORM);
//        ajaxChangeEvent.setProcess("@this");
//        ajaxChangeEvent.setPartialSubmit(true);
//        ajaxChangeEvent.addAjaxBehaviorListener(new AjaxBehaviorListener() {
//            @Override
//            public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
//                renderCheckDataTable = false;
//                renderRadioDataTable = false;
//                renderInputText = false;
//                renderInputTextArea = false;
//                System.out.println("Desde el ajax behavior!! " + event.getComponent().getAttributes().get("value"));
//                switch (event.getComponent().getAttributes().get("value").toString()) {
//                    case "Check":
//                        renderCheckDataTable = true;
//                        break;
//                    case "Radio":
//                        renderRadioDataTable = true;
//                        break;
//                    case "Input":
//                        renderInputText = true;
//                        break;
//                    case "Area":
//                        renderInputTextArea = true;
//                        break;
//                }
//                
//                RequestContext.getCurrentInstance().update("cuestionariosForm");
//                FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("cuestionariosForm");
//            }
//        });
//        presentacionesDropdown.addClientBehavior("change", ajaxChangeEvent);

        CommandButton radioButton = new CommandButton();
        radioButton.setIcon(Utilities.BULLET_ICON);
        radioButton.setTitle("Opcion Simple");
        radioButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        radioButton.setImmediate(true);
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                renderResponseOption("Radio");
            }
        });

        CommandButton checkButton = new CommandButton();
        checkButton.setIcon(Utilities.CHECK_ICON);
        checkButton.setTitle("Opcion Multiple");
        checkButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        checkButton.setImmediate(true);
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                renderResponseOption("Check");
            }
        });

        CommandButton inputTextButton = new CommandButton();
        inputTextButton.setIcon(Utilities.MINUS_ICON);
        inputTextButton.setTitle("Texto Corto");
        inputTextButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        inputTextButton.setImmediate(true);
        inputTextButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                renderResponseOption("Input");
            }
        });

        CommandButton inputTextAreaButton = new CommandButton();
        inputTextAreaButton.setIcon(Utilities.TWO_HORIZONTAL_LINES_ICON);
        inputTextAreaButton.setTitle("Texto Largo");
        inputTextAreaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        inputTextAreaButton.setImmediate(true);
        inputTextAreaButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                renderResponseOption("Area");
            }
        });

        HtmlPanelGroup panelGroup = new HtmlPanelGroup();
        panelGroup.getChildren().add(radioButton);
        panelGroup.getChildren().add(checkButton);
        panelGroup.getChildren().add(inputTextButton);
        panelGroup.getChildren().add(inputTextAreaButton);

        return panelGroup;
    }

    private void renderResponseOption(String type) {
        renderCheckDataTable = false;
        renderRadioDataTable = false;
        renderInputText = false;
        renderInputTextArea = false;

        switch (type) {
            case "Check":
                renderCheckDataTable = true;
                break;
            case "Radio":
                renderRadioDataTable = true;
                break;
            case "Input":
                renderInputText = true;
                break;
            case "Area":
                renderInputTextArea = true;
                break;
        }
        
        pregunta.setEstiloOpciones(type);
        configureOpcionesDeRespuesta();
        secondRowFirstColumn.getChildren().clear();
        secondRowFirstColumn.getChildren().add(opcionDeRespuestaVisual);
    }

    private CommandButton configureRemovePreguntaButton() {
        CommandButton removePreguntaButton = new CommandButton();
        removePreguntaButton.setIcon(Utilities.TRASH_ICON);
        removePreguntaButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        removePreguntaButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                onRemovePregunta();
            }
        });

        return removePreguntaButton;
    }

    private void onRemovePregunta() {
        this.grupoPreguntasViewItemPadre.getChildren().remove(this);
    }

    private void configureOpcionesDeRespuesta() {
        /*
         Para las preguntas de multiples opciones de respuesta utilizamos una
         tabla con Checks
         */
        if (renderCheckDataTable) {
            DataTable checkDataTable = new DataTable();
            checkDataTable.setRendered(renderCheckDataTable);
            checkDataTable.setValue(opcionesList);
            checkDataTable.setVar("opcion");
            checkDataTable.setEditable(true);
            checkDataTable.setEditMode("cell");
            checkDataTable.setSelection(selectedOpcion);
//            checkDataTable.setRowKey(createValueExpression("#{opcion.idOpcion}", Integer.class));
            checkDataTable.getFacets().put("header", getTableOpcionesHeader());
//            checkDataTable.getChildren().add(getTableOpcionesFirstColumn("multiple"));
            
            SelectManyCheckbox selectManyCheckbox = new SelectManyCheckbox();
            selectManyCheckbox.setId("customCheck");
            checkDataTable.getChildren().add(selectManyCheckbox);
            
            Column column = getTableOpcionesFirstColumn(null);
            Checkbox checkbox = new Checkbox();
            checkbox.setFor("customCheck");
            checkbox.setItemIndex(0);
            column.getChildren().add(checkbox);
            checkDataTable.getChildren().add(column);
            
            checkDataTable.getChildren().add(getTableOpcionesSecondColumn());
            checkDataTable.getChildren().add(getTableOpcionesThirdColumn());

            opcionDeRespuestaVisual = checkDataTable;
        }

        /*
         Para las preguntas de una unica respuesta con multiples opciones
         utilizamos una tabla con Radio
         */
        if (renderRadioDataTable) {
            DataTable radioDataTable = new DataTable();
            radioDataTable.setRendered(renderRadioDataTable);
            radioDataTable.setValue(opcionesList);
            radioDataTable.setVar("opcion");
            radioDataTable.setEditable(true);
            radioDataTable.setEditMode("cell");
            radioDataTable.setSelection(selectedOpcion);
            radioDataTable.getFacets().put("header", getTableOpcionesHeader());
            
            SelectOneRadio selectOneRadio = new SelectOneRadio();
            selectOneRadio.setId("customRadio");
            radioDataTable.getChildren().add(selectOneRadio);
            
            Column column = getTableOpcionesFirstColumn(null);
            RadioButton radioButton = new RadioButton();
            radioButton.setFor("customRadio");
            radioButton.setItemIndex(0);
            column.getChildren().add(radioButton);
            radioDataTable.getChildren().add(column);
            
            radioDataTable.getChildren().add(getTableOpcionesSecondColumn());
            radioDataTable.getChildren().add(getTableOpcionesThirdColumn());

            opcionDeRespuestaVisual = radioDataTable;
        }

        //Para las preguntas con respuesta abiertas cortas utilizamos Input
        if (renderInputText) {
            InputText inputText = new InputText();
            inputText.setRendered(renderInputText);
            inputText.setReadonly(true);

            opcionDeRespuestaVisual = inputText;
        }

        //Para las preguntas con respuesta abierta larga utilizamos Area 
        if (renderInputTextArea) {
            InputTextarea inputTextArea = new InputTextarea();
            inputTextArea.setRendered(renderInputTextArea);
            inputTextArea.setReadonly(true);
            
            opcionDeRespuestaVisual = inputTextArea;
        }
    }

    private HtmlPanelGroup getTableOpcionesHeader() {
        HtmlPanelGroup headerGroup = new HtmlPanelGroup();
        headerGroup.setStyle("text-align:left");

        OutputLabel titulo = new OutputLabel();
        titulo.setValue("Opciones");

        Spacer spacer = new Spacer();
        spacer.setWidth("15px");

        CommandButton agregarOpcionButton = new CommandButton();
        agregarOpcionButton.setIcon(Utilities.PLUSTHICK_ICON);
        agregarOpcionButton.setTitle("Agregar Opción");
        agregarOpcionButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        agregarOpcionButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                opcionesList.add(new OpcionRespuesta(pregunta));
            }
        });

        headerGroup.getChildren().add(agregarOpcionButton);
        headerGroup.getChildren().add(spacer);
        headerGroup.getChildren().add(titulo);

        return headerGroup;
    }

    private Column getTableOpcionesFirstColumn(String selectionMode) {
        Column firstColumn = new Column();
        firstColumn.setSelectionMode(selectionMode);
        firstColumn.setStyle("width:16px;text-align:center");

        return firstColumn;
    }

    private Column getTableOpcionesSecondColumn() {
        Column secondColumn = new Column();
        secondColumn.setHeaderText("Descripción");
        secondColumn.setStyle("text-align:left");

        //Esto es para hacer las celdas editables
        CellEditor cellEditor = new CellEditor();
        HtmlOutputText outputText = new HtmlOutputText();
        outputText.setValueExpression("value", createValueExpression("#{opcion.texto}", String.class));
        cellEditor.getFacets().put("output", outputText);
        InputText inputText = new InputText();
        inputText.setStyle("width:96%;text-align:left");
        inputText.setValueExpression("value", createValueExpression("#{opcion.texto}", String.class));
        cellEditor.getFacets().put("input", inputText);

        secondColumn.getChildren().add(cellEditor);

        return secondColumn;
    }
    
    private Column getTableOpcionesThirdColumn() {
        Column thirdColumn = new Column();
        CommandButton removeOpcionButton = new CommandButton();
        removeOpcionButton.setIcon(Utilities.TRASH_ICON);
        removeOpcionButton.setUpdate(Utilities.CUESTIONARIOS_FORM);
        removeOpcionButton.addActionListener(new ActionListener() {
            @Override
            public void processAction(ActionEvent event) throws AbortProcessingException {
                System.out.println("No se como se hace aqui....");
            }
        });
        
        thirdColumn.setStyle("width:5%");
        thirdColumn.getChildren().add(removeOpcionButton);
        
        return thirdColumn;
    }

    public static ValueExpression createValueExpression(String expression, Class<?> expectedType) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getExpressionFactory()
                .createValueExpression(context.getELContext(), expression, expectedType);
    }
    
    public void onSave() {
        //Seteamos el orden de las opcones de respuesta
        for (int i = 0; i < opcionesList.size(); i++) {
            opcionesList.get(i).setOrden(i+1);
        }
        
        pregunta.setTexto(textoPregunta.getValue().toString());
        pregunta.setOpcioneRespuestaList(opcionesList);
        this.grupoPreguntasViewItemPadre.getGrupoPreguntas().getPreguntaList().add(pregunta);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public Map<String, String> getEstiloDeOpcionesMap() {
        return estiloDeOpcionesMap;
    }

    public void setEstiloDeOpcionesMap(Map<String, String> estiloDeOpcionesMap) {
        this.estiloDeOpcionesMap = estiloDeOpcionesMap;
    }

    public String getSelectedOpcion() {
        return selectedOpcion;
    }

    public void setSelectedOpcion(String selectedOpcion) {
        this.selectedOpcion = selectedOpcion;
    }

    public List<OpcionRespuesta> getOpcionesList() {
        return opcionesList;
    }

    public void setOpcionesList(List<OpcionRespuesta> opcionesList) {
        this.opcionesList = opcionesList;
    }

    public Column getSecondRowFirstColumn() {
        return secondRowFirstColumn;
    }

    public void setSecondRowFirstColumn(Column secondRowFirstColumn) {
        this.secondRowFirstColumn = secondRowFirstColumn;
    }

    public UIComponent getOpcionDeRespuestaVisual() {
        return opcionDeRespuestaVisual;
    }

    public void setOpcionDeRespuestaVisual(UIComponent opcionDeRespuestaVisual) {
        this.opcionDeRespuestaVisual = opcionDeRespuestaVisual;
    }

    public boolean isRenderCheckDataTable() {
        return renderCheckDataTable;
    }

    public void setRenderCheckDataTable(boolean renderCheckDataTable) {
        this.renderCheckDataTable = renderCheckDataTable;
    }

    public boolean isRenderRadioDataTable() {
        return renderRadioDataTable;
    }

    public void setRenderRadioDataTable(boolean renderRadioDataTable) {
        this.renderRadioDataTable = renderRadioDataTable;
    }

    public boolean isRenderInputText() {
        return renderInputText;
    }

    public void setRenderInputText(boolean renderInputText) {
        this.renderInputText = renderInputText;
    }

    public boolean isRenderInputTextArea() {
        return renderInputTextArea;
    }

    public void setRenderInputTextArea(boolean renderInputTextArea) {
        this.renderInputTextArea = renderInputTextArea;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    //</editor-fold>

}
