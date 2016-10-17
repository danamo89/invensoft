/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
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
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @Size(max = 500)
    @Column(name = "IDS_OPCIONES_RESPUESTA", length = 500)
    private String idsOpcionesRespuesta;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA", nullable = false)
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    
    @Transient
    private List<Integer> arrayIdsOpcionesRespuesta;
    @Transient
    private Integer idOpcionRespuesta;

    public RespuestaPregunta() {
    }

    public RespuestaPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }
    
    public RespuestaPregunta(Integer idRespuestaPregunta) {
        this.idRespuestaPregunta = idRespuestaPregunta;
    }
    
    @PostLoad
    public void postLoad() {
        if (idsOpcionesRespuesta != null && !idsOpcionesRespuesta.isEmpty()) {
            switch(pregunta.getEstiloOpciones()) {
                case "Check":
                    if (arrayIdsOpcionesRespuesta == null) {
                        arrayIdsOpcionesRespuesta = new LinkedList<>();
                    }
                    
                    for (int i = 0; i < idsOpcionesRespuesta.split(",").length; i++) {
                        arrayIdsOpcionesRespuesta.add(Integer.valueOf(idsOpcionesRespuesta.split(",")[i]));
                    }
                    break;
                case "Radio":
                    idOpcionRespuesta = Integer.valueOf(idsOpcionesRespuesta);
                    break;
            }
        }
    }
    
    @PrePersist
    public void prePersist() {
        idsOpcionesRespuesta = "";
        if ((arrayIdsOpcionesRespuesta != null && arrayIdsOpcionesRespuesta.size() > 0) || (idOpcionRespuesta != null)) {
            switch(pregunta.getEstiloOpciones()) {
                case "Check":
                    for (int i = 0; i < arrayIdsOpcionesRespuesta.size(); i++) {
                        if (arrayIdsOpcionesRespuesta.get(i) != null) {
                            idsOpcionesRespuesta += arrayIdsOpcionesRespuesta.get(i);
                            if (i<arrayIdsOpcionesRespuesta.size()-1) {
                                idsOpcionesRespuesta += ",";
                            }
                        }
                    }
                    break;
                case "Radio":
                    idsOpcionesRespuesta = String.valueOf(idOpcionRespuesta);
                    break;
            }
        }
    }
    
    public void traspose() {
        this.prePersist();
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

    public String getIdsOpcionesRespuesta() {
        return idsOpcionesRespuesta;
    }

    public void setIdsOpcionesRespuesta(String idsOpcionesRespuesta) {
        this.idsOpcionesRespuesta = idsOpcionesRespuesta;
    }

    public List<Integer> getArrayIdsOpcionesRespuesta() {
        return arrayIdsOpcionesRespuesta;
    }

    public void setArrayIdsOpcionesRespuesta(List<Integer> arrayIdsOpcionesRespuesta) {
        this.arrayIdsOpcionesRespuesta = arrayIdsOpcionesRespuesta;
    }

    public Integer getIdOpcionRespuesta() {
        return idOpcionRespuesta;
    }

    public void setIdOpcionRespuesta(Integer idOpcionRespuesta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
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
                    respuesta = arrayIdsOpcionesRespuesta != null && arrayIdsOpcionesRespuesta.size() > 0 ? "1" : "";
                    break;
                case "Radio":
                    respuesta = idOpcionRespuesta != null ? "1" : "";
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
