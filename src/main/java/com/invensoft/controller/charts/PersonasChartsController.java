/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.controller.charts;

import com.invensoft.model.EducacionFormal;
import com.invensoft.model.Pais;
import com.invensoft.model.Persona;
import com.invensoft.model.Sector;
import com.invensoft.service.IPaisService;
import com.invensoft.service.IPersonaService;
import com.invensoft.service.ISectorService;
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

    private PieChartModel pieChartPersonasXFormacionFormal;
    private PieChartModel pieChartPersonasXSector;
    private PieChartModel pieChartPersonasXCategoriaLaboral;
    private PieChartModel pieChartPersonasXLocalidad;
    private BarChartModel barChartModel;
    private List<Persona> personasList;
    private List<Object[]> personasXCategoriaLaboralList;
    private List<Object[]> personasXLocalidadList;
    private List<Pais> paisesList;
    private List<Sector> sectoresList;
    private Integer topPersonasXCategoriaLaboral;
    private Integer topPersonasXLocalidad;

    //Services
    @ManagedProperty(value = "#{personaService}")
    private IPersonaService personaService;
    @ManagedProperty(value = "#{paisService}")
    private IPaisService paisService;
    @ManagedProperty(value = "#{sectorService}")
    private ISectorService sectorService;

    /**
     * Creates a new instance of PersonasChartsController
     */
    public PersonasChartsController() {
    }

    @PostConstruct
    public void postConstruct() {
        if (personasList == null) {
            personasList = personaService.findAll();
        }

        if (paisesList == null) {
            paisesList = paisService.findAll();
        }

        if (sectoresList == null) {
            sectoresList = sectorService.findAll();
        }
        
        topPersonasXCategoriaLaboral = 20;
        
        if (personasXCategoriaLaboralList == null) {
            personasXCategoriaLaboralList = personaService.findTopPersonasXCategoriaLaboral(topPersonasXCategoriaLaboral);
        }
        
        topPersonasXLocalidad = 10;
        
        if (personasXLocalidadList == null) {
            personasXLocalidadList = personaService.findTopPersonasXLocalidad(topPersonasXLocalidad);
        }

        this.loadChartData();
        this.createPieChartPersonasXFormacionFormal();
        this.createPieChartPersonasXSector();
        this.createPieCharPersonasXCategoriaLaboral();
        this.createPieCharPersonasXLocalidad();
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
            pieChartPersonasXFormacionFormal = new PieChartModel();
            pieChartPersonasXFormacionFormal.setLegendPosition("w");
            pieChartPersonasXFormacionFormal.setShowDataLabels(true);

            int primario = 0, secundario = 0, terciario = 0, universitario = 0, postgrado = 0;

            for (Persona persona : personasList) {
                for (EducacionFormal educacionFormal : persona.getEducacionesFormalesList()) {
                    switch (educacionFormal.getNivelEstudio()) {
                        case "Primario":
                            primario++;
                            break;
                        case "Secundario":
                            secundario++;
                            break;
                        case "Terciario":
                            terciario++;
                            break;
                        case "Universitario":
                            universitario++;
                            break;
                        case "Postgrado":
                            postgrado++;
                            break;
                    }
                }
            }

            int total = primario + secundario + terciario + universitario + postgrado;
            pieChartPersonasXFormacionFormal.setTitle("Personas por Educacion Formal (" + total + "/" + personasList.size() + ")");

            pieChartPersonasXFormacionFormal.set("(" + primario + ") Primario", primario);
            pieChartPersonasXFormacionFormal.set("(" + secundario + ") Secundario", secundario);
            pieChartPersonasXFormacionFormal.set("(" + terciario + ") Terciario", terciario);
            pieChartPersonasXFormacionFormal.set("(" + universitario + ") Universitario", universitario);
            pieChartPersonasXFormacionFormal.set("(" + postgrado + ") Postgrado", postgrado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPieChartPersonasXSector() {
        try {
            pieChartPersonasXSector = new PieChartModel();
            pieChartPersonasXSector.setLegendPosition("w");
            pieChartPersonasXSector.setShowDataLabels(true);

            int total = 0;
            for (Sector sector : sectoresList) {
                int cantidad = 0;
                for (Persona persona : personasList) {
                    if (sector.getIdSector().equals(persona.getSector().getIdSector())) {
                        cantidad++;
                    }
                }
                
                pieChartPersonasXSector.set("("+cantidad+") "+sector.getNombre(),cantidad);
                total += cantidad;
            }
            
            pieChartPersonasXSector.setTitle("Cantidad de Personas por Sector (" + total + "/" + personasList.size() + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPieCharPersonasXCategoriaLaboral() {
        try {
            pieChartPersonasXCategoriaLaboral = new PieChartModel();
            pieChartPersonasXCategoriaLaboral.setLegendPosition("w");
            pieChartPersonasXCategoriaLaboral.setShowDataLabels(true);
            
            for (Object[] objects : personasXCategoriaLaboralList) {
                pieChartPersonasXCategoriaLaboral.set("("+objects[1].toString()+") "+objects[0],Integer.parseInt(objects[1].toString()));
            }
            
            pieChartPersonasXCategoriaLaboral.setTitle("Top "+topPersonasXCategoriaLaboral+" cantidad de personas por Categoria Laboral.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createPieCharPersonasXLocalidad() {
        try {
            pieChartPersonasXLocalidad = new PieChartModel();
            pieChartPersonasXLocalidad.setLegendPosition("w");
            pieChartPersonasXLocalidad.setShowDataLabels(true);
            
            for (Object[] objects : personasXLocalidadList) {
                pieChartPersonasXLocalidad.set("("+objects[1].toString()+") "+objects[0],Integer.parseInt(objects[1].toString()));
            }
            
            pieChartPersonasXLocalidad.setTitle("Top "+topPersonasXLocalidad+" cantidad de personas por Localidad.");
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

    public PieChartModel getPieChartPersonasXFormacionFormal() {
        return pieChartPersonasXFormacionFormal;
    }

    public void setPieChartPersonasXFormacionFormal(PieChartModel pieChartPersonasXFormacionFormal) {
        this.pieChartPersonasXFormacionFormal = pieChartPersonasXFormacionFormal;
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

    public PieChartModel getPieChartPersonasXSector() {
        return pieChartPersonasXSector;
    }

    public void setPieChartPersonasXSector(PieChartModel pieChartPersonasXSector) {
        this.pieChartPersonasXSector = pieChartPersonasXSector;
    }

    public List<Sector> getSectoresList() {
        return sectoresList;
    }

    public void setSectoresList(List<Sector> sectoresList) {
        this.sectoresList = sectoresList;
    }

    public ISectorService getSectorService() {
        return sectorService;
    }

    public void setSectorService(ISectorService sectorService) {
        this.sectorService = sectorService;
    }
    
    public PieChartModel getPieChartPersonasXCategoriaLaboral() {
        return pieChartPersonasXCategoriaLaboral;
    }

    public void setPieChartPersonasXCategoriaLaboral(PieChartModel pieChartPersonasXCategoriaLaboral) {
        this.pieChartPersonasXCategoriaLaboral = pieChartPersonasXCategoriaLaboral;
    }

    public List<Object[]> getPersonasXCategoriaLaboralList() {
        return personasXCategoriaLaboralList;
    }

    public void setPersonasXCategoriaLaboralList(List<Object[]> personasXCategoriaLaboralList) {
        this.personasXCategoriaLaboralList = personasXCategoriaLaboralList;
    }

    public Integer getTopPersonasXCategoriaLaboral() {
        return topPersonasXCategoriaLaboral;
    }

    public void setTopPersonasXCategoriaLaboral(Integer topPersonasXCategoriaLaboral) {
        this.topPersonasXCategoriaLaboral = topPersonasXCategoriaLaboral;
    }

    public PieChartModel getPieChartPersonasXLocalidad() {
        return pieChartPersonasXLocalidad;
    }

    public void setPieChartPersonasXLocalidad(PieChartModel pieChartPersonasXLocalidad) {
        this.pieChartPersonasXLocalidad = pieChartPersonasXLocalidad;
    }

    public List<Object[]> getPersonasXLocalidadList() {
        return personasXLocalidadList;
    }

    public void setPersonasXLocalidadList(List<Object[]> personasXLocalidadList) {
        this.personasXLocalidadList = personasXLocalidadList;
    }

    public Integer getTopPersonasXLocalidad() {
        return topPersonasXLocalidad;
    }

    public void setTopPersonasXLocalidad(Integer topPersonasXLocalidad) {
        this.topPersonasXLocalidad = topPersonasXLocalidad;
    }
    //</editor-fold>
}
