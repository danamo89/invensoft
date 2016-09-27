/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IConfiguracionDao;
import com.invensoft.model.Configuracion;
import com.invensoft.service.IConfiguracionService;
import com.invensoft.util.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "configuracionService")
public class ConfiguracionServiceImpl implements IConfiguracionService {

    @Autowired
    private IConfiguracionDao configuracionDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public Configuracion findBy(String parametro) {
        try {
            return configuracionDao.findBy(parametro);
        } catch (Exception e) {
            return null;
        }
    }
    
}
