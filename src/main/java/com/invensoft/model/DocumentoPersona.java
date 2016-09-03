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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nanchy_2
 */
@Entity
@Table(name="documentos_persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="DocumentosPersona.findAll", query="SELECT dp FROM DocumentoPersona dp")
})

public class DocumentoPersona implements Serializable {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOCUMENTO_PERSONA", nullable = false)
    private Integer idDocumentoPersona;
    
    @JoinColumn(name = "ID_DOCUMENTO", referencedColumnName = "ID_DOCUMENTO", nullable = false)
    @ManyToOne(optional = false)
    private Documento documento;
    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional = false)
    private Persona persona;
    
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
    
    
    
}
