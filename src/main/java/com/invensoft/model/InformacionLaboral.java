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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nanchy_2
 */
@Entity
@Table(name = "informaciones_laborales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionLaboral.findAll", query = "SELECT i FROM InformacionLaboral i"),
    @NamedQuery(name = "InformacionLaboral.findById", query = "SELECT i FROM InformacionLaboral i WHERE i.idInformacionLaboral = :idInformacionLaboral"),
    @NamedQuery(name = "InformacionLaboral.findByIdCargo", query = "SELECT i FROM InformacionLaboral i WHERE i.cargo.idCargo = :idCargo"),
    @NamedQuery(name = "InformacionLaboral.findByPersona", query = "SELECT i FROM InformacionLaboral i WHERE i.persona.idPersona = :idPersona ")})
    

public class InformacionLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INFORMACION_LABORAL", nullable = false)
    private Integer idInformacionLaboral;
    
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID_CARGO", nullable = false)
    @ManyToOne(optional = false)
    private Cargo cargo;
    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID_PERSONA", nullable = false)
    @ManyToOne(optional=false)
    private Persona persona;
    
    
    public Integer getIdInformacionLaboral() {
        return idInformacionLaboral;
    }

    public void setIdInformacionLaboral(Integer idInformacionLaboral) {
        this.idInformacionLaboral = idInformacionLaboral;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
