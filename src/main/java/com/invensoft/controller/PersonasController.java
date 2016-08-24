/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.EducacionFormal;
import com.invensoft.model.EducacionNoFormal;
import com.invensoft.model.EstadoCivil;
import com.invensoft.model.Familiar;
import com.invensoft.model.Pais;
import com.invensoft.model.Persona;
import com.invensoft.model.TipoIdentificacion;
import com.invensoft.service.IEstadoCivilService;
import com.invensoft.service.IPaisService;
import com.invensoft.service.IPersonaService;
import com.invensoft.service.ITipoIdentificacionService;
import com.invensoft.util.FacesUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author David
 */
@ManagedBean(name = "personasController")
@ViewScoped
public class PersonasController implements Serializable {

    private Persona persona;
    private Familiar familiar;
    private EducacionFormal educacionFormal;
    private EducacionNoFormal educacionNoFormal;
    private List<Persona> personasList;
    private List<TipoIdentificacion> tiposIdentificacionList;
    private List<Pais> paisesList;
    private List<EstadoCivil> estadosCivilesList;
    
    private List<Integer> rangoAniosPermitidos;
    private boolean showPersonasTable;
    

    //Services
    @ManagedProperty(value = "#{personaService}")
    private IPersonaService personasService;
    @ManagedProperty(value = "#{tipoIdentificacionService}")
    private ITipoIdentificacionService tipoIdentificacionService;
    @ManagedProperty(value = "#{paisService}")
    private IPaisService paisService;
    @ManagedProperty(value = "#{estadoCivilService}")
    private IEstadoCivilService estadoCivilService;

    /**
     * Creates a new instance of PersonasController
     */
    public PersonasController() {
    }

    @PostConstruct
    public void initController() {
        showPersonasTable = true;
        personasList = personasService.finAll();
        
        familiar = new Familiar();
        educacionFormal = new EducacionFormal();
        educacionNoFormal = new EducacionNoFormal();

        this.loadLists();
    }
    
    private void loadLists() {
        if (tiposIdentificacionList == null) {
            tiposIdentificacionList = tipoIdentificacionService.findAll();
        }

        if (paisesList == null) {
            paisesList = paisService.findAll();
        }

        if (estadosCivilesList == null) {
            estadosCivilesList = estadoCivilService.findAll();
        }
        
        if (rangoAniosPermitidos == null) {
            rangoAniosPermitidos = new LinkedList<>();
            int anioMaximo = Calendar.getInstance().get(Calendar.YEAR);
            
            for (int anio = anioMaximo; anio >= anioMaximo-100; anio--) {
                rangoAniosPermitidos.add(anio);
            }
        }
    }

    public void onViewPersonaDetailedInfo(Persona persona) {
        this.persona = persona;
        this.showPersonasTable = false;
    }

    public void onCreatePersona() {
        this.persona = new Persona();
        this.showPersonasTable = false;
    }

    public void onAddFamiliar() {
        if (this.persona.getFamiliaresList() == null) {
            this.persona.setFamiliaresList(new LinkedList<Familiar>());
        }

        for (TipoIdentificacion tipoIdentificacion : tiposIdentificacionList) {
            if (tipoIdentificacion.getIdTipoIdentificacion().equals(familiar.getTipoIdentificacion().getIdTipoIdentificacion())) {
                familiar.setTipoIdentificacion(tipoIdentificacion);
            }
        }
        
        this.familiar.setPersona(this.persona);
        this.persona.getFamiliaresList().add(this.familiar);
        this.familiar = new Familiar();
    }

    public void onViewFamiliarDetailedInfo(Familiar familiar) {
        this.familiar = familiar;
    }
    
    public void onAddEducacionFormal() {
        if (this.persona.getEducacionesFormalesList() == null) {
            this.persona.setEducacionesFormalesList(new LinkedList<EducacionFormal>());
        }
        
        this.educacionFormal.setPersona(this.persona);
        this.persona.getEducacionesFormalesList().add(this.educacionFormal);
        this.educacionFormal = new EducacionFormal();
    }
    
    public void onViewEducacionFormalDetail(EducacionFormal educacionFormal) {
        this.educacionFormal = educacionFormal;
    }
    
