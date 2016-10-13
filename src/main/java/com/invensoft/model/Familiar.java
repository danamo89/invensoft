/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "familiares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Familiar.findAll", query = "SELECT f FROM Familiar f"),
    @NamedQuery(name = "Familiar.findByIdFamiliar", query = "SELECT f FROM Familiar f WHERE f.idFamiliar = :idFamiliar"),
    @NamedQuery(name = "Familiar.findByNombre", query = "SELECT f FROM Familiar f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Familiar.findByFechaNacimiento", query = "SELECT f FROM Familiar f WHERE f.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Familiar.findByNumeroIdentificacion", query = "SELECT f FROM Familiar f WHERE f.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "Familiar.findByParentesco", query = "SELECT f FROM Familiar f WHERE f.parentesco = :parentesco"),
    @NamedQuery(name = "Familiar.findByUltimoAnioCursado", query = "SELECT f FROM Familiar f WHERE f.ultimoAnioCursado = :ultimoAnioCursado"),
    @NamedQuery(name = "Familiar.findByIdPersona", query = "SELECT f FROM Familiar f WHERE f.persona.idPersona = :idPersona")})
public class Familiar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_FAMILIAR", nullable = false)
    private Integer idFamiliar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE", nullable = false, length = 255)
    private String nombre;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Size(max = 45)
    @Column(name = "NUMERO_IDENTIFICACION", length = 45)
    private String numeroIdentificacion;
    @Size(max = 45)
    @Column(name = "PARENTESCO", length = 45)
    private String parentesco;
    @Column(name = "ULTIMO_ANIO_CURSADO")
    private String ultimoAnioCursado;
    @JoinColumn(name = "ID_TIPO_IDENTIFICACION", referencedColumnName = "ID_TIPO_IDENTIFICACION", nullable = false)
    @ManyToOne(optional = false)
    private TipoIdentificacion tipoIdentificacion;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public Familiar() {
        this.tipoIdentificacion = new TipoIdentificacion();
    }

    public Familiar(Integer idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public Familiar(Integer idFamiliar, String nombre) {
        this.idFamiliar = idFamiliar;
        this.nombre = nombre;
    }

    public Integer getIdFamiliar() {
        return idFamiliar;
    }

    public void setIdFamiliar(Integer idFamiliar) {
        this.idFamiliar = idFamiliar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getUltimoAnioCursado() {
        return ultimoAnioCursado;
    }

    public void setUltimoAnioCursado(String ultimoAnioCursado) {
        this.ultimoAnioCursado = ultimoAnioCursado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFamiliar != null ? idFamiliar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Familiar)) {
            return false;
        }
        Familiar other = (Familiar) object;
        if ((this.idFamiliar == null && other.idFamiliar != null) || (this.idFamiliar != null && !this.idFamiliar.equals(other.idFamiliar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Familiar[ idFamiliar=" + idFamiliar + " ]";
    }
    
}
