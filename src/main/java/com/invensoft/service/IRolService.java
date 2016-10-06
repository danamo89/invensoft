/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Rol;
import java.util.List;

/**
 *
 * @author dnavarro
 */
public interface IRolService {
    
    public List<Rol> findAll();
    public void save(Rol rol);
    public void save(List<Rol> rolesList);
    
}
