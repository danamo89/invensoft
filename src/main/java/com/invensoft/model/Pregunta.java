/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "preguntas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "Pregunta.findByTexto", query = "SELECT p FROM Pregunta p WHERE p.texto = :texto"),
    @NamedQuery(name = "Pregunta.findByEstiloOpciones", query = "SELECT p FROM Pregunta p WHERE p.estiloOpciones = :estiloOpciones")})
public class Pregunta implements Serializable, Comparable<Pregunta> {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PREGUNTA", nullable = false)
    private Integer idPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TEXTO", nullable = false, length = 255)
    private String texto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ESTILO_OPCIONES", nullable = false, length = 45)
    private String estiloOpciones;
    @Column(name = "ORDEN")
    private Integer orden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Pregunta", fetch = FetchType.LAZY)
    private List<OpcionRespuesta> opcioneRespuestaList;
    @JoinColumn(name = "ID_GRUPO_PREGUNTAS", referencedColumnName = "ID_GRUPO_PREGUNTAS", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GrupoPreguntas grupoPreguntas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pregunta", fetch = FetchType.LAZY)
    private List<RespuestaPregunta> respuestaPreguntaList;

    public Pregunta() {
        opcioneRespuestaList = new ArrayList<>();
    }

    public Pregunta(GrupoPreguntas grupoPreguntas) {
        this.grupoPreguntas = grupoPreguntas;
        opcioneRespuestaList = new ArrayList<>();
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Integer idPregunta, String texto, String estiloOpciones) {
        this.idPregunta = idPregunta;
        this.texto = texto;
        this.estiloOpciones = estiloOpciones;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getEstiloOpciones() {
        return estiloOpciones;
    }

    public void setEstiloOpciones(String estiloOpciones) {
        this.estiloOpciones = estiloOpciones;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<OpcionRespuesta> getOpcioneRespuestaList() {
        return opcioneRespuestaList;
    }

    public void setOpcioneRespuestaList(List<OpcionRespuesta> opcioneRespuestaList) {
        this.opcioneRespuestaList = opcioneRespuestaList;
    }

    public GrupoPreguntas getGrupoPreguntas() {
        return grupoPreguntas;
    }

    public void setGrupoPreguntas(GrupoPreguntas grupoPreguntas) {
        this.grupoPreguntas = grupoPreguntas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Pregunta[ idPregunta=" + idPregunta + " ]";
    }

    @Override
    public int compareTo(Pregunta o) {
        int r = orden.compareTo(o.getOrden());
        return r;
    }

    @XmlTransient
    public List<RespuestaPregunta> getRespuestaPreguntaList() {
        return respuestaPreguntaList;
    }

    public void setRespuestaPreguntaList(List<RespuestaPregunta> respuestaPreguntaList) {
        this.respuestaPreguntaList = respuestaPreguntaList;
    }
    
}
