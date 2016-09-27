/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import com.invensoft.model.Cuestionario;

/**
 *
 * @author David
 */
public interface ICuestionarioDao extends IGenericDao<Cuestionario, Integer> {
    
    Cuestionario findCuestionarioSaludOcupacional();
    Cuestionario findCuestionarioDesarrolloProfesional();
    
}
