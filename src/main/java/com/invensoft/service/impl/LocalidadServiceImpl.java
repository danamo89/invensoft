/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.ILocalidadDao;
import com.invensoft.model.Localidad;
import com.invensoft.service.ILocalidadService;
import com.invensoft.util.MessageBean;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author David
 */
@Service(value = "localidadService")
public class LocalidadServiceImpl implements ILocalidadService {

    @Autowired
    private ILocalidadDao localidadDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Localidad> findAll() {
        try {
            return localidadDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar las Localidades", e.getMessage());
            return null;
        }
    }

    @Override
    public List<Localidad> findByOrderBy(Map<String, Object> fieldsAndValues, String[] orders) {
        try {
            return localidadDao.findByOrderBy(fieldsAndValues, orders);
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar las Localidades", e.getMessage());
            return null;
        }
    }
    
}
