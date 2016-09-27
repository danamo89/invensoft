/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IPuestoDao;
import com.invensoft.model.Puesto;
import com.invensoft.service.IPuestoService;
import com.invensoft.util.MessageBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nanchy-PC
 */
@Service(value = "puestoService")
public class PuestoServiceImpl implements IPuestoService {
    @Autowired
    private IPuestoDao puestoDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Puesto> findAll() {
        try {
            return puestoDao.findAll();
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error al consultar el listado de cuestionarios", e.getMessage());
            return null;
        }
    }
}
