/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Pais;
import java.util.List;

/**
 *
 * @author David
 */
public interface IPaisService {
    
    List<Pais> findAll();
    
}
