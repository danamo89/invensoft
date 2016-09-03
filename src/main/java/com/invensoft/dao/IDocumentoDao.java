/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.dao;

import com.invensoft.model.Documento;
import java.util.List;

/**
 *
 * @author Nanchy_2
 */
public interface IDocumentoDao {
    
    public List<Documento> findAll();
    public List<Documento> findDocumentosTipoRRHH();
    public List<Documento> findDocumentosTipoPersonales();
    public List<Documento> findDocumentosTipoFamiliares();
    
}
