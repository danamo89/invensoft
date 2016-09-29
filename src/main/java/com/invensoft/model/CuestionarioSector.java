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
 * @author David
 */
@Entity
@Table(name = "cuestionarios_sectores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuestionarioSector.findAll", query = "SELECT c FROM CuestionarioSector c"),
    @NamedQuery(name = "CuestionarioSector.findByIdCuestionarioSector", query = "SELECT c FROM CuestionarioSector c WHERE c.idCuestionarioSector = :idCuestionarioSector")})
public class CuestionarioSector implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CUESTIONARIO_SECTOR", nullable = false)
    private Integer idCuestionarioSector;
    @JoinColumn(name = "ID_CUESTIONARIO", referencedColumnName = "ID_CUESTIONARIO", nullable = false)
    @ManyToOne(optional = false)
    private Cuestionario cuestionario;
    @JoinColumn(name = "ID_SECTOR", referencedColumnName = "ID_SECTOR", nullable = false)
    @ManyToOne(optional = false)
    private Sector sector;

    public CuestionarioSector() {
    }

    public CuestionarioSector(Integer idCuestionarioSector) {
        this.idCuestionarioSector = idCuestionarioSector;
    }

    public Integer getIdCuestionarioSector() {
        return idCuestionarioSector;
    }

    public void setIdCuestionarioSector(Integer idCuestionarioSector) {
        this.idCuestionarioSector = idCuestionarioSector;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuestionarioSector != null ? idCuestionarioSector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CuestionarioSector)) {
            return false;
        }
        CuestionarioSector other = (CuestionarioSector) object;
        if ((this.idCuestionarioSector == null && other.idCuestionarioSector != null) || (this.idCuestionarioSector != null && !this.idCuestionarioSector.equals(other.idCuestionarioSector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.CuestionarioSector[ idCuestionarioSector=" + idCuestionarioSector + " ]";
    }
    
}