    public void onAddEducacionNoFormal() {
        if (this.persona.getEducacionesNoFormalesList() == null) {
            this.persona.setEducacionesNoFormalesList(new LinkedList<EducacionNoFormal>());
        }
        
        this.educacionNoFormal.setPersona(this.persona);
        this.persona.getEducacionesNoFormalesList().add(this.educacionNoFormal);
        this.educacionNoFormal = new EducacionNoFormal();
    }
    
    public void onViewEducacionNoFormalDetail(EducacionNoFormal educacionNoFormal) {
        this.educacionNoFormal = educacionNoFormal;
    }

    public void onSave() {
        try {
            //Actualizamos el tipo de identificacion
            for (TipoIdentificacion tipoIdentificacion : tiposIdentificacionList) {
                if (tipoIdentificacion.getIdTipoIdentificacion().equals(persona.getTipoIdentificacion().getIdTipoIdentificacion())) {
                    persona.setTipoIdentificacion(tipoIdentificacion);
                }
            }

            //Actualizamos el Pais de origen
            for (Pais pais : paisesList) {
                if (pais.getIdPais().equals(persona.getPaisOrigen().getIdPais())) {
                    persona.setPaisOrigen(pais);
                }
            }

            //Actualizamos el estado civil
            for (EstadoCivil estadoCivil : estadosCivilesList) {
                if (estadoCivil.getIdEstadoCivil().equals(persona.getEstadoCivil().getIdEstadoCivil())) {
                    persona.setEstadoCivil(estadoCivil);
                }
            }

            personasService.save(persona);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Persona> personasList) {
        this.personasList = personasList;
    }

    public boolean isShowPersonasTable() {
        return showPersonasTable;
    }

    public void setShowPersonasTable(boolean showPersonasTable) {
        this.showPersonasTable = showPersonasTable;
    }

    public IPersonaService getPersonasService() {
        return personasService;
    }

    public void setPersonasService(IPersonaService personasService) {
        this.personasService = personasService;
    }

    public List<TipoIdentificacion> getTiposIdentificacionList() {
        return tiposIdentificacionList;
    }

    public void setTiposIdentificacionList(List<TipoIdentificacion> tiposIdentificacionList) {
        this.tiposIdentificacionList = tiposIdentificacionList;
    }

    public ITipoIdentificacionService getTipoIdentificacionService() {
        return tipoIdentificacionService;
    }

    public void setTipoIdentificacionService(ITipoIdentificacionService tipoIdentificacionService) {
        this.tipoIdentificacionService = tipoIdentificacionService;
    }

    public IPaisService getPaisService() {
        return paisService;
    }

    public void setPaisService(IPaisService paisService) {
        this.paisService = paisService;
    }

    public List<Pais> getPaisesList() {
        return paisesList;
    }

    public void setPaisesList(List<Pais> paisesList) {
        this.paisesList = paisesList;
    }

    public List<EstadoCivil> getEstadosCivilesList() {
        return estadosCivilesList;
    }

    public void setEstadosCivilesList(List<EstadoCivil> estadosCivilesList) {
        this.estadosCivilesList = estadosCivilesList;
    }

    public IEstadoCivilService getEstadoCivilService() {
        return estadoCivilService;
    }

    public void setEstadoCivilService(IEstadoCivilService estadoCivilService) {
        this.estadoCivilService = estadoCivilService;
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }

    public List<Integer> getRangoAniosPermitidos() {
        return rangoAniosPermitidos;
    }

    public void setRangoAniosPermitidos(List<Integer> rangoAniosPermitidos) {
        this.rangoAniosPermitidos = rangoAniosPermitidos;
    }
    
    public EducacionFormal getEducacionFormal() {
        return educacionFormal;
    }

    public void setEducacionFormal(EducacionFormal educacionFormal) {
        this.educacionFormal = educacionFormal;
    }

    public EducacionNoFormal getEducacionNoFormal() {
        return educacionNoFormal;
    }

    public void setEducacionNoFormal(EducacionNoFormal educacionNoFormal) {
        this.educacionNoFormal = educacionNoFormal;
    }
    //</editor-fold>
}
