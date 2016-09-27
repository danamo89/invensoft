/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;
import org.primefaces.component.selectoneradio.SelectOneRadio;

/**
 *
 * @author dnavarro
 */
public class RespuestaPregunta {
    
    private Pregunta pregunta;
    private OpcionRespuesta opcionRespuesta;
    private String textoRespuesta;

    public RespuestaPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public RespuestaPregunta(Pregunta pregunta, OpcionRespuesta opcionRespuesta) {
        this.pregunta = pregunta;
        this.opcionRespuesta = opcionRespuesta;
    }

    public RespuestaPregunta(Pregunta pregunta, String textoRespuesta) {
        this.pregunta = pregunta;
        this.textoRespuesta = textoRespuesta;
    }
    

    public String getValue() {
        String respuesta = "";
        switch (pregunta.getEstiloOpciones()) {
            case "Check":
            case "Radio":
                respuesta = opcionRespuesta.getTexto();
                break;
            case "Input":
            case "Area":
                respuesta = textoRespuesta;
                break;
        }
        
        return respuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionRespuesta getOpcionRespuesta() {
        if (opcionRespuesta == null) {
            opcionRespuesta = new OpcionRespuesta();
        }
        return opcionRespuesta;
    }

    public void setOpcionRespuesta(OpcionRespuesta opcionRespuesta) {
        this.opcionRespuesta = opcionRespuesta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }
    
}
