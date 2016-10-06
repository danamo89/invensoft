/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invensoft.service.impl;

import com.invensoft.dao.IRolDao;
import com.invensoft.model.Rol;
import com.invensoft.service.IRolService;
import com.invensoft.util.MessageBean;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dnavarro
 */
@Service(value = "rolService")
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolDao rolDao;
    @Autowired
    private MessageBean messageBean;
    
    @Override
    public List<Rol> findAll() {
        try {
            return rolDao.findAllOrderBy("nombre");
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
            return new LinkedList<>();
        }
    }

    @Override
    public void save(Rol rol) {
        try {
            rolDao.save(rol);
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
        }
    }

    @Override
    public void save(List<Rol> rolesList) {
        try {
            rolDao.save(rolesList);
        } catch (Exception e) {
            messageBean.addError("Ha ocurrido un error", e.getMessage());
        }
    }
    
}
