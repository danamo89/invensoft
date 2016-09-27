/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model.custom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import static java.util.stream.Collectors.toMap;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlPanelGroup;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author David
 */
public class OpcionesRespuestaViewItem extends HtmlPanelGroup implements Serializable {
    
    private List<String> alineacionesList;
    private Map<String,String> presentacionesMap;
    
    private String alineacion;
    private String presentacion;
    private List<String> opcionesList;

    public OpcionesRespuestaViewItem() {
        this.opcionesList = new ArrayList<>();
        this.alineacionesList = new ArrayList();
        this.presentacionesMap = new HashMap<>();
        
        //Defaults
        this.alineacion = "Vertical";
        this.presentacion = "Check";
        
        fillLists();
        configure();
    }
    
    private void fillLists() {
        alineacionesList.add("Horizontal");
        alineacionesList.add("Vertical");
        
        presentacionesMap.put("Seleccion multiple", "Check");
        presentacionesMap.put("Seleccion simple", "Radio");
        presentacionesMap.put("Texto corto", "Input");
        presentacionesMap.put("Texto largo", "Area");
        
        //Con esto se ordenta automaticamente el mapa
        presentacionesMap = new TreeMap<>(presentacionesMap);
    }
    
    private void configure() {
        SelectOneMenu alineacionesDropdown = new SelectOneMenu();
        alineacionesDropdown.setValue(alineacion);
        for (String alineacionItem : alineacionesList) {
            UISelectItem selectItem = new UISelectItem();
            selectItem.setItemLabel(alineacionItem);
            selectItem.setItemValue(alineacionItem);
            alineacionesDropdown.getChildren().add(selectItem);
        }
        
        SelectOneMenu presentacionesDropdown = new SelectOneMenu();
        presentacionesDropdown.setValue(presentacion);
        for (String key : presentacionesMap.keySet()) {
            UISelectItem selectItem = new UISelectItem();
            selectItem.setItemLabel(key);
            selectItem.setItemValue(presentacionesMap.get(key));
            presentacionesDropdown.getChildren().add(selectItem);
        }
        
        // Esta de alineacion la dejamos para más adelante. Por ahora van todas
        // en vertical.
        //this.getChildren().add(alineacionesDropdown);
        this.getChildren().add(presentacionesDropdown);
    }
    
    
    /*
    SelectManyCheckbox checkBoxOptions = new SelectManyCheckbox();
    checkBoxOptions.setStyle("text-align: left; padding-left: 20px");
    checkBoxOptions.setLayout("grid");
    checkBoxOptions.setColumns(1);

    for (String opcion : opcionViewItem.getListOpciones()) {
        UISelectItem selectItem = new UISelectItem();
        selectItem.setItemLabel(opcion);
        selectItem.setItemValue(opcion);
        checkBoxOptions.getChildren().add(selectItem);
    }
    */
    
    
}
