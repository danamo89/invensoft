/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Localidad;
import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public interface ILocalidadService {
    
    List<Localidad> findAll();
    List<Localidad> findByOrderBy(Map<String, Object> fieldsAndValues, String[] orders);
    
}
