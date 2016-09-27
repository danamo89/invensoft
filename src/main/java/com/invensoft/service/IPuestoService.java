/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Puesto;
import java.util.List;

/**
 *
 * @author Nanchy-PC
 */
public interface IPuestoService {
    public List<Puesto> findAll();
}
