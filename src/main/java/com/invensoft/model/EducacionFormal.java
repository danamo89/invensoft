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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "educaciones_formales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EducacionFormal.findAll", query = "SELECT e FROM EducacionFormal e"),
    @NamedQuery(name = "EducacionFormal.findByIdEducacionFormal", query = "SELECT e FROM EducacionFormal e WHERE e.idEducacionFormal = :idEducacionFormal"),
    @NamedQuery(name = "EducacionFormal.findByNivelEstudio", query = "SELECT e FROM EducacionFormal e WHERE e.nivelEstudio = :nivelEstudio"),
    @NamedQuery(name = "EducacionFormal.findByAnioEgreso", query = "SELECT e FROM EducacionFormal e WHERE e.anioEgreso = :anioEgreso"),
    @NamedQuery(name = "EducacionFormal.findByInstitucion", query = "SELECT e FROM EducacionFormal e WHERE e.institucion = :institucion"),
    @NamedQuery(name = "EducacionFormal.findByTitulo", query = "SELECT e FROM EducacionFormal e WHERE e.titulo = :titulo")})
public class EducacionFormal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EDUCACION_FORMAL", nullable = false)
    private Integer idEducacionFormal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NIVEL_ESTUDIO", nullable = false, length = 50)
    private String nivelEstudio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO_EGRESO", nullable = false)
    private int anioEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTITUCION", nullable = false, length = 255)
    private String institucion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TITULO", nullable = false, length = 100)
    private String titulo;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public EducacionFormal() {
    }

    public EducacionFormal(Integer idEducacionFormal) {
        this.idEducacionFormal = idEducacionFormal;
    }

    public EducacionFormal(Integer idEducacionFormal, String nivelEstudio, int anioEgreso, String institucion, String titulo) {
        this.idEducacionFormal = idEducacionFormal;
        this.nivelEstudio = nivelEstudio;
        this.anioEgreso = anioEgreso;
        this.institucion = institucion;
        this.titulo = titulo;
    }

    public Integer getIdEducacionFormal() {
        return idEducacionFormal;
    }

    public void setIdEducacionFormal(Integer idEducacionFormal) {
        this.idEducacionFormal = idEducacionFormal;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public int getAnioEgreso() {
        return anioEgreso;
    }

    public void setAnioEgreso(int anioEgreso) {
        this.anioEgreso = anioEgreso;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEducacionFormal != null ? idEducacionFormal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EducacionFormal)) {
            return false;
        }
        EducacionFormal other = (EducacionFormal) object;
        if ((this.idEducacionFormal == null && other.idEducacionFormal != null) || (this.idEducacionFormal != null && !this.idEducacionFormal.equals(other.idEducacionFormal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.EducacionFormal[ idEducacionFormal=" + idEducacionFormal + " ]";
    }
    
}
