/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import com.invensoft.model.Persona;
import java.util.List;

/**
 *
 * @author David
 */
public interface IPersonaDao extends IGenericDao<Persona, Integer> {
    
    public List<Object[]> findTopPersonasXCategoriaLaboral(Integer top) throws Exception;
    public List<Object[]> findTopPersonasXLocalidad(Integer top) throws Exception;
    
}
