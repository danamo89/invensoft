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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "documentos_personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentoPersona.findAll", query = "SELECT d FROM DocumentoPersona d"),
    @NamedQuery(name = "DocumentoPersona.findByIdDocumentoPersona", query = "SELECT d FROM DocumentoPersona d WHERE d.idDocumentoPersona = :idDocumentoPersona")})
public class DocumentoPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOCUMENTO_PERSONA", nullable = false)
    private Integer idDocumentoPersona;
    @JoinColumn(name = "ID_DOCUMENTO", referencedColumnName = "ID_DOCUMENTO", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Documento documento;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    public DocumentoPersona() {
    }

    public DocumentoPersona(Documento documento, Persona persona) {
        this.documento = documento;
        this.persona = persona;
    }

    public DocumentoPersona(Integer idDocumentoPersona) {
        this.idDocumentoPersona = idDocumentoPersona;
    }

    public Integer getIdDocumentoPersona() {
        return idDocumentoPersona;
    }

    public void setIdDocumentoPersona(Integer idDocumentoPersona) {
        this.idDocumentoPersona = idDocumentoPersona;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        hash += (idDocumentoPersona != null ? idDocumentoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocumentoPersona)) {
            return false;
        }
        DocumentoPersona other = (DocumentoPersona) object;
        if ((this.idDocumentoPersona == null && other.idDocumentoPersona != null) || (this.idDocumentoPersona != null && !this.idDocumentoPersona.equals(other.idDocumentoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.DocumentoPersona[ idDocumentoPersona=" + idDocumentoPersona + " ]";
    }
    
}
