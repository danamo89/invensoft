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
 * @author David
 */
@Entity
@Table(name = "configuraciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c"),
    @NamedQuery(name = "Configuracion.findByIdConfiguracion", query = "SELECT c FROM Configuracion c WHERE c.idConfiguracion = :idConfiguracion"),
    @NamedQuery(name = "Configuracion.findByParametro", query = "SELECT c FROM Configuracion c WHERE c.parametro = :parametro"),
    @NamedQuery(name = "Configuracion.findByValor", query = "SELECT c FROM Configuracion c WHERE c.valor = :valor"),
    @NamedQuery(name = "Configuracion.findByDescripcion", query = "SELECT c FROM Configuracion c WHERE c.descripcion = :descripcion")})
public class Configuracion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONFIGURACION", nullable = false)
    private Integer idConfiguracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PARAMETRO", nullable = false, length = 45)
    private String parametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "VALOR", nullable = false, length = 255)
    private String valor;
    @Size(max = 255)
    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;

    public Configuracion() {
    }

    public Configuracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public Configuracion(Integer idConfiguracion, String parametro, String valor) {
        this.idConfiguracion = idConfiguracion;
        this.parametro = parametro;
        this.valor = valor;
    }

    public Integer getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracion != null ? idConfiguracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.idConfiguracion == null && other.idConfiguracion != null) || (this.idConfiguracion != null && !this.idConfiguracion.equals(other.idConfiguracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Configuracion[ idConfiguracion=" + idConfiguracion + " ]";
    }
    
}
