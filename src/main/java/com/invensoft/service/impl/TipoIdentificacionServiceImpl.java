/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.ITipoIdentificacionDao;
import com.invensoft.model.TipoIdentificacion;
import com.invensoft.service.ITipoIdentificacionService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "tipoIdentificacionService")
public class TipoIdentificacionServiceImpl implements ITipoIdentificacionService {

    @Autowired
    private ITipoIdentificacionDao tipoIdentificacionDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<TipoIdentificacion> findAll() {
        try {
            return tipoIdentificacionDao.findAll();
        } catch (Exception ex) {
            messageBean.addError("Ha ocurrido un error", ex.getMessage());
            return null;
        }
    }
    
}
