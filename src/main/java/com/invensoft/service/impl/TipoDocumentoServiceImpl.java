/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.ITipoDocumentoDao;
import com.invensoft.model.TipoDocumento;
import com.invensoft.service.ITipoDocumentoService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "tipoDocumentoService")
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

    @Autowired
    private ITipoDocumentoDao tipoDocumentoDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<TipoDocumento> findAll() {
        try {
            return this.tipoDocumentoDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el listado de tipos de documento", e.getMessage());
            return null;
        }
    }
    
}
