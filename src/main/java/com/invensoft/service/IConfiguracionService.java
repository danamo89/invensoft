/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Configuracion;

/**
 *
 * @author David
 */
public interface IConfiguracionService {
    
    Configuracion findBy(String parametro);
    
}
