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
@Table(name="documentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Documento.findAll", query="SELECT d FROM Documento d"),
    @NamedQuery(name="Documento.findDocumentosByTipo", query="SELECT d FROM Documento d WHERE d.documentoTipo.idDocumentoTipo = :idDocumentoTipo")
})

public class Documento implements Serializable {
    public static final Integer DOCUMENTO_RRHH = 1;
    public static final Integer DOCUMENTO_PERSONALES = 2;
    public static final Integer DOCUMENTO_FAMILIARES = 3;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOCUMENTO", nullable = false)
    private Integer idDocumento;
    
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    
    @JoinColumn(name="ID_DOCUMENTO_TIPO", referencedColumnName = "ID_DOCUMENTO_TIPO", nullable=false)
    @ManyToOne(optional=false)
    private DocumentoTipo documentoTipo;
    
    public Documento() {
        
    }
    
    public Documento (Integer idDocumento) {
        this.idDocumento = idDocumento;
    }
    
    public Documento(Integer idDocumento, String descripcion) {
        this.idDocumento = idDocumento;
        this.descripcion = descripcion;
    }
    
    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public DocumentoTipo getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoTipo documentoTipo) {
        this.documentoTipo = documentoTipo;
    }
}
