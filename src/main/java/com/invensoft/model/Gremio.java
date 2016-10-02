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
 * @author Nanchy-PC
 */
@Entity
@Table(name = "gremios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gremios.findAll", query = "SELECT g FROM Gremio g"),
    @NamedQuery(name = "Gremios.findByIdGremio", query = "SELECT g FROM Gremio g WHERE g.idGremio = :idGremio"),
    @NamedQuery(name = "Gremios.findByDescripcion", query = "SELECT g FROM Gremio g WHERE g.descripcion = :descripcion")})
public class Gremio implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "sigla", nullable = false, length = 45)
    private String sigla;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gremio", nullable = false)
    private Integer idGremio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
    @OneToMany(mappedBy = "idGremio", fetch = FetchType.LAZY)
    private List<CategoriaLaboral> categoriasLaboralesList;

    public Gremio() {
    }

    public Gremio(Integer idGremio) {
        this.idGremio = idGremio;
    }

    public Gremio(Integer idGremio, String descripcion) {
        this.idGremio = idGremio;
        this.descripcion = descripcion;
    }

    public Integer getIdGremio() {
        return idGremio;
    }

    public void setIdGremio(Integer idGremio) {
        this.idGremio = idGremio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<CategoriaLaboral> getCategoriasLaboralesList() {
        return categoriasLaboralesList;
    }

    public void setCategoriasLaboralesList(List<CategoriaLaboral> categoriasLaboralesList) {
        this.categoriasLaboralesList = categoriasLaboralesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGremio != null ? idGremio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gremio)) {
            return false;
        }
        Gremio other = (Gremio) object;
        if ((this.idGremio == null && other.idGremio != null) || (this.idGremio != null && !this.idGremio.equals(other.idGremio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Gremios[ idGremio=" + idGremio + " ]";
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
}
