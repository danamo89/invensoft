/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import com.invensoft.model.EstadoCivil;
import java.util.List;

/**
 *
 * @author David
 */
public interface IEstadoCivilDao {
    
    List<EstadoCivil> findAll() throws Exception;
    
}
