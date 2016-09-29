/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "respuestas_preguntas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaPregunta.findAll", query = "SELECT r FROM RespuestaPregunta r"),
    @NamedQuery(name = "RespuestaPregunta.findByIdRespuestaPregunta", query = "SELECT r FROM RespuestaPregunta r WHERE r.idRespuestaPregunta = :idRespuestaPregunta"),
    @NamedQuery(name = "RespuestaPregunta.findByTextoRespuesta", query = "SELECT r FROM RespuestaPregunta r WHERE r.textoRespuesta = :textoRespuesta")})
public class RespuestaPregunta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RESPUESTA_PREGUNTA", nullable = false)
    private Integer idRespuestaPregunta;
    @Size(max = 500)
    @Column(name = "TEXTO_RESPUESTA", length = 500)
    private String textoRespuesta;
    @JoinColumn(name = "ID_OPCION_RESPUESTA", referencedColumnName = "ID_OPCION_RESPUESTA")
    @ManyToOne(optional = true)
    private OpcionRespuesta opcionRespuesta;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA", nullable = false)
    @ManyToOne(optional = false)
    private Pregunta pregunta;

    public RespuestaPregunta() {
    }

    public RespuestaPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
        this.opcionRespuesta = new OpcionRespuesta();
    }
    
    public RespuestaPregunta(Pregunta pregunta, OpcionRespuesta opcionRespuesta) {
        this.pregunta = pregunta;
        this.opcionRespuesta = opcionRespuesta;
    }
    
    public RespuestaPregunta(Pregunta pregunta, String textoRespuesta) {
        this.pregunta = pregunta;
        this.textoRespuesta = textoRespuesta;
        this.opcionRespuesta = new OpcionRespuesta();
    }
    
    public RespuestaPregunta(Integer idRespuestaPregunta) {
        this.idRespuestaPregunta = idRespuestaPregunta;
    }

    public Integer getIdRespuestaPregunta() {
        return idRespuestaPregunta;
    }

    public void setIdRespuestaPregunta(Integer idRespuestaPregunta) {
        this.idRespuestaPregunta = idRespuestaPregunta;
    }

    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public OpcionRespuesta getOpcionRespuesta() {
        return opcionRespuesta;
    }

    public void setOpcionRespuesta(OpcionRespuesta opcionRespuesta) {
        this.opcionRespuesta = opcionRespuesta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Pregunta getPregunta() {
        return pregunta; 
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaPregunta != null ? idRespuestaPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaPregunta)) {
            return false;
        }
        RespuestaPregunta other = (RespuestaPregunta) object;
        if ((this.idRespuestaPregunta == null && other.idRespuestaPregunta != null) || (this.idRespuestaPregunta != null && !this.idRespuestaPregunta.equals(other.idRespuestaPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.RespuestaPregunta[ idRespuestaPregunta=" + idRespuestaPregunta + " ]";
    }
    
    public String getValue() {
        String respuesta = "";
        try {
            switch(pregunta.getEstiloOpciones()) {
                case "Check":
                case "Radio":
                    respuesta = opcionRespuesta.getIdOpcionRespuesta().toString();
                    break;
                case "Input":
                case "Area":
                    respuesta = textoRespuesta;
                    break;
            }
        } catch (Exception e) {
            //ignored
        }
        
        return respuesta;
    }
    
}
