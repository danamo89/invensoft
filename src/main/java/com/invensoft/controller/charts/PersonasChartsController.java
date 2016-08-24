/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller.charts;

import com.invensoft.model.EducacionFormal;
import com.invensoft.model.Pais;
import com.invensoft.model.Persona;
import com.invensoft.service.IPaisService;
import com.invensoft.service.IPersonaService;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author David
 */
@ManagedBean(name = "personasChartsController")
@ViewScoped
public class PersonasChartsController implements Serializable {

    private PieChartModel pieChartModel;
    private BarChartModel barChartModel;
    private List<Persona> personasList;
    private List<Pais> paisesList;

    //Services
    @ManagedProperty(value = "#{personaService}")
    private IPersonaService personaService;
    @ManagedProperty(value = "#{paisService}")
    private IPaisService paisService;

    /**
     * Creates a new instance of PersonasChartsController
     */
    public PersonasChartsController() {
    }

    @PostConstruct
    public void postConstruct() {
        if (personasList == null) {
            personasList = personaService.finAll();
        }

        if (paisesList == null) {
            paisesList = paisService.findAll();
        }

        this.loadChartData();
        this.createPieChartPersonasXFormacionFormal();
    }

    private void loadChartData() {
        try {
            int maximaCantidad = 0;
            barChartModel = new BarChartModel();

            ChartSeries serieTiposIdentificacion = new ChartSeries();
            serieTiposIdentificacion.setLabel("Tipos Identificacion");

            for (Pais pais : paisesList) {
                int cantidadDePersonas = 0;
                for (Persona persona : personasList) {
                    if (Objects.equals(pais.getIdPais(), persona.getPaisOrigen().getIdPais())) {
                        cantidadDePersonas++;
                    }
                }

                serieTiposIdentificacion.set(pais.getNombre(), cantidadDePersonas);
                
                if (cantidadDePersonas > maximaCantidad) {
                    maximaCantidad = cantidadDePersonas;
                }
            } 

            barChartModel.addSeries(serieTiposIdentificacion);

            barChartModel.setTitle("Cantidad de Empleados por Pais de Procedencia");
            barChartModel.setLegendPosition("ne");

            Axis xAxis = barChartModel.getAxis(AxisType.X);
            xAxis.setLabel("Paises");

            Axis yAxis = barChartModel.getAxis(AxisType.Y);
            yAxis.setLabel("Cantidad de Personas");
            yAxis.setMin(0);
            yAxis.setMax(maximaCantidad); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createPieChartPersonasXFormacionFormal() {
        try {
            pieChartModel = new PieChartModel();
            pieChartModel.setTitle("Cantidad de Personas por Educacion Formal");
            pieChartModel.setLegendPosition("w");
            pieChartModel.setShowDataLabels(true);
            
            int primario = 0, secundario = 0, terciario = 0, universitario = 0, postgrado = 0;
            
            for (Persona persona : personasList) {
                for (EducacionFormal educacionFormal : persona.getEducacionesFormalesList()) {
                    switch(educacionFormal.getNivelEstudio()) {
                        case "Primario": primario++; break;
                        case "Secundario": secundario++; break;
                        case "Terciario": terciario++; break;
                        case "Universitario": universitario++; break;
                        case "Postgrado": postgrado++; break;
                    }
                }
            }
            
            pieChartModel.set("Primario",primario);
            pieChartModel.set("Secundario",secundario);
            pieChartModel.set("Terciario",terciario);
            pieChartModel.set("Universitario",universitario);
            pieChartModel.set("Postgrado",postgrado);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Getter && Setter">
    public List<Persona> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Persona> personasList) {
        this.personasList = personasList;
    }

    public IPersonaService getPersonaService() {
        return personaService;
    }

    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    public void setPieChartModel(PieChartModel pieChartModel) {
        this.pieChartModel = pieChartModel;
    }

    public List<Pais> getPaisesList() {
        return paisesList;
    }

    public void setPaisesList(List<Pais> paisesList) {
        this.paisesList = paisesList;
    }

    public IPaisService getPaisService() {
        return paisService;
    }

    public void setPaisService(IPaisService paisService) {
        this.paisService = paisService;
    }
    
    //</editor-fold>
}
