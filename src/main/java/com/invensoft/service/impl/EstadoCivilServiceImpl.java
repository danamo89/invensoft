/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IEstadoCivilDao;
import com.invensoft.model.EstadoCivil;
import com.invensoft.service.IEstadoCivilService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "estadoCivilService")
public class EstadoCivilServiceImpl implements IEstadoCivilService {
    
    @Autowired
    private IEstadoCivilDao estadoCivilDao;
    @Autowired
    private MessageBean messageBean;

    @Override
    public List<EstadoCivil> findAll() {
        try {
            return estadoCivilDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return null;
        }
    }
}
