/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller;

import com.invensoft.controller.helper.CuestionarioPainter;
import com.invensoft.model.Cuestionario;
import com.invensoft.model.CuestionarioSector;
import com.invensoft.model.Persona;
import com.invensoft.model.RespuestaPregunta;
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
import javax.faces.component.html.HtmlPanelGroup;

/**
 *
 * @author David
 */
@ManagedBean(name = "resultadosCuestionariosController")
@ViewScoped
public class ResultadosCuestionariosController implements Serializable {

    private Persona persona;
    private List<Persona> personasList;
    private CuestionarioPainter cuestionarioPainter;
    private HtmlPanelGroup saludOcupacionalRootPanelGroup;
    private List<RespuestaPregunta> listRespuestasPreguntasCuestionarioSaludOcupacional;
    private HtmlPanelGroup desarrolloProfesionalRootPanelGroup;
    private List<RespuestaPregunta> listRespuestasPreguntasCuestionarioDesarrolloProfesional;
    private HtmlPanelGroup cuestionarioRootPanelGroup;
    private List<RespuestaPregunta> listRespuestasPreguntasCuestionario;
    private boolean showResumenTable;

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
        cuestionarioPainter = new CuestionarioPainter();
        cuestionarioPainter.setDisableFields(true);
        cuestionarioRootPanelGroup = new HtmlPanelGroup();

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

    public void onViewPersonaDetailedInfo(Persona persona) {
        this.persona = persona;
        showResumenTable = false;
        loadCuestionarios();
    }

    private void loadCuestionarios() {
        try {
            Cuestionario cuestionarioSaludOcupacional = cuestionarioService.findCuestionarioSaludOcupacional();
            listRespuestasPreguntasCuestionarioSaludOcupacional = cuestionarioPainter.paint(saludOcupacionalRootPanelGroup, cuestionarioSaludOcupacional, respuestaPreguntaService.findBy(persona, cuestionarioSaludOcupacional));

            Cuestionario cuestionarioDesarrolloProfesional = cuestionarioService.findCuestionarioDesarrolloProfesional();
            listRespuestasPreguntasCuestionarioDesarrolloProfesional = cuestionarioPainter.paint(desarrolloProfesionalRootPanelGroup, cuestionarioDesarrolloProfesional, respuestaPreguntaService.findBy(persona, cuestionarioDesarrolloProfesional));

            cuestionarioRootPanelGroup.getChildren().clear();

            if (listRespuestasPreguntasCuestionario == null) {
                listRespuestasPreguntasCuestionario = new LinkedList<>();
            }

            for (CuestionarioSector cuestionarioSector : persona.getSector().getCuestionariosSectorList()) {
                listRespuestasPreguntasCuestionario.addAll(cuestionarioPainter.paint(cuestionarioRootPanelGroup, cuestionarioSector.getCuestionario(), respuestaPreguntaService.findBy(persona, cuestionarioSector.getCuestionario()), false));
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

    public CuestionarioPainter getCuestionarioPainter() {
        return cuestionarioPainter;
    }

    public void setCuestionarioPainter(CuestionarioPainter cuestionarioPainter) {
        this.cuestionarioPainter = cuestionarioPainter;
    }

    public HtmlPanelGroup getCuestionarioRootPanelGroup() {
        return cuestionarioRootPanelGroup;
    }

    public void setCuestionarioRootPanelGroup(HtmlPanelGroup cuestionarioRootPanelGroup) {
        this.cuestionarioRootPanelGroup = cuestionarioRootPanelGroup;
    }

    public List<RespuestaPregunta> getListRespuestasPreguntasCuestionario() {
        return listRespuestasPreguntasCuestionario;
    }

    public void setListRespuestasPreguntasCuestionario(List<RespuestaPregunta> listRespuestasPreguntasCuestionario) {
        this.listRespuestasPreguntasCuestionario = listRespuestasPreguntasCuestionario;
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

    public HtmlPanelGroup getSaludOcupacionalRootPanelGroup() {
        return saludOcupacionalRootPanelGroup;
    }

    public void setSaludOcupacionalRootPanelGroup(HtmlPanelGroup saludOcupacionalRootPanelGroup) {
        this.saludOcupacionalRootPanelGroup = saludOcupacionalRootPanelGroup;
    }

    public List<RespuestaPregunta> getListRespuestasPreguntasCuestionarioSaludOcupacional() {
        return listRespuestasPreguntasCuestionarioSaludOcupacional;
    }

    public void setListRespuestasPreguntasCuestionarioSaludOcupacional(List<RespuestaPregunta> listRespuestasPreguntasCuestionarioSaludOcupacional) {
        this.listRespuestasPreguntasCuestionarioSaludOcupacional = listRespuestasPreguntasCuestionarioSaludOcupacional;
    }

    public HtmlPanelGroup getDesarrolloProfesionalRootPanelGroup() {
        return desarrolloProfesionalRootPanelGroup;
    }

    public void setDesarrolloProfesionalRootPanelGroup(HtmlPanelGroup desarrolloProfesionalRootPanelGroup) {
        this.desarrolloProfesionalRootPanelGroup = desarrolloProfesionalRootPanelGroup;
    }

    public List<RespuestaPregunta> getListRespuestasPreguntasCuestionarioDesarrolloProfesional() {
        return listRespuestasPreguntasCuestionarioDesarrolloProfesional;
    }

    public void setListRespuestasPreguntasCuestionarioDesarrolloProfesional(List<RespuestaPregunta> listRespuestasPreguntasCuestionarioDesarrolloProfesional) {
        this.listRespuestasPreguntasCuestionarioDesarrolloProfesional = listRespuestasPreguntasCuestionarioDesarrolloProfesional;
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
    //</editor-fold>
}
