/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Cuestionario;
import com.invensoft.model.Persona;
import com.invensoft.model.RespuestaPregunta;
import java.util.List;

/**
 *
 * @author David
 */
public interface IRespuestaPreguntaService {
    
    List<RespuestaPregunta> findBy(Persona persona, Cuestionario cuestionario);
    void save(List<RespuestaPregunta> list);
    public void ordernarCuestionario(Cuestionario cuestionario, Persona persona);
            
}
