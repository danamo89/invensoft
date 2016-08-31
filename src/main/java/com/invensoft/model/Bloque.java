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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "bloques")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bloque.findAll", query = "SELECT b FROM Bloque b"),
    @NamedQuery(name = "Bloque.findByIdBloque", query = "SELECT b FROM Bloque b WHERE b.idBloque = :idBloque"),
    @NamedQuery(name = "Bloque.findByTitulo", query = "SELECT b FROM Bloque b WHERE b.titulo = :titulo"),
    @NamedQuery(name = "Bloque.findByDescripcion", query = "SELECT b FROM Bloque b WHERE b.descripcion = :descripcion")})
public class Bloque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_BLOQUE", nullable = false)
    private Integer idBloque;
    @Size(max = 45)
    @Column(name = "TITULO", length = 45)
    private String titulo;
    @Size(max = 500)
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;
    @JoinColumn(name = "ID_CUESTIONARIO", referencedColumnName = "ID_CUESTIONARIO", nullable = false)
    @ManyToOne(optional = false)
    private Cuestionario idCuestionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bloque")
    private List<GrupoPreguntas> grupoPreguntasList;

    public Bloque() {
    }

    public Bloque(Integer idBloque) {
        this.idBloque = idBloque;
    }

    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
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

    public Cuestionario getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Cuestionario idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    @XmlTransient
    public List<GrupoPreguntas> getGrupoPreguntasList() {
        return grupoPreguntasList;
    }

    public void setGrupoPreguntasList(List<GrupoPreguntas> grupoPreguntasList) {
        this.grupoPreguntasList = grupoPreguntasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBloque != null ? idBloque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bloque)) {
            return false;
        }
        Bloque other = (Bloque) object;
        if ((this.idBloque == null && other.idBloque != null) || (this.idBloque != null && !this.idBloque.equals(other.idBloque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Bloque[ idBloque=" + idBloque + " ]";
    }
    
}
