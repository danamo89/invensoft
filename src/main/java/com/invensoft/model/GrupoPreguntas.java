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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "grupos_preguntas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoPreguntas.findAll", query = "SELECT g FROM GrupoPreguntas g"),
    @NamedQuery(name = "GrupoPreguntas.findByIdGrupoPreguntas", query = "SELECT g FROM GrupoPreguntas g WHERE g.idGrupoPreguntas = :idGrupoPreguntas"),
    @NamedQuery(name = "GrupoPreguntas.findByTitulo", query = "SELECT g FROM GrupoPreguntas g WHERE g.titulo = :titulo")})
public class GrupoPreguntas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO_PREGUNTAS", nullable = false)
    private Integer idGrupoPreguntas;
    @Size(max = 255)
    @Column(name = "TITULO", length = 255)
    private String titulo;
    @Column(name = "ORDEN")
    private Integer orden;
    @JoinColumn(name = "ID_CUESTIONARIO", referencedColumnName = "ID_CUESTIONARIO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cuestionario cuestionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoPreguntas", fetch = FetchType.LAZY)
    private List<Pregunta> preguntaList;

    public GrupoPreguntas() {
        preguntaList = new ArrayList<>();
    }

    public GrupoPreguntas(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
        preguntaList = new ArrayList<>();
    }

    public GrupoPreguntas(Integer idGrupoPreguntas) {
        this.idGrupoPreguntas = idGrupoPreguntas;
    }

    public Integer getIdGrupoPreguntas() {
        return idGrupoPreguntas;
    }

    public void setIdGrupoPreguntas(Integer idGrupoPreguntas) {
        this.idGrupoPreguntas = idGrupoPreguntas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoPreguntas != null ? idGrupoPreguntas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoPreguntas)) {
            return false;
        }
        GrupoPreguntas other = (GrupoPreguntas) object;
        if ((this.idGrupoPreguntas == null && other.idGrupoPreguntas != null) || (this.idGrupoPreguntas != null && !this.idGrupoPreguntas.equals(other.idGrupoPreguntas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.GrupoPreguntas[ idGrupoPreguntas=" + idGrupoPreguntas + " ]";
    }
    
}
