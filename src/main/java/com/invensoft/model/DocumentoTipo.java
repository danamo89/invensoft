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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nanchy_2
 */
@Entity
@Table(name="documentos_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="DocumentoTipo.findAll", query="SELECT dt FROM DocumentoTipo dt"),
    @NamedQuery(name="DocumentoTipo.findByIdDocumentoTipo", query="SELECT dt FROM DocumentoTipo dt WHERE dt.idDocumentoTipo = :idDocumentoTipo")
})
public class DocumentoTipo implements Serializable {
       
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DOCUMENTO_TIPO", nullable = false)
    private Integer idDocumentoTipo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DESCRIPCION", nullable = false, length = 45)
    
    private String descripcion;

    public Integer getIdDocumentoTipo() {
        return idDocumentoTipo;
    }

    public void setIdDocumentoTipo(Integer idDocumentoTipo) {
        this.idDocumentoTipo = idDocumentoTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
