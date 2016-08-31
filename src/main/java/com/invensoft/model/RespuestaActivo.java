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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "respuestas_activos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaActivo.findAll", query = "SELECT r FROM RespuestaActivo r"),
    @NamedQuery(name = "RespuestaActivo.findByIdRespuestaActivo", query = "SELECT r FROM RespuestaActivo r WHERE r.idRespuestaActivo = :idRespuestaActivo"),
    @NamedQuery(name = "RespuestaActivo.findByTexto", query = "SELECT r FROM RespuestaActivo r WHERE r.texto = :texto")})
public class RespuestaActivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_RESPUESTA_ACTIVO", nullable = false)
    private Integer idRespuestaActivo;
    @Size(max = 500)
    @Column(name = "TEXTO", length = 500)
    private String texto;
    @JoinColumn(name = "ID_ACTIVO", referencedColumnName = "ID_ACTIVO", nullable = false)
    @ManyToOne(optional = false)
    private Activo idActivo;
    @JoinColumn(name = "ID_OPCION", referencedColumnName = "ID_OPCION")
    @ManyToOne
    private Opcion idOpcion;
    @JoinColumn(name = "ID_PREGUNTA", referencedColumnName = "ID_PREGUNTA", nullable = false)
    @ManyToOne(optional = false)
    private Pregunta idPregunta;

    public RespuestaActivo() {
    }

    public RespuestaActivo(Integer idRespuestaActivo) {
        this.idRespuestaActivo = idRespuestaActivo;
    }

    public Integer getIdRespuestaActivo() {
        return idRespuestaActivo;
    }

    public void setIdRespuestaActivo(Integer idRespuestaActivo) {
        this.idRespuestaActivo = idRespuestaActivo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Activo getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Activo idActivo) {
        this.idActivo = idActivo;
    }

    public Opcion getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Opcion idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Pregunta getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Pregunta idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuestaActivo != null ? idRespuestaActivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaActivo)) {
            return false;
        }
        RespuestaActivo other = (RespuestaActivo) object;
        if ((this.idRespuestaActivo == null && other.idRespuestaActivo != null) || (this.idRespuestaActivo != null && !this.idRespuestaActivo.equals(other.idRespuestaActivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.RespuestaActivo[ idRespuestaActivo=" + idRespuestaActivo + " ]";
    }
    
}
