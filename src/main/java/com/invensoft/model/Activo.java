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
@Table(name = "activos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activo.findAll", query = "SELECT a FROM Activo a"),
    @NamedQuery(name = "Activo.findByIdActivo", query = "SELECT a FROM Activo a WHERE a.idActivo = :idActivo"),
    @NamedQuery(name = "Activo.findByFechaAlta", query = "SELECT a FROM Activo a WHERE a.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "Activo.findByNombre", query = "SELECT a FROM Activo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Activo.findByDescripcion", query = "SELECT a FROM Activo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Activo.findByCodigo", query = "SELECT a FROM Activo a WHERE a.codigo = :codigo")})
public class Activo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ACTIVO", nullable = false)
    private Integer idActivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ALTA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    @Size(max = 300)
    @Column(name = "DESCRIPCION", length = 300)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO", nullable = false, length = 45)
    private String codigo;
    @JoinColumn(name = "ID_TIPO_ACTIVO", referencedColumnName = "ID_TIPO_ACTIVO", nullable = false)
    @ManyToOne(optional = false)
    private TipoActivo tipoActivo;
    @JoinColumn(name = "ID_USUARIO_ALTA", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioAlta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivo")
    private List<RespuestaActivo> respuestaActivoList;

    public Activo() {
    }

    public Activo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public Activo(Integer idActivo, Date fechaAlta, String nombre, String codigo) {
        this.idActivo = idActivo;
        this.fechaAlta = fechaAlta;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Integer getIdActivo() {
        return idActivo;
    }

    public void setIdActivo(Integer idActivo) {
        this.idActivo = idActivo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoActivo getTipoActivo() {
        return tipoActivo;
    }

    public void setTipoActivo(TipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    public Usuario getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(Usuario usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    @XmlTransient
    public List<RespuestaActivo> getRespuestaActivoList() {
        return respuestaActivoList;
    }

    public void setRespuestaActivoList(List<RespuestaActivo> respuestaActivoList) {
        this.respuestaActivoList = respuestaActivoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivo != null ? idActivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Activo)) {
            return false;
        }
        Activo other = (Activo) object;
        if ((this.idActivo == null && other.idActivo != null) || (this.idActivo != null && !this.idActivo.equals(other.idActivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Activo[ idActivo=" + idActivo + " ]";
    }
    
}
