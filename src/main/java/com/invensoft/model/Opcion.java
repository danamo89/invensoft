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
@Table(name = "opciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o"),
    @NamedQuery(name = "Opcion.findByIdOpcion", query = "SELECT o FROM Opcion o WHERE o.idOpcion = :idOpcion"),
    @NamedQuery(name = "Opcion.findByTexto", query = "SELECT o FROM Opcion o WHERE o.texto = :texto")})
public class Opcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPCION", nullable = false)
    private Integer idOpcion;
    @Size(max = 100)
    @Column(name = "TEXTO", length = 100)
    private String texto;
    @OneToMany(mappedBy = "idOpcion")
    private List<RespuestaActivo> respuestaActivoList;
    @JoinColumn(name = "ID_GRUPO_OPCIONES", referencedColumnName = "ID_GRUPO_OPCIONES", nullable = false)
    @ManyToOne(optional = false)
    private GrupoOpciones idGrupoOpciones;

    public Opcion() {
    }

    public Opcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
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

    public GrupoOpciones getIdGrupoOpciones() {
        return idGrupoOpciones;
    }

    public void setIdGrupoOpciones(GrupoOpciones idGrupoOpciones) {
        this.idGrupoOpciones = idGrupoOpciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Opcion[ idOpcion=" + idOpcion + " ]";
    }
    
}
