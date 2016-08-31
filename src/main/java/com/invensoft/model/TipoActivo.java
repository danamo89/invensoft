/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "tipos_activos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoActivo.findAll", query = "SELECT t FROM TipoActivo t"),
    @NamedQuery(name = "TipoActivo.findByIdTipoActivo", query = "SELECT t FROM TipoActivo t WHERE t.idTipoActivo = :idTipoActivo"),
    @NamedQuery(name = "TipoActivo.findByNombre", query = "SELECT t FROM TipoActivo t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoActivo.findByDescripcion", query = "SELECT t FROM TipoActivo t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoActivo.findByEstado", query = "SELECT t FROM TipoActivo t WHERE t.estado = :estado"),
    @NamedQuery(name = "TipoActivo.findByFechaAlta", query = "SELECT t FROM TipoActivo t WHERE t.fechaAlta = :fechaAlta")})
public class TipoActivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TIPO_ACTIVO", nullable = false)
    private Integer idTipoActivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Size(max = 255)
    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO", nullable = false, length = 1)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ALTA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoActivo")
    private List<Activo> activoList;
    @JoinColumn(name = "ID_CUESTIONARIO", referencedColumnName = "ID_CUESTIONARIO", nullable = false)
    @ManyToOne(optional = false)
    private Cuestionario cuestionario;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public TipoActivo() {
    }

    public TipoActivo(Integer idTipoActivo) {
        this.idTipoActivo = idTipoActivo;
    }

    public TipoActivo(Integer idTipoActivo, String nombre, String estado, Date fechaAlta) {
        this.idTipoActivo = idTipoActivo;
        this.nombre = nombre;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdTipoActivo() {
        return idTipoActivo;
    }

    public void setIdTipoActivo(Integer idTipoActivo) {
        this.idTipoActivo = idTipoActivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @XmlTransient
    public List<Activo> getActivoList() {
        return activoList;
    }

    public void setActivoList(List<Activo> activoList) {
        this.activoList = activoList;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoActivo != null ? idTipoActivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActivo)) {
            return false;
        }
        TipoActivo other = (TipoActivo) object;
        if ((this.idTipoActivo == null && other.idTipoActivo != null) || (this.idTipoActivo != null && !this.idTipoActivo.equals(other.idTipoActivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.TipoActivo[ idTipoActivo=" + idTipoActivo + " ]";
    }
    
}
