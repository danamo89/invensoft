/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.model.Cuestionario;
import com.invensoft.model.CuestionarioSector;
import com.invensoft.model.Persona;
import com.invensoft.service.ICuestionarioService;
import com.invensoft.service.IPersonaService;
import com.invensoft.service.IRespuestaPreguntaService;
import java.io.Serializable;
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
@ManagedBean(name = "resultadosCuestionariosController")
@ViewScoped
public class ResultadosCuestionariosController implements Serializable {

    private Persona persona;
    private List<Persona> personasList;
    private Cuestionario cuestionarioSaludOcupacional;
    private Cuestionario cuestionarioDesarrolloProfesional;
    private boolean showResumenTable;
    private boolean disableNext;
    private boolean disablePrevious;
    private Integer actualIndex;

    //Services
    @ManagedProperty(value = "#{personaService}")
    private IPersonaService personasService;
    @ManagedProperty(value = "#{cuestionarioService}")
    private ICuestionarioService cuestionarioService;
    @ManagedProperty(value = "#{respuestaPreguntaService}")
    private IRespuestaPreguntaService respuestaPreguntaService;

    /**
     * Creates a new instance of ResultadosCuestionariosController
     */
    public ResultadosCuestionariosController() {
    }

    @PostConstruct
    public void postConstruct() {
        showResumenTable = true;
        this.loadLists();
    }

    private void loadLists() {
        if (personasList == null) {
            personasList = personasService.findAll();

            if (personasList == null) {
                personasList = new LinkedList<>();
            }
        }
    }

//    public void onViewPersonaDetailedInfo(Persona persona) {
//        this.persona = persona;
//        showResumenTable = false;
//        loadCuestionarios();
//    }

    public void onViewPersonaDetailedInfo(Integer index) {
        this.persona = personasList.get(index);
        actualIndex = index;
        showResumenTable = false;
        validateButtons();
        loadCuestionarios();
    }
    
    public void onNext() {
        onViewPersonaDetailedInfo(actualIndex+1);
    }
    
    public void onPrevious() {
        onViewPersonaDetailedInfo(actualIndex-1);
    }
    
    private void validateButtons() {
        disableNext = false;
        disablePrevious = false;
        
        if (actualIndex == 0) {
            disablePrevious = true;
        } 
        
        if (actualIndex == personasList.size()-1) {
            disableNext = true;
        }
    }

    private void loadCuestionarios() {
        try {
            cuestionarioSaludOcupacional = cuestionarioService.findCuestionarioSaludOcupacional();
            respuestaPreguntaService.ordernarCuestionario(cuestionarioSaludOcupacional,persona);
            
            cuestionarioDesarrolloProfesional = cuestionarioService.findCuestionarioDesarrolloProfesional();
            respuestaPreguntaService.ordernarCuestionario(cuestionarioDesarrolloProfesional,persona);
            
            for (CuestionarioSector cuestionarioSector : persona.getSector().getCuestionariosSectorList()) {
                respuestaPreguntaService.ordernarCuestionario(cuestionarioSector.getCuestionario(),persona);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getters && Setters">
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

    public IPersonaService getPersonasService() {
        return personasService;
    }

    public void setPersonasService(IPersonaService personasService) {
        this.personasService = personasService;
    }

    public ICuestionarioService getCuestionarioService() {
        return cuestionarioService;
    }

    public void setCuestionarioService(ICuestionarioService cuestionarioService) {
        this.cuestionarioService = cuestionarioService;
    }

    public IRespuestaPreguntaService getRespuestaPreguntaService() {
        return respuestaPreguntaService;
    }

    public void setRespuestaPreguntaService(IRespuestaPreguntaService respuestaPreguntaService) {
        this.respuestaPreguntaService = respuestaPreguntaService;
    }

    public boolean isShowResumenTable() {
        return showResumenTable;
    }

    public void setShowResumenTable(boolean showResumenTable) {
        this.showResumenTable = showResumenTable;
    }

    public Cuestionario getCuestionarioSaludOcupacional() {
        return cuestionarioSaludOcupacional;
    }

    public void setCuestionarioSaludOcupacional(Cuestionario cuestionarioSaludOcupacional) {
        this.cuestionarioSaludOcupacional = cuestionarioSaludOcupacional;
    }

    public Cuestionario getCuestionarioDesarrolloProfesional() {
        return cuestionarioDesarrolloProfesional;
    }

    public void setCuestionarioDesarrolloProfesional(Cuestionario cuestionarioDesarrolloProfesional) {
        this.cuestionarioDesarrolloProfesional = cuestionarioDesarrolloProfesional;
    }

    public boolean isDisableNext() {
        return disableNext;
    }

    public void setDisableNext(boolean disableNext) {
        this.disableNext = disableNext;
    }

    public boolean isDisablePrevious() {
        return disablePrevious;
    }

    public void setDisablePrevious(boolean disablePrevious) {
        this.disablePrevious = disablePrevious;
    }

    public Integer getActualIndex() {
        return actualIndex;
    }

    public void setActualIndex(Integer actualIndex) {
        this.actualIndex = actualIndex;
    }
    //</editor-fold>
}
