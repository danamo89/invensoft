/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Pregunta.findByTexto", query = "SELECT p FROM Pregunta p WHERE p.texto = :texto")})
public class Pregunta implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<RespuestaActivo> respuestaActivoList;
    @JoinColumn(name = "ID_GRUPO_PREGUNTAS", referencedColumnName = "ID_GRUPO_PREGUNTAS", nullable = false)
    @ManyToOne(optional = false)
    private GrupoPreguntas grupoPreguntas;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Integer idPregunta, String texto) {
        this.idPregunta = idPregunta;
        this.texto = texto;
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

    @XmlTransient
    public List<RespuestaActivo> getRespuestaActivoList() {
        return respuestaActivoList;
    }

    public void setRespuestaActivoList(List<RespuestaActivo> respuestaActivoList) {
        this.respuestaActivoList = respuestaActivoList;
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
    
}
