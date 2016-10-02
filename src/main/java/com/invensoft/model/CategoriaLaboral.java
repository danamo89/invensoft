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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nanchy-PC
 */
@Entity
@Table(name = "categorias_laborales", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_categoria_laboral"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriasLaborales.findAll", query = "SELECT c FROM CategoriaLaboral c"),
    @NamedQuery(name = "CategoriasLaborales.findByIdCategoriaLaboral", query = "SELECT c FROM CategoriaLaboral c WHERE c.idCategoriaLaboral = :idCategoriaLaboral"),
    @NamedQuery(name = "CategoriasLaborales.findByEsc", query = "SELECT c FROM CategoriaLaboral c WHERE c.esc = :esc"),
    @NamedQuery(name = "CategoriasLaborales.findByCat", query = "SELECT c FROM CategoriaLaboral c WHERE c.cat = :cat"),
    @NamedQuery(name = "CategoriasLaborales.findByDescripcion", query = "SELECT c FROM CategoriaLaboral c WHERE c.descripcion = :descripcion")})
public class CategoriaLaboral implements Serializable {

    @OneToMany(mappedBy = "idCategoriaLaboral", fetch = FetchType.LAZY)
    private List<Persona> personaList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria_laboral", nullable = false)
    private Integer idCategoriaLaboral;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "esc", nullable = false, length = 45)
    private String esc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "cat", nullable = false, length = 45)
    private String cat;
    @Size(max = 100)
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @JoinColumn(name = "id_gremio", referencedColumnName = "id_gremio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gremio idGremio;

    public CategoriaLaboral() {
        this.idCategoriaLaboral = 0;
    }

    public CategoriaLaboral(Integer idCategoriaLaboral) {
        this.idCategoriaLaboral = idCategoriaLaboral;
    }

    public CategoriaLaboral(Integer idCategoriaLaboral, String esc, String cat) {
        this.idCategoriaLaboral = idCategoriaLaboral;
        this.esc = esc;
        this.cat = cat;
    }

    public Integer getIdCategoriaLaboral() {
        return idCategoriaLaboral;
    }

    public void setIdCategoriaLaboral(Integer idCategoriaLaboral) {
        this.idCategoriaLaboral = idCategoriaLaboral;
    }

    public String getEsc() {
        return esc;
    }

    public void setEsc(String esc) {
        this.esc = esc;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Gremio getIdGremio() {
        return idGremio;
    }

    public void setIdGremio(Gremio idGremio) {
        this.idGremio = idGremio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoriaLaboral != null ? idCategoriaLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaLaboral)) {
            return false;
        }
        CategoriaLaboral other = (CategoriaLaboral) object;
        if ((this.idCategoriaLaboral == null && other.idCategoriaLaboral != null) || (this.idCategoriaLaboral != null && !this.idCategoriaLaboral.equals(other.idCategoriaLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.CategoriasLaborales[ idCategoriaLaboral=" + idCategoriaLaboral + " ]";
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
    
}
