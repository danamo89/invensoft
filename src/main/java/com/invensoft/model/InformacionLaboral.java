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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "informaciones_laborales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionLaboral.findAll", query = "SELECT i FROM InformacionLaboral i"),
    @NamedQuery(name = "InformacionLaboral.findByIdInformacionLaboral", query = "SELECT i FROM InformacionLaboral i WHERE i.idInformacionLaboral = :idInformacionLaboral"),
    @NamedQuery(name = "InformacionLaboral.findByEmpresa", query = "SELECT i FROM InformacionLaboral i WHERE i.empresa = :empresa"),
    @NamedQuery(name = "InformacionLaboral.findByDomicilio", query = "SELECT i FROM InformacionLaboral i WHERE i.domicilio = :domicilio"),
    @NamedQuery(name = "InformacionLaboral.findByCargo", query = "SELECT i FROM InformacionLaboral i WHERE i.cargo = :cargo"),
    @NamedQuery(name = "InformacionLaboral.findByDesde", query = "SELECT i FROM InformacionLaboral i WHERE i.desde = :desde"),
    @NamedQuery(name = "InformacionLaboral.findByHasta", query = "SELECT i FROM InformacionLaboral i WHERE i.hasta = :hasta"),
    @NamedQuery(name = "InformacionLaboral.findByMotivoEgreso", query = "SELECT i FROM InformacionLaboral i WHERE i.motivoEgreso = :motivoEgreso")})
public class InformacionLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_informacion_laboral", nullable = false)
    private Integer idInformacionLaboral;
    @Size(max = 255)
    @Column(name = "EMPRESA", length = 255)
    private String empresa;
    @Size(max = 255)
    @Column(name = "DOMICILIO", length = 255)
    private String domicilio;
    @Size(max = 255)
    @Column(name = "CARGO", length = 255)
    private String cargo;
    @Column(name = "DESDE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date desde;
    @Column(name = "HASTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hasta;
    @Size(max = 255)
    @Column(name = "MOTIVO_EGRESO", length = 255)
    private String motivoEgreso;
    @JoinColumn(name = "id_persona", referencedColumnName = "ID_PERSONA")
    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    public InformacionLaboral() {
    }

    public InformacionLaboral(Integer idInformacionLaboral) {
        this.idInformacionLaboral = idInformacionLaboral;
    }

    public Integer getIdInformacionLaboral() {
        return idInformacionLaboral;
    }

    public void setIdInformacionLaboral(Integer idInformacionLaboral) {
        this.idInformacionLaboral = idInformacionLaboral;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public String getMotivoEgreso() {
        return motivoEgreso;
    }

    public void setMotivoEgreso(String motivoEgreso) {
        this.motivoEgreso = motivoEgreso;
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
        hash += (idInformacionLaboral != null ? idInformacionLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionLaboral)) {
            return false;
        }
        InformacionLaboral other = (InformacionLaboral) object;
        if ((this.idInformacionLaboral == null && other.idInformacionLaboral != null) || (this.idInformacionLaboral != null && !this.idInformacionLaboral.equals(other.idInformacionLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.InformacionLaboral[ idInformacionLaboral=" + idInformacionLaboral + " ]";
    }
    
}
