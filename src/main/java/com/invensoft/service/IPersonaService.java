/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Persona;
import java.io.IOException;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author David
 */
public interface IPersonaService {
    Persona findById(Integer id);
    List<Persona> findAll();
    Persona save(Persona persona);
    void delete(Persona persona);
    
    XSSFWorkbook exportarPersonaExcel(Persona persona) throws IOException;
    XSSFWorkbook exportarPersonasAllExcel() throws IOException;
    public List<Object[]> findPersonasBasicData();
    public List<Object[]> findPersonasReportData();
    public List<Object[]> findTopPersonasXCategoriaLaboral(Integer top);
    public List<Object[]> findTopPersonasXLocalidad(Integer top);
    
}
