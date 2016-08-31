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
@Table(name = "grupos_opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoOpciones.findAll", query = "SELECT g FROM GrupoOpciones g"),
    @NamedQuery(name = "GrupoOpciones.findByIdGrupoOpciones", query = "SELECT g FROM GrupoOpciones g WHERE g.idGrupoOpciones = :idGrupoOpciones"),
    @NamedQuery(name = "GrupoOpciones.findByTipoVisualizacion", query = "SELECT g FROM GrupoOpciones g WHERE g.tipoVisualizacion = :tipoVisualizacion"),
    @NamedQuery(name = "GrupoOpciones.findByTitulo", query = "SELECT g FROM GrupoOpciones g WHERE g.titulo = :titulo")})
public class GrupoOpciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GRUPO_OPCIONES", nullable = false)
    private Integer idGrupoOpciones;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "TIPO_VISUALIZACION", nullable = false, length = 45)
    private String tipoVisualizacion;
    @Size(max = 45)
    @Column(name = "TITULO", length = 45)
    private String titulo;
    @JoinColumn(name = "ID_GRUPO_PREGUNTAS", referencedColumnName = "ID_GRUPO_PREGUNTAS", nullable = false)
    @ManyToOne(optional = false)
    private GrupoPreguntas grupoPreguntas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupoOpciones")
    private List<Opcion> opcionList;

    public GrupoOpciones() {
    }

    public GrupoOpciones(Integer idGrupoOpciones) {
        this.idGrupoOpciones = idGrupoOpciones;
    }

    public GrupoOpciones(Integer idGrupoOpciones, String tipoVisualizacion) {
        this.idGrupoOpciones = idGrupoOpciones;
        this.tipoVisualizacion = tipoVisualizacion;
    }

    public Integer getIdGrupoOpciones() {
        return idGrupoOpciones;
    }

    public void setIdGrupoOpciones(Integer idGrupoOpciones) {
        this.idGrupoOpciones = idGrupoOpciones;
    }

    public String getTipoVisualizacion() {
        return tipoVisualizacion;
    }

    public void setTipoVisualizacion(String tipoVisualizacion) {
        this.tipoVisualizacion = tipoVisualizacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public GrupoPreguntas getGrupoPreguntas() {
        return grupoPreguntas;
    }

    public void setGrupoPreguntas(GrupoPreguntas grupoPreguntas) {
        this.grupoPreguntas = grupoPreguntas;
    }

    @XmlTransient
    public List<Opcion> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoOpciones != null ? idGrupoOpciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoOpciones)) {
            return false;
        }
        GrupoOpciones other = (GrupoOpciones) object;
        if ((this.idGrupoOpciones == null && other.idGrupoOpciones != null) || (this.idGrupoOpciones != null && !this.idGrupoOpciones.equals(other.idGrupoOpciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.GrupoOpciones[ idGrupoOpciones=" + idGrupoOpciones + " ]";
    }
    
}
