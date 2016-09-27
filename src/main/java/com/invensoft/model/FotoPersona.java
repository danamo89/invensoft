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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "foto_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FotoPersona.findAll", query = "SELECT f FROM FotoPersona f"),
    @NamedQuery(name = "FotoPersona.findByIdFotoPersona", query = "SELECT f FROM FotoPersona f WHERE f.idFotoPersona = :idFotoPersona"),
    @NamedQuery(name = "FotoPersona.findByExtension", query = "SELECT f FROM FotoPersona f WHERE f.extension = :extension")})
public class FotoPersona implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "foto", nullable = false)
    private byte[] foto;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_foto_persona", nullable = false)
    private Integer idFotoPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "extension", nullable = false, length = 4)
    private String extension;
    @OneToMany(mappedBy = "fotoPersona")
    private List<Persona> personaList;

    public FotoPersona() {
    }

    public FotoPersona(Integer idFotoPersona) {
        this.idFotoPersona = idFotoPersona;
    }

    public FotoPersona(Integer idFotoPersona, byte[] foto, String extension) {
        this.idFotoPersona = idFotoPersona;
        this.foto = foto;
        this.extension = extension;
    }

    public Integer getIdFotoPersona() {
        return idFotoPersona;
    }

    public void setIdFotoPersona(Integer idFotoPersona) {
        this.idFotoPersona = idFotoPersona;
    }


    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFotoPersona != null ? idFotoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FotoPersona)) {
            return false;
        }
        FotoPersona other = (FotoPersona) object;
        if ((this.idFotoPersona == null && other.idFotoPersona != null) || (this.idFotoPersona != null && !this.idFotoPersona.equals(other.idFotoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.FotoPersona[ idFotoPersona=" + idFotoPersona + " ]";
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    
}
