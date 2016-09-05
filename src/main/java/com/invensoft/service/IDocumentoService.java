/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service;

import com.invensoft.model.Documento;
import java.util.List;

/**
 *
 * @author Nanchy_2
 */
public interface IDocumentoService {
    
    public List<Documento> findAll();
    
}
