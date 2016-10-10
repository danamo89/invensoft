/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Persona;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author David
 */
public interface IPersonaService {
    
    List<Persona> findAll();
    void save(Persona persona);
    void delete(Persona persona);
    
    void exportarExcel(Persona persona, String ubicacion, String nombreArchivo) throws IOException;
    
    public List<Object[]> findTopPersonasXCategoriaLaboral(Integer top);
    public List<Object[]> findTopPersonasXLocalidad(Integer top);
    
}
