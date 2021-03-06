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
@Table(name = "cuestionarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuestionario.findAll", query = "SELECT c FROM Cuestionario c"),
    @NamedQuery(name = "Cuestionario.findByIdCuestionario", query = "SELECT c FROM Cuestionario c WHERE c.idCuestionario = :idCuestionario"),
    @NamedQuery(name = "Cuestionario.findByTitulo", query = "SELECT c FROM Cuestionario c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "Cuestionario.findByDescripcion", query = "SELECT c FROM Cuestionario c WHERE c.descripcion = :descripcion")})
public class Cuestionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CUESTIONARIO", nullable = false)
    private Integer idCuestionario;
    @Size(max = 45)
    @Column(name = "TITULO", length = 45)
    private String titulo;
    @Size(max = 500)
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuestionario")
    private List<Bloque> bloqueList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuestionario")
    private List<TipoActivo> tipoActivoList;

    public Cuestionario() {
    }

    public Cuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Bloque> getBloqueList() {
        return bloqueList;
    }

    public void setBloqueList(List<Bloque> bloqueList) {
        this.bloqueList = bloqueList;
    }

    @XmlTransient
    public List<TipoActivo> getTipoActivoList() {
        return tipoActivoList;
    }

    public void setTipoActivoList(List<TipoActivo> tipoActivoList) {
        this.tipoActivoList = tipoActivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuestionario != null ? idCuestionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuestionario)) {
            return false;
        }
        Cuestionario other = (Cuestionario) object;
        if ((this.idCuestionario == null && other.idCuestionario != null) || (this.idCuestionario != null && !this.idCuestionario.equals(other.idCuestionario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Cuestionario[ idCuestionario=" + idCuestionario + " ]";
    }
    
}
