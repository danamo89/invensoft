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
    
    List<Persona> findAll();
    void save(Persona persona);
    
    XSSFWorkbook exportarPersonaExcel(Persona persona) throws IOException;
    XSSFWorkbook exportarPersonasAllExcel() throws IOException;
    
}
