/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "opciones_respuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionRespuesta.findAll", query = "SELECT o FROM OpcionRespuesta o"),
    @NamedQuery(name = "OpcionRespuesta.findByIdOpcionRespuesta", query = "SELECT o FROM OpcionRespuesta o WHERE o.idOpcionRespuesta = :idOpcionRespuesta"),
    @NamedQuery(name = "OpcionRespuesta.findByTexto", query = "SELECT o FROM OpcionRespuesta o WHERE o.texto = :texto")})
public class OpcionRespuesta implements Serializable, Comparable<OpcionRespuesta> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPCION_RESPUESTA", nullable = false)
    private Integer idOpcionRespuesta;
    @Size(max = 500)
    @Column(name = "TEXTO")
    private String texto;
    @Column(name = "ORDEN")
    private Integer orden;
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA", nullable = false)
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    @OneToMany(mappedBy = "opcionRespuesta", fetch = FetchType.LAZY)
    private List<RespuestaPregunta> respuestaPreguntaList;
    
    public OpcionRespuesta() {
    }

    public OpcionRespuesta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionRespuesta(Integer idOpcionRespuesta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
    }

    public OpcionRespuesta(Integer idOpcionRespuesta, Pregunta pregunta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
        this.pregunta = pregunta;
    }

    public Integer getIdOpcionRespuesta() {
        return idOpcionRespuesta;
    }

    public void setIdOpcionRespuesta(Integer idOpcionRespuesta) {
        this.idOpcionRespuesta = idOpcionRespuesta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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
        hash += (idOpcionRespuesta != null ? idOpcionRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionRespuesta)) {
            return false;
        }
        OpcionRespuesta other = (OpcionRespuesta) object;
        if ((this.idOpcionRespuesta == null && other.idOpcionRespuesta != null) || (this.idOpcionRespuesta != null && !this.idOpcionRespuesta.equals(other.idOpcionRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.OpcioneRespuesta[ idOpcionRespuesta=" + idOpcionRespuesta + " ]";
    }

    @Override
    public int compareTo(OpcionRespuesta o) {
        return orden.compareTo(o.getOrden());
    }

    @XmlTransient
    public List<RespuestaPregunta> getRespuestaPreguntaList() {
        return respuestaPreguntaList;
    }

    public void setRespuestaPreguntaList(List<RespuestaPregunta> respuestaPreguntaList) {
        this.respuestaPreguntaList = respuestaPreguntaList;
    }
    
}
