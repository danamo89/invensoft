/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
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
import javax.persistence.PrePersist;
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
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByLegajo", query = "SELECT p FROM Persona p WHERE p.legajo = :legajo"),
    @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Persona.findByApellidos", query = "SELECT p FROM Persona p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Persona.findByNumeroIdentificacion", query = "SELECT p FROM Persona p WHERE p.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Persona.findByDomicilio", query = "SELECT p FROM Persona p WHERE p.domicilio = :domicilio"),
    @NamedQuery(name = "Persona.findByEmail", query = "SELECT p FROM Persona p WHERE p.email = :email"),
    @NamedQuery(name = "Persona.findByLocalidad", query = "SELECT p FROM Persona p WHERE p.localidad = :localidad"),
    @NamedQuery(name = "Persona.findByProvincia", query = "SELECT p FROM Persona p WHERE p.provincia = :provincia"),
    @NamedQuery(name = "Persona.findByNumeroDomicilio", query = "SELECT p FROM Persona p WHERE p.numeroDomicilio = :numeroDomicilio"),
    @NamedQuery(name = "Persona.findByPiso", query = "SELECT p FROM Persona p WHERE p.piso = :piso"),
    @NamedQuery(name = "Persona.findByDepartamento", query = "SELECT p FROM Persona p WHERE p.departamento = :departamento"),
    @NamedQuery(name = "Persona.findByCodigoPostal", query = "SELECT p FROM Persona p WHERE p.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "Persona.findByTelefonoParticular", query = "SELECT p FROM Persona p WHERE p.telefonoParticular = :telefonoParticular"),
    @NamedQuery(name = "Persona.findByCelular", query = "SELECT p FROM Persona p WHERE p.celular = :celular"),
    @NamedQuery(name = "Persona.findByCuil", query = "SELECT p FROM Persona p WHERE p.cuil = :cuil"),
    @NamedQuery(name = "Persona.findBySector", query = "SELECT p FROM Persona p WHERE p.sector = :sector"),
    @NamedQuery(name = "Persona.findByCategoriaLaboral", query = "SELECT p FROM Persona p WHERE p.categoriaLaboral = :categoriaLaboral"),
    @NamedQuery(name = "Persona.findByHorario", query = "SELECT p FROM Persona p WHERE p.horario = :horario"),
    @NamedQuery(name = "Persona.findByJefeInmediato", query = "SELECT p FROM Persona p WHERE p.jefeInmediato = :jefeInmediato"),
    @NamedQuery(name = "Persona.findByObraSocial", query = "SELECT p FROM Persona p WHERE p.obraSocial = :obraSocial"),
    @NamedQuery(name = "Persona.findByBanco", query = "SELECT p FROM Persona p WHERE p.banco = :banco"),
    @NamedQuery(name = "Persona.findByTieneCredencialArt", query = "SELECT p FROM Persona p WHERE p.tieneCredencialArt = :tieneCredencialArt")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PERSONA", nullable = false)
    private Integer idPersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LEGAJO", nullable = false, length = 45)
    private String legajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRES", nullable = false, length = 100)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_IDENTIFICACION", nullable = false, length = 45)
    private String numeroIdentificacion;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Size(max = 255)
    @Column(name = "LUGAR_NACIMIENTO", length = 255)
    private String lugarNacimiento;
    @Size(max = 255)
    @Column(name = "DOMICILIO", length = 255)
    private String domicilio;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "EMAIL", length = 150)
    private String email;
    @Size(max = 100)
    @Column(name = "LOCALIDAD", length = 100)
    private String localidad;
    @Size(max = 100)
    @Column(name = "PROVINCIA", length = 100)
    private String provincia;
    @Size(max = 5)
    @Column(name = "NUMERO_DOMICILIO", length = 5)
    private String numeroDomicilio;
    @Size(max = 2)
    @Column(name = "PISO", length = 2)
    private String piso;
    @Size(max = 5)
    @Column(name = "DEPARTAMENTO", length = 5)
    private String departamento;
    @Size(max = 10)
    @Column(name = "CODIGO_POSTAL", length = 10)
    private String codigoPostal;
    @Size(max = 45)
    @Column(name = "TELEFONO_PARTICULAR", length = 45)
    private String telefonoParticular;
    @Size(max = 245)
    @Column(name = "CELULAR", length = 245)
    private String celular;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUIL", nullable = false, length = 45)
    private String cuil;
    @Size(max = 100)
    @Column(name = "SECTOR", length = 100)
    private String sector;
    @Size(max = 45)
    @Column(name = "CATEGORIA_LABORAL", length = 45)
    private String categoriaLaboral;
    @Size(max = 45)
    @Column(name = "HORARIO", length = 45)
    private String horario;
    @Size(max = 255)
    @Column(name = "JEFE_INMEDIATO", length = 255)
    private String jefeInmediato;
    @Size(max = 100)
    @Column(name = "OBRA_SOCIAL", length = 100)
    private String obraSocial;
    @Size(max = 100)
    @Column(name = "BANCO", length = 100)
    private String banco;
    @Size(max = 1)
    @Column(name = "TIENE_CREDENCIAL_ART", length = 1)
    private String tieneCredencialArt;
    @Size(max = 100)
    @Column(name = "LUGAR_DE_TRABAJO", length = 100)
    private String lugarDeTrabajo;
    @JoinColumn(name = "ID_ESTADO_CIVIL", referencedColumnName = "ID_ESTADO_CIVIL", nullable = false)
    @ManyToOne(optional = false)
    private EstadoCivil estadoCivil;
    @JoinColumn(name = "ID_PAIS_ORIGEN", referencedColumnName = "ID_PAIS", nullable = false)
    @ManyToOne(optional = false)
    private Pais paisOrigen;
    @JoinColumn(name = "ID_TIPO_IDENTIFICACION", referencedColumnName = "ID_TIPO_IDENTIFICACION", nullable = false)
    @ManyToOne(optional = false)
    private TipoIdentificacion tipoIdentificacion;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "persona")
    private List<EducacionFormal> educacionesFormalesList;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "persona")
    private List<EducacionNoFormal> educacionesNoFormalesList;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "persona")
    private List<Familiar> familiaresList;

    public Persona() {
        this.estadoCivil = new EstadoCivil();
        this.paisOrigen = new Pais();
        this.tipoIdentificacion = new TipoIdentificacion();
        this.educacionesFormalesList = new LinkedList<>();
        this.educacionesNoFormalesList = new LinkedList<>();
        this.familiaresList = new LinkedList<>();
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String legajo, String nombres, String apellidos, String numeroIdentificacion, String cuil) {
        this.idPersona = idPersona;
        this.legajo = legajo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroIdentificacion = numeroIdentificacion;
        this.cuil = cuil;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNumeroDomicilio() {
        return numeroDomicilio;
    }

    public void setNumeroDomicilio(String numeroDomicilio) {
        this.numeroDomicilio = numeroDomicilio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefonoParticular() {
        return telefonoParticular;
    }

    public void setTelefonoParticular(String telefonoParticular) {
        this.telefonoParticular = telefonoParticular;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCategoriaLaboral() {
        return categoriaLaboral;
    }

    public void setCategoriaLaboral(String categoriaLaboral) {
        this.categoriaLaboral = categoriaLaboral;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTieneCredencialArt() {
        return tieneCredencialArt;
    }

    public void setTieneCredencialArt(String tieneCredencialArt) {
        this.tieneCredencialArt = tieneCredencialArt;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Pais getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(Pais paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public List<Familiar> getFamiliaresList() {
        return familiaresList;
    }

    public void setFamiliaresList(List<Familiar> familiaresList) {
        this.familiaresList = familiaresList;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }
    
    @XmlTransient
    public List<EducacionFormal> getEducacionesFormalesList() {
        return educacionesFormalesList;
    }

    public void setEducacionesFormalesList(List<EducacionFormal> educacionesFormalesList) {
        this.educacionesFormalesList = educacionesFormalesList;
    }
    
    @XmlTransient
    public List<EducacionNoFormal> getEducacionesNoFormalesList() {
        return educacionesNoFormalesList;
    }

    public void setEducacionesNoFormalesList(List<EducacionNoFormal> educacionesNoFormalesList) {
        this.educacionesNoFormalesList = educacionesNoFormalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.invensoft.model.Persona[ idPersona=" + idPersona + " ]";
    }
}
