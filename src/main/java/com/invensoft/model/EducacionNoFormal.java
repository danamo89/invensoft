/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "educaciones_no_formales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EducacionNoFormal.findAll", query = "SELECT e FROM EducacionNoFormal e"),
    @NamedQuery(name = "EducacionNoFormal.findByIdEducacionNoFormal", query = "SELECT e FROM EducacionNoFormal e WHERE e.idEducacionNoFormal = :idEducacionNoFormal"),
    @NamedQuery(name = "EducacionNoFormal.findByFecha", query = "SELECT e FROM EducacionNoFormal e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "EducacionNoFormal.findByInstitucion", query = "SELECT e FROM EducacionNoFormal e WHERE e.institucion = :institucion"),
    @NamedQuery(name = "EducacionNoFormal.findByTipoEducacionNoformal", query = "SELECT e FROM EducacionNoFormal e WHERE e.tipoEducacionNoFormal = :tipoEducacionNoFormal"),
    @NamedQuery(name = "EducacionNoFormal.findByTema", query = "SELECT e FROM EducacionNoFormal e WHERE e.tema = :tema")})
public class EducacionNoFormal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EDUCACION_NO_FORMAL", nullable = false)
    private Integer idEducacionNoFormal;
    @Column(name = "FECHA")
//    @Temporal(TemporalType.TIMESTAMP)
    private String fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTITUCION", nullable = false, length = 255)
    private String institucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEMA", nullable = false, length = 255)
    private String tema;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    @NotNull
    @Column(name = "TIPO_EDUCACION_NO_FORMAL", nullable = false, length = 50)
    private String tipoEducacionNoFormal;

    public EducacionNoFormal() {
    }

    public EducacionNoFormal(Integer idEducacionNoFormal) {
        this.idEducacionNoFormal = idEducacionNoFormal;
    }

    public EducacionNoFormal(Integer idEducacionNoFormal, String institucion, String tema) {
        this.idEducacionNoFormal = idEducacionNoFormal;
        this.institucion = institucion;
        this.tema = tema;
    }

    public Integer getIdEducacionNoFormal() {
        return idEducacionNoFormal;
    }

    public void setIdEducacionNoFormal(Integer idEducacionNoFormal) {
        this.idEducacionNoFormal = idEducacionNoFormal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTipoEducacionNoFormal() {
        return tipoEducacionNoFormal;
    }

    public void setTipoEducacionNoFormal(String tipoEducacionNoFormal) {
        this.tipoEducacionNoFormal = tipoEducacionNoFormal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEducacionNoFormal != null ? idEducacionNoFormal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EducacionNoFormal)) {
            return false;
        }
        EducacionNoFormal other = (EducacionNoFormal) object;
        if ((this.idEducacionNoFormal == null && other.idEducacionNoFormal != null) || (this.idEducacionNoFormal != null && !this.idEducacionNoFormal.equals(other.idEducacionNoFormal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.EducacionNoFormal[ idEducacionNoFormal=" + idEducacionNoFormal + " ]";
    }
    
}
