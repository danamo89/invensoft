/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IDocumentoDao;
import com.invensoft.model.Documento;
import com.invensoft.service.IDocumentoService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nanchy_2
 */
@Service(value="documentoService")
public class DocumentoServiceImpl implements IDocumentoService {
    
    @Autowired
    IDocumentoDao documentoDao;
    
    @Autowired
    MessageBean messageBean;
    
    @Override
    public List<Documento> findAll() {
        try {
            return documentoDao.findAll();
        }catch(Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return null;
        }
    }
    
}
